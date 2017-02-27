$(function(){
	
	$("#cpaMenu input").attr("disabled",true);
	/**
	 * 判断表单的信息是否通过验证
	 */
	var validator = $("#orgFeeForm").validate({
		//debug: true, //只验证不提交表单
	    //meta: "validate",//设置meta来封装验证规则
		//onkeyup:false, //是否在敲击键盘时验证
		ignore: "", //验证隐藏域
	    errorElement: 'span',
	    errorClass: 'help-block help-block-error',
	    focusInvalid: true, //当为false时，验证无效时，没有焦点响应
	    rules : {  
	    	cpaFeeType:{
				cboListChecked:true //复选框验证
			},
			cpsFeeType:{
				cboListChecked:true //复选框
			},
			cpaFeeAttr:{
				required: true
			},
			fixedMoney:{
				required:"#fixed:checked", //id名称为fixed的dom被选中时，则需要验证
				number:true, //必须输入数字包含负数,小数
		    	min:0	
			},
			fixedMoneyRatio:{
				required:"#propertion:checked",
				number:true, //必须输入数字包含负数,小数
				//digits:true, //必须输入整数 包括0
				min:0
			},
			orgAmountLimit:{
				//required: true,
				//min:1,
				digits:true
			},
			orgInvestdeadlineLimit:{
				//required: true,
				//min:1,
				digits:true //必须输入整数 包括0
			}
	    	
	    },  
	    messages : {
	    	//feeType:{cboListChecked:"请至少选中一个！"}
	    	cpaFeeAttr:{
				required: "cpa收费方式请选中一项！"
			},
			fixedMoney:{
				required:"费用不能为空",
				number:"请输入合法的数字",
		    	min:"输入值不能小于{0}"	
			},
			fixedMoneyRatio:{
				required:"首投金额比例不能为空",
				number:"请输入合法的数字",
				min:"输入值不能小于{0}"	
			},
			orgAmountLimit:{
				digits:"必须输入整数"
			},
			orgInvestdeadlineLimit:{
				digits:"必须输入整数"
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
	    errorPlacement: function (error, element) { //指定错误信息位置
	        //element.parent('div').append(error);
	        if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
	            var ename = element.attr('name'); //获取元素的name属性
	            error.appendTo(element.parent()); //将错误信息添加当前元素的父结点后面
	            //error.insertAfter(element);
	          } else {
	            element.parent('div').append(error);
	          }
	    },
	    submitHandler: function (form) {
	    	//console.info($(form).toFormJSON());
	    	$.ajax({
	        	data:$(form).toFormJSON(), //表单对象数据转json字符串提交
	        	contentType : 'application/json;charset=UTF-8', //设置请求头信息
	            dataType:'json',
	            type:'post',
	            url : form.action,
	            success: function (result) {
	            	if(result.isFlag){
	            		//新增成功
						layer.alert(result.msg,{icon: 6,shift: 4,title:'提示'},function(index){
							layer.close(index);
							$.switchPage("合作机构列表","rest/cim/cimorginfo/list"); //跳到机构列表页面
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
	  			  	showError(XmlHttpRequest.responseText+"更新机构收费模式失败！");
	  		  	}
	          });
	    }
	});
	
	
	/**
	 * cpa复选框是否被选中
	 * alert($(this).attr("checked")); //checked undefined
	 * alert(this.checked); //true false
	 */
	$("#cpacbo").click(function(){
		if(this.checked){
			$("#cpsFeeType-error").closest('.form-group').removeClass('has-error'); //删除验证错误样式
			$("#cpsFeeType-error").remove(); //移除jqueryValidate span错误提示标签
	        //当前为选中状态
			$("#cpaMenu").show("fast"); //显示cpa选项菜单
			$("#cpaMenu input").attr("disabled",false); //启用cpa下所有input标签
	    }else{
	        //当前为不选中状态
	    	$("#cpaMenu").hide("fast"); //隐藏cpa选项菜单
	    	$("#cpaMenu input").attr("disabled",true); //禁用cpa下所有input标签
	    	//$("#cpaMenu input[name='cpaFeeAttr']").attr("checked",false); //cpa下所有单选框不选中
	    	//清空所有文本框
	    }
		
	});
	
	/**
	 * cps复选框被选中
	 */
	$("#cpscbo").click(function(){
		if(this.checked){
			$("#cpaFeeType-error").closest('.form-group').removeClass('has-error'); //删除验证错误样式
			$("#cpaFeeType-error").remove(); //移除jqueryValidate span错误提示标签
	        //当前为选中状态
			$("#cpsMenu").show("fast"); //显示cps选项菜单
			$("#cpsMenu input").attr("disabled",false); //启用cps下所有input标签
	    }else{
	        //当前为不选中状态
	    	$("#cpsMenu").hide("fast"); //隐藏cps选项菜单
	    	$("#cpsMenu input").attr("disabled",true); //禁用cps下所有input标签
	    	//覆盖子选项 恢复默认设置
	    }
	});
	
	 /**
	  * cpa单选框点击事件  匹配第一个单选框
	  */
    $("input:radio[name='cpaFeeAttr']:eq(0)").click(function(){
    	$("#fixedContent").show("fast"); //显示新投资人固定费用文本框
    	$("input[name='fixedMoney']").attr("disabled",false); //启用投资人固定费用文本框
    	
    	$("#firstInvestTab").hide("fast"); //隐藏首投金额表格div
    	//$("#firstInvestTable input").val(""); //清空table下所有的input元素的值
    	$("#firstInvestTable input").attr("disabled", true); //禁用table下所有的input元素
    	
    	$("#propertionContent").hide("fast"); //隐藏首投金额比例div
    	//$("input[name='fixedMoneyRatio']").val(""); //清空首投金额比例文本框
    	$("input[name='fixedMoneyRatio']").attr("disabled",true); //禁用首投金额比例文本框
    	
    	//validator.resetForm();
    });
    
    /**
     * cpa单选框点击事件  匹配第二个单选框
     */
    $("input:radio[name='cpaFeeAttr']:eq(1)").click(function(){
    	$("#propertionContent").show("fast"); //显示首投金额比例文本框
    	$("input[name='fixedMoneyRatio']").attr("disabled",false); //启用首投金额比例文本框
    	
    	$("#fixedContent").hide("fast"); //隐藏新投资人固定费用文本框
    	//$("input[name='fixedMoney']").val(""); //清空投资人固定费用文本框
    	$("input[name='fixedMoney']").attr("disabled",true); //禁用投资人固定费用文本框
    	
    	$("#firstInvestTab").hide("fast"); //隐藏首投金额表格div
    	//$("#firstInvestTable input").val(""); //清空table下所有的input元素的值
    	$("#firstInvestTable input").attr("disabled", true); //禁用table下所有的input元素
    });
    
    /**
     * cpa单选框点击事件  匹配第三个单选框
     */
    $("input:radio[name='cpaFeeAttr']:eq(2)").click(function(){
    	$("#firstInvestTab").show("fast"); //显示首投金额表格
    	$("#firstInvestTable input").attr("disabled", false); //启用table下所有的input元素
    	
    	$("#fixedContent").hide("fast"); //隐藏新投资人固定费用文本框
    	//$("input[name='fixedMoney']").val(""); //清空投资人固定费用文本框
    	$("input[name='fixedMoney']").attr("disabled",true); //禁用投资人固定费用文本框
    	
    	$("#propertionContent").hide("fast"); //隐藏首投金额比例div
    	//$("input[name='fixedMoneyRatio']").val(""); //清空首投金额比例文本框
    	$("input[name='fixedMoneyRatio']").attr("disabled",true); //禁用首投金额比例文本框
    	
    	
       // $("input:hidden[name='id']").attr("disabled",true); //禁用此隐藏域
    });
    
    /**
     * cps单选框点击事件  匹配第一个单选框
     */
    $("input:radio[name='cpsFeeAttr']:eq(0)").click(function(){
    	$("#productDeadlineTab").show("fast"); //显示首投金额表格
    	$("#productDeadlineTable input").attr("disabled", false); //启用table下所有的input元素
    	
    	$("#monthSaleTotalTab").hide("fast"); //隐藏首投金额表格div
    	//$("#monthSaleTotalTable input").val(""); //清空table下所有的input元素的值
    	$("#monthSaleTotalTable input").attr("disabled", true); //禁用table下所有的input元素
    });
    
    /**
     * cps单选框点击事件  匹配第二个单选框
     */
    $("input:radio[name='cpsFeeAttr']:eq(1)").click(function(){
    	$("#monthSaleTotalTab").show("fast"); //显示首投金额表格
    	$("#monthSaleTotalTable input").attr("disabled", false); //启用table下所有的input元素
    	
    	$("#productDeadlineTab").hide("fast"); //隐藏首投金额表格div
    	//$("#productDeadlineTable input").val(""); //清空table下所有的input元素的值
    	$("#productDeadlineTable input").attr("disabled", true); //禁用table下所有的input元素
    });
    
    
    /*监听 cpa复选框 被选中回显处理*/
	if($("#cpaFeeTypeCode").val() == 'cpa'){
		//alert("cpa被选中！");
		//$("#cpacbo").trigger('click');//自动触发点击事件
		$("#cpacbo").click();//自动触发点击事件
	}
	
	 /*监听 cps复选框 被选中回显处理*/
	if($("#cpsFeeTypeCode").val() == 'cps'){
		//alert("cps被选中！");
		//$("#cpacbo").trigger('click');//自动触发点击事件
		$("#cpscbo").click();//自动触发点击事件
	}
	
	
	/*监听 cpa下新用户固定首投单选框 被选中回显处理*/
	if($("#cpaFeeTypeAttr").val() == 'fixed'){
		//alert("cpa下新用户固定首投单选框被选中！");
		$("#fixed").click();//自动触发点击事件
	}
	
	/*监听 cpa下首投比例单选框 被选中回显处理*/
	if($("#cpaFeeTypeAttr").val() == 'propertion'){
		//alert("cpa下首投比例单选框被选中！");
		$("#propertion").click();//自动触发点击事件
	}
	
	/*监听 cpa下首投区间单选框 被选中回显处理*/
	if($("#cpaFeeTypeAttr").val() == 'float_fixed'){
		//alert("cpa下首投区间单选框被选中！");
		$("#float_fixed").click();//自动触发点击事件
	}
	
	
	/*监听 cps下产品期限单选框 被选中回显处理*/
	if($("#cpsFeeTypeAttr").val() == 'year_propertion'){
		//alert("cps下产品期限单选框被选中！");
		$("#year_propertion").click();//自动触发点击事件
		//$("#cpaMenu input").attr("disabled",true); //禁用cpa下所有input标签
		//validator.resetForm();
	}
	
	/*监听 cps月销售总额单选框 被选中回显处理*/
	if($("#cpsFeeTypeAttr").val() == 'month_amount_propertion'){
		//alert("cps下月销售总额单选框被选中！");
		$("#month_amount_propertion").click();//自动触发点击事件
		//$("#cpaMenu input").attr("disabled",true); //禁用cpa下所有input标签
		//validator.resetForm();
	}
	
});

	/**
	 * 收费方式复选框自定义验证方法 
	 */
	$.validator.addMethod('cboListChecked', function(value, element) { 
	    var checkedCount = 0; 
	    /*
	      $("input[name=feeType]").each(function() {  
	    	  if ($(this).attr('checked')) { 
	    		  checkedCount++; 
	    		 }  
	       });
	      */
	    if($("#cpacbo").attr('checked') || $("#cpscbo").attr('checked')){
	    	checkedCount++;
	    }
	   
	    return checkedCount > 0; 
	     
	},"机构收费方式请选中至少一项"); 

//判断是否至少选中一个复选框
//$("input:checkbox[name='feecbo']:checked").length > 0
	
	/**
	 * cpa 添加首投金额区间
	 */
	function addFirstInvestRow(addBtn){
		//$('#firstInvestTable').find('tbody').append("<tr><td>333</td><td>444</td></tr>");
		var firstInvest_index = parseInt($(addBtn).attr("data-index")); //指定首投金额区间行下标 默认：NaN 
		if(firstInvest_index >= 100){
			firstInvest_index = firstInvest_index + 1;
		}else{
			firstInvest_index = 100; //首投金额区间行下标默认从3开始
		}
		
		$(addBtn).attr("data-index",firstInvest_index); //设置首投金额区间行下标
		
		var $firtbody = $("#firstInvestTable tbody"); //获取首投金额的tbody
		
		$firtbody.append(appendFirstInvestHtml(firstInvest_index)); //拼接 HTML
	}
	
	/**
	 * cpa 首投金额区间 HTML拼接
	 */
	function appendFirstInvestHtml(index) {
		var content = [];
		content.push('<tr id="feeRecords-' + index + '">');
		content.push('<td class="col-md-8">');
		content.push('<div class="form-group col-md-4" style="margin: 10px 0px 0px 50px;">');
		content.push('<input type="text" class="form-control text-center" name="orgFeeRecords_feeRecords' + index + '.intervalMinVal" autocomplete="off" min="1" digits="true" required="required" />');
		content.push('</div>');
		content.push('<div class="col-md-1" style="margin-top: 18px;">&#126;</div>');
		content.push('<div class="form-group col-md-4" style="margin-top: 10px;">');
		content.push('<input type="text" class="form-control text-center" name="orgFeeRecords_feeRecords' + index + '.intervalMaxVal" autocomplete="off" min="1" digits="true" />');
		content.push('</div></td>');
		content.push('<td><div class="form-group" style="margin-top: 10px;">');
		content.push('<input type="text" class="form-control text-center" name="orgFeeRecords_feeRecords' + index + '.feeVal" autocomplete="off" min="0" number="true" required="required" />');
		content.push('</div></td>');
		content.push('<td><div style="margin-top: 10px;">');
		content.push('<button type="button" class="btn btn-default btn-danger" onclick="deleteFirstInvestRow(this);" data-feerowid="feeRecords-' + index + '"><i class="fa fa-trash-o"></i> 删除</button>');
		content.push('</div></td>');
		content.push('</tr>');
		return content.join(''); //指定使用的分隔符
    }
	
	/**
	 * cpa 删除首投金额区间
	 */
	function deleteFirstInvestRow(delBtn){
		//询问框
		layer.confirm('确定删除此行？', {btn: ['确定','取消'],title:'提示',shift: 6,icon: 3}, function(){
			
			var rowid = $(delBtn).attr("data-feerowid"); //获取团队成员的行id
			
		  	if(!$(delBtn).attr("data-feeid")){
		  		layer.msg('删除此条记录成功！', {icon: 1,time:500});
		  		//新录入的区间记录(未保存到数据库)，只移除此行
				$("#"+rowid).remove(); //删除此行
		  	}else{
		  		var firstInvestId = $(delBtn).attr("data-feeid"); //获取首投记录主键id
		  		//数据库中执行删除
		  		$.ajax({
		        	data:{"id":firstInvestId}, //表单对象数据转json字符串提交
		            dataType:'json',
		            type:'post',
		            url : '/rest/cim/cimorginfo/deleteOrgFee',
		            success: function (result) {
		            	if(result.isFlag){
		            		layer.msg('删除此条记录成功！', {icon: 1,time:500});
							$("#"+rowid).remove(); //删除此行
						}else{
							layer.msg('删除此条记录失败！', {icon: 2,time:500});
						}
		            },
		            error:function(XmlHttpRequest,textStatus, errorThrown) {
		  			  	console.log(XmlHttpRequest.status);
		  			  	console.log(textStatus);
		  			  	showError(XmlHttpRequest.responseText+"删除此条记录失败！");
		  		  	}
		            
		          });
		  	}
		});
	}
	
	
	/**
	 * cps 产品期限 HTML拼接
	 */
	function appendProductDeadHtml(index) {
		var content = [];
		content.push('<tr id="feeRecords-' + index + '">');
		content.push('<td class="col-md-8">');
		content.push('<div class="form-group col-md-4" style="margin: 10px 0px 0px 50px;">');
		content.push('<input type="text" class="form-control text-center" name="orgFeeRecords_feeRecords' + index + '.intervalMinVal" autocomplete="off" min="1" digits="true" required="required" />');
		content.push('</div>');
		content.push('<div class="col-md-1" style="margin-top: 18px;">&#126;</div>');
		content.push('<div class="form-group col-md-4" style="margin-top: 10px;">');
		content.push('<input type="text" class="form-control text-center" name="orgFeeRecords_feeRecords' + index + '.intervalMaxVal" autocomplete="off" min="1" digits="true" />');
		content.push('</div></td>');
		content.push('<td><div class="form-group" style="margin-top: 10px;">');
		content.push('<input type="text" class="form-control text-center" name="orgFeeRecords_feeRecords' + index + '.moneyRatio" autocomplete="off" min="0" number="true" required="required" />');
		content.push('</div></td>');
		content.push('<td><div style="margin-top: 10px;">');
		content.push('<button type="button" class="btn btn-default btn-danger" onclick="deleteProductDeadlineRow(this);" data-feerowid="feeRecords-' + index + '"><i class="fa fa-trash-o"></i> 删除</button>');
		content.push('</div></td>');
		content.push('</tr>');
		return content.join(''); //指定使用的分隔符
    }
	
	
	
	/**
	 * cps 增加产品期限区间
	 */
	function addProductDeadlineRow(addBtn){
		var index = parseInt($(addBtn).attr("data-index")); //指定产品期限区间行下标 默认：NaN 
		if(index >= 10000){
			index = index + 1;
		}else{
			index = 10000; //产品期限区间行下标默认从1004开始
		}
		
		$(addBtn).attr("data-index",index); //设置产品期限区间行下标
		
		var $firtbody = $("#productDeadlineTable tbody"); //获取产品期限区间的tbody
		
		$firtbody.append(appendProductDeadHtml(index)); //拼接 HTML
	}
	
	/**
	 * cps 删除产品期限区间
	 */
	function deleteProductDeadlineRow(delBtn){
		//询问框
		layer.confirm('确定删除此行？', {btn: ['确定','取消'],title:'提示',shift: 6,icon: 3}, function(){
			
			var rowid = $(delBtn).attr("data-feerowid"); //获取行id
			
		  	if(!$(delBtn).attr("data-feeid")){
		  		layer.msg('删除此条记录成功！', {icon: 1,time:500});
		  		//新录入的区间记录(未保存到数据库)，只移除此行
				$("#"+rowid).remove(); //删除此行
		  	}else{
		  		var feeProductId = $(delBtn).attr("data-feeid"); //获取首投记录主键id
		  		//数据库中执行删除
		  		$.ajax({
		        	data:{"id":feeProductId}, //表单对象数据转json字符串提交
		            dataType:'json',
		            type:'post',
		            url : '/rest/cim/cimorginfo/deleteOrgFee',
		            success: function (result) {
		            	if(result.isFlag){
		            		layer.msg('删除此条记录成功！', {icon: 1,time:500});
							$("#"+rowid).remove(); //删除此行
						}else{
							layer.msg('删除此条记录失败！', {icon: 2,time:500});
						}
		            },
		            error:function(XmlHttpRequest,textStatus, errorThrown) {
		  			  	console.log(XmlHttpRequest.status);
		  			  	console.log(textStatus);
		  			  	showError(XmlHttpRequest.responseText+"删除此条记录失败！");
		  		  	}
		            
		          });
		  	}
		});
	}
	
	
	/**
	 * cps 月销售总额 HTML拼接
	 */
	function appendMonthSaleHtml(index) {
		var content = [];
		content.push('<tr id="feeRecords-' + index + '">');
		content.push('<td class="col-md-8">');
		content.push('<div class="form-group col-md-4" style="margin: 10px 0px 0px 50px;">');
		content.push('<input type="text" class="form-control text-center" name="orgFeeRecords_feeRecords' + index + '.intervalMinVal" autocomplete="off" min="1" digits="true" required="required" />');
		content.push('</div>');
		content.push('<div class="col-md-1" style="margin-top: 18px;">&#126;</div>');
		content.push('<div class="form-group col-md-4" style="margin-top: 10px;">');
		content.push('<input type="text" class="form-control text-center" name="orgFeeRecords_feeRecords' + index + '.intervalMaxVal" autocomplete="off" min="1" digits="true" />');
		content.push('</div></td>');
		content.push('<td><div class="form-group" style="margin-top: 10px;">');
		content.push('<input type="text" class="form-control text-center" name="orgFeeRecords_feeRecords' + index + '.moneyRatio" autocomplete="off" min="0" number="true" required="required" />');
		content.push('</div></td>');
		content.push('<td><div style="margin-top: 10px;">');
		content.push('<button type="button" class="btn btn-default btn-danger" onclick="deleteMonthSaleTotalRow(this);" data-feerowid="feeRecords-' + index + '"><i class="fa fa-trash-o"></i> 删除</button>');
		content.push('</div></td>');
		content.push('</tr>');
		return content.join(''); //指定使用的分隔符
    }
	
	/**
	 * cps 增加月销售总额区间
	 */
	function addMonthSaleTotalRow(addBtn){
		var index = parseInt($(addBtn).attr("data-index")); //指定月销售总额区间行下标 默认：NaN 
		if(index >= 20000){
			index = index + 1;
		}else{
			index = 20000; //月销售总额区间行下标默认从1004开始
		}
		
		$(addBtn).attr("data-index",index); //设置月销售总额区间行下标
		
		var $firtbody = $("#monthSaleTotalTable tbody"); //获取月销售总额区间的tbody
		
		$firtbody.append(appendMonthSaleHtml(index)); //拼接 HTML
	}
	
	/**
	 * cps 删除月销售总额区间
	 */
	function deleteMonthSaleTotalRow(delBtn){
		//询问框
		layer.confirm('确定删除此行？', {btn: ['确定','取消'],title:'提示',shift: 6,icon: 3}, function(){
			
			var rowid = $(delBtn).attr("data-feerowid"); //获取行id
			
		  	if(!$(delBtn).attr("data-feeid")){
		  		layer.msg('删除此条记录成功！', {icon: 1,time:500});
		  		//新录入的区间记录(未保存到数据库)，只移除此行
				$("#"+rowid).remove(); //删除此行
		  	}else{
		  		var feeMonthSaleId = $(delBtn).attr("data-feeid"); //获取首投记录主键id
		  		//数据库中执行删除
		  		$.ajax({
		        	data:{"id":feeMonthSaleId}, //表单对象数据转json字符串提交
		            dataType:'json',
		            type:'post',
		            url : '/rest/cim/cimorginfo/deleteOrgFee',
		            success: function (result) {
		            	if(result.isFlag){
		            		layer.msg('删除此条记录成功！', {icon: 1,time:500});
							$("#"+rowid).remove(); //删除此行
						}else{
							layer.msg('删除此条记录失败！', {icon: 2,time:500});
						}
		            },
		            error:function(XmlHttpRequest,textStatus, errorThrown) {
		  			  	console.log(XmlHttpRequest.status);
		  			  	console.log(textStatus);
		  			  	showError(XmlHttpRequest.responseText+"删除此条记录失败！");
		  		  	}
		            
		          });
		  	}
		});
	}
	
