<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style type="text/css">
	.span-unit{
			padding-top: 10px;
			display: inline-block;
			font-size: 10px;
		}
	
	.span-hint{
		padding-top: 3px;
		display: inline-block;
		color: blue;
		font-size: 14px;
	}
</style>

<div class="container">
		<div class="modal-dialog" style="width: 800px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">佣金设置</h4>
                </div>
				<form  id="myOrgFeeRatioForm">
				   <input type="hidden" id="modal_orgNumber" name="orgNumber" value="${param.orgNumber}" />
				   <input type="hidden" name="orgOldFeeRatio" value="${orgFeeRatio}" /> <!-- 机构原来的佣金率 -->
				   
	               <div class="modal-body">
	               
	               	  <div class="row">
	                       <label class="col-md-3 control-label"></label>
	                       <div>
	                       	<span style="color: red;font-size: 13px;">此操作将修改该机构下所有在售产品的佣金率</span>
	                       </div>
	                   </div> 
	                   
	                   <div class="page-header" style="margin: 10px 0px">
	                    	<strong>机构当前佣金率：</strong>
	                    </div>
	                    <div class="col-md-12">
	                          <div class="form-group">
	                              <label class="control-label col-md-3">当前佣金率：</label>
	                              <span class="span-hint">${orgFeeRatio} %</span>
	                          </div>
	                     </div>
	                    
	                   <div class="page-header" style="margin: 10px 0px">
	                    	<strong>设置活动开始定时任务：</strong>
	                    	<input type="hidden" name="activityStartTaskType" value="1" />
	                    </div>
	                     
		                <div class="col-md-12">
		                    <div class="form-group">
		                  	 <label class="control-label col-md-3">任务生效时间：</label>
		                     <div class="col-md-5">
		                       <input type="text" name="activityStartTaskTime" id="activityStartTaskTime" class="form-control" autocomplete="off" placeholder="定时任务启动时间" readonly="readonly" /> 
		                     </div>
		                    </div>
		                </div> 
		                  
	                    <div class="col-md-12" style="margin-top: 10px;">
	                          <div class="form-group">
	                              <label class="control-label col-md-3">机构新佣金率：</label>
	                              <span  class="span-unit">%</span>
	                              <div class="col-md-3">
	                                   <input type="text" class="form-control" id="activityStartOrgFeeRatio" name="activityStartOrgFeeRatio"  autocomplete="off" placeholder="佣金率" />
	                              </div>
	                          </div>
	                       </div>
	                       
	                    <div class="page-header" style="margin: 10px 0px">
	                    	<strong>设置活动结束定时任务：</strong>
	                    	<input type="hidden" name="activityEndTaskType" value="2" />
	                    </div>
	                    
	                      <div class="col-md-12">
		                    <div class="form-group">
		                  	 <label class="control-label col-md-3">任务生效时间：</label>
		                     <div class="col-md-5">
		                       <input type="text" name="activityEndTaskTime" id="activityEndTaskTime" class="form-control" autocomplete="off" placeholder="定时任务启动时间" readonly="readonly" /> 
		                     </div>
		                    </div>
		                  </div> 
	                    
	                       <div class="col-md-12" style="margin-top: 10px;">
	                          <div class="form-group">
	                              <label class="control-label col-md-3">机构新佣金率：</label>
	                              <span  class="span-unit">%</span>
	                              <div class="col-md-3">
	                                   <input type="text" class="form-control" id="activityEndOrgFeeRatio" name="activityEndOrgFeeRatio"  autocomplete="off" placeholder="佣金率" />
	                              </div>
	                          </div>
	                       </div>
	                       
	                   <div class="page-header" style="margin: 10px 0px">
	                    	<strong>佣金率设置原因：</strong>
	                   </div>
	                    <div class="col-md-12">
	                          <div class="form-group">
	                              <label class="control-label col-md-3" style="margin-top: 6px;">设置原因：</label>
	                              <div class="col-md-8">
	                                   <input type="text" class="form-control"  name="taskCreateReason"  autocomplete="off"  placeholder="请简述设置原因..." required="required" />
	                              </div>
	                          </div>
	                     </div>                  
		               
	                   
	                   <div class="row" style="margin-top: 70px;">
		                   	<c:if test="${not empty orgFeeTasks}">
	                			<div class="col-md-12">
									<table class="table table-bordered table-hover">
									<span style="color:blue; font-size: 14px;"><strong style="color: red;">待执行</strong>的佣金设置定时任务：</span>
									  <thead>
									    <tr class="success">
									      <th class="text-center">机构名称</th>
									      <th class="text-center">执行顺序</th>
									      <th class="text-center">佣金率</th>
									      <th class="text-center">生效时间</th>
									      <th class="text-center">设置原因</th>
									      <th class="text-center">创建人</th>
									      <th class="text-center">操作</th>
									    </tr>
									  </thead>
									  <tbody>
									  	<c:forEach var="orgFeeTask" items="${orgFeeTasks}" varStatus="idx">
										  		<tr id="${orgFeeTask.fid}">
												      <td>
													     ${orgFeeTask.orgName}
												      </td>
												      <td>
												      	${idx.count}
												      </td>
												      <td>
													     ${orgFeeTask.orgFeeRatio} %
												      </td>
												      
												      <td>
												      	 <fmt:formatDate value="${orgFeeTask.taskStartTime}" pattern="yyyy-MM-dd HH:mm:00" />	
												      </td>
												      <td>${orgFeeTask.taskCreateReason}</td>
												      <td>${orgFeeTask.creater}</td>
												      <td>
									                     <button type="button" class="btn btn-default btn-danger" onclick="deleteOrgFeeTaskRow(this);" data-rowid="${orgFeeTask.fid}"><i class="fa fa-trash-o"></i> 删除</button>
												      </td>
												    </tr>
									  	</c:forEach>
									  </tbody>
									</table>
								</div>
							</c:if>
	                   </div>
	                 
	               </div>
	              <!-- 表单提交传入后台机构id -->
	              <div class="modal-footer">
	                  <button type="submit" class="btn btn-primary">保存</button>
	                  <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	              </div>
	        </form>
        </div>
    </div>
 </div>        
 
