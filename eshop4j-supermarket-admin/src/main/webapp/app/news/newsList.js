
using(["datagrid","pagination","common","messager"],function(){
	
	lh.on("click").addHandler({"selector":'a.J_openNewsWin',"handler" : function(e){
		var options = $(this).attr("data-options");
		var obj = eval('(' + options + ')');
		var title = "";
		var href = "rest";
		var opType = obj.type;
		if(opType == "udpate"){
			title = "编辑";
			href +="/news/toupdate?id="+obj.id;
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
							field : 'typeName',
							title : '类别',
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
							field : 'crtTime',
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
		    	request("rest/news/list",params);
		    }
		}); 
		
		var options =  $(p).data("pagination").options;
		var params=jsonFromt($('#newsQueryForm').serializeArray());
		params.pageIndex=options.pageNumber;
		params.pageSize=options.pageSize;
		request("rest/news/list",params); 
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





