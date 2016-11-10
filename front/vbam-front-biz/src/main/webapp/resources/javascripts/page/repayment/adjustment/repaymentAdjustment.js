var pageSize = 10;
var validatorForNewPlanEditBtn;
var validatorForNewExpenseEditBtn;
$(function () {

    initrepaymentPlan();
    initreplaymentExpense();
    
    $(".repayment-navlink").click(function (event) {
        var obj = $(event.target).parent("li");
        if (!obj.hasClass("repayment-nav-active")) {
            obj.addClass("repayment-nav-active").siblings().removeClass("repayment-nav-active");
        }
        if (obj.is(".repayment-nav ul li:first")) {
            $("#repayment-plan").removeClass("hidden");
            $("#repayment-cost").addClass("hidden");
        } else if (obj.is(".repayment-nav ul li:last")) {
            $("#repayment-plan").addClass("hidden");
            $("#repayment-cost").removeClass("hidden");
        }
      //检查提交按钮位置
        chkfix();
    });
    
    $(".main-header a,.redirect").click(function(e){
    	e.preventDefault();
    	var url = $(this).attr("href");
    	$.ajax({
			type: "POST",
			url: basepath + "/repayment/adjustment/redirectValidate",
			data: {
				redisKey: $.trim($("input[name='redisKey']").val())
				},
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
					$(".template-leave").click();
					$("body").on("click", "#comfirmLeave",function(){
						window.location.href = url;
					});
				}else{
					window.location.href = url;
				}
			}
		});
    });
	
    //确认离开当前页面提示弹窗
    new PopWindow(".template-leave", {
        title: "确认离开",
        width: 460,
        reload: true,
        template: "#template-leave"
    }).init();

    //查看还款公式弹窗
//    new PopWindow(".repayDetail-link", {
//        title: "还款计划信息",
//        width: 860,
//        reload: true,
//        template: "#template-repayPlanInfo"
//    }).init();

    //新费用弹窗
