using(["datagrid","pagination","common","messager"],function(){
	
	lh.on("click").addHandler({"selector":'a.J_openNewsWin',"handler" : function(e){
		var options = $(this).attr("data-options");
		var obj = eval('(' + options + ')');
		var title = "";
		var href = "rest";
		var opType = obj.type;
		if(opType == "udpate"){
			title = "编辑";
			href +="/acc/smclassroom/toEdit?id="+obj.id;
			alert(1111);
			var $windowInvestor =$('#newsWin').window({
				title:title,
				width:1000,
				height:900,
			    top:($(window).height() - 600) * 0.5,   
	            left:($(window).width() - 350) * 0.5,
			    modal:true,
			    closed:true,
			    href:href
			});
			$windowInvestor.window("open");
		}else if(opType == "0" || opType == "-1" || opType == "del"){
					$.messager.progress({
						title : '请稍后',
						msg : '玩命加载中...'
					});
					var url ='rest/news/setstatu';
					if(opType== 'del'){
						url = 'rest/news/del';
					}
					$.ajax({
						type : 'post',
						url : url,
						data : 'id=' + obj.id+"&opType="+opType,
						dataType : 'json',
						success : function(result) {
							$.messager.progress('close');
							if (result.isFlag) {
								$.messager.alert('提示', result.msg, 'info');
								$('#newsDg').datagrid({url:"rest/news/list", queryParams:jsonFromt($('#newsQueryForm').serializeArray())});
							} else {
								$.messager.alert('提示', result.msg, 'error');
							}
							
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							$.messager.progress('close');
							$.messager.alert('提示', '服务异常！', 'error');
						}
					});
		}
		
			
			return false;
		}
	});
	
	/**
	 * //否则根据获取新内容并添加新tab
			
	 */
	
	var $datagrid = $('#newsDg').datagrid(
			{	
				pagination: true, 
		        rownumbers: false,
		        checkOnSelect:false,
				pageList:[10,20,40,50,100,500],
				fitColumns:true,		
				striped:true,
				toolbar:"#newsToolbar",
				queryParams:jsonFromt($('#newsQueryForm').serializeArray()),
				columns : [ [
						{
							field : 'id',
							title : 'ID',
							width : 100,
							align : 'center'
						},
						{
							field : 'label',
							title : '标签',
							width : 120,
							align : 'center'
						},
						{
							field : 'title',
							title : '标题',
							width : 150,
							align : 'center'
						},
						{
							field : 'showInx',
							title : '显示排序',
							width : 150,
							align : 'center'
						},
						{
							field : 'createTime',
							title : '添加时间',
							width : 120,
							align : 'center',
							formatter : function(value, row, index) {
								if(value != null){
									var date = new Date(value);  
			                         return formateDate1(date);
								}else{
									return '-';
								}
		                           
							}
						},
						{
							field : 'creator',
							title : '操作人',
							width : 120,
							align : 'center'
						},
						{
							field : 'status',
							title : '状态',
							width : 100,
							align : 'center',
							formatter : function(value, row, index) {
								if(value == '0'){
			                         return '显示';
								}else if(value == '-1'){
									return '不显示';
								}else{
									return '-';
								}
		                           
							}
						},
						{
							field : 'showInx',
							title : '显示顺序',
							width : 80,
							align : 'center'
						},
						{
							field : 'appType',
							title : '应用类别',
							width : 60,
							align : 'center',
							formatter : function(value, row, index) {
								if(value == '1'){
			                         return '理财师端';
								}else if(value == '2'){
									return '投资者端';
								}else{
									return '-';
								}
		                           
							}
						},
						{
							field : 'validBegin',
							title : '上架时间',
							width : 60,
							align : 'center',
							formatter : function(value, row, index) {
								if(value != null){
									var date = new Date(value);  
			                         return formateDate1(date);
								}else{
									return '-';
								}
		                           
							}
						},
						{
							field : 'validEnd',
							title : '下架时间',
							width : 60,
							align : 'center',
							formatter : function(value, row, index) {
								if(value != null){
									var date = new Date(value);  
			                         return formateDate1(date);
								}else{
									return '-';
								}
		                           
							}
						},
						{
							field : '_operate',
							title : '操作',
							width : 100,
							formatter: function(value, row, index) {
								var ret = '<a href="#" class="J_openNewsWin"  data-options="{id:'+row.id+',type:\'udpate\'}" >编辑</a>';
								if(row.status=='0'){
									ret +='&nbsp;&nbsp;<a  class="J_openNewsWin" data-options="{id:'+row.id+',type:\'-1\'}">不显示</a>';
								}else{
									ret +='&nbsp;&nbsp;<a  class="J_openNewsWin" data-options="{id:'+row.id+',type:\'0\'}">显示</a>';
									ret +='&nbsp;&nbsp;<a  class="J_openNewsWin" data-options="{id:'+row.id+',type:\'del\'}">删除</a>';//不显示的可以删除
								}
									
								 return ret;  
							},
							align : 'center'
						} ] ]
			});
	        
			$('#advQueryButton').click(function() {
				var params=jsonFromt($('#newsQueryForm').serializeArray());
				var options =  $(p).data("pagination").options;
				params.pageIndex=options.pageNumber;
				params.pageSize=options.pageSize;
				request("rest/news/list",params); 
			});
			//新增
			$("#newsAddBtn").click(function(){
				/*$form = $("#investorQueryForm").attr("action",$('#path').val()+"/export/investor.htm");
				$form[0].submit();*/
				// $('#bodyLayout').layout('panel','center').width;
				// $('#bodyLayout').layout('panel','center').height;
				var $windowNews =$('#newsWin').window({
					title:'新增',
				    top:($(window).height() - 600) * 0.5,   
		            left:($(window).width() - 350) * 0.5,
				    modal:true,
				    closed:true,
			/*width:$('#bodyLayout').layout('panel','center').width,
				    height:$('#bodyLayout').layout('panel','center').height,*/
				    width:1000,
				    height:900,
				    maximized:false,
				    href:"rest/news/tosave"
				});
				$windowNews.window("open");
			});
			var p = $datagrid.datagrid('getPager');
		    $(p).pagination({  
		    pageSize: 20,//每页显示的记录条数，默认为5  
		    pageList: [20,40,60,100],//可以设置每页记录条数的列表  
		    beforePageText: '第',//页数文本框前显示的汉字  
		    afterPageText: '页    共 {pages} 页',  
		    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		    onSelectPage: function(pageNumber, pageSize){
		    	var params=jsonFromt($('#newsQueryForm').serializeArray());
		    	params.pageIndex=pageNumber;
				params.pageSize=pageSize;
		    	request("/rest/acc/smclassroom/list",params);
		    }
		}); 
		
		var options =  $(p).data("pagination").options;
		var params=jsonFromt($('#newsQueryForm').serializeArray());
		params.pageIndex=options.pageNumber;
		params.pageSize=options.pageSize;
		request("/rest/acc/smclassroom/list",params); 
		function request(url,query,resultType){
			$.ajax({
				  type: 'POST',
				  url: url,
				  data: query,
				  success: function(data){
					   $datagrid.datagrid('loadData', data);
				  },
				  dataType: resultType||"json"
				});
		}
	
	
});


