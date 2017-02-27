using(["messager","form"],function(){
	function closeWin(){
		var $window = self.parent.$("#proWin").window();
		$window.window('close');
	}
	//产品保存
	$("#productAddSave").bind('click',function(){
			$('#productAaddForm').form('submit', {
				   url: "rest/productos/add",
				   onSubmit: function(){
					  return checkForm();
				    },
				   success: function(result){
					   result = eval('(' + result + ')');					  
					   if(result.isFlag){					   
						   //关闭窗口,重新加载
						   $.messager.alert('提示',"添加成功",'info');
						   closeWin();
						   reFreshProList();
					   } else  {
						   $.messager.alert('提示',result.msg,'info');
					   }
				   },
				   onLoadError: function() {
		        	   alertMessage('提示',"保存失败",'info');
		           }
			});
	});
	//表单输入校验
	 function checkForm(){
		 if($("#listRecomendedStr").val().length>15){
			   $.messager.alert('提示','列表推荐语最多15个字符','info');
			   $("#listRecomendedStr").val("");
			   $("#listRecomendedStr").focus();
			   return false;
		   }
		 if($("#ransferProtocalName").val().length> 0){//收益转让协议名称不为空  链接地址必填
			if($("#ransferProtocalUrl").val().length<1){
				 $.messager.alert('提示','收益转让协议名称不为空  链接地址必填','info');
				   $("#ransferProtocalUrl").focus();
				   return false;
			}
			   
		 }
		 if($("#ransferProtocalUrl").val().length> 0){//收益转让协议名称不为空  链接地址必填
				if($("#ransferProtocalName").val().length<1){
					 $.messager.alert('提示','收益转让协议链接地址不为空  协议名称必填','info');
					   $("#ransferProtocalName").focus();
					   return false;
				}
				   
			}
		   //投资端 详情推荐语 前端没做 隐藏
		 
		   /*if($("#invDtlPageDes").val().length>15){
			   $.messager.alert('提示','投资端 详情推荐语最多15个字符','info');
			   $("#invDtlPageDes").focus();
			   return false;
		   }*/
		   //理财师端 详情推荐语 隐藏
		   /*if($("#lcsDtlPageDes").val().length>15){
			   $.messager.alert('提示','理财师端 详情推荐语最多15个字符','info');
			   $("#lcsDtlPageDes").focus();
			   return false;
		   }*/
		 return $("#productAaddForm").form('validate');
		  // return true;
	 }
	
	//产品取消
	$("#productAddcacl").bind('click',function(){
		closeWin();
	});
	//列表推荐
	$("input[name='isListRecommended']").click(function(){
		if($("input[name='isListRecommended']:checked").val() =='1'){
			$('#ListRecomentDiv').css('display','inline');
			
		}else{
			$('#ListRecomentDiv').css('display','none');
		}
	});
	//添加 产品说明
	$("#dtlPageAddBtn").click(function(){
		if($("#productIllustrationUrl").val().length>0){
			$("#dtlPageDiv").html($("<a>").text($("#productName").val()+" 详情").attr({target:"_blank",href:"http://minvestor.xiaoniuapp.com/pages/agreement/"+$("#productIllustrationUrl").val()})[0].outerHTML);
		}else{
			$("#dtlPageDiv").empty();
		}
	});
	
	//添加安全保障页
	$("#securePageAddBtn").click(function(){
		if($("#securityGuaranteeUrl").val().length>0){
			$("#securePageDiv").html($("<a>").text($("#productName").val()+" 安全保障").attr({target:"_blank",href:"http://minvestor.xiaoniuapp.com/pages/agreement/"+$("#securityGuaranteeUrl").val()})[0].outerHTML);
		}else{
			$("#securePageDiv").empty();
		}
	});
	//添加  产品购买协议
	$("#productProtocalAddBtn").click(function(){
		if($("#productProtocalId").val().length>0){
			$("#productProtocalDiv").html($("<a>").text("产品购买协议").attr({target:"_blank",href:$("#productProtocalId").val()})[0].outerHTML);
		}else{
			$("#productProtocalDiv").empty();
		}
	});
	
	//添加 收益转让协议
	$("#ransferProtocalUrlAddBtn").click(function(){
		if($("#ransferProtocalUrl").val().length>0){
			$("#ransferProtocalUrlDiv").html($("<a>").text("收益转让协议").attr({target:"_blank",href:$("#ransferProtocalUrl").val()})[0].outerHTML);
		}else{
			$("#ransferProtocalUrlDiv").empty();
		}
	});
	
	//开售类型
	$("input[name='beginSaleType']").click(function(){
		if($("input[name='beginSaleType']:checked").val() =='1'){//即时
			$('#fixSaleTimeDiv').css('display','none');
			$('#preSaleTimeDiv').css('display','none');
			$("#fixSaleTimeInput").attr("disabled","disabled");
			$("#preSaleTimeInput").attr("disabled","disabled");
		}else if($("input[name='beginSaleType']:checked").val() =='2'){//定时
			$('#fixSaleTimeDiv').css('display','inline');
			$('#preSaleTimeDiv').css('display','none');
			$("#preSaleTimeInput").attr("disabled","disabled");
			$("#fixSaleTimeInput").removeAttr("disabled");
			
		}else if($("input[name='beginSaleType']:checked").val() =='3'){//预售
			$('#preSaleTimeDiv').css('display','inline');
			$('#fixSaleTimeDiv').css('display','none');
			$("#fixSaleTimeInput").attr("disabled","disabled");
			$("#preSaleTimeInput").removeAttr("disabled");
		}
	});
	function reFreshProList(){
		 $('#proDg').datagrid({pageSize:20,url:"rest/product/prolist", queryParams:jsonFromt($('#proQueryForm').serializeArray())});
	}
	//修改保存
	$("#productUpdate").bind('click',function(){
			$('#productAaddForm').form('submit', {
				   url: "rest/productos/update",
				   onSubmit: function(){
					   return checkForm();
				    },
				   success: function(result){
					   result = eval('(' + result + ')');
					   if(result.isFlag){				   
						   //关闭窗口,重新加载
						   $.messager.alert('提示',result.msg,'info');
						   closeWin();
						   reFreshProList();
//						   $('#proDg').datagrid('reload'); 
					   } else  {
						   $.messager.alert('提示',result.msg,'info');
					   }
				   },
				   onLoadError: function() {
		        	   alertMessage('提示',"保存失败",'info');
		           }
			});
	});
	//编辑产品
	$("#productEdit").bind('click',function(){
		$("#productAaddForm").form('submit',{
			url: "rest/productos/edit",
			onSubmit: function(){
				return checkForm();
			    },
			success: function(result){
				   result = eval("(" + result + ")");
				   if(result.isFlag){				   
					   //关闭窗口,重新加载
					   $.messager.alert('提示',result.msg,'info');
					   closeWin();
					   reFreshProList();
				   } else  {
					   $.messager.alert('提示',result.msg,'info');
				   }
			   },
			   onLoadError: function() {
	        	   $.messager.alert('提示',"编辑失败,系统异常,请联系管理员",'error');
	           }
		});
	
});
	//校验产品名称是否已经存在
	$('#productName').blur(function () { 
		$.ajax({
		    type: "POST",
		    url: "rest/productos/validname",
		    data: "proName="+$("#productName").val(),
		    success: function(result){
		        if(result){
		        	$("#productName").val("");
		        	$("#proNameValidDiv").html("<font color='red'>产品名称已经存在</font>");
		        }else{
		        	$("#proNameValidDiv").empty();
		        }
		    }
		});	
	}); 
	
	//是否募集
	$("input[name='isCollect']").click(function(){
		if($("input[name='isCollect']:checked").val() =='2'){//募集
			//重新加载起息方式的下列框  
			 $("#interestWay").empty();
             $("#interestWay").append("<option value='3'>T+0</option>");
             $("#interestWay").append("<option value='4'>T+1</option>");
             //             
             $("#interestWaySpan").html("募集完成后");
             //
             $('#validBeginDateDiv').css('display','inline');
             $('#collectInterestWayDiv').css('display','inline');
			$('#raiseInfoDiv').css('display','block');
			
		}else{
			//重新加载起息方式的下列框
			 $("#interestWay").empty();
			 $("#interestWay").append("<option value='1'>T+0</option>");
             $("#interestWay").append("<option value='2'>T+1</option>");
			//
             $("#interestWaySpan").html("投资后");
             //
             $('#validBeginDateDiv').css('display','none');
             $('#collectInterestWayDiv').css('display','none');
             
			$('#raiseInfoDiv').css('display','none');
		}
	});
	//无募集期收益 清空募集期收益
	$("input[name='hasCollectRate']").click(function(){
		if($("input[name='hasCollectRate']:checked").val() =='0'){//募集期无收益
			$('#collectRate').val("");
		}
	});
	//无募集期佣金 清空募集期佣金
	$("input[name='hasCollectRatio']").click(function(){
		if($("input[name='hasCollectRatio']:checked").val() =='0'){//募集期无收益
			$('#collectRatio').val("");
		}
	});
	//固定日期起息和投资后后起息 互斥
	if($('#validBeginDate').length>0){
		$("input[name='interestWayType']").click(function(){
			if($("input[name='interestWayType']:checked").val() =='1'){//募集期无收益
				$('#validBeginDate').val("");
			}
		});
	}
	

	
});