//    new PopWindow(".editNewRepay-link", {
//        title: "修改费用",
//        width: 830,
//        reload: true,
//        template: "#template-newCost"
//    }).init();

    //打开还款公式弹窗
    $("body").on("click", "#manualGrid-plan .repayDetail-link", function(e){
        $(".window-overlay").removeClass("hidden");
        $("#template-repayPlanInfo").removeClass("hidden");
        removeVal();
    });

    //关闭还款公式弹窗
    $(".autoCloseBar, #template-repayPlanInfo .back-link").click(function(){
        $(".window-overlay").addClass("hidden");
        $("#template-repayPlanInfo").addClass("hidden");
    });
    
    //打开新费用弹窗
    $("body").on("click", "#manualGrid-cost .editNewRepay-link", function(e){
        $(".window-overlay").removeClass("hidden");
        $("#template-editCost").removeClass("hidden");
        removeVal();
    });

    //关闭修改新费用弹窗
    $(".autoCloseBar, #template-editCost .back-link").click(function(){
        $(".window-overlay").addClass("hidden");
        $("#template-editCost").addClass("hidden");
    });
    
    //还款计划修改
    $("body").on("click", ".repayDetail-link", function(){
    	$("span[name='errorPromptForNewPlan']").css("visibility","hidden");
    	$("#newPlanConfirm").attr("data-id",$(this).attr("data-id"));
    	// 对已还信息弹窗内容进行验证添加
    	paidRepaymentPlanAdjustment();
		$.ajax({
			type: "POST",
			url: basepath+"/repayment/adjustment/getAccountRepaymentPlanSingleLine",
			data: { redisKey: $.trim($("input[name='redisKey']").val()),
					planId: $.trim($(this).attr("data-id"))},
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
					$("span[name='loanNoForNewPlan']").text(data.data.loanNo);
					$("span[name='repaymentPeriodForNewPlan']").text(data.data.repaymentPeriod);
					$("span[name='financeBalanceForNewPlan']").text(data.data.financeBalance.formatMoney());
					$("span[name='valueDateForNewPlan']").text(data.data.valueDate);
					$("span[name='settleInterestDateForNewPlan']").text(data.data.settleInterestDate);
					$("span[name='repaymentDateForNewPlan']").text(data.data.repaymentDate);
					$("span[name='accountPrincipalForNewPlan']").text(data.data.accountPrincipal.formatMoney());
					$("input[name='accountInterestForNewPlan']").val(data.data.accountInterest.formatMoney());
					$("input[name='accountServiceChargeForNewPlan']").val(data.data.accountServiceCharge.formatMoney());
					$("span[name='overduePrincipalForNewPlan']").text(data.data.overduePrincipal.formatMoney());
					$("input[name='overdueInterestForNewPlan']").val(data.data.overdueInterest.formatMoney());
					$("input[name='overdueServiceChargeForNewPlan']").val(data.data.overdueServiceCharge.formatMoney());
					$("input[name='extensionChargeForNewPlan']").val(data.data.extensionCharge.formatMoney());
					$("input[name='overdueManagerFeeForNewPlan']").val(data.data.overdueManagerFee.formatMoney());
					$("span[name='settleStatusForNewPlan']").text(data.data.settleStatus);
					$("input[name='settleStatusForPage']").val(data.data.settleStatusForPage);
				}else{
					$(".window-overlay").addClass("hidden");
			        $("#template-repayPlanInfo").addClass("hidden");
			        showAlertWin(data.message);
				}
			}
		});	
    });
    
    // 还款计划修改->保存
	$("body").delegate("#newPlanConfirm", "click", function() {
		$("span[name='errorPromptForNewPlan']").css("visibility","hidden");
		if(!validatorForNewPlanEditBtn.validate()) {
			return;
		};
		var cancelbtn = $(this).next();
		$.ajax({
			type: "POST",
			url: basepath+"/repayment/adjustment/updateAccountRepaymentInfo",
			data: { redisKey: $.trim($("input[name='redisKey']").val()),
				loanNo: $.trim($("span[name='loanNoForNewPlan']").text()),
				repaymentPeriod: $.trim($("span[name='repaymentPeriodForNewPlan']").text()),
				financeBalance: $.trim($("span[name='financeBalanceForNewPlan']").text().parseMoney()),
				valueDate: $.trim($("span[name='valueDateForNewPlan']").text()),
				settleInterestDate: $.trim($("span[name='settleInterestDateForNewPlan']").text()),
				repaymentDate: $.trim($("span[name='repaymentDateForNewPlan']").text()),
				accountPrincipal: $.trim($("span[name='accountPrincipalForNewPlan']").text().parseMoney()),
				accountInterest: $.trim($("input[name='accountInterestForNewPlan']").val().parseMoney()),
				accountServiceCharge: $.trim($("input[name='accountServiceChargeForNewPlan']").val().parseMoney()),
				overduePrincipal: $.trim($("span[name='overduePrincipalForNewPlan']").text().parseMoney()),
				overdueInterest: $.trim($("input[name='overdueInterestForNewPlan']").val().parseMoney()),
				overdueServiceCharge: $.trim($("input[name='overdueServiceChargeForNewPlan']").val().parseMoney()),
				extensionCharge: $.trim($("input[name='extensionChargeForNewPlan']").val().parseMoney()),
				overdueManagerFee: $.trim($("input[name='overdueManagerFeeForNewPlan']").val().parseMoney()),
				settleStatus: $.trim($("span[name='settleStatusForNewPlan']").text()),
				settleStatusForPage: $.trim($("input[name='settleStatusForPage']").val()),
				id: $.trim($(this).attr("data-id"))
			},
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
					cancelbtn.click();
					initrepaymentPlan();
				}else{
					$("span[name='errorPromptForNewPlan']").text(data.message);
	        		$("span[name='errorPromptForNewPlan']").css("visibility","visible");
				}
			}
		});	
	});
	
    // 修改新费用
	$("body").delegate(".editNewRepay-link", "click", function() {
		$("span[name='errorPromptForNewExpense']").css("visibility","hidden");
		$("span[name='subjectItemForNewPay']").text($(this).parent().siblings().eq(1).text());
		$("input[name='accountAmount']").val($(this).parent().prev().prev().text());
		$("#cost-confirm").attr("data-id",$(this).attr("data-id"));
		setValidator($("input[name='accountAmount']"), "required", true, accountAmountRule.required);
		setValidator($("input[name='accountAmount']"), "pattern", accountAmountRule.pattern, accountAmountRule.message);
		validatorForNewExpenseEditBtn = $("#newExpenseEdit").kendoValidator(
			{
				rules: {
					accountAmountRule: accountAmountRule.checkRule
				},
				messages: {
					accountAmountRule: accountAmountRule.checkMessage
				},
				needRuleAttrbute : false
			}	
		).data("kendoValidator");
	});
	
    $("body").on( "click", "#cost-confirm", function(){
    	$("span[name='errorPromptForNewExpense']").css("visibility","hidden");
    	if(!validatorForNewExpenseEditBtn.validate()) {
			return;
		};
    	var cancelbtn = $(this).next();
    	$.ajax({
			type: "POST",
			url: basepath+"/repayment/adjustment/accountExpenseEdit",
			data: { redisKey: $.trim($("input[name='redisKey']").val()),
					expenseId: $.trim($(this).attr("data-id")),
					accountExpenseAmount: $.trim($("input[name='accountAmount']").val().parseMoney()),
			},
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
					cancelbtn.click();
					initreplaymentExpense();
				}else{
					$("span[name='errorPromptForNewExpense']").text(data.message);
	        		$("span[name='errorPromptForNewExpense']").css("visibility","visible");
				}
			}
		});
    });
    
    // 确认还款
	$("body").delegate("#repaymentConfirm", "click", function() {
		$("#repaymentPlanAndExpense").submit();
	});
	
	// 判断计划结清状态
	$("body").delegate(".forEditNewPlan", "change", function() {
		var accountPrincipal = $.trim($("input[name='accountPrincipalForNewPlan']").val());
		var accountInterest = $.trim($("input[name='accountInterestForNewPlan']").val());
		var accountServiceCharge = $.trim($("input[name='accountServiceChargeForNewPlan']").val());
		var overduePrincipal = $.trim($("input[name='overduePrincipalForNewPlan']").val());
		var overdueInterest = $.trim($("input[name='overdueInterestForNewPlan']").val());
		var overdueServiceCharge = $.trim($("input[name='overdueServiceChargeForNewPlan']").val());
		var extensionCharge = $.trim($("input[name='extensionChargeForNewPlan']").val());
		var overdueManagerFee = $.trim($("input[name='overdueManagerFeeForNewPlan']").val());
		var paidEarlyRepaymentCharge = $.trim($("input[name='paidEarlyRepaymentChargeForNewPlan']").val());
		if(Number(accountPrincipal).toFixed(2) == 0 && Number(accountInterest).toFixed(2) == 0 && Number(accountServiceCharge).toFixed(2) == 0 
			&& Number(overduePrincipal).toFixed(2) == 0 && Number(overdueInterest).toFixed(2) == 0 && Number(overdueServiceCharge).toFixed(2) == 0
			&& Number(extensionCharge).toFixed(2) == 0 && Number(overdueManagerFee).toFixed(2) == 0 && Number(paidEarlyRepaymentCharge).toFixed(2) == 0){
			$("span[name='settleStatusForNewPlan']").text("已结清");
		}else{
			$("span[name='settleStatusForNewPlan']").text("未结清");
		}
	});
 });

