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
    <th align="center" >投资客户人数</th>
    <th align="center" >成交额</th>
  </tr>
  <tr>
    <td><div id="investorNum" style="width: 800px;height:400px;"></div></td>
    <td><div id="investMoneyNum" style="width: 800px;height:400px;"></div></td>
  </tr>
   <tr>
    <th align="center">投资客户总计：<div id="totalperson" style="display: inline;"></div>人</th>
    <th align="center">年化成交总额：<div id="totalmoney" style="display: inline;"></div></th>
  </tr>
   <tr>
    <th align="center">今日投资客户：${data.personAmout.currdayperson}人</th>
    <th align="center">今日投资总额：${data.investMoney.currdaymoney}</th>
  </tr>
   <tr>
    <th align="center">最近一周投资客户：${data.personAmout.currweekperson }人</th>
    <th align="center">最近一周投资总额：${data.investMoney.currweekmoney}</th>
  </tr>
   <tr>
    <th align="center">本月投资客户：${data.personAmout.currmonthperson}人</th>
    <th align="center">本月投资总额：${data.investMoney.currmothmoney}</th>
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
		url:"rest/investor/totaldata",
		async:true,
		success:function(result){
			$('#totalperson').html(result.totalperson);
			 $('#totalmoney').html(result.totalmoney);
		}
		}); 
	 
	});
 function loadView(){
	 var params=jsonFromt($('#investorDataViewSearh').serializeArray());
	 $.getJSON('rest/investor/statdata',params, function (json) {
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