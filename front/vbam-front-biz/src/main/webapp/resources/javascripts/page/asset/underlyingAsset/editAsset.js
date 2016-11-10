var assetValidator;
var canSubmit = true;
$(function(){
	initValidator();
	
	//日期
	initDatepicker();
	
	//专项计划编号获焦事件
	$("body").on("focus", "#special-plan", function() {
		loadSpecialProgramPop();
	});
	
	//提交操作
	$("body").on("click","#submitEditAssetBtn",function() {
		submitEditAsset();
	});
	
	//提前还款下拉框，展期天数下拉框，违约资产下拉框
	$("body").on("change","#forwardSelect,#extensionSelect,#overdueSelect",function(){
		$("#forwardDays,#extensionDays,#overdueDays").trigger("blur");
	});
	
	$("body").on("click", "#interests-btn", function(){
		confirmSpecialProgram();
	});
	
})

//提交操作
function submitEditAsset(){
	if (!assetValidator.validate()) {
		return;
	}
	if(canSubmit) {
		canSubmit = false;
		var postData = {};
		postData.contractNo = $('#businessContractNo').val();
		postData.underlyingAssetId = $('#loanNo').val();
		postData.customerName = $.trim($("#customerName").val());
		postData.customerEconomicCategory =　$.trim($("#customerEconomicCategory").val());
		postData.customerCity =　$.trim($("#customerCity").val());
		postData.customerIndustry =　$.trim($("#customerIndustry").val());
		postData.customerRating =　$.trim($("#customerRating").val());
		postData.counterparty =　$.trim($("#counterparty").val());
		postData.counterpartyEconomicCategory =　$.trim($("#counterpartyEconomicCategory").val());
		postData.counterpartyCity =　$.trim($("#counterpartyCity").val());
		postData.counterpartyIndustry =　$.trim($("#counterpartyIndustry").val());
		postData.counterpartyRating =　$.trim($("#counterpartyRating").val());
		postData.guaranteeInfo =　$.trim($("#guaranteeInfo").val());
		postData.guaranteeType =　$.trim($("#guaranteeType").val());
		postData.guaranteeCompanyInfo =　$.trim($("#guaranteeCompanyInfo").val());
		postData.guaranteeCompanyType =　$.trim($("#guaranteeCompanyType").val());
		postData.guaranteeGoodsNo =　$.trim($("#guaranteeGoodsNo").val());
		postData.creditLimit =　$.trim($("#creditLimit").val().parseMoney());
		postData.creditBalance =　$.trim($("#creditBalance").val().parseMoney());
		postData.counterpartyLimit =　$.trim($("#counterpartyLimit").val().parseMoney());
		postData.counterpartyBalance =　$.trim($("#counterpartyBalance").val().parseMoney());
		postData.invoiceAmount =　$.trim($("#invoiceAmount").val().parseMoney());
		postData.invoiceBalance =　$.trim($("#invoiceBalance").val().parseMoney());
		postData.financeAmount =　$.trim($("#financeAmount").val().parseMoney());
		postData.financeBalance =　$.trim($("#financeBalance").val().parseMoney());
		postData.loanDate =　$.trim($("#loanDate").val());
		postData.dueDate =　$.trim($("#dueDate").val());
		postData.repaymentMethod =　$.trim($("#repaymentMethod").val());
		postData.interestRateUnit =　$.trim($("#interestRateUnitSelect").val());
		postData.interestRate =　$.trim($("#interestRate").val());
		postData.loanPeriod =　$.trim($("#loanPeriod").val());
		postData.fiveLevelCategory =　$.trim($("#fiveLevelCategory").val());
		postData.settleStatus =　$.trim($("#settleStatusSelect").val());
		postData.forwardDays =　$.trim($("#forwardDays").val());
		postData.overdueDays =　$.trim($("#overdueDays").val());
		postData.extensionDays =　$.trim($("#extensionDays").val());
		postData.programId = $('#special-plan').val();
		cana.postJson(basepath + "/asset/underlyingAsset/editAsset", postData, submitSuccess, showErrorBox, function(data) {canSubmit = true;$("#submitEditAssetBtn").text('提交');});
		$(this).text('提交中...');
	}
}

