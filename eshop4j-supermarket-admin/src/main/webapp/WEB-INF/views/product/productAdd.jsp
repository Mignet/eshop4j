<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	 <fieldset style="margin:20px 20px;">
    <legend>基本信息</legend>
		<div class="prdAdd" style="margin-top: 40px">
			<span class="leftsp">产品名称：</span>
			<span class="rightsp"><input  class="easyui-validatebox" type="text" id="productName" name="productName" data-options="required:true" id="productName" ><div id="proNameValidDiv" style="display: inline;"></div></span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">产品类型：</span>
			 <span class="rightsp">
			<select name="productCate" id="productCate" style="width:120px; ">
			<%-- <c:forEach items="${proCateList}"  var="item" >
			<option value="${item.cateId }">${item.cateName }</option>
			</c:forEach> --%>
			<option value="1001">固收产品</option>
			</select> 
		<!-- 	<input id="productCate" class="easyui-combobox" name="productCate"
    data-options="valueField:'cateId',textField:'cateName',url:'rest/productos/allpubliccate'"> -->
			</span>
		</div>
<!-- 		<div class="prdAdd">
			<span class="leftsp">起息日：</span>
			<span class="rightsp"><input class="easyui-validatebox"  name="validBeginDate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width:150px" data-options="required:true">
			</span>
		</div> -->
		<div class="prdAdd">
			<span class="leftsp">产品总额：</span>
			<span class="rightsp"><input class="easyui-numberbox" type="text" name="buyTotalMoney" data-options="required:true,precision:2" >
			<form:errors path="buyTotalMoney" cssClass="error" /> 
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">起投金额：</span>
			<span class="rightsp"><input class="easyui-numberbox" type="text" name="buyMinMoney"  missingMessage="" data-options="required:true,precision:2">
			<form:errors path="buyMinMoney" cssClass="error" /> 
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">递增金额：</span>
			<span class="rightsp"><input class="easyui-numberbox" type="text" name="buyIncreaseMoney"  data-options="required:true,precision:2">
			<form:errors path="buyIncreaseMoney" cssClass="error" /> 
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">单用户购买上限：</span>
			<span class="rightsp"><input class="easyui-numberbox" type="text" name="custBuyMaxMoney" data-options="required:true,precision:2">
			<form:errors path="custBuyMaxMoney" cssClass="error" /> 
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">投资期限：</span>
			<span class="rightsp"><input class="easyui-numberbox" type="text" name="deadLineValue" data-options="required:true">天
			<form:errors path="custBuyMaxMoney" cssClass="error" /> 
			</span>
		</div>

		<div class="prdAdd">
			<span class="leftsp">收益方式：</span>
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
			<span class="leftsp">产品说明页：</span>
			<span class="rightsp"><input class="easyui-validatebox" type="text" name="productIllustrationUrl"  id="productIllustrationUrl" data-options="required:true">
			<input type="button" value="添加" id="dtlPageAddBtn">
			<div id="dtlPageDiv" style="display: inline;">
			</div>
			</span>
			
		</div>
		<div class="prdAdd">
			<span class="leftsp">安全保障页：</span>
			<span class="rightsp"><input class="easyui-validatebox" type="text" name="securityGuaranteeUrl" id="securityGuaranteeUrl" data-options="required:true" >
			<input type="button" value="添加"  id="securePageAddBtn">
			<div id="securePageDiv" style="display: inline;">
			</div>
			</span>
			
		</div>
		<div class="prdAdd">
			<span class="leftsp">投资端首页推荐：</span>
			<span class="rightsp">
				<input name="isRecommended" type="radio"  value="2" checked="checked" disabled="disabled" >否 <input name="isRecommended" type="radio"  disabled="disabled" value="1" id="indexRecoment"> 是
				&nbsp;*仅在售和预售状态中的产品能设置首页推荐
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">分类排序：</span>
			<span class="rightsp">
				<%-- <select name="cateSort">
					<c:forEach var="item" begin="1" end="10" step="1">
							<option value="${item }">${item }</option>
					</c:forEach>
				</select> --%>
				<input  name="cateSort" type="text" class="easyui-numberbox" style="width:120px; " /> 
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">热门推荐：</span>
			<span class="rightsp">
				<input name="isListRecommended" type="radio"  value="0" checked="checked">否 <input name="isListRecommended" type="radio"  value="1" id="isListRecoment"> 是
					<div id="ListRecomentDiv" style="display: none;">
			<%-- <select name="listRecommendedSort">
			<c:forEach var="item" begin="1" end="5" step="1">
							<option value="${item }">${item }</option>
		   </c:forEach>
			</select> --%>
			<input  name="listRecommendedSort" type="text" class="easyui-numberbox" style="width:120px; " />
			推荐语：<input type="text" style="width:120px; " name="listRecomendedStr"  id="listRecomendedStr"/>最多15个字符，留空则不显示
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
				<input name="isCollect" type="radio"  value="1" checked="checked" >不进行募集 &nbsp;&nbsp;<input name="isCollect" type="radio"   value="2" > 进行限期募集
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">起息方式：</span>
			<span class="rightsp">
			<div id="collectInterestWayDiv" style="display: none;" >
			<input name="interestWayType" type="radio"  value="1" checked="checked">
			</div>
			<span id="interestWaySpan">投资后</span>			
			
			<select name="interestWay" id="interestWay">
			<option value='1'>T+0</option>
			<option value='2'>T+1</option>
			</select>起息&nbsp;&nbsp;
			 <div id="validBeginDateDiv" style="display: none;">
			 <input name="interestWayType" type="radio"  value="2" id="indexRecoment"> 固定日期起息
			 <input class="easyui-validatebox"  name="validBeginDate" id="validBeginDate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width:150px" >
			 </div>
			</span>
		</div>

		<div id="raiseInfoDiv" style="display: none;">
			<div class="prdAdd">
			<span class="leftsp">募集有效期到：</span>
			<span class="rightsp"><input class="easyui-validatebox"  name="collectEndTime"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width:150px" >
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">投资客户募集期间收益：</span>
			<span class="rightsp"><input name="hasCollectRate" type="radio"  value="1" checked="checked">募集期间有收益<input type="text" style="width:80px; "  name="collectRate" id="collectRate"/>%
			&nbsp;&nbsp;<input name="hasCollectRate" type="radio"  value="0" checked="checked">募集期间无收益
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">理财师募集期间佣金：</span>
			<span class="rightsp"><input name="hasCollectRatio" type="radio"  value="1" checked="checked">募集期间有佣金<input type="text" style="width:80px; " name="collectRatio" id="collectRatio" />%
			&nbsp;&nbsp;<input name="hasCollectRatio" type="radio"  value="0" checked="checked">募集期间无佣金
			</span>
		</div>
