package com.eshop4j.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.eshop4j.core.datatable.DataInfo;
import com.eshop4j.core.datatable.DataResult;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.datatable.ErrorField;
import com.eshop4j.core.export.ExportSupport;
import com.eshop4j.core.util.JsonUtils;
import com.eshop4j.web.model.CimOrginfo;
import com.eshop4j.web.model.CimProduct;
import com.eshop4j.web.model.CimProductCate;
import com.eshop4j.web.model.CimProductInfoCate;
import com.eshop4j.web.request.OrgTagsEditRequest;
import com.eshop4j.web.request.ProductEditRequest;
import com.eshop4j.web.request.ProductListDataRequest;
import com.eshop4j.web.request.ProductSaleDetailRequest;
import com.eshop4j.web.request.ProductSaleListRequest;
import com.eshop4j.web.request.ProductsSalesStatisticsRequest;
import com.eshop4j.web.response.ProductDetailForManageResponse;
import com.eshop4j.web.response.ProductSaleDetailResponse;
import com.eshop4j.web.response.ProductsSalesStatisticsResponse;
import com.eshop4j.web.service.CimOrginfoService;
import com.eshop4j.web.service.CimProductCateService;
import com.eshop4j.web.service.CimProductInfoCateService;
import com.eshop4j.web.service.CimProductService;
import com.eshop4j.xoss.interceptors.DateConvertEditor;
import com.eshop4j.xoss.util.OpenResponseUtil;
import com.eshop4j.xoss.util.RequestLogging;

 /**
 * 
 * @描述： 实体控制器
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月14日 18:23:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "cim/cimproduct")
@RequestLogging("实体控制器")
public class CimProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductController.class);

	@Resource
	private CimProductService cimProductService;
	@Resource
	private CimOrginfoService cimOrginfoService;
	@Resource
	private CimProductInfoCateService cimProductInfoCateService;
	@Resource
	private CimProductCateService cimProductCateService;
	@Resource
	private ExportSupport exportSupport;
	
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
     * 基于角色 比如拥有OPERATION_MANAGER角色，才可以查看列表.
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)
    @RequestLogging("管理后台-理财产品管理-产品管理列表页面")
    public String cimProduct(Model model) {
    	/**
    	 * 查询所有的合作中机构
    	 */
    	CimOrginfo cimOrginfo = new CimOrginfo();
    	cimOrginfo.setStatus(1);//合作中
    	List<CimOrginfo> cimOrginfoList = cimOrginfoService.selectListByCondition(cimOrginfo);
    	model.addAttribute("cimOrginfoList", cimOrginfoList);
    	/**
    	 * 查询所有可用的产品分类
    	 */
    	//公共分类
    	CimProductCate cimProductCate = new  CimProductCate();
    	cimProductCate.setDisabled("0");
    	cimProductCate.setIspublic(0);
    	List<CimProductCate> cimProductCateListP = cimProductCateService.selectListByCondition(cimProductCate);
    	model.addAttribute("cimProductCateListP", cimProductCateListP);
    	//投呗分类
    	cimProductCate.setIspublic(1);
    	List<CimProductCate> cimProductCateListT = cimProductCateService.selectListByCondition(cimProductCate);
    	model.addAttribute("cimProductCateListT", cimProductCateListT);
    	//猎财大师分类
    	cimProductCate.setIspublic(2);
    	List<CimProductCate> cimProductCateListL = cimProductCateService.selectListByCondition(cimProductCate);
    	model.addAttribute("cimProductCateListL", cimProductCateListL);
    	return "cimproduct/cimproduct-list";
    }

    /**
     * datatables的例子<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("管理后台-理财产品管理-产品管理列表数据")
	public DataTableReturn getCimProducts(@RequestBody ProductListDataRequest productListDataRequest) {
    	LOGGER.info("管理后台-查看列表数据,productListDataRequest={}",JSONObject.toJSONString(productListDataRequest));
    	productListDataRequest.initOrdersOriginal();
		DataTableReturn tableReturn = cimProductService.selectProductListForManage(productListDataRequest);
		return tableReturn;
	}
    
    /**
     * 查询该产品已经关联的可用分类信息<br>
     * @return
     */
    @RequestMapping(value="/queryProductInfoCateList")
    @ResponseBody
    @RequestLogging("管理后台-理财产品管理-查询产品已关联的可用分类")
	public List<CimProductInfoCate> getCimProducts(String productId) {
    	LOGGER.info("管理后台-查询产品已关联的可用分类,productId={}",productId);
    	List<CimProductInfoCate> cimProductInfoCateList = new ArrayList<CimProductInfoCate>();
    	if(StringUtils.isNotEmpty(productId)){		
    		CimProductInfoCate cimProductInfoCate = new CimProductInfoCate();
    		cimProductInfoCate.setProductId(productId);
    		cimProductInfoCateList = cimProductInfoCateService.selectCateListByCondition(cimProductInfoCate);
    	}
		return cimProductInfoCateList;
	}

    /**
     * 产品编辑
     */
    @RequestMapping(value="/productEdit")
    @ResponseBody
    @RequestLogging("管理后台-理财产品管理-产品编辑")
    public String productEdit(@RequestBody ProductEditRequest productEditRequest,BindingResult validResult) {
		/**
		 * 普通参数校验
		 */
    	if (OpenResponseUtil.existsParamsError(validResult)) {
    		return "false";
		}
    	//设置产品分类及排序
    	if(productEditRequest.getCateListArray() != null && productEditRequest.getCateListArray().size() > 0){
    		//删除产品原有所有分类和排序
    		cimProductInfoCateService.deleProductCate(productEditRequest.getProductIdForEdit(), null);
    		for(CimProductInfoCate cimProductInfoCate : productEditRequest.getCateListArray()){
    			cimProductInfoCate.setDescription("设置产品分类及排序");
    			cimProductInfoCate.setUpdateTime(new Date());
    			cimProductInfoCateService.insert(cimProductInfoCate);
    		}
    	}
    	
    	
    	//设置列表推荐
    	if(productEditRequest.getShowIndex() != productEditRequest.getOriginalShowIndex()){
    		CimProduct cimProduct =  new CimProduct();
    		cimProduct.setShowIndex(productEditRequest.getShowIndex());
    		cimProduct.setId(productEditRequest.getProductTableId());
    		cimProductService.update(cimProduct);
    	}
    	return "success";
    }

    /**
     * 产品详情
     */
    @RequestMapping(value="/productDetail")
    @ResponseBody
    @RequestLogging("管理后台-理财产品管理-查询产品详情")
    public ProductDetailForManageResponse productDetail(String productTableId,String productId) {
		LOGGER.info("管理后台-查询产品详情, productTableId={},productId={}",productTableId,productId);
		ProductDetailForManageResponse productDetailForManageResponse = new ProductDetailForManageResponse();
		productDetailForManageResponse = cimProductService.queryProductDetailForManerge(productId);
    	return productDetailForManageResponse;
    }
    
   /**
    * 产品审核
    * @param auditType  审核类型  partAudit-部分审核  allAudit-全部审核
    * @param auditCode  审核code  0-审核通过  1-审核未通过
    * @param productTableIdList  待审核的产品表主键id列   格式 1,2,3,4
    * @return
    */
    @RequestMapping(value="/productAudit")
    @ResponseBody
    @RequestLogging("管理后台-理财产品管理-产品审批")
    public String productAudit(String auditType,Integer auditCode,String tableId) {
		LOGGER.info("管理后台-产品审批, auditType={},auditCode={}",auditType,auditCode);
		LOGGER.info("管理后台-产品审批, tableId={}",tableId);
	    cimProductService.productAudit(auditType,auditCode,tableId);
    	return "success";
    }
    
	/**
	 * 查看产品销售页面
	 * @param model
	 * @return
	 */
    @RequestMapping(value="/saleList",   method=RequestMethod.GET)
    @RequestLogging("管理后台-理财产品管理-查看产品销售页面")
    public String saleList(Model model) {
    	//查询机构列表
    	CimOrginfo cimOrginfo = new CimOrginfo();
    	cimOrginfo.setStatus(1);//合作中
    	List<CimOrginfo> cimOrginfoList = cimOrginfoService.selectListByCondition(cimOrginfo);
    	model.addAttribute("cimOrginfoList", cimOrginfoList);
    	return "cimproduct/cimproduct-saleList";
    }
    
	/**
	 * 产品销售页面数据加载
	 * @param model
	 * @return
	 */
    @ResponseBody
    @RequestLogging("管理后台-理财产品管理-产品销售页面数据加载")
    @RequestMapping(value="/saleList",   method=RequestMethod.POST)
	public DataTableReturn getSaleList(@RequestBody ProductSaleListRequest productSaleListRequest) {
    	LOGGER.info("管理后台-产品销售页面数据加载,productListDataRequest={}",JSONObject.toJSONString(productSaleListRequest));
    	productSaleListRequest.initOrdersOriginal();
		DataTableReturn tableReturn = cimProductService.selectProductSaleListForManage(productSaleListRequest);
		return tableReturn;
	}
    
	/**
	 * 产品销售页面数据加载
	 * @param model
	 * @return
	 */
    @ResponseBody
    @RequestLogging("管理后台-理财产品管理-产品销售详细查询")
    @RequestMapping(value="/productSaleDetail",   method=RequestMethod.POST)
	public List<ProductSaleDetailResponse> productSaleDetail(ProductSaleDetailRequest productSaleDetailRequest) {
    	LOGGER.info("管理后台-产品销售详细查询,productSaleDetailRequest={}",JSONObject.toJSONString(productSaleDetailRequest));
    	List<ProductSaleDetailResponse>  productSaleDetailResponses= cimProductService.selectProductSaleDetail(productSaleDetailRequest);
		return productSaleDetailResponses;
	}
    
    
    /**
     * 产品销售
     */
    @RequestMapping(value="/salesStatistics",   method=RequestMethod.GET)
    @RequestLogging("管理后台-查看产品销售列表")
    public String cimProductSalesStatistics(Model model) {
    	//查询机构列表
    	CimOrginfo cimOrginfo = new CimOrginfo();
    	cimOrginfo.setStatus(1);//合作中
    	List<CimOrginfo> cimOrginfoList = cimOrginfoService.selectListByCondition(cimOrginfo);
    	model.addAttribute("cimOrginfoList", cimOrginfoList);
    	return "cimproduct/cimproduct-salesStatistics";
    }
    
    /**
     * 产品销售页面 datatables<br>
     * @return
     */
    @RequestMapping(value="/salesStatistics", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("管理后台-产品销售页面数据")
    public DataTableReturn getSalesStatistics(@RequestBody ProductsSalesStatisticsRequest ProductsSalesStatisticsRequest){
    	ProductsSalesStatisticsRequest.initOrdersOriginal();
		DataTableReturn tableReturn = cimProductService.selectSalesStatisticsByDatatables(ProductsSalesStatisticsRequest);
		return tableReturn;
    }
    
    /**
     * 产品销售数据导出
     * @param request
     * @param response
     * @param req
     * @throws Exception
     */
	@RequestMapping(value = "/exportSalesStatistics")
	public void export(HttpServletRequest request,HttpServletResponse response,ProductsSalesStatisticsRequest ProductsSalesStatisticsRequest) throws Exception {
		//导出默认时间段数据
		String tempFileName = "product/salesStatistics.xls";
		List<ProductsSalesStatisticsResponse> salesStatisticslist = cimProductService.selectSalesStatistics(ProductsSalesStatisticsRequest);
		
		 Map<String, Object> params = new HashMap<String,Object>();
		 params.put("size", Long.valueOf(salesStatisticslist.size()));
		 params.put("list", salesStatisticslist);
		 exportSupport.export( request,  response,  tempFileName,  params);
	}
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@RequestLogging("CUD操作")
	public DataResult save(@RequestParam String rows) {
    	DataInfo df = JsonUtils.fromJsonToObject(rows, DataInfo.class); 
    	@SuppressWarnings("unchecked")
		Map<String,CimProduct> map =  (Map<String, CimProduct>) df.getData();
    	DataResult dr = new DataResult();
    	List<CimProduct> datas = new ArrayList<CimProduct>();
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
					CimProduct r = new CimProduct();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<CimProduct>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<CimProduct> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        }    
					this.cimProductService.insert(r);
				}
			}
			if(DataInfo.ACTION_EDIT.equals(df.getAction())){
				for (String key : map.keySet()) {
					CimProduct r = new CimProduct();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<CimProduct>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<CimProduct> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        } 
					this.cimProductService.update(r);
				}
			}
			if(DataInfo.ACTION_REMOVE.equals(df.getAction())){
				for (String key : map.keySet()) {
					this.cimProductService.delete(Long.parseLong(key));
				}
			}
		} catch (Exception e) {
			dr.setError(e.getMessage());
		}
    	dr.setData(datas);
    	return dr;
	}
    
    /**
     * 机构产品标签编辑
     */
    @RequestMapping(value="/orgTagsEdit")
    @ResponseBody
    @RequestLogging("管理后台-理财产品管理-产品编辑")
    public String orgTags(@RequestBody @Valid OrgTagsEditRequest orgTagsEditRequest,BindingResult validResult) {
		/**
		 * 普通参数校验
		 */
    	if (OpenResponseUtil.existsParamsError(validResult)) {
    		return "false";
		}
    	//设置机构产品标签
    	if(orgTagsEditRequest.getCateListArray() != null && orgTagsEditRequest.getCateListArray().size() > 0){
    		CimProduct tempProduct = new CimProduct();
    		tempProduct.setOrgNumber(orgTagsEditRequest.getOrgNumber());
    		//只为当前在售产品添加标签
    		tempProduct.setStatus(1);
    		List<CimProduct> orgProductList = cimProductService.selectListByCondition(tempProduct);
    		List<CimProductInfoCate> newCimProductInfoCateList = new ArrayList<CimProductInfoCate>();
    		for(CimProduct product : orgProductList){
    			CimProductInfoCate cimProductInfoCate = new CimProductInfoCate();
    			cimProductInfoCate.setProductId(product.getProductId());
    			List<CimProductInfoCate> existedCimProductInfoCateList = cimProductInfoCateService.selectListByCondition(cimProductInfoCate);
    			for(CimProductInfoCate cimProductInfoCateTemp : orgTagsEditRequest.getCateListArray()){
    				boolean add = true;
    				for(CimProductInfoCate existedProductInfoCate : existedCimProductInfoCateList){
    					if(existedProductInfoCate.getCateId() == cimProductInfoCateTemp.getCateId()){
    						add = false;   						
    					}
    				}
    				if(add){
    					CimProductInfoCate temp = new CimProductInfoCate();
    					temp.setCateId(cimProductInfoCateTemp.getCateId());
    					temp.setProductId(product.getProductId());
    					temp.setDescription("设置产品分类及排序");
    					temp.setUpdateTime(new Date());
    					temp.setSort(0);
    					newCimProductInfoCateList.add(temp);
    				}
        		}
    		}
    		if(newCimProductInfoCateList.size() != 0){
    			cimProductInfoCateService.insertBatch(newCimProductInfoCateList);
    		}  		   		
    	}
    	return "success";
    }
    	
}
