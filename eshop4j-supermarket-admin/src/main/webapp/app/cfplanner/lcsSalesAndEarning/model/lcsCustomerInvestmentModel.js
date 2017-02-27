lcsCustomerInvestmentModel = {
	rownumbers:true,
	singleSelect:true,
	autoRowHeight:false,
	pagination: true, 
	/*showFooter:true,*/
	fitColumns:true,
	toolbar:"#lcsCustomerInvestmentToolbar",
	rowStyler : function(value,row,index){
			return 'height:40px;';
	},
	columns : [ [
						{
							field : 'name',
							title : '投资客户',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'investmentTotalAmount',
							title : '销售金额(元)',
							width : 200,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'productName',
							title : '购买产品',
							width : 200,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'tfee',
							title : '贡献佣金',
							width : 250,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'startDate',
							title : '开始日期',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'endDate',
							title : '结束日期',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'deadLine',
							title : '产品期限',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						}
						] ]
}