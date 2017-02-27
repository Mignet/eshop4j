
$(document).ready(function() {
    
    var table = $('#dtable').DataTable( {
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
            "url": "/rest/redpackettemplate/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，json化
        },
        "columns": [
            { "data": "name" },
            { "data": "type" },
            { "data": "expiresDay" },
            { "data": "money" },
            { "data": "repaymentAmt" },
            
            { "data": "time" },
            { "data": "operator" }, 
            { "data": "redpacketTemplateId","render": function ( data, type, row ) {
            	
              	//渲染 把数据源中的标题和url组成超链接
              	var a = '<a href="#" class="btn btn-default btn-sm ui-redirect" data-title="红包编辑" data-url="/rest/redpackettemplate/'+data+'/editPage">红包编辑</a>';
  	            var b = '<a href="javascript:;" class="btn btn-default btn-sm J_binding_platform" data-redpacektId="'+data+'">绑定机构</a>';
                return a+b;
           }  }
        ]
    } );
  //查询工具栏
    $(".J_search_toolbar").html($("#template-tools").html());
    var $modal = $('#publicModal');
    $("#dtable").on("click",".J_binding_platform",function(){
		$.ajax({
			url : "/rest/redpackettemplate/platformPage?redpacketId="+$(event.target).attr("data-redpacektId"),
			type : 'GET',
			success : function(data) {
				$modal.find(".modal-footer").hide();
				$modal.find(".modal-title").html("绑定机构");
				$modal.find(".modal-body").html(data);
				$modal.modal('show');
			}
		});
    });
    
    $(".J_cps_seting").on("click",function(event){
    
    	   layer.confirm('确定设置cps主推平台?', {btn: ['确定','取消'],title:'系统提示',icon: 3}, function(){
    		   $.post('rest/redpackettemplate/setMainPlatform', {'platform': $("#cpsPlatform").val(),'model':2}, function(data, textStatus, xhr) {
    	             layer.msg(data.msg,{time: 1000,icon: data.isFlag?1:0});
    	         });
    			
               });
    	
    	 
    });
    
    $(".J_cpa_seting").on("click",function(event){
    	 layer.confirm('确定设置cpa主推平台?', {btn: ['确定','取消'],title:'系统提示',icon: 3}, function(){
  		   $.post('rest/redpackettemplate/setMainPlatform', {'platform': $("#cpaPlatform").val(),'model':1}, function(data, textStatus, xhr) {
  	             layer.msg(data.msg,{time: 1000,icon: data.isFlag?1:0});
  	         });
  			
             });
    	
    });
} );
