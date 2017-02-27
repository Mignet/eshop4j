<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style type="text/css">
	.span-hint{
		padding-top: 10px;
		display: inline-block;
		color: blue;
		font-size: 10px;
	}
	.span-unit{
		padding-top: 10px;
		display: inline-block;
		font-size: 10px;
	}

</style>

<div class="container">
	<form id="orgFeeForm" action="/rest/cim/cimorginfo/addOrgFee" method="post">
		<input type="hidden" name="orgNumber" value="${orgNumber}" /><!-- 机构编码 -->
		<div class="row">
				<div class="page-header">
					<span  style="color: blue;font-size: 15px;">该机构旗下的所有产品都按以下方式对机构进行收费、对理财师进行结佣。CPA + CPS模式同时选中时按CPS模式计算佣金。</span>
				</div>
	                  
				<div class="row">
					<div class="col-md-12 page-header">
		                      <div class="form-group">
		                          <label class="col-md-9"><strong>CPA &nbsp;(按投资人数量进行收费)</strong></label>
		                      </div>
		             </div>
	   		    </div> 
	   		    
	   		    <div class="row">
					<div class="col-md-12">
						<div class="form-group">
							  <label class="col-md-2"></label>
		                      <div class="col-md-9 page-header checkbox">
		                      		<label>
		                          		<input type="checkbox" id="cpacbo" name="cpaFeeType" value="cpa" /><strong>CPA收费方式</strong>
		                          	</label>
		                      </div>
		                  </div>
		             </div>
				</div>
				
				
		         <div class="row" id="cpaMenu" name="cpaMenu" style="display: none;">
						<div class="col-md-12">
		                      <label class="col-md-2"></label>
		                          <div class="col-md-10">
		                          		<div class="row">
												<label class="col-md-3 form-group radio">
												   <input type="radio" name="cpaFeeAttr" id="fixed" value="fixed" />每个新投资人固定费用：
												</label>
												<div id="fixedContent" style="display: none;">
													<span  class="span-unit">元</span>
													<div class="form-group col-md-2" style="margin-left: -60px">
													  <input type="text" class="form-control" name="fixedMoney" autocomplete="off"  />
													</div>
												</div>
										</div>
										
										<div class="row">
												 <label class="col-md-4 form-group radio">
												   <input type="radio" name="cpaFeeAttr" id="propertion" value="propertion" />根据首投金额的比例进行收费：
												</label>
												<div id="propertionContent" style="display: none;">
													<span  class="span-unit">%</span>
													<div class="form-group col-md-2" style="margin-left: -100px">
													  <input type="text" class="form-control" name="fixedMoneyRatio" autocomplete="off"  />
													</div>
												</div>
										</div>
										
										<div class="row">
												<label class="col-md-3 form-group radio">
												  <input type="radio" name="cpaFeeAttr" value="float_fixed">
												  	按首投金额，填写以下表格：
												</label>
										</div>
										
										<div class="row" id="firstInvestTab" style="display: none;">
											<div class="col-md-9">
												<table class="table table-bordered table-hover" id="firstInvestTable">
												 <span style="color:red;">*请按以下示例输入合法的首投金额区间,否则会导致收费计算异常。(以下示例中1000元以上只需要输入最小区间首投金额)</span>
												  <thead>
												    <tr class="success">
												      <th class="text-center">输入首投金额区间&nbsp(元)</th>
												      <th class="text-center">输入收费金额&nbsp(元)</th>
												      <th class="text-center">操作</th>
												    </tr>
												  </thead>
												  <tbody>
												    <tr id="feeRecords-1">
												      <td class="col-md-8">
													      <div class="form-group col-md-4" style="margin: 10px 0px 0px 50px;">
														      <input type="text" class="form-control text-center" min="1" digits="true" name="orgFeeRecords_feeRecords1.intervalMinVal" autocomplete="off" placeholder="1" required="required"/>
														  </div>
														  <div class="col-md-1" style="margin-top: 18px;">
														   &#126;
														  </div>
														  <div class="form-group col-md-4" style="margin-top: 10px;">
														      <input type="text" class="form-control text-center" min="1" digits="true" name="orgFeeRecords_feeRecords1.intervalMaxVal" autocomplete="off"  placeholder="500" />
														  </div>
												      </td>
												      <td>
												      		<div class="form-group" style="margin-top: 10px;">
														      <input type="text" class="form-control text-center" min="0" number="true" name="orgFeeRecords_feeRecords1.feeVal" autocomplete="off" placeholder="收费金额" required="required"/>
														    </div>
														    
												      </td>
												      <td>
												      		<div style="margin-top: 10px;">
									                          <button type="button" class="btn btn-default btn-danger" onclick="deleteFirstInvestRow(this);" data-feerowid="feeRecords-1"><i class="fa fa-trash-o"></i> 删除</button>
									                        </div>
												      </td>
												    </tr>
												    <tr id="feeRecords-2">
												      <td class="col-md-8">
													      <div class="form-group col-md-4" style="margin: 10px 0px 0px 50px;">
														      <input type="text" class="form-control text-center" min="1" digits="true" name="orgFeeRecords_feeRecords2.intervalMinVal" autocomplete="off" placeholder="501" required="required"/>
														  </div>
														  <div class="col-md-1" style="margin-top: 18px;">
														   &#126;
														  </div>
														  <div class="form-group col-md-4" style="margin-top: 10px;">
														      <input type="text" class="form-control text-center" min="1" digits="true" name="orgFeeRecords_feeRecords2.intervalMaxVal" autocomplete="off"  placeholder="1000" />
														  </div>
												      </td>
												      <td>
												      		<div class="form-group" style="margin-top: 10px;">
														      <input type="text" class="form-control text-center" min="0" number="true" name="orgFeeRecords_feeRecords2.feeVal" autocomplete="off" placeholder="收费金额" required="required"/>
														    </div>
												      </td>
												      <td>
														    <div style="margin-top: 10px;">
									                          <!-- return false 阻止表单提交 -->
									                          <button type="button" class="btn btn-default btn-danger" onclick="deleteFirstInvestRow(this);" data-feerowid="feeRecords-2"><i class="fa fa-trash-o"></i> 删除</button>
									                        </div>
												      </td>
												    </tr>
												    <tr id="feeRecords-3">
												      <td class="col-md-8">
													      <div class="form-group col-md-4" style="margin: 10px 0px 0px 50px;">
														      <input type="text" class="form-control text-center" min="1" digits="true" name="orgFeeRecords_feeRecords3.intervalMinVal" autocomplete="off" placeholder="1001" required="required"/>
														  </div>
														  <div class="col-md-1" style="margin-top: 18px;">
														   &#126;
														  </div>
														  <div class="form-group col-md-4" style="margin-top: 10px;">
														      <input type="text" class="form-control text-center" min="1" digits="true" name="orgFeeRecords_feeRecords3.intervalMaxVal" autocomplete="off" />
														  </div>
												      </td>
												      <td>
												      		<div class="form-group" style="margin-top: 10px;">
														      <input type="text" class="form-control text-center" min="0" number="true" name="orgFeeRecords_feeRecords3.feeVal" autocomplete="off" placeholder="收费金额" required="required"/>
														    </div>
														    
												      </td>
												      <td>
														    <div style="margin-top: 10px;">
									                          <!-- return false 阻止表单提交 -->
									                          <button type="button" class="btn btn-default btn-danger" onclick="deleteFirstInvestRow(this);" data-feerowid="feeRecords-3"><i class="fa fa-trash-o"></i> 删除</button>
									                        </div>
												      </td>
												    </tr>
												  </tbody>
												</table>
											</div>
											
											
											<div class="row" id="addFirstInvest">
												<div class="col-md-12">
									                      <div class="form-group">
									                          <label class="col-md-4"></label>
									                          <div class="col-md-8">
									                               <button type="button" class="btn btn-default" onclick="addFirstInvestRow(this);"><i class="fa fa-plus"></i> 新增</button>
									                          </div>
									                      </div>
									             </div>
									  		 </div> 
									  		 
										</div>
										
										
		                          </div>
			             </div>
			             
			             
			    <div class="row">
					<div class="col-md-12">
							  <label class="col-md-1"></label>
		                      <div class="col-md-9 page-header">
		                          		<strong>限投条件设置<span style="color: red;">(注意：CPA和CPS同时被选中时则可以不设置...)</span></strong>
		                      </div>
		             </div>
	   		    </div> 
	   		    
	   		    <div class="row" >
	   		    	<div class="col-md-12">
	                      <label class="col-md-2"></label>
	                      <div class="col-md-10">
	                      	 <div class="row" >
								 	<label class="col-md-2" style="margin-top: 8px;">限投金额：</label>
								 	<span  class="span-hint">超过该金额，给理财师结算时按该金额计算</span>
									<div class="form-group col-md-2" style="margin-left: -60px">
									  <input type="text" class="form-control" name="orgAmountLimit" autocomplete="off"  />
									</div>
									<div class="form-group col-md-1" style="margin: 10px 0px 0px -15px">元</div>
							 </div>
							 
							 <div class="row" >
								 	<label class="col-md-2" style="margin-top: 8px;">限投期限：</label>
								 	<span  class="span-hint">超过该期限，给理财师结算时按该期限计算</span>
									<div class="form-group col-md-2" style="margin-left: -60px">
									  <input type="text" class="form-control" name="orgInvestdeadlineLimit" autocomplete="off"  />
									</div>
									<div class="form-group col-md-1" style="margin: 10px 0px 0px -15px">天</div>
									<!-- 
									<div class="input-append">
									  <input id="appendedInput" type="text">
									  <span class="add-on">.00</span>
									</div>
									-->
							 </div>
						 </div>
					</div>
				</div>
			             
			             
	   		</div> 
	   		    
	   		    
	   		     
				
				
				
				
				<div class="row" style="margin-top: 80px;">
					<div class="col-md-12 page-header">
		                      <div class="form-group">
		                          <label class="col-md-9"><strong>CPS &nbsp;(根据年化投资金额，按比例进行收费)</strong></label>
		                      </div>
		             </div>
	   		    </div> 
	   		    
	   		    <div class="row">
					<div class="col-md-12">
						<div class="form-group">
							  <label class="col-md-2"></label>
		                      <div class="col-md-9 page-header checkbox">
		                      		<label>
		                          		<input type="checkbox" id="cpscbo" name="cpsFeeType" value="cps"/><strong>CPS收费方式</strong>
		                          	</label>
		                      </div>
		                  </div>
		             </div>
				</div>
				
				
		         <div class="row" id="cpsMenu" name="cpsMenu" style="display: none;">
						<div class="col-md-12">
		                      <label class="col-md-2"></label>
		                          <div class="col-md-10">
										
										<div class="row">
												<label class="col-md-3 radio">
												  <input type="radio" name="cpsFeeAttr" value="year_propertion">
												  	按产品期限，填写以下表格：
												</label>
										</div>
										
										<div class="row" id="productDeadlineTab" style="display: none;">
											<div class="col-md-9">
												<table class="table table-bordered table-hover" id="productDeadlineTable">
												<span style="color:red;">*请按以下示例输入合法的产品期限区间,否则会导致收费计算异常。(以下示例中90天以上只需要输入最小区间产品期限)</span>
												  <thead>
												    <tr class="success">
												      <th class="text-center">输入产品期限区间&nbsp(天)</th>
												      <th class="text-center">输入收费比例&nbsp(%)</th>
												      <th class="text-center">操作</th>
												    </tr>
												  </thead>
												  <tbody>
												    <tr id="feeRecords-1000">
													    <td class="col-md-8">
														      <div class="form-group col-md-4" style="margin: 10px 0px 0px 50px;">
															      <input type="text" class="form-control text-center" min="1" digits="true" required="required" name="orgFeeRecords_feeRecords1000.intervalMinVal" autocomplete="off" placeholder="1" />
															  </div>
															  <div class="col-md-1" style="margin-top: 18px;">
															   &#126;
															  </div>
															  <div class="form-group col-md-4" style="margin-top: 10px;">
															      <input type="text" class="form-control text-center" min="1" digits="true" name="orgFeeRecords_feeRecords1000.intervalMaxVal" autocomplete="off"  placeholder="30" />
															  </div>
													      </td>
													      
													      <td>
													      		<div class="form-group" style="margin-top: 10px;">
															      <input type="text" class="form-control text-center" min="0" number="true" required="required" name="orgFeeRecords_feeRecords1000.moneyRatio" autocomplete="off" placeholder="收费比例" />
															    </div>
															    
													      </td>
													      <td>
													      		<div style="margin-top: 10px;">
										                          <button type="button" class="btn btn-default btn-danger" onclick="deleteProductDeadlineRow(this);" data-feerowid="feeRecords-1000"><i class="fa fa-trash-o"></i> 删除</button>
										                        </div>
													      </td>
												    </tr>
												    
												    <tr id="feeRecords-1001">
												      <td class="col-md-8">
													      <div class="form-group col-md-4" style="margin: 10px 0px 0px 50px;">
														      <input type="text" class="form-control text-center" min="1" digits="true" required="required" name="orgFeeRecords_feeRecords1001.intervalMinVal" autocomplete="off" placeholder="31" />
														  </div>
														  <div class="col-md-1" style="margin-top: 18px;">
														   &#126;
														  </div>
														  <div class="form-group col-md-4" style="margin-top: 10px;">
														      <input type="text" class="form-control text-center" min="1" digits="true" name="orgFeeRecords_feeRecords1001.intervalMaxVal" autocomplete="off"  placeholder="90" />
														  </div>
												      </td>
												      <td>
												      		<div class="form-group" style="margin-top: 10px;">
														      <input type="text" class="form-control text-center" min="0" number="true" required="required" name="orgFeeRecords_feeRecords1001.moneyRatio" autocomplete="off" placeholder="收费比例" />
														    </div>
														    
												      </td>
												      <td>
												      		<div style="margin-top: 10px;">
									                          <button type="button" class="btn btn-default btn-danger" onclick="deleteProductDeadlineRow(this);" data-feerowid="feeRecords-1001"><i class="fa fa-trash-o"></i> 删除</button>
									                        </div>
												      </td>
												    </tr>
												    <tr id="feeRecords-1002">
												      <td class="col-md-8">
													      <div class="form-group col-md-4" style="margin: 10px 0px 0px 50px;">
														      <input type="text" class="form-control text-center" min="1" digits="true" required="required" name="orgFeeRecords_feeRecords1002.intervalMinVal" autocomplete="off" placeholder="91" />
														  </div>
														  <div class="col-md-1" style="margin-top: 18px;">
														   &#126;
														  </div>
														  <div class="form-group col-md-4" style="margin-top: 10px;">
														      <input type="text" class="form-control text-center" min="1" digits="true" name="orgFeeRecords_feeRecords1002.intervalMaxVal" autocomplete="off"  />
														  </div>
												      </td>
												      <td>
												      		<div class="form-group" style="margin-top: 10px;">
														      <input type="text" class="form-control text-center" min="0" number="true" required="required" name="orgFeeRecords_feeRecords1002.moneyRatio" autocomplete="off" placeholder="收费比例" />
														    </div>
														   
												      </td>
												      <td>
												       		<div style="margin-top: 10px;">
									                          <button type="button" class="btn btn-default btn-danger" onclick="deleteProductDeadlineRow(this);" data-feerowid="feeRecords-1002"><i class="fa fa-trash-o"></i> 删除</button>
									                        </div>
												      </td>
												    </tr>
												  </tbody>
												</table>
											</div>
											<div class="row" id="addProductDeadline">
												<div class="col-md-12">
									                      <div class="form-group">
									                          <label class="col-md-4"></label>
									                          <div class="col-md-8">
									                               <button type="button" class="btn btn-default" onclick="addProductDeadlineRow(this);"><i class="fa fa-plus"></i> 新增</button>
									                          </div>
									                      </div>
									             </div>
									    	</div>
									    
										</div>
										
										 
										
										<div class="row">
												<label class="col-md-3 radio">
												  <input type="radio" name="cpsFeeAttr" value="month_amount_propertion">
												  	按月销售总额，填写以下表格：
												</label>
										</div>
										
										<div class="row" id="monthSaleTotalTab" style="display: none;">
											<div class="col-md-9">
												<table class="table table-bordered table-hover" id="monthSaleTotalTable">
												<span style="color:red;">*请按以下示例输入合法的月销售总额区间,否则会导致收费计算异常。(以下示例中5000万以上只需要输入最小区间总额)</span>
												  <thead>
												    <tr class="success">
												      <th class="text-center">输入月销售总额区间&nbsp(元)</th>
												      <th class="text-center">输入收费比例&nbsp(%)</th>
												      <th class="text-center">操作</th>
												    </tr>
												  </thead>
												  <tbody>
												    <tr id="feeRecords-2000">
												      <td class="col-md-8">
													      <div class="form-group col-md-4" style="margin: 10px 0px 0px 50px;">
														      <input type="text" class="form-control text-center" min="1" digits="true" required="required" name="orgFeeRecords_feeRecords2000.intervalMinVal" autocomplete="off" placeholder="1" />
														  </div>
														  <div class="col-md-1" style="margin-top: 18px;">
														   &#126;
														  </div>
														  <div class="form-group col-md-4" style="margin-top: 10px;">
														      <input type="text" class="form-control text-center" min="1" digits="true" name="orgFeeRecords_feeRecords2000.intervalMaxVal" autocomplete="off"  placeholder="10000000" />
														  </div>
												      </td>
												      <td>
												      		<div class="form-group" style="margin-top: 10px;">
														      <input type="text" class="form-control text-center" min="0" number="true" required="required" name="orgFeeRecords_feeRecords2000.moneyRatio" autocomplete="off" placeholder="收费比例" />
														    </div>
												      </td>
												      <td>
														    <div style="margin-top: 10px;">
									                          <button type="button" class="btn btn-default btn-danger" onclick="deleteMonthSaleTotalRow(this);" data-feerowid="feeRecords-2000"><i class="fa fa-trash-o"></i> 删除</button>
									                        </div>
												      </td>
												    </tr>
												    <tr id="feeRecords-2001">
												      <td class="col-md-8">
													      <div class="form-group col-md-4" style="margin: 10px 0px 0px 50px;">
														      <input type="text" class="form-control text-center" min="1" digits="true" required="required" name="orgFeeRecords_feeRecords2001.intervalMinVal" autocomplete="off" placeholder="10000001" />
														  </div>
														  <div class="col-md-1" style="margin-top: 18px;">
														   &#126;
														  </div>
														  <div class="form-group col-md-4" style="margin-top: 10px;">
														      <input type="text" class="form-control text-center" min="1" digits="true" name="orgFeeRecords_feeRecords2001.intervalMaxVal" autocomplete="off"  placeholder="50000000" />
														  </div>
												      </td>
												      <td>
												      		<div class="form-group" style="margin-top: 10px;">
														      <input type="text" class="form-control text-center" min="0" number="true" required="required" name="orgFeeRecords_feeRecords2001.moneyRatio" autocomplete="off" placeholder="收费比例" />
														    </div>
												      </td>
												      <td>
														    <div style="margin-top: 10px;">
									                          <button type="button" class="btn btn-default btn-danger" onclick="deleteMonthSaleTotalRow(this);" data-feerowid="feeRecords-2001"><i class="fa fa-trash-o"></i> 删除</button>
									                        </div>
												      </td>
												    </tr>
												    <tr id="feeRecords-2002">
												      <td class="col-md-8">
													      <div class="form-group col-md-4" style="margin: 10px 0px 0px 50px;">
														      <input type="text" class="form-control text-center" min="1" digits="true"  required="required" name="orgFeeRecords_feeRecords2002.intervalMinVal" autocomplete="off" placeholder="50000001" />
														  </div>
														  <div class="col-md-1" style="margin-top: 18px;">
														   &#126;
														  </div>
														  <div class="form-group col-md-4" style="margin-top: 10px;">
														      <input type="text" class="form-control text-center" min="1" digits="true" name="orgFeeRecords_feeRecords2002.intervalMaxVal" autocomplete="off" />
														  </div>
												      </td>
												      <td>
												      		<div class="form-group" style="margin-top: 10px;">
														      <input type="text" class="form-control text-center" min="0" number="true" required="required" name="orgFeeRecords_feeRecords2002.moneyRatio" autocomplete="off" placeholder="收费比例" />
														    </div>
												      </td>
												      <td>
														    <div style="margin-top: 10px;">
									                          <button type="button" class="btn btn-default btn-danger" onclick="deleteMonthSaleTotalRow(this);" data-feerowid="feeRecords-2002"><i class="fa fa-trash-o"></i> 删除</button>
									                        </div>
												      </td>
												    </tr>
												  </tbody>
												</table>
											</div>
											
											<div class="row" id="addMonthSaleTotal">
												<div class="col-md-12">
									                      <div class="form-group">
									                          <label class="col-md-4"></label>
									                          <div class="col-md-8">
									                               <button type="button" class="btn btn-default" onclick="addMonthSaleTotalRow(this);"><i class="fa fa-plus"></i> 新增</button>
									                          </div>
									                      </div>
									             </div>
									   		</div> 
										</div>
										
		                         </div>
			             </div>
	   		    </div> 
	   		    
	   	</div> 

	   	
	    <div class="row" style="padding-top: 100px;">
				<div class="col-md-12">
	                      <div class="form-group">
	                          <label class="col-md-5"></label>
	                          <div class="col-md-7">
	                               <button type="submit" class="btn btn-primary"><i class="fa fa-check"></i> 保存</button>
	                               &nbsp&nbsp&nbsp
	                               <button type="button" class="btn btn-default" onclick='javascript:$.switchPage("合作机构列表","rest/cim/cimorginfo/list");'><i class="fa fa-arrow-left"></i> 返回</button>
	                          </div>
	                      </div>
	             </div>
	    </div>                   	
	
	</form>	
	
</div>

<script type="text/javascript" src="app/cimorginfo/formdata-convert-tojson.js"></script>
<script type="text/javascript" src="app/cimorginfo/cimorgfeerecord-add.js"></script>