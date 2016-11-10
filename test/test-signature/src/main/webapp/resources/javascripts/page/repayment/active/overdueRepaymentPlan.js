var userType = "";
$(function(){
	// 获取用户类型
	userType = $(".main-container").data("usertype");
    $(".repayment-navlink").click(function(event){
        var obj = $(event.target).parent("li");
        if(!obj.hasClass("repayment-nav-active")){
            obj.addClass("repayment-nav-active").siblings().removeClass("repayment-nav-active");
        }
        if(obj.is(".repayment-nav ul li:first")){
        	$("#repayment-plan").removeClass("hidden");
        	$("#repayment-cost").addClass("hidden");
        	initReplaymentPlan();
        } else if(obj.is(".repayment-nav ul li:last")){
        	$("#repayment-plan").addClass("hidden");
        	$("#repayment-cost").removeClass("hidden");
        	// 获取逾期费用列表
        	initReplaymentExpense();
        }
    });
    
    // 获取逾期还款计划列表
    initReplaymentPlan();
    
    $("body").on("click", ".repay-search", function(){
    	initReplaymentPlan();
    	initReplaymentExpense();
    });
    
    
	data = {};
    $("body").on("click", ".activeRepayPlan-link", function() {
    	var url = basepath + "/repayment/active/showActiveRepaymentPage?loanInfoId=" + $(this).parent().next().text(),
    	data = {
    			activeRepaymentType:"single_plan"
    	};
    	StandardPost(url, data);
    });
    
    $("body").on("click", ".activeRepayExpense-link", function() {
    	var url = basepath + "/repayment/active/showActiveRepaymentPage?loanInfoId=" + $(this).parent().next().text(),
		data = {
				activeRepaymentType:"single_expense"
		};
		StandardPost(url, data);
	});
});

function initReplaymentPlan(){
    $("#manualGrid-plan").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
            pageSize: 10,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                	data: { financeCompany: $.trim($("input[name='financeCompany']").val()),
                    		factorCompany: $.trim($("input[name='factorCompany']").val()),
                    		coreCompanyName: $.trim($("input[name='coreCompanyName']").val()),
                    		loanNo: $.trim($("input[name='loanNo']").val()),
                    		businessProduct: $.trim($("input[name='businessProduct']").val()),
                    		beginRepaymentDate: $.trim($("input[name='begainRepaymentDate']").val()),
                    		endRepaymentDate: $.trim($("input[name='endRepaymentDate']").val())
                    	},
                    type : "POST",
                    url: basepath + "/repayment/active/getOverdueRepaymentPlan"
                }
            },
            serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
		    //解析远程响应的数据
		    schema:{
		    	data: "data",
				total: function(data) {
					var total = data.totalNum;
	  				if(total == 0){
	  					$("#manualGrid-plan .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#manualGrid-plan .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
		    }
        },
        columns: [{
            field: "loanInfoId",
            title: "平台流水号",
            width: 100
        },{
            field: "businessProduct",
            title: "业务产品",
            width: 100
        },{
            field: "factorCompany",
            title: "资金方",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            hidden: userType == "FACTOR"
        },{
            field: "financeCompany",
            title: "融资客户",
            width: 150,
            hidden: userType == "FINACE"
        },{
            field: "coreCompanyName",
            title: "核心企业",
            width: 150,
            hidden: userType == "CORECOMPANY"
        },{
            field: "loanNo",
            title: "放款编号",
            width: 150
        },{
            field: "repaymentPeriod",
            title: "期数",
            width: 50
        },{
            field: "financeAmount",
            title: "融资金额",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "financeBalance",
            title: "融资余额",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "valueDate",
            title: "收益计算日",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "settleInterestDate",
            title: "收益分配日",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "repaymentDate",
            title: "还款日",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "overduePrincipal",
            title: "逾期本金",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "overdueInterest",
            title: "逾期收益",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "overdueServiceCharge",
            title: "逾期服务费",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidOverduePrincipal",
            title: "已还逾期本金",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidOverdueInterest",
            title: "已还逾期收益",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidOverdueServiceCharge",
            title: "已还逾期服务费",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidExtensionCharge",
            title: "已还展期费用",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            title: "操作",
            sortable: false,
            width:120,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	var link = "";
            	if(activeRepaymentAuth){
            		if(data.repaied)
            			link += "<a class='comRow-link incRole-link activeRepayPlan-link' href='javascript:void(0);'>还款</a>";
            	}
            	return link;
            }
        },{
            field: "loanInfoId",
            title: "主键",
            width: 120,
            hidden: true
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

function initReplaymentExpense(){
    $("#manualGrid-cost").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
            pageSize: 10,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                	data: { financeCompany: $.trim($("input[name='financeCompany']").val()),
                		factorCompany: $.trim($("input[name='factorCompany']").val()),
                		coreCompanyName: $("input[name=coreCompanyName]").val(),
                		loanNo: $.trim($("input[name='loanNo']").val()),
                		businessProduct: $.trim($("input[name='businessProduct']").val()),
                		beginRepaymentDate: $.trim($("input[name='begainRepaymentDate']").val()),
                		endRepaymentDate: $.trim($("input[name='endRepaymentDate']").val())
                	},
                	type : "POST",
                	url: basepath + "/repayment/active/getOverdueRepaymentExpense"
                }
            },
		    serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
		    //解析远程响应的数据
		    schema:{
		    	data: "data",
				total: function(data) {
					var total = data.totalNum;
						if(total == 0){
							$("#manualGrid-cost .k-grid-header-wrap").css("overflow-x","auto");
						}else {
							$("#manualGrid-cost .k-grid-header-wrap").css("overflow-x","");
					}
						return total;
				}
		    }
        },
        columns: [{
            field: "loanNo",
            title: "放款编号",
            width: 100
        },{
            field: "loanInfoId",
            title: "平台流水号",
            width: 120
        },{
            field: "expenseSubject",
            title: "费用名目",
            width: 120
        },{
            field: "repaymentDate",
            title: "还款日",
            width: 120
        },{
            field: "repaymentAmount",
            title: "应还费用",
            width: 120
        },{
            field: "paidAmount",
            title: "已还费用",
            width: 150
        },{
            title: "操作",
            sortable: false,
            width:120,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	var link = "";
            	if(activeRepaymentAuth){
            		if(data.repaied)
            			link += "<a class='comRow-link incRole-link activeRepayExpense-link' href='javascript:void(0);'>还款</a>";
            	}
            	return link;
            }
        },{
            field: "loanInfoId",
            title: "主键",
            width: 120,
            hidden: true
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