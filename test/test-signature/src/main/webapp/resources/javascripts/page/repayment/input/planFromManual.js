var pageSize = 10;
var isChargeStandardOthers = false;
function createPlanValidator() {
	setValidator($("span[name='loanNoForAdd']"), "required", true, loanNoRule.required);
	setValidator($("span[name='businessContractNoForAdd']"), "required", true, businessContractNoRule.required);
	setValidator($("input[name='financeBalanceForAdd']"), "required", true, financeBalanceRule.required);
	setValidator($("input[name='valueDateForAdd']"), "required",true, valueDateRule.required);
	setValidator($("input[name='dueDateForAdd']"), "required",true, dueDateRule.required);
	setValidator($("input[name='settleInterestDateForAdd']"), "required",true, settleInterestDateRule.required);
	setValidator($("input[name='repaymentDateForAdd']"), "required",true, repaymentDateRule.required);
	setValidator($("input[name='accountRepaymentPrincipalForAdd']"), "required",true, accountRepaymentPrincipalRule.required);
	setValidator($("input[name='accountRepaymentInterestForAdd']"), "required",true, accountRepaymentInterestRule.required);
	setValidator($("input[name='accountRepaymentServiceChargeForAdd']"), "required",true, accountRepaymentServiceChargeRule.required);

	var validatorForCreatePlan = $("#planAdd").kendoValidator({
		rules: {
			financeBalanceRule: financeBalanceRule.balanceRule,
			accountRepaymentPrincipalRule: accountRepaymentPrincipalRule.accountRepaymentPrincipalRule,
			accountRepaymentInterestRule: accountRepaymentInterestRule.accountRepaymentInterestRule,
			accountRepaymentServiceChargeRule: accountRepaymentServiceChargeRule.accountRepaymentServiceChargeRule
		},
		messages: {
			financeBalanceRule: financeBalanceRule.balanceRuleMessage,
			accountRepaymentPrincipalRule: accountRepaymentPrincipalRule.accountRepaymentPrincipalRuleMessage,
			accountRepaymentInterestRule: accountRepaymentInterestRule.accountRepaymentInterestRuleMessage,
			accountRepaymentServiceChargeRule: accountRepaymentServiceChargeRule.accountRepaymentServiceChargeRuleMessage
		},
		needRuleAttrbute : false
	}).data("kendoValidator");
	return validatorForCreatePlan;
};

function createCostValidator() {
	
	setValidator($("#costName"), "required", true, costNameRule.required);
	setValidator($("#chargeVal"), "required", true, chargeValRule.required);
	setValidator($("input[name='repaymentDate']"), "required", true, repaymentDateRule.required);

	var validator = $("#costAdd").kendoValidator({
		rules: {
			chargeRatioRule: chargeValRule.ratioRule,
			chargeAmountRule: chargeValRule.amountRule,
			repaymentDateRule: repaymentDateRule.notRightRule,
			othersChargeStandardRuleNotNull: othersChargeStandardRule.ruleNotNull,
			othersChargeStandardruleNotRight: othersChargeStandardRule.ruleNotRight
		},
		messages: {
			chargeRatioRule: chargeValRule.ratioRuleMessage,
			chargeAmountRule: chargeValRule.amountRuleMessage,
			repaymentDateRule: repaymentDateRule.notRightRuleMessage,
			othersChargeStandardRuleNotNull: othersChargeStandardRule.ruleNotNullMessage,
			othersChargeStandardruleNotRight: othersChargeStandardRule.ruleNotRightMessage
		},
		needRuleAttrbute : false
	}).data("kendoValidator");
	
	return validator;
}

