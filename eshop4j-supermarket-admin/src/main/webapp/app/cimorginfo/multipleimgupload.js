/*!
 * bootstrap-fileinput v4.3.5
 * http://plugins.krajee.com/file-input
 *
 * Author: Kartik Visweswaran
 * Copyright: 2014 - 2016, Kartik Visweswaran, Krajee.com
 *
 * Licensed under the BSD 3-Clause
 * https://github.com/kartik-v/bootstrap-fileinput/blob/master/LICENSE.md
 */

!function ($) {

  "use strict"; // jshint ;_
  var uploadUrl =  $("#imgServerUrl").val(); // http://preimage.tophlc.com/
  var orgEnvironmentArray = [];//移动端-办公环境图
  var orgPaperPictureArray = []; //移动端-其他资格证
  var orgHonorPictureArray = []; //移动端-荣誉证书
  
  var orgPcEnvironmentArray = [];//PC端-办公环境图
  var orgPcPaperPictureArray = []; //PC端-其他资格证
  var orgPcHonorPictureArray = []; //PC端-荣誉证书
  
  /**
   * 数组去重
   */
  var unique = function(arr){
  	var n = {},r=[]; //n为hash表，r为临时数组
  	for(var i = 0; i < arr.length; i++) //遍历当前数组
  	{
  		if (!n[arr[i]]) //如果hash表中没有当前项
  		{
  			n[arr[i]] = true; //存入hash表
  			r.push(arr[i]); //把当前数组的当前项push到临时数组里面
  		}
  	}
  	return r;
  }
  
  var imgupload = function (element, md5) {
    var initialPreview = [];
    var initialPreviewConfig = [];
    if(md5){
    	//1-2ef1588975b6988f1248d2a23cced020,2-8920d27f73a538c2992296e7cb8241ff,
      md5 = md5.substring(0,md5.lastIndexOf(",")); //去掉最后一个逗号
      var images = md5.split(",");
      
      /*
      if(element.attr('id') == "orgEnvironmentPicture"){ //办公环境图
    	 // orgEnvironmentArray = orgEnvironmentArray.concat(images); //合并数组
		}else if(element.attr('id') == "orgPaperPicture"){ //其他资格证
		 // orgPaperPictureArray = orgPaperPictureArray.concat(images); //合并数组
		}else if(element.attr('id') == "orgHonorPicture"){ //荣誉证书
		 // orgHonorPictureArray = orgHonorPictureArray.concat(images); //合并数组
		}
		*/
      for(var img in images){
    	 // console.info("打印img");
    	 // console.info(images[img]);
    	  //1-2ef1588975b6988f1248d2a23cced020
    	  img = images[img].split("-");
    	  initialPreviewConfig.push({'key': img[0]}); //{key: 21},
    	  initialPreview.push('<img src="'+uploadUrl+img[1]+'" class="file-preview-image" alt="图片" title="上传图片" style="width:auto;height:160px;"/>');
      }
      
    }
    element.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //上传的地址
            allowedFileExtensions : ['jpg','png'],//接收的文件后缀
            showUpload: false, //是否显示上传按钮
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式 
            enctype: 'multipart/form-data',
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            initialPreview:initialPreview,
            maxFileSize: 100,//单位为kb 如果为0表示不限制文件大小
            buttonLabelClass: "", //去除按钮样式 hidden-xs
            initialPreviewFileType: 'image', //预览文件默认为图片
            overwriteInitial: false, //上传时不覆盖原图片/文件(适用于多图片/文件上传),默认true
            initialPreviewConfig: initialPreviewConfig,
            deleteUrl: "/rest/cim/cimorginfo/deletePicture", //删除操作
            /* 
            initialPreviewConfig: [
                   	            {key: 21},
                   	            {key: 22},
                   	            {key: 3}
                   	        ],      	        
            initialPreviewConfig: [
                                   {showDelete: false} //预览状态下不显示删除按钮
                               ],*/
            previewZoomSettings: { 
            	image: {width: "auto", height: "auto"}//预览图片设置
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
            	/*
            	actions: '<div class="file-actions">\n' +
            	'    <div class="file-footer-buttons">\n' +
            	'        {upload} {delete} {zoom} {other}' +
            	'    </div>\n' +
            	'    {drag}\n' +
            	'    <div class="file-upload-indicator" title="{indicatorTitle}"><i class="glyphicon glyphicon-ok-sign file-icon-large text-success"></i></div>\n' +
            	'    <div class="clearfix"></div>\n' +
            	'</div>',
            	*/
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
                /*    
	            progress: '<div class="progress">\n' +
	            '    <div class="progress-bar progress-bar-success progress-bar-striped text-center" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%;">\n' +
	            '        100%\n' +
	            '     </div>\n' +
	            '</div>'
	            */
               // progress: ""
            },
            /*
            initialPreviewAsData: true, //不上传原图，只上传预览状态下的图片。默认false
            overwriteInitial: false, //上传时不覆盖原图片/文件(适用于多图片/文件上传),默认true
            uploadAsync: false,
               
            
            allowedPreviewTypes : [ 'image' ],
            allowedFileTypes: ['image'],
            dropZoneEnabled: false,//是否显示拖拽区域
            minImageWidth: 50, //图片的最小宽度
            minImageHeight: 50,//图片的最小高度
            maxImageWidth: 1000,//图片的最大宽度
            maxImageHeight: 1000,//图片的最大高度
            maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
            minFileCount: 0,
            maxFileCount: 10, //表示允许同时上传的最大文件个数
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            */
            ajaxSettings:{
            	headers: {
            		//"Access-Control-Allow-Origin":"120.76.97.142",
                    //"X-Requested-With":"XMLHttpRequest"
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
						//result = MD5:75ea5c5942f061c89319d33586828a2c,
						var result =  data.substring(data.indexOf("MD5:")+4,data.indexOf(",")); //"MD5:"的下标 会返回 首字母M的下标 
						if(element.attr('id') == "orgEnvironmentPicture"){ //移动端-办公环境图
							orgEnvironmentArray.push(result);
							$('input[type=hidden][name=' + element.attr('id') + ']').val(unique(orgEnvironmentArray)); //去重保存在隐藏域中 等待提交
						}else if(element.attr('id') == "orgPaperPicture"){ //移动端-其他资格证
							orgPaperPictureArray.push(result);
							$('input[type=hidden][name=' + element.attr('id') + ']').val(unique(orgPaperPictureArray)); //去重保存在隐藏域中 等待提交
						}else if(element.attr('id') == "orgHonorPicture"){ //移动端-荣誉证书
							orgHonorPictureArray.push(result);
							$('input[type=hidden][name=' + element.attr('id') + ']').val(unique(orgHonorPictureArray)); //去重保存在隐藏域中 等待提交
						}else if(element.attr('id') == "orgPcEnvironmentPicture"){ //PC端-办公环境图
							orgPcEnvironmentArray.push(result);
							$('input[type=hidden][name=' + element.attr('id') + ']').val(unique(orgPcEnvironmentArray)); //去重保存在隐藏域中 等待提交
						}else if(element.attr('id') == "orgPcPaperPicture"){ //PC端-其他资格证
							orgPcPaperPictureArray.push(result);
							$('input[type=hidden][name=' + element.attr('id') + ']').val(unique(orgPcPaperPictureArray)); //去重保存在隐藏域中 等待提交
						}else if(element.attr('id') == "orgPcHonorPicture"){ //PC端-荣誉证书
							orgPcHonorPictureArray.push(result);
							$('input[type=hidden][name=' + element.attr('id') + ']').val(unique(orgPcHonorPictureArray)); //去重保存在隐藏域中 等待提交
						}
						
					}
					else{
						layer.alert('上传失败，非图片！',{icon: 5,shift: 4,title:'提示'});
					}
               },
               error: function(XmlHttpRequest,textStatus,errorThrown){
                 //console.log(XmlHttpRequest); //{readyState: 0, responseText: "", responseJSON: null, status: 0, statusText: "error"}
                 console.log(XmlHttpRequest.status); //0
                 console.log(textStatus); //error
                 showError(textStatus+"上传失败！");
               }
            }
        });
  }


  $(function () {
    var $uploadImgs = $('body').find('.uploadMultipleImg');
    if ($uploadImgs) {
      $uploadImgs.each(function(index, el) {
        var $el = $(el);
        imgupload($el,$el.attr('data-md5'));
      });
    }
  })

}(window.jQuery);