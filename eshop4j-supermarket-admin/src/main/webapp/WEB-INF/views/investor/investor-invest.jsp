<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.eshop4j.js"></script>
<div id="main-news" class="container-fluid">
    <div class="table-responsive">
        <table id="J-newslist" class="table table-bordered" data-defer="true" data-xtoolbars="#template-search" data-url="rest/investorInvest/getInvestorInvest" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" date-order="true" data-edit="id" data-format="id:cumsId">序号</th>
                    <th data-name="userName"  data-format="status:nameFormat">姓名</th>
                    <th data-name="mobile">电话</th>
                    <th data-name="rectInvestTime">最后投资时间</th>
                    <th data-name="saleProfix">销售收入</th>
                    <th data-name="totalInvestAmount"  data-format="status:totalInvestFormat">投资总额(元)</th>
                    <th data-name="currInvestAmount">在投金额(元)</th>
                    <th data-name="investCount">投资笔数</th>
                    <th data-name="totalProfit">收益总额(元)</th>
                    <th data-name="hongbaoAmount">红包金额(元)</th>
                    <th data-name="otherReward">其他奖励(元)</th>
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
        
        var totalInvestFormat = function (data,type,full,meta) {
        	if(data!=null && data >0) {
        		return "<a class='ui-redirect' data-debug='0' data-title='投资明细' data-url='rest/investorInvest/investRecordPage?userId="+full.userId+"'>"+data+"</a>";
        	} else {
        		return data;
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
        	<input name="name"  class="form-control" style="width:200px"  placeholder="输入姓名或手机号...">
        	<a class="btn btn-default J_search" href="#" role="button">查询</a>
		</div>
        </form>
    </script>
</div>
    
    
    