function chkfix(){
    var clientHeight = $(window).height();
    var offsetTop = $("#foot-fixBar").offset().top;

    if(offsetTop >= clientHeight){
        $("#foot-fixBar").addClass("foot-fixBar");
    }else{
        $("#foot-fixBar").removeClass("foot-fixBar");
    }
}

function paidRepaymentPlanAdjustment(){

	setValidator($("input[name='accountInterestForNewPlan']"), "required", true, accountInterestForNewPlanRule.required);
//	setValidator($("input[name='accountInterestForNewPlan']"), "pattern", accountInterestForNewPlanRule.pattern, accountInterestForNewPlanRule.message);
	
	setValidator($("input[name='accountServiceChargeForNewPlan']"), "required", true, accountServiceChargeForNewPlanRule.required);
//	setValidator($("input[name='accountServiceChargeForNewPlan']"), "pattern", accountServiceChargeForNewPlanRule.pattern, accountServiceChargeForNewPlanRule.message);

	setValidator($("input[name='overdueInterestForNewPlan']"), "required", true, overdueInterestForNewPlanRule.required);
//	setValidator($("input[name='overdueInterestForNewPlan']"), "pattern", overdueInterestForNewPlanRule.pattern, overdueInterestForNewPlanRule.message);
	
	setValidator($("input[name='overdueServiceChargeForNewPlan']"), "required", true, overdueServiceChargeForNewPlanRule.required);
//	setValidator($("input[name='overdueServiceChargeForNewPlan']"), "pattern", overdueServiceChargeForNewPlanRule.pattern, overdueServiceChargeForNewPlanRule.message);
	
	setValidator($("input[name='extensionChargeForNewPlan']"), "required", true, extensionChargeForNewPlanRule.required);
//	setValidator($("input[name='extensionChargeForNewPlan']"), "pattern", extensionChargeForNewPlanRule.pattern, extensionChargeForNewPlanRule.message);
	
	setValidator($("input[name='overdueManagerFeeForNewPlan']"), "required", true, overdueManagerFeeForNewPlanRule.required);
//	setValidator($("input[name='overdueManagerFeeForNewPlan']"), "pattern", overdueManagerFeeForNewPlanRule.pattern, overdueManagerFeeForNewPlanRule.message);

	setValidator($("input[name='paidEarlyRepaymentChargeForNewPlan']"), "required", true, paidEarlyRepaymentChargeForNewPlanRule.required);
//	setValidator($("input[name='paidEarlyRepaymentChargeForNewPlan']"), "pattern", paidEarlyRepaymentChargeForNewPlanRule.pattern, paidEarlyRepaymentChargeForNewPlanRule.message);
	
	validatorForNewPlanEditBtn = $("#frmForEdit").kendoValidator(
			{
				rules: {
					accountInterestForNewPlanRule: accountInterestForNewPlanRule.checkRule,
					accountServiceChargeForNewPlanRule: accountServiceChargeForNewPlanRule.checkRule,
					overdueInterestForNewPlanRule: overdueInterestForNewPlanRule.checkRule,
					overdueServiceChargeForNewPlanRule: overdueServiceChargeForNewPlanRule.checkRule,
					extensionChargeForNewPlanRule: extensionChargeForNewPlanRule.checkRule,
					overdueManagerFeeForNewPlanRule: overdueManagerFeeForNewPlanRule.checkRule,
					paidEarlyRepaymentChargeForNewPlanRule: paidEarlyRepaymentChargeForNewPlanRule.checkRule
				},
				messages: {
					accountInterestForNewPlanRule: accountInterestForNewPlanRule.checkMessage,
					accountServiceChargeForNewPlanRule: accountServiceChargeForNewPlanRule.checkMessage,
					overdueInterestForNewPlanRule: overdueInterestForNewPlanRule.checkMessage,
					overdueServiceChargeForNewPlanRule: overdueServiceChargeForNewPlanRule.checkMessage,
					extensionChargeForNewPlanRule: extensionChargeForNewPlanRule.checkMessage,
					overdueManagerFeeForNewPlanRule: overdueManagerFeeForNewPlanRule.checkMessage,
					paidEarlyRepaymentChargeForNewPlanRule: paidEarlyRepaymentChargeForNewPlanRule.checkMessage
				},
				needRuleAttrbute : false
				
			}).data("kendoValidator");
}