function calculatePlanCost() {
	var standard;
	var ratio;
	var count;
	var totalcost;
	var singleCost;
	if($("#chargeMethod .active").data("chargemethod") == "比例"){
		if($("#chargeStandard .active").data("chargestandard") == "融资金额"){
			standard = parseFloat($("#financeAmountForShow").text().parseMoney());
			$("#chargeStandardRight").text(standard.toFixed(2).formatMoney());
		}else if($("#chargeStandard .active").data("chargestandard") == "融资余额") {
			standard = parseFloat($("#financeBalance").val().parseMoney());
			$("#chargeStandardRight").text(standard.toFixed(2).formatMoney());
		}else{
			standard = parseFloat($("#othersChargeStandard").val() == "" ? 0 : $("#othersChargeStandard").val().parseMoney());
			$("#chargeStandardRight").text(standard.toFixed(2).formatMoney());
		}
		ratio = parseFloat($("#chargeVal").val() == "" ? 0 : $("#chargeVal").val().parseMoney())/100;
		totalcost = (standard * ratio).toFixed(2);
	}else{
		totalcost = parseFloat($("#chargeVal").val() == "" ? 0 : $("#chargeVal").val().parseMoney()).toFixed(2);
	}
	$("#totalCostRight").text(totalcost.formatMoney());
	if($("#count .active").data("count") == "1"){
		count = 1;
	}else{
		count = $("#chargeCnt").val();
	}
	$("#countRight").text(count);
	singleCost = (totalcost / count).toFixed(2);
	$("#singleCostRight").text(singleCost.formatMoney());
}

function validatePlanCost() {
	
	var costValidator = createCostValidator();
	
	$("body").on("click", "#chargeMethod .radio", function() {
		if($("#chargeVal").val() != "")
			$("#chargeVal").blur();
		if($("#chargeMethod .active").data("chargemethod") == "比例"){
			$("#chargeStandardTr").removeClass("hidden");
			$("#chargeStandardRightTr").removeClass("hidden");
			$("#chargeValUnit").removeClass("hidden");
		}else{
			$("#chargeStandardTr").addClass("hidden");
			$("#chargeStandardRightTr").addClass("hidden");
			$("#chargeValUnit").addClass("hidden");
		}
	})
	
	$("body").on("change", "#costName", function() {
		$("#costNameRight").text($("#costName").val());
	});
	
	$("body").on("click", "#chargeStandard .radio", function() {
		if("其他" != $(this).data("chargestandard")){
			$("#othersChargeStandard").blur();
			$("#othersChargeStandard").val("");
		}else{
			isChargeStandardOthers = true;
		}
		calculatePlanCost();
	});
	
	$("body").on("blur", "#othersChargeStandard", function() {
		calculatePlanCost();
	});
	
	$("body").on("blur", "#chargeVal", function(){
		calculatePlanCost();
	});
	
	$("body").on("click", "#settleStatus .radio", function(){
		$("input[name='repaymentDate']").blur();
	});
	
	$("body").on("click", "#count .radio", function(){
		if($("#count .active").data("count") == 1){
			$("#chargeCnt").val("1");
		}
		$("input[name='repaymentDate']").change();
		calculatePlanCost();
	});
	
	$("body").on("change", "#chargeCnt", function() {
		var isFocus = $("#chargeCnt").is(":focus");
		if(isFocus){
			$("#chargeCnt").click();
		}
		$("input[name='repaymentDate']").change();
		calculatePlanCost();
	});
	
	$("body").on("change", "input[name='repaymentDate']", function(){
		var repaymentDate = "";
		var i = 0;
		$("input[name='repaymentDate']").each(function() {
			if(i == 0){
				repaymentDate += $(this).val();
				i++;
			}else{
				repaymentDate += "," + $(this).val();
				i++;
			}
		});
		$("#repaymentDateRight").text(repaymentDate);
	});
	
	$("body").on("click", "#comfirmAddExpense", function() {
		$("span[name='errorPromptForExpense']").css("visibility","hidden");
		if(!costValidator.validate()) {
			return;
		};
		$.ajax({
	        type: "POST",
	        url: basepath+"/repayment/plan/input/createExpenseToRedis?loanInfoId=" + $("#loanInfoId").val() + "&redisKey=" + $("#redisKey").val(),
	        data: {
	        	loanNo: $("#loanNo").val(),
				repaymentInfoId: $("#id").val(),
				financeCompany: $("#financeCompanyForShow").text(),
				expenseSubject: $("#costNameRight").text(),
				repaymentAmount: $("#singleCostRight").text().parseMoney(),
				repaymentDate: $("#repaymentDateRight").text(),
				settleStatus: $("#settleStatus .active").data("settlestatus")
			},
	        dataType: "json",
	        success: function(data){
	        	if(data.status=='SUCCESS'){
	        		$(".window-overlay").addClass("hidden");
	        		$("#template-manualCost").addClass("hidden");
	        		removeVal();
	        		checkPassReplaymentExpense();
	        	}else{
	        		$("span[name='errorPromptForExpense']").text(data.message);
	        		$("span[name='errorPromptForExpense']").css("visibility","visible");
	        	}
	        },
	    });
	});
	
	$("body").on("click", "#comfirmEditExpense", function() {
//		if(!costValidator.validate()) {
//			return;
//		};
		$.ajax({
	        type: "POST",
	        url: basepath+"/repayment/plan/input/updateSingleExpenseFromRedis?redisKey=" + $("#redisKey").val(),
	        data: {
	        	id: $(this).data("id"),
				loanNo: $("#loanNo").val(),
				repaymentInfoId: $("#id").val(),
				financeCompany: $("#financeCompanyForShow").text(),
				expenseSubject: $("#costNameForEdit").val(),
				repaymentAmount: $("#repaymentAmountForEdit").val().parseMoney(),
				repaymentDate: $("#repaymentDateForEdit").val(),
				settleStatus: $("#settleStatusForEdit .active").data("settlestatus")
			},
	        dataType: "json",
	        success: function(data){
	        	if(data.status=='SUCCESS'){
	        		$(".window-overlay").addClass("hidden");
	        		$("#template-editManualCost").addClass("hidden");
	        		removeVal();
	        		checkPassReplaymentExpense();
	        	}else{
	        		$("span[name='errorPromptForExpenseEdit']").text(data.message);
	        		$("span[name='errorPromptForExpenseEdit']").css("visibility","visible");
	        	}
	        },
	    });
	});
	
}

