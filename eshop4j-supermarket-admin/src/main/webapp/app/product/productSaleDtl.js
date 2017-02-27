using(["datagrid","pagination","messager"],function(){
	var $datagrid = $('#proSaleDtlDg').datagrid(
			{	
				pagination: true, 
		        rownumbers: true,
				pageList:[10,20,40,50,100,500],
				fit:true,
				singleSelect:true,
				toolbar:"#proSaleDtlToolbar",
				queryParams:jsonFromt($('#proSaleDtlQueryForm').serializeArray()),
				columns : [ [

						{
							field : 'buyUserName',
							title : '投资者姓名',
							width : 200,
							align : 'center'
						},
						{
							field : 'buyUserMobile',
							title : '投资者账号',
							width : 200,
							align : 'center'
						},
						{
							field : 'buyedMoney',
							title : '销售金额(元)',
							width : 150,
							align : 'center',
							formatter:function(value,row,index){ 
								return value;
		                     },
						},
						{
							field : 'buyedTime',
							title : '销售时间',
							width : 150,
							align : 'center',
							formatter : function(value, row, index) {
								if(value != null){
									var date = new Date(value);  
			                         return formateDate1(date);
								}else{
									return '-';
								}
							}
						}] ]
			});
			$('#proSaleDtlQueryButton').click(function() {
				var params=jsonFromt($('#proSaleDtlQueryForm').serializeArray());
				var options =  $(p).data("pagination").options;
				params.page=options.pageNumber;
				params.rows=options.pageSize;
				request("rest/product/prosaledtllist",params); 
			});
			$("#proSaleDtlExcelButton").click(function(){
				$.messager.confirm('提示', '导出最近30天的记录，确定导出？'	, function(r) {
					if (r) {
						$form = $("#proSaleDtlQueryForm").attr("action","rest/export/prosaledtl");
						$form[0].submit();
					}
					});
			});
			//retBtn
			$("#retBtn").click(function(){
				var $window = self.parent.$("#proSaleWin").window();
				$window.window('close');
			});
			var p = $datagrid.datagrid('getPager');
		    $(p).pagination({  
		    pageSize: 20,//每页显示的记录条数，默认为5  
		    pageList: [20,40,60,100],//可以设置每页记录条数的列表  
		    beforePageText: '第',//页数文本框前显示的汉字  
		    afterPageText: '页    共 {pages} 页',  
		    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		    onSelectPage: function(pageNumber, pageSize){
		    	var params=jsonFromt($('#proSaleDtlQueryForm').serializeArray());
		    	params.page=pageNumber;
				params.rows=pageSize;
		    	request("rest/product/prosaledtllist",params);
		    }
		}); 
		var options =  $(p).data("pagination").options;
		var params=jsonFromt($('#proSaleDtlQueryForm').serializeArray());
		params.page=options.pageNumber;
		params.rows=options.pageSize;
		request("rest/product/prosaledtllist",params); 
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
