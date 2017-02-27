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
            "url": "/rest/cim/cimorgrisk/list",
            "type": "POST",
            "data":function(data){
	            	data.orgName = $('#org_select option:selected').val(); //查询条件
	            	return {'_dt_json':JSON.stringify(data)};//传递对象太多，json化
            	}
        },
        "order": [[ 4, 'desc' ]], //按创建时间 降序排列
        //"order": '', //不排序
        "columns": [
            { "data": "orgName","orderable": false },
            { "data": "indicatorName","orderable": false },
            { "data": "indicatorScore","orderable": false },
            { "data": "creator","orderable": false },
            { "data": "createTime"},
              
            { "data": "rid" ,"orderable": false,"render": function ( data, type, row ) {
	              	//渲染 把数据源中的标题和url组成超链接 
            		var b = '<button class="btn btn-sm btn-default btn-icon edit" data-url="/rest/cim/cimorgrisk/toEdit?id='+row.rid+'"><i class="fa fa-edit"></i>编辑</button>&nbsp';
            	    var a = '<button class="btn btn-sm btn-default btn-danger" onclick="deleteRisk('+row.rid+');"><i class="fa fa-trash-o"></i> 删除</button>';
	                return b+a;
               }}
			    
        ]
    });
    
    //包裹查询工具栏
    $(".J_search_toolbar").html($("#template-search").html());
    
    //查询
	$(".J_search").click(function() {
		table.draw(); //重绘表格。执行比如添加、删除、改变排序、筛选或者分页这些操作时  会重新排序和过滤
	});
	
	/**
	 * 新增
	 */
	$("#addOrgRisk").click(function(){
		var add_url = $(this).attr("data-url");
		$.ajax({
			url : add_url,
			type : 'GET',
			dataType: 'html',
			success : function(data) {
				//$modal.find(".modal-footer").hide();
				//$modal.find(".modal-title").html("发放红包"); //模态框标题设置
				//$modal.find(".modal-body").html(data);
				//$modal.modal('show');
				$("#orgRiskModal").html(data);
				$("#orgRiskModal").find(".modal-title").html("新增风控信息"); //模态框标题设置
				$("#orgRiskModal").modal('show');
			}
		});
	});
	
	/**
	 * 编辑
	 */
    $("#dtable").on("click",".edit",function(){
    	//var orgNum = $(event.target).attr("data-orgNumber");
    	//var orgNumber = $(this).attr("data-orgNumber");
    	var edit_url = $(this).attr("data-url");
		$.ajax({
			url : edit_url,
			type : 'GET',
			dataType: 'html',
			success : function(data) {
				$("#orgRiskModal").html(data);
				$("#orgRiskModal").find(".modal-title").html("编辑风控信息"); //模态框标题设置
				$("#orgRiskModal").modal('show');
			}
		});
    });
    
    
    
});




/**
 * 删除机构风控信息
 */
function deleteRisk(id){
	//询问框
	layer.confirm('确定删除此条信息？', {btn: ['确定','取消'],title:'提示',shift: 6,icon: 3}, function(){
	  		//数据库中执行删除
	  		$.ajax({
	        	data:{"rid":id}, //表单对象数据转json字符串提交
	            dataType:'json',
	            type:'post',
	            url : '/rest/cim/cimorgrisk/delete',
	            success: function (result) {
	            	if(result.isFlag){
	            		layer.msg('删除此信息成功！', {icon: 1,time:500});
	            		table.draw(); 
					}else{
						//删除团队成员失败
						layer.msg('删除此信息失败！', {icon: 2,time:500});
					}
	            },
	            error:function(XmlHttpRequest,textStatus, errorThrown) {
	  			  	console.log(XmlHttpRequest.status);
	  			  	console.log(textStatus);
	  			  	showError(XmlHttpRequest.responseText+"删除此信息失败！");
	  		  	}
	            
	          });
		
	});
	
}
