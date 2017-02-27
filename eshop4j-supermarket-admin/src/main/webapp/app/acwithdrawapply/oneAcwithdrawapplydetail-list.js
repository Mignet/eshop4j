var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];
var table;

$(document).ready(function() {
	
    table = $('#example').DataTable( {
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
        	"sSearch":       "注册手机号:",
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
            "url": "/rest/acc/acwithdrawapply/oneDetaillist",
            "type": "POST",
            "data":function(d){
            	return {'_dt_json':JSON.stringify(d)
            		};
            		}//传递对象太多，json化
        },
        'columnDefs': [{
	         'targets': 0,
	         'searchable': false,
	         'orderable': false,
	         'className': 'dt-body-center'
	      }],
	    'order': [[4, 'desc']],
        "columns": [
   			 { "data": "id","orderable": false,
            	"render":function (data,type,full,meta) {
					var _p = typeof meta.settings.oAjaxData._dt_json == "string"?$.parseJSON(meta.settings.oAjaxData._dt_json):meta.settings.oAjaxData._dt_json;
                    var start = (_p.start / _p.length) +1;
                    return (meta.row+1)+(start-1)*10;
				}},
            { "data": "orderId","orderable": false },
            { "data": "userName","orderable": false },
            { "data": "mobile","orderable": false },
            { "data": "bisTime","orderable": false },
            { "data": "confirmTime","orderable": false },
            { "data": "noticeTime","orderable": false },
            { "data": "totalAmount","orderable": false },
            { "data": "amount","orderable": false },
            { "data": "fee","orderable": false },
            { "data": "status",render:function ( data, type, row ) {
               
               if(data==0)return "提现记录"
           	   if(data==1)return "提现中,待审核"
           	   if(data==2)return "审核通过,等待支付平台结果"
           	   if(data==3)return "审核不通过"
         	   if(data==4)return "冻结失败"
         	   if(data==5)return "提现成功"
           	   if(data==6)return "提现失败,需要解冻"
           	   if(data==7)return "提现失败,已处理解冻"
         	   if(data==8)return "打款处理中"
           	
                }},
            { "data": "userType",render:function ( data, type, row ) {
            	
           	   if(data==1)return "猎财大师"
           	   if(data==2)return "投呗"
           	
                }},
            { "data": "dealId","orderable": false },
            { "data": "remark","orderable": false },
			    
        ],
        select: true,
        buttons: []
    } );
    
});
