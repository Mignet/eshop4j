$(document).ready(function() {

     table = $('#fee-list').DataTable( {
    	dom: '<"J_search_toolbar">frtip',
    	ordering:false,
		searching:false,
		lengthChange:false,
		paging:true,
		select:true,
		serverSide:true,
		deferLoading:false,
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
            "url": "/rest/fee/list",
            "type": "POST",
            "data":function(d){
            	var mobile = $('.J_search_toolbar input[name=mobile]').val();
            	if(!mobile)layer.alert("手机号码不能为空", {icon: 0}); //失败
            	d.mobile = mobile;
            	return {'_dt_json':JSON.stringify(d)};
            }//传递对象太多，json化
        },
        "columns": [
            { "data": "name"},
            { "data": "mobile" },
            { "data": "amount" },
            { "data": "count"},
            { "data": "time"},
            { "data": "sendTime"},
            { "data": "mobile","render": function ( data, type, row ) {
              	//渲染 把数据源中的标题和url组成超链接
              	var a = '<a href="#" class="btn btn-default btn-sm ui-redirect" data-title="已发佣金纪录" data-url="/rest/fee/'+row.mobile+'/recordPage"><i class="fa fa-search"></i> 已发佣金纪录</a>';
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
    //佣金计算
    $("a.J_fee_calcu").click(function() {
    	layer.confirm('佣金计算完成后,不可重复计算,确认计算？', {btn: ['确定','取消'],title:'系统提示',icon: 3}, function(){
    		 $.post('/rest/fee/feeCalcu', function(data, textStatus, xhr) {
    			 if(!data.isFlag){
                     layer.alert(data.msg, {icon: 0}); //失败
                 }else{
                     layer.msg(data.msg,{time: 2000,icon: 1}); //成功
                 }
    		 });
		});
           
    });
        //佣金发放
    $("a.J_fee_pay").click(function() {
    	layer.confirm('发放完成后理财师即可提现，确认佣金发放？', {btn: ['确定','取消'],title:'系统提示',icon: 3}, function(){
    		  $.post('/rest/fee/feePay', function(data, textStatus, xhr) {
                  if(!data.isFlag){
                      layer.alert(data.msg, {icon: 0}); //失败
                  }else{
                      layer.msg(data.msg,{time: 2000,icon: 1}); //成功
                  }
          });
		});  
    });


} );
