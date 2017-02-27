lcsHisrecModel = {
	singleSelect:true,
	autoRowHeight:false,
	pagination: true, 
	rownumbers: true,
	rowStyler : function(value,row,index){
			return 'height:30px;';
	},
	columns : [ [
					
						{
							field : 'remark',
							title : '职级名称',
							width : 200,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'crtTime',
							title : '时间',
							width : 200,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						}
						] ]
}