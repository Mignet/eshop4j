<%--
  Created by IntelliJ IDEA.
  User: Mignet
  Date: 2016/6/24
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
//String scheme = request.getScheme();//总是http
String port = request.getServerPort()==80?"":":" +String.valueOf(request.getServerPort());
String basePath = "//" + request.getServerName() +  port + path + "/";
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js" style="background-color: #ffffff;">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8" />
    <title>日志查询器-空格键暂停与播放日志</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <meta name="MobileOptimized" content="320">

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN THEME STYLES -->
    <link href="assets/css/style-metronic.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/style.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/style-responsive.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/pages/tasks.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color" />
    <link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
    <!-- END THEME STYLES -->
    <!-- BEGIN PLUGIN STYLES -->
    <link href="assets/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />
    <!-- END PLUGIN STYLES -->
    <link rel="shortcut icon" href="app/img/favicon.ico" />

    <script src="assets/plugins/jquery/jquery-1.11.1.js" type="text/javascript"></script>
    <style type="text/css">
        html,body{ margin: 0!important; padding: 0!important; width: 100%; height: 100%; overflow: hidden;background-color: #2B2B2B !important;}
        .bg1{background-color: #2B2B2B;}
        .bg2{background-color: #515658;}
        .bg3{background-color: #3C3F41;}
        .ling1{background-color: #282828;}
        .log-wrapper{}
        .log-head{ height: 34px; background-color: #3C3F41;}
        .log-bank{ height: 5px; clear: both;border: 1px solid #282828; background-color: #515658;}
        .log-content{ position: absolute; top: 39px; left: 0; right: 0; bottom: 0; margin: auto; padding: 15px;  color: #BBBBBB; cursor: pointer; overflow-y: auto; z-index: 888;}
        .log-content-o{ z-index: 777; text-align: center; display: table; vertical-align: middle; width: 100%; opacity: 0.325; }
        .log-content-o i{font-size:5em; font-weight: normal;}
        /*定义滚动条高宽及背景 高宽分别对应横竖滚动条的尺寸*/
        ::-webkit-scrollbar
        {
            width: 5px;
            height: 5px;
            background-color: #515658;
        }

        /*定义滚动条轨道 内阴影+圆角*/
        ::-webkit-scrollbar-track
        {
            border-radius: 3px;
            background-color: #2B2B2B;
        }

        /*定义滑块 内阴影+圆角*/
        ::-webkit-scrollbar-thumb
        {
            border-radius: 3px;
            -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
            background-color: #555;
        }
    </style>
</head>
<!-- END HEAD -->
<body>
<header class="log-head">
</header>
<div class="log-bank"></div>
<div id="logs" class="log-content" data-time="true">

</div>
<div id="logs-show" class="log-content log-content-o"><div style="display: table-cell; vertical-align: middle; text-align: center; width: 100%;"><i class="fa fa-play" style="display: none;"></i><i class="fa fa-pause"></i></div></div>
<script type="text/javascript">
    $(document).ready(function () {
        window.linkwee = {logger:null};
        var loadLog = function () {
            $.get("rest/log/data",{seek:50000},function (result) {
                $("#logs").html(result.content);
                $("#logs").scrollTop($("#logs")[0].scrollHeight);
            },"json");
        }
        var startLoad = function () {
            return setInterval(function () {
                loadLog();
            },3000);
        }

        $(document).keyup(function (e) {
            var keycode = e.which;
            if(keycode == 32){
                var tim =  $("#logs").attr("data-time")=="false"?false:true;
                if(!tim){ // 开始
                    linkwee.logger =startLoad();
                    $("#logs").attr("data-time","true");

                    $("#logs-show .fa").hide();
                    $("#logs-show  .fa-pause").fadeIn();


                }
                else{ //暂停
                    clearInterval(linkwee.logger);
                    $("#logs").attr("data-time","false");
                    $("#logs-show  .fa").hide();
                    $("#logs-show  .fa-play").fadeIn();

                }
            }
        });
        $("#logs").click(function () {

        });
        loadLog();
       linkwee.logger =startLoad();
    });
</script>
</body>
</html>
