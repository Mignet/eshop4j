<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    request.setAttribute("ctx", request.getContextPath());
%>
<script type="text/javascript">
    window.UEDITOR_HOME_URL = '${ctx}/assets/plugins/ueditor/';
</script>
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<!-- moment -->
<script type="text/javascript" src="assets/plugins/bootstrap-daterangepicker/moment.min.js"  ></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.zimg.js"></script>
<!-- Editor -->
<script type="text/javascript" src="assets/plugins/data-tables/extensions/Editor/js/dataTables.editor.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Editor/css/editor.dataTables.min.css"  />

<script type="text/javascript" src="assets/plugins/data-tables/extensions/Buttons/js/dataTables.buttons.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Buttons/css/buttons.dataTables.min.css"  />

<script type="text/javascript" src="assets/plugins/data-tables/extensions/Select/js/dataTables.select.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Select/css/select.dataTables.min.css"  />



<style type="text/css">
    div.DTED_Lightbox_Background{ z-index: 777777;}
    div.DTED_Lightbox_Wrapper{ z-index:777778;}
    div.DTE_Body div.DTE_Body_Content div.DTE_Field{ padding: 5px 20px;}
    div.DTE_Body div.DTE_Body_Content div.DTE_Field>label{ display: block; clear: both; float: none; padding: 5px 0;}
    div.DTE_Body div.DTE_Body_Content div.DTE_Field>div.DTE_Field_Input{
        float: none; width: 100%;
    }
    .editor-datetime{ z-index: 777779 !important;}
</style>

<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>
<table id="dtable" class="table table-bordered"  cellspacing="0" width="100%">
        <thead>
            <tr>
            <th>序号</th>
            <th>消息标题</th>
            <th>链接</th>
            <th>状态</th>
            <th>应用类别</th>
            <th>应用平台</th>            
            <th>生效时间</th>
            <th>创建时间</th>
            <th>修改时间</th>
            </tr>
        </thead>
 
    </table>
<div id="hidue" style="display: none;">
    <script id="container1" name="content" type="text/plain"></script>
</div>
<script type="text/javascript">
var zimgUrl = "${img_server}";
    var config = {serverUrl:"rest/cms/msg/ueditor_config",customDomain:true,
    	zimg:zimgUrl,
        zIndex:999999,
        autoHeightEnabled:false,
        initialFrameHeight:300,
        initialFrameWidth:680,
        enableAutoSave:false,
        wordCount:false,
        toolbars: [[
            /* 'fullscreen', 'source', '|', 'undo', 'redo', '|',
            'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
            'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
            'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
            'directionalityltr', 'directionalityrtl', 'indent', '|',
            'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
            'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
            'simpleupload', 'insertimage','scrawl', 'insertvideo', 'music', 'attachment', 'map', 'gmap', 'insertframe', 'insertcode', 'pagebreak', 'template', 'background', '|',
            'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
            'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
            'print', 'preview', 'searchreplace', 'drafts' */
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
    };
    var uex = UE.getEditor("container1",config);

    ;(function ($, DataTable) {

        if ( ! DataTable.ext.editorFields ) {
            DataTable.ext.editorFields = {};
        }

        var Editor = DataTable.Editor;
        var _fieldTypes = DataTable.ext.editorFields;

        _fieldTypes.todo = {
            create: function ( conf ) {
                var that = this;

                conf._enabled = true;

                // Create the elements to use for the input
                conf._input = document.createElement("div");
                var tex = document.createElement("div");
                var tare = document.createElement("textarea");
                tare.style.display = "none";
                tare.setAttribute("class","editor-content");
                tex.setAttribute("class","editor-body");
                conf._input.setAttribute("class","J_ueditor");
                conf._input.appendChild(tex);
                conf._input.appendChild(tare);
                return conf._input;
            },

            get: function ( conf ) {
                console.log($(conf._input).find(".editor-content").val());
                return $(conf._input).find(".editor-content").val();
            },

            set: function ( conf, val ) {
                $(conf._input).find(".editor-content").text(val);
            },

            enable: function ( conf ) {
                conf._enabled = true;
            },

            disable: function ( conf ) {
                conf._enabled = false;
            }
        };

    })(jQuery, jQuery.fn.dataTable);


</script>
<script type="text/javascript" src="app/msg/msg-list.js"></script>


