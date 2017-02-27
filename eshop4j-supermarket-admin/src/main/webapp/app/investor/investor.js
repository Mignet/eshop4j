
using(["datagrid","pagination","common"],function(){
	
	lh.on("click").addHandler({"selector":'input.J_openInvestWin',"handler" : function(e){
		var options = $(this).attr("data-options");
		var obj = eval('(' + options + ')');
		console.info(obj);
		var title = "";
		var href = "rest";
		if(obj.type == "reset"){
			title = "重置密码";
			href +="/invest/topwdreset?mobile="+obj.mobile;
		}else if(obj.type == "dtl"){
			title = "客户详情";
			href +="/invest/investdtl?mobile="+obj.mobile;
		}else if(obj.type =="changeLcs"){
			title = "重新绑定理财师";
			href +="/customerfix/toChangeLcs?mobile="+obj.mobile;
		}
		var $windowInvestor =$('#investorWin').window({
			title:title,
		    width:800,
		    height:700,
		    top:($(window).height() - 600) * 0.5,   
            left:($(window).width() - 350) * 0.5,
		    modal:true,
		    closed:true,
		    href:href
		});
		$windowInvestor.window("open");
			
			return false;
		}
	});
	
	lh.on("click").addHandler({"selector":'a.J_invit_addTabs',"handler" : function(e){
		var options = $(this).attr("data-options");
		var obj = eval('(' + options + ')');
		console.info(obj);
		var tabTitle = "邀请的客户";
		var url = "rest/invest/toinvestedlist?mobile="+obj.mobile;
		var $tabs = $('#tabs');
		if ($tabs.tabs('exists', tabTitle)) {
			//如果存在先关闭
			$tabs.tabs('close', tabTitle);
			//刷新内容
			$('#mm-tabupdate').trigger('click');
		} 
		//添加新tab
		$tabs.tabs('add', {
			title : tabTitle,
			href : url,
			closable : true,
		});
		//选中
		$tabs.tabs('select', tabTitle);
		
			
			return false;
		}
	});
	
	lh.on("click").addHandler({"selector":'a.J_bound',"handler" : function(e){
		var options = $(this).attr("data-options");
		var obj = eval('(' + options + ')');
		var title = "绑定理财师";
		var href = 'rest/customerfix/tobound?customerMobile='+obj.mobile;
		var $windowInvestor =$('#investorWin').window({
			title:title,
		    width:800,
		    height:700,
		    top:($(window).height() - 600) * 0.5,   
            left:($(window).width() - 350) * 0.5,
		    modal:true,
		    closed:true,
		    href:href
		});
		$windowInvestor.window("open");
			return false;
		}
	});
	/**
	 * //否则根据获取新内容并添加新tab
			
	 */
	
	var $datagrid = $('#dg').datagrid(
			{	
				pagination: true, 
		        rownumbers: true,
		        checkOnSelect:false,
				pageList:[10,20,40,50,100,500],
				fitColumns:true,				
				toolbar:"#investorToolbar",
				remoteSort:false,
				rowStyler:function(value,row,index){
					return 'height:40px;';
			    },
				queryParams:jsonFromt($('#investorQueryForm').serializeArray()),
				columns : [ [
						{
							field : 'customerName',
							title : '姓名',
							width : 110,
							align : 'center',
							formatter:function(value, row, index){
								return value||"-";
							}
						},
						{
							field : 'customerMobile',
							title : '电话',
							width : 200,
							align : 'center',
							formatter:function(value, row, index){
								if(value.length>0){
									return value.substring(0,3)+'****'+value.substring(7,value.length);
								}
								
							}
						},
						{
							field : 'currsaleuser',
							title : '所属理财师',
							width : 200,
							align : 'center',
							formatter: function(value, row, index) {								
								if(typeof(row.currSaleuserNumber) == "undefined" ){
									return '&nbsp;&nbsp;<a class="J_bound" href="#" data-options="{mobile:'+row.customerMobile+'}">绑定</a>';
								}else{
									return value;
								}
							}
						},
						{
							field : 'investNum',
							title : '邀请客户',
							width : 120,
							align : 'center',
							formatter: function(value, row, index) {
								if(parseInt(value) >0 ){
									return '&nbsp;&nbsp;<a class="ui-redirect" data-title="邀请的好友" href="javascript:;" data-url="rest/freindsInfo/list?userId=' + row.customerId + '">'+ value +'</a>'
								}else{
									return '-';
								}
							},
						},
						{
							field : 'regtime',
							title : '注册时间',
							width : 110,
							align : 'center',
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
							field : '_operate',
							title : '操作',
							width : 180,
							formatter: function(value, row, index) {
								var ret = '';
									/*ret +='<input  class="J_openInvestWin" type = "button" value="客户详情" data-options="{mobile:'+row.customerMobile+',type:\'dtl\'}" ></input>';
									ret +='&nbsp;&nbsp;<input  type = "button" class="J_openInvestWin" value="重置密码" data-options="{mobile:'+row.customerMobile+',type:\'reset\'}"></input>';
									ret +='&nbsp;&nbsp;<input  type = "button" class="J_openInvestWin" value="重绑理财师" data-options="{mobile:'+row.customerMobile+',type:\'changeLcs\'}"></input>';*/
									ret +='&nbsp;&nbsp;<button class="ui-redirect" data-title="详情" href="javascript:;" data-url="rest/invest/investorDetail?mobile='+row.customerMobile+'">详情</button>'
									/*ret +='&nbsp;&nbsp;<button class="ui-redirect" data-title="邀请的好友" href="javascript:;" data-url="rest/freindsInfo/list?userId=' + row.customerId + '">邀请的好友</button>'*/
								 return ret;  
							},
							align : 'center'
						} ] ]
			});
	        
			$('#queryButton').click(function() {
				var params=jsonFromt($('#investorQueryForm').serializeArray());
				var options =  $(p).data("pagination").options;
				params.page=options.pageNumber;
				params.rows=options.pageSize;
				request("rest/invest/investorlist",params); 
			});
			$("#excelInvestorButton").click(function(){
				$form = $("#investorQueryForm").attr("action","rest/export/investor");
				$form[0].submit();
			});
			var p = $datagrid.datagrid('getPager');
		    $(p).pagination({  
		    pageSize: 20,//每页显示的记录条数，默认为5  
		    pageList: [20,40,60,100],//可以设置每页记录条数的列表  
		    beforePageText: '第',//页数文本框前显示的汉字  
		    afterPageText: '页    共 {pages} 页',  
		    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		    onSelectPage: function(pageNumber, pageSize){
		    	var params=jsonFromt($('#investorQueryForm').serializeArray());
		    	params.page=pageNumber;
				params.rows=pageSize;
		    	request("rest/invest/investorlist",params);
		    }
		}); 
		
		var options =  $(p).data("pagination").options;
		var params=jsonFromt($('#investorQueryForm').serializeArray());
		params.page=options.pageNumber;
		params.rows=options.pageSize;
		//request("rest/invest/investorlist",params); 
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





