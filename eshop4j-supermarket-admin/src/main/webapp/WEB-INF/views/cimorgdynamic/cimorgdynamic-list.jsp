<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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


<script type="text/javascript" src="app/cimorgdynamic/cimorgdynamic-list.js"></script>

<style type="text/css">
	.table th, .table td{
		vertical-align: middle !important; /*表格内容优先垂直居中*/
	}
</style>
<table id="dtable" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            <th>所属机构</th>
            <th>机构动态标题</th>
            <th>创建人</th>
            <th>发布时间</th>
            <th>操作</th>
            </tr>
        </thead>
 
</table>
 <!-- layer弹层组件 -->
 <script type="text/javascript" src="assets/plugins/layer/layer.js"></script> 
 <script type="text/template" id="template-search">
        <form>
            <div class="row">
                <div class="col-sm-2">
                   <div class="input-group">
							 <span class="input-group-btn">			
 								<select id="org_select" name="orgName" class="form-control" style="width: 150px;display: inline-block;margin-right: 20px">
			                        	<option value="">请选择机构</option>
										<c:forEach items="${orgList}" var="org">
			                            		<option value="${org.orgName}">${org.orgName}</option>
										</c:forEach>
		                        </select>
 							</span>
                        <span class="input-group-btn">
                            <input type="text" name="orgTitle" class="form-control" style="width: 150px;margin-right: 20px"  placeholder="机构动态标题" autocomplete="off" />
                        </span>
						<span class="input-group-btn">
                            <input type="text" name="startTime" id="startTime" style="width: 150px;margin-right: 10px;" class="form-control" autocomplete="off" placeholder="发布起始时间" />
                        </span>
						<span class="input-group-btn">
							&#126;
						</span>
						<span class="input-group-btn">
                            <input type="text" name="endTime" id="endTime" style="width: 150px;margin-left: 10px" class="form-control" autocomplete="off" placeholder="发布结束时间" />
                        </span>
                        <span class="input-group-btn">
                             <a class="btn btn-default J_search" style="margin-left: 20px" role="button"><i class="fa fa-search"></i> 查询</a>
                        </span>
					    <span class="input-group-btn">
							<a class="btn btn-default btn-icon ui-redirect" style="margin-left: 20px" data-title="新增机构动态" data-url="/rest/cim/cimorgdynamic/toAdd" role="button"><i class="fa fa-plus"></i> 新增</a>
					 	</span>
                    </div>
                </div>
				
            </div>

        </form>
    </script>
