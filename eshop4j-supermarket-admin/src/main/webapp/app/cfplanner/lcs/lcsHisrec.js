using(['common','datagrid'],function(){
	lh.inintDatagrid('#lcsHisrecDg',null,'../lcsList/getHisrec.htm?lcsNumber='+$("#win input[name='lcsNumber']").val(),lcsHisrecModel);
});