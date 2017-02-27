lcsSalesAndEarningModel = {
	rownumbers:true,
	singleSelect:true,
	autoRowHeight:false,
	pagination: true, 
	fitColumns:true,
	remoteSort:false,
	toolbar:"#lcsSalesAndEarningToolbar",
	rowStyler : function(value,row,index){
			return 'height:40px;';
	},
	columns : [ [
						{
							field : 'number',
							title : 'id',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'mobile',
							title : '电话',
							width : 200,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'name',
							title : '理财师',
							width : 200,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'investmentTotalAmount',
							title : '累计销售',
							width : 250,
							align : 'center',
							sortable:true,
							sorter:function(a,b){  
								if (a == b){  
									return 0;
								} else if(a > b) {  
									return 1
								} else{
									return -1;
								} 
							}
						},
						{
							field : 'investmentCount',
							title : '销售比数',
							width : 150,
							align : 'center',
							sortable:true,
							sorter:function(a,b){  
								if (a == b){  
									return 0;
								} else if(a > b) {  
									return 1
								} else{
									return -1;
								} 
							}
						},
						{
							field : 'tfee',
							title : '佣金',
							width : 100,
							align : 'center',
							sortable:true,
							sorter:function(a,b){  
								if (a == b){  
									return 0;
								} else if(a > b) {  
									return 1
								} else{
									return -1;
								} 
							}
						},
						{
							field : 'recommendedAmount',
							title : '推荐收益',
							width : 80,
							align : 'center',
							sortable:true,
							sorter:function(a,b){  
								if (a == b){  
									return 0;
								} else if(a > b) {  
									return 1
								} else{
									return -1;
								} 
							}
						},
						{
							field : 'profitTotalAmount',
							title : '活动奖励',
							width : 80,
							align : 'center',
							sortable:true,
							sorter:function(a,b){  
								if (a == b){  
									return 0;
								} else if(a > b) {  
									return 1
								} else{
									return -1;
								} 
							}
						},
						{
							field : 'handler',
							title : '操作',
							width : 600,
							align : 'center',
							formatter : function(value, row, index){
								var detail = $("<a>").text("佣金明细").attr({"data-mobile":row.mobile,name:"lcsCommissionDetail",href:"javascript:;"})[0].outerHTML;
								var lcsRecommendedIncome = $("<a>").text("推荐收益").attr({"data-mobile":row.mobile,name:"lcsRecommendedIncome",href:"javascript:;"})[0].outerHTML;
								var activityProfit = $("<a>").text("活动奖励").attr({"data-mobile":row.mobile,name:"activityProfit",href:"javascript:;"})[0].outerHTML;
								var lcsCustomer = $("<a>").text("当前客户在投").attr({"data-mobile":row.mobile,name:"customerInvestment",href:"javascript:;"})[0].outerHTML;
								
								var div1 = $("<div>").append(detail).append("   ").append(lcsRecommendedIncome).append("   ").append(activityProfit).append("   ").append(lcsCustomer).append("   ");
								return div1[0].outerHTML;
							}
						}
						] ]
}