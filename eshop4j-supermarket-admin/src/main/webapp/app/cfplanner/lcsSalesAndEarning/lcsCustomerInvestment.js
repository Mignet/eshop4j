using(['common','my97DatePicker','datagrid'],function(){
	lh.inintDatagrid('#lcsCustomerInvestmentDg','#lcsCustomerInvestmentSearch','rest/lcsSalesAndEarningList/getLcsCustomerInvestmentList',lcsCustomerInvestmentModel);
	
	lh.on("click").addHandler(
			{"selector":"#lcsCustomerInvestmentSearch .J_export","handler": function(e){
				$form = $("#lcsCustomerInvestmentSearch").attr("action","rest/lcsSalesAndEarningList/exportLcsCustomerInvestmentDetail");
				$form[0].submit();
				return false;
			}
	});
});