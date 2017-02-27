<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <style>
.prdAdd{
	width: 800px;
}
.leftsp{
	width: 200px;
	text-align: right;
	float: left;
	height: 40px;
	font-weight: bolder;
}
.rightsp{
	width: 600px;
	text-align: left;
	float: right;
	height: 40px;
}
input[type="text"] {
	width: 220px;
}
select{
	width: 100px;
}
</style> 
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<div >
	<form  id="productAaddForm" method="post">
	<input name="productId" value="${dtl.productId }" type="hidden">
	 <fieldset style="margin:20px 20px;">
    <legend>基本信息</legend>
		<div class="prdAdd" style="margin-top: 40px">
			<span class="leftsp">产品名称：</span>
			<span class="rightsp"><input  class="easyui-validatebox" type="text" name="productName"  disabled="disabled" data-options="required:true" id="productName" value="${dtl.productName }" ></span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">产品类型：</span>
			<span class="rightsp">
			<%-- <input id="productCate" class="easyui-combobox" name="productCate"
    data-options="valueField:'cateId',textField:'cateName',url:'rest/productos/allpubliccate'" value="${dtl.productCate }"> --%>
    <select name="productCate" id="productCate" disabled="disabled" style="width:120px; ">
			<%-- <c:forEach items="${proCateList}"  var="item" >
			<option value="${item.cateId }" <c:if test="${item.cateId eq dtl.productCate }">selected</c:if> >${item.cateName }</option>
			</c:forEach> --%>
			<option value="1001">固收产品</option>
			</select> 
			<input id="productCate" name="productCate" value="${dtl.productCate}" type="hidden" />
			</span>
		</div>
		<%-- <div class="prdAdd">
			<span class="leftsp">起息日：</span>
			<span class="rightsp"><input  name="validBeginDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width:100px" 
			value="${dtl.validBeginDate }">
			</span>
		</div> --%>
		<div class="prdAdd">
			<span class="leftsp">产品总额：</span>
			<span class="rightsp">
			<input class="easyui-numberbox" type="text" name="buyTotalMoney" disabled="disabled" data-options="required:true" value="<fmt:formatNumber value="${dtl.buyTotalMoney }" pattern="0.00"/>" >
			<form:errors path="buyTotalMoney" cssClass="error" /> 
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">起投金额：</span>
			<span class="rightsp"><input class="easyui-numberbox" disabled="disabled" type="text" name="buyMinMoney"  missingMessage="" data-options="required:true" value="<fmt:formatNumber value="${dtl.buyMinMoney }" pattern="0.00"/>">
			<form:errors path="buyMinMoney" cssClass="error" /> 
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">递增金额：</span>
			<span class="rightsp"><input class="easyui-numberbox" type="text" disabled="disabled" name="buyIncreaseMoney"  data-options="required:true" value="<fmt:formatNumber value="${dtl.buyIncreaseMoney }" pattern="0.00"/>">
			<form:errors path="buyIncreaseMoney" cssClass="error" /> 
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">单用户购买上限：</span>
			<span class="rightsp"><input class="easyui-numberbox" type="text" disabled="disabled" name="custBuyMaxMoney" data-options="required:true" value="<fmt:formatNumber value="${dtl.custBuyMaxMoney }" pattern="0.00"/>">
			<form:errors path="custBuyMaxMoney" cssClass="error" /> 
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">投资期限：</span>
			<span class="rightsp"><input class="easyui-numberbox" type="text" disabled="disabled" name="deadLineValue" data-options="required:true" value="${dtl.deadLineValue }">天
			<form:errors path="custBuyMaxMoney" cssClass="error" /> 
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">起息方式：</span>
			<span class="rightsp">
			<%-- <input name="interestWay" type="radio" disabled="disabled" <c:if test="${dtl.interestWay eq 1}">checked="checked"</c:if> value="1" checked="checked">购买当日 <input name="interestWay" disabled="disabled" type="radio" <c:if test="${dtl.interestWay eq 2}">checked="checked"</c:if> value="2" > 购买次日 --%>
			<select name="interestWay" disabled="disabled">
			<option value="2" <c:if test="${dtl.interestWay eq 2}">selected="selected"</c:if>>购买次日</option>
			</select>
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">收益方式：</span>
			<span class="rightsp">
				<select name="repaymentWay" disabled="disabled">
					<option value="1"  <c:if test="${dtl.repaymentWay eq 1}">selected="selected"</c:if>>一次性到期</option>
					<option value="2" <c:if test="${dtl.repaymentWay eq 2}">selected="selected"</c:if>>一次性按日</option>
					<option value="3" <c:if test="${dtl.repaymentWay eq 3}">selected="selected"</c:if>>一次性按月</option>
					<option value="4" <c:if test="${dtl.repaymentWay eq 4}">selected="selected"</c:if>>一次性按季</option>
					<option value="5" <c:if test="${dtl.repaymentWay eq 5}">selected="selected"</c:if>>等额本息(按月)</option>
					<option value="6" <c:if test="${dtl.repaymentWay eq 6}">selected="selected"</c:if>>等额本息(按季)</option>
				</select>
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">产品说明页：</span>
			<span class="rightsp"><input class="easyui-validatebox" type="text" name="productIllustrationUrl"  id="productIllustrationUrl" data-options="required:true" value="${dtl.productIllustrationUrl }">
			<input type="button" value="添加" id="dtlPageAddBtn">
			<div id="dtlPageDiv" style="display: inline;">
			</div>
			</span>
			
		</div>
		<div class="prdAdd">
			<span class="leftsp">安全保障页：</span>
			<span class="rightsp"><input class="easyui-validatebox" type="text" name="securityGuaranteeUrl" id="securityGuaranteeUrl" value="${dtl.securityGuaranteeUrl }" data-options="required:true" >
			<input type="button" value="添加"  id="securePageAddBtn">
			<div id="securePageDiv" style="display: inline;">
			</div>
			</span>
			
		</div>
		<div class="prdAdd">
			<span class="leftsp">投资端首页推荐：</span>
			<span class="rightsp">
				<input name="isRecommended" type="radio" <c:if test="${!dtl.canShowIndexRecomonded}">disabled="disabled"</c:if> value="2" <c:if test="${dtl.isRecommended eq 2}">checked="checked"</c:if> >否 
				<input name="isRecommended" <c:if test="${dtl.isRecommended eq 1}">checked="checked"</c:if> type="radio" <c:if test="${!dtl.canShowIndexRecomonded}">disabled="disabled"</c:if> value="1" id="indexRecoment"> 是
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">分类排序：</span>
			<span class="rightsp">
				<%-- <select name="cateSort">
						<c:forEach var="item" begin="1" end="10" step="1">
							<option value="${item }"
								<c:if test="${dtl.cateSort eq item}">selected="selected"</c:if>>${item }</option>
						</c:forEach>
				</select> --%>
				<input  name="cateSort" type="text" class="easyui-numberbox" style="width:120px; " value="${dtl.cateSort}" />
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">热门推荐：</span>
			<span class="rightsp">
				<input name="isListRecommended" type="radio"  value="0" <c:if test="${dtl.isListRecommended eq 0}">checked="checked"</c:if>>否 <input name="isListRecommended" type="radio" <c:if test="${dtl.isListRecommended eq 1}">checked="checked"</c:if> value="1" id="isListRecoment"> 是
					<div id="ListRecomentDiv" style="display: <c:if test="${dtl.isListRecommended eq 0 }">none</c:if><c:if test="${dtl.isListRecommended eq 1 }">inline</c:if>;">
			<%-- <select name="listRecommendedSort">
						<c:forEach var="item" begin="1" end="5" step="1">
							<option value="${item }"
								<c:if test="${dtl.listRecommendedSort eq item}">selected="selected"</c:if>>${item }</option>
						</c:forEach>
					</select> --%>
					<input  name="listRecommendedSort" type="text" class="easyui-numberbox" style="width:120px; " value="${dtl.listRecommendedSort}" />
			推荐语：<input type="text" style="width:120px; " name="listRecomendedStr" id="listRecomendedStr" value="${dtl.listRecomendedStr }" />最多15个字符，留空则不显示
			</div>
			</span>
		
		</div>
		</fieldset>
			<!-- 募集信息 -->
		<div style="padding-top: 30px;text-align:center;width: 90%;">
			<span>-------------------------------------募集信息------------------------------------------------</span>
		</div>
		<div class="prdAdd">
			 <span class="leftsp">是否进行募集：</span> 
			<span class="rightsp">
				<input name="isCollect" type="radio"  value="1" <c:if test="${dtl.isCollect eq 1}">checked="checked"</c:if> disabled="disabled" >不进行募集 &nbsp;&nbsp;<input name="isCollect" type="radio" disabled="disabled"  value="2" <c:if test="${dtl.isCollect eq 2}">checked="checked"</c:if> > 进行限期募集
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">起息方式：</span>
			<span class="rightsp">
			<div id="collectInterestWayDiv" style="display:<c:if test="${dtl.isCollect eq 1}">none</c:if><c:if test="${dtl.isCollect eq 2}">inline</c:if> ;" >
			<input name="interestWayType" type="radio"  value="1" disabled="disabled" <c:if test="${ empty dtl.validBeginDate }">checked="checked"</c:if>>
			</div>
			<span id="interestWaySpan">投资后</span>			
			
			<select name="interestWay" id="interestWay" disabled="disabled">
			<c:if test="${dtl.isCollect eq 1}">
			<option value='1'>T+0</option>
			<option value='2'>T+1</option>
			</c:if>
			
			<c:if test="${dtl.isCollect eq 2}">
			<option value='3'>T+0</option>
			<option value='4'>T+1</option>
			</c:if>
			
			</select>起息&nbsp;&nbsp;
			 <div id="validBeginDateDiv" style="display: <c:if test="${dtl.isCollect eq 1}">none</c:if><c:if test="${dtl.isCollect eq 2}">inline</c:if>;">
			 <input name="interestWayType" type="radio"  value="2" disabled="disabled" id="indexRecoment" <c:if test="${! empty dtl.validBeginDate }">checked="checked"</c:if>> 固定日期起息
			 <input class="easyui-validatebox" disabled="disabled" name="validBeginDate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width:150px" value="<fmt:formatDate value="${dtl.validBeginDate }" pattern="yyyy-MM-dd" />" >
			 </div>
			</span>
		</div>

		<div id="raiseInfoDiv" style="display: <c:if test="${dtl.isCollect eq 1}">none</c:if><c:if test="${dtl.isCollect eq 2}">inline</c:if>;">
			<div class="prdAdd">
			<span class="leftsp">募集有效期到：</span>
			<span class="rightsp"><input class="easyui-validatebox"  name="collectEndTime" disabled="disabled" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width:150px"  value="<fmt:formatDate value="${dtl.collectEndTime }" pattern="yyyy-MM-dd" />">
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">投资客户募集期间收益：</span>
			<span class="rightsp"><input name="hasCollectRate" disabled="disabled" type="radio"  value="1" <c:if test="${! empty dtl.collectRate }">checked="checked"</c:if>>募集期间有收益<input type="text" style="width:80px;"  disabled="disabled" name="collectRate" id="collectRate" value="<fmt:formatNumber value="${dtl.collectRate }" pattern="0.00"/>" />%
			 &nbsp;&nbsp;<input name="hasCollectRate" type="radio" disabled="disabled" value="0" <c:if test="${ empty dtl.collectRate }">checked="checked"</c:if>>募集期间无收益
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">理财师募集期间佣金：</span>
			<span class="rightsp"><input name="hasCollectRatio" type="radio" disabled="disabled" value="1" <c:if test="${! empty dtl.collectRatio }">checked="checked"</c:if>>募集期间有佣金<input type="text" style="width:80px; " disabled="disabled" name="collectRatio" id="collectRatio" value="<fmt:formatNumber value="${dtl.collectRatio }" pattern="0.00"/>"  />%
			&nbsp;&nbsp;<input name="hasCollectRatio" type="radio"  value="0" disabled="disabled" <c:if test="${empty dtl.collectRatio }">checked="checked"</c:if>>募集期间无佣金
			</span>
		</div>

		</div>
		 <fieldset>
    <legend>投资端信息</legend>
    	<div class="prdAdd">
			<span class="leftsp">年化收益：</span>
			<span class="rightsp"><input  disabled="disabled" class="easyui-validatebox"  value="<fmt:formatNumber value="${dtl.fixRate }" pattern="0.00"/>" type="text" name="fixRate" data-options="required:true,validType:'number'">%*支持两位小数</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">显示标签：</span>
			<span class="rightsp"><%-- <input class="easyui-validatebox"  type="text" name="invCornerIco" style="width:120px; " value="${dtl.invCornerIco }"> --%>
			<input  class="easyui-validatebox"  type="text" name="invLabel1" style="width:120px; " value="${dtl.invLabel1 }">
			<input  class="easyui-validatebox"  type="text" name="invLabel2" style="width:120px; " value="${dtl.invLabel2 }"></span>
		</div>

		<%-- <div class="prdAdd">
			<span class="leftsp">详情页推荐语：</span>
			<span class="rightsp">
				<input  class="easyui-validatebox" value="${dtl.invDtlPageDes }" type="text" name="invDtlPageDes" id="invDtlPageDes" >最多15个字符，留空则不显示
			</span>
		</div> --%>
		<div class="prdAdd">
			<span class="leftsp">产品购买协议：</span>
			<span class="rightsp">
			<input  class="easyui-validatebox"  type="text" name="productProtocalName" data-options="required:true"  id="productProtocalName" placeholder="协议名称" value="${dtl.productProtocalName }">
				<input  class="easyui-validatebox"  type="text" name="productProtocalId" data-options="required:true"  id="productProtocalId" value="${dtl.productProtocalId }">
				<input type="button" value="添加" id="productProtocalAddBtn"> 
				<div id="productProtocalDiv" style="display: inline;"></div>
			</span>
			
		</div>
		<div class="prdAdd">
			<span class="leftsp">收益转让协议：</span>
			<span class="rightsp">
			<input  class="easyui-validatebox"  type="text" name="ransferProtocalName"  id="ransferProtocalName" placeholder="协议名称" value="${dtl.ransferProtocalName }">
				<input  class="easyui-validatebox"  type="text" name="ransferProtocalUrl"  id="ransferProtocalUrl" value="${dtl.ransferProtocalUrl }">
				<input type="button" value="添加" id="ransferProtocalUrlAddBtn"> 
				<div id="ransferProtocalUrlDiv" style="display: inline;"></div>
			</span>
			
		</div>
	</fieldset>
	<fieldset>
    <legend>理财师端信息</legend>
    	<div class="prdAdd">
			<span class="leftsp">年化佣金：</span>
			<span class="rightsp"><input  disabled="disabled" class="easyui-numberbox"  type="text" name="feeRatio" value="<fmt:formatNumber value="${dtl.feeRatio }" pattern="0.00"/>" data-options="min:0,precision:2,required:true,validType:'number'">%*支持两位小数</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">销售奖励：</span>
			<span class="rightsp"><input disabled="disabled"  class="easyui-numberbox"  type="text" name="saleReward" value="<fmt:formatNumber value="${dtl.saleReward }" pattern="0.00"/>" data-options="min:0,precision:2,validType:'number'">% *支持两位小数</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">显示标签：</span>
			<span class="rightsp"><%-- <input  class="easyui-validatebox"  type="text" name="lcsCornerIco" style="width:120px; " value="${dtl.lcsCornerIco }"> --%>
			<input  class="easyui-validatebox"  type="text" name="lcsLabel1" style="width:120px; " value="${dtl.lcsLabel1 }">
			<input  class="easyui-validatebox"  type="text" name="lcsLabel2" style="width:120px; " value="${dtl.lcsLabel2 }"></span>
		</div>

		<%-- <div class="prdAdd">
			<span class="leftsp">详情页推荐语：</span>
			<span class="rightsp">
				<input  class="easyui-validatebox"  type="text" name="lcsDtlPageDes" id="lcsDtlPageDes"  value="${dtl.lcsDtlPageDes }">最多15个字符，留空则不显示
			</span>
		</div> --%>
	</fieldset>
	
	<fieldset>
    <legend>开售设置</legend>
    <div class="prdAdd">
			<span class="leftsp">类型：</span>
			<span class="rightsp">
			<c:if test="${ dtl.beginSaleType eq 1 }">
			<input name="beginSaleType" type="radio"  value="1" checked="checked" disabled="disabled" >即时
			<input name="beginSaleType" type="radio"  value="2" id="isListRecoment" disabled="disabled"> 定时
			<input name="beginSaleType" type="radio"  value="3" id="isListRecoment" disabled="disabled"> 预售
			</c:if> 
			<c:if test="${ dtl.beginSaleType eq 2 }">
			<input name="beginSaleType" type="radio"  value="1" disabled="disabled" >即时
			<input name="beginSaleType" type="radio"  value="2" id="isListRecoment" checked="checked" disabled="disabled"> 定时
			<input  name="beginSaleTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  disabled="disabled" style="width:100px"  value="<fmt:formatDate value="${dtl.beginSaleTime }" pattern="yyyy-MM-dd HH:mm:ss" />" >
			<input name="beginSaleType" type="radio"  value="3" id="isListRecoment"  disabled="disabled"> 预售
			</c:if>
			<c:if test="${ dtl.beginSaleType eq 3 }">
			<input name="beginSaleType" type="radio"  value="1"  disabled="disabled">即时
			<input name="beginSaleType" type="radio"  value="2" id="isListRecoment"  disabled="disabled"> 定时
			<input name="beginSaleType" type="radio"  value="3" id="isListRecoment" checked="checked" disabled="disabled"> 预售
			<input  name="beginSaleTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" disabled="disabled" style="width:100px"  value="<fmt:formatDate value="${dtl.beginSaleTime }" pattern="yyyy-MM-dd HH:mm:ss" />" >
			</c:if>
				<%-- <input name="beginSaleType" type="radio"  value="1" <c:if test="${empty dtl.beginSaleTime and (dtl.saleStatus ne 1 and dtl.saleStatus ne 5) }">checked="checked"</c:if> >即时
				<input name="beginSaleType" type="radio"  value="2" id="isListRecoment" <c:if test="${!empty dtl.beginSaleTime and (dtl.saleStatus eq 5 ) }">checked="checked"</c:if>> 定时
				<div id="fixSaleTimeDiv" style="display: <c:choose><c:when test="${dtl.beginSaleType eq 2}">inline</c:when><c:otherwise>none</c:otherwise></c:choose>;">
				<input   name="beginSaleTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width:100px" <c:if test="${dtl.beginSaleType != 2}">disabled="disabled"</c:if> value="<fmt:formatDate value="${dtl.beginSaleTime }" pattern="yyyy-MM-dd HH:mm:ss" />">
				</div>
				
				<input name="beginSaleType" type="radio"  value="3" id="isListRecoment" <c:if test="${!empty dtl.beginSaleTime and (dtl.saleStatus eq 1 ) }">checked="checked"</c:if>> 预售
				<div id="preSaleTimeDiv" style="display: <c:choose><c:when test="${dtl.beginSaleType eq 3}">inline</c:when><c:otherwise>none</c:otherwise></c:choose>;">
				<input  name="beginSaleTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width:100px" <c:if test="${dtl.beginSaleType != 3}">disabled="disabled"</c:if> value="<fmt:formatDate value="${dtl.beginSaleTime }" pattern="yyyy-MM-dd HH:mm:ss" />" >
				</div> --%>
				
			</span>
	</div>
    
    </fieldset>

		
