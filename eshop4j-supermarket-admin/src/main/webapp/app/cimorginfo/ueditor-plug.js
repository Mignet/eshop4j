/*
 * Ueditor
 */

!function ($) {

  "use strict"; // jshint ;_
  var imgUploadUrl =  $("#imgServerUrl").val(); // http://preimage.tophlc.com/
  
  /**
   * Editor插件初始化
   */
  var initUeditor = function (id) {
	  var ue = UE.getEditor(id,{
		  	serverUrl:"rest/cim/cimorginfo/ueditor_config",  // 服务器统一请求接口路径
		  	customDomain:true, //若实例化编辑器的页面手动修改的domain，此处需要设置为true
			zimg:imgUploadUrl, //图片上传处理地址
			zIndex:9996, //编辑器层级的基数,默认是900
			autoHeightEnabled:false, //是否自动长高,默认true
			//initialFrameWidth:1000,  //初始化编辑器宽度,默认1000
			initialFrameHeight:500, //初始化编辑器高度,默认320
			toolbars: [[ //工具栏上的所有的功能按钮和下拉框，可以在new编辑器的实例时选择自己需要的从新定义
				'fullscreen', 'source', '|', 'undo', 'redo', '|',
				'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
				'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
				'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
				'directionalityltr', 'directionalityrtl', 'indent', '|',
				'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
				'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
				  'scrawl', 'insertvideo', 'music', 'attachment', 'map', 'gmap', 'insertframe', 'insertcode', 'pagebreak', 'template', 'background', '|',
				'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
				'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
				'print', 'preview', 'searchreplace', 'drafts'
			]]
		});
  }
  
 
  

  /**
   * 初始化ueditor富文本插件
   */
  $(function () {
    var $ueditor = $('body').find('.ueditorPlug');
    if ($ueditor) {
    	$ueditor.each(function(index, element) {
    		initUeditor($(element).attr('id'));
      });
    }
  })

}(window.jQuery);