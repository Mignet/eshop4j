<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<input type="hidden" id="mobile" value="${mobile}">
<input type="hidden" id="month" value="${month}">
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<!-- moment -->
<script type="text/javascript" src="assets/plugins/bootstrap-daterangepicker/moment.min.js"  ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="app/fee/fee-record-detail-page.js"></script>


<table id="fee-record-detail-list" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
	            <th>用户名称</th>
	            <th>手机号码</th>
	            <th>产品名称</th>
	            <th>投资金额(元)</th>
	            <th>佣金(元)</th>
	            <th>佣金率(%)</th>
	            <th>佣金类型</th>
	            <th>描述</th>
	            <th>时间</th>
            </tr>
          
        </thead>
    </table>

	<script type="text/linkwee-template" id="template-tools">
            <div class="row">
                <div class="col-sm-2">
                    <input name="customerMobile" class="form-control"   placeholder="请输入客户手机号码" autocomplete="off">
                </div>
				<div class="col-sm-2">
					<input id="time" placeholder="请选择佣金生成时间" name="time" class="Wdate" type="text" autocomplete="off" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">                  
                </div>
				<div class="col-sm-1">
                    <div class="input-group">
                		<a class="btn btn-default J_search" href="#" role="button"><i class="fa fa-search"></i></a>
                    </div>
                </div>
            </div>
    </script>

