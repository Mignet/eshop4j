<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" >  window['rproductIds']  = [];</script>
<div class="container">
<c:if test="${!empty errorMgs }">
				<div class="row">
					<div class="col-md-8">
						<h4><small><p class="text-muted">errorMgs</p></small></h4>
					</div>
				</div>
</c:if>

<c:if test="${empty errorMgs }">



<form id="redpacketFrom">	
			<input type="hidden" name="redpacketId" value="${redpacketId}">
			<!-- <input type="hidden" name="type" value="1"> -->
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
					<select name="type" class="form-control">
						<option value="1" selected="selected">投资返现红包</option>
					</select>
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">红包名称:</label>
				<div class="col-md-4">
					  <input type="text" class="form-control" name="name" value="${redpacket.name}" autocomplete="off" placeholder="红包名称" />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">红包金额:</label>
				<div class="col-md-4">
					 <input type="text" class="form-control" name="money" value="${redpacket.money}" autocomplete="off" placeholder="红包金额" />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">红包描述:</label>
				<div class="col-md-4">
					 <input type="text" class="form-control" name="remark" value="${redpacket.remark}" autocomplete="off" placeholder="红包描述" />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-10  col-sm-offset-2  control-label">
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
					 	<option value="0" <c:if test="${redpacket.limitPlatform==0}">selected="selected"</c:if> >不限</option>
					 	<option value="1" <c:if test="${redpacket.limitPlatform==1}">selected="selected"</c:if>  >限制</option>
					 </select>
				</div>
				<div class="col-md-2">
					 <select class="form-control" id="platformId" name="platformId"> 
					 	<option value="">选择平台</option>
					 	<c:forEach var="platform" items="${platformList}" >
									<option value="${platform.orgNumber}" <c:if test="${redpacket.platformId==platform.orgNumber }">selected="selected"</c:if> >${platform.name}</option>
							</c:forEach>
					 </select>
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">投资形式:</label>
				<div class="col-md-2">
					 <select name="investLlimit" class="form-control">
					 	<option value="0" <c:if test="${redpacket.investLlimit==0}">selected="selected"</c:if> >不限</option>
					 	<option value="1" <c:if test="${redpacket.investLlimit==1}">selected="selected"</c:if> >用户首投</option>
					 	<option value="2" <c:if test="${redpacket.investLlimit==2}">selected="selected"</c:if> >平台首投</option>
					 </select>
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">投资金额限制:</label>
				<div class="col-md-2">
					 <select name="limitMoney" class="form-control">
					 	<option value="0" <c:if test="${redpacket.limitMoney==0}">selected="selected"</c:if> >不限</option>
					 	<option value="1" <c:if test="${redpacket.limitMoney==1}">selected="selected"</c:if> >大于</option>
					 	<option value="2" <c:if test="${redpacket.limitMoney==2}">selected="selected"</c:if> >大于等于</option>
					 </select>
				</div>
				<div class="col-md-2">
					 <input type="text" class="form-control" name="investMoney" <c:if test="${redpacket.limitMoney==1 || redpacket.limitMoney==2}">value="${redpacket.investMoney}"</c:if>  autocomplete="off" placeholder="投资金额" />
				</div>

			</div>
			<br>
			<div class="row">
				<div class="col-md-4"><input type="radio" value="0" <c:if test="${redpacket.limitProduct==0}">checked="checked"</c:if>   name="limitProduct">&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label">不限产品</label></div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-2">
					<input type="radio" value="1" <c:if test="${redpacket.limitProduct==1}">checked="checked"</c:if> name="limitProduct">&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label">投资期限</label>
				</div>
				<div class="col-md-2">
					 <select name="relationalOperator" class="form-control">
					 	<option value="0"  <c:if test="${redpacket.limitProduct==1 && redpacket.relationalOperator==0}">selected="selected"</c:if> >等于</option>
					 	<option value="1" <c:if test="${redpacket.limitProduct==1 && redpacket.relationalOperator==1}">selected="selected"</c:if> >大于等于</option>
					 </select>
				</div>
				<div class="col-md-2">
					<input type="text" class="form-control" name="deadline"  <c:if test="${redpacket.limitProduct==1}">value="${redpacket.deadline}"</c:if>  autocomplete="off" placeholder="投资期限  (单位 ：天)" />
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-2">
					<input type="radio" value="2" name="limitProduct" id="limitProductId" <c:if test="${redpacket.limitProduct==2}">checked="checked"</c:if> >&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label">适用产品</label>
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
				        <c:if test="${redpacket.limitProduct==2 && !empty products}">
					        <script type="text/javascript" >
					        	var pids = '${redpacket.pids}';
								window['rproductIds']=pids.split(',');
					        </script>
				        	<c:forEach var="product" items="${products}" >
								<tr role="row" >
									<td>${product.productName }</td>
									<td>${product.productRateText }</td>
									<td>${product.feeRatio }</td>
									<td>${product.deadLineValueText }</td>
									<td><a href="javascript:;"
										data-pid="${product.productId }"
										class="btn btn-danger btn-sm J_productId">取消</a></td>
								</tr>
							</c:forEach>
				        </c:if>
		    		</table>
				</div>
			 </div>
			 <br>
			 <br>
			 <br>
			<div class="row">
				 <div class="col-md-2 col-md-offset-4">
				 	<a class="btn btn-danger active J_updateRedpacket"  role="button"  >更新</a>
				 	&nbsp;&nbsp;&nbsp;&nbsp;
				 	<a class="btn btn-default active J_goback" role="button"  >返回</a>
				 </div>
			</div>
</form>
</c:if>
</div>
<c:if test="${empty errorMgs }">
<script type="text/javascript"  src="assets/plugins/layer/layer.js"></script>
<script type="text/javascript" >

$(document).ready(function() {
	   var $modal = $('#publicModal');
	    $("#query").on("click",function(){
            var name = $("#productName").val();
            if(!name) {
              layer.alert("产品名称不能为空",{icon: 5,title:'提示'});
              return false;
            }
			$.ajax({
				url :  "/rest/redpacket/productPage",
				type : 'GET',
				success : function(data) {
                    $modal.find(".modal-title").html("理财产品查找结果");
					$modal.find(".modal-body").html(data);
					$modal.modal('show');
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

    $(".J_updateRedpacket").on("click", function(event) {
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
            if(!data.deadline || !$.isNumeric(data.deadline) || data.deadline<1){
                 layer.msg('产品期限必须为大于等于1的数值',{time: 1000,icon: 0});
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
    	$.post('rest/redpacket/edit', data, function(data, textStatus, xhr) {
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
</c:if>