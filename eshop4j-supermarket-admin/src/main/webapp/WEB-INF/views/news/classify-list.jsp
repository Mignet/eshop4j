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
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>
<div id="main-classify" class="container-fluid">
    <div class="table-responsive">
        <table id="J-classifylist" class="table table-bordered" data-xtoolbars="#template-search"  data-url="rest/news/classify/list_ajax" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" data-edit="id">ID</th>
                    <th data-name="showIndex" >排序</th>
                    <th data-name="name">分类名称</th>
                    <th data-name="id" data-format="id:news_opts">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">
     
        var news_opts  = function (data,type,full,meta) {
              return '<a class="btn btn-sm btn-default btn-icon ui-redirect" href="javascript:;" data-title="资讯编辑" data-url="/rest/news/classify/tosave?id='+data+'" ><i class="fa fa-edit"></i>编辑</a> &nbsp;&nbsp;<a class="btn btn-sm btn-danger btn-icon J_classify_delete" data-id="'+data+'" href="javascript:;"><i class="fa fa-trash-o"></i>删除</a>';
        }

       var $db= $("#J-classifylist").linkweeTable();
        $("#main-classify").delegate("a.J_classify_delete","click",function () {
            var iid = $(this).attr("data-id");
            bootbox.confirm("确定要执行删除操作吗？",function (result) {
                if(result){
                    $.get("rest/news/classify/del",{id:iid},function (result) {
                        if(result.isFlag){
                            $db.draw();
                        }
                    });
                }
            });
        });
    </script>
    <script type="text/linkwee-template" id="template-search">
        <form>
            <div class="row">
                <div class="col-sm-3">
                    <select id="classify_app_type" name="appType" class="form-control" style="width: 120px; display: inline-block;" >
                        <option value="1">猎财大师</option>
                        <option value="2">T呗</option>
                    </select>
                </div>
              
				<div class="col-sm-3">
                    <div class="input-group">
                        <span class="input-group-btn">
                             <a class="btn btn-default J_search" href="#" role="button">查询</a>
                            <a class="btn btn-default btn-icon ui-redirect" href="javascript:;" data-title="新建分类" data-url="/rest/news/classify/tosave" role="button"><i class="fa fa-plus"></i>新建分类</a>
                        </span>
                    </div>
                </div>
            </div>
        </form>
    </script>
</div>
