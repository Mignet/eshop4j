
using(["datagrid","pagination","messager","common"],function(){
	
	lh.on("click").addHandler({"selector":'input.J_openProWin',"handler" : function(e){
		var options = $(this).attr("data-options");
		var obj = eval('(' + options + ')');
		console.info(obj);
		var title = "";
		var href = "";
		var type = obj.type;
		if(type=="update"||type=="edit"){
			if(type=="update"){
				title = "产品修改";
				href = "rest/productos/dtl?opType=update&productId="+obj.productId;
			}else if(type=="edit"){
				title = "产品编辑";
				href = "rest/productos/dtl?opType=edit&productId="+obj.productId;
			}
			var $windowPro =$('#proWin').window({
				title:title,
			    width:1000,
			    height:800,
			    top:($(window).height() - 600) * 0.5,
			    left:($(window).width() - 350) * 0.5,
			    modal:true,
			    closed:true,
			    href:href
			});
			$windowPro.window("open");
		}else if(type=="on" ||type=="off" ||type=="release" ){
			var opType =0;
			var opMsg = "";
			var url="";
			var data="";
			if(type=="on"){
				url = "rest/productos/onoffpro";
				data ="opType=1&productId="+obj.productId;
				opMsg = "上架"
			}else if(type=="off"){
				url = "rest/productos/onoffpro";
				data ="opType=2&productId="+obj.productId;
				opMsg = "下架"
			}else if(type=="release"){
				url = "rest/productos/release";
				data ="productId="+obj.productId;
				opMsg = "发布";
			}
			$.messager.confirm('提示', '确定'+opMsg+"?"	, function(r) {
				if (r) {
					$.messager.progress({
						title : '请稍后',
						msg : '玩命加载中...'
					});
				
					$.ajax({
						type : 'post',
						url : url,
						data : data,
						dataType : 'json',
						success : function(result) {
							$.messager.progress('close');
							if (result.message=='success') {
								$.messager.alert('提示', result.data, 'info');
								//关闭模式窗口 刷新列表
								/*var $window = self.parent.$("#proWin").window();
								$window.window('refresh','rest/product/prolist');
								//self.parent.$("#investorWin").window('close');
								self.parent.$('#proDg').datagrid('reload');*/
                                $('#proDg').datagrid({pageSize:20,url:"rest/product/prolist", queryParams:jsonFromt($('#proQueryForm').serializeArray())});
							} else {
								$.messager.alert('提示', result.data, 'error');
							}
							//closeWin();
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							$.messager.progress('close');
							$.messager.alert('提示', '服务异常！', 'error');
						}
					});
				}
			});
		}
		
			return false;
		}
	});
	
	lh.on("click").addHandler({"selector":'a.J_addTabs',"handler" : function(e){
		var options = $(this).attr("data-options");
		var obj = eval('(' + options + ')');
		console.info(obj);
		var tabTitle = "销售明细";
		var url = "rest/invest/toinvestedlist";
		var $tabs = $('#tabs');
		if ($tabs.tabs('exists', tabTitle)) {
			//选中
			$tabs.tabs('select', tabTitle);
			//刷新内容
			$('#mm-tabupdate').trigger('click');
		} else {
			//添加新tab
			$tabs.tabs('add', {
				title : tabTitle,
				href : url,
				closable : true,
			});
		}
			
			return false;
		}
	});
	/**
	 * //否则根据获取新内容并添加新tab
			
	 */
	
	/**
	 * 
	 */
	var $datagrid = $('#proDg').datagrid(
			{	
				pagination: true, 
		        rownumbers: true,
				pageList:[10,20,40,50,100,500],
				fitColumns:true,
				toolbar:"#proToolbar",
				singleSelect:true,
				queryParams:jsonFromt($('#proQueryForm').serializeArray()),
				frozenColumns:[[
							{
								field : 'productName',
								title : '产品名称',
								width : 180,
								align : 'center'
							}
				              ]],
				columns : [ [
						
						{
							field : 'cateName',
							title : '分类',
							width : 120,
							align : 'center'
						},
						{
							field : 'fixRate',
							title : '收益率',
							width : 120,
							align : 'center',
							formatter: function(value, row, index) {
								if(parseFloat(value) >0 ){
									return value+'%';
								}else if(parseFloat(row.flowMinRate) >0){
									return row.flowMinRate +"~" +row.flowMaxRate+'%'
								}else{
									return '-';
								}
							}
						},
						{
							field : 'feeRatio',
							title : '佣金率',
							width : 120,
							align : 'center',
							formatter: function(value, row, index) {
								if(parseInt(value) >0 ){
									return value+'%';
								}else{
									return '-';
								}
							}
						},
						{
							field : 'proDays',
							title : '期限/天',
							width : 100,
							align : 'center'
						},
						{
							field : 'buyTotalMoney',
							title : '限额',
							width : 120,
							align : 'center'
						},
						{
							field : 'saleStatusName',
							title : '销售状态',
							width : 80,
							align : 'center',
							formatter : function(value, row, index) {
								if(row.greyFlag == 1){
			                         return '灰度';
								}else{
									return row.saleStatusName;
								}
		                           
							}
						},
						{
							field : 'beginSaleTime',
							title : '开售时间',
							width : 100,
							align : 'center',
							formatter : function(value, row, index) {
								if(value != null){
									var date = new Date(value);  
			                         return formateDate1(date);
								}else{
									return '-';
								}
		                           
							}
						},{
							field : 'proRecomend',
							title : '推荐',
							width : 100,
							align : 'center'
						},
						{
							field : '_operate',
							title : '操作',
							width : 150,
							formatter: function(value, row, index) {
								var ret = '';
								var hasModify = $("#product-sale-modify").length>0;
								var hasPublic = $("#product-sale-publish").length>0;
								var hasBack = $("#product-sale-back").length>0;
								
								//产品销售状态:1-预售、2-在售、3-售罄、4-下架、5-定时发售、6-募集中、7-募集失败-8募集成功
								if(row.greyFlag==1){//灰度 1-预售、2-在售、3-售罄、4-下架
									if(hasModify){
										ret += '<input  class="J_openProWin" type = "button" value="修改" data-options="{productId:\''+row.productId+'\',type:\'update\'}" ></input>';	
									}
									if(hasPublic){
										ret += '&nbsp;&nbsp;<input  class="J_openProWin" type = "button" value="发布" data-options="{productId:\''+row.productId+'\',type:\'release\'}" ></input>';
									}
									
								}else if(row.saleStatus==1){
									if(hasModify){
										ret += '<input  class="J_openProWin" type = "button" value="编辑" data-options="{productId:\''+row.productId+'\',type:\'edit\'}" ></input>';
									}
									if(hasBack){
										ret += '&nbsp;&nbsp;<input  class="J_openProWin" type = "button" value="下架" data-options="{productId:\''+row.productId+'\',type:\'off\'}" ></input>';
									}
									
								}else if(row.saleStatus==2){
									if(hasModify){
										ret += '<input  class="J_openProWin" type = "button" value="编辑" data-options="{productId:\''+row.productId+'\',type:\'edit\'}" ></input>';
									}
									if(hasBack){
										ret += '&nbsp;&nbsp;<input  class="J_openProWin" type = "button" value="下架" data-options="{productId:\''+row.productId+'\',type:\'off\'}" ></input>';
									}
									
								}else if(row.saleStatus==3){//
									if(hasModify){
									ret += '<input  class="J_openProWin" type = "button" value="编辑" data-options="{productId:\''+row.productId+'\',type:\'edit\'}" ></input>';
									}
								}else if(row.saleStatus==4){
									if(hasModify){
										ret += '<input  class="J_openProWin" type = "button" value="编辑" data-options="{productId:\''+row.productId+'\',type:\'edit\'}" ></input>';
									}
									if(hasPublic){
										ret += '&nbsp;&nbsp;<input  class="J_openProWin" type = "button" value="上架" data-options="{productId:\''+row.productId+'\',type:\'on\'}" ></input>';
									}
									
								}else if(row.saleStatus==5){
									if(hasModify){
										ret += '<input  class="J_openProWin" type = "button" value="编辑" data-options="{productId:\''+row.productId+'\',type:\'edit\'}" ></input>';
									}
									if(hasBack){
										ret += '&nbsp;&nbsp;<input  class="J_openProWin" type = "button" value="下架" data-options="{productId:\''+row.productId+'\',type:\'off\'}" ></input>';	
									}
									
								}else if(row.saleStatus==6){
									if(hasModify){
									ret += '<input  class="J_openProWin" type = "button" value="编辑" data-options="{productId:\''+row.productId+'\',type:\'edit\'}" ></input>';
									}
								}else if(row.saleStatus==7){
									if(hasModify){
									ret += '<input  class="J_openProWin" type = "button" value="编辑" data-options="{productId:\''+row.productId+'\',type:\'edit\'}" ></input>';
									}
									if(hasBack){
									ret += '&nbsp;&nbsp;<input  class="J_openProWin" type = "button" value="下架" data-options="{productId:\''+row.productId+'\',type:\'off\'}" ></input>';
									}
								}else if(row.saleStatus==8){
									if(hasModify){
									ret += '<input  class="J_openProWin" type = "button" value="编辑" data-options="{productId:\''+row.productId+'\',type:\'edit\'}" ></input>';
									}
									if(hasBack){
									ret += '&nbsp;&nbsp;<input  class="J_openProWin" type = "button" value="下架" data-options="{productId:\''+row.productId+'\',type:\'off\'}" ></input>';
									}
								}
								 return ret;  
							},
							align : 'center'
						} ] ]
			});
			var p = $datagrid.datagrid('getPager');
			$('#proQueryButton').click(function() {
				var params=jsonFromt($('#proQueryForm').serializeArray());
				var options =  $(p).data("pagination").options;
				params.page=options.pageNumber;
				params.rows=options.pageSize;
				request("rest/product/prolist",params); 
			});
			
		    $(p).pagination({  
		    pageSize: 20,//每页显示的记录条数，默认为5  
		    pageList: [20,40,60,100],//可以设置每页记录条数的列表  
		    beforePageText: '第',//页数文本框前显示的汉字  
		    afterPageText: '页    共 {pages} 页',  
		    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		    onSelectPage: function(pageNumber, pageSize){
		    	var params=jsonFromt($('#proQueryForm').serializeArray());
				params.page=pageNumber;
				params.rows=pageSize;
		    	request("rest/product/prolist",params);
		    }
		}); 
		
		var options =  $(p).data("pagination").options;
		
		request("rest/product/prolist",{page:options.pageNumber,rows:options.pageSize}); 
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
	
		//添加产品
		$("#proAddBt").bind('click',function(){
			var productCate = $("#productCate").val();
			var title = "添加固收类新产品";
			if(productCate=='1002'){
				title = "添加浮动收益产品";
			}
			var url = "rest/product/productAdd";
			var $windowPro =$('#proWin').window({
				title:title,
			    width:800,
			    height:600,
			    top:($(window).height() - 600) * 0.5,
			    left:($(window).width() - 350) * 0.5,
			    modal:true,
			    closed:true,
			    /*maximized:true,*/
			    href:"rest/product/productAdd?productCate="+productCate
			});
			$windowPro.window("open");

	/*		var $tabs = $('#tabs');
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
			$tabs.tabs('select', tabTitle);*/
		});
});

