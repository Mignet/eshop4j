<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
    request.setAttribute("ctx", request.getContextPath());
%>
<input id="path" type="hidden" value="${ctx}" />
<script type="text/javascript">
    window.UEDITOR_HOME_URL = '${ctx}/assets/plugins/ueditor/';
</script>
<!-- ueditor -->
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.zimg.js"></script>


 <style type="text/css">
	div.col-md-8.fill{
		margin-top: 10px;
	}
	.span-hint{
		padding-top: 10px;
		display: inline-block;
		color: blue;
		font-size: 10px;
	}
	.span-unit{
		padding-top: 10px;
		display: inline-block;
		font-size: 10px;
	}
	/*头像上传样式*/
	.kv-avatar .file-preview-frame,.kv-avatar .file-preview-frame:hover {
    margin: 0;
    padding: 0;
    border: none;
    box-shadow: none;
    text-align: center;
	}
	.kv-avatar .file-input {
	    display: table-cell;
	    max-width: 220px;
	}
</style>
<!-- 图片服务器 -->
<input type="hidden" id="imgServerUrl" value="${img_server}"/>
<div class="container">
	<form id="orgForm" action="/rest/cim/cimorginfo/update" method="post">
		<div class="row">
			<div class="page-header">
					<h4><strong>基本信息</strong></h4>
			</div>
			<div class="col-sm-10"><!-- 小屏幕  ≥768px-->
			 		<input type="hidden" name="id" value="${orgInfo.id}" /><!-- 机构主键id -->
			 		<input type="hidden" name="status" value="${orgInfo.status}" /> <!-- 机构合作状态 -->
			 		<input type="hidden" name="orgIsstaticproduct" value="${orgInfo.orgIsstaticproduct}" /> <!-- 是否虚拟机构 -->
			 		<input type="hidden" name="orgGrayStatus" value="${orgInfo.orgGrayStatus}" /> <!-- 是否灰度机构 -->
			 		
			 		<div class="col-md-8">
				 		<div class="form-group">
					        <label class="control-label col-md-3">虚拟机构：</label>
					        <span  class="span-hint">机构未进行技术对接需开启(ON)</span>
							 <div class="switch col-md-3">
								<input type="checkbox" id="virtualOrg" data-name="orgIsstaticproduct" class="bootstrapSwitch" <c:if test="${orgInfo.orgIsstaticproduct == 1}">checked</c:if> />
							</div>
							
					    </div>
				    </div>
				    
				     <div class="col-md-8">
				 		<div class="form-group">
					        <label class="control-label col-md-3">机构灰度模式：</label>
					        <span  class="span-hint">设置机构为灰度模式需开启(ON)</span>
							 <div class="switch col-md-3">
								<input type="checkbox" id="orgGrayStatus" data-name="orgGrayStatus" class="bootstrapSwitch" <c:if test="${orgInfo.orgGrayStatus == 1}">checked</c:if> />
							</div>
					    </div>
				    </div>
				    
			 		<div class="col-md-8">
				 		<div class="form-group">
					        <label class="control-label col-md-3">合作状态：</label>
					        <span  class="span-hint">只有已合作(ON)的机构才会在APP和PC端显示</span>
							 <div class="switch col-md-3">
								<input type="checkbox" id="orgStatus" data-name="status" class="bootstrapSwitch" <c:if test="${orgInfo.status == 1}">checked</c:if>  />
							</div>
							
					    </div>
				    </div>
				    
				    <div class="col-md-8">
				 		<div class="form-group">
					        <label class="control-label col-md-3">对接机构类型：</label>
							 <div class="col-md-7">
							 	 <label class="radio-inline">
							      	<input type="radio" name="orgJoinType" value="0" <c:if test="${orgInfo.orgJoinType == 0}">checked</c:if> /> 移动+PC端
							     </label>
							     <label class="radio-inline">
							      	<input type="radio" name="orgJoinType" value="1" <c:if test="${orgInfo.orgJoinType == 1}">checked</c:if> /> 移动端
							     </label>
							     <label class="radio-inline">
							      	<input type="radio" name="orgJoinType" value="2" <c:if test="${orgInfo.orgJoinType == 2}">checked</c:if> /> PC端
							     </label>
							</div>
					    </div>
				    </div>
				    
					  <div class="col-md-8 fill"><!-- 中等屏幕 ≥992px -->
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构名称：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgName"  id="orgName" value="${orgInfo.name}" autocomplete="off" placeholder="请输入机构名称" required />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构编码：</label>
	                          <span  class="span-hint">设置后不能修改</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control required" name="orgNumber" value="${orgInfo.orgNumber}" autocomplete="off" placeholder="请输入机构名称大写全拼" readonly="readonly" />
	                          </div>
	                      </div>
	                  </div>
	                 
	                  <div class="col-md-8">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">后台账户：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgAccount" value="${orgInfo.orgAccount}" autocomplete="off" placeholder="机构后台账户" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">登录密码：</label>
	                          <div class="col-md-5">
	                               <input type="text" onfocus="javascript:this.type='password'" class="form-control" name="orgPassword"  autocomplete="off" placeholder="需要修改密码则输入" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">注册资本：</label>
	                          <span  class="span-unit">万元</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="capital" value="${orgInfo.capital}" autocomplete="off" placeholder="请输入机构注册资本" />
	                          </div>
	                      </div>
	                  </div>
	              
                  <div class="col-md-8">
                    <div class="form-group">
                  	 <label class="control-label col-md-3">上线时间：</label>
                     <div class="col-md-5">
                       <input type="text" name="upTime" id="upTime" value="<fmt:formatDate value="${orgInfo.upTime}" pattern="yyyy-MM-dd" />" class="form-control" autocomplete="off" placeholder="请输入机构上线时间" readonly="readonly" /> 
                     </div>
                    </div>
                  </div> 
	                  
	                <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构佣金率：</label>
	                          <span  class="span-unit">%</span>
	                          <span  class="span-hint">&nbsp;设置后不能修改</span>
	                          <div class="col-md-2">
	                               <input type="text" class="form-control" value="${orgInfo.orgFeeRatio}" autocomplete="off" placeholder="机构所属产品给理财师返佣比例" readonly="readonly" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                   <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">级差佣金率：</label>
	                          <span  class="span-unit">%</span>
	                          <div class="col-md-2">
	                               <input type="text" class="form-control" value="${orgInfo.diffFeeRatio}" name="diffFeeRatio" autocomplete="off" placeholder="理财师级差佣金率默认为1.5%" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">安全评级：</label>
	                          <div class="col-md-5">
	                               <select id="security_grade" name="grade" class="form-control" style="width: 150px;display: inline-block;" required="required">
			                        	<option value="">请选择安全评级</option>
			                            <option value="6" <c:if test="${orgInfo.grade eq 'AAA'}">selected="selected"</c:if>>AAA</option>
			                            <option value="5" <c:if test="${orgInfo.grade eq 'AA'}">selected="selected"</c:if>>AA</option>
			                            <option value="4" <c:if test="${orgInfo.grade eq 'A'}">selected="selected"</c:if>>A</option>
			                            <option value="3" <c:if test="${orgInfo.grade eq 'BBB'}">selected="selected"</c:if>>BBB</option>
			                            <option value="2" <c:if test="${orgInfo.grade eq 'BB'}">selected="selected"</c:if>>BB</option>
			                            <option value="1" <c:if test="${orgInfo.grade eq 'B'}">selected="selected"</c:if>>B</option>
		                    		</select>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构背景：</label>
	                          <div class="col-md-5">
	                               <select id="org_context" name="context" class="form-control" style="width: 150px;display: inline-block;" required="required">
			                        	<option value="">请选择机构背景</option>
	                               		<c:forEach items="${orgBackgroundList}" var="orgback">
			                            	<option value="${orgback.configValue}" <c:if test="${orgInfo.context eq orgback.configValue}">selected="selected"</c:if>>${orgback.configValue}</option>
	                               		</c:forEach>
		                    		</select>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">安全保证金：</label>
	                          <span  class="span-unit">元</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgMargin" value="${orgInfo.orgMargin}" autocomplete="off" placeholder="请输入机构缴纳的保证金" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">所在城市：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="city" value="${orgInfo.city}" autocomplete="off" placeholder="请输入机构所在城市" required="required"/>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">法人代表：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="representative" value="${orgInfo.representative}" autocomplete="off" placeholder="请输入机构法人代表" required="required" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">ICP备案：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="icpFiling" value="${orgInfo.icpFiling}" autocomplete="off" placeholder="请输入机构icp备案" required="required"/>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">联系方式：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="contact" value="${orgInfo.contact}" autocomplete="off" placeholder="请输入机构联系电话" required="required"/>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">资金托管：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="trusteeship" value="${orgInfo.trusteeship}" autocomplete="off" placeholder="请输入机构资金托管行" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构亮点：</label>
	                          <span  class="span-hint">有多个以英文逗号分隔(例：1,2)</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgAdvantage" value="${orgInfo.orgAdvantage}" autocomplete="off" placeholder="请输入机构亮点介绍" required="required" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">T呗平台自定义标签：</label>
	                          <span  class="span-hint">有多个以英文逗号分隔(例：1,2)</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgInvestTag" value="${orgInfo.orgInvestTag}" autocomplete="off" placeholder="请输入T呗端平台自定义标签" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">猎财平台自定义标签：</label>
	                          <span  class="span-hint">有多个以英文逗号分隔(例：1,2)</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgPlannerTag" value="${orgInfo.orgPlannerTag}" autocomplete="off" placeholder="请输入猎财端平台自定义标签" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">T呗产品自定义标签：</label>
	                          <span  class="span-hint">有多个以英文逗号分隔(例：1,2)</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgProductTag" value="${orgInfo.orgProductTag}" autocomplete="off" placeholder="请输入T呗端产品自定义标签" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">猎财产品自定义标签：</label>
	                          <span  class="span-hint">有多个以英文逗号分隔(例：1,2)</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgPlannerProductTag" value="${orgInfo.orgPlannerProductTag}" autocomplete="off" placeholder="请输入猎财端产品自定义标签" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                 <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">PC端-债券转让：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgDebentureTransfer" value="${orgInfo.orgDebentureTransfer}" autocomplete="off" />
	                          </div>
	                      </div>
	                 </div>
	                  
	                 <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">PC端-投标保障：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgBidSecurity" value="${orgInfo.orgBidSecurity}" autocomplete="off" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                 <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">PC端-保障模式：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgSecurityMode" value="${orgInfo.orgSecurityMode}" autocomplete="off" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">PC端-机构考察报告名称：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="orgInvestigationReport" value="${orgInfo.orgInvestigationReport}" autocomplete="off" placeholder="请输入机构考察报告名称" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">PC端-机构考察报告下载地址：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="orgInvestigationReportUrl" value="${orgInfo.orgInvestigationReportUrl}" autocomplete="off" placeholder="请输入机构考察报告下载地址" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">PC端-网站备案详情：</label>
	                          <div class="col-md-5">
	                               <textarea id="orgWebsiteRecords" class="ueditorPlug" name="orgWebsiteRecords" style="width: 800px;">${orgInfo.orgWebsiteRecords}</textarea>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">PC端-机构联系方式详情：</label>
	                          <div class="col-md-5">
	                               <textarea id="orgContactDetails" class="ueditorPlug" name="orgContactDetails" style="width: 800px;">${orgInfo.orgContactDetails}</textarea>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构官网地址：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="orgUrl" value="${orgInfo.orgUrl}" autocomplete="off" placeholder="请输入机构官网地址" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">产品购买地址：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="orgProductUrl" value="${orgInfo.orgProductUrl}" autocomplete="off" placeholder="请输入机构在售产品购买地址" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">用户中心地址：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="orgUsercenterUrl" value="${orgInfo.orgUsercenterUrl}" autocomplete="off" placeholder="请输入机构用户中心地址" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">绑定用户地址：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="orgBindUserUrl" value="${orgInfo.orgBindUserUrl}" autocomplete="off" placeholder="请输入绑定用户地址" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                 
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">余额查询地址：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="orgUserbalanceUrl" value="${orgInfo.orgUserbalanceUrl}" autocomplete="off" placeholder="请输入用户资产余额查询接口地址（可选）" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                   <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">用户校验地址：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="orgUserExistUrl" value="${orgInfo.orgUserExistUrl}" autocomplete="off" placeholder="请输入机构用户是否存在接口地址" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                 <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">合作结束跳转地址：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="cooperationEndUrl" value="${orgInfo.cooperationEndUrl}" autocomplete="off" placeholder="合作结束已开通账户需要跳转的地址" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构简介：</label>
	                          <div class="col-md-5">
	                               <textarea name="orgProfile" style="width: 600px;height: 100px;">${orgInfo.orgProfile}</textarea>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">安全保障：</label>
	                          <div class="col-md-5">
	                               <textarea name="orgSecurity" style="width: 600px;height: 100px;">${orgInfo.orgSecurity}</textarea>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">荣誉(文字介绍)：</label>
	                          <div class="col-md-5">
	                               <textarea id="orgHonor" name="orgHonor" style="width: 600px;height: 100px;">${orgInfo.orgHonor}</textarea>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">移动端-投资攻略：</label>
	                          <div class="col-md-5">
	                               <textarea id="orgInvestStrategy" class="ueditorPlug" name="orgInvestStrategy" style="width: 800px;">${orgInfo.orgInvestStrategy}</textarea>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">移动端-猎财攻略：</label>
	                          <div class="col-md-5">
	                               <textarea id="orgPlannerStrategy" class="ueditorPlug" name="orgPlannerStrategy" style="width: 800px;">${orgInfo.orgPlannerStrategy}</textarea>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  
	                  
				<!-- 上传图片 -->	
				<div class="col-md-8 fill">
					<div class="form-group">
						<label class="control-label col-md-3">移动端-机构显示Logo：</label>
						<span  class="span-hint">132*132 px,透明背景</span>
						<div class="col-md-6">
							<input class="uploadImg"  type="file" id="platformImg" data-md5="${orgInfo.platformIco}" />
							<!-- 上传成功存入隐藏域中 -->
							<input name="platformImg" type="hidden" />
						</div>
					</div>
				</div>
				
				<div class="col-md-8">	
					<div class="form-group">
						
					</div> 
				</div>
				
				<!-- 上传图片 -->	
				<div class="col-md-8">
					<div class="form-group">
						<label class="control-label col-md-3">移动端-产品列表中机构Logo：</label>
						<span  class="span-hint">198*60 px,透明背景</span>
						<div class="col-md-6">
							<input class="uploadImg"  type="file" id="platformListImg" data-md5="${orgInfo.platformlistIco}" />
							<!-- 上传成功存入隐藏域中 -->
							<input name="platformListImg" type="hidden" />
						</div>
					</div>
				</div>
				
				<div class="col-md-8">	
					<div class="form-group">
					</div> 
				</div>
				
				<!-- 上传图片 -->	
				<div class="col-md-8">
					<div class="form-group">
						<label class="control-label col-md-3">移动端-前台图片：</label>
						<span  class="span-hint">1080*504 px</span>
						<div class="col-md-6">
							<input class="uploadImg"  type="file" id="platformDetailImg" data-md5="${orgInfo.platformDetailImg}" />
							<!-- 上传成功存入隐藏域中 -->
							<input name="platformDetailImg" type="hidden" />
						</div>
					</div>
				</div>
				
				<div class="col-md-8">	
					<div class="form-group">
					</div> 
				</div>
				
				<!-- 上传图片 -->	
				<!-- 
				<div class="col-md-8">
					<div class="form-group">
						<label class="control-label col-md-3">营业执照：</label>
						<div class="col-md-6">
							<input class="uploadImg"  type="file" id="businessLicenseImg" data-md5="${orgInfo.businessLicense}" />
							<input name="businessLicenseImg" type="hidden" />
						</div>
					</div>
				</div>
				
				<div class="col-md-8">	
					<div class="form-group">
						
					</div> 
				</div>
				 -->
				 
				  	  <div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-md-3">PC端-优质平台活动图片：</label>
								<span  class="span-hint">235*71 px</span>
								<div class="col-md-6">
									<input class="uploadImg"  type="file" id="orgAdvertisement" data-md5="${orgInfo.orgAdvertisement}" />
									<!-- 上传成功存入隐藏域中 -->
									<input name="orgAdvertisement" type="hidden" />
								</div>
							</div>
						</div>
						
						<div class="col-md-8">	
							<div class="form-group">
							</div> 
						</div>
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">PC端-优质平台活动图片链接地址：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="orgAdvertisementUrl" value="${orgInfo.orgAdvertisementUrl}" autocomplete="off" placeholder="用户点击优质平台活动图片后跳转的地址(需要跳转则配置)" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                  
				<div class="col-md-8">
					<div class="form-group">
						<label class="control-label col-md-3">PC端-机构显示Logo：</label>
						<div class="col-md-6">
							<input class="uploadImg"  type="file" id="pcPlatformImg" data-md5="${orgInfo.pcPlatformImg}" />
							<!-- 上传成功存入隐藏域中 -->
							<input name="pcPlatformImg" type="hidden" />
						</div>
					</div>
				</div>
				
				<div class="col-md-8">	
					<div class="form-group">
						
					</div> 
				</div>
				<!-- 
				<div class="col-md-8">
					<div class="form-group">
						<label class="control-label col-md-3">PC端-产品及优质平台中机构Logo：</label>
						<div class="col-md-6">
							<input class="uploadImg"  type="file" id="pcPlatformListImg" data-md5="${orgInfo.pcPlatformListImg}" />
							<input name="pcPlatformListImg" type="hidden" />
						</div>
					</div>
				</div>
				
				<div class="col-md-8">	
					<div class="form-group">
						
					</div> 
				</div>
				 -->
				<div class="col-md-8">
					<div class="form-group">
						<label class="control-label col-md-3">PC端-前台图片：</label>
						<div class="col-md-6">
							<input class="uploadImg"  type="file" id="pcPlatformDetailImg" data-md5="${orgInfo.pcPlatformDetailImg}" />
							<!-- 上传成功存入隐藏域中 -->
							<input name="pcPlatformDetailImg" type="hidden" />
						</div>
					</div>
				</div>
				
				<div class="col-md-8">	
					<div class="form-group">
						
					</div> 
				</div>
				
				<!-- 上传图片 -->	
				<div class="col-md-8">
					<div class="form-group">
						<label class="control-label col-md-3">PC端-最新入驻平台Logo：</label>
						<span  class="span-hint">165*73 px</span>
						<div class="col-md-6">
							<input class="uploadImg"  type="file" id="orgNewestImg" data-md5="${orgInfo.orgNewestImg}" />
							<!-- 上传成功存入隐藏域中 -->
							<input name="orgNewestImg" type="hidden" />
						</div>
					</div>
				</div>
				
				<div class="col-md-8">	
					<div class="form-group">
					</div> 
				</div>
				
			 </div> <!--小屏幕宽度限制-->
			
		</div>
		
		<div class="row" style="width: 100%;">
				<!-- 多图上传 -->	
				<div>
					<div class="form-group">
						<label class="control-label col-md-3">移动端-办公环境：</label>
						<span  class="span-hint">支持多图上传</span>
						<div>
							<input id="orgEnvironmentPicture" type="file" multiple class="uploadMultipleImg" data-md5="${orgEnvironments}" />
							<!-- 上传成功存入隐藏域中 -->
							<input name="orgEnvironmentPicture" type="hidden" />
						</div>
					</div>
				</div>
				
				<div>	
					<div class="form-group">
					</div> 
				</div>
		</div>
		
		<div class="row" style="width: 100%;">
				<!-- 多图上传 -->	
				<div>
					<div class="form-group">
						<label class="control-label col-md-3">移动端-营业执照及其他资格证：</label>
						<span  class="span-hint">支持多图上传</span>
						<div>
							<input id="orgPaperPicture" type="file" multiple class="uploadMultipleImg" data-md5="${orgPapers}" />
							<!-- 上传成功存入隐藏域中 -->
							<input name="orgPaperPicture" type="hidden" />
						</div>
					</div>
				</div>
				
				<div>	
					<div class="form-group">
					</div> 
				</div>
		</div>
		
		<div class="row" style="width: 100%;">
				<!-- 多图上传 -->	
				<div>
					<div class="form-group">
						<label class="control-label col-md-3">移动端-荣誉证书：</label>
						<span  class="span-hint">支持多图上传</span>
						<div>
							<input id="orgHonorPicture" type="file" multiple class="uploadMultipleImg" data-md5="${orgHonors}" />
							<!-- 上传成功存入隐藏域中 -->
							<input name="orgHonorPicture" type="hidden" />
						</div>
					</div>
				</div>
				
				<div>	
					<div class="form-group">
					</div> 
				</div>
		</div>
		
		<div class="row" style="width: 100%;">
				<!-- 多图上传 -->	
				<div>
					<div class="form-group">
						<label class="control-label col-md-3">PC端-办公环境：</label>
						<span  class="span-hint">支持多图上传</span>
						<div>
							<input id="orgPcEnvironmentPicture" type="file" multiple class="uploadMultipleImg" data-md5="${orgPcEnvironments}" />
							<!-- 上传成功存入隐藏域中 -->
							<input name="orgPcEnvironmentPicture" type="hidden" />
						</div>
					</div>
				</div>
				
				<div>	
					<div class="form-group">
					</div> 
				</div>
		</div>
		
		<div class="row" style="width: 100%;">
				<!-- 多图上传 -->	
				<div>
					<div class="form-group">
						<label class="control-label col-md-3">PC端-营业执照及其他资格证：</label>
						<span  class="span-hint">支持多图上传</span>
						<div>
							<input id="orgPcPaperPicture" type="file" multiple class="uploadMultipleImg" data-md5="${orgPcPapers}" />
							<!-- 上传成功存入隐藏域中 -->
							<input name="orgPcPaperPicture" type="hidden" />
						</div>
					</div>
				</div>
				
				<div>	
					<div class="form-group">
					</div> 
				</div>
		</div>
		
		<div class="row" style="width: 100%;">
				<!-- 多图上传 -->	
				<div>
					<div class="form-group">
						<label class="control-label col-md-3">PC端-荣誉证书：</label>
						<span  class="span-hint">支持多图上传</span>
						<div>
							<input id="orgPcHonorPicture" type="file" multiple class="uploadMultipleImg" data-md5="${orgPcHonors}" />
							<!-- 上传成功存入隐藏域中 -->
							<input name="orgPcHonorPicture" type="hidden" />
						</div>
					</div>
				</div>
				
				<div>	
					<div class="form-group">
					</div> 
				</div>
		</div>
		
		
		<div class="row" id="teamInfo">
				<div class="page-header">
					<h4><strong>团队信息</strong></h4>
					<span  style="color: blue;font-size: 15px;">注意：团队成员头像建议不小于200*200的半身照。</span>
					<label class="control-label col-md-2"></label>
				</div>
				<div class="col-sm-12" id="teamList">
					<c:forEach var="team" items="${orgInfo.teams}" varStatus="idx">
	                   <div class="form-group" id="team-${idx.count}">
                    	<div class="row">
	                        <label class="control-label col-md-3">团队成员：</label>
	                        <input type="hidden" name="teams_team${idx.count}.tid" value="${team.tid}"/><!-- 团队成员主键id -->
	                        <div id="team-errors-${idx.count}" class="center-block" style="width:800px;display:none"></div>
	                      </div>
	                        <div class="row">
		                         <div class="col-md-2">
									    <div class="kv-avatar center-block" style="width:200px">
									        <input type="file" class="uploadHead" data-md5="${team.orgIcon}?f=png" data-errors="team-errors-${idx.count}" data-validateerrors="team-icon-${idx.count}" />
									    </div>
									    <div class="form-group" style="text-align: center;">
											<input id="team-icon-${idx.count}" name="teams_team${idx.count}.orgIcon" type="hidden" value="${team.orgIcon}" required="required"/>
										</div>
								</div>
		                         
								<div class="col-md-8" style="margin:40px 0px 0px 50px;">
										<div class="row">
											<div class="form-group col-md-4">
												<input type="text" class="form-control" name="teams_team${idx.count}.orgMemberName" value="${team.orgMemberName}" autocomplete="off" placeholder="请输入成员姓名" required />
											</div>
											<div class="form-group col-md-4">
												<input type="text" class="form-control" name="teams_team${idx.count}.orgMemberGrade" value="${team.orgMemberGrade}" autocomplete="off" placeholder="请输入成员职位" required />
											</div>
										</div>
			                    <div class="row">
			                        	<div class="form-group col-md-8">
											<textarea name="teams_team${idx.count}.orgDescribe" style="width: 600px;height: 100px;" placeholder="个人简介(建议300字以内)" required>${team.orgDescribe}</textarea>
										</div>
										<div class="col-md-4" style="padding:20px 0px 0px 130px">
				                          		<!-- return false 阻止表单提交 -->
				                               <button type="button" class="btn btn-default btn-danger" onclick="deleteTeam(this);" data-teamrowid="team-${idx.count}" data-teamid="${team.tid}" data-orgNumber="${orgInfo.orgNumber}"><i class="fa fa-trash-o"></i> 删除</button>
				                          </div>
									 </div>
								 </div>
						</div>
				 </div>
	           </c:forEach>
				   
			</div>
			
			  <div class="row" id="addTeamInfo">
							<div class="col-md-12">
				                      <div class="form-group">
				                          <label class="col-md-4"></label>
				                          <div class="col-md-8">
				                          		<!-- return false 阻止表单提交 -->
				                               <button type="button" class="btn btn-default" onclick="addTeam(this);"><i class="fa fa-plus"></i> 新增成员</button>
				                          </div>
				                      </div>
				             </div>
				   </div> 
		</div>
		
	   		    
		<div class="row" style="padding-top: 100px;">
				<div class="col-md-12">
	                      <div class="form-group">
	                          <label class="col-md-5"></label>
	                          <div class="col-md-7">
	                               <button type="submit" class="btn btn-primary"><i class="fa fa-check"></i> 保存</button>
	                               &nbsp&nbsp&nbsp
	                               <button type="button" class="btn btn-default" onclick='javascript:$.switchPage("合作机构列表","rest/cim/cimorginfo/list");'><i class="fa fa-arrow-left"></i> 返回</button>
	                          </div>
	                      </div>
	             </div>
	    </div>              	
	
	</form>	
	
</div>


<script type="text/javascript" src="app/cimorginfo/multipleimgupload.js"></script>
<script type="text/javascript" src="app/cimorginfo/ueditor-plug.js"></script>
<script type="text/javascript" src="app/cimorginfo/imgupload.js"></script>
<script type="text/javascript">
	/*延迟1秒加载图像初始化插件*/
	setTimeout(function(){
		$("<script>").attr({type:"text/javascript",src:"app/cimorginfo/headupload.js"}).appendTo("body");
	},1000);
</script>
<script type="text/javascript" src="app/cimorginfo/formdata-convert-tojson.js"></script>
<script type="text/javascript" src="app/cimorginfo/cimorginfo-edit.js"></script>