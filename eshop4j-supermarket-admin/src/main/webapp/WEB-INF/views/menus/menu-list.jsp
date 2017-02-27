<%--
  Created by IntelliJ IDEA.
  User: Mignet
  Date: 2016/6/22
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="app/css/eshop4j.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<script type="text/javascript" src="assets/plugins/handlebars/handlebars-4.0.5.js"  ></script>
<script type="text/javascript" src="app/js/jquery.eshop4j.js"></script>

<div class="ux-warpper">
<div id="main-menus" class="container-fluid">
    <div class="table-responsive">
        <table id="JmenuList" class="table table-bordered" data-url="rest/menus/list_json"
               data-order="false"
               data-form="true"
               data-xtoolbars="#template-search" data-cols="false">
            <thead>
            <tr>
                <th data-name="id" data-format="id:serial">序号</th>
                <th data-name="parentName" data-format="parentName:pname">上级菜单</th>
                <th data-name="menuIcon" data-format="menuIcon:iconf">图标</th>
                <th data-name="menuName">菜单名称</th>
                <th data-name="menuUrl">菜单地址</th>
                <th data-name="permissionSign">权限码值</th>
                <th data-name="sort" data-format="sort:sortv">排序</th>
                <th data-name="isDisable" data-format="isDisable:ott">是否禁用</th>
                <th data-name="addTime">添加时间</th>
                <th data-name="id" data-format="id:opts">操作</th>
            </tr>
            </thead>
        </table>
        <div style=" clear: both;"></div>
    </div>
    <script type="text/javascript">

        Handlebars.registerHelper('compare', function(left, operator, right, options) {
            if (arguments.length < 3) {
                throw new Error('Handlerbars Helper "compare" needs 2 parameters');
            }
            var operators = {
                '==':     function(l, r) {return l == r; },
                '===':    function(l, r) {return l === r; },
                '!=':     function(l, r) {return l != r; },
                '!==':    function(l, r) {return l !== r; },
                '<':      function(l, r) {return l < r; },
                '>':      function(l, r) {return l > r; },
                '<=':     function(l, r) {return l <= r; },
                '>=':     function(l, r) {return l >= r; },
                'typeof': function(l, r) {return typeof l == r; }
            };

            if (!operators[operator]) {
                throw new Error('Handlerbars Helper "compare" doesn\'t know the operator ' + operator);
            }

            var result = operators[operator](left, right);

            if (result) {
                return options.fn(this);
            } else {
                return options.inverse(this);
            }
        });

        jQuery.validator.addMethod("zn",function (value, element) {
            value = value.replace(/\s/g);
            if(value.length>0){
                return /^[\u4e00-\u9fa5a-z_A-Z0-9]*$/.test(value);
            }
            return true;
        });

       var menusDb= $("#JmenuList").eshop4jTable({
            formats:{
                "opts":function (data,type,full,meta) {
                    return '<a class="btn btn-sm btn-default btn-icon J_menu_save" href="javascript:;" data-title="更新菜单"  data-url="/rest/menus/save?id='+data+'" ><i class="fa fa-edit"></i>编辑</a> &nbsp;&nbsp;<a class="btn btn-sm btn-danger btn-icon J_menus_delete" data-id="'+data+'" href="javascript:;"><i class="fa fa-trash-o"></i>删除</a>';
                },
                "ott":function (data,type,full,meta) {
                    return data == 1?"是":"否";
                },
                "pname":function (data,type,full,meta) {
                    return data ==null?"--" : data;
                },"sortv":function (data,type,full,meta) {
                    var v = full.sort == null?0:Number(full.sort);
                    return '<input style="width: 50px; text-align: center;" type="text" class="J_sortupdate" data-id="'+full.id+'" value="'+v+'"/>';
                },
                "iconf":function (data,type,full,meta) {
                    return data?'<i class="fa '+data+'"></i>':"--";
                }
            }
        });


       $("#main-menus").delegate("a.J_menu_save","click",function () {
           var url = $(this).attr("data-url");
           var $title = $(this).attr("data-title");

           $.get(url,{},function (result) {
               bootbox.dialog({
                   message:$(result),
                   title:$title,
                   closeButton:false,
                   buttons:{
                       Cancel: {
                           label: "取消",
                           className: "btn-default",
                           callback: function ($event) {
                               var $form = $($event.delegateTarget).find("form");
                           }
                       }
                       , OK: {
                           label: "确定",
                           className: "btn-primary",
                           callback: function ($event) {
                               var $form = $($event.delegateTarget).find("form");
                               if($form.submit().validate().valid()){
                                   var result = false;
                                  $.ajax({
                                      type:"POST",
                                      async:false,
                                      url:$form.attr("action"),
                                      dataType:"json",
                                      data:$form.eshop4jFormObject(),
                                      error:function () {
                                          
                                      },
                                      success:function (data, textStatus, jqXHR) {
                                          if(data){
                                              result = true;
                                              menusDb.draw();
                                              toastr.success("操作成功");
                                          }
                                          else{

                                          }
                                      }
                                  });
                                   return result;
                               }
                               return false;
                           }
                       }
                   }
               });
           },"html");
       });
    
       $("#main-menus").delegate("a.J_menus_delete","click",function () {
           var id = $(this).attr("data-id");
           var iid = [];
           iid.push(id);
          bootbox.confirm("确定要执行删除菜单操作?",function (result) {
              if(result){
                  $.post("/rest/menus/delete_json",{"iids":iid},function (result) {
                      if(result){
                          toastr.success("删除操作成功");
                          menusDb.draw();
                      }
                      else{
                          toastr.error("删除操作失败");
                      }

                  },"json");

              }
          });
       });

        // 更新排序
        $("#main-menus").delegate("input.J_sortupdate","change",function () {
           var iid = $(this).attr("data-id");
            var sort = $(this).val();
            $.get("rest/menus/update/sort/"+iid+"/"+sort,{},function (result) {
                if(result){
                    console.log("更新排序成功");
                }
            });
        });
    </script>

    <script type="text/eshop4j-template" id="template-search">
        <form>
            <div class="row">
                <div class="col-sm-2">
                    <select  name="parentId" class="form-control">
                        <option value="-1">请选择上级菜单</option>
                        <option value="0">顶级菜单</option>
                        <c:if test="${!empty parentList}">
                            <c:forEach var="item" items="${parentList }"  varStatus="dn">
                                <option <c:if test="${(menu.parentId eq item.id)  }"> selected="selected"</c:if> value="${item.id }">${item.menuName }</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input name="menuName" class="form-control"   placeholder="请输入菜单名称" autocomplete="off">

                        <span class="input-group-btn">
                             <a class="btn btn-default J_search" href="#" role="button">查询</a>
                            <a class="btn btn-default btn-icon J_menu_save" href="javascript:;" data-title="新增菜单" data-url="rest/menus/save"><i class="fa fa-plus"></i>新增</a>
                        </span>
                    </div>
                </div>
            </div>
        </form>
    </script>



</div>
</div>