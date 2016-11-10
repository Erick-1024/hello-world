$(function() {
	// 初始化控件
	initControl();
	// 初始化
	initEvent();
});

/**
 * 初始化控件
 */
function initEvent() {
	// 赎回
	$('body').on('click', 'a[name=redeemAssetPool]', function() {
		$('a[name=confirmBoxOK]').attr('underlyingAssetId', $(this).attr('underlyingAssetId'));
		$('a[name=confirmBoxOK]').attr('action', 'redeemAssetPool');
		showConfirmBox("确定赎回" + $($(this).parent().parent().children()[0]).text() + "基础资产吗？");
	});

	// 待入池
	$('body').on('click', 'a[name=outAssetPoolAndKeepBind]', function() {
		$('a[name=confirmBoxOK]').attr('underlyingAssetId', $(this).attr('underlyingAssetId'));
		$('a[name=confirmBoxOK]').attr('action', 'outAssetPoolAndKeepBind');
		showConfirmBox("确定待入池" + $($(this).parent().parent().children()[0]).text() + "基础资产吗？");
	});

	// 出池
	$('body').on('click', 'a[name=underlyingAssetId]', function() {
		$('a[name=confirmBoxOK]').attr('underlyingAssetId', $(this).attr('underlyingAssetId'));
		$('a[name=confirmBoxOK]').attr('action', 'underlyingAssetId');
		showConfirmBox("确定出池" + $($(this).parent().parent().children()[0]).text() + "基础资产吗？");
	});

	// 根据操作类型调用 赎回、待入池、出池
	$('body').on('click', 'a[name=confirmBoxOK]', function() {
		var action = $(this).attr('action');
		if (action == "redeemAssetPool") {
			cana.get(basepath + "/asset/pool/redeemAssetPool?underlyingAssetId=" + $(this) .attr('underlyingAssetId'), null, deleteSuccess, deleteFail, null);
		}
		if (action == "outAssetPoolAndKeepBind") {
			cana.get(basepath + "/asset/pool/outAssetPoolAndKeepBind?underlyingAssetId=" + $(this).attr('underlyingAssetId'), null, deleteSuccess, deleteFail, null);
		}
		if (action == "underlyingAssetId") {
			cana.get(basepath + "/asset/pool/outAssetPoolAndDelete?underlyingAssetId=" + $(this).attr('underlyingAssetId'), null, deleteSuccess, deleteFail, null);
		}
		confirmPopWindow.close();
	});

	// 打开历史明细弹窗
	$("body").on("click", 'a[name=history]', function() {
		$(".open-history-btn").click();
		$(".k-widget.k-window").css("top", "20%");
		var loanId = $(this).attr('underlyingAssetId');
		var customerName = $(this).attr('customerName') == 'null' ? '' : $(this).attr('customerName');
		var counterparty = $(this).attr('counterparty') == 'null' ? '' : $(this).attr('counterparty');
		var repaymentMethod = $(this).attr('repaymentMethod') == 'null' ? '' : $(this).attr('repaymentMethod');
		$('#popWindowLoanId').text(loanId);
		$('#popWindowCustomerName').text(customerName);
		$('#popWindowCounterparty').text(counterparty);
		
		// 初始化和加载历史明细信息
		loadHistoryGrid(loanId, repaymentMethod);
	});

	// 还款计划
	$("body").on("click", 'a[name=loanPaid]', function() {
		$(".open-paid-btn").click();
		$(".k-widget.k-window").css("top", "20%");
		var loanId = $(this).attr('underlyingAssetId');
		
		// 初始化和加载还款计划信息
		loadLoanPaidGrid(loanId);
	});

	// 入池
	$("body").on("click", 'a[name=assetpoolEnter]', function() {
		location.href = encodeURI(basepath + "/asset/pool/assetpoolEnter?id=" + $("#specialProgramId").val() + "&status="+  $("#status").val());
	});
}

