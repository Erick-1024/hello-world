$(function() {
	setValidator($("#deductionTime"), "required", true, deductionTimeRule.required);
	setValidator($("#deductionTime"), "pattern", deductionTimeRule.pattern, deductionTimeRule.message);
	setValidator($("#extensionDays"), "required", true, extensionDaysRule.required);
	setValidator($("#extensionDays"), "pattern", extensionDaysRule.pattern, extensionDaysRule.message);
	setValidator($("#extensionRatio"), "required", true, extensionRatioRule.required);
	setValidator($("#extensionRatio"), "pattern", extensionRatioRule.pattern, extensionRatioRule.message);
	setValidator($("#penaltyRate"), "required", true, penaltyRateRule.required);
	setValidator($("#penaltyRate"), "pattern", penaltyRateRule.pattern, penaltyRateRule.message);
	setValidator($("#earlyRepaymentChargeRatio"), "required", true, earlyRepaymentChargeRatioRule.required);
	setValidator($("#earlyRepaymentChargeRatio"), "pattern", earlyRepaymentChargeRatioRule.pattern, earlyRepaymentChargeRatioRule.message);
	setValidator($("#accountAccumulationTime"), "required", true, accountAccumulationTimeRule.required);
	setValidator($("#accountAccumulationTime"), "pattern", accountAccumulationTimeRule.pattern, accountAccumulationTimeRule.message);
	var validator = $("#defaultRuleAdd-form").kendoValidator().data("kendoValidator");
	
	$("body").on("click", "#addDefaultRule", function() {
		if(!validator.validate()) {
			return;
		};
		$.post(
				basepath + "/guide/addRule",
				{
					id: $.trim($("#ruleId").text()),
					factorTransferInAccountNo: $.trim($("#factorTransferInAccountNo").val()),
					deductionTime: $.trim($("#deductionTime").val()),
					deductionRule: $.trim($("#deductionRule .active").data("deductionrule")),
					extensionDays: $.trim($("#extensionDays").val()),
					extensionRatio: $.trim($("#extensionRatio").val()),
					penaltyRate: $.trim($("#penaltyRate").val()),
					earlyRepaymentChargeRatio: $.trim($("#earlyRepaymentChargeRatio").val()),
					accountAccumulationTime: $.trim($("#accountAccumulationTime").val())
				},
				function(data) {
					if(data.status == "SUCCESS"){
						showSuccessWin(data.message);
		    			setTimeout("$('.btn').click()", 500)
						location.reload();
					}else{
						showAlertWin(data.message);
					}
				}
			);
	});
	
});