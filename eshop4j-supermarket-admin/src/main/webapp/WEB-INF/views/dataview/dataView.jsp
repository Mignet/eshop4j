<%@ page language="java" pageEncoding="utf-8"%>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="assets/plugins/echarts/echarts.min.js"  ></script>
<div>
<table>
 <tr>
 	<th align="center" colspan="2" style="height: 20px;">
 			<form id="investorDataViewSearh" style="top: 15px;"> 
				日期: <input id="custDataViewStartDate" name="startDate" class="easyui-datebox" style="width:110px" value="${startDate}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'custDataViewEndDate\')}'})"> 至
	        	<input id="custDataViewEndDate" name="endDate" class="easyui-datebox" style="width:110px" value="${endDate}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'custDataViewStartDate\')}'})">
				<a href="javascript:void(0)"  id="investorDateViewSearchBtn" class="easyui-linkbutton" >搜索</a>
			</form>
 	</th>
 </tr>
  <tr>
    <th align="center" >注册用户</th>
    <th align="center" >投资用户</th>
  </tr>
  <tr>
    <td><div id="investorNum" style="width: 800px;height:400px;"></div></td>
    <td><div id="investMoneyNum" style="width: 800px;height:400px;"></div></td>
  </tr>
   <tr>
    <th align="center"><h4><b>注册用户</b></h4></th>
    <th align="center"><h4><b>投资用户</b></h4></th>
  </tr>
    <tr>
    <th align="center">今日：${register.todayCount}人</th>
    <th align="center">今日新增：${investor.todayNewCount}</th>
  </tr>
   <tr>
    <th align="center">昨日：${register.yesterdayCount}人</th>
    <th align="center">今日总数：${investor.todayTotalCount}</th>
  </tr>
   <tr>
    <th align="center">本月：${register.thisMonthCount }人</th>
    <th align="center">昨日新增：${investor.yesterdayNewCount}</th>
  </tr>
   <tr>
    <th align="center">区间：${register.intervalCount}人</th>
    <th align="center">昨日总数：${investor.yesterdayTotalCount}</th>
  </tr>
   <tr>
    <th align="center">历史：${register.totalCount}人</th>
    <th align="center">本月新增：${investor.thisMonthNewCount}</th>
  </tr>
  <tr>
    <th align="center"></th>
    <th align="center">本月总数：${investor.thisMonthTotalCount}</th>
  </tr>
  <tr>
    <th align="center"></th>
    <th align="center">区间新增：${investor.intervalNewCount}</th>
  </tr>
  <tr>
    <th align="center"></th>
    <th align="center">区间总数：${investor.intervalTotalCount}</th>
  </tr>
  <tr>
    <th align="center"></th>
    <th align="center">历史累计：${investor.totalCount}</th>
  </tr>
</table>
</div>
 
  
  <script type="text/javascript" src="app/common/util.js"></script>
 <script type="text/javascript" > 
 $(document).ready(function() {
	 loadView();
	 //异步加载总投资人和年化投资额
	  $.ajax({
		type:"POST",
		url:"rest/dataview/totaldata",
		async:true,
		success:function(result){
			$('#totalperson').html(result.totalperson);
			 $('#totalmoney').html(result.totalmoney);
		}
		}); 
	 
	});
 function loadView(){
	 var params=jsonFromt($('#investorDataViewSearh').serializeArray());
	 $.getJSON('rest/dataview/statdata',params, function (json) {
		 var personCategories = [];  
		 var personValues = [];
		 
		 var moneyCategories = [];  
		 var moneyValues = [];
		 
		 var person =json.data.person;
		 $.each(person,function(i,data){
			 personCategories.push(data.investDate);
			 personValues.push(data.personNum);
		 }); 
		 var money =json.data.money;
		 $.each(money,function(i,data){
			 moneyCategories.push(data.investDate);
			 moneyValues.push(data.investTotal);
			 
		 }); 
		 var investorNum = echarts.init(document.getElementById('investorNum'));
		//指定图表的配置项和数据
		 var option = {
		     tooltip: {
		    	 trigger: 'axis'
		     },
		     toolbox: {  
	             show : true,  
	             feature : {  
	                 mark : {show: true},  
	                 magicType : {show: true, type: ['line', 'bar']}
	             }  
	         },
		     legend: {
		         data:['投资客户']
		     },
		     calculable : true, 
		     xAxis: {
		    	 type : 'category',
		         data: personCategories,
		         boundaryGap: false
		     },
		     yAxis: {
		    	 type: 'value',
		    	 min:0,
		         boundaryGap: false
		     },
		     series: [{
		         name: '人数',
		         type:'line',
		         data: personValues
		     }]
		 };
		 investorNum.setOption(option);
		 
		 var investMoneyNum = echarts.init(document.getElementById('investMoneyNum'));
			//指定图表的配置项和数据
			 option = {
			     tooltip: {
			    	 
			    	 trigger: 'axis'
			     },
			     toolbox: {  
		             show : true,  
		             feature : {  
		                 mark : {show: true},  
		                 magicType : {show: true, type: ['line', 'bar']}
		             }  
		         },
			     legend: {
			         data:['客户投资额']
			     },
			     calculable : true, 
			     xAxis: {
			    	 type : 'category',
			         data: moneyCategories,
			         boundaryGap: false
			     },
			     yAxis: {
			    	 type: 'value',
			    	 min:0,
			         boundaryGap: false  
			     },
			     series: [{
			         name: '投资额',
			         type:'line',
			         data: moneyValues
			     }]
			 };
			 investMoneyNum.setOption(option);
	 });
 }
	$('#investorDateViewSearchBtn').click(function() {
		loadView();
	});
 </script>