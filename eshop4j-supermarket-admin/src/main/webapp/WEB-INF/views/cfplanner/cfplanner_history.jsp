<%--
  Created by IntelliJ IDEA.
  User: lenli
  Date: 2016/5/27
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<table class="table table-bordered">
<thead>
<tr>
    <th>序号</th>
    <th>职级名称</th>
    <th>时间</th>
</tr>
</thead>
    <tbody>
<c:choose>
    <c:when test="${!empty queryList}">
        <c:forEach var="row" items="${queryList}" varStatus="item">
            <tr>
                <td><c:out value="${item.index+1}"/></td>
                <td><c:out value="${row.remarks}"/></td>
                <td><fmt:formatDate value="${row.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            </tr>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <tr>
            <td colspan="3" align="center">目前没有记录!</td>
        </tr>
    </c:otherwise>
</c:choose>

    </tbody>
</table>
