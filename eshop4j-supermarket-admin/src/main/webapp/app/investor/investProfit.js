
using(["datagrid","pagination","common"],function(){
	
	lh.on("click").addHandler({"selector":'input.J_openWin',"handler" : function(e){
		var options = $(this).attr("data-options");
		var obj = eval('(' + options + ')');
		var title = "";
		var href = "";
		if(obj.type == "invest"){
			title = "投资明细";
			href +="rest/invest/toinvestlist?customer="+obj.mobile;
		}else if(obj.type == "recharge"){
			title = "充值明细";
			href +="rest/invest/torechargelist?customer="+obj.mobile;
		}else if(obj.type == "withdraw"){
			title = "提现明细";
			href +="rest/invest/towithdrawlist?customer="+obj.mobile;
		}
		var customerWinow =$('#customerWin').window({
			title:title,
		    width:900,
		    height:600,
		    top:($(window).height() - 400) * 0.5,              
		    modal:true,
		    closed:true,
		    href:href
		});
		customerWinow.window("open");
		return false;
		}
	});
	
	var $datagrid = $('#investProfitDg').datagrid(
			{	
				pagination: true, 
		        rownumbers: true,
				pageList:[10,20,40,50,100,500],
				fitColumns:true,
				toolbar:"#investProfitToolbar",
				rowStyler:function(value,row,index){
					return 'height:40px;';
			    },
				queryParams:jsonFromt($('#investProfitQueryForm').serializeArray()),
				frozenColumns:[[
								{
									field : 'customerName',
									title : '姓名',
									width : 120,
									align : 'center'
								},
								{
									field : 'customerMobile',
									title : '电话',
									width : 150,
									align : 'center'
								},
								{
									field : 'regTime',
									title : '注册时间',
									width : 200,
									align : 'center',
									formatter : function(value, row, index) {
										if(value != null){
											var date = new Date(value);  
					                         return formateDate1(date);
										}else{
											return '-';
										}
				                           
									}
								}
								
								]],
				columns : [ [
						{
							field : 'investTotal',
							title : '投资总额',
							width : 150,
							align : 'left'
						},
						{
							field : 'investingTotal',
							title : '在投金额',
							width : 150,
							align : 'center'
						},
						{
							field : 'investCount',
							title : '投资笔数',
							width : 80,
							align : 'left'
						},
						{
							field : 'totalProfit',
							title : '收益总额',
							width : 80,
							align : 'left'
						},
						{
							field : 'redpaperAmout',
							title : '红包金额',
							width : 80,
							align : 'left'
						},
						{
							field : 'activeReward',
							title : '活动奖励',
							width : 80,
							align : 'left',
							formatter:function(value,row,index){
								return '-';
							}
						},
						{
							field : 'otherReward',
							title : '其他奖励',
							width : 80,
							align : 'left',
							formatter:function(value,row,index){
								return '-';
							}
						},
						{
							field : 'recentlyInvest',
							title : '最近投资',
							width : 80,
							align : 'left'
						},
						{
							field : '_operate',
							title : '操作',
							width : 200,
							formatter: function(value, row, index) {
								var ret = '<input  class="J_openWin" type = "button" value="投资" data-options="{mobile:'+row.customerMobile+',type:\'invest\'}" ></input>';
									ret +='&nbsp;&nbsp;<input  type = "button" class="J_openWin" value="充值" data-options="{mobile:'+row.customerMobile+',type:\'recharge\'}"></input>';
									ret +='&nbsp;&nbsp;<input  type = "button" class="J_openWin" value="提现" data-options="{mobile:'+row.customerMobile+',type:\'withdraw\'}"></input>';
								 return ret;  
							},
							align : 'center'
						} ] ]
			});
			var p = $datagrid.datagrid('getPager');
			
			$('#investProfitQueryButton').click(function() {
				var params=jsonFromt($('#investProfitQueryForm').serializeArray());
				var options =  $(p).data("pagination").options;
				params.page=options.pageNumber;
				params.rows=options.pageSize;
				request("rest/invest/investprofitlist",params); 
			});
			$("#investProfitExportBtn").click(function(){
				$form = $("#investProfitQueryForm").attr("action","rest/export/investprofit");
				$form[0].submit();
			});
			
		    $(p).pagination({  
		    pageSize: 20,//每页显示的记录条数，默认为5  
		    pageList: [20,40,60,100],//可以设置每页记录条数的列表  
		    beforePageText: '第',//页数文本框前显示的汉字  
		    afterPageText: '页    共 {pages} 页',  
		    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		    onSelectPage: function(pageNumber, pageSize){
		    	var params=jsonFromt($('#investProfitQueryForm').serializeArray());
		    	params.page=pageNumber;
				params.rows=pageSize;
		    	request("rest/invest/investprofitlist",params);
		    }
		}); 
		
		var options =  $(p).data("pagination").options;
		//request("rest/invest/investprofitlist",{page:options.pageNumber,rows:options.pageSize}); 
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
