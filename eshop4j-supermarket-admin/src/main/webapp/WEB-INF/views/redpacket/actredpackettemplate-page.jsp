<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<script type="text/javascript" src="app/redpacket/actredpackettemplate-list.js"></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>


	<div class="page-header">
		<div class="row">
			<div class="col-md-3">


				 		<select class="form-control" id="cpaPlatform" >
				 			<option value="">不设置cpa主推平台</option>
				 			
				 			 <c:forEach var="platform" items="${cpa}" >
									<option value="${platform.platformId}" <c:if test="${cpaMainPlatform==platform.platformId }">selected="selected"</c:if> >${platform.platform}</option>
							</c:forEach>
					 	</select>
			</div>
			<div class="col-md-3">
			<div class="input-group">
                       <a class="btn btn-default btn-icon J_cpa_seting" href="javascript:;" role="button"><i class="fa fa-plus"></i>&nbsp;&nbsp;设置cpa主推平台</a>
                    </div>
			</div>
			</div>
			 <br> <br>
			<div class="row">

			<div class="col-md-3">
				 		<select class="form-control" id="cpsPlatform" >
				 			<option value="">不设置cps主推平台</option>
				 			 <c:forEach var="platform" items="${cps}" >
									<option value="${platform.platformId}" <c:if test="${cpsMainPlatform==platform.platformId }">selected="selected"</c:if> >${platform.platform}</option>
							</c:forEach>
					 	</select>
			</div>
			<div class="col-md-3">
			<div class="input-group">
                       <a class="btn btn-default btn-icon J_cps_seting" href="javascript:;"  role="button"><i class="fa fa-plus"></i>&nbsp;&nbsp;设置cps主推平台</a>
                    </div>
			</div>
		</div>


	</div>

	 <br>


<table id="dtable" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            <th>红包名称</th>
            <th>红包类型</th>
            <th>红包过期时长</th>
            <th>红包金额</th>
             <th>回款金额</th>
            <th>时间</th>
            <th>操作人</th>
            <th>操作</th>

            </tr>
        </thead>

    </table>

	<script type="text/linkwee-template" id="template-tools">
            <div class="row">
                <div class="col-sm-3">
                    <div class="input-group">
                       <a class="btn btn-default btn-icon ui-redirect" href="javascript:;" data-title="新增红包" data-url="/rest/redpackettemplate/addPage" role="button"><i class="fa fa-plus"></i>&nbsp;&nbsp;新增</a>
                    </div>
                </div>
            </div>
    </script>

