<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
 request.setAttribute("ctx", request.getContextPath());
%>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script src="assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-10">
			<form id="uploadZimg">
				<input id="lefile" type="file" name="userfile" onchange="uploadImage(this);" style="display: none" />
			</form>
			<form id="uploadZicon">
				<input id="iconfile" type="file" name="userfile" onchange="uploadIcon(this);" style="display: none" />
			</form>
			<form id="uploadZplatformImg">
				<input id="platformfile" type="file" name="userfile" onchange="uploadPlatformImg(this);" style="display: none" />
			</form>
			<form id="advForm" action="<c:if test="${empty actionType}">rest/cms/activitylist/save</c:if><c:if test="${actionType eq 'edit'}">rest/cms/activitylist/update</c:if>" method="post">
			<c:if test="${actionType eq 'edit'}">
			<input type="hidden" name="id" value="${dtl.id}"/>
			</c:if>
			
				<div class="form-group">
					<label>应用类别</label> <select id="appType" class="form-control"
						name="appType" >
						<option value="1"
							<c:if test="${dtl.appType eq '1' }">selected</c:if>>理财师端</option>
						<option value="2"
							<c:if test="${dtl.appType eq '2' }">selected</c:if>>投资端</option>
					</select>
				</div>
				<div class="form-group">
					<label>置为封面</label> <select id="isCover" class="form-control"
						name="isCover" >
						<option value="0"
							<c:if test="${dtl.isCover eq '0' }">selected</c:if>>否</option>
						<option value="1"
							<c:if test="${dtl.isCover eq '1' }">selected</c:if>>是</option>
					</select>					
			    </div>
			    
			    <div class="form-group">
					<label>活动平台</label> <select id="activityPlatform" class="form-control"
						name="activityPlatform" >
						<option value="猎财大师" <c:if test="${dtl.activityPlatform eq '猎财大师' }">selected</c:if> >猎财大师</option>
						<option value="T呗" <c:if test="${dtl.activityPlatform eq 'T呗' }">selected</c:if>>T呗</option>
						<c:forEach var="item" items="${activityPlatformList }" varStatus="dn">
                            <option value="${item.name }" <c:if test="${dtl.activityPlatform eq item.name }">selected</c:if>>${item.name }</option>
                        </c:forEach>
					</select>
				</div>
				
				<div class="form-group">
					<label>活动标题</label> <input name="activityName" type="text" class="form-control"  maxlength="50"
						value="${dtl.activityName }" />
				</div>
				<div class="form-group" style="display: none;">
					<label>活动点评</label> <input name="activityDesc" type="text" class="form-control"  maxlength="255"
						value="${dtl.activityDesc }" />
				</div>
			    
				<div class="form-group">
					<label>活动列表页活动图片</label>
					<div class="input-append">
						<input id="photoCover" class="input-large" type="text" placeholder="请选择要上传的图片" style="height:30px;">
						<button class="btn btn-primary" onclick="$('input[id=lefile]').click(); return false;">选择图片</button>
					</div>
				</div>
				<div class="form-group">
					<input name="activityImg" type="hidden" value="${dtl.activityImg}"/>
					<div class="row" id="images-list">
						<c:if test="${ !empty dtl.activityImg}">
							<img src="${imgServerUrl}${dtl.activityImg}"  id="img-thumbnail" style="width: 1080px;" />
						</c:if>
					</div>
				</div>
				
				<div class="form-group">
					<label>平台信息页活动图片</label>
					<div class="input-append">
						<input id="platformCover" class="input-large" type="text" placeholder="请选择要上传的图片" style="height:30px;">
						<button class="btn btn-primary" onclick="$('input[id=platformfile]').click(); return false;">选择图片</button>
					</div>
				</div>
				<div class="form-group">
					<input name="platformImg" type="hidden" value="${dtl.platformImg}"/>
					<div class="row" id="platformImg-list">
						<c:if test="${ !empty dtl.platformImg}">
							<img src="${imgServerUrl}${dtl.platformImg}"  id="img-thumbnail" style="width: 1080px;" />
						</c:if>
					</div>
				</div>
							
			<!-- 
				<div class="form-group" >
					<label>活动图标连接</label> <input name="activityImg" type="text" class="form-control"  maxlength="100"
						value="${dtl.activityImg }" />
				</div>

				<div class="form-group">
					<label>结束图片地址</label> <input name="activityEndImg" type="text" class="form-control"  maxlength="100"
						value="${dtl.activityEndImg }" />
				</div>
			 -->	
				<div class="form-group" >
					<label>活动连接</label> <input name="linkUrl"  class="form-control"  maxlength="100"
						value="${dtl.linkUrl }" />
				</div>

				<div class="form-group">
					<label>有效时间</label>
					<div class="row">
						<div class="col-sm-6">
							<input name="startDate" class="Wdate"
								onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}'})"
								style="width: 200px" value="<fmt:formatDate value="${dtl.startDate }" pattern="yyyy-MM-dd HH:mm:ss"/>"
								id="startDate" />至
								<input name="endDate"
								onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}'})"
								class="Wdate" style="width: 200px" value="<fmt:formatDate value="${dtl.endDate }" pattern="yyyy-MM-dd HH:mm:ss"/>"
								id="endDate" />
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label>显示排序</label> <input name="showIndex" type="text" class="form-control"  maxlength="50"
						value="${dtl.showIndex }" />
				</div>
				
				<%-- <div class="form-group">
					<label>状态</label> <select id="status" class="form-control"
						name="status" >
						<option value="0"
							<c:if test="${dtl.status eq '0' }">selected</c:if>>启用</option>
						<option value="1"
							<c:if test="${dtl.status eq '1' }">selected</c:if>>禁用</option>
					</select>
				</div> --%>
						
				<div class="form-group">
					<label>分享标题</label> <input name="shareTitle" type="text" class="form-control"  maxlength="64"
						value="${dtl.shareTitle }" />
				</div>
				
				<div class="form-group">
					<label>分享摘要</label> <input name="shareDesc" type="text" class="form-control"  maxlength="128"
						value="${dtl.shareDesc }" />
				</div>
					
				<div class="form-group">
					<label>分享图标</label>
					<div class="input-append">
						<input id="iconCover" class="input-large" type="text" placeholder="请选择要上传的图片" style="height:30px;">
						<button class="btn btn-primary" onclick="$('input[id=iconfile]').click(); return false;">选择图片</button>
					</div>
				</div>
				<div class="form-group">
					<input name="shareIcon" type="hidden" value="${dtl.shareIcon}"/>
					<div class="row" id="icons-list">
						<c:if test="${ !empty dtl.shareIcon}">
							<img src="${imgServerUrl}${dtl.shareIcon}"  id="icon-thumbnail" style="width: 300px;" />
						</c:if>
					</div>
				</div>
				
				<div class="form-group">
					<label>分享连接</label> <input name="shareLink" type="text" class="form-control"  maxlength="128"
						value="${dtl.shareLink }" />
				</div>

				<button type="submit" class="btn btn-default">保存</button>
			</form>
		</div>
	</div>
	
