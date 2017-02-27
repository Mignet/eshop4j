<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>

<div>
    <div class="table-responsive">
        <table id="J-newslist" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="/rest/cim/cimproductcate/list" data-order="true" data-paging="true" data-size="10" data-cols="false">
            <thead>
                <tr>
                    <th data-name="cateId">分类ID</th>
                    <th data-name="cateName">分类名称</th>
                	<th data-name="orderNum">排序</th>
                    <th data-name="cateLogoInvestor" data-format="cateLogoInvestor:cateLogo">投资者端LOGO</th>
                    <th data-name="cateLogoChannel" data-format="cateLogoChannel:cateLogo">猎财大师LOGO</th>
                    <th data-name="disabled" data-format="disabled:disabledFormat">是否生效</th>
                    <th data-name="modifyTime" >最近修改时间</th>
                    <th data-name="description" >分类描述</th>
                    <th data-name="ispublic" data-format="ispublic:ispublicFormat">是否为公共分类</th>
                    <th data-name="ifShow" data-format="ifShow:ifShowFormat">是否展示标签</th>
                    <th data-name="urlLink" >跳转链接</th>
                    <th data-name="cateDeclare" >说明</th>
                    <th data-name="orgName">主推平台</th>
                    <th  data-format="cateId:doFCT">编辑</th>
                </tr>
            </thead>
        </table>
    </div>
    
      <script type="text/javascript">
	    var cateLogo= function (data,type,full,meta) {
	    	var data = data.trim();
	    	if(data != ""){
	    		return '<img src="'+full.imgServerUrl+data+'?f=png" width="100" height="100" />';
	    	}
	    	return '';
	    }
	    
	    var disabledFormat = function (data,type,full,meta) {
            if(data==0)return "生效";
	        if(data==1)return "失效";
	        return "";
	    }
    
	    var ispublicFormat = function (data,type,full,meta) {
            if(data==0)return "公共分类";
            if(data==1)return "投呗";
            if(data==2)return "猎财大师";
            return "";
	    }
	    
	    var ifShowFormat = function (data,type,full,meta) {
            if(data==0)return "需要";
            if(data==1)return "不需要";
            return "";
	    }
	    
        var doFCT = function (data,type,full,meta) {
          	//渲染 把数据源中的标题和url组成超链接
	        var edit = '<a href="#" class="btn btn-sm btn-default btn-icon J_productCateEdit" data-title="产品分类编辑" data-id='+full.cateId+'><i class="fa fa-edit" ></i>编辑</a>';
            return edit;
        }
       var $db= $("#J-newslist").linkweeTable();
    </script>
    <script type="text/javascript" src="app/cimProductCate/cimproductcate-list.js"></script>
    <script type="text/linkwee-template" id="template-search">
          	<div class="row">
          	  <div class="col-xs-8 ">
			        <form>
			        	<input name="cateName"  class="easyui-textbox" style="width:200px"  placeholder="产品分类名称">
						主推平台:
						<select id="orgNumber" name="orgNumber">
							<option value="99" selected="selected">全部</option>
							<c:if test="${!empty cimOrginfoList}">
			    				<c:forEach items="${cimOrginfoList}"  var="cimOrginfoL">
			    					<option value="${cimOrginfoL.orgNumber}">${cimOrginfoL.name}</option>
			    				</c:forEach>
							</c:if>
			        	</select>
			        	<a class="btn btn-default btn-sm J_search" href="javascript:" role="button">查询</a>
			        </form>
			  </div>
          	  <div class="col-xs-4 text-right">
					<button type="button" title="添加产品分类" id="productCateAddButton" class="btn btn-info ">添加产品分类</button>
		      </div>
         </div>
    </script>
    
</div>

<!-- 图片服务器 -->
<input type="hidden" id="imgServerUrl" value="${img_server}"/>

