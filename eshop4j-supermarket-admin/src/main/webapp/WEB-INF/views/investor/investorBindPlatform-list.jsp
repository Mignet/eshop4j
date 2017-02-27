<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<!-- moment -->
<!-- Editor -->
<script type="text/javascript" src="assets/plugins/data-tables/extensions/Buttons/js/dataTables.buttons.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Buttons/css/buttons.dataTables.min.css"  />

<script type="text/javascript" src="assets/plugins/data-tables/extensions/Select/js/dataTables.select.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Select/css/select.dataTables.min.css"  />

<script type="text/javascript" src="app/investor/investorBindPlatform-list.js"></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>
<style type="text/css">
.table th, .table td{
vertical-align: middle !important; /*表格内容优先垂直居中*/
}
</style>
<table id="dtable" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            <th>机构名称</th>
            <th>机构编码</th>
            <th>第三方机构帐号</th>
            <th>是否机构新用户</th>
            <th>是否有投资</th>
            <th>第三方机构账号类别</th>
            <th>绑定时间</th>
            </tr>
        </thead>
 
    </table>
    

 
 <script type="text/template" id="template-search">
        <form>
            <div class="row">
                <div class="col-sm-2">
                    <div class="input-group">
                        <input name="mobile" class="form-control"   placeholder="请输入投资人手机号码" autocomplete="off">
                        <span class="input-group-btn">
                             <a class="btn btn-default J_search" href="javacript:void(0);" role="button"><i class="fa fa-search"></i> 查询</a>
                        </span>
                    </div>
                </div>

            </div>

        </form>
    </script>

 