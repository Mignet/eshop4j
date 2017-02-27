var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];
var table;

$(document).ready(function() {
	
    table = $('#apprExample').DataTable( {
    	dom: "Bfrtip",
        "processing": true,
        "serverSide": true,
        "language": {
        	select: {
                rows: {
                    _: "已选择 %d 行",
                    1: "已选择 1 行"
                }
            },
        	"sProcessing":   "处理中...",
        	"sLengthMenu":   "显示 _MENU_ 项结果",
        	"sZeroRecords":  "没有匹配结果",
        	"sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        	"sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
        	"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        	"sInfoPostFix":  "",
        	"sSearch":       "客户名称:",
        	"sUrl":          "",
        	"sEmptyTable":     "表中数据为空",
        	"sLoadingRecords": "载入中...",
        	"sInfoThousands":  ",",
        	"oPaginate": {
        		"sFirst":    "首页",
        		"sPrevious": "上页",
        		"sNext":     "下页",
        		"sLast":     "末页"
        	},
        	"oAria": {
        		"sSortAscending":  ": 以升序排列此列",
        		"sSortDescending": ": 以降序排列此列"
        	}
        },
        "ajax": {
            "url": "/rest/acc/acwithdrawapply/onelist",
            "type": "POST",
            "data":function(d){
            	return {'_dt_json':JSON.stringify(d)
            		};
            		}//传递对象太多，json化
        },
        'columnDefs': [{
	         'targets': 0,
	         'searchable': false,
	         'orderable': false,
	         'className': 'dt-body-center'
	      }],
	    'order': [[6, 'asc']],
        "columns": [
            { "data": "id","orderable": false ,
            	"render":function (data,type,full,meta) {
      	             return '<input type="checkbox" id="checkboxId" name="txid" value="' + $('<div/>').text(data).html() + '">';
   				},},
   			{ "data": "id","orderable": false},
            { "data": "orderId","orderable": false },
            { "data": "userName","orderable": false },
            { "data": "mobile","orderable": false },
            { "data": "bisTime","orderable": false },
            { "data": "confirmTime","orderable": false },
            { "data": "noticeTime","orderable": false },
            { "data": "totalAmount","orderable": false },
            { "data": "amount","orderable": false },
            { "data": "fee","orderable": false },
            { "data": "status",render:function ( data, type, row ) {
                
                if(data==0)return "提现记录"
            	   if(data==1)return "提现中,待审核"
            	   if(data==2)return "审核通过,等待支付平台结果"
            	   if(data==3)return "审核不通过"
          	   if(data==4)return "冻结失败"
          	   if(data==5)return "提现成功"
            	   if(data==6)return "提现失败,需要解冻"
            	   if(data==7)return "提现失败,已处理解冻"
          	   if(data==8)return "打款处理中"
            	
                 }},
            { "data": "userType",render:function ( data, type, row ) {
            	
            	  if(data==1)return "猎财大师"
              	  if(data==2)return "投呗"
          	
               }},
            { "data": "dealId","orderable": false },
            { "data": "remark","orderable": false },
			    
        ],
        select: true,
        buttons: []
    } );
    
    
	   $('#apprexample-select-all').on('click', function(){
	      var rows = table.rows({ 'search': 'applied' }).nodes();
	      $('input[type="checkbox"]', rows).prop('checked', this.checked);
	   });

	   $('#apprExample tbody').on('change', 'input[type="checkbox"]', function(){
	      if(!this.checked){
	         var el = $('#example-select-all').get(0);
	         if(el && el.checked && ('indeterminate' in el)){
	            el.indeterminate = true;
	         }
	      }
	   });

	   $('#appr-example').on('submit', function(e){
		   var form = this;

	      table.$('input[type="checkbox"]').each(function(){
	         if(!$.contains(document, this)){
	            if(this.checked){
	               $(form).append(
	                  $('<input>')
	                     .attr('type', 'hidden')
	                     .attr('name', this.name)
	                     .val(this.value)
	               );
	            }
	         } 
	      });
	      var ids = $(form).serialize().replace("select_all=1&","").replace(/&/g,",").replace(/txid=/g,"");
	      
	      if($(form).serialize()==""){
	    	  alert("请选择审批数据");
	    	  e.preventDefault();
	    	  return;
	      }else{
	    	  $.ajax({  
		          url: '/rest/acc/acwithdrawapply/approveOnePay' ,  
		          type: 'POST',  
		          data: 'ids=' + ids,  
		          success: function (result) { 
		        	  console.log("returndata",result);
		              if (result.success) {
		                 //保存成功  1.关闭弹出层，2.刷新表格数据
		                 alert(result.message);
		                 table.draw();
		              }else {
		                 alert(result.message);
		                 table.draw();
		              }
		              document.getElementById("apprexample-select-all").checked = false;
		          },  
		          error:function(XmlHttpRequest,textStatus, errorThrown)
		    	  {
		    		  console.log(XmlHttpRequest.status);
		    		  console.log(textStatus);
		    		  showError(XmlHttpRequest.responseText);
		    	  } 
		     });  
	      }
	      
	       
	      $('input[type="hidden"][name="txid"]', form).remove();     

	      e.preventDefault();
    
} )
});
