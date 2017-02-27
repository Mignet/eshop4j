;(function ($win,$) {

        $win.linkwee= {routerTree:[]};
        linkwee.router={
        breadcrumbChangeEvent:function () {
            var nodes = linkwee.routerTree;
//            console.log(nodes);
               var count = nodes.length;
               var lastNode = nodes[count-1];
                $("#index-page-title").html(lastNode.title);
                var first_menus = $("#index-menu").parent();
                var ul = first_menus.parent();
                 first_menus.nextAll().remove();
                for(var i =0;i<count;i++){
                    if(i<count-1){
                        ul.append('<li><a class="ui-redirect" href="javascript:;" data-title="'+nodes[i].title+'" data-url="'+nodes[i].url+'">'+nodes[i].title+'</a><i class="fa fa-angle-right"></i></li>');
                    }
                    else{
                        ul.append('<li><span>'+nodes[i].title+'</span></li>');
                    }
                }
                
            }
        };
        linkwee.addRouterTree = function (title,url) {
            var burl = $("base").attr("href");
            var is_parent = arguments[2]?arguments[2]:false;
            var hasnode = false;
            var nodes = linkwee.routerTree;
            url = url.replace(burl,"").replace("http:","").replace("https:","");
            if(is_parent){
                linkwee.routerTree = [{title:title,url:url}];
            }
            else{
                for(var i=0;i<nodes.length;i++){
                    if(nodes[i].title === title || nodes[i].url === url){
                        nodes.splice(i,nodes.length-i);
                        hasnode = true;
                        break;
                    }
                }
                if(!hasnode || nodes.length<=0){
                    linkwee.routerTree.push({title:title,url:url});
                }

            }
            linkwee.router.breadcrumbChangeEvent();




        }
})(window,jQuery);

// index操作
$(function() {
    App.init();

    var Index = (function() {
        var me = {};

        function changeMenu(title){
        	$('#index-page-title').html(title);
        	$('#index-menu2').html(title);
        	
        }
        function changeHeader(header){
        	$('#index-menu').html(header)
        }
        // 处理一级菜单点击
        me.handleMenuClick = function() {
            $('#page-sidebar-menu > li').click(function(e) {
            	//移除其他选择样式
                var menu = $('#page-sidebar-menu');
                var li = menu.find('li.active').removeClass('active');
            });
        };


        /**
         * 渲染HTML页面
         */
        me.switchPage = function (pageName,pageUrl) {
            var $method = "GET";
            linkwee.addRouterTree(pageName,pageUrl);
            changeMenu(pageName);
            $.ajax({
                type :$method,
                url :pageUrl,
                success :function(data){
                    $('#main-content').html(data);
                },
                //async :false,
                error:function(XmlHttpRequest,textStatus, errorThrown)
                {
                    $('#main-content').html(XmlHttpRequest.responseText);
                }
            });
        }

        /**
         * post 请求加载页面
         * @param title
         * @param url
         * @param params
         */
        me.loading = function (title,url,params) {
            changeMenu(title);
            $.post(url,params,function (result) {
                $('#main-content').html(result);
            },'html');
        }


        // 处理子菜单点击[点击之后，在中间内容区域加载内容]
        me.handleSubMenuClick = function() {
            var self = this;
            $('#page-sidebar-menu li a').click(function(e) {
                e.preventDefault();
                var url = this.href;
                if (url !== null && url !== 'javascript:;') {
                	//设置引导显示menu>menu2
                    linkwee.addRouterTree(this.text,url,true);
                    self.switchPage(this.text,url);

                }
            });
        };

        me.init = function() {
        	//set bootbox locale
            var self = this;
        	bootbox.setDefaults({locale:"zh_CN"});
            me.handleMenuClick();
            me.handleSubMenuClick();
            $(document).delegate(".ui-redirect","click",function () {
                var $debug = $(this).attr("data-debug");
                var $localDebug =  localStorage.debug;
                if(($debug && parseInt($debug) === 1) && ($localDebug === undefined || !$localDebug)  ){
                    return false;
                }
                else{
                    var pageTile = $(this).attr("data-title");
                    var url = $(this).attr("data-url");
                    self.switchPage(pageTile,url);
                }
            });
        };

        return me;
    })();

    Index.init();
    $.extend({
        switchPage:function (pageTile,url) {
            Index.switchPage(pageTile,url);
        },
        Go:function (title,url,params) {
            Index.loading(title,url,params);
        }
    });
    //触发点击事件,点击首页
    $('#btn-dashboard').trigger("click");
});