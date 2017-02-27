<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
<shiro:hasPermission name="product-sale:modify">
	<input type="hidden" id="product-sale-modify" />
</shiro:hasPermission>
<shiro:hasPermission name="product-sale:publish">
	<input type="hidden" id="product-sale-publish" />
</shiro:hasPermission>
<shiro:hasPermission name="product-sale:back">
	<input type="hidden" id="product-sale-back" />
</shiro:hasPermission>
<table id="proDg" title="产品列表"  style="width:100%;" ></table>
    <div id="proToolbar"style="display: none;">
        <div>
        	<form id="proQueryForm">
	        	产品名称： <input placeholder="输入产品名称" name="productName" style="width:110px">
	        	<select name="proCateId" id="proCateId">
	        	<option value="">全部</option>
			<c:forEach items="${proCateList}"  var="item" >
			<option value="${item.cateId }">${item.cateName }</option>
			</c:forEach>
			</select>
				&nbsp;&nbsp;<select name="saleStatus" id="saleStatus">
				<!-- 1-预售、2-在售、3-售罄、4-下架、5-定时发售 -->
			<option value="">全部</option>
			<option value="1">预售</option>
			<option value="2">在售</option>
			<option value="3">售罄</option>
			<option value="4">下架</option>
			<option value="5">定时发售</option>
			<option value="6">灰度</option>
			</select>
			 
		        <a href="#" id="proQueryButton" class="easyui-linkbutton" >&nbsp;&nbsp;查 询&nbsp;&nbsp;</a>
			 <shiro:hasPermission name="product-sale:modify">
			<select name="productCate" style="margin-left: 150px;margin-right:0px;" id="productCate">
				<option value="1001">固定收益产品</option>
				<option value="1002">浮动收益产品</option>
			</select> 	       
		        <a href="#" id="proAddBt" class="easyui-linkbutton" style="margin-left: 30px" >&nbsp;&nbsp;添加产品&nbsp;&nbsp;</a>
		        </shiro:hasPermission>
	        </form>
        </div>
    </div>
    <div id="proWin" class="easyui-window" data-options="iconCls:'icon-save',closed:true"></div>

<script type="text/javascript" src="app/common/util.js"></script>
<script type="text/javascript" src="app/product/productList.js"></script>
