var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];

$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
    	ajax: {
            create: {
                type: 'POST',
                url:  'rest/cim/syspushartificialqueue/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            edit: {
                type: 'POST',
                url:  'rest/cim/syspushartificialqueue/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            remove: {
            	 type: 'POST',
                 url:  'rest/cim/syspushartificialqueue/save',
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
                label: "推送对象(1理财师,2投资者):",
                name: "appType",
            	type:  "radio",
                options: [
                    { label: "理财师", value: 1 },
                    { label: "投资者",  value: 2 }
                ],
                "default": 1
            }, 
         {
                label: "推送内容:",
                name: "content"
            }, 
         {
                label: "推送跳转链接:",
                name: "link"
            },     
         {
                label: "状态(0即时,1定时):",
                name: "startType",
                type:  "radio",
                options: [
                    { label: "即时", value: 0 },
                    { label: "定时",  value: 1 }
                ],
                "default": 1
            }, 
         {
                label: "定时发送时间:",
                name: "startTime",
                type:"datetime",
                format: 'YYYY-MM-DD HH:mm:ss'
            }, 
         {
                label: "发送对象,全部or指定用户:",
                name: "sendObjectType",
                type:  "radio",
                options: [
                    { label: "全部", value: 0 },
                    { label: "指定用户",  value: 1 }
                ],
                "default": 0
            }       
         
        ]
    } );
    //前端校验
    editor.on( 'preSubmit', function ( e, o, action ) {
        if ( action !== 'remove' ) {
        	
            var content = editor.field( 'content' );
            if ( ! content.val() ) {
            	content.error( 'push content must be given' );
            }
            
            var link = editor.field( 'link' );
            if ( ! link.val() ) {
            	link.error( 'push link must be given' );
            }
            
            var startTime = editor.field( 'startTime' );
            if ( ! startTime.val() ) {
            	startTime.error( 'time must be given' );
            }
 
            // Only validate user input values - different values indicate that
            // the end user has not entered a value
            /*if ( ! name.isMultiValue() ) {
                if ( ! name.val() ) {
                	name.error( 'A activity name must be given' );
                }
                 
                if ( name.val().length <= 2 ) {
                	name.error( 'The activity name length must be more that 2 characters' );
                }
            }*/
 
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
    	dom: '<"J_search_toolbar">frtip',
        "processing": true,
        "serverSide": true,      
       /* "bAutoWidth":false,
        "aoColumnDefs": [
                         {"sWidth":"50px", "aTargets": [0]},
                         {"sWidth":"100px", "aTargets": [1]},
                         {"sWidth":"150px", "aTargets": [2]},
                         {"sWidth":"100px", "aTargets": [3]},
                         {"sWidth":"100px", "aTargets": [4]},
                         {"sWidth":"100px", "aTargets": [5]},
                         {"sWidth":"100px", "aTargets": [6]},
                         {"sWidth":"100px", "aTargets": [7]}
                     ],*/
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
            "url": "/rest/cim/syspushartificialqueue/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，json化
        },
        "order": [[ 3, 'desc' ]],
        "columnDefs": [{ className: "link-display", "targets": [ 2 ] }],
        "columns": [
            { "data": "id","orderable": true},          
            { "data": "content","orderable": true },
            { "data": "link","orderable": true,
			    render:function (data,type,full,meta) {
			    	if(data){
			    		return '<a  target="_blank" href="'+data+'" title="'+data+'" >推送地址</a>';
			    	}
			    	return '';
			        
			    } },
            { "data": "startTime","orderable": true },
            { "data": "crtTime","orderable": true },
            { "data": "remark","orderable": true },
            { "data": "status","orderable": true,render:function ( data, type, row ) {
            	//0待发送,1已发送,2已撤销
            	   if(data===0){
            		   return "待发送";
            	   }
            	   if(data===1){
            		   return "已发送";
            	   }
            	   if(data===2){
            		   return "已撤销";
            	   }
            	   return "异常"+row.status
               } },              
               { "data": "id" ,render: function( data, type, row ) {
            	   var content = '<a href="#" class="btn btn-sm btn-default btn-icon J_syspus_model" data-title="详情" data-url="/rest/cim/syspushartificialqueue/pushDetail?id='+row.id+'"><i class="fa fa-info"></i>详情</a>';
            	   if(row.status===0){
            		    content += '<a href="#" class="btn btn-sm btn-default btn-icon J_abandoned" data-title="撤销" data-url="/rest/cim/syspushartificialqueue/abandoned?id='+row.id+'"><i class="fa fa-edit"></i>撤销</a>';
            	   }else{
            		   content += '<a href="#" class="btn btn-sm btn-default btn-icon J_abandoned" data-title="删除" data-url="/rest/cim/syspushartificialqueue/delpush?id='+row.id+'"><i class="fa fa-trash-o"></i>删除</a>';
            	   }
            	   return content;
	  	           
              }}    
        ],
        select: true
      /*  buttons: [
			{
			    extend: "create",
			    text: '新增推送',
			    action: function ( e, dt, node, config ) {
			       // this.disable(); // disable button
			    	location.href= "/rest/redpacket/addPage";
			    }
			},
			{
				 dom: {
			            container: {
			                tag: 'addBtnDiv'
			            }
			        }
			},
            { extend: "edit",   editor: editor },
            { extend: "remove", editor: editor }
        ],*/
        
    } );
    //加载工具栏
    $(".J_search_toolbar").html($("#template-tools").html());
    
    //撤销
	$("body").off('click','a.J_abandoned').on('click','a.J_abandoned',function(event){
		var abandonedUrl = $(this).attr("data-url"); //查询url
		var msg = $(this).attr("data-title"); 
        bootbox.confirm("确定"+msg+"？",function (result) {
            if(result){
				$.ajax({
					url : abandonedUrl,
					type : 'post',
					dataType:'json',
					success : function(result) {			
						if(result.isFlag){
							layer.msg(msg+'成功',{time: 1000,icon: 0});
							table.draw(); 
						}
						
					},
				  error:function(XmlHttpRequest,textStatus, errorThrown)
				  {
					  console.log(XmlHttpRequest.status);
					  console.log(textStatus);
					  showError(XmlHttpRequest.responseText);
				  }
				});
            }
        });
	});
	
	  //打开模式窗口
	 var $modal = $('#publicModal');
	$("body").off('click','a.J_syspus_model').on('click','a.J_syspus_model',function(event){
		var url = $(this).attr("data-url"); //查询url
		var msg = $(this).attr("data-title"); 
		$.ajax({
			url : url,
			type : 'GET',
			success : function(data) {
				$modal.find(".modal-footer").hide();
				$modal.find(".modal-title").html("系统自定义推送");
				$modal.find(".modal-body").html(data);
				$modal.modal().css({
				    width: 'auto'					   
				});
				$modal.modal('show');
			}
		});
    });
	
	});


