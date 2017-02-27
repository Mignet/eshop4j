<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
<form id="redpacketFrom">
<input type="hidden" id="salesOrgId" value="${crmSalesOrg.salesOrgId}">
			<div class="page-header">
				<div class="row">
					<div class="col-md-8">
						<h4><small><p class="text-muted">发放奖励</p></small></h4>
					</div>
				</div>
			</div>
			
			<div class="row">
				<label class="col-sm-2 control-label">可发放批次</label>
				<div class="col-md-4">
				<select  name="batch" id="batch">
				 	<option value="">请选择</option>
				  	<c:forEach  items="${batchList}" var="item">
						<option value="${item}">${item}</option>
				 	</c:forEach> 
				 </select>
				</div>
			</div>
			<br>
			
			
			<br>
			<div class="row">
				 <div class="col-md-2 col-md-offset-4">
				 	<a class="btn btn-danger J_sendRedpacket"  role="button" >确认发放</a>&nbsp;&nbsp;&nbsp;
				 	<a class="btn btn-default active J_goback" role="button"  >返回</a>
				 </div>
			</div>
</form>
</div>



 <!-- 模态框（Modal）-->
<script type="text/javascript"  src="assets/plugins/layer/layer.js"></script>
<script type="text/javascript" >

$(document).ready(function() {

	//正式发送红包
 	$(".J_sendRedpacket").click(function() {

 		if($('#batch').val() == '') {
 			layer.msg("请选择批次！",{time: 2000,icon: 0});
 			return;
 		}
 		var formData = new FormData();
 		formData.append('batch',$("#batch").val());
 		layer.confirm('您确定要发放【' + $('#batch').val() + '】吗？', {btn: ['确定','取消'],title:'系统提示',icon: 3}, function(){
    		$.ajax({
 	 		    url: './rest/acc/acofflinerewarddraft/grantReward',
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
				},
		 		error: function (xhr, textStatus, errorThrown) {
		            //错误信息处理
		 			console.info(errorThrown);
		        }
 	 		});
		});  
 	 	return;
 	});
	
    $(".J_goback").on("click", function(event) {
        $.switchPage("机构列表","rest/acc/acofflinerewarddraft/list"); //跳到机构列表页面
        return false;
    });
    

} );
</script>
