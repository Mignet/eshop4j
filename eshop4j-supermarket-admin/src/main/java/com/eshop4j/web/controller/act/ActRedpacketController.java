package com.eshop4j.web.controller.act;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.ObjectUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.common.collect.Lists;
import com.eshop4j.act.redpacket.service.ActRedpacketService;
import com.eshop4j.core.base.ResponseResult;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.exception.ServiceException;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.core.util.JsonUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.model.CimOrginfo;
import com.eshop4j.web.model.User;
import com.eshop4j.web.request.act.ProductSearchRequest;
import com.eshop4j.web.request.act.RedPacketInfoRequest;
import com.eshop4j.web.request.act.RedpacketStatisticsRequest;
import com.eshop4j.web.response.act.ProductPageResponse;
import com.eshop4j.web.response.act.RedpacketStatisticsResponse;
import com.eshop4j.web.service.CimOrginfoService;
import com.eshop4j.web.service.CimProductService;
import com.eshop4j.xoss.helper.DateUtils;
import com.eshop4j.xoss.interceptors.DateConvertEditor;
import com.eshop4j.xoss.util.RequestLogging;
import com.eshop4j.xoss.util.ResponseUtil;

 /**
 * 
 * @描述： ActRedpacketController控制器
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年08月22日 11:27:17
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "redpacket")
@RequestLogging("红包列表")
public class ActRedpacketController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActRedpacketController.class);

	@Autowired
	private ActRedpacketService redpacketService;

	@Autowired
	private CimProductService  productService;
	
	@Autowired
	private CimOrginfoService cimOrginfoService;
	
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
    @RequestMapping(value="initPage")
    public String initPage(Model model) {
    	return "redpacket/redpacket-page";
    }
    
    
    /**
     * 添加页面
     */
    @RequestMapping(value="addPage")
    public String getRedpacketAddPage(Model model) {
    	CimOrginfo req = new CimOrginfo();
  		req.setStatus(1);
		model.addAttribute("platformList",cimOrginfoService.selectListByCondition(req));
    	return "redpacket/redpacket-add-page";
    }
    
    /**
     * 修改页面
     */
    @RequestMapping(value="{redpacketId}/editPage")
    public String getRedpacketEditPage(@PathVariable("redpacketId")String redpacketId,Model model) {
    	try {
    		if(StringUtils.isNotBlank(redpacketId)){
    			RedPacketInfoRequest redPacketInfo = redpacketService.getRedPacketInfo(redpacketId);
    			
    			String pids = redPacketInfo.getPids();
    			if(StringUtils.isNotBlank(pids)){
    				model.addAttribute("products",  productService.queryProductByProductIds(org.apache.commons.lang.StringUtils.split(pids, ",")));
    			}
    			CimOrginfo req = new CimOrginfo();
    	  		req.setStatus(1);
    			model.addAttribute("platformList",cimOrginfoService.selectListByCondition(req));
    			model.addAttribute("redpacketId", redpacketId);
    			model.addAttribute("redpacket",  redPacketInfo);
    		}
			
		}catch (ServiceException e) {
			LOGGER.error("redpacketEdit exception : {}", e.getMessage());
			model.addAttribute("errorMgs",e.getMessage());
		}  catch (Exception e) {
			LOGGER.error("redpacketEdit exception : {}", e.getMessage());
			model.addAttribute("errorMgs","查询红包失败");
		}
    	return "redpacket/redpacket-edit-page";
    }
    
    /**
     * 产品列表页面
     */
    @RequestMapping(value="productPage")
    public String getProductPage(Model model) {
    	return "redpacket/product-page";
    }
    
    /**
     * 发送红包页面
     * @param _dt_json
     * @return
     */
    @RequestMapping(value="sendRedpacketPage")
	public Object getSendRedpacketPage(Model model) {
    	return "redpacket/send-redpacket-page";
	}
    
    
    @RequestMapping(value="redpacketStatisticsPage")
  	public Object getRedpacketStatisticsPage(Model model) {
    	model.addAttribute("date",DateUtils.format(new Date(), DateUtils.FORMAT_SHORT));
      	return "redpacket/redpacket-statistics-page";
  	}
      
    
    
    @RequestMapping(value="list")
    @ResponseBody
    @RequestLogging("查看列表")
	public Object getActRedpackets(@RequestParam String  _dt_json) {
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		return redpacketService.getRedpacketList(dataTable);
	}
    
 
    
    
    
    @RequestMapping(value="add")
    @ResponseBody
    @RequestLogging("添加红包")
	public Object redpacketAdd(@Valid RedPacketInfoRequest  redPacketInfo,BindingResult bindResult,HttpSession session) {
    	if(ResponseUtil.existsParamsError(bindResult)) {
   	    	return ResponseUtil.getErrorParams(bindResult);
        }
    	try {
    		User user = (User) session.getAttribute("userInfo"); 
    		redPacketInfo.setOperator(user.getUsername());
			redpacketService.insertRedpacket(redPacketInfo);
			return new ResponseResult(true,"添加成功");
		} catch (Exception e) {
			LOGGER.error("redpacketAdd exception : {}", e.getMessage());
		}
    	return new ResponseResult(false,"添加失败");
	}
    
    
    @RequestMapping(value="edit")
    @ResponseBody
    @RequestLogging("编辑红包")
	public Object redpacketEdit(@Valid RedPacketInfoRequest redPacketInfo,BindingResult bindResult,HttpSession session) {
    	if(ResponseUtil.existsParamsError(bindResult)) {
   	    	return ResponseUtil.getErrorParams(bindResult);
        }
    	try {
    		if(StringUtils.isBlank(redPacketInfo.getRedpacketId()))return new ResponseResult(true,"不存在的红包");  		
			User user = (User) session.getAttribute("userInfo"); 
    		redPacketInfo.setOperator(user.getUsername());
			redpacketService.updateRedpacket(redPacketInfo);
			return new ResponseResult(true,"更新成功");
		}catch (ServiceException e) {
			LOGGER.error("redpacketEdit exception : {}", e.getMessage());
			return new ResponseResult(false,e.getMessage());
		} catch (Exception e) {
			LOGGER.error("redpacketEdit exception : {}", e.getMessage());
		}
    	return new ResponseResult(false,"更新失败");
	}
    
    
    /**
	 * 下载导入模板
	 * @param response
	 * @param request
	 * @throws FileNotFoundException
	 */
	@RequestMapping(value = "downloadImportTemplate")
	public void downloadImportTemplate(HttpServletResponse response,HttpServletRequest request) {
		LOGGER.info("下载导入发放红包模板");
		// 下载本地文件
		String fileName = "sendRedPacket.xls"; // 文件的默认保存名letter
		// 读到流中
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/xls/template/sendRedPacket.xls");
		InputStream inStream=null;
		OutputStream outStream=null;
		try {
			inStream = new FileInputStream(path);// 文件的存放路径
			response.reset();
			/*设置头信息以及编码*/
			response.setContentType("multipart/form-data");
			response.setCharacterEncoding("UTF-8");
			/*设置下载时的文件名*/
			response.addHeader("Content-Disposition", "attachment; filename=\""+ new String(fileName.getBytes(), "ISO8859-1") + "\"");
			outStream=response.getOutputStream();
			byte[] b = new byte[100];
			int len;
			while ((len = inStream.read(b)) > 0)
				outStream.write(b, 0, len);
		} catch (IOException e) {
			LOGGER.error("下载导入模板出现异常",e);
		}finally{
			try {
				if(inStream!=null){
					inStream.close();
				}
				if(outStream!=null){
					outStream.close();
				}
			} catch (IOException e) {
				LOGGER.error("下载导入模板关闭输入流时出现异常",e);
			}
		}
	}
    
    @RequestMapping(value="sendRedpacket")
    @ResponseBody
    @RequestLogging("发送红包")
	public Object sendRedpacket(HttpServletRequest request,HttpSession session) {   
    	try {
     		User user = (User) session.getAttribute("userInfo"); 
    		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
            MultipartFile file  =  multipartRequest.getFile("file");
            String redpacketId = multipartRequest.getParameter("redpacketId");
            String dateType = multipartRequest.getParameter("dateType");
            String expiresDay = multipartRequest.getParameter("expiresDay");
            String expiresDate = multipartRequest.getParameter("expiresDate");
            if(StringUtils.isBlank(dateType)  ){
            	return new ResponseResult(false,"过期时间类型不能为空");
            }
            int datetype = Integer.valueOf(dateType);
            if(StringUtils.isBlank(expiresDay) && StringUtils.isBlank(expiresDate) ){
            	return new ResponseResult(false,"过期时间不能为空");
            }
            Date expiresTime = null;
           
            if(datetype==1){
            	expiresTime = DateTime.now().plusDays(Integer.parseInt(expiresDay)).toDate();
            }else{
            	expiresTime = DateUtils.parse(expiresDate,DateUtils.FORMAT_SHORT);
            }
            Integer sendNums = Integer.valueOf(multipartRequest.getParameter("sendNums")); //发送数量   	
            if(sendNums==null || sendNums<=0 || sendNums>3)return new ResponseResult(false,"发送红包数量不能为0且小于3个");
            Integer type = Integer.valueOf(multipartRequest.getParameter("type")); //发送数量
            Set<String> msg = null;
            if(ObjectUtils.equals(type, 0)){
            	msg =  redpacketService.sendLcsRedPacket(file, redpacketId, sendNums, expiresTime, user.getUsername());
            }else  if(ObjectUtils.equals(type, 1)){
            	msg = redpacketService.sendCustomerRedPacket(file, redpacketId, sendNums,expiresTime, user.getUsername());
            }else{
            	return new ResponseResult(false,"请选择发放类型");
            }	
    		return new ResponseResult(true,"发送成功",msg);
		}catch (ServiceException e) {
			LOGGER.error("redpacketEdit exception : {}", e.getMessage());
			return new ResponseResult(false,e.getMessage());
		} catch (Exception e) {
			LOGGER.error("redpacketEdit exception : {}", e.getMessage());
		}
    	return new ResponseResult(false,"发送失败");
	}
    
    
    @RequestMapping(value="getProducts")
    @ResponseBody
    @RequestLogging("获取绑定产品列表")
	public Object getProducts(@RequestParam String  _dt_json) {   
    	try {
    		ProductSearchRequest dt = JsonUtils.fromJsonToObject(_dt_json, ProductSearchRequest.class);
    		DataTableReturn tableReturn = new DataTableReturn();
    		tableReturn.setDraw(dt.getDraw()+1);
    		Page<ProductPageResponse> page = new Page<ProductPageResponse>(dt.getStart()/dt.getLength()+1,dt.getLength());
    		List<ProductPageResponse> list = productService.queryProductByProductName(dt.getOrgNumer(), dt.getName(), page);
    		tableReturn.setData(list);
    		tableReturn.setRecordsFiltered(page.getTotalCount());
    		tableReturn.setRecordsTotal(page.getTotalCount());
    		return tableReturn;
		}catch (ServiceException e) {
			LOGGER.error("redpacketEdit exception : {}", e.getMessage());
			return new ResponseResult(false,e.getMessage());
		} catch (Exception e) {
			LOGGER.error("redpacketEdit exception : {}", e.getMessage());
		}
    	return new ResponseResult(false,"查询失败");
	}
    
    @RequestMapping(value="redpacketStatistics")
    @ResponseBody
    @RequestLogging("获取红包每日统计数据")
    public Object getRedpacketStatistics(@RequestParam String  _dt_json){
    	RedpacketStatisticsRequest dt = JsonUtils.fromJsonToObject(_dt_json, RedpacketStatisticsRequest.class);
    	
    	DataTableReturn tableReturn = new DataTableReturn();
    	
    	int total = 0;
    	tableReturn.setDraw(dt.getDraw()+1);
    	RedpacketStatisticsResponse statisticsResponse =  redpacketService.getRedpacketStatistics(dt.getDate());
    	if(statisticsResponse!=null){
    		total= 1 ;
    		tableReturn.setData(Lists.newArrayList(statisticsResponse));
    	}
		tableReturn.setRecordsFiltered(total);
		tableReturn.setRecordsTotal(total);
    	return tableReturn;
    }
    
	
}
