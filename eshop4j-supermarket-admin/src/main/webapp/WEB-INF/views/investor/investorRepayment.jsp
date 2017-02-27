<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>

<div id="main-adv" class="container-fluid">

    <div class="table-responsive">
        <table id="J-InvestorRepayment_list" class="table table-bordered">
            <thead>
                <tr>
                    <th>客户姓名</th>
		            <th>客户手机号</th>
		            <th>所属理财师</th>
		            <th>理财师手机号</th>
		            <th>回款总投资额</th>
		            <th>回款项目数</th>
		            <th>在投金额</th>
		            <th>已完成金额</th>
		            <th>历史收益总额(元)</th>
                    <th> 操作</th>

                </tr>
            </thead>
        </table>
    </div>
    
    
        <script type="text/eshop4j-template" id="template-search">
  			<form id="export_investorRepayment">
            <div class="row">
				<div class="col-xs-3">
					<select class="form-control" id="dateType" name="dateType">
						<option value="0">昨天回款数据</option>
						<option value="1" selected="selected">最近3天回款数据</option>
						<option value="2">最近7天回款数据</option>
						<option value="3">最近30天回款数据</option>
		        	</select>
				</div>
				<div class="col-xs-1">
					<select class="form-control" id="type" name="type">
						<option value="0" >客户</option>
						<option value="1" >理财师</option>
		        	</select>
					
				</div>
                <div class="col-xs-2">
					<input  class="form-control" name="mobileOrName" id="mobileOrName" type="text" placeholder="姓名或者手机号码"/>
				</div>
 				<div class="col-xs-1">
					<a class="btn btn-default  J_search" href="#" role="button" style="margin-left: 20px;">查询</a>
				</div>
				 <div class="col-xs-2 col-xs-offset-1">
					<a class="btn btn-default J_export" href="javascript:investorRepaymentDownload();"  role="button" style="margin-left: 20px;">导出数据</a>
				</div>
   			</div>
</form>
    </script>
    
    <script type="text/javascript">
	    function investorRepaymentDownload(){
	 	    $form = $("#export_investorRepayment").attr("action","rest/invest/investorRepaymentDownload");
			$form[0].submit();
			return false;
	    }
        var $db = $("#J-InvestorRepayment_list").DataTable(
                {
                    ordering:false,
                    searching:false,
                    lengthChange:false,
                    paging:true,
                    select:true,
                    serverSide:true,
                    scrollX:true,
                    dom: '<"J_toolbar ux-toolber">frtip',
                    ajax:{
                        url:"rest/invest/investorRepayment",
                        type:"POST",
                        dataSrc:function (result) {
                            return result.data;
                        },
                        data:function (d) {
                           	d.dateType = $('#dateType').val();
                           	d.type = $('#type').val();
                           	d.mobileOrName = $('#mobileOrName').val();
                            d.columns = [];
                            d.search = {};
                        }
                    },
                    columns:[
                        {data:"investorName"},
                        {data:"investorMobiel"},
                        {data:"cfpName"},
                        {data:"cfpMobile"},
                        {data:"repaymentTotalAmt"},
                        {data:"productCount"},
                        {data:"investAmt"},
                        {data:"completionInvestAmt"},
                        {data:"profit"},
                        {data:"investorId",render: function ( data, type, row ) {
                            var url ='rest/invest/getCustomerInvestDetailPage?userId='+data+'&name='+row.investorName+'&mobile='+row.investorMobiel+'&platfrom=99&platfromText=全部';
                            return '<a href="#" class="btn btn-link ui-redirect " data-title="投资详情" data-url="'+url+'">查看投资详情</a>';
                         }}
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
        );
 		$(".J_toolbar").html($("#template-search").html());
        
        $(".J_search").click(function () {
            $db.draw();
        });

    </script>
	
</div>




