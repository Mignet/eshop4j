<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>


<div id="main-news" class="container-fluid">
    <div class="table-responsive">
        <table id="J-newslist" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="rest/cim/cimproduct/list" data-order="true" data-paging="true" data-size="10" data-cols="false" data-order="true">
            <thead>
                <tr>
                	<th data-name="id" data-format="id:tableIdFormat"><input name="select_all" id="example-select-all" type="checkbox"></th>
                    <th data-name="productName" data-format="productName:linkFormat">产品名称</th>
                    <th data-name="productTypeText">类型</th>
                	<th data-name="orgName">机构名</th>
                    <th data-name="productRateText" data-order="true">收益率</th>
                    <th data-name="feeRatio" data-order="true">佣金率</th>
                    <th data-name="deadLineValueText" data-order="true">产品期限</th>
                    <th data-name="buyTotalMoney" data-order="true">总额度</th>
                    <th data-name="saleStartTime" data-order="true">销售开始时间</th>
                    <th data-name="statusText">产品状态</th>
                    <th data-name="auditStatusText">审核状态</th>
                    <th data-name="auditTime" data-order="true">产品审核时间</th>
                    <th data-name="hotIndex" data-order="true">热门</th>
                    <th data-name="showIndex" data-order="true">列表</th>
                    <th  data-format="productId:doFCT">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    
    <script type="text/javascript">
    
	    var tableIdFormat= function (data,type,full,meta) {
	        return '<input type="checkbox" name="tableId" value="' + data + '">';
	    }
	    
	    var linkFormat = function (data,type,full,meta) {
	        return '<p title="'+data+'" class="J_productDetail"  data-id='+full.id+' data-productId='+full.productId+'  style="cursor: pointer;" >'+cutFormatString(data,8)+'</p>';
	    }
    
        var doFCT = function (data,type,full,meta) {
          	//渲染 把数据源中的标题和url组成超链接
	        var edit = '<a href="#" class="btn btn-sm btn-default btn-icon J_productEdit" data-title="产品编辑" data-id='+full.id+' data-productId='+full.productId+' data-hotIndex="'+full.hotIndex+'" data-showIndex="'+full.showIndex+'" ><i class="fa fa-edit" ></i>编辑</a>';
            return edit;
        }
       var $db= $("#J-newslist").linkweeTable();
    </script>
    <script type="text/javascript" src="app/cimproduct/cimproduct-list.js"></script>
    <script type="text/linkwee-template" id="template-search">
          	<div class="row">
          	  <div class="col-xs-8 ">
			        <form>
			        	<input name="productName"  class="easyui-textbox" style="width:200px"  placeholder="产品名称">
						机构类型:
						<select id="orgNumber" name="orgNumber">
							<option value="99" selected="selected">全部</option>
							<c:if test="${!empty cimOrginfoList}">
			    				<c:forEach items="${cimOrginfoList}"  var="cimOrginfoL">
			    					<option value="${cimOrginfoL.orgNumber}">${cimOrginfoL.name}</option>
			    				</c:forEach>
							</c:if>
			        	</select>
						产品类型:
						<select id="productType" name="productType">
							<option value="99" selected="selected">全部</option>
							<option value="1">P2P</option>
			            	<option value="2">信托</option>
			            	<option value="3">资管</option>
							<option value="4">基金</option>
			            	<option value="401">公募基金</option>
			            	<option value="402">阳光私募</option>
							<option value="403">股权基金</option>
			            	<option value="5">保险</option>
			            	<option value="6">众筹</option>
							<option value="999">其他</option>
			        	</select>
						产品状态:
						<select id="status" name="status">
							<option value="99">全部</option>
							<option value="1" selected="selected">在售</option>
			            	<option value="2">售罄</option>
			            	<option value="3">募集失败</option>
			        	</select>
						审核状态:
						<select id="auditStatus" name="auditStatus">
							<option value="99" selected="selected">全部</option>
							<option value="1">审核通过</option>
			            	<option value="2">待审核</option>
			            	<option value="3">审核未通过</option>
			         	</select>
			        	<a class="btn btn-default btn-sm J_search" href="javascript:" role="button">查询</a>
			        </form>
			  </div>
          	  <div class="col-xs-4 text-right">
					<button type="button" title="所选产品" id="partAuditButton" class="btn btn-info ">批量审核</button>
					<button type="button" title="所选机构" id="orgTagsButton" class="btn btn-info ">机构产品分类</button>
					<button type="button" title="全部待审核产品" id="allAuditButton" class="btn btn-danger " style="display:none">全部产品审核通过</button>
		      </div>
         </div>
    </script>
