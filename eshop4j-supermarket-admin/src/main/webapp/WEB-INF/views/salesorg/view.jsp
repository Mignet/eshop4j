<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
<form id="redpacketFrom">
			<div class="page-header">
				<div class="row">
					<div class="col-md-8">
						<h4><small><p class="text-muted">基本信息</p></small></h4>
					</div>
				</div>
			</div>
			
			<div class="row">
				<label class="col-sm-2 control-label">机构名称:</label>
				<div class="col-md-4">
					 ${crmSalesOrg.name}
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">机构编码:</label>
				<div class="col-md-4">
					 ${crmSalesOrg.salesOrgId}
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">所在城市:</label>
				<div class="col-md-4">
				 ${crmSalesOrg.city}
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">法人代表:</label>
				<div class="col-md-4">
				 ${crmSalesOrg.legalPerson}
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">联系人:</label>
				<div class="col-md-4">
				${crmSalesOrg.contacts}
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">联系方式:</label>
				<div class="col-md-4">
				${crmSalesOrg.contactMobile}
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">管理账户:</label>
				<div class="col-md-4">
				${crmSalesOrg.managerAccount}
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">开始合作时间:</label>
				<div class="col-md-4">
				<fmt:formatDate  value="${crmSalesOrg.cooperationTime}" type="date" pattern="yyyy-MM-dd" />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">合作状态:</label>
				<div class="col-md-4">
				<c:if test="${crmSalesOrg.cooperationStatus == 1}">合作中</c:if>
				<c:if test="${crmSalesOrg.cooperationStatus == 2}" >暂停合作</c:if>
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">下属理财师数量:</label>
				<div class="col-md-4">
				${crmSalesOrg.cfplannerCount}
				</div>
			</div>
			
			<div class="page-header">
				<div class="row">
					<div class="col-md-8">
						<h4><small><p class="text-muted">销售业绩</p></small></h4>
					</div>
				</div>
			</div>
			
			<div class="row">
				<label class="col-sm-2 control-label">累计销售总额:</label>
				<div class="col-md-4">
					 ${crmSalesOrg.historySales} 万元
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">本月销售业绩:</label>
				<div class="col-md-4">
					 ${crmSalesOrg.thisMonthSales} 万元
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">本月销售费用:</label>
				<div class="col-md-4">
					 ${crmSalesOrg.thisMonthFee} 元
				</div>
			</div>
			
			<div class="row">
				 <div class="col-md-2 col-md-offset-4">
				 	<a class="btn btn-default active J_goback" role="button"  >返回</a>
				 </div>
			</div>
</form>
</div>



 <!-- 模态框（Modal）-->
<script type="text/javascript"  src="assets/plugins/layer/layer.js"></script>
<script type="text/javascript" >

$(document).ready(function() {

    $(".J_goback").on("click", function(event) {
        $.switchPage("机构列表","rest/crmSalesOrg/salesOrgListPage"); //跳到机构列表页面
        return false;
    });

} );
</script>
