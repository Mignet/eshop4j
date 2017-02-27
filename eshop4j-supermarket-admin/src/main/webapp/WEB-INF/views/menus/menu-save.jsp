<%--
  Created by IntelliJ IDEA.
  User: Mignet
  Date: 2016/6/22
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="menu-edit" style="padding: 10px 20px;">
    <div class="container-fluid">
        <div class="row">
            <form id="xmenuForm" action="rest/menus/save_json" method="post">

                <div class="form-group">
                    <label>上级菜单</label>
                    <select  name="parentId" class="form-control">
                        <option value="0">请选择上级菜单</option>
                        <c:if test="${!empty parentList}">
                            <c:forEach var="item" items="${parentList }"  varStatus="dn">
                                <option <c:if test="${(menu.parentId eq item.id)  }"> selected="selected"</c:if> value="${item.id }">${item.menuName }</option>
                            </c:forEach>
                        </c:if>
                    </select>

                </div>

                <div class="form-group">
                    <label>菜单名称</label>
                    <input name="menuName" type="text" class="form-control" value="<c:out value="${menu.menuName}" default="" />"  placeholder="请输入菜单名称" autocomplete="off" />
                </div>

                <div class="form-group">
                    <label>菜单图标样式</label>
                    <input name="menuIcon" id="JmenusIcon" type="text" class="form-control" value="<c:out value="${menu.menuIcon}" default="" />"  placeholder="点击这里选择菜单图标样式" title="点击这里选择菜单图标样式" readonly="readonly" autocomplete="off" />
                </div>

                <div class="form-group">
                    <label>菜单地址</label>
                    <input name="menuUrl" type="text" class="form-control" value="<c:out value="${menu.menuUrl}" default="" />"  placeholder="请输入菜单地址"  autocomplete="off"/>
                </div>

                <div class="form-group">
                    <label>权限码</label>
                    <input name="permissionSign" title="点击这里选择权限码" id="JpermissionSign" type="text" class="form-control" value="<c:out value="${menu.permissionSign}" default="" />"  placeholder="点击这里选择权限码" autocomplete="off" readonly="readonly" />
                </div>

                <div class="form-group">
                    <label>显示顺序</label>
                    <input name="sort" class="form-control"  value="<c:out value="${menu.sort}" default="0" />" autocomplete="off" />
                </div>

                <div class="form-group">
                    <label>是否禁用</label>
                    <select id="isDisable" class="form-control" name="isDisable">
                        <option value="0" <c:if test="${menu.isDisable eq 0}">selected="selected"</c:if>>启用</option>
                        <option value="1" <c:if test="${menu.isDisable eq 1}">selected="selected"</c:if>>禁用</option>
                    </select>
                </div>

                <input type="hidden" name="id" value="<c:out value="${menu.id}" default=""></c:out>"/>
            </form>
        </div>
    </div>
    <script type="text/javascript">
        var icons = ["fa-adjust","fa-anchor","fa-archive","fa-area-chart","fa-arrows","fa-arrows-h","fa-arrows-v","fa-asterisk","fa-at","fa-automobile","fa-balance-scale","fa-ban","fa-bank","fa-bar-chart","fa-bar-chart-o","fa-barcode","fa-bars","fa-battery-0","fa-battery-1","fa-battery-2","fa-battery-3","fa-battery-4","fa-battery-empty","fa-battery-full","fa-battery-half","fa-battery-quarter","fa-battery-three-quarters","fa-bed","fa-beer","fa-bell","fa-bell-o","fa-bell-slash","fa-bell-slash-o","fa-bicycle","fa-binoculars","fa-birthday-cake","fa-bluetooth","fa-bluetooth-b","fa-bolt","fa-bomb","fa-book","fa-bookmark","fa-bookmark-o","fa-briefcase","fa-bug","fa-building","fa-building-o","fa-bullhorn","fa-bullseye","fa-bus","fa-cab","fa-calculator","fa-calendar","fa-calendar-check-o","fa-calendar-minus-o","fa-calendar-o","fa-calendar-plus-o","fa-calendar-times-o","fa-camera","fa-camera-retro","fa-car","fa-caret-square-o-down","fa-caret-square-o-left","fa-caret-square-o-right","fa-caret-square-o-up","fa-cart-arrow-down","fa-cart-plus","fa-cc","fa-certificate","fa-check","fa-check-circle","fa-check-circle-o","fa-check-square","fa-check-square-o","fa-child","fa-circle","fa-circle-o","fa-circle-o-notch","fa-circle-thin","fa-clock-o","fa-clone","fa-close","fa-cloud","fa-cloud-download","fa-cloud-upload","fa-code","fa-code-fork","fa-coffee","fa-cog","fa-cogs","fa-comment","fa-comment-o","fa-commenting","fa-commenting-o","fa-comments","fa-comments-o","fa-compass","fa-copyright","fa-creative-commons","fa-credit-card","fa-credit-card-alt","fa-crop","fa-crosshairs","fa-cube","fa-cubes","fa-cutlery","fa-dashboard","fa-database","fa-desktop","fa-diamond","fa-dot-circle-o","fa-download","fa-edit","fa-ellipsis-h","fa-ellipsis-v","fa-envelope","fa-envelope-o","fa-envelope-square","fa-eraser","fa-exchange","fa-exclamation","fa-exclamation-circle","fa-exclamation-triangle","fa-external-link","fa-external-link-square","fa-eye","fa-eye-slash","fa-eyedropper","fa-fax","fa-feed","fa-female","fa-fighter-jet","fa-file-archive-o","fa-file-audio-o","fa-file-code-o","fa-file-excel-o","fa-file-image-o","fa-file-movie-o","fa-file-pdf-o","fa-file-photo-o","fa-file-picture-o","fa-file-powerpoint-o","fa-file-sound-o","fa-file-video-o","fa-file-word-o","fa-file-zip-o","fa-film","fa-filter","fa-fire","fa-fire-extinguisher","fa-flag","fa-flag-checkered","fa-flag-o","fa-flash","fa-flask","fa-folder","fa-folder-o","fa-folder-open","fa-folder-open-o","fa-frown-o","fa-futbol-o","fa-gamepad","fa-gavel","fa-gear","fa-gears","fa-gift","fa-glass","fa-globe","fa-graduation-cap","fa-group","fa-hand-grab-o","fa-hand-lizard-o","fa-hand-paper-o","fa-hand-peace-o","fa-hand-pointer-o","fa-hand-rock-o","fa-hand-scissors-o","fa-hand-spock-o","fa-hand-stop-o","fa-hashtag","fa-hdd-o","fa-headphones","fa-heart","fa-heart-o","fa-heartbeat","fa-history","fa-home","fa-hotel","fa-hourglass","fa-hourglass-1","fa-hourglass-2","fa-hourglass-3","fa-hourglass-end","fa-hourglass-half","fa-hourglass-o","fa-hourglass-start","fa-i-cursor","fa-image","fa-inbox","fa-industry","fa-info","fa-info-circle","fa-institution","fa-key","fa-keyboard-o","fa-language","fa-laptop","fa-leaf","fa-legal","fa-lemon-o","fa-level-down","fa-level-up","fa-life-bouy","fa-life-buoy","fa-life-ring","fa-life-saver","fa-lightbulb-o","fa-line-chart","fa-location-arrow","fa-lock","fa-magic","fa-magnet","fa-mail-forward","fa-mail-reply","fa-mail-reply-all","fa-male","fa-map","fa-map-marker","fa-map-o","fa-map-pin","fa-map-signs","fa-meh-o","fa-microphone","fa-microphone-slash","fa-minus","fa-minus-circle","fa-minus-square","fa-minus-square-o","fa-mobile","fa-mobile-phone","fa-money","fa-moon-o","fa-mortar-board","fa-motorcycle","fa-mouse-pointer","fa-music","fa-navicon","fa-newspaper-o","fa-object-group","fa-object-ungroup","fa-paint-brush","fa-paper-plane","fa-paper-plane-o","fa-paw","fa-pencil","fa-pencil-square","fa-pencil-square-o","fa-percent","fa-phone","fa-phone-square","fa-photo","fa-picture-o","fa-pie-chart","fa-plane","fa-plug","fa-plus","fa-plus-circle","fa-plus-square","fa-plus-square-o","fa-power-off","fa-print","fa-puzzle-piece","fa-qrcode","fa-question","fa-question-circle","fa-quote-left","fa-quote-right","fa-random","fa-recycle","fa-refresh","fa-registered","fa-remove","fa-reorder","fa-reply","fa-reply-all","fa-retweet","fa-road","fa-rocket","fa-rss","fa-rss-square","fa-search","fa-search-minus","fa-search-plus","fa-send","fa-send-o","fa-server","fa-share","fa-share-alt","fa-share-alt-square","fa-share-square","fa-share-square-o","fa-shield","fa-ship","fa-shopping-bag","fa-shopping-basket","fa-shopping-cart","fa-sign-in","fa-sign-out","fa-signal","fa-sitemap","fa-sliders","fa-smile-o","fa-soccer-ball-o","fa-sort","fa-sort-alpha-asc","fa-sort-alpha-desc","fa-sort-amount-asc","fa-sort-amount-desc","fa-sort-asc","fa-sort-desc","fa-sort-down","fa-sort-numeric-asc","fa-sort-numeric-desc","fa-sort-up","fa-space-shuttle","fa-spinner","fa-spoon","fa-square","fa-square-o","fa-star","fa-star-half","fa-star-half-empty","fa-star-half-full","fa-star-half-o","fa-star-o","fa-sticky-note","fa-sticky-note-o","fa-street-view","fa-suitcase","fa-sun-o","fa-support","fa-tablet","fa-tachometer","fa-tag","fa-tags","fa-tasks","fa-taxi","fa-television","fa-terminal","fa-thumb-tack","fa-thumbs-down","fa-thumbs-o-down","fa-thumbs-o-up","fa-thumbs-up","fa-ticket","fa-times","fa-times-circle","fa-times-circle-o","fa-tint","fa-toggle-down","fa-toggle-left","fa-toggle-off","fa-toggle-on","fa-toggle-right","fa-toggle-up","fa-trademark","fa-trash","fa-trash-o","fa-tree","fa-trophy","fa-truck","fa-tty","fa-tv","fa-umbrella","fa-university","fa-unlock","fa-unlock-alt","fa-unsorted","fa-upload","fa-user","fa-user-plus","fa-user-secret","fa-user-times","fa-users","fa-video-camera","fa-volume-down","fa-volume-off","fa-volume-up","fa-warning","fa-wheelchair","fa-wifi","fa-wrench"];
        $("#JpermissionSign").click(function () {

            $.get("/rest/permissions/json",{},function (result) {
                if($.isArray(result) && result.length>0){

                    var cateList = [];
                    for(var rows in result){
                        var has = false;
                        var rr = result[rows];
                        for(var itt in cateList){
                            if(cateList[itt].name == rr.permissionCategory){
                                has = true;
                                break;
                            }
                        }
                        if(!has){
                            cateList.push({code:rr.permissionSign,name:rr.permissionCategory});
                        }

                    }
                    var template  =  Handlebars.compile($("#T-permission").html());
                    var content = template({"cateList":cateList,"list":result});


                    bootbox.dialog({
                        message:content,
                        title:"请选择权限码",
                        closeButton:false,
                        buttons:{
                            Cancel: {
                                label: "取消",
                                className: "btn-default",
                                callback: function ($event) {
                                }
                            }
                            , OK:{
                                label: "确定",
                                className: "btn-primary",
                                callback: function ($event) {
                                    var permission_code = $($event.delegateTarget).find('input[type=radio][name=permission_code]:checked').val();
                                    if(typeof permission_code == "undefined"){
                                        bootbox.alert("请选择权限码");
                                        return false;
                                    }
                                    else{
                                        $("#JpermissionSign").val(permission_code);
                                    }
                                    return true;
                                }
                            }
                        }
                    });
                }
            },"json");


        });
        
        $("#JmenusIcon").click(function () {
            var template  =  Handlebars.compile($("#T-menuicon").html());
            var content = template({"list":icons});
            bootbox.dialog({
                message:content,
                title:"请选择菜单图标",
                closeButton:false,
                buttons:{
                    Cancel: {
                        label: "取消",
                        className: "btn-default",
                        callback: function ($event) {
                        }
                    }
                    , OK:{
                        label: "确定",
                        className: "btn-primary",
                        callback: function ($event) {
                            var permission_code = $($event.delegateTarget).find('input[type=radio][name=item_icon]:checked').val();
                            if(typeof permission_code == "undefined"){
                                bootbox.alert("请选择菜单图标");
                                return false;
                            }
                            else{
                                $("#JmenusIcon").val(permission_code);
                            }
                            return true;
                        }
                    }
                }
            });
        });

        $("#xmenuForm").validate({
            ignore: "",
            focusInvalid:false,
            errorElement: 'span',
            errorClass: 'help-block help-block-error',
            errorPlacement:function (error,element) {
                element.parent().addClass("has-error");
                error.appendTo(element.parent());
            },
            success:function (element) {
                element.closest('.form-group').removeClass('has-error');
                element.remove();
            },
            rules:{
                menuName:{
                    required:true,
                    zn:true
                },
                menuUrl:{
                    required:true
                }
//                permissionSign:{
//                    required:true
//                }
            },
            messages:{
                menuName:{
                    required:"菜单名称不能为空",
                    zn:"只能输入中文、英文、数字、下划线"
                },
                menuUrl:{
                    required:"菜单地址不能为空"
                }
//                permissionSign:{
//                    required:"请选择权限码"
//                }

            },
            submitHandler:function () {
               return false;
            }
        });
    </script>

    <script type="text/handlebars-template" id="T-permission">
      <div style="max-height: 500px; overflow-y:auto;overflow-x: hidden;">
          <div class="row">
              {{#each cateList}}
                    <div class="col-sm-12"><h4><b>{{name}}</b></h4></div>
                  {{#each ../list}}
                        {{#compare permissionCategory '==' ../name}}
                     <div class="col-sm-6"><label><input type="radio" name="permission_code" value="{{permissionSign}}"/>{{permissionName}}[{{description}}]</label></div>
                        {{/compare}}
                  {{/each}}
              {{/each}}
          </div>
      </div>
    </script>

    <script type="text/handlebars-template" id="T-menuicon">
        <div style="max-height: 500px; overflow-y:auto;overflow-x: hidden;">
            <div class="row fontawesome-icon-list">
                {{#each list}}
                        <div class="col-md-5 col-sm-6"><label><input type="radio" name="item_icon" value="{{this}}"/>&nbsp;&nbsp;<a href="javascript:;" style="text-underline: none;"><i class="fa {{this}}"></i><span class="sr-only">dfsfsfsd</span>&nbsp;&nbsp;{{this}}</a></label></div>
                {{/each}}
            </div>
        </div>
    </script>


</div>