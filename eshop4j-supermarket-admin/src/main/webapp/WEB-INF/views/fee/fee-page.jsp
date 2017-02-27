<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<!-- moment -->
<script type="text/javascript" src="assets/plugins/bootstrap-daterangepicker/moment.min.js"  ></script>
<!-- layer弹层组件 -->
<script type="text/javascript"  src="assets/plugins/layer/layer.js"></script>
<script type="text/javascript" src="app/fee/fee-page.js"></script>


<table id="fee-list" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
	            <th>理财师姓名</th>
	            <th>理财师手机号码</th>
	            <th>理财师总佣金</th>
	            <th>发放佣金次数</th>
	            <th>上次发放月份</th>
	            <th>上次发放时间</th>
	            <th>操作</th>
            </tr>
        </thead>

    </table>


	<script type="text/eshop4j-template" id="template-tools">
			
            <div class="row">
                <div class="col-sm-3">
                    <div class="input-group">
                        <input name="mobile" class="form-control"   placeholder="请输入手机号码" autocomplete="off">
                        <span class="input-group-btn">
                             <a class="btn btn-default J_search" href="#" role="button"><i class="fa fa-search"></i></a>
                        </span>
                    </div>
                </div>
				<shiro:hasPermission name="fee:calc">
				<div class="col-sm-3 col-sm-offset-3">
						
   						<a class="btn btn-danger J_fee_calcu" href="#" role="button"><i class="fa fa-list"></i> 计算上月佣金&nbsp;&nbsp;(不可逆)</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
					
						<a class="btn btn-default J_fee_download" href="/rest/fee/feeDownload" role="button"><i class="fa fa-download"></i> 导出佣金计算结果</a>
                </div>
				</shiro:hasPermission>
				<shiro:hasPermission name="fee:pay">
				<div class="col-sm-2 col-sm-offset-1">
                    <a class="btn btn-danger J_fee_pay" style="border-width:1px; border-style:solid; border-color:#c02121;" href="#" role="button"><i class="fa fa-play"></i> 发放佣金&nbsp;&nbsp;(不可逆)</a>
                </div>
				</shiro:hasPermission>
            </div>
    </script>

