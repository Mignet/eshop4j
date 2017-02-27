<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" type="text/css" href="app/css/eshop4j.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.eshop4j.js"></script>

<div class="container-fluid">
	<div class="table-responsive">
	 <table id="J-salelist" class="table table-bordered" data-url="rest/lcsList/getLcsList" data-order="false" data-xtoolbars="#template-search" data-defer="true">
		<thead>
			<tr>
				<th data-name="id" data-format="id:serial">序号</th>
				<th data-name="userName" data-format='userName:nameFormat'>姓名</th>
				<th data-name="mobile">电话</th>
				<th data-name="cfpLevel">职级</th>
				<th data-name="parentName" data-format='parentName:nameFormat'>上级理财师</th>
				<th data-name="parentMobile">上级账号</th>
				<th data-name="teamMemberCount" data-format="teamMemberCount:linkFormat">团队人数</th>
				<th data-name="customerCount" data-format="customerCount:linkFormat" >客户人数</th>
				<th data-name="createTime">注册时间</th>
				<th data-name="mobile" data-format="mobile:linkFormat">操作</th>
			</tr>
		</thead>
	</table>
	</div>
	<script type="text/javascript">
        var linkFormat = function (data,type,full,meta) {
            var num = Number(data);
            var title = "";
            if(!full){
                return "";
            }
            var url = "";
            switch (meta.col){

                case 6:
                     num = new Number(data);
                    title = "团队列表";
                    url = 'rest/lcsList/teamlist?mobile='+full.mobile;
                    break;
                case 7:
                     num = new Number(data);
                    title = "客户列表";
                    url = 'rest/lcsList/customelist/'+full.mobile;
                    break;
                case 9:
                	num = "详情";
                    title = "理财师详情";
                    url = 'rest/lcsList/getLcsDetail?mobile='+data;
                    break;
            }
            return data?'<a class="ui-redirect" data-title="'+title+'" data-url="'+url+'">'+num+'</a>':'0';
        }
       var $datatables =  $("#J-salelist").eshop4jTable();
       
       var nameFormat = function (data,type,full,meta) {
       	if(data!=null && data !='') {
       		return data;
       	} else {
       		return "--";
       	} 
       }
    </script>

	<script type="text/eshop4j-template" id="template-search">
<form>
 		<div class="input-group">
			<input name="nameOrmobile"  class="form-control" style="width:200px"  placeholder="输入姓名或手机号...">
			<a class="btn btn-default J_search" href="#" role="button">查询</a>
		</div>
</form>
	</script>
</div>




