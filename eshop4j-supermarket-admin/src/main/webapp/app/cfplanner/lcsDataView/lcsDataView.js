using(['echarts','my97DatePicker','common'],function(){
	var lcs = echarts.init(document.getElementById('lcs'));
	var lcsValid = echarts.init(document.getElementById('lcsValid'));
	lh.on("click").addHandler({
		"selector":"#lcsDataViewSearch",
		"handler":function(e){
			refreshEcharts(lh.serializeObject("#lcsDataViewSearh"));
		}
	});
	
	function refreshEcharts(query){
		$.post('rest/lcsDataView/getLcsDataView',query?query:{},function (json){
			var lcsData = json.data.lcs;
			var lcsValidData = json.data.lcsValid;
			/*$("#lcsCount").text('理财师总计：'+lcsData.total);
			$("#lcsValidCount").text('理财师总计：'+lcsValidData.total);*/
			echartsDataWarp(lcsData.data,lcs);
			echartsDataWarp(lcsValidData.data,lcsValid);
		});
	}
	function echartsDataWarp(data,echartElement){
		 var categories = []; 
		 var values = [];
		 $.each(data,function(i,data){
			 categories.push(data.date);
			 values.push(data.count);
		 });
		 window.onresize = echartElement.resize;
		 echartElement.setOption({
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
		         data:['理财师']
		     },
		     calculable : true, 
		     xAxis: {
		    	 type : 'category',
		         data: categories,
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
		         data: values
		     }]
		 });
	}
	refreshEcharts();
});