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
        <table id="J-newslist" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="rest/crmSalesOrg/getCfplannerSalesList?salesOrgId=${salesOrgId}" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" date-order="true" data-edit="id" data-format="id:cumsId">序号</th>
                    <th data-name="userName"  >理财师姓名</th>
                    <th data-name="mobile">手机号</th>
                    <th data-name="city">所在城市</th>
                    <th data-name="customerCount" >名下客户数</th>
                    <th data-name="createTime">绑定时间</th>
                    <th data-name="totalSales">累计销售额(元)</th>
                    <th data-name="totalfee">累计销售佣金(元)</th>
                    <th data-name="thisMonthSales">本月销售额(元)</th>
                    <th data-name="thisMonthtotalfee">本月销售佣金(元)</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">

    
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

       var $db= $("#J-newslist").linkweeTable();
    </script>
    <script type="text/linkwee-template" id="template-search">
        <form>
		<div class="input-group">
        	理财师：<input name="nameOrmobile"  class="form-control" style="width:200px"  placeholder="输入姓名或手机号...">
&nbsp;&nbsp;&nbsp;&nbsp;
城市：<select id="city" name="city">
				<option value="">全部</option>
            	<option value="北京市">北京</option>
           		<option value="上海市">上海</option>
				<option value="广州市">广州</option>
				<option value="深圳市">深圳</option>
				<option value="天津市">天津</option>
				<option value="重庆市">重庆</option>
				<option value="杭州市">杭州</option>
				<option value="南京市">南京</option>
				<option value="成都市">成都</option>
				<option value="武汉市">武汉</option>
        	 </select>
&nbsp;&nbsp;&nbsp;&nbsp;
        	<a class="btn btn-default J_search" href="#" role="button">查询</a>
<span class="input-group-btn">
						<a class="btn btn-default btn-icon ui-redirect" href="javacript:void(0);" data-title="导入理财师" data-url="/rest/crmSalesOrg/importPage?salesOrgId=${salesOrgId}" role="button"><i class="fa fa-plus"></i>导入理财师</a>
					 </span>
		</div>
        </form>
    </script>
</div>
    
    
    