/*var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];

$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
    	ajax: {
            create: {
                type: 'POST',
                url:  'rest/acc/smclassroom/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            edit: {
                type: 'POST',
                url:  'rest/acc/smclassroom/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            remove: {
            	 type: 'POST',
                 url:  'rest/acc/smclassroom/save',
                 data:function(d){return {'rows':JSON.stringify(d)};}
            }
        },
        table: "#dtable",
        idSrc:  'id',
        i18n: {
            "create": {
                "button": "新增",
                "title":  "创建新的实体",
                "submit": "确定"
            },
            "edit": {
                "button": "编辑",
                "title":  "编辑实体",
                "submit": "确定"
            },
            "remove": {
                "button": "删除",
                "title":  "删除",
                "submit": "确定",
                "confirm": {
                    "_": "确定要删除选择的 %d 行数据吗?",
                    "1": "确定要删除选择的 1 行数据吗?"
                }
            },
         
            "error": {
                "system": "发生系统错误 (More information)"
            },
         
            "multi": {
                "title": "多个值",
                "info": "选择的内容中当前输入框包含不同的值. 把他们设置成相同的值, 点击这里, 否则它们仍然保留不同的值.",
                "restore": "取消改动"
            },
         
            "datetime": {
                "previous": '前',
                "next":     '后',
                "months":   [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
                "weekdays": [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
                "amPm":     [ '上午', '下午' ],
                "unknown":  '-'
            }
        },
        fields: [
         {
                label: "自增长ID:",
                name: "id"
                ,type:"hidden"
            }, 
         {
                label: "应用端口(1:猎财大师2:投呗）:",
                name: "appType"
            }, 
         {
                label: "图片:",
                name: "img"
            }, 
         {
                label: "标题:",
                name: "title"
            }, 
         {
                label: "跳转地址:",
                name: "linkUrl"
            }, 
         {
                label: "课堂内容:",
                name: "content"
            }, 
         {
                label: "状态:0发布,1删除2初始化类别:",
                name: "status"
            }, 
         {
                label: "创建者:",
                name: "creator"
            }, 
         {
                label: "创建时间:",
                name: "createTime"
            }, 
         {
                label: "显示排序:",
                name: "showInx"
            }, 
         {
                label: "生效时间:",
                name: "validBegin"
            }, 
         {
                label: "结束时间:",
                name: "validEnd"
            }, 
         {
                label: "修改时间:",
                name: "modifiyTime"
            }, 
         {
                label: "标签:",
                name: "label"
            }, 
         {
                label: "默认为0，0=不置顶,1=置顶:",
                name: "isStick"
            }, 
        ]
    } );
    //前端校验
    editor.on( 'preSubmit', function ( e, o, action ) {
        if ( action !== 'remove' ) {
            var name = editor.field( 'name' );
 
            // Only validate user input values - different values indicate that
            // the end user has not entered a value
            if ( ! name.isMultiValue() ) {
                if ( ! name.val() ) {
                	name.error( 'A activity name must be given' );
                }
                 
                if ( name.val().length <= 2 ) {
                	name.error( 'The activity name length must be more that 2 characters' );
                }
            }
 
            // ... additional validation rules
 
            // If any error was reported, cancel the submission so it can be corrected
            if ( this.inError() ) {
                return false;
            }
        }
    } );
    var shiro_admin = "disabled=true";
    if($('#shiro_admin')){
    	shiro_admin = "";
    }
    var table = $('#dtable').DataTable( {
    	dom: "Bfrtip",
        "processing": true,
        "serverSide": true,
        "language": {
        	select: {
                rows: {
                    _: "已选择 %d 行",
                    1: "已选择 1 行"
                }
            },
        	"sProcessing":   "处理中...",
        	"sLengthMenu":   "显示 _MENU_ 项结果",
        	"sZeroRecords":  "没有匹配结果",
        	"sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        	"sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
        	"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        	"sInfoPostFix":  "",
        	"sSearch":       "搜索:",
        	"sUrl":          "",
        	"sEmptyTable":     "表中数据为空",
        	"sLoadingRecords": "载入中...",
        	"sInfoThousands":  ",",
        	"oPaginate": {
        		"sFirst":    "首页",
        		"sPrevious": "上页",
        		"sNext":     "下页",
        		"sLast":     "末页"
        	},
        	"oAria": {
        		"sSortAscending":  ": 以升序排列此列",
        		"sSortDescending": ": 以降序排列此列"
        	}
        },
        "ajax": {
            "url": "/rest/acc/smclassroom/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，json化
        },
        "columns": [
            { "data": "id","orderable": false },
            { "data": "label","orderable": false },
            { "data": "title","orderable": false },
            { "data": "showInx","orderable": false },
            { "data": "createTime","orderable": false },
            { "data": "creator","orderable": false },
            { "data": "id" ,"orderable": false,"render": function ( data, type, row ) {
            	if(row.status=='1'){
  	            	var bind = '<button class="btn btn-sm btn-default btn-icon ui-redirect" data-title="解绑" onclick="unbund('+row.mobile+')"><i class="fa fa-weixin">解绑</i></button>';
  	            }else{
  	            	var bind = '<a href="#" class="btn btn-sm btn-default btn-icon ui-redirect" data-title="绑卡" data-url="/rest/acc/acaccountbind/toEdit?mobile='+row.mobile+'"><i class="fa fa-jsfiddle"></i>绑卡</a>';
  	            }
                
  	            return bind;
           }}    
//            { "data": "appType","orderable": false },
//            { "data": "img","orderable": false },
//            { "data": "linkUrl","orderable": false },
//            { "data": "content","orderable": false },
//            { "data": "status","orderable": false },
//            { "data": "validBegin","orderable": false },
//            { "data": "validEnd","orderable": false },
//            { "data": "modifiyTime","orderable": false },
//            { "data": "isStick","orderable": false },
			    
        ],
        select: true,
        buttons: [
            { extend: "create", editor: editor },
            { extend: "edit",   editor: editor },
            { extend: "remove", editor: editor }
        ]
    } );
    
} );
*/