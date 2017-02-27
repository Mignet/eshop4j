<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>
<div id="main-news" class="container-fluid">
    <div class="table-responsive">
        <table id="J-newslist" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="rest/investor/getFreindsList?userId=${userId}" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" date-order="true" data-edit="id" data-format="id:cumsId">序号</th>
                    <th data-name="userName"  data-format="status:nameFormat">姓名</th>
                    <th data-name="mobile">电话</th>
                    <th data-name="userName" data-format="status:cums">认证</th>
                    <th data-name="createTime">注册时间</th>
                    <th data-name="investAmount">投资总额(元)</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">

        var cums = function (data,type,full,meta) {
        	if(data!=null && data !='') {
        		return "已认证";
        	} else {
        		return "未认证";
        	} 
        }
        
        var nameFormat = function (data,type,full,meta) {
        	if(data!=null && data !='') {
        		return data;
        	} else {
        		return "--";
        	} 
        }
        
        var cumsId = function (data,type,full,meta) {
        	return meta.row+1;
        }

       var $db= $("#J-newslist").linkweeTable();
    </script>
    <script type="text/linkwee-template" id="template-search">
        <form>
		<div class="input-group">
        	<input name="name"  class="form-control" style="width:200px"  placeholder="输入姓名或手机号...">
        	<a class="btn btn-default J_search" href="#" role="button">查询</a>
		</div>
        </form>
    </script>
</div>
    
    
    


