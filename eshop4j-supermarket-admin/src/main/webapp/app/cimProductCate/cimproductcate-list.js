$(function(){
	//添加产品分类
   $("body").on("click",'#productCateAddButton',function(event){
	   //隐藏产品编辑保存按钮,展示产品添加保存按钮
	   var imgServerUrl= $("#imgServerUrl").val();
	   initParameters();
	   $("#productCateEditSave").hide();
	   $("#productCateAddSave").show();
	   //图片插件初始化
	   imguploadFct(imgServerUrl,$("#cateLogoInvestor"),false);
	   imguploadFct(imgServerUrl,$("#cateLogoChannel"),false);
	   $("#productCateEditModal").modal('show');
	});
   
	//产品分类编辑按钮
   $("#J-newslist").on("click",'.J_productCateEdit',function(event){
   	  //点击到图标上面  则不响应
      if($(event.target).context.localName == 'i'){
   		return false;
       }
	   var cateId = $(event.target).attr("data-id");
	   $("#cateId").val(cateId);
	   
	   initParameters();
	   
	   //查询产品分类信息
		$.ajax({
			   type: "POST",
			   url: "rest/cim/cimproductcate/queryProductCate",
			   data: {cateId:cateId},
			   async:false,
			   success: function(jsonData){
				   for(var item in jsonData){
//					   console.log("item:	"+item+"	DATA:	"+jsonData[item]);
					   if(item == 'majorRecommendationPlatform'){
						   $("#majorRecommendationPlatform option[value='"+jsonData[item]+"']").attr("selected", true);
					   } else if(item == 'cateName' || item == 'orderNum' || item == 'description' || item == 'urlLink' || item == 'cateDeclare'){						   
						   $("#"+item).val(jsonData[item]);  
					   } else if(item == 'ifShow'|| item == 'ispublic'|| item == 'disabled'){
						   $('input[type="radio"][name="'+item+'"][value="'+jsonData[item]+'"]').attr("checked","checked");
					   } else if(item == 'cateLogoInvestor' || item == 'cateLogoChannel'){
//						   console.log("item:	"+item+"	DATA:	"+jsonData[item]);
						   imguploadFct($("#imgServerUrl").val(),$("#"+item),jsonData[item]);
					   }
					 }
			   },
			   error: function (XMLHttpRequest, textStatus, errorThrown) {
				   $("#productEditError").html('查询产品分类信息异常');
			}
		});
	   
	   //隐藏产品添加保存按钮,展示产品编辑保存按钮
	   $("#productCateAddSave").hide();
	   $("#productCateEditSave").show();
	   $("#productCateEditModal").modal('show');
   });
   
	//编辑产品分类提交表单
	$("#productCateEditSave").on('click',function(){
		$.ajax({
			   type: "POST",
			   url: "rest/cim/cimproductcate/updateProductcate",
			   data: $("#orgTagsEditForm").serialize(),
			   success: function(msg){
				   if(msg == 'success'){
					   $("#productCateEditModal").modal('hide');
					   $("a.J_search").trigger('click');
				   } else {
					   $("#productCateEditError").html('编辑产品分类信息失败');
				   }
			   },
			   error: function (XMLHttpRequest, textStatus, errorThrown) {
				   $("#productCateEditError").html('编辑产品分类信息异常');
			}
		});
	})
   
	//添加产品分类提交表单
	$("#productCateAddSave").on('click',function(){
		$.ajax({
			   type: "POST",
			   url: "rest/cim/cimproductcate/addProductcate",
			   data: $("#orgTagsEditForm").serialize(),
			   success: function(msg){
				   if(msg == 'success'){
					   $("#productCateEditModal").modal('hide');
					   $("a.J_search").trigger('click');
				   } else {
					   $("#productCateEditError").html('添加产品分类信息失败');
				   }
			   },
			   error: function (XMLHttpRequest, textStatus, errorThrown) {
				   $("#productCateEditError").html('添加产品分类信息异常');
			}
		});
	})
	
	
});

/**
 *初始化参数
 */
