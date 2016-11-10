
/**
 * 未提交的冲销记录
 */
var $paidPlans = [];
var $canSubmit = true;

$(function(){
    //初始化还款冲销弹窗
    new PopWindow(".open-costPop",{
        title: "还款冲销",
        width: 800,
        reload: true,
        template: "#template-resetPwd-cost"
    }).init();
    //初始化修改弹窗
    new PopWindow(".open-repaymentPop",{
        title: "修改还款",
        width: 800,
        reload: true,
        template: "#template-resetPwd-arr"
    }).init();
    //初始化消息弹窗
    new PopWindow(".open-message-btn",{
        title: "提示",
        width: 400,
        reload: true,
        template: "#tipBox_template"
    }).init();
    //判断冲销弹窗冲销项目是否选择
    $("body").on("click",".item-btn",function(){
        var $item = [];
        $("[name=item]:checked").each(function(){
            $item.push($(this).val());
        });
        if($item!=''){
            $(".check-item-btn").val("true");
        }else{
            $(".check-item-btn").val('');
        }
    });

    //打开还款冲销弹窗
    $("body").on("click",".add-repayment-btn",function(){
    	//取最早的一笔未结清的还款计划
    	var $unsettlePlanIndex = getFirstUnSettlePlan();
    	if ($unsettlePlanIndex < 0) {
    		alertMessage("没有未结清的还款计划需要还款冲销");
    		return;
    	}

    	var $unsettlePlan = getPlanByIndex($unsettlePlanIndex);
    	var $paid = getPaidByLoanPlanId($unsettlePlan.loanPlanId);
    	if ($paid) {
    		alertMessage("第一个未结清的还款计划有未提交的还款冲销记录");
    		return;
    	}
        $(".open-costPop").click();
        PopMoneyNumFormat();

        //表单验证
        var validator = $("#cost-form").kendoValidator({
    		rules: {
    			paidAmountRule: function(input) {
    				if(input.is("[name=in-money]")) {
    					var $paidAmount = input.val().parseMoney();
    					if (!/^([1-9][\d]{0,8}|0)(\.[\d]{1,2})?$/.test($paidAmount)) {
    						input.attr("data-paidAmountRule-msg", "金额格式不正确");
    						return false;
    					}
    					if (parseFloat($paidAmount) <= 0) {
    						input.attr("data-paidAmountRule-msg", "入账金额必须大于0");
    						return false;
    					}
    					// 计算选择的冲销项目最大剩余应还金额
    					var $maxPaidAmount = 0;
    					if ($('.overdueChecked')[0].checked)
    						$maxPaidAmount = $maxPaidAmount.add($unsettlePlan.accountOverdue.subtract($unsettlePlan.originPaidOverdue));
    					if ($('.interestChecked')[0].checked)
    						$maxPaidAmount = $maxPaidAmount.add($unsettlePlan.accountInterest.subtract($unsettlePlan.originPaidInterest));
    					if ($('.principalChecked')[0].checked)
    						$maxPaidAmount = $maxPaidAmount.add($unsettlePlan.accountPrincipal.subtract($unsettlePlan.originPaidPrincipal));

    					if (compareFloat($paidAmount, $maxPaidAmount) > 0) {
    						input.attr("data-paidAmountRule-msg", "不能大于剩余应还金额" + $maxPaidAmount);
    						return false;
    					}
    				}
    				return true;
    			},
    			paidDateRule: function(input) {
    				if(input.is("[name=in-date]")){
    					// 入账日期大于还款日且应还逾期为0则提示需要修改该计划逾期费，则检查还款计划的应还逾期必须大于0
    					if (input.val() > $unsettlePlan.repaymentDate
    							&& $unsettlePlan.accountOverdue == 0) {
	    					input.attr("data-paidDateRule-msg", "该还款计划应还逾期费为0，请修改该还款计划的应还逾期费");
	    					return false;
    					}
    				}
    				return true;
    			}
    		},
    		needRuleAttrbute : false
    	}).data("kendoValidator");
        $("#refund-pop-btn").click(function() {
            if(!validator.validate()) {
                return;
            }
            closeThePop();

            // 校验通过后，将本次冲销记录保存到全局变量中
            var $leftAmount = parseFloat($("input[name=in-money]").val().parseMoney());
            var $paidOverdue = 0;
            if ($('.overdueChecked')[0].checked) {
            	$paidOverdue = subFloat($unsettlePlan.accountOverdue, $unsettlePlan.originPaidOverdue);
            	if (compareFloat($paidOverdue, $leftAmount) > 0)
            		$paidOverdue = $leftAmount;
            	$leftAmount = subFloat($leftAmount, $paidOverdue);
            	$unsettlePlan.paidOverdue = addFloat($unsettlePlan.paidOverdue, $paidOverdue);
            	
            }

            var $paidInterest = 0;
            if ($('.interestChecked')[0].checked) {
            	$paidInterest = subFloat($unsettlePlan.accountInterest, $unsettlePlan.originPaidInterest);
            	if (compareFloat($paidInterest, $leftAmount) > 0)
            		$paidInterest = $leftAmount;
            	$leftAmount = subFloat($leftAmount, $paidInterest);
            	$unsettlePlan.paidInterest = addFloat($unsettlePlan.paidInterest, $paidInterest);
            }

            var $paidPrincipal = 0;
            if ($('.principalChecked')[0].checked) {
            	$paidPrincipal = subFloat($unsettlePlan.accountPrincipal, $unsettlePlan.originPaidPrincipal);
            	if (compareFloat($paidPrincipal, $leftAmount) > 0)
            		$paidPrincipal = $leftAmount;
            	$leftAmount = subFloat($leftAmount, $paidPrincipal);
            	$unsettlePlan.paidPrincipal = addFloat($unsettlePlan.paidPrincipal, $paidPrincipal);
            }

            var $paidPlan = {};
            $paidPlan.loanPlanId = $unsettlePlan.loanPlanId;
            $paidPlan.paidPrincipal = $paidPrincipal;
            $paidPlan.paidInterest = $paidInterest;
            $paidPlan.paidOverdue = $paidOverdue;
            $paidPlan.paidDate = $("input[name=in-date]").val();
        	$paidPlan.settleStatus = $unsettlePlan.forceSettled() ? 'SETTLED' : 'UNSETTLE';

            $paidPlans.push($paidPlan);

            // 并修改该还款计划的入账日期、已还本金、已还利息、已还逾期、结清状态
            if (!$unsettlePlan.lastPaidDate || $unsettlePlan.lastPaidDate < $paidPlan.paidDate)
            	$unsettlePlan.lastPaidDate = $paidPlan.paidDate;
            $unsettlePlan.settleStatus = $paidPlan.settleStatus;
            setPlanToTableOnPage($unsettlePlan, $unsettlePlanIndex);
        });
    });

    //打开修改弹窗
    $("body").on("click",".ch-repayment-btn",function(){
        var $index = amendClientRowRead("refund-btn");
        if($index!=undefined){
        	var $unsettlePlan = getPlanByIndex($index);
        	var $paid = getPaidByLoanPlanId($unsettlePlan.loanPlanId);

        	// 检查当前选中的修改计划是否有未提交的冲销，若没有则提示该计划没有未提交的还款冲销记录需要修改
        	if (!$paid) {
        		alertMessage("该还款计划没有未提交的还款冲销记录，请先进行还款冲销操作");
        		return;
        	}

        	// 如果有未提交的还款冲销记录，检查是否已还是否已经全部等于应还，如果是则提示该还款计划已全部还清，不可再修改
        	if ($unsettlePlan.forceSettled()) {
        		alertMessage("该还款计划已还全部等于应还，不可再修改");
        		return;
        	}

        	// 将本次的应还本金、已还利息、已还逾期、结清状态初始化到修改弹窗中
        	$(".open-repaymentPop").click();
        	PopMoneyNumFormat();

            $(".refund-capital").val($paid.paidPrincipal.toFixed(2).formatMoney());
            $(".refund-accrual").val($paid.paidInterest.toFixed(2).formatMoney());
            $(".refund-demurrage").val($paid.paidOverdue.toFixed(2).formatMoney());
            var $settleStatusText = $paid.settleStatus == 'SETTLED' ? "已结清" : "未结清";
            $("#refund-status").val($settleStatusText);
            $(".refund-status").prev().find(".k-input").text($settleStatusText);

            // 校验规则，已还金额均合法且不能大于剩余应还金额，已还之和等于本次入帐金额
            var validator = $("#repayment-form").kendoValidator({
        		rules: {
        			paidAmountRule: function(input) {
    					return validateModifyPaidInput(input, $unsettlePlan, $paid);
        			}
        		},
        		needRuleAttrbute : false
        	}).data("kendoValidator");
            $("#repayment-btn").click(function() {
                if(!validator.validate()) {
                    return;
                }

                closeThePop();
                // 修改后，将本次修改后的已还与原始已还相加修改到计划列表中，修改结清状态，更新全局变量中的本次冲销记录
                var $paidPrincipal = parseFloat($(".refund-capital").val().parseMoney());
                var $paidInterest = parseFloat($(".refund-accrual").val().parseMoney());
                var $paidOverdue = parseFloat($(".refund-demurrage").val().parseMoney());
                var $settleStatus = $("#refund-status option:selected").text() == "已结清" ? 'SETTLED' : 'UNSETTLE';

                $paid.paidPrincipal = $paidPrincipal;
                $paid.paidInterest = $paidInterest;
                $paid.paidOverdue = $paidOverdue;
                $paid.settleStatus = $settleStatus;

                $unsettlePlan.paidPrincipal = addFloat($unsettlePlan.originPaidPrincipal, $paid.paidPrincipal);
                $unsettlePlan.paidInterest = addFloat($unsettlePlan.originPaidInterest, $paid.paidInterest);
                $unsettlePlan.paidOverdue = addFloat($unsettlePlan.originPaidOverdue, $paid.paidOverdue);
                $unsettlePlan.settleStatus = $paid.settleStatus;
            	
	            setPlanToTableOnPage($unsettlePlan, $index);
            });
        }
    });
    //时间空间 （针对弹窗中时间空间失效的情况）
    $("body").delegate(".time-one", "focusin", function() {
        //选择日期范围是今天之前(<=今天)
        $(this).datepicker({
            format: "yyyy-mm-dd",
            language: "zh-CN",
            autoclose: true,
            todayHighlight: true,
            weekStart: 0,
            firstDay: 0
        }).on("show", function () {
            $("div.datepicker table thead .prev").html("");
            $("div.datepicker table thead .next").html("");
        });
    });

    // 提交按钮事件，点击后检查冲销记录全局变量，如果没有则提示请进行还款冲销
    // 如果有，则检查除了最后一个之外，所有的冲销记录必须是已结清状态，否则提示有未结清的还款计划
    $("#submit-paids-btn").on("click", function() {
    	if (!$canSubmit) {
    		alertMessage("请勿重复提交");
    		return;
    	}
    	$canSubmit = false;

    	if ($paidPlans.length == 0) {
    		alertMessage("没有未提交的还款冲销");
    		$canSubmit = true;
    		return;
    	}

    	
    	for (var $i = 0; $i < $paidPlans.length - 1; ++$i) {
    		if ($paidPlans[$i].settleStatus != 'SETTLED') {
    			alertMessage("本次提交的第" + $i + "个还款冲销需要是已结清状态");
        		$canSubmit = true;
        		return;
    		}
    	}
    	var $submitData = {};
    	$submitData.loanInfoId = $("#paidLoanInfoId").val();
    	$submitData.paidPlans = $paidPlans;
		$.ajax({
			type: 'post',
			url: "",
			contentType:'application/json',
			data: JSON.stringify($submitData),
			success: function(data){
				if("SUCCESS" == data.status){
					alertMessage("还款成功", 2);
					$("#tip-box-window").closest('.k-window').find('.k-window-actions').hide();
					var $comfirmedButton = '<div class="dlg-del-row">'
			            + '<a class="default-link confirm-link" href="' + basepath + "/asset/loan/paid?loanInfoId=" + $("#paidLoanInfoId").val() + '">继续冲销</a>'
			            + '<a class="default-link confirm-link" href="' + basepath + "/asset/loan/goto/loanDetail?id=" + $("#paidLoanInfoId").val() + '">进入详情</a>'
			            + '</div>';
				    $("#tip-box-window").append($comfirmedButton);
				} else {
					alertMessage(data.message, 3);
		    		$canSubmit = true;
				}
			},
			error: function(data){
				alertMessage("网络异常：" + data.responseText, 3);
	    		$canSubmit = true;
			}
		});
	});
});