<!-- 		<div class="prdAdd">
			<span class="leftsp">是否有banner：</span>
			<span class="rightsp"><input name="interestWay" type="radio"  value="1" checked="checked">无banner<input type="text" style="width:80px; " name="listRecomendedStr" />
			<input name="interestWay" type="radio"  value="1" checked="checked">上传banner
			</span>
		</div> -->
		</div>



		<fieldset>
    <legend>投资端信息</legend>
    	<div class="prdAdd">
			<span class="leftsp">年化收益：</span>
			<span class="rightsp"><input  class="easyui-numberbox"  type="text" name="fixRate" data-options="required:true,precision:2">%*支持两位小数</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">显示标签：</span>
			<span class="rightsp"><!-- <input class="easyui-validatebox"  type="text" name="invCornerIco" style="width:120px; " placeholder="标签1（角标）"> -->
			<input  class="easyui-validatebox"  type="text" name="invLabel1" style="width:120px; " placeholder="标签1">
			<input  class="easyui-validatebox"  type="text" name="invLabel2" style="width:120px; " placeholder="标签2"></span>
		</div>

		<!-- <div class="prdAdd">
			<span class="leftsp">详情页推荐语：</span>
			<span class="rightsp">
				<input  class="easyui-validatebox"  type="text" name="invDtlPageDes" id="invDtlPageDes" >最多15个字符，留空则不显示
			</span>
		</div> -->
		<div class="prdAdd">
			<span class="leftsp">产品购买协议：</span>
			<span class="rightsp">
			<input  class="easyui-validatebox"  type="text" name="productProtocalName" data-options="required:true"  id="productProtocalName" placeholder="协议名称">
				<input  class="easyui-validatebox"  type="text" name="productProtocalId" data-options="required:true"  id="productProtocalId" placeholder="协议地址">
				<input type="button" value="添加" id="productProtocalAddBtn"> 
				<div id="productProtocalDiv" style="display: inline;"></div>
			</span>
			
		</div>
		<div class="prdAdd">
			<span class="leftsp">收益转让协议：</span>
			<span class="rightsp">
				<input  class="easyui-validatebox"  type="text" name="ransferProtocalName"  id="ransferProtocalName" placeholder="协议名称">
				<input  class="easyui-validatebox"  type="text" name="ransferProtocalUrl"  id="ransferProtocalUrl" placeholder="协议地址">
				<input type="button" value="添加" id="ransferProtocalUrlAddBtn">
				<div id="ransferProtocalUrlDiv" style="display: inline;"></div> 
			</span>
			
		</div>
	</fieldset>
	<fieldset>
    <legend>理财师端信息</legend>
    	<div class="prdAdd">
			<span class="leftsp">年化佣金：</span>
			<span class="rightsp"><input  class="easyui-numberbox"  type="text" name="feeRatio" data-options="min:0,precision:2,required:true,validType:'number'">%*支持两位小数</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">销售奖励：</span>
			<span class="rightsp"><input  class="easyui-numberbox"  type="text" name="saleReward" data-options="min:0,precision:2,validType:'number'">% *支持两位小数</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">显示标签：</span>
			<span class="rightsp"><!-- <input  class="easyui-validatebox"  type="text" name="lcsCornerIco" style="width:120px; " placeholder="标签1（角标）"> -->
			<input  class="easyui-validatebox"  type="text" name="lcsLabel1" style="width:120px; " placeholder="标签1">
			<input  class="easyui-validatebox"  type="text" name="lcsLabel2" style="width:120px; " placeholder="标签2"></span>
		</div>

		<!-- <div class="prdAdd">
			<span class="leftsp">详情页推荐语：</span>
			<span class="rightsp">
				<input  class="easyui-validatebox"  type="text" name="lcsDtlPageDes" id="lcsDtlPageDes">最多15个字符，留空则不显示
			</span>
		</div> -->
	</fieldset>
	
	<fieldset>
    <legend>开售设置</legend>
    <div class="prdAdd">
			<span class="leftsp">类型：</span>
			<span class="rightsp">
				<input name="beginSaleType" type="radio"  value="1" checked="checked">即时
				<input name="beginSaleType" type="radio"  value="2" id="isListRecoment"> 定时
				<div id="fixSaleTimeDiv" style="display: none;">
				<input   name="beginSaleTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width:150px" id="fixSaleTimeInput" disabled="disabled">
				</div>
				
				<input name="beginSaleType" type="radio"  value="3" id="isListRecoment"> 预售
				<div id="preSaleTimeDiv" style="display: none;">
				<input  name="beginSaleTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width:150px" id="preSaleTimeInput" disabled="disabled">
				</div>
				
			</span>
	</div>
    
    </fieldset>

		
		<div style="clear: both;"></div>																		
		<!-- <div style="padding-top: 60px;text-align:center;">
			<span>-------------------------------------产品佣金配置------------------------------------------------</span>
		</div> -->
	</form>
	
	<div class="prdAdd" style="margin-top: 50px;margin-bottom: 100px;">
		<span style="margin-left: 300px"> <input type="button" id="productAddSave" value="保存" style="width: 80px;height: 30px"/></span>
		<span style="margin-left: 50px"> <input type="button"  id="productAddcacl" value="取消" style="width: 80px;height: 30px"/></span>
	</div>
	
</div>

<script type="text/javascript" src="app/product/productAdd.js"></script>
