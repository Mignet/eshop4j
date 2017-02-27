<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<div class="container-fluid">
	<div class="table-responsive">
		<table id="redpaper-product-list-table" class="table table-bordered" style="width:100%;">
			<thead>
				<tr>
					<th>类型</th>
					<th>名称</th>
					<th>期限</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
	<script type="text/linkwee-template" id="template-search">
		<input name="pname"  class="easyui-textbox" style="width:200px"  placeholder="产品名称">
		<select name="pdeadline">
			<option value="0">不限</option>
			<option value="29">29期</option>
			<option value="90">90期</option>
			<option value="92">92期</option>
			<option value="180">180期</option>
			<option value="183">183期</option>
		</select>
		<a class="btn btn-default btn-sm J_redpaperProductList_search" href="javascript:;" role="button">查询</a>
	</script>
<script type="text/javascript" src="app/activity/redpaper-product-list.js"></script>
</div>
