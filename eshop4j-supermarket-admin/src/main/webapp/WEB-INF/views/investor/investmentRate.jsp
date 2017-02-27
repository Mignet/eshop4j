<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="assets/plugins/echarts/echarts.min.js"  ></script>

<div class="container">
<div class="row">
		<div class="row">
			<h4>总体数据</h4>
		</div>
		<div >
			<br>
		</div>
		<div class="row">
			<div><span>T呗历史累计人数及投资率：</span></div>
		</div>
		<div >
			<br>
		</div>
		<div class="row">
		<table style="border:1px solid #dcdcdc;">
			<tr style="border:1px solid #dcdcdc;">
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center">注册人数</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center">投资人数</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center">复投人数</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center">在投人数</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center">投资率</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center">复投率</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center">在投率</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center">登录人数</td>
			</tr>
			<tr style="border:1px solid #dcdcdc;">
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center" id="registerCount">0</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center" id="investmentCount">0</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center" id="reInvestmentCount">0</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center" id="investingCount">0</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center" id="investmentRate">0</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center" id="reInvestmentRate">0</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center" id="investingRate">0</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center" id="loginCount">0</td>
			</tr>
			
		</table>
		</div>
<div >
			<br>
		</div>
		
<div class="row">
		备注：<br>
		（1）、投资人数：通过T呗投资过的人数（投资一次及以上）；<br>
		（2）、复投人数：通过T呗投资过两次及以上的人数；<br>
		（3）、投资率=投资人数 / 登录人数；<br>
		（4）、复投率=复投人数 / 投资人数；<br>
		（5）、在投率=在投人数 / 投资人数；<br>
		（6）、数据中已删除2016年10月6、7、8号的数据（异常数据）；<br>
		（7）、以上统计时间为：2016年8月25号猎财大师上线之后。
</div>



<div><br><br><br><br></div>

<div class="row">
			<h4>细分数据</h4>
		</div>
		<div >
			<br>
		</div>
		<div class="row">
			<div>
			<span>平台: </span>&nbsp;&nbsp;
			<%-- <input checked="checked" name="platform" type="checkbox"/> 全部 &nbsp;
			 <c:forEach  items="${orgList}" var="item">
			<input  name="platform" type="checkbox" value=" ${item.orgNumber}"/> ${item.orgName}&nbsp;
			 </c:forEach> --%>
			 <select  name="platform" id="platform">
			 	<option value="">全部</option>
			  <c:forEach  items="${orgList}" var="item">
			<option value="${item.orgNumber}">${item.orgName}</option>
			 </c:forEach> 
			 
			 </select>
			</div>
		</div>
		<div >
			<br>
		</div>
		<div class="row">
			<div><span>时间：</span>&nbsp;
			<!-- <input checked="checked" name="timeRadio" type="radio"/> 最近7天 &nbsp;
			<input name="timeRadio" type="radio"/> 最近30天 &nbsp; -->
			<input id="startTimeForSearch" name="startTimeForSearch" class="Wdate" style="width:110px" value="${startDate}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTimeForSearch\')}'})"> 至
        	<input id="endTimeForSearch" name="endTimeForSearch" class="Wdate" style="width:110px" value="${endDate}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTimeForSearch\')}'})">
			<a href="javascript:void(0)" onclick="loadView()"  id="investorDateViewSearchBtn" class="easyui-linkbutton" >搜索</a>
			</div>
		</div>
		<div >
			<br>
		</div>
		<div class="row">
			<div><span>时间内，平台投资人数及投资率：</span></div>
		</div>
		<div >
			<br>
		</div>
		<div class="row">
		<table style="border:1px solid #dcdcdc;">
			<tr style="border:1px solid #dcdcdc;">
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center">平台注册人数</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center">投资人数</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center">复投人数</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center">在投人数</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center">平台注册投资率</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center">复投率</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center">在投率</td>
			</tr>
			<tr style="border:1px solid #dcdcdc;">
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center" id="registerCount2">0</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center" id="investmentCount2">0</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center" id="reInvestmentCount2">0</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center" id="investingCount2">0</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center" id="investmentRate2">0</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center" id="reInvestmentRate2">0</td>
				<td style="border:1px solid #dcdcdc;" width="100px" height="30px" align="center" id="investingRate2">0</td>
			</tr>
			
		</table>
		</div>
		
<div class="row">
		备注：<br>
（1）、投资人数：通过T呗投资过的人数（投资一次及以上）；<br>
（2）、复投人数：通过T呗投资过两次及以上的人数；<br>
（3）、平台注册投资率=投资人数 / 平台注册人数；<br>
（4）、复投率=复投人数 / 投资人数；<br>
（5）、在投率=在投人数 / 投资人数；<br>
（6）、开始时间仅能选择2016年8月25号（猎财大师上线）及以后的日期；<br>
（7）、当时间段包含了2016年10月6、7、8号时，这三天的数据将被清除；<br>
（8）、当平台选择“全部”时，平台注册人数、平台注册投资率表示：T呗的注册人数、T呗的注册投资率。当平台选择其他平台时，表示：用户在该平台的注册人数、该平台的平台注册投资率。
</div>




	</div>
</div>
 
  
  <script type="text/javascript" src="app/common/util.js"></script>
 <script type="text/javascript" > 
 
 $(document).ready(function() {
	  $.ajax({
		type:"POST",
		url:"rest/cfpAchievement/getInvestmentRate",
		async:true,
		success:function(result){
			$('#registerCount').html(result.registerCount);
			$('#loginCount').html(result.loginCount);
			$('#investmentCount').html(result.investmentCount);
			$('#reInvestmentCount').html(result.reInvestmentCount);
			$('#investingCount').html(result.investingCount);
			$('#investmentRate').html(result.investmentRate);
			$('#reInvestmentRate').html(result.reInvestmentRate);
			$('#investingRate').html(result.investingRate);
			$('#registerCount2').html(result.registerCount);
			$('#investmentCount2').html(result.investmentCount);
			$('#reInvestmentCount2').html(result.reInvestmentCount);
			$('#investingCount2').html(result.investingCount);
			$('#investmentRate2').html(result.investmentRate);
			$('#reInvestmentRate2').html(result.reInvestmentRate);
			$('#investingRate2').html(result.investingRate);
		}
		});
	 
	});
 
 function loadView(){
	 $.ajax({
			type:"POST",
			url:"rest/cfpAchievement/getInvestmentRate",
			async:true,
			data : {  
	            "platfrom" : $('#platform').val(),
	            "startTimeForSearch" : $('#startTimeForSearch').val(),
	            "endTimeForSearch" : $('#endTimeForSearch').val()
	        },
			success:function(result){
				$('#registerCount2').html(result.registerCount);
				$('#investmentCount2').html(result.investmentCount);
				$('#reInvestmentCount2').html(result.reInvestmentCount);
				$('#investingCount2').html(result.investingCount);
				$('#investmentRate2').html(result.investmentRate);
				$('#reInvestmentRate2').html(result.reInvestmentRate);
				$('#investingRate2').html(result.investingRate);
			}
			});
 }
 
 </script>