function initParameters(){
	$("#orgTagsEditForm")[0].reset();
	
	//初始化选择框
	$("#majorRecommendationPlatform option[value='99']").attr("selected", true);
	
	//初始化上传插件
	var cateLogoInvestor = "";
	cateLogoInvestor += '<input class="uploadImg"  type="file" id="cateLogoInvestor" accept="image/*"/>';
	cateLogoInvestor += '<input name="cateLogoInvestor" type="hidden" required="required"/>';
	$("#uploadImgCateLogoInvestor").html(cateLogoInvestor);
	
	var cateLogoChannel = "";
	cateLogoChannel += '<input class="uploadImg"  type="file" id="cateLogoChannel" accept="image/*"/>';
	cateLogoChannel += '<input name="cateLogoChannel" type="hidden" required="required"/>';
	$("#uploadImgCateLogoChannel").html(cateLogoChannel);
}

/**
 * 图片上传
 * @param uploadUrl   图片服务器路径
 * @param element 	  图片上传对象
 * @param md5		  图片md5
 */
function imguploadFct(uploadUrl,element,md5) {
    var initialPreview = [];
    if(md5){
      initialPreview.push('<img src="'+uploadUrl+md5+'?f=png" class="file-preview-image" alt="图片" title="上传图片" style="width: 100%; height: 160px;"/>');
    }
    console.log(initialPreview);
    element.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //上传的地址
            showPreview : true,
            allowedFileExtensions : ['jpg', 'png'],//接收的文件后缀
            showUpload: false, //是否显示上传按钮
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式            
            enctype: 'multipart/form-data',
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            initialPreview:initialPreview,
            buttonLabelClass: "", //去除按钮样式 hidden-xs
            initialPreviewFileType: 'image', //预览文件默认为图片
            initialPreviewConfig: [
                                   {showDelete: false} //预览状态下不显示删除按钮
                               ],
            previewZoomSettings: { 
            	image: {width: "auto", height: "auto"}, //预览图片设置
            	//image: {width: "auto", height: "100%"}, //预览图片设置
            	//object: {width: "213px", height: "160px"}
            },
            previewZoomButtonTitles: { //预览图片 按钮标题
            	prev: '上一张',
                next: '下一张',
                toggleheader: '隐藏标题',
                fullscreen: '切换全屏',
                borderless: '展开图片',
                close: '关闭'
            },
           
            layoutTemplates: { 
            	modalMain: '<div id="kvFileinputModal" class="file-zoom-dialog modal fade" tabindex="-1" aria-labelledby="kvFileinputModalLabel"></div>',
                modal: '<div class="modal-dialog modal-lg" role="document">\n' +
                    '  <div class="modal-content" style="width: 100%;overflow:auto;">\n' +
                    '    <div class="modal-header">\n' +
                    '      <div class="kv-zoom-actions pull-right">{toggleheader}{fullscreen}{borderless}{close}</div>\n' +
                    '      <h3 class="modal-title">{heading} <small><span class="kv-zoom-title"></span></small></h3>\n' +
                    '    </div>\n' +
                    '    <div class="modal-body">\n' +
                    '      <div class="floating-buttons"></div>\n' +
                    '      <div class="kv-zoom-body file-zoom-content"></div>\n' + '{prev} {next}\n' +
                    '    </div>\n' +
                    '  </div>\n' +
                    '</div>\n'
            },
            ajaxSettings:{
            	headers: {
                },
               type: 'post',
               dataType: 'html',
               //dataType: 'JSONP',
               async: false, //发送同步请求
               cache: false,
               contentType: false,
               processData: false,
               success: function(data){
            	   if(data.indexOf("MD5") != -1){
       			 		showTips("上传成功");
       			 		$("#"+element.attr("id")).closest('.form-group').removeClass('has-error'); //删除验证错误样式
       			 		$("#"+element.attr("id")+"-error").remove(); //移除jqueryValidate span错误提示标签
						var result =  data.substring(data.indexOf("MD5:")+4,data.indexOf(",")); //"MD5:"的下标 会返回 首字母M的下标 
						$('input[type=hidden][name=' + element.attr('id') + ']').val(result); //保存在隐藏域中 等待提交
					}
					else{
						layer.alert('上传失败，非图片！',{icon: 5,shift: 4,title:'提示'});
					}
               },
               error: function(XmlHttpRequest,textStatus,errorThrown){
                 console.log(XmlHttpRequest.status); //0
                 console.log(textStatus); //error
                 showError(textStatus+"上传失败！");
               }
            }
        });
  }