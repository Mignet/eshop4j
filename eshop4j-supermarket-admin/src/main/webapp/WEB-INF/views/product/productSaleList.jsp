<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="assets/plugins/easyui/easyloader.js"  ></script>
<script type="text/javascript">
easyloader.base = 'assets/plugins/easyui/';
easyloader.theme = 'bootstrap';
easyloader.locale = 'zh_CN';

easyloader.modules.common = {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	js: 'common.js'
};
easyloader.modules.my97DatePicker = {
		js: '../../My97DatePicker/WdatePicker.js'
};

</script>

    <table id="proSaleDg" title="产品销售"  style="width:100%;" ></table>
    <div id="proSaleToolbar"style="display: none;">
        <div>
        	<form id="proSaleQueryForm">
	        		产品名称： <input  name="productName" style="width:110px" placeholder="输入产品名称" />
	        	<select name="proCateId" id="proCateId">
	        	<option value="">全部</option>
			<c:forEach items="${proCateList}"  var="item" >
			<option value="${item.cateId }">${item.cateName }</option>
			</c:forEach>
			</select>
				&nbsp;&nbsp;<select name="saleStatus" id="saleStatus">
				<!-- 1-预售、2-在售、3-售罄、4-下架、5-定时发售 -->
			<option value="">全部</option>
			<option value="2">在售</option>
			<option value="3">售罄</option>
			<option value="4">下架</option>
			</select>
		         <a href="#" id="proSaleQueryButton" class="easyui-linkbutton" >&nbsp;&nbsp;查 询&nbsp;&nbsp;</a>
		       <!--  <a href="#" id="proSaleExcelBtn" class="easyui-linkbutton" >导出EXCEL</a> -->
	        </form>
        </div>
    </div>
 <div id="proSaleWin" class="easyui-window" data-options="iconCls:'icon-save',closed:true"></div>
<script type="text/javascript" src="app/common/util.js"></script>
<script type="text/javascript" src="app/product/productSale.js"></script>
