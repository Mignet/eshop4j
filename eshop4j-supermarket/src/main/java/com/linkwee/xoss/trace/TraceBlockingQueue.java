package com.linkwee.xoss.trace;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TraceBlockingQueue<T> extends LinkedBlockingQueue<T>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7775534895562767658L;



	class ElementWrap{
		private T element;
		private Span curSpan;
		
		public ElementWrap(T element, Span curSpan) {
			super();
			this.element = element;
			this.curSpan = curSpan;
		}

		public T getElement() {
			return element;
		}

		public Span getCurSpan() {
			return curSpan;
		}
		
	}
	
	LinkedBlockingQueue<ElementWrap> blockingQueue;
	

	public TraceBlockingQueue(int queueSize) {
		blockingQueue = new LinkedBlockingQueue<ElementWrap>(queueSize);
		
	}


	@Override
	public void put(T e) throws InterruptedException {
		blockingQueue.put(new ElementWrap(e, Tracer.getCurrentSpan()));
	}


	@Override
	public boolean offer(T e, long timeout, TimeUnit unit)throws InterruptedException {
		return blockingQueue.offer(new ElementWrap(e, Tracer.getCurrentSpan()), timeout, unit);
	}
	
	@Override
	public boolean offer(T e) {
		return blockingQueue.offer(new ElementWrap(e, Tracer.getCurrentSpan()));
	}

	@Override
	public T poll(long timeout, TimeUnit unit) throws InterruptedException {
		ElementWrap wrap =blockingQueue.poll(timeout, unit);
		if(null!=wrap.getCurSpan())
			Tracer.setCurrentSpan(wrap.getCurSpan());
		return wrap.getElement();
	}


	@Override
	public T poll() {
		ElementWrap wrap =blockingQueue.poll();
		if(null!=wrap.getCurSpan())
			Tracer.setCurrentSpan(wrap.getCurSpan());
		return wrap.getElement();
	}

	

	@Override
	public T take() throws InterruptedException {
		ElementWrap wrap =blockingQueue.take();
		if(null!=wrap.getCurSpan())
			Tracer.setCurrentSpan(wrap.getCurSpan());
		return wrap.getElement();
	}


	@Override
	public int size() {
		return blockingQueue.size();
	}
	
}
