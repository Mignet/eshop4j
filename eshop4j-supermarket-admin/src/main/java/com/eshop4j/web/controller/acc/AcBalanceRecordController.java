package com.eshop4j.web.controller.acc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.eshop4j.core.datatable.DataInfo;
import com.eshop4j.core.datatable.DataResult;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.datatable.ErrorField;
import com.eshop4j.core.result.Result;
import com.eshop4j.core.util.JsonUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.enums.AppTypeEnum;
import com.eshop4j.web.enums.MsgModuleEnum;
import com.eshop4j.web.enums.PersonalMsgTypeEnum;
import com.eshop4j.web.enums.SmsTypeEnum;
import com.eshop4j.web.model.CrmUserInfo;
import com.eshop4j.web.model.SmMessageQueue;
import com.eshop4j.web.model.SmMessageTemplate;
import com.eshop4j.web.model.acc.AcAccountBind;
import com.eshop4j.web.model.acc.AcBalanceRecord;
import com.eshop4j.web.model.mc.SysMsg;
import com.eshop4j.web.model.mc.SysPushMessage;
import com.eshop4j.web.service.AcAccountBindService;
import com.eshop4j.web.service.AcBalanceRecordService;
import com.eshop4j.web.service.CrmUserInfoService;
import com.eshop4j.web.service.SmMessageQueueService;
import com.eshop4j.web.service.SysMsgService;
import com.eshop4j.xoss.helper.DateUtils;
import com.eshop4j.xoss.helper.PushMessageHelper;
import com.eshop4j.xoss.helper.ThreadpoolService;
import com.eshop4j.xoss.interceptors.DateConvertEditor;
import com.eshop4j.xoss.util.ExcelReader;
import com.eshop4j.xoss.util.RequestLogging;

 /**
 * 
 * @描述： AcBalanceRecordController控制器
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年09月02日 16:04:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "acc/acbalancerecord")
@RequestLogging("AcBalanceRecordController控制器")
public class AcBalanceRecordController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AcBalanceRecordController.class);

	@Resource
	private AcBalanceRecordService acBalanceRecordService;
	
	@Resource
	private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private AcAccountBindService accountbindService;
	
	@Resource
	private PushMessageHelper pushMessageHelper;
	
	@Resource
	private SysMsgService sysMsgService;
	
	@Resource
	private SmMessageQueueService smMessageQueueService;
	
	/**
	 * 转换器
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

    /**
     * 查看列表
     */
    @RequestMapping(value="/grantlist",   method=RequestMethod.GET)
    @RequestLogging("查看列表页面")
    public String acBalanceRecord(Model model) {
    	return "acbalancerecord/acgrantrecord-list";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/grantlist", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getAcBalanceRecords(@RequestParam String  _dt_json) {
		LOGGER.debug("AcBalanceRecord list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = acBalanceRecordService.selectGrantByDatatables(dataTable);
		return tableReturn;
	}
    
    /**
     * 查看列表
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)
    @RequestLogging("查看列表页面")
    public String queryAcBalanceRecord(Model model) {
    	return "acbalancerecord/acbalancerecord-list";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn  queryAcBalanceRecords(@RequestParam String  _dt_json) {
		LOGGER.debug("AcBalanceRecord list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = acBalanceRecordService.selectByDatatables(dataTable);
		return tableReturn;
	}



    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@RequestLogging("CUD操作")
	public DataResult save(@RequestParam String rows) {
    	DataInfo df = JsonUtils.fromJsonToObject(rows, DataInfo.class); 
    	@SuppressWarnings("unchecked")
		Map<String,AcBalanceRecord> map =  (Map<String, AcBalanceRecord>) df.getData();
    	DataResult dr = new DataResult();
    	List<AcBalanceRecord> datas = new ArrayList<AcBalanceRecord>();
    	List<ErrorField> errors = new ArrayList<ErrorField>();
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();    
        Validator validator = factory.getValidator();   
        //下面用到bean属性copy，需要对日期进行转换
        DateConverter dateConverter = new DateConverter();
        dateConverter.setPattern("yyyy-MM-dd HH:mm:ss");
        ConvertUtils.register(dateConverter, java.util.Date.class); 
    	try {
			if(DataInfo.ACTION_CREATE.equals(df.getAction())){
				for (String key : map.keySet()) {
					AcBalanceRecord r = new AcBalanceRecord();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<AcBalanceRecord>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<AcBalanceRecord> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        }    
					this.acBalanceRecordService.insert(r);
				}
			}
			if(DataInfo.ACTION_EDIT.equals(df.getAction())){
				for (String key : map.keySet()) {
					AcBalanceRecord r = new AcBalanceRecord();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<AcBalanceRecord>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<AcBalanceRecord> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        } 
					this.acBalanceRecordService.update(r);
				}
			}
			if(DataInfo.ACTION_REMOVE.equals(df.getAction())){
				for (String key : map.keySet()) {
					this.acBalanceRecordService.delete(Long.parseLong(key));
				}
			}
		} catch (Exception e) {
			dr.setError(e.getMessage());
		}
    	dr.setData(datas);
    	return dr;
	}
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Result upload(@RequestParam MultipartFile file, @RequestParam String rewardName, @RequestParam String profitType) throws IOException{
		String fileName = file.getOriginalFilename();
		LOGGER.info("用户上传导入数据  文件名=【{}】",fileName);
		String excelType = fileName.substring(fileName.lastIndexOf(".")+1);
		List<Map<String,String>> list = null;
		try {
			list = ExcelReader.readExcel(file.getInputStream(), excelType);
		} catch (Exception e) {
			LOGGER.info("|UPLOAD ExcelReader.readExcel解析异常  文件名=【{}】",fileName);
			e.printStackTrace();
			return new Result(false,500,"EXCEL解析异常！");
		}
		
		Subject currentUser = ThreadContext.getSubject();//获取当前操作用户
		Double sussessSum = 0.0;
		//消息推送
		final Set<String> userIds = Sets.newConcurrentHashSet();
		final List<SysMsg> sysMsgs = Lists.newArrayList();
		final List<SysPushMessage> pushMsgs = Lists.newArrayList();
		final List<SmMessageQueue> messages = Lists.newArrayList();
		//消息模板
		SmMessageTemplate condit = new SmMessageTemplate();
		condit.setModuleId( MsgModuleEnum.ACTIVITYREWARDRELEASE.getValue());
		condit.setAppType(AppTypeEnum.INVESTOR.getKey());//提现模板 理财师和投资的一致
		SmMessageTemplate messageTmp = smMessageQueueService.queryMessageTemplete(condit);
		final int appType = Integer.parseInt(profitType);
		
		if(list!=null){
			List<AcBalanceRecord> balanceList = new ArrayList<AcBalanceRecord>();
			for(Map<String,String> obj:list){
				String mobile = obj.get("手机号码");
				if(mobile.length()!=11){
					return new Result(false,500,"【"+mobile+"】手机号码长度不对");
				}
				CrmUserInfo crm = crmUserInfoService.selectCrmUserInfoByMobile(mobile);
				if(crm==null){
					return new Result(false,500,"【"+mobile+"】用户不存在");
				}
				
				AcAccountBind ac = accountbindService.selectAccountByUserId(crm.getUserId());
				
				if(ac==null){
					return new Result(false,500,"【"+mobile+"】用户没有账号记录");
				} 
				
				String profit =  obj.get("收益");
				try {
					sussessSum = sussessSum + Double.parseDouble(profit);
				} catch (Exception e) {
					return new Result(false,500,"Excel表格【收益】有非法字符,请输入数字");
				}
				
				String serialNumber = String.format("UPLOAD-%s-%s-%s",new SimpleDateFormat("yyyyMMdd").format(new Date()),mobile,profit);
				
				List<AcBalanceRecord> baList = acBalanceRecordService.checkSameSerialNumber(serialNumber);
				
				if(baList.size()>0){
					return new Result(false,500,"【"+mobile+"】当天有相同金额发放,请确认是否重复发放");
				} 
				
				AcBalanceRecord re = new AcBalanceRecord();
				re.setBankCardId(ac.getBankCardId());
				re.setTransAmount(profit!=null?new BigDecimal(profit):null);
				re.setMobile(mobile);
				re.setCreateTime(new Date());
				re.setCreatePerson(currentUser != null?JSON.toJSONString(currentUser.getPrincipal()):null);
				re.setOrderId(StringUtils.getUUID());
				re.setTransDate(new Date());
				re.setTransType(Integer.parseInt(profitType));
				re.setUserId(crm.getUserId());
				re.setUserType(Integer.parseInt(profitType)==12||Integer.parseInt(profitType)==14||Integer.parseInt(profitType)==15?1:2);//投资者
				re.setRemark(rewardName);
				re.setCreateType(1);
				re.setSerialNumber(serialNumber);
				balanceList.add(re);
				
				//封装推送消息体
				userIds.add(crm.getUserId());
				if(messageTmp == null) continue;
				String content = String.format(messageTmp.getContent(),re.getUserType().intValue() == 1? "猎财" : "T呗",rewardName,BigDecimal.valueOf(Double.parseDouble(profit)).setScale(2, BigDecimal.ROUND_DOWN));
				sysMsgs.add(sysMsgService.fillSysMsg(re.getUserType(),PersonalMsgTypeEnum.PAYMENTRETURN,crm.getUserId(),content));
				messages.add(smMessageQueueService.fillSmMessageQueue(re.getUserType(),mobile,content,MsgModuleEnum.ACTIVITYREWARDRELEASE));
				pushMsgs.add(new SysPushMessage(crm.getUserId(),"活动奖励发放",content));
		    }
			try {
				acBalanceRecordService.grantProfit(balanceList);
			} catch (Exception e) {
				LOGGER.info("========发放奖励异常{}==============",e);
				return new Result(false,500,"发放奖励异常");
			}
			final Integer transType = balanceList.size()>0 ? balanceList.get(0).getTransType() : null;	
			//消息中心代码
			if(balanceList!= null && balanceList.size()>0){
				ThreadpoolService.execute(new Runnable() {			
					@Override
					public void run() {
						boolean needPushMsg = false;//关联理财师账户则推送通知栏
						if(transType.intValue() == 14 || transType.intValue() == 12){
							needPushMsg = true;
						}
						//短信通知
						smMessageQueueService.batchSendDiffContentMessageAndPmsg(messages,true,sysMsgs);
						//通知栏推送
						if(needPushMsg){ 	
							Map<String,Object> urlparam = Maps.newHashMap();
							urlparam.put("profitType", "3");
							urlparam.put("month", DateUtils.getMonth());
							pushMessageHelper.BatchSinglePush(AppTypeEnum.CHANNEL, SmsTypeEnum.LFEERECEIVED,urlparam,pushMsgs, null);
						}
						if(appType == 3 || appType == 4){//投资端
							Map<String,Object> urlparam = Maps.newHashMap();
							urlparam.put("typeValue", appType);
							pushMessageHelper.BatchSinglePush(AppTypeEnum.INVESTOR, SmsTypeEnum.REWARDBALANCE_INC,urlparam,pushMsgs, null);
						}
					}
				});
			}
		}
		DecimalFormat df = new DecimalFormat("0.00");
		LOGGER.info("========发放奖励成功============");
		return new Result(true,200,String.format("发放奖励成功,发放金额【%s】",df.format(sussessSum)));
		
	}
    
    
    /**
	 * 下载导入模板
	 * @param response
	 * @param request
	 * @throws FileNotFoundException
	 */
	@RequestMapping(value = "/downloadExcelTemplate")
	public void downloadImportTemplate(HttpServletResponse response,HttpServletRequest request) {
		LOGGER.info("下载发放奖励Excel模板");
		// 下载本地文件
		String fileName = "activity_profit.xlsx";
		// 读到流中
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF");
		InputStream inStream=null;
		OutputStream outStream=null;
		try {
			inStream = new FileInputStream(path+ "/xls/profit/activity_profit.xlsx");// 文件的存放路径
			response.reset();
			response.setContentType("multipart/form-data");
			response.setCharacterEncoding("UTF-8");
			response.addHeader("Content-Disposition", "attachment; filename=\""+ new String(fileName.getBytes(), "ISO8859-1") + "\"");
			outStream=response.getOutputStream();
			byte[] b = new byte[100];
			int len;
			while ((len = inStream.read(b)) > 0)
				outStream.write(b, 0, len);
		} catch (IOException e) {
			LOGGER.error("下载发放奖励Excel模板异常",e);
		}finally{
			try {
				if(inStream!=null){
					inStream.close();
				}
				if(outStream!=null){
					outStream.close();
				}
			} catch (IOException e) {
				LOGGER.error("下载发放奖励Excel模板关闭输入流时出现异常",e);
			}
		}
	}
	
	
	
}
