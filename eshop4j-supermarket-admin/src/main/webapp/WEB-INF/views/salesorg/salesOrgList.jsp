<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>
<div id="main-news" class="container-fluid">
    <div class="table-responsive">
        <table id="J-newslist" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="rest/crmSalesOrg/getSalesOrgList" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" date-order="true" data-edit="id" data-format="id:cumsId">序号</th>
                    <th data-name="name"  >机构名称</th>
                    <th data-name="contacts">联系人</th>
                    <th data-name="contactMobile">联系电话</th>
                    <th data-name="cfplannerCount" data-format="status:cfplannerCountFormat">下属理财师</th>
                    <th data-name="thisMonthSales" data-format="status:salesDetailFormat">本月销售总额(万元)</th>
                    <th data-name="historySales" >累计销售额(万元)</th>
                    <th data-name="thisMonthFee" data-format="status:salesDetailFormat">本月销售佣金(元)</th>
                    <th data-name="lastMonthSales" >上月销售额(万元)</th>
                    <th data-name="lastMonthYearSales" >上月年化销售额(万元)</th>
                    <th data-name="lastMonthStockSales" >上月存量销售额(万元)</th>
                    <th data-name="lastMonthStockYearSales" >上月存量年化销售额(万元)</th>
                    <th data-name="cooperationStatus" data-format="status:cums">合作状态</th>
                    <th data-name="cooperationTime">开始合作时间</th>
                    <th data-format="id:doFCT">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">

	    var doFCT = function (data,type,full,meta) {
	      	//渲染 把数据源中的标题和url组成超链接
	      	var a = '<a href="#" class="btn btn-default btn-sm ui-redirect" data-title="查看" data-url="/rest/crmSalesOrg/view?id='+ data +'">查看</a>';
	      	var b = '<a href="#" class="btn btn-default btn-sm ui-redirect" data-title="编辑" data-url="/rest/crmSalesOrg/editPage?id='+ data +'">编辑</a>';
	      	var c = '<a href="#" class="btn btn-default btn-sm ui-redirect" data-title="导入" data-url="/rest/crmSalesOrg/importPage?salesOrgId='+ full.salesOrgId +'">导入理财师</a>';
	        return a+b+c;
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
        var cfplannerCountFormat = function (data,type,full,meta) {
        	if(data!=null && data >0) {
        		return "<a class='ui-redirect' data-debug='0' data-title='理财师' data-url='rest/crmSalesOrg/cfplannerSalesListPage?salesOrgId="+full.salesOrgId+"'>"+data+"</a>";
        	} else {
        		return data;
        	} 
        }
        var salesDetailFormat = function (data,type,full,meta) {
        	if(data!=null && data >0) {
        		return "<a class='ui-redirect' data-debug='0' data-title='销售明细' data-url='rest/crmSalesOrg/salesDetailListPage?salesOrgId="+full.salesOrgId+"'>"+data+"</a>";
        	} else {
        		return data;
        	} 
        }
       var $db= $("#J-newslist").linkweeTable();
    </script>
    <script type="text/linkwee-template" id="template-search">
        <form>
		<div class="input-group">
        	<input name="nameOrmobile"  class="form-control" style="width:200px"  placeholder="机构名称、姓名、手机号...">
        	<a class="btn btn-default J_search" href="#" role="button">查询</a>
			<span class="input-group-btn">
						<a class="btn btn-default btn-icon ui-redirect" href="javacript:void(0);" data-title="新增机构" data-url="/rest/crmSalesOrg/addPage" role="button"><i class="fa fa-plus"></i> 新增</a>
					 </span>
		</div>
        </form>
    </script>
</div>
    
    
    