</div>

    
 <!-- 产品编辑 :模态框（Modal）-->
<div class="modal fade " id="productEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width: 700px">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">产品编辑 </h4>
      </div>
      <div class="modal-body">
      <form id="productEditForm">
          <div class="form-group">
          	<div class="row">
	          	<div class="col-xs-3 text-left">
	          		<strong>推荐</strong>
	          	</div>
          	</div>
          	<hr>
          </div>
          <div class="form-group">
          	<div class="row">
          	  <div class="col-xs-3 ">
		                            列表排序:
			  </div>
          	  <div class="col-xs-3 ">
		            <input type="text" id="showIndex" name="showIndex" class="form-control input-sm" value="0" placeholder="列表排序">
			  </div>
            </div>
          </div>
          <div class="row" style="  margin-top: 40px;">
	          <div class="col-xs-3 text-left">
	          	<strong>产品分类[标签]</strong>
	          </div>
          </div>
          <hr>
			<c:if test="${!empty cimProductCateListP}">
	          	<div class="row" style="margin-top: 20px;">
	          	  <div class="col-xs-3 text-right" style="margin-top: 10px;">
			          	  <strong>公共分类:</strong>
				  </div>
	          	  <div class="col-xs-9 ">
		    				<c:forEach items="${cimProductCateListP}"  var="cimPCateListP"  varStatus="cimPCateListPStatus">
			    				<c:if test="${cimPCateListPStatus.count%2 == 1}">
			    				  <div class="row" style="margin-top: 10px;">
								</c:if>
								 <div class="col-xs-6 text-left">
									<label class="checkbox-inline">
									  <input type="checkbox" name="productCate" value="${cimPCateListP.cateId}"  data="${cimPCateListP.description}"> 
									 	 ${cimPCateListP.description}
									  	<c:if test="${cimPCateListP.ifShow == 0}">[显]</c:if>
									  	<input type="text" id="Index_${cimPCateListP.cateId}" name="Index_${cimPCateListP.cateId}" class="form-control input-sm" style="width: 50px;" placeholder="排序">
									</label>
								  </div>
								<c:if test="${cimPCateListPStatus.count%2 == 0 || cimPCateListPStatus.last}">
			    				 	</div>
								</c:if>
		    				</c:forEach>
				  </div>
	            </div>
			</c:if>	
			<c:if test="${!empty cimProductCateListT}">
	          	<div class="row" style="margin-top: 20px;">
	          	  <div class="col-xs-3 text-right" style="margin-top: 10px;">
			          	  <strong>投呗:</strong>
				  </div>
	          	  <div class="col-xs-9 ">
		    				<c:forEach items="${cimProductCateListT}"  var="cimPCateListT"  varStatus="cimPCateListTStatus">
			    				<c:if test="${cimPCateListTStatus.count%2 == 1}">
			    				  <div class="row" style="margin-top: 10px;">
								</c:if>
								 <div class="col-xs-6 text-left">
									<label class="checkbox-inline">
									  <input type="checkbox" name="productCate" value="${cimPCateListT.cateId}"  data="${cimPCateListT.description}">
									  		${cimPCateListT.description}
									  		<c:if test="${cimPCateListT.ifShow == 0}">[显]</c:if>
									  <input type="text" id="Index_${cimPCateListT.cateId}" name="Index_${cimPCateListT.cateId}" class="form-control input-sm" style="width: 50px;" placeholder="排序">
									</label>
								  </div>
								<c:if test="${cimPCateListTStatus.count%2 == 0 || cimPCateListTStatus.last}">
			    				 	</div>
								</c:if>
		    				</c:forEach>
				  </div>
	            </div>
			</c:if>	
			<c:if test="${!empty cimProductCateListL}">
	          	<div class="row" style="margin-top: 20px;">
	          	  <div class="col-xs-3 text-right" style="margin-top: 10px;">
			          	  <strong>猎财大师:</strong>
				  </div>
	          	  <div class="col-xs-9 ">
		    				<c:forEach items="${cimProductCateListL}"  var="cimPCateListL"  varStatus="cimPCateListLStatus">
			    				<c:if test="${cimPCateListLStatus.count%2 == 1}">
			    				  <div class="row" style="margin-top: 10px;">
								</c:if>
								 <div class="col-xs-6 text-left">
									<label class="checkbox-inline">
									  <input type="checkbox" name="productCate" value="${cimPCateListL.cateId}"  data="${cimPCateListL.description}">
									  ${cimPCateListL.description}
									  <c:if test="${cimPCateListL.ifShow == 0}">[显]</c:if>
									  <input type="text" id="Index_${cimPCateListL.cateId}" name="Index_${cimPCateListL.cateId}" class="form-control input-sm" style="width: 50px;" placeholder="排序">
									</label>
								  </div>
								<c:if test="${cimPCateListLStatus.count%2 == 0 || cimPCateListLStatus.last}">
			    				 	</div>
								</c:if>
		    				</c:forEach>
				  </div>
	            </div>                       
			</c:if>
			<input type="hidden" id="originalShowIndex" name="originalShowIndex">
      		<input type="hidden" id="productIdForEdit" name="productIdForEdit">
      		<input type="hidden" id="productTableId" name="productTableId" >
        </form>        
      </div>
      <div class="row text-center">
      		<p id="productEditError"  style="color: red;font-weight: bolder;"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button id="productEditSave" type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>


