<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="assets/plugins/echarts/echarts.min.js"  ></script>
<script type="text/javascript" src="assets/plugins/flot/jquery.flot.js"></script>
<script type="text/javascript" src="assets/plugins/flot/jquery.flot.resize.js"></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-4">
			<div class="row">
				<div class="col-sm-8">
						<h4>总体数据:</h4>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-12">
					<div id="feeDistribution_chart" style="width: 600px;height:400px;" />
		        </div>
		        <script type="text/javascript" >
		         	var key = eval("("+'${dataKey}'+")");
		         	var value = eval("("+'${dataValue}'+")");
		  			// 指定图表的配置项和数据
		        	var option = {
			            title: {
			                text: '佣金区间历史总人数分布图',
			                x:'center'
			            },
			            xAxis: {
			                data: key,
			                axisLabel :{
			                    interval:0
			                }
			            },
			            yAxis: {},
			            series: [{
			                name: '人数',
			                type: 'bar',
			                data: value
			            }]
		         	};
		         	var feeDistribution_chart = echarts.init(document.getElementById('feeDistribution_chart'));
		         	feeDistribution_chart.setOption(option);
		 		</script>
			</div>
			<br>
   			 <br>
			<div class="row">
				<div class="col-sm-12" >
					<div class="table-responsive">
				        <table class="table table-bordered">
				            <thead>
				                <tr>
				                    <th>佣金档（元）</th>
						            <th>人数</th>
				                </tr>
				            </thead>
				            <tbody>
				            <c:forEach var="data" items="${datas}">
				        		 <tr>
				                    <td>${data.key}</td>
				                    <td>${data.value}</td>
				                </tr>
				        	</c:forEach>
				        	</tbody>
				        </table>
				    </div>
		        </div>
   	 		</div>
		</div>

		<div class="col-sm-7 col-sm-offset-1">
			<div class="row">
				<div class="col-sm-8">
						<h4>细分数据:</h4>
				</div>
			</div>
			<br>
   			 <br>
   			 <div class="row">
				<div class="col-sm-3">
						<select id="orgInfo" class="form-control">
							<option value="">选择平台</option>
							 <c:forEach var="orgInfo" items="${orgInfos}">
				        		 <option value="${orgInfo.orgNumber}">${orgInfo.name}</option>
				        	</c:forEach>
						</select>
				</div>
				<div class="col-sm-2">
					<input id="start" placeholder="开始日期" name="start" class="form-control" value="${begintime }"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'end\',{d:-28});}'})">

				</div>
				<div class="col-sm-1">
					______
				</div>
				<div class="col-sm-2">

					<input id="end" placeholder="结束日期" name="end" class="form-control" value="${endtime }"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'start\',{d:28});}'})">
				</div>
				<div class="col-sm-2">
					<a class="btn btn-default J_search" href="#" role="button"><i class="fa fa-search"></i> 查询</a>
				</div>
			</div>
			<br>
			 <div class="row">
				<div class="col-sm-8">
						<h4>时间段内，佣金-占比概率分布图:</h4>
				</div>
			</div>
   			 <br>
			<div class="row">
				<div class="col-sm-12" >
					<div id="feeDistributionRatio_chart" style="width: 600px;height:400px;" />
		        </div>
			</div>
			<br>
   			 <div class="row">
				<div class="col-sm-12">
						<h4>时间段内，佣金区间人数分布图 :</h4>
				</div>
			</div>
			 <div class="row">
				<div class="col-sm-12">
						<h4><font color="red">(由于图表插件数据量多时显示有问题，当显示不完整时请将鼠标悬停至具体柱状图上查看具体明细数据)</font></h4>
				</div>
			</div>

			<br>
			<div class="row">
				<div class="col-sm-12" >
					<div id="feeDistributionDatail_chart" style="width: 800px;height:600px;" />
		        </div>
			</div>
			<br>

 			<div class="row">
				<div class="col-sm-8">
						<h4>时间段内，佣金区间人数分布明细：</h4>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-sm-12" >
					<div id="feeDistributionDatail_div"  />
		        </div>
			</div>
			<br>


			<div class="row">
				<div class="col-sm-6">
						<h4>时间段内，获得佣金最多的理财师Top100：</h4>
				</div>
				<div class="col-sm-4">
						<a class="btn btn-default J_topListDownload" href="rest/feeDistribution/topListDownload" role="button"><i class="fa fa-download"></i> 导出</a>
				</div>
			</div>



   			<br>
			<div class="row">
				<div class="col-sm-12" >
					<div class="table-responsive" id="topDiv" />
		        </div>
			</div>
		</div>
    </div>



</div>

