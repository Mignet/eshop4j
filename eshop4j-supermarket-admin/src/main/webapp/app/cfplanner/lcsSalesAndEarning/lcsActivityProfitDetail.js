using(['common','datagrid'],function(){
	lh.inintDatagrid('#lcsActivityProfitDetailDg','#lcsActivityProfitDetailSearch','rest/lcsSalesAndEarningList/getLcsActivityProfitDetailList',lcsActivityProfitDetailModel);
	lh.on("click").addHandler(
			{"selector":"#lcsActivityProfitDetailSearch .J_export","handler": function(e){
				$form = $("#lcsActivityProfitDetailSearch").attr("action","rest/lcsSalesAndEarningList/exportLcsActivityProfitDetail");
				$form[0].submit();
				return false;
			}
	});
});