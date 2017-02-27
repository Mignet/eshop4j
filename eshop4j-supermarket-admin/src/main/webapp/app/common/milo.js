/**
 * 处理命名空间
 * @param {string} 空间名称，可多个
 * @return {object} 对象
 */
namespace = function(){
    var argus = arguments;
    for(var i = 0; i < argus.length; i++){
        var objs = argus[i].split(".");
		var obj = window;
        for(var j = 0; j < objs.length; j++){
            obj[objs[j]] = obj[objs[j]] || {};
            obj = obj[objs[j]];
        }
    }
    return obj;
};


namespace("milo.base");

(function(){
	/**
	 * 为对象进行扩展属性和方法
	 * @param {object} object 对象
	 * @return {boolean} 是/否
	 */
	milo.base.extend = function(destination, source) {
		if (destination == null) {
			destination = source
		}
		else {
			for (var property in source){
				if ( getParamType(source[property]).toLowerCase() === "object" &&
					getParamType(destination[property]).toLowerCase() === "object" )
						extend(destination[property], source[property])
				else
					destination[property] = source[property];
			}
		}
		return destination;
	}

	milo.base.extendLess = function(destination, source) {
		var newopt = source;
		for (var i in destination) {
			if (isObject(source) && typeof(source[i]) != 'undefined') {
				destination[i] = newopt[i]
			}
		}
		return destination
	}

	/**
	 * 类式继承类
	 * @param {object} subClass 子类
	 * @param {object} superClass 基础类
	 * @return {undefined}
	 */
	milo.base.extendClass = function(subClass,superClass){
		var F = function(){};
		F.prototype = superClass.prototype;
		subClass.prototype = new F();
		subClass.prototype.constructor = subClass;
		subClass.superclass = superClass.prototype;
		if (superClass.prototype.constructor == Object.prototype.constructor){
			superClass.prototype.constructor = superClass
		}
	}

	/**
	 * 原型继承类
	 * @param {object} object 基类
	 * @return {object} 生成的子类
	 */
	milo.base.cloneClass = function(object){
		if(!isObject(object)) return object;
		if(object == null) return object;
		var F = new Object();
		for(var i in object)
			F[i] = cloneClass(object[i]);
		return F;
	}
	/**
	 * 绑定上下文
	 * @param {function,context} object
	 * @return {object}
	 */
	milo.base.bind = function(fn,context){
		 return function(){
			 return fn.apply(context,arguments);
		 };
	}

	milo.base.extend( milo.base, {
		/**
		 * 判断对象是否定义
		 * 其实只对对象中的元素判断有效，如是纯变量，此方法会无法调用，需要外面加try
		 * @param {object} object 对象
		 * @return {boolean} 是/否
		 */
		isUndefined : function(o){
    		 	return o === undefined && typeof o == "undefined";
    	},
		/**
		 * 判断对象是否数组
		 * @param {object} object 对象
		 * @return {boolean} 是/否
		 */
		isArray : function(obj) {
			return getParamType(obj).toLowerCase() === "array";
		},
		/**
		 * 判断对象是否函数
		 * @param {object} object 对象
		 * @return {boolean} 是/否
		 */
		isFunction : function(obj){
			return getParamType(obj).toLowerCase() === "function";
		},
		/**
		 * 判断对象是否对象
		 * @param {object} object 对象
		 * @return {boolean} 是/否
		 */
		isObject : function(obj) {
			return getParamType(obj).toLowerCase() === "object";
		},
		/**
		 * 判断对象是否数值
		 * @param {object} object 对象
		 * @return {boolean} 是/否
		 */
		isNumber : function(obj) {
			return getParamType(obj).toLowerCase() === "number";
		},
		/**
		 * 判断对象是否字符串
		 * @param {object} object 对象
		 * @return {boolean} 是/否
		 */
		isString : function(obj) {
			return getParamType(obj).toLowerCase() === "string";
		},
		/**
		 * 判断是否布尔值
		 * @param {object} object 对象
		 * @return {boolean} 是/否
		 */
		isBoolean : function(obj) {
			return getParamType(obj).toLowerCase() === "boolean";
		},
		/**
		 * 判断对象是否日期
		 * @param {object} object 对象
		 * @return {boolean} 是/否
		 */
		isDate : function(obj){
			return getParamType(obj).toLowerCase() === "date";
		},

		/**
		 * 判断对象是否DOM元素
		 * @param {object} obj DOM对象
		 * @return {boolean} 是/否
		 */
		isDom : function(obj){
    		try{
    			return obj && typeof obj === "object" && !isUndefined(obj.nodeType) && obj.nodeType==1 && !isUndefined(obj.nodeName) && typeof obj.nodeName == "string";
    		}
    		catch(e){
    			//console.log(e)
    			return false;
    		}
    	},

		/**
		 * 获取DOM对象的值
		 * @param {object} obj DOM对象
		 * @return {string} 取value或innerHTML
		 */
    	getDomVal : function(obj){
    		return obj.value || obj.innerHTML;
    	},
		/**
		 * 索引序列
		 * @param {serial,function} 数组或对象集合
		 * @return {undefined}
		 */
    	forEach : function(haystack, callback) {
			var i = 0,
				length = haystack.length,
				name;

			if (length !== undefined) {
				for (; i < length;) {
					if (callback.call(haystack[i], i, haystack[i++]) === false) {
						break;
					}
				}
			} else {
				for (name in haystack) {
					callback.call(haystack[name], name, haystack[name]);
				}
			}
		},
    	/**
		 * 获取dom对象
		 * @param {string|dom} dom的id或对象k
		 * @return {dom}
		 */
		g : function(obj){
			return (typeof obj=='object')?obj:document.getElementById(obj);
		}
	});

	/**
	 * 获取对象类型
	 * @private
	 * @param {object} object 对象
	 * @return {string} 类型
	 * 可判断类型：Boolean Number String Function Array Date RegExp Object
	 */
	function getParamType(obj){
		return obj == null ? String(obj) :
			Object.prototype.toString.call(obj).replace(/\[object\s+(\w+)\]/i,"$1") || "object";
	}
})();

