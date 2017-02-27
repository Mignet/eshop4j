<%--
  Created by IntelliJ IDEA.
  User: Mignet
  Date: 2016/5/30
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>




<div id="main-customerx" class="container-fluid">
    <div class="table-responsive">
    <div class="ux-toolber">
    <div class="row">
        <div class="col-lg-4">
            <c:if test="${!empty cfp}"><p id="cfpinfo">理财师：${cfp.userName} ${cfp.mobile}</c:if> </p>
        </div>
    </div>

    <div class="row">

        <div class="col-lg-4">
            <div class="input-group">
                <input type="text" class="form-control" name="search_mobile" id="search_mobile" placeholder="输入姓名或手机号...">
			  <span class="input-group-btn">
				<button class="btn btn-default J_search_cfp" type="button">查询</button>
			  </span>
            </div><!-- /input-group -->
        </div>
    </div>
    </div>
    <table id="custome-list-table" class="table table-bordered">
        <thead>
        <tr>
            <td>序号</td>
            <td>名称</td>
            <td>电话</td>
            <td>证件号</td>
            <td>来源</td>
            <td>绑定时间</td>
            <td>总投资额（元）</td>
            <td>投资笔数</td>
            <td>在投总额（元）</td>
            <td>在投笔数</td>
            <td>贡献佣金（元）</td>
        </tr>
        </thead>
    </table>
        </div>

    <script type="text/javascript">
        var $dataTable = $("#custome-list-table").DataTable({
            ordering:false,
            searching:false,
            lengthChange:false,
            paging:true,
            select:true,
            serverSide:true,
            ajax:{
                url:'rest/lcsList/customelist_json',
                type:"POST",
                dataSrc:function (result) {
                    return result.data;
                },
                data:function (d) {
                    var mobile = '${cfp.mobile}';
                    var search = $.trim($('input[name=search_mobile]').val());
                    d.searchtext =search;
                    d.mobile = mobile;
                    d.columns = [];
                    d.search ={};
                }
            },
            columns:[
                {data:"id"},
                {data:"customerName"},
                {data:"customerMobile"},
                {data:"idCard"},
                {data:"orgName"},
                {data:"createTime"},
                {data:"totalAmount"},
                {data:"totalNums"},
                {data:"curAmount"},
                {data:"curNums"},
                {data:"feeAmount"}
            ],
            columnDefs:[
                {
                    targets:0,
                    data:"id",
                    render:function (data,type,full,meta) {
                        var start = (meta.settings.oAjaxData.start / meta.settings.oAjaxData.length) +1;
                        return (meta.row+1)+(start-1)*10;
                    }
                },
                {
                    targets:1,
                    data:"customerName",
                    render:function (data,type,full,meta) {
                        return data?data:"--";
                    }
                },
                {
                    targets:3,
                    data:"idCard",
                    render:function (data,type,full,meta) {
                        return data?data.substring(0,data.length-12)+'********'+data.substring(data.length-4,data.length):"--";
                    }
                },
                {
                    targets:10,
                    data:"feeAmount",
                    render:function (data,type,full,meta) {
                        return Number(data).toFixed(2);
                    }
                }
            ],
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
        });


        var updateCFPInfo = function () {
            var url = "rest/cfprelevant/recommend_profit_json";
            var $nameOrmobile = $('input[name=search_mobile]').val();
            $.post(url,{mobile:$nameOrmobile},function (result) {
                if(result){
                    $("#cfpinfo").html('理财师：'+result.name+'&nbsp;&nbsp;'+result.mobile+' <input type="hidden" name="org_mobile" value="'+result.mobile+'"/>')
                }

            },'json');
        }


        $("#main-customerx .J_search_cfp").click(function () {
            $dataTable.draw();
            updateCFPInfo();
        });
    </script>
</div>
