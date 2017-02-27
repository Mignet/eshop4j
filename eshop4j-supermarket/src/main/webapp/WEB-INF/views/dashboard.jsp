<%@ page language="java" pageEncoding="utf-8"%>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="assets/plugins/echarts/echarts.min.js"  ></script>
<script type="text/javascript" src="assets/plugins/flot/jquery.flot.js"></script>
<script type="text/javascript" src="assets/plugins/flot/jquery.flot.resize.js"></script>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-6" style="min-width: 30px;">
			<div class="tabbable"> <!-- Only required for left/right tabs -->
			  <ul class="nav nav-tabs">
			    <li><a class="tag" href="#tab1" data-toggle="tab" id="today" name="tag" value="1">今天</a></li>
	            <li><a class="tag" href="#tab2" data-toggle="tab" id="yestoday" name="tag" value="0">昨天</a></li>
	            <li class="active"><a class="tag" href="#tab3" data-toggle="tab" id="lastWeek" name="tag" value="7">最近7天</a></li>
	            <li><a class="tag" href="#tab4" data-toggle="tab" id="lastMonth" name="tag" value="30">最近30天</a></li>
	           <!--  <li><div class="col-sm-6" style="min-width: 100px;margin-left:50px">
			            <form id="dataStatisticsViewSearh" style="top: 15px;"> 
							日期: <input id="custDataViewStartDate" name="startTime" class="easyui-datebox" style="width:110px" value="" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'custDataViewEndDate\')}'})"> 至
				        	<input id="custDataViewEndDate" name="endTime" class="easyui-datebox" style="width:110px" value="" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'custDataViewStartDate\')}'})">
							<a href="javascript:void(0)"  id="dataStatisticsViewSearhBtn" class="easyui-linkbutton btn btn-default" >搜索</a>
						</form>
			        </div>
        		</li> -->
			  </ul>
			  <div class="tab-content">
			    <div class="tab-pane active" id="tab1">
			      <p></p>
			    </div>
			    <div class="tab-pane active" id="tab2">
			      <p></p>
			    </div>	
			    <div class="tab-pane active" id="tab3">
			      <p></p>
			    </div>
			    <div class="tab-pane active" id="tab4">
			      <p></p>
			    </div>	    
			  </div>
			</div>       
        </div>       
    	<div class="col-sm-6" style="min-width: 100px;">
           <form id="dataStatisticsViewSearh" style="top: 15px;"> 
			日期: <input id="custDataViewStartDate" name="startTime" class="easyui-datebox" style="width:110px" value="" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'custDataViewEndDate\')}'})"> 至
        	<input id="custDataViewEndDate" name="endTime" class="easyui-datebox" style="width:110px" value="" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'custDataViewStartDate\')}'})">
			<a href="javascript:void(0)"  id="dataStatisticsViewSearhBtn" class="easyui-linkbutton btn btn-default" >搜索</a>
		</form>
       </div>
    </div>
       
    <div class="row">
	    <div class="col-lg-2 col-md-3 col-sm-6 col-xs-12">
	        <div class="dashboard-stat blue">
	            <div class="visual">
	                <i class="fa fa-comments"></i>
	            </div>
	            <div class="details">
	                <div id="newInvestor" class="number"></div>
	            </div>
	            <a class="more" href="#">新增投资人（人）<i class="m-icon-swapright m-icon-white"></i>
	            </a>
	        </div>
	    </div>
	    <div class="col-lg-2 col-md-3 col-sm-6 col-xs-12">
	        <div class="dashboard-stat blue">
	            <div class="visual">
	                <i class="fa fa-comments"></i>
	            </div>
	            <div class="details">
	                <div id="totalInvestor" class="number"></div>
	            </div>
	            <a class="more" href="#">累计投资人（人）<i class="m-icon-swapright m-icon-white"></i>
	            </a>
	        </div>
	    </div>
	    <div class="col-lg-2 col-md-3 col-sm-6 col-xs-12">
	        <div class="dashboard-stat green">
	            <div class="visual">
	                <i class="fa fa-bar-chart"></i>
	            </div>
	            <div class="details">
	                <div id="newCfp" class="number">549</div>
	            </div>
	            <a class="more" href="#">新增理财师（人）<i class="m-icon-swapright m-icon-white"></i>
	            </a>
	        </div>
	    </div>
	    <div class="col-lg-2 col-md-3 col-sm-6 col-xs-12">
	        <div class="dashboard-stat green">
	            <div class="visual">
	                <i class="fa fa-bar-chart"></i>
	            </div>
	            <div class="details">
	                <div id="totalCfp" class="number">549</div>
	            </div>
	            <a class="more" href="#">累计理财师（人）<i class="m-icon-swapright m-icon-white"></i>
	            </a>
	        </div>
	    </div>
	    <div class="col-lg-2 col-md-3 col-sm-6 col-xs-12">
	        <div class="dashboard-stat yellow">
	            <div class="visual">
	                <i class="fa fa-money"></i>
	            </div>
	            <div class="details">
	                <div id="newInvestment" class="number">89</div>
	            </div>
	            <a class="more" href="#">新增投资额（元）<i class="m-icon-swapright m-icon-white"></i>
	            </a>
	        </div>
	    </div>	        
	    <div class="col-lg-2 col-md-3 col-sm-6 col-xs-12">
	        <div class="dashboard-stat yellow">
	            <div class="visual">
	                <i class="fa fa-money"></i>
	            </div>
	            <div class="details">
	                <div id="totalInvestment" class="number">89</div>
	            </div>
	            <a class="more" href="#">累计投资额（元）<i class="m-icon-swapright m-icon-white"></i>
	            </a>
	        </div>
	    </div>	    
	</div>
    
    <div class="row">
        <div class="col-lg-4 col-md-6 col-sm-6 col-xs-12">
            <div id="investorStatistics" style="min-height: 300px;"></div>
        </div>
        <div class="col-lg-4 col-md-6 col-sm-6 col-xs-12">
            <div id="cfpStatistics" style="min-height: 300px;"></div>
        </div>
        <div class="col-lg-4 col-md-6 col-sm-6 col-xs-12">
            <div id="investmentStatistics" style="min-height: 300px;"></div>
        </div>
    </div>

