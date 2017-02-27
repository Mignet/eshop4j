<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script src="assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-10">		
			<form id="xnewsClassifyForm" action="rest/news/classify/save" method="post">
				<c:if test="${!empty classify}">
					<input type="hidden" name="id" value="${classify.id}"/>
				</c:if>
							
				<div class="form-group">
					<label>分类名称</label>
					<input name="name" type="text" class="form-control" value="${classify.name}" placeholder="请输入资讯标题"  />
				</div>
				<div class="form-group">
					<label>分类排序</label>
					<input name="showIndex" class="form-control"  value="${classify.showIndex }" />
				</div>	
				<div class="form-group">
					<label>应用端口</label>
					<select id="appType" class="form-control" name="appType">
						<option value="1" <c:if test="${classify.appType eq '1' }">selected</c:if> >猎财大师</option>
						<option value="2" <c:if test="${classify.appType eq '2' }">selected</c:if> >T呗</option>
					</select>
				</div>		
				<button type="submit" class="btn btn-default">发布</button>
			</form>
		</div>
	</div>
	<script type="text/javascript">
	
		$("#xnewsClassifyForm").submit(function () {
			//ue.sync();
		}).validate({
			ignore: "",
			focusInvalid:false,
			errorElement: 'span',
			errorClass: 'help-block help-block-error',
			rules:{
				name:{
					required:true
				}
			},
			messages:{			
				name:{
					required:"请填写分类名称"
				}
			},
			errorPlacement:function (error,element) {
				element.parent().addClass("has-error");
				error.appendTo(element.parent());
			},
			success:function (element) {
				element.closest('.form-group').removeClass('has-error');
				element.remove();
			},
	
			submitHandler:function (form) {
				$(form).ajaxSubmit({
					success:function (result) {
						if(result.isFlag){
							bootbox.alert(result.msg,function () {
								$.switchPage("资讯分类列表","/rest/news/classify/list");
							});
						}
						else{
							bootbox.alert(result.msg);
						}
	
					}
				});
			}
		});
	</script>
</div>

