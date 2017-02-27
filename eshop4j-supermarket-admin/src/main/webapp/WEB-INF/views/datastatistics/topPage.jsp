<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <table id="top-table" class="table table-bordered">
				            <thead>
				                <tr>
				                	<th>序号</th>
				                    <th>理财师姓名</th>
						            <th>理财师地区</th>
						            <th>理财师手机号</th>
						            <th>获得佣金金额(元)</th>
				                </tr>
				            </thead>
				            <tbody>
				            <c:forEach var="data" items="${datas}" varStatus="status">
				        		 <tr>
				        		 	<td>${ status.index + 1}</td>
				                    <td>${data.name}</td>
				                    <td>${data.city}</td>
				                    <td>${data.mobile}</td>
				                    <td>${data.amt}</td>
				                </tr>
				        	</c:forEach>
				            </tbody>
				        </table>