function submitSuccess(data) {
	var underlyingAssetId = $('#loanNo').val();
	showTipBox("notice-icon02", "操作成功，3秒后自动跳转到详情页");
	gou(3, basepath + "/asset/underlyingAsset/underlyingAssetDetail?underlyingAssetId=" + underlyingAssetId)
}

function gou(secs, surl) {
	if(--secs > 0) {
		setTimeout("gou("+secs+",'"+surl+"')",1000);
	} else {
		window.location.href = surl;
	}
}

//弹出错误弹框
function showErrorBox(message){
	showTipBox("notice-icon01", message);
}

//初始化校验对象
function initValidator(){
	setValidator($("#forwardDays"), "pattern", periodRule.pattern, periodRule.message);
	setValidator($("#extensionDays"), "pattern", periodRule.pattern, periodRule.message);
	setValidator($("#overdueDays"), "pattern", periodRule.pattern, periodRule.message);
	assetValidator = $("#editAssetForm").kendoValidator({
		rules: {
			amountMinRule: amountRule.ruleMin,
			creditLimit: amountRule.rulePattern,
			creditBalance: amountRule.rulePattern,
			credit: creditBalanceRule.ruleMax,
			counterpartyLimit: amountRule.rulePattern,
			counterpartyBalance: amountRule.rulePattern,
			counterparty: counterpartyBalanceRule.ruleMax,
			invoiceLimit: amountRule.rulePattern,
			invoiceBalance: amountRule.rulePattern,
			invoice: invoiceBalanceRule.ruleMax,
			financeLimit: amountRule.rulePattern,
			financeBalance: amountRule.rulePattern,
			finance: financeBalanceRule.ruleMax,
			rateMinRule: rateRule.ruleMin,
			rateRulePattern: rateRule.rulePattern,
			forwardDays: forwardDaysRule.ruleMatch,
			extensionDays: extensionDaysRule.ruleMatch,
			overdueDays: overdueDaysRule.ruleMatch,
		},
		messages: {
			amountMinRule: amountRule.ruleMinMessage,
			creditLimit: amountRule.ruleMessage,
			creditBalance: amountRule.ruleMessage,
			credit: creditBalanceRule.ruleMaxMessage,
			counterpartyLimit: amountRule.ruleMessage,
			counterpartyBalance: amountRule.ruleMessage,
			counterparty: counterpartyBalanceRule.ruleMaxMessage,
			invoiceLimit: amountRule.ruleMessage,
			invoiceBalance: amountRule.ruleMessage,
			invoice: invoiceBalanceRule.ruleMaxMessage,
			financeLimit: amountRule.ruleMessage,
			financeBalance: amountRule.ruleMessage,
			finance: financeBalanceRule.ruleMaxMessage,
			rateMinRule: rateRule.ruleMinMessage,
			rateRulePattern: rateRule.ruleMessagePattern,
			forwardDays: forwardDaysRule.ruleMatchMessage,
			extensionDays: extensionDaysRule.ruleMatchMessage,
			overdueDays: overdueDaysRule.ruleMatchMessage,
		},
		needRuleAttrbute : false
	}).data("kendoValidator");
}

//初始化日期
function initDatepicker(){
	$(".time-one").datepicker({
		format : "yyyy-mm-dd",
		language : "zh-CN",
		autoclose : true,
		todayHighlight : true,
		weekStart : 0,
		firstDay : 0,
		onClose : function(selectedDate) {
			if(selectedDate!=""){
				$("input.time-two").datepicker("option","minDate", DateAdd("d", "1", new Date(selectedDate)).format("yyyy-MM-dd"));
			}
		}
	}).on("show", function() {
		$("div.datepicker table thead .prev").html("");
		$("div.datepicker table thead .next").html("");
	});
	$(".time-two").datepicker({
		format : "yyyy-mm-dd",
		language : "zh-CN",
		autoclose : true,
		todayHighlight : true,
		weekStart : 0,
		firstDay : 0,
		onClose : function(selectedDate) {
			if(selectedDate!=""){
				$("input.time-one").datepicker("option","maxDate", DateAdd("d", "-1", new Date(selectedDate)).format("yyyy-MM-dd"));
			}
		}
	}).on("show", function() {
		$("div.datepicker table thead .prev").html("");
		$("div.datepicker table thead .next").html("");
	});
}

