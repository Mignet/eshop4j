<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- <link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  /> -->
<link rel="stylesheet" type="text/css" href="app/css/eshop4j.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.eshop4j.js"></script>
<div id="main-news" class="container-fluid">
    <div class="table-responsive">
        <table id="J-activityList" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="rest/cms/activitylist/list_ajax" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" date-order="true" data-edit="id" data-format="id:cumsId">序号</th>
                    <th data-name="activityPlatform">活动平台</th>
		            <th data-name="activityName">活动名称</th>
		            <th data-name="appType" data-format="appType:cumsType">显示端口</th>
		            <th data-name="startDate">上线时间</th>
		            <th data-name="endDate">结束时间</th>
		            <th data-name="activityStatus" data-format="activityStatus:cumsStatus">状态</th>
		            <th data-name="id" data-format="id:cumsDetail">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">

        var cumsStatus = function (data,type,full,meta) {
        	if(data==999) {
        		return "所有";
        	} else if(data == 0) {
        		return "进行中";
        	} else if(data == -1) {
        		return "未开始";
        	} else if(data == 1) {
        		return "已下线";
        	} 
        }
        
        var cumsType = function (data,type,full,meta) {
        	if(data==0) {
        		return "公共";
        	} else if(data == 1) {
        		return "猎财大师";
        	} else if(data == 2) {
        		return "T呗";
        	} 
        }
        
        var cumsId = function (data,type,full,meta) {
        	return meta.row+1;
        }
        
        var cumsDetail = function (data,type,full,meta) {
        	return '<a class="btn btn-sm btn-default btn-icon ui-redirect" href="javascript:;" data-title="活动编辑" data-url="/rest/cms/activitylist/toEdit?id='+data+'" ><i class="fa fa-edit"></i>编辑</a>';
        }

       var $db= $("#J-activityList").eshop4jTable();
    </script>
    <script type="text/eshop4j-template" id="template-search">
		<form>
            <div class="row">
                <div class="col-sm-6">
					活动平台
					<select id="activity_platform" name="activityPlatform" class="form-control" style="width: 150px;display: inline-block;" >
                        <option value="">所有</option>
						<option value="猎财大师">猎财大师</option>
						<option value="T呗">T呗</option>
                        <c:forEach var="item" items="${activityPlatformList }" varStatus="dn">
                            <option value="${item.name }">${item.name }</option>
                        </c:forEach>
                    </select> 
					显示端口                
                    <select id="activity_app_type" name="appType" class="form-control" style="width: 110px; display: inline-block;" >
						<option value="">所有</option>
						<option value="1">猎财大师</option>
						<option value="2">T呗</option>                                               
                    </select>
					活动状态
					<select id="activity_status" name="activityStatus" class="form-control" style="width: 110px; display: inline-block;" >
						<option value="999">所有</option>
						<option value="0">进行中</option> 
						<option value="-1">未开始</option>
						<option value="1">已下线</option>                                              
                    </select>
					
                </div>
                <div class="col-sm-3">
                    <div class="input-group">
                        <span class="input-group-btn"> 
							<a class="btn btn-default J_search" href="#" role="button">搜索</a>                           
                            <a class="btn btn-default btn-icon ui-redirect" href="javascript:;" data-title="新增活动" data-url="/rest/cms/activitylist/toSave" role="button"><i class="fa fa-plus"></i>新增活动</a>
                        </span>
                    </div>
                </div>
            </div>
        </form>
    </script>
</div>
    
    
    


