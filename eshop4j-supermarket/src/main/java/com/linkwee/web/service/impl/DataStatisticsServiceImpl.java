package com.linkwee.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.web.dao.DataStatisticsMapper;
import com.linkwee.web.request.DataStatisticsRequest;
import com.linkwee.web.service.DataStatisticsService;
import com.linkwee.xoss.helper.DateUtils;

@Service("dataStatisticsService")
public class DataStatisticsServiceImpl implements DataStatisticsService {

	private static final Logger logger = LoggerFactory.getLogger(DataStatisticsServiceImpl.class);
	
	@Resource
	private DataStatisticsMapper dataStatisticsMapper;

	@Override
	public Map<String, Object> queryInvestorLcsAndInvestment(DataStatisticsRequest dataStatisticsRequest) {
		// TODO Auto-generated method stub
		String dateFormat="yyyy-MM-dd";
		if(dataStatisticsRequest.getDateFormat() != null){
			dateFormat = dataStatisticsRequest.getDateFormat();
		}
		
		if(dateFormat.equals("yyyy-MM-dd")){
			dataStatisticsRequest.setDateFormat("%Y-%m-%d");
		}else{
			dataStatisticsRequest.setDateFormat("%Y-%m-%d %H");
		}
		
				
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> InvestorLcsAndInvestmentCount = dataStatisticsMapper.queryInvestorLcsAndInvestment(dataStatisticsRequest);
		resultMap.put("count", InvestorLcsAndInvestmentCount);		
		
		List<Map<String,Object>> newInvestorTable = dataStatisticsMapper.queryNewInvestorTable(dataStatisticsRequest);				
		List<Object> list =  new ArrayList<Object>();
		List<String> strList = DateUtils.getBetweeenTime(dataStatisticsRequest.getStartTime(), dataStatisticsRequest.getEndTime(), dateFormat);
		/**
		 * 手动补全投资人数
		 */
		for(String str:strList){
			boolean isExist = false;
			for(Map<String,Object> item:newInvestorTable){
				if(item.get("registerInvestorTime") !=null && item.get("registerInvestorTime").equals(str)){
					isExist = true;
					item.put("registerInvestorTime", changDateStringtoHour(str));
					list.add(item);
					continue;
				}
				
			}
			if(!isExist){//没有查询数据手动补0
				Map<String,Object> temp = new HashMap<String,Object>();
				str = changDateStringtoHour(str);
				temp.put("registerInvestorTime", str);
				temp.put("registerInvestorNum", 0);
				list.add(temp);
			}else{
				
			}
		}
		resultMap.put("investor", list);
		
		List<Map<String,Object>> newCfpTable = dataStatisticsMapper.queryNewCfpTable(dataStatisticsRequest);
		List<Object> CfpList =  new ArrayList<Object>();
		List<String> CfpStrList = DateUtils.getBetweeenTime(dataStatisticsRequest.getStartTime(), dataStatisticsRequest.getEndTime(), dateFormat);
		/**
		 * 手动补全理财师人数
		 */
		for(String str:CfpStrList){
			boolean isExist = false;
			for(Map<String,Object> item:newCfpTable){
				if(item.get("registerCfpTime") !=null && item.get("registerCfpTime").equals(str)){
					isExist = true;
					item.put("registerCfpTime", changDateStringtoHour(str));
					CfpList.add(item);
					continue;
				}
				
			}
			if(!isExist){//没有查询数据手动补0
				Map<String,Object> temp = new HashMap<String,Object>();
				str = changDateStringtoHour(str);
				temp.put("registerCfpTime", str);
				temp.put("registerCfpNum", 0);
				CfpList.add(temp);
			}else{
				
			}
		}
		resultMap.put("cfp", CfpList);
		
		List<Map<String,Object>> currentInvestment = dataStatisticsMapper.queryCurrentInvestment(dataStatisticsRequest);
		List<Object> investmentList =  new ArrayList<Object>();
		List<String> investmentStrList = DateUtils.getBetweeenTime(dataStatisticsRequest.getStartTime(), dataStatisticsRequest.getEndTime(), dateFormat);
		/**
		 * 手动补全投资记录
		 */
		for(String str:investmentStrList){
			boolean isExist = false;
			for(Map<String,Object> item:currentInvestment){
				if(item.get("investTime") !=null && item.get("investTime").equals(str)){
					isExist = true;
					item.put("investTime", changDateStringtoHour(str));
					investmentList.add(item);
					continue;
				}
				
			}
			if(!isExist){//没有查询数据手动补0
				Map<String,Object> temp = new HashMap<String,Object>();
				str = changDateStringtoHour(str);
				temp.put("investTime", str);
				temp.put("currentInvestment", 0);
				investmentList.add(temp);
			}else{
				
			}
		}
		resultMap.put("investment", investmentList);
		
		return resultMap;
	}
	
	public String changDateStringtoHour(String dateString){
		String reg = "^\\d{4}-(0[1-9]|1[1-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s([0-1][0-9]|2[0-3])$";
		Pattern p = Pattern.compile(reg);
		Matcher matcher = p.matcher(dateString);
		String resultDate=null;
		if(matcher.matches()){
			Date tempDate = DateUtils.parse(dateString,"yyyy-MM-dd HH");
			resultDate = DateUtils.format(tempDate, "HH:mm");
		}else{
			resultDate = dateString; 
		}
		return resultDate;
	}

}
