package com.eshop4j.web.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.NumberUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.dao.AcAccountBindMapper;
import com.eshop4j.web.dao.AcAccountTypeMapper;
import com.eshop4j.web.dao.AcBalanceRecordMapper;
import com.eshop4j.web.dao.AcBankCodeMappingMapper;
import com.eshop4j.web.dao.AcCityListMapper;
import com.eshop4j.web.dao.AcHolidayDataMapper;
import com.eshop4j.web.dao.AcProvinceListMapper;
import com.eshop4j.web.dao.AcWithdrawApplyMapper;
import com.eshop4j.web.enums.MsgModuleEnum;
import com.eshop4j.web.model.acc.AcAccountBind;
import com.eshop4j.web.model.acc.AcAccountRecharge;
import com.eshop4j.web.model.acc.AcBalanceRecord;
import com.eshop4j.web.model.acc.AcBankCardInfo;
import com.eshop4j.web.model.acc.AcCityList;
import com.eshop4j.web.model.acc.AcHolidayData;
import com.eshop4j.web.model.acc.AcWithdrawApply;
import com.eshop4j.web.model.acc.AcWithdrawTimes;
import com.eshop4j.web.request.acc.ResetPayPwdRequest;
import com.eshop4j.web.request.acc.TwoPwdRequest;
import com.eshop4j.web.request.acc.UserWithdrawRequest;
import com.eshop4j.web.response.acc.AcAccountTypeReponse;
import com.eshop4j.web.response.acc.AcBankCodeResponse;
import com.eshop4j.web.response.acc.CityInfoResponse;
import com.eshop4j.web.response.acc.ProvinceInfoResponse;
import com.eshop4j.web.response.acc.WithdrawBankCardResponse;
import com.eshop4j.web.service.AcAccountBindService;
import com.eshop4j.web.service.AcBankCardInfoService;
import com.eshop4j.xoss.api.AppRequestHead;
import com.eshop4j.xoss.helper.DateUtils;
import com.eshop4j.xoss.helper.JsonWebTokenHepler;
import com.eshop4j.xoss.helper.MessageHelper;
import com.eshop4j.xoss.util.AppUtils;
import com.eshop4j.xoss.util.MD5;
import com.eshop4j.xoss.util.RandomTextCreator;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月12日 19:10:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("accountbindService")
public class AcAccountBindServiceImpl extends GenericServiceImpl<AcAccountBind, Long> implements AcAccountBindService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AcAccountBindServiceImpl.class);
	//跟机构约定的手续费
	private static final Integer FEE = 1;
	
	@Resource
    private JsonWebTokenHepler jsonWebTokenHepler;
	
	@Resource
	private AcAccountBindMapper accountbindMapper;
	
	@Resource
	private AcCityListMapper acCityListMapper;
	
	@Resource
	private AcProvinceListMapper acProvinceListMapper;
	
	@Resource
	private AcBankCodeMappingMapper acBankCodeMappingMapper;
	
	@Resource
	private AcWithdrawApplyMapper acWithdrawApplyMapper;
	
	@Resource
	private AcAccountTypeMapper acAccountTypeMapper;
	
	@Resource
	private MessageHelper messageHelper;
	
	@Resource
	private  AcHolidayDataMapper acHolidayDataMapper;
	
	@Resource
	private AcBankCardInfoService acBankCardInfoService;
	
	@Resource
	private AcBalanceRecordMapper acBalanceRecordMapper;
	
	@Override
    public GenericDao<AcAccountBind, Long> getDao() {
        return accountbindMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- Bind -- 排序和模糊查询 ");
		Page<AcAccountBind> page = new Page<AcAccountBind>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<AcAccountBind> list = this.accountbindMapper.selectBySearchInfo(dt,page);
		//对银行预留手机号码、银行卡号、身份证进行加密
		for(AcAccountBind ac: list){
			ac.setReserveMobile(RandomTextCreator.encrypTion(ac.getReserveMobile()));
			ac.setBankCard(RandomTextCreator.encrypTion(ac.getBankCard()));
			ac.setIdCard(RandomTextCreator.encrypTion(ac.getIdCard()));
		}
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public boolean doVerifyPayPwdState(AppRequestHead head) {
		String userId = jsonWebTokenHepler.getUserIdByToken(head.getToken());
		AcAccountBind ab = accountbindMapper.selectAccountByUserId(userId);
		if(ab!=null&&ab.getTranPwd()!=null&&ab.getTranPwd().length()>0){
			return true;//密码
		}else{
			return false;//密码空
		}
	}

	@Override
	public boolean doVerifyPayPwdSame(TwoPwdRequest req,AppRequestHead head) {
		String userId = jsonWebTokenHepler.getUserIdByToken(head.getToken());
		AcAccountBind ab = accountbindMapper.selectAccountByUserId(userId);
		if(ab!=null&&req.getOldPwd()!=null&&MD5.crypt(req.getOldPwd()).equals(ab.getTranPwd())){
			return true;//源码正确
		}else{
			return false;//密码空
		}
	}

	@Override
	public boolean queryAuthentication(String userId) {
		AcAccountBind acbind = accountbindMapper.selectAccountByUserId(userId);
		if(acbind!=null&&"1".equals(acbind.getStatus().toString())){
			return true;//已绑定
		}else{
			return false;//未绑定
		}
	}

	@Override
	public List<ProvinceInfoResponse> queryAllProvince() {
		return acProvinceListMapper.queryAllProvince();
	}

	@Override
	public List<CityInfoResponse> queryCityByProvince(String  provinceId) {
		return acCityListMapper.selectByProvinceCode( provinceId);
	}

	@Override
	public List<AcBankCodeResponse> queryAllBank() {
		return acBankCodeMappingMapper.queryAllBank();
	}

	@Override
	public void initAccountBind(String userId,int userType) {
		AcAccountBind bind = new AcAccountBind();
		bind.setUserId(userId);
		AcAccountBind acbind = accountbindMapper.selectAccountByUserId(userId);
		if(acbind==null){
			bind.setUserType(userType);
			accountbindMapper.insertSelective(bind);
		}
	}

	@Override
	public WithdrawBankCardResponse queryWithdrawBankCard(String userId) {
		WithdrawBankCardResponse with = new WithdrawBankCardResponse();
		AcAccountBind ac = accountbindMapper.selectAccountByUserId(userId);
		if(ac!=null){
			with.setBankCard(ac.getBankCard());
			with.setBankName(ac.getBankName());
			with.setUserName(ac.getUserName());
			with.setTotalAmount(NumberUtils.getFormat(Double.parseDouble(ac.getTotalAmount()), "0.00"));
			with.setMobile(ac.getReserveMobile());
			with.setCity(ac.getCity());
			with.setKaiHuHang(ac.getKaiHuHang());
			//根据城市获取省份
			if(ac.getCity()!=null){
				AcCityList accity = new AcCityList();
				accity.setCityName(ac.getCity());
				AcCityList ci = acCityListMapper.selectOneByCondition(accity);
				with.setProvince(ci!=null?ci.getProvinceName():null);
			}
			with.setNeedkaiHuHang(ac.getKaiHuHang()==null?true:false);
			//只有提现有成功的情况下才不需要开户行
/*			int in = accountbindMapper.isNeedkaiHuHang(ac.getBankCardId());
			with.setNeedkaiHuHang(in>0?false:true);*/
		}
		//判断用户是否需要手续费
		with.setFee(needFee(userId)==true?FEE.toString():"0");
		with.setHasFee(needFee(userId));
		Integer times = queryLimitTimes(userId);
		with.setLimitTimes(times);
		//预计到账时间
		//先查询当日是否节假日
		with.setPaymentDate("预计"+isHoliday() +" 24点前");
		return with;
	}
	
	public String isHoliday(){
		SimpleDateFormat sf1 = new SimpleDateFormat(DateUtils.FORMAT_SHORT);
		String nowDay = sf1.format(new Date());
		AcHolidayData ho = new AcHolidayData();
		ho.setNoWorkDay(nowDay);
		AcHolidayData reho = acHolidayDataMapper.selectOneByCondition(ho);
		if(reho==null){
			int hours = new Date().getHours();
			if(hours<15){
				return DateUtils.getNow(DateUtils.FORMAT_SHORT);
			}else{
				//获取下一个工作日
				return nextWorkDay(1);
			}
		}else{//是节假日,获取下一个工作日
			return nextWorkDay(1);
		}
	}
	
	public String nextWorkDay(int in){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.FORMAT_SHORT);
		calendar.add(Calendar.DAY_OF_YEAR, in);
		Date date = calendar.getTime();
		String nextDay = sdf.format(date);
		AcHolidayData ho = new AcHolidayData();
		ho.setNoWorkDay(nextDay);
		AcHolidayData reho = acHolidayDataMapper.selectOneByCondition(ho);
		if(reho==null){
			return nextDay;
		}else{
			nextDay = nextWorkDay(in+1);
		}
		return nextDay;
	}

	@Override
	@Transactional
	public Map<String,String> userWithdrawApply(UserWithdrawRequest req) {
		Map<String,String> returnStr = new HashMap<String, String>();
		BigDecimal amount = new BigDecimal(req.getAmount().replaceAll(" ", "")); 

		//运营人员禁止提现
		int i = accountbindMapper.isForbiWithdrawUser(req.getUserId());
		
		if(i>0){
			returnStr.put("code","运营人员不能提现");
        	return returnStr;
		}
		
		AcAccountBind acBind = accountbindMapper.selectAccountByUserId(req.getUserId());
		//查询银行卡信息
		AcBankCardInfo card = acBankCardInfoService.selectByBankCardId(acBind.getBankCardId());
		//增加冻结操作
		AcWithdrawApply apply = new AcWithdrawApply();
		apply.setAmount(amount);
		apply.setBisName("提现申请");
		apply.setBisTime(new Date());
		
		
		BigDecimal fee = new BigDecimal(0);
		//是否需要手续费
		if(needFee(req.getUserId())){
			fee = new BigDecimal(FEE);
		}
		apply.setOrderId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		apply.setUserType(req.getUserType());
		apply.setStatus("1");//提现中，资金已冻结，待审核
		apply.setCreatedDate(new Date());
		apply.setUserId(req.getUserId());
		apply.setUserName(acBind.getUserName());
		apply.setFee(fee);
		apply.setTotalAmount(amount.add(fee));
		apply.setBankCardId(acBind.getBankCardId());
		apply.setMobile(card.getMobile());
		apply.setPaymentDate("预计"+isHoliday() +" 24点前");
		apply.setBankName(req.getBankName());
		apply.setCity(req.getCity());
		apply.setKaiHuHang(req.getKaihuhang());
		apply.setBankCode(req.getBankCode());
		
		BigDecimal oldFreezeAmount = new BigDecimal(acBind.getFreezeAmount());
        BigDecimal oldWithdrawAmount = new BigDecimal(acBind.getWithdrawAmount());
        BigDecimal oldTotalAmount = new BigDecimal(acBind.getTotalAmount());
        
        BigDecimal totalAmount = amount.add(fee);
        
        //判断账号余额是否满足提现  结果:-1小于 | 0等于| 1大于
        if(oldTotalAmount.compareTo(totalAmount)==-1){
        	returnStr.put("code","账号余额不满足提现");
        	return returnStr;
        };
        //保存提现信息
        acWithdrawApplyMapper.insertSelective(apply);
		//更新开户行、城市到绑卡信息表
		AcAccountBind ac = new AcAccountBind();
		ac.setUserId(req.getUserId());
		ac.setCity(req.getCity());
		ac.setKaiHuHang(req.getKaihuhang());
		//判断银行卡名称是否变动
//		ac.setBankName(req.getBankName());
//		ac.setBankCode(req.getBankCode());
		//处理冻结字段 交易金额  总金额
		ac.setWithdrawAmount(oldFreezeAmount.add(totalAmount).toString());
		ac.setFreezeAmount(oldWithdrawAmount.add(totalAmount).toString());
		ac.setTotalAmount(oldTotalAmount.subtract(totalAmount).toString());
		accountbindMapper.updateByPrimaryKeySelective(ac);
		//更新开户行、城市到银行卡信息表
		if(req.getCity()!=null&&req.getKaihuhang()!=null){
			AcBankCardInfo model = new AcBankCardInfo();
			model.setCity(req.getCity());
			model.setKaiHuHang(req.getKaihuhang());
			model.setUserId(req.getUserId());
			model.setBankCard(req.getBankCard());
//			model.setBankName(req.getBankName());
//			model.setBankCode(req.getBankCode());
			acBankCardInfoService.update(model);
		}
		returnStr.put("code","00");
    	return returnStr;
		
	}
	
	//判断本次提现是否需要手续费
	public boolean needFee(String userId){
		String startTime = getMonthFirstDay();
		String endTime = getMonthEndDay();
		AcWithdrawTimes time = acWithdrawApplyMapper.queryWithdrawTimes(userId,startTime,endTime);
		if(time!=null&&time.getTimes()>=3){
			if(!(time.getFee()>(time.getTimes()-3)*FEE)){
				return true;
			}
		}
		return false;
	}
	
	//免费剩余次数
    public Integer queryLimitTimes(String userId){
    	String startTime = getMonthFirstDay();
		String endTime = getMonthEndDay();
    	AcWithdrawTimes time = acWithdrawApplyMapper.queryWithdrawTimes(userId,startTime,endTime);
    	if(time!=null&&time.getTimes()<=3){
    		return 3-time.getTimes();
		}else if(time!=null&&(time.getFee()>(time.getTimes()-3)*FEE)){
			return 1;
		}
		return 0;
    	
    } 
	
	public String getMonthFirstDay(){
		Calendar curCal = Calendar.getInstance();
        SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        curCal.set(Calendar.DAY_OF_MONTH, 1);
        Date beginTime = curCal.getTime();
		return datef.format(beginTime) + " 00:00:00";
	};
	
	public String getMonthEndDay(){
		Calendar curCal = Calendar.getInstance();
        SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        curCal.set(Calendar.DATE, 1);
        curCal.roll(Calendar.DATE, -1);
        Date endTime = curCal.getTime();
		return datef.format(endTime) + " 23:59:59";
	};

	@Override
	public double queryAccountBalance(String userId) {
		AcAccountBind ac = accountbindMapper.selectAccountByUserId(userId);
		return Double.parseDouble(ac.getTotalAmount());
	}

	@Override
	public double queryWithdrawingAmount(String userId) {
		AcAccountBind ac = accountbindMapper.selectAccountByUserId(userId);
		return Double.parseDouble(ac.getWithdrawAmount());
	}

	@Override
	public void resetPayPwd(ResetPayPwdRequest req) {
        //重置密码token  校验
		AcAccountBind bind = new AcAccountBind();
		bind.setTranPwd(MD5.crypt(req.getPwd()));
		bind.setUserId(req.getUserId());
		accountbindMapper.updateByPrimaryKeySelective(bind);
	}

	@Override
	public AcAccountBind selectAccountByUserId(String userId) {
		return accountbindMapper.selectAccountByUserId(userId);
	}

	@Override
	public List<AcAccountTypeReponse> queryAllAccountType(String userType) {
		return acAccountTypeMapper.queryAllAccountType(userType);
	}

	@Override
	public boolean sendVcode(String mobile,AppRequestHead head) {
		//发送手机验证码
		boolean boo = messageHelper.sendVerifyCode(mobile, AppUtils.getAppType(head.getOrgNumber()), MsgModuleEnum.UPDATETRADEPWD);
	    return boo;
	}

	@Override
	public boolean checkVerifyCode(String mobile,String verifyCode) {
		boolean boo = messageHelper.checkVerifyCode(mobile, MsgModuleEnum.UPDATETRADEPWD,verifyCode);
	    return boo;
	}

	@Override
	public Double queryWithdrawSummary(String userId) {
		return acWithdrawApplyMapper.queryWithdrawSummary(userId);
		
	}

	@Override
	public boolean isbindBankcard(String userId) {
		AcAccountBind bind =  accountbindMapper.selectAccountByUserId(userId);
		if(bind!=null&&"1".equals(bind.getStatus().toString())){
			return true;
		}else{
			return false;
		}
	}

	@Override
	@Transactional
	public String accountRecharge(AcAccountRecharge recharge) throws Exception{
		LOGGER.debug(">>>>>>>>>>>充值starting>>>>>>>>>");
		//先更改账号金额
		AcAccountBind bind =  accountbindMapper.selectAccountByUserId(recharge.getUserId());
		BigDecimal totalAmount = new BigDecimal(bind.getTotalAmount());
		bind.setTotalAmount(totalAmount.add(recharge.getTransAmount()).toString());
		accountbindMapper.updateByPrimaryKeySelective(bind);
		//再插入银行流水记录
		AcBalanceRecord record = new AcBalanceRecord();
		String serialNumber = StringUtils.getUUID();
		record.setTransAmount(recharge.getTransAmount());
		record.setOrderId(recharge.getRedpacketId());
		record.setUserId(recharge.getUserId());
		record.setBankCardId(bind.getBankCardId());
		record.setCreateTime(new Date());
		record.setTransDate(new Date());
		record.setUserType(recharge.getUserType());
		record.setTransType(recharge.getTransType());
		record.setRemark(recharge.getRemark());
		record.setSerialNumber(serialNumber);
		acBalanceRecordMapper.insertSelective(record);
		LOGGER.debug(">>>>>>>>>>>充值完成>>>>>>>>>");
		return serialNumber;
	}

	@Override
	public void acAccountUnbund(String userId) {
		accountbindMapper.acAccountUnbund(userId);
	}

	@Override
	public AcBankCodeResponse queryBankByBankId(int bankId) {
		return acBankCodeMappingMapper.queryBankById(bankId);
	}

	@Override
	public AcBankCodeResponse queryBankByBankName(String bankname) {
		return acBankCodeMappingMapper.queryBankByName(bankname);
	}

}
