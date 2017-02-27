$(document).ready(function() {
    var shiro_admin = "disabled=true";
    if($('#shiro_admin')){
    	shiro_admin = "";
    }
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
            "url": "/rest/investor/queryInvestorBindPlatformList",
            "type": "POST",
            "data":function(d){
            	d.mobile = $('.J_search_toolbar input[name=mobile]').val(); //查询条件
            	return {'_dt_json':JSON.stringify(d)};//传递对象太多，json化
            	}
        },
        //"order": [[ 4, 'desc' ]], // 按第五列 降序排列
        "order": '', //不排序
        "columns": [
            { "data": "orgName","orderable": false },
            { "data": "orgNumber","orderable": false },
            { "data": "orgAccount","orderable": false },
            { "data": "isNewUser","orderable": false ,"render": function ( data, type, row ) {//1:B,2:BB,3:BBB,4:A,5:AA,6:AAA'
           	   if(data == 1)
           		   return "是";
           	   else if(data == 0)
           		   return "否";
	           else
	      		   return null;
              }},
            { "data": "isInvested" ,"orderable": false ,"render": function ( data, type, row ) {
             	   if(data == 1)
             		   return "是";
             	   else if(data == 0)
             		   return "否";
             	   else
             		   return null;
             	}},
             { "data": "orgAccountType" ,"orderable": false ,"render": function ( data, type, row ) {
           	   if(data == 1)
           		   return "微信账号";
           	   else if(data == 2)
           		   return "第三方web账户";
           	   else
           		   return null;
             }},
            
            { "data": "bindDate","orderable": false }
			    
        ],
        select: true
    });
    
  
    //包裹查询工具栏
    $(".J_search_toolbar").html($("#template-search").html());
    
    //查询
	$("a.J_search").click(function() {
		table.draw(); //重绘表格。执行比如添加、删除、改变排序、筛选或者分页这些操作时  会重新排序和过滤
	});
	
	
    
});

