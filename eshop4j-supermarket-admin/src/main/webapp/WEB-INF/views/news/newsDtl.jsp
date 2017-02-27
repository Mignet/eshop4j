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
				<input id="lefile" type="file" name="userfile11" onchange="uploadSubmit(this);" style="display: none"/>
			</form>
			<form id="uploadZicon">
				<input id="iconfile" type="file" name="userfile" onchange="uploadIcon(this);" style="display: none" />
			</form>
			<form id="xnewsForm" action="rest/news/save" method="post">
				<c:if test="${!empty news}">
					<input type="hidden" name="id" value="${news.id}"/>
				</c:if>
				<div class="form-group">
					<label>应用端口</label>
					<select id="appType" class="form-control" name="appType">
						<option value="1" <c:if test="${news.appType eq '1' }">selected</c:if> >理财师</option>
						<option value="2" <c:if test="${news.appType eq '2' }">selected</c:if> >投资者</option>
					</select>
				</div>
				<div class="form-group">
					<label>类别</label>
					<select  name="typeCode" class="form-control">
						<c:forEach var="item" items="${newsTypeList }"  varStatus="dn">
							<option <c:if test="${(not empty requestScope.news and requestScope.news.typeCode eq item.id)  }"> selected="selected"</c:if> value="${item.id }">${item.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label>发布人</label>
					<input name="creator" type="text" class="form-control" value="${news.creator }"  placeholder="请输入发布人名称" />
				</div>
				<div class="form-group">
					<label>标题</label>
					<input name="title" type="text" class="form-control" value="${news.title}" placeholder="请输入资讯标题"  />
				</div>


				<div class="form-group">
					<label>列表图片</label>
					<div class="input-append">
						<%--<input id="photoCover" autocomplete="off" class="form-control" type="text" placeholder="点击这里选择图片并上传要上传的图片,图片分辨率​259 * 149" readonly="readonly">--%>
						<a class="form-control" href="javascript:;" onclick="$('input[id=lefile]').click(); return false;" style="width: 500px; text-decoration: none; background-color: #1fa67a; color: #ffffff;">点击这里选择图片并上传,图片分辨率​为259 * 149</a>
					</div>
				</div>
				<div class="form-group">
					<input name="img" type="hidden"/>
					<div class="row" id="images-list">
						<c:if test="${ !empty news.img}">
							<img src="${news.img}" id="img-thumbnail" class="image thumbnail" onerror="javascript:$(this).remove();" style="width: 300px;" />
						</c:if>
					</div>
				</div>
				
				<div class="form-group">
					<label>分享图标</label>
					<div class="input-append">
						<!-- <input id="iconCover" class="input-large" type="text" placeholder="请选择要上传的图片" style="height:30px;"> -->
						<a class="form-control" href="javascript:;" onclick="$('input[id=iconfile]').click(); return false;"style="width: 500px; text-decoration: none; background-color: #1fa67a; color: #ffffff;">选择图片</a>
					</div>
				</div>
				<div class="form-group">
					<input name="shareIcon" type="hidden" value="${news.shareIcon}"/>
					<div class="row" id="icons-list">
						<c:if test="${ !empty news.shareIcon}">
							<img src="${img_server}${news.shareIcon}"  id="icon-thumbnail" style="width: 300px;" />
						</c:if>
					</div>
				</div>

				<div class="form-group" style="display: none;">
					<label>标签</label>
					<input name="name" type="text" class="form-control" value="${news.name}"  placeholder="请输入资讯标签" />
				</div>
				<div class="form-group">
					<label>发布时间</label>
					<div class="row">
						<div class="col-sm-6">
							<input id="lcsListStartDate" name="validBegin" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'lcsListEndDate\')}'})"  style="width:150px" value="${news.validBegin }" />
						</div>
					</div>
				</div>



				<div class="form-group" style="display: none;">
					<label>到期时间</label>
					<div class="row">
						<div class="col-sm-6">
							<input id="lcsListEndDate" name="validEnd" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'lcsListStartDate\')}'})"  style="width:150px" value="2099" />
						</div>
					</div>
				</div>

				<div class="form-group" style="display: none;">
					<label>是否置顶</label>
					<select id="is_stick" class="form-control" name="isStick">
						<option value="0">不置顶</option>
						<option value="1">置顶</option>
					</select>
				</div>

				<div class="form-group" style="display: none;">
					<label>显示顺序</label>
					<input name="showInx" class="form-control"  value="${news.showInx }" />
				</div>
				
				<div class="form-group">
					<label>摘要</label>
					<textarea id="summary" name="summary" class="form-control">${news.summary}</textarea>
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
					<textarea id="content" name="content">${news.content }</textarea>
					<%--<script id="container" name="content" type="text/plain">${news.content }</script>--%>
				</div>
				<div class="form-group" style="display: none;" id="linkUrlId">
					<label>链接</label>
					<input name="linkUrl" class="form-control"  value="${news.linkUrl }" />
				</div>
						
				<button type="submit" class="btn btn-default">发布</button>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		
	jQuery.validator.addMethod("checkPicSize", function(value,element) {
	    var fileSize=element.files[0].size;
	    var maxSize = 100*1024;
	    if(fileSize > maxSize){
	        return false;
	    }else{
	        return true;
	    }
	}, "请上传大小在100K以下的图片");
	
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
		
		/* $(function(){
			$("#uploadZimg").validate({
				ignore: "",
				focusInvalid:false,
				errorElement: 'span',
				errorClass: 'help-block help-block-error',
			    rules: {
			    	userfile11:{
						checkPicSize:[]
					}
			    },
			    messages: {
			    	
			    },
			    errorPlacement:function (error,element) {
			    	alert("oo");
					element.parent().addClass("has-error");
					error.appendTo(element.parent());
				},
				success:function (element) {
					alert("kk");
					element.closest('.form-group').removeClass('has-error');
					element.remove();
				}
			});
		}); */
		
		var uploadSubmit = function ($file) {
			var file_val = $("#lefile").val();
					
			var fileSize=$file.files[0].size;
			 var maxSize = 100*1024;
			 if(fileSize > maxSize){
			    bootbox.alert("请上传大小在100K以下的图片");
			    $("#lefile").val("");
			    return;
			 }  
			 		
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
							showIcon(zimgUrl+result);
						}
						else{
							console.log("upload fail.");
						}
					}
				});
			}
		}

			$("#xnewsForm").submit(function () {
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
					linkUrl:{
						required:false,
						url:true
					}

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
					linkUrl:{
						url:"必须输入URL地址"
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
									$.switchPage("资讯列表","rest/news/list");
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