/**
 * 操作成功
 * @param data
 */
function deleteSuccess(data) {
	$(".open-message-btn").click();
	$("#tip-box-window .dlg-notice").addClass("notice-icon02");
	$("#tip-box-window .notice-content").text("操作成功");
	setTimeout("gotoPage()",1000);
}


/**
 * 刷新当前页面
 */
function gotoPage() {
	location.href = encodeURI(basepath + "/asset/pool/assetpoolManage?id=" + $("#specialProgramId").val() + "&status="+  $("#status").val());
}

/**
 * 操作失败
 * @param data
 */
function deleteFail(data) {
	$(".open-message-btn").click();
	$("#tip-box-window .dlg-notice").addClass("notice-icon03");
	$("#tip-box-window .notice-content").text(data);
}

/**
 * 初始化控件
 */
function initControl() {
	var status =  $('#status').val();
	// 入池权限 + 如果状态“成立”，可循环购买 + 如果状态不是“结束”
	if (enterAuth && status !="CLOSE") {
		$('a[name="assetpoolEnter"]').show();
		
		if (status == "ESTABLISH" && $('#cyclePurchaseStructure').val() == 'false') {
			$('a[name="assetpoolEnter"]').hide();
		} 
	}
	
	// 初始化历史明细弹窗
	new PopWindow(".open-history-btn", {
		title : "历史还款明细",
		width : 1000,
		reload : true,
		template : "#template-history"
	}).init();

	new PopWindow(".open-paid-btn", {
		title : "还款计划",
		width : 1000,
		reload : true,
		template : "#template-paid"
	}).init();

	// 初始化消息弹窗
	new PopWindow(".open-message-btn", {
		title : "提示",
		width : 400,
		reload : true,
		template : "#tipBox_template"
	}).init();
	
	loadData();
}

/**
 * 加载数据
 */