function initrepaymentPlan(){
	$("#manualGrid-plan").remove();
	$("#repayment-plan").append("<div class='finance-grid' id='manualGrid-plan' style='margin-top:15px;'></div>");
	//通过还款信息
    $("#manualGrid-plan").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                    data: { redisKey: $.trim($("input[name='redisKey']").val())},
                    type : "POST",
                    url: basepath + "/repayment/adjustment/getAccountRepaymentPlan"
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
	  					$("#repayment-plan .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#repayment-plan .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
		    }
        },
        columns: [{
            field: "loanNo",
            title: "放款编号",
            width: 130
        },{
            field: "repaymentPeriod",
            title: "期数",
            width: 50,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "financeBalance",
            title: "融资余额",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.financeBalance + "";
            	return money.formatMoney();	
            }
        },{
            field: "valueDate",
            title: "收益计算日",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "settleInterestDate",
            title: "收益分配日",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "repaymentDate",
            title: "还款日",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "accountPrincipal",
            title: "应还本金",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.accountPrincipal + "";
            	if(data.accountPrincipalNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        },{
            field: "accountInterest",
            title: "应还收益",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.accountInterest + "";
            	if(data.accountInterestNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        },{
            field: "accountServiceCharge",
            title: "应还服务费",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.accountServiceCharge + "";
            	if(data.accountServiceChargeNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        },{
            field: "overduePrincipal",
            title: "逾期本金",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.overduePrincipal + "";
            	if(data.overduePrincipalNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        }, {
            field: "overdueInterest",
            title: "逾期收益",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.overdueInterest + "";
            	if(data.overdueInterestNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        }, {
            field: "overdueServiceCharge",
            title: "逾期服务费",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.overdueServiceCharge + "";
            	if(data.overdueServiceChargeNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        }, {
            field: "extensionCharge",
            title: "展期费用",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.extensionCharge + "";
            	if(data.extensionChargeNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        }, {
            field: "overdueManagerFee",
            title: "逾期管理费",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.overdueManagerFee + "";
            	if(data.overdueManagerFeeNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        },{
            field: "accountTotalAmount",
            title: "应还总金额",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.accountTotalAmount + "";
            	if(data.accountTotalAmountNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        },{
            field: "settleStatus",
            title: "结清状态",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "createDate",
            title: "操作",
            width: 150,
            template: function(data){
            	var url="";
            	if(data.settleStatusForPage=="未结清"){
            		url += "<a class='comRow-link repayDetail-link' href='javascript:void(0);' data-id="+data.id+">修改</a>";
            	}
            	return url; 
            	
            }
        }],
        pageable: {
            pageSizes: false,//设置每页显示行数
            pageSize: pageSize,
            buttonCount: 5,  //显示页数
            page: 1,
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


function initreplaymentExpense(){
	$("#manualGrid-cost").remove();
	$("#repayment-cost").append("<div class=\"finance-grid\" id=\"manualGrid-cost\" style=\"margin-top:15px;\"></div>");
	//通过还款信息
	$("#manualGrid-cost").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                    data: { redisKey: $.trim($("input[name='redisKey']").val())  },
                    type : "POST",
                    url: basepath + "/repayment/adjustment/getPaidRepaymentExpense"
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
	  					$("#repayment-cost .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#repayment-cost .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
			}
        },
        columns: [{
            field: "loanNo",
            title: "放款编号",
            width: 150
        },{
            field: "expenseSubject",
            title: "费用明目",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "repaymentDate",
            title: "还款日",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "repaymentAmount",
            title: "应还金额",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.repaymentAmount + "";
            	if(data.repaymentAmountNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        },{
            field: "settleStatus",
            title: "结清状态",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            title: "修改",
            width: 150,
            template: function(data) {
            	var url="";
            	if(data.settleStatusForPage=="未结清"){
            		url +="<a class='comRow-link editNewRepay-link' href='javascript:void(0);' data-id="+data.id+">修改</a>";
            	}
            	return url;
			}
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            buttonCount: 5,  //显示页数
            pageSize: 10,
            page: 1,
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

//移除输入框中所有值
function removeVal() {
	$(".k-invalid-msg").attr("style", "display:none");
	$(".k-invalid-msg").prev().removeClass("k-invalid");
	$(".k-invalid-msg").prev().attr("aria-invalid", "");
}


window.onload = function(){
    var clientHeight = 0;
    var realHeight = 0;
    //判断浏览器是否为IE
    var userAgent = navigator.userAgent;
    if(userAgent.indexOf("Firefox") > -1){
        clientHeight = $(window).height();
        realHeight = $(document).height();
    }else{
        clientHeight = document.body.clientHeight;
        realHeight = window.screen.availHeight;
    }
    if((realHeight-clientHeight) >= 64){
        $("#foot-fixBar").addClass("foot-fixBar");
    }else{
        $("#foot-fixBar").removeClass("foot-fixBar");
    }
    chkfix();
    $(window).scroll(function(){
        var scrollTop = $(window).scrollTop();
        var distanceTop = $('#footTop').offset().top - $(window).height();
        if(scrollTop >= distanceTop){
            $("#foot-fixBar").removeClass("foot-fixBar");
        }else{
            $("#foot-fixBar").addClass("foot-fixBar");
        }
    });
};
