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

<script type="text/javascript" src="app/activity/activityprofit-list.js"></script>
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
          url: '/rest/activityprofit/upload' ,  
          type: 'POST',  
          data: formData,  
          async: false,  
          cache: false,  
          contentType: false,  
          processData: false,  
          success: function (result) { 
        	  console.log("returndata",result);
              if (result.success) {
                 //保存成功  1.关闭弹出层，2.刷新表格数据
                 alert(result.message);
                 $("#addModal").modal("hide");
                 table.draw();
             }else {
                 alert(result.message);
                 $("#addModal").modal("hide");
                 table.draw();
             }
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
            <th>流水号</th>
            <th>活动编码</th>
            <th>理财师编码</th>
            <th>收益</th>
            <th>发放日期</th>
            <th>创建时间</th>
            <th>交易单号</th>
            <th>充值流水号</th>
            <th>用户ID</th>
            <th>备注</th>
            </tr>
        </thead>
 
    </table>
    
    <!-- Modal -->
<!--------------------------添加/修改信息的弹出层---------------------------->
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
