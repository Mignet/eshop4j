package com.linkwee.web.controller.crm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
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
import com.linkwee.core.export.ExportSupport;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.request.tc.TcFeeDistributionRequest;
import com.linkwee.web.response.tc.InvestmentTopDetailResponse;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.InvestmentDistributionService;
import com.linkwee.xoss.util.RequestLogging;

@Controller
@RequestMapping("investmentDistribution")
@RequestLogging("佣金")
public class InvestmentDistributionController {
	@Resource
	private ExportSupport exportSupport;
	@Autowired
	private InvestmentDistributionService investmentDistributionService;
	
	@Autowired
	private CimOrginfoService  orginfoService;
	
	private Map<String, String> keyMap = Maps.newHashMap();
	
	@PostConstruct
	private void init(){
		keyMap.put("lev1","0-0.1万");
    	keyMap.put("lev2","0.1-0.5万");
    	keyMap.put("lev3","0.5-1万");
    	keyMap.put("lev4","1-2万");
    	keyMap.put("lev5","2-5万");
    	keyMap.put("lev6","5-10万");
    	keyMap.put("lev7","10-50万");
    	keyMap.put("lev8","50万以上");
	}
	
	/**
     * 查看列表
     */
    @RequestMapping(value="initPage")
    public String initPage(Model model) {
    	Map<  String,Long> sortMap = Maps.newTreeMap();
    	Map<String, Long> datas = investmentDistributionService.getFeeTotalData();
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
    	return "investmentDistribution/feeDistribution";
    }
    
    @RequestMapping(value="topList")
    public String getList(TcFeeDistributionRequest req ,Model model){
		model.addAttribute("datas", investmentDistributionService.getTop(req));
		return "investmentDistribution/topPage";
    }
    
    @RequestMapping(value="feeDataDetailPage")
    public String feeDataDetailPage(TcFeeDistributionRequest req ,Model model){
		model.addAttribute("type",req.getType());
		List<Map<String, Long>> datas = investmentDistributionService.getFeeDataDetail(req);
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
    	Map<  String,String> sortMap = Maps.newTreeMap();
    	for (Entry<String, String> entry: keyMap.entrySet()) {
    		sortMap.put(entry.getKey(),entry.getValue());
		}
    	model.addAttribute("valuess",valuess);
    	model.addAttribute("titles",sortMap.values());
		return "investmentDistribution/feeDataDetailPage";
    }
    
    @RequestMapping(value="feeDataDetail")
    @ResponseBody
    public Object getFeeDataDetail(TcFeeDistributionRequest req){
    	Map<String, Object> result = Maps.newHashMap();
    	List<Map<String, Long>> datas = investmentDistributionService.getFeeDataDetail(req);
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
    	Map<String, Float> data = investmentDistributionService.getFeeDistributionRatio(req);
    	result.put("dataKey",data.keySet());
    	result.put("dataValue", data.values());
		return result;
    }
    
    @RequestMapping("topListDownload")
	@RequestLogging("导出用户投资top100")
	public void topListDownload(HttpServletRequest request, HttpServletResponse response,TcFeeDistributionRequest req) {
		String tempFileName = "lcs/investmentTop100.xls";
		List<InvestmentTopDetailResponse> list = investmentDistributionService.getTop(req);
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("size", Long.valueOf(list.size()));
		params.put("list", list);
		exportSupport.export( request,  response,  tempFileName,  params);
	}
    
    

}
