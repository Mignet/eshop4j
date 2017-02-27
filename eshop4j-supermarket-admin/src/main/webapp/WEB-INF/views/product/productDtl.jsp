<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
 request.setAttribute("ctx", request.getContextPath());
%>

<input id="path" type="hidden" value="${ctx}" />
<input id="productId" type="hidden" value="${product.productId}" />
 <div>
<form id="proDtlForm">
<table style="width: 100%;line-height: 30px;">
<tbody>
<tr>
<td align="right" width="25%">名称：</td>
<td width="30%">${product.productName}</td>
<td align="right" width="25%">类型：</td>
<td>${product.typeName} </td>
</tr>

<tr>
<td align="right" width="25%">理财师标签：</td>
<td width="30%"> ${product.invLabel} </td>
<td align="right" width="25%">投资端标签：</td>
<td> ${product.lcsLabel}</td>
</tr>

<tr>
<td align="right" width="25%">预期年化：</td>
<td width="30%">${product.fixRate}%</td>
<td align="right" width="25%">收益方式：</td>
<td>${product.repaymentTypeName} </td>
</tr>

<tr>
<td align="right" width="25%">起投金额：</td>
<td width="30%">${product.buyMinMoney} </td>
<td align="right" width="25%"></td>
<td></td>
</tr>

<tr>
<td align="right" width="25%">佣金：</td>
<td width="30%" >${product.feeRatio} </td>
<td align="right" width="25%">佣金结算：</td>
<td>${product.buyMinMoney} </td>
</tr>

<tr>
<td align="right" width="25%">销售奖励：</td>
<td width="30%">${product.saleReward} </td>
<td align="right" width="25%">投资期限：</td>
<td>${product.deadLineValue} </td>
</tr>

<tr>
<td align="right" width="25%">限额：</td>
<td width="30%"> ${product.deadLineType}</td>
<td align="right" width="25%">状态：</td>
<td>${product.statusName} </td>
</tr>

<tr>
<td align="right" width="25%">上架时间：</td>
<td width="30%"><fmt:formatDate  value="${product.collectBeginTime}" type="date" pattern="yyyy-MM-dd" /> </td>
<td align="right" width="25%">下架时间：</td>
<td><fmt:formatDate  value="${product.collectEndTime}" type="date" pattern="yyyy-MM-dd" /></td>
</tr>

<tr>
<td align="right" width="25%">起息日：</td>
<td width="30%"><fmt:formatDate  value="${product.validBeginDate}" type="date" pattern="yyyy-MM-dd" /></td>
<td align="right" width="25%">到期日：</td>
<td><fmt:formatDate  value="${product.validEndDate}" type="date" pattern="yyyy-MM-dd" /> </td>
</tr>

<tr>
<td align="right" width="25%">产品说明：</td>
<td  colspan="3"><textarea rows="8" cols="80" readonly="readonly">
${product.productDescStr }
</textarea> </td>
</tr>

<tr>
<td colspan="4" align="center">
<input type="button" id="btnCancel" value="取&nbsp;&nbsp;&nbsp;&nbsp;消" onclick="closeWin()"/>
<!-- <input type="button" id="btnUpdate" value="修&nbsp;&nbsp;&nbsp;&nbsp;改" onclick="closeWin()"/> -->
</td>
</tr>


</tbody>
</table>
</form>
</div> 
<script type="text/javascript" >
function closeWin(){
	var $window = self.parent.$("#proWin").window();
	$window.window('close');
}
$('#btnCancel').click(function() {
	closeWin();
});

</script>
