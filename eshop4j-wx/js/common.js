function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

//取回cookie
function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
function changeItem1(){
	var item1Cnt = document.getElementById('product101').value;
	setCookie('item1Cnt',item1Cnt,7);
	calcTotal();
}
function changeItem2(){
	var item2Cnt = document.getElementById('product102').value;
	setCookie('item2Cnt',item2Cnt,7);
	calcTotal();
}
function calcTotal() {
	var item1Cnt = getNumFromCookie("item1Cnt");
	var item2Cnt = getNumFromCookie("item2Cnt");
	if(document.getElementById("total")){
		document.getElementById("total").innerHTML = (item1Cnt*980+item2Cnt*980) + '.00';
	}
	document.getElementById('itemInCartCnt').innerHTML = item1Cnt+item2Cnt;
	document.getElementById('product101').value = item1Cnt;
	document.getElementById('product102').value = item2Cnt;
	if(item1Cnt==0){
		document.getElementById('item1').style.display = 'none';	
	}else{
		document.getElementById('item1').style.display = 'block';
	}
	if(item2Cnt==0){
		document.getElementById('item2').style.display = 'none';	
	}else{
		document.getElementById('item2').style.display = 'block';
	}
}
function addOneItem1(){
	addItem1(1);
	calcTotal();
}
function addOneItem2(){
	addItem2(1);
	calcTotal();
}
function subOneItem1(){
	subItem1(1);
	calcTotal();
}
function subOneItem2(){
	subItem2(1);
	calcTotal();
}
function getNumFromCookie(cname){
	var cnt = getCookie(cname);
	if(cnt == "" || cnt == 0) {
		cnt = 0;
	}else{
		cnt = cnt|0;
	}
	return cnt;
}
function addItem1(n){
	n = n|0;
	var item1Cnt = getNumFromCookie("item1Cnt");
	setCookie('item1Cnt',item1Cnt+n,7);
}

function addItem2(n){
	n = n|0;
	var item2Cnt = getNumFromCookie("item2Cnt");
	setCookie('item2Cnt',(item2Cnt|0)+n,7);
}

function subItem1(n){
	n = n|0;
	var item1Cnt = getNumFromCookie("item1Cnt");
	item1Cnt = item1Cnt - n;
	if(item1Cnt<0){
		item1Cnt = 0;
	}
	setCookie('item1Cnt',item1Cnt,7);
}

function subItem2(n){
	n = n|0;
	var item2Cnt = getNumFromCookie("item2Cnt");
	item2Cnt = item2Cnt - n;
	if(item2Cnt<0){
		item2Cnt = 0;
	}
	setCookie('item2Cnt',item2Cnt,7);
}