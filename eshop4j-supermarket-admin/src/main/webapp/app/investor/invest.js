using(["datagrid","pagination"],function(){
	var $datagrid = $('#investListDg').datagrid({
		pagination : true,
		rownumbers : true,
		pageList : [ 10, 20, 40, 50, 100, 500 ],
		fitColumns : true,
		showFooter : true,
		toolbar : "#investRecordToolbar",
		queryParams : jsonFromt($('#investRecordQueryForm').serializeArray()),
		columns : [ [ {
			field : 'investTime',
			title : '投资时间',
			width : 150,
			align : 'left',
			formatter : function(value, row, index) {
				var date = new Date(value);
				return formateDate1(date);
			}
		}, {
			field : 'productName',
			title : '产品名称',
			width : 200,
			align : 'left'
		}, {
			field : 'investState',
			title : '状态',
			width : 200,
			align : 'left',
			formatter : function(value, row, index) {

				if (2 == value) {
					return '<font color="red">投资</font>';
				} else if (3 == value) {
					return '<font color="green">赎回</font>';
				} else {
					return '';
				}
			}
		}, {
			field : 'endDate',
			title : '到期时间',
			width : 150,
			align : 'center',
			formatter : function(value, row, index) {

				if (value != null) {
					var date = new Date(value);
					return formateDate1(date);
				} else {
					return '-';
				}

			},
		}, {
			field : 'profit',
			title : '收益金额(元)',
			width : 80,
			align : 'left',
			formatter : function(value, row, index) {
				if (row.investState == 2) {
					return '-<font color="red">' + value + '</font>';
				} else if (row.investState == 3) {
					return '+<font color="green">' + value + '</font>';
				} else {
					return value;
				}
			}
		}, {
			field : 'investAmount',
			title : '投资总额(元)',
			width : 80,
			align : 'left',
			formatter : function(value, row, index) {

				if (row.investState == 2) {
					return '-<font color="red">' + value + '</font>';
				} else if (row.investState == 3) {
					return '+<font color="green">' + value + '</font>';
				} else {
					return value;
				}
			}
		} ] ]
	});
	
			$('#investRecordQueryButton').click(function() {
				var params=jsonFromt($('#investRecordQueryForm').serializeArray());
				var options =  $(p).data("pagination").options;
				params.page=options.pageNumber;
				params.rows=options.pageSize;
				request("rest/invest/investlist",params); 
			});
			var p = $datagrid.datagrid('getPager');
		    $(p).pagination({  
		    pageSize: 20,// 每页显示的记录条数，默认为5
		    pageList: [20,40,60,100],//可以设置每页记录条数的列表  
		    beforePageText: '第',//页数文本框前显示的汉字  
		    afterPageText: '页    共 {pages} 页',  
		    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		    onSelectPage: function(pageNumber, pageSize){
		    	var params=jsonFromt($('#investRecordQueryForm').serializeArray());
		    	params.page=pageNumber;
				params.rows=pageSize;
		    	request("rest/invest/investlist",params);
		    }
		}); 
		var options =  $(p).data("pagination").options;
		var params=jsonFromt($('#investRecordQueryForm').serializeArray());
		params.page=options.pageNumber;
		params.rows=options.pageSize;
		
		request("rest/invest/investlist",params); 
		function request(url,query,resultType){
			$.ajax({
				  type: 'POST',
				  url: url,
				  data: query,
				  success: function(data){
					  debugger;
					   $datagrid.datagrid('loadData', data);
				  },
				  dataType: resultType||"json"
				});
		}
	
});
