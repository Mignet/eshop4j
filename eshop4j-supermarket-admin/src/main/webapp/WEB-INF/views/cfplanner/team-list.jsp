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




<div id="main-teamlist" class="container-fluid">

    <div class="table-responsive">
    <div class="ux-toolber">
        <div class="row">
            <div class="col-lg-4">
                <c:if test="${!empty cfp}"><p id="cfpinfo">理财师：${cfp.userName} ${cfp.mobile}</c:if> </p>
            </div>
        </div>

        <div class="row">

            <div class="col-lg-3">
                <div class="input-group">
                    <input type="text" class="form-control" name="search_mobile" id="search_mobile" placeholder="输入姓名或手机号码...">
			  <span class="input-group-btn">
				<button class="btn btn-default J_search_cfp" type="button">查询</button>
			  </span>
                </div><!-- /input-group -->
            </div>
        </div>
    </div>

    <table id="team-list-table" class="table table-bordered">
        <thead>
        <tr>
            <th>序号</th>
            <th>名称</th>
            <th>电话</th>
            <th>团队关系</th>
            <th>绑定时间</th>
            <th>销售额（元）</th>
            <th>销售笔数</th>
            <th>创造收益（元）</th>
        </tr>
        </thead>
    </table>
    </div>
    <script type="text/javascript">
        var $dataTable = $("#team-list-table").DataTable({
            ordering:false,
            searching:false,
            lengthChange:false,
            paging:true,
            select:true,
            serverSide:true,
            ajax:{
                url:'rest/lcsList/teamlist_json',
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
                {data:"name"},
                {data:"mobile"},
                {data:"level"},
                {data:"createTime"},
                {data:"totalAmount"},
                {data:"totalNums"},
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
                    data:"name",
                    render:function (data,type,full,meta) {
                        return data?data:"--";
                    }
                },
                {
                    targets:3,
                    data:"level",
                    render:function (data,type,full,meta) {
                        return data=='v1'?'一级':'<a href="javascript:;" style="color:red;">二级</a>';
                    }
                },
                {
                    targets:5,
                    data:"totalAmount",
                    render:function (data,type,full,meta) {
                        return Number(data).toFixed(2);
                    }
                },
                {
                    targets:7,
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


        $("#main-teamlist .J_search_cfp").click(function () {
            $dataTable.draw();
            updateCFPInfo();
        });
    </script>
</div>
