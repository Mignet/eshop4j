lcsTeamModel = {
	rownumbers:true,
	singleSelect:true,
	autoRowHeight:false,
	pagination: true,
	fitColumns:true,
	toolbar:"#lcsTeamToolbar",
	rowStyler : function(value,row,index){
			return 'height:40px;';
	},
	columns : [ [
						{
							field : 'name',
							title : '名称',
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
							field : 'idcard',
							title : '证件号',
							width : 200,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'teamLevel',
							title : '团队关系',
							width : 150,
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
							field : 'sales',
							title : '销售额(元)',
							width : 200,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'saleCount',
							title : '销售笔数',
							width : 80,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'earnings',
							title : '创造收益(元)',
							width : 150,
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
								if(row.teamLevel=="二级"){
									return $("<a>").text("解除绑定").css("color","#AAAAAA").attr({href:"javascript:;"})[0].outerHTML;
								}else{
									return $("<a>").text("解除绑定").attr({"data-mobile":row.mobile,name:"unbind",href:"javascript:;"})[0].outerHTML;
								}
								
							}
						}
						] ]
}