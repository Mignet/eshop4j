<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
      <%
  request.setCharacterEncoding("UTF-8") ;
 %>

<style type="text/css">
	.uk-blank{ margin: 0; padding: 0; height: 10px; clear: both; overflow: hidden;}
</style>
<div class="container">

	<div class="row">
		<div class="col-lg-4">
			<div class="input-group">
				<input type="text" class="form-control" name="search_mobile" id="search_mobile" placeholder="输入电话...">
			  <span class="input-group-btn">
				<button class="btn btn-default J_search_cfp" type="button">查询</button>
			  </span>
			</div><!-- /input-group -->
		</div>
	</div>
	<div class="uk-blank"></div>

<c:if test="${empty dtl}">
	<div class="jumbotron">
		<h1>用户不存在</h1>
		<p>...</p>
	</div>
</c:if>

<c:if test="${!empty dtl}">


	<div class="row">
		<div  class="col-lg-4">投资端用户：<span style="color: #0b97c4;">${dtl.userName}&nbsp;&nbsp;${dtl.mobile}</span></div>
	</div>
	<div class="uk-blank"></div>
	
	<c:if test="${!empty dtl.headImage}">
		<div class="row" id="wrapper_head_image">
			<div class="page-header">
				<h4><small>头像</small></h4>
			</div>
			<div class="row">
				<div class="col-sm-8"><img src="${dtl.headImage}" class="img-thumbnail" width="200" /></div>
				<div class="col-sm-4"><shiro:hasPermission name="cms-avator:*"><a  class="btn btn-primary J_delete_headimage" data-mobile="${dtl.mobile}">删除头像</a></shiro:hasPermission></div>
			</div>
		</div>
		<div class="uk-blank"></div>
		<script type="text/javascript">
			$("a.J_delete_headimage").click(function () {
				var _m = $(this).attr("data-mobile");
				bootbox.confirm("确认头像是不合规定并执行删除操作吗？",function (result) {
					if(result){
						$.get("rest/invest/removeHeadImage",{mobile:_m},function ($result) {
							if($result.isFlag){
								$("#wrapper_head_image").remove();
							}
						});

					}
				});
			});
		</script>
	</c:if>

	<div class="row">
		<div class="page-header">
			<h4>基本信息</h4>
		</div>
		
		<div class="row">
			<div class="col-sm-6"><span>用户ID：</span><span>${dtl.userId}</span></div>
			<div class="col-sm-6"></div>
		</div>
		
		<div class="uk-blank"></div>
		<div class="row">
			<div class="col-sm-6"><span>帐号：</span><span>${dtl.mobile}</span></div>
			<div class="col-sm-6"></div>
		</div>
		
		<div class="uk-blank"></div>
	    <div class="row">
			<div class="col-sm-8">
				<span>姓名：</span>
				<c:if test="${empty dtl.userName}" >未认证</c:if>
				<c:if test="${!empty dtl.userName}" >${dtl.userName}</c:if>
			</div>
			<div class="col-sm-4"><shiro:hasPermission name="investor-pwd:modify"><a  class="btn btn-primary" data-toggle="modal" data-target="#J_changepasswd">修改密码</a></shiro:hasPermission></div>

		</div>
		<div class="uk-blank"></div>
		<div class="row">
			<div class="col-sm-6"><span>注册时间：</span><span><fmt:formatDate value="${dtl.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span></div>
			<div class="col-sm-6"></div>
		</div>
		<div class="uk-blank"></div>
		<div class="row">
			<div class="col-sm-6"><span>电话：</span><span>${dtl.mobile}</span></div>
			<div class="col-sm-6"></div>
		</div>
		<div class="uk-blank"></div>
		<div class="row" >
			<div class="col-sm-6"><span>身份证：</span>
				<span id="user_certno">
				<c:if test="${!empty dtl.idCard}" >
					${fn:substring(dtl.idCard,0,3)}****${fn:substring(dtl.idCard,fn:length(dtl.idCard)-4,fn:length(dtl.idCard))}
				</c:if>
				<c:if test="${empty dtl.idCard}" >
					未绑定
				</c:if> 
				
			</span>
			</div>
			<div class="col-sm-6"></div>
		</div>

		<div class="row" >
			<div class="col-sm-6"><span>银行卡：</span>
				<span id="user_bankno">
				<c:if test="${!empty dtl.bankCard}" >
					${dtl.bankName} ${fn:substring(dtl.bankCard,0,3)}****${fn:substring(dtl.bankCard,fn:length(dtl.bankCard)-4,fn:length(dtl.bankCard))}
				</c:if>
				<c:if test="${empty dtl.bankCard}">
					未绑定
				</c:if> 
				</span>
			</div>
			<div class="col-sm-6"></div>
		</div>


	</div>


	<div class="row">
		<div class="page-header">
			<h4><small>关系信息</small></h4>
		</div>
		<div class="row">
			<div class="col-sm-8">归属理财师：<span>${dtl.cfplannerName} ${dtl.cfplannerMobile} <c:if test="${dtl.isfreeCustomer==0}">专属</c:if></span></div>
			<div class="col-sm-4"><shiro:hasPermission name="investor-parent:modify"><a  class="btn btn-primary" data-toggle="modal" data-target="#J_changeParent">变更理财师</a></shiro:hasPermission></div>
		</div>
		<div class="uk-blank"></div>
		操作记录
		<table style="border:1px solid #dcdcdc;">
		  <tr style="border:1px solid #dcdcdc;">
		    <td style="border:1px solid #dcdcdc;" width="100px">理财师帐号</td>
		    <td style="border:1px solid #dcdcdc;" width="100px">理财师上级姓名</td>
		    <td style="border:1px solid #dcdcdc;" width="100px">绑定时间</td>
		    <td style="border:1px solid #dcdcdc;" width="100px">解绑时间</td>
		  </tr>
		 <c:forEach  items="${dtl.changeLcsRecordList}" var="item">
		  <tr style="border:1px solid #dcdcdc;">
		    <td style="border:1px solid #dcdcdc;" width="100px">${item.cfplannerMobile}</td>
		    <td style="border:1px solid #dcdcdc;" width="100px">${item.cfplannerName}</td>
		    <td style="border:1px solid #dcdcdc;" width="100px">
		   		<c:if test="${item.type ==1}"> <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></c:if>
		    </td>
		    <td style="border:1px solid #dcdcdc;" width="100px">
		    	<c:if test="${item.type ==2}"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></c:if>
		   	</td>
		  </tr>
		  </c:forEach>
		</table>
	</div>





	<div id="J_changepasswd" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">修改密码</h4>
				</div>
				<div class="modal-body">
					<form id="J_formchange" method="post" action="rest/investor/resetpwd">

						<div class="form-group">
							<label for="newpwd">${dtl.userName}</label>
						</div>

						<div class="form-group">
							<label for="newpwd">电话：${dtl.mobile}</label>
						</div>

						<div class="form-group">
							<label for="newPwd">新密码</label>
							<input type="password" class="form-control" id="newPwd" name="newPwd" placeholder="请输入新密码" />
						</div>

						<div class="form-group">
							<label for="newPwdSure">确认密码</label>
							<input type="password" class="form-control" id="newPwdSure" name="newPwdSure" placeholder="请输入新密码" />
						</div>
						<input type="hidden" value="${dtl.mobile}" name="mobile"/>

					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary J_submit">确定</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	
	<div id="J_changeParent" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">变更理财师</h4>
				</div>
				<div class="modal-body">
					<form id="J_formChangeParent" method="post" action="rest/investor/changeCfplanner">
						<div class="form-group">
							<label for="newpwd">该操作将使理财师 ${dtl.cfplannerName} ${dtl.cfplannerMobile} 与客户${dtl.userName}${dtl.mobile} 解除关系，请选择解绑后理财师归属</label>
						</div>

						<div class="form-group">
							<label for="newpwd"><input type="radio" name="changeType"   value="1" checked="checked" />指定新理财师</label>
							<label><input type="text" name="lcsMobile" maxlength="11" id='lcsMobile' /></label>
						</div>

						<div class="form-group">
							<label for="newpwd"><input type="radio" name="changeType" value="2"/>变为自由客户</label>
						</div>

						<input type="hidden" value="${dtl.mobile}" name="mobile"/>

					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary J_changeParent_submit">确定</button>
				</div>
			</div>
		</div>
	</div>