function loadData() {
	$("#monitorList-grid").empty();
	// 初始化表格
	var grid = $("#monitorList-grid").kendoGrid({
		selectable : "row", // 设置可选择数据行
		sortable : true, // 列排序
		dataSource : {
			type : "json", // 后台返回的数据类型
			method : "post",
			transport : {
				read : {
					data : { id : $("#specialProgramId").val() },
					type : "POST",
					url : basepath + "/asset/pool/assetpoolManageList"
				}
			},
			// 解析远程响应的数据
			serverPaging : true,
			serverSorting : true,
			serverFiltering : true,
			schema : {
				data : "data",
				total : function(data) {
					var total = data.totalNum;
					if (total == 0) {
						$("#monitorList-grid .k-grid-header-wrap").css("overflow-x", "auto");
					} else {
						$("#monitorList-grid .k-grid-header-wrap").css("overflow-x", "");
					}
					return total;
				}
			}
		},
		sortable : false,
		groupable : false,
		columns : [{
					field : "loanNo",
					title : "放款编号",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "businessContractNo",
					title : "业务合同号",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "customerName",
					title : "借款人名称",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "customerEconomicCategory",
					title : "借款人类型",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "counterparty",
					title : "交易对手名称",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "counterpartyEconomicCategory",
					title : "交易对手类型",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "customerCity",
					title : "借款人所在地区",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "customerIndustry",
					title : "借款人所属行业",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "counterpartyCity",
					title : "交易对手所在地区",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "counterpartyIndustry",
					title : "交易对手所属行业",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "creditLimit",
					title : "授信额度",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "creditBalance",
					title : "可用额度",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "counterpartyLimit",
					title : "交易对手非承保额度",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "counterpartyBalance",
					title : "交易对手非承保额度余额",
					width : 180,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "invoiceAmount",
					title : "应收账款金额",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "invoiceBalance",
					title : "应收账款余额",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "financeAmount",
					title : "融资金额",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "financeBalance",
					title : "融资余额",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "loanDate",
					title : "起息日",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "dueDate",
					title : "到期日",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "repaymentMethod",
					title : "还款方式",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "interestRate",
					title : "利率",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "loanPeriod",
					title : "期限",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "accountIncome",
					title : "当日应还收入",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "accountPrincipal",
					title : "当日应还本金",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "accountAmount",
					title : "当日应还总额",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "paidIncome",
					title : "当日实际收入",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "paidPrincipal",
					title : "当日实际本金",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "paidAmount",
					title : "当日实际总额",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "unpaidAmount",
					title : "当日未偿总额",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				}, {
					field : "measure",
					title : "操作",
					width : 350,
					attributes : {
						style : "text-align: center"
					},
					template : function(item) {
						var status = $('#status').val()
						var edHtml = '';
						if (redeemAuth && status !="CLOSE") {
							edHtml += '<a class="userCenter-link" name="redeemAssetPool" href="javascript:void(0);" underlyingAssetId='+ item.loanNo + ' style="margin-right:10px;" >赎回</a>';
						}
						if (keepAuth && status !="CLOSE") {
							edHtml += '<a class="userCenter-link" name="outAssetPoolAndKeepBind" href="javascript:void(0);"  underlyingAssetId=' + item.loanNo + '  style="margin-right:10px;" >待入池</a>';
						}
						if (outAuth && status !="CLOSE") {
							edHtml += '<a class="userCenter-link" name="underlyingAssetId" href="javascript:void(0);" underlyingAssetId=' + item.loanNo + ' style="margin-right:10px;" >出池</a>';
						}
						if (paidAuth && item.assetSource != "MANUAL") {
							edHtml += '<a class="userCenter-link" name="loanPaid" href="javascript:void(0);" underlyingAssetId=' + item.loanNo + '  style="margin-right:10px;" >还款计划</a>';
						}
						if (historyAuth && item.assetSource != "MANUAL") {
							edHtml += '<a class="userCenter-link" name="history" href="javascript:void(0);" customerName=' + item.customerName + ' counterparty=' + item.counterparty + ' underlyingAssetId=' + item.loanNo + ' repaymentMethod=' + item.repaymentMethod + ' style="margin-right:10px;" >历史还款明细</a>';
						}
						return edHtml;
					}
				}],
		pageable : {
			pageSizes : false, // 设置每页显示行数
			buttonCount : 10, // 显示页数
			pageSize : 10,
			messages : {
				display : "共<span class='sumData'>{2}</span>条数据",
				empty : "没有数据",
				page : "页",
				of : "/ {0}",
				itemsPerPage : "条/页",
				first : "第一页",
				previous : "前一页",
				next : "下一页",
				last : "最后一页"
			}
		}
	});
}

/**
 * 加载历史明细
 * @param loanId
 * @param repaymentMethod
 */
