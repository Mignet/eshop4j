<%--
  Created by IntelliJ IDEA.
  User: lenli
  Date: 2016/5/31
  Time: 10:09
  To change this template use File | Settings | File Templates.
  理财师销售与收益列表
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="app/css/eshop4j.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.eshop4j.js"></script>


<div id="main-sale" class="container-fluid">
    <div class="table-responsive">
        <table id="J-salelist" class="table table-bordered" data-url="rest/cfplannerSale/getCfplannerSaleList" data-order="false" data-xtoolbars="#template-search" data-defer="true">
            <thead>
            <tr>
                <th data-name="id" data-format="id:serial">序号</th>
                <th data-name="userName" data-format='userName:nameFormat'> 理财师</th>
                <th data-name="mobile"> 电话号码</th>
                <th data-name="totalSales" data-format="investmentTotalAmount:float"> 累计销售</th>
                <th data-name="countSales">销售笔数</th>
                <th data-name="fee" data-format="fee:linkFormat">佣金(元)</th>
                <th data-name="allowance" data-format="allowance:linkFormat"> 推荐收益(元)</th>
                <th data-name="activityReward" data-format="activityReward:linkFormat">活动奖励(元)</th>
                <th data-name="currInvestAmount" data-format="currInvestAmount:linkFormat">客户在投(元)</th>
            </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">
        var linkFormat = function (data,type,full,meta) {
            var num = Number(data);
            var title = "销售佣金明细";
            if(!full){
                return "";
            }
            var url = 'rest/cfplannerSale/feeDetailList?userId='+full.userId;
            switch (meta.col){

                case 5:
                     num = new Number(full.fee);
                    title = "销售佣金明细";
                    url = 'rest/cfplannerSale/feeDetailList?userId='+full.userId;
                    break;
                case 6:
                     num = new Number(full.allowance);
                    title = "推荐收益明细";
                    url = 'rest/cfplannerSale/allowanceDetailList?userId='+full.userId;
                    break;
                case 7:
                     num = new Number(full.activityReward);
                    title = "活动奖励明细";
                    url = 'rest/cfplannerSale/activityRewardList?userId='+full.userId;
                    break;
                case 8:
                     num = new Number(full.currInvestAmount);
                    title = "当前客户在投";
                    url = 'rest/cfplannerSale/customerInvestList?userId='+full.userId;
                    break;
            }
            return data?'<a class="ui-redirect" data-title="'+title+'" data-url="'+url+'">'+num.toFixed(2)+'</a>':'0';
        }
       var $datatables =  $("#J-salelist").eshop4jTable();
       
       var nameFormat = function (data,type,full,meta) {
       	if(data!=null && data !='') {
       		return data;
       	} else {
       		return "--";
       	} 
       }
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