/**
 * 修改还款冲销时的校验函数
 * @param input 输入框
 * @param $unsettlePlan 修改的还款计划
 * @param $paid 修改前的还款冲销记录
 */
function validateModifyPaidInput(input, $unsettlePlan, $paid) {
	var $inputType;
	if (input.is(".refund-capital"))
		$inputType = "paidPrincipal";
	else if (input.is(".refund-accrual"))
		$inputType = "paidInterest";
	else if (input.is(".refund-demurrage"))
		$inputType = "paidOverdue";
	else
		return true;


	var $paidAmount = input.val().parseMoney();
	if (!/^([1-9][\d]{0,8}|0)(\.[\d]{1,2})?$/.test($paidAmount)) {
		input.attr("data-paidAmountRule-msg", "金额格式不正确");
		return false;
	}
	if (parseFloat($paidAmount) < 0) {
		input.attr("data-paidAmountRule-msg", "必须大于等于0");
		return false;
	}
	// 计算选择的冲销项目最大剩余应还金额
	var $maxPaidAmount = 0;
	if ($inputType == "paidPrincipal")
		$maxPaidAmount = subFloat($unsettlePlan.accountPrincipal, $unsettlePlan.originPaidPrincipal);
	if ($inputType == "paidInterest")
		$maxPaidAmount = subFloat($unsettlePlan.accountInterest, $unsettlePlan.originPaidInterest);
	if ($inputType == "paidOverdue")
		$maxPaidAmount = subFloat($unsettlePlan.accountOverdue, $unsettlePlan.originPaidOverdue);

	if (compareFloat($paidAmount, $maxPaidAmount) > 0) {
		input.attr("data-paidAmountRule-msg", "不能大于剩余应还金额" + $maxPaidAmount);
		return false;
	}

	var $cachedPaidAmount = addFloat(addFloat($paid.paidPrincipal, $paid.paidInterest), $paid.paidOverdue);
	var $pagePaidAmout = addFloat($(".refund-capital").val().parseMoney(), $(".refund-accrual").val().parseMoney());
	$pagePaidAmout = addFloat($pagePaidAmout, $(".refund-demurrage").val().parseMoney());
	if (compareFloat($cachedPaidAmount, $pagePaidAmout) != 0) {
		input.attr("data-paidAmountRule-msg", "本金利息逾期之和不等于本次入帐金额" + $cachedPaidAmount);
		return false;
	}
	return true;
}

