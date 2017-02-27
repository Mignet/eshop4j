var editor; // use a global for the submit and return data rendering in the examples

$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
    	ajax: {
            create: {
                type: 'POST',
                url:  'rest/cms/msg/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            edit: {
                type: 'POST',
                url:  'rest/cms/msg/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            remove: {
            	 type: 'POST',
                 url:  'rest/cms/msg/save',
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
                label: "序号:",
                name: "id"
                ,type:"hidden"
            }, 
         {
                label: "消息标题:",
                name: "message"
            }, 
         /*{
                label: "链接:",
                name: "link"
            }, */
         {
                label: "状态:",
                name: "status",
                type:  "select",
                options: [
                    { label: "发布", value: 0 },
                    { label: "删除",  value: 1 }
                ],
                "default": 0
            }, 
           {
                label: "应用类别:",
                name: "appType",
                type:  "select",
                options: [
                    { label: "理财师", value: 1 },
                    { label: "投资者",  value: 2 }
                ],
                "default": 1
            }, 
            /* {
                label: "应用平台:",
                name: "platform",
                type:  "select",
                options: [
                    { label: "wechat", value: 1 },
                    { label: "android",  value: 2 },
                    { label: "ios", value: 3 },
                    { label: "wap", value: 4 },
                    { label: "web", value: 5 }
                ],
                "default": 1
            },*/
            {
                label: "应用类型及端口：",
                name: "platform",
                type:  "radio",
                options: [
                    /*{ label: "猎财大师", value: 6 },*/
                    { label: "T呗移动端", value: 1 },
                    { label: "T呗PC端", value: 5 }
                    
                ],
                "default": 1
            },
         {
                label: "生效时间:",
                name: "startTime",
                type:"datetime",
                format: 'YYYY-MM-DD HH:mm:ss'
            },
            {
                id:"umessage",
                label:"消息内容",
                name:"content",
                type:"todo"
            }
        ]
    } );
    
    editor.dependent( 'appType', function ( val ) {
						        return val == '1' ? {
							"options" : {
								"platform" : [ { label: "猎财大师", value: 6 }]
							}
						} : {
							"options" : {
								"platform" : [ { label: "T呗移动端", value: 1 },
							                    { label: "T呗PC端", value: 5 } ]
							}
						};
    } );
    // 前端校验
    editor.on( 'preSubmit', function ( e, o, action ) {
        if ( action !== 'remove' ) {
            console.log("----------------------------");
            for (var na in o.data){
                if(typeof o.data[na] =="object"){
                    for(var kname in o.data[na]){
                        if(kname == "content"){
                            if($(".J_ueditor").size()>0){
                                var currentParnet = uex.container.parentNode.parentNode;
                                var c = uex.getContent();
                                $(currentParnet).parent().find(".editor-content").val(c);
                                o.data[na][kname] = $(currentParnet).parent().find(".editor-content").val();
                            }
                        }
                    }
                }
            }

            var content = editor.field( 'content' );

            // Only validate user input values - different values indicate that
            // the end user has not entered a value
            /*if ( ! content.isMultiValue() ) {
                if ( ! content.val() ) {
                	content.error( 'A  content must be given' );
                }
                 
                if ( content.val().length <= 2 ) {
                	content.error( 'The content length must be more that 2 characters' );
                }
            }*/
            var message = editor.field( 'message' );
            if ( ! message.val() ) {
            	message.error( 'Message title must be given' );
            }
            
            var startTime = editor.field( 'startTime' );
            if ( ! startTime.val() ) {
            	startTime.error( 'Realese time must be given' );
            }
            
           
            if ( ! content.val() ) {
            	content.error( 'Message content must be given' );
            }
            
            
 
            // ... additional validation rules
 
            // If any error was reported, cancel the submission so it can be corrected
            if ( this.inError() ) {
                return false;
            }
        }
    } );

    editor.on("open",function () {
        var content = $(".J_ueditor").find("textarea").text();
        var bo = $(".J_ueditor").find(".editor-body");
        bo.html("");
        bo.append(uex.container.parentNode);
        uex.reset();
        setTimeout(function () {
            uex.setContent(content);
        },200);
        $(".DTE_Footer").find('.DTE_Form_Error').css("display","block");//调整提交按钮显示
    });

    editor.on("close",function () {
        var currentParnet = uex.container.parentNode.parentNode;
        var c = uex.getContent();
        $("#hidue").html("");
        $("#hidue").append(uex.container.parentNode);
        uex.reset();
        $(currentParnet).parent().find(".editor-content").text(c);
    });
    
    editor.on("setData",function (ename,full,meta,acname) {
        if(acname == "edit"){

            var currentParnet = uex.container.parentNode.parentNode;
            // var c = uex.getContent();
            // $(currentParnet).parent().find(".editor-content").val(c);
            meta.message = $(currentParnet).parent().find(".editor-content").val();
            //console.log("000000000000000000000");
           // console.log(meta);

        }
        return true;
    });

    editor.on("initEdit",function ($e,$row,$full) {
        editor.show();

    });
    
    var shiro_admin = "disabled=true";
    if($('#shiro_admin')){
    	shiro_admin = "";
    }
    
  //扩展DT的默认配置
    $.extend($.fn.dataTable.defaults, {
        //在 dom 里面不配置 f ，可以隐藏掉默认的搜索框
        dom: 't<"dataTables_info"il>p',
        //DT初始化完毕回调函数
        initComplete: function(settings) {
            var _$this = this;
            var searchHTML = '<label><span>应用平台及端口:</span> <select id="scondit1" type="search"><option value="1">猎财大师</option><option value="2">T呗移动端</option><option value="3">T呗PC端</option></select></label>';
            searchHTML += '<label><span>标题:</span> <input type="search" id="scondit2" placeholder="请输入标题" aria-controls="dtable"></label>';
            searchHTML += '<label> <input type="button" id="btnSearch"  aria-controls="dtable" value ="搜索"></label>';
            //快捷操作的HTML DOM
            $(_$this.selector + '_wrapper .dataTables_filter').html("");
            $(_$this.selector + '_wrapper .dataTables_filter').append(searchHTML);

            //重写搜索事件
            /*$(_$this.selector + '_wrapper .dataTables_filter input').bind('keyup',
            function(e) {
                if (e.keyCode == 13 || (e.keyCode == 8 && (this.value.length == 0))) {
                	 //自己定义的搜索框，可以是时间控件，或者checkbox 等等
                    var args1 = $("#scondit1").val();                    
                    var args2 = $("#scondit2").val();
                    //this.value
                    _$this.api().search(args1+","+args2).draw();
                }
            });*/
            //
            $("#btnSearch").click( function() { 
            	 var args1 = $("#scondit1").val();                    
                 var args2 = $("#scondit2").val();
                 _$this.api().search(args1+","+args2).draw();
            	
            });
        }
    });

    
  //给搜索按钮绑定点击事件
    $(document).on("click","#dtable button.search",function(){
        var args1 = $("#scondit1").val();
        var args2 = $("#scondit2").val();
        table.search(args1+","+args2).draw();
    });
    
    var table = $('#dtable').DataTable( {
    	dom: "Bfrtip",
    	"searching": true,
        "processing": true,
        "serverSide": true,
        "scrollX": true,
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
        	"sSearch":       "搜索：",
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
            "url": "/rest/cms/msg/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，json化
        },
        "order": [[ 6, 'desc' ]],
        "columns": [
            { "data": "id" },
            { "data": "message" },
            { "data": "link" },
            { "data": "status",render:function ( data, type, row ) {
            	//(0发布,1删除)
           	   if(data===0){
           		   return "发布";
           	   }
           	   if(data===1){
           		   return "删除";
           	   }
           	   return "异常"+row.status
              }},
            { "data": "appType",render:function ( data, type, row ) {
         	   if(data===1){
         		   return "理财师";
         	   }
         	   if(data===2){
         		   return "投资者";
         	   }
         	   return "异常"+row.appType
              }},
              { "data": "platform",render:function ( data, type, row ) {
            	   if(data===1){
            		   return "wechat";
            	   }
            	   if(data===2){
            		   return "android";
            	   }
            	   if(data===3){
            		   return "ios";
            	   }
            	   if(data===4){
            		   return "wap";
            	   }
            	   if(data===5){
            		   return "web";
            	   }
            	   return ""
               }},
            { "data": "startTime" },
            { "data": "crtTime" },
            { "data": "modifyTime" },
			    
        ],
        select: true,
        buttons: [
            { extend: "create", editor: editor },
            { extend: "edit",   editor: editor },
            { extend: "remove", editor: editor }
        ]
    } );
    
} );
