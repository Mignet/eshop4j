var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];

$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
    	ajax: {
            create: {
                type: 'POST',
                url:  'rest/sm/smdynamicnews/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            edit: {
                type: 'POST',
                url:  'rest/sm/smdynamicnews/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            remove: {
            	 type: 'POST',
                 url:  'rest/sm/smdynamicnews/save',
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
                label: "流水号:",
                name: "id"
                ,type:"hidden"
            }, 
         {
                label: "应用端口（1：猎财大师2：T呗）:",
                name: "appType"
            }, 
         {
                label: "类型标识:",
                name: "typeCode"
            }, 
         {
                label: "类型名称:",
                name: "typeName"
            }, 
         {
                label: "图标:",
                name: "img"
            }, 
         {
                label: "标题:",
                name: "title"
            }, 
         {
                label: "摘要:",
                name: "summary"
            }, 
         {
                label: "链接:",
                name: "linkUrl"
            }, 
         {
                label: "内容:",
                name: "content"
            }, 
         {
                label: "状态:0发布,1删除2初始化类别:",
                name: "status"
            }, 
         {
                label: ":",
                name: "creator"
            }, 
         {
                label: "创建时间:",
                name: "crtTime"
            }, 
         {
                label: "显示排序:",
                name: "showIndex"
            }, 
         {
                label: "生效时间:",
                name: "validBegin"
            }, 
         {
                label: "结束时间:",
                name: "validEnd"
            }, 
         {
                label: "修改时间:",
                name: "modifiyTime"
            }, 
         {
                label: "创建者:",
                name: "createor"
            }, 
         {
                label: "默认为0，0=不置顶,1=置顶:",
                name: "isStick"
            }, 
         {
                label: "扩张字段1:",
                name: "extends1"
            }, 
         {
                label: "扩张字段2:",
                name: "extends2"
            }, 
         {
                label: "扩张字段3:",
                name: "extends3"
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
            "url": "/rest/sm/smdynamicnews/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，json化
        },
        "columns": [
            { "data": "id","orderable": false },
            { "data": "appType","orderable": false },
            { "data": "typeCode","orderable": false },
            { "data": "typeName","orderable": false },
            { "data": "img","orderable": false },
            { "data": "title","orderable": false },
            { "data": "summary","orderable": false },
            { "data": "linkUrl","orderable": false },
            { "data": "content","orderable": false },
            { "data": "status","orderable": false },
            { "data": "creator","orderable": false },
            { "data": "crtTime","orderable": false },
            { "data": "showIndex","orderable": false },
            { "data": "validBegin","orderable": false },
            { "data": "validEnd","orderable": false },
            { "data": "modifiyTime","orderable": false },
            { "data": "createor","orderable": false },
            { "data": "isStick","orderable": false },
            { "data": "extends1","orderable": false },
            { "data": "extends2","orderable": false },
            { "data": "extends3","orderable": false },
			    
        ],
        select: true,
        buttons: [
            { extend: "create", editor: editor },
            { extend: "edit",   editor: editor },
            { extend: "remove", editor: editor }
        ]
    } );
    
} );