/**
 * 通过还款计划ID从冲销记录中返回本次冲销数据
 */
function getPaidByLoanPlanId($loanPlanId) {
	if ($paidPlans.length) {
		for (var $i in $paidPlans) {
			if ($paidPlans[$i].loanPlanId == $loanPlanId)
				return $paidPlans[$i];
		}
	}
	return null;
}

function pushPaidByLoanPlanId($paid) {
	if ($paidPlans.length) {
		for (var $i in $paidPlans) {
			if ($paidPlans[$i].loanPlanId == $paid.loanPlanId)
				$paidPlans[$i] = $paid;
		}
	}
}

/**
 * 还款冲销后将最新的还款计划回显到页面上
 * @param $plan
 */
function setPlanToTableOnPage($plan, $index) {
	var $tr = $("#refund-tb tr").eq($index);
	$tr.find('.paidPrincipal').text($plan.paidPrincipal.toFixed(2).formatMoney());
	$tr.find('.paidInterest').text($plan.paidInterest.toFixed(2).formatMoney());
	$tr.find('.paidOverdue').text($plan.paidOverdue.toFixed(2).formatMoney());
	$tr.find('.lastPaidDate').text($plan.lastPaidDate);

	var $days = (new Date($plan.lastPaidDate).getTime() - new Date($plan.repaymentDate).getTime()) / (1000*60*60*24);
	var $forwardDays = $days < 0 ? -$days : '-';
	var $overdueDays = $days > 0 ? $days : '-';
	$tr.find('.forwardDays').text($forwardDays);
	$tr.find('.overdueDays').text($overdueDays);

	if ('SETTLED' == $plan.settleStatus)
		$tr.find('.settleStatusDesc').text("已结清");
}