<script type="text/javascript" >
 $(document).ready(function() {
	 var feeDistributionDatail_chart = echarts.init(document.getElementById('feeDistributionDatail_chart'));
	 var feeDistributionRatio_chart = echarts.init(document.getElementById('feeDistributionRatio_chart'));
        start();
        $("a.J_search").click(function(event) {
              start();
        });

          $("a.J_topListDownload").click(function(event) {
            var data = getParameters();
            var href =  $(this).attr("href");
            $(this).attr("href" ,href + '?'+milo.serialize(data));
        });


        $("#feeDistributionDatail_div").on("change","#type",function(event){
        	/*var data = {};
            data.start = $("#start").val();
            if(!data.start){
                layer.msg("开始日期不能为空！",{time: 2000,icon: 0});
                return false;
            }
            data.end = $("#end").val();
             if(!data.end){
                layer.msg("结束日期不能为空！",{time: 2000,icon: 0});
                return false;
            }
            data.orgInfo = $("#orgInfo").val();
        	*/

            var data = getParameters();
            data.type  = $(this).val();
        	searchFeeDistributionDatailPage(data);
        	return false;
        });

        function start(){
            var data = getParameters();

            searchFeeDistributionRatio(data);

             searchFeeDistributionDatail(data);
             searchFeeDistributionDatailPage(data);
             searchTopList(data);
        }

        function getParameters(){
            var data = {};
             data.start = $("#start").val();
              if(!data.start){
                layer.msg("开始日期不能为空！",{time: 2000,icon: 0});
                return false;
            }
             data.end = $("#end").val();
              if(!data.end){
                layer.msg("结束日期不能为空！",{time: 2000,icon: 0});
                return false;
            }
             data.orgInfo = $("#orgInfo").val();
             data.type =  $("#feeDistributionDatail_div").find("#type").val() | 0;
             return data;
        }
        function searchTopList(data){
           $.post('rest/feeDistribution/topList',data, function(data, textStatus, xhr) {
             $("#topDiv").html(data);
             });
        }
        function searchFeeDistributionDatailPage(data){
        	//data.type = $("#type").val();
            $.post('rest/feeDistribution/feeDataDetailPage',data, function(data, textStatus, xhr) {
               $("#feeDistributionDatail_div").html(data);
              });
         }

         function  searchFeeDistributionRatio(data){
             $.post('rest/feeDistribution/feeDistributionRatio',data, function(data, textStatus, xhr) {

                     var option = {
                                tooltip : {
                                    trigger: 'axis',
                                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                                    }
                                },
                                tooltip : {
	                                  trigger: 'item',
	                                  formatter: "{a} <br/> {b} : {c}%"
	                              },
                                xAxis: {
                                	name : "佣金(元)",
                                	nameLocation: 'middle',
                                	nameTextStyle: {
                                		color: 'red',
                                        fontSize: 14
                                    },
                                    nameGap: 25,
                                    type : 'category',
                                    data: data.dataKey ,
                                    axisLabel: {
                                        formatter: '{value}元'
                                    }

                                },
                                yAxis: {
                                	name : "占比",
                                	nameTextStyle: {
                                		color: 'red',
                                        fontSize: 14
                                    },
                                    axisLabel: {
                                        formatter: '{value}%'
                                    }
                                },
                                series: [{
                                     name:"比例",
                                     type:'line',
                                     data:data.dataValue
                                }]


                            };
                   feeDistributionRatio_chart.setOption(option);
              });
         }

        function searchFeeDistributionDatail(data){
           $.post('rest/feeDistribution/feeDataDetail',data, function(data, textStatus, xhr) {
        	   console.info(data);
                var datavalues = new Array();
                $.each(data.datas, function(index, val) {
                      datavalues.push({
                          name: index,
                          type: 'bar',
                          stack:'count',
                         /*  label: {
				                normal: {
				                    show: true,
				                    position: 'insideTop'
				                }
				            }, */
                           data: val
                      });
                });
                  var option = {
                                tooltip : {
                                    trigger: 'axis',
                                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                                    }
                                },
                                 legend: {
                                    data: data.dataType
                                },
                          grid: {
                              left: '3%',
                              right: '4%',
                              bottom: '3%',
                              containLabel: true
                          },

                                xAxis: {
                                	name : "时间",
                                	nameLocation: 'middle',
                                	nameTextStyle: {
                                		color: 'red',
                                        fontSize: 14
                                    },
                                    nameGap: 25,
                                    type : 'category',
                                    data: data.dataKey/* ,
                                    axisLabel :{
                                        interval:0,
                                    } */
                                },
                                yAxis: {
                                	name : "人数",
                                	nameTextStyle: {
                                		color: 'red',
                                        fontSize: 14
                                    },
                                    axisLabel: {
                                        formatter: '{value}人'
                                    }

                                },
                                series: datavalues
                            };
                          feeDistributionDatail_chart.setOption(option);
             });
        }

 });
</script>