</div>

 <script type="text/javascript" src="app/common/util.js"></script>
<!--  <style type="text/css">
 .tickLabel
{
 -o-transform:rotate(-90deg);
  -moz-transform:rotate(-90deg);
 -webkit-transform:rotate(-90deg);
  filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=1);
  lineHeight: 13;
}
 </style> -->
 <script type="text/javascript" > 
 $(document).ready(function() {
	var params = {tag:7};	
	var path = 'rest/data/statistics/tag';
	loadView(path,params);
 }); 

 function loadView(path,params){
	  
	 $.getJSON(path,params, function (json) {
		 
		 $('#newInvestor').text(json.data.count.newInvestor);
		 $('#newCfp').text(json.data.count.newCfp);
		 $('#newInvestment').text((json.data.count.newInvestment==undefined?0:json.data.count.newInvestment));
		 $('#totalInvestor').text(json.data.count.totalInvestor);
		 $('#totalCfp').text(json.data.count.totalCfp);
		 $('#totalInvestment').text(json.data.count.totalInvestment);
	
		 var investorCategories = [];  
		 var investorValues = [];
		 
		 var cfpCategories = [];  
		 var cfpValues = [];
		 
		 var investmentCategories = [];  
		 var investmentValues = [];
		 
		 /* var investorData = json.data.investor;
		 $.each(investorData,function(i,data){
			 investorCategories.push(data.registerInvestorTime);
			 investorValues.push(data.registerInvestorNum);
		 });
		 
		 var cfpData = json.data.cfp;
		 $.each(cfpData,function(i,data){
			 cfpCategories.push(data.registerCfpTime);
			 cfpValues.push(data.registerCfpNum);
		 });
		 
		 var investmentData = json.data.investment;
		 $.each(investmentData,function(i,data){
			 investmentCategories.push(data.investTime);
			 investmentValues.push(data.currentInvestment);
		 });  */
		 
		 /*var investorStatistics = echarts.init(document.getElementById('investorStatistics'));
		 var investorOption = {
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
		         data:['新增投资人']
		     },
		     calculable : true, 
		     xAxis: {
		    	 type : 'category',
		         data: investorCategories,
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
		         data: investorValues
		     }]
		 };
		 investorStatistics.setOption(investorOption);
		
		 var cfpStatistics = echarts.init(document.getElementById('cfpStatistics'));
		 var cfpOption = {
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
		         data:['新增理财师']
		     },
		     calculable : true, 
		     xAxis: {
		    	 type : 'category',
		         data: cfpCategories,
		         boundaryGap: true
		     },
		     yAxis: {
		    	 type: 'value',
		    	 min:0,
		         boundaryGap: true
		     },
		     series: [{
		         name: '人数',
		         type:'line',
		         data: cfpValues
		     }]
		 };
		 cfpStatistics.setOption(cfpOption);
		 
		 var investmentStatistics = echarts.init(document.getElementById('investmentStatistics'));
		 var investmentOption = {
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
		         data:['新增理财师']
		     },
		     calculable : true, 
		     xAxis: {
		    	 type : 'category',
		         data: investmentCategories,
		         boundaryGap: false
		     },
		     yAxis: {
		    	 type: 'value',
		    	 min:0,
		         boundaryGap: false
		     },
		     series: [{
		         name: '金额',
		         type:'line',
		         data: investmentValues
		     }]
		 };
		 investmentStatistics.setOption(investmentOption);   */
		 
		 var investorData = json.data.investor;
		 $.each(investorData,function(i,data){
			 investorCategories.push([i,data.registerInvestorTime]);
			 investorValues.push([i,data.registerInvestorNum]);
		 });
		 
		 var cfpData = json.data.cfp;
		 $.each(cfpData,function(i,data){
			 cfpCategories.push([i,data.registerCfpTime]);
			 cfpValues.push([i,data.registerCfpNum]);
		 });
		 
		 var investmentData = json.data.investment;
		 $.each(investmentData,function(i,data){
			 investmentCategories.push([i,data.investTime]);
			 investmentValues.push([i,data.currentInvestment]);
		 }); 
		        
         var investorPlot = $.plot(document.getElementById('investorStatistics'),[{ label: "新增投资人",color:"#27a9e3", data: investorValues}], {
             series: {
                 lines: { show: true },
                 points: { show: true }
             },
             grid: { hoverable: true, clickable: true },
             xaxis:{ticks:investorCategories},
             yaxis:{}
           });
         
         function formatXaxis(array){
        	 var result = new Array();
        	 console.log(array);
        	 if(array.length > 8){
        		 for(var i=0,j=0;i < array.length;i++){
        			 if(i%8 == 0){
        				 result[j] = array[i];
        				 j++;
        			 }
        		 }
        	 }else{
        		 return array;
        	 }
        	 return result;
         }
         
         function showTooltip(x, y, contents) {
             $('<div id="tooltip">' + contents + '</div>').css( {
                 position: 'absolute',
                 display: 'none',
                 top: y + 5,
                 left: x + 5,
                 border: '1px solid #fdd',
                 padding: '2px',
                 'background-color': '#fee',
                 opacity: 0.80
             }).appendTo("body").fadeIn(200);
         }

         var previousPoint = null;
         $("#investorStatistics").bind("plothover", function (event, pos, item) {
             if (item) {
                 if (previousPoint != item.dataIndex) {
                     previousPoint = item.dataIndex;
                     $("#tooltip").remove();
                     var y = item.datapoint[1]; 
                     showTooltip(item.pageX, item.pageY,
                    		 "时间："+ item.series.xaxis.ticks[item.datapoint[0]].label + "<br/>" + "新增：" + y+"人");
                 }
             }
             else {
                 $("#tooltip").remove();
                 previousPoint = null;            
             }          
         });
         $("#investorStatistics").bind("plotclick", function (event, pos, item) {
             if (item) {
            	 investorPlot.highlight(item.series, item.datapoint);
                 var path = 'rest/data/statistics';
                 var params = {startTime:item.series.xaxis.ticks[item.datapoint[0]].label,endTime:item.series.xaxis.ticks[item.datapoint[0]].label};	
         		 loadView(path,params);
             }
         });
         
        
         var cfpPlot = $.plot(document.getElementById('cfpStatistics'),[{ label: "新增理财师",color:"#28b779", data: cfpValues}],{
             series: {
                 lines: { show: true },
                 points: { show: true }
             },
             grid: { hoverable: true, clickable: true },
             xaxis:{ticks:cfpCategories},
             yaxis:{}
           });        
         $("#cfpStatistics").bind("plothover", function (event, pos, item) {
             if (item) {
                 if (previousPoint != item.dataIndex) {
                     previousPoint = item.dataIndex;
                     $("#tooltip").remove();
                     var y = item.datapoint[1];                     
                     showTooltip(item.pageX, item.pageY,
                    		 "时间："+ item.series.xaxis.ticks[item.datapoint[0]].label + "<br/>" + "新增：" + y+"人");
                 }
             }
             else {
                 $("#tooltip").remove();
                 previousPoint = null;            
             }          
         });        
         $("#cfpStatistics").bind("plotclick", function (event, pos, item) {
             if (item) {
            	 investorPlot.highlight(item.series, item.datapoint);
                 var path = 'rest/data/statistics';
                 var params = {startTime:item.series.xaxis.ticks[item.datapoint[0]].label,endTime:item.series.xaxis.ticks[item.datapoint[0]].label};	
         		 loadView(path,params);
             }
         });
         
         var investmentPlot = $.plot(document.getElementById('investmentStatistics'),[{ label: "新增投资额",color:"#ffb848", data: investmentValues}],{
             series: {
                 lines: { show: true },
                 points: { show: true }
             },
             grid: { hoverable: true, clickable: true },
             xaxis:{ticks:investmentCategories},
             yaxis:{}
           }); 
         $("#investmentStatistics").bind("plothover", function (event, pos, item) {
             if (item) {
                 if (previousPoint != item.dataIndex) {
                     previousPoint = item.dataIndex;
                     $("#tooltip").remove();
                     var y = item.datapoint[1].toFixed(2);                     
                     showTooltip(item.pageX, item.pageY,
                    		 "时间："+ item.series.xaxis.ticks[item.datapoint[0]].label + "<br/>" + "新增：" + y+"元");
                 }
             }
             else {
                 $("#tooltip").remove();
                 previousPoint = null;            
             }          
         });
         $("#investmentStatistics").bind("plotclick", function (event, pos, item) {
             if (item) {
            	 investorPlot.highlight(item.series, item.datapoint);
                 var path = 'rest/data/statistics';
                 var params = {startTime:item.series.xaxis.ticks[item.datapoint[0]].label,endTime:item.series.xaxis.ticks[item.datapoint[0]].label};	
         		 loadView(path,params);
             }
         }); 
		 
	 }); 
 }
	$('#dataStatisticsViewSearhBtn').click(function() {
		var params=jsonFromt($('#dataStatisticsViewSearh').serializeArray());
		var path = 'rest/data/statistics';
		loadView(path,params);
	});
	$('.tag').click(function(e) {
		var tag = $(e.target).attr("value");
		var params = {tag:tag};	
		var path = 'rest/data/statistics/tag';
		loadView(path,params);
	});
 </script>