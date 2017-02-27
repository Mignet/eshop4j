<%--
  Created by IntelliJ IDEA.
  User: Mignet
  Date: 2016/6/7
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<%--<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />--%>

<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.js"  ></script>
<script type="text/javascript" src="assets/plugins/data-tables/extensions/Select/js/dataTables.select.min.js"  ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>
<div class="ux-warpper">
<div class="container-fluid" id="main-accountlog">
    <div class="table-responsive">
        <table class="table table-bordered"  id="accountlog-table" data-url="rest/log/account_ajax" data-xtoolbars="#accountlog-template" data-order="true"  data-cols="false">
            <thead>
            <tr>
                <th data-name="id" data-format="id:serial">序号</th>
                <th data-name="customerName">客户名称</th>
                <th data-name="mobile">手机</th>
                <th data-name="amount" data-order="true">发放金额</th>
                <th data-name="opType" data-format="opType:opt_type_format">操作类型</th>
                <th data-name="resultMsg">结果</th>
                <th data-name="sourceType" data-format="sourceType:source_format">来源</th>
            </tr>
            </thead>
        </table>
        <div class="ux-clean"></div>
    </div>
    <script type="text/javascript">
        var source_format = function (data,type,full,meta) {
            data = data == null?1:parseInt(data);
            return data == 1?"理财师":"投资端";
        }
        var opt_type_format = function (data,type,full,meta) {
            switch (parseInt(data)){
                case 1:
                    return "绑卡";
                    break;
                case 2:
                    return "充值";
                    break;
                case 3:
                    return "提现";
                    break;
                default:
                        return "--";
                    break;
            }
        }
        $("#accountlog-table").linkweeTable();
        
    </script>
</div>
</div>
<script type="text/linkwee-template" id="accountlog-template">
    <form>
        <input name="searchValue"  style="width:200px"  placeholder="输入电话或姓名">
        日期从: <input id="lcsDataViewStartDate" name="startDate" class="Wdate" type="text"  onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'lcsDataViewEndDate\',{d:-1})}'})"/>
        到: <input id="lcsDataViewEndDate" name="endDate" class="Wdate" type="text"   onFocus="WdatePicker({minDate:'#F{$dp.$D(\'lcsDataViewStartDate\',{d:1})}'})"/>
        <a class="btn btn-default btn-sm J_search" href="#" role="button">查询</a>
    </form>
</script>