<script type="text/javascript">

	 var imgServerUrl = "${imgServerUrl}"; //图片服务器
	 
	 $.extend($.validator.defaults,{ignore:""});
	 
	 jQuery.validator.addMethod("isNeedLinkUrl", function(value, element) {
		 if($("#pageLocation").val() =="app_home_page"){
			     return value.length>0;
		 }else{
			 return true;
		 }

		}, "首页banner需设置图片链接地址");   
	//控制图片链接显示
		$('#pageLocation').change(function(){ 
		var optVal = $(this).children('option:selected').val();
		if(optVal =='app_opening'){
			$('#imgLinkUrlDiv').css('display','none');
			$('#showIndexDiv').css('display','none');
		}else{
			$('#imgLinkUrlDiv').css('display','block');
			$('#showIndexDiv').css('display','block');
		}
		
		});
		var showImage =function (url) {
			$("#images-list").html('<img src="'+url+'" class="img-thumbnail"/>');
		}
		
		
		var uploadImage = function ($file) {
			var fileSize=$file.files[0].size;
			 var maxSize = 100*1024;
			 if(fileSize > maxSize){
			    bootbox.alert("请上传大小在100K以下的图片");
			    $("#lefile").val("");
			    return;
			 }
			var file_val = $($file).val();
			if(file_val.length>0){
				var fileData = new FormData(document.getElementById("uploadZimg"));
				$.ajax({
					type:'post',
					url: imgServerUrl,
					data: fileData,
					async: false,
					cache: false,
					contentType: false,
					processData: false,
					success:function (data) {
						if(data.indexOf("MD5")!=-1){
							var result =  data.substring(data.indexOf("MD5:")+4,data.indexOf(","));
							console.log(result);
							$('input[type=hidden][name=activityImg]').val(result);
							showImage(imgServerUrl+result);
						}
						else{
							console.log("upload fail.");
						}
					}
				});
			}
		}
		
		var showIcon =function (url) {
			$("#icons-list").html('<img src="'+url+'" class="img-thumbnail"/>');
		}
		var uploadIcon = function ($file) {
			var fileSize=$file.files[0].size;
			 var maxSize = 100*1024;
			 if(fileSize > maxSize){
			    bootbox.alert("请上传大小在100K以下的图片");
			    $("#iconfile").val("");
			    return;
			 }
			var file_val = $($file).val();
			if(file_val.length>0){
				var fileData = new FormData(document.getElementById("uploadZicon"));
				$.ajax({
					type:'post',
					url: imgServerUrl,
					data: fileData,
					async: false,
					cache: false,
					contentType: false,
					processData: false,
					success:function (data) {
						if(data.indexOf("MD5")!=-1){
							var result =  data.substring(data.indexOf("MD5:")+4,data.indexOf(","));
							console.log(result);
							$('input[type=hidden][name=shareIcon]').val(result);
							showIcon(imgServerUrl+result);
						}
						else{
							console.log("upload fail.");
						}
					}
				});
			}
		}
		
		var showPlatformImg =function (url) {
			$("#platformImg-list").html('<img src="'+url+'" class="img-thumbnail"/>');
		}
		var uploadPlatformImg = function ($file) {
			var fileSize=$file.files[0].size;
			 var maxSize = 100*1024;
			 if(fileSize > maxSize){
			    bootbox.alert("请上传大小在100K以下的图片");
			    $("#platformfile").val("");
			    return;
			 }
			var file_val = $($file).val();
			if(file_val.length>0){
				var fileData = new FormData(document.getElementById("uploadZplatformImg"));
				$.ajax({
					type:'post',
					url: imgServerUrl,
					data: fileData,
					async: false,
					cache: false,
					contentType: false,
					processData: false,
					success:function (data) {
						if(data.indexOf("MD5")!=-1){
							var result =  data.substring(data.indexOf("MD5:")+4,data.indexOf(","));
							console.log(result);
							$('input[type=hidden][name=platformImg]').val(result);
							showPlatformImg(imgServerUrl+result);
						}
						else{
							console.log("upload fail.");
						}
					}
				});
			}
		}

		
			$("#advForm").validate({
				focusInvalid:false,
				errorElement: 'span',
				errorClass: 'help-block help-block-error',
				rules:{
					activityName:{
						required:true
					},
					activityImg:{
						required:true
					},
					linkUrl:{
						required:true
					},
					startDate:{
						required:true
					},
					endDate:{
						required:true
					},
					activityEndImg:{
						required:true
					},
					shareTitle:{
						required:true
					},
					shareDesc:{
						required:true
					},
					shareIcon:{
						required:true
					},
					platformImg:{
						required:true
					}
				},
				messages:{
					activityName:{
						required:"名称不能为空"
					},
					activityImg:{
						required:"活动列表页活动图片不能为空"
					},
					linkUrl:{
						required:"活动连接不能为空"
					},
					startDate:{
						required:"请选择发布开始时间"
					},
					endDate:{
						required:"请选择发布结束时间"
					},
					activityEndImg:{
						required:"活动结束图片地址不能为空"
					},
					shareTitle:{
						required:"活动分享标题不能为空"
					},
					shareDesc:{
						required:"活动分享摘要不能为空"
					},
					shareIcon:{
						required:"活动分享图标不能为空"
					},
					platformImg:{
						required:"平台信息页活动图片不能为空"
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
									$.switchPage("活动列表管理","rest/cms/activitylist/list");
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