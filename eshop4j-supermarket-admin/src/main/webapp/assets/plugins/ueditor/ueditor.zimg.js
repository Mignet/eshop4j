/**
 * Created by Mignet on 2016/6/8.
 * Ueditor zimg上传插件
 */
;(function (UE) {

    if(typeof jQuery == "undefined"){
        console.log("pls loading jquery.js");
        return;
    }

    if(typeof UE == "undefined"){
        console.log("pls loading ueditor.all.js");
    }
    else{
        UE.registerUI("zimgupload",function (editor,zimgupload) {
            var me = this;
            var $container;
            var zimg_url = editor.options.zimg;
            if(typeof zimg_url == "undefined" || zimg_url == ""){
                return false;
            }
            //注册按钮执行时的command命令，使用命令默认就会带有回退操作
            editor.registerCommand(zimgupload, {
                execCommand: function() {
                }
            });
            //初始化上传元素
            var initBtn=function (o) {
                $container  = o;


                var xfile = document.createElement("input");
                xfile.setAttribute("type","file");
                xfile.style.opacity = 0;
                UE.dom.domUtils.on(xfile,"change",function () {
                    var fileData = new FormData();
                    fileData.append("userfile",this.files[0]);
                    console.log(fileData);
                    $.event.trigger("ajaxStart");
                    $.ajax({
                        type:'post',
                        url: zimg_url,
                        data: fileData,
                        async: true,
                        cache: false,
                        contentType: false,
                        processData: false,
                        success:function (data) {
                            $.event.trigger("ajaxStop");
                            if(data.indexOf("MD5")!=-1){
                                var result =  data.substring(data.indexOf("MD5:")+4,data.indexOf(","));
                                var url = zimg_url+result;
                                console.log(result);
                                me.execCommand("inserthtml",'<img src="'+url+'"/>');
                            }
                            else{
                                console.log("upload fail.");
                            }
                        }
                    });



                });
                $container.appendChild(xfile);



            }
            //创建一个button

            var btn = new UE.ui.Button({
                //按钮的名字
                name: zimgupload,
                //提示
                title: "图片上传",
                //添加额外样式，指定icon图标，这里默认使用一个重复的icon
                cssRules: 'background-position: -380px 0;',

                //点击时执行的命令
                onclick: function() {
                    //这里可以不用执行命令,做你自己的操作也可
                    console.log(this.target.childNodes);
                    editor.execCommand(zimgupload);
                }
            });

            //当点到编辑内容上时，按钮要做的状态反射
            editor.addListener('selectionchange', function() {
                var state = editor.queryCommandState(zimgupload);
                if (state == -1) {
                    btn.setDisabled(true);
                    btn.setChecked(false);
                } else {
                    btn.setDisabled(false);
                    btn.setChecked(state);
                }
            });

            // 插件渲染开始时间
            editor.addListener("ready",function () {
                var b = btn.getDom('body');
                initBtn(b.children[0]);
            });

            //因为你是添加button,所以需要返回这个button
            return btn;
        });
    }
})(UE);