<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>

<script type="text/javascript" src="assets/plugins/echarts/echarts.min.js" ></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>

<div id="main-adv" class="container-fluid">
	<div class="page-breadcrumb breadcrumb">
		<div class="row">
	             <div class="col-xs-3">
			    <select id="platfrom" class="form-control" placeholder="平台" name="platfrom">
					<option value="" selected="selected">全部平台</option>
					<c:if test="${!empty selects}">
	    				<c:forEach items="${selects}"  var="select">
	    					<option value="${select.orgNumber}">${select.orgName}</option>
	    				</c:forEach>
					</c:if>
	        	</select>
			</div>

			<div class="col-xs-3">
						日期:
						<input id="startTime" placeholder="开始日期" name="startTime" class="Wdate" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'})">
						<input id="endTime" placeholder="结束日期" name="endTime" class="Wdate" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'})">
			</div>
			<div class="col-xs-1">
		    	<a class="btn btn-default btn-sm J_search" href="#" role="button" style="margin-left: 20px;">查询</a>
		   </div>

		</div>
	</div>
	<br>

	<div class="row">
		<div class="col-md-6">
			<div id="main" style="width: 600px;height:600px;"></div>
		</div>
		<div class="col-md-6 slave"  style="display: none;">
			<div id="slave" style="width: 600px;height:600px;"></div>
		</div>
	</div>
	<br>


	<div class="page-header">
		<div class="row">
			<div class="col-md-6">
				<div class="row">
					<div class="col-md-5">
						<h4>详细数据</h4>
					</div>
					<div class="col-md-7">
						<h4><small><p class="text-muted" id="main_total">总投资额： 0.00 元</p></small> </h4>
					</div>
				</div>
			</div>

			<div class="col-md-6 slave"  style="display: none;">
				<div class="row">
					<div class="col-md-5">
						<h4>详细数据</h4>
					</div>
					<div class="col-md-7">
						<h4><small><p class="text-muted" id="slave_total">总投资额： 0.00 元</p></small> </h4>
					</div>
				</div>
			</div>
		</div>
	</div>

	 <br>
	<div class="row">
		<div class="col-md-6">
		    <div class="table-responsive">
		        <table class="J_investmentDistributionStatistics table table-bordered">
		            <thead>
		                <tr>
		                    <th>平台名称</th>
				            <th>投资金额(万元)</th>
				            <th>投资笔数</th>
				            <th>投资人数</th>
				            <th>持有金额(万元)</th>
				            <th>回款中金额(万元)</th>
				            <th>赎回金额(万元)</th>
		                </tr>
		            </thead>
		        </table>
		    </div>
		</div>

		<div class="col-md-6 slave" style="display: none;">
		    <div class="table-responsive">
		        <table class="J_platfromInvestStatistics table table-bordered">
		            <thead>
		                <tr>
		                    <th>产品期限</th>
				            <th>投资金额(元)</th>
		                </tr>
		            </thead>
		        </table>
		    </div>
		</div>
	</div>
