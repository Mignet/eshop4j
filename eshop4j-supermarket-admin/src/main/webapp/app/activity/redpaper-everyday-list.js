var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];

$(document).ready(function() {
    var shiro_admin = "disabled=true";
    if($('#shiro_admin')){
    	shiro_admin = "";
    }
    var table = $('#dtable').DataTable( {
    	width: 800,
    	dom: "Bfrtip",
    	"searching": false, //关闭本地搜索
    	"ordering": false, //关闭表格的排序功能
        "processing": true, //是否显示处理状态
        "serverSide": true, //是否开启服务器模式
         select: true,
        //客户端处理模式——此模式下如：过滤、分页、排序的处理都在浏览器中进行。
        //服务器端处理模式——此模式下如：过滤、分页、排序的处理都放在服务器端进行。

        "ajax": {
            //"url": $('#path').val()+"/rest/redpaper/redpaperList",
            "url": "./rest/redpaper/redpaperEveryDayList",
            "type": "POST",
            "data":function(requestParams){return {'dt_json':JSON.stringify(requestParams)};}//传递对象太多，json化
        },
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
        	"sSearch":       "搜索:",
        	"sUrl":          "",
        	"sEmptyTable":     "表中数据为空",
        	"sLoadingRecords": "载入中...",
        	"sInfoThousands":  ",",
        	"oPaginate": {
        		"sFirst":    "首页",
        		"sPrevious": "上页",
        		"sNext":     "下页",
        		"sLast":     "末页"
        	}
        },

        "columns": [
            { "data": "calDate"},
            { "data": "sendRedpacketlcsCounts"},
            { "data": "lcsSendRedpacketCounts"},
            { "data": "lcsSendRedpacketCustomerCounts"},
            { "data": "lcsSendRedpacketAmount"},
            { "data": "useRedpacketCustomerCounts"},
            { "data": "redpacketUseCounts"},
            { "data": "redpacketUseAmount"}
        ],
        select: true,
        buttons: [
        ]
    } );
    
} );
