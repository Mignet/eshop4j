
using(["datagrid","pagination","common","messager"],function(){
	
	lh.on("click").addHandler({"selector":'a.J_openAdvWin',"handler" : function(e){
		var options = $(this).attr("data-options");
		var obj = eval('(' + options + ')');
		var title = "";
		var href = $('#path').val();
		var opType = obj.type;
		if(opType == "udpate"){
			title = "编辑";
			href +="/adv/toupdate.htm?id="+obj.id;
			var $windowInvestor =$('#advWin').window({
				title:title,
			    width:700,
			    height:600,
			    top:($(window).height() - 600) * 0.5,   
	            left:($(window).width() - 350) * 0.5,
			    modal:true,
			    closed:true,
			    href:href
			});
			$windowInvestor.window("open");
		}else if(opType == "0" || opType == "-1" || opType== 'del'){
					$.messager.progress({
						title : '请稍后',
						msg : '玩命加载中...'
					});
				
					var url =$('#path').val() + '/adv/setstatu.htm';
					if(opType== 'del'){
						url =$('#path').val() + '/adv/del.htm';
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
								$('#advDg').datagrid('reload');
								$('#advDg').datagrid({url:$('#path').val()+"/adv/list.htm", queryParams:jsonFromt($('#advQueryForm').serializeArray())});
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
	
	var $datagrid = $('#advDg').datagrid(
			{	
				pagination: true, 
		        rownumbers: true,
		        checkOnSelect:false,
				pageList:[10,20,40,50,100,500],
				fitColumns:true,		
				striped:true,
				toolbar:"#advToolbar",
				queryParams:jsonFromt($('#advQueryForm').serializeArray()),
				columns : [ [
						{
							field : 'pageIndex',
							title : '图片显示位置',
							width : 150,
							align : 'center'
						},
						{
							field : 'pageIndexDesc',
							title : '位置描述',
							width : 120,
							align : 'center'
						},
						{
							field : 'imgUrl',
							title : '图片',
							width : 200,
							align : 'center',
							formatter : function(value, row, index) {
								if(value != null){
									return '<img src=\"'+value+'\" width=\"80px\" height=\"100px\" />';
								}else{
									return '-';
								}
		                           
							}
						},
						{
							field : 'linkUrl',
							title : '链接地址',
							width : 170,
							align : 'center'
						},
						{
							field : 'showIndex',
							title : '显示顺序',
							width : 150,
							align : 'center'
						},
						{
							field : 'status',
							title : '显示状态',
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
							field : 'appType',
							title : '应用类别',
							width : 60,
							align : 'center',
							formatter : function(value, row, index) {
								if(value == '1'){
			                         return '理财师';
								}else if(value == '2'){
									return '投资者';
								}else{
									return '-';
								}
		                           
							}
						},
						{
							field : 'validBeginDate',
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
							field : 'validEndDate',
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
								var ret = '<a href="#" class="J_openAdvWin"  data-options="{id:'+row.id+',type:\'udpate\'}" >编辑</a>';
								if(row.status=='0'){
									ret +='&nbsp;&nbsp;<a  class="J_openAdvWin" data-options="{id:'+row.id+',type:\'-1\'}">不显示</a>';
								}else{
									ret +='&nbsp;&nbsp;<a  class="J_openAdvWin" data-options="{id:'+row.id+',type:\'0\'}">显示</a>';
									ret +='&nbsp;&nbsp;<a  class="J_openAdvWin" data-options="{id:'+row.id+',type:\'del\'}">删除</a>';
								}
									
								 return ret;  
							},
							align : 'center'
						} ] ]
			});
	        
			$('#advQueryButton').click(function() {
				var params=jsonFromt($('#advQueryForm').serializeArray());
				var options =  $(p).data("pagination").options;
				params.pageIndex=options.pageNumber;
				params.pageSize=options.pageSize;
				request($('#path').val()+"/adv/list.htm",params); 
			});
			//新增
			$("#advAddBtn").click(function(){
				/*$form = $("#investorQueryForm").attr("action",$('#path').val()+"/export/investor.htm");
				$form[0].submit();*/
				var $windowAdv =$('#advWin').window({
					title:'新增',
				    width:700,
				    height:600,
				    top:($(window).height() - 600) * 0.5,   
		            left:($(window).width() - 350) * 0.5,
				    modal:true,
				    closed:true,
				    href:$('#path').val()+"/adv/tosave.htm"
				});
				$windowAdv.window("open");
			});
			var p = $datagrid.datagrid('getPager');
		    $(p).pagination({  
		    pageSize: 20,//每页显示的记录条数，默认为5  
		    pageList: [20,40,60,100],//可以设置每页记录条数的列表  
		    beforePageText: '第',//页数文本框前显示的汉字  
		    afterPageText: '页    共 {pages} 页',  
		    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		    onSelectPage: function(pageNumber, pageSize){
		    	var params=jsonFromt($('#advQueryForm').serializeArray());
		    	params.pageIndex=pageNumber;
				params.pageSize=pageSize;
		    	request($('#path').val()+"/adv/list.htm",params);
		    }
		}); 
		
		var options =  $(p).data("pagination").options;
		var params=jsonFromt($('#advQueryForm').serializeArray());
		params.pageIndex=options.pageNumber;
		params.pageSize=options.pageSize;
		request($('#path').val()+"/adv/list.htm",params); 
		function request(url,query,resultType){
			   $.post(url,query,resultType||"json").done(function(data){
				   $datagrid.datagrid('loadData', data);
			   }).fail(function(){
				   
			   }).always(function(){
				   
			   });
		}
	
	
});





