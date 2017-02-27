package com.eshop4j.tc.fee.common.strategy;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.eshop4j.tc.fee.common.FeeCalcDelegate;
import com.eshop4j.web.model.CimOrginfo;
import com.eshop4j.web.model.CimProduct;
import com.eshop4j.web.model.cim.CimProductInvestRecord;
import com.eshop4j.web.model.vo.InvestRecordWrapper;
import com.eshop4j.web.service.CimOrginfoService;
import com.eshop4j.web.service.CimProductInvestRecordService;
import com.eshop4j.web.service.CollectAware;
import com.eshop4j.web.service.InvestRecordAware;
import com.eshop4j.xoss.helper.ThreadpoolService;
/**
 * 募集期 佣金计算策略 募集期内默认不处理，募集成功后同意计算
 * @author ch
 *
 */
@Component
public class RaiseFeeCalcStrategy  implements FeeCalcStrategy,CollectAware{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RaiseFeeCalcStrategy.class);
	
	@Resource
	private CimProductInvestRecordService investRecordService;
	
	@Resource
	private CimOrginfoService orginfoService;

	@Resource
	private FeeCalcDelegate delegate;
	
	@Resource(name="cimOrgFeeGatherService")
	private InvestRecordAware cimOrgFeeGatherService;
	
	@Resource(name="feeCalcService")
	private InvestRecordAware feeCalcService;
	
	
	private List<InvestRecordAware> investRecordAwares;
	
	@PostConstruct
	private void init(){
		investRecordAwares = Lists.newArrayList(cimOrgFeeGatherService,feeCalcService);
	}
	
	@Override
	public boolean matchCalcStrategy(InvestRecordWrapper investRecordWrapper) {
		return ObjectUtils.equals(investRecordWrapper.getProductType(), 2);
	}
	

	@Override
	public void feeCalc(InvestRecordWrapper investRecord) throws Exception{
		String remark = "该产品仍在募集中,募集成功后将会显示佣金";
		investRecord.setRemark(remark);
		//募集期年化为0 否则募集成功计算佣金时年化叠加
		//募集期间理财师佣金为零, 募集成功后更新佣金
		investRecord.setProductDays(0);
		//交给委托类计算
		delegate.feeCalc(investRecord);
	}

	@Override
	public void collectProcess(final CimProduct product) {
	
		ThreadpoolService.execute(new Runnable() {
			
			@Override
			public void run() {
				try{
					LOGGER.info("collectProcess product = {}", product);
					//查询机构是否存在
					CimOrginfo orginfo = new CimOrginfo();
					orginfo.setOrgNumber(product.getOrgNumber());
					orginfo = orginfoService.selectOne(orginfo);
					if(orginfo == null ){
						LOGGER.warn("机构不存在 orgNumber="+product.getOrgNumber());
						return;
					} 
				
					List<InvestRecordWrapper> investRecordWrappers = investRecordService.getInvestRecordByProduct(product);
					if(investRecordWrappers ==null || investRecordWrappers.isEmpty()){
						LOGGER.warn("产品不存在投资记录  ProductId ={}",product.getProductId());
						return;
					}
					
					CimProductInvestRecord productInvestRecord = null;
					try{
						//更新到期日期
						productInvestRecord = new CimProductInvestRecord();
						productInvestRecord.setProductId(product.getProductId());
						Date endDate = DateUtils.addDays(com.eshop4j.core.util.DateUtils.parse(com.eshop4j.core.util.DateUtils.format(product.getSaleEndTime(), com.eshop4j.core.util.DateUtils.FORMAT_SHORT), com.eshop4j.core.util.DateUtils.FORMAT_SHORT), product.getDeadLineMinValue()+1);
						productInvestRecord.setEndTime(endDate);
						investRecordService.updateInvestRecordEndTimeByProductId(productInvestRecord);
					}catch(Exception e){
						LOGGER.warn("updateInvestRecordEndTimeByProductId  productInvestRecord = {},exception={}",productInvestRecord,e);
					}
					
					
					//String remark = StringUtils.join(new Object[]{orginfo.getName(),product.getProductName(),"募集成功佣金"}, "-");
					     	
					//按计费模式计算佣金
					for (InvestRecordWrapper investRecordWrapper : investRecordWrappers) {
						//investRecordWrapper.setRemark(remark);
						//计算实际期限
						int days = Days.daysBetween(LocalDate.fromDateFields(investRecordWrapper.getInvestTime()), LocalDate.fromDateFields(product.getSaleEndTime())).getDays();
						investRecordWrapper.setProductDays(product.getDeadLineMinValue()+days);
						//募集成功按固定期产品计算佣金
						investRecordWrapper.setProductType(1);
						
						for (InvestRecordAware investRecordAware : investRecordAwares) {
							try{
								investRecordAware.investRecordProcess(investRecordWrapper);
							}catch(Exception e){
								LOGGER.warn("collectProcess exception investRecordAware={},investRecordWrapper={},exception={}", new Object[]{investRecordAware,investRecordWrapper,e});
							}
						}
						
						/*cimOrgFeeGatherService.investRecordProcess(investRecordWrapper);
						
						feeCalcService.investRecordProcess(investRecordWrapper);*/
					}
				}catch(Exception e){
					LOGGER.error("collectProcess exception product={}", product,e);
				}
			}
		});
		
	}
}


