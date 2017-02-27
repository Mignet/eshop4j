<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>
<script type="text/javascript" src="app/cimproduct/cimproduct-saleList.js"></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"></script>

<div id="main-news" class="container-fluid">
    <div class="table-responsive">
        <table id="J-newslist" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="rest/cim/cimproduct/saleList" data-order="true" data-paging="true" data-size="10" data-cols="false" data-order="true">
            <thead>
                <tr>
                    <th data-name="productName">产品名称</th>
                    <th data-name="productTypeText">产品类型</th>
                	<th data-name="orgName">机构</th>
                    <th data-name="buyTotalMoney" data-order="true">总额度</th>
                    <th data-name="buyedTotalMoney" data-order="true">销售额度</th>
                    <th data-name="surplusMoney"  data-order="true">剩余额度</th>
                    <th data-name="statusText">销售状态</th>
                    <th data-name="saleStartTime" data-order="true">开售时间</th>
                    <th data-name="saleEndTime" data-order="true">售罄时间</th>
                    <th  data-format="productId:doFCT">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    
    <script type="text/javascript">
    
        var doFCT = function (data,type,full,meta) {
          	//渲染 把数据源中的标题和url组成超链接
	        var edit = '<a href="#" class="btn btn-sm btn-default btn-icon J_productSale_detail" data-title="销售明细" data-productId='+full.productId+' data-productName='+full.productName+' "><i class="fa fa-search-plus"></i>销售明细</a>';
            return edit;
        }
       var $db= $("#J-newslist").linkweeTable();
    </script>
    
    <script type="text/linkwee-template" id="template-search">
          	<div class="row">
          	  <div class="col-xs-8 ">
			        <form>
			        	<input name="productName"  class="easyui-textbox" style="width:200px"  placeholder="产品名称">
						机构类型:
						<select id="orgNumber" name="orgNumber">
							<option value="99" selected="selected">全部</option>
							<c:if test="${!empty cimOrginfoList}">
			    				<c:forEach items="${cimOrginfoList}"  var="cimOrginfoList">
			    					<option value="${cimOrginfoList.orgNumber}">${cimOrginfoList.name}</option>
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
							<option value="99" selected="selected">全部</option>
							<option value="1">在售</option>
			            	<option value="2">售罄</option>
			            	<option value="3">募集失败</option>
			        	</select>
			        	<a class="btn btn-default btn-sm J_search" href="javascript:" role="button">查询</a>
			        </form>
			  </div>
         </div>
    </script>
</div>

    
 <!-- 产品销售详情:模态框（Modal）-->
<div class="modal fade" id="productSaleDetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width: 900px">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"><span id="productSaleName"></span></h4>
      </div>
      <div class="modal-body">
          	<div class="row">
          	  <div class="col-xs-12 ">
			        <form id="productSaleDetailForm">
			        	<input id="productNaOrNumber" name="productNaOrNumber"  class="easyui-textbox" style="width:200px;margin-right: 20px;"  placeholder="投资者姓名或账号">
				  成交时间<input id="investStartTime" name="investStartTime" class="Wdate" type="text" style="margin-left: 5px;" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'investEndTime\')}'})"/>
				    -  <input id="investEndTime" name="investEndTime" class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'investStartTime\')}'})"/>
			        	<a class="btn btn-default btn-sm productSaleDetail_search" href="javascript:" role="button">搜索</a>
			        	<input type="hidden" id="productId" name="productId" >
			        </form>
			  </div>
         </div>
		<hr>
		<div class="row">
			<table class="table table-bordered table-striped">
				<thead>
			        <tr>
			          <th>投资者姓名</th>
			          <th>投资者账号</th>
			          <th>成交金额（元）</th>
			          <th>成交时间</th>
			        </tr>
			    </thead>
				<tbody id="productSaleContent">
				</tbody>			    		  
			</table>		
        </div>
        
      </div>
        
      <div class="row text-center">
      		<p id="productSaleError"  style="color: red;font-weight: bolder;"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
