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
		padding-top: 8px;
		display: inline-block;
		color: blue;
		font-size: 10px;
	}
	div.col-md-10.fill{
		margin-top: 10px;
	}
</style>

<div class="container">
		<div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel"></h4>
                </div>
				<form  id="myOrgRiskModalForm" action='/rest/cim/cimorgrisk/add' method="post">
	               <div class="modal-body">
	               			 <div class="row">
								 <div class="col-md-10">
								 		<div class="form-group">
									        <label class="control-label col-md-4">所属机构：</label>
											 <div class="col-md-5">
												<select id="org_risk_select" name="orgName" class="form-control" style="width: 150px;display: inline-block;margin-right: 20px" required="required">
							                        	<option value="">请选择机构</option>
														<c:forEach items="${orgList}" var="org">
							                            		<option value="${org.orgNumber}">${org.orgName}</option>
														</c:forEach>
						                        </select>
											</div>
											
									    </div>
								    </div>
									
									<div class="col-md-10 fill">
					                      <div class="form-group">
					                          <label class="control-label col-md-4">背景实力：</label>
					                          <span  class="span-hint">百分制，例:90</span>
					                          <div class="col-md-5">
					                               <input type="text" class="form-control" name="backgroundScore"  autocomplete="off" placeholder="请输入分数(支持小数)" required />
					                          </div>
					                      </div>
					                  </div>
					                  
					                  <div class="col-md-10 fill">
					                      <div class="form-group">
					                          <label class="control-label col-md-4">风险控制：</label>
					                          <span  class="span-hint">百分制，例:60.89</span>
					                          <div class="col-md-5">
					                               <input type="text" class="form-control" name="riskControlScore"  autocomplete="off" placeholder="请输入分数(支持小数)" />
					                          </div>
					                      </div>
					                  </div>
					                  
					                  <div class="col-md-10 fill">
					                      <div class="form-group">
					                          <label class="control-label col-md-4">运营能力：</label>
					                          <span  class="span-hint">百分制，同上..</span>
					                          <div class="col-md-5">
					                               <input type="text" class="form-control" name="runPowerScore"  autocomplete="off" placeholder="请输入分数(支持小数)" required />
					                          </div>
					                      </div>
					                  </div>
					                  
					                  <div class="col-md-10 fill">
					                      <div class="form-group">
					                          <label class="control-label col-md-4">信息披露：</label>
					                          <span  class="span-hint">百分制，同上..</span>
					                          <div class="col-md-5">
					                               <input type="text" class="form-control" name="inforDisclosureScore"  autocomplete="off" placeholder="请输入分数(支持小数)" />
					                          </div>
					                      </div>
					                  </div>
					                  
					                  <div class="col-md-10 fill">
					                      <div class="form-group">
					                          <label class="control-label col-md-4">用户体验：</label>
					                          <span  class="span-hint">百分制，同上..</span>
					                          <div class="col-md-5">
					                               <input type="text" class="form-control" name="userExperienceScore"  autocomplete="off" placeholder="请输入分数(支持小数)" required />
					                          </div>
					                      </div>
					                  </div>
					                  
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
//判断表单的信息是否通过验证
$("#myOrgRiskModalForm").validate({
	//debug: true,//只验证不提交表单
	//meta: "validate",//设置meta来封装验证规则
	//onkeyup:false, //是否在敲击键盘时验证
	errorElement: 'span',
	errorClass: 'help-block help-block-error',
	focusInvalid: false, //当为false时，验证无效时，没有焦点响应
	rules : {  
		backgroundScore : {
			required: true,
			min:0,
			max:100,
			number : true
		},
		riskControlScore : {
			required: true,
			min:0,
			max:100,
			number : true
		},
		runPowerScore : {
			required: true,
			min:0,
			max:100,
			number : true
		},
		inforDisclosureScore : {
			required: true,
			min:0,
			max:100,
			number : true
		},
		userExperienceScore : {
			required: true,
			min:0,
			max:100,
			number : true
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
	    var orgNumber = $('#org_risk_select option:selected').val(); //下拉框选中的值
	    var orgName = $('#org_risk_select option:selected').text(); //下拉框显示的文本
	    var backgroundScore = $('input[name="backgroundScore"]').val(); 
	    var riskControlScore = $('input[name="riskControlScore"]').val();
	    var runPowerScore = $('input[name="runPowerScore"]').val();
	    var inforDisclosureScore = $('input[name="inforDisclosureScore"]').val();
	    var userExperienceScore = $('input[name="userExperienceScore"]').val();
		$.ajax({
			data: { 
				"orgNumber":orgNumber,"orgName":orgName , 
				"backgroundScore":backgroundScore,"riskControlScore":riskControlScore,
				"runPowerScore":runPowerScore,"inforDisclosureScore":inforDisclosureScore,
				"userExperienceScore":userExperienceScore
				}, //表单对象数据转json字符串提交
			//data : $(form).serialize(), //表单对象数据转json字符串提交
			dataType : 'json',
			type : 'post',
			url : form.action,
			success: function (data) {
				$("#orgRiskModal").modal("hide"); //隐藏模态框
				if (data.isFlag) {
					//保存成功  1.关闭弹出层，2.刷新表格数据
					showTips(data.msg);
					table.draw();//刷新表格数据
					//$.switchPage("机构风控信息","rest/cim/cimorgrisk/list"); //跳到机构列表页面
				} else {
					showError(data.msg);
				}
			},
			error:function(XmlHttpRequest,textStatus, errorThrown) {
				console.log(XmlHttpRequest.status);
				console.log(textStatus);
				showError(XmlHttpRequest.responseText+"新增机构风控信息失败！");
			}
		});
	}
});

</script>
                