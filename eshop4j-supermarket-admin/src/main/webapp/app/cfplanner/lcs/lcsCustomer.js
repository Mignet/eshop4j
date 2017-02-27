using(['common','my97DatePicker','datagrid','messager'],function(){
	lh.inintDatagrid('#lcsCustomerDg','#lcsCustomerSearch','rest/lcsList/getLcsCustomerList',lcsCustomerListModel);
	lh.on("click").addHandler([{"selector":"a[name='unbind']","handler": function(e){
			var $this= $(this);
			$.messager.confirm('系统提示','确认解除该理财师与用户的绑定关系?',function(r){
			    if (r){
			    	lh.post('rest/lcsList/unbindByCustomer',{'lcsNumber':$("#lcsCustomerSearch input[name='lcsNumber']").val(),"customerMobile":$this.attr("data-number")},function(data){
			    		if(data.isFlag){
				    		lh.post('rest/lcsList/getLcsCustomerList',{'lcsNumber':$("#lcsCustomerSearch input[name='lcsNumber']").val(),pageIndex:1,pageSize:20},function(data){
				    			$('#lcsCustomerDg').datagrid('loadData',  {rows:data.datas,total:data.totalCount});
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
			return false;
		}
	},
	{"selector":"#lcsCustomerSearch .J_export","handler": function(e){
		$form = $("#lcsCustomerSearch").attr("action","rest/lcsList/exportLcsCustomerList");
		$form[0].submit();
		return false;
	}}]);
});