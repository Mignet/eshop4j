/**
 * 截取字符串并转化成 超过部分转化成...
 * @param string
 */
function cutFormatString(string,length){
	if(string.trim().length > length){
		return string.substring(0,length)+'...';
	} else {
		return string
	}
}