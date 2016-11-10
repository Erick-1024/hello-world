var ids = "";
var companyNames = "";
function addRule() {
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
	var validator = $("#ruleAdd-form").kendoValidator({
		rules : {
			deductionTimeRule : deductionTimeRule.rule,
			accountAccumulationTimeRule : accountAccumulationTimeRule.rule
		},
		messages : {
			deductionTimeRule : deductionTimeRule.ruleMessage,
			accountAccumulationTimeRule : accountAccumulationTimeRule.ruleMessage
		},
		needRuleAttrbute : false
	}).data("kendoValidator");
	
	$("body").on("click", "#addRule-button", function() {
		if(!validator.validate()) {
			return;
		};
		if(ids == "" || companyNames == ""){
			showAlertWin("请添加融资客户后再提交")
			return;
		}
		$.post(
			basepath + "/repayment/rule/addRule",
			{
				id: $.trim($("#ruleId").text()),
				factorTransferInAccountNo: $.trim($("#factorTransferInAccountNo").text().parseBankAccountNo()),
				fianceCustomerIds: $.trim(ids),
				fianceCustomerCompanys: $.trim(companyNames),
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
					location.href = basepath + "/repayment/rule/showDetail?ruleId=" + $.trim($("#ruleId").text());
				}else{
					showAlertWin(data.message)
				}
			}
		);
	});
}

function bindEvent() {
    //新建行
    $(".addCustom-link").click(function(){
    	if(0 == supervisorSum){
    		showAlertWin("您没有融资客户可以添加还款规则");
    		return;
    	}
        $(".window-overlay").removeClass("hidden");
        $("#template-addCustom").removeClass("hidden");
    });
//    //修改行
//    var target;
//    $(".repayRule-item").on("click", ".addCustom-table .editCustom-link", function(e){
//        target = $(e.target).closest("td").prev();
//        var txt = target.html();
//        $("#originName").val(txt);
//        //打开弹窗
//        $(".window-overlay").removeClass("hidden");
//        $("#template-eidtCustom").removeClass("hidden");
//
//    });
//    $("#template-eidtCustom .confirm-link").click(function(){
//
//        var originName = $("#originName").val();
//        if(originName == ''){
//            return ;
//        }
//        target.html(originName);
//        //关闭弹窗
//        $(".window-overlay").addClass("hidden");
//        $("#template-eidtCustom").addClass("hidden");
//    });
//
    //关闭调整弹窗
    $(".autoCloseBar, #template-addCustom .back-link").click(function(){
        $(".window-overlay").addClass("hidden");
        $("#template-addCustom").addClass("hidden");
    });
//    //关闭调整弹窗
//    $(".autoCloseBar, #template-eidtCustom .back-link").click(function(){
//        $(".window-overlay").addClass("hidden");
//        $("#template-eidtCustom").addClass("hidden");
//    });
//
    //删除行
    $(".repayRule-item").on("click", ".addCustom-table .delCustom-link", function(){
    	supervisorSum ++;
    	var curId = $(this).data("id").toString();
    	var curCompanyName = $(this).data("companyname");
    	var cuttingIdStrStart = ids.indexOf(curId);
    	var cuttingIdStrEnd = ids.indexOf(curId) + curId.length;
    	var cuttingCompanyNameStrStart = companyNames.indexOf(curCompanyName);
    	var cuttingCompanyNameStrEnd = companyNames.indexOf(curCompanyName) + curCompanyName.length;
    	if(cuttingIdStrEnd != ids.length){
    		ids = ids.substr(0, cuttingIdStrStart) + ids.substr(cuttingIdStrEnd + 1, ids.length);
    		companyNames = companyNames.substr(0, cuttingCompanyNameStrStart) + companyNames.substr(cuttingCompanyNameStrEnd + 1, companyNames.length);
    	}else{
    		ids = ids.substr(0, cuttingIdStrStart - 1);
    		companyNames = companyNames.substr(0, cuttingCompanyNameStrStart - 1);
    	}
    	$(this).parent().parent().remove();
    	var sequence = 1;
    	$(".sequence").each(function() {
			$(this).text(sequence ++);
		});
    });
    

    $("#template-addCustom .confirm-link").click(function(){
        var lastNum = parseInt($("#addTbody tr:last").children("td:first").html());
        if(isNaN(lastNum)){
            lastNum = 1;
        }else{
            lastNum += 1;
        }
        var id = $("#supervisor").val();
        var companyName = $("#supervisor option:selected").text();
        if(ids.indexOf(id) != -1){
        	$(".window-overlay").addClass("hidden");
            $("#template-addCustom").addClass("hidden");
            showAlertWin("融资客户不能重复添加");
            return ;
        }
        supervisorSum --;
        var addTr = '<tr>' +
                '<td class="sequence">'+lastNum+'</td>' +
                '<td>'+companyName+'</td>' +
                '<td>' +
//                '<a class="repayment-link editCustom-link" data-id=' + id + ' data-companyName=' + companyName + ' href="javascript:void(0);">修改</a>' +
                '<a class="repayment-link delCustom-link" data-id=' + id + ' data-companyName=' + companyName + '  href="javascript:void(0);">删除</a>' +
                '</td>' +
                '</tr>';

        $("#addTbody").append(addTr);
        if(ids == ""){
        	ids += id;
        	companyNames += companyName;
        }else{
        	ids +=  "," + id;
        	companyNames += "," + companyName;
        }
        //关闭弹窗
        $(".window-overlay").addClass("hidden");
        $("#template-addCustom").addClass("hidden");
    });
}


$(function() {
	bindEvent();
	addRule();
});