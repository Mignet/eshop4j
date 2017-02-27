using(['common','my97DatePicker','datagrid','window'],function(){
//	$('#lcsListSearch .J_export').remove();
	lh.inintDatagrid('#lcsDg','#lcsListSearch','rest/lcsList/getLcsList',lcsListModel,true);
	lh.on("click").addHandler([
	   // {"selector":"a[name='detail']","handler": function(e){
		// 	var $this = $(this);
		// 	lh.openWin({
		// 		title : "理财师详情",
		// 	    width:600,
		// 	    height:400,
		// 	    modal:true,
		// 	    href:'rest/lcsList/getLcsDetail?mobile='+$this.attr("data-mobile")
		// 			});
		// 	return false;
	   // }},
	   {"selector":"a[name='updatePwd']","handler": function(e){
		  
			var $this = $(this);
			lh.openWin({
				title : "理财师密码修改",
			    width:600,
			    height:400,
			    modal:true,
			    href:'rest/lcsList/resetpwdPage?mobile='+$this.attr("data-mobile")
			});
			return false;
		}},
		{"selector":"a[name='changeParent']","handler": function(e){
			  
			var $this = $(this);
			lh.openWin({
				title : "更改上级",
			    width:600,
			    height:400,
			    modal:true,
			    href:'rest/lcsList/toChangeParent?mobile='+$this.attr("data-mobile")
			});
			return false;
		}},
		{"selector":"a[name='department']","handler": function(e){
			var $this = $(this);
			lh.openWin({
				title : "新财富-自由理财师切换",
			    width:500,
			    height:200,
			    modal:true,
			    href:'rest/lcsList/orgPage?department='+$this.text()+'&lcsNumber='+$this.attr("data-number")
			});
			return false;
		}},
		{"selector":"a[name='cfplevel']","handler": function(e){
			var $this = $(this);
			lh.openWin({
				title : "理财师职级变更记录",
			    width:500,
			    height:500,
			    modal:true,
			    href:'rest/lcsList/lcsHisrecPage?lcsNumber='+$this.attr("data-number")
			});
			return false;
		}},
		{"selector":"a[name='lcsCustomer']","handler": function(e){
			var $this = $(this);
			lh.openWin({
				title : "理财师客户列表",
				width:1200,
			    height:700,
				inline:true,
				collapsible:false,
			    modal:true,
			    href:'rest/lcsList/lcsCustomerListPage',
			    queryParams :{"lcsNumber":$this.attr("data-lcsnumber"),"mobile":$this.attr("data-mobile")}
			});
			
			return false;
		}},
		{"selector":"a[name='lcsTeam']","handler": function(e){
			var $this = $(this);
			lh.openWin({
				title : "理财师团队列表",
				width:1200,
			    height:700,
				inline:true,
				collapsible:false,
			    modal:true,
			    href:'rest/lcsList/lcsTeamListPage',
			    queryParams :{"lcsNumber":$this.attr("data-lcsnumber")}
			});
			return false;
		}}
		,
		{"selector":"#lcsListSearch .J_export","handler": function(e){
			$form = $("#lcsListSearch").attr("action","rest/lcsList/exportLcsList");
			$form[0].submit();
			return false;
		}}
	]);
	
});