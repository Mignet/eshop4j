lcsListModel = {
	singleSelect:true,
	autoRowHeight:false,
	pagination: true,
	fitColumns:true,
	remoteSort:false,
	toolbar:"#lcsListToolbar",
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
							field : 'name',
							title : '姓名',
							width : 200,
							align : 'center',
							formatter : function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'rz',
							title : '认证',
							width : 200,
							align : 'center',
							formatter : function(value, row, index){
								return row.idcard?"已认证" : "<a  style='color: red;'>未认证</a>";
							}
						},
						{
							field : 'mobile',
							title : '电话',
							width : 200,
							align : 'center',
							formatter:function(value, row, index){
								if(value.length>0){
									return value.substring(0,3)+'****'+value.substring(7,value.length);
								}
								
							}
						},
						{
							field : 'idcard',
							title : '身份证',
							width : 250,
							align : 'center',
							formatter : function(value, row, index){
								if(value){
									return value.substring(0,value.length-4)+'****';
								}else{
									return "<a  style='color: red;'>暂未绑定</a>";
								}
								
							}
							
						},
						{
							field : 'department',
							title : '所属机构',
							width : 150,
							align : 'center',
							formatter : function(value, row, index){
								return $('<a>').text(value).attr({"data-number":row.number,href:"javascript:;",name:"department"})[0].outerHTML;
							}
						},
						{
							field : 'cfplevel',
							title : '职级',
							width : 200,
							align : 'center',
							formatter : function(value, row, index){
								return $('<a>').text(value).attr({"data-number":row.number,href:"javascript:;",name:"cfplevel"})[0].outerHTML;
							}
						},
		{
			field : 'parentName',
			title:"上级理财师",
			width:200,
			align:"center"
		},
		{
			field : 'parentMobile',
			title:"上级账号",
			width:200,
			align:"center"
		},
						{
							field : 'team',
							title : '团队人数',
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
							field : 'customer',
							title : '客户人数',
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
							field : 'regTime',
							title : '注册时间',
							width : 150,
							align : 'center',
							sortable:true,
							sorter:function(a,b){    
				                a = a.split('-');    
				                b = b.split('-');    
				                if (a[0] == b[0]){    
				                    if (a[1] == b[1]){    
				                        return (a[2]>b[2]?1:-1);    
				                    } else {    
				                        return (a[1]>b[01]?1:-1);    
				                    }    
				                } else {    
				                    return (a[0]>b[0]?1:-1);    
				                }    
				            } 
						},
						{
							field : 'handler',
							title : '操作',
							width : 300,
							align : 'center',
							formatter : function(value, row, index){
								var detail = $("<a>").text("详情").attr({"data-mobile":row.mobile,name:"detail",href:"javascript:;","data-title":"理财师详情","data-url":"rest/lcsList/getLcsDetail?mobile="+row.mobile,"class":"ui-redirect"})[0].outerHTML;
								var updatePwd = $("<a>").text("修改密码").attr({"data-mobile":row.mobile,name:"updatePwd",href:"javascript:;"})[0].outerHTML;
								var changeParent = $("<a>").text("更改上级").attr({"data-mobile":row.mobile,name:"changeParent",href:"javascript:;"})[0].outerHTML;
								var lcsTeam = $("<a>").text("团队").attr({"data-lcsNumber":row.number,name:"lcsTeam",href:"javascript:;"})[0].outerHTML;
								var lcsCustomer = $("<a>").text("客户").attr({"data-lcsNumber":row.number,"data-mobile":row.mobile,name:"lcsCustomer",href:"javascript:;"})[0].outerHTML;
								var div1 = $("<div>").append(detail);
								return div1[0].outerHTML;
							}
						}
						] ]
}