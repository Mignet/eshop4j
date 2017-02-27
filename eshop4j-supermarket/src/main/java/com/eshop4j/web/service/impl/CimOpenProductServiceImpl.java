package com.eshop4j.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.util.ApplicationUtils;
import com.eshop4j.openapi.request.ProductPushRequest;
import com.eshop4j.openapi.request.ProductUpdatRequest;
import com.eshop4j.web.model.CimOrginfo;
import com.eshop4j.web.model.CimProduct;
import com.eshop4j.web.model.CimProductStatistics;
import com.eshop4j.web.model.SysConfig;
import com.eshop4j.web.service.CimOpenProductService;
import com.eshop4j.web.service.CimOrginfoService;
import com.eshop4j.web.service.CimProductService;
import com.eshop4j.web.service.CimProductStatisticsService;
import com.eshop4j.web.service.CollectAware;
import com.eshop4j.web.service.SysConfigService;
import com.eshop4j.xoss.api.OpenRequestHead;
import com.eshop4j.xoss.util.OpenResponseUtil;

@Service("cimOpenProductService")
public class CimOpenProductServiceImpl implements CimOpenProductService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOpenProductServiceImpl.class);
	
	@Resource
	private CimProductService cimProductService;
	@Resource
	private CimProductStatisticsService cimProductStatisticsService;
	@Resource
	private CimOrginfoService cimOrginfoService;
	@Resource
	private CollectAware collectAware;
	@Resource
	private SysConfigService sysConfigService;

	@Override
	public BaseResponse pushProduct(ProductPushRequest productPushRequest,OpenRequestHead openRequestHead) {
		
		try {
			/**
			 * 查询该产品是否在已经在产品表存在  
			 * 若存在 则更新  产品
			 * 否则 添加产品
			 */
			CimProduct cimProduct =  new CimProduct();
			cimProduct.setOrgNumber(openRequestHead.getOrgNumber());
			cimProduct.setThirdProductId(productPushRequest.getThirdProductId());
			cimProduct = cimProductService.selectOne(cimProduct);
			
			/**
			 * 新建产品表对象用于接收传过来数据
			 */
			CimProduct cimProductNew = new CimProduct();
			BeanUtils.copyProperties(productPushRequest, cimProductNew);
			cimProductNew.setStatus(1);//设置产品状态为在售
			cimProductNew.setAuditStatus(2);//设置产品审核状态为待审核
			
			/**
			 * 新建产品统计表对象用于接收传过来数据
			 */
			CimProductStatistics cimProductStatisticsNew = new CimProductStatistics();
			cimProductStatisticsNew.setBuyedTotalMoney(productPushRequest.getBuyedTotalMoney());//产品被投资总额
			cimProductStatisticsNew.setBuyedTotalPeople(productPushRequest.getBuyedTotalPeople());//产品已投资人数
			
			if(null == cimProduct){//插入产品信息
				
				/**
				 * 插入产品信息前，判断是否需要更新机构表 
				 * 1：最小年化收益 2:最大年化收益 3:最小产品期限4:最大产品期限
				 * 5:产品最小期限天数 自定义显示6:产品最大期限天数 自定义显示
				 */
				CimOrginfo cimOrginfo =  new CimOrginfo();
				cimOrginfo.setOrgNumber(openRequestHead.getOrgNumber());
				cimOrginfo = cimOrginfoService.selectOne(cimOrginfo);
				
				CimOrginfo cimOrginfoNew =  new CimOrginfo();
				cimOrginfoNew.setId(cimOrginfo.getId());
				//cimOrginfoNew.setOrgNumber(cimOrginfo.getOrgNumber());
				boolean isNeedUpdateCimOrginfo = false;
				//1：最小年化收益
				if(cimProductNew.getFlowMinRate().compareTo(cimOrginfo.getMinProfit()) == -1 || cimOrginfo.getMinProfit().compareTo(BigDecimal.ZERO) == 0){
					cimOrginfoNew.setMinProfit(cimProductNew.getFlowMinRate());
					isNeedUpdateCimOrginfo = true;
				}
				//2:最大年化收益
				if(cimProductNew.getFlowMaxRate().compareTo(cimOrginfo.getMaxProfit()) == 1){
					cimOrginfoNew.setMaxProfit(cimProductNew.getFlowMaxRate());
					isNeedUpdateCimOrginfo = true;
				}
				//3:最小产品期限  5:产品最小期限天数 自定义显示
				if(cimProductNew.getDeadLineMinValue() < cimOrginfo.getMinDeadLine() || cimOrginfo.getMinDeadLine() == 0){
					cimOrginfoNew.setMinDeadLine(cimProductNew.getDeadLineMinValue());
					cimOrginfoNew.setDeadLineMinSelfDefined(cimProductNew.getDeadLineMinSelfDefined());
					isNeedUpdateCimOrginfo = true;
				}
				//4:最大产品期限	6:产品最大期限天数 自定义显示
				if(cimProductNew.getDeadLineMaxValue() > cimOrginfo.getMaxDeadLine()){
					cimOrginfoNew.setMaxDeadLine(cimProductNew.getDeadLineMaxValue());
					cimOrginfoNew.setDeadLineMaxSelfDefined(cimProductNew.getDeadLineMaxSelfDefined());
					isNeedUpdateCimOrginfo = true;
				}
				if(isNeedUpdateCimOrginfo){
					cimOrginfoService.update(cimOrginfoNew);
				}
				
				
				/**
				 * 插入产品表和产品统计表
				 */
				String productId = ApplicationUtils.randomUUID(true,true);//生成一个产品ID
				
				/**
				 * 保存到产品表
				 */
				cimProductNew.setProductId(productId);
				cimProductNew.setRemark("产品推送添加产品信息");
				cimProductNew.setOrgNumber(openRequestHead.getOrgNumber());
				cimProductNew.setCreator(openRequestHead.getOrgNumber());
				cimProductNew.setCreateTime(new Date());
				cimProductNew.setFeeRatio(cimOrginfo.getOrgFeeRatio());//取机构佣金设置产品佣金
				//查询是否自动审核   0-自动审核  1-手动审核
				SysConfig  sysConfig = sysConfigService.querySysConfigByKey("product_system_config", "product_auto_audit", 0);
				if(sysConfig != null && sysConfig.getConfigValue().equalsIgnoreCase("YES")){
					cimProductNew.setAuditStatus(1);
					cimProductNew.setAuditTime(new Date());
				}
				
				/**
				 * 手动处理  是否固定期限(1=固定期限|2=浮动期限) 避免第三方理解错误
				 */
				if(productPushRequest.getIsCollect() == 2){//募集产品
					if(productPushRequest.getIsRedemption() == 0){//不支持赎回和转让
						cimProductNew.setIsFixedDeadline(1);//固定期限
					} else {
						cimProductNew.setIsFixedDeadline(2);//浮动期限
					}
				} else {
					if(productPushRequest.getDeadLineMinValue().equals(productPushRequest.getDeadLineMaxValue())){
						cimProductNew.setIsFixedDeadline(1);//固定期限
					} else {
						cimProductNew.setIsFixedDeadline(2);//浮动期限
					}
				}
				
				/**
				 * 自动设置管理员进行产品编辑信息
				 */
				cimProductService.autoProductEdit(cimProductNew);
				
				/**
				 * 保存到产品表
				 */
				cimProductService.insert(cimProductNew);
				
				/**
				 * 保存到产品统计表
				 */
				cimProductStatisticsNew.setProductId(productId);
				cimProductStatisticsNew.setCreateTime(new Date());//创建时间
				cimProductStatisticsService.insert(cimProductStatisticsNew);
				
				/**
				 * 根据推送的产品信息自动进行产品分类
				 */
				cimProductService.autoHandleProductCateForPush(productId,productPushRequest);
				
			} else {
				/**
				 * 更新产品信息
				 * 若产品状态是2-售罄或者3-募集失败 或者产品1=审核通过(上架） 则不允许更新    
				 * 只有产品在1-在售  且产品审核状态是(2-待审核或3-审核未通过(下架))才可以更新
				 */
				if(cimProduct.getStatus() != null && cimProduct.getStatus() == 1 && (cimProduct.getAuditStatus() == 2 || cimProduct.getAuditStatus() == 3)){			
					/**
					 * 更新产品表
					 */
					cimProductNew.setId(cimProduct.getId());
					cimProductNew.setUpdateTime(new Date());
					cimProductNew.setRemark("产品推送更新产品信息");
					cimProductNew.setUpdater(openRequestHead.getOrgNumber());
					cimProductNew.setUpdateTime(new Date());
					cimProductService.update(cimProductNew);
					
					/**
					 * 更新产品统计表
					 */
					CimProductStatistics cimProductStatistics = new CimProductStatistics();
					cimProductStatistics.setProductId(cimProduct.getProductId());
					cimProductStatistics = cimProductStatisticsService.selectOne(cimProductStatistics);
					cimProductStatisticsNew.setId(cimProductStatistics.getId());;
					cimProductStatisticsNew.setUpdateTime(new Date());//更新时间
					cimProductStatisticsService.update(cimProductStatisticsNew);
				} else {
					return OpenResponseUtil.getErrorBusi(new BaseResponse("PUSHPRODUCT_FAIL", "推送产品失败,当前产品不是在售状态或者产品已通过审核,不允许进行更新 "));
				}
			}
		} catch (Exception e) {
			LOGGER.info("推送产品异常,OrgNumber={},ProductPushRequestNew={}",openRequestHead.getOrgNumber(),JSONObject.toJSONString(productPushRequest));
			LOGGER.error("推送产品异常",e);
			return OpenResponseUtil.getErrorBusi(new BaseResponse("PUSHPRODUCT_EXCEPTION", "推送产品异常"));
		}
		return OpenResponseUtil.getSuccessResponse();
	}

	@Override
	public BaseResponse updateProduct(ProductUpdatRequest productUpdatRequest,OpenRequestHead openRequestHead) {
		
    	try {
    		/**
    		 * 该产品当前状态如果是在售    则更新   否则不执行更新产品操作
    		 */
    		CimProduct cimProduct =  new CimProduct();
    		cimProduct.setOrgNumber(openRequestHead.getOrgNumber());
    		cimProduct.setThirdProductId(productUpdatRequest.getThirdProductId());
    		cimProduct.setStatus(1);
    		cimProduct = cimProductService.selectOne(cimProduct);
    		if(null != cimProduct){
    			
    			/**
    			 * 特殊参数校验
    			 * 1:不是募集的产品  不允许更新产品状态为募集失败
    			 * 2:更新产品状态为2-售罄或者3-募集失败时，产品销售结束时间不能为空
    			 */
    			if(cimProduct.getIsCollect() == 1 && productUpdatRequest.getStatus() == 3){
    				return OpenResponseUtil.getErrorBusi(new BaseResponse("UPDATEPRODUCT_FAIL", "更新产品失败,不是募集的产品 ,不允许更新产品状态为募集失败"));
    			}else if((productUpdatRequest.getStatus() !=null && productUpdatRequest.getStatus() == 2 || productUpdatRequest.getStatus() == 3) && productUpdatRequest.getSaleEndTime() == null){
					return OpenResponseUtil.getErrorBusi(new BaseResponse("UPDATEPRODUCT_FAIL", "更新产品失败,更新产品状态为2-售罄或者3-募集失败时，产品销售结束时间不能为空"));
				} else {
					/**
					 * 更新产品表
					 */
					CimProduct cimProductNew =  new CimProduct();
					cimProductNew.setId(cimProduct.getId());
					cimProductNew.setSaleEndTime(productUpdatRequest.getSaleEndTime());
					//只有推送过来的产品状态不是在售的  才更新产品的产品的状态
					if(productUpdatRequest.getStatus() != 1){				
						cimProductNew.setStatus(productUpdatRequest.getStatus());
					}
					//只有推送过来的产品总额度不为空，且大于当前产品总额度，则更新当前产品总额度
					if(productUpdatRequest.getBuyTotalMoney() != null && productUpdatRequest.getBuyTotalMoney().compareTo(cimProduct.getBuyTotalMoney()) == 1){
						cimProductNew.setBuyTotalMoney(productUpdatRequest.getBuyTotalMoney());
					}
					//只有推送过来的产品期限同时不为空时,则更新产品的期限
					if(productUpdatRequest.getDeadLineMinValue() != null && productUpdatRequest.getDeadLineMinValue() >= 0 
							&& productUpdatRequest.getDeadLineMaxValue() != null && productUpdatRequest.getDeadLineMaxValue() >= 0
							&& StringUtils.isNotBlank(productUpdatRequest.getDeadLineMinSelfDefined()) 
							&& StringUtils.isNotBlank(productUpdatRequest.getDeadLineMaxSelfDefined())){
						cimProductNew.setDeadLineMinValue(productUpdatRequest.getDeadLineMinValue());
						cimProductNew.setDeadLineMaxValue(productUpdatRequest.getDeadLineMaxValue());
						cimProductNew.setDeadLineMinSelfDefined(productUpdatRequest.getDeadLineMinSelfDefined());
						cimProductNew.setDeadLineMaxSelfDefined(productUpdatRequest.getDeadLineMaxSelfDefined());
					}
					cimProductNew.setAddRate(productUpdatRequest.getAddRate());
					cimProductNew.setUpdater(openRequestHead.getOrgNumber());
					cimProductNew.setUpdateTime(new Date());
					cimProductNew.setRemark("更新产品信息");
					cimProductService.update(cimProductNew);
					
					/**
					 * 如果是募集产品且募集成功，进行募集期产品佣金计算
					 */
					if(cimProduct.getIsCollect() == 2 && productUpdatRequest.getStatus() == 2){
						LOGGER.info("开始更新产品,募集产品且募集成功,进行募集期产品佣金计算,OrgNumber={},productId={}",JSONObject.toJSONString(cimProduct.getOrgNumber()),JSONObject.toJSONString(cimProduct.getProductId()));
						try {
							cimProduct.setSaleEndTime(productUpdatRequest.getSaleEndTime());
							cimProduct.setStatus(productUpdatRequest.getStatus());
							cimProduct.setAddRate(productUpdatRequest.getAddRate());
							collectAware.collectProcess(cimProduct);
						} catch (Exception e) {
							LOGGER.info("更新募集产品为募集成功时,进行募集期产品佣金计算异常,OrgNumber={},productUpdatRequest={}",openRequestHead.getOrgNumber(),JSONObject.toJSONString(productUpdatRequest));
							LOGGER.error("更新募集产品为募集成功时,进行募集期产品佣金计算异常",e);
						}
					}
				}
 
    			/**
    			 * 更新产品统计表
    			 */
    			CimProductStatistics cimProductStatistics = new CimProductStatistics();
    			cimProductStatistics.setProductId(cimProduct.getProductId());
    			cimProductStatistics = cimProductStatisticsService.selectOne(cimProductStatistics);
    			cimProductStatistics.setBuyedTotalMoney(productUpdatRequest.getBuyedTotalMoney());
    			cimProductStatistics.setBuyedTotalPeople(productUpdatRequest.getBuyedTotalPeople());
    			cimProductStatistics.setUpdateTime(new Date());
    			cimProductStatisticsService.update(cimProductStatistics);
    			return OpenResponseUtil.getSuccessResponse();
    		} else {
    			LOGGER.info("更新产品失败,当前产品不存在或者不是在售状态,不允许进行更新,productUpdatRequest={}",JSONObject.toJSONString(productUpdatRequest));
    			return OpenResponseUtil.getErrorBusi(new BaseResponse("UPDATEPRODUCT_FAIL", "更新产品失败,当前产品不存在或者不是在售状态,不允许进行更新"));
    		}
		} catch (Exception e) {
			LOGGER.info("更新产品异常,OrgNumber={},productUpdatRequest={}",openRequestHead.getOrgNumber(),JSONObject.toJSONString(productUpdatRequest));
			e.printStackTrace();
			return OpenResponseUtil.getErrorBusi(new BaseResponse("UPDATEPRODUCT_EXCEPTION", "更新产品异常"));
		}
	}
}
