var importWindow;

$(function(){
	
	//初始化历史明细弹窗
	new PopWindow(".open-history-btn",{
	    title: "历史还款明细",
	    width: 1000,
	    reload: true,
	    template: "#template-history"
	}).init();
	
	//初始化选择导入方式弹窗
	importWindow = new PopWindow("#import",{
	    title: "放款信息录入选择",
	    width: 400,
	    reload: true,
	    template: "#template-import"
	}).init();
	
	//打开历史明细弹窗
	$("body").on("click",".history-btn",function(){
	    $(".open-history-btn").click();
	    $(".k-widget.k-window").css("top","20%");
	    $('#popWindowLoanId').text($(this).parent().attr('loanId'));
	    $('#popWindowCustomerName').text($(this).attr('customerName'));
	    $('#popWindowCounterparty').empty();
	    cana.get(basepath + "/asset/customer/getCustomerName/" +$(this).attr('counterpartyId') , null, getCustomerNameSuccess, null, null);
	    loadHistoryGrid($(this).parent().attr('loanId'), $(this).attr('repaymentMethod'));
	});
	
    $('body').on('click', 'a[name=tipBoxButton]', function() {
		tipBoxTemplate.close();
		$('.k-overlay').hide();
	});
    
    $('body').on('click', 'a[name=confirmBoxOK]', function() {
    	cana.get(basepath + "/asset/loan/deleteAssetLoan?loanInfoId=" + $('#deleteId').prop('loanId') , null, deleteSuccess, deleteFail, null);
    	confirmPopWindow.close();
    });
	
    $('body').on('click', "a[name=delete]", function() {
    	var loanId = $(this).parent().attr('loanId');
    	$('#deleteId').prop('loanId', loanId);
    	showConfirmBox("确定删除" + loanId + "该笔放款吗？");
    });
    
    $('body').on('click', '.radio', function() {
    	$(this).addClass("active");
    	$(this).siblings().removeClass("active");
    	updateImportType();
    });
    
    $('body').on('click', '#planImport', function() {
    	window.open(basepath + "/asset/loan/gotoLoanPlanImport");
    });
    
    $('body').on('click', '#entryCreateLoanPageButton', function() {
    	 if($('#excelImport').hasClass("active")) {
    		 window.open(basepath + "/asset/loan/gotoLoanInfoImport");
    	 } else {
    		 window.open(basepath + "/asset/loan/createAssetLoan");
    	 }
    	 importWindow.close();
    	 $('.k-overlay').hide();
    });
    
	$('#search').click(function() {
		var grid = $("#monitorList-grid").data("kendoGrid");
		grid.setDataSource(getDataSource());
	});
	
	loadListGrid();
	
	bindEntry();

	$("body").on("click",".k-window-action .k-i-close",function(){
        $('.k-overlay').hide();
    });
});

function deleteSuccess(data) {
	showTipBox("notice-icon02", "删除成功");
	$('#search').click();
}

function deleteFail(data) {
	showTipBox("notice-icon03", "删除失败");
}

function getCustomerNameSuccess(data) {
	$('#popWindowCounterparty').text(data);
}

function bindEntry() {
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('#search').click();
    	}
    });
}

function getDataSource(){
	return new kendo.data.DataSource({
		pageSize : 10,
		type : "json", // 后台返回的数据类型
		method : "post",
		transport : {
			read : {
				type : "post",
				url: basepath + "/asset/loan/get/loanList",
				data: {
					customerName: $.trim($('#customerName').val()),
					businessContractNo: $.trim($('#businessContractNo').val()),
					businessProduct: $.trim($('#businessProduct').val()),
					settleStatus: $.trim($('#settleStatus').val()),
					loanStartDate: $.trim($('#loanStartDate').val()),
					loanEndDate: $.trim($('#loanEndDate').val()),
					dueStartDate: $.trim($('#dueStartDate').val()),
					dueEndDate: $.trim($('#dueEndDate').val())
				}
			}
		},
		serverPaging: true,
		serverSorting: true,
		serverFiltering: true,
		schema : {
			data: "data",
			total: function(data){
				var total = data.totalNum;
				if(total == 0){
					$("#monitorSrl-grid .k-grid-header-wrap").css("overflow-x","auto");
				}else{
					$("#monitorSrl-grid .k-grid-header-wrap").css("overflow-x","");
				}
				return total;
			}
		}
    });
}

