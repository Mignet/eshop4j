<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<!-- moment -->
<script type="text/javascript" src="assets/plugins/bootstrap-daterangepicker/moment.min.js"  ></script>
<!-- Editor -->
<script type="text/javascript" src="assets/plugins/data-tables/extensions/Editor/js/dataTables.editor.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Editor/css/editor.dataTables.min.css"  />

<script type="text/javascript" src="assets/plugins/data-tables/extensions/Buttons/js/dataTables.buttons.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Buttons/css/buttons.dataTables.min.css"  />

<script type="text/javascript" src="assets/plugins/data-tables/extensions/Select/js/dataTables.select.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Select/css/select.dataTables.min.css"  />

<script type="text/javascript" src="app/activity/redpaper-list.js"></script>


<style>

div.editor-datetime{
z-index: 9997 !important;
}
div.DTED_Lightbox_Wrapper{
z-index: 9996 !important;
}
div.DTE div.DTE_Body div.DTE_Field > label {
        float: none;
        clear: both;
        width: 100%;
    }
div.DTE div.DTE_Body div.DTE_Field > div.DTE_Field_Input {
    float: none;
    clear: both;
    width: 100%;
}
</style>

<input type="hidden" id="path" value="<%=request.getContextPath() %>" />
<input type="hidden" id="operator" value="${userInfo.username}" />
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>
<table id="dtable" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>红包ID</th>
                <th>红包名称</th>
                <th>红包金额</th>
                <th>发放数量</th>
                <th>发放时间</th>
                <th>到期时间</th>
                <th>添加时间</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
        </thead>
 
    </table>

<!-- 正式发送红包 :模态框（Modal）-->
<div class="modal fade" id="sendRedpaperModal" tabindex="-1" role="dialog" aria-hidden="true">
                   <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h4 class="modal-title" id="myModalLabel">红包发放</h4>
                            </div>
                            <div class="modal-body">
                            	<h5>发放用户</h5>
                                <form role="form"  method="post" id="myForm">
	                                <div class="form-group">
										  <label>
										     <input type="radio" name="lcsType" value="all" checked>所有理财师
										  </label>
									</div>
	                                   
	                                  <div class="form-group">
										  <label>
										     <input type="radio"  name="lcsType" value="lcsByLevel">按职级向理财师发放
										  </label>
									  </div>
									  <div id="options" style="display: none;">
										  <div class="checkbox">
										    <label>
										      <input type="checkbox" name="cbolevel" value="PROBATION"> 理财师
										    </label>
										  </div>
										  <div class="checkbox">
										    <label>
										      <input type="checkbox" name="cbolevel" value="INTERNSHIP"> 高级理财师
										    </label>
										  </div>
										  <div class="checkbox">
										    <label>
										      <input type="checkbox" name="cbolevel" value="JUNIOR"> 资深理财师
										    </label>
										  </div>
										  <div class="checkbox">
										    <label>
										      <input type="checkbox" name="cbolevel" value="MIDDLE"> 金牌理财师
										    </label>
										  </div>
										  <div class="checkbox">
										    <label>
										      <input type="checkbox" name="cbolevel" value="HIGH"> 白金理财师
										    </label>
										  </div>
										  <div class="checkbox">
										    <label>
										      <input type="checkbox" name="cbolevel" value="SENIOR"> 钻石理财师
										    </label>
										  </div>
										  <div class="checkbox">
										    <label>
										      <input type="checkbox" name="cbolevel" value="SUPER"> 私人银行家
										    </label>
										  </div>
									  </div>
									  <div class="form-group">
										  <label>
										     <input type="radio"  name="lcsType" value="importlcs">导入理财师名单发放红包
										  </label>
										  <div id="uploadForm" style="display: none;">
										    <input id="file" type="file" name="file"/>
										</div>
									  </div>
	                                   <div class="form-group">
	                                    	<label class="control-label">发放数量</label>
	                                        <input type="text" class="form-control" name="sendNums" placeholder="为每位理财师发放多少个红包">
	                                    </div>
	                                    
	                                    
                                </form>
                                
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="sendRedpaper">正式发送</button>
                            </div>
                        </div>
                    </div>
           </div>

<!-- 白名单模态框 -->
<div class="modal fade" id="sendWhiteListRedpaperModal" tabindex="-1" role="dialog" aria-hidden="true">
                   <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h4 class="modal-title">白名单用户红包发放</h4>
                            </div>
                            <div class="modal-body">
	                                   <div class="form-group">
	                                    	<label class="control-label">发放数量</label>
	                                        <input type="text" class="form-control" name="sendNums" id="whiteSendNums" placeholder="为每位理财师发放多少个红包">
	                                    </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="sendWhiteListRedpaper">白名单发送</button>
                            </div>
                        </div>
                    </div>
           </div>