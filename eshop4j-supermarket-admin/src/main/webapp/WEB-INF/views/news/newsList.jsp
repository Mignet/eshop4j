<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.eshop4j.web.enums.NewsTypeEnum" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
NewsTypeEnum[] newsTypeList = NewsTypeEnum.values();
request.setAttribute("newsTypeList", newsTypeList);
%>
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
    <table id="newsDg" title="资讯列表"  style="width:100%;" ></table>
    <div id="newsToolbar"style="display: none;">
        <div>
		<form id="newsQueryForm">
			类别：<select class="easyui-combobox" name="typeCode" panelHeight="auto" style="width: 120px; height: 37px;">
		<c:forEach var="item" items="${newsTypeList }" varStatus="dn">
			<option value="${item.key }">${item.value }</option>
		</c:forEach>
	</select>标题： <input class="easyui-textbox" name="title" style="width: 110px" />

			<a href="#" id="newsQueryButton" class="easyui-linkbutton">&nbsp;&nbsp;查
				询&nbsp;&nbsp;</a> <a href="#"  data-id="newsAddBtn"  class="easyui-linkbutton ui-redirect" data-title="新增资讯" data-url="/rest/news/tosave">新增</a>
		</form>
	</div>
    </div>
    <div id="newsWin" class="easyui-window"  data-options="iconCls:'icon-save',closed:true">

<script type="text/javascript" src="app/common/util.js"></script>
<script type="text/javascript" src="app/news/newsList.js"></script>

