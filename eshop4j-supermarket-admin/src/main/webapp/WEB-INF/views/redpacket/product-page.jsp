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


   	<table id="product-list" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
	            <th>产品名称</th>
	            <th>收益率</th>
	            <th>佣金率</th>
	            <th>产品期限</th>
	            <th>操作</th>
            </tr>
        </thead>
  	</table>
	<script type="text/javascript" >


	$(document).ready(function() {
           var bindingClass = 'btn btn-default btn-sm J_productId';
           var unbindingClass = 'btn btn-danger btn-sm J_productId';
	    var $table = $('#product-list').DataTable( {
	    dom: "Bfrtip",
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
	            "url": "/rest/redpacket/getProducts",
	            "type": "POST",
	            "data":function(d){
                      d.name= $("#productName").val()
                      d.orgNumer=$("#platformId").val();
                      return {'_dt_json':JSON.stringify(d)};
                   }//传递对象太多，json化
	        },
	        "columns": [
	            { "data": "productName" },
	            { "data": "productRateText" },
	            { "data": "feeRatio"},
	            { "data": "deadLineValueText" },
	            { "data": "productId","render": function ( data, type, row ) {

                    var isBinding = milo.hasValue(window['rproductIds'],row.productId);

                              return $("<a>").text(isBinding ? '取消': '绑定').attr({
                                        'href' : 'javascript:;',
                                        'data-pid' : row.productId
                                      }).addClass(isBinding ? unbindingClass : bindingClass )[0].outerHTML;

	           } }
	        ]
	    } );

		$("#product-list").off('click.J_productId').on("click", "a.J_productId", function(event) {

			var array = window['rproductIds'];
			var $target = $(event.target);
			var pid = $target.attr("data-pid");
			if (milo.hasValue(array, pid)) {
				if (milo.remove(array, pid)){
					$target.removeClass(unbindingClass).addClass(bindingClass).text("绑定");
					$("#bindingProducts").find(".J_productId[data-pid='"+pid+"']").parent().parent().remove();
				}

			} else {
				array.push(pid);
				$target.removeClass(bindingClass).addClass(unbindingClass).text("取消");
                $("#bindingProducts").append($("#product-list").find(".J_productId[data-pid='"+pid+"']").parent().parent()[0].outerHTML);
			}
			return false;
		});

	});


	</script>