<!-- 产品详情 :模态框（Modal）-->
<div class="modal fade bs-example-modal-lg" id="productDetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width: 900px">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">产品详情 </h4>
      </div>
      <div class="modal-body">
	      <div class="row text-center">
      		<h4>基本信息</h4>
	      </div>
      	<hr>
      	  <div class="row ">
      	      <div class="col-xs-6 ">
				<dl class="dl-horizontal">
					<dt>产品名称：</dt><dd id="productName"></dd>
					<dt>产品类型：</dt><dd id="productTypeText"></dd>
					<dt>还本付息方式：</dt><dd id="repaymentWayText"></dd>
					<dt>产品销售开始时间：</dt><dd id="saleStartTime"></dd>
					<dt>产品销售结束时间：</dt><dd id="saleEndTime"></dd>
					<dt>是否浮动利率：</dt><dd id="isFlowText"></dd>
					<dt>浮动最小利率：</dt><dd id="flowMinRate"></dd>
					<dt>浮动最大利率：</dt><dd id="flowMaxRate"></dd>
					<dt>加息利率：</dt><dd id="addRate"></dd>
					<dt>平台返现利率：</dt><dd id="platformCashback"></dd>
					<dt>是否固定期限：</dt><dd id="isFixedDeadlineText"></dd>
					<dt>产品最小期限天数：</dt><dd id="deadLineMinValue"></dd>
					<dt>产品最大期限天数：</dt><dd id="deadLineMaxValue"></dd>
					<dt>产品最小期限天数自定义：</dt><dd id="deadLineMinSelfDefined"></dd>
					<dt>产品最大期限天数自定义：</dt><dd id="deadLineMaxSelfDefined"></dd>
					<dt>是否需要募集开始截止时间：</dt><dd id="isCollectText"></dd>
					<dt>募集开始时间：</dt><dd id="collectBeginTime"></dd>
					<dt>募集截止时间：</dt><dd id="collectEndTime"></dd>
					<dt>起息方式：</dt><dd id="interestWayText"></dd>
					<dt>产品起息日格式：</dt><dd id="validBeginDate"></dd>
					<dt>产品到期日格式：</dt><dd id="validEndDate"></dd>
					<dt>产品单笔购买最小额度：</dt><dd id="buyMinMoney"></dd>
					<dt>产品单笔购买最大额度：</dt><dd id="buyMaxMoney"></dd>
					<dt>产品描述：</dt><dd ><div id="showDesc"><button id="sign">展开</button></div><div id="productDesc" style="display:none"></div></dd>
				</dl>	
			  </div>
			  <div class="col-xs-6 ">
				<dl class="dl-horizontal">
					<dt>产品总额度：</dt><dd id="buyTotalMoney"></dd>
					<dt>移动端显示排序索引：</dt><dd id="showIndex"></dd>
					<dt>是否拥有产品进度：</dt><dd id="isHaveProgressText"></dd>
					<dt>是否可赎回可转让：</dt><dd id="isRedemptionText"></dd>
					<dt>可赎回天数：</dt><dd id="redemptionTime"></dd>
					<dt>可转让天数：</dt><dd id="assignmentTime"></dd>
					<dt>货币类型：</dt><dd id="moneyTypeText"></dd>
					<dt>风控类型：</dt><dd id="riskControlTypeText"></dd>
					<dt>风险级别：</dt><dd id="riskLevelText"></dd>
					<dt>产品状态：</dt><dd id="statusText"></dd>
					<dt>创建者用户名：</dt><dd id="creator"></dd>
					<dt>创建时间：</dt><dd id="createTime"></dd>
					<dt>最后一次修改者用户名：</dt><dd id="updater"></dd>
					<dt>最后一次修改时间：</dt><dd id="updateTime"></dd>
					<dt>修改或审核操作的说明：</dt><dd id="remark"></dd>
					<dt>产品ID：</dt><dd id="productId"></dd>
					<dt>机构编码：</dt><dd id="orgNumberText"></dd>
					<dt>外部产品ID：</dt><dd id="thirdProductId"></dd>
					<dt>是否限额产品：</dt><dd id="isQuotaText"></dd>
					<dt>购买递增金额：</dt><dd id="buyIncreaseMoney"></dd>
					<dt>佣金率：</dt><dd id="feeRatio"></dd>
					<dt>审核状态：</dt><dd id="auditStatusText"></dd>
					<dt>审核时间：</dt><dd id="auditTime"></dd>
				</dl>	
			  </div>
	      </div>
	      <div class="row text-center">
      		<h4>统计信息</h4>
	      </div>
	    <hr>
      	  <div class="row ">
      	      <div class="col-xs-6 ">
				<dl class="dl-horizontal">
					<dt>产品已投资总额度：</dt><dd id="buyedTotalMoney"></dd>
					<dt>产品已投资人数：</dt><dd id="buyedTotalPeople"></dd>
				</dl>	
			  </div>
			  <div class="col-xs-6 ">
			  </div>
	      </div>	    
      </div>
      <div class="row text-center">
      		<p id="productDetailError"  style="color: red;font-weight: bolder;"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>


 <!-- 产品审核:模态框（Modal）-->
