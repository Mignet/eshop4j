<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
<div class="container">
		<div class="row">
			<div class="page-header">
					<h4><strong>基本信息</strong></h4>
			</div>
			<div class="col-sm-10"><!-- 小屏幕  ≥768px-->
					
					<div class="col-md-8">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">是否虚拟机构：</label>
	                          <div class="col-md-5">
	                             <c:choose> 
									  <c:when test="${orgInfo.orgIsstaticproduct == 1}">
									  	<input type="text" class="form-control"  value="是" readonly="readonly" />
									  </c:when> 
									  <c:when test="${orgInfo.orgIsstaticproduct == 0}">   
									   	<input type="text" class="form-control"  value="否" readonly="readonly" />  
									  </c:when> 
									  <c:otherwise>
									  	 <input type="text" class="form-control"  value="否" readonly="readonly" />   
									  </c:otherwise> 
								</c:choose> 
	                          </div>
	                      </div>
	                 </div>
	                  
					<div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">合作状态：</label>
	                          <div class="col-md-5">
	                             <c:choose> 
									  <c:when test="${orgInfo.status == 1}">
									  	<input type="text" class="form-control"  value="合作中" readonly="readonly" />
									  </c:when> 
									  <c:when test="${orgInfo.status == 0}">   
									   	<input type="text" class="form-control"  value="合作结束" readonly="readonly" />  
									  </c:when> 
									  <c:otherwise>
									  	 <input type="text" class="form-control"  value="待上线" readonly="readonly" />   
									  </c:otherwise> 
								</c:choose> 
	                          </div>
	                      </div>
	                  </div>
	                  
					  <div class="col-md-8 fill"><!-- 中等屏幕 ≥992px -->
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构名称：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgName"  id="orgName" value="${orgInfo.name}" readonly="readonly"/>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构编码：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgNumber" value="${orgInfo.orgNumber}" readonly="readonly" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                 
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">后台账户：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgAccount" value="${orgInfo.orgAccount}" readonly="readonly" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">注册资本：</label>
	                          <span  class="span-unit">万元</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="capital" value="${orgInfo.capital}" readonly="readonly" />
	                          </div>
	                      </div>
	                  </div>
	                  
		              <div class="col-md-8">
	                    <div class="form-group">
	                  	 <label class="control-label col-md-3">上线时间：</label>
	                     <div class="col-md-5">
	                       <input type="text" name="upTime" id="upTime" class="form-control" value="<fmt:formatDate value="${orgInfo.upTime}" pattern="yyyy-MM-dd" />" readonly="readonly" /> 
	                     </div>
	                    </div>
	                  </div> 
                  
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">安全评级：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="grade" value="${orgInfo.grade}" readonly="readonly" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">首页推荐：</label>
	                          <div class="col-md-5">
		                          <c:choose> 
									  <c:when test="${orgInfo.recommend == 1}">
									  	 <input type="text" class="form-control"  value="推荐" readonly="readonly" />
									  </c:when> 
									  <c:when test="${orgInfo.recommend == 0}">   
									   	<input type="text" class="form-control"  value="不推荐" readonly="readonly" />
									  </c:when> 
									  <c:otherwise>   
									 	 <input type="text" class="form-control"  value="不推荐" readonly="readonly" />
									  </c:otherwise> 
									</c:choose> 
	                          
	                          </div>
	                      </div>
	                  </div>
	                  
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构背景：</label>
	                          <div class="col-md-5">
	                           	<input type="text" class="form-control" name="context" value="${orgInfo.context}" readonly="readonly" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">安全保证金：</label>
	                          <span  class="span-unit">元</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgMargin" value="${orgInfo.orgMargin}" readonly="readonly" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">所在城市：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="city" value="${orgInfo.city}" readonly="readonly" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">法人代表：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="representative" value="${orgInfo.representative}" readonly="readonly" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">ICP备案：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="icpFiling" value="${orgInfo.icpFiling}" readonly="readonly" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">联系方式：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="contact" value="${orgInfo.contact}" readonly="readonly" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">资金托管：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="trusteeship" value="${orgInfo.trusteeship}" readonly="readonly" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                   <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构亮点：</label>
	                          <span  class="span-hint">有多个以英文逗号分隔(例：1,2)</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgAdvantage" value="${orgInfo.orgAdvantage}" readonly="readonly" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">产品自定义标签：</label>
	                          <span  class="span-hint">有多个以英文逗号分隔(例：1,2)</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgProductTag" value="${orgInfo.orgProductTag}" readonly="readonly" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                   <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构佣金率：</label>
	                          <span  class="span-unit">%</span>
	                          <div class="col-md-2">
	                               <input type="text" class="form-control" name="orgFeeRatio" value="${orgInfo.orgFeeRatio}" readonly="readonly" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                   <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">最小年化收益：</label>
	                          <span  class="span-unit">%</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgFeeRatio" value="${orgInfo.minProfit}" readonly="readonly" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                   <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">最大年化收益：</label>
	                          <span  class="span-unit">%</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgFeeRatio" value="${orgInfo.maxProfit}" readonly="readonly" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构公钥：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control"  value="${thirdkeyConfig.orgKey}" readonly="readonly" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构私钥：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control"  value="${thirdkeyConfig.orgSecret}" readonly="readonly" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                   <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构考察报告：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="orgInvestigationReport" value="${orgInfo.orgInvestigationReport}" readonly="readonly" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构官网地址：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="orgUrl" value="${orgInfo.orgUrl}" readonly="readonly" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">产品购买地址：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="orgProductUrl" value="${orgInfo.orgProductUrl}" readonly="readonly" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">用户中心地址：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="orgUsercenterUrl" value="${orgInfo.orgUsercenterUrl}" readonly="readonly" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">绑定用户地址：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="orgBindUserUrl"  value="${orgInfo.orgBindUserUrl}" readonly="readonly" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">余额查询地址：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="orgUserbalanceUrl" value="${orgInfo.orgUserbalanceUrl}" readonly="readonly" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">用户校验地址：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="orgUserExistUrl" value="${orgInfo.orgUserExistUrl}" readonly="readonly" style="width: 600px;" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构简介：</label>
	                          <div class="col-md-5">
	                               <textarea name="orgProfile" style="width: 600px;height: 100px;" readonly="readonly">${orgInfo.orgProfile}</textarea>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">安全保障：</label>
	                          <div class="col-md-5">
	                               <textarea name="orgSecurity" style="width: 600px;height: 100px;" readonly="readonly">${orgInfo.orgSecurity}</textarea>
	                          </div>
	                      </div>
	                  </div>
				<!-- 上传图片 -->	
				<div class="col-md-8 fill">
					<div class="form-group">
						<label class="control-label col-md-3">机构logo：</label>
						<span  class="span-hint">132*132 px,透明背景</span>
						<div class="col-md-6">
							<c:if test="${!empty orgInfo.platformIco}">
								<img src="${img_server}${orgInfo.platformIco}?f=png" />
							</c:if>
						</div>
					</div>
				</div>
				
				<!-- 上传图片 -->	
				<div class="col-md-8 fill">
					<div class="form-group">
						<label class="control-label col-md-3">机构列表logo：</label>
						<span  class="span-hint">198*60 px,透明背景</span>
						<div class="col-md-6">
							<c:if test="${!empty orgInfo.platformlistIco}">
								<img src="${img_server}${orgInfo.platformlistIco}?f=png" />
							</c:if>
						</div>
					</div>
				</div>
				
				<!-- 上传图片 -->	
				<div class="col-md-8 fill">
					<div class="form-group">
						<label class="control-label col-md-3">前台照片：</label>
						<div class="col-md-6">
							<c:if test="${!empty orgInfo.platformDetailImg}">
								<img src="${img_server}${orgInfo.platformDetailImg}?f=png" style="width: 600px;height: 400px;"/>
							</c:if>
						</div>
					</div>
				</div>
				
				<!-- 上传图片 -->	
				<div class="col-md-8 fill">
					<div class="form-group">
						<label class="control-label col-md-3">营业执照：</label>
						<div class="col-md-6">
							<c:if test="${!empty orgInfo.businessLicense}">
								<img src="${img_server}${orgInfo.businessLicense}" style="width: 600px;height: 800px;"/>
							</c:if>
						</div>
					</div>
				</div>
				
				<!-- 上传图片 -->	
				<div class="col-md-8 fill">
					<div class="form-group">
						<label class="control-label col-md-3">PC端广告图片：</label>
						<div class="col-md-6">
							<c:if test="${!empty orgInfo.orgAdvertisement}">
								<img src="${img_server}${orgInfo.orgAdvertisement}" />
							</c:if>
						</div>
					</div>
				</div>
				
				
			 </div> <!--小屏幕宽度限制-->
			
		</div>
		
		<div class="row" id="teamInfo">
				<div class="page-header">
					<h4><strong>团队信息</strong></h4>
					<label class="control-label col-md-2"></label>
				</div>
				<div class="col-sm-12" id="teamList">
				
				<c:forEach var="team" items="${orgInfo.teams}" varStatus="idx">
                    <div class="form-group">
                    	<div class="row">
	                        <label class="control-label col-md-3">团队成员${idx.count}：</label>
	                      </div>
	                        <div class="row">
		                         <div class="col-md-2">
									    <div>
									    	<c:if test="${!empty team.orgIcon}">
									        	<img src="${img_server}${team.orgIcon}?f=png" />
									        </c:if>
									    </div>
								</div>
									<div class="col-md-8">
										<div class="row">
											<div class="form-group col-md-4">
												<input type="text" class="form-control" value="${team.orgMemberName}" readonly="readonly"/>
											</div>
											<div class="form-group col-md-4">
												<input type="text" class="form-control" value="${team.orgMemberGrade}" readonly="readonly"/>
											</div>
										</div>
			                        <div class="row">
			                        	<div class="form-group col-md-8">
											<textarea  style="width: 600px;height: 100px;" readonly="readonly">${team.orgDescribe}</textarea>
										</div>
									 </div>
								 </div>
						</div>
                 </div>
                 
              </c:forEach>
		</div>
		
			<div class="row" style="padding-top: 100px;">
					<div class="col-md-12">
		                      <div class="form-group">
		                          <label class="col-md-5"></label>
		                          <div class="col-md-7">
		                               <button type="button" class="btn btn-default" onclick='javascript:$.switchPage("合作机构列表","rest/cim/cimorginfo/list");'><i class="fa fa-arrow-left"></i> 返回</button>
		                          </div>
		                      </div>
		             </div>
		    </div>    
</div>