<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>

<div id="main-adv" class="container-fluid">
    <div class="table-responsive">
        <table id="J-org-sale_fee_list" class="table table-bordered">
            <thead>
                <tr>
                	<th>平台名称</th>
                    <th>日期</th>
		            <th>销售额（万元）</th>
		            <th>年化销售额（万元）</th>
		            <th>投资人数</th>
		            <th>人均投资额（万元）</th>
		            <th>新增投资人</th>
		            <th>新增投资人投资额（万元）</th>
		            <th>销售费用（元）</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">
        var $db = $("#J-org-sale_fee_list").DataTable(
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
                        url:"rest/orgsalefee/list_ajax",
                        type:"POST",
                        dataSrc:function (result) {
                            return result.data;
                        },
                        data:function (d) {

                            var startDate =$('#startDate').val();
                            var endDate =$('#endDate').val();
                            var orgNumber =$('#orgNumber').val();
                            d.startDate = startDate == undefined?'':startDate;
                            d.endDate = endDate == undefined?'':endDate;
                            d.orgNumber = orgNumber;
                            d.columns = [];
                            d.search = {};
                        }
                    },
                    columns:[
						{data:"orgName"},
                        {data:"statDate"},
                        {data:"daySaleAmount",width:"120px;"},
                        {data:"daySaleForYearAmount"},
                        {data:"investPersonAmount"},
                        {data:"avgInvest",width:"120px;"},
                        {data:"newInvestor",width:"120px;"},
                        {data:"newInvestAmount",width:"120px;"},
                        {data:"totalFeeAmount",width:"120px;", "render": function ( data, type, row ) {
                            return '￥'+data ;
                        } }
                       
                    ],
                    columnDefs:[  ],
                    language:{
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

        $("#main-adv").delegate("a.J_adv_delete","click",function () {
            var iid = $(this).attr("data-id");
            bootbox.confirm("确定要执行删除操作吗？",function (result) {
                if(result){
                    $.get("rest/adv/del",{id:iid},function (result) {
                        if(result.isFlag){
                            $db.draw();
                        }
                    });
                }
            });
        });

        $(".J_search").click(function () {
            $db.draw();
            //显示投资总额
            showInvestAmount();         
            
        });
        
    	//显示投资总额
		function showInvestAmount(){
    		 var startDate =$('#startDate').val();
             var endDate =$('#endDate').val();
             var orgNumber =$('#orgNumber').val();
            
    		$.ajax({
    				url :"rest/orgsalefee/invest_amount",
    				type : 'post',
    				data:{'startDate':startDate,'endDate':endDate,'orgNumber':orgNumber},					
    				dataType : "json",
    				success : function(data) {
    					$("#investAmount").html("<font color='red'>投资总额："+data+"</font>");
    				},
    		 		error: function (xhr, textStatus, errorThrown) {
    		            //错误信息处理
    		 			console.info(errorThrown);
    		        }
    			});

    	};
    	
    	$(".J_export").click(function(){
			$form = $("#orgFeeSearchForm").attr("action","rest/orgsalefee/export");
			$form[0].submit();
		});

    </script>
    <script type="text/eshop4j-template" id="template-search">
		<form id="orgFeeSearchForm">
		机构类型:
		<select id="orgNumber" name="orgNumber">
			<option value="99" selected="selected">全部</option>
			<c:if test="${!empty cimOrginfoList}">
   				<c:forEach items="${cimOrginfoList}"  var="cimOrginfoL">
   					<option value="${cimOrginfoL.orgNumber}">${cimOrginfoL.name}</option>
   				</c:forEach>
			</c:if>
       	</select>

   	日期<input id="startDate" name="startDate" class="form-control Wdate"
				onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'})" 
				style="width: 150px;display: inline-block;" /> <input id="endDate" name="endDate"
				class="form-control Wdate"
				onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'})"
				style="width: 150px;display: inline-block;" />
    
        <a class="btn btn-default btn-sm J_search" href="#" role="button">查询</a>
		<div id = "investAmount" style="width: 150px;display: inline-block;text-align: center;"></div>
		<div id = "exportBtnDiv" style="width: 300px;display: inline-block;float: right;">
		<a class="btn btn-default btn-sm J_export" href="#" role="button"  style="float: right;">导出数据</a>
		</div>
	</form>
    </script>

</div>




