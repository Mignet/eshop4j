<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <table id="J-invest_list" class="table table-bordered">
            <thead>
                <tr>
                    <th>姓名</th>
		            <th>手机号</th>
		            <th>最后投资日期</th>
		            <th>历史投资总额(万元)</th>
		            <th>在投金额(万元)</th>
		            <th>投资笔数</th>
		            <th>收益总额(元)</th>
		            <th>销售费用(元)</th>
                    <!-- <th> 操作</th> -->

                </tr>
            </thead>
        </table>

    </div>

         <!-- 模态框（Modal）-->
	<div class="modal fade" id="invest_list_modal" tabindex="-1" role="dialog" aria-hidden="true" style="top: 50px">
	<div class="modal-dialog" style="width:900px;">
	                        <div class="modal-content">
	                            <div class="modal-header">

	                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	                                  <span aria-hidden="true">&times;</span></button>
	                                </button>
	                                <h4 class="modal-title"></h4>
	                            </div>
	                             <div class="modal-body">
	                              </div>
	                             <div class="modal-footer">
							        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							      </div>
	                        </div>
	                 	</div>
	</div>
    <script type="text/javascript">

        var $db = $("#J-invest_list").DataTable(
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
                        url:"rest/invest/queryCustomerInvestStatistics",
                        type:"POST",
                        dataSrc:function (result) {
                            return result.data;
                        },
                        data:function (d) {
                            d.nameOrMobile = $('#nameOrMobile').val();
                            d.platfrom = $('#platfrom').val();
                            d.columns = [];
                            d.search = {};
                        }
                    },
                    columns:[
                        {data:"name",render: function ( data, type, row ) {
                        	var platfrom = $('#platfrom').val();
                        	var platfromText = $('#platfrom').find("option:selected").text();
                            var url ='rest/invest/getCustomerInvestDetailPage?userId='+row.userId+'&name='+row.name+'&mobile='+row.mobile+'&platfrom='+platfrom+'&platfromText='+platfromText;
                            return '<a href="javascript:;" title="点击查看投资详情"  class="btn btn-link J_customerInvestDetail"  data-url="'+url+'">'+row.name+'</a>';
                         }},
                        {data:"mobile"},
                        {data:"rectInvestTime"},
                        {data:"totalAmt"},
                        {data:"investamt"},
                        {data:"investCount"},
                        {data:"profitamt"},
                        {data:"saleamt"}/* ,
                        {data:"userId",render: function ( data, type, row ) {
                        	var platfrom = $('#platfrom').val();
                        	var platfromText = $('#platfrom').find("option:selected").text();
                            var url ='rest/invest/getCustomerInvestDetailPage?userId='+row.userId+'&name='+row.name+'&mobile='+row.mobile+'&platfrom='+platfrom+'&platfromText='+platfromText;
                            return '<a href="#" class="btn btn-link ui-redirect " data-title="投资详情" data-url="'+url+'">查看投资详情</a>';
                         }} */
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

        var $modal = $('#invest_list_modal');
        $("#J-invest_list").on("click",".J_customerInvestDetail",function(evnet){
    		$.ajax({
    			url : $(event.target).attr("data-url"),
    			type : 'GET',
    			success : function(data) {
    				$modal.find(".modal-footer").hide();
    				$modal.find(".modal-title").html("投资详情");
    				$modal.find(".modal-body").html(data);
    				$modal.modal('show');
    			}
    		});
        });

    </script>

    <script type="text/eshop4j-template" id="template-search">
            <div class="row">
                <div class="col-xs-8">
					<input  class="easyui-textbox" name="nameOrMobile" id="nameOrMobile" type="text" placeholder="输入手机号或姓名"/>
					机构类型:
					<select id="platfrom" name="platfrom">
						<option value="99" selected="selected">全部</option>
						<c:if test="${!empty cimOrginfoList}">
		    				<c:forEach items="${cimOrginfoList}"  var="cimOrginfoL">
		    					<option value="${cimOrginfoL.orgNumber}">${cimOrginfoL.name}</option>
		    				</c:forEach>
						</c:if>
		        	</select>
					<a class="btn btn-default btn-sm J_search" href="#" role="button" style="margin-left: 20px;">查询</a>
				</div>
				 <div class="col-xs-2 col-xs-offset-2">
					<a class="btn btn-link ui-redirect" href="#" data-url="rest/invest/investmentDistributionStatisticsPage" data-title="投资分布" role="button" style="margin-left: 20px;">投资分布</a>
				</div>
   			</div>
    </script>
</div>




