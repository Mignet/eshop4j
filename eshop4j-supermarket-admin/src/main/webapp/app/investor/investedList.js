using(["datagrid"],function(){
	var $datagrid = $('#investedDg').datagrid(
			{	
				url:$('#path').val()+"/invest/investedlist.htm",
				pagination: true, 
		        rownumbers: true,
				pageList:[10,20,40,50,100,500],
				fit:true,
				toolbar:"#toolbar",
				queryParams:jsonFromt($('#investedForm').serializeArray()),
				columns : [ [
						{
							field : 'customerName',
							title : '名称',
							width : 150,
							align : 'left'
						},
						{
							field : 'customerMobile',
							title : '电话',
							width : 200,
							align : 'left'
						},
						{
							field : 'regTime',
							title : '注册时间',
							width : 200,
							align : 'left',
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
							field : 'totalInvest',
							title : '总投资额(元)',
							width : 150,
							align : 'center',
							formatter : function(value, row, index) {
								if(value == null){
									return '0';
								}else{
									return value;
								}
							}
						},
						{
							field : 'proNum',
							title : '购买产品数',
							width : 80,
							align : 'center'
						}] ]
			});
			$('#queryButton').click(function() {
				var params=jsonFromt($('#investedForm').serializeArray());
				request($('#path').val()+"/invest/investedlist.htm",params); 
			});
			//excelButton
			$("#investedExcelBtn").click(function(){
				$form = $("#investedForm").attr("action",$('#path').val()+"/export/visited.htm");
				$form[0].submit();
			});
			function request(url,query,resultType){
				   $.post(url,query,resultType||"json").done(function(data){
					   $datagrid.datagrid('loadData', data);
				   }).fail(function(){
					   
				   }).always(function(){
					   
				   });
			}
			
	
	
});
