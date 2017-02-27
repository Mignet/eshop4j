<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<!-- moment -->
<script type="text/javascript" src="assets/plugins/bootstrap-daterangepicker/moment.min.js"  ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>


<table id="redpacket-statistics-list" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>

	            <th>发放理财师红包人数</th>
	            <th>发放理财师红包金额</th>
	            <th>发放理财师红包数量</th>

	            <th>发放客户红包人数</th>
	            <th>发放客户红包金额</th>
	            <th>发放客户红包数量</th>

	            <th>理财师发给客户红包的使用数量</th>
	            <th>红包总共使用量</th>
	            <th>红包总共使用金额</th>
            </tr>
        </thead>
    </table>

	<script type="text/eshop4j-template" id="template-search">
            <div class="row">
                <div class="col-sm-3">
					 <input  placeholder="日期" id="date" name="date" class="Wdate" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					<a class="btn btn-default J_search" href="#" role="button"><i class="fa fa-search"></i> 查询</a>
                </div>
            </div>
    </script>
<script type="text/javascript" >

  var $table;
	$(document).ready(function() {
	     $table = $('#redpacket-statistics-list').DataTable( {
	    dom : '<"J_search_toolbar">frtip',
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
	            "url": "/rest/redpacket/redpacketStatistics",
	            "type": "POST",
	            "data":function(d){
                      d.date= $("#date").val()
                      return {'_dt_json':JSON.stringify(d)};
                   }//传递对象太多，json化
	        },
	        "columns": [
	            { "data": "cfplannerNum" },
	            { "data": "cfplannerRedpacketMoney" },
	            { "data": "cfplannerRedpacketCount"},
                 { "data": "customerNum" },
                 { "data": "customerRedpacketMoney" },
                 { "data": "customerRedpacketCount"},
                 { "data": "useNum" },
                 { "data": "useCount" },
                 { "data": "totalMoney"}
	        ]
	    } );

            //包裹查询工具栏
            $(".J_search_toolbar").html($("#template-search").html());

            //查询
           $("a.J_search").click(function() {
                $table.draw();
            });


	});


	</script>
