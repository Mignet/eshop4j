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
        <table id="J-newslist" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="rest/acc/acofflinerewarddraft/getAcOfflineRewardDraft" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" date-order="true" data-edit="id" data-format="id:cumsId">序号</th>
                    <th data-name="userName" >姓名</th>
                    <th data-name="mobile" >电话</th>
                    <th data-name="transType" data-format="transType:cumsTransType">类型</th>
                    <th data-name="transAmount" >金额(元)</th>
                    <th data-name="remark">备注</th>
                    <th data-name="rewardTime" >奖励时间</th>
                    <th data-name="grantTime" >发放时间</th>
                    <th data-name="createTime">导入时间</th>
                    <th data-name="createPerson" >操作人</th>
                    <th data-name="batch">批次</th>
                    <th data-name="status" data-format="status:cumsStatus">状态</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">

        var cumsId = function (data,type,full,meta) {
        	return meta.row+1;
        }
        
        var cumsTransType = function (data,type,full,meta) {
        	if(data == 3) {
        		return "活动奖励-投呗";
        	} else if(data == 14) {
        		return "活动奖励-理财师";
        	}else if(data == 12) {
        		return "佣金";
        	}else if(data == 4) {
        		return "红包";
        	}else if(data == 15) {
        		return "leader奖励";
        	}else {
        		return "其他";
        	}
        }
        
        var cumsStatus = function (data,type,full,meta) {
        	if(data == 0) {
        		return "未发放";
        	} else if(data == 1) {
        		return "已发放";
        	}
        }

       var $db= $("#J-newslist").linkweeTable();
       
    </script>
    <script type="text/linkwee-template" id="template-search">
        <form>
		<div class="input-group">
        	<input name="name"  class="form-control" style="width:200px"  placeholder="输入姓名或手机号...">
&nbsp;&nbsp;&nbsp;&nbsp;
未发放批次：<select  name="batch" id="batch">
				 	<option value="">请选择</option>
				  	<c:forEach  items="${batchList}" var="item">
						<option value="${item}">${item}</option>
				 	</c:forEach> 
				 </select>
&nbsp;&nbsp;&nbsp;&nbsp;
状态：<select  name="status" id="status">
				 	<option value="">请选择</option>
					<option value="0">未发放</option>
					<option value="1">已发放</option>
				 </select>
&nbsp;&nbsp;&nbsp;&nbsp;
        	<a class="btn btn-default J_search" href="#" role="button">查询</a>
<span class="input-group-btn">
						<a class="btn btn-default btn-icon ui-redirect" href="javacript:void(0);" data-title="录入奖励数据" data-url="/rest/acc/acofflinerewarddraft/importPage" role="button"><i class="fa fa-plus"></i>录入奖励数据</a>
					 </span>
<span class="input-group-btn">
						<a class="btn btn-default btn-icon ui-redirect" href="javacript:void(0);" data-title="奖励发放" data-url="/rest/acc/acofflinerewarddraft/grantRewardPage" role="button"><i class="fa fa-plus"></i>奖励发放</a>
					 </span>
		</div>
        </form>
 
    </script>
</div>
    
    
    


