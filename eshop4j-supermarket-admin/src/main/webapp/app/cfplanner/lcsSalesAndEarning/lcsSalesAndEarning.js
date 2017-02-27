using(['common','my97DatePicker','datagrid','window'],function(){
	
	lh.inintDatagrid('#lcsSalesAndEarningDg','#lcsSalesAndEarningSearch','rest/lcsSalesAndEarningList/getLcsSalesAndEarningList',lcsSalesAndEarningModel,true);
	lh.on("click").addHandler([
	  /*  {"selector":"#lcsSalesAndEarningSearch .J_export","handler": function(e){
			$form = $("#lcsSalesAndEarningSearch").attr("action","rest/lcsSalesAndEarningList/exportLcsSalesAndEarningDetail");
			$form[0].submit();
			return false;
		}},*/
		{"selector":"a[name='lcsCommissionDetail']","handler": function(e){
		var $this = $(this);
		lh.openWin({
			title : "佣金明细",
		    width:1200,
		    height:600,
		    modal:true,
		    href:'rest/lcsSalesAndEarningList/lcsCommissionDetailPage?mobile='+$this.attr("data-mobile")
		});
		return false;
		}},
		{"selector":"a[name='lcsRecommendedIncome']","handler": function(e){
			lh.openWin({
				title : "推荐收益明细",
				width:1200,
			    height:700,
				inline:true,
				collapsible:false,
			    modal:true,
			    href:'rest/lcsSalesAndEarningList/lcsRecommendedIncomePage',
			    queryParams :{"mobile":$(this).attr("data-mobile")}
			});
			return false;
		}},
		{"selector":"a[name='activityProfit']","handler": function(e){
			var $this = $(this);
			lh.openWin({
				title : "活动奖励",
			    width:1000,
			    height:600,
			    modal:true,
			    href:'rest/lcsSalesAndEarningList/LcsActivityProfitDetailPage?mobile='+$this.attr("data-mobile")
			});
			return false;
		}},
		{"selector":"a[name='customerInvestment']","handler": function(e){
			var $this = $(this);
			lh.openWin({
				title : "当前客户在投",
			    width:1100,
			    height:600,
			    modal:true,
			    href:'rest/lcsSalesAndEarningList/lcsCustomerInvestmentPage?mobile='+$this.attr("data-mobile")
			});
			return false;
		}}
	]);

});