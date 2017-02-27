<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
 <!-- layer弹层组件 -->
 <script type="text/javascript" src="assets/plugins/layer/layer.js"></script> 
<script type="text/javascript" src="app/sysPushArtificialQueue/syspushartificialqueue-list.js"></script>
<style type="text/css">
	.link-display{  
		padding: 0px;
        margin: 0px;       
        /* overflow: hidden;
        white-space: nowrap;
        word-wrap: break-word;
        text-overflow: ellipsis; */
        }
	
</style>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>
<table id="dtable" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            <th>序号</th>            
            <th>推送内容</th>
            <th>跳转链接</th>
            <th>推送时间</th>
            <th>创建时间</th>
            <th>操作人</th>
            <th>状态</th>
            <th>操作</th> 
            </tr>
        </thead>
 
    </table>

	<script type="text/linkwee-template" id="template-tools">
            <div class="row">
                <div class="col-sm-3">
                    <div class="input-group">
                       <a class="btn btn-default btn-sm J_syspus_model" href="javascript:;" data-title="新增推送" data-url="/rest/cim/syspushartificialqueue/addPage" role="button" id = "syspush_add"><i class="fa fa-plus"></i>&nbsp;&nbsp;新增</a>
                    </div>
                </div>
            </div>
    </script>
