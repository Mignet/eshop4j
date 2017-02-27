
using(["datagrid"],function(){
	var $datagrid = $('#rechargeDg').datagrid(
			{	
				pagination: false, 
		        rownumbers: true,				
				fit:true,
				toolbar:"#rechargeToolbar",
				queryParams:jsonFromt($('#rechargeQueryForm').serializeArray()),
				columns : [ [
						{
							field : 'amount',
							title : '金额',
							width : 150,
							align : 'left'
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
			$('#rechargeQueryButton').click(function() {
				var params=jsonFromt($('#rechargeQueryForm').serializeArray());
				request("rest/invest/rechargelist",params); 
			});
			var params=jsonFromt($('#rechargeQueryForm').serializeArray());
			console.info(params);
		    request("rest/invest/rechargelist",params); 
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