function loadHistoryGrid(loanId, repaymentMethod) {
	$("#history-grid").empty();
	//初始化弹窗表格
    $("#history-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
			pageSize : 10,
			type : "json", // 后台返回的数据类型
			method : "post",
			transport : {
				read : {
					type : "post",
					url: basepath + "/asset/loan/get/paidList",
					data: {
						loanId: loanId
					}
				}
			},
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			schema : {
				data: "data",
				total: function(data){
					var total = data.totalNum;
					if(total == 0){
						$("#monitorSrl-grid .k-grid-header-wrap").css("overflow-x","auto");
					}else{
						$("#monitorSrl-grid .k-grid-header-wrap").css("overflow-x","");
					}
					return total;
				}
			}
        },
        columns: [{
            field: "createTime",
            title: "操作时间",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data) {
            	return new Date(data.createTime).format("yyyy-MM-dd hh:mm");
            }
        },{
            field: "paidDate",
            title: "入账日期",
            width: 100,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "repaymentMethod",
            title: "还款方式",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	return repaymentMethod;
            }
        },{
            field: "paidAmount",
            title: "还款金额",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	var paidAmount = data.paidAmount;
                return paidAmount == null ? "-" : fmoney(paidAmount/100);
            }
        },{
            field: "paidPrincipal",
            title: "已还本金",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	var paidPrincipal = data.paidPrincipal;
                return paidPrincipal == null ? "-" : fmoney(paidPrincipal/100);
            }
        },{
            field: "paidInterest",
            title: "已还利息",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	var paidInterest = data.paidInterest;
                return paidInterest == null ? "-" : fmoney(paidInterest/100);
            }
        },{
            field: "forwardDays",
            title: "提前天数",
            width: 60,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	return data.forwardDays == null ? "-" : data.forwardDays;
            }
        },{
            field: "overdueDays",
            title: "逾期天数",
            width: 60,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	return data.overdueDays == null ? "-" : data.overdueDays;
            }
        },{
            field: "paidOverdue",
            title: "已还逾期费",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	var paidOverdue = data.paidOverdue;
                return paidOverdue == null ? "-" : fmoney(paidOverdue/100);
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            buttonCount: 5,  //显示页数
            messages: {
                display: "共<span class='sumData'>{2}</span>条数据",
                empty: "没有数据",
                page: "页",
                of: "/ {0}",
                itemsPerPage: "条/页",
                first: "第一页",
                previous: "前一页",
                next: "下一页",
                last: "最后一页"
            }
        }
    });
}

function loadListGrid() {
	//初始化表格
	var grid = $("#monitorList-grid").kendoGrid({
	    selectable: "row",  //设置可选择数据行
	    sortable: true,  //列排序
	    columns: [{
	        field: "id",
	        title: "放款编号",
	        width: 120,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "businessContractNo",
	        title: "业务合同号",
	        width: 80,
	        attributes:{
	            style:"text-align:center"
	        }
	    },{
	        field: "customerName",
	        title: "融资客户",
	        width: 150,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "businessProduct",
	        title: "业务产品",
	        width: 100,
	        attributes: {
	            style: "text-align: center"
	        },
            template: function(data){
                return BusinessProduct[data.businessProduct];
            }
	    },{
	        field: "financeAmount",
	        title: "融资金额",
	        width: 100,
	        attributes: {
	            style: "text-align: center"
	        },
            template: function(data){
                return fmoney(data.financeAmount/100);
            }
	    },{
	        field: "financeBalance",
	        title: "融资余额",
	        width: 100,
	        attributes: {
	            style: "text-align: center"
	        },
            template: function(data){
                return fmoney(data.financeBalance/100);
            }
	    },{
	        field: "loanDate",
	        title: "放款日",
	        width: 80,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "dueDate",
	        title: "到期日",
	        width: 80,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "repaymentMethod",
	        title: "还款方式",
	        width: 100,
	        attributes: {
	            style: "text-align: center"
	        },
            template: function(data){
                return RepaymentType[data.repaymentMethod];
            }
	    },{
	        field: "settleStatus",
	        title: "状态",
	        width: 80,
	        attributes: {
	            style: "text-align: center"
	        },
            template: function(data){
                return SettleStatus[data.settleStatus];
            }
	    },{
	        field: "measure",
	        title: "操作",
	        width: 200,
	        attributes: {
	            style: "text-align: center"
	        },
	        template: function(data){
	            var edHtml = "<span loanId='" + data.id + "'>";
	            if (asset_loan_detail_auth)
	            	edHtml += "<a class='comRow-link' href='" + basepath + "/asset/loan/goto/loanDetail?id=" + data.id + "'>详情</a>";

	            if (asset_loan_modify_auth
	            		&& data.settleStatus != 'SETTLED'
	            		&& !data.underlyingAsset
	            		&& !data.denyModifyOrPaid)
	            	edHtml += "<a class='comRow-link' href='" + basepath + "/asset/loan/editAssetLoan?id=" + data.id + "'>修改</a>";

	            if (asset_loan_delete_auth
	            		&& !data.existPaidInfo
	            		&& !data.underlyingAsset
	            		&& !data.denyModifyOrPaid)
	            	edHtml += "<a class='comRow-link' name='delete' href='javascript:void(0);'>删除</a>";

	            if (asset_loan_paid_history_auth)
	            	edHtml += "<a class='comRow-link history-btn' customerName='" + data.customerName + "' counterpartyId='" + data.counterpartyId + "' repaymentMethod='" + RepaymentType[data.repaymentMethod] + "' href='javascript:void(0);'>历史还款明细</a>";
	            if (asset_loan_paid_auth
	            		&& data.settleStatus != 'SETTLED'
            			&& !data.denyModifyOrPaid)
	            	edHtml += "<a class='comRow-link' href='" + basepath + "/asset/loan/paid?loanInfoId=" + data.id + "'>还款冲销</a>";

	            edHtml += "</span>";
	            return edHtml;
	        }
	    }],
	    pageable: {
	        pageSizes: false,  //设置每页显示行数
	        buttonCount: 5,  //显示页数
	        messages: {
	            display: "共<span class='sumData'>{2}</span>条数据",
	            empty: "没有数据",
	            page: "页",
	            of: "/ {0}",
	            itemsPerPage: "条/页",
	            first: "第一页",
	            previous: "前一页",
	            next: "下一页",
	            last: "最后一页"
	        }
	    }
	}).data("kendoGrid");
	grid.setDataSource(getDataSource());
}