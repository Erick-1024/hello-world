var pageSize = 10;

var percentPattern = "^[0-9]+(.[0-9]{1,3})?$";

var submitFlag = true;

var interestRateRule={
	required : "融资比例不能为空",
	pattern: percentPattern,
	message: "融资比例格式不正确",
	rule: function(input) {
		if(input.is("input[name='exa-ratio']")){
			if("" != $("input[name='exa-ratio']").val()){
				if(parseFloat($("input[name='exa-ratio']").val()) > 0 && parseFloat($("input[name='exa-ratio']").val()) <= 100){
					return true;
				}else {
					return false;
				}
			}else {
				return true;
			}
		}
		return true;
	},
	ruleValMessage: "融资比例应大于0且小于100"
};
var subLimitRateRule={
		rule: function(input) {
			if(input.is("input[name='exa-son']")){
				if($("input[name='exa-yes']").is(':checked')){
					if("" != $("input[name='exa-son']").val()){
						if(parseFloat($("input[name='exa-son']").val()) > 0){
							return true;
						}else {
							return false;
						}
					}else {
						return false;
					}
				}
			}
			return true;
		},
		ruleMessage: "子额度应大于0"
};

$(function(){
    //初始化新增约定弹窗
    new PopWindow(".open-sec-btn",{
        title: "新增约定信息",
        width: 800,
        reload: true,
        template: "#template-resetPwd-new"
    }).init();
    //初始化新增约定弹窗
    new PopWindow(".open-appoint-btn",{
        title: "新增/修改约定信息",
        width: 800,
        reload: true,
        template: "#template-resetPwd-arr"
    }).init();
    //初始化费用信息弹窗
    new PopWindow(".open-cost-btn",{
        title: "新增/修改费用信息",
        width: 800,
        reload: true,
        template: "#template-resetPwd-cost"
    }).init();
    //初始化担保信息弹窗
    new PopWindow(".open-assure-btn",{
        title: "新增/修改担保信息",
        width: 800,
        reload: true,
        template: "#template-resetPwd-ass"
    }).init();
    //初始化消息弹窗
    new PopWindow(".open-message-btn",{
        title: "提示",
        width: 400,
        reload: true,
        template: "#tipBox_template"
    }).init();
	 //初始化确认弹窗
    confirmPopWindow = new PopWindow({
        title: "提示",
        width: 420,
        reload: true,
        template: "#template-notice"
    }).init();
    //新增约定弹窗（第一步）
    $("body").on("click",".add-appoint-btn",function(){
        $(".open-sec-btn").click();
        //新建弹窗表格初始化
        getCustomerForWindow();
    });
    //新增选择页面子额度可输入控制
    $("body").on("change",".checked-btn",function(){
        var attr = $(this).is(":checked");
        if(attr==true){
            $(".for-check-one").removeAttr("disabled","disabled");
        }else{
            $(".for-check-one").attr("disabled","disabled");
        }
    });

    //新增约定信息
    $("body").on("click",".go-appoint-pop",function(){
    	var custId = $('input[name="client-choose"]:checked').val();
    	if(custId == null || custId == ""){
    		showAlertWin("请选择一个客户");
    		return;
    	}
        $(".open-appoint-btn").click();
        $("span[name='apParty']").text($('input[name="client-choose"]:checked').parent().next().next().text());
        $("span[name='custType']").text($('input[name="client-choose"]:checked').parent().next().next().next().text());
        $("input[name='custId']").val(custId);
        $("input[name='custType']").val($('input[name="client-choose"]:checked').parent().next().next().next().next().text());
        moneyFormat();
        //表单验证
        setValidator($("input[name='exa-ratio']"), "required", true, interestRateRule.required);
        setValidator($("input[name='exa-ratio']"), "pattern", interestRateRule.pattern, interestRateRule.message);
        var validator = $("#appoint-form").kendoValidator({
    		rules: {
    			interestRateRule: interestRateRule.rule,
    			subLimitRateRule: subLimitRateRule.rule,
    		},
    		messages: {
    			interestRateRule: interestRateRule.ruleValMessage,
    			subLimitRateRule: subLimitRateRule.ruleMessage,
    		},
    		needRuleAttrbute : false
    	}).data("kendoValidator");
        $("#appoint-btn").click(function() {
            if(!validator.validate()) {
                return;
            }
            opAppoint();
        });
    });
    //新增费用信息
    $("body").on("click",".add-cost-btn",function(){
        $(".open-cost-btn").click();
        //表单验证
        var validator = $("#cost-form").kendoValidator().data("kendoValidator");
        $("#cost-btn").click(function() {
            if(!validator.validate()) {
                return;
            }
            opCost();
        });
        moneyFormat();
    });
    //新增担保信息
    $("body").on("click",".add-assure-btn",function(){
        $(".open-assure-btn").click();
        //表单验证
        var validator = $("#assure-form").kendoValidator().data("kendoValidator");
        $("#assure-btn").click(function() {
            if(!validator.validate()) {
                return;
            }
            opAssure();
        });
    });
    //修改约定信息
    $("body").on("click",".ch-appoint-btn",function(){
        var $index = amendClientRowRead("appoint-tb");
        if($index!=undefined){
            $(".open-appoint-btn").click();
            var $td = $("#appoint-tb tr").eq($index).find("td");
            $(".appoint-ch-id").val($index);
            $("span[name='custType']").text($("#appoint-tb tr").eq($index).find("input[name='custTypeDesc']").val());
            $(".ap-name").text($td.eq(0).text());
            $(".ap-ratio").val($td.eq(1).text().substring(0,$td.eq(1).text().length-1));
            var selectVal = $("#appoint-tb tr").find("input[name='counterpartyList["+$index+"].factoringType']").val();
            var type = $("#appoint-form select.ap-type").data("kendoDropDownList");
            type.select($("#appoint-form select.ap-type option[value="+selectVal+"]").index());
            moneyFormat();
            if($td.eq(3).text()=="是"){
                $("input[name=exa-yes]").attr("checked","checked");
                $(".for-check-one").removeAttr("disabled","disabled").val($td.eq(4).text().substring(1));
            }else{
                $("input[name=exa-yes]").removeAttr("checked","checked");
                $(".for-check-one").attr("disabled","disabled");
            }

            //表单验证
            setValidator($("input[name='exa-ratio']"), "required", true, interestRateRule.required);
            setValidator($("input[name='exa-ratio']"), "pattern", interestRateRule.pattern, interestRateRule.message);
            var validator = $("#appoint-form").kendoValidator({
        		rules: {
        			interestRateRule: interestRateRule.rule,
        			subLimitRateRule: subLimitRateRule.rule,
        		},
        		messages: {
        			interestRateRule: interestRateRule.ruleValMessage,
        			subLimitRateRule: subLimitRateRule.ruleMessage,
        		},
        		needRuleAttrbute : false
        	}).data("kendoValidator");
            $("#appoint-btn").click(function() {
                if(!validator.validate()) {
                    return;
                }
                opAppoint();
            });
        }
    });
    //修改费用信息
    $("body").on("click",".ch-cost-btn",function(){
        var $index = amendClientRowRead("cost-tb");
        if($index!=undefined){
            $(".open-cost-btn").click();
            var $td = $("#cost-tb tr").eq($index).find("td");

            $(".co-class").val($td.eq(0).text());
            $(".co-money").val($td.eq(1).text().substring(1));
            $(".cost-ch-id").val($index);

            //表单验证
            var validator = $("#cost-form").kendoValidator().data("kendoValidator");
            $("#cost-btn").click(function() {
                if(!validator.validate()) {
                    return;
                }
                opCost();
            });
        }
        moneyFormat();
    });
    //修改担保信息
    $("body").on("click",".ch-assure-btn",function(){
        var $index = amendClientRowRead("assure-tb");
        if($index!=undefined){
            $(".open-assure-btn").click();
            var $td = $("#assure-tb tr").eq($index).find("td");

            $(".assure-ch-id").val($index);
            $(".as-num").val($td.eq(0).text());
            $(".as-message").val($td.eq(1).text());
            $(".as-type").val($td.eq(2).text());

            //表单验证
            var validator = $("#assure-form").kendoValidator().data("kendoValidator");
            $("#assure-btn").click(function() {
                if(!validator.validate()) {
                    return;
                }
                opAssure();
            });
        }
    });
    //删除约定信息
    $("body").on("click",".del-appoint-btn",function(){
        removeClientRow("appoint-tb");
    });
    //删除费用信息
    $("body").on("click",".del-cost-btn",function(){
        removeClientRow("cost-tb");
    });
    //删除担保信息
    $("body").on("click",".del-assure-btn",function(){
        removeClientRow("assure-tb");
    });

    // 加载时判断是否加载额度信息
    var businessContractNo = $.trim($("input[name='businessContractNo']").val());
    var customerId = $.trim($("input[name='customerId']").val());
    if(businessContractNo != null && businessContractNo != ""){
    	queryCredit(businessContractNo, customerId);
    }
    
    // 查询额度信息
    $("body").on("blur","input[name='businessContractNo']",function(){
    	var businessContractNo = $.trim($(this).val());
    	var customerId = $.trim($("input[name='customerId']").val());
    	queryCredit(businessContractNo, customerId);
    });

    // 搜索客户信息
    $("body").on("click",".form-search-btn",function(){
    	getCustomerForWindow();
    });

    // 提交
    $("body").on("click",".sc-btn",function(){
    	if(submitFlag){
    		submitFlag = false;
    		var tempCreditId = $("#creditVersion").val();
    		if(submitValidate()){
    			$.ajax({
    				type: "POST",
    				url: basepath + "/asset/factorBusiness/queryCreditVersion",
    				data: {creditId : $("#creditId").val()},
    				dataType: "json",
    				success: function(data){
    					if(data.status == "SUCCESS"){
    						if(data.data == tempCreditId){
    							$("#businessFactorForm").submit();
    						}else{
    							submitFlag = true;
    							showAlertWin("额度发生变化，请确认新的额度信息再提交");
    							queryCredit($.trim($("input[name='businessContractNo']").val()), $.trim($("input[name='customerId']").val()))
    						}
    					}else{
    						submitFlag = true;
    						showAlertWin("系统繁忙，请稍后重试");
    					}
    				}
    			});
    		}else{
    			submitFlag = true;
    		}
    	}
    });
    
    $("body").on("keyup", ".bankCard", bankCardNum);
});

