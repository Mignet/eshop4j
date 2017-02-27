<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <input type="hidden" id="userId" value="${param.userId}">
    <input type="hidden" id="platfrom" value="${param.platfrom}">
	 <div class="row">
        <div class="col-sm-4">
			<h4><small>姓名:  ${param.name} </small></h4>
		</div>
		  <div class="col-sm-4">
			<h4><small>手机号: ${param.mobile}</small></h4>
		</div>
		<div class="col-sm-4">
			<h4><small>机构类型: ${param.platfromText}</small></h4>
		</div>
	 </div>
        <table id="J-investDetail_list" class="table table-bordered">
            <thead>
                <tr>
                    <th>投资产品</th>
		            <th>预期年化率</th>
		            <th>投资额（万元）</th>
		            <th>预计收益（元）</th>
		            <th>销售费用（元）</th>
		            <th>投资时间</th>
		           <!--  <th>起息时间</th> -->
		            <th>到期时间</th>
                    <th> 状态</th>
                </tr>
            </thead>
        </table>
    <script type="text/javascript">
    $(document).ready(function() {
        var $db = $("#J-investDetail_list").DataTable(
                {
                    ordering:false,
                    searching:false,
                    lengthChange:false,
                    paging:true,
                    select:false,
                    serverSide:true,
                    scrollX:false,
                    ajax:{
                        url:"rest/invest/queryCustomerInvestDetail",
                        type:"POST",
                        dataSrc:function (result) {
                            return result.data;
                        },
                        data:function (d) {
                            d.userId = $('#userId').val();
                            d.platfrom = $('#platfrom').val();
                            d.columns = [];
                            d.search = {};
                        }
                    },
                    columns:[
                        {data:"productName"},
                        {data:"rate"},
                        {data:"investAmt"},
                        {data:"profit"},
                        {data:"feeAmt"},
                        {data:"startTime"},
                      /*   {data:"startTime"}, */
                        {data:"endTime"},
                        {data:"status"}
                    ],
                    columnDefs:[  ],
                    language:{
                        select: {
                            rows: {
                                _: "已选择 %d 行",
                                1: "已选择 1 行"
                            }
                        },
                        "emptyTable":"没有数据表",
                        info:"显示第 _START_  至 _END_  项结果，共 _TOTAL_ 项",
                        infoEmpty:"",
                        paginate:{
                            "first":"首页",
                            "next":"下一页",
                            "previous":"上一页"
                        }
                    }
                }
        );});
    </script>




