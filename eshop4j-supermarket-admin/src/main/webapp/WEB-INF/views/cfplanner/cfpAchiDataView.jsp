<%@ page language="java" pageEncoding="utf-8"%>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="assets/plugins/echarts/echarts.min.js"  ></script>

<div>
<table>
 <%-- <tr>
 	<th align="center" colspan="2" style="height: 20px;">
 			<form id="investorDataViewSearh" style="top: 15px;"> 
				日期: <input id="custDataViewStartDate" name="startDate" class="easyui-datebox" style="width:110px" value="${startDate}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'custDataViewEndDate\')}'})"> 至
	        	<input id="custDataViewEndDate" name="endDate" class="easyui-datebox" style="width:110px" value="${endDate}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'custDataViewStartDate\')}'})">
				<a href="javascript:void(0)"  id="investorDateViewSearchBtn" class="easyui-linkbutton" >搜索</a>
			</form>
 	</th>
 </tr> --%>
  <tr>
    <td><div id="cfpAreaData" style="width: 600px;height:300px;"></div></td>
  </tr>
  <tr>
    <td align="center" >理财师人数</td>
  </tr>
  <tr>
   <td><div  style="width: 100px;height:50px;"></td>
 </tr>
  <tr>
    <td><div id="cfpCustomerCountData" style="width: 600px;height:300px;"></div></td>
    <td><div  style="width: 100px;height:300px;"></td>
    <td>
    
    
    <div id="main-news" class="container-fluid"style="width: 400px;height:300px;">
    <div class="table-responsive">
        <table id="J-newslist" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="rest/cfpAchievement/getCfpOfCustomerCount" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" date-order="true" data-edit="id" data-format="id:cumsId">序号</th>
                    <th data-name="cfpName" >理财师姓名</th>
                    <th data-name="cfpMobile" data-format="cfpMobile:cumsMobile">理财师手机</th>
                    <th data-name="yearAmount" >年化业绩</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">

        var cumsId = function (data,type,full,meta) {
        	return meta.row+1;
        }

       var $db= $("#J-newslist").eshop4jTable();
       
    </script>
    <script type="text/eshop4j-template" id="template-search">
        <form id="exportCfpAchievement">
        客户数<select id="name" name="name">
			<option value="">全部</option>
            <option value="2">2客户</option>
            <option value="3">3客户</option>
			<option value="4">4客户</option>
			<option value="5">5客户</option>
			<option value="10">6-10客户</option>
			<option value="20">11-20客户</option>
			<option value="50">21-50客户</option>
			<option value="60">50客户以上</option>
         </select>
&nbsp;&nbsp;&nbsp;&nbsp;
        <a class="btn btn-default btn-sm J_search"  role="button">查询</a>
        </form>
 
    </script>
</div>
    
    
    </td>
  </tr>
  <tr>
    <td align="center" >名下客户数</td>
  </tr>
  
  <tr>
   <td><div  style="width: 100px;height:100px;"></td>
 </tr>
 
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
    <td><div id="cfpYearAchiData" style="width: 600px;height:300px;"></div></td>
    
    <td><div  style="width: 100px;height:300px;"></td>
    <td>
    
    
    <div id="main-news2" class="container-fluid"style="width: 400px;height:300px;">
    <div class="table-responsive">
        <table id="J-newslist2" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search2" data-url="rest/cfpAchievement/getCfpAchiValueList" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" date-order="true" data-edit="id" data-format="id:cumsId2">序号</th>
                    <th data-name="cfpName" >理财师姓名</th>
                    <th data-name="cfpMobile" data-format="cfpMobile:cumsMobile">理财师手机</th>
                    <th data-name="yearAmount" >年化业绩</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">

        var cumsId2 = function (data,type,full,meta) {
        	return meta.row+1;
        }
        
        var cumsMobile = function (data,type,full,meta) {
        	return data?data.substring(0,data.length-8)+'****'+data.substring(data.length-4,data.length):"--";
        }

       var $db= $("#J-newslist2").eshop4jTable();
       
    </script>
    <script type="text/eshop4j-template" id="template-search2">
        <form id="exportCfpAchievement2">
        年化业绩<select id="name" name="name">
			<option value="">全部</option>
            <option value="10">10万以下</option>
            <option value="20">10-20万</option>
			<option value="30">20-30万</option>
			<option value="40">30-40万</option>
			<option value="50">40-50万</option>
			<option value="100">50-100万</option>
			<option value="110">100万以上</option>
         </select>
&nbsp;&nbsp;&nbsp;&nbsp;
		日期
		<input id="startTimeForSearch" name="startTimeForSearch" class="Wdate" type="text" value ="" onfocus="WdatePicker()" />
		至
		<input id="endTimeForSearch" name="endTimeForSearch" class="Wdate" type="text" value ="" onfocus="WdatePicker()" />
