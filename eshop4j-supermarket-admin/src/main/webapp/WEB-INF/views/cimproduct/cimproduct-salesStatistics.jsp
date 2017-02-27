<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>
<script type="text/javascript" src="app/js/common.js"></script>

<div id="main-news" class="container-fluid">
    <div class="table-responsive">
        <table id="J-newslist" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="rest/cim/cimproduct/salesStatistics" data-paging="true" data-size="10" data-cols="false" data-order="true">
            <thead>
                <tr>
                	<th data-name="orgName">平台名称</th>
                    <th data-name="productName" data-format="productName:linkFormat">产品名称</th>
                    <th data-name="productTypeText" >产品类型</th>
                    <th data-name="productRateText" data-order="true">收益率</th>
                    <th data-name="deadLineValueText" data-order="true">产品期限</th>
                    <th data-name="statusText">产品状态</th>
                    <th data-name="saleroom" data-order="true">销售额</th>
                    <th data-name="saleroomYear" data-order="true">年化销售额</th>
                    <th data-name="investNumberOfPeople" data-order="true">投资人数</th>
                    <th data-name="investPeopleAverage">人均投资额</th>
                    <th data-name="saleCost">销售费用(元)</th>
                    <th data-name="auditPassTime">上架时间</th>
                </tr>
            </thead>
        </table>
    </div>
    
    <script type="text/javascript">
	    var linkFormat = function (data,type,full,meta) {
	        return '<p title="'+data+'" >'+cutFormatString(data,18)+'</p>';
	    }
       var $db= $("#J-newslist").linkweeTable();
       
       $(function(){  	   
	    	$("#main-news").on('click','#J_export',function(event){
				$form = $("#salesStatisticsQueryForm").attr("action","rest/cim/cimproduct/exportSalesStatistics");
				$form[0].submit();		   
	    	});	    	 
       });
    </script>
    
    <script type="text/linkwee-template" id="template-search">
        <form id="salesStatisticsQueryForm">
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
			产品状态:
			<select id="status" name="status">
				<option value="99" selected="selected">全部</option>
				<option value="1">在售</option>
            	<option value="2">售罄</option>
            	<option value="3">募集失败</option>
        	</select>
  上架时间<input id="auditStartTime" name="auditStartTime" class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'auditEndTime\')}'})"/>
    -  <input id="auditEndTime" name="auditEndTime" class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'auditStartTime\')}'})"/>
        	<a class="btn btn-default btn-sm J_search" href="#" role="button">查询</a>
			<a id="J_export" class="btn btn-default btn-sm J_export" href="javascript:;" role="button" style="float: right;">导出数据</a>
        </form>
    </script>
</div>
