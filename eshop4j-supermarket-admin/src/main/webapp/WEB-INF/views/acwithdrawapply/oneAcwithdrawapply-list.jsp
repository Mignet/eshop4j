<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<!-- moment -->
<script type="text/javascript" src="assets/plugins/bootstrap-daterangepicker/moment.min.js"  ></script>
<!-- Editor -->
<script type="text/javascript" src="assets/plugins/data-tables/extensions/Editor/js/dataTables.editor.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Editor/css/editor.dataTables.min.css"  />

<script type="text/javascript" src="assets/plugins/data-tables/extensions/Buttons/js/dataTables.buttons.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Buttons/css/buttons.dataTables.min.css"  />

<script type="text/javascript" src="assets/plugins/data-tables/extensions/Select/js/dataTables.select.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Select/css/select.dataTables.min.css"  />

<script type="text/javascript" src="app/acwithdrawapply/oneAcwithdrawapply-list.js"></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>


<form name="appr-example" id="appr-example">
<table id="apprExample" class="display select" cellspacing="0" width="100%">
  <thead>
       <tr>
       <th><input name="select_all" value="1" id="apprexample-select-all" type="checkbox"></th>
       <th>id</th>
       <th>提现流水号</th>
       <th>客户姓名</th>
       <th>预留手机号</th>
       <th>提现时间</th>
       <th>审核时间</th>
       <th>通知时间</th>
       <th>交易总金额</th>
       <th>提值金额</th>
       <th>手续费</th>
       <th>提值状态</th>
       <th>类别</th>
       <th>批次号</th>
       <th>备注</th>
       </tr>
   </thead>
</table>
<p class="form-group">
   <button type="submit" class="btn btn-primary">审核通过</button>
</p>
</form>

