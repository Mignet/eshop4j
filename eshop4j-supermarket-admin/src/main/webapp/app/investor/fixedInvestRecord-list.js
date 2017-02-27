var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];

$(document).ready(function() {
    var table = $('#dtable').DataTable( {
    	dom: "Bfrtip",
        "processing": true,
        "serverSide": true,
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
        	},
        	"oAria": {
        		"sSortAscending":  ": 以升序排列此列",
        		"sSortDescending": ": 以降序排列此列"
        	}
        },
        "ajax": {
            "url": "/rest/customerInvest/investRecordlist",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d), 'customerId':$('#customerId').val() };}//传递对象太多，json化
        },
        "columns": [
            { "data": "id","orderable": false },
            { "data": "productName","orderable": false },
            { "data": "investAmt","orderable": false },
            { "data": "rate","orderable": false },
            { "data": "profit","orderable": false },
            { "data": "bizTime","orderable": false },
            { "data": "startTime","orderable": false },
            { "data": "endTime","orderable": false },
            { "data": "status","orderable": false,"render": function ( data, type, row ) {
           	   //0公用，1理财师，2投资者
           	   if(data==1)return "投资失败"
           	   if(data==2)return "投资中"
           	   if(data==3)return "已赎回"
              }},
			    
        ],
        select: true,
        buttons: [
            { extend: "create", editor: editor },
            { extend: "edit",   editor: editor },
            { extend: "remove", editor: editor }
        ]
    } );
    
} );
