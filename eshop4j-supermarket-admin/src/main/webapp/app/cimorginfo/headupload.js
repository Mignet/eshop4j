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
  var zimgUrl =  $("#imgServerUrl").val(); // http://preimage.tophlc.com/
  var btnCust = '';
  /* 
  var btnCust = '<button type="button" class="btn btn-default" title="Add picture tags" ' + 
  'onclick="alert(\'上传点击事件.\')">' +
  '<i class="glyphicon glyphicon-tag"></i>' +
  '</button>'; 
  
  btnCust = '<button type="button" class="btn btn-default fileinput-upload fileinput-upload-button" title="上传选中文件" ' + 
  'onclick="alert(\'上传点击事件.\')">' +
  '<i class="glyphicon glyphicon-upload"></i>' +
  '</button>'; 
  */
 // <a href="http://preimage.tophlc.com/" tabindex="500" title="上传选中文件" class="btn btn-default fileinput-upload fileinput-upload-button"><i class="glyphicon glyphicon-upload"></i><span class="">上传</span></a>
 // <button type="button" tabindex="500" title="清除选中文件" class="btn btn-default fileinput-remove fileinput-remove-button"><i class="glyphicon glyphicon-trash"></i>  <span class="">移除</span></button>
   var headupload = function (element, md5) {
   var defaultPreviewContent = [];
    if(md5){
    	//defaultPreviewContent.push('<img src="'+uploadUrl+'/'+options+'" class="file-preview-image" alt="Desert" title="Desert">');
    	defaultPreviewContent.push('<img src="'+zimgUrl+md5+'" alt="成员头像" title="头像上传" style="width:160px /><h6 class="text-muted">点击上传头像</h6>');
    	//'<img src="http://preimage.tophlc.com/e9cc38a97c6284d096d0cbbf8d0aafa7?f=png" alt="你的头像" title="头像上传" style="width:160px"><h6 class="text-muted">点击上传头像</h6>'
    }
    element.fileinput({
	    	language: 'zh', //设置语言
			uploadUrl: zimgUrl, //上传的地址
			enctype: 'multipart/form-data',
		    overwriteInitial: true, //覆盖最初的内容
		    showClose: true, //显示关闭(X)
		    showCaption: false, //隐藏标题
		    showBrowse: false, //隐藏浏览按钮
		    showUpload: false, //隐藏上传按钮
		    showRemove: false, //隐藏移除按钮
		    browseOnZoneClick: true, //点击头像上传
		    maxFileSize: 100,//单位为kb 如果为0表示不限制文件大小
		    //removeLabel: '',
		    //removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
		    //removeTitle: '移除',
		    elErrorContainer: '#' + element.attr("data-errors"), //错误提示
		    msgErrorClass: 'alert alert-block alert-danger',
		    defaultPreviewContent: defaultPreviewContent,
		    layoutTemplates: {main2: '{preview} ' +  btnCust + ' {remove} {browse}'},
		    allowedFileExtensions: ["jpg", "png"],
		    ajaxSettings:{
	               type: 'post',
	               dataType: 'html',
	               async: false, //发送同步请求
	               cache: false,
	               contentType: false,
	               processData: false,
	               success: function(data){
	            	   if(data.indexOf("MD5") != -1){
	       			 		showTips("上传成功");
	       			 		$("#"+element.attr("data-validateerrors")+"-error").remove(); //移除jqueryValidate span错误提示标签
	       			 		var result =  data.substring(data.indexOf("MD5:")+4,data.indexOf(",")); //"MD5:"的下标 会返回 首字母M的下标
	       			 		$('#' + element.attr("data-validateerrors")).val(result); //保存头像到隐藏域中
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


  $(function () {
    var $uploadImgs = $('body').find('.uploadHead');
    if ($uploadImgs) {
      $uploadImgs.each(function(index, el) {
        var $el = $(el);
        headupload($el,$el.attr('data-md5'));
      });
    }
  })

}(window.jQuery);