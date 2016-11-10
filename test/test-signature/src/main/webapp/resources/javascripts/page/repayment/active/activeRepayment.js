/**
 * Created by jiaxin.qian on 16-3-23.
 */
function refreshBalance () {
    $("body").on("click", "#refreshBalance", function () {
    			queryBalance($("#accountNo option:selected").text());
    });
};

function queryBalance(accountNo) {
    $.post(basepath + "/repayment/active/queryAccountBalance",
        {accountNo: accountNo.parseBankAccountNo()},
        function (data) {
            if (data.status == "SUCCESS") {
            	$("#balance").text(data.data.formatMoney());
            } else {
                showAlertWin(data.message);
            }
        });
};

function accountChange () {
    $("body").on("change", "#accountNo", function () {
        $("#accountType").text($(this).val());
        queryBalance($("#accountNo option:selected").text());
    });
};

function forgetPsw () {
    new PopWindow("#forgetPsw", {
        title: "忘记支付密码",
        width: 460,
        reload: true,
        resizable: false,
        content: {
            template: '<div class="dlg-notice-row" style="text-align:center">' +
            '<span class="notice-content">请与CANA客服人员联系，联系电话021-83866655-8008</span>' +
            '</div>'
        }
    }).init();
};

function confirmRepayment () {
    $("body").on("click", ".confirm-link", function () {
					if(!validator.validate()) {
						return;
					};
    	    activeRepayment();
	});
	
}

function activeRepayment () {
    $.post(basepath + "/repayment/active/activeRepayment?loanInfoId=" + $(".confirm-link").data("loaninfoid"),
        {activeRepaymentType:    $(".confirm-link").data("activerepaymenttype"),
    	    accountNo           :    $("#accountNo option:selected").text().parseBankAccountNo(),
    	    amount              :    $("#amount").val().parseMoney(),
    	    payPsw              :    $("#password").val()},
    	    function (data) {
				if (data.status == "SUCCESS") {
					location.href = basepath + "/repayment/active/showActiveRepaymentSuccessPage?loanInfoId=" + $(".confirm-link").data("loaninfoid");
				} else {
					showAlertWin(data.message);
				}
			})
};

function newValidator() {
	setValidator($("#amount"), "required", true, amountRule.required);
	setValidator($("#password"), "required", true, payPswRule.required);

	validator = $("#activeRepayment-form").kendoValidator({
		rules: {
			amountRule: amountRule.maxRule,
			balanceRule: amountRule.balanceRule
		},
		messages: {
			amountRule: amountRule.maxRuleMessage,
			balanceRule: amountRule.balanceRuleMessage
		},
		needRuleAttrbute : false
	}).data("kendoValidator");
}

function init () {
    $("#accountNo").change();
};

$(function () {
		newValidator();
		 confirmRepayment();
    accountChange();
    refreshBalance();
    forgetPsw();
    init();
});