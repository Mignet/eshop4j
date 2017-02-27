$(function(){
	/**
	 * 点击产品销售详细
	 */
	$("body").on('click','.J_productSale_detail',function(event){
		var productId = $(event.target).attr("data-productId");
		$("#productId").val(productId);//设置隐藏productId
		var productName = $(event.target).attr("data-productName");
		$("#productSaleName").html(productName);//设置productName
		//加载销售详细
		$("#productNaOrNumber").val('');
		$("#investStartTime").val('');
		$("#investEndTime").val('');
		$('a.productSaleDetail_search').trigger('click');
		$("#productSaleDetailModal").modal('show');
	});
	
	/**
	 * 销售详细页面点击搜索
	 */
	$('a.productSaleDetail_search').bind('click',function(){
		$.ajax({
			   type: "POST",
			   url: "rest/cim/cimproduct/productSaleDetail",
			   data:$("#productSaleDetailForm").serialize(),
			   success: function(jsonDataList){
				   if(jsonDataList){
					   var htm = '';
					   for(var o in jsonDataList){
						   console.log(jsonDataList[o]);
						   htm += '<tr>';
						   for(var item in jsonDataList[o]){
							   htm += '<td>' + jsonDataList[o][item] + '</td>';
						   }
						   htm += '</tr>'
					   }
					   $("#productSaleContent").html(htm);	
				   } else {
					   $("#productSaleError").html("产品销售详情失败");	
				   }
			   },
			   error: function (XMLHttpRequest, textStatus, errorThrown) {
				   $("#productSaleError").html("产品销售详情异常");
			}
		});
	});
});