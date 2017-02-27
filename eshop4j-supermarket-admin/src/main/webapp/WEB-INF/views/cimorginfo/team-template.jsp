<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="form-group" id="team-${param.teamindex}">
                    	<div class="row">
	                        <label class="control-label col-md-3">团队成员：</label>
	                        <div id="team-errors-${param.teamindex}" class="center-block" style="width:800px;display:none"></div>
	                      </div>
	                        <div class="row">
		                         <div class="col-md-2">
									    <div class="kv-avatar center-block" style="width:200px">
									        <input type="file" class="uploadHead" data-md5="e9cc38a97c6284d096d0cbbf8d0aafa7?f=png" data-errors="team-errors-${param.teamindex}" data-validateerrors="team-icon-${param.teamindex}" />
									    </div>
									    <div class="form-group" style="text-align: center;">
											<input id="team-icon-${param.teamindex}" name="teams_team${param.teamindex}.orgIcon" type="hidden" required/>
										</div>
								</div>
		                         
									<div class="col-md-8" style="margin:40px 0px 0px 50px;">
										<div class="row">
											<div class="form-group col-md-4">
												<input type="text" class="form-control" name="teams_team${param.teamindex}.orgMemberName" autocomplete="off" placeholder="请输入成员姓名" required />
											</div>
											<div class="form-group col-md-4">
												<input type="text" class="form-control" name="teams_team${param.teamindex}.orgMemberGrade" autocomplete="off" placeholder="请输入成员职位" required />
											</div>
										</div>
			                        <div class="row">
			                        	<div class="form-group col-md-8">
											<textarea name="teams_team${param.teamindex}.orgDescribe" style="width: 600px;height: 100px;" placeholder="个人简介(建议300字以内)" required></textarea>
										</div>
										<div class="col-md-4" style="padding:20px 0px 0px 130px">
				                          		<!-- return false 阻止表单提交 -->
				                               <button type="button" class="btn btn-default btn-danger" onclick="deleteTeam(this);" data-teamrowid="team-${param.teamindex}"><i class="fa fa-trash-o"></i> 删除</button>
				                          </div>
									 </div>
								 </div>
						</div>
						
 </div>
 <script id="templatejs" type="text/javascript" src="app/cimorginfo/headupload.js"></script>