/**
 * 获取某一行还款计划中的还款计划ID
 */
function getPlanByIndex($index) {
	var $plan = {};
	var $tr = $("#refund-tb tr").eq($index);
	$plan.loanPlanId = $tr.find('input[name="loanPlanId"]').val();
	$plan.repaymentDate = $.trim($tr.find('.repaymentDate').text());
	$plan.accountPrincipal = parseFloat($.trim($tr.find('.accountPrincipal').text()).parseMoney());
	$plan.accountInterest = parseFloat($.trim($tr.find('.accountInterest').text()).parseMoney());
	$plan.accountOverdue = parseFloat($.trim($tr.find('.accountOverdue').text()).parseMoney());
	$plan.accountAmount = parseFloat($.trim($tr.find('.accountAmount').text()).parseMoney());
	$plan.originPaidPrincipal = parseFloat($tr.find('.paidPrincipal').attr('originPaidPrincipal').parseMoney());
	$plan.originPaidInterest = parseFloat($tr.find('.paidInterest').attr('originPaidInterest').parseMoney());
	$plan.originPaidOverdue = parseFloat($tr.find('.paidOverdue').attr('originPaidOverdue').parseMoney());
	$plan.paidPrincipal = parseFloat($tr.find('.paidPrincipal').text().parseMoney());
	$plan.paidInterest = parseFloat($tr.find('.paidInterest').text().parseMoney());
	$plan.paidOverdue = parseFloat($tr.find('.paidOverdue').text().parseMoney());
	$plan.lastPaidDate = $.trim($tr.find('.lastPaidDate').text());
	$plan.forceSettled = function() {
		if (this.paidPrincipal == this.accountPrincipal
				&& this.paidInterest == this.accountInterest
				&& this.paidOverdue == this.accountOverdue)
			return true;
		else
			return false;
	}
	return $plan;
}
/**
 * 提示
 * @param $type 1 表示警告，2表示成功，3表示错误
 */
