<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
 request.setAttribute("ctx", request.getContextPath());
%>
<input id="path" type="hidden" value="${ctx}" />
<script type="text/javascript">
	window.UEDITOR_HOME_URL = '${ctx}/assets/plugins/ueditor/';
</script>
<script src="assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.zimg.js"></script>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-10">
			<form id="cimProductEditForm" action="rest/cim/cimproductedit/save" method="post">
				<c:if test="${!empty cimProductEdit}">
					<input type="hidden" name="id" value="${cimProductEdit.id}"/>
				</c:if>
				<div class="form-group">
					<label>机构名称</label>
					<select name="orgNumber" class="form-control">
						<option value="">请选择机构名称</option>
						<c:forEach var="item" items="${orgList}"  varStatus="dn">
							<option <c:if test="${(not empty requestScope.cimProductEdit and requestScope.cimProductEdit.orgNumber eq item.orgNumber)}"> selected="selected"</c:if> value="${item.orgNumber}">${item.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label>产品名称</label>
					<input name="productName" type="text" class="form-control" value="${cimProductEdit.productName}"  placeholder="请输入产品名称" />
				</div>
							
				<div class="form-group" id="productDescId">
					<label>产品描述</label>
					<textarea id="product_desc" name="productDesc">${cimProductEdit.productDesc}</textarea>
				</div>
							
				<button type="submit" class="btn btn-default">确定</button>
			</form>
		</div>
	</div>
	<script type="text/javascript">	
		var zimgUrl = "${img_server}";
		
		jQuery.validator.addMethod("zn",function (value, element) {
			value = value.replace(/\s/g);
			if(value.length>0){
				return /^[\u4e00-\u9fa5a-z_A-Z0-9]*$/.test(value);
			}
			return true;
		});
		var ue = UE.getEditor('product_desc',{serverUrl:"rest/cim/cimproductedit/ueditor_config",customDomain:true,
			zimg:zimgUrl,
			zIndex:9996,
			autoHeightEnabled:false,
			initialFrameHeight:500,
			toolbars: [[
				'fullscreen', 'source', '|', 'undo', 'redo', '|',
				'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
				'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
				'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
				'directionalityltr', 'directionalityrtl', 'indent', '|',
				'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
				'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
				  'scrawl', 'insertvideo', 'music', 'attachment', 'map', 'gmap', 'insertframe', 'insertcode', 'pagebreak', 'template', 'background', '|',
				'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
				'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
				'print', 'preview', 'searchreplace', 'drafts'
			]]
		});
		

		$("#cimProductEditForm").submit(function () {
			ue.sync();
		}).validate({
			ignore: "",
			focusInvalid:false,
			errorElement: 'span',
			errorClass: 'help-block help-block-error',
			rules:{
				orgNumber:{
					required:true
				},
				productName:{
					required:true
				},
				productDesc:{
					required:true
				}
			},
			messages:{
				orgNumber:{
					required:"机构名称不能为空"
				},
				productName:{
					required:"产品名称不能为空"
				},
				productDesc:{
					required:"产品描述不能为空"					
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
								$.switchPage("产品详情编辑列表","rest/cim/cimproductedit/list");
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

