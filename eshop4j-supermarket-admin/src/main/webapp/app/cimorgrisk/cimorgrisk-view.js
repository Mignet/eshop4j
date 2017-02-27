$(function(){
	/*
	 * 判断表单的信息是否通过验证
	 */
	$("#orgDynamicForm").validate({
		//debug: true, //只验证不提交表单
	    //meta: "validate",//设置meta来封装验证规则
		//onkeyup:false, //是否在敲击键盘时验证
		ignore: "", //验证隐藏域
	    errorElement: 'span',
	    errorClass: 'help-block help-block-error',
	    focusInvalid: true, //当为false时，验证无效时，没有焦点响应
	    rules : {  
	    	orgTitle:{
				rangelength:[2,40] //输入长度必须介于 2 和 50 之间的字符串
			},
			orgDynamicUrl:{
				required:"#orgDynamicUrlRdo:checked" //id名称为orgDynamicUrlRdo的dom被选中时，则需要验证
			},
			orgContent:{
				required:"#orgContentRdo:checked" //id名称为orgContentRdo的dom被选中时，则需要验证
			}
	    },  
	    messages : { 
	    	orgTitle:{
				rangelength:"机构动态标题长度必须介于 {0} 到 {1} 位之间" //输入长度必须介于 6 和 18 之间的字符串
			}
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
	    	var id = $('input[name="id"]').val(); //主键id
	    	var orgNumber = $('#org_select option:selected').val(); //下拉框选中的值
	    	var orgName = $('#org_select option:selected').text(); //下拉框显示的文本
	    	var orgDynamicUrl = $('input[name="orgDynamicUrl"]').val(); //机构动态链接
	    	var orgTitle = $("#orgTitle").val();//机构动态标题
	    	var orgContent = UE.getEditor('orgContent').getContent();//机构动态正文信息
	    	var dynamicrdo = $("input[name='dynamicrdo']:checked").val(); //获取单选框选中的值
	    	$.ajax({
	        	data: { "id":id ,"orgNumber":orgNumber , "orgName":orgName  , "orgDynamicUrl":orgDynamicUrl , "orgTitle":orgTitle , "orgContent":orgContent , "dynamicrdo":dynamicrdo }, //表单对象数据转json字符串提交
	        	//contentType : 'application/json;charset=UTF-8', //设置请求头信息
	            dataType:'json',
	            type:'post',
	            url : form.action,
	            success: function (result) {
	            	if(result.isFlag){
	            		//新增成功
						layer.alert(result.msg,{icon: 6,shift: 4,title:'提示'},function(index){
							layer.close(index);
							$.switchPage("合作机构动态","rest/cim/cimorgdynamic/list"); //跳到机构列表页面
						});
					}else{
						//新增失败
						layer.alert(result.msg,{icon: 5,shift: 6,title:'提示'});
						//showError(result.msg);
					}
	            },
	            error:function(XmlHttpRequest,textStatus, errorThrown) {
	  			  	console.log(XmlHttpRequest.status);
	  			  	console.log(textStatus);
	  			  	showError(XmlHttpRequest.responseText+"新增机构动态信息失败！");
	  		  	}
	          });
	    }
	});
	
	
	 /**
	  * 动态链接选项
	  * 单选框点击事件  匹配第一个单选框
	  */
   $("input:radio[name='dynamicrdo']:eq(0)").click(function(){
	  
	   $("#dynamicUrlDiv").show("fast"); //显示动态链接框
	  // $("input[name='orgDynamicUrl']").attr("disabled",false); //启用 动态链接框
	   $("#orgContentDiv").hide("fast"); //隐藏富文本框
	  // UE.getEditor('orgContent').setDisabled();	//禁用
	  // UE.getEditor('orgContent').setContent(""); //清空富文本框内容
   });
   
   	/**
   	 * 富文本选项
	 * 单选框点击事件  匹配第二个单选框
	 */
   $("input:radio[name='dynamicrdo']:eq(1)").click(function(){
	   
		$("#orgContentDiv").show("fast"); // 显示富文本框
		//UE.getEditor('orgContent').setEnabled();	 //启用富文本框
		$("#dynamicUrlDiv").hide("fast"); //隐藏动态链接框
	    //$("input[name='orgDynamicUrl']").attr("disabled", true); //禁用
	    //$("input[name='orgDynamicUrl']").val(""); //清空动态链接
	  });
   
   /*监听 动态链接选项 被选中回显处理*/
	if($("#checkedRdo").val() != ""){
		//动态链接选项 被选中
		//alert("动态链接选项 被选中！");
		$("#orgDynamicUrlRdo").click();//触发点击事件
	}else{
		//富文本选项 被选中
		//alert("富文本选项 被选中！");
		$("#orgContentRdo").click();//触发点击事件
	}
   
});