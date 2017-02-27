<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
<form id="redpacketFrom">
			<div class="page-header">
				<div class="row">
					<div class="col-md-8">
						<h4><small><p class="text-muted">红包基本信息</p></small></h4>
					</div>
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">红包类型:</label>
				<div class="col-md-4">
					<select name="type">
						<option value="1">投资返现红包</option>
					</select>
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">红包名称:</label>
				<div class="col-md-4">
					  <input type="text" class="form-control" name="name" autocomplete="off" placeholder="红包名称" />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">红包金额:</label>
				<div class="col-md-4">
					 <input type="text" class="form-control" name="money" autocomplete="off" placeholder="红包金额" />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">红包描述:</label>
				<div class="col-md-4">
					 <input type="text" class="form-control" name="remark" autocomplete="off" placeholder="红包描述" />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-10 col-sm-offset-2 control-label">
					<span style="color: red;font-size: 14px;">描述必须以英文逗号分隔,逗号之前为期限描述,之后为金额描述.如:'180天期以上,10000元起'</span>
				</label>
			</div>
			<div class="page-header">
				<div class="row">
					<div class="col-md-8">
						<h4><small><p class="text-muted">使用条件</p></small></h4>
					</div>
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">适用平台:</label>
				<div class="col-md-2">
					 <select name="limitPlatform" class="form-control">
					 	<option value="0">不限</option>
					 	<option value="1">限制</option>
					 </select>
				</div>
				<div class="col-md-2">
					 <select class="form-control" id="platformId" name="platformId"> 
					 	<option value="">选择平台</option>
					 	<c:forEach var="platform" items="${platformList}" >
									<option value="${platform.orgNumber}" >${platform.name}</option>
							</c:forEach>
					 </select>
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">投资形式:</label>
				<div class="col-md-2">
					 <select name="investLlimit" class="form-control">
					 	<option value="0">不限</option>
					 	<option value="1">用户首投</option>
					 	<option value="2">平台首投</option>
					 </select>
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">投资金额限制:</label>
				<div class="col-md-2">
					 <select name="limitMoney" class="form-control">
					 	<option value="0">不限</option>
					 	<option value="1">大于</option>
					 	<option value="2">大于等于</option>
					 </select>
				</div>
				<div class="col-md-2">
					 <input type="text" class="form-control" name="investMoney" autocomplete="off" placeholder="投资金额" />
				</div>

			</div>
			<br>
			<div class="row">
				<div class="col-md-4"><input type="radio" value="0" checked="checked" name="limitProduct">&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label">不限产品</label></div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-2">
					<input type="radio" value="1" name="limitProduct">&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label">投资期限</label>
				</div>
				<div class="col-md-2">
					 <select name="relationalOperator" class="form-control">
					 	<option value="0">等于</option>
					 	<option value="1">大于等于</option>
					 </select>
				</div>
				<div class="col-md-2">
					<input type="text" class="form-control" name="deadline" autocomplete="off" placeholder="投资期限  (单位 ：天)" />
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-2">
					<input type="radio" value="2" name="limitProduct" id="limitProductId" >&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label">适用产品</label>
				</div>
			</div>
			<br>
			 <div class="row">
			 	<div class="col-md-2">
					<input type="text" class="form-control" id="productName" placeholder="搜索产品名称" >
				</div>
				<div class="col-md-1"><a class="btn btn-default btn-icon"   id="query" role="button">查询</a></div>
			 </div>
			 <br>
			 <div class="row">
					<div class="col-md-8">
						<h3><small><p >已绑定产品</p></small></h3>
					</div>
				</div>
			  <div class="row">
			 	<div class="col-md-10">
					<table id="bindingProducts" class="table table-bordered" cellspacing="0" width="100%">
				        <thead>
				            <tr>
					            <th>产品名称</th>
					            <th>收益率</th>
					            <th>佣金率</th>
					            <th>产品期限</th>
					            <th>操作</th>
				            </tr>
				        </thead>
		    		</table>
				</div>
			 </div>
			 <br>
			 <br>
			 <br>
			<div class="row">
				 <div class="col-md-2 col-md-offset-4">
				 	<a class="btn btn-danger active J_addRedpacket"  role="button"  >保存</a>
				 	&nbsp;&nbsp;&nbsp;&nbsp;
				 	<a class="btn btn-default active J_goback" role="button"  >返回</a>
				 </div>
			</div>
