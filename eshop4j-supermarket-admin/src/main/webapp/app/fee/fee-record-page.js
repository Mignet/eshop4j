$(document).ready(function() {
   
     table = $('#fee-record-list').DataTable( {
    	dom: '<"J_search_toolbar">frtip',
    	ordering:false,
		searching:false,
		lengthChange:false,
		paging:true,
		select:true,
		serverSide:true,
		//deferLoading:false,
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
        	"sEmptyTable":     "数据为空",
        	"sLoadingRecords": "载入中...",
        	"sInfoThousands":  ",",
        	"oPaginate": {
        		"sFirst":    "首页",
        		"sPrevious": "上页",
        		"sNext":     "下页",
        		"sLast":     "末页"
        	}
        },
        "ajax": {
            "url": "/rest/fee/record",
            "type": "POST",
            "data":function(d){
            	var mobile = $('#mobile').val();
            	if(!mobile)layer.alert("手机号码不能为空", {icon: 0}); //失败
            	d.mobile = mobile;
            	var month = $('#month').val();
            	d.month = month;
            	return {'_dt_json':JSON.stringify(d)};
            }//传递对象太多，json化
        },
        "columns": [  
            { "data": "name"},
            { "data": "mobile" },
            { "data": "amount" },
            { "data": "time"},
            { "data": "sendTime"},
            { "data": "status"},
            
            { "data": "mobile","render": function ( data, type, row ) {          	
              	//渲染 把数据源中的标题和url组成超链接
              	var a = '<a href="#" class="btn btn-default btn-sm ui-redirect" data-title="佣金纪录明细" data-url="/rest/fee/'+row.time+'/'+row.mobile+'/recordDetailPage"><i class="fa fa-search"></i> 佣金纪录明细</a>';
  	            /*var b = '<a href="javascript:;" class="btn btn-default btn-sm J_send_redpacket" data-redpacektId="'+data+'">红包发放</a>';*/
                return a;
           } }
			    
        ]
    } );
    //查询工具栏
    $(".J_search_toolbar").html($("#template-tools").html());
    //查询
	$("a.J_search").click(function() {
		table.draw(); //重绘表格。执行比如添加、删除、改变排序、筛选或者分页这些操作时  会重新排序和过滤
	});
} );
