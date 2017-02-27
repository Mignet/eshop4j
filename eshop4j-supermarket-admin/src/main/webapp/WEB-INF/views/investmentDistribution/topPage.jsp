<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <table id="top-table" class="table table-bordered">
				            <thead>
				                <tr>
				                	<th>序号</th>
				                	<th>投资人姓名</th>
				                	<th>投资人手机号</th>
				                    <th>理财师姓名</th>
						            <th>理财师地区</th>
						            <th>理财师手机号</th>
						            <th>用户投资总额(万元)</th>
						            <th>平均年化金额(万元)</th>
				                </tr>
				            </thead>
				            <tbody>
				            <c:forEach var="data" items="${datas}" varStatus="status">
				        		 <tr>
				        		 	<td>${ status.index + 1}</td>
				                    <td>${data.userName}</td>
				                    <td>${data.mobile}</td>
				                    <td>${data.cfpName}</td>
				                    <td>${data.cfpCity}</td>
				                    <td>${data.cfpMobile}</td>
				                    <td>${data.productAmount}</td>
				                    <td>${data.yearpurAmount}</td>
				                </tr>
				        	</c:forEach>
				            </tbody>
				        </table>

