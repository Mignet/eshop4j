package com.linkwee.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.api.request.acc.MonthProfixDetailRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.tc.fee.model.TCFeedetail;
import com.linkwee.tc.fee.service.TCFeeDetailService;
import com.linkwee.web.dao.AcAccountBindMapper;
import com.linkwee.web.dao.AcBalanceRecordMapper;
import com.linkwee.web.model.acc.AcBalanceRecord;
import com.linkwee.web.model.acc.MonthProfixDetailListResp;
import com.linkwee.web.model.acc.MonthProfixTotalListResp;
import com.linkwee.web.service.AcBalanceRecordService;


 /**
 * 
 * @描述：AcBalanceRecordService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("acBalanceRecordService")
public class AcBalanceRecordServiceImpl extends GenericServiceImpl<AcBalanceRecord, Long> implements AcBalanceRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AcBalanceRecordServiceImpl.class);
	
	@Resource
	private AcBalanceRecordMapper acBalanceRecordMapper;
	
	@Resource
	private AcAccountBindMapper accountbindMapper;
	
	@Resource
	private TCFeeDetailService feeDetailService;
	
	@Override
    public GenericDao<AcBalanceRecord, Long> getDao() {
        return acBalanceRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- AcBalanceRecord -- 排序和模糊查询 ");
		Page<AcBalanceRecord> page = new Page<AcBalanceRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<AcBalanceRecord> list = this.acBalanceRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public PaginatorResponse<AcBalanceRecord> queryMyAccountDetails(Page<AcBalanceRecord> page,
			Map<String, Object> conditions) {
		PaginatorResponse<AcBalanceRecord> accountListResponse = new PaginatorResponse<AcBalanceRecord>();
		List<AcBalanceRecord> accountList = acBalanceRecordMapper.queryMyAccountDetails(page,conditions);
		accountListResponse.setDatas(accountList);
		accountListResponse.setValuesByPage(page);
		return accountListResponse;
	}
	
	@Override
	public PaginatorResponse<AcBalanceRecord> queryMyAccountDetails2(Page<AcBalanceRecord> page,
			Map<String, Object> conditions) {
		PaginatorResponse<AcBalanceRecord> accountListResponse = new PaginatorResponse<AcBalanceRecord>();
		List<AcBalanceRecord> accountList = acBalanceRecordMapper.queryMyAccountDetails2(page,conditions);
		accountListResponse.setDatas(accountList);
		accountListResponse.setValuesByPage(page);
		return accountListResponse;
	}

	@Override
	@Transactional
	public void grantProfit(List<AcBalanceRecord> balanList) {
		for(AcBalanceRecord ac:balanList){
			//插入交易明细
			acBalanceRecordMapper.insertSelective(ac);
		    //添加发放金额
			accountbindMapper.acGrantProfit(ac.getTransAmount().toString(),ac.getUserId());
			
		}
		
	}

	@Override
	public DataTableReturn selectGrantByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- AcBalanceRecord -- 排序和模糊查询 ");
		Page<AcBalanceRecord> page = new Page<AcBalanceRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<AcBalanceRecord> list = this.acBalanceRecordMapper.selectGrantBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<AcBalanceRecord> checkSameSerialNumber(String serialNumber) {
		return acBalanceRecordMapper.checkSameSerialNumber(serialNumber);
	}

	@Override
	public PaginatorResponse<MonthProfixDetailListResp> queryMonthProfixDetailList(MonthProfixDetailRequest req,
			Page<MonthProfixDetailListResp> page) {
		PaginatorResponse<MonthProfixDetailListResp> paginatorResponse = new PaginatorResponse<MonthProfixDetailListResp>();
		List<MonthProfixDetailListResp> list = acBalanceRecordMapper.queryMonthProfixDetailList(req,page);
		/*List<MonthProfixDetailListResponse> list = new ArrayList<MonthProfixDetailListResponse>();
		MonthProfixDetailListResponse re = new MonthProfixDetailListResponse();
		re.setAmount("150.00");
		re.setDeadline("3个月");
		re.setDescription("客户15083841185购买 天天牛A款90天 金额2000.00元");
		re.setFeeRate("2.5%");
		re.setProfixType("1");
		re.setProfixTypeName("销售佣金");
		re.setTime("2016-11-11 10:10");
		list.add(re);*/
		paginatorResponse.setDatas(list);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public PaginatorResponse<MonthProfixTotalListResp> queryProfixTotalList(MonthProfixDetailRequest req,
			Page<MonthProfixTotalListResp> page) {
		// TODO Auto-generated method stub
		PaginatorResponse<MonthProfixTotalListResp> paginatorResponse = new PaginatorResponse<MonthProfixTotalListResp>();
		List<MonthProfixTotalListResp> list = acBalanceRecordMapper.queryProfixTotalList(req,page);
		for (int i = 0; i < list.size(); i++) {
			MonthProfixTotalListResp resp = list.get(i);
			String thisMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
			Calendar c = Calendar.getInstance();
		    c.add(Calendar.MONTH, -1);
		    String lastMonth = new SimpleDateFormat("yyyy-MM").format(c.getTime());
			if(thisMonth.equals(resp.getMonth().trim())) {
				resp.setMonthDesc("本月收益");
				resp.setGrantDesc("下月15号发放");
			} else {
				TCFeedetail detail = feeDetailService.queryFeedetailByUserIdAndMonthLimitOne(req.getUserId(), resp.getMonth());
				if(detail == null) {
					resp.setGrantDesc("");
				} else {
					boolean flag = feeDetailService.isGrantFeeByMonth(resp.getMonth().replaceAll("-", ""));
					if(flag) {
						resp.setGrantDesc("已发放");
					} else {
						if(lastMonth.equals(resp.getMonth().trim())){
							resp.setGrantDesc("本月15号发放");
						} else {
							resp.setGrantDesc("");
						}
						
					}
				}
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					Date date = sdf.parse(resp.getMonth());
					int m = date.getMonth() + 1;
					resp.setMonthDesc(m + "月收益");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		paginatorResponse.setDatas(list);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public Double queryTotalIncome(String userId) {
		return acBalanceRecordMapper.queryTotalIncome(userId);
	}
	

}