</form>
</div>



 <!-- 模态框（Modal）-->
<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-hidden="true" style="top: 200px">
<div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">

                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                  <span aria-hidden="true">&times;</span></button>
                                </button>
                                <h4 class="modal-title">理财产品查找结果</h4>
                            </div>
                             <div class="modal-body"></div>
                             <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						      </div>
                        </div>
                 	</div>
</div>
<script type="text/javascript"  src="assets/plugins/layer/layer.js"></script>
<script type="text/javascript" >

$(document).ready(function() {
  window['rproductIds']  = [];
   var $modal = $('#modal');
	$("#query").on("click",function(){
		 var name = $("#productName").val();
	     if(!name) {
	       layer.alert("产品名称不能为空",{icon: 5,title:'提示'});
	       return false;
	     }
	     $modal.modal('show');
	});
	$modal.on("show.bs.modal", function() {
		$.ajax({
			url : "/rest/redpacket/productPage",
			type : 'GET',
			success : function(data) {
				$modal.find(".modal-body").html(data);
			}
		});
	});
	$("#bindingProducts").off('click.J_productId').on("click", "a.J_productId", function(event) {
		var array = window['rproductIds'];
		var $target = $(event.target);
		var pid = $target.attr("data-pid");
		if (milo.hasValue(array, pid)) {
			if (milo.remove(array, pid)){
				$("#bindingProducts").find(".J_productId[data-pid='"+pid+"']").parent().parent().remove();
			}
		}
		return false;
	});

    $(".J_addRedpacket").on("click", function(event) {
    	var data =milo.jsonFromt($("form").serializeArray());
    	if(!data.name){
             layer.msg('红包名称不能为空',{time: 1000,icon: 0});
             return false;
    	}
    	if( !$.isNumeric(data.money) || data.money<1 ){
          layer.msg('红包金额必须为大于等于1的数值',{time: 1000,icon: 0});
          return false;
      	}
    	if(!data.remark){
             layer.msg('红包描述不能为空',{time: 1000,icon: 0});
            return false;
    	}

        if(data.limitPlatform==1){
          if(!data.platformId){
               layer.msg('平台不能为空',{time: 1000,icon: 0});
              return false;
          }
      }
      if(data.limitMoney==1 || data.limitMoney==2){
          if(!data.investMoney || !$.isNumeric(data.investMoney) || data.investMoney<1){
              layer.msg('购买产品金额必须为大于等于1的数值',{time: 1000,icon: 0});
              return false;
          }
      }

        if(data.limitProduct==1){
            if(!data.deadline || !$.isNumeric(data.deadline) || data.deadline<=0){
                 layer.msg('产品期限必须为大于0的数值',{time: 1000,icon: 0});
                return false;
            }

        }
        if(data.limitProduct==2){
    		var rproductIds= window['rproductIds'];
        	console.info(rproductIds);
    		var notEmpty = milo.getLength(rproductIds)>0;
            	if(notEmpty){
            		data.pids = rproductIds.join(",");
            	}else{
            		layer.msg('请至少绑定一个产品！',{time: 1000,icon: 0});
            		return false;
            	}
    	};
    	$.post('rest/redpacket/add', data, function(data, textStatus, xhr) {
            layer.msg(data.msg,{time: 1000,icon: data.isFlag?1:0});
            if( data.isFlag)$.switchPage("红包列表","rest/redpacket/initPage"); //跳到红包列表页面
        });
        return false;
    });

    $(".J_goback").on("click", function(event) {
        $.switchPage("红包列表","rest/redpacket/initPage"); //跳到红包列表页面
        return false;
    });

} );
</script>