function bankCardNum(){
	var temp = this.value.replace(/\D/g, '').replace(/(....)(?=.)/g, '$1 ');
	if(temp != this.value){
		this.value = temp;
	}
};

function bankCardFormat(){
	
}

// 异步查询额度以及额度当前版本
function queryCredit(businessContractNo, customerId){
	$.ajax({
		type: "POST",
		url: basepath + "/asset/factorBusiness/queryCredit",
		data: {businessContractNo : businessContractNo,
			customerId: customerId,
			id: $("input[name='id']").val()},
		dataType: "json",
		success: function(data){
			if(data.status == "SUCCESS"){
				$("span[name='creditModeDesc']").text(data.data.creditModeDesc);
				$("span[name='currencyDesc']").text(data.data.currency);
				$("span[name='total']").text("￥" + data.data.totalLimitStr.formatMoney());
				$("span[name='used']").text("￥" + data.data.usedLimitStr.formatMoney());
				$("span[name='available']").text("￥" + data.data.availableLimitStr.formatMoney());
				$("span[name='due']").text(data.data.dueDate);
				$("#creditId").val(data.data.id);
				queryCreditVersion();
			}else{
				$("span[name='creditModeDesc']").text();
				$("span[name='currencyDesc']").text();
				$("span[name='total']").text();
				$("span[name='used']").text();
				$("span[name='available']").text();
				$("span[name='due']").text();
				$("#creditId").val("");
			}
		}
	});
}

