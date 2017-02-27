$(document).ready(function() {
					window['rproductIds'] = [];
					$("body").off("click", "a.J_productId");
					$("body").on("click", "a.J_productId", function(event) {
						var array = window['rproductIds'];
						var $target = $(event.target);
						var pid = $target.attr("data-pid");
						if (milo.hasValue(array, pid)) {
							if (milo.remove(array, pid))
								$target.text("绑定");
						} else {
							array.push(pid);
							$target.text("取消绑定");
						}
						return false;
					});
					var $dataTable = $("#redpaper-product-list-table")
							.DataTable(
									{
										ordering : false,
										searching : false,
										lengthChange : false,
										paging : true,
										select : false,
										serverSide : true,
										deferLoading : false,
										dom : '<"J_redpaperProductList_toolbar">frtip',
										ajax : {
											url : "./rest/redpaper/queryBindingRedpaperProductList",
											type : "POST",
											dataSrc : function(result) {
												return result.data;
											},
											data : function(d) {
												d.pname = $('.J_redpaperProductList_toolbar input[name=pname]').val();
												d.pdeadline = $('.J_redpaperProductList_toolbar select[name=pdeadline]').val();
												return {'dt_json' : JSON.stringify(d)};
											}
										},
										columns : [
												{
													data : "productTypeId",
													width : 60
												},
												{
													data : "productName"
												},
												{
													data : "deadLineValue",
													width : 60
												},
												{
													data : "productId",
													width : 60,
													render : function(data,type, row) {
														var isBinding = milo.hasValue(window['rproductIds'],row.productId);
														return $("<a>").text(isBinding ? '取消绑定': '绑定').attr({
																			'href' : 'javascript:;',
																			'data-pid' : row.productId
																		}).addClass('J_productId')[0].outerHTML;
													}
												}

										],
										language : {
											"emptyTable" : "没有数据表",
											info : "显示第 _START_  至 _END_  项结果，共 _TOTAL_ 项",
											infoEmpty : "",
											paginate : {
												"first" : "首页",
												"next" : "下一页",
												"previous" : "上一页"
											}
										}
									});
					$(".J_redpaperProductList_toolbar").html(
							$("#template-search").html());
					$("a.J_redpaperProductList_search").click(function() {
						$dataTable.draw();
					});
				});