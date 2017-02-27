var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];

$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
    	ajax: {
            create: {
                type: 'POST',
                url:  'rest/sm/smappversion/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            edit: {
                type: 'POST',
                url:  'rest/sm/smappversion/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            remove: {
            	 type: 'POST',
                 url:  'rest/sm/smappversion/save',
                 data:function(d){return {'rows':JSON.stringify(d)};}
            }
        },
        table: "#dtable",
        idSrc:  'id',
        i18n: {
            "create": {
                "button": "新增",
                "title":  "创建新的实体",
                "submit": "确定"
            },
            "edit": {
                "button": "编辑",
                "title":  "编辑实体",
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
                label: "ID",
                name: "id"
                ,type:"hidden"
            }, 
         {
                label: "名称",
                name: "name"
            }, 
         {
                label: "版本",
                name: "version"
            }, 
         {
                label: "最低版本",
                name: "minVersion"
            }, 
         {
                label: "平台",
                name: "platform"/*,
                type:  "select",
                options: [
                    { label: "ios", value: "ios" },
                    { label: "android",  value: "android" }
                ],
                "default": "ios"*/
            }, 
         {
                label: "下载链接",
                name: "downloadUrl"
            }, 
         {
                label: "注册提示",
                name: "regHints"
            }, 
         {
                label: "升级提示",
                name: "updateHints"
            }, 
         {
                label: "开放注册",
                name: "openReg",
                type:  "select",
                options: [
                    { label: "开放", value: 0 },
                    { label: "不开放",  value: 1 }
                ],
                "default": 0
            }, 
         {
                label: "状态",
                name: "status",
                type:  "select",
                options: [
                    { label: "可用", value: 0 },
                    { label: "不可用",  value: 1 }
                ],
                "default": 0
            }, 
         {
                label: "发布时间",
                name: "issueTime",
                type:"datetime",
                format: 'YYYY-MM-DD hh:mm:ss'
            }, 
         {
                label: "创建时间",
                name: "crtTime",
                type:"datetime",
                format: 'YYYY-MM-DD hh:mm:ss'
            }, 
         {
                label: "修改时间",
                name: "modifyTime",
                type:"datetime",
                format: 'YYYY-MM-DD hh:mm:ss'
            }, 
         {
                label: "客户端类型",
                name: "appType",
                type:  "select",
                options: [
                    { label: "猎财大师", value: 1 },
                    { label: "投呗",  value: 2 }
                ],
                "default": 1
            }, 
        ]
    } );  
    //前端校验
    editor.on( 'preSubmit', function ( e, o, action ) {
        if ( action !== 'remove' ) {
            var name = editor.field( 'name' );
 
            // Only validate user input values - different values indicate that
            // the end user has not entered a value
            if ( ! name.isMultiValue() ) {
                if ( ! name.val() ) {
                	name.error( 'A activity name must be given' );
                }
                 
                if ( name.val().length <= 2 ) {
                	name.error( 'The activity name length must be more that 2 characters' );
                }
            }
 
            // ... additional validation rules
 
            // If any error was reported, cancel the submission so it can be corrected
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
            "url": "/rest/sm/smappversion/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，json化
        },
        "columns": [
            { "data": "id","orderable": true },
            { "data": "name","orderable": true },
            { "data": "version","orderable": true },
            { "data": "minVersion","orderable": true },
            { "data": "platform","orderable": true },
            { "data": "downloadUrl","orderable": true },
            { "data": "regHints","orderable": true },
            { "data": "updateHints","orderable": true },
            { "data": "openReg",
            	"orderable": true,
            	"render":function(data,type,row){          		
            		if(data=="0"){
            			return "开放";
            		}else if(data == "2"){
            			return "不开放";
            		}
            	}
            },
            { "data": "status",
            	"orderable": true,
            	"render":function(data,type,row){          		
            		if(data=="0"){
            			return "可用";
            		}else if(data == "1"){
            			return "不可用";
            		}
            	}
            },
            { "data": "issueTime","orderable": true },
            { "data": "crtTime","orderable": true },
            { "data": "modifyTime","orderable": true },
            { "data": "appType",
            	"orderable": true,
            	"render":function(data,type,row){
            		console.log("data:"+data+",type:"+type+",row:"+row);
            		if(data=="1"){
            			return "猎财大师";
            		}else if(data == "2"){
            			return "投呗";
            		}
            	}
            },          	    
        ],
        "order":[ [ 0, 'desc' ]],
        select: true,
        buttons: [
            { extend: "create", editor: editor },
            { extend: "edit",   editor: editor },
            { extend: "remove", editor: editor }
        ]
    } );
    
/*    editor.on('open',function(){
    	$('.DTE_Action_Create #DTE_Field_name').on( 'change', function () {     
            if ( editor.get( 'name' ) === 'IOS版本' ) {
                editor.set( 'downloadUrl', "www.baidu.com" );
            }
        } );
    });*/
    
} );