<script type="text/javascript">

	var user_mobile = "${dtl.mobile}";
	var $userId = "${dtl.userId}";
	var  $current_url = "rest/investor/getInvestorDetail?mobile="+user_mobile;
	var  $reload_page =function () {
		$.switchPage("理财师详情",$current_url);
	}

	/**
	 * 修改登录密码
	 */
	$("#J_formchange").validate({
		focusInvalid:false,
		errorElement: 'span',
		errorClass: 'help-block help-block-error',
		 rules:{
			newPwd:{
				required:true,
				rangelength:[6,20],
			},
			newPwdSure:{
				required:true,
				rangelength:[6,20],
				equalTo:"#newPwd"
			}
		},
		messages:{
			newPwd:{
				required:"请输入新密码",
				rangelength:$.format("密码的长度必须在{0}到{1}字符之间！"),
				checkPassword:"密码必须是字母和数字组合,长度至少6位"
			},
			newPwdSure:{
				required:"请输入确认密码",
				rangelength:$.format("确认密码的长度必须在{0}到{1}字符之间！"),
				checkPassword:"密码必须是字母和数字组合,长度至少6位"
			}
		},
		success:function (element) {
			element.closest('.form-group').removeClass('has-error');
			element.remove();
		},
		submitHandler:function (form) {
			bootbox.confirm("确认修改登录密码?",function (result) {
				if(result){
					$(form).ajaxSubmit({
						success:function (result) {
							if(result.isFlag){
								$("#J_changepasswd").modal("hide");
								bootbox.alert("密码修改成功");
							}
							else{
								bootbox.alert("密码修改失败");
							}
						}
					});
				}
				else{
					$("#J_changepasswd").modal("hide");
				}
			});

		},
		errorPlacement:function (error,element) {
			element.parent().addClass("has-error");
			error.appendTo(element.parent());
		}
	});

	$('.J_submit').click(function() {
		$("#J_formchange").submit();
	});
	
	$("#J_formChangeParent").validate({
		focusInvalid:false,
		errorElement: 'span',
		errorClass: 'help-block help-block-error',
		success:function (element) {
			element.closest('.form-group').removeClass('has-error');
			element.remove();
		},
		submitHandler:function (form) {
			
			bootbox.confirm("确认变更吗?",function (result) {
				if(result){
					$(form).ajaxSubmit({
						success:function (result) {
							if(result.isFlag){
								$("#J_changeParent").modal("hide");
								$("#new_parent_mobile").html($("#parentMobile").val());
								showTips("变更理财师成功");
								reloadUserInfo();
							}
							else{
								showError(result.msg);
							}
						}
					});
				}
				else{
					$("#J_changeParent").modal("hide");
				}
			});
			
		},
		errorPlacement:function (error,element) {
			element.parent().addClass("has-error");
			error.appendTo(element.parent());
		}
	});
	
	$('.J_changeParent_submit').click(function() {
		$("#J_formChangeParent").submit();
	});
	
	
	jQuery.validator.addMethod("checkPassword", function(value, element) {
		value = value.replace(/\s/g);
		if(value.length>0){
			return !/^[0-9]+$/.test(value) && !/^[a-zA-Z]+$/.test(value) && /.{6,}$/.test();
		}
		return true;

		});  
</script>
</c:if>

<script type="text/javascript">
	// 当前页搜索功能
	$(".J_search_cfp").click(function () {
		var mobile = $("#search_mobile").val();
		var url = "rest/investor/getInvestorDetail";
		console.log("reloading:"+url);
		$.Go("用户详情",url,{mobile:mobile});
	});
	
	//重新加载页面
	function reloadUserInfo() {
		var url = "rest/investor/getInvestorDetail";
		console.log("reloading:"+url);
		setTimeout(function () {
			$.Go("用户详情",url,{mobile:user_mobile});
		}, 500);
		
	};

</script>
</div>


