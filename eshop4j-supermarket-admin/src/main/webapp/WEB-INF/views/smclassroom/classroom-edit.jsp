<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
 request.setAttribute("ctx", request.getContextPath());
%>
<%--<link href="assets/plugins/bootstrap-daterangepicker/css/daterangepicker.css" rel="stylesheet" type="text/css" />--%>
<input id="path" type="hidden" value="${ctx}" />
<script type="text/javascript">
	window.UEDITOR_HOME_URL = '${ctx}/assets/plugins/ueditor/';
</script>
<script src="assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
<%--<script type="text/javascript" src="assets/plugins/bootstrap-daterangepicker/js/moment.min.js"  ></script>--%>
<%--<script type="text/javascript" src="assets/plugins/bootstrap-daterangepicker/js/daterangepicker.js"  ></script>--%>
<%--<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>--%>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.zimg.js"></script>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-10">
		    <form id="uploadZimg">
				<input id="lefile" type="file" name="userfile" onchange="uploadSubmit(this);" style="display:none">
			</form>
			<form id="uploadZicon">
				<input id="iconfile" type="file" name="userfile" onchange="uploadIcon(this);" style="display: none" />
			</form>
			<form id="editClassForm" action="rest/acc/smclassroom/save" method="post">
				<c:if test="${!empty classroom}">
					<input type="hidden" name="id" value="${classroom.id}"/>
				</c:if>
				<div class="form-group">
					<label>发布人</label>
					<input name="creator" type="text" class="form-control" value="${classroom.creator }"  placeholder="请输入发布人名称" />
				</div>
				<div class="form-group">
					<label>标题</label>
					<input name="title" type="text" class="form-control" value="${classroom.title}" placeholder="请输入资讯标题"  />
				</div>


				<div class="form-group">
					<label>列表图片</label>
					<div class="input-append">
						<a class="form-control" href="javascript:;" onclick="$('input[id=lefile]').click(); return false;" style="width: 500px; text-decoration: none; background-color: #1fa67a; color: #ffffff;">点击这里选择图片并上传,图片分辨率​为259 * 149</a>
					</div>
				</div>
				<div class="form-group">
					<input name="img" type="hidden"/>
					<div class="row" id="images-list">
						<c:if test="${ !empty classroom.img}">
							<img src="${classroom.img}" id="img-thumbnail" class="image thumbnail" onerror="javascript:$(this).remove();" style="width: 300px;" />
						</c:if>
					</div>
				</div>
				
				<div class="form-group">
					<label>标签</label>
					<input name="label" type="text" class="form-control" value="${classroom.label}"  placeholder="请输入资讯标签" />
				</div>
				<div class="form-group">
					<label>发布时间</label>
					<div class="row">
						<div class="col-sm-6">
							<input id="validBegin" name="validBegin" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'validEnd\')}'})"  style="width:150px" value="${classroom.validBegin }" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label>到期时间</label>
					<div class="row">
						<div class="col-sm-6">
							<input id="validEnd" name="validEnd" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'validBegin\')}'})"  style="width:150px" value="${classroom.validEnd }" />
						</div>
					</div>
				</div>

				
				<div class="form-group">
					<label>状态</label>
					<select id="status" class="form-control" name="status">
<!-- 						<option value="0">启用</option> -->
<!-- 						<option value="1">关闭</option> -->
						<option value="0" <c:if test="${classroom.status eq '0' }">selected</c:if> >启用</option>
						<option value="1" <c:if test="${classroom.status eq '1' }">selected</c:if> >关闭</option>
					</select>
				</div>

				<div class="form-group">
					<label>显示顺序</label>
					<input name="showInx" class="form-control"  value="${classroom.showInx }" />
				</div>
				
