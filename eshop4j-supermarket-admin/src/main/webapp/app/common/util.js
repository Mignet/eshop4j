//公用js

//讲form序列化数组转换为JSON
function jsonFromt(data) {
	var json = {};
	for (var i = 0; i < data.length; i++) {
		json[data[i].name] = data[i].value;
	}
	return json;
}

function formateDate1(date){
	if(date == null || date.toString()=='Invalid Date' || !date instanceof Date){
		return "";
	}
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	if(m<10){
		m = '0' + m;
	}
	var d = date.getDate();
	if(d<10){
		d = '0' + d;
	}
	var h = date.getHours();
	if(h<10){
		h = '0' + h;
	}
	var m1 = date.getMinutes();
	if(m1<10){
		m1 = '0' + m1;
	}
	var s = date.getSeconds();
	if(s<10){
		s = '0' + s;
	}
	return y + "-"+ m + "-" + d + " " + h + ":" + m1 + ":" + s;
}

//密码校验
function vPass(v_pw){
	var patrn_shuzi=/^[0-9]$/; 
	var patrn_zimu=/^[a-z]|[A-Z]$/;
	var patrn_teshu=/^[^A-Za-z0-9]$/;
	var flag = 0;
	var s = v_pw.split('');
	console.info(s);
	    
	for(var i=0;i<s.length;i++){
	  if (patrn_shuzi.exec(s[i])) {
	     flag = flag+1;
	     break;
	  }
	}
	for(var i=0;i<s.length;i++){
	  if (patrn_zimu.exec(s[i])) {
	    flag = flag+1;
	     break;
	  }
	}
	for(var i=0;i<s.length;i++){
	  if (patrn_teshu.exec(s[i])) {
	     flag = flag+1;
	     break;
	  }
	}

	if (flag <= 1) {	
	return false;
	}else{
	return true;
	}
}
