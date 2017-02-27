<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.linkwee.web.enums.AdvPlacementEnum" %>
<%
 	request.setAttribute("ctx", request.getContextPath());
	AdvPlacementEnum[] advPlacementList = AdvPlacementEnum.values();
	request.setAttribute("advPlacementList", advPlacementList);
%>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script src="assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-10">
			<form id="uploadZimg">
				<input id="lefile" type="file" name="userfile"
					onchange="uploadImage(this);" style="display: none">
			</form>
			<form id="uploadZicon">
				<input id="iconfile" type="file" name="userfile"
					onchange="uploadIcon(this);" style="display: none">
			</form>
			<form id="advForm" action="<c:if test="${empty actionType}">rest/adv/save</c:if><c:if test="${actionType eq 'edit'}">rest/adv/update</c:if>" method="post">
			<c:if test="${actionType eq 'edit'}">
			<input type="hidden" name="id" value="${dtl.id}"/>
			</c:if>
			
			   <div class="form-group">
					<label>应用类别</label> <select id="appType" class="form-control"
						name="appType" >
						<option value="1"
							<c:if test="${dtl.appType eq '1' }">selected</c:if>>理财端</option>
						<option value="2"
							<c:if test="${dtl.appType eq '2' }">selected</c:if>>投资端</option>
					</select>
				</div>

				<div class="form-group">
					<label>显示位置</label> <select id="pageLocation" class="form-control"
						name="pageLocation" >
						<%-- <option value="app_opening"
							<c:if test="${dtl.pageIndex eq 'app_opening' }">selected</c:if>>开屏</option>
						<option value="app_home_page"
							<c:if test="${dtl.pageIndex eq 'app_home_page' }">selected</c:if>>app首页</option> --%>
							
						<%--<c:forEach var="item" items="${advPlacementList }"  varStatus="dn">
							<option <c:if test="${(not empty requestScope.dtl and requestScope.dtl.pageIndex eq item.key)  }"> selected="selected"</c:if> value="${item.key }">${item.value }</option>
						</c:forEach>--%>
					</select>
				</div>
				
				<div class="form-group">
					<label>图片</label>
					<div class="input-append">
						<input id="photoCover" class="input-large" type="text" placeholder="请选择要上传的图片" style="height:30px;">
						<button class="btn btn-primary" onclick="$('input[id=lefile]').click(); return false;">选择图片</button>
					</div>
				</div>
				<div class="form-group">
					<input name="imgUrl" type="hidden" value="${dtl.imgUrl}"/>
					<div class="row" id="images-list">
						<c:if test="${ !empty dtl.imgUrl}">
							<img src="${dtl.imgUrl}"  id="img-thumbnail" style="width: 300px;" />
						</c:if>
					</div>
				</div>
				
				<div class="form-group" id="imgLinkUrlDiv">
					<label>URL链接地址</label> <input name="linkUrl" type="text" class="form-control"
						value="${dtl.linkUrl }" />
				</div>

				<div class="form-group" id="showIndexDiv" >
					<label>显示排序</label> <input name="showIndex"  class="form-control"
						value="${dtl.showIndex }" />
				</div>

				<div class="form-group">
					<label>状态</label> <select id="status" class="form-control"
						name="status" >
						<option value="0"
							<c:if test="${dtl.status eq '0' }">selected</c:if>>显示</option>
						<option value="-1"
							<c:if test="${dtl.status eq '-1' }">selected</c:if>>不显示</option>
					</select>
				</div>
				
				<div class="form-group">
					<label>有效时间</label>
					<div class="row">
						<div class="col-sm-6">
							<input name="validBeginDate" class="Wdate"
								onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'validEndDate\')}'})"
								style="width: 200px" value="<fmt:formatDate value="${dtl.validBeginDate }" pattern="yyyy-MM-dd HH:mm:ss"/>"
								id="validBeginDate" />至
								<input name="validEndDate"
								onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'validBeginDate\')}'})"
								class="Wdate" style="width: 200px" value="<fmt:formatDate value="${dtl.validEndDate }" pattern="yyyy-MM-dd HH:mm:ss"/>"
								id="validEndDate" />
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label>分享标题</label> <input name="shareTitle" type="text" class="form-control"
						value="${dtl.shareTitle } " maxlength="32"/>
				</div>
				
				<div class="form-group">
					<label>分享详情</label> <input name="shareDesc" type="text" class="form-control"
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
					<%-- <label>分享图标</label> <input name="shareIcon" type="text" class="form-control"
						value="${dtl.shareIcon }" /> --%>
					<input name="shareIcon" type="hidden" value="${dtl.shareIcon}"/>
					<div class="row" id="icons-list">
						<c:if test="${ !empty dtl.shareIcon}">
							<img src="${img_server}${dtl.shareIcon}"  id="icon-thumbnail" style="width: 300px;" />
						</c:if>
					</div>
				</div>
				
				<div class="form-group">
					<label>分享连接</label> <input name="shareLink" type="text" class="form-control"
						value="${dtl.shareLink }" />
				</div>

				<button type="submit" class="btn btn-default">发布</button>
			</form>
		</div>
	</div>
	<script type="text/javascript">
	var zimgUrl = "${img_server}";
	 jQuery.validator.addMethod("isNeedLinkUrl", function(value, element) {
		 if($("#pageLocation").val() =="app_home_page"){
			     return value.length>0;
		 }else{
			 return true;
		 }

		}, "首页banner需设置图片链接地址");   
	//控制图片链接显示
		/* $('#pageLocation').change(function(){ 
			var optVal = $(this).children('option:selected').val();
			if(optVal =='app_opening'){
				$('#imgLinkUrlDiv').css('display','none');
				$('#showIndexDiv').css('display','none');
			}else{
				$('#imgLinkUrlDiv').css('display','block');
				$('#showIndexDiv').css('display','block');
			}		
		}); */
		var showImage =function (url) {
			$("#images-list").html('<img src="'+url+'" class="img-thumbnail"/>');
		}
		var uploadImage = function ($file) {
			if($("#pageLocation option:selected").val() == "app_opening"){
				var fileSize=$file.files[0].size;
				 var maxSize = 150*1024;
				 if(fileSize > maxSize){
				    bootbox.alert("请上传大小在150K以下的图片");
				    $("#lefile").val("");
				    return;
				 }
			}else{
				var fileSize=$file.files[0].size;
				 var maxSize = 100*1024;
				 if(fileSize > maxSize){
				    bootbox.alert("请上传大小在100K以下的图片");
				    $("#lefile").val("");
				    return;
				 }
			}
			
			var file_val = $($file).val();
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
							console.log(result);
							$('input[type=hidden][name=imgUrl]').val(result);
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
							showIcon(zimgUrl+result+"?w=160&h=160");
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
					linkUrl:{
						isNeedLinkUrl:true
						//required:true
					},
					validBeginDate:{
						required:true
					},
					validEndDate:{
						required:true
					}
				},
				messages:{
					linkUrl:{
						required:"链接地址不能为空"

					},
					validBeginDate:{
						required:"请选择发布开始时间"
					},
					validEndDate:{
						required:"请选择发布结束时间"
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
									$.switchPage("广告列表","rest/adv/init");
								});

							}
							else{
								bootbox.alert(result.msg);
							}

						}
					});
				}

			});
			
	        
	        var a =  new Array();
	    	<c:forEach var="item" items="${advPlacementList }"  varStatus="dn">
	    		var json = eval('(' + '{"key":"${item.key }", "value":"${item.value }","appType":"${item.appType}"}' + ')'); 
	    	    a.push(json);
		   </c:forEach>
		  
		   var pageIndex = '${requestScope.dtl.pageIndex}';
	        $(document).ready(function(){
	        	 var appType = $("#appType").children('option:selected').val();
			   for (var i = 0; i < a.length; i++){
	      		 if(a[i].appType =="0" || a[i].appType == appType){
	      			var option = "<option value='"+a[i].key;
       			 	option += "'";
	       			 if(pageIndex == a[i].key){
	       				 option += " selected";
	       			 }
	       			 option += ">"+a[i].value+"</option>";
	       			 $("#pageLocation").append(option);
		      		 }
	      		 
	      	 }        	
	        	
	        }) ;
	        //绑定事件
	        $('#appType').change(function(){      
	        	 var appType = $("#appType").children('option:selected').val();
	        	$("#pageLocation").empty();
	        	 for (var i = 0; i < a.length; i++){
	        		 if(a[i].appType =="0" || a[i].appType == appType){
	        			 var option = "<option value='"+a[i].key;
	        			 option += "'";
	        			 if(pageIndex == a[i].key){
	        				 option += " selected";
	        			 }
	        			 option += ">"+a[i].value+"</option>";
	        			 $("#pageLocation").append(option);
	        		 }
	        		 
	        	 }
	        	
	        	});
	</script>
</div>