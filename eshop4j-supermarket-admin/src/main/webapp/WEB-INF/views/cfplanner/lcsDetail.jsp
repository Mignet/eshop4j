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
				<input type="text" class="form-control" name="search_mobile" id="search_mobile" placeholder="输入手机号...">
			  <span class="input-group-btn">
				<button class="btn btn-default J_search_cfp" type="button">查询</button>
			  </span>
			</div><!-- /input-group -->
		</div>
	</div>
	<div class="uk-blank"></div>

<c:if test="${empty dtl}">
	<div class="jumbotron">
		<h1>理财师不存在</h1>
		<p>...</p>
	</div>
</c:if>

<c:if test="${!empty dtl}">




	<div class="row">
		<div  class="col-lg-4">理财师：<span style="color: #0b97c4;">${dtl.userName}&nbsp;&nbsp;${dtl.mobile}</span></div>
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
						$.get("rest/lcsList/remove/headimage",{mobile:_m},function ($result) {
							if($result.isFlag){
								$("#wrapper_head_image").remove();
							} else {
								bootbox.alert(result.msg);
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
			<div class="col-sm-8">
				<span>姓名：</span>
					<c:if test="${empty dtl.userName}" >未认证</c:if>
					<c:if test="${!empty dtl.userName}" >${dtl.userName}</c:if>
			</div>
			<div class="col-sm-4"><shiro:hasPermission name="cfp-pwd:modify"><a  class="btn btn-primary" data-toggle="modal" data-target="#J_changepasswd">修改密码</a></shiro:hasPermission></div>
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
				<span id="user_certno"><c:if test="${empty dtl.idCard}" >未绑定</c:if>
					<c:if test="${!empty dtl.idCard}" >${fn:substring(dtl.idCard,0,3)}****${fn:substring(dtl.idCard,fn:length(dtl.idCard)-4,fn:length(dtl.idCard))}</c:if></span>
			</div>
			<div class="col-sm-6"></div>
		</div>

		<div class="row" >
			<div class="col-sm-6"><span>银行卡：</span>
				<span id="user_bankno"><c:if test="${empty dtl.bankCard}" >未绑定</c:if>
					<c:if test="${!empty dtl.bankCard}" >${dtl.bankName} ${fn:substring(dtl.bankCard,0,3)}****${fn:substring(dtl.bankCard,fn:length(dtl.bankCard)-4,fn:length(dtl.bankCard))}</c:if></span>
			</div>
			<div class="col-sm-6"></div>
		</div>


	</div>


	<div class="row">
		<div class="page-header">
			<h4>关系信息</h4>
		</div>
		<div class="row">
			<div class="col-sm-8">上级理财师：<span>${dtl.parentName} ${dtl.parentMobile}</span></div>
			<div class="col-sm-4"><shiro:hasPermission name="cfp-parent:modify"><a  class="btn btn-primary" data-toggle="modal" data-target="#J_changeParent">更改上级</a></shiro:hasPermission></div>
		</div>
		<div class="uk-blank"></div>
		操作记录
		<table style="border:1px solid #dcdcdc;">
		  <tr style="border:1px solid #dcdcdc;">
		    <td style="border:1px solid #dcdcdc;" width="100px">上级帐号</td>
		    <td style="border:1px solid #dcdcdc;" width="100px">上级姓名</td>
		    <td style="border:1px solid #dcdcdc;" width="100px">绑定时间</td>
		    <td style="border:1px solid #dcdcdc;" width="100px">解绑时间</td>
		  </tr>
		  <c:forEach  items="${dtl.changeParentRecordList}" var="item">
		  <tr style="border:1px solid #dcdcdc;">
		    <td style="border:1px solid #dcdcdc;" width="100px">${item.parentMobile}</td>
		    <td style="border:1px solid #dcdcdc;" width="100px">${item.parentName}</td>
		    <td style="border:1px solid #dcdcdc;" width="100px">
		   		<c:if test="${item.type ==2}"> <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></c:if>
		    </td>
		    <td style="border:1px solid #dcdcdc;" width="100px">
		    	<c:if test="${item.type ==3}"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></c:if>
		   	</td>
		  </tr>
		  </c:forEach>
		</table>
	</div>

	<div class="row">
		<div class="page-header">
			<h4>职级信息</h4>
		</div>
		<div class="row">
			<div class="col-sm-8"><span>经验：</span><span>${dtl.experience}</span></div>
			<div class="col-sm-4"><a class="btn btn-primary J_history" href="rest/lcsList/querycfphistory/${dtl.userId}">职级变更记录</a></div>
		</div>
		<div class="uk-blank"></div>
		<div class="row">
			<div class="col-sm-6"><span>职级：</span><span>${dtl.cfpLevel}</span></div>
			<div class="col-sm-6"></div>
		</div>

	</div>


	<%-- <div class="row">
		<div class="page-header">
			<h4>机构信息</h4>
		</div>

			<c:if test="${!empty dtl.department}">
				<div class="row">
					<div class="col-sm-8"><span>所属机构：</span><span>${dtl.department}</span></div>
					<div class="col-sm-4"><shiro:hasPermission name="cfp-group:modify"><a class="btn btn-primary J_torginevent">修改机构</a></shiro:hasPermission></div>
				</div>
			</c:if>
	</div> --%>

	<div class="row">
		<div class="page-header">
			<h4>账户操作</h4>
		</div>

		<form class="form-horizontal">
			<div class="form-group">
				<label class="col-sm-2 "><shiro:hasPermission name="cfp-cfp:delete"><button type="button" class="btn btn-warning" id="J_cancelcfp">取消理财师账号</button></shiro:hasPermission></label>
				<div class="col-sm-10"><label style="display: none;">2015-05-11操作1次</label></div>
			</div>

			<div class="form-group">
				<%-- <label class="col-sm-2"><button type="button" id="btnDisabledLogin" class="btn btn-warning">禁止账号登录</button></label>
				<c:if test="${not empty dtl.disabledLoginTime}">
				<div class="col-sm-10"><label><fmt:formatDate value="${dtl.disabledLoginTime}" pattern="yyyy-MM-dd" />禁止，<fmt:formatDate value="${dtl.disabledLoginEndTime}" pattern="yyyy-MM-dd" />可登录</label></div>
				</c:if> --%>
					<shiro:hasPermission name="cfp-login:modify">
				<label class="col-sm-2"><button type="button" class="btn btn-warning" id="J_banuser">禁止账号登录</button></label>
					<c:if test="${not empty dtl.disabledLoginTime}">
				<div class="col-sm-10"><label><fmt:formatDate value="${dtl.disabledLoginTime}" pattern="yyyy-MM-dd" />禁止，<fmt:formatDate value="${dtl.disabledLoginEndTime}" pattern="yyyy-MM-dd" />可登录</label></div>
				</c:if>
					</shiro:hasPermission>
			</div>
		</form>



	</div>



	<div id="J_changepasswd" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">修改密码</h4>
				</div>
				<div class="modal-body">
					<form id="J_formchange" method="post" action="rest/lcsList/resetpwd">

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
					<h4 class="modal-title">更换上级</h4>
				</div>
				<div class="modal-body">
					<form id="J_formChangeParent" method="post" action="rest/lcsList/changeParent">
						<div class="form-group">
							<label for="newpwd">该操作将使理财师 ${dtl.userName}${dtl.mobile} 与上级 ${dtl.parentName} ${dtl.parentMobile} 解除关系，请选择解绑后理财师归属</label>
						</div>

						<div class="form-group">
							<label for="newpwd"><input type="radio" name="changeType"   value="1" checked="checked" />指定新上级帐号</label>
							<label><input type="text" name="parentMobile" maxlength="11" id='parentMobile' /></label>
						</div>

						<div class="form-group">
							<label for="newpwd"><input type="radio" name="changeType" value="2"/>变为一级理财师</label>
						</div>

						<input type="hidden" value="${dtl.mobile}" name="mobile"/>

					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary J_changeParent_submit">确定</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

	<!--更换机构弹窗-->
	<%-- <div id="J_torgindialog" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">理财师机构修改</h4>
				</div>
				<div class="modal-body">
					<form id="J_torgin" method="post" action="rest/lcsList/replaceLcs">
						<div class="form-group">
							<label>${dtl.userName}</label>
						</div>

						<div class="form-group">
							<label>电&nbsp;&nbsp;话：${dtl.mobile}</label>
						</div>

						<div class="form-group">
							<label for="J_tor_parentId">理财师性质</label>
							<div>
								<select name="parentId" id="J_tor_parentId">
									<c:forEach items="${torlist}" var="row">
										<c:choose>
											<c:when test="${dtl.departmentParentId != row.number}">
												<option value="${row.number}"><c:out value="${row.name}"/></option>
											</c:when>
											<c:otherwise>
												<option selected="selected" value="${row.number}"><c:out value="${row.name}"/></option>
											</c:otherwise>
										</c:choose>

									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="J_department">所属公司</label>
							<div>
								<select name="department" id="J_department">

								</select>
							</div>
						</div>

						<input type="hidden" value="${dtl.mobile}" name="mobile"/>

					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary J_torgin_submit">确定</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal --> --%>



<script type="text/javascript">

	var user_mobile = "${dtl.mobile}";
	var $departmentParentId = "${dtl.departmentParentId}";
	var $departmentId = "${dtl.departmentId}";
	var $cfp_number = "${dtl.id}";
	var $userId = "${dtl.userId}";
	var  $current_url = "rest/lcsList/getLcsDetail?mobile="+user_mobile;
	var  $reload_page =function () {
		$.switchPage("理财师详情",$current_url);
	}

	// 机构
	$("a.J_torginevent").click(function () {
		$("#J_torgindialog").modal("show");
	});

	//提交机构更变
	$(".J_torgin_submit").click(function () {
		bootbox.confirm("该操作将切换用户机构身份，确认修改吗？",function (result) {
			if(result){
				var id = $("#J_department").val();
				$.get("rest/lcsList/replaceLcs",{lcsNumber:$cfp_number,department:id},function (result) {
					$("#J_torgindialog").modal("hide");
					if(result.isFlag){
						bootbox.alert("操作成功",function () {
							$reload_page();
						});

					}
					else{
						bootbox.alert("操作失败");
					}
				},"json");
			}
		});
	});

	
	$("#J_tor_parentId").change(function () {
		var parentId = $(this).val();
		$.get("rest/lcsList/querytorginfonode",{parentid:parentId},function ($result) {
				$("#J_department").html("");
				$.each($result,function ($i,$row) {
					if($row.number == $departmentId){
						$("#J_department").append('<option selected="selected" value="'+$row.number+'">'+$row.name+'</option>');
					}
					else{
						$("#J_department").append('<option value="'+$row.number+'">'+$row.name+'</option>');
					}

				});
		},"json");
	}).trigger("change");





	// 职级记录
	$("a.J_history").click(function () {
			$.get($(this).attr("href"),{},function (html) {
				bootbox.dialog({
					message:html,
					title:"理财师职级变更记录"
				});
			},"html");
		return false;
	});

    // 取消理财账号
	$("#J_cancelcfp").click(function () {
		bootbox.confirm("该操作将使用户退出理财师，变为原上级理财师${dtl.parentName}的客户，确认操作吗？",function (result) {
			if(result){
				$.get("rest/lcsList/exitLcs",{mobile:user_mobile},function (result) {
					if(result.isFlag){
						bootbox.alert("操作成功",function () {
							$.switchPage("理财师列表","rest/lcsList/lcsListPage");
						});
					}
					else{
						bootbox.alert(result.msg);
					}
				});
			}
		});
	})

	/**
	 * 禁止账号登录
	 */
	$("#J_banuser").click(function () {
		bootbox.confirm("该操作将禁止该理财师账号登录，期限90天，确认操作吗？",function (result) {
			if(result){
				$.get("rest/lcsList/disabledLogin",{mobile:user_mobile},function (result) {
					if(result.isFlag){
						bootbox.alert("操作成功");
						reloadUserInfo();
					}
					else{
						bootbox.alert(result.msg);
					}
				})
			}
		});
	});

	jQuery.validator.addMethod("passwd",function (value, element) {
		value = value.replace(/\s/g);
		if(value.length>0){
			return !/^[0-9]+$/.test(value) && !/^[a-zA-Z]+$/.test(value) && /.{6,}$/.test();
		}
		return true;
	});

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
				passwd:true
			},
			newPwdSure:{
				required:true,
				passwd:true,
				equalTo:"#newPwd"
			}
		},
		messages:{
			newPwd:{
				required:"请输入新密码",
				rangelength:$.format("密码的长度必须在{0}到{1}字符之间！"),
				passwd:"密码必须是字母和数字组合,长度至少6位"
			},
			newPwdSure:{
				required:"请输入确认密码",
				rangelength:$.format("确认密码的长度必须在{0}到{1}字符之间！"),
				passwd:"确认密码必须是字母和数字组合,长度至少6位"
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
	
	/* 更换上级 */
	$("#J_formChangeParent").validate({
		focusInvalid:false,
		errorElement: 'span',
		errorClass: 'help-block help-block-error',
		success:function (element) {
			element.closest('.form-group').removeClass('has-error');
			element.remove();
		},
		submitHandler:function (form) {
			$(form).ajaxSubmit({
				success:function (result) {
					if(result.isFlag){
						$("#J_changeParent").modal("hide");
						$("#new_parent_mobile").html($("#parentMobile").val());
						showTips("更换上级成功");
						reloadUserInfo();
					}
					else{
						showTips(result.msg);
					}
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
	
	
	
</script>
</c:if>

<script type="text/javascript">
	// 当前页搜索功能
	$(".J_search_cfp").click(function () {
		var mobile = $("#search_mobile").val();
		mobile = mobile.replace(/(^\s+)|(\s+$)/g, "");
		if(!/^(13[0-9]|15[0-9]|18[0-9]|17[0-9])\d{8}$/.test(mobile)){
			bootbox.alert("请输入正确的手机号码");
			return false;
		}
		var url = "rest/lcsList/getLcsDetail";
		console.log("reloading:"+url);
		$.Go("理财师详情",url,{mobile:mobile});
	});
	
	//重新加载页面
	function reloadUserInfo() {
		var url = "rest/lcsList/getLcsDetail";
		console.log("reloading:"+url);
		setTimeout(function () {
			$.Go("理财师详情",url,{mobile:user_mobile});
		}, 500);
		
	};

</script>
</div>