function loadHistoryGrid(loanId, repaymentMethod) {
	$("#history-grid").empty();
	// 初始化弹窗表格
	$("#history-grid").kendoGrid({
		selectable : "row", // 设置可选择数据行
		sortable : true, // 列排序
		dataSource : {
			pageSize : 10,
			type : "json", // 后台返回的数据类型
			method : "post",
			transport : {
				read : {
					type : "post",
					url : basepath + "/asset/pool/getLoanHistoryList",
					data : { underlyingAssetId : loanId }
				}
			},
			serverPaging : true,
			serverSorting : true,
			serverFiltering : true,
			schema : {
				data : "data",
				total : function(data) {
					var total = data.totalNum;
					if (total == 0) {
						$("#monitorSrl-grid .k-grid-header-wrap").css("overflow-x", "auto");
					} else {
						$("#monitorSrl-grid .k-grid-header-wrap").css("overflow-x", "");
					}
					return total;
				}
			}
		},
		columns : [{
			field : "createTime",
			title : "操作时间",
			width : 100,
			attributes : {
				style : "text-align: center"
			},
			template : function(data) {
				return new Date(data.createTime).format("yyyy-MM-dd hh:mm");
			}
		}, {
			field : "paidDate",
			title : "入账日期",
			width : 100,
			attributes : {
				style : "text-align:center"
			}
		}, {
			field : "repaymentMethod",
			title : "还款方式",
			width : 120,
			attributes : {
				style : "text-align: center"
			},
			template : function(data) {
				return repaymentMethod;
			}
		}, {
			field : "paidAmount",
			title : "还款金额",
			width : 100,
			attributes : {
				style : "text-align: center"
			},
			template : function(data) {
				var paidAmount = data.paidAmount;
				return paidAmount == null ? "-" : fmoney(paidAmount / 100);
			}
		}, {
			field : "paidPrincipal",
			title : "已还本金",
			width : 100,
			attributes : {
				style : "text-align: center"
			},
			template : function(data) {
				var paidPrincipal = data.paidPrincipal;
				return paidPrincipal == null ? "-" : fmoney(paidPrincipal / 100);
			}
		}, {
			field : "paidInterest",
			title : "已还利息",
			width : 100,
			attributes : {
				style : "text-align: center"
			},
			template : function(data) {
				var paidInterest = data.paidInterest;
				return paidInterest == null ? "-" : fmoney(paidInterest / 100);
			}
		}, {
			field : "forwardDays",
			title : "提前天数",
			width : 60,
			attributes : {
				style : "text-align: center"
			},
			template : function(data) {
				return data.forwardDays == null ? "-" : data.forwardDays;
			}
		}, {
			field : "overdueDays",
			title : "逾期天数",
			width : 60,
			attributes : {
				style : "text-align: center"
			},
			template : function(data) {
				return data.overdueDays == null ? "-" : data.overdueDays;
			}
		}, {
			field : "paidOverdue",
			title : "已还逾期费",
			width : 100,
			attributes : {
				style : "text-align: center"
			},
			template : function(data) {
				var paidOverdue = data.paidOverdue;
				return paidOverdue == null ? "-" : fmoney(paidOverdue / 100);
			}
		} ],
		pageable : {
			pageSizes : false, // 设置每页显示行数
			buttonCount : 5, // 显示页数
			messages : {
				display : "共<span class='sumData'>{2}</span>条数据",
				empty : "没有数据",
				page : "页",
				of : "/ {0}",
				itemsPerPage : "条/页",
				first : "第一页",
				previous : "前一页",
				next : "下一页",
				last : "最后一页"
			}
		}
	});
}

/**
 * 加载还款计划
 * @param loanId
 */
function loadLoanPaidGrid(loanId) {
	$("#paid-grid").empty();
	// 初始化弹窗表格
	$("#paid-grid").kendoGrid({
		selectable : "row", // 设置可选择数据行
		sortable : true, // 列排序
		dataSource : {
			type : "json", // 后台返回的数据类型
			method : "post",
			transport : {
				read : {
					type : "post",
					url : basepath + "/asset/pool/getLoanPaidList",
					data : {
						underlyingAssetId : loanId
					}
				}
			},
		},
		columns : [ {
			field : "financeBalance",
			title : "融资余额",
			width : 100,
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "valueDate",
			title : "起息日",
			width : 100,
			attributes : {
				style : "text-align:center"
			}
		}, {
			field : "settleInterestDate",
			title : "结息日",
			width : 100,
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "repaymentDate",
			title : "还款日",
			width : 100,
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "accountPrincipal",
			title : "应还本金",
			width : 100,
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "accountInterest",
			title : "应还利息",
			width : 60,
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "accountOverdue",
			title : "应还逾期费",
			width : 60,
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "accountAmount",
			title : "应还总金额",
			width : 100,
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "settleStatusDesc",
			title : "结清状态",
			width : 100,
			attributes : {
				style : "text-align: center"
			}
		}]
	});
}