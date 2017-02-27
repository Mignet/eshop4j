lcsActivityProfitDetailModel = {
	rownumbers:true,
	singleSelect:true,
	autoRowHeight:false,
	pagination: true, 
	/*showFooter:true,*/
	fitColumns:true,
	toolbar:"#lcsActivityProfitDetailToolbar",
	rowStyler : function(value,row,index){
			return 'height:40px;';
	},
	columns : [ [
						{
							field : 'name',
							title : '理财师',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'activity',
							title : '活动名称',
							width : 200,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'startDate',
							title : '开始时间',
							width : 200,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'endDate',
							title : '结束时间',
							width : 250,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'profit',
							title : '获得奖励(元)',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						}
						] ]
}