lcsCommissionDetailModel = {
	rownumbers:true,
	singleSelect:true,
	autoRowHeight:false,
	pagination: true, 
	fitColumns:true,
	/*showFooter:true,*/
	toolbar:"#lcsCommissionDetailToolbar",
	rowStyler : function(value,row,index){
			return 'height:40px;';
	},
	columns : [ [
						{
							field : 'name',
							title : '人员',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'mobile',
							title : '手机号',
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
							field : 'investmentAmount',
							title : '累计销售(元)',
							width : 250,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'fix',
							title : '年利率',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						
						{
							field : 'foptype',
							title : '状态',
							width : 80,
							align : 'center',
							formatter : function(value, row, index){
								if(value==2)return "在投";
								if(value==3)return "赎回";
								if(value==4)return "回款";
								return "-";
							}
						},
						{
							field : 'startDate',
							title : '投资时间',
							width : 180,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						}
						,
						{
							field : 'endDate',
							title : '到期日期',
							width : 180,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}	
						}
						,
						{
							field : 'tfee',
							title : '贡献佣金(元)',
							width : 80,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						}
						] ]
}