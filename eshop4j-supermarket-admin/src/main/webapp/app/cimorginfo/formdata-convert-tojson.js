//将表单序列化成json格式的数据(但不适用于含有控件的表单，例如复选框、多选的select)
 (function($){
	 
        	
    $.fn.toFormJSON = function () {
        		
        		var $this = this;
        		//单属性对象
				var sjsonObj={};
				
				var mjsonObj;
				//对象集合
				var jsonAray = new Array();
				//多属性对象集合
				var objs = new Array();
				
			    $.each(this.serializeArray(), function(index,param) {
			    	
			    	var names = param.name.split('.'); //表单中每个标签对象的name属性
			    	
			    	if(names.length>1){
			    		if(objs.length>0){
			    			 $.each(objs, function(index,obj){
			   				 	 var o = obj[names[0]];
				    			 if (o){
				    				 o[names[1]] = param.value;
				    				 return false;
				    			 }else if(index==(objs.length-1)){
				    				var temObj={};
							    	temObj[names[1]] = param.value;
							    	mjsonObj={};
							    	mjsonObj[names[0]]=temObj;
							    	objs.push(mjsonObj);
							    	return false;
				    			 }
				    			
				    		});
			    			
			    		}else{
				    		var temObj={};
					    	temObj[names[1]] = param.value;
					    	mjsonObj={};
					    	mjsonObj[names[0]]=temObj;
				    		objs.push(mjsonObj);
			    		}
		    		
			    	}else{
			    		
			    		sjsonObj[param.name] = param.value;
			    	}
			    });
			    
			    jsonAray.push(sjsonObj);
			    
			    if(objs.length>0){
			    	var name;
			    	var o = {};
			    	var a = new Array();
			    	$.each(objs, function(index,obj){
			    		for ( var key in obj) {
			    			name =  key.split('_')[0];
			    			a.push(obj[key]);
						}
			    	})
			    	o[name] = a;
			    	jsonAray.push(o);
			    }
			    var params={};
			    $.each(jsonAray, function(index,obj){
			    	for ( var key in obj) {
			    		params[key] = obj[key];
					}
			    });
			    return JSON.stringify(params);
			  };
			  
        	
   $.fn.serializeObject = function() {  
        	    var o = {};  
        	    var a = this.serializeArray();  
        	    $.each(a, function() {  
        	        if (o[this.name]) {  
        	            if (!o[this.name].push) {  
        	                o[this.name] = [ o[this.name] ];  
        	            }  
        	            o[this.name].push(this.value || '');  
        	        } else {  
        	            o[this.name] = this.value || '';  
        	        }  
        	    });  
        	    return o;  
        	};  
        	
   $.fn.serializeJson = function(){
                var jsonData1 = {};
                var serializeArray = this.serializeArray();
                // 先转换成{"id": ["12","14"], "name": ["aaa","bbb"], "pwd":["pwd1","pwd2"]}这种形式
                $(serializeArray).each(function () {
                    if (jsonData1[this.name]) {
                        if ($.isArray(jsonData1[this.name])) {
                            jsonData1[this.name].push(this.value);
                        } else {
                            jsonData1[this.name] = [jsonData1[this.name], this.value];
                        }
                    } else {
                        jsonData1[this.name] = this.value;
                    }
                });
                // 再转成[{"id": "12", "name": "aaa", "pwd":"pwd1"},{"id": "14", "name": "bb", "pwd":"pwd2"}]的形式
                var vCount = 0;
                // 计算json内部的数组最大长度
                for(var item in jsonData1){
                    var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length : 1;
                    vCount = (tmp > vCount) ? tmp : vCount;
                }

                if(vCount > 1) {
                    var jsonData2 = new Array();
                    for(var i = 0; i < vCount; i++){
                        var jsonObj = {};
                        for(var item in jsonData1) {
                            jsonObj[item] = jsonData1[item][i];
                        }
                        jsonData2.push(jsonObj);
                    }
                    return JSON.stringify(jsonData2);
                }else{
                    return "[" + JSON.stringify(jsonData1) + "]";
                }
            };
        })(jQuery);