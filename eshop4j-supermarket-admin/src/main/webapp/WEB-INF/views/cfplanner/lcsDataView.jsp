<%@ page language="java" pageEncoding="utf-8"%>

<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"></script>
<%--<script type="text/javascript" src="app/cfplanner/lcsDataView/lcsDataView.js"></script>--%>
<script type="text/javascript" src="assets/plugins/flot/jquery.flot.js"></script>
<script type="text/javascript" src="assets/plugins/flot/jquery.flot.resize.js"></script>


<div class="container-fluid">
    <div class="row">
        <div class="col-sm-6">
            <form  style="top: 15px;">
                日期从: <input id="lcsDataViewStartDate" name="start" class="Wdate" type="text" value ="${start}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'lcsDataViewEndDate\',{d:-1})}'})"/>
                到: <input id="lcsDataViewEndDate" name="end" class="Wdate" type="text"  value ="${end}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'lcsDataViewStartDate\',{d:1})}',maxDate:'#F{$dp.$D(\'lcsDataViewStartDate\',{d:7})}'})"/>
                <button type="button"   class="btn btn-default J_submit">搜索</button>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <div class="cfpchart" style="min-height: 300px;"></div>
        </div>
        <div class="col-sm-6">
            <div class="cfpchart-1" style="min-height: 300px;"></div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-6">
            <table>



                <tr>
                    <th align="center" id="lcsCount">理财师总计：${data.lcs.total}</th>

                </tr>
                <tr>
                    <th align="center">今日新增：${data.lcs.curday }人</th>

                </tr>
                <tr>
                    <th align="center">最近一周新增：${data.lcs.curWeek }人</th>

                </tr>
                <tr>
                    <th align="center">本月新增：${data.lcs.curmonth }人</th>
                </tr>
            </table>
        </div>
        <div class="col-sm-6">
            <table>



                <tr>
                    <th align="center" id="lcsValidCount">有效理财师总计：${data.lcsValid.total}</th>
                </tr>
                <tr>
                    <th align="center">今日新增：${data.lcsValid.curday }人</th>
                </tr>
                <tr>
                    <th align="center">最近一周新增：${data.lcsValid.curWeek }人</th>
                </tr>
                <tr>
                    <th align="center">本月新增：${data.lcsValid.curmonth }人</th>
                </tr>
            </table>
        </div>
    </div>

    <script type="text/javascript">
        var showChart = function (result) {
            var lcsData = result.data.lcs.data;
            var lcsValidData = result.data.lcsValid.data;
            var cfp_count = lcsData.length;
            var cfp_count_valid = lcsValidData.length;
            var cfpData = [];
            var cfpDataLabel=[];
            var cfpValidData = [];
            var cfpValidLabel=[];
            for(var i=0;i<cfp_count;i++){
                cfpData.push([i,lcsData[i].count]);
                cfpDataLabel.push([i,lcsData[i].date]);
            }

            for(var i=0;i<cfp_count_valid;i++){
                cfpValidData.push([i,lcsValidData[i].count]);
                cfpValidLabel.push([i,lcsValidData[i].date]);
            }
            $.plot($(".cfpchart"),[{ label: "理财师",color:"#FF9900", data: cfpData}],{
                xaxis:{ticks:cfpDataLabel},
                yaxis:{tickSize:1}
            });

            $.plot($(".cfpchart-1"),[{ label: "有效理财师",color:"#009966", data: cfpValidData}],{
                xaxis:{ticks:cfpValidLabel},
                yaxis:{tickSize:1}
            });
        }
        var loadingChart = function () {
            var start = $("#lcsDataViewStartDate").val();
            var end = $("#lcsDataViewEndDate").val();
            $.post("rest/lcsList/getLcsDataView",{start:start,end:end},function (result) {
                showChart(result);
            });
        }

        loadingChart();

        $(".J_submit").click(function () {
            loadingChart();
        });


    </script>
</div>

<div>

</div>

 