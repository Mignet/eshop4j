<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.linkwee.web.enums.AdvPlacementEnum" %>
<%
	AdvPlacementEnum[] advPlacementList = AdvPlacementEnum.values();
	request.setAttribute("advPlacementList", advPlacementList);
%>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>


<div id="main-adv" class="container-fluid">
    <div class="table-responsive">
        <table id="J-advlist" class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>显示位置</th>
                    <th>图片</th>
                    <th>链接地址</th>
                    <th>显示顺序</th>
                    <th>状态</th>
                    <th>应用类别</th>
                    <th>上架时间</th>
                    <th>下架时间</th>
                    <th>操作</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">
        var $db = $("#J-advlist").DataTable(
                {
                    ordering:false,
                    searching:false,
                    lengthChange:false,
                    paging:true,
                    select:true,
                    serverSide:true,
                    scrollX:true,
                    dom: '<"J_toolbar ux-toolber">frtip',
                    ajax:{
                        url:"rest/adv/list_json",
                        type:"POST",
                        dataSrc:function (result) {
                            return result.data;
                        },
                        data:function (d) {
                            var pageIndex =$('#pageIndex').val();
                            var app_type =$('#news_app_type').val();
                            var title = $.trim($('input[name=search_title]').val());
                            d.pageIndex = pageIndex == undefined?'app_opening':pageIndex;
                            d.appType = app_type == undefined?1:app_type;
                            d.columns = [];
                            d.search = {};
                        }
                    },
                    columns:[
                        {data:"id"},
                        {data:"pageIndex",width:"50px;"},
                        {data:"imgUrl",width:"50px;"},
                        {data:"linkUrl",width:"50px;"},
                        {data:"showIndex",width:"50px;"},
                        {data:"processingStatus",width:"50px;"},
                        {data:"appType",width:"50px;"},
                        {data:"validBeginDate",width:"50px;"},
                        {data:"validEndDate",width:"50px;"},
                        {data:"id",width:"120px;"}
                    ],
                    columnDefs:[
						{
						    targets:1,
						    data:"pageIndex",
						    render:function (data,type,full,meta) {
						    	
						    	if(data=="app_opening"){
						    		return "开屏广告";
						    	}
						    	else if(data=="app_home_page"){
						    		return "Banner页";
						    	}
						    	else if(data=="pc_idx_middle"){
						    		return "pc版投呗首页页中";
						    	}
						    	else if(data=="pc_banner"){
						    		return "pc版banner";
						    	}
						    	else if(data=="platform_banner"){
						    		return "平台banner";
						    	}
						    	else if(data=="product_banner"){
						    		return "产品banner";
						    	}
						    	else{
						    		return "";
						    	}
						        
						    }
						},
						{
						    targets:2,
						    data:"imgUrl",
						    render:function (data,type,full,meta) {
						    	if(data){
						    		return '<img src="'+data+'" width="100" height="100" />';
						    	}
						    	return '';
						    	
						        
						    }
						},
						{
						    targets:3,
						    data:"linkUrl",
						    render:function (data,type,full,meta) {
						    	if(data){
						    		return '<a  target="_blank" href="'+data+'" title="'+data+'" >'+data+'</a>';
						    	}
						    	return '';
						        
						    }
						},
						{
                            targets:5,
                            data:"processingStatus",
                            render:function (data,type,full,meta) {
                                return '<font color="red">'+data+'</font>';
                            }
                        },
                        {
                            targets:6,
                            data:"appType",
                            render:function (data,type,full,meta) {
                            	if(data=='0'){
                            		return '公用';
                            	}else if(data =='1'){
                            		return '理财师端';
                            	}else if(data =='2'){
                            		return '投资端';
                            	}
                                
                            }
                        },
                        {
                            targets:9,
                            data:"id",
                            render:function (data,type,full,meta) {                          	
                                return '<a class="btn btn-sm btn-default btn-icon ui-redirect" href="javascript:;" data-title="编辑广告" data-url="/rest/adv/toupdate?id='+data+'" ><i class="fa fa-edit"></i>编辑</a> &nbsp;&nbsp;<a class="btn btn-sm btn-danger btn-icon J_adv_delete" data-id="'+data+'" href="javascript:;"><i class="fa fa-trash-o"></i>删除</a>';
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
                }
        );
        $(".J_toolbar").html($("#template-search").html());

        $("#main-adv").delegate("a.J_adv_delete","click",function () {
            var iid = $(this).attr("data-id");
            bootbox.confirm("确定要执行删除操作吗？",function (result) {
                if(result){
                    $.get("rest/adv/del",{id:iid},function (result) {
                        if(result.isFlag){
                            $db.draw();
                        }
                    });
                }
            });
        });

        $(".J_search").click(function () {
            $db.draw();
        });
        
        var a =  new Array();
    	<c:forEach var="item" items="${advPlacementList }"  varStatus="dn">
    		var json = eval('(' + '{"key":"${item.key }", "value":"${item.value }","appType":"${item.appType}"}' + ')'); 
    	    a.push(json);
	   </c:forEach>
	  
	   
        $(document).ready(function(){
        	 var appType = $("#news_app_type").children('option:selected').val();
		   for (var i = 0; i < a.length; i++){
      		 if(a[i].appType =="0" || a[i].appType == appType){
      			 $("#pageIndex").append("<option value='"+a[i].key+"'>"+a[i].value+"</option>");
      		 }
      		 
      	 }        	
        	
        }) ;
        //绑定事件
        $('#news_app_type').change(function(){      
        	 var appType = $("#news_app_type").children('option:selected').val();
        	$("#pageIndex").empty();
        	 for (var i = 0; i < a.length; i++){
        		 if(a[i].appType =="0" || a[i].appType == appType){
        			 $("#pageIndex").append("<option value='"+a[i].key+"'>"+a[i].value+"</option>");
        		 }
        		 
        	 }
        	
        	});
        

    </script>
    <script type="text/linkwee-template" id="template-search">
 		<select id="news_app_type" name="appType" class="form-control" style="width: 94px; display: inline-block;">
            <option value="1">理财师</option>
            <option value="2">投资端</option>
         </select>

         <select id="pageIndex" name="pageIndex" class="form-control" style="width: 150px; display: inline-block;">
			
         </select>


        <a class="btn btn-default btn-sm J_search" href="#" role="button">查询</a>
        <a class="btn btn-default btn-sm ui-redirect" href="javascript:;" data-title="新增广告" data-url="/rest/adv/tosave" role="button">新增</a>
    </script>
</div>


