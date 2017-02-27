<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
<form id="redpacketFrom">
<input name="id" type="hidden" value="${crmSalesOrg.id}" />
			<div class="page-header">
				<div class="row">
					<div class="col-md-8">
						<h4><small><p class="text-muted">新增销售机构</p></small></h4>
					</div>
				</div>
			</div>
			
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">机构名称:</label>
				<div class="col-md-4">
					  <input type="text" class="form-control" name="name" value="${crmSalesOrg.name}" autocomplete="off"  />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">所在城市:</label>
				<div class="col-md-4">
					 <input type="text" class="form-control" name="city" value="${crmSalesOrg.city}" autocomplete="off"  />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">法人代表:</label>
				<div class="col-md-4">
					 <input type="text" class="form-control" name="legalPerson" value="${crmSalesOrg.legalPerson}" autocomplete="off"  />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">联系人:</label>
				<div class="col-md-4">
					 <input type="text" class="form-control" name="contacts"  value="${crmSalesOrg.contacts}" autocomplete="off"  />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">联系方式:</label>
				<div class="col-md-4">
					 <input type="text" class="form-control" name="contactMobile" value="${crmSalesOrg.contactMobile}" autocomplete="off"  />
				</div>
				*该手机号需已注册猎财大师，销售机构费用将发放到该账户
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">管理账户:</label>
				<div class="col-md-4">
					 <input type="text" class="form-control" name="managerAccount"  value="${crmSalesOrg.managerAccount}" autocomplete="off"  />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">登录密码:</label>
				<div class="col-md-4">
					 <input type="text" class="form-control" name="password"  value="" autocomplete="off" placeholder="如需修改，请填写" />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">开始合作时间:</label>
				<div class="col-md-4">
				<input id="cooperationTime" name="cooperationTime" class="Wdate" type="text" value="<fmt:formatDate  value="${crmSalesOrg.cooperationTime}" type="date" pattern="yyyy-MM-dd" />" onfocus="WdatePicker()" />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">合作状态:</label>
				<div class="col-md-4">
					<select name="cooperationStatus">
						<option value="1" <c:if test="${crmSalesOrg.cooperationStatus == 1}">selected="selected"</c:if>>合作中</option>
						<option value="2" <c:if test="${crmSalesOrg.cooperationStatus == 2}">selected="selected"</c:if>>暂停合作</option>
					</select>
				</div>
			</div>
			 <br><br><br>
			<div class="row">
				 <div class="col-md-2 col-md-offset-4">
				 	<a class="btn btn-danger active J_addRedpacket"  role="button"  >保存</a>
				 	&nbsp;&nbsp;&nbsp;&nbsp;
				 	<a class="btn btn-default active J_goback" role="button"  >返回</a>
				 </div>
			</div>
</form>
</div>



 <!-- 模态框（Modal）-->
<script type="text/javascript"  src="assets/plugins/layer/layer.js"></script>
<script src="app/lib/security/sha256.js" type="text/javascript"></script>
<script type="text/javascript" >

$(document).ready(function() {

    $(".J_addRedpacket").on("click", function(event) {
    	var data =milo.jsonFromt($("form").serializeArray());
    	if(!data.password){
    	} else {
    		data.password=sha256_digest(data.password);
    	}
    	if(!data.name){
             layer.msg('机构名称不能为空',{time: 1000,icon: 0});
             return false;
    	}
    	if(!data.city){
             layer.msg('所在城市不能为空',{time: 1000,icon: 0});
            return false;
    	}
    	if(!data.legalPerson){
            layer.msg('法人代表不能为空',{time: 1000,icon: 0});
           return false;
   		}
    	if(!data.contacts){
            layer.msg('联系人不能为空',{time: 1000,icon: 0});
           return false;
   		}
    	if(!data.contactMobile){
            layer.msg('联系方式不能为空',{time: 1000,icon: 0});
           return false;
   		}
    	if(!data.managerAccount){
            layer.msg('管理账户不能为空',{time: 1000,icon: 0});
           return false;
   		}
    	$.post('rest/crmSalesOrg/editOrg', data, function(data, textStatus, xhr) {
            layer.msg(data.msg,{time: 1000,icon: data.isFlag?1:0});
        });
        return false;
    });

    $(".J_goback").on("click", function(event) {
        $.switchPage("机构列表","rest/crmSalesOrg/salesOrgListPage"); //跳到机构列表页面
        return false;
    });

} );
</script>
