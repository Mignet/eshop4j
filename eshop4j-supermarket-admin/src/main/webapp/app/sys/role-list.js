var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];

$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
    	ajax: {
            create: {
                type: 'POST',
                url:  'rest/role/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            edit: {
                type: 'POST',
                url:  'rest/role/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            remove: {
            	 type: 'POST',
                 url:  'rest/role/save',
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
                "previous": '向前',
                "next":     '向后',
                "months":   [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
                "weekdays": [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
                "amPm":     [ '上午', '下午' ],
                "unknown":  '-'
            }
        },
        fields: [ {
                label: "角色编号:",
                name: "id",
                type:"hidden"
            }, {
                label: "角色名称:",
                name: "roleName"
            }, {
                label: "角色标识:",
                name: "roleSign"
            }, {
                label: "描述:",
                name: "description"
            }
        ]
    } );
    //前端校验
    editor.on( 'preSubmit', function ( e, o, action ) {
        if ( action !== 'remove' ) {
            var roleName = editor.field( 'roleName' );
 
            // Only validate user input values - different values indicate that
            // the end user has not entered a value
            if ( ! roleName.isMultiValue() ) {
                if ( ! roleName.val() ) {
                	roleName.error( 'A role name must be given' );
                }
                 
                if ( roleName.val().length <= 2 ) {
                	roleName.error( 'The role name length must be more that 2 characters' );
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
            "url": "/rest/role/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，封装一下
        },
        "columns": [
            { "data": "id" },
            { "data": "roleName"},
            { "data": "roleSign" },
            { "data": "description" },
            {
                "data": null,
                "orderable": false,
                "defaultContent": "<button id='editrow' "+shiro_admin+" type='button' data-toggle=\"modal\"  data-target=\"#roleModal\"><i class='fa fa-edit'></i>配置权限</button>"
            }
        ],
        select: true,
        buttons: [
            { extend: "create", editor: editor },
            { extend: "edit",   editor: editor },
            { extend: "remove", editor: editor }
        ]
    } );
 // 数据编辑
    $('#dtable tbody').on('click', 'button#editrow', function (data) {
        var row = table.row( $(this).closest('tr') );
    	var data = row.data();
        console.log(data.id+"-"+data.roleName+"-"+data.roleSign+"-"+data.description);
        setPermissions(data.id);
    });
    
  //分配权限
  function setPermissions(id){
  	$('#roleid').val(id);
  	$.ajax({
  		url : '/rest/role/' + id+'/permission',
  		type : 'GET',
  		success : function(data) {
  			 var permissions = data.permissions;
  			 permissionList = data.permissionList;
  			 $('#permissions-div').html('');
  			 var checks = '';
  			 var permission_category = '';var flag = false;var j = 0;
  			 for(var i=0;i<permissions.length;i++){
  				 var permission = permissions[i];
  				 if(permission_category != permission.permissionCategory){
  					 permission_category = permission.permissionCategory;
  					 if(j!=0&&j%2==1){
  						 checks +=  '<div class="col-md-6"><h6>&nbsp;</h6></div>';
  					 }
  					 checks +=  '<span class="display: block"><b>' + permission_category+'</b></span><br>';
  					 flag = true;
  					 j=0;
  				 }
  				 if(inArray(permission,permissionList,'id')){
  					 checks +=  '<div class="col-md-6"><input name="ck" type="checkbox" value="'+permission.id+'"  checked="checked"/>'+permission.permissionName+'['+permission.description+']'+'</div>';
  				 }else{
  					 checks +=  '<div class="col-md-6"><input name="ck" type="checkbox" value="'+permission.id+'" />'+permission.permissionName+'['+permission.description+']'+'</div>';
  				 }
  				 if(flag){
  					flag = !flag;
  				 }
  				 j++;
  			 }
  			 $('#permissions-div').append(checks);
  		},
  	 //async :false,
  	  error:function(XmlHttpRequest,textStatus, errorThrown)
  	  {
  		  showError(XmlHttpRequest.responseText);
  	  }
  	});
  }
} );

//更新权限
function submitPermissions(){
	var permissions = '';
	$("input[name='ck']:checked").each(
		function(){
			permissions += this.value+",";
	});
	if(permissions==''&&permissionList.length==0){
		showError('注意:你没有选择任何权限');return;
	}
	$("#roleModal").modal("hide");
	$.ajax({
		url:"/rest/role/"+$("#roleid").val()+"/permission",
		type : 'POST',
		data:'permissions='+permissions,
		success : function(result) {
			 if (result.success) {
                //保存成功  1.关闭弹出层，2.刷新表格数据
                showTips(result.message);
//                refreshGrid();
            }else {
                showError(result.message);
            }
		},
	 //async :false,
	  error:function(XmlHttpRequest,textStatus, errorThrown)
	  {
		  showError(XmlHttpRequest.responseText);
	  }
	});
}