milo.base.extend(window, milo.base);

/**
 * <p>
 * Example:
 * <pre>
 * <code>
	var a=['1','2','3','4'] ;
	var b=['1','2','5','23432',2] ;
	alert(milo.filter(a,b)) //["3","4"]
	var c = milo.unique(a,b)
	alert(c);//输出["3","4",'5','23432']
 *</code>
 *</pre>
 * </p>
 */

namespace("milo.array");

(function(){
var array = milo.array;
extend( array, {
	/**
	 * 判断数组内容个数
	 * @param {array} array 对象
	 * @return {int} 长度
	 */
	getLength : function(arr){
		var l = 0;
		for(var key in arr){
			l ++;
		}
		return l;
	},
	/**
	 * 复制数组
	 * @param {array} array 对象
	 * @return {array} 新数组对象
	 */
	clone : function(arr){
		var a = [];
		for(var i=0; i<arr.length; ++i) {
			a.push(arr[i]);
		}
		return a;
	},
	/**
	 * 判断数组中是否存在这个值
	 * @param {array} arr 数组对象
	 * @param {object} value 对象
	 * @return {boolean} 是/否
	 */
	hasValue : function(arr, value){
		var find = false;
		if (isArray(arr) || isObject(arr))
			for(var key in arr){
				if (arr[key] == value) find = true;
			}
		return find;
	},
	/**
	 * 根据值获得数组中的key
	 * @param {array} arr 数组对象
	 * @param {object} value 对象
	 * @return {string} key
	 */
	getArrayKey : function(arr, value){
		var findKey = -1;
		if (isArray(arr) || isObject(arr))
			for(var key in arr){
				if (arr[key] == value) findKey = key;
			}
		return findKey;
	},
	/**
	 * 删除数组中指定的value
	 * @param {array} arr 数组对象
	 * @param {object} value 对象
	 * @return {boolean} 是/否
	 */
	remove : function(arr, value){
		var findKey = milo.getArrayKey(arr, value);
		var result = (findKey !==-1);
		result&&arr.splice(findKey,1);
		return result;
	},
	/**
	 * 返回a1数组有a2没有的值
	 * @param {array} a1 数组对象
	 * @param {array} a2 数组对象
	 * @return {array} key
	 */
	filter : function (a1, a2) {
		var res = [];
		for(var i=0;i<a1.length;i++) {
			if(!milo.hasValue(a2, a1[i]))
				res.push(a1[i]);
		}
		return res;
	},
	/**
	 * 两个数组的值的交集
	 * @param {array} arr 数组
	 * @param {array} arr 数组
	 * @return {array} key
	 */
	unique : function (a1, a2) {
		return milo.filter(a1,a2).concat(milo.filter(a2,a1));
	}
});
})();

