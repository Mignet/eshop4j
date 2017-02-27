$(function(){
	//审核状态绑定change事件
    $("body").on("change",'#auditStatus',function(event){
		if($(event.target).val() == 2){
			$("#allAuditButton").show("slow");
		} else {
			$("#allAuditButton").hide("slow");
		}
     });
    
	//产品编辑按钮
    $("#J-newslist").on("click",'.J_productEdit',function(event){
    	//点击到图标上面  则不响应
    	if($(event.target).context.localName == 'i'){
    		return false;
        }
 	   var productTableId = $(event.target).attr("data-id");
 	   var productIdForEdit = $(event.target).attr("data-productId");
 	   var showIndex = $(event.target).attr("data-showIndex");
 		$("#showIndex").val(showIndex);
 		$("#originalShowIndex").val(showIndex);
 		$("#productIdForEdit").val(productIdForEdit);
 		$("#productTableId").val(productTableId);
 		
 		//查询该产品已经关联的可用分类信息
 		//所有复选框带选 所有排序  初始化
 		$("input[type='checkbox'][name='productCate']").attr("checked",false);
 		$("input[id^='Index_'][name^='Index_']").val('').attr("readonly",true);
		$.ajax({
			   type: "POST",
			   url: "rest/cim/cimproduct/queryProductInfoCateList",
			   data: {productId:productIdForEdit},
			   async:false,
			   success: function(cimProductInfoCateList){
				   for(var index in  cimProductInfoCateList){
					   var cateId = cimProductInfoCateList[index].cateId;
					   var sort = cimProductInfoCateList[index].sort;
					   $("input[type='checkbox'][name='productCate'][value='"+cateId+"']").trigger("click");
					   $("#Index_"+cateId).val(sort);
				   }
			   },
			   error: function (XMLHttpRequest, textStatus, errorThrown) {
				   $("#productEditError").html('查询产品分类信息异常[不影响产品编辑提交]');
			}
		});
		
 		$("#productEditModal").modal('show');
    });
    
	//设置产品分类按钮点击
	$("input[type='checkbox'][name='productCate']").bind("click",function(event){
		var value = $(event.target).val();
		if($(event.target).is(':checked')){
			$("#Index_"+ value).val("0").attr("readonly",false);
		} else {
			$("#Index_"+ value).val("").attr("readonly",true);
		}
	});
	
	//提交产品编辑表单
	$("#productEditSave").on('click',function(){
		var productId = $('#productIdForEdit').val();
		//表单校验
		var params = {};
		params["showIndex"] = $('#showIndex').val();
		params["productTableId"] = $('#productTableId').val();
		params["productIdForEdit"] = productId;
		params["originalShowIndex"] = $('#originalShowIndex').val();
        var cateListArray = new Array();
        $("#productEditForm input[type='checkbox'][name='productCate']:checked").each(function(i){
        	var cateId = $(this).val();
        	var cateDescription = $(this).attr("data");
        	var sort = $("#Index_"+cateId).val();
        	if(sort == "" || sort == null){
        		 $("#productEditError").html(cateDescription+'排序不能为空');
        	} else { 		
        		cateListArray.push({cateId:cateId,sort:sort,productId:productId});
        	}
        });
        params["cateListArray"] = cateListArray;
        
		$.ajax({
			   type: "POST",
			   url: "rest/cim/cimproduct/productEdit",
			   contentType : 'application/json;charset=utf-8',
			   data: JSON.stringify(params),
			   success: function(msg){
				   if(msg == 'success'){
					   $("#productEditModal").modal('hide');
					   $("a.J_search").trigger('click');
				   } else {
					   $("#productEditError").html('提交产品编辑信息失败');
				   }
			   },
			   error: function (XMLHttpRequest, textStatus, errorThrown) {
				   $("#productEditError").html('提交产品编辑信息异常');
			}
		});
	})
	
	//点击产品名称查看产品详情
    $("#J-newslist").on("click",'.J_productDetail',function(event){
  	   var productTableId = $(event.target).attr("data-id");
  	   var productId = $(event.target).attr("data-productId");
		$.ajax({
			   type: "POST",
			   url: "rest/cim/cimproduct/productDetail",
			   data: {"productTableId":productTableId,"productId":productId},
			   success: function(jsonData){
				   if(jsonData){
					   for(var item in jsonData){
//						   console.log("item:	"+item+"	DATA:	"+jsonData[item]);
						   if(item == 'orgNumber'){
							   $("#orgNumberText").html(jsonData[item]);
						   } else if(item != 'productType' && item != 'status' && item != 'auditStatus'){						   
							   $("#"+item).html(jsonData[item]);  
						   }
						 }
				   } else {
					   $("#productDetailError").html('查看产品详情信息失败');
				   }
			   },
			   error: function (XMLHttpRequest, textStatus, errorThrown) {
				   $("#productDetailError").html('查看产品详情信息异常');
			}
		});
  		$("#productDetailModal").modal('show');
     });
	
	//产品描述按钮点击
	$("#showDesc").click(showDesc);
	
	//产品复选框选择事件
	$("#example-select-all").change(function(){
		if($("#example-select-all").is(":checked")){
			$("input[type='checkbox'][name='tableId']").attr("checked","checked");
		} else {
			$("input[type='checkbox'][name='tableId']").removeAttr("checked");
		}
	});
	
	//批量审核
   $("body").on("click",'#partAuditButton',function(event){	
		if(!$("input[type='checkbox'][name='tableId']").is(':checked')){
			bootbox.alert("请选中相应的产品");
		} else {
			$("#productAuditModal").modal('show');
		}
	});
   
   //批量审核保存
   $("body").on("click",'#productAuditSave',function(event){
	   var auditCode = $('input[type="radio"][name="productAuditCode"]:checked').val();
	   var checkboxValue = $("input[type='checkbox'][name='tableId']:checked").serialize();
		$.ajax({
			   type: "POST",
			   url: "rest/cim/cimproduct/productAudit",
			   data: "auditType=partAudit&auditCode="+auditCode+"&"+checkboxValue+"",
			   success: function(message){
				   if(message == "success"){
					   $("#productAuditModal").modal('hide');
					   $("#example-select-all").removeAttr("checked");
					   $("a.J_search").trigger('click');
				   } else {
					   $("#productAuditError").html("批量审核失败");	
				   }
			   },
			   error: function (XMLHttpRequest, textStatus, errorThrown) {
				   $("#productAuditError").html("批量审核异常");
			}
		});
	});
	
  //全部审核
  $("body").on("click",'#allAuditButton',function(event){	
	  bootbox.confirm('确定<span style="font-weight: bolder; font-size: 20px;">全部待审核产品</span>全部<span style="font-weight: bolder; font-size: 20px;">审核通过</span>吗?', function(result){
		  if(result){	  
			  $.ajax({
				  type: "POST",
				  url: "rest/cim/cimproduct/productAudit",
				  data: {"auditType":"allAudit","auditCode":"1","productTableIdList":""},
				  success: function(message){
					  if(message == "success"){
						  bootbox.alert("全部审核通过成功");
						  $("a.J_search").trigger('click');
					  } else {
						  bootbox.alert("全部审核失败");
					  }
				  },
				  error: function (XMLHttpRequest, textStatus, errorThrown) {
					  bootbox.alert("全部审核异常");
				  }
			  });
		  }
	  });
	});
  
  //机构产品标签
  $("body").on("click",'#orgTagsButton',function(event){	
		if($("#orgNumber option:selected").val() == "99"){
			bootbox.alert("请选中相应的机构");
		} else {
			$("#orgEditModal").modal('show');
		}
	});
  
  //提交机构标签表单
	$("#orgTagsEditSave").on('click',function(){
      var cateListArray = new Array();
      var params = {};
      $("#orgTagsEditForm input[type='checkbox'][name='productCate']:checked").each(function(i){
      	var cateId = $(this).val();     		
      	cateListArray.push({cateId:cateId});
      });
      params["cateListArray"] = cateListArray;
      params["orgNumber"] = $("#orgNumber option:selected").val();
      
		$.ajax({
			   type: "POST",
			   url: "rest/cim/cimproduct/orgTagsEdit",
			   contentType : 'application/json;charset=utf-8',
			   data: JSON.stringify(params),
			   success: function(msg){
				   if(msg == 'success'){
					   $("#orgEditModal").modal('hide');
				   } else {
					   $("#orgTagsEditError").html('机构产品标签编辑失败');
				   }
			   },
			   error: function (XMLHttpRequest, textStatus, errorThrown) {
				   $("#orgTagsEditError").html('机构产品标签编辑失败');
			}
		});
	})
});

/**
 * 截取字符串并转化成 超过部分转化成...
 * @param string
 */
function cutFormatString(string,length){
	if(string.trim().length > length){
		return string.substring(0,length)+'...';
	} else {
		return string;
	}
}

function trimNull(target){
	if(target == null || target == ""){
		target = "";
	}
	return target;
}

function showDesc() {
	if ($("#productDesc").css("display") == "none") {
		$("#productDesc").css("display", "inline");
		$("#sign").text("收起");
	} else {
		$("#productDesc").css("display", "none");
		$("#sign").text("展开");
	}
	;
}