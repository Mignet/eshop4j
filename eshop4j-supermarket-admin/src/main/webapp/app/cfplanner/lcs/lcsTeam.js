using(['common','my97DatePicker','datagrid','messager'],function(){
	
	lh.inintDatagrid('#lcsTeamDg','#lcsTeamSearch','rest/lcsList/getLcsTeamList',lcsTeamModel);
	lh.on("click").addHandler([{"selector":"a[name='unbind']","handler": function(e){
			var $this= $(this);
			$.messager.confirm('系统提示','确认解除该理财师的绑定关系?',function(r){
			    if (r){
			    	lh.post('rest/lcsList/exitLcs',{'mobile':$this.attr("data-mobile")},function(data){
			    		if(data.isFlag){
			    			lh.post('rest/lcsList/getLcsTeamList',{'lcsNumber':$("#lcsTeamSearch input[name='lcsNumber']").val(),pageIndex:1,pageSize:20},function(data){
			    				$('#lcsTeamDg').datagrid('loadData',  {rows:data.datas,total:data.totalCount});
				    		 });
			    		}else{
			    			if(data.msg){
			    				$.messager.alert('系统提示',data.msg);
			    			}else{
			    				$.messager.alert('系统提示',"操作失败,请重试!");
			    			}
			    		}
			    		
			    		
			    		
			    	 });
			    }
			});
		}
	},
	{"selector":"#lcsTeamSearch .J_export","handler": function(e){
		$form = $("#lcsTeamSearch").attr("action","rest/lcsList/exportLcsTeamList");
		$form[0].submit();
		return false;
	}}
	]);
});