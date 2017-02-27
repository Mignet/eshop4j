<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.linkwee.web.enums.DynamicNewsTypeEnum" %>
<%
 request.setAttribute("ctx", request.getContextPath());
DynamicNewsTypeEnum[] dynamicNewsTypeList = DynamicNewsTypeEnum.values();
request.setAttribute("dynamicNewsTypeList", dynamicNewsTypeList);
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
			<form id="uploadZimg">
				<input id="lefile" type="file" name="userfile" onchange="uploadSubmit(this);" style="display:none">
			</form>
			<form id="dynamicnewsForm" action="rest/sm/smdynamicnews/save" method="post">
				<c:if test="${!empty smDynamicNews}">
					<input type="hidden" name="id" value="${smDynamicNews.id}"/>
				</c:if>
				<div class="form-group">
					<label>应用端口</label>
					<select id="appType" class="form-control" name="appType">
						<option value="2" <c:if test="${smDynamicNews.appType eq '2' }">selected</c:if> >T呗</option>
						<option value="1" <c:if test="${smDynamicNews.appType eq '1' }">selected</c:if> >猎财大师</option>						
					</select>
				</div>
				<div class="form-group">
					<label>类别</label>
					<select  name="typeCode" class="form-control">
						<c:forEach var="item" items="${dynamicNewsTypeList }"  varStatus="dn">
							<option <c:if test="${(not empty requestScope.smDynamicNews and requestScope.smDynamicNews.typeCode eq item.key)  }"> selected="selected"</c:if> value="${item.key }">${item.value }</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label>标题</label>
					<input name="title" type="text" class="form-control" value="${smDynamicNews.title}" placeholder="请输入动态标题"  />
				</div>
				<div class="form-group">
					<label>图片</label>
					<div class="input-append">
						<%--<input id="photoCover" autocomplete="off" class="form-control" type="text" placeholder="点击这里选择图片并上传要上传的图片,图片分辨率​259 * 149" readonly="readonly">--%>
						<a class="form-control" href="javascript:;" onclick="$('input[id=lefile]').click(); return false;" style="width: 500px; text-decoration: none; background-color: #1fa67a; color: #ffffff;">点击这里选择图片并上传,图片分辨率​为259 * 149</a>
					</div>
				</div>
				<div class="form-group">
					<input name="img" type="hidden"/>
					<div class="row" id="images-list">
						<c:if test="${ !empty smDynamicNews.img}">
							<img src="${smDynamicNews.img}" id="img-thumbnail" class="image thumbnail" onerror="javascript:$(this).remove();" style="width: 300px;" />
						</c:if>
					</div>
				</div>							
				<div class="form-group">
					<label>发布时间</label>
					<div class="row">
						<div class="col-sm-6">
							<input id="validBegin" name="validBegin" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width:150px" value="${smDynamicNews.validBegin }" />
						</div>
					</div>
				</div>
				<%-- <div class="form-group" >
					<label>到期时间</label>
					<div class="row">
						<div class="col-sm-6">
							<input id="lcsListEndDate" name="validEnd" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'lcsListStartDate\')}'})"  style="width:150px" value="${smDynamicNews.validEnd }" />
						</div>
					</div>
				</div> --%>
				<div class="form-group">
					<label>状态</label>
					<select id="status" class="form-control" name="status">
						<option value="0" <c:if test="${smDynamicNews.status eq '0' }">selected</c:if> >启用</option>
						<option value="1" <c:if test="${smDynamicNews.status eq '1' }">selected</c:if> >删除</option>						
					</select>
				</div>
				<div class="form-group">
					<label>显示顺序</label>
					<input name="showIndex" class="form-control"  value="${smDynamicNews.showIndex }" />
				</div>
				
				<div class="form-group">
					<label>摘要</label>
					<textarea id="summary" name="summary" class="form-control">${smDynamicNews.summary}</textarea>
				</div>
				
				<div class="form-group">
				  <div class="col-md-12">
					  <label>
					     <input type="radio" name="recommend" value="1" id="recommend" checked="checked">正文&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
					 </label>
					 <label>
					     <input type="radio" name="recommend" value="0" id="recommendddd">链接&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
					 </label>
                  </div>    
				</div>
				
				<div class="form-group" id="contentId">
					<label>正文</label>
					<textarea id="content" name="content">${smDynamicNews.content }</textarea>
				</div>
				<div class="form-group" style="display: none;" id="linkUrlId">
					<label>链接</label>
					<input name="linkUrl" class="form-control"  value="${smDynamicNews.linkUrl }" />
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
		var ue = UE.getEditor('content',{serverUrl:"rest/sm/smdynamicnews/ueditor_config",customDomain:true,
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

		var uploadSubmit = function () {
			var file_val = $("#lefile").val();
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


			$("#dynamicnewsForm").submit(function () {
				ue.sync();
			}).validate({
				ignore: "",
				focusInvalid:false,
				errorElement: 'span',
				errorClass: 'help-block help-block-error',
				rules:{
					validBegin:{
						required:true
					},
					title:{
						required:true,
						rangelength:[5,70]
					}
				},
				messages:{				
					validBegin:{
						required:"请选择发布开始时间"
					},
					title:{
						required:"新闻动态标题不能空",
						rangelength:$.format("新闻动态标题的长度必须在{0}到{1}字符之间！")
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
									$.switchPage("新闻动态列表","rest/sm/smdynamicnews/list");
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

