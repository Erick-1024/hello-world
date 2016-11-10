var pageSize = 10;
var validatorForAutoAllocation;
var validatorForPlanAdjustment;
var validatorForNewPlanEdit; 
var validatorForNewExpenseEdit;
var validatorForNewExpenseRepayment;
$(function(){
	paidRepaymentPlan();
	accountRepaymentPlan();
	$(".repayment-navlink").click(function(event){
		var obj = $(event.target).parent("li");
		if(!obj.hasClass("repayment-nav-active")){
			obj.addClass("repayment-nav-active").siblings().removeClass("repayment-nav-active");
		}
		if(obj.is(".repayment-nav ul li:first")){
			$("#repayment-plan").removeClass("hidden");
			$("#repayment-cost").addClass("hidden");
			paidRepaymentPlan();
			accountRepaymentPlan();
		}else if(obj.is(".repayment-nav ul li:last")){
			$("#repayment-plan").addClass("hidden");
			$("#repayment-cost").removeClass("hidden");
			paidRepaymentExpense();
			accountRepaymentExpense();
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

    //打开新费用弹窗
    $("body").on("click", "#manualGrid-newCost .editNewRepay-link", function(e){
        $(".window-overlay").removeClass("hidden");
        $("#template-newCost").removeClass("hidden");
    });
    
  //关闭修改新费用弹窗
    $(".autoCloseBar, #template-newCost .back-link").click(function(){
        $(".window-overlay").addClass("hidden");
        $("#template-newCost").addClass("hidden");
    });
	
	// 自动分配
	$("body").on("click", "#autoAllocationCharge", function(){
		setValidator($("input[name='chargeForAutoAllocation']"), "required", true, currentRepaymentTotalAmountRule.required);
		validatorForAutoAllocation = $("input[name='chargeForAutoAllocation']").kendoValidator({
			rules: {
				currentRepaymentTotalAmountRule: currentRepaymentTotalAmountRule.checkRule
			},
			messages: {
				currentRepaymentTotalAmountRule: currentRepaymentTotalAmountRule.checkMessage
			},
			needRuleAttrbute : false
			}).data("kendoValidator");
		// 点击自动分配时验证
		if(!validatorForAutoAllocation.validate()) {
			return;
		};
		$.ajax({
			type: "POST",
			url: basepath+"/repayment/adjustment/autoAllocationCharge",
			data: { redisKey: $.trim($("input[name='redisKey']").val()),
					loanInfoId: $.trim($("input[name='loanInfoIdForAutoAllocation']").val()),
					charge: $.trim($("input[name='chargeForAutoAllocation']").val().parseMoney())
				},
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
					paidRepaymentPlan();
					accountRepaymentPlan();
				}else{
					showAlertWin(data.message);
				}
			}
		});	
	});
	
	// 已还费用弹窗验证增加
	paidExpenseRepaymentValidate();
	
	//打开本次还费用弹窗
	$("body").on("click", "#manualGrid-cost .editRepayed-link", function(e){
		$("span[name='errorPromptForExpense']").css("visibility","hidden");
		$(".window-overlay").removeClass("hidden");
		$("#template-editRepayed").removeClass("hidden");
		// 去除所有验证消息
		$(".k-invalid-msg").attr("style", "display:none");
		$(".k-invalid-msg").prev().removeClass("k-invalid");
		$(".k-invalid-msg").prev().attr("aria-invalid", "");
		$("input[name='paidAmout']").val("");
		$("input[name='offlineRepaymentDate']").val("");
		$("span[name='subjectItemForPay']").text($(this).parent().prev().prev().prev().text());
		$("#currentRepaymentFeeConfirm").attr("data-id",$(this).attr("data-id"));
	});

	//关闭调整弹窗
	$(".autoCloseBar, #template-editRepayed .back-link").click(function(){
		$(".window-overlay").addClass("hidden");
		$("#template-editRepayed").addClass("hidden");
	});

	// 对已还信息弹窗内容进行验证添加
	paidRepaymentPlanAdjustmentValidate();
	
	//打开本次还款信息弹窗
	$("body").on("click", "#manualGrid-plan .adjust-link", function(e){
		$("span[name='errorPromptForPlan']").css("visibility","hidden");
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth() + 1; 
		var day = date.getDate();
		month = month < 10 ? ("0" + month) : month;
		day = day < 10 ? ("0" + day) : day;
		var today = year + "-" + month + "-" + day;
		$("input[name='offlineRepaymentDateForPaid']").val(today);
		$(".k-invalid-msg").attr("style", "display:none");
		$(".k-invalid-msg").prev().removeClass("k-invalid");
		$(".k-invalid-msg").prev().attr("aria-invalid", "");
		$(".window-overlay").removeClass("hidden");
		$("#template-theRepay").removeClass("hidden");
		$("#paidRepaymentPlanAdjusetConfirm").attr("data-id",$(this).attr("data-id"));
		$("#paidRepaymentPlanAdjusetConfirm").attr("data-principal",$(this).attr("data-principal"));
		// 获取已还还款计划信息
		$.ajax({
			type: "POST",
			url: basepath+"/repayment/adjustment/getPaidRepaymentIncrement",
			data: { redisKey: $.trim($("input[name='redisKey']").val()),
					planId: $.trim($(this).attr("data-id"))},
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
					$("span[name='loanInfoIdForPaid']").text(data.data.loanNo);
					$("span[name='repaymentPeriodForPaid']").text(data.data.repaymentPeriod);
					$("span[name='valueDateForPaid']").text(data.data.valueDate);
					$("span[name='settleInterestDateForPaid']").text(data.data.settleInterestDate);
					$("span[name='repaymentDateForPaid']").text(data.data.repaymentDate);
					$("input[name='paidNormalPrincipalForPaid']").val(data.data.paidNormalPrincipal.formatMoney());
					$("input[name='paidNormalInterestForPaid']").val(data.data.paidNormalInterest);
					$("input[name='paidNormalServiceChargeForPaid']").val(data.data.paidNormalServiceCharge.formatMoney());
					$("input[name='paidOverduePrincipalForPaid']").val(data.data.paidOverduePrincipal.formatMoney());
					$("input[name='paidOverdueInterestForPaid']").val(data.data.paidOverdueInterest.formatMoney());
					$("input[name='paidOverdueServiceChargeForPaid']").val(data.data.paidOverdueServiceCharge.formatMoney());
					$("input[name='paidExtensionChargeForPaid']").val(data.data.paidExtensionCharge.formatMoney());
					$("input[name='paidOverdueManagerFeeForPaid']").val(data.data.paidOverdueManagerFee.formatMoney());
					$("input[name='settleStatusForPlanAdjustmentForPage']").val(data.data.settleStatusForPage);
					$("input[name='paidEarlyRepaymentChargeForPaid']").val(data.data.paidEarlyRepaymentCharge.formatMoney());
					$("input[name='loanInfoIdForPlan']").val(data.data.loanInfoId);
				}else{
					$(".window-overlay").addClass("hidden");
					$("#template-theRepay").addClass("hidden");
					paidRepaymentPlan();
					accountRepaymentPlan();
					showAlertWin(data.message);
				}
			}
		});
	});

	// 提前还款费用计算
	$("body").on("blur", "input[name='paidNormalPrincipalForPaid']", function(){
		var ratio = $("span[name='earlyRepaymentChargeRatio']").text();
		var result=earlyRepaymentChargeCalculate($("input[name='paidNormalPrincipalForPaid']").val(),$("#paidRepaymentPlanAdjusetConfirm").attr("data-principal"),ratio.substring(0,ratio.lastIndexOf("%")),$("input[name='offlineRepaymentDateForPaid']").val(),$("span[name='repaymentDateForPaid']").text());
		$("input[name='paidEarlyRepaymentChargeForPaid']").val(result);
	});
	
	// 提前还款费用计算
	$("body").on("change", "input[name='offlineRepaymentDateForPaid']", function(){
		var ratio = $("span[name='earlyRepaymentChargeRatio']").text();
		var result=earlyRepaymentChargeCalculate($("input[name='paidNormalPrincipalForPaid']").val(),$("#paidRepaymentPlanAdjusetConfirm").attr("data-principal"),ratio.substring(0,ratio.lastIndexOf("%")),$("input[name='offlineRepaymentDateForPaid']").val(),$("span[name='repaymentDateForPaid']").text());
		$("input[name='paidEarlyRepaymentChargeForPaid']").val(result);
	});
	
	//关闭调整弹窗
	$(".autoCloseBar, #template-theRepay .back-link").click(function(){
		$(".window-overlay").addClass("hidden");
		$("#template-theRepay").addClass("hidden");
	});
	
	//打开修改新还款信息弹窗
	$("body").on("click", "#manualGrid-newPlan .editRepay-link", function(e){
		$("span[name='errorPromptForNewPlan']").css("visibility","hidden");
		// 对已修改新计划弹窗内容进行验证添加
		accountRepaymentPlanEditInputValidate();
		$(".k-invalid-msg").attr("style", "display:none");
		$(".k-invalid-msg").prev().removeClass("k-invalid");
		$(".k-invalid-msg").prev().attr("aria-invalid", "");
		$(".window-overlay").removeClass("hidden");
		$("#template-newRepayPlan").removeClass("hidden");
		$("#newPlanConfirm").attr("data-id",$(this).attr("data-id"));
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
					$("input[name='accountPrincipalForNewPlan']").val(data.data.accountPrincipal.formatMoney());
					$("input[name='accountInterestForNewPlan']").val(data.data.accountInterest.formatMoney());
					$("input[name='accountServiceChargeForNewPlan']").val(data.data.accountServiceCharge.formatMoney());
					$("input[name='overduePrincipalForNewPlan']").val(data.data.overduePrincipal.formatMoney());
					$("input[name='overdueInterestForNewPlan']").val(data.data.overdueInterest.formatMoney());
					$("input[name='overdueServiceChargeForNewPlan']").val(data.data.overdueServiceCharge.formatMoney());
					$("input[name='extensionChargeForNewPlan']").val(data.data.extensionCharge.formatMoney());
					$("input[name='overdueManagerFeeForNewPlan']").val(data.data.overdueManagerFee.formatMoney());
					$("input[name='paidEarlyRepaymentChargeForNewPlan']").val(data.data.paidEarlyRepaymentCharge.formatMoney());
					$("span[name='settleStatusForNewPlan']").text(data.data.settleStatus);
					if(data.data.periodStatus == "OVERDUE"){
						$("input[name='accountPrincipalForNewPlan']").attr("disabled",true)
						$("input[name='accountInterestForNewPlan']").attr("disabled",true)
						$("input[name='accountServiceChargeForNewPlan']").attr("disabled",true)
					}
					if(data.data.periodStatus != "CURRENTIN" && data.data.periodStatus != "CURRENTOUT"){
						$("input[name='paidEarlyRepaymentChargeForNewPlan']").attr("disabled",true);
					}
					if(data.data.periodStatus == "CURRENTIN" || data.data.periodStatus == "CURRENTOUT"){
						$("input[name='overduePrincipalForNewPlan']").attr("disabled",true);
						$("input[name='overdueInterestForNewPlan']").attr("disabled",true);
						$("input[name='overdueServiceChargeForNewPlan']").attr("disabled",true);
						$("input[name='overdueManagerFeeForNewPlan']").attr("disabled",true);
					}
					$("input[name='settleStatusForNewPlanForPage']").val(data.data.settleStatusForPage);
				}else{
					$(".window-overlay").addClass("hidden");
					$("#template-newRepayPlan").addClass("hidden");
					paidRepaymentPlan();
					accountRepaymentPlan();
					showAlertWin(data.message);
				}
			}
		});
	});

	//关闭调整弹窗
	$(".autoCloseBar, #template-newRepayPlan .back-link").click(function(){
		$(".window-overlay").addClass("hidden");
		$("#template-newRepayPlan").addClass("hidden");
	});
	
	// 修改新费用
	$("body").delegate(".editNewRepay-link", "click", function() {
		$("span[name='errorPromptForNewExpense']").css("visibility","hidden");
		$("span[name='subjectItemForNewPay']").text($(this).parent().prev().prev().prev().prev().text());
		$("input[name='accountAmount']").val($(this).parent().prev().prev().text().formatMoney());
		var accountAmount = $.trim($("input[name='accountAmount']").val().parseMoney());
		if(Number(accountAmount).toFixed(2) == 0){
			$("span[name='settleStatusForNewExpense']").text("已结清");
		}else{
			$("span[name='settleStatusForNewExpense']").text("未结清");
		}
		$("#editRepaymentExpenseConfirm").attr("data-id",$(this).attr("data-id"));
	});
	
	// 还款(费用)->确定
	$("body").delegate("#currentRepaymentFeeConfirm", "click", function() {
		$("span[name='errorPromptForExpense']").css("visibility","hidden");
		if(!validatorForNewExpenseRepayment.validate()) {
			return;
		};
		$.ajax({
			type: "POST",
			url: basepath+"/repayment/adjustment/updateCurrentRepaymentExpense",
			data: { redisKey: $.trim($("input[name='redisKey']").val()),
					expenseId: $.trim($(this).attr("data-id")),
					paidAmount: $.trim($("input[name='paidAmout']").val().parseMoney()),
					repaymentDate: $.trim($("input[name='offlineRepaymentDate']").val())},
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
					$(".window-overlay").addClass("hidden");
					$("#template-editRepayed").addClass("hidden");
					paidRepaymentExpense();
					accountRepaymentExpense();
				}else{
					$("span[name='errorPromptForExpense']").text(data.message);
	        		$("span[name='errorPromptForExpense']").css("visibility","visible");
				}
			}
		});	
	});
	
	// 判断计划结清状态
	$("body").delegate(".forEditNewPlan", "blur", function() {
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
	
	// 判断费用结清状态
	$("body").delegate("input[name='accountAmount']", "blur", function() {
		var accountAmount = $.trim($("input[name='accountAmount']").val().parseMoney());
		if(Number(accountAmount).toFixed(2) == 0 && accountAmount!=="" ){
			$("span[name='settleStatusForNewExpense']").text("已结清");
		}else{
			$("span[name='settleStatusForNewExpense']").text("未结清");
		}
	});
	
	//修改新费用->保存
	$("body").delegate("#editRepaymentExpenseConfirm", "click", function(e) {
		$("span[name='errorPromptForNewExpense']").css("visibility","hidden");
		if(!validatorForNewExpenseEdit.validate()) {
			return;
		};
		$.ajax({
			type: "POST",
			url: basepath+"/repayment/adjustment/accountExpenseEdit",
			data: { redisKey: $.trim($("input[name='redisKey']").val()),
					expenseId: $.trim($(this).attr("data-id")),
					accountExpenseAmount: $.trim($("input[name='accountAmount']").val().parseMoney())},
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
					$(".window-overlay").addClass("hidden");
			        $("#template-newCost").addClass("hidden");
					paidRepaymentExpense();
					accountRepaymentExpense();
				}else{
					$("span[name='errorPromptForNewExpense']").text(data.message);
	        		$("span[name='errorPromptForNewExpense']").css("visibility","visible");
				}
			}
		});	
	});
	
	// 还款计划调整->保存
	$("body").delegate("#paidRepaymentPlanAdjusetConfirm", "click", function() {
		$("span[name='errorPromptForPlan']").css("visibility","hidden");
		if(!validatorForPlanAdjustment.validate()){
			return;
		};
		$.ajax({
			type: "POST",
			url: basepath+"/repayment/adjustment/paidRepaymentInfoAdjustment",
			data: { redisKey: $.trim($("input[name='redisKey']").val()),
				loanNo: $.trim($("span[name='loanInfoIdForPaid']").text()),
				repaymentPeriod: $.trim($("span[name='repaymentPeriodForPaid']").text()),
				valueDate: $.trim($("span[name='valueDateForPaid']").text()),
				settleInterestDate: $.trim($("span[name='settleInterestDateForPaid']").text()),
				repaymentDate: $.trim($("span[name='repaymentDateForPaid']").text()),
				offlineRepaymentDate: $.trim($("input[name='offlineRepaymentDateForPaid']").val()),
				paidNormalPrincipal: $.trim($("input[name='paidNormalPrincipalForPaid']").val().parseMoney()),
				paidNormalInterest: $.trim($("input[name='paidNormalInterestForPaid']").val().parseMoney()),
				paidNormalServiceCharge: $.trim($("input[name='paidNormalServiceChargeForPaid']").val().parseMoney()),
				paidOverduePrincipal: $.trim($("input[name='paidOverduePrincipalForPaid']").val().parseMoney()),
				paidOverdueInterest: $.trim($("input[name='paidOverdueInterestForPaid']").val().parseMoney()),
				paidOverdueServiceCharge: $.trim($("input[name='paidOverdueServiceChargeForPaid']").val().parseMoney()),
				paidExtensionCharge: $.trim($("input[name='paidExtensionChargeForPaid']").val().parseMoney()),
				paidOverdueManagerFee: $.trim($("input[name='paidOverdueManagerFeeForPaid']").val().parseMoney()),
				paidEarlyRepaymentCharge: $.trim($("input[name='paidEarlyRepaymentChargeForPaid']").val().parseMoney()),
				settleStatusForPage: $.trim($("input[name='settleStatusForPlanAdjustmentForPage']").val()),
				loanInfoId: $.trim($("input[name='loanInfoIdForPlan']").val()),
				id: $.trim($(this).attr("data-id"))
			},
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
					$(".window-overlay").addClass("hidden");
					$("#template-theRepay").addClass("hidden");
					paidRepaymentPlan();
					accountRepaymentPlan();
				}else{
					$("span[name='errorPromptForPlan']").text(data.message);
	        		$("span[name='errorPromptForPlan']").css("visibility","visible");
				}
			}
		});	
	});
	
	// 还款计划修改->保存
	$("body").delegate("#newPlanConfirm", "click", function() {
		$("span[name='errorPromptForNewPlan']").css("visibility","hidden");
		if(!validatorForNewPlanEdit.validate()) {
			return;
		};
		$.ajax({
			type: "POST",
			url: basepath+"/repayment/adjustment/updateAccountRepaymentInfo",
			data: { redisKey: $.trim($("input[name='redisKey']").val()),
				loanNo: $.trim($("span[name='loanNoForNewPlan']").text()),
				repaymentPeriod: $.trim($("span[name='repaymentPeriodForNewPlan']").text().parseMoney()),
				financeBalance: $.trim($("span[name='financeBalanceForNewPlan']").text().parseMoney()),
				valueDate: $.trim($("span[name='valueDateForNewPlan']").text()),
				settleStatus: $.trim($("span[name='settleStatusForNewPlan']").text()),
				settleInterestDate: $.trim($("span[name='settleInterestDateForNewPlan']").text()),
				repaymentDate: $.trim($("span[name='repaymentDateForNewPlan']").text()),
				accountPrincipal: $.trim($("input[name='accountPrincipalForNewPlan']").val().parseMoney()),
				accountInterest: $.trim($("input[name='accountInterestForNewPlan']").val().parseMoney()),
				accountServiceCharge: $.trim($("input[name='accountServiceChargeForNewPlan']").val().parseMoney()),
				overduePrincipal: $.trim($("input[name='overduePrincipalForNewPlan']").val().parseMoney()),
				overdueInterest: $.trim($("input[name='overdueInterestForNewPlan']").val().parseMoney()),
				overdueServiceCharge: $.trim($("input[name='overdueServiceChargeForNewPlan']").val().parseMoney()),
				extensionCharge: $.trim($("input[name='extensionChargeForNewPlan']").val().parseMoney()),
				overdueManagerFee: $.trim($("input[name='overdueManagerFeeForNewPlan']").val().parseMoney()),
				paidEarlyRepaymentCharge: $.trim($("input[name='paidEarlyRepaymentChargeForNewPlan']").val()),
				settleStatusForPage: $.trim($("input[name='settleStatusForNewPlanForPage']").val()),
				id: $.trim($(this).attr("data-id"))
			},
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
					$(".window-overlay").addClass("hidden");
					$("#template-newRepayPlan").addClass("hidden");
					paidRepaymentPlan();
					accountRepaymentPlan();
				}else{
					$("span[name='errorPromptForNewPlan']").text(data.message);
	        		$("span[name='errorPromptForNewPlan']").css("visibility","visible");
				}
			}
		});	
	});
	
	// 确认还款
	$("body").delegate("#repaymentConfirm", "click", function() {
		$.ajax({
			type: "POST",
			url: basepath+"/repayment/adjustment/submitValidate",
			data: { redisKey: $.trim($("input[name='redisKey']").val())
			},
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
					$("#repaymentPlanAndExpense").submit();
				}else{
					showAlertWin(data.message);
				}
			}
		});
		
		
	});

	// 初始化弹窗添加事件
	$("body").delegate(".editNewRepay-link", "click", function() {
		$("span[name='errorPromptForNewExpense']").css("visibility","hidden");
		setValidator($("input[name='accountAmount']"), "required", true, accountAmountRule.required);
		validatorForNewExpenseEdit = $("#newExpenseEdit").kendoValidator({
			rules: {
				accountAmountRule: accountAmountRule.checkRule
			},
			messages: {
				accountAmountRule: accountAmountRule.checkMessage
			},
			needRuleAttrbute : false
		}).data("kendoValidator");
	});
	
    //历史明细弹窗
    new PopWindow(".repayDetail-link", {
        title: "历史还款明细",
        width: 800,
        reload: true,
        template: "#template-repayDetail"
    }).init();

    $("body").on("click", ".dlg-wrapper-foot .close-window", function(e){
        var win = $(e.target).closest(".k-window");
        var overlay = win.prev(".k-overlay");
        if(win.is(":visible")){
            win.css("display", "none");
            overlay.css("display", "none");
        }
    });
    
	//历史还款明细点击事件
	$("body").on("click",".repayDetail-link", function(){
		$.ajax({
			type: 'post',
			url: basepath + "/loanInfo/manage/getRepaymentDetailsHistory",
			data: {loanId:$.trim($("input[name='loanInfoIdForAutoAllocation']").val())},
			success: function(data){
				if(null == data.data){
					$("#noRepaymentDetails").removeClass("hidden");
				}else{
					$("#haveRepaymentDetails").removeClass("hidden");
					var json = data.data;
					$(json).each(function(){
						var text = "<tr>";
						text += "<td>"+this.operatingTime+"</td>";
						text += "<td>"+this.repaymentMethod+"</td>";
						text += "<td>"+this.amountDetails+"</td>";
						text += "<td>"+this.offlineTime+"</td>";
						text += "</tr>";
						$(text).appendTo("#repaymentDetailsHistory");
					});
				}
			}
		});
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

Number.prototype.add = function(arg){   
    var r1,r2,m;   
    try{r1=this.toString().split(".")[1].length}catch(e){r1=0}   
    try{r2=arg.toString().split(".")[1].length}catch(e){r2=0}   
    m=Math.pow(100,Math.max(r1,r2))   
    return (this*m+arg*m)/m   
}

// 设置已还计划调整验证
function paidRepaymentPlanAdjustmentValidate(){
	setValidator($("input[name='offlineRepaymentDateForPaid']"), "required", true, offlineRepaymentDateForPaidRule.required);
	setValidator($("input[name='paidNormalPrincipalForPaid']"), "required", true, normalPrincipalForPaidRule.required);
	setValidator($("input[name='paidNormalInterestForPaid']"), "required", true, normalInterestForPaidRule.required);
	setValidator($("input[name='paidNormalServiceChargeForPaid']"), "required", true, normalServiceChargeForPaidRule.required);
	setValidator($("input[name='paidOverduePrincipalForPaid']"), "required", true, overduePrincipalForPaidRule.required);
	setValidator($("input[name='paidOverdueInterestForPaid']"), "required", true, overdueInterestForPaidRule.required);
	setValidator($("input[name='paidOverdueServiceChargeForPaid']"), "required", true, overdueServiceChargeForPaidRule.required);
	setValidator($("input[name='paidExtensionChargeForPaid']"), "required", true, extensionChargeForPaidRule.required);
	setValidator($("input[name='paidOverdueManagerFeeForPaid']"), "required", true, overdueManagerFeeForPaidRule.required);
	setValidator($("input[name='paidEarlyRepaymentChargeForPaid']"), "required", true, earlyRepaymentChargeForPaidRule.required);
	validatorForPlanAdjustment = $("#frmForAdjustment").kendoValidator({
		rules: {
			normalPrincipalForPaidRule: normalPrincipalForPaidRule.checkRule,
			normalInterestForPaidRule: normalInterestForPaidRule.checkRule,
			normalServiceChargeForPaidRule: normalServiceChargeForPaidRule.checkRule,
			overduePrincipalForPaidRule: overduePrincipalForPaidRule.checkRule,
			overdueInterestForPaidRule: overdueInterestForPaidRule.checkRule,
			overdueServiceChargeForPaidRule: overdueServiceChargeForPaidRule.checkRule,
			extensionChargeForPaidRule: extensionChargeForPaidRule.checkRule,
			overdueManagerFeeForPaidRule: overdueManagerFeeForPaidRule.checkRule,
			earlyRepaymentChargeForPaidRule: earlyRepaymentChargeForPaidRule.checkRule
		},
		messages: {
			normalPrincipalForPaidRule: normalPrincipalForPaidRule.checkMessage,
			normalInterestForPaidRule: normalInterestForPaidRule.checkMessage,
			normalServiceChargeForPaidRule: normalServiceChargeForPaidRule.checkMessage,
			overduePrincipalForPaidRule: overduePrincipalForPaidRule.checkMessage,
			overdueInterestForPaidRule: overdueInterestForPaidRule.checkMessage,
			overdueServiceChargeForPaidRule: overdueServiceChargeForPaidRule.checkMessage,
			extensionChargeForPaidRule: extensionChargeForPaidRule.checkMessage,
			overdueManagerFeeForPaidRule: overdueManagerFeeForPaidRule.checkMessage,
			earlyRepaymentChargeForPaidRule: earlyRepaymentChargeForPaidRule.checkMessage
		},
		needRuleAttrbute : false
	}).data("kendoValidator");
}

//设置新计划修改验证
function accountRepaymentPlanEditInputValidate(){
	setValidator($("input[name='accountPrincipalForNewPlan']"), "required", true, accountPrincipalForNewPlanRule.required);
	setValidator($("input[name='accountInterestForNewPlan']"), "required", true, accountInterestForNewPlanRule.required);
	setValidator($("input[name='accountServiceChargeForNewPlan']"), "required", true, accountServiceChargeForNewPlanRule.required);
	setValidator($("input[name='overduePrincipalForNewPlan']"), "required", true, overduePrincipalForNewPlanRule.required);
	setValidator($("input[name='overdueInterestForNewPlan']"), "required", true, overdueInterestForNewPlanRule.required);
	setValidator($("input[name='overdueServiceChargeForNewPlan']"), "required", true, overdueServiceChargeForNewPlanRule.required);
	setValidator($("input[name='extensionChargeForNewPlan']"), "required", true, extensionChargeForNewPlanRule.required);
	setValidator($("input[name='overdueManagerFeeForNewPlan']"), "required", true, overdueManagerFeeForNewPlanRule.required);
	setValidator($("input[name='paidEarlyRepaymentChargeForNewPlan']"), "required", true, paidEarlyRepaymentChargeForNewPlanRule.required);
	
	validatorForNewPlanEdit = $("#frmForEdit").kendoValidator({
		rules: {
			accountPrincipalForNewPlanRule: accountPrincipalForNewPlanRule.checkRule,
			accountInterestForNewPlanRule: accountInterestForNewPlanRule.checkRule,
			accountServiceChargeForNewPlanRule: accountServiceChargeForNewPlanRule.checkRule,
			overduePrincipalForNewPlanRule: overduePrincipalForNewPlanRule.checkRule,
			overdueInterestForNewPlanRule: overdueInterestForNewPlanRule.checkRule,
			overdueServiceChargeForNewPlanRule: overdueServiceChargeForNewPlanRule.checkRule,
			extensionChargeForNewPlanRule: extensionChargeForNewPlanRule.checkRule,
			overdueManagerFeeForNewPlanRule: overdueManagerFeeForNewPlanRule.checkRule,
			paidEarlyRepaymentChargeForNewPlanRule: paidEarlyRepaymentChargeForNewPlanRule.checkRule
		},
		messages: {
			accountPrincipalForNewPlanRule: accountPrincipalForNewPlanRule.checkMessage,
			accountInterestForNewPlanRule: accountInterestForNewPlanRule.checkMessage,
			accountServiceChargeForNewPlanRule: accountServiceChargeForNewPlanRule.checkMessage,
			overduePrincipalForNewPlanRule: overduePrincipalForNewPlanRule.checkMessage,
			overdueInterestForNewPlanRule: overdueInterestForNewPlanRule.checkMessage,
			overdueServiceChargeForNewPlanRule: overdueServiceChargeForNewPlanRule.checkMessage,
			extensionChargeForNewPlanRule: extensionChargeForNewPlanRule.checkMessage,
			overdueManagerFeeForNewPlanRule: overdueManagerFeeForNewPlanRule.checkMessage,
			paidEarlyRepaymentChargeForNewPlanRule: paidEarlyRepaymentChargeForNewPlanRule.checkMessage
		},
		needRuleAttrbute : false
	}).data("kendoValidator");
}

// 已还费用弹窗验证增加
function paidExpenseRepaymentValidate(){
	setValidator($("input[name='paidAmout']"), "required", true, currentRepaymentAmountRule.required);
	
	setValidator($("input[name='offlineRepaymentDate']"), "required", true, offlineRepaymentDateForPaidRule.required);

	validatorForNewExpenseRepayment = $("#newExpenseRepayment").kendoValidator({
		rules: {
			currentRepaymentAmountRule: currentRepaymentAmountRule.checkRule,
		},
		messages: {
			currentRepaymentAmountRule: currentRepaymentAmountRule.checkMessage,
		},
		needRuleAttrbute : false
	}).data("kendoValidator");
}

// 计算提前还款手续费
function earlyRepaymentChargeCalculate(paidNormalPrincipal, accountPrincipal, earlyRepaymentChargeRate, offlineRepaymentDate, repaymentDate){
	var pNP = Number(paidNormalPrincipal.parseMoney());
	var aP = Number(accountPrincipal.parseMoney());
	var eRCR = Number(earlyRepaymentChargeRate.parseMoney());
	var compareResult = dateCompare(offlineRepaymentDate,repaymentDate);
	if(compareResult == -1){
		if(pNP <= 0){
			return Number(0).toFixed(2);
		}
		return (pNP*eRCR/100).toFixed(2);
	}else if(compareResult == 0){
		if((pNP-aP) <= 0){
			return Number(0).toFixed(2);
		}
		return ((pNP-aP)*eRCR/100).toFixed(2);
	}else{
		return Number(0).toFixed(2);
	}
}

// 日期大小比较
function dateCompare(startdate,enddate){   
	var arr=startdate.split("-");    
	var starttime=new Date(arr[0],arr[1],arr[2]);    
	var starttimes=starttime.getTime();   
  
	var arrs=enddate.split("-");    
	var lktime=new Date(arrs[0],arrs[1],arrs[2]);    
	var lktimes=lktime.getTime();   
  
	if(starttimes > lktimes) 
		return 1;
	else if(starttimes == lktimes)
		return 0;
	else
		return -1;
} 

//已还还款计划信息
function paidRepaymentPlan(){
	$("#manualGrid-plan").remove();
	$("#manualGrid-planWrap").append("<div class='finance-grid' id='manualGrid-plan'></div>");
    $("#manualGrid-plan").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
            pageSize: pageSize,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                    data: { redisKey: $.trim($("input[name='redisKey']").val()) },
                    type : "POST",
                    url: basepath + "/repayment/adjustment/getPaidRepaymentPlan"
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
	  					$("#manualGrid-planWrap .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#manualGrid-planWrap .k-grid-header-wrap").css("overflow-x","");
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
            field: "repaymentPeriod",
            title: "期数",
            width: 50,
            attributes: {
                style: "text-align: center"
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
            field: "paidNormalPrincipal",
            title: "已还本金",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.paidNormalPrincipal + "";
            	if(data.paidNormalPrincipalNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        },{
            field: "paidNormalInterest",
            title: "已还收益",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.paidNormalInterest + "";
            	if(data.paidNormalInterestNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        },{
            field: "paidNormalServiceCharge",
            title: "已还服务费",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.paidNormalServiceCharge + "";
            	if(data.paidNormalServiceChargeNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        },{
            field: "paidOverduePrincipal",
            title: "已还逾期本金",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.paidOverduePrincipal + "";
            	if(data.paidOverduePrincipalNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        },{
            field: "paidOverdueInterest",
            title: "已还逾期收益",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.paidOverdueInterest + "";
            	if(data.paidOverdueInterestNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        },{
            field: "paidOverdueServiceCharge",
            title: "已还逾期服务费",
            width: 150,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.paidOverdueServiceCharge + "";
            	if(data.paidOverdueServiceChargeNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        },{
            field: "paidExtensionCharge",
            title: "已还展期费用",
            width: 150,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.paidExtensionCharge + "";
            	if(data.paidExtensionChargeNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        },{
            field: "paidOverdueManagerFee",
            title: "已还逾期管理费",
            width: 150,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.paidOverdueManagerFee + "";
            	if(data.paidOverdueManagerFeeNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        },{
            field: "paidEarlyRepaymentCharge",
            title: "已还提前还款手续费",
            width: 150,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.paidEarlyRepaymentCharge + "";
            	if(data.paidEarlyRepaymentChargeNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        },{
            field: "paidTotalAmount",
            title: "已还总金额",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.paidTotalAmount + "";
            	if(data.paidTotalAmountNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        },{
            title: "操作",
            sortable: false,
            width:100,
            template: function(data){
            	var url="";
            	if( data.periodStatus != "FUTURE" && data.settleStatusForPage=="未结清"){
            		url += "<a class='comRow-link adjust-link' href='javascript:void(0);' data-id='"+data.id+"' data-principal='"+data.accountPrincipal+"'>调整</a>";
            	}
            	return url; 
            },
            editable: "popup"
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            pageSize: pageSize,
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

//新还款计划
function accountRepaymentPlan(){
	$("#manualGrid-newPlan").remove();
	$("#manualGrid-newPlanWrap").append("<div class='finance-grid' id='manualGrid-newPlan'></div>");
    $("#manualGrid-newPlan").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
            pageSize: 10,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                    data: { redisKey: $.trim($("input[name='redisKey']").val())  },
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
	  					$("#manualGrid-newPlanWrap .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#manualGrid-newPlanWrap .k-grid-header-wrap").css("overflow-x","");
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
            template: function(data){
            	var money = data.accountPrincipal + "";
            	return money.formatMoney();	
            }
        },{
            field: "accountInterest",
            title: "应还收益",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.accountInterest + "";
            	return money.formatMoney();	
            }
        },{
            field: "accountServiceCharge",
            title: "应还服务费",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.accountServiceCharge + "";
            	return money.formatMoney();	
            }
        },{
            field: "overduePrincipal",
            title: "逾期本金",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.overduePrincipal + "";
            	return money.formatMoney();	
            }
        },{
            field: "overdueInterest",
            title: "逾期收益",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.overdueInterest + "";
            	return money.formatMoney();	
            }
        },{
            field: "overdueServiceCharge",
            title: "逾期服务费",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.overdueServiceCharge + "";
            	return money.formatMoney();	
            }
        },{
            field: "extensionCharge",
            title: "展期费用",
            width: 150,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.extensionCharge + "";
            	return money.formatMoney();	
            }
        },{
            field: "overdueManagerFee",
            title: "逾期管理费",
            width: 150,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.overdueManagerFee + "";
            	return money.formatMoney();	
            }
        },{
            field: "accountTotalAmount",
            title: "应还总金额",
            width: 150,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.accountTotalAmount + "";
            	return money.formatMoney();	
            }
        },{
            field: "settleStatus",
            title: "结清状态",
            width: 120
        },{
            title: "操作",
            sortable: false,
            width:100,
            template: function(data){
            	var url="";
            	if(data.settleStatusForPage=="未结清"){
            		url += "<a class='comRow-link editRepay-link' href='javascript:void(0);' data-id='"+data.id+"'>修改</a>";
            	}
            	return url; 
            },
            editable: "popup"
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            pageSize: pageSize,
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

//已还还款费用
function paidRepaymentExpense(){
	$("#manualGrid-cost").remove();
	$("#manualGrid-costWrap").append("<div class='finance-grid' id='manualGrid-cost'></div>");
    $("#manualGrid-cost").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
            pageSize: 10,
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
	  					$("#manualGrid-costWrap .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#manualGrid-costWrap .k-grid-header-wrap").css("overflow-x","");
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
            field: "expenseSubject",
            title: "费用明目",
            width: 120
        },{
            field: "repaymentDate",
            title: "还款日",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "paidAmount",
            title: "已还金额",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.paidAmount + "";
            	if(data.paidAmountNew != null){
            		return "<font color='red'>" + money.formatMoney() + "</font>";	
            	}else{
            		return money.formatMoney();	
            	}
            }
        },{
            title: "操作",
            sortable: false,
            width:100,
            template: function(data){
            	var url="";
            	if(data.settleStatusForPage=="未结清"){
            		url += "<a class='comRow-link editRepayed-link' href='javascript:void(0);' data-id='"+data.id+"'>还款</a>";
            	}
            	return url;
            },
            editable: "popup"
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            pageSize: pageSize,
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

//新还款费用
function accountRepaymentExpense(){
	$("#manualGrid-newCost").remove();
	$("#manualGrid-newCostWrap").append("<div class='finance-grid' id='manualGrid-newCost'></div>");
    $("#manualGrid-newCost").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
            pageSize: 10,
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
	  					$("#manualGrid-newCostWrap .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#manualGrid-newCostWrap .k-grid-header-wrap").css("overflow-x","");
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
            field: "expenseSubject",
            title: "费用明目",
            width: 120
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
            template: function(data){
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
            title: "操作",
            sortable: false,
            width:100,
            template: function(data){
            	if(data.settleStatusForPage=="未结清"){
            		return	"<a class='comRow-link editNewRepay-link' href='javascript:void(0);' data-id='"+data.id+"'>修改</a>";
            	}
            	return "";
            },
            editable: "popup"
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            pageSize: pageSize,
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
