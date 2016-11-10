$(function () {
	$.post(basepath + "/account/trade/checkSetPayPassword", {}, function(data) {
		if (data.data.isSetPayPassword == "false") 
			showAlertWin("您尚未设置支付密码，请在“企业信息->账户信息”中设置！");
	});
	
	activeRepayment();
	confirmActiveRepayment();
	gotoListLink();
	accountChange();
	refreshBalance();
	forgetPsw();
	init();
	
    /*输入还款金额后校验并显示归还本金等信息*/
    $("body").on("blur","#amount",function(){
    	if(!verifyAmount(this)){
    		$(".re-tr-col").hide();
    		return;
    	}
        $(".re-tr-col").show();
        $(this).css("border-color","#dadada");
        $(this).next().hide();
        if($(".activeRepayment-link").attr("data-autoRepaymentPlans")=="true")
        	prepareRepayment();
    });
    
    /*输入支付密码后校验*/
    $("body").on("blur","#password",function(){
    	if(!verifyPassword(this)){
    		return;
    	}
        $(this).css("border-color","#dadada");
        $(this).next().hide();
    });
    
    /*结清贷款复选框点击事件*/
    $("body").on("click",".checkbox-btn-bg",function(){
    	if($(this).hasClass("active")){
    		//还款金额为最大还款金额
    		$("#amount").val($("#maxAmount").text());
    		//校验金额
    		$("#amount").blur();
    	}
    });
    
    /*还款结果弹窗调用*/
    $(".two").click(function(){
        $(".pop-out-x").hide();
        $(".pop-out-c").show();
    });
});

function init() {
	$("#accountNo").change();
};

//校验金额
function verifyAmount(obj){
	var inp = $(obj).val();
    if(inp==''){
    	showVerifyError(obj,"不能为空");
    	return　false;
    }
    var amount = parseFloat($(obj).val().parseMoney());
    if(isNaN(amount)){
    	showVerifyError(obj,"格式不正确");
    	return false;
    }
    if(amount <= 0){
    	showVerifyError(obj,"不能小于等于0");
    	return false;
    }
    if(isNaN(parseFloat($("#balance").text().parseMoney()))){
    	showVerifyError(obj,"账户余额获取失败");
    	return false;
    }
    if(amount > parseFloat($("#balance").text().parseMoney())){
    	showVerifyError(obj,"不能大于账户余额");
    	return false;
    }
    if(amount > parseFloat($("#maxAmount").text().parseMoney())){
    	showVerifyError(obj,"不能大于最大还款金额");
    	return false;
    }
    if(amount < parseFloat($("#minAmount").text().parseMoney())){
    	showVerifyError(obj,"不能小于最低还款金额");
    	return false;
    }
    return true;
}
//校验支付密码
function verifyPassword(obj){
	var inp = $(obj).val();
    if(inp==''){
    	showVerifyError(obj,"不能为空");
    	return　false;
    }
    return true;
}
//展示校验错误
function showVerifyError(obj,errorText){
	$(obj).css("border-color","#ff6600");
	$(obj).next().text(errorText);
    $(obj).next().show();
}


function refreshBalance () {
    $("body").on("click", "#refreshBalance", function () {
    	queryBalance($("#accountNo option:selected").text());
    });
};

function queryBalance(accountNo) {
    $.post(basepath + "/repayment/active/queryAccountBalance",
    {
    	accountNo: accountNo.parseBankAccountNo()
    },
    function (data) {
        if (data.status == "SUCCESS") {
        	$("#balance").text(data.data.formatMoney());
        } else {
        	$("#balance").text("获取失败");
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
            template: '<div class="dlg-notice-row style="text-align:center">' +
            '<span class="notice-content">请与CANA客服人员联系，联系电话021-53866655-8008</span>' +
            '</div>'
        }
    }).init();
};

/*点击主动还款*/
function activeRepayment(){
	 $("body").on("click", ".activeRepayment-link", function () {
		if(!verifyAmount($("#amount"))){
			$(".re-tr-col").hide();
			return;
		}
		if(!verifyPassword($("#password"))){
			return;
		}
        $(".pop-out-x").show();
    });
}

/*二次确认主动还款*/
function confirmActiveRepayment() {
    $("body").on("click", ".confirmActiveRepayment-link", function () {
    	$(".pop-out-x").hide();
    	activeRepaymentPost();
	});
}

/*还款成功后的确定按钮*/
function gotoListLink() {
    $("body").on("click", ".goto-list-link", function () {
    	$(".pop-out-c").hide();
    	location.href = basepath + "/repayment/active/showActiveRepaymentSuccessPage?loanInfoId=" + $(".activeRepayment-link").attr("data-loaninfoid") + "&autoRepaymentPlans=" + $(".activeRepayment-link").attr("data-autoRepaymentPlans");
	});
}


function activeRepaymentPost () {
    $.post(basepath + "/repayment/active/activeRepayment?loanInfoId=" + $(".activeRepayment-link").attr("data-loaninfoid"),
    {
		activeRepaymentType : $(".activeRepayment-link").attr("data-activerepaymenttype"),
	    accountNo : $("#accountNo option:selected").text().parseBankAccountNo(),
	    amount : $("#amount").val().parseMoney(),
	    payPsw : $("#password").val()
	    },
	    function (data) {
			if (data.status == "SUCCESS") {
				$(".pop-out-c").show();
			} else {
				showAlertWin(data.message);
			}
	})
};

function prepareRepayment() {
	var loanInfoId = $(".activeRepayment-link").attr("data-loaninfoId");
	var repaymentAmount = $("#amount").val().parseMoney();
    $.post(basepath + "/repayment/active/prepareRepayment",
    {
    	loanInfoId : loanInfoId,
    	repaymentAmount : repaymentAmount
    },
    function (data) {
	    if (data.status == "SUCCESS") {
	    	var info = data.data;
	    	$("#paid-principal").text(info.paidPrincipal.formatMoney());
	    	$("#paid-interest").text(info.paidInterest.formatMoney());
	    	$("#paid-overdueAmount").text(info.paidOverdueAmount.formatMoney());
	    	$("#paid-serviceCharge").text(info.paidServiceCharge.formatMoney());
	    }
	});
};