</div>

    <script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
    	   var tbChart = echarts.init(document.getElementById('main'));
        var $db = $(".J_investmentDistributionStatistics").DataTable(
                {
                    ordering:false,
                    searching:false,
                    lengthChange:true,
                    paging:false,
                    select:false,
                    serverSide:true,
                    scrollX:true,
                    dom: 'frtip',
                    ajax:{
                        url:"rest/invest/investmentDistributionStatistics",
                        type:"POST",
                        dataSrc:function (result) {
                        	$("#main_total").html('总投资额： '+result.total+' 元');
	                         var option = {
	                              title : {
	                            	  text: 'T呗第三方投资额分布(万元)',
	                                  x:'center'
	                              },
	                              tooltip : {
	                                  trigger: 'item',
	                                  formatter: "{a} <br/>{b} : {c} ({d}%)"
	                              },
	                              legend: {
	                            	  x : 'center',
	                                  y : 'bottom',
	                                  data:(function(){
                                         var keys = [];
	                                  	$.each(result.datas, function(index, data) {
	                                  		    keys.push(data.name);
	                                  	});
                                        return keys;
	                                  })()
	                              },
	                              calculable : true,
	                              series : [
	                                  {
	                                      name: '投资来源',
	                                      type: 'pie',
	                                      center : ['50%', '50%'],
	                                      radius : '50%',
	                                      data:result.datas,
	                                      itemStyle: {
	                                          emphasis: {
	                                        	  shadowBlur: 5,
	                                              shadowOffsetX: 0,
	                                              shadowColor: 'rgba(0, 0, 0, 0.5)'
	                                          }
	                                      }
	                                  }
	                              ]
                          	};
	                        tbChart.setOption(option);
	                        return result.datas;
                        },
                        data:function (d) {
                            //d.nameOrMobile = $('#nameOrMobile').val();
                            d.platfrom = $('#platfrom').val();
                            d.startTime = $('#startTime').val();
                            d.endTime = $('#endTime').val();
                            d.columns = [];
                            d.search = {};
                        }
                    },
                    columns:[
                        {data:"name"},
                        {data:"value"},
                        {data:"investCount"},
                        {data:"personCount"},
                        {data:"holdAmount"},
                        {data:"returningAmount"},
                        {data:"returnedAmount"}
                    ],
                    columnDefs:[ 
						{
							targets:1,
							data:"value",
							render:function (data,type,full,meta) {
								return Number(data).toFixed(4);
							}
						
						},
						{
							targets:4,
							data:"holdAmount",
							render:function (data,type,full,meta) {
								return Number(data).toFixed(4);
							}
						
						},
						{
							targets:5,
							data:"returningAmount",
							render:function (data,type,full,meta) {
								return Number(data).toFixed(4);
							}
						
						},
						{
							targets:6,
							data:"returnedAmount",
							render:function (data,type,full,meta) {
								return Number(data).toFixed(4);
							}
						
						},
                                ],
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


         var slaveChart = echarts.init(document.getElementById('slave'));
        var $platfromInvestStatistics = $(".J_platfromInvestStatistics").DataTable(
                {
                    ordering:false,
                    searching:false,
                    lengthChange:true,
                    paging:false,
                    select:false,
                    serverSide:true,
                    scrollX:true,
                    deferLoading:false,
                    dom: 'frtip',
                    ajax:{
                        url:"rest/invest/platfromInvestStatistics",
                        type:"POST",
                        dataSrc:function (result) {
                        	 $("#slave_total").html('总投资额： '+result.total+' 元');
                              if(result.total==0){
                                result.datas = [];
                                 result.datas.push({
                                  name : "无",
                                  value : "无"
                                 });
                              }
	                         var option = {
	                              tooltip : {
	                                  trigger: 'item',
	                                  formatter: "{a} <br/>{b} : {c} ({d}%)"
	                              },
	                              legend: {
	                            	  x : 'center',
	                                  y : 'bottom',
	                                  data:(function(){
                                         var keys = [];
	                                  	$.each(result.datas, function(index, data) {
	                                  		    keys.push(data.name);
	                                  	});
                                        return keys;
	                                  })()
	                              },
	                              calculable : true,
	                              series : [
	                                  {
	                                      name: '投资期限',
	                                      type: 'pie',
	                                      center : ['50%', '50%'],
	                                      radius : '50%',
	                                      data:result.datas,
	                                      itemStyle: {
	                                          emphasis: {
	                                        	  shadowBlur: 5,
	                                              shadowOffsetX: 0,
	                                              shadowColor: 'rgba(0, 0, 0, 0.5)'
	                                          }
	                                      }
	                                  }
	                              ]
                          	};
	                        slaveChart.setOption(option);
	                        return result.datas;
                        },
                        data:function (d) {
                            d.platfrom = $('#platfrom').val();
                            d.startTime = $('#startTime').val();
                            d.endTime = $('#endTime').val();
                            d.columns = [];
                            d.search = {};
                        }
                    },
                    columns:[
                        {data:"name"},
                        {data:"value"}
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

        $(".J_search").click(function () {
            $db.draw();
            var platfrom = $("#platfrom").val();
            if(platfrom==""){
              $(".slave").hide();
            }else{
               $platfromInvestStatistics.draw();
               $(".slave").show();
            }

        });

    </script>


</div>