<!-- 				<div class="form-group"> -->
<!-- 					<label>分享图片链接</label> -->
<%-- 					<textarea id="shareLink" name="shareLink" class="form-control">${classroom.shareLink}</textarea> --%>
<!-- 				</div> -->
				
				<div class="form-group">
					<label>分享图标</label>
					<div class="input-append">
						<a class="form-control" href="javascript:;" onclick="$('input[id=iconfile]').click(); return false;" style="width: 500px; text-decoration: none; background-color: #1fa67a; color: #ffffff;">点击这里选择图片并上传,图片分辨率​为259 * 149</a>
					</div>
				</div>
				<div class="form-group">
					<input name="shareIcon" type="hidden"/>
					<div class="row" id="images-showIcon">
						<c:if test="${ !empty classroom.shareIcon}">
							<img src="${classroom.shareIcon}" id="img-thumbnail" class="image thumbnail" onerror="javascript:$(this).remove();" style="width: 300px;" />
						</c:if>
					</div>
				</div>
				
				<div class="form-group">
					<label>分享标题</label>
					<textarea id="shareTitle" name="shareTitle" class="form-control">${classroom.shareTitle}</textarea>
				</div>
				
				<div class="form-group">
					<label>分享描述</label>
					<textarea id="shareDesc" name="shareDesc" class="form-control">${classroom.shareDesc}</textarea>
				</div>

				<div class="form-group">
					<label>摘要</label>
					<textarea id="summary" name="summary" class="form-control">${classroom.summary}</textarea>
				</div>
				
				<div class="form-group">
				  <div class="col-md-12">
						  <label>
						     <input type="radio" name="recommend" value="1" id="recommend" checked="checked">正文&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						 </label>
						 <label>
						     <input type="radio" name="recommend" value="0" id="recommend">链接&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						 </label>
                      </div>    
				</div>
				
				 <div class="form-group" style="display: none;" id="linkUrlId">
                      <div class="form-group">
                          <label class="control-label col-md-3">设置链接地址：</label>
                          <div class="col-md-7">
                               <input type="text" class="form-control" id="linkUrl" name="linkUrl" placeholder="请输入链接地址"  value="${classroom.linkUrl }" >
                          </div>
                      </div>
                  </div>
                  
				
				<div class="form-group" id="contentId">
					<label>正文</label>
					<textarea id="content" name="content">${classroom.content }</textarea>
				</div>
				<button type="submit" class="btn btn-default">发布</button>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		var zimgUrl = "${img_server}";

		var previewImage = function (evt) {
			if (!window.FileReader) return;
			var files = evt.target.files;
			for (var i = 0, f; f = files[i]; i++) {
				if (!f.type.match('image.*')) {
					continue;
				}
				var reader = new FileReader();
				reader.onload = (function(theFile) {
					return function(e) {
						// img 元素
						$("#images-list").html("");
						var im = document.getElementById("#images-list");
						var pim = document.createElement("img");
						pim.setAttribute("class","img-thumbnail");
						pim.src = e.target.result;
						pim.setAttribute("style","max-width:300px;");
						im.appendChild(pim);
						$("#images-list").html(im);

					};
				})(f);
				reader.readAsDataURL(f);
			}
		}

		jQuery.validator.addMethod("zn",function (value, element) {
			value = value.replace(/\s/g);
			if(value.length>0){
				return /^[\u4e00-\u9fa5a-z_A-Z0-9]*$/.test(value);
			}
			return true;
		});
		var ue = UE.getEditor('content',{serverUrl:"rest/news/ueditor_config",customDomain:true,
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
		var showImage =function (url) {
			$("#images-list").html('<img src="'+url+'" class="img-thumbnail"/>');
		}
		
		var showIcon =function (url) {
			$("#images-showIcon").html('<img src="'+url+'" class="img-thumbnail"/>');
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
			if($file.files.length>0){
				$("#photoCover").val($file.files[0].name);
			}
			return false;
		};
		var uploadSubmit = function () {
			var file_val = $("#lefile").val();
//			debugger;
			if(file_val.length>0){
				var fileData = new FormData(document.getElementById("uploadZimg"));
				$.ajax({
					type:'post',
					url: zimgUrl,
					data: fileData,
					async: false,
					cache: false,
					contentType: false,
					processData: false,
					success:function (data) {
						if(data.indexOf("MD5")!=-1){
							var result =  data.substring(data.indexOf("MD5:")+4,data.indexOf(","));
							$('input[type=hidden][name=img]').val(result);
							showImage(zimgUrl+result+"?w=160&h=160");
						}
						else{
							console.log("upload fail.");
						}
					}
				});
			}
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
					url: zimgUrl,
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
							showIcon(zimgUrl+result+"?w=160&h=160");
						}
						else{
							console.log("upload fail.");
						}
					}
				});
			}
		}


			$("#editClassForm").submit(function () {
				ue.sync();
			}).validate({
				ignore: "",
				focusInvalid:false,
				errorElement: 'span',
				errorClass: 'help-block help-block-error',
				rules:{
					creator:{
						required:true,
						zn:true

					},
					validBegin:{
						required:true
					},

					title:{
						required:true,
						rangelength:[5,70]
					},
// 					linkUrl:{
// 						required:false,
// 						url:true
// 					}

				},
				messages:{
					creator:{
						required:"发布人不能为空",
						zn:'只能输入中文、英文、数字、下划线'

					},
					validBegin:{
						required:"请选择发布开始时间"
					},

					title:{
						required:"资讯标题不能空",
						rangelength:$.format("资讯标题的长度必须在{0}到{1}字符之间！")
					},
// 					linkUrl:{
// 						url:"必须输入URL地址"
// 					}

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
                       console.log("form");
                       console.log(form);
//					uploadSubmit();
					$(form).ajaxSubmit({
						success:function (result) {
							if(result.isFlag){
								bootbox.alert(result.msg,function () {
									$.switchPage("课堂列表","rest/acc/smclassroom/list");
								});
							}
							else{
								bootbox.alert(result.msg);
							}

						}
					});
				}

			});
			
			 $("input:radio[name='recommend']:eq(0)").click(function(){
			    	$("#contentId").show(); 
			    	$("#linkUrlId").hide();
			    });
			 
			 $("input:radio[name='recommend']:eq(1)").click(function(){
			    	$("#contentId").hide();
			    	$("#linkUrlId").show();
			    });
	</script>
</div>

