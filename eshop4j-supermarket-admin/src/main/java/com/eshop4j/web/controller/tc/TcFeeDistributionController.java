package com.eshop4j.web.controller.tc;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.eshop4j.core.export.ExportSupport;
import com.eshop4j.web.model.CimOrginfo;
import com.eshop4j.web.request.tc.TcFeeDistributionRequest;
import com.eshop4j.web.response.tc.FeeTopDetailResponse;
import com.eshop4j.web.response.tc.FeebalanceListResponse;
import com.eshop4j.web.service.CimOrginfoService;
import com.eshop4j.web.service.TcFeeDistributionService;
import com.eshop4j.xoss.util.RequestLogging;

@Controller
@RequestMapping("feeDistribution")
@RequestLogging("佣金")
public class TcFeeDistributionController {
	
	@Autowired
	private TcFeeDistributionService feeDistributionService;
	
	@Autowired
	private CimOrginfoService  orginfoService;
	
	@Autowired
	private ExportSupport exportSupport;
	
	private Map<String, String> keyMap = Maps.newLinkedHashMap();
	
	@PostConstruct
	private void init(){
		keyMap.put("lev1","0-50");
    	keyMap.put("lev2","50-200");
    	keyMap.put("lev3","200-500");
    	keyMap.put("lev4","500-1000");
    	keyMap.put("lev5","1000-5000");
    	keyMap.put("lev6","5000-1万");
    	keyMap.put("lev7","1-5万");
    	keyMap.put("lev8","5万以上");
	}
	
	 /**
     * 查看列表
     */
    @RequestMapping(value="initPage")
    public String initPage(Model model) {
    	Map<  String,Long> sortMap = Maps.newTreeMap();
    	Map<String, Long> datas = feeDistributionService.getFeeTotalData();
    	for (Entry<String, Long> entry: datas.entrySet()) {
    		sortMap.put(entry.getKey(),entry.getValue());
		}
    	Map<  String,Long> dataMap = Maps.newLinkedHashMap();
    	
    	for (Entry<String, Long> entry: sortMap.entrySet()) {
    		dataMap.put(keyMap.get(entry.getKey()),entry.getValue());
		}
    	
    	model.addAttribute("datas",dataMap);
    	model.addAttribute("dataKey",JSON.toJSONString(dataMap.keySet()));
    	model.addAttribute("dataValue",JSON.toJSONString(dataMap.values()));
    	CimOrginfo orginfo = new CimOrginfo();
    	orginfo.setStatus(1);
    	List<CimOrginfo> orgInfos = orginfoService.selectListByCondition(orginfo);
    	model.addAttribute("orgInfos",orgInfos);
    	DateTime now = DateTime.now();
    	String month = now.toString("yyyy-MM");
    	String begintime = StringUtils.join(new Object[]{month,"01"},'-');
    	model.addAttribute("begintime",begintime);
		String endtime = StringUtils.join(new Object[]{month,now.dayOfMonth().withMaximumValue().dayOfMonth().get()},'-');
		model.addAttribute("endtime",endtime);
    	return "datastatistics/feeDistribution";
    }
    
    @RequestMapping(value="topList")
    public String getList(TcFeeDistributionRequest req ,Model model){
		model.addAttribute("datas", feeDistributionService.getTop(req));
		return "datastatistics/topPage";
    }
    
    @RequestMapping("topListDownload")
    @RequestLogging("理财师佣金top100导出")
    public void feeDownload(TcFeeDistributionRequest req ,HttpServletRequest request, HttpServletResponse response){
    	Map<String, Object> datas = new LinkedHashMap<String, Object>();
    	List<FeeTopDetailResponse> list = feeDistributionService.getTop(req);
		datas.put("list", list==null?Collections.emptyList():list);
		datas.put("size", list==null?0l:Long.valueOf(list.size()));
    	exportSupport.export(request, response, "lcs/fee/feeDistribution.xls", datas);
    }
    
    @RequestMapping(value="feeDataDetailPage")
    public String feeDataDetailPage(TcFeeDistributionRequest req ,Model model){
		model.addAttribute("type",req.getType());
		List<Map<String, Long>> datas = feeDistributionService.getFeeDataDetail(req);
    	List<List<Object>> valuess = Lists.newArrayList();
    	for (Map<String, Long> map : datas) {
    		List<Object> values = Lists.newArrayListWithCapacity(map.size());
    		Map<String, Long> sortDataMap = Maps.newTreeMap();
    		for (Entry<String, Long> entry: map.entrySet()) {
        		if(ObjectUtils.equals(entry.getKey(), "showdate")){
        			values.add(entry.getValue());
        		}else{
        			sortDataMap.put(entry.getKey(), entry.getValue());
        		}
    		}
    		for (Entry<String, Long> entry: sortDataMap.entrySet()) {
    			values.add(entry.getValue());
			}
    		valuess.add(values);
		}
    	model.addAttribute("valuess",valuess);
    	model.addAttribute("titles",keyMap.values());
		return "datastatistics/feeDataDetailPage";
    }
    
    @RequestMapping(value="feeDataDetail")
    @ResponseBody
    public Object getFeeDataDetail(TcFeeDistributionRequest req){
    	Map<String, Object> result = Maps.newHashMap();
    	List<Map<String, Long>> datas = feeDistributionService.getFeeDataDetail(req);
    	Set<String> showDates = Sets.newTreeSet();
    	Map<String, List<Long>> dataMap = Maps.newLinkedHashMap();
    	
    	for (Map<String, Long> map : datas) {
    		for (Entry<String, Long> entry: map.entrySet()) {
        		if(ObjectUtils.equals(entry.getKey(), "showdate")){
        			showDates.add(String.valueOf(entry.getValue()));
        		}else{
        			
        			List<Long> values = dataMap.get(entry.getKey());
        			if(values==null){
        				values = Lists.newArrayList();
        				dataMap.put(entry.getKey(), values);
        			}
        			values.add(entry.getValue());
        		}
    		}
		}
    	Map<String, List<Long>> sortDataMap = Maps.newTreeMap();
    	for (Entry<String,  List<Long>> entry: dataMap.entrySet()) {
			sortDataMap.put(entry.getKey() , entry.getValue());
		}
    	dataMap = Maps.newLinkedHashMap();
    	for (Entry<String,  List<Long>> entry: sortDataMap.entrySet()) {
 			String key = keyMap.get(entry.getKey());
 			dataMap.put(key, entry.getValue());
 		}
    	result.put("dataKey",showDates);
    	result.put("dataType", dataMap.keySet());
    	result.put("datas", dataMap);
		return result;
    }
    
    @RequestMapping(value="feeDistributionRatio")
    @ResponseBody
    public Object getFeeDistributionRatio(TcFeeDistributionRequest req){
    	Map<String, Object> result = Maps.newHashMap();
    	Map<String, Float> data = feeDistributionService.getFeeDistributionRatio(req);
    	result.put("dataKey",data.keySet());
    	result.put("dataValue", data.values());
		return result;
    }

}
