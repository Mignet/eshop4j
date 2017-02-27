<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- DataTables -->
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>
<div id="main-dynamicnews" class="container-fluid">
    <div class="table-responsive">
		<table id="J-dynamicnewslist" class="table table-bordered" data-xtoolbars="#template-search"  data-url="rest/sm/smdynamicnews/list_ajax" data-order="false" data-paging="true" data-size="10">
		        <thead>
		            <tr>
		            <th data-name="id" data-edit="id">ID</th>
		            <th data-name="appType" data-order="true">应用端口</th>
		            <th data-name="typeCode" data-format="typeCode:dynamicnews_typeCode">类型</th>
		            <th data-name="title">标题</th>		           
		            <th data-name="status" data-format="status:dynamicnews_status">状态</th>
		            <th data-name="crtTime">创建时间</th>
		            <th data-name="showIndex">显示排序</th>
		            <th data-name="validBegin">生效时间</th>
		            <!-- <th data-name="validEnd">结束时间</th> -->
		            <th data-name="creator">创建者</th>
		            <th data-name="id" data-format="id:dynamicnews_opts">操作</th>
		            </tr>
		        </thead>
		 
		    </table>
		</div>
    <script type="text/javascript">
        var dynamicnews_opts  = function (data,type,full,meta) {
              return '<a class="btn btn-sm btn-default btn-icon ui-redirect" href="javascript:;" data-title="动态编辑" data-url="/rest/sm/smdynamicnews/tosave?id='+data+'" ><i class="fa fa-edit"></i>编辑</a> &nbsp;&nbsp;<a class="btn btn-sm btn-danger btn-icon J_dynamicnews_delete" data-id="'+data+'" href="javascript:;"><i class="fa fa-trash-o"></i>删除</a>';
        }
        
        var dynamicnews_typeCode  = function (data,type,full,meta) {
        	if(data == "1"){
        		return "媒体报道";
        	}else if(data == "2"){
        		return "T呗动态";
        	}
            return "T呗动态";
        }
        
        var dynamicnews_status  = function (data,type,full,meta) {
        	if(data == "0"){
        		return "启用";
        	}else if(data == "1"){
        		return "删除";
        	}
            return "启用";
        }

       var $db= $("#J-dynamicnewslist").linkweeTable();
        $("#main-dynamicnews").delegate("a.J_dynamicnews_delete","click",function () {
            var iid = $(this).attr("data-id");
            bootbox.confirm("确定要执行删除操作吗？",function (result) {
                if(result){
                    $.get("rest/sm/smdynamicnews/del",{id:iid},function (result) {
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
					<select id="news_type_code" name="typeCode" class="form-control" style="width: 150px;display: inline-block;" >
                        <option value="">请选择动态类别</option>
                        <c:forEach var="item" items="${dynamicNewsTypeList }" varStatus="dn">
                            <option value="${item.key }">${item.value }</option>
                        </c:forEach>
                    </select>                 
                    <select id="news_app_type" name="appType" class="form-control" style="width: 110px; display: inline-block;" >
						<option value="2">T呗</option>                        
						<option value="1">猎财大师</option>                       
                    </select>
                </div>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input name="title" class="form-control"   placeholder="请输入标题关键字" autocomplete="off">
                        <span class="input-group-btn">
                             <a class="btn btn-default J_search" href="#" role="button">查询</a>
                            <a class="btn btn-default btn-icon ui-redirect" href="javascript:;" data-title="新增新闻动态" data-url="/rest/sm/smdynamicnews/tosave" role="button"><i class="fa fa-plus"></i>新增</a>
                        </span>
                    </div>
                </div>
            </div>
        </form>
    </script>
</div>

