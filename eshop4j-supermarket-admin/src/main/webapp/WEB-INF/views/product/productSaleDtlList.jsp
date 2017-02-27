<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <script type="text/javascript" src="assets/plugins/easyui/easyloader.js"  ></script>
<script type="text/javascript">
easyloader.base = 'assets/plugins/easyui/';
easyloader.theme = 'bootstrap';
easyloader.locale = 'zh_CN';

easyloader.modules.common = {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	js: 'common.js'
};
easyloader.modules.my97DatePicker = {
		js: '../../My97DatePicker/WdatePicker.js'
};

</script> -->
 <script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script> 
	<div style="padding: 10px;font-size: 14px;">产品：<font color="red" >${productName }的销售明细</font></div>
    <table id="proSaleDtlDg"   style="width:100%;" ></table>
    <div id="proSaleDtlToolbar"style="display: none;">
        <div>
        	<form id="proSaleDtlQueryForm">
        	<input name="buyUserId"   style="width:110px" id="buyUserId" placeholder="投资者姓名" >
	        	销售时间：<input name="collectBeginTimeStart" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"   style="width:110px" id="collectBeginTimeStart"> 至
	        	<input name="collectBeginTimeEnd" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:110px" id="collectBeginTimeEnd"/>
	        	<input name="productId" type="hidden" value="${productId}" />
		        <a href="#" id="proSaleDtlQueryButton" class="easyui-linkbutton" >&nbsp;&nbsp;查 询&nbsp;&nbsp;</a>
		        <a href="#" id="retBtn"  class="easyui-linkbutton">&nbsp;&nbsp;返回&nbsp;&nbsp;</a> 
		        <!-- <a href="#" id="proSaleDtlExcelButton" class="easyui-linkbutton" >导出EXCEL</a> -->
	        </form>
        </div>
    </div>
    
<script type="text/javascript" src="app/common/util.js"></script>
<script type="text/javascript" src="app/product/productSaleDtl.js"></script>
