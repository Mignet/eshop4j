<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
<form role="form"  method="post" id="sendRedpacketFrom">
	<input type="hidden" id="redpacketId" value="${param.redpacketId}">
	<div class="row">
		<label class="col-sm-2 control-label">
			<input type="radio"  checked="checked" name="type" value="0">&nbsp;&nbsp;理财师名单
		</label>
		<label class="col-sm-2 control-label">
			<input type="radio"  checked="checked" name="type" value="1">&nbsp;&nbsp;客户名单
		</label>
	</div>

	<br>

   <!--  <div class="row">

			<label class="col-sm-1 control-label">过期时间:</label>



			<div class="col-md-2">
				<input id="expiresDate" placeholder="过期日期" name="expiresDate" class="form-control" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-{%d+1}'})">
			</div>
			<div class="col-md-2">
				<input id="expiresDay" placeholder="过期时长" name="expiresDay" class="form-control" type="text"  >
			</div>

    </div>
    <br> -->

    <div class="row">
		<label class="col-sm-2 control-label">
			<input type="radio" value="0"  checked="checked" name="dateType" id="dateType">&nbsp;&nbsp;红包过期日期:
		</label>
		<div class="col-sm-2">
			<input id="expiresDate" placeholder="过期日期" name="expiresDate" class="form-control" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-{%d+1}'})">
		</div>
	</div>
	<br>
	<div class="row">
		<label class="col-sm-2 control-label">
			<input type="radio"  value="1" name="dateType" id="dateType">&nbsp;&nbsp;红包可用时长:
		</label>
		<div class="col-sm-2">
			<input id="expiresDay" placeholder="请输入天数(单位：天)" name="expiresDay" class="form-control" type="text"  >
		</div>
	</div>
	<br>
	<div class="row">
			<label class="col-sm-2 control-label">每人发放红包个数:</label>
			<div class="col-md-2">
				<input type="text" class="form-control" id="sendNums" name="sendNums" placeholder="每人发放红包个数">
			</div>
			<label class="col-sm-1 control-label"><span style="color: red;font-size: 10px;">最多发3个</span></label>
    </div>
     <br>
	<div class="row">
		 <div class="col-sm-3">
		  	 <input  id="datafile" class="form-control" type="file" name="file" />
		</div>
		<label class="col-sm-2 control-label">
			 <span style="color: red;font-size: 10px;">请点击下载<a href="/rest/redpacket/downloadImportTemplate">《模板》</a>，仅支持xls文件，最大10000条.</span>
		</label>
	</div>
	<br>
	<div class="row">
		 <div class="col-sm-2 col-sm-offset-4">
		 	<a class="btn btn-danger J_sendRedpacket"  role="button" >发送红包</a>&nbsp;&nbsp;&nbsp;
		 	<a class="btn btn-default" data-dismiss="modal"  role="button" >关闭</a>
		</div>
	</div>
  </form>
</div>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

$(document).ready(function() {

	//正式发送红包
 	$(".J_sendRedpacket").click(function() {

 		var expiresDate = $("#expiresDate").val();
 		var expiresDay = $("#expiresDay").val();
 		 var dateType =$("input[name=dateType]:checked").val();
 		var sendNums = $("input[name=sendNums]").val();
 		console.info(dateType);

 		if(dateType==0 && !expiresDate){
 			layer.msg('红包过期日期不能为空',{time: 1000,icon: 0});
 			return false;
 		}
 		if(dateType==1 ){
 			
 			if(!expiresDay){
 				layer.msg('红包可用时长不能为空',{time: 1000,icon: 0});
 	 			return false;
 	 		}
 			
 			expiresDay = parseInt(expiresDay);
 			if(expiresDay<1){
 	 			layer.msg('可用时长请输入大于0的数字',{time: 1000,icon: 0});
 	 			return false;
 	 		}
 		}
 	

 		if(sendNums<1 || sendNums>3){
 			layer.msg('发放数量请输入[1-3]数字',{time: 1000,icon: 0});
 			return false;
 		}

 		var file = $('#datafile').val(); //获取文件名
 		var ext = file.substring(file.lastIndexOf(".")+1).toLowerCase(); //获取文件后缀名
 		if(ext != "xls"){
 			layer.msg("不是xls结尾的文件！",{time: 2000,icon: 0});
			return;
 		}
 		var formData = new FormData();
 		formData.append('file', $('#datafile')[0].files[0]);
 		formData.append('redpacketId',$("#redpacketId").val());
 		formData.append('dateType',dateType);
 		formData.append('expiresDate',expiresDate);
 		formData.append('expiresDay',expiresDay);
 		formData.append('sendNums',sendNums);
 		formData.append('type',$("input[name='type']:checked").val());
 		$.ajax({
 	 		    url: './rest/redpacket/sendRedpacket',
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
 	 		    	table.draw();//刷新
 	 		    	showData(data.data);
				},
		 		error: function (xhr, textStatus, errorThrown) {
		            //错误信息处理
		 			console.info(errorThrown);
		        }
 	 		});
 	 		return;
 	});

	function showData(msg){
		if(!msg)return;
		console.info(msg);
	}

});
</script>


