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

<script type="text/javascript" src="app/acaccounterrorlog/acaccounterrorlog-list.js"></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>
<table id="dtable" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            <th>Id</th>
            <th>批次号</th>
            <th>类名和方法</th>
            <th>发送参数</th>
            <th>返回参数</th>
            <th>创建时间</th>
            <th>描述</th>
            </tr>
        </thead>
 
    </table>


