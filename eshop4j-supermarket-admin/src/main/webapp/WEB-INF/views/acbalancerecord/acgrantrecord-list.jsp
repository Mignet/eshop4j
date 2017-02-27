<%@ page language="java" pageEncoding="utf-8"%>
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

<script type="text/javascript" src="app/acbalancerecord/acgrantrecord-list.js"></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>
<body>
  <script>
	
   function upload(obj) {
	   
	  var rewardName= $("#rewardName").val();
	  var fileId = $("#fileId").val();
	  if(rewardName==""){
		  alert("奖励名称不能为空!");
		  return;
	  }
	  if(fileId==""){
		  alert("请选择文件!");
		  return;
	  }
	 var formData = new FormData($( "#formUpload" )[0]);  
     $.ajax({  
          url: '/rest/acc/acbalancerecord/upload' ,  
          type: 'POST',  
          data: formData,  
          async: false,  
          cache: false,  
          contentType: false,  
          processData: false,  
          success: function (result) { 
              if (result.success) {
                 //保存成功  1.关闭弹出层，2.刷新表格数据
                 alert(result.message);
                 table.draw();
             }else {
                 alert(result.message);
                 table.draw();
             }
	         $("#addModal").modal("hide");
             $("#rewardName").val("");
             $("#fileId").val("");
             $("#profitType").val("");
          },  
          error:function(XmlHttpRequest,textStatus, errorThrown)
    	  {
    		  console.log(XmlHttpRequest.status);
    		  console.log(textStatus);
    		  showError(XmlHttpRequest.responseText);
    	  } 
     });  
   }
  </script>
	<button class="btn btn-primary" data-toggle="modal"  type="button"
				 data-target="#addModal"> 发放奖励
				</button>
</body>
<table id="dtable" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            <th>Id</th>
            <th>类型</th>
            <th>客户名称</th>
            <th>注册手机号</th>
            <th>发放金额</th>
            <th>交易日期</th>
            <th>备注</th>
            </tr>
        </thead>
</table>

<div class="modal fade" id="addModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                <i class="icon-pencil"></i><span id="lblAddTitle" style="font-weight:bold">发放奖励</span>
            </h4>
         </div>
         <form action="/rest/activityprofit/upload" method="POST" enctype="multipart/form-data" id="formUpload"> 
             <div class="modal-body">
             		<div class="row">
                    	 <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label col-md-12"><a href="/rest/acc/acbalancerecord/downloadExcelTemplate">下载导入模板</a></label>
                            </div>
                         </div>
                     </div>
                     
                     <div class="row">
                    	 <div class="col-md-12">
                            <div class="form-group">
                               <label class="control-label col-md-2">发放类型</label>
	                          <div class="col-md-10">
	                               <select id="profitType" name="profitType" class="form-control" style="width: 150px;display: inline-block;" required="required">
			                        	<option value="">请选择发放类型</option>
			                            <option value="3">活动奖励-T呗</option>
			                            <option value="4">红包-T呗</option>
			                            <option value="14">活动奖励-猎财大师</option>
			                            <option value="12">佣金-猎财大师</option>
			                            <option value="15">leader奖励-猎财大师</option>
		                    		</select>
	                          </div>
                            </div>
                         </div>
                     </div>
	                  
                    <div class="row">
                    	 <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label col-md-2">奖励名称</label>
                                <div class="col-md-10">
                                    <input id="rewardName" name="rewardName" type="text" for="rewardName" class="form-control" placeholder="奖励名称..." />
                                </div>
                            </div>
                         </div>
                     </div>
                     <div class="row"></div>
                      <div class="row">
	                      <div class="col-md-12">
	                            <div class="form-group">
	                                <label class="control-label col-md-2">上传文件</label>
	                                <div class="col-md-10">
	                                   <input type="file" name="file" id="fileId" /><br/>  
	                                </div>
	                            </div>
	                         </div>
                      
                      </div>
              </div> 
			    
	         <div class="modal-footer">
                   <button type="submit" class="btn blue" onclick="upload(this);return false;">确定</button>
                   <button id="reset" type="reset" class="btn green" data-dismiss="modal">取消</button>
	         </div>
         </form>
      </div>
  </div>
</div>