<!-- 产品分类编辑 :模态框（Modal）-->
<div class="modal fade bs-example-modal-lg" id="productCateEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width: 900px">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">产品分类 </h4>
      </div>
      <div class="modal-body">
	    <form id="orgTagsEditForm"> 
	      	  <div class="row ">      	  
	      	      <div class="col-xs-12 ">
					<dl class="dl-horizontal">
					    <div style="margin: 10px 0;">
							<dt>分类名称：</dt>
							<dd>
							 <div class="col-xs-4">
								 <input id="cateName" name="cateName" type="text" class="form-control" placeholder="分类名称">					
							  </div>
							</dd>
						</div>
						<div style="margin: 10px 0;">
							<dt>排序：</dt>
							<dd>
							  <div class="col-xs-4">
								  <input id="orderNum" name="orderNum" type="text" class="form-control" placeholder="排序">				
							  </div>															
							</dd>
						</div>
						<div style="margin: 10px 0;">
							<dt>投资者端LOGO：</dt>
							<dd>
								<!-- 上传图片 -->	
								<div class="col-md-8">
									<div class="form-group">
										<span  class="span-hint"></span>
										<div id="uploadImgCateLogoInvestor" class="col-md-12">
											<input class="uploadImg"  type="file" id="cateLogoInvestor" accept="image/*"/>
											<!-- 上传成功存入隐藏域中 -->
											<input name="cateLogoInvestor" type="hidden" required="required"/>
										</div>
									</div>
								</div>						
							</dd>
						</div>
						<div style="margin: 10px 0;">
							<dt>猎才大师LOGO：</dt>
							<dd >
								<!-- 上传图片 -->	
								<div class="col-md-8">
									<div class="form-group">
										<span  class="span-hint"></span>
										<div id="uploadImgCateLogoChannel" class="col-md-12">
											<input class="uploadImg"  type="file" id="cateLogoChannel" accept="image/*"/>
											<!-- 上传成功存入隐藏域中 -->
											<input name="cateLogoChannel" type="hidden" required="required"/>
										</div>
									</div>
								</div>							
							</dd>
						</div>
						<div style="margin: 10px 0;">
							<dt>是否生效：</dt>
							<dd id="disabled">
								 <div class="col-md-7">
								 	 <label class="radio-inline">
								      	<input type="radio" name="disabled" value="0" checked="checked" /> 生效
								     </label>
								     <label class="radio-inline">
								      	<input type="radio" name="disabled" value="1" /> 失效
								     </label>
								</div>
							</dd>
						</div>
						<div style="margin: 10px 0;">						
							<dt>分类描述：</dt>
							<dd>
								  <div class="col-xs-4">			
								 	  <input id="description" name="description" type="text" class="form-control" placeholder="分类描述">
								  </div>
							</dd>
						</div>
						<div style="margin: 10px 0;">						
							<dt>是否为公共分类：</dt>
							<dd id="ispublic">
						 		<div class="col-md-7">
								 	 <label class="radio-inline">
								      	<input type="radio" name="ispublic" value="0" checked="checked"/> 公共分类
								     </label>
								     <label class="radio-inline">
								      	<input type="radio" name="ispublic" value="1" /> 仅投呗
								     </label>
								     <label class="radio-inline">
								      	<input type="radio" name="ispublic" value="2" /> 仅猎才大师
								     </label>							     
								</div>						
							</dd>
						</div>
						<div style="margin: 10px 0;">						
							<dt>是否展示标签：</dt>
							<dd id="ifShow">
								 <div class="col-md-7">
								 	 <label class="radio-inline">
								      	<input type="radio" name="ifShow" value="0"  /> 需要
								     </label>
								     <label class="radio-inline">
								      	<input type="radio" name="ifShow" value="1" checked="checked"/> 不需要
								     </label>
								</div>						
							</dd>
						</div>
						<div style="margin: 10px 0;">						
							<dt>跳转链接：</dt>
							<dd >
								  <div class="col-xs-10">			
								 	  <input id="urlLink" name="urlLink" type="text" class="form-control" placeholder="跳转链接">
								  </div>						
							</dd>
						</div>
						<div style="margin: 10px 0;">
							<dt>说明：</dt>
							<dd>
								  <div class="col-xs-10">			
								 	  <input id="cateDeclare" name="cateDeclare" type="text" class="form-control" placeholder="说明">
								  </div>							
							</dd>
						</div>
						<div style="margin: 10px 0;">				
							<dt>主推平台：</dt>
							<dd id="majorRecommendationPlatform">
								  <div class="col-xs-4">		
									<select id="majorRecommendationPlatform" name="majorRecommendationPlatform" class="form-control input-sm">
										<option value="99" selected="selected">不推荐</option>
										<c:if test="${!empty cimOrginfoList}">
						    				<c:forEach items="${cimOrginfoList}"  var="cimOrginfoL">
						    					<option value="${cimOrginfoL.orgNumber}">${cimOrginfoL.name}</option>
						    				</c:forEach>
										</c:if>
						        	</select>
								  </div>							
							</dd>
						</div>
					</dl>	
				  </div>
		      </div>
		      <input type="hidden" id="cateId" name="cateId" >
	     </form>    
      </div>
      <div class="row text-center">
      		<p id="productCateEditError" style="color: red;font-weight: bolder;"></p>
      </div>
      <div id="productCateEditSave" class="modal-footer">
        <button id="productCateEditSaveButton" type="button" class="btn btn-primary" data-dismiss="modal">编辑保存</button>
      </div>
      <div id="productCateAddSave" class="modal-footer">
        <button id="productCateAddSaveButton" type="button" class="btn btn-primary" data-dismiss="modal">添加</button>
      </div>
    </div>
  </div>
</div>

