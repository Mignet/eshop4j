
using(["datagrid","pagination","common"],function(){
	
	lh.on("click").addHandler({"selector":'input.J_saleDtl_addTabs',"handler" : function(e){
		var options = $(this).attr("data-options");
		var obj = eval('(' + options + ')');
		var title = "销售明细";
		var href = "rest/product/toprosaledtllist?productId="+obj.productId;
		var $proSaleWin =$('#proSaleWin').window({
			title:title,
		    width:800,
		    height:800,
		    top:($(window).height() - 600) * 0.5,
		    left:($(window).width() - 350) * 0.5,
		    modal:true,
		    closed:true,
		    href:href
		});
		$proSaleWin.window("open");
			return false;
		}
	});
	/**
	 * //否则根据获取新内容并添加新tab
			
	 */
	
	var $datagrid = $('#proSaleDg').datagrid(
			{	
				pagination: true, 
		        rownumbers: true,
				pageList:[10,20,40,50,100,500],
				fitColumns:true,
				remoteSort:false,
				showFooter:true,
				singleSelect:true,
				toolbar:"#proSaleToolbar",
				queryParams:jsonFromt($('#proSaleQueryForm').serializeArray()),
				columns : [ [
						{
							field : 'CateName',
							title : '分类',
							width : 150,
							align : 'center'
						},
						{
							field : 'produtName',
							title : '产品名称',
							width : 150,
							align : 'center'
						}, 
						{
							field : 'buyTotalMoney',
							title : '限额（元）',
							width : 200,
							sortable:true,
							align : 'right',
							sorter:function(a,b){
								return (a>b?1:-1);
							}
						},
						{
							field : 'buyedTotalMoney',
							title : '销售额度（元）',
							width : 200,
							sortable:true,
							align : 'right',
							sorter:function(a,b){
								return (a>b?1:-1);
							}
						},
						{
							field : 'restMoney',
							title : '剩余额度（元）',
							sortable:true,
							width : 150,
							align : 'right',
							sorter:function(a,b){
								return (a>b?1:-1);
							}
						},
						{
							field : 'saleStatusName',
							title : '销售状态',
							width : 80,
							align : 'center'
						},{
							field : 'beginSaleTime',
							title : '开售时间',
							width : 200,
							sortable:true,
							align : 'right',
							formatter : function(value, row, index) {
								if(value != null){
									var date = new Date(value);  
			                         return formateDate1(date);
								}else{
									return '-';
								}
		                           
							}
						},{
							field : 'collectFinishTime',
							title : '售罄时间',
							width : 200,
							sortable:true,
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
							width : 150,
							align : 'center',
							formatter: function(value, row, index) {
								return '<input  class="J_saleDtl_addTabs" type = "button" value="销售明细" data-options="{productId:\''+row.productId+'\',type:\'detail\'}" ></input>';
								//return '---';
							},
							
						} ] ]
			});
			$('#proSaleQueryButton').click(function() {
				var params=jsonFromt($('#proSaleQueryForm').serializeArray());
				var options =  $(p).data("pagination").options;
				params.page=options.pageNumber;
				params.rows=options.pageSize;
				request("rest/product/prosalelist",params); 
			});
			//proSaleExcelBtn
			$("#proSaleExcelBtn").click(function(){
				$form = $("#proSaleQueryForm").attr("action","rest/export/prosale");
				$form[0].submit();
			});
			var p = $datagrid.datagrid('getPager');
		    $(p).pagination({  
		    pageSize: 20,//每页显示的记录条数，默认为5  
		    pageList: [20,40,60,100],//可以设置每页记录条数的列表  
		    beforePageText: '第',//页数文本框前显示的汉字  
		    afterPageText: '页    共 {pages} 页',  
		    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		    onSelectPage: function(pageNumber, pageSize){
		    	var params=jsonFromt($('#proSaleQueryForm').serializeArray());
		    	params.page=pageNumber;
				params.rows=pageSize;
		    	request("rest/product/prosalelist",params);
		    }
		}); 
		
		var options =  $(p).data("pagination").options;
		
		request("rest/product/prosalelist",{page:options.pageNumber,rows:options.pageSize}); 
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

