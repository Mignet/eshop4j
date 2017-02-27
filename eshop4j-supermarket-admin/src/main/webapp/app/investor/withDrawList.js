
using(["datagrid"],function(){
	var $datagrid = $('#withDrawDg').datagrid(
			{	
				pagination: false, 
		        rownumbers: true,
				pageList:[10,20,40,50,100,500],
				fit:true,
				toolbar:"#withDrawToolbar",
				queryParams:jsonFromt($('#withDrawQueryForm').serializeArray()),
				columns : [ [
						{
							field : 'totalAmount',
							title : '提现金额',
							width : 150,
							align : 'left'
						},{
							field : 'amount',
							title : '提现金额',
							width : 200,
							align : 'left',
							formatter : function(value, row, index) {

								return value;
							}
						},{
							field : 'fee',
							title : '手续费',
							width : 200,
							align : 'left',
							formatter : function(value, row, index) {
								return value;
							}
						},
						{
							field : 'status',
							title : '状态',
							width : 200,
							align : 'left',
							formatter : function(value, row, index) {

								return value;
							}
						},
						{
							field : 'bisTime',
							title : '时间',
							width : 200,
							align : 'left'
						}
						] ]
			});
			$('#withDrawQueryButton').click(function() {
				var params=jsonFromt($('#withDrawQueryForm').serializeArray());
				request("rest/invest/withdrawlist",params); 
			});
			var params=jsonFromt($('#withDrawQueryForm').serializeArray());
		    request("rest/invest/withdrawlist",params); 
			function request(url,query,resultType){
				console.info(params);
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
