<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<input type="hidden" id="mobile" value="${mobile}">
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"></script>
<!-- moment -->
<script type="text/javascript" src="assets/plugins/bootstrap-daterangepicker/moment.min.js"  ></script>

<script type="text/javascript" src="app/fee/fee-record-page.js"></script>


<table id="fee-record-list" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
	            <th>理财师姓名</th>
	            <th>理财师手机号码</th>
	            <th>理财师佣金</th>
	            <th>发放月份</th>
	            <th>发放时间</th>
	            <th>状态</th>
	            <th>操作</th>
            </tr>
        </thead>
    </table>

	<script type="text/linkwee-template" id="template-tools">
            <div class="row">
                <div class="col-sm-1">
					<input id="month" placeholder="请选择月份" name="month" class="form-control Wdate" type="text" autocomplete="off" onfocus="WdatePicker({dateFmt:'yyyyMM'})">
                   
                </div>
				 <div class="col-sm-1">
						<a class="btn btn-default J_search" href="#" role="button"><i class="fa fa-search"></i></a>
                    </div>
            </div>
    </script>

