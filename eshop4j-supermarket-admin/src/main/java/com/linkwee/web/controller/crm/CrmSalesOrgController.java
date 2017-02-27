package com.linkwee.web.controller.crm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.linkwee.core.Import.PoiImport;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.User;
import com.linkwee.web.model.crm.CrmSalesOrg;
import com.linkwee.web.model.crm.SaleOrgAchiResp;
import com.linkwee.web.model.crm.SalesOrgCfpImport;
import com.linkwee.web.model.crm.TeamStatisticalResponse;
import com.linkwee.web.request.LcsListRequest;
import com.linkwee.web.request.SaleOrgDetailRequest;
import com.linkwee.web.request.crm.SalesOrgRequest;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmSalesOrgService;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;

 /**
 * 
 * @描述： CrmSalesOrgController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月03日 15:12:23
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "crmSalesOrg")
@RequestLogging("CrmSalesOrgController控制器")
public class CrmSalesOrgController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrmSalesOrgController.class);

	@Resource
	private CrmSalesOrgService crmSalesOrgService;
	
	@Resource
	private CimOrginfoService cimOrginfoService;
	
	@Resource
	private CrmCfplannerService crmCfplannerService;
	

    /**
	 * 销售机构列表
	 */
	@RequestMapping("salesOrgListPage")
	public String salesOrgListPage() {
		return "salesorg/salesOrgList";
	}	
	
	/**
	 * 获取销售机构列表
	 * @param lcsListRequest
	 * @return
	 */
	@RequestMapping("getSalesOrgList")
	@ResponseBody
	@RequestLogging("获取销售机构列表")
	public Object getSalesOrgList(LcsListRequest lcsListRequest, DataTable dataTable) {
		DataTableReturn dataTableReturn = crmSalesOrgService.querySalesOrgList(dataTable, lcsListRequest);
		return dataTableReturn;
	}
	
	/**
     * 添加页面
     */
    @RequestMapping(value="addPage")
    public String addPage(Model model) {
    	return "salesorg/add";
    }
    
    /**
     * 添加机构
     */
    @RequestMapping(value="addOrg")
    @ResponseBody
    @RequestLogging("添加机构")
	public Object addOrg(@Valid SalesOrgRequest  req,BindingResult bindResult,HttpSession session) {
    	if(ResponseUtil.existsParamsError(bindResult)) {
   	    	return ResponseUtil.getErrorParams(bindResult);
        }
    	try {
    		CrmSalesOrg bo = new CrmSalesOrg();
    		bo.setName(req.getName());
    		List<CrmSalesOrg> list = crmSalesOrgService.selectListByCondition(bo);
    		if(list != null && list.size() > 0 ) {
    			return new ResponseResult(false,"添加失败，机构名称重复");
    		}
    		
    		CrmSalesOrg bo2 = new CrmSalesOrg();
    		bo2.setManagerAccount(req.getManagerAccount());
    		List<CrmSalesOrg> list2 = crmSalesOrgService.selectListByCondition(bo2);
    		if(list2 != null && list2.size() > 0 ) {
    			return new ResponseResult(false,"添加失败，管理账户重复");
    		}
    		
    		CrmSalesOrg bo3 = new CrmSalesOrg();
    		bo3.setContactMobile(req.getContactMobile());
    		List<CrmSalesOrg> list3 = crmSalesOrgService.selectListByCondition(bo3);
    		if(list3 != null && list3.size() > 0 ) {
    			return new ResponseResult(false,"添加失败，电话号码重复");
    		}
    		
    		CrmCfplanner cfp = crmCfplannerService.queryCfplannerByMobile(req.getContactMobile());
    		if(cfp == null) {
    			return new ResponseResult(false,"添加失败，联系手机号需已注册猎才大师");
    		}
    		
    		CrmSalesOrg crmSalesOrg = new CrmSalesOrg();
    		crmSalesOrg.setName(req.getName());
    		crmSalesOrg.setCity(req.getCity());
    		crmSalesOrg.setContactMobile(req.getContactMobile());
    		crmSalesOrg.setContacts(req.getContacts());
    		crmSalesOrg.setCooperationStatus(req.getCooperationStatus());
    		crmSalesOrg.setCooperationTime(req.getCooperationTime());
    		crmSalesOrg.setCreateTime(new Date());
    		crmSalesOrg.setLastUpdateTime(new Date());
    		crmSalesOrg.setLegalPerson(req.getLegalPerson());
    		crmSalesOrg.setManagerAccount(req.getManagerAccount());
    		crmSalesOrg.setPassword(req.getPassword());
    		crmSalesOrg.setSalesOrgId(StringUtils.getUUID());
    		crmSalesOrgService.insert(crmSalesOrg);
    		crmCfplannerService.updatesSalesOrgId(crmSalesOrg.getSalesOrgId(), crmSalesOrg.getContactMobile());
			return new ResponseResult(true,"添加成功");
		} catch (Exception e) {
			LOGGER.error("addOrg exception : {}", e.getMessage());
			return new ResponseResult(false,"添加失败,参数异常");
		}
	}
    
    /**
     * 编辑页面
     */
    @RequestMapping(value="editPage")
    public String editPage(Model model,String id) {
    	CrmSalesOrg crmSalesOrg = new CrmSalesOrg();
    	if(id != null && !"".equals(id)) {
    		Long idLong = Long.parseLong(id);
    		crmSalesOrg = crmSalesOrgService.selectById(idLong);
    	}
    	model.addAttribute("crmSalesOrg", crmSalesOrg);
    	return "salesorg/edit";
    }
    
    /**
     * 编辑机构
     */
    @RequestMapping(value="editOrg")
    @ResponseBody
    @RequestLogging("编辑机构")
	public Object editOrg(@Valid SalesOrgRequest  req,BindingResult bindResult,HttpSession session) {
    	if(ResponseUtil.existsParamsError(bindResult)) {
   	    	return ResponseUtil.getErrorParams(bindResult);
        }
    	try {
    		boolean nameFlag = crmSalesOrgService.checkNameExistForUpdate(req.getId(), req.getName());
    		if(nameFlag) {
    			return new ResponseResult(false,"编辑失败，机构名称重复");
    		}
    		boolean mobileFlag = crmSalesOrgService.checkMobileExistForUpdate(req.getId(), req.getContactMobile());
    		if(mobileFlag) {
    			return new ResponseResult(false,"编辑失败，电话号码重复");
    		}
    		boolean accountFlag = crmSalesOrgService.checkAccountExistForUpdate(req.getId(), req.getManagerAccount());
    		if(accountFlag) {
    			return new ResponseResult(false,"编辑失败，管理帐号重复");
    		}
    		CrmCfplanner cfp = crmCfplannerService.queryCfplannerByMobile(req.getContactMobile());
    		if(cfp == null) {
    			return new ResponseResult(false,"编辑失败，联系手机号需已注册猎才大师");
    		}
    		CrmSalesOrg crmSalesOrg = new CrmSalesOrg();
    		crmSalesOrg.setName(req.getName());
    		crmSalesOrg.setCity(req.getCity());
    		crmSalesOrg.setContactMobile(req.getContactMobile());
    		crmSalesOrg.setContacts(req.getContacts());
    		crmSalesOrg.setCooperationStatus(req.getCooperationStatus());
    		crmSalesOrg.setCooperationTime(req.getCooperationTime());
    		crmSalesOrg.setLastUpdateTime(new Date());
    		crmSalesOrg.setLegalPerson(req.getLegalPerson());
    		crmSalesOrg.setManagerAccount(req.getManagerAccount());
    		crmSalesOrg.setPassword(req.getPassword());
    		crmSalesOrg.setId(req.getId());
    		crmSalesOrgService.update(crmSalesOrg);
			return new ResponseResult(true,"编辑成功");
		} catch (Exception e) {
			LOGGER.error("editOrg exception : {}", e.getMessage());
			return new ResponseResult(false,"编辑失败,参数异常");
		}
	}
    
    /**
     * 查看
     */
    @RequestMapping(value="view")
    public String view(Model model,String id) {
    	CrmSalesOrg crmSalesOrg = new CrmSalesOrg();
    	if(id != null && !"".equals(id)) {
    		Long idLong = Long.parseLong(id);
    		crmSalesOrg = crmSalesOrgService.selectById(idLong);
    		SaleOrgAchiResp saleOrgAchiResp = crmSalesOrgService.querySalesOrgAchiByNumber(crmSalesOrg.getSalesOrgId());
    		crmSalesOrg.setHistorySales(saleOrgAchiResp.getHistorySales());
    		crmSalesOrg.setThisMonthFee(saleOrgAchiResp.getThisMonthFee());
    		crmSalesOrg.setThisMonthSales(saleOrgAchiResp.getThisMonthSales());
    		crmSalesOrg.setCfplannerCount(saleOrgAchiResp.getCfplannerCount());
    	}
    	model.addAttribute("crmSalesOrg", crmSalesOrg);
    	return "salesorg/view";
    }
    
    /**
     * 批量给理财师分配销售机构页面
     */
    @RequestMapping(value="importPage")
    public String importPage(Model model,String salesOrgId) {
    	CrmSalesOrg crmSalesOrg = new CrmSalesOrg();
    	if(salesOrgId != null && !"".equals(salesOrgId)) {
    		crmSalesOrg.setSalesOrgId(salesOrgId);
    		crmSalesOrg = crmSalesOrgService.selectOne(crmSalesOrg);
    	}
    	model.addAttribute("crmSalesOrg", crmSalesOrg);
    	return "salesorg/importPage";
    }
    
    @RequestMapping(value="importCfp")
    @ResponseBody
    @RequestLogging("销售机构导入理财师")
	public Object importCfp(HttpServletRequest request,HttpSession session) {   
    	try {
    		User user = (User) session.getAttribute("userInfo"); 
    		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
    		String salesOrgId = multipartRequest.getParameter("salesOrgId");
            MultipartFile file  =  multipartRequest.getFile("file");
            Set<String> msg = null;
            InputStream inputStream = file.getInputStream();
            List<SalesOrgCfpImport>  cfpList = PoiImport.dataImport(inputStream, SalesOrgCfpImport.class);
            if(cfpList == null || cfpList.size() == 0) {
            	return new ResponseResult(false,"导入失败，数据为空");
            }
            for(SalesOrgCfpImport salesOrgCfpImport : cfpList) {
            	 crmCfplannerService.updatesSalesOrgId(salesOrgId, salesOrgCfpImport.getMobile());
            }
            
    		return new ResponseResult(true,"导入成功",msg);
		}catch (ServiceException e) {
			LOGGER.error("sales org import cfplanner exception : {}", e.getMessage());
			return new ResponseResult(false,e.getMessage());
		} catch (Exception e) {
			LOGGER.error("sales org import cfplanner  exception : {}", e.getMessage());
		}
    	return new ResponseResult(false,"导入失败");
	}
    
    /**
   	 * 下载导入模板
   	 * @param response
   	 * @param request
   	 * @throws FileNotFoundException
   	 */
   	@RequestMapping(value = "/downloadExcelTemplate")
   	public void downloadImportTemplate(HttpServletResponse response,HttpServletRequest request) {
   		LOGGER.info("下载销售机构导入理财师Excel模板");
   		// 下载本地文件
   		String fileName = "import_cfp.xls";
   		// 读到流中
   		String path = request.getSession().getServletContext().getRealPath("/WEB-INF");
   		InputStream inStream=null;
   		OutputStream outStream=null;
   		try {
   			inStream = new FileInputStream(path+ "/xls/lcs/salesOrg/import_cfp.xls");// 文件的存放路径
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
   			LOGGER.error("下载销售机构导入理财师Excel模板异常",e);
   		}finally{
   			try {
   				if(inStream!=null){
   					inStream.close();
   				}
   				if(outStream!=null){
   					outStream.close();
   				}
   			} catch (IOException e) {
   				LOGGER.error("下载销售机构导入理财师Excel模板关闭输入流时出现异常",e);
   			}
   		}
   	}
    
    /**
  	 * 理财师销售列表
  	 */
  	@RequestMapping("cfplannerSalesListPage")
  	public String cfplannerSalesListPage(Model model, @RequestParam String salesOrgId) {
  		model.addAttribute("salesOrgId", salesOrgId);
  		return "salesorg/cfplannerSalesList";
  	}	
  	
  	/**
  	 * 获取理财师销售列表
  	 * @param lcsListRequest
  	 * @return
  	 */
  	@RequestMapping("getCfplannerSalesList")
  	@ResponseBody
  	@RequestLogging("获取销售机构列表")
  	public Object getCfplannerSalesList(SaleOrgDetailRequest req, DataTable dataTable) {
  		DataTableReturn dataTableReturn = crmSalesOrgService.querySalesOrgCfpList(dataTable,req);
  		return dataTableReturn;
  	}
  	
  	/**
  	 * 销售业绩明细列表
  	 */
  	@RequestMapping("salesDetailListPage")
  	public String salesDetailListPage(Model model, @RequestParam String salesOrgId) {
  		model.addAttribute("salesOrgId", salesOrgId);
  		CimOrginfo req = new CimOrginfo();
  		req.setStatus(1);
  		List<CimOrginfo> platformList = cimOrginfoService.selectListByCondition(null);
  		model.addAttribute("platformList", platformList);
  		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
  		Calendar c = Calendar.getInstance();    
	    c.add(Calendar.MONTH, 0);
	    c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	    String startTimeForSearch = format.format(c.getTime());
	    String endTimeForSearch = format.format(new Date());
  		model.addAttribute("startTimeForSearch", startTimeForSearch);
  		model.addAttribute("endTimeForSearch", endTimeForSearch);
  		return "salesorg/salesDetailList";
  	}	
  	
  	/**
  	 * 获取 销售业绩明细列表
  	 * @param lcsListRequest
  	 * @return
  	 */
  	@RequestMapping("getSalesDetailList")
  	@ResponseBody
  	@RequestLogging("获取销售机构列表")
  	public Object getSalesDetailList(SaleOrgDetailRequest req, DataTable dataTable) {
  		if(StringUtils.isBlank(req.getStartTimeForSearch()) && StringUtils.isBlank(req.getEndTimeForSearch())) {
  			//如果没有传时间，设置开始时间为本月一日
  			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
  			Calendar c = Calendar.getInstance();    
  		    c.add(Calendar.MONTH, 0);
  		    c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
  		    String first = format.format(c.getTime());
  			req.setStartTimeForSearch(first);
  		}
  		DataTableReturn dataTableReturn = crmSalesOrgService.querySalesDetailList(dataTable,req);
  		return dataTableReturn;
  	}

  	
  	@RequestMapping("getSalesDetailListTotal")
	@ResponseBody
	@RequestLogging("获取销售机构统计")
	public Object getSalesDetailListTotal(SaleOrgDetailRequest req){
  		TeamStatisticalResponse resp = crmSalesOrgService.getSalesDetailListTotal( req);
  		int cfpOfInvestedCount =  crmSalesOrgService.querycfpOfInvestedCount(req);
  		if(cfpOfInvestedCount > 0) {
  			resp.setAvgSalesAmt(resp.getTotalAmt().divide(new BigDecimal(cfpOfInvestedCount), 4, BigDecimal.ROUND_DOWN));
  		}
		return resp;
	}
	
  	
  	
}
