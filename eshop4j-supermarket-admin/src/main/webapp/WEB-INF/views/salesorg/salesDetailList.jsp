<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.eshop4j.js"></script>
<input type="hidden" id="salesOrgId" value="${salesOrgId}">
<div id="main-news" class="container-fluid">
    <div class="table-responsive">
        <table id="J-newslist" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="rest/crmSalesOrg/getSalesDetailList?salesOrgId=${salesOrgId}" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" date-order="true" data-edit="id" data-format="id:cumsId">序号</th>
                    <th data-name="cfpName"  >理财师姓名</th>
                    <th data-name="cfpMobile">理财师手机</th>
                    <th data-name="city">所在城市</th>
                    <th data-name="investorName">投资人姓名</th>
                    <th data-name="investorMobile">投资人手机</th>
                    <th data-name="investAmount">投资金额(元)</th>
                    <th data-name="platform">投资平台</th>
                    <th data-name="investTime">投资时间</th>
                    <th data-name="salesCost">销售佣金(元)</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">

	    var doFCT = function (data,type,full,meta) {
	      	//渲染 把数据源中的标题和url组成超链接
	      	var a = '<a href="#" class="btn btn-default btn-sm ui-redirect" data-title="查看" data-url="/rest/crmSalesOrg/view?id='+ data +'">查看</a>';
	      	var b = '<a href="#" class="btn btn-default btn-sm ui-redirect" data-title="编辑" data-url="/rest/crmSalesOrg/editPage?id='+ data +'">编辑</a>';
	        return a+b;
	    }
	    var cums = function (data,type,full,meta) {
	    	if(data!=null && data ==1) {
	    		return "合作中";
	    	} else if(data!=null && data ==2) {
	    		return "暂停合作";
	    	} 
	    }
    
        var nameFormat = function (data,type,full,meta) {
        	if(data!=null && data !='') {
        		return data;
        	} else {
        		return "--";
        	} 
        }
        
        var cumsId = function (data,type,full,meta) {
        	return meta.row+1;
        }

        
       var $db= $("#J-newslist").eshop4jTable();
    </script>
    
   
    
    <script type="text/eshop4j-template" id="template-search">
        <form>
		<div class="input-group">
        	理财师：<input name="nameOrmobile" id="nameOrmobile"  class="form-control" style="width:200px"  placeholder="输入姓名或手机号...">
&nbsp;&nbsp;&nbsp;&nbsp;
城市：<select id="city" name="city">
				<option value="">全部</option>
            	<option value="北京">北京</option>
           		<option value="上海">上海</option>
				<option value="广州">广州</option>
				<option value="深圳">深圳</option>
				<option value="天津">天津</option>
				<option value="重庆">重庆</option>
				<option value="杭州">杭州</option>
				<option value="南京">南京</option>
				<option value="成都">成都</option>
				<option value="武汉">武汉</option>
        	 </select>
&nbsp;&nbsp;&nbsp;&nbsp;
			平台：<select id="platform" name="platform">
				<option value="">全部</option>
				  <c:forEach items="${platformList}" var="keyword" varStatus="id">
     				<option value="${keyword.orgNumber}">${keyword.name}</option>
 				    </c:forEach>
        	 </select>
&nbsp;&nbsp;&nbsp;&nbsp;
			销售时间：
				<input id="startTimeForSearch" name="startTimeForSearch" class="Wdate" type="text" value ="${startTimeForSearch}" onfocus="WdatePicker()" />
				至
				<input id="endTimeForSearch" name="endTimeForSearch" class="Wdate" type="text" value ="${endTimeForSearch}" onfocus="WdatePicker()" />
			&nbsp;&nbsp;&nbsp;&nbsp;
        	<a class="btn btn-default J_search" id="J_search_a" href="#" onclick="example()" role="button">查询</a>
&nbsp;&nbsp;&nbsp;&nbsp;默认显示当月记录
<br>
<br>
<div class="row">
	<div class="col-sm-12" id="context">查询区间内 : </div>
</div>
<br>
<br>  
		</div>
        </form>
    </script>
    
    
     <script type="text/javascript">
    function example(){
	                $.post(
	                		'rest/crmSalesOrg/getSalesDetailListTotal',
	                		{
		                		city : $('#city').val(),
		                		nameOrmobile : $('#nameOrmobile').val(),
		                		platform : $('#platform').val(),
		                		startTimeForSearch : $('#startTimeForSearch').val(),
		                		endTimeForSearch : $('#endTimeForSearch').val(),
		                		salesOrgId : $('#salesOrgId').val(),
		                	},
	                		function(result) {
	                	 		$("#context").html("查询区间内 : <br>成交  "+result.investCount+"单； 总额 "+result.totalAmt+"万元；  年化投资额"+ result.totalYearpurAmt+"万元；  理财师平均销售 "+result.avgSalesAmt+" 万元； 销售佣金总计"+result.totalFeeAmt +"万元； <br>"
	                	 				 + "存量投资额："+result.stockpurAmt +"万元；    存量年化投资额："+ result.stockYearpurAmt+"万元     " );
	                });
    	}
    
    $(function(){ 
    	
    	setTimeout(function(){
    		example()
    	},1000);
    	
    	}); 
     </script>
</div>
    
    
    


