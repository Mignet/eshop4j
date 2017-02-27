<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<!-- moment -->
<script type="text/javascript" src="assets/plugins/bootstrap-daterangepicker/moment.min.js"  ></script>
<script type="text/javascript"  src="assets/plugins/layer/layer.js"></script>
<script type="text/javascript" src="app/redpacket/actredpacket-list.js"></script>


<table id="redpacket-list" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            <th>红包名称</th>
            <th>红包金额</th>
            <th>发放数量</th>
            <th>发放时间</th>
            <th>发放次数</th>
            <th>最近到期时间</th>
            <th>最晚到期时间</th>
            <th>添加时间</th>
            <th>状态</th>
            <th>操作人</th>
            <th>操作</th>
            </tr>
        </thead>
    </table>

	<script type="text/linkwee-template" id="template-tools">
            <div class="row">
                <div class="col-sm-3">
                    <div class="input-group">
                       <a class="btn btn-default btn-icon ui-redirect" href="javascript:;" data-title="新增红包" data-url="/rest/redpacket/addPage" role="button"><i class="fa fa-plus"></i>&nbsp;&nbsp;新增</a>
                    </div>
                </div>
            </div>
    </script>

