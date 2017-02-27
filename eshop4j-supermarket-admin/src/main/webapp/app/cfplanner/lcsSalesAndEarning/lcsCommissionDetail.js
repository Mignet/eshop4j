using(['common','my97DatePicker','datagrid'],function(){
	lh.inintDatagrid('#lcsCommissionDetailDg','#lcsCommissionDetailSearch','rest/lcsSalesAndEarningList/getLcsCommissionDetailList',lcsCommissionDetailModel);
	
	lh.on("click").addHandler(
		{"selector":"#lcsCommissionDetailSearch .J_export","handler": function(e){
			$form = $("#lcsCommissionDetailSearch").attr("action","rest/lcsSalesAndEarningList/exportLcsCommissionDetail");
			$form[0].submit();
			return false;
		}
	});
	
});