$(function(){
    $(".main-header a,.redirect").click(function(e){
    	e.preventDefault();
    	var url = $(this).attr("href");
    	$.ajax({
			type: "POST",
			url: basepath + "/repayment/plan/input/getNumByRedisKey",
			data: {
				loanInfoId: $("#loanInfoId").val(),
				redisKey : $.trim($("input[name='redisKey']").val())
				},
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
					if( Number(data.data.totalCorrectNumForPlan) != 0 || Number(data.data.totalCorrectNumForExpense) != 0){
						$(".template-leave").click();
						$("body").on("click", "#comfirmLeave",function(){
							window.location.href = url;
						});
					}else{
						window.location.href = url;
					}
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
	
	validatePlanCost();
	
	$(".repayment-navlink").click(function(event){
	var obj = $(event.target).parent("li");
	if(!obj.hasClass("repayment-nav-active")){
		obj.addClass("repayment-nav-active").siblings().removeClass("repayment-nav-active");
	}

	if(obj.is(".repayment-nav ul li:first")){
		checkPassRepaymentPlan();
		$("#repayment-plan").removeClass("hidden");
		$("#repayment-cost").addClass("hidden");
	}else if(obj.is(".repayment-nav ul li:last")){
		checkPassReplaymentExpense();
		$("#repayment-plan").addClass("hidden");
		$("#repayment-cost").removeClass("hidden");
		}
		//检查提交按钮位置
	    chkfix();
	});
	
    //确认删除提示弹窗
    new PopWindow("#manualGrid-plan .delManual", {
        title: "确认删除",
        width: 420,
        reload: true,
        template: "#template-del"
    }).init();
    
    
    //确认删除提示弹窗
    new PopWindow("#manualGrid-cost .delManual", {
        title: "确认删除",
        width: 420,
        reload: true,
        template: "#template-del-expense"
    }).init();
    
    //关闭弹窗
    $("body").on("click", ".dlg-wrapper .back-link", function(e){
        var win = $(e.target).closest(".k-window");
        var overlay = win.prev(".k-overlay");
        if(win.is(":visible")){
            win.css("display", "none");
            overlay.css("display", "none");
        }
    });
    
    $(".checkboxWrap .radio").click(function(){
        $(this).addClass("active");
        $(this).siblings().removeClass("active");
        var num = $("#manualTbody").children(".date-row").length;
        if($(this).is(".checkboxWrap .radio:first") && (num > 1)){
            $("#manualTbody .date-row:first").nextAll().remove();
        }
        if($(this).is(".checkboxWrap .radio:last")){
            var cnt = $("#chargeCnt").val();
            numChange(cnt);
        }
    });

    $("#chargeCnt").change(function(){
        var cnt = $("#chargeCnt").val();
        numChange(cnt);
    });

    function numChange(num){
        var addRow = '<tr class="date-row">' +
                '<td class="manual-title">还款日</td>' +
                '<td>' +
                '<input name="repaymentDate" type="text" value=' + new Date().format("yyyy-MM-dd") + ' class="datepicker default" readonly>' +
                '</td>' +
                '</tr>';
        $("#manualTbody").find(".date-row").remove();
        for(var i=1; i<=num; i++ ){
            $("#manualTbody").append(addRow);
            $("#manualTbody .datepicker").datepicker();
        }
    }
	// 还款信息校验
	
	//初始化表格
	checkPassRepaymentPlan();
	
     //新增还款计划
	$(".addRepayPlan").click(function(){
		$("span[name='errorPromptForPlan']").css("visibility","hidden");
    	$(".save-button").removeClass("hidden");
    	$(".edit-button").addClass("hidden");
		removeVal();
		$("span[name='accountRepaymentTotalForAdd']").text(repaymentTotalSum());
 		$(".window-overlay").removeClass("hidden");
 		$("#template-manualPlan").removeClass("hidden");
 		$.ajax({
	        type: "POST",
	        url: basepath+"/repayment/plan/input/calculateRepaymentPeriod",
	        data: {redisKey:$.trim($("input[name='redisKey']").val()),id: $.trim($("input[name='id']").val())},
	        dataType: "json",
	        success: function(data){
	        	if(data.status=='SUCCESS'){
	        		$("span[name='repaymentPeriodForAdd']").text(data.data);
	        	}else{
	        		$(".window-overlay").addClass("hidden");
	        		$("#template-manualPlan").addClass("hidden");
	        		showAlertWin(data.message);
	        	}
	        },
	    });
	});
	
	//保存还款计划
	repaymentPlanSave();
	
     //修改还款计划
	$("body").on("click", "#manualGrid-plan .editManual", function(){
		removeVal();
		$("span[name='errorPromptForPlan']").css("visibility","hidden");
    	$(".save-button").addClass("hidden");
    	$(".edit-button").removeClass("hidden");
		var id = $(this).parent().next().text();
		$(".window-overlay").removeClass("hidden");
		$("#template-manualPlan").removeClass("hidden");
		//获取还款计划信息
		$.ajax({
	        type: "POST",
	        url: basepath+"/repayment/plan/input/getSinglePlanFromRedis",
	        data: {redisKey:$.trim($("input[name='redisKey']").val()),id: id, loanInfoId: $("#loanInfoId").val()},
	        dataType: "json",
	        success: function(data){
	        	if(data.status=='SUCCESS'){
	        		$("input[name='financeBalanceForAdd']").val(data.data.financeBalance);
	        		$("span[name='repaymentPeriodForAdd']").text(data.data.repaymentPeriod);
	        		$("input[name='valueDateForAdd']").val(data.data.valueDate);
	        		$("input[name='financeBalanceForAdd']").val(data.data.financeBalance);
	        		$("input[name='settleInterestDateForAdd']").val(data.data.settleInterestDate);
	        		$("input[name='repaymentDateForAdd']").val(data.data.repaymentDate);
	        		$("input[name='accountRepaymentPrincipalForAdd']").val(data.data.accountRepaymentPrincipal);
	        		$("input[name='accountRepaymentInterestForAdd']").val(data.data.accountRepaymentInterest);
	        		$("input[name='accountRepaymentServiceChargeForAdd']").val(data.data.accountRepaymentServiceCharge);
	        		$("span[name='accountRepaymentTotalForAdd']").text(repaymentTotalSum());
	        		$("div[name='settleStatusForAdd']").find("label[class='radio active']").removeClass("active");
	        		if(data.data.settleStatus=="已结清"){
	        			$("#settled").addClass("active");
	        		}else{
	        			$("#unSettle").addClass("active");
	        		}
	        		$("#confirmForAdd").attr("singleid", id);
	        		repaymentPlanSave();
	        	}else{
	        		$(".window-overlay").addClass("hidden");
	        		$("#template-manualPlan").addClass("hidden");
	        		showAlertWin(data.message);
	        	}
	        },
	    });
	});
	
	//修改还款计划保存
	repaymentPlanEdit();
	
     //新增还款费用
	$(".addRepayCost").click(function(){
		$("span[name='errorPromptForExpense']").css("visibility","hidden");
		removeVal();
		$(".window-overlay").removeClass("hidden");
		$("#template-manualCost").removeClass("hidden");
	});

     //关闭还款计划弹窗
	$(".planCloseBar, #template-manualPlan .back-link").click(function(){
		$(".window-overlay").addClass("hidden");
		$("#template-manualPlan").addClass("hidden");
     });

     //关闭还款费用弹窗
	$(".costCloseBar, #template-manualCost .back-link").click(function(){
		$(".window-overlay").addClass("hidden");
		$("#template-manualCost").addClass("hidden");
		removeVal();
	});
	
    //关闭编辑还款费用弹窗
	$(".editCloseBar, #template-editManualCost .back-link").click(function(){
		$(".window-overlay").addClass("hidden");
		$("#template-editManualCost").addClass("hidden");
		removeVal();
	});

     //删除还款计划行
	$("body").on("click", "#manualGrid-plan .delManual", function(e){
		$("#comfirmDel").attr("single",$.trim($(this).parent().next().text()));
	});
	
	//确认删除还款计划
	$("body").delegate("#comfirmDel", "click", function(e) {
		$.ajax({
	        type: "POST",
	        url: basepath+"/repayment/plan/input/deleteSinglePlanFromRedis",
	        data: {
	        	redisKey:$.trim($("input[name='redisKey']").val()),
	        	id:$.trim($("#comfirmDel").attr("single")),
	        	loanInfoId:$("#loanInfoId").val()
	        	},
	        dataType: "json",
	        success: function(data){
	        	if(data.status=='SUCCESS'){
	        		checkPassRepaymentPlan()
	        		var win = $(e.target).closest(".k-window");
	                var overlay = win.prev(".k-overlay");
	                if(win.is(":visible")){
	                    win.css("display", "none");
	                    overlay.css("display", "none");
	                }
	        	}else{
	        		var win = $(e.target).closest(".k-window");
	                var overlay = win.prev(".k-overlay");
	                if(win.is(":visible")){
	                    win.css("display", "none");
	                    overlay.css("display", "none");
	                }
	                showAlertWin(data.message);
	        	}
	        }
	    });
	});
	
    // 修改费用列表
	$("body").on("click", "#manualGrid-cost .editManual", function(){
		removeVal();
		$("span[name='errorPromptForExpenseEdit']").css("visibility","hidden");
		$("#comfirmEditExpense").data("id", $(this).data("id"));

		$.ajax({
			type: "POST",
			url: basepath+"/repayment/plan/input/getSingleExpenseFromRedis",
			data: 
			{
				redisKey:$.trim($("input[name='redisKey']").val()),
				id: $(this).data("id"),
				loanInfoId: $("#loanInfoId").val()
			},
			dataType: "json",
			success: function(data){
				if(data.status=='SUCCESS'){
					$("#costNameForEdit").val(data.data.expenseSubject);
					$("#repaymentDateForEdit").val(data.data.repaymentDate);
					$("#repaymentAmountForEdit").val(data.data.repaymentAmount.formatMoney());
	        		if(data.data.settleStatus=="未结清"){
	        			$("#settleStatusForEdit .radio").first().click();
	        		}else{
	        			$("#settleStatusForEdit .radio").last().click();
	        		}
					$(".window-overlay").removeClass("hidden");
					$("#template-editManualCost").removeClass("hidden");
				}else{
					$(".window-overlay").addClass("hidden");
	        		$("#template-manualPlan").addClass("hidden");
	        		showAlertWin(data.message);
	        	}
	        },
	    });
	});
	
     //删除还款费用行
	$("body").on("click", "#manualGrid-cost .delManual", function(e){
		$("#comfirmDelExpense").attr("single",$.trim($(this).attr("data-id")));
	});
     
	//确认删除还款费用
	$("body").delegate("#comfirmDelExpense", "click", function(e) {
		$.ajax({
	        type: "POST",
	        url: basepath+"/repayment/plan/input/deleteSingleExpenseFromRedis",
	        data: {
	        	loanInfoId:$("#loanInfoId").val(),
	        	redisKey:$.trim($("input[name='redisKey']").val()),
	        	id:$.trim($("#comfirmDelExpense").attr("single"))
	        	},
	        dataType: "json",
	        success: function(data){
	        	if(data.status=='SUCCESS'){
	        		checkPassRepaymentPlan()
	        		 var win = $(e.target).closest(".k-window");
	                var overlay = win.prev(".k-overlay");
	                if(win.is(":visible")){
	                    win.css("display", "none");
	                    overlay.css("display", "none");
	                    checkPassReplaymentExpense();
	                }
	        	}else{
	        		var win = $(e.target).closest(".k-window");
                    var overlay = win.prev(".k-overlay");
                    if(win.is(":visible")){
                        win.css("display", "none");
                        overlay.css("display", "none");
                    }
                    showAlertWin(data.message);
	        	}
	        }
	    });
	});
	
	// 点击确定保存数据
	$("body").delegate("#savePlanAndExpenseFromManual", "click", function(){
		$.ajax({
			type: "POST",
			url: basepath + "/repayment/plan/input/getNumByRedisKey",
			data: {
				loanInfoId: $("#loanInfoId").val(),
				redisKey : $.trim($("input[name='redisKey']").val())
				},
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
					if( Number(data.data.totalCorrectNumForPlan) == 0 && Number(data.data.totalCorrectNumForExpense) == 0){
						showAlertWin("未添加任何数据，保存失败");
					}else{
						$("#saveInfo").submit();
					}
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

//保存还款计划
function repaymentPlanSave(){
	$("body").delegate("input[name='accountRepaymentPrincipalForAdd']", "blur", function() {
		$("span[name='accountRepaymentTotalForAdd']").text(repaymentTotalSum());
    });
	$("body").delegate("input[name='accountRepaymentInterestForAdd']", "blur", function() {
		$("span[name='accountRepaymentTotalForAdd']").text(repaymentTotalSum());
	});
	$("body").delegate("input[name='accountRepaymentServiceChargeForAdd']", "blur", function() {
		$("span[name='accountRepaymentTotalForAdd']").text(repaymentTotalSum());
	});
	
	var planValidator = createPlanValidator();

	$("body").on("click", "#confirmForAdd" , function() {
		$("span[name='errorPromptForPlan']").css("visibility","hidden");
		if(!planValidator.validate()) {
			return;
		};
		$.ajax({
	        type: "POST",
	        url: basepath+"/repayment/plan/input/createPlanToRedis",
	        data: {redisKey:$.trim($("input[name='redisKey']").val()),loanNo:$.trim($("span[name='loanNoForAdd']").text()),
	        		financeBalance:$.trim($("input[name='financeBalanceForAdd']").val().parseMoney()),valueDate:$.trim($("input[name='valueDateForAdd']").val()),dueDate:$.trim($("span[name='dueDateForAdd']").text()),
	        		settleInterestDate:$.trim($("input[name='settleInterestDateForAdd']").val()),financeAmount:$.trim($("span[name='financeAmountForShow']").text().parseMoney()),loanDate:$.trim($("span[name='loanDateForShow']").text()),
	        		repaymentDate:$.trim($("input[name='repaymentDateForAdd']").val()),accountRepaymentPrincipal:$.trim($("input[name='accountRepaymentPrincipalForAdd']").val().parseMoney()),
	        		accountRepaymentInterest:$.trim($("input[name='accountRepaymentInterestForAdd']").val().parseMoney()),accountRepaymentServiceCharge:$.trim($("input[name='accountRepaymentServiceChargeForAdd']").val().parseMoney()),
	        		accountRepaymentTotal:$.trim($("span[name='accountRepaymentTotalForAdd']").text().parseMoney()),settleStatus:$.trim($("div[name='settleStatusForAdd']").find("label[class='radio active']").find("span[class='labelFonts']").text()),
	        		financeCompany:$.trim($("span[name='financeCompanyForShow']").text().parseMoney()),repaymentPeriod:$.trim($("span[name='repaymentPeriodForAdd']").text()),id:$.trim($("#id").val())},
	        dataType: "json",
	        success: function(data){
	        	if(data.status=='SUCCESS'){
	        		$(".window-overlay").addClass("hidden");
	        		$("#template-manualPlan").addClass("hidden");
	        		checkPassRepaymentPlan()
	        	}else{
	        		$("span[name='errorPromptForPlan']").text(data.message);
	        		$("span[name='errorPromptForPlan']").css("visibility","visible");
	        	}
	        }
	    });
	});
}

// 修改还款计划保存
function repaymentPlanEdit(){
	var validator = $("#planAdd").kendoValidator().data("kendoValidator");
	$("body").on("click", "#confirmForEdit" , function() {
		if(!validator.validate()) {
			return;
		};
		$.ajax({
	        type: "POST",
	        url: basepath+"/repayment/plan/input/updateSinglePlanFromRedis",
	        data: {redisKey:$.trim($("input[name='redisKey']").val()),loanNo:$.trim($("span[name='loanNoForAdd']").text()),
	        		financeBalance:$.trim($("input[name='financeBalanceForAdd']").val().parseMoney()),valueDate:$.trim($("input[name='valueDateForAdd']").val()),dueDate:$.trim($("span[name='dueDateForAdd']").text()),
	        		settleInterestDate:$.trim($("input[name='settleInterestDateForAdd']").val()),financeAmount:$.trim($("span[name='financeAmountForShow']").text().parseMoney()),loanDate:$.trim($("span[name='loanDateForShow']").text()),
	        		repaymentDate:$.trim($("input[name='repaymentDateForAdd']").val()),accountRepaymentPrincipal:$.trim($("input[name='accountRepaymentPrincipalForAdd']").val().parseMoney()),
	        		accountRepaymentInterest:$.trim($("input[name='accountRepaymentInterestForAdd']").val().parseMoney()),accountRepaymentServiceCharge:$.trim($("input[name='accountRepaymentServiceChargeForAdd']").val().parseMoney()),
	        		accountRepaymentTotal:$.trim($("span[name='accountRepaymentTotalForAdd']").text().parseMoney()),settleStatus:$.trim($("div[name='settleStatusForAdd']").find("label[class='radio active']").find("span[class='labelFonts']").text()),
	        		financeCompany:$.trim($("span[name='financeCompanyForShow']").text()),repaymentPeriod:$.trim($("span[name='repaymentPeriodForAdd']").text()),id:$.trim($("#confirmForAdd").attr("singleid")),
	        		loanInfoId:$.trim($.trim($("#id").val()))},
	        dataType: "json",
	        success: function(data){
	        	if(data.status=='SUCCESS'){
	        		$(".window-overlay").addClass("hidden");
	        		$("#template-manualPlan").addClass("hidden");
	        		checkPassRepaymentPlan()
	        	}else{
	        		$("span[name='errorPromptForPlan']").text(data.message);
	        		$("span[name='errorPromptForPlan']").css("visibility","visible");
	        	}
	        }
	    });
	});
}

//计算应还总金额
function repaymentTotalSum(){
	var accountRepaymentPrincipalForAdd = $.trim($("input[name='accountRepaymentPrincipalForAdd']").val().parseMoney());
	var accountRepaymentInterestForAdd = $.trim($("input[name='accountRepaymentInterestForAdd']").val().parseMoney());
	var accountRepaymentServiceChargeForAdd = $.trim($("input[name='accountRepaymentServiceChargeForAdd']").val().parseMoney());
	if(accountRepaymentPrincipalForAdd==""){
		accountRepaymentPrincipalForAdd = 0.00
	}
	if(accountRepaymentInterestForAdd==""){
		accountRepaymentInterestForAdd = 0.00
	}
	if(accountRepaymentServiceChargeForAdd==""){
		accountRepaymentServiceChargeForAdd = 0.00
	}
	return Number(accountRepaymentPrincipalForAdd).add(accountRepaymentInterestForAdd).add(accountRepaymentServiceChargeForAdd).toFixed(2).formatMoney();
}

Number.prototype.add = function(arg){   
    var r1,r2,m;   
    try{r1=this.toString().split(".")[1].length}catch(e){r1=0}   
    try{r2=arg.toString().split(".")[1].length}catch(e){r2=0}   
    m=Math.pow(100,Math.max(r1,r2))   
    return (this*m+arg*m)/m   
}   

function checkPassRepaymentPlan(){
	$("#manualGrid-plan").remove();
	$("#manualGrid-planWrap").append("<div class='finance-grid' id='manualGrid-plan' style='margin-top:15px;'></div>");
	//通过还款信息
    $("#manualGrid-plan").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                    data: { redisKey : $.trim($("input[name='redisKey']").val()),
							dataType : 1,
							id:$.trim($("input[name='id']").val())
					},
                    type : "POST",
                    url: basepath + "/repayment/plan/input/getPlanByRedisKey"
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
            template: function(data){
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
            field: "accountRepaymentPrincipal",
            title: "应还本金",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.accountRepaymentPrincipal + "";
            	return money.formatMoney();	
            }
        },{
            field: "accountRepaymentInterest",
            title: "应还收益",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.accountRepaymentInterest + "";
            	return money.formatMoney();	
            }
        },{
            field: "accountRepaymentServiceCharge",
            title: "应还服务费",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.accountRepaymentServiceCharge + "";
            	return money.formatMoney();	
            }
        },{
            field: "accountRepaymentTotal",
            title: "应还总金额",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data){
            	var money = data.accountRepaymentTotal + "";
            	return money.formatMoney();	
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
            	if(data.editAble=="EDITUNADLE"){
            		return "";
            	}else{
            		return "<a class='comRow-link editManual' href='javascript:void(0);'>修改</a>"+"<a class='comRow-link delManual' href='javascript:void(0);'>删除</a>"; 
            	}
            }
        },{
            field: "id",
            title: "主键",
            width: 120,
            hidden: true
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


function checkPassReplaymentExpense(){
	$("#manualGrid-cost").remove();
	$("#manualGrid-costWrap").append("<div class='finance-grid' id='manualGrid-cost' style='margin-top:15px;'></div>");
	//通过还款信息
	$("#manualGrid-cost").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
            	 read: {
                     data: { redisKey : $.trim($("input[name='redisKey']").val()),
 							dataType : 1,
 							id:$.trim($("input[name='id']").val())
                     },
                     type : "POST",
                     url: basepath + "/repayment/plan/input/getExpenseByRedisKey"
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
            field: "expenseSubject",
            title: "费用明目",
            width: 120
//        },{
//            field: "chargeMethod",
//            title: "计费方式",
//            width: 120,
//            attributes: {
//                style: "text-align: center"
//            }
//        },{
//            field: "chargeStandard",
//            title: "计费基准",
//            width: 120,
//            template: function(data) {
//				if(data.chargeStandard == "融资金额")
//					return $("#financeAmountForShow").text();
//				if(data.chargeStandard == "融资余额")
//					return $("#financeBalance").val();
//				else return data.chargeStandard == "" ? "" : data.chargeStandard.formatMoney();
//			},
//            attributes: {
//                style: "text-align: right"
//            }
//        },{
//            field: "chargeVal",
//            title: "计费值",
//            width: 120,
//            template: function(data) {
//            	var chargeVal = data.chargeRatio == "" ? data.chargeAmount : data.chargeRatio;
//            	if(data.chargeMethod == "比例"){
//            		return chargeVal.formatMoney() + "%";
//            	}else{
//            		return chargeVal == "" ? "" : chargeVal.formatMoney();
//            	}
//			},
//            attributes: {
//                style: "text-align: right"
//            }
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
            	return money.formatMoney();	
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
            width: 150,
            template: function(data) {
            	var url = "";
            	if(data.editAble=="EDITUNADLE"){
            		return url;
            	}
            	url += "<a class='comRow-link editManual' data-id=" + data.id + " href='javascript:void(0);'>修改</a>";
            	url += "<a class='comRow-link delManual' data-id=" + data.id + " href='javascript:void(0);'>删除</a>";
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
	$("input[name='financeBalanceForAdd']").val("");
	$("input[name='valueDateForAdd']").val("");
	$("input[name='settleInterestDateForAdd']").val("");
	$("input[name='repaymentDateForAdd']").val("");
	$("input[name='accountRepaymentPrincipalForAdd']").val("");
	$("input[name='accountRepaymentInterestForAdd']").val("");
	$("input[name='accountRepaymentServiceChargeForAdd']").val("");
	$("#costName").val("");
	$("#costNameRight").text("");
	$("#chargeStandardRight").text("");
	$("#othersChargeStandard").val("");
	$("#chargeVal").val("");
	$("#totalCostRight").text("");
	$("#chargeCount").removeClass("hidden");
	$("#chargeCountRight").removeClass("hidden");
	$("#countRight").text("");
	$("#singleCostRight").text("");
	$("#repaymentDateRight").text(new Date().format("yyyy-MM-dd"));
	$("#chargeMethod").children().removeClass("active");
	$("#chargeMethod").children().first().click();
	$("#chargeStandard").children().removeClass("active");
	$("#chargeStandard").children().first().click();
	$("#count").children().removeClass("active");
	$("#count").children().first().click();
	$("input[name='repaymentDate']").val(new Date().format("yyyy-MM-dd"));
	$("#chargeCnt").val("1");
	$("#settleStatus").children().removeClass("active");
	$("#settleStatus").children().first().click();
	$("#costNameForEdit").val("");
	$("#repaymentDateForEdit").val("");
	$("#repaymentAmountForEdit").val("");
	$("#settleStatusForEdit .radio").first().click();
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
