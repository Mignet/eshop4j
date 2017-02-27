$(function(){
	
	/**
	 * 初始化bootstrapSwitch插件
	 */
	$(".bootstrapSwitch").bootstrapSwitch();
	var $switch = $('body').find('.bootstrapSwitch');
    if ($switch) {
    	$switch.each(function(index, element) {
    		initBootStrapSwitch($(element).attr('id'),$(element).attr('data-name'));
      });
    }
	
	
	//console.info(moment().format('YYYY-MM-DD HH:mm:ss')); //获取系统当前时间
	/**
	 * 初始化日期插件
	 */
	$('#upTime').daterangepicker({
		showDropdowns: true, //自定义可选择年、月
		singleDatePicker: true, //是否是单个时间选择器
		format: 'YYYY-MM-DD', //日期格式化
		startDate: '2015-01-01', // 默认起始时间
		maxDate: moment().format('YYYY-MM-DD')	//可选最迟时间
	},
	function(start, end, label) { //回调
		$("#upTime").closest('.form-group').removeClass('has-error'); //删除验证错误样式
		$("#upTime-error").remove(); //移除jqueryValidate span错误提示标签
	   // alert('A date range was chosen: ' +start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
	  }
	);
	/**
	 * 判断表单的信息是否通过验证
	 */
	$("#orgForm").validate({
		//debug: true, //只验证不提交表单
	    //meta: "validate",//设置meta来封装验证规则
		//onkeyup:false, //是否在敲击键盘时验证
		ignore: "", //验证隐藏域
	    errorElement: 'span',
	    errorClass: 'help-block help-block-error',
	    focusInvalid: true, //当为false时，验证无效时，没有焦点响应
	    rules : {  
	    	//capital:"required", //单个条件验证
	    	capital:{ //多条件验证
	    		//digits:true, //必须输入整数 包括0
	    		required:true,
	    		number:true, //必须输入数字包含负数,小数
	    		min:1 //输入值不能小于 1
			},
			orgFeeRatio:{
				required:true,
				number:true, //必须输入数字包含负数,小数
				min:0.1	
			},
			upTime:{
				//dateISO:true, //必须输入正确格式的日期（ISO），例如：2009-06-23 不验证日期合法性
				isDate:true,   //自定义日期验证 验证日期合法和格式2009-06-23
				required:true
			},
			orgAccount:{
				rangelength:[6,18] //输入长度必须介于 6 和 18 之间的字符串
			},
			orgPassword:{
				rangelength:[6,18] //输入长度必须介于 6 和 18 之间的字符串
			},
			orgMargin:{
	    		number:true, //必须输入数字包含负数,小数
	    		min:1 //输入值不能小于 1
			},
			diffFeeRatio:{
				number:true, //必须输入数字包含负数,小数
				min:0	
			}
	    	
	    },  
	    messages : { 
	    	/*
	    	capital:{
	    		number:"请输入合法的数字",
	    		min:"输入值不能小于1"
			}
	    	*/
	    	orgFeeRatio:{
				required:"理财师佣金率不能为空！",
				number:"请输入合法的数字",
				min:"输入值不能小于{0}"	
			},
			upTime:{
				required:"机构上线时间不能为空！"
			},
			orgAccount:{
				rangelength:"后台账户长度必须介于 {0} 到 {1} 位之间" //输入长度必须介于 6 和 18 之间的字符串
			},
			orgPassword:{
				rangelength:"登录密码长度必须介于 {0} 到 {1} 位之间" //输入长度必须介于 6 和 18 之间的字符串
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
	    	//console.info($(form).toFormJSON());
	    	$.ajax({
	        	data:$(form).toFormJSON(), //表单对象数据转json字符串提交
	        	contentType : 'application/json;charset=UTF-8', //设置请求头格式
	            dataType:'json',
	            type:'post',
	            url : form.action,
	            success: function (result) {
	            	if(result.isFlag){
	            		//编辑机构信息成功
						layer.alert(result.msg,{icon: 6,shift: 4,title:'提示'},function(index){
							layer.close(index);
							//debugger;
							$.switchPage("合作机构列表","rest/cim/cimorginfo/list"); //跳到机构列表页面
						});
					}else{
						//编辑机构信息失败
						layer.alert(result.msg,{icon: 5,shift: 6,title:'提示'});
						//showError(result.msg);
					}
	            },
	            error:function(XmlHttpRequest,textStatus, errorThrown) {
	  			  	console.log(XmlHttpRequest.status);
	  			  	console.log(textStatus);
	  			  	showError(XmlHttpRequest.responseText+"更新机构信息失败！");
	  		  	}
	          });
	    }
	});
	
	
	
});


	/**
	 * 初始化BootStrapSwitch插件
	 * @param id
	 * @param name
	 */
	function initBootStrapSwitch(id,name){
		$("#"+id).on('switch-change', function (e, data) {
			var $el = $(data.el); //获取复选框
			if(data.value == true){
				//$el.val(1); //是
				$('input[name="'+name+'"]').val(1);
			}else{
				//$el.val(0); //不是
				$('input[name="'+name+'"]').val(0);
			}
			//console.log($el[0]);
			
		});
	}
	

	/**
	 * 验证: isDate:true
	 * 可以验证 2011-07-07和2011/07/07两种短横线和斜杠格式的
	 */	
	jQuery.validator.addMethod("isDate", function(value, element){
		var ereg = /^(\d{1,4})(-|\/)(\d{1,2})(-|\/)(\d{1,2})$/;
		var r = value.match(ereg);
		if (r == null) {
			return false;
		}
		var d = new Date(r[1], r[3] - 1, r[5]);
		var result = (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[5]);
		return this.optional(element) || (result);
	}, "请输入正确的日期");
		
	/*	
	$("p").on("click", function(){
		alert( $(this).text() );
		});	
	*/
	
	/**
	 * 删除团队成员
	 */
	function deleteTeam(delBtn){
		//询问框
		layer.confirm('确定删除此成员？', {btn: ['确定','取消'],title:'提示',shift: 6,icon: 3}, function(){
		  
			var rowid = $(delBtn).attr("data-teamrowid"); //获取团队成员的行id
			
		  	if(!$(delBtn).attr("data-teamid")){
		  		layer.msg('删除此团队成员成功！', {icon: 1,time:500});
		  		//新录入的成员(未保存到数据库)，只移除此行
				$("#"+rowid).remove(); //删除此行
		  	}else{
		  		var teamid = $(delBtn).attr("data-teamid"); //获取团队成员主键id
		  		var orgNumber = $(delBtn).attr("data-orgNumber"); //获取机构编码
		  		//数据库中执行删除
		  		$.ajax({
		        	data:{"id":teamid,"orgNumber":orgNumber}, //表单对象数据转json字符串提交
		            dataType:'json',
		            type:'post',
		            url : '/rest/cim/cimorginfo/deleteTeam',
		            success: function (result) {
		            	if(result.isFlag){
		            		layer.msg('删除此团队成员成功！', {icon: 1,time:500});
		            		//删除团队成功
							$("#"+rowid).remove(); //删除此行
						}else{
							//删除团队成员失败
							layer.msg('删除此团队成员失败！', {icon: 2,time:500});
						}
		            },
		            error:function(XmlHttpRequest,textStatus, errorThrown) {
		  			  	console.log(XmlHttpRequest.status);
		  			  	console.log(textStatus);
		  			  	showError(XmlHttpRequest.responseText+"删除团队成员失败！");
		  		  	}
		            
		          });
		  	}
			
		});
		
	}
	
	
	
	/**
	 * 添加团队成员
	 */
	function addTeam(addBtn){
		var team_index = parseInt($(addBtn).attr("data-index")); //指定团队成员下标 默认：NaN 
		if(team_index >= 100){
			team_index = team_index + 1;
		}else{
			team_index = 100; //成员下标默认从100开始
		}
		
		$(addBtn).attr("data-index",team_index); //设置团队成员下标
		
		$.ajax({
		        //async :false,
		        url: '/rest/cim/cimorginfo/toTeamTemplate?teamindex='+team_index,
		        type: 'get',
		        dataType: 'html',
		        success:function(html){
					//截取html
					//var result =  html.substring(html.indexOf('<div class="form-group">'),html.lastIndexOf("</div>")+6);
		        	$("#teamList").append(html); //新增团队成员
		        	$("#templatejs").remove(); //删除初始化头像时的js标签
		        	//$("#team-3").focus(); //div获取焦点时 需设置tabindex="0"
		        }
		    });
		
	}
	