/**
 * <p>
 * <pre>
 * <code>
 * console.log(milo.toDateString('/')) // 2016/06/21
 *</code></pre>
 * </p>
 */

namespace("milo.date");
(function(){
var date = milo.date;
var _d = new Date();
extend( date, {
	/**
	 * 获取日期
	 * @param {string} sep 分隔符 默认为-
	 * @return {string} yyyy-mm-dd
	 */
	toDateString : function(nd){
		var a=[],
			dt = isDate(nd) ? nd : _d;
			m = dt.getMonth()+1,
			d = dt.getDate(),
			sep = arguments[1] ? arguments[1] : (isString(arguments[0]) ? arguments[0] : "-");
		a.push(dt.getFullYear());
		a.push( m.toString().length < 2 ? "0" + m : m);
		a.push( d.toString().length < 2 ? "0" + d : d);
		return a.join(sep);
	},
	/**
	 * 获取日期和时间
	 * @param {string} sep 分隔符 默认为-
	 * @return {string} yyyy-mm-dd hh:ii:ss
	 */
	toDateTimeString : function(nd){
	    var dt = isDate(nd) ? nd : _d,
			h = dt.getHours(),
			i = dt.getMinutes(),
			s = dt.getSeconds(),
			a = [];
		a.push(h.toString().length < 2 ? "0" + h : h);
		a.push(i.toString().length < 2 ? "0" + i : i);
		a.push(s.toString().length < 2 ? "0" + s : s);
		return date.toDateString.apply(this,arguments) + " " + a.join(":");
	},
	/**
	 * 获取服务器时间
	 * @return {date} Date
	 */
	getSeverDateTime : function(){
		var xhr = window.ActiveXObject ? new ActiveXObject("Microsoft.XMLHTTP") : new XMLHttpRequest();
		xhr.open("HEAD", window.location.href, false);
		xhr.send();
		var d= new Date(xhr.getResponseHeader("Date"));
		return d;
	}
});
})();

/**
 * @author cathzhang
 * @version 0.1.0.0
 * @date 2012-06-01
 * @class milo.object
 * 对象处理通用方法
 */

namespace("milo.object");

