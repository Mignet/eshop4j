package com.linkwee.web.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.acc.AddBankCardRequest;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.plugins.bill99.apipay.services.batchpayws.BatchPayServiceLocator;
import com.linkwee.plugins.bill99.entity.BankRequestBean;
import com.linkwee.plugins.bill99.entity.BankResponseBean;
import com.linkwee.plugins.bill99.util.MD5Util;
import com.linkwee.plugins.huafu.entity.AcVerifyIsCredit;
import com.linkwee.plugins.huafu.entity.AcVerifyRealName;
import com.linkwee.plugins.huafu.utils.Cipher3DES;
import com.linkwee.plugins.huafu.utils.HttpRequestClient;
import com.linkwee.plugins.huafu.utils.RsaSignCoder;
import com.linkwee.web.dao.AcAccountBindMapper;
import com.linkwee.web.dao.AcBalanceRecordMapper;
import com.linkwee.web.dao.AcBankCardInfoMapper;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.acc.AcAccountBind;
import com.linkwee.web.model.acc.AcBalanceRecord;
import com.linkwee.web.model.acc.AcBankCardInfo;
import com.linkwee.web.model.acc.Bill99Config;
import com.linkwee.web.model.acc.VerifyRealNameConfig;
import com.linkwee.web.service.AcBankCardInfoService;
import com.linkwee.web.service.AcWhiteCardService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.helper.DateUtils;
import com.linkwee.xoss.util.HttpClientUtil;


 /**
 * 
 * @描述：AcBankCardInfoService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("acBankCardInfoService")
public class AcBankCardInfoServiceImpl extends GenericServiceImpl<AcBankCardInfo, Long> implements AcBankCardInfoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AcBankCardInfoServiceImpl.class);
	
	@Resource
	private VerifyRealNameConfig verifyConfig;                                       
	                                       
	@Resource
	private Bill99Config bill99config;
	
	@Resource
	private AcBankCardInfoMapper acBankCardInfoMapper;
	
	@Resource
	private AcAccountBindMapper acAccountBindMapper;
	
	@Resource
	private AcBalanceRecordMapper acBalanceRecordMapper;
	
	@Resource
    private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private AcWhiteCardService acWhiteCardService;
	
	@Resource
	private SysConfigService sysConfigService;
	
	@Override
    public GenericDao<AcBankCardInfo, Long> getDao() {
        return acBankCardInfoMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- AcBankCardInfo -- 排序和模糊查询 ");
		Page<AcBankCardInfo> page = new Page<AcBankCardInfo>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<AcBankCardInfo> list = this.acBankCardInfoMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public void insertBankCard(AddBankCardRequest req) {
		//先从tac_bank_card_info表中查看有无该银行卡的信息
		AcBankCardInfo card = new AcBankCardInfo();
		card.setUserId(req.getUserId());
		AcBankCardInfo rescard = acBankCardInfoMapper.selectBankCardInfoByUserId(req.getUserId(),req.getBankCard());
		card.setBankCard(req.getBankCard());
		card.setBankCode(req.getBankCode());
		card.setMobile(req.getMobile());
		card.setIdCard(req.getIdCard());
		card.setBankName(req.getBankName());
		card.setBankId(req.getBankId());
		card.setKaiHuHang(req.getKaihuhang());
		card.setCity(req.getCity());
		card.setUserName(req.getUserName());
		
		AcAccountBind bind = new AcAccountBind(); 
		bind.setBankCard(req.getBankCard());
		bind.setBankCode(req.getBankCode());
		bind.setUserId(req.getUserId());
		bind.setBankName(req.getBankName());
		bind.setKaiHuHang(req.getKaihuhang());
		bind.setCity(req.getCity());
		bind.setIdCard(req.getIdCard());
		bind.setUserName(req.getUserName());
		bind.setReserveMobile(req.getMobile());
		bind.setUserType(req.getUserType());
		bind.setStatus(1);//绑卡
		if(rescard==null){
			card.setBankCardId(StringUtils.getUUID());
			card.setCreateTime(new Date());
			acBankCardInfoMapper.insertSelective(card);
			bind.setBankCardId(card.getBankCardId());//关联银行卡ID
		}
		else{
			bind.setBankCardId(rescard.getBankCardId());
			acBankCardInfoMapper.updateByPrimaryKeySelective(card);
		}
		AcAccountBind acbind = acAccountBindMapper.selectAccountByUserId(req.getUserId());
		if(acbind==null){
			acAccountBindMapper.insertSelective(bind);
		}else{
			acAccountBindMapper.updateByPrimaryKeySelective(bind);
		}
		//同步更改tcrm_user_info用户名字
		CrmUserInfo crm = new CrmUserInfo();
		crm.setUserId(req.getUserId());
		crm.setUserName(req.getUserName());
		crmUserInfoService.updateByUserId(crm);
	}

	@Override
	public Map<String, String> verifyRealName(AddBankCardRequest req) throws Exception {
		     Map<String, String> returnStr = new HashMap<String, String>();
		     
		    //实名验证紧急开关
		    String swicth = sysConfigService.getValuesByKey(SysConfigConstant.VERIFY_REAL_NAME_SWICTH);
			if("ON".equals(swicth)){
	        	returnStr.put("code","00");
	        	return returnStr;
	        }
	        
			/**
			 * 1、组装请求报文
			 */
			JSONObject headerJson = new JSONObject();
			JSONObject conditionJson = new JSONObject();
			//查询批次号 验证批次号  用户生成的唯一编号 唯一，不超过20位
			headerJson.put("qryBatchNo", DateUtils.getNow("yyyyMMddhhmmss")+getRandomString(3));//20160525151642123
			//平台分配的商户编号
			headerJson.put("userCode", verifyConfig.getUserCode());//"HFJK20160811000004"
			//应用编号 平台创建应用分配的唯一编号
			headerJson.put("sysCode", verifyConfig.getSysCode());//"HFJKAPP20160811000003"
			//查询原因  简单说明调用原由，可为空
			headerJson.put("qryReason", "提现");
			//查询日期 格式：yyyyMMdd，可为空
			headerJson.put("qryDate", DateUtils.getNow("yyyyMMdd"));
			//查询时间 格式：hhmmss，可为空
			headerJson.put("qryTime", DateUtils.getNow("hhmmss"));//格式：hhmmss
			
			//姓名 不超过20位
			conditionJson.put("realName", req.getUserName());
			//身份证号码 必须符合身份证标准规范
			conditionJson.put("idCard", req.getIdCard());
			//银行卡号码 银行卡不能为空且符合银行卡规范
			conditionJson.put("bankCard", req.getBankCard());
			//手机号码
