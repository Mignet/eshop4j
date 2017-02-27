$(document).ready(function() {
	
   var table = $('#dtable').DataTable({
    	//dom: "Bfrtip",
    	"dom" : '<"J_search_toolbar">frtip', //自定义工具类
        "processing": true,
        "serverSide": true,
        "searching": false, //关闭本地搜索
        "language": {
        	select: {
                rows: {
                    _: "已选择 %d 行",
                    1: "已选择 1 行"
                }
            },
        	"sProcessing":   "处理中...",
        	"sLengthMenu":   "显示 _MENU_ 项结果",
        	"sZeroRecords":  "没有匹配结果",
        	"sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        	"sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
        	"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        	"sInfoPostFix":  "",
        	"sSearch":       "机构名称搜索:",
        	"sUrl":          "",
        	"sEmptyTable":     "表中数据为空",
        	"sLoadingRecords": "载入中...",
        	"sInfoThousands":  ",",
        	"oPaginate": {
        		"sFirst":    "首页",
        		"sPrevious": "上页",
        		"sNext":     "下页",
        		"sLast":     "末页"
        	},
        	"oAria": {
        		"sSortAscending":  ": 以升序排列此列",
        		"sSortDescending": ": 以降序排列此列"
        	}
        },
        "ajax": {
            "url": "/rest/cim/cimorginfo/list",
            "type": "POST",
            "data":function(d){
            	d.orgName = $('.J_search_toolbar input[name=orgname]').val(); //查询条件
            	return {'_dt_json':JSON.stringify(d)};//传递对象太多，json化
            	}
        },
        "order": [[ 3, 'desc' ]], //平台列表信息 按创建时间 降序排列
        "columns": [
            //{ "data": "id","orderable": false },
            { "data": "orgNumber","orderable": false },
            { "data": "name","orderable": false },
            //{ "data": "orgAccount","orderable": false },
            { "data": "capital","orderable": false },
            //{ "data": "upTime","orderable": false },
            { "data": "createTime","orderable": false },
            //{ "data": "city","orderable": false },
            //{ "data": "representative","orderable": false },
            //{ "data": "contact","orderable": false },
            
            { "data": "grade" ,"render": function ( data, type, row ) {//1:B,2:BB,3:BBB,4:A,5:AA,6:AAA'
           	   if(data=="1")
           		   return "B";
           	   else if(data=="2")
           		   return "BB";
	           else if(data=="3")
	        	   return "BBB";
	           else if(data=="4")
	        	   return "A";
	           else if(data=="5")
	        	   return "AA";
	           else if(data=="6")
	           	   return "AAA";
	           else
	           	   return "未定义";
              }},
            { "data": "recommend" ,"render": function ( data, type, row ) {
             	   if(data=="1")
             		   return "推荐";
             	   else if(data=="0")
             		   return "不推荐";
             	   else
             		   return null;
             }},
            { "data": "homepageSort" },
            { "data": "top" },
            { "data": "status" ,"render": function ( data, type, row ) {
           	   if(data=="1")
           		   return "合作中";
           	   else if(data=="0")
           		   return "合作结束";
           	   else
           		   return "待上线";
              }},
              { "data": "orgGrayStatus" ,"render": function ( data, type, row ) {
              	   if(data=="1")
              		   return "开启";
              	   else if(data=="0")
              		   return "关闭";
	               else
	           		   return null;
              }},
              { "data": "id" ,"orderable": false,"render": function ( data, type, row ) {
	              	//渲染 把数据源中的标题和url组成超链接 
            	    var a = '<a href="javacript:void(0);" class="btn btn-default btn-sm ui-redirect" data-title="收费模式" data-url="/rest/cim/cimorginfo/toOrgFeeView?orgNumber='+row.orgNumber+'">收费模式</a>&nbsp';
            	    var b = '<button class="btn btn-default btn-sm J_recommendInfo" data-toggle="modal" data-target="#recommendModal" data-url="/rest/cim/cimorginfo/toRecommend?id='+row.id+'">推荐</button>&nbsp';
            	    var c = '<a href="javacript:void(0);" class="btn btn-default btn-sm J_feeRatio" data-orgNumber="'+row.orgNumber+'">佣金</a>&nbsp';
	              	var d = '<a href="javacript:void(0);" class="btn btn-default btn-sm ui-redirect" data-title="机构详情" data-url="/rest/cim/cimorginfo/toDetail?orgNumber='+row.orgNumber+'">详情</a>&nbsp';
	  	            var e = '<a href="javacript:void(0);" class="btn btn-sm btn-default btn-icon ui-redirect" data-title="机构编辑" data-url="/rest/cim/cimorginfo/toEdit?orgNumber='+row.orgNumber+'"><i class="fa fa-edit"></i>编辑</a>';
	                return a+b+c+d+e;
               }}
			    
        ],
        select: true
    });
    
  
    //包裹查询工具栏
    $(".J_search_toolbar").html($("#template-search").html());
    
    //查询
	$("a.J_search").click(function() {
		table.draw(); //重绘表格。执行比如添加、删除、改变排序、筛选或者分页这些操作时  会重新排序和过滤
	});
	
	
	//var $modal = $('#publicModal'); //model.jsp公共模态框页面
	
	/**
	 * 佣金设置
	 */
    $("#dtable").on("click",".J_feeRatio",function(){
    	//var orgNum = $(event.target).attr("data-orgNumber");
    	var orgNumber = $(this).attr("data-orgNumber");
		$.ajax({
			url : "/rest/cim/cimorgfeetimetask/toOrgFeeRatioView?orgNumber="+orgNumber,
			type : 'GET',
			dataType: 'html',
			success : function(data) {
				//$modal.find(".modal-footer").hide();
				//$modal.find(".modal-title").html("发放红包"); //模态框标题设置
				//$modal.find(".modal-body").html(data);
				//$modal.modal('show');
				$("#orgFeeRatioModal").html(data);
				$("#orgFeeRatioModal").modal('show');
			}
		});
    });
    
	//推荐信息查询
	$("#dtable").off('click','.J_recommendInfo').on('click','.J_recommendInfo',function(event){
		//layer.msg('推荐',{time: 1000,icon: 0});
		var recommendurl = $(this).attr("data-url"); //查询url
		$.ajax({
			url : recommendurl,
			type : 'get',
			dataType:'json',
			success : function(result) {
				//layer.msg('测试一下aa',{time: 1000,icon: 0});
				$("#orgNumber").val(result.data.orgNumber); //设置隐藏域
				$("#recommendSort").val(result.data.homepageSort); //推荐排名
				$("#orgListSort").val(result.data.top); //列表排名
				var recommend =result.data.recommend; //0-不推荐、1-推荐
				if(recommend == 1){
					$("#recommend").attr("checked","checked");
					$("#setSort").show();
				}else if(recommend == 0){
					$("#norecommend").attr("checked","checked");
					$("#setSort").hide();
				}
				
			},
		  error:function(XmlHttpRequest,textStatus, errorThrown)
		  {
			  console.log(XmlHttpRequest.status);
			  console.log(textStatus);
			  showError(XmlHttpRequest.responseText);
		  }
		});
	});
	
	
	 //单选框点击事件  匹配第一个单选框
    $("input:radio[name='recommend']:eq(0)").click(function(){
    	$("#setSort").show(); //显示推荐排名文本框
    });
    
    //单选框点击事件  匹配第二个单选框
    $("input:radio[name='recommend']:eq(1)").click(function(){
    	$("#setSort").hide(); //隐藏推荐排名文本框
    	$("#recommendSort").val("");
    	//validator.resetForm();
		//$('.form-group').removeClass('has-error');
    });

	//重置/取消
	$("#reset").click(function(){
			validator.resetForm();
			//clear error msg
			$('.form-group').removeClass('has-error');
	  });
	
	//监听modal的hidden 清空modal旧数据
	$("#recommendModal").on("hidden.bs.modal", function() {
		//console.info('推荐信息模态框关闭重置模态框..');
		validator.resetForm();
		$('.form-group').removeClass('has-error');
		$(this).removeData("bs.modal");
	});
	
	
	
	/**
	 * 校验机构列表是否存在此排名
	 */
	jQuery.validator.addMethod("checkOrgListSort",function(value,element){
	    var top = value; //要验证的值
	   // var res;
	    $.ajax({
	        type:"GET",
	        async:false, 
	        url:"/rest/cim/cimorginfo/checkOrgListSort",
	        data:"top="+top+"&orgNumber="+$("#orgNumber").val(),
	        success:function(data){
	        	console.info(data);
	            if(data.isFlag){ //此机构排名已经存在
	                res = false;
	            }else{
	                res = true;
	            }
	        }
	    });
	    return res;
	},"此机构列表排名已存在,排名不能与其他机构重复！");

	/**
	 * 校验机构首页推荐是否存在此排名
	 */
	jQuery.validator.addMethod("checkOrgHomePageSort",function(value,element){
	    var homepageSort = value; //要验证的值
	   // var res;
	    $.ajax({
	        type:"GET",
	        async:false, 
	        url:"/rest/cim/cimorginfo/checkOrgHomePageSort",
	        data:"homepageSort="+homepageSort+"&orgNumber="+$("#orgNumber").val(),
	        success:function(data){
	        	console.info(data);
	            if(data.isFlag){ //此机构排名已经存在
	                res = false;
	            }else{
	                res = true;
	            }
	        }
	    });
	    return res;
	},"此机构首页推荐排名已存在,不能与其他机构重复！");

	

	//判断表单的信息是否通过验证
	validator = $("#myForm").validate({
		//debug: true,//只验证不提交表单
	    //meta: "validate",//设置meta来封装验证规则
		//onkeyup:false, //是否在敲击键盘时验证
	    errorElement: 'span',
	    errorClass: 'help-block help-block-error',
	    focusInvalid: false, //当为false时，验证无效时，没有焦点响应
	    rules : {  
	    	homepageSort : {  
	    		required: true,
	    		checkOrgHomePageSort : true,
	    		number : true
            },  
            top : {  
            	//required: true,
            	checkOrgListSort : true,
            	number : true
            }
        },  
        messages : {  
        	homepageSort : {  
                required : "首页排名不能为空！",
                number : "请输入合法的数字(含负数)！"
            },  
            top : {  
                //required : "列表排名不能为空！",
                number : "请输入合法的数字(含负数)！"
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
	        $("#recommendModal").modal("hide"); //隐藏模态框
	        $.ajax({
	        	//data:$(form).toFormJSON(), //表单对象数据转json字符串提交
	        	//contentType : 'application/json;charset=UTF-8', //设置请求头信息
	        	data:$(form).serialize(),
	        	dataType:'json',
	            type:'post',
	            url : '/rest/cim/cimorginfo/updateOrgRecommendInfo',
	            success: function (data) {
	                if (data.isFlag) {
	                    //保存成功  1.关闭弹出层，2.刷新表格数据
	                	//layer.msg(data.message,{time: 1000,icon: 0});
	                    showTips(data.msg);
	                    table.draw();//刷新表格数据
	                } else {
	                    showError(data.msg);
	                }
	            },
	            error:function(XmlHttpRequest,textStatus, errorThrown) {
	  			  	console.log(XmlHttpRequest.status);
	  			  	console.log(textStatus);
	  			  	showError(XmlHttpRequest.responseText+"推荐机构失败！");
	  		  	}
	          });
	    }
	});
    
	
});



