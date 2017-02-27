(function(){

	var $ = window.jQuery;

	
	/** 

	绑定事件 
	selector ： 绑定的对象
	events ： 绑定事件
	handler ：处理事件的回调函数
	data　：传入回调函数中的参数,通过event.data访问。
	*/
	function _bindingEvent(selector , events  , handler , data){
		$("body").off(events,selector);
		data ? $("body").on(events,selector,data,handler) : $("body").on(events,selector,handler);
		return false;
	}
	
	/**
	 * 为多个对象添加同一种事件 
	 * return 返回对象{
	 * "events" : "事件",
	 * addHandler :function(elments){
	 * 
	 * } "添加处理事件的方法" 
	 *}
	 */
	function _on(events){
		return {"events" : events,addHandler : function(elment){
			var _this = this;
			if($.isArray(elment)){
				$.each(elment, function(index, val) {
					_bindingEvent(val.selector,_this.events,val.handler,val.data);
				});
			}else{
				_bindingEvent(elment.selector,_this.events,elment.handler,elment.data);
			}
		}};
	}
	
	function _getUrlParam(name)  
    {  
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");  
        var r = window.location.search.substr(1).match(reg);  
        if (r!=null) return unescape(r[2]); return null;  
    }
	
	function _get(url,query,resultType){
	
	}
	
	function _post(url,query,fnc){
		/*using(["messager"],function(){
			$.messager.progress({
				title : '请稍后',
				msg : '数据加载中...'
			});
			$.post(url,query,"json").done(function(data){
				try {
					fnc.call(this,data);
				} catch (e) {
					console.info(e);
					$.messager.alert('系统提示',"数据加载失败,请重新访问!");
				}
			   }).fail(function(){
				   $.messager.alert('系统提示',"数据加载失败,请检查网络!");
			   }).always(function(){
				   $.messager.progress('close');
			   });
		});*/
		$.ajax({
			  type: 'POST',
			  url: url,
			  data: query,
			  success: fnc,
			  dataType: 'json'
			});
	}
	
	function _serializeObject(formId){
		   var o = {};  
		   var a = $(formId).serializeArray();  
		   $.each(a, function() {  
		       if (o[this.name]) {  
		           if (!o[this.name].push) {  
		               o[this.name] = [o[this.name]];  
		           }  
		           o[this.name].push(this.value || '');  
		       } else {  
		           o[this.name] = this.value || '';  
		       }  
		   });  
		   return o; 
	}
	
	function _openWin(data){
		var $win = $("#win");
		if($win){
			$win.parent().remove();
		}
		data = $.extend({},data,{top:($(window).height() - 600) * 0.5});
		$("body").append($("<div>").attr({"id":"win"}));
		$("#win").window(data);
	}
	
	function _inintDatagrid(gridId,formId,url,model,unLoad){
		using(["datagrid","pagination"],function(){
			if(formId){
				lh.on("click").addHandler({"selector":formId + " a.J_search","handler" : function(e){
						var query =_serializeObject(formId);
						var options =  $(p).data("pagination").options;
						 query.pageIndex=(unLoad?1:options.pageNumber);
					     query.pageSize=(unLoad?1:options.pageSize);
						/*query.pageIndex=options.pageNumber;
				        query.pageSize=options.pageSize;*/
				        _post(url,query,function(data){
				        	 var value = {rows:data.datas,total:data.totalCount};
							 if(data.footer)value.footer=data.footer;
							 $datagrid.datagrid('loadData', value);
				      		   //$datagrid.datagrid('loadData',  {rows:data.datas,total:data.totalCount});
				      	 });
						 return false;
					}
				});
			}
			var $datagrid= $(gridId).datagrid(model);
			var p = $datagrid.datagrid('getPager');
	        $(p).pagination({  
		        pageSize: 20,//每页显示的记录条数，默认为20  
		        pageList: [20],//可以设置每页记录条数的列表  
		        beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		        onSelectPage: function(pageNumber, pageSize){
		        	var options =  $(p).data("pagination").options;
		        	var query ={};
			        if(formId){
			        	query = _serializeObject(formId);
			        	
			        }
			   
			        query.pageIndex=(unLoad?1:options.pageNumber);
			        query.pageSize=(unLoad?1:options.pageSize);
			        _post(url,query,function(data){
			        	 var value = {rows:data.datas,total:data.totalCount};
			        	 if(data.footer)value.footer=data.footer;
						 $datagrid.datagrid('loadData', value);
			      		 //$datagrid.datagrid('loadData',  {rows:data.datas,total:data.totalCount});
			      	 });
		        }
			}); 
	        if(unLoad)return false;
			var options =  $(p).data("pagination").options;
			var query ={};
			if(formId){
	        	query = _serializeObject(formId);
	        }
	        query.pageIndex=options.pageNumber;
	        query.pageSize=options.pageSize;
			
	        _post(url,query,function(data){
				 var value = {rows:data.datas,total:data.totalCount};
				 if(data.footer)value.footer=data.footer;
				 $datagrid.datagrid('loadData', value);
			 });
	       
			
		});
	}

	var lh = {
		
		bindingEvent: function (selector , events  , handler , data){
			return _bindingEvent(selector , events  , handler , data);
		},
		on: function(events){
			return _on(events);
		},
		post:function(url,query,resultType){
			return _post(url,query,resultType);
		},
		inintDatagrid: function(gridId,formId,url,model,unLoad){
			return _inintDatagrid(gridId,formId,url,model,unLoad||false);
		},
		serializeObject : function(formId){
			return _serializeObject(formId);
		},
		getUrlParam : function(name){
			return _getUrlParam(name);
		},
		openWin :function(data){
			return _openWin(data);
		}

	}
	window.lh = lh;

})();