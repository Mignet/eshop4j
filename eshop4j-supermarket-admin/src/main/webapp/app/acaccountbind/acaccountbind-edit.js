$(document).ready(function() {
	
   $('#frm-example').on('submit', function(e){
	   e.preventDefault();
    })
	
	/**
	 * 判断表单的信息是否通过验证
	 */
	$("#frm-example").validate({
		//debug: true, //只验证不提交表单
	    //meta: "validate",//设置meta来封装验证规则
		//onkeyup:false, //是否在敲击键盘时验证
		ignore: "", //验证隐藏域
	    errorElement: 'span',
	    errorClass: 'help-block help-block-error',
	    focusInvalid: true, //当为false时，验证无效时，没有焦点响应
	    rules : { 
	    	mobile:{required:true,number:true},
	    	userName:"required", //单个条件验证
	    	idCard:{required:true,number:true}, 
	    	bankId:"required", 
	    	bankCard:{required:true,number:true}, 
	    	reserveMobile:{required:true,number:true}, 
	    },  
	    messages : { 
	    	mobile:{required:"注册手机号码不能为空！",},
	    	userName:{required:"用户姓名不能为空！",},
			idCard:{required:"身份证号不能为空！",},
			bankId:{required:"银行不能为空！",},
			bankCard:{required:"银行卡号不能为空！",},
			reserveMobile:{required:"银行预留手机号码不能为空！"},
	    },  
	    highlight: function (element) {
	    	//可以给未通过验证的元素加效果、闪烁等
	        $(element).closest('.form-group').addClass('has-error');
	    },
	    success: function (label) {
	        label.closest('.form-group').removeClass('has-error');
	        label.remove();
	    },
	    errorPlacement: function (error, element) {
	        element.parent('div').append(error);
	    },
	    submitHandler: function (form) {
	    	$.ajax({
	        	data:$(form).toFormJSON(), //表单对象数据转json字符串提交
	        	contentType : 'application/json;charset=UTF-8', //设置请求头格式
	            type:'POST',
	            url: '/rest/acc/acaccountbind/update' ,
	            success: function (result) {
	            	if(result.isFlag){
	            		//绑卡成功
						layer.alert(result.msg,{icon: 6,shift: 4,title:'提示'},function(index){
							layer.close(index);
							//debugger;
							$.switchPage("绑卡账户列表","rest/acc/acaccountbind/list"); //跳到机构列表页面
						});
					}else{
						//绑卡失败
						layer.alert(result.msg,{icon: 5,shift: 6,title:'提示'});
						//showError(result.msg);
					}
	            },
	            error:function(XmlHttpRequest,textStatus, errorThrown) {
	  			  	console.log(XmlHttpRequest.status);
	  			  	console.log(textStatus);
	  			  	showError(XmlHttpRequest.responseText+"绑卡失败！");
	  		  	}
	          });
	    }
	});
	
});