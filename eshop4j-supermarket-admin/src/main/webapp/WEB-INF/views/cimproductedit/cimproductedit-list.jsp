<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>
<div id="main-productEdit" class="container-fluid">
    <div class="table-responsive">
        <table id="J-productEditList" class="table table-bordered" data-xtoolbars="#template-search" data-cols="false"  data-order="true" data-url="rest/cim/cimproductedit/list_ajax" data-order="true" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" data-edit="id" data-order="true">ID</th>
                    <th data-name="orgName" data-order="true">机构名称</th>
                    <th data-name="productName" data-order="true">产品名称</th>
                    <th data-name="id" data-format="id:productEdit_opts">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">       
        var productEdit_opts  = function (data,type,full,meta) {
              return '<a class="btn btn-sm btn-default btn-icon ui-redirect" href="javascript:;" data-title="资讯编辑" data-url="/rest/cim/cimproductedit/tosave?id='+data+'" ><i class="fa fa-edit"></i>编辑</a> &nbsp;&nbsp;<a class="btn btn-sm btn-danger btn-icon J_productEdit_delete" data-id="'+data+'" href="javascript:;"><i class="fa fa-trash-o"></i>删除</a>';
        }

       var $db= $("#J-productEditList").linkweeTable();
        $("#main-productEdit").delegate("a.J_productEdit_delete","click",function () {
            var iid = $(this).attr("data-id");
            bootbox.confirm("确定要执行删除操作吗？",function (result) {
                if(result){
                    $.get("rest/cim/cimproductedit/del",{id:iid},function (result) {
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
                    <select id="productEdit_org_number" name="orgNumber" class="form-control" style="width: 150px;display: inline-block;" >
                        <option value="">请选择机构名称</option>
                        <c:forEach var="item" items="${orgList }" varStatus="dn">
                            <option value="${item.orgNumber }">${item.name }</option>
                        </c:forEach>
                    </select>                   
                </div>


                <div class="col-sm-3">
                    <div class="input-group">
                        <input name="productName" class="form-control" placeholder="请输入产品名称关键字" autocomplete="off">
                        <span class="input-group-btn">
                             <a class="btn btn-default J_search" href="#" role="button">查询</a>                          
                        </span>
                    </div>
                </div>

				<div class="col-sm-3">   
					<span class="input-group-btn" style="float:right"> 
						<a class="btn btn-default btn-icon ui-redirect" href="javascript:;" data-title="新增产品详情编辑" data-url="/rest/cim/cimproductedit/tosave" role="button"><i class="fa fa-plus"></i>新增</a>             
					</span>            
                </div>

            </div>

        </form>
    </script>
</div>
