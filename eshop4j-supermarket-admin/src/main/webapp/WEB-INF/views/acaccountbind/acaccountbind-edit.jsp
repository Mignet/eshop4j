<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <script type="text/javascript" src="app/cimorginfo/formdata-convert-tojson.js"></script>

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

<div class="container">
	<form id="frm-example"><!-- id="bindForm" action="/rest/acc/acaccountbind/update" method="post" -->
		<div class="row">
			<div class="page-header">
					<h4><strong>绑卡基本信息</strong></h4>
			</div>
			<div class="col-sm-10">
					  <div class="col-md-8">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">注册手机号码：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control required" readonly="readonly" name="mobile"  id="mobile" value="${mobile}"/>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">用户姓名：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control required" name="userName" id="userName" value="${userName}" placeholder="用户姓名"/>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">身份证号码：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control required" name="idCard" id="idCard" value="${idCard}" placeholder="身份证号码"/>
	                          </div>
	                      </div>
	                  </div>
	                  
                     <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">银行：</label>
	                          <div class="col-md-5">
	                               <select id="bankId" name="bankId" class="form-control" style="width: 150px;display: inline-block;">
			                        	<option value="">请选择银行</option>
	                               		<c:forEach items="${bankList}" var="bank">
			                            	<option value="${bank.bankId}" <c:if test="${bank.bankName eq bankName }">selected</c:if> >${bank.bankName}</option>
	                               		</c:forEach>
		                    		</select>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">银行卡号：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control required" name="bankCard" id="bankCard" value="${bankCard}" placeholder="银行卡号"/>
	                          </div>
	                      </div>
	                  </div>
	                  
	                  <div class="col-md-8 fill">
	                      <div class="form-group">
	                          <label class="control-label col-md-3">银行预留手机号码：</label>
	                          <div class="col-md-5">
	                               <input type="text" class="form-control required" name="reserveMobile" id="reserveMobile" value="${reserveMobile}" placeholder="银行预留手机号码"/>
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
	                               <button type="submit" class="btn btn-primary"><i class="fa fa-check"></i> 保存</button>
	                               &nbsp&nbsp&nbsp
	                               <button type="button" class="btn btn-default" onclick='javascript:$.switchPage("绑卡账户列表","rest/acc/acaccountbind/list");'><i class="fa fa-arrow-left"></i> 返回</button>
	                          </div>
	                      </div>
	             </div>
	    </div>              	
	
	</form>	
	
</div>

<script type="text/javascript" src="app/acaccountbind/acaccountbind-edit.js"></script>