<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="row">
				<div class="col-sm-3">
						<select id="type" class="form-control">
						
						
							<option value="0" <c:if test="${type==0 }">selected="selected"</c:if>>按周显示</option>
							<option value="1" <c:if test="${type==1 }">selected="selected"</c:if>>按月显示</option>
							<option value="2" <c:if test="${type==2 }">selected="selected"</c:if>>按日显示</option>
						</select>
				</div>
			</div>
   			 <br>
  <table id="top-table" class="table table-bordered">
				            <thead>
				            	<tr>
				            		<th>档位 \ 时间</th>
					            	<c:forEach var="title" items="${titles}">
					                	<th>${title}</th>
					                </c:forEach>
				                </tr>
				            </thead>
				            <tbody>
				            <c:forEach var="values" items="${valuess}" >
				        		 <tr>
				        		  <c:forEach var="value" items="${values}" varStatus="status">
				        		   	<td>${value}</td>
				                    </c:forEach>
				                 </tr>
				        	</c:forEach>
				            </tbody>
				        </table>

