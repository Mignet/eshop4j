package com.eshop4j.xoss.helper;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.eshop4j.web.service.SysConfigService;
import com.eshop4j.xoss.trace.Span;
import com.eshop4j.xoss.trace.Tracer;
import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.Sequence;


/**
 * DisruptorHelper 
 * @author ch
 *
 */

@Component
public class DisruptorHelper implements DisposableBean{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DisruptorHelper.class);
	
	private static final String RINGBUFFER_SIZE_KEY = "ringBuffer_size";
	/**
	 * 注入线程服务 ，使其初始化 
	 */
	@Autowired
	private ThreadpoolService threadpoolService;
	
	@Autowired
	private SysConfigService sysConfigService;
	
	public static class ElementWrap<T>{
		private T element;
		private Span curSpan;
		
		public ElementWrap() {
		
		}
		
		public ElementWrap(T element) {
			super();
			this.element = element;
		}


		public ElementWrap(T element, Span curSpan) {
			super();
			this.element = element;
			this.curSpan = curSpan;
		}
		
		
		public void setElement(T element) {
			this.element = element;
		}
		public void setCurSpan(Span curSpan) {
			this.curSpan = curSpan;
		}
		public T getElement() {
			return element;
		}

		public Span getCurSpan() {
			return curSpan;
		}
		
	}
	
	public static abstract class AbstractEventHandler<T> implements EventHandler<ElementWrap<T>>{

		@Override
		public void onEvent(ElementWrap<T> event, long sequence,boolean endOfBatch) throws Exception {
			if(null!=event.getCurSpan())
				Tracer.setCurrentSpan(event.getCurSpan());
			onEvent(event.getElement());
		}
		
		protected abstract void onEvent(T event) throws Exception ;
	}

	private static final int DEFAULT_BUFFER_SIZE = 2048;
	
	@SuppressWarnings("rawtypes")
	private static final ConcurrentMap<String, RingBuffer> maps = Maps.newConcurrentMap(); 
	
	private static final List<BatchEventProcessor<?>> processors = Lists.newCopyOnWriteArrayList();
	
	
	public <T> void  createRingBuffer(String key) throws Exception{
		try{
			@SuppressWarnings("unchecked")
			RingBuffer<ElementWrap<T>> ringBuffer = maps.get(key);
			if(ringBuffer==null){
				int buffer_size = DEFAULT_BUFFER_SIZE;
				String ringbuffer_size_key = sysConfigService.getValuesByKey(RINGBUFFER_SIZE_KEY);
				if(StringUtils.isNotBlank(ringbuffer_size_key)){
					buffer_size = Integer.parseInt(ringbuffer_size_key);
				}
				ringBuffer = RingBuffer.createSingleProducer(new EventFactory<ElementWrap<T>>(){
					@SuppressWarnings({ "unchecked", "rawtypes" })
					@Override
					public ElementWrap<T> newInstance() {
						return new ElementWrap();
					}
					
				},buffer_size,new BlockingWaitStrategy());
				maps.putIfAbsent(key, ringBuffer);
			}
		}catch(Exception e){
			throw e;
		}
		
		
	}
	
	public <T> void  addEventProcessor(String key,AbstractEventHandler<T>... eventHandlers) throws Exception{
		try{
			@SuppressWarnings("unchecked")
			RingBuffer<ElementWrap<T>> ringBuffer = maps.get(key);
			List<BatchEventProcessor<ElementWrap<T>>> processors = Lists.newArrayList();
			List<Sequence> sequences = Lists.newArrayList();		
			for (int i = 0; i < eventHandlers.length; i++) {
				BatchEventProcessor<ElementWrap<T>> processor = new BatchEventProcessor<ElementWrap<T>>(ringBuffer, ringBuffer.newBarrier(), eventHandlers[i]);
				processors.add( processor);
				sequences.add(processor.getSequence());
			}
			ringBuffer.addGatingSequences(sequences.toArray(new Sequence[0]));
			for (int i = 0; i < processors.size(); i++) {
				ThreadpoolService.execute(processors.get(i));
				LOGGER.info("DisruptorHelper addEventProcessor processor name: {},processors : {}", processors.get(i).getClass().getName(),processors);
			}
			DisruptorHelper.processors.addAll(processors);
		}catch(Exception e){
			throw e;
		}
		
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> void publish(String key, T t){
		RingBuffer ringBuffer = maps.get(key);
		if(ringBuffer==null) return;
		long seq = ringBuffer.next();
		try{
			ElementWrap elementWrap= (ElementWrap)ringBuffer.get(seq);
			elementWrap.setCurSpan(Tracer.getCurrentSpan());
			elementWrap.setElement(t);
		}finally{
			ringBuffer.publish(seq);
		}
		
	}
	
	@Override
	public void destroy() throws Exception {
		if(processors.isEmpty())return;
		for (BatchEventProcessor<?> batchEventProcessor : processors) {
			batchEventProcessor.halt();
		}
	}

}
