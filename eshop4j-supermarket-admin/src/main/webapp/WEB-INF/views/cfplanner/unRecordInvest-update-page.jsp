<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"></script>

<form id="unRecordInvestUpdateFrom">
			<input type="hidden" name="id" value="${param.id}">

			<div class="row">
				<label class="col-sm-2 control-label">审核状态:</label>
				<div class="col-md-4">
					<select name="status" class="form-control">
					 		<option value="2">不通过</option>
					        <option value="1" >通过</option>

					</select>
				</div>
			</div>
		<!-- 	<br>
			<div class="row">
				<label class="col-sm-2 control-label">购买时间:</label>
				<div class="col-md-4">
                   <input type="text" name="investTime" autocomplete="off" class="Wdate" placeholder="购买时间" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">产品期限:</label>
				<div class="col-md-4">
                   <input type="text" name="deadLine" autocomplete="off" class="form-control" placeholder="产品期限">
				</div>
			</div> -->
		<!-- 	<br>
			<div class="row">
				<label class="col-sm-2 control-label">理财师佣金:</label>
				<div class="col-md-4">
                   <input type="text" name="feeAmt" autocomplete="off" class="form-control" placeholder="理财师佣金">
				</div>
			</div> -->
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">描述:</label>
				<div class="col-md-4">
                   <textarea name="remark" autocomplete="off" class="form-control" placeholder="描述"></textarea>
				</div>
			</div>
			<br>


			 <br>
			 <br>
			 <br>
			<div class="row">
				 <div class="col-md-2 col-md-offset-4">
				 	<a class="btn btn-danger active J_update_status"  role="button"  >更新</a>
				 	&nbsp;&nbsp;&nbsp;&nbsp;
				 	<a class="btn btn-default active " data-dismiss="modal" role="button"  >关闭</a>
				 </div>
			</div>
</form>
</div>

<script type="text/javascript" >

$(document).ready(function() {
    $(".J_update_status").on("click", function(event) {
    	var data =milo.jsonFromt($("#unRecordInvestUpdateFrom").serializeArray());
    	if(!data.id){
             layer.msg('编号不能为空',{time: 1000,icon: 0});
             return false;
    	}
    	if(data.status==2 && !data.remark){
            layer.msg('审核不通过描述不能为空',{time: 1000,icon: 0});
           return false;
   		}

   /*  	if(data.status==1){
    		  if( !data.investTime){
	             layer.msg('投资时间不能为空',{time: 1000,icon: 0});
	            return false;
	    	 }
             if( !data.deadLine){
                  layer.msg('产品期限不能为空',{time: 1000,icon: 0});
                 return false;
             } 
             if( !data.feeAmt){
                  layer.msg('理财师佣金不能为空',{time: 1000,icon: 0});
                 return false;
             }
	   	}else {
		    	if(data.status==2 && !data.remark){
		             layer.msg('审核不通过描述不能为空',{time: 1000,icon: 0});
		             
		             
		            return false;
		    	}
	   	} */
    	$.post('rest/unRecordInvest/updateStatus', data, function(data, textStatus, xhr) {
            layer.msg(data.msg,{time: 1000,icon: data.isFlag?1:0});
            table.draw();
            $('#publicModal').modal("hide"); //隐藏
        });
        return false;
    });
} );
</script>