function alertMessage($message, $type) {
	$(".open-message-btn").click();
    $("#tip-box-window .dlg-notice").addClass("notice-icon0" + ($type || "1"));
    $("#tip-box-window .notice-content").text($message);
}

/** 
 * 寻找第一个未结清的项，返回改行的序号，从0开始，如果没有找到则返回-1
 */
function getFirstUnSettlePlan() {
	var $table = $("#refund-tb tr");
	var $index= -1;  //保存第一条未结清项的tr序号 第一行从0开始
	$.each($table, function(obj){
	    var $txt = $.trim($(this).find(".settleStatusDesc").text());
	    if($txt=="未结清"){
	        $index=$(this).index();
	        return false;
	    }
	});
	return $index;
}

//表格操作（判断修改的行，返回修改的行号）
function amendClientRowRead(ckb){
    var ckbs=$("input[name="+ckb+"]:checked");
    if(ckbs.size()==0){
    	alertMessage("请选择需要修改的行！");
    }else if(ckbs.size()!=1){
    	alertMessage("一次只能修改一行！");
    }else{
        var chTr_index =ckbs.parent().parent().index();  //用于标记修改的行,仅用于修改操作
        return chTr_index;
    }
}

//关闭弹窗
function closeThePop(){
    $(".k-overlay").hide();
    $(".k-window").hide();
}

/**
 * 将两个数值相加，结果保留两位小数，返回浮点数
 * @param number1
 * @param number2
 */
function addFloat(number1, number2) {
	var $result = parseFloat(number1) + parseFloat(number2);
	return parseFloat($result.toFixed(2));
}

/**
 * 减法，结果保留两位小数，返回浮点数
 * @param number1
 * @param number2
 */
function subFloat(number1, number2) {
	var $result = parseFloat(number1) - parseFloat(number2);
	return parseFloat($result.toFixed(2));
}

/**
 * 比较两个数大小，先转为2位有效小数再比较
 * @param num1
 * @param num2
 */
function compareFloat(num1, num2) {
	var $num1 = parseFloat(parseFloat(num1).toFixed(2));
	var $num2 = parseFloat(parseFloat(num2).toFixed(2));
	if ($num1 > $num2)
		return 1;
	else if ($num1 < $num2)
		return -1;
	else
		return 0;
}