function queryCreditVersion(){
	$.ajax({
		type: "POST",
		url: basepath + "/asset/factorBusiness/queryCreditVersion",
		data: {creditId : $("#creditId").val()},
		dataType: "json",
		success: function(data){
			if(data.status == "SUCCESS"){
				$("#creditVersion").val(data.data);
			}else{
				$("#creditId").val("");
			}
		}
	});
}

//表格操作（增加）
function addClientRow(tab,trHtml){
	var $tr = $("#"+tab+"");
	trHtml.appendTo($tr);
	/*
    var $tr=$("#"+tab+" tr").eq(-1);
    if($tr.size()==0){
        $(".open-message-btn").click();
        $("#tip-box-window .dlg-notice").addClass("notice-icon01");
        $("#tip-box-window .notice-content").text("指定的table id或行数不存在！");
        return;
    }
    $tr.after(trHtml);
    */
}
//表格操作（判断修改的行，返回修改的行号）
function amendClientRowRead(ckb){
    var ckbs=$("input[name="+ckb+"]:checked");
    if(ckbs.size()==0){
        $(".open-message-btn").click();
        $("#tip-box-window .dlg-notice").addClass("notice-icon01");
        $("#tip-box-window .notice-content").text("请选择需要修改的行！");
    }else if(ckbs.size()!=1){
        $(".open-message-btn").click();
        $("#tip-box-window .dlg-notice").addClass("notice-icon01");
        $("#tip-box-window .notice-content").text("一次只能修改一行！");
    }else{
        var chTr_index =ckbs.parent().parent().index();  //用于标记修改的行,仅用于修改操作
        return chTr_index;
    }
}
//表格操作（删除）
function removeClientRow(ckb) {
    //获取选中的复选框，然后循环遍历删除
    var ckbs = $("input[name=" + ckb + "]:checked");
    if (ckbs.size() == 0) {
        $(".open-message-btn").click();
        $("#tip-box-window .dlg-notice").addClass("notice-icon01");
        $("#tip-box-window .notice-content").text("请选择要删除的行！");
        return;
    }
    
	confirmPopWindow.open().center();
    $("#confirm-box-window #operationObj").val();
    $("#confirm-box-window .notice-content").html("是否确认删除");
    
    $("#delete-confirm").click(function(){
	    ckbs.each(function () {
	        $(this).parent().parent().remove();
	    });
	    //删除后更新序号
	    var len = $("#"+ckb+" tr").length;
	    for(var i = 0;i<len;i++) {
	        $("#" + ckb + " tr:eq(" + i + ") th:last").html(i+1);
	        var $temp = $("#" + ckb + " tr:eq(" + i + ")").find(".needAlterIndex");
	        $temp.each(function () {
	            $(this).prop("name", $(this).data("left") + i + $(this).data("right"));
	        });
	        $temp = $("#" + ckb + " tr:eq(" + i + ")").find(".sequence");
	        $temp.each(function () {
	            $(this).val(i);
	        });
	    }
	    closeThePop();
    });
}
//关闭弹窗
function closeThePop(){
    $(".k-overlay").hide();
    $(".k-window").hide();
}
//新增/修改约定信息核心操作
function opAppoint(){
	var custId = $("input[name='custId']").val();
	var custType = $("span[name='custType']").text();
	var custTypeEnum = $("input[name='custType']").val();
	var flag = $("input[name=exa-yes]").is(":checked") ? true : false;
	var type = $(".ap-type  option:selected").val();
	var index = $("#appoint-tb tr").length;
	var subLimitTemp = "";
	var moneyFlag="-"
	var $son = "";
	if(flag){
		subLimitTemp = $(".for-check-one").val().parseMoney();
		moneyFlag = "￥"
		$son = $(".for-check-one").val();
	}else{
		subLimitTemp = "";
	}
    var $index = $(".appoint-ch-id").val(),
        $ind = $("#appoint-tb tr").length+1,
        $name = $(".ap-name").text(),
        $ratio = $(".ap-ratio").val(),
        $type = $(".ap-type").prev().find(".k-input").text(),
        $judge = $("input[name=exa-yes]").is(":checked")?"是":"否",
        html = $('<tr class="client-add-tr">'+
              '<th><input type="checkbox" class="input-new" name="appoint-tb"></th>'+
              '<th>'+$ind+'</th>'+
              '<td>'+$name+'</td>'+
              '<td>'+$ratio+'%</td>'+
              '<td>'+$type+'</td>'+
              '<td>'+$judge+'</td>'+
              '<td>'+ moneyFlag + $son+'</td>'+
              '<input class="needAlterIndex" type="hidden" name="counterpartyList['+index+'].counterparty" value='+$name+' data-left="counterpartyList[" data-right="].counterparty">'+
              '<input type="hidden" name="custTypeDesc" value='+custType+'>'+
              '<input class="needAlterIndex financingRatio" type="hidden" name="counterpartyList['+index+'].financingRatio" value='+$ratio+'% data-left="counterpartyList[" data-right="].financingRatio">'+
              '<input class="needAlterIndex" type="hidden" name="counterpartyList['+index+'].counterpartyType" value='+custTypeEnum+' data-left="counterpartyList[" data-right="].counterpartyType">'+
              '<input class="needAlterIndex counterpartyId" type="hidden" name="counterpartyList['+index+'].counterpartyId" value='+custId+' data-left="counterpartyList[" data-right="].counterpartyId">'+
              '<input class="needAlterIndex" type="hidden" name="counterpartyList['+index+'].querySubLimitFlag" value=' + flag + ' data-left="counterpartyList[" data-right="].querySubLimitFlag">'+
              '<input class="needAlterIndex" type="hidden" name="counterpartyList['+index+'].subLimit" value='+ subLimitTemp +' data-left="counterpartyList[" data-right="].subLimit">'+
              '<input class="needAlterIndex" type="hidden" name="counterpartyList['+index+'].factoringType" value='+ type +' data-left="counterpartyList[" data-right="].factoringType">'+
              '<input class="needAlterIndex sequence" type="hidden" name="counterpartyList['+index+'].sequence" value='+ index +' data-left="counterpartyList[" data-right="].sequence">'+
              '<input type="hidden" name="factoringTypeDesc" value='+ $type +'>'+
              '</tr>');
    closeThePop();
    if($index==''){
        addClientRow("appoint-tb",html);
    }else{
        var $td = $("#appoint-tb tr").eq($index).find("td");
        $td.eq(0).html($name);
        $td.eq(1).html($ratio+"%");
        $td.eq(2).html($type);
        $td.eq(3).html($judge);
        if(flag){
        	$td.eq(4).html("￥"+$son);
        }else{
        	$td.eq(4).html("-");
        }
        $("#appoint-tb tr").find("input[name='counterpartyList["+$index+"].financingRatio']").val($ratio+"%");
        $("#appoint-tb tr").find("input[name='counterpartyList["+$index+"].querySubLimitFlag']").val(flag);
        $("#appoint-tb tr").find("input[name='counterpartyList["+$index+"].subLimit']").val(subLimitTemp);
        $("#appoint-tb tr").find("input[name='counterpartyList["+$index+"].factoringType']").val(type);
    }
}
//新增/修改费用信息核心操作
function opCost(){
	var index = $("#cost-tb tr").length;
	var amount = $(".co-money").val().parseMoney();
    var $index = $(".cost-ch-id").val(),
        $ind = $("#cost-tb tr").length+1,
        $class = $(".co-class").val(),
        $money = $(".co-money").val(),
        html = $('<tr class="client-add-tr">'+
                '<th><input type="checkbox" class="input-new" name="cost-tb"></th>'+
                '<th>'+$ind+'</th>'+
                '<td>'+$class+'</td>'+
                '<td>￥'+$money+'</td>'+
                '<td></td>'+
                '<input class="needAlterIndex" type="hidden" name="expenseList['+index+'].expenseSubject" value='+ $class +' data-left="expenseList[" data-right="].expenseSubject">'+
                '<input class="needAlterIndex" type="hidden" name="expenseList['+index+'].amountStr" value='+ amount +' data-left="expenseList[" data-right="].amountStr">'+
                '<input class="needAlterIndex sequence" type="hidden" name="expenseList['+index+'].sequence" value='+ index +' data-left="expenseList[" data-right="].sequence">'+
                '</tr>');
    closeThePop();
    if($index==''){
        addClientRow("cost-tb",html);
    }else{
        var $td = $("#cost-tb tr").eq($index).find("td");
        $td.eq(0).html($class);
        $td.eq(1).html("￥"+$money);
        $("#cost-tb tr").find("input[name='expenseList["+$index+"].expenseSubject']").val($class);
        $("#cost-tb tr").find("input[name='expenseList["+$index+"].amountStr']").val(amount);
    }
}
//新增/修改担保信息核心操作
function opAssure(){
	var index = $("#assure-tb tr").length;
    var $index = $(".assure-ch-id").val(),
        $ind = $("#assure-tb tr").length+1,
        $num = $(".as-num").val(),
        $message = $(".as-message").val(),
        $type = $(".as-type").val(),
        html = $('<tr class="client-add-tr">'+
                '<th><input type="checkbox" class="input-new" name="assure-tb"></th>'+
                '<th>'+$ind+'</th>'+
                '<td>'+$num+'</td>'+
                '<td>'+$message+'</td>'+
                '<td>'+$type+'</td>'+
                '<input class="needAlterIndex" type="hidden" name="guaranteeInfoList['+index+'].guaranteedContractNo" value=' + $num + ' data-left="guaranteeInfoList[" data-right="].guaranteedContractNo">'+
                '<input class="needAlterIndex" type="hidden" name="guaranteeInfoList['+index+'].guarantorInfo" value='+ $message +' data-left="guaranteeInfoList[" data-right="].guarantorInfo">'+
                '<input class="needAlterIndex" type="hidden" name="guaranteeInfoList['+index+'].guaranteeType" value='+ $type +' data-left="guaranteeInfoList[" data-right="].guaranteeType">'+
                '<input class="needAlterIndex sequence" type="hidden" name="guaranteeInfoList['+index+'].sequence" value='+ index +' data-left="guaranteeInfoList[" data-right="].sequence">'+
                '</tr>');
    closeThePop();
    if($index==''){
        addClientRow("assure-tb",html);
    }else{
        var $td = $("#assure-tb tr").eq($index).find("td");
        $td.eq(0).html($num);
        $td.eq(1).html($message);
        $td.eq(2).html($type);
        $("#assure-tb tr").find("input[name='guaranteeInfoList["+$index+"].guaranteedContractNo']").val($num);
        $("#assure-tb tr").find("input[name='guaranteeInfoList["+$index+"].guarantorInfo']").val($message);
        $("#assure-tb tr").find("input[name='guaranteeInfoList["+$index+"].guaranteeType']").val($type);
    }
}