//			conditionJson.put("mobile", req.getMobile());
			
			JSONObject allJson = new JSONObject();
			allJson.put("header", headerJson);
			allJson.put("condition", conditionJson);
			
			String data = allJson.toString();
			LOGGER.debug("请求报文:{}",data);
			
			String privateKey = verifyConfig.getPrivateKey();//"/SVSy1FU0DSS8qiJ5mtb5qj3";//平台提供的加密秘钥
			String vector = getRandomString(8);//用户自己生成的 随机8位数字或者字母
			//2、加密请求报文
			String encrData = Cipher3DES.encrypt(data, privateKey, vector);
			
			//3、对请求报文进行签名
			String signature=RsaSignCoder.sign(encrData, verifyConfig.getSignPrivateKey()); 
			
			/**
			 * 组装请求参数 调用实名认证接口
			 */
			//4、请求API对应的接口
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			//请求条件的加密报文
			parameters.add(new BasicNameValuePair("condition", encrData));
			//平台分配的用户编号
			parameters.add(new BasicNameValuePair("userCode", verifyConfig.getUserCode()));//"HFJK20160811000004"
			//加密请求报文的签名值
			parameters.add(new BasicNameValuePair("signature", signature));
			//加密偏移量，用户随机生成8位随机数字或字母
			parameters.add(new BasicNameValuePair("vector", vector));
			
			//四要素URL:http://api.hfdatas.com/superapi/super/auth/smrz4
			String reslult = HttpRequestClient.invokeHttp(verifyConfig.getHttpUrl(), parameters, "UTF-8");
			LOGGER.debug("返回报文:{}",reslult);
			
			
			/**
			 * 解析平台返回报文
			 */
			//5、获取接口返回报文
			Map<String, String> resultMap = new HashMap<String, String>();
		    // 将json字符串转换成jsonObject  
			JSONObject requestJson = JSONObject.parseObject(reslult);
			Set<String> it = requestJson.keySet();  
	        // 遍历jsonObject数据，添加到Map对象  
			for (String key:it){  
				 String value = String.valueOf(requestJson.get(key));  
	             resultMap.put(key, value); 
	        }  
			//加密请求报文的签名值
	        String sign = resultMap.get("signature");
	        //返回数据的加密报文
	        String datas = resultMap.get("contents");
	        
	        //6、验证加密内容报文的签名
	        boolean isTrue= true;
	        String str = null;
	       
	        if(sign!=null&&datas!=null){
	        	//确认解签是否通过  返回true则表示验证通过 下一步进行解密报文体加密数据（签名主要用于验证报文数据是否被篡改）
	        	isTrue=RsaSignCoder.verify(datas, verifyConfig.getSignPublicKey(), sign);
	        	 /**
				 * 验签及解密报文
				 */
	        	if(isTrue){
	        		str = Cipher3DES.decrypt(datas, privateKey, vector);
	        		//7、验签通过则进行解密返回的加密报文
					LOGGER.debug("解密结果:{}",str);
	        	}else{
					returnStr.put("code","999");
				}
	        }else{
	        	str = reslult;
	        }
	        
			JSONObject json = JSONObject.parseObject(str);
			@SuppressWarnings("static-access")
			AcVerifyRealName ver = json.toJavaObject(json, AcVerifyRealName.class);
			if(ver!=null&&ver.getData()!=null&&ver.getData().get(0).getRecord()!=null&&"00".equals(ver.getData().get(0).getRecord().get(0).getResCode())){
				LOGGER.debug("实名验证成功");
				returnStr.put("code",ver.getData().get(0).getRecord().get(0).getResCode());
			}else if(ver!=null&&ver.getData()!=null&&ver.getData().get(0).getRecord()!=null&&!"00".equals(ver.getData().get(0).getRecord().get(0).getResCode())){
				LOGGER.debug("实名验证错误:{}",ver.getData().get(0).getRecord().get(0).getResDesc());
				returnStr.put("code",ver.getData().get(0).getRecord().get(0).getResCode());
			}else if(ver!=null&&ver.getMsg()!=null){
				LOGGER.debug("错误代码:{} 错误代码描述:{}",ver.getMsg().getCode(),ver.getMsg().getCodeDesc());
				returnStr.put("code",ver.getMsg().getCode());
			}
				
		return returnStr;
	}
	
	/**
	 * 随机8位数字或者字母
	 * @param @param 
	 * @param binder
	 */
	public static String getRandomString(int length) { 
	    String base = "0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }

	@Override
	public AcBankCardInfo selectByBankCardId(String bankCardId) {
		return acBankCardInfoMapper.selectByBankCardId(bankCardId);
	}

	@Override
	public AcBankCardInfo selectByBankCard(String bankCard) {
		return acBankCardInfoMapper.selectByBankCard(bankCard);
	}

	@SuppressWarnings("static-access")
	@Override
	public Map<String, String> checkBankCardAndBankName(AddBankCardRequest req) throws Exception {
		Map<String,String> returnStr = new HashMap<String, String>();

		//先判断该银行卡是否属于白名单,这步的作用主要是应对接口出错的时候人工将银行卡添加到白名单,让用户绑卡
        boolean isexist = acWhiteCardService.queryAcWhiteCardByBankCard(req.getBankCard());
        if(isexist){
        	returnStr.put("code","00");
        	return returnStr;
        }
        StringBuilder strUrl = new StringBuilder();
        strUrl.append(verifyConfig.getJuheUrl()).append("?cardid=").append(req.getBankCard()).append("&key=").append(verifyConfig.getJuheKey());
//    	String str= HttpClientUtil.httpsGet("http://detectionBankCard.api.juhe.cn/bankCard?cardid="+req.getBankCard()+"&key=2c2c3f212db80bb0347e0f99c24f1c6e");
        String str= HttpClientUtil.httpsGet(strUrl.toString());
    	JSONObject json = JSONObject.parseObject(str);
    	AcVerifyIsCredit ver = null;
 		try {
 			ver = json.toJavaObject(json, AcVerifyIsCredit.class);
		} catch (Exception e) {
			returnStr.put("code","请输入正确银行卡号");
        	return returnStr;
		}
 		
 		
 		if(ver==null||ver.getResult()==null){
 			returnStr.put("code","银行属性验证返回异常");
        	return returnStr;
 		}
 		
// 		String bankName = ver.getResult().getBank();
		String bankCardType = ver.getResult().getNature();
		if(bankCardType!=null&&bankCardType.contains("贷记卡")){
			returnStr.put("code", "只支持绑定银行卡(借记卡)");
			return returnStr;
		}
		//判断银行名称是否一致
//		boolean flag = req.getBankName().contains(bankName);
//		returnStr.put("code", flag==false?"银行卡号与所选银行名称不一致":"00");
		returnStr.put("code","00");
 		LOGGER.debug(JSONArray.toJSONString(ver).toString());
		return returnStr;
	}
    
	@SuppressWarnings("static-access")
	@Override
	public Map<String, String> queryBankName(String bankCard) throws Exception {
		Map<String,String> returnStr = new HashMap<String, String>();

        StringBuilder strUrl = new StringBuilder();
        strUrl.append(verifyConfig.getJuheUrl()).append("?cardid=").append(bankCard).append("&key=").append(verifyConfig.getJuheKey());
        String str= HttpClientUtil.httpsGet(strUrl.toString());
    	JSONObject json = JSONObject.parseObject(str);
    	AcVerifyIsCredit ver = null;
 		try {
 			ver = json.toJavaObject(json, AcVerifyIsCredit.class);
		} catch (Exception e) {
			returnStr.put("code","请输入正确银行卡号");
        	return returnStr;
		}
 		
 		if(ver==null||ver.getResult()==null){
 			returnStr.put("code","银行属性验证返回异常");
        	return returnStr;
 		}
 		
 		String bankName = ver.getResult().getBank();
		returnStr.put("bankName",bankName);
		return returnStr;
	}
	
/*	@Override
	public Map<String,String>  checkBankCardAndBankName(AddBankCardRequest req) throws Exception {
		Map<String,String> returnStr = new HashMap<String, String>();
		//先判断该银行卡是否属于白名单,这步的作用主要是应对华付的接口出错的时候人工将银行卡添加到白名单,让用户绑卡
        boolean isexist = acWhiteCardService.queryAcWhiteCardByBankCard(req.getBankCard());
        if(isexist){
        	returnStr.put("code","00");
        	return returnStr;
        }
		//1、组装请求报文
		JSONObject headerJson = new JSONObject();
		JSONObject conditionJson = new JSONObject();
		headerJson.put("qryBatchNo", DateUtils.getNow("yyyyMMddhhmmss")+getRandomString(3));//验证批次号  用户生成的唯一编号
		headerJson.put("userCode", verifyConfig.getUserCode());//商户编号，即用户编号
		headerJson.put("sysCode", verifyConfig.getSysCode());//应用编号
		headerJson.put("qryReason", "领会银行卡属性查询");//原因
		headerJson.put("qryDate", DateUtils.getNow("yyyyMMdd"));//格式：yyyyMMdd
		headerJson.put("qryTime", DateUtils.getNow("hhmmss"));//格式：hhmmss
		conditionJson.put("bankCard", req.getBankCard());
		JSONObject allJson = new JSONObject();
		allJson.put("header", headerJson);
		allJson.put("condition", conditionJson);
		String data = allJson.toString();
		LOGGER.debug("请求报文:{}",data);
		
		String privateKey = "1rYfBx/42X+hc1vBO25UvEZd";//平台提供的加密秘钥
		String vector = getRandomString(8);//用户自己生成的 随机8位数字或者字母
		//2、加密请求报文
		String encrData = Cipher3DES.encrypt(data, privateKey, vector);
		
		//3、对请求报文进行签名
		String signature=RsaSignCoder.sign(encrData, verifyConfig.getSignPrivateKey()); 
		
		*//**
		 * 组装请求参数 调用实名认证接口
		 *//*
		//4、请求API对应的接口
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("condition", encrData));
		parameters.add(new BasicNameValuePair("userCode", verifyConfig.getUserCode()));
		parameters.add(new BasicNameValuePair("signature", signature));
		parameters.add(new BasicNameValuePair("vector", vector));//随机生成的8位数偏移量
		String reslult = HttpRequestClient.invokeHttp("http://api.hfdatas.com/superapi/super/bankcard/nature", parameters, "UTF-8");
		LOGGER.debug("返回报文:{}",reslult);
		
		
		*//**
		 * 解析平台返回报文
		 *//*
		//5、获取接口返回报文
		Map<String, String> resultMap = new HashMap<String, String>();
	    // 将json字符串转换成jsonObject  
		JSONObject requestJson = JSONObject.parseObject(reslult);
		Set<String> it = requestJson.keySet();  
        // 遍历jsonObject数据，添加到Map对象  
		for (String key:it){  
			 String value = String.valueOf(requestJson.get(key));  
             resultMap.put(key, value); 
        }  
		//加密请求报文的签名值
        String sign = resultMap.get("signature");
        //返回数据的加密报文
        String datas = resultMap.get("contents");
        
        //6、验证加密内容报文的签名
        boolean isTrue= true;
        String str = null;
       
        if(sign!=null&&datas!=null){
        	//确认解签是否通过  返回true则表示验证通过 下一步进行解密报文体加密数据（签名主要用于验证报文数据是否被篡改）
        	isTrue=RsaSignCoder.verify(datas, verifyConfig.getSignPublicKey(), sign);
        	 *//**
			 * 验签及解密报文
			 *//*
        	if(isTrue){
        		str = Cipher3DES.decrypt(datas, privateKey, vector);
        		//7、验签通过则进行解密返回的加密报文
				LOGGER.debug("解密结果:{}"+str);
        	}else{
				returnStr.put("code","验签及解密失败");
			}
        }else{
        	str = reslult;
        }
        
		JSONObject json = JSONObject.parseObject(str);
		@SuppressWarnings("static-access")
		AcVerifyRealName ver = json.toJavaObject(json, AcVerifyRealName.class);
		if(ver!=null&&ver.getMsg()!=null){
			//例：【02】-【未知银行卡号】 【01】-【银行卡号有误】
			LOGGER.debug("实名验证错误=【{}】,错误信息【{}】",req.getUserId()+req.getUserName(),ver.getMsg().getCode()+ver.getMsg().getCodeDesc());
			returnStr.put("code", ver.getMsg().getCodeDesc());
			return returnStr;
		}else if(ver!=null&&ver.getData()!=null&&ver.getData().get(0).getRecord()!=null){
			
			String bankName = ver.getData().get(0).getRecord().get(0).getBankName();
			String bankCardType = ver.getData().get(0).getRecord().get(0).getBankCardType();
			if(bankCardType!=null&&bankCardType.contains("贷记卡")){
				returnStr.put("code", "只支持绑定银行卡(借记卡)");
				return returnStr;
			}
			//判断银行名称是否一致
			boolean flag = req.getBankName().contains(bankName);
			returnStr.put("code", flag==false?"银行卡与银行名称不一致":"00");
		} 
		return returnStr;
	}*/

}
