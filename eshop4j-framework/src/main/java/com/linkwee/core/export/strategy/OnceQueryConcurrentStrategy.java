package com.linkwee.core.export.strategy;

import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.linkwee.core.export.PoiThread;
import com.linkwee.core.export.bean.PoiThreadExportDefinition;


public class OnceQueryConcurrentStrategy extends  AbstractConcurrentStrategy {
	
	private List<?> datas;
	private Map<String, Object> map;
	public OnceQueryConcurrentStrategy(Map<String, Object> map) {
		super(((Long)map.get("size")));
		this.map = map;
	}

	@Override
	protected void before() {
		for(String key : map.keySet()){
			if(StringUtils.hasLength(key) && !key.equals("size")){
				datas =(List<?>)map.get(key);
			}	
		}
	}

	@Override
	protected void custom(int runThreadIndex,PoiThreadExportDefinition poiThreadExportDefinitio) {
		poiThreadExportDefinitio.setDatas(datas);
		PoiThread poiThread = new PoiThread(poiThreadExportDefinitio);
		executorService.execute(poiThread);
	}

	@Override
	protected void after() {
		map=null;
	}	

}
