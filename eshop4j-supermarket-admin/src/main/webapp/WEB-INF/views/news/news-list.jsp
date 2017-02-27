<%--
  Created by IntelliJ IDEA.
  User: Mignet
  Date: 2016/6/3
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="app/css/eshop4j.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.eshop4j.js"></script>
<div id="main-news" class="container-fluid">
    <div class="table-responsive">
        <table id="J-newslist" class="table table-bordered" data-xtoolbars="#template-search"  data-url="rest/news/list_ajax" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" data-edit="id">ID</th>
                    <th data-name="typeName" >类别</th>
                    <th data-name="title">标题</th>
                    <%--<th data-name="title" data-format="typeName:cums">置顶</th>--%>
                    <th data-name="validBegin">发布时间</th>
                    <!-- <th data-name="validEnd">到期时间</th> -->
                    <th data-name="creator">发布人</th>
                    <th data-name="id" data-format="id:news_opts">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">

        var cums = function (data,type,full,meta) {
            return '否';
        }
        
        var news_opts  = function (data,type,full,meta) {
              return '<a class="btn btn-sm btn-default btn-icon ui-redirect" href="javascript:;" data-title="资讯编辑" data-url="/rest/news/tosave?id='+data+'" ><i class="fa fa-edit"></i>编辑</a> &nbsp;&nbsp;<a class="btn btn-sm btn-danger btn-icon J_news_delete" data-id="'+data+'" href="javascript:;"><i class="fa fa-trash-o"></i>删除</a>';
        }

       var $db= $("#J-newslist").eshop4jTable();
        $("#main-news").delegate("a.J_news_delete","click",function () {
            var iid = $(this).attr("data-id");
            bootbox.confirm("确定要执行删除操作吗？",function (result) {
                if(result){
                    $.get("rest/news/del",{id:iid},function (result) {
                        if(result.isFlag){
                            $db.draw();
                        }
                    });
                }
            });
        });
    </script>
    <script type="text/eshop4j-template" id="template-search">
        <form>
            <div class="row">
                <div class="col-sm-3">
                    <select id="news_type_code" name="typeCode" class="form-control" style="width: 150px;display: inline-block;" >
                        <option value="0">请选择资讯类别</option>
                        <c:forEach var="item" items="${newsTypeList }" varStatus="dn">
                            <option value="${item.id }">${item.name }</option>
                        </c:forEach>
                    </select>
                    <select id="news_app_type" name="appType" class="form-control" style="width: 94px; display: inline-block;" >
                        <option value="1">理财师</option>
                        <option value="2">投资端</option>
                    </select>

                </div>


                <div class="col-sm-3">
                    <div class="input-group">
                        <input name="title" class="form-control"   placeholder="请输入标题关键字" autocomplete="off">
                        <span class="input-group-btn">
                             <a class="btn btn-default J_search" href="#" role="button">查询</a>                          
                        </span>
                    </div>
                </div>

				<div class="col-sm-3">   
					<span class="input-group-btn" style="float:right"> 
						<a class="btn btn-default btn-icon ui-redirect" href="javascript:;" data-title="新增资讯" data-url="/rest/news/tosave" role="button"><i class="fa fa-plus"></i>新增</a>             
                    	<a class="btn btn-default btn-icon ui-redirect" href="javascript:;" data-title="资讯分类" data-url="/rest/news/classify/list" role="button">资讯分类</a>         
					</span>            
                </div>

            </div>




        </form>
    </script>
</div>