function submitValidate(){
	var bcn = $("input[name='businessContractNo']").val();
	if(bcn == null || bcn == ""){
		showAlertWin("业务合同号不能为空");
		return false;
	}
	var cv = $("#creditId").val();
	if(cv == null || cv == ""){
		showAlertWin("业务合同号不存在或已被使用");
		return false;
	}
	var cur = $("select[name='currency']").val();
	if(cur == null || cur == ""){
		showAlertWin("币种不能为空");
		return false;
	}
	var pn = $("input[name='projectName']").val();
	if(pn == null || pn == ""){
		showAlertWin("项目名称不能为空");
		return false;
	}
	var bp = $("select[name='businessProduct']").val();
	if(bp == null || bp == ""){
		showAlertWin("业务产品不能为空");
		return false;
	}
	var bm = $("select[name='businessMode']").val();
	if(bm == null || bm == ""){
		showAlertWin("业务模式不能为空");
		return false;
	}
	var csd = $("input[name='contractStartDate']").val();
	if(csd == null || csd == ""){
		showAlertWin("合同起始日不能为空");
		return false;
	}
	var ced = $("input[name='contractEndDate']").val();
	if(ced == null || ced == ""){
		showAlertWin("合同到期日不能为空");
		return false;
	}
	var $counterparties = $(".financingRatio");
	if($counterparties.length <= 0 ){
		showAlertWin("交易对手不能为空");
		return false;
	}
//	var radio = 0;
//	$counterparties.each(function () {
//       var tempRadio = $(this).val().substring(0,$(this).val().length-1);
//       radio += parseFloat(tempRadio);
//    });
//	if(radio > 100){
//		showAlertWin("融资比例之和不能大于100%");
//		return false;
//	}
	var fan = $("input[name='factoringAccountName']").val();
	if(fan == null || fan == ""){
		showAlertWin("保理专户户名不能为空");
		return false;
	}
	var faba = $("input[name='factoringAccountBankAddress']").val();
	if(faba == null || faba == ""){
		showAlertWin("保理专户开户行不能为空");
		return false;
	}
	var fa = $("input[name='factoringAccount']").val();
	if(fa == null || fa == ""){
		showAlertWin("保理专户账号不能为空");
		return false;
	}
	var san = $("input[name='settlementAccountName']").val();
	if(san == null || san == ""){
		showAlertWin("结算账户户名不能为空");
		return false;
	}
	var saba = $("input[name='settlementAccountBankAddress']").val();
	if(saba == null || saba == ""){
		showAlertWin("结算账户开户行不能为空");
		return false;
	}
	var sa = $("input[name='settlementAccount']").val();
	if(sa == null || sa == ""){
		showAlertWin("结算账户账号不能为空");
		return false;
	}
	return true;
}