<div class="modal fade" id="productAuditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">产品审核 </h4>
      </div>
      <div class="modal-body">
          <div class="form-group">
			<div class="row">
			  <div class="col-xs-6 text-center">
				<label class="radio-inline text-left">
					  <input type="radio" name="productAuditCode"  value="1"> 批量审核通过
				</label>
			  </div>
			  <div class="col-xs-6 text-center">
				<label class="radio-inline text-left">
					  <input type="radio" name="productAuditCode"  value="3" checked="checked"> 批量审核不通过
				</label>
			  </div>			  
			</div>
          </div>   
      </div>
      <div class="row text-center">
      		<p id="productAuditError"  style="color: red;font-weight: bolder;"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button id="productAuditSave" type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>

<!-- 为机构添加产品标签 :模态框（Modal）-->
<div class="modal fade " id="orgEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width: 700px">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">添加机构产品标签 </h4>
      </div>
      <div class="modal-body">
      <form id="orgTagsEditForm">                  
			<c:if test="${!empty cimProductCateListP}">
	          	<div class="row" style="margin-top: 20px;">
	          	  <div class="col-xs-3 text-right" style="margin-top: 10px;">
			          	  <strong>公共分类:</strong>
				  </div>
	          	  <div class="col-xs-9 ">
		    				<c:forEach items="${cimProductCateListP}"  var="cimPCateListP"  varStatus="cimPCateListPStatus">
			    				<c:if test="${cimPCateListPStatus.count%2 == 1}">
			    				  <div class="row" style="margin-top: 10px;">
								</c:if>
								 <div class="col-xs-6 text-left">
									<label class="checkbox-inline">
									  <input type="checkbox" name="productCate" value="${cimPCateListP.cateId}"  data="${cimPCateListP.description}"> 
									 	 ${cimPCateListP.description}
									  	<c:if test="${cimPCateListP.ifShow == 0}">[显]</c:if>									  	
									</label>
								  </div>
								<c:if test="${cimPCateListPStatus.count%2 == 0 || cimPCateListPStatus.last}">
			    				 	</div>
								</c:if>
		    				</c:forEach>
				  </div>
	            </div>
			</c:if>	
			<c:if test="${!empty cimProductCateListT}">
	          	<div class="row" style="margin-top: 20px;">
	          	  <div class="col-xs-3 text-right" style="margin-top: 10px;">
			          	  <strong>投呗:</strong>
				  </div>
	          	  <div class="col-xs-9 ">
		    				<c:forEach items="${cimProductCateListT}"  var="cimPCateListT"  varStatus="cimPCateListTStatus">
			    				<c:if test="${cimPCateListTStatus.count%2 == 1}">
			    				  <div class="row" style="margin-top: 10px;">
								</c:if>
								 <div class="col-xs-6 text-left">
									<label class="checkbox-inline">
									  <input type="checkbox" name="productCate" value="${cimPCateListT.cateId}"  data="${cimPCateListT.description}">
									  		${cimPCateListT.description}
									  		<c:if test="${cimPCateListT.ifShow == 0}">[显]</c:if>									  
									</label>
								  </div>
								<c:if test="${cimPCateListTStatus.count%2 == 0 || cimPCateListTStatus.last}">
			    				 	</div>
								</c:if>
		    				</c:forEach>
				  </div>
	            </div>
			</c:if>	
			<c:if test="${!empty cimProductCateListL}">
	          	<div class="row" style="margin-top: 20px;">
	          	  <div class="col-xs-3 text-right" style="margin-top: 10px;">
			          	  <strong>猎财大师:</strong>
				  </div>
	          	  <div class="col-xs-9 ">
		    				<c:forEach items="${cimProductCateListL}"  var="cimPCateListL"  varStatus="cimPCateListLStatus">
			    				<c:if test="${cimPCateListLStatus.count%2 == 1}">
			    				  <div class="row" style="margin-top: 10px;">
								</c:if>
								 <div class="col-xs-6 text-left">
									<label class="checkbox-inline">
									  <input type="checkbox" name="productCate" value="${cimPCateListL.cateId}"  data="${cimPCateListL.description}">
									  ${cimPCateListL.description}
									  <c:if test="${cimPCateListL.ifShow == 0}">[显]</c:if>								  
									</label>
								  </div>
								<c:if test="${cimPCateListLStatus.count%2 == 0 || cimPCateListLStatus.last}">
			    				 	</div>
								</c:if>
		    				</c:forEach>
				  </div>
	            </div>                       
			</c:if>
        </form>        
      </div>
      <div class="row text-center">
      		<p id="orgTagsEditError"  style="color: red;font-weight: bolder;"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button id="orgTagsEditSave" type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>
