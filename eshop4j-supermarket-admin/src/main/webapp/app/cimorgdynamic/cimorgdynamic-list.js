 var table;
$(document).ready(function() {
		table = $('#dtable').DataTable({
    	"dom" : '<"J_search_toolbar">frtip', //自定义工具类
        "processing": true,
        "serverSide": true,
        "searching": false, //关闭本地搜索
        "select": true,
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
            "url": "/rest/cim/cimorgdynamic/list",
            "type": "POST",
            "data":function(data){
	            	data.orgName = $('#org_select option:selected').val(); //查询条件
	            	data.orgTitle = $('input[name="orgTitle"]').val(); //查询条件
	            	var startTime = $('input[name="startTime"]').val(); //日期查询条件
	        		var endTime = $('input[name="endTime"]').val(); //日期查询条件
	            	if(startTime != "" && endTime != ""){
	            		data.startTime = startTime //查询条件
	            		data.endTime = endTime; //查询条件
	            	}
	            	return {'_dt_json':JSON.stringify(data)};//传递对象太多，json化
            	}
        },
        "order": [[ 3, 'desc' ]], //平台列表信息 按创建时间 降序排列
        //"order": '', //不排序
        "columns": [
            { "data": "orgName","orderable": false },
            { "data": "orgTitle","orderable": false },
            { "data": "creator","orderable": false },
            { "data": "releaseTime"},
              
            { "data": "id" ,"orderable": false,"render": function ( data, type, row ) {
	              	//渲染 把数据源中的标题和url组成超链接 
            		var b = '<button class="btn btn-sm btn-default btn-icon ui-redirect" data-title="机构动态信息编辑" data-url="/rest/cim/cimorgdynamic/toEdit?id='+row.id+'"><i class="fa fa-edit"></i>编辑</button>&nbsp';
            	    var a = '<button class="btn btn-sm btn-default btn-danger" onclick="deleteDynamic('+row.id+');"><i class="fa fa-trash-o"></i> 删除</button>';
	                return b+a;
               }}
			    
        ]
    });
    
    //包裹查询工具栏
    $(".J_search_toolbar").html($("#template-search").html());
    
    //查询
	$(".J_search").click(function() {
		var startTime = $('input[name="startTime"]').val(); //日期查询条件
		var endTime = $('input[name="endTime"]').val(); //日期查询条件
		/**
		 * 非空验证
		 */
		/*
		if(startTime == "" || endTime == ""){
			layer.alert("其中一个日期不能为空",{icon: 3, title:'提示'});
			return;
		}
		*/
		/**
		 * 日期格式验证 yyyy-mm-dd
		 */
		
		if(startTime != "" || endTime != ""){
			var reg = /^(\d{4})-(\d{2})-(\d{2})$/;  //正则匹配
			
			if (!reg.test(startTime) && RegExp.$2 <= 12 && RegExp.$3 <= 31){  
				layer.alert("日期格式错误默认为yyyy-mm-dd",{icon: 3, title:'开始日期填错了！'});
				return;  
			} 
			
			if (!reg.test(endTime) && RegExp.$2 <= 12 && RegExp.$3 <= 31){  
				layer.alert("日期格式错误默认为yyyy-mm-dd",{icon: 3, title:'结束日期填错了！'});
				return;  
			}  
		}
		
		/**
		 * 日期选择范围验证
		 */
		var sTime = new Date(startTime);
		var eTime = new Date(endTime);
		if(sTime > eTime){
			layer.alert("亲，你是在玩我吗？",{icon: 3, title:'日期填错了！'});
			return;
		}
		
		table.draw(); //重绘表格。执行比如添加、删除、改变排序、筛选或者分页这些操作时  会重新排序和过滤
	});
	
	
	/**
	 * 初始化日期插件
	 */
	$('#startTime').daterangepicker({
		showDropdowns: true, //自定义可选择年、月
		singleDatePicker: true, //是否是单个时间选择器
		format: 'YYYY-MM-DD', //日期格式化
		startDate: moment().format('YYYY-MM-DD'), // 默认起始时间
		maxDate: moment().format('YYYY-MM-DD')	//可选最迟时间
	},
	function(start, end, label) { //回调
		$("#startTime").closest('.form-group').removeClass('has-error'); //删除验证错误样式
		$("#startTime-error").remove(); //移除jqueryValidate span错误提示标签
	   // alert('A date range was chosen: ' +start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
	  }
	);
	
	$('#endTime').daterangepicker({
		showDropdowns: true, //自定义可选择年、月
		singleDatePicker: true, //是否是单个时间选择器
		format: 'YYYY-MM-DD', //日期格式化
		startDate: moment().format('YYYY-MM-DD'), // 默认起始时间
		maxDate: moment().format('YYYY-MM-DD')	//可选最迟时间
	},
	function(start, end, label) { //回调
		$("#endTime").closest('.form-group').removeClass('has-error'); //删除验证错误样式
		$("#endTime-error").remove(); //移除jqueryValidate span错误提示标签
	   // alert('A date range was chosen: ' +start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
	  }
	);
	
	
});


/**
 * 判断输入框中输入的日期格式为yyyy-mm-dd和正确的日期  
 */
function IsDate(sm,mystring) {  
	var reg = /^(\d{4})-(\d{2})-(\d{2})$/;  
	var str = mystring;  
	var arr = reg.exec(str);  //返回一个数组，其中存放匹配的结果。如果未找到匹配，则返回值为 null。
	if (str=="") return true;  
	if (!reg.test(str) && RegExp.$2 <= 12 && RegExp.$3 <= 31){  
		alert("请保证"+sm+"中输入的日期格式为yyyy-mm-dd或正确的日期!");  
		return false;  
	}  
	return true;  
}  

/**
 * 删除机构动态
 */
function deleteDynamic(id){
	//询问框
	layer.confirm('确定删除此条动态？', {btn: ['确定','取消'],title:'提示',shift: 6,icon: 3}, function(){
	  		//数据库中执行删除
	  		$.ajax({
	        	data:{"id":id}, //表单对象数据转json字符串提交
	            dataType:'json',
	            type:'post',
	            url : '/rest/cim/cimorgdynamic/deleteDynamic',
	            success: function (result) {
	            	if(result.isFlag){
	            		layer.msg('删除此条动态成功！', {icon: 1,time:500});
	            		table.draw(); 
					}else{
						//删除团队成员失败
						layer.msg('删除此条动态失败！', {icon: 2,time:500});
					}
	            },
	            error:function(XmlHttpRequest,textStatus, errorThrown) {
	  			  	console.log(XmlHttpRequest.status);
	  			  	console.log(textStatus);
	  			  	showError(XmlHttpRequest.responseText+"删除此条动态失败！");
	  		  	}
	            
	          });
		
	});
	
}
