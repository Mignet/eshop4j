using(['common','my97DatePicker','datagrid'],function(){
	lh.inintDatagrid('#lcsRecommendedIncomeDg','#lcsRecommendedIncomeSearch','rest/lcsSalesAndEarningList/getLcsRecommendedIncomeList',lcsRecommendedIncomeModel);
	lh.on("click").addHandler(
			{"selector":"#lcsRecommendedIncomeSearch .J_export","handler": function(e){
				$form = $("#lcsRecommendedIncomeSearch").attr("action","rest/lcsSalesAndEarningList/exportLcsRecommendedIncomeDetail");
				$form[0].submit();
				return false;
			}
	});
});