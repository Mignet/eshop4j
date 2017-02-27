package com.linkwee.core.export.strategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import com.linkwee.core.export.BeanCopyCallback;
import com.linkwee.core.export.DataProcessing;
import com.linkwee.core.export.POIProcessing;
import com.linkwee.core.export.QueryCallback;
import com.linkwee.core.export.bean.CellDetailsBean;
import com.linkwee.core.export.bean.PoiThreadExportDefinition;

public class MultipleQueriesConcurrentStrategy<T> extends  AbstractConcurrentStrategy {
	
	private POIProcessing poiProcessing;
	
	//通信队列
	public BlockingDeque<CellDetailsBean> blockingDeque ;
	
	/**
	 * 深拷贝回调函数
	 */
	private final BeanCopyCallback<T> copyCallback = new BeanCopyCallback<T>() {
		@Override
		public T copy() throws Exception {
			synchronized(t){
				ByteArrayOutputStream b = new ByteArrayOutputStream(1024);
				ObjectOutputStream objOut = new ObjectOutputStream(b);
			
				objOut.writeObject(t);
				
				ByteArrayInputStream  bin=new ByteArrayInputStream(b.toByteArray());
				ObjectInputStream objIn = new ObjectInputStream(bin);
				@SuppressWarnings("unchecked")
				T copyObj = (T)objIn.readObject();
				objOut.close();
				objIn.close();
				return copyObj;
			}
			
		}
	};
	 
	//查询实体
	private final T t;
	 
	private Object obj;
	private QueryCallback<T> callback;
	
	public MultipleQueriesConcurrentStrategy(Long size,QueryCallback<T> callback,T t,Object obj) {
		this(size,minSize,callback,t,obj);
	}
	
	public MultipleQueriesConcurrentStrategy(Long size,int minSize,QueryCallback<T> callback,T t,Object obj) {
		super(minSize,size);
		this.t = t;
		this.callback = callback;
		this.obj = obj;
		this.blockingDeque = new LinkedBlockingDeque<CellDetailsBean>(size.intValue());
		
	}
	
	@Override
	protected void before() {
		poiProcessing = new POIProcessing(blockingDeque);
		poiProcessing.start();
	}

	@Override
	protected void custom(int runThreadIndex,PoiThreadExportDefinition poiThreadExportDefinitio) {
		executorService.execute(new DataProcessing<T>(poiThreadExportDefinitio, blockingDeque,callback,copyCallback,obj,runThreadIndex,minSize));
	}

	@Override
	protected void after() {
		while(true){
			if(blockingDeque.isEmpty()){
				break;
			}
		}
	}
	
	


}
