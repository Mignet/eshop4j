<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style type="text/css">
	.uk-blank{ margin: 0; padding: 0; height: 10px; clear: both; overflow: hidden;}
	th{text-align: center;}
</style>
<div class="container">

<input type="hidden" id="path" value="<%=request.getContextPath() %>" />
<input type="hidden" id="operator" value="${userInfo.username}" />

	<div class="row">
		<div class="page-header">
			<h4><small>红包统计</small></h4>
		</div>
		<div class="uk-blank"></div>
		<table style="border:1px solid #dcdcdc; text-align: center;">
		  <tr style="border:1px solid #dcdcdc;">
		    <th style="border:1px solid #dcdcdc;" width="160px">发放红包的理财师人数</th>
		    <th style="border:1px solid #dcdcdc;" width="160px">理财师发放的红包数量</th>
		    <th style="border:1px solid #dcdcdc;" width="160px">理财师发红包的客户数量</th>
		    <th style="border:1px solid #dcdcdc;" width="160px">使用红包的客户数量</th>
		    <th style="border:1px solid #dcdcdc;" width="160px">红包使用数量</th>
		    <th style="border:1px solid #dcdcdc;" width="160px">红包使用金额</th>
		  </tr>
		  <tr style="border:1px solid #dcdcdc;">
		    <td style="border:1px solid #dcdcdc;" width="100px">${rcal.sendRedpacketlcsCounts}</td>
		    <td style="border:1px solid #dcdcdc;" width="100px">${rcal.lcsSendRedpacketCounts}</td>
		    <td style="border:1px solid #dcdcdc;" width="100px">${rcal.lcsSendRedpacketCustomerCounts}</td>
		    <td style="border:1px solid #dcdcdc;" width="100px">${rcal.useRedpacketCustomerCounts}</td>
		    <td style="border:1px solid #dcdcdc;" width="100px">${rcal.redpacketUseCounts}</td>
		    <td style="border:1px solid #dcdcdc;" width="100px">${rcal.redpacketUseAmount}</td>
		  </tr>
		</table>
	</div>
	
	<div class="row">
			<div class="page-header">
				<h4><small>详细信息</small></h4>
			</div>
			<p>红包ID：${redpaperInfo.id}</p>
			<p>红包类型：投资返现红包</p>
			<p>红包名称：${redpaperInfo.showName}</p>
			<p>红包描述：${redpaperInfo.remark}</p>
			<p>红包金额：${redpaperInfo.redpaperMoney} 元</p>
			<p>红包发送人数：${rcal.lcsSendRedpacketCustomerCounts} 人</p>
			<p>红包发送数量：${rcal.lcsSendRedpacketCounts} 个</p>
			<p>添加时间：<fmt:formatDate value="${redpaperInfo.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
			<p>发送时间：<fmt:formatDate value="${redpaperInfo.sendTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
			<p>过期时间：<fmt:formatDate value="${redpaperInfo.expireDate}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
	</div>
	
	
	<div class="row">
			<div class="page-header">
				<h4><small>红包使用条件</small></h4>
			</div>
				<p>金额限制:</p>
					<c:if test="${redpaperUserInfo.limitMoney==0}">不限</c:if>
					<c:if test="${redpaperUserInfo.limitMoney==1}">
						最小金额:<c:if test="${redpaperUserInfo.minMoney==0}">不限</c:if><c:if test="${redpaperUserInfo.minMoney!=0}">${redpaperUserInfo.minMoney} </c:if>,
						最大金额:<c:if test="${redpaperUserInfo.maxMoney==0}">不限 </c:if><c:if test="${redpaperUserInfo.maxMoney!=0}">${redpaperUserInfo.maxMoney}</c:if>
					</c:if>
				<p>投资用户限制:</p>
					<c:if test="${redpaperUserInfo.limitInvestUser==0}">不限</c:if>
					<c:if test="${redpaperUserInfo.limitInvestUser==1}">首次投资用户使用</c:if>
				<p>投资产品限制:</p>
					<c:if test="${redpaperUserInfo.limitInvestProduct==0}">不限</c:if>
					<c:if test="${redpaperUserInfo.limitInvestProduct==1}">
						<c:if test="${redpaperUserInfo.operator==0}">等于 ${redpaperUserInfo.deadline}天期</c:if>
						<c:if test="${redpaperUserInfo.operator==1}">大于等于 ${redpaperUserInfo.deadline}天期</c:if>
					</c:if>
					<c:if test="${redpaperUserInfo.limitInvestProduct==2}">产品编号</c:if>

			<c:if test="${redpaperUserInfo.limitInvestProduct==2}">
				 <p>仅特定产品才能使用该红包</p>
					<div class="uk-blank"></div>
						<table style="border:1px solid #dcdcdc; text-align: center;">
						  <tr style="border:1px solid #dcdcdc;">
						    <th style="border:1px solid #dcdcdc;" width="160px">产品名称</th>
						    <th style="border:1px solid #dcdcdc;" width="160px">产品期限</th>
						    <th style="border:1px solid #dcdcdc;" width="160px">年化收益</th>
						    <th style="border:1px solid #dcdcdc;" width="160px">开售时间</th>
						  </tr>
						  <c:forEach var="product" items="${ps}">
							  <tr style="border:1px solid #dcdcdc;">
							    <td style="border:1px solid #dcdcdc;" width="100px">${product.productName}</td>
							    <td style="border:1px solid #dcdcdc;" width="100px">${product.deadLineValue}</td>
							    <td style="border:1px solid #dcdcdc;" width="100px">${product.fixRate}</td>
							    <td style="border:1px solid #dcdcdc;" width="100px"><fmt:formatDate value="${product.beginSaleTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							  </tr>
						  </c:forEach>
						</table>
				</div>
			</c:if>
		</div>
	</div> 
</div>