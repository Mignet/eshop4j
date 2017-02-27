//定义
//--------------------------本页面业务--------------------------
function addInit(){
	$("#id").val(0);
	$("#username").val("");
	$("#username").removeAttr("readonly");
	//校验
	$("#username").rules('add',{
            	required: true,
            	rangelength:[4,20],
            	remote:{                                          //验证用户名是否存在
                    type:"GET",
                    url:"/rest/user/check",             
                    data:{
                      name:function(){return $("#username").val();}
                    } 
                   }, 
        messages:{required:"用户名不能为空！",rangelength:$.format("用户名位数必须在{0}到{1}字符之间！"),remote:"用户名已经被注册"}                    
	})
	$("#state").val('Y');
	$("#description").val('不同于注册界面,管理界面自动为用户设置初始密码');
}
/**
 * 编辑
 * @param id
 */
function updateInit(id){
	$.ajax({
		url : '/rest/user/' + id,
		type : 'GET',
		success : function(result) {
			$("#id").val(result.id);
			$("#username").val(result.username);
			$("#username").attr("readonly","readonly");
			$("#username").rules("remove"); //修改时不需要验证
			$("#state").val(result.state);
			$("#description").val(result.description);
		},
	  error:function(XmlHttpRequest,textStatus, errorThrown)
	  {
		  console.log(XmlHttpRequest.status);
		  console.log(textStatus);
		  showError(XmlHttpRequest.responseText);
	  }
	});
}
//恢复原始密码
function resetPwd(id) {
	$.ajax({
		url : 'rest/user/'+id+'/resetpwd',
		type:'json',
		type : 'POST',
		success : function(result) {
			 if (result.success) {
                 //保存成功  1.关闭弹出层，2.刷新表格数据
                 showTips(result.message);
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
//删除
function del(id) {
	$.ajax({
		url : '/rest/user/' + id,
		type : 'DELETE',
		success : function(result) {
			 if (result.success) {
                 //保存成功  1.关闭弹出层，2.刷新表格数据
                 showTips(result.message);
                 refreshGrid();
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
var roleList=[];
//分配角色
function setRoles(id){
	$('#userid').val(id);
	$.ajax({
		url : '/rest/user/' + id+'/role',
		type : 'GET',
		success : function(data) {
			 var roles = data.roles;
			 roleList = data.roleList;
			 $('#roles-div').html('');
			 var checks = '';
			 for(var i=0;i<roles.length;i++){
				 var role = roles[i];
				 if(inArray(role,roleList,'id')){
					 checks +=  '<div><input name="ck" type="checkbox" value="'+role.id+'"  checked="checked"/>'+role.roleName+'['+role.description+']'+'</div>'
				 }else{
					 checks +=  '<div><input name="ck" type="checkbox" value="'+role.id+'" />'+role.roleName+'['+role.description+']'+'</div>'
				 }
			 }
			 $('#roles-div').append(checks);
		},
	 //async :false,
	  error:function(XmlHttpRequest,textStatus, errorThrown)
	  {
		  showError(XmlHttpRequest.responseText);
	  }
	});
}
//刷新grid
function refreshGrid(){
	search(1,5);// 初始化第一页数据
}
//搜索
function search(page,rows){
	  var condition = $("#ffSearch").serialize();
	  searchByCondition(page,rows,condition);
}
//提交设置角色
function submitRoles(){
	var roles = '';
	$("input[name='ck']:checked").each(
			function(){
				roles += this.value+",";
	});
	if(roles==''&&roleList.length==0){
		showError('注意:你没有选择任何角色');return;
	}
	$("#roleModal").modal("hide");
	$.ajax({
		url:"/rest/user/"+$("#userid").val()+"/role",
		type : 'POST',
		data:'roles='+roles,
		success : function(result) {
			 if (result.success) {
                //保存成功  1.关闭弹出层，2.刷新表格数据
                showTips(result.message);
                refreshGrid();
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

//条件搜索
function searchByCondition(page,rows,condition){
  var gCondition = condition;
  url = "/rest/user/list?pageNo="+page+"&pageSize="+rows;
  if(condition){
	  url += "&"+condition;
  }
  $.getJSON(url,function(result){
    $("#totalCount").text(result.totalCount);
    $("#totalPageCount").text(result.totalPages);

    $("#grid_body").html('');
 
    $.each(result.datas,function(i,item){
      //遍历显示内容
      var tr = "<tr>";
     /* tr += "<td><input class='checkboxes' type=\"checkbox\" name=\"checkbox\" value=\"+item.id+\"></td> ";*/
      tr += "<td>"+ item.id + "</td>";
      tr += "<td>"+ item.username + "</td>";
      if(item.state=='Y'){
        tr += "<td><span class='label label-success'>有效</span></td>";
      }else{
        tr += "<td><span class='label label-danger'>无效</span></td>";
      }
      tr += "<td>"+ item.createTime + "</td>";
      tr += "<td>"+ item.description + "</td>";
      tr += getActionHtml(item.id);//通过脚本生成按钮列
      tr += "</tr>";
      $("#grid_body").append(tr);
    });
    
  //翻页
    var element=$('#grid_paging');
    if(result.totalCount>0){
        var options = {
            bootstrapMajorVersion:3,
            currentPage:result.pageNo,
            numberOfPages:result.totalCount,
            totalPages:Math.ceil(result.totalCount/result.pageSize),
            onPageChanged:function(event,oldPage,newPage){
            	searchByCondition(newPage,result.pageSize,condition);//paging
            }
        }
        element.bootstrapPaginator(options);
    }else{
        element.html("");
    }
  });
}
//操作按钮
function getActionHtml(id){
	var td = "<td>"
	td += "<button  class=\"btn btn-primary\" type=\"button\"  data-toggle=\"modal\"  data-target=\"#userModal\" onclick=\"updateInit("+id+")\">编辑</button>";
	td += "&nbsp;"
	td += "<button  class=\"btn btn-primary\" type=\"button\" onclick=\"del("+id+")\">删除</button>";
	td += "&nbsp;"
	td += "<button  class=\"btn btn-primary\" type=\"button\" onclick=\"resetPwd("+id+")\">重置为原始密码</button>";
	td += "&nbsp;"
	td += "<button  class=\"btn btn-primary\" type=\"button\"  data-toggle=\"modal\"  data-target=\"#roleModal\" onclick=\"setRoles("+id+")\">分配角色</button>";
	td += "</td>";
	return td;
}
//绑定表单相关事件
function BindEvent() {
	//重置
	$("#reset").click(function(){
			validatorUser.resetForm();
			//clear error msg
			$('.form-group').removeClass('has-error');
	  });

	//判断表单的信息是否通过验证
	var validatorUser = $("#ffAdd").validate({
        meta: "validate",
        errorElement: 'span',
        errorClass: 'help-block help-block-error',
        focusInvalid: false,
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement: function (error, element) {
            element.parent('div').append(error);
        },
        submitHandler: function (form) {
            $("#userModal").modal("hide");
            $(form).ajaxSubmit({
                type:"post",
                url:"/rest/user/create",
                //beforeSubmit: showRequest,for example:are you sure to submit?
                success: function (result) {
                    if (result.success) {
                        //保存成功  1.关闭弹出层，2.刷新表格数据
                        showTips(result.message);
                        refreshGrid();
                    } else {
                        showError(result.message);
                    }
                },
                error:function(XmlHttpRequest,textStatus, errorThrown) {
      			  	console.log(XmlHttpRequest.status);
      			  	console.log(textStatus);
      			  	showError(XmlHttpRequest.responseText);
      		  	}
              });
        }
    });
}

//页面初始化时调用
$(function() {
//	initJsTree(); // 初始化树
	BindEvent(); // 绑定事件处理
	search(1,5);// 初始化第一页数据
//	InitDictItem(); // 初始化字典信息
});
