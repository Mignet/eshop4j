var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];

(function ($, DataTable) {
if ( ! DataTable.ext.editorFields ) {
    DataTable.ext.editorFields = {};
}
var table;

var Editor = DataTable.Editor;
var _fieldTypes = DataTable.ext.editorFields;
_fieldTypes.productList = {
    create: function ( conf ) {
        var that = this;
        conf._enabled = true;
        $.ajax({
            async :false,
            url: './rest/redpaper/queryBindingRedpaperProductListPage',
            type: 'get',
            dataType: 'html',
            success:function(data){
            	 var $div= $('<div id="pidsPage">');
                 $div.html(data);
                conf._input =$div;
            }
        });
        return conf._input;
    }
};
})(jQuery, jQuery.fn.dataTable);

$(document).ready(function() {


	//监听modal的hidden 清空modal旧数据
	$("#sendRedpaperModal").on("hidden.bs.modal", function() {
		//var file = $('#file');  
		//file.outerHTML=file.outerHTML;  //清空上传文件路径地址
		//document.getElementById("#myForm").reset(); 
		//$("#myForm").reset();
		console.info('正式发送红包模态框关闭重置模态框..');
		$("#sendRedpaper").removeAttr("disabled"); //启用发送按钮
		resetRedpaperModal();
		delete window['sendRedpaperData']; //清空
		$(this).removeData("bs.modal");
	});

	$("#sendWhiteListRedpaperModal").on("hidden.bs.modal", function() {
		console.info('红包白名单模态框关闭重置模态框..');
		resetRedpaperModal();
		delete window['sendWriteListRedpaperData']; //清空
		$(this).removeData("bs.modal");
	});

    editor = new $.fn.dataTable.Editor( {
    	ajax: {
            create: {
                type: 'POST',
                url:  'rest/redpaper/save',
                data:function(d){
                	return d;
                }
            }
        },
        table: "#dtable",
        idSrc:  'id',
        i18n: {
            "create": {
                "button": "新增红包",
                "title":  "红包信息",
                "submit": "保存"
            },
            "error": {
                "system": "发生系统错误 (More information)"
            },

            "multi": {
                "title": "多个值",
                "info": "选择的内容中当前输入框包含不同的值. 把他们设置成相同的值, 点击这里, 否则它们仍然保留不同的值.",
                "restore": "取消改动"
            },

            "datetime": {
                "previous": '前',
                "next":     '后',
                "months":   [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
                "weekdays": [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
                "amPm":     [ '上午', '下午' ],
                "unknown":  '-'
            }
        },

        fields: [
            {
            	label: "红包类型:",
            	name: "rtype",
                type: "select",
                options: [{ label: "投资返现红包", value: 1 }],
                "default": 1
            },

            {
                label: "红包名称:",
                name: "name"
            },
            {
                label: "红包描述:",
                name: "remark"
            },
            {
                label: "红包金额:",
                name: "money"
            },

            {
                label: "有效日期:",
                name: "validDate",
                type:  'datetime',
                format: 'YYYY-MM-DD HH:mm:ss'
            },
            {
                label: "投资金额限制:",
                name: "limitMoney",
                type: "select",
                options: [
                  { label: "不限", value: 0 },
                  { label: "金额限制",  value: 1 }
                 ],
                 "default": 1
            },
            {
                label: "最小投资金额:",
                name: "minMoney",
                fieldInfo: "0 表示不限最小投资金额"
            },
            {
                label: "最大投资金额:",
                name: "maxMoney",
                fieldInfo: "0 表示不限最大投资金额"
            },

            {
                label: "投资用户限制:",
                name: "limitInvestUser",
                type: "select",
                options: [
                  { label: "不限", value: 0 }/*,
                  { label: "首次投资用户使用",  value: 1 }*/
                 ],
                 "default": 0
            },

            {
                label: "投资产品限制:",
                name: "limitInvestProduct",
                type: "select",
                options: [
                  { label: "不限产品", value: 0 },
                  { label: "按产品期限限制",  value: 1 },
                  { label: "按产品编号限制", value: 2 },
                 ],
                 "default": 0
            },
            {
                label: "期限关系:",
                name: "operator",
                type: "select",
                options: [
                  { label: "等于", value: 0 },
                  { label: "大于等于",  value: 1 }
                 ],
                "default": 0
            },
            {
                label: "期限:",
                name: "deadline"
            },
            {
                label: "产品列表:",
                name: "plist",
                type:"productList"
            }


        ]
    } );
    editor.on( 'preSubmit', function ( e, d, action ) {

    	var data = d.data[0];
    	if(!data.name){
             layer.msg('红包名称不能为空',{time: 1000,icon: 0});
             return false;
    	}
    	if(!data.remark){
             layer.msg('红包描述不能为空',{time: 1000,icon: 0});
            return false;
    	}
         if( !$.isNumeric(data.money) || data.money<=0 ){
            layer.msg('红包金额必须为大于0的数值',{time: 1000,icon: 0});
            return false;
         }
         if(!data.validDate){
            layer.msg('有效日期不能为空',{time: 1000,icon: 0});
           return false;
        }
        if(data.limitMoney==1){
            if(!data.minMoney || !$.isNumeric(data.minMoney) || data.minMoney<0){
                 layer.msg('购买产品金额最小值必须为大于等于0的数值',{time: 1000,icon: 0});
                return false;
            }
            if(!data.maxMoney || !$.isNumeric(data.maxMoney) || data.maxMoney<0){
                 layer.msg('购买产品金额最大值必须为大于等于0的数值',{time: 1000,icon: 0});
               return false;
            }
        }

        if(data.limitInvestProduct==1){
            if(!data.deadline || !$.isNumeric(data.deadline) || data.deadline<=0){
                 layer.msg('产品期限必须为大于0的数值',{time: 1000,icon: 0});
                return false;
            }

        }
        if(data.limitInvestProduct==2){
    		var rproductIds= window['rproductIds'];
    		var notEmpty = milo.getLength(rproductIds)>0;
            	if(notEmpty){
            		data.pids = rproductIds.join(",");
            	}else{
            		layer.msg('请至少选中一个产品！',{time: 1000,icon: 0});
            		return false;
            	}
    	};





    	data =  milo.serialize(data);
    	$.post('rest/redpaper/save', data, function(data, textStatus, xhr) {
    		editor.close();
    		table.draw(); //刷新
                    layer.msg(data.msg,{time: 1000,icon: data.isFlag?1:0});

        });
        refreshPage();
        return false;

    });

    editor.dependent( 'limitMoney', function ( val ) {
        return val===0 ?
            { hide: [
                  "minMoney","maxMoney"
            ] } :
            { show: [
                  "minMoney","maxMoney"
             ]};
    } );

    editor.dependent( 'limitInvestProduct', function ( val ) {
    	if(val===0){
    		return {
	    				hide: [
	                     "operator" ,"deadline", "plist"
	                     ]
    				};
    	}else if(val===1){
    		return {
    			hide: [
    			       "plist"
	                   ],
    			show: [
                     "operator"  , "deadline"
                ]
    		};
    	}else if(val===2){
    		return {
				 hide: [
				        "operator" ,"deadline"
                 ],
                 show: [
                        "plist"
                 ]
			};
    	}

        return {
			hide: [
                   "operator","deadline", "plist"
              ]
  		};
    } );

    var shiro_admin = "disabled=true";
    if($('#shiro_admin')){
    	shiro_admin = "";
    }
     table = $('#dtable').DataTable( {
    	width: 800,
    	dom: "Bfrtip",
    	"searching": false, //关闭本地搜索
    	"ordering": false, //关闭表格的排序功能
        "processing": true, //是否显示处理状态
        "serverSide": true, //是否开启服务器模式
         select: true,
        //客户端处理模式——此模式下如：过滤、分页、排序的处理都在浏览器中进行。
        //服务器端处理模式——此模式下如：过滤、分页、排序的处理都放在服务器端进行。

        "ajax": {
            //"url": $('#path').val()+"/rest/redpaper/redpaperList",
            "url": "./rest/redpaper/redpaperList",
            "type": "POST",
            "data":function(requestParams){return {'dt_json':JSON.stringify(requestParams)};}//传递对象太多，json化
        },
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
        	"sSearch":       "搜索:",
        	"sUrl":          "",
        	"sEmptyTable":     "表中数据为空",
        	"sLoadingRecords": "载入中...",
        	"sInfoThousands":  ",",
        	"oPaginate": {
        		"sFirst":    "首页",
        		"sPrevious": "上页",
        		"sNext":     "下页",
        		"sLast":     "末页"
        	}
        },

        "columns": [
            { "data": "id"},
            { "data": "showName"},
            { "data": "redpaperMoney"},
            { "data": "redpaperCount"},
            { "data": "sendTime"},
            { "data": "expireDate"},
            { "data": "addTime"},
            { "data": "redpaperCount" ,"render": function ( data, type, row ) {
         	   if(data>=1)
         		   return "已发送"
         	   else
         		   return "未发送"
            }},
            { "data": "id" ,"render": function ( data, type, row ) {
            	var param = {};
            	//param.id=row.id;
            	param.activityId=row.activityId;
            	param.redPacketTypeId=row.redPaperType;
            	//console.info(row);

            	//console.info("data: "+data);
            	//渲染 把数据源中的标题和url组成超链接
                //return '<a href="' + data + '" target="_blank">' + row.title + '</a>';
            	var a = '<a href="#" class="J_sendWriteListUsers" data-param='+JSON.stringify(param)+'>发送白名单用户</a>  '; //
	            var b = '<a href="#" class="ui-redirect J_redpaperEdit" data-title="红包编辑" data-url="rest/redpaper/getRedpacketEditPage?activityId='+row.activityId+'&redpacketTypeId='+row.redPaperType+'">编辑</a>  ';
	            //var c = '<a href="rest/redpaper/queryRedpaperInfo" onclick="details(\''+ row.id + '\')">详情及统计</a>   ';
	            var c = '<a href="#" class="ui-redirect J_redpaperDetail" data-title="红包详情及统计" data-url="rest/redpaper/queryRedpaperInfo?activityId='+row.activityId+'&redPacketTypeId='+row.redPaperType+'">详情及统计</a> ';
	            var d = '<a href="#" id='+row.redPaperType+' class="J_sendUsers" data-param='+JSON.stringify(param)+' >正式发送</a> ';
                return a+b+c+d;
             }}
        ],



        buttons: [
            { extend: "create", editor: editor ,formButtons: [
                     { label: '取消', fn: function () { refreshPage();this.close(); } },'保存'
           ]}
        ]
    } );


    //给白名单发红包
     $("body").on("click","a.J_sendWriteListUsers",function(event){
      	var $target = $(event.target);
      	window['sendWriteListRedpaperData'] = $target.attr("data-param");
      	$("#sendWhiteListRedpaperModal").modal("show");
     });

     //正式发红包
     $("body").on("click","a.J_sendUsers",function(event){
    	 var $target = $(event.target);
    	 window['sendRedpaperData'] = $target.attr("data-param");
    	 $("#sendRedpaperModal").modal("show");
    	 $("#uploadForm").hide(); //隐藏file上传标签
     });
   
     
    //单选框点击事件  匹配第一个单选框
    $("input:radio[name='lcsType']:eq(0)").click(function(){
    	$("#options").hide(); //隐藏理财师级别复选框
    	$("#uploadForm").hide(); //隐藏file上传标签

    });
    
    //单选框点击事件  匹配第二个单选框
    $("input:radio[name='lcsType']:eq(1)").click(function(){
    	$("#uploadForm").hide(); //隐藏file上传标签
    	$("#options").show(); //显示理财师级别复选框
    });
    
    //单选框点击事件  匹配第三个单选框
    $("input:radio[name='lcsType']:eq(2)").click(function(){
    	$("#options").hide(); //隐藏理财师级别复选框
    	$("#uploadForm").show(); //显示理财师级别复选框
    	$("input[type=checkbox]").attr("checked",false); //默认复选框不选中
    });
     

   //正式发送红包
 	$("#sendRedpaper").click(function() {
 		var rdoCheck=$("input:radio[name='lcsType']:checked"); //获取选中的单选框
 		var rdoVal = rdoCheck.val(); //all  lcsByLevel importlcs
 		//拼接请求参数
 		var sendData = window['sendRedpaperData'];
 		sendData = jQuery.parseJSON(sendData);
 		var sendNums = $("input[type=text][name=sendNums]").val();
 		if(sendNums>=1){
 		   //alert("是数字");
 		   sendData.sendNums = sendNums; //发送红包数
 		}else{
 		   layer.msg('发放数量请输入[1-9]数字',{time: 1000,icon: 0});
 		   return;
 		}
 		if(rdoVal == "importlcs" ){ //导入发放红包
 			if($('#file').val()==""){
 				layer.msg("上传文件不能为空！",{time: 2000,icon: 1});
 				return;
 			}  
 			
 			var formData = new FormData();
 	 		formData.append('file', $('#file')[0].files[0]);
 	 		formData.append('activityId',sendData.activityId);
 	 		formData.append('redPacketTypeId',sendData.redPacketTypeId);
 	 		
 	 		$.ajax({
 	 		    url: './rest/redpaper/import',
 	 		    type: 'POST',
 	 		    cache: false,
 	 		    data: formData,
 	 		    processData: false,
 	 		    contentType: false,
 	 		    success: function(data) {
					console.info(data);
					layer.msg(data.msg,{time: 2000,icon: 1});
					$("#sendRedpaperModal").modal("hide"); //隐藏
					table.draw();//刷新
				},
		 		error: function (xhr, textStatus, errorThrown) {
		            //错误信息处理
		 			console.info(errorThrown);
		        }
 	 		});
 	 		return;
 		}
 		
 		
 		var level = "";
 		if(rdoVal == "lcsByLevel" ){ //按理财师级别发放红包
 			var cbo=$("input:checkbox[name='cbolevel']:checked"); //判断单选框是否被选中
 			if(cbo.length <= 0){
 				layer.msg('请至少选中一个理财师级别！',{time: 1000,icon: 0});
 				//layer.alert('请至少勾选一行进行删除！', {icon: 0});
				return;
 			}
 			$("input[type='checkbox']").each(function(){
 				if(this.checked){
 					level+=$(this).val()+",";
 				}
 			});
 		}
 		
 		//拼接请求参数
 		sendData.lcsLevel = level;

 		$("#sendRedpaper").attr("disabled","true"); //禁用发送按钮

 		$.ajax({
 					url :"./rest/redpaper/sendRedpaper",
 					type : 'post',
 					//data:{'startDate':startDate,'endDate':endDate},
 					//data:milo.serialize(sendData),
 					data:sendData,
 					dataType : "json",
 					success : function(data) {
 						console.info(data);
 						layer.msg(data.msg,{time: 2000,icon: 1});
 						$("#sendRedpaperModal").modal("hide"); //隐藏
 						table.draw();//刷新
 						//$("#4d56ce1137614e7aa17779fd79c98353").attr("disabled","true");
 					},
			 		error: function (xhr, textStatus, errorThrown) {
			            //错误信息处理
			 			console.info(errorThrown);
			        }
 				});
 	});


 	//发送白名单红包
 	$("#sendWhiteListRedpaper").click(function() {

 		//拼接请求参数
 		var sendData = window['sendWriteListRedpaperData'];
 		sendData = jQuery.parseJSON(sendData);
 		var sendNums = $("#whiteSendNums").val();
 		//sendNums!=""&&sendNums>=1&&!isNaN(sendNums)
 		if(sendNums>=1){
 		   //alert("是数字");
 		   sendData.sendNums = sendNums; //发送红包数
 		}else{
 		   layer.msg('发放数量请输入[1-9]数字',{time: 1000,icon: 0});
 		   return;
 		}
 		$.ajax({
 					url :"./rest/redpaper/sendWhiteListUserRedpaper",
 					type : 'post',
 					data:sendData,
 					dataType : "json",
 					success : function(data) {
 						console.info(data);
 						layer.msg(data.msg,{time: 2000,icon: 1});
 						$("#sendWhiteListRedpaperModal").modal("hide"); //隐藏
 						table.draw();//刷新

 					},
			 		error: function (xhr, textStatus, errorThrown) {
			            /*错误信息处理*/
			 			console.info(errorThrown);
			        }
 				});
 	});

});

//恢复发送红包模态框到 默认设置
function resetRedpaperModal(){
	$("#options").hide(); //隐藏理财师级别复选框
	$("input[type=radio][value=all]").attr("checked",true); //默认选中所有理财师
	$("input[type=checkbox]").attr("checked",false); //不选中
	$("input[type=text][name=sendNums]").val(""); //红包发送数量默认为空
	//file.after(file.clone().val("")); 
	//file.remove(); 
}




function refreshPage(){
	delete window['rproductIds'];
	$.ajax({
        async :false,
        url: './rest/redpaper/queryBindingRedpaperProductListPage',
        type: 'get',
        dataType: 'html',
        success:function(data){
        	$("#pidsPage").html(data);;
        }
    });

}
