<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />

<!-- Editor -->
<script type="text/javascript" src="assets/plugins/data-tables/extensions/Editor/js/dataTables.editor.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Editor/css/editor.dataTables.min.css"  />

<script type="text/javascript" src="assets/plugins/data-tables/extensions/Buttons/js/dataTables.buttons.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Buttons/css/buttons.dataTables.min.css"  />

<script type="text/javascript" src="assets/plugins/data-tables/extensions/Select/js/dataTables.select.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Select/css/select.dataTables.min.css"  />

<script type="text/javascript" src="app/sys/role-list.js"></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>
<p>角色是权限的集合</p>
<table id="dtable" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>角色编号</th>
                <th>角色名称</th>
                <th>角色标识</th>
                <th>描述</th>
                <th>操作</th>
            </tr>
        </thead>
 
       <!--  <tfoot>
            <tr>
             	<th>角色编号</th>
                <th>角色名称</th>
                <th>角色标识</th>
                <th>描述</th>
                <th>操作</th>
            </tr>
        </tfoot> -->
    </table>

<!--------------------------分配权限信息的弹出层---------------------------->
<div class="modal fade" id="roleModal" tabindex="-1" role="dialog" 
   aria-labelledby="roleModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="roleModalLabel">
                <i class="icon-pencil"></i><span id="lblAddTitle" style="font-weight:bold">分配权限</span>
            </h4>
         </div>
            <form class="form" id="ffRoles" >
                <div class="modal-body" >
                    <div  id="permissions-div">
                            	<!-- 选择 -->
                    </div>
                </div>
                <!-- HIDDEN FILEDS -->
	             <input type="hidden" id="roleid" name="roleid" />
                <!-- HIDDEN FILEDS END -->
	         <div class="modal-footer">
	                <button id="confirm_btn" type="button" class="btn blue" onclick="submitPermissions();">确定</button>
	                <button id="reset_btn" type="reset" class="btn green" data-dismiss="modal">取消</button>
	         </div>
         </form>
      </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->