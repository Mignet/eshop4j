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
	<form id="orgForm" action="/rest/cim/cimorginfo/add" method="post">
		<div class="row">
			<div class="page-header">
					<h4><strong>基本信息</strong></h4>
			</div>
			<div class="col-sm-10"><!-- 小屏幕  ≥768px-->
				<input type="hidden" name="orgIsstaticproduct" value="0" /> <!-- 是否虚拟机构 -->
				<input type="hidden" name="orgGrayStatus" value="0" /> <!-- 是否灰度机构 -->
				 
				  <div class="col-md-8">
				 		<div class="form-group">
					        <label class="control-label col-md-3">虚拟机构：</label>
					        <span  class="span-hint">机构未进行技术对接需开启(ON)</span>
							 <div class="switch col-md-3">
								<input type="checkbox" id="virtualOrg" data-name="orgIsstaticproduct" class="bootstrapSwitch" />
							</div>
							
					    </div>
				    </div>
				    
				    <div class="col-md-8">
				 		<div class="form-group">
					        <label class="control-label col-md-3">机构灰度模式：</label>
					        <span  class="span-hint">设置机构为灰度模式需开启(ON)</span>
							 <div class="switch col-md-3">
								<input type="checkbox" id="orgGrayStatus" data-name="orgGrayStatus" class="bootstrapSwitch" />
							</div>
					    </div>
				    </div>
				    
				    <div class="col-md-8">
				 		<div class="form-group">
					        <label class="control-label col-md-3">对接机构类型：</label>
							 <div class="col-md-7">
							 	 <label class="radio-inline">
							      	<input type="radio" name="orgJoinType" value="0" checked /> 移动+PC端
							     </label>
							     <label class="radio-inline">
							      	<input type="radio" name="orgJoinType" value="1" /> 移动端
							     </label>
							     <label class="radio-inline">
							      	<input type="radio" name="orgJoinType" value="2" /> PC端
							     </label>
							</div>
					    </div>
				    </div>
				    
					  <div class="col-md-8 fill"><!-- 中等屏幕 ≥992px -->
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构名称：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgName"  id="orgName" autocomplete="off" placeholder="请输入机构名称" required />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构编码：</label>
	                          <span  class="span-hint">例：陆金所 = LUJINSUO</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control required" name="orgNumber" autocomplete="off" placeholder="请输入机构名称大写全拼" />
	                          </div>
	                          <!-- 
	                           <div class="col-md-4 input-prepend input-append">
								  <span class="add-on">OPEN_</span>
									 <input type="text" class="form-control" name="orgNumber" autocomplete="off" placeholder="请输入机构名称全拼" />
								  <span class="add-on">_WEB</span>
								</div>
								 -->
	                      </div>
	                      
	                  </div>
	                 
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">注册资本：</label>
	                          <span  class="span-unit">万元</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="capital" autocomplete="off" placeholder="请输入机构注册资本" />
	                          </div>
	                      </div>
	                  </div>
	              
	                  <div class="col-md-8">
	                    <div class="form-group">
	                  	 <label class="control-label col-md-3">上线时间：</label>
	                     <div class="col-md-5">
	                       <input type="text" name="upTime" id="upTime" class="form-control" autocomplete="off" placeholder="请输入机构上线时间" readonly="readonly" /> 
	                     </div>
	                    </div>
	                  </div> 
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构佣金率：</label>
	                          <span  class="span-unit">%</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgFeeRatio" autocomplete="off" placeholder="机构所属产品给理财师返佣比例" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">级差佣金率：</label>
	                          <span  class="span-unit">%</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="diffFeeRatio" autocomplete="off" placeholder="理财师级差佣金率默认为1.5%" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">安全评级：</label>
	                          <div class="col-md-5">
	                               <select id="security_grade" name="grade" class="form-control" style="width: 150px;display: inline-block;" required="required">
			                        	<option value="">请选择安全评级</option>
			                            <option value="6">AAA</option>
			                            <option value="5">AA</option>
			                            <option value="4">A</option>
			                            <option value="3">BBB</option>
			                            <option value="2">BB</option>
			                            <option value="1">B</option>
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
			                            	<option value="${orgback.configValue}">${orgback.configValue}</option>
	                               		</c:forEach>
		                    		</select>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">所在城市：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="city" autocomplete="off" placeholder="请输入机构所在城市" required="required"/>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">法人代表：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="representative" autocomplete="off" placeholder="请输入机构法人代表" required="required"/>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">ICP备案：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="icpFiling" autocomplete="off" placeholder="请输入机构icp备案" required="required"/>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">联系方式：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="contact" autocomplete="off" placeholder="请输入机构联系电话" required="required"/>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">资金托管：</label>
	                          <span  class="span-hint">不填，则默认:无托管</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="trusteeship" autocomplete="off" placeholder="请输入机构资金托管行" />
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构亮点：</label>
	                          <span  class="span-hint">有多个以英文逗号分隔(例：1,2)</span>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control" name="orgAdvantage" autocomplete="off" placeholder="请输入机构亮点介绍" required="required" />
	                          </div>
	                      </div>
	                  </div>
	                  
	             
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">机构简介：</label>
	                          <div class="col-md-5">
	                               <textarea name="orgProfile" style="width: 600px;height: 100px;" required="required"></textarea>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">安全保障：</label>
	                          <div class="col-md-5">
	                               <textarea name="orgSecurity" style="width: 600px;height: 100px;" required="required"></textarea>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">移动端-投资攻略：</label>
	                          <div class="col-md-5">
	                               <textarea id="orgInvestStrategy" class="ueditorPlug" name="orgInvestStrategy" style="width: 800px;" required="required"></textarea>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">移动端-猎财攻略：</label>
	                          <div class="col-md-5">
	                               <textarea id="orgPlannerStrategy" class="ueditorPlug" name="orgPlannerStrategy" style="width: 800px;" required="required"></textarea>
	                          </div>
	                      </div>
	                  </div>
	                  
				<!-- 上传图片 -->	
				<div class="col-md-8 fill">
					<div class="form-group">
						<label class="control-label col-md-3">移动端-机构显示Logo：</label>
						<span  class="span-hint">132*132 px,透明背景</span>
						<div class="col-md-6">
							<input class="uploadImg"  type="file" id="platformImg" />
							<!-- 上传成功存入隐藏域中 accept="image/*"-->
							<input name="platformImg" type="hidden"  required="required"/>
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
							<input class="uploadImg"  type="file" id="platformListImg" />
							<!-- 上传成功存入隐藏域中 -->
							<input name="platformListImg" type="hidden" required="required"/>
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
							<input class="uploadImg"  type="file" id="platformDetailImg" />
							<!-- 上传成功存入隐藏域中 -->
							<input name="platformDetailImg" type="hidden" required="required"/>
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
							<label class="control-label col-md-3">PC端-优质平台活动图片：</label>
							<span  class="span-hint">235*71 px</span>
							<div class="col-md-6">
								<input class="uploadImg"  type="file" id="orgAdvertisement" />
								<!-- 上传成功存入隐藏域中 -->
								<input name="orgAdvertisement" type="hidden" required="required"/>
							</div>
						</div>
					</div>
					
					<div class="col-md-8">	
						<div class="form-group">
						</div> 
					</div>
				<!--    
	                <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">PC端-优质平台活动图片链接地址：</label>
	                          <div class="col-md-9">
	                               <input type="text" class="form-control" name="orgAdvertisementUrl" autocomplete="off" placeholder="请输入PC端-优质平台活动图片链接地址" style="width: 600px;" required="required"/>
	                          </div>
	                      </div>
	                  </div>
	             -->   
				
				<div class="col-md-8 fill">
					<div class="form-group">
						<label class="control-label col-md-3">PC端-机构显示Logo：</label>
						<span  class="span-hint"></span>
						<div class="col-md-6">
							<input class="uploadImg"  type="file" id="pcPlatformImg" />
							<!-- 上传成功存入隐藏域中 -->
							<input name="pcPlatformImg" type="hidden"  required="required"/>
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
						<label class="control-label col-md-3">PC端-产品及优质平台中机构Logo：</label>
						<span  class="span-hint"></span>
						<div class="col-md-6">
							<input class="uploadImg"  type="file" id="pcPlatformListImg" />
							<input name="pcPlatformListImg" type="hidden" required="required"/>
						</div>
					</div>
				</div>
				
				<div class="col-md-8">	
					<div class="form-group">
					</div> 
				</div>
				 -->
				<!-- 上传图片 -->	
				<div class="col-md-8">
					<div class="form-group">
						<label class="control-label col-md-3">PC端-前台图片：</label>
						<span  class="span-hint"></span>
						<div class="col-md-6">
							<input class="uploadImg"  type="file" id="pcPlatformDetailImg" />
							<!-- 上传成功存入隐藏域中 -->
							<input name="pcPlatformDetailImg" type="hidden" required="required"/>
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
							<input class="uploadImg"  type="file" id="orgNewestImg" />
							<!-- 上传成功存入隐藏域中 -->
							<input name="orgNewestImg" type="hidden" required="required"/>
						</div>
					</div>
				</div>
				
				<div class="col-md-8">	
					<div class="form-group">
					</div> 
				</div>
				
				
			 </div> <!--小屏幕宽度限制-->
			
		</div>
		
		
		<div class="row" id="teamInfo">
				<div class="page-header">
					<h4><strong>团队信息</strong></h4>
					<span  style="color: blue;font-size: 15px;">注意：团队成员头像建议不小于200*200的半身照。</span>
					<label class="control-label col-md-2"></label>
				</div>
				
				<div class="col-sm-12" id="teamList">
				<!-- 
                    <div class="form-group" id="team-1">
                    	<div class="row">
	                        <label class="control-label col-md-3">团队成员：</label>
							<div id="team-errors-1" class="center-block" style="width:800px;display:none"></div>
	                      </div>
	                        <div class="row">
		                         <div class="col-md-2">
									    <div class="kv-avatar center-block" style="width:200px">
									        <input type="file" class="uploadHead" data-md5="e9cc38a97c6284d096d0cbbf8d0aafa7?f=png" data-errors="team-errors-1" data-validateerrors="team-icon-1" />
									    </div>
									   	<div class="form-group" style="text-align: center;">
											<input id="team-icon-1" name="teams_team1.orgIcon" type="hidden" required/>
										</div>
								</div>
								
									<div class="col-md-8" style="margin:40px 0px 0px 50px;">
										<div class="row">
											<div class="form-group col-md-4">
												<input type="text" class="form-control" name="teams_team1.orgMemberName" autocomplete="off" placeholder="请输入成员姓名" required />
											</div>
											<div class="form-group col-md-4">
												<input type="text" class="form-control" name="teams_team1.orgMemberGrade" autocomplete="off" placeholder="请输入成员职位"required />
											</div>
										</div>
			                        <div class="row">
			                        	<div class="form-group col-md-8">
											<textarea name="teams_team1.orgDescribe" style="width: 600px;height: 100px;" placeholder="个人简介(建议300字以内)" required></textarea>
										</div>
									 </div>
								 </div>
						</div>
                 </div>
                 
                 
                 <div class="form-group" id="team-2">
                    	<div class="row">
	                        <label class="control-label col-md-3">团队成员：</label>
	                        <div id="team-errors-2" class="center-block" style="width:800px;display:none"></div>
	                      </div>
	                        <div class="row">
		                         <div class="col-md-2">
									    <div class="kv-avatar center-block" style="width:200px">
									        <input type="file" class="uploadHead" data-md5="e9cc38a97c6284d096d0cbbf8d0aafa7?f=png" data-errors="team-errors-2" data-validateerrors="team-icon-2" />
									    </div>
									    <div class="form-group" style="text-align: center;">
											<input id="team-icon-2" name="teams_team2.orgIcon" type="hidden" required/>
										</div>
								</div>
		                         
									<div class="col-md-8" style="margin:40px 0px 0px 50px;">
										<div class="row">
											<div class="form-group col-md-4">
												<input type="text" class="form-control" name="teams_team2.orgMemberName" autocomplete="off" placeholder="请输入成员姓名" required />
											</div>
											<div class="form-group col-md-4">
												<input type="text" class="form-control" name="teams_team2.orgMemberGrade" autocomplete="off" placeholder="请输入成员职位" required />
											</div>
										</div>
			                        <div class="row">
			                        	<div class="form-group col-md-8">
											<textarea name="teams_team2.orgDescribe" style="width: 600px;height: 100px;" placeholder="个人简介(建议300字以内)" required></textarea>
										</div>
									 </div>
								</div>
						</div>
						
                 </div>
                 
                 -->
				   
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
<script type="text/javascript" src="app/cimorginfo/headupload.js"></script>
<script type="text/javascript" src="app/cimorginfo/formdata-convert-tojson.js"></script>
<script type="text/javascript" src="app/cimorginfo/cimorginfo-add.js"></script>