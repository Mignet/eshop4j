var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];

$(document).ready(function() {
	
	
    editor = new $.fn.dataTable.Editor( {
    	ajax: {
            create: {
                type: 'POST',
                url:  'rest/acc/acaccountbind/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            edit: {
                type: 'POST',
                url:  'rest/acc/acaccountbind/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            remove: {
            	 type: 'POST',
                 url:  'rest/acc/acaccountbind/save',
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
                label: "自增长主键:",
                name: "id"
                ,type:"hidden"
            }, 
         {
                label: "客户姓名:",
                name: "userName"
            }, 
         {
                label: "银行预留手机号码:",
                name: "reserveMobile"
            }, 
         {
                label: "银行卡号:",
                name: "bankCard"
            }, 
         {
                label: "银行编码:",
                name: "bankCode"
            }, 
         {
                label: "银行名称:",
                name: "bankName"
            }, 
         {
                label: "城市:",
                name: "city"
            }, 
         {
                label: "开户行:",
                name: "kaiHuHang"
            }, 
         {
                label: "账户总金额:",
                name: "totalAmount"
            }, 
         {
                label: "冻结金额:",
                name: "freezeAmount"
            }, 
         {
                label: "提现金额:",
                name: "withdrawAmount"
            }, 
         {
                label: "身份证号:",
                name: "idCard"
            }, 
         {
                label: "创建时间:",
                name: "createTime"
            }, 
        ]
    } );
    //前端校验
    editor.on( 'preSubmit', function ( e, o, action ) {
        if ( action !== 'remove' ) {
            var name = editor.field( 'name' );
            if ( ! name.isMultiValue() ) {
                if ( ! name.val() ) {
                	name.error( 'A activity name must be given' );
                }
                 
                if ( name.val().length <= 2 ) {
                	name.error( 'The activity name length must be more that 2 characters' );
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
            "url": "/rest/acc/acaccountbind/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，json化
        },
        "order": [[ 13, 'desc' ]],
        "columns": [
            { "data": "id","orderable": false,
            	"render":function (data,type,full,meta) {
					var _p = typeof meta.settings.oAjaxData._dt_json == "string"?$.parseJSON(meta.settings.oAjaxData._dt_json):meta.settings.oAjaxData._dt_json;
                    var start = (_p.start / _p.length) +1;
                    return (meta.row+1)+(start-1)*10;
				}},
            { "data": "userName","orderable": false },
            { "data": "mobile","orderable": false },
            { "data": "reserveMobile","orderable": false },
            { "data": "bankCard","orderable": false },
            { "data": "bankCode","orderable": false },
            { "data": "bankName","orderable": false },
            { "data": "city","orderable": false },
            { "data": "kaiHuHang","orderable": false },
            { "data": "totalAmount","orderable": false },
            { "data": "freezeAmount","orderable": false },
            { "data": "withdrawAmount","orderable": false },
            { "data": "idCard","orderable": false },
            { "data": "createTime","orderable": false },
            { "data": "id" ,"orderable": false,"render": function ( data, type, row ) {
            	if(row.status=='1'){
  	            	var bind = '<button class="btn btn-sm btn-default btn-icon ui-redirect" data-title="解绑" onclick="unbund('+row.mobile+')"><i class="fa fa-weixin">解绑</i></button>';
  	            }else{
  	            	var bind = '<a href="#" class="btn btn-sm btn-default btn-icon ui-redirect" data-title="绑卡" data-url="/rest/acc/acaccountbind/toEdit?mobile='+row.mobile+'"><i class="fa fa-jsfiddle"></i>绑卡</a>';
  	            }
                
  	            return bind;
           }}    
        ],
        select: true,
        buttons: []
        
    } );
    
   
} );


function unbund(mobile) {
	if(confirm("确定需要解绑【"+mobile+"】用户？")){
		$.ajax({
        	data:{"mobile":mobile}, //表单对象数据转json字符串提交
            type:'POST',
            url: '/rest/acc/acaccountbind/unbund' ,
            async: false,  
    	    dataType:"json",
    	    success : function(result) {
    			console.log(result.message);
    			layer.alert(result.message,{icon: 6,shift: 4,title:'提示'},function(index){
					layer.close(index);
					//debugger;
					$.switchPage("绑卡账户列表","rest/acc/acaccountbind/list"); //跳到机构列表页面
				});
    		},
            error:function(XmlHttpRequest,textStatus, errorThrown) {
  			  	console.log(XmlHttpRequest.status);
  			  	console.log(textStatus);
  			  	showError(XmlHttpRequest.responseText+"解绑失败！");
  		  	}
          });
	}
}
