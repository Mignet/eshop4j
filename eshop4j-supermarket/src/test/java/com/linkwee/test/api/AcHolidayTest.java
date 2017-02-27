package com.linkwee.test.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.test.TestSupport;
import com.linkwee.web.dao.AcHolidayDataMapper;
import com.linkwee.web.model.acc.AcHolidayData;

public class AcHolidayTest extends TestSupport {
	
	@Resource
	private  AcHolidayDataMapper acHolidayDataMapper;
	
	@Test
	public  void insertDate(){
        String nianyue = "2017-12";		
      //检查具体日期是否为节假日，工作日对应结果为 0, 休息日对应结果为 1, 节假日对应的结果为 2；
        String data = "{'02':'1','03':'1','09':'1','10':'1','16':'1','17':'1','23':'1','24':'1','30':'1','31':'1'}";	
        TreeMap resultMap = new TreeMap();
		List<AcHolidayData> list = new ArrayList<AcHolidayData>();
		
	    // 将json字符串转换成jsonObject  
		JSONObject requestJson = JSONObject.parseObject(data);
		Set<String> it = requestJson.keySet(); 
		// 遍历jsonObject数据，添加到Map对象  
		for(String key:it){
		   String sign = String.valueOf(requestJson.get(key));  
		   resultMap.put(key, sign);  
        } 

		Iterator aa = resultMap.entrySet().iterator();  
        while (aa.hasNext()) {  
         // entry的输出结果如key0=value0等  
         Map.Entry entry =(Map.Entry) aa.next();  
         AcHolidayData ac =  new AcHolidayData();
		 ac.setNoWorkDay(nianyue+"-"+entry.getKey());
		 ac.setSign(entry.getValue().toString());
		 list.add(ac);
         Object key = entry.getKey();  
         Object value=entry.getValue();  
         System.out.println(entry);  
         System.out.println(key);  
         System.out.println(value);  
        }
		System.out.println(list);
		for(AcHolidayData d:list){
			acHolidayDataMapper.insertSelective(d);
			
		}
		
	}
	
}
