/**
 * Created by Mignet on 2016/6/6.
 * html 标签属性说明
 * data-url 为table标签属性 为ajax请求源
 * data-cols 为table标签属性,在ajax加载时是否传dataTables columns 与search 参数
 * data-defer 为table标签属性，是否延迟第一次加载数据
 * data-xtoolbars 为table标签属性，自定义toolbars 值为内容模板id如 #template
 * data-name 为td标签属性,值为数据源行数据属性名称
 * data-format 为td标签属性,值为 fieldName:functionName,fieldName行数据属性名称,functionName为自定义方法名接受四个参数
 * {
 *              ordering:false,
                searching:true,
                lengthChange:false,
                paging:true,
                select:true,
                serverSide:true,
                xtoolbars:null,  // 自定义toolbars 值为内容模板id如 #template
                scrollX:false,
                xcols:true,     // 在ajax加载时是否传dataTables columns 与search 参数
 * }
 *
 * 开启指定列的排序功能所需要的条件,table上需开启排序 data-cols="false" ,data-order="true", 然后再指定列开启排序 <td data-order="true">
 */
;(function ($) {
    $.fn.extend({
        linkweeFormObject:function () {

            var arrs =  $(this).serializeArray();

            var result = {};
            var count = arrs.length;
            if(count>0){
                for(var i =0;i<count;i++){
                    result[arrs[i].name] = arrs[i].value;
                }
            }
            return result;
        },
        linkweeTable:function (options) {
            var _defaults = {
                ordering:false,
                order:[],
                searching:true,
                lengthChange:false,
                paging:true,
                select:true,
                searchEvent:null,
                serverSide:true,
                xtoolbars:null,  // 自定义toolbars 值为内容模板id如 #template
                scrollX:false,
                xcols:true,
                dom: '<"J_toolbar ux-toolber">frtip',
                "language": {
                    select: {
                        rows: {
                            _: "已选择 %d 行",
                            1: "已选择 1 行"
                        }
                    },
                    "sProcessing":   "处理中...",
                    "sLengthMenu":   "显示 _MENU_ 项结果",
                    "sZeroRecords":  "没有匹配结果",
                    "sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                    "sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
                    "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                    "sInfoPostFix":  "",
                    "sSearch":       "搜索:",
                    "sUrl":          "",
                    "sEmptyTable":     "表中数据为空",
                    "sLoadingRecords": "载入中...",
                    "sInfoThousands":  ",",
                    "oPaginate": {
                        "sFirst":    "首页",
                        "sPrevious": "上页",
                        "sNext":     "下页",
                        "sLast":     "末页"
                    },
                    "oAria": {
                        "sSortAscending":  ": 以升序排列此列",
                        "sSortDescending": ": 以降序排列此列"
                    }
                },
                dataSrc:null,
                formats:{
                }

            };

            var __defaultFomat = {
                "serial":function (data,type,full,meta) {  // 默认表格序号列
                    var _p = typeof meta.settings.oAjaxData == "string"?$.parseJSON(meta.settings.oAjaxData):meta.settings.oAjaxData;
                    var start = (_p.start / _p.length) +1;
                    return (meta.row+1)+(start-1)*10;
                },
                "float":function (data,type,full,meta){

                    var n = Number(data);
                    return n.toFixed(2);
                }
            }




            var __settings = $.extend(_defaults,options);
            __settings.formats = $.extend(__settings.formats,__defaultFomat);

            // 读取元素上以data-开头的属性值
            var findData = function ($my,attrName,$default) {
                var value = $($my).attr("data-"+attrName);
                if(typeof value == "undefined"){
                    value = $default;
                }
                switch (value){
                    case "true":
                        value = true;
                        break;
                    case "false":
                        value = false;
                        break;
                }
                return value;
            }
            // debug打日志
            var $debug = function ($obj) {
                if(window.console){
                    console.log($obj);
                }
            }





            // 判断是否加载了jquery.datatables.min.js
            if(!$.fn.DataTable){
                $debug("Please load jquery.datatables.min.js");
                return false;
            }

            // 回调字符串方法
            var callFunction = function (funname,data,type,full,meta) {
                var fn = $.isFunction(window[funname])?window[funname]:__settings.formats[funname];
                return fn(data,type,full,meta);
            }


            var findWrapper = function ($obj) {
                var pid = "#"+$obj.attr("id")+"_wrapper"; //J-salelist
                return $(pid);
            }


            console.info(__settings);

             var $db = null;
             $(this).each(function () {
                var $target = $(this);
                var o = {
                    ordering:findData($target,"order",false),
                    searching:findData($target,"search",false),
                    select:findData($target,"select",true),
                    paging:findData($target,"paging",true),
                    xcols:findData($target,"cols",true),
                    xtoolbars:findData($target,"xtoolbars",null),
                    xparams:findData($target,"form",false),

                    ajax:{
                        url:findData($target,"url",false),
                        type:"POST",
                        dataSrc:function (result) {
                            if(__settings.dataSrc){
                                return __settings.dataSrc(result);
                            }
                            return result.data;
                        },
                        data:function (params) {
                            if(o.xtoolbars){
                                var searchForm = findWrapper($target).find(".J_toolbar").find("form");
                                if(searchForm.length ==1){
                                    if(o.xparams){
                                        params["params"] = $(searchForm).linkweeFormObject();
                                    }
                                    else{
                                        params=$.extend(params,$(searchForm).linkweeFormObject());
                                    }

                                }
                            }

                            if(o.xcols){
                                params.columns = [];
                                params.search = {};
                            }
                            else{
                                return params =  JSON.stringify(params);
                            }


                        }
                    }
                };

                 // 调整发送json格式数据
                 if(!o.xcols){
                     o.ajax.dataType = "json";
                     o.ajax.processData = false;
                     o.ajax.contentType = 'application/json;charset=UTF-8';
                 }

                 // 是否进行延迟加载
                 var $deferLoading = findData($target,"defer",false);
                 if($deferLoading){
                     o.deferLoading = true;
                 }

                var ths = $target.find("thead > tr>th");
                var columns = [];
                var columnDefs = [];
                ths.each(function (i,n) {
                    var field_name = findData(n,"name",false);
                    var field_format = findData(n,"format",false);
                    var field_order = findData(n,"order",false);
                    var defs = {targets:i};
                    if(field_name){
                        columns.push({data:field_name,"orderable":field_order});
                    }

                    // defs.orderable = field_order;
                    // 自定义列的格式化显示
                    if(field_format){
                        var form = field_format.split(":");
                        defs["data"] = form[0];
                        defs["render"] = function (data, type, row,meta) {
                            return callFunction(form[1],data, type, row,meta);
                        };
                    }
                    columnDefs.push(defs);
                });
                o.columns = columns;
                o.columnDefs = columnDefs;
                var x = $.extend(__settings,o);
                 console.log(x);
                 $db = $target.on("init.dt",function (e,_setting,ox,oh) {
                     _setting['aaSorting']=[[3,'desc']];
                     // 自定义搜索与绑定搜索事件
                     var $bars = findWrapper($target).find(".J_toolbar");
                     if($bars.length>0){
                         $bars.html($(x.xtoolbars).html());
                         $bars.find(".J_search").click(function () {
                             if(typeof x.searchEvent == "function"){
                                 if(x.searchEvent()){
                                     $db.draw();
                                 }
                             }
                             else{
                                 $db.draw();
                             }
                         });
                     }
                 }).DataTable(x).on("select",function ( e, dt, type, indexes) {
                     console.log("select item rows");
                 });
            });
            return $db;

        }
    });
})(jQuery);