(function(){

extend( milo.object, {

	/**
	 * 格式化json对象
	 * @param  {[obj]} data
	 * @return {[json]}    json
	 */
	jsonFromt : function jsonFromt(data) {
		var json = {};
		for (var i = 0; i < data.length; i++) {
			json[data[i].name] = data[i].value;
		}
		return json;
	},
	/**
	 * 序列化JSON对象
	 * 对object转化为url参数字符串，各属性间以&分隔，如a=1&b=2&c=3
	 * 对象属性为string 则进行encodeURIComponent编码
	 * 对象属性为bool 则以0代表false 1代表true
	 * 对象属性为对象，则会继续进行递归序列化
	 * 对象属性为function 则返回function.toString
	 * @param {object} jsonObj json对象
	 * @return {string}
	 */
	serialize : function(jsonObj){
		var newJsonObj = null;
		if (typeof(jsonObj) == 'undefined' || typeof(jsonObj) == 'function')
			newJsonObj = '';
		if (typeof(jsonObj) == 'number')
			newJsonObj = jsonObj.toString();
		if (typeof(jsonObj) == 'boolean')
			newJsonObj = (jsonObj) ? '1' : '0';
		if (typeof(jsonObj) == 'object') {
			if (!jsonObj) newJsonObj = '';
			if (jsonObj instanceof RegExp) newJsonObj = jsonObj.toString();
		}
		if (typeof(jsonObj) == 'string')
			newJsonObj = jsonObj;
		if (typeof(newJsonObj) == 'string')
			return encodeURIComponent(newJsonObj);

		var ret = [];
		if (jsonObj instanceof Array) {
			for (var i = 0; i < jsonObj.length; i++) {
				if (typeof(jsonObj[i]) == 'undefined') 	continue;
				ret.push(typeof(jsonObj[i]) == 'object' ? '' : milo.serialize(jsonObj[i]))
			}
			return ret.join('|')
		}
		else {
			for (var i in jsonObj) {
				if (typeof(jsonObj[i]) == 'undefined') 	continue;
				newJsonObj = null;
				if (typeof(jsonObj[i]) == 'object') {
					if (jsonObj[i] instanceof Array) {
						newJsonObj = jsonObj[i];
						ret.push(i + '=' + milo.serialize(newJsonObj));
					} else {
						ret.push(i + '=')
					}
				} else {
					newJsonObj = jsonObj[i];
					ret.push(i + '=' + milo.serialize(newJsonObj));
				}
			}
			return ret.join('&')
		}
	},
	/**
	 * 反序列化为JSON对象
	 * 对url参形形式的对象反序列化成为JSON对象
	 * 与serialize相对应
	 * @param {object} jsonObj json对象
	 * @return {string}
	 */
	unSerialize : function(jsonStr, de){
		de = de || 0;
		jsonStr = jsonStr.toString();
		if (!jsonStr) return {};
		var retObj = {},
			obj1Ret = jsonStr.split('&');
		if (obj1Ret.length == 0) return retObj
		for (var i = 0; i < obj1Ret.length; i++) {
			if (!obj1Ret[i]) continue;
			var ret2 = obj1Ret[i].split('=');
			if (ret2.length >= 2) {
				var ret0 = obj1Ret[i].substr(0, obj1Ret[i].indexOf('=')),
					ret1 = obj1Ret[i].substr(obj1Ret[i].indexOf('=') + 1);
				if (!ret1) ret1 = '';
				if (ret0) retObj[ret0] = de == 0? decodeURIComponent(ret1) : ret1;
			}
		}
		return retObj;
	},
	/**
	 * 对整个object进行utf8格式的url解码
	 * @param {object} newopt 解码对象
	 * @return {object} 已解码对象
	 */
	decode : function(newopt) {
		if (typeof(newopt) == 'string') {
			try {
				return decodeURIComponent(newopt)
			} catch(e) {}
			return newopt
		}
		if (typeof(newopt) == 'object') {
			if (newopt == null) {
				return null
			}
			if (newopt instanceof Array) {
				for (var i = 0; i < newopt.length; i++) {
					newopt[i] = milo.decode(newopt[i])
				}
				return newopt
			} else if (newopt instanceof RegExp) {
				return newopt
			} else {
				for (var i in newopt) {
					newopt[i] = milo.decode(newopt[i])
				}
				return newopt
			}
		}
		return newopt
	}

});
})();

milo.base.extend(milo, milo.array);
milo.base.extend(milo, milo.date);
milo.base.extend(milo, milo.object);

