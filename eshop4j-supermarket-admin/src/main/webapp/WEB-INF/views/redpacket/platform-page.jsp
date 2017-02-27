<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<!-- layer弹层组件 -->
<script type="text/javascript"  src="assets/plugins/layer/layer.js"></script>
<!-- moment -->
<script type="text/javascript" src="assets/plugins/bootstrap-daterangepicker/moment.min.js"  ></script>
<script type="text/javascript" >  window['platformIds']  =eval('${ids}') ;</script>

	<div class="row">
		<div class="col-sm-3">
	
			<select class="form-control" id="platformModel" name="platformModel">
				<option value="1">首投</option>
				<option value="2">复投</option>
			</select>
		</div>
		<div class="col-sm-2">
			<a class="btn btn-default J_platform_search" href="javacript:;"
				role="button"><i class="fa fa-search"></i> 查询</a>
		</div>
	
	</div>
<table id="platform-list" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
	            <th>平台名称</th>
	            <th>收费模式</th>
	            <th>操作</th>
            </tr>
        </thead>
  	</table>


	<script type="text/javascript" >

	$(document).ready(function() {
        var bindingClass = 'btn btn-default btn-sm J_platformId';
        var unbindingClass = 'btn btn-danger btn-sm J_platformId';
	    $table = $('#platform-list').DataTable( {
	  dom: '<"J_search_toolbar">frtip',
	    ordering:false,
		searching:false,
		lengthChange:false,
		paging:false,
		select:false,
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
	        	//"sLengthMenu":   "显示 _MENU_ 项结果",
	        	"sZeroRecords":  "没有匹配结果",
	        	//"sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
	        	//"sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
	        	//"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
	        	"sEmptyTable":     "数据为空",
	        	"sLoadingRecords": "载入中...",
	        	"sInfoThousands":  ","
	        	/* "oPaginate": {
	        		"sFirst":    "首页",
	        		"sPrevious": "上页",
	        		"sNext":     "下页",
	        		"sLast":     "末页"
	        	} */
	        },
	        "ajax": {
	            "url": "/rest/redpackettemplate/getPlatform",
	            "type": "POST",
	            "data":function(d){
                      console.info($('#platformModel option:selected').val());
                      d.model= $('#platformModel option:selected').val()||'1';
                      debugger;
                      d.columns = [];
                      d.search = {};
                      return {'_dt_json':JSON.stringify(d)};
                   }//传递对象太多，json化
	        },
	        "columns": [
	            { "data": "platform" },
	            { "data": "model" },
	            { "data": "platformId","render": function ( data, type, row ) {
                    var isBinding = milo.hasValue(window['platformIds'],row.platformId);
                              return $("<a>").text(isBinding ? '取消': '绑定').attr({
                                        'href' : 'javascript:;',
                                        'data-pid' : row.platformId
                                      }).addClass(isBinding ? unbindingClass : bindingClass )[0].outerHTML;

	           } }
	        ]
	    } );

		$("#platform-list").off('click.J_platformId').on("click", "a.J_platformId", function(event) {

			var array = window['platformIds'];
			var $target = $(event.target);
			var pid = $target.attr("data-pid");

			if (milo.hasValue(array, pid)) {
                     layer.confirm('取消机构,回款时不发放该红包,确定取消?', {btn: ['确定','取消'],title:'系统提示',icon: 3}, function(){
  				  if (milo.remove(array, pid)){
                          $.post('rest/redpackettemplate/bindingPlatform', {'platformId':pid,'model':$('#platformModel option:selected').val(),'status':1,'redpacketId' :'${redpacketId} '}, function(data, textStatus, xhr) {
                              layer.msg(data.msg,{time: 1000,icon: data.isFlag?1:0});
                              $target.removeClass(unbindingClass).addClass(bindingClass).text("绑定");
                          });
  				  }
                  });
			} else {

                    layer.confirm('绑定机构,回款时发放该红包,确定绑定?', {btn: ['确定','取消'],title:'系统提示',icon: 3}, function(){
                          $.post('rest/redpackettemplate/bindingPlatform', {'platformId':pid,'model':$('#platformModel option:selected').val(),'status':0,'redpacketId' :'${redpacketId} '}, function(data, textStatus, xhr) {
                              layer.msg(data.msg,{time: 1000,icon: data.isFlag?1:0});
                              array.push(pid);
                              $target.removeClass(bindingClass).addClass(unbindingClass).text("取消");
                          });
                      });
			}
			return false;
		});



    //查询工具栏
    $(".J_search_toolbar").html($("#template-select").html());

    $("a.J_platform_search").click(function() {
    	window['platformIds']  =eval('${ids}');
    	$table.draw(); //重绘表格。执行比如添加、删除、改变排序、筛选或者分页这些操作时  会重新排序和过滤
    	return false;
    });




	});


	</script>
