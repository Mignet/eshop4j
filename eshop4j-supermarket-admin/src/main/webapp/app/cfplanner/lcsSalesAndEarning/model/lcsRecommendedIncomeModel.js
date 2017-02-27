lcsRecommendedIncomeModel = {
	rownumbers:true,
	singleSelect:true,
	autoRowHeight:false,
	pagination: true, 
	/*showFooter:true,*/
	fitColumns:true,
	toolbar:"#lcsRecommendedIncomeToolbar",
	rowStyler : function(value,row,index){
			return 'height:40px;';
	},
	columns : [ [
						{
							field : 'mobile',
							title : '电话',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'name',
							title : '人员',
							width : 200,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'level',
							title : '关系',
							width : 200,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						
						{
							field : 'proportion',
							title : '比例(%)',
							width : 250,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},{
							field : 'productName',
							title : '产品名称',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'sales',
							title : '销售额',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'earnings',
							title : '推荐收益',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'startDate',
							title : '销售时间',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'endDate',
							title : '到期日期',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						}
						] ]
}