var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];

$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
    	ajax: {
            create: {
                type: 'POST',
                url:  'rest/sys/gray/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            remove: {
            	 type: 'POST',
                 url:  'rest/sys/gray/save',
                 data:function(d){return {'rows':JSON.stringify(d)};}
            }
        },
        table: "#dtable",
        idSrc:  'id',
        i18n: {
            "create": {
                "button": "添加白名单",
                "title":  "创建新的实体",
                "submit": "确定"
            },
            "remove": {
                "button": "删除",
                "title":  "删除",
                "submit": "确定",
                "confirm": {
                    "_": "确定要删除选择的 %d 行数据吗?",
                    "1": "确定要删除选择的 1 行数据吗?"
                }
            },
         
            "error": {
                "system": "发生系统错误 (More information)"
            },
         
            "multi": {
                "title": "多个值",
                "info": "选择的内容中当前输入框包含不同的值. 把他们设置成相同的值, 点击这里, 否则它们仍然保留不同的值.",
                "restore": "取消改动"
            },
         
            "datetime": {
                "previous": '前',
                "next":     '后',
                "months":   [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
                "weekdays": [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
                "amPm":     [ '上午', '下午' ],
                "unknown":  '-'
            }
        },
        fields: [
         {
                label: ":",
                name: "id"
                ,type:"hidden"
            }, 
         {
                label: "注册手机号码:",
                name: "mobile"
            }, {
                label: "类型:",
                name: "type",
                type:  "select",
                options: [
                    { label: "公共", value: 0 },
                    { label: "产品",  value: 1 },
                    { label: "机构",  value: 2 }
                ],
                "default": 0
            },
        ]
    } );
    //前端校验
    editor.on( 'preSubmit', function ( e, o, action ) {
        if ( action !== 'remove' ) {
            var mobile = editor.field( 'mobile' );
            if ( ! mobile.isMultiValue() ) {
                if ( ! mobile.val() ) {
                	mobile.error( '手机号码不能为空！' );
                }
                 
                if ( mobile.val().length != 11 ) {
                	mobile.error( '手机号码长度不对!' );
                }
                if( mobile.val().length == 11){
                	$.ajax({
                		url : '/rest/sys/gray/checkMobile',
                		type : 'POST',
                		data: {"mobile":mobile.val()}, 
                		async: false,  
                	    dataType:"json", 
                		success : function(result) {
                			console.log(result.message);
                			if(result.statusCode==500){
                				mobile.error(result.message);
                			}
                		},
                	  error:function(XmlHttpRequest,textStatus, errorThrown)
                	  {
                		  console.log(XmlHttpRequest.status);
                		  console.log(textStatus);
                		  showError(XmlHttpRequest.responseText);
                	  }
                	})
                }
            }
            if ( this.inError() ) {
                return false;
            }
        }
    } );
    var shiro_admin = "disabled=true";
    if($('#shiro_admin')){
    	shiro_admin = "";
    }
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
        	"sSearch":       "手机号码:",
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
            "url": "/rest/sys/gray/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，json化
        },
        "order": [[ 0, 'desc' ]],
        "columns": [
            { "data": "id","orderable": false, 
            	"render":function (data,type,full,meta) {
					var _p = typeof meta.settings.oAjaxData._dt_json == "string"?$.parseJSON(meta.settings.oAjaxData._dt_json):meta.settings.oAjaxData._dt_json;
                    var start = (_p.start / _p.length) +1;
                    return (meta.row+1)+(start-1)*10;
				}
            },
            { "data": "mobile","orderable": false },
            { "data": "userId","orderable": false },
            { "data": "userName","orderable": false },
			    
        ],
        select: true,
        buttons: [
            { extend: "create", editor: editor },
            { extend: "remove", editor: editor }
        ]
    } );
    
} );
