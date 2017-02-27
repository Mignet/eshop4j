<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
</style>
<!-- 图片服务器 -->
<input type="hidden" id="imgServerUrl" value="${img_server}" />
<input type="hidden" id="checkedRdo" value="${orgDynamicInfo.orgDynamicUrl}" />
<div class="container">
	<form id="orgDynamicForm" action='/rest/cim/cimorgdynamic/<c:if test="${action eq 'add'}">add</c:if><c:if test="${action eq 'edit'}">edit</c:if>' method="post">
	<c:if test="${action eq 'edit'}">
		<!-- 机构动态信息主键id -->
		<input type="hidden" name="id" value="${orgDynamicInfo.id}" />
	</c:if>
		<div class="row">
			<div class="page-header">
					<h4><strong>机构动态信息</strong></h4>
			</div>
			<div class="col-sm-10"><!-- 小屏幕  ≥768px-->
				 <div class="col-md-8">
				 		<div class="form-group">
					        <label class="control-label col-md-3">所属机构：</label>
							 <div class="col-md-3">
								<select id="org_select" name="orgName" class="form-control" style="width: 150px;display: inline-block;margin-right: 20px" required="required">
			                        	<option value="">请选择机构</option>
										<c:forEach items="${orgList}" var="org">
			                            		<option value="${org.orgNumber}" <c:if test="${orgDynamicInfo.orgNumber eq org.orgNumber}">selected="selected"</c:if>>${org.orgName}</option>
										</c:forEach>
		                        </select>
							</div>
							
					    </div>
				    </div>
					  <div class="col-md-8 fill"><!-- 中等屏幕 ≥992px -->
	                      <div class="form-group">
	                          <label class="control-label col-md-3">动态标题：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="orgTitle"  id="orgTitle" value="${orgDynamicInfo.orgTitle}" autocomplete="off" placeholder="请输入机构动态标题" required />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
		                    <div class="form-group">
		                  	 <label class="control-label col-md-3">发布时间：</label>
		                     <div class="col-md-5">
		                       <input type="text" class="form-control" name="releaseTime" id="releaseTime" value="<fmt:formatDate value="${orgDynamicInfo.releaseTime}" pattern="yyyy-MM-dd HH:mm:ss" />" autocomplete="off" placeholder="请输入发布时间" readonly="readonly" required="required"/>
		                     </div>
		                    </div>
		              </div> 
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">
		                          	<input type="radio" name="dynamicrdo" id="orgDynamicUrlRdo" value="orgDynamicUrl" <c:if test="${!empty orgDynamicInfo.orgDynamicUrl}">checked="checked"</c:if> /> 动态链接：
	                          </label>
	                          <div class="col-md-6" id="dynamicUrlDiv">
	                               <input type="text" class="form-control" name="orgDynamicUrl" value="${orgDynamicInfo.orgDynamicUrl}" autocomplete="off" style="width: 600px;" required="required"/>
	                         </div>
	                      </div>
	                  </div>
	                          
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">
		                          <c:if test="${action eq 'add'}">
		                          	<input type="radio" name="dynamicrdo" id="orgContentRdo" value="orgContent" checked="checked" /> 正文信息：
		                          </c:if>
		                          <c:if test="${action eq 'edit'}">
		                          	<input type="radio" name="dynamicrdo" id="orgContentRdo" value="orgContent" <c:if test="${!empty orgDynamicInfo.orgContent}">checked="checked"</c:if> /> 正文信息：
		                          </c:if>
	                          </label>
	                          <div class="col-md-6" id="orgContentDiv" style="z-index: 1">
	                               <textarea id="orgContent" class="ueditorPlug" name="orgContent" style="width: 800px;" required="required">${orgDynamicInfo.orgContent}</textarea>
	                          </div>
	                      </div>
	                  </div>
	                  
			 </div> <!--小屏幕宽度限制-->
			
		</div>
		
		
		<div class="row" style="padding-top: 100px;">
				<div class="col-md-12">
	                      <div class="form-group">
	                          <label class="col-md-5"></label>
	                          <div class="col-md-7">
	                               <button type="submit" class="btn btn-primary"><i class="fa fa-check"></i> 发布</button>
	                               &nbsp&nbsp&nbsp
	                               <button type="button" class="btn btn-default" onclick='javascript:$.switchPage("合作机构动态","rest/cim/cimorgdynamic/list");'><i class="fa fa-arrow-left"></i> 返回</button>
	                          </div>
	                      </div>
	             </div>
	    </div>              	
	
	</form>	
	
</div>

<script type="text/javascript" src="app/cimorgdynamic/ueditor-plug.js"></script>
<script type="text/javascript" src="app/cimorgdynamic/cimorgdynamic-view.js"></script>