&nbsp;&nbsp;&nbsp;&nbsp;
        <a class="btn btn-default btn-sm J_search"  role="button">查询</a>
        </form>
 
    </script>
</div>
    
    
    </td>
    
  </tr>
  <tr>
    <td align="center" >年化业绩</td>
  </tr>
</table>
</div>
 
  
  <script type="text/javascript" src="app/common/util.js"></script>
 <script type="text/javascript" > 
 $(document).ready(function() {
	 loadView();
	 //异步加载总投资人和年化投资额
	  /* $.ajax({
		type:"POST",
		url:"rest/investor/totaldata",
		async:true,
		success:function(result){
			$('#totalperson').html(result.totalperson);
			 $('#totalmoney').html(result.totalmoney);
		}
		});  */
	 
	});
 function loadView(){
	 
	 $.getJSON('rest/cfpAchievement/getCfpAchiDataView',params, function (json) {
		 var areaCfpCategories = [];  
		 var areaCfpValues = [];
		 
		 var person =json.data.cfpAreaData;
		 $.each(person,function(i,data){
			 areaCfpCategories.push(data.city);
			 areaCfpValues.push(data.personNum);
		 }); 
		 var cfpAreaData = echarts.init(document.getElementById('cfpAreaData'));
		//指定图表的配置项和数据
		 var option = {
				    color: ['#3398DB'],
				    tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
				    },
				
				    grid: {
				        left: '3%',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    xAxis : [
				        {
				            type : 'value'
				        }
				    ],
				    yAxis : [
				        {
				            type : 'category',
				            axisTick : {show: false},
				            data : areaCfpCategories
				        }
				    ],
				    series : [
				        {
				            name:'人数',
				            type:'bar',
				            label: {
				                normal: {
				                    show: true,
				                    position: 'inside'
				                }
				            },
				            data:areaCfpValues
				        },
				       
				    ]
				};
		 cfpAreaData.setOption(option);
		 
	 });
	 
	 
	 $.getJSON('rest/cfpAchievement/getCfpCustomerCountData',params, function (json) {
		 var areaCfpCategories = [];  
		 var areaCfpValues = [];
		 
		 var person =json.data.cfpCustomerCountData;
		 $.each(person,function(i,data){
			 areaCfpCategories.push(data.customerValue);
			 areaCfpValues.push(data.personNum);
		 }); 
		 var cfpCustomerCountData = echarts.init(document.getElementById('cfpCustomerCountData'));
		//指定图表的配置项和数据
		 option = {
			    color: ['#3398DB'],
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : areaCfpCategories,
			            axisTick: {
			                alignWithLabel: true
			            }
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'理财师人数',
			            type:'bar',
			            barWidth: '60%',
			            data:areaCfpValues
			        }
			    ]
			};
		 cfpCustomerCountData.setOption(option);
		 
	 });
	 
	 var params=jsonFromt($('#investorDataViewSearh').serializeArray());
	 $.getJSON('rest/cfpAchievement/cfpYearAchiData',params, function (json) {
		 var areaCfpCategories = [];  
		 var areaCfpValues = [];
		 
		 var person =json.data.cfpYearAchiData;
		 $.each(person,function(i,data){
			 areaCfpCategories.push(data.yearAmount);
			 areaCfpValues.push(data.personNum);
		 }); 
		 var cfpCustomerCountData = echarts.init(document.getElementById('cfpYearAchiData'));
		//指定图表的配置项和数据
		 option = {
			    color: ['#3398DB'],
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : areaCfpCategories,
			            axisTick: {
			                alignWithLabel: true
			            }
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'理财师人数',
			            type:'bar',
			            barWidth: '60%',
			            data:areaCfpValues
			        }
			    ]
			};
		 cfpCustomerCountData.setOption(option);
		 
	 });
	 
	 
	 
 }
 
function loadView2(){
	 
	 
	 var params=jsonFromt($('#investorDataViewSearh').serializeArray());
	 $.getJSON('rest/cfpAchievement/cfpYearAchiData',params, function (json) {
		 var areaCfpCategories = [];  
		 var areaCfpValues = [];
		 
		 var person =json.data.cfpYearAchiData;
		 $.each(person,function(i,data){
			 areaCfpCategories.push(data.yearAmount);
			 areaCfpValues.push(data.personNum);
		 }); 
		 var cfpCustomerCountData = echarts.init(document.getElementById('cfpYearAchiData'));
		//指定图表的配置项和数据
		 option = {
			    color: ['#3398DB'],
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : areaCfpCategories,
			            axisTick: {
			                alignWithLabel: true
			            }
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'理财师人数',
			            type:'bar',
			            barWidth: '60%',
			            data:areaCfpValues
			        }
			    ]
			};
		 cfpCustomerCountData.setOption(option);
		 
	 });
	 
	 
	 
 }
 
	$('#investorDateViewSearchBtn').click(function() {
		loadView2();
	});
 </script>