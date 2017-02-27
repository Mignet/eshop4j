<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="container">
<form id="redpacketFrom">
<input type="hidden" value="${pushItem.id }" name="id">
			<!-- <div class="page-header">
				<div class="row">
					<div class="col-md-8">
						<h4><small><p class="text-muted">推送基本信息</p></small></h4>
					</div>
				</div>
			</div> -->
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">推送对象(1理财师,2投资者):</label>
				<div class="col-md-4">
					<input type="radio" value="1"  <c:if test="${pushItem.appType == 1 || empty pushItem}">checked="checked"</c:if>   name="appType">理财师 
					<input type="radio" value="2"  <c:if test="${pushItem.appType == 2 }">checked="checked"</c:if> name="appType">投资者
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">推送内容:</label>
				<div class="col-md-4">
					  <input type="text" class="form-control" name="content" autocomplete="off" placeholder="推送内容" value="${pushItem.content }"/>
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">推送跳转链接:</label>
				<div class="col-md-4">
					 <input type="text" class="form-control" name="link" autocomplete="off" placeholder="推送跳转链接"  value="${pushItem.link }"/>
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">状态(0即时,1定时):</label>
				<div class="col-md-4">
					 <input type="radio" value="0" id="startTypeRightNow" <c:if test="${pushItem.startType == 0 }">checked="checked"</c:if> name="startType">即时
					<input type="radio" value="1" id="startTypeTimer" <c:if test="${pushItem.startType == 1 || empty pushItem }">checked="checked"</c:if> name="startType">定时
				</div>
			</div>
			
			<div id="startTime_div" style="display:block;">
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">定时发送时间:</label>
				<div class="col-md-4">
					  <input type="text" class="form-control" name="startTime" value="<fmt:formatDate value="${pushItem.startTime}" pattern="yyyy-MM-dd HH:mm" />" autocomplete="off" placeholder="定时发送时必填，即时发送可不填" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:ss'})"/>
				</div>
			</div>
			</div>
			
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">发送对象,全部or指定用户:</label>
				<div class="col-md-4">
					 <input type="radio" value="0"  name="sendObjectType" id = "sendObjectType_all" <c:if test="${pushItem.sendObjectType == 0 || empty pushItem}">checked="checked"</c:if> >全部
					<input type="radio" value="1"  name="sendObjectType" id = "sendObjectType_import" <c:if test="${pushItem.sendObjectType == 1 }">checked="checked"</c:if> >指定用户
				</div>
			</div>
			<br>
			<div id="import_file_div" style="display:none;">
				<div class="row">
				<label class="col-sm-2 control-label">导入推送对象:</label>
				<div class="col-md-4">
					  <input id="datafile" class="form-control" type="file" name="file" />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label"></label>
				<div class="col-md-4">
					  <span	style="color: red; font-size: 10px;">请点击下载<a href="/rest/cim/syspushartificialqueue/downloadImportTemplate">《模板》</a>，仅支持xls文件，最大10000条.</span>
				</div>
			</div>
			<br>
			</div>

		<div id="file_content_div" style="display: <c:if test="${empty pushItem}">none</c:if><c:if test="${ ! empty pushItem.userIds} ">block</c:if>;">
			<div class="row">
				<label class="col-sm-2 control-label">推送对象:</label>
				<div class="col-md-4">
					<textarea rows="10" cols="40">
					<c:forEach items="${pushItem.mobileList}" var="mobile">
			          ${mobile}
	                </c:forEach>
					</textarea>
				</div>
			</div>
			<br>
		</div>

		<div class="row">
			 <div class="col-sm-2 col-sm-offset-4">
			 	<a class="btn btn-danger J_sendRedpacket"  role="button" >保存</a>&nbsp;&nbsp;&nbsp;
			 	<a class="btn btn-default" data-dismiss="modal"  role="button" >关闭</a>
			</div>
		</div>
		
</form>
</div>

<script type="text/javascript"  src="assets/plugins/layer/layer.js"></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	//点击导入推送显示模板
 	$("#sendObjectType_import").click(function() {
 		$("#import_file_div").show();
 	});
 	$("#sendObjectType_all").click(function() {
 		$("#import_file_div").hide();
 	});
 	//控制定时输入的显示
 	$("#startTypeTimer").click(function() {
 		$("#startTime_div").show();
 	});
 	$("#startTypeRightNow").click(function() {
 		$("#startTime_div").hide();
 	});
	

	//开始推送
 	$(".J_sendRedpacket").click(function() {
 		var appType = $("input[name='appType']:checked").val();//推送对象
 		var content = $("input[name=content]").val();
 		var link = $("input[name=link]").val();
 		var startType = $("input[name='startType']:checked").val();
 		var startTime = $("input[name=startTime]").val();//发送时间
 		var sendObjectType = $("input[name='sendObjectType']:checked").val();//发送对象
 		var id = $("input[name=id]").val();

 		if(content == ""){
 			layer.msg("推送内容不能为空！",{time: 2000,icon: 0});
			return;
 		}
 		
 		if(link == "" ){
 			layer.msg("推送跳转链接不能为空！",{time: 2000,icon: 0});
			return;
 		}
 		
 		var file = $('#datafile').val(); //获取文件名
 		var ext = file.substring(file.lastIndexOf(".")+1).toLowerCase(); //获取文件后缀名
 		if(sendObjectType == "1" && ext != "xls"){
 			layer.msg("不是xls结尾的文件！",{time: 2000,icon: 0});
			return;
 		}
 		var formData = new FormData();
 		formData.append('file', $('#datafile')[0].files[0]);
 		formData.append('appType',appType);
 		formData.append('content',content);
 		formData.append('link',link);
 		formData.append('startType',startType);
 		formData.append('startTime',startTime);
 		formData.append('sendObjectType',sendObjectType);
 		formData.append('id',id);
 		
 		$.ajax({
 	 		    url: './rest/cim/syspushartificialqueue/addSysPush',
 	 		    type: 'POST',
 	 		    cache: false,
 	 		    data: formData,
 	 		    processData: false,
 	 		    contentType: false,
 	 		    success: function(data) {
 	 		    	if(data.isFlag == false){
 	 		    		layer.alert(data.msg, {icon: 0}); //失败
 	 		    	}else{
 	 		    		layer.msg(data.msg,{time: 2000,icon: 1}); //成功
 	 		    	}
 	 		    	$('#publicModal').modal("hide"); //隐藏
 	 		    	var table = $('#dtable').DataTable();
 	 		    	table.draw();//刷新
 	 		    	//$('#dtable').DataTable("draw");
				},
		 		error: function (xhr, textStatus, errorThrown) {
		            //错误信息处理
		 			console.info(errorThrown);
		        }
 	 		});
 	 		return;
 	});
	


});
</script>