function moneyFormat(){
	$("body input.moneyNum").on({
        focus: function(){
            $(this).attr("data-oval",$(this).val()); //将当前值存入自定义属性
        },
        blur: function(){
            var oldVal=($(this).attr("data-oval")); //获取原值
            var newVal=($(this).val()); //获取当前值
            if (oldVal!=newVal) {
                if(newVal == "" || isNaN(newVal)){
                    this.value = "";
                    return this.value;
                }
                var s = this.value;
                var temp;
                //n = n > 0 && n <= 20 ? n : 2;
                if(/.+(\..*\.|\-).*/.test(s)){
                    return;
                }
                s = parseFloat((s + "").replace(/[^\d\.\-]/g, "")).toFixed(2) + "";
                var l = s.split(".")[0].split("").reverse(),
                    r = s.split(".")[1];
                t = "";
                for(i = 0; i < l.length; i ++ ) {
                    t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length && (l[i+1]!='-')? "," : "");
                }
                temp = t.split("").reverse().join("") + "." + r;
                this.value = temp;
                return this.value;
            }
        }
    });
}

function getCustomerForWindow(){
	var sequence = 0;
	$("#client-out-grid").empty();
	//初始化数据表格
    var grid = $("#client-out-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
//        sortable: true,  //列排序
        dataSource:{
//            pageSize: 5,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
            	read: function(option){
            		var $counterpartyIdList = $(".counterpartyId");
            		var paraData = {"exceptList":[]};
            		var i = 0;
            		if($counterpartyIdList.length > 0){
            			$counterpartyIdList.each(function () {
            				paraData.exceptList[i] = $.trim($(this).val());
            				i++;
            			});
            		}
            		paraData.exceptList[i] = $.trim($("input[name='customerId']").val());
                	paraData.customerName = $.trim($(".customerNameWindow").val());
                	paraData.page = option.data.page;
                	paraData.pageSize = option.data.pageSize;
                	$.ajax({
                		contentType:"application/json",
                    	data: JSON.stringify(paraData),
                    	type : "POST",
                    	dataType:"json",
                    	url: basepath+"/asset/factorBusiness/queryCounterpartyList",
                    	success:function(data){
                    		option.success(data);
                    	}
                	});
                 }
            },
		    serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			schema: {
				data: "data",
				total: function(data) {
					var total = data.totalNum;
						if(total == 0){
							$("#manual-repayGrid .k-grid-header-wrap").css("overflow-x","auto");
						}else {
							$("#manual-repayGrid .k-grid-header-wrap").css("overflow-x","");
					}
						return total;
				}
			}
        },
        columns: [{
            field: "sequence",
            title: "选择用户",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(item){
                return "<input type='radio' name='client-choose' value='"+item.id+"' class='client-radio'>";
            }
        },{
            field: "sequence",
            title: "序号",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template : function(data){
				sequence ++;
				return sequence;
			},
        },{
            field: "customerName",
            title: "客户名称",
            width: 300,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "customerTypeDesc",
            title: "客户类型",
            width: 300,
            hidden: true
        },{
            field: "customerType",
            title: "客户类型",
            width: 300,
            hidden: true
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            pageSize: pageSize,
            page: 1,
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
    grid.data("kendoGrid").pager.bind('change', function(){
		sequence = 0;
	});
}