<script type="text/javascript">

	/**
	 * 活动开始初始化日期插件
	 */
	$('#activityStartTaskTime').daterangepicker({
		timePickerIncrement : 1, //时间的增量，单位为分钟 
        timePicker12Hour : false, //是否使用12小时制来显示时间 
       //timePicker24Hour: true, 
		timePicker : true, //是否显示小时和分钟
		showDropdowns: true, //自定义可选择年、月
		singleDatePicker: true, //是否是单个时间选择器
		format: 'YYYY-MM-DD HH:mm:00', //日期格式化
		startDate: moment().add('hours',1).format('YYYY-MM-DD HH:mm:ss'), // 默认活动开始时间 当前时间加一小时
		minDate: moment().format('YYYY-MM-DD')	//可选最早时间
	},
	function(start, end, label) { //回调
		$("#activityStartTaskTime").closest('.form-group').removeClass('has-error'); //删除验证错误样式
		$("#activityStartTaskTime-error").remove(); //移除jqueryValidate span错误提示标签
	   // alert('A date range was chosen: ' +start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
	  }
	);
	
	/**
	 * 活动结束初始化日期插件
	 */
	
	$('#activityEndTaskTime').daterangepicker({
		timePickerIncrement : 1, //时间的增量，单位为分钟 
        timePicker12Hour : false, //是否使用12小时制来显示时间 
		timePicker : true, //是否显示小时和分钟
		showDropdowns: true, //自定义可选择年、月
		singleDatePicker: true, //是否是单个时间选择器
		format: 'YYYY-MM-DD HH:mm:00', //日期格式化
		startDate: moment().add('hours',2).format('YYYY-MM-DD HH:mm:ss'), // 默认结束时间 比 开始时间晚2小时 .add('days',1)
		minDate: moment().format('YYYY-MM-DD')	//可选最早时间 moment().add('hours',1).format('HH:mm:ss'); 

	},
	function(start, end, label) { //回调
		$("#activityEndTaskTime").closest('.form-group').removeClass('has-error'); //删除验证错误样式
		$("#activityEndTaskTime-error").remove(); //移除jqueryValidate span错误提示标签
	   // alert('A date range was chosen: ' +start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
	  }
	);
	
	//判断表单的信息是否通过验证
	$("#myOrgFeeRatioForm").validate({
		//debug: true,//只验证不提交表单
		//meta: "validate",//设置meta来封装验证规则
		//onkeyup:false, //是否在敲击键盘时验证
		errorElement: 'span',
		errorClass: 'help-block help-block-error',
		focusInvalid: false, //当为false时，验证无效时，没有焦点响应
		rules : {  
			activityStartOrgFeeRatio : {
				required: true,
				min:0,
				number : true
			},
			activityEndOrgFeeRatio : {
				required: true,
				min:0,
				number : true
			},
			activityEndTaskTime:{
				required: true,
				compareDate:true
			},
			activityStartTaskTime:{
				required: true
			}
		},  
		messages : {  
			
		},  
		highlight: function (element) {
			//可以给未通过验证的元素加效果、闪烁等
			$(element).closest('.form-group').addClass('has-error');
		},
		success: function (label) {
			label.closest('.form-group').removeClass('has-error');
			label.remove();
		},
		errorPlacement: function (error, element) {
			element.parent('div').append(error);
		},
		submitHandler: function (form){
			//var orgFeeRatio = $("#modal_orgFeeRatio").val(); //机构佣金
			//var orgNumber = $("#modal_orgNumber").val(); //机构编码
			$.ajax({
				data:$(form).serialize(), //表单对象数据转json字符串提交
				dataType:'json',
				type:'post',
				url : '/rest/cim/cimorgfeetimetask/insertOrgFeeRatioTask',
				success: function (data) {
					$("#orgFeeRatioModal").modal("hide"); //隐藏模态框
					if (data.isFlag) {
						//保存成功  1.关闭弹出层，2.刷新表格数据
						showTips(data.msg);
						//table.draw();//刷新表格数据
					} else {
						showError(data.msg);
					}
				},
				error:function(XmlHttpRequest,textStatus, errorThrown) {
					console.log(XmlHttpRequest.status);
					console.log(textStatus);
					showError(XmlHttpRequest.responseText+"更新机构佣金信息失败！");
				}
			});
		}
	});
	
	
	/**
	 * 删除机构佣金设置定时任务
	 */
	function deleteOrgFeeTaskRow(delBtn){
		//询问框
		layer.confirm('确定删除此任务？', {btn: ['确定','取消'],title:'提示',shift: 6,icon: 3}, function(){
		  
			var rowid = $(delBtn).attr("data-rowid"); //获取团队成员的行/主键id
			
	  		//数据库中执行删除
	  		$.ajax({
	        	data:{"id":rowid}, //表单对象数据转json字符串提交
	            dataType:'json',
	            type:'post',
	            url : '/rest/cim/cimorgfeetimetask/deleteOrgFeeTask',
	            success: function (result) {
	            	if(result.isFlag){
	            		layer.msg(result.msg, {icon: 1,time:500});
	            		//删除成功
						$("#"+rowid).remove(); //删除此行
					}else{
						//删除失败
						layer.msg(result.msg, {icon: 2,time:500});
					}
	            },
	            error:function(XmlHttpRequest,textStatus, errorThrown) {
	  			  	console.log(XmlHttpRequest.status);
	  			  	console.log(textStatus);
	  			  	showError(XmlHttpRequest.responseText+"删除机构佣金设置定时任务失败！");
	  		  	}
	            
	          });
		  	
			
		});
		
	}
	
	/**
	 * 自定义时间验证: compareDate:true
	 */	
	
	jQuery.validator.addMethod("compareDate",function(value,element){
        var assigntime = $("#activityStartTaskTime").val();
        var deadlinetime = $("#activityEndTaskTime").val();
        var reg = new RegExp('-','g');
        assigntime = assigntime.replace(reg,'/');//正则替换
        deadlinetime = deadlinetime.replace(reg,'/');
        assigntime = new Date(parseInt(Date.parse(assigntime),10));
        deadlinetime = new Date(parseInt(Date.parse(deadlinetime),10));
        if(assigntime >= deadlinetime){
            return false;
        }else{
            return true;
        }
    },"结束日期必须大于开始日期");

</script>
                