<!-- 		<div class="prdAdd">
			<span class="leftsp">还本付息方式：</span>
			<span class="rightsp">
				<select name="repaymentWay">
					<option value="1" selected="selected">一次性到期</option>
					<option value="2">一次性按日</option>
					<option value="3">一次性按月</option>
					<option value="4">一次性按季</option>
					<option value="5">等额本息(按月)</option>
					<option value="6">等额本息(按季)</option>
				</select>
			</span>
		</div>
		
		<div class="prdAdd">
			<span class="leftsp">起息方式：</span>
			<span class="rightsp">
				<select name="interestWay">
					<option value="1" >购买当日</option>
					<option value="2" selected="selected">购买次日</option>
					<option value="3">募集完成当日</option>
					<option value="4">募集完成次日</option>
					<option value="5">指定日期</option>
				</select>
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">产品起息日(yyyy-MM-dd)：</span>
			<span class="rightsp"><input  class="easyui-datetimebox"  type="text" name="validBeginDate"></span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">产品到期日(yyyy-MM-dd)：</span>
			<span class="rightsp"><input  class="easyui-datetimebox"  type="text" name="validEndDate"></span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">产品单笔购买最小额度：</span>
			<span class="rightsp"><input  class="easyui-validatebox"  type="text" name="buyMinMoney"  data-options="required:true"></span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">产品单笔购买最大额度：</span>
			<span class="rightsp"><input  class="easyui-validatebox"  type="text" name="buyMaxMoney"></span>
		</div>
		
		<div class="prdAdd">
			<span class="leftsp">是否可赎回：</span>
			<span class="rightsp">
				<select name="isRedemption">
					<option value="1" selected="selected">不赎回</option>
					<option value="2">赎回</option>
				</select>
			</span>
		</div>
		
		<div class="prdAdd">
			<span class="leftsp">收益权转让协议名称：</span>
			<span class="rightsp"><input  class="easyui-validatebox"  type="text" name="ransferProtocalName"></span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">收益权转让协议地址：</span>
			<span class="rightsp"><input  class="easyui-validatebox"  type="text" name="ransferProtocalUrl"></span>
		</div> -->
	
		

		<div style="clear: both;"></div>																		
		<!-- <div style="padding-top: 60px;text-align:center;">
			<span>-------------------------------------产品佣金配置------------------------------------------------</span>
		</div> -->
	</form>
	
	<div class="prdAdd" style="margin-top: 50px;margin-bottom: 100px;">
		<span style="margin-left: 300px"> <input type="button" id="productEdit" value="保存" style="width: 80px;height: 30px"/></span>
		<span style="margin-left: 50px"> <input type="button"  id="productAddcacl" value="取消" style="width: 80px;height: 30px"/></span>
	</div>
	
</div>

<script type="text/javascript" src="app/product/productAdd.js"></script>
