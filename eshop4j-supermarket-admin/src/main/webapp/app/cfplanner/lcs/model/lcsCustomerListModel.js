lcsCustomerListModel = {
	rownumbers:true,
	singleSelect:true,
	autoRowHeight:false,
	pagination: true, 
	fitColumns:true,
	toolbar:"#lcsCustomerToolbar",
	rowStyler : function(value,row,index){
			return 'height:30px;';
	},
	columns : [ [
					
						{
							field : 'name',
							title : '名称',
							width : 100,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'mobile',
							title : '电话',
							width : 100,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'from',
							title : '来源',
							width : 100,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'bindTime',
							title : '绑定时间',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'investmentTotalAmount',
							title : '总投资额（元）',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'investmentCount',
							title : '投资笔数',
							width : 80,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'nowInvestmentAmount',
							title : '在投总额(元)',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'nowInvestmentCount',
							title : '在投笔数',
							width : 80,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'tfee',
							title : '贡献佣金',
							width : 100,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'handler',
							title : '操作',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								var lcsMobile = $("#lcsCustomerSearch input[name='lcsMobile']").val();
								return row.mobile==lcsMobile?$("<a>").text("解除绑定").css("color","#AAAAAA").attr({href:"javascript:;"})[0].outerHTML:$("<a>").text("解除绑定").attr({"data-number":row.mobile,name:"unbind",href:"javascript:;"})[0].outerHTML;
							}
						}
						] ]
}