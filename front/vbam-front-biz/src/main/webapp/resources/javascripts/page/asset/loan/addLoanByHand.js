//全局变量
var checkedInvoiceIdGlobal = [];//已增加的应收账款在弹框中的id数组
var invoicePopDataGlobal = [];//应收账款弹框数据
var counterpartyTextGlobal;//当前选中的交易对手text
var overdueDateGlobal;//计算后的到期日
var canSubmit = true;
var loanValidator;
var businessContractNoGlobal="";
var deleteCkb;

$(function() {
	
	initButtonEventAndTable();
	
	$("body").on("blur","#businessContractNo",function(){
		var businessContractNo = $.trim($("#businessContractNo").val());
		if(businessContractNo == "")
			return;
		if(businessContractNoGlobal==businessContractNo)
			return;
		cana.post(basepath + "/asset/loan/get/businessInfo",{businessContractNo: businessContractNo},getBusinessInfoSuccess,getBusinessInfoError,null);
	})

	$("body").on("change","#counterpartySelect",function(){
		updateByCounterparty();
	})
	
	$("body").on("blur","#loanPeriod",function(){
		calculateOverdueDate();
	})
	
	$("body").on("change","#dateUnit",function(){
		calculateOverdueDate();
	})
	
	//弹框关闭
	$('body').on('click', 'a[name=tipBoxButton]', function() {
		tipBoxTemplate.close();
	});
	
	// 确认框确认按钮
	$('body').on('click', 'a[name=confirmBoxOK]', function() {
		removeClientRow(deleteCkb);
		if("receivables-tb" == deleteCkb) {
			//计算最高放款金额
			if($("input[name=receivables-tb]:checked").size!=0)
				calculateMaxLoanAmount();
		}
    	confirmPopWindow.close();
    });
	
	loadDataWhenEditLoanPage();
	
});

function loadDataWhenEditLoanPage(){
	if(isEditLoan){
		addInvoiceInfoWhenEditLoanPage(invoiceInfoList);
		updateCreditData(credit);
		addCostInfoWhenEditLoanPage(expenseList);
		generateLoanPlanSuccess(loanPlanList);
	}
}

//修改页面 一开始加载相关的费用
function addCostInfoWhenEditLoanPage(expenseList){
	$.each(expenseList,function(i,v){
		var $idx = $("#cost-tb tr").length;
		var disabledHtml = '';
		if(existPaidInfo)
			disabledHtml = 'disabled="disabled"';
		var $html = $('<tr class="client-add-tr">'
				+ '<th><input type="checkbox" '+ disabledHtml +'class="input-new" name="cost-tb"></th>'
				+ '<th>'
				+ ($idx + 1)
				+ '</th>'
				+ '<td>'
				+ v.expenseSubject
				+ '</td>'
				+ '<td>￥'
				+ v.amountStr
				+ '</td>'
				+ '<td></td></tr>');
		addClientRow("cost-tb",$html);
	})
}

//修改页面 一开始加载相关的应收账款
function addInvoiceInfoWhenEditLoanPage(invoiceInfoList){
	counterpartyTextGlobal　= $("#counterpartySelect option:selected").text();
	//清空已增加的应收账款列表
	$("#receivables-tb tbody").empty();
	$.each(invoiceInfoList,function(i,v){
		var $idx = $("#receivables-tb tr").length;
		var disabledHtml = '';
		if(existPaidInfo)
			disabledHtml = 'disabled="disabled"';
		var $html = $('<tr class="client-add-tr" invoiceId="' + v.id + '">'
				+ '<th><input type="checkbox" ' + disabledHtml + 'class="input-new" name="receivables-tb"></th>'
				+ '<th>'
				+ ($idx + 1)
				+ '</th>'
				+ '<td>'
				+ v.counterparty
				+ '</td>'
				+ '<td>'
				+ v.invoiceNo
				+ '</td>'
				+ '<td>￥'
				+ v.nominvoiceAmt
				+ '</td>'
				+ '<td>￥'
				+ v.invoiceAmt
				+ '</td>'
				+ '<td>'
				+ v.financingRatio + '%'
				+ '</td>'
				+ '<td>'
				+ v.invoiceDate
				+ '</td>'
				+ '<td>'
				+ v.dueDate
				+ '</td>' + '</tr>');
		addClientRow("receivables-tb",$html);
		checkedInvoiceIdGlobal.push(v.id);
	})
	if(invoiceInfoList.length>0)
		invoicePopDataGlobal = invoicePopDataGlobal.concat(invoiceInfoList);
}

//根据融资期限，期限单位，放款日　计算到期日
function calculateOverdueDate(){
	if($("#loanDate").val()=="" || $("#loanPeriod").val()=="")
		return;
	var interval = convertDateUnit($("#dateUnit").val());
	var loanPeriod = $("#loanPeriod").val();
	var loanDate = $("#loanDate").val();
	var date = loanDate.replace(/-/g, "/");
	overdueDateGlobal = calculateDate(interval, loanPeriod, new Date(date));
	if(interval=="y" || interval=="m")
		overdueDateGlobal = DateAdd("d", "-1", new Date(overdueDateGlobal));
	$("input.time-twox").val(new Date(overdueDateGlobal).format("yyyy-MM-dd"));
	$("input.time-twox").trigger("blur");
}

/**
 * 2015-01-31 => 2015-02-01 （加一天）
 * 2015-01-31 => 2015-02-28 （加一个月）
 * 2015-02-28 => 2015-03-31 （加一个月）
 * 2015-01-30 => 2015-02-28 （加一个月）
 * 2015-02-28 => 2016-02-29  (加一年)
 */
function calculateDate(interval, number, date){
	var dateDay = date.getDate();//date的日
	var dateLastDay = getLastDay(new Date(date));//date的最后一天的日
	var dateFirst = new Date(date.getFullYear(),date.getMonth(),1);//date的第一天
	var retDateFirst = DateAdd(interval, number, new Date(dateFirst));//date加减后的日期的第一天
	var retDateLastDay = getLastDay(new Date(retDateFirst));//retDateFirst的最后一天的日
	var retDate;
	
	if(interval!="d" && (date.getDate()==dateLastDay || retDateLastDay<=dateDay)){//(date 是月底) or (retDateFirst的最后一天的日 <= date的日)
		retDate = new Date(new Date(retDateFirst).getFullYear(),new Date(retDateFirst).getMonth(),retDateLastDay);//那么返回的是加减后日期的月底
	}else{
		retDate = DateAdd(interval, number, new Date(date));
	}
	return retDate;
}

//交易对手下拉框的值变更后，联动的数据做变更
function updateByCounterparty(){
	var counterpartyValue = $("#counterpartySelect").val();
	var counterpartyText = $("#counterpartySelect option:selected").text();
	counterpartyTextGlobal　= counterpartyText;
	cana.post(basepath + "/asset/loan/get/unLoanInvoiceList",{businessContractNo : $.trim($("#businessContractNo").val()), counterpartyId : counterpartyValue},getUnInvoiceListSuccess,showErrorBox,null);
}

//根据业务合同号获取数据成功
function getBusinessInfoSuccess(data){
	businessContractNoGlobal = $.trim($("#businessContractNo").val());//给全局变量最新的可用合同号
	$("#loanInfoId").val(data.loanInfoId);
	//交易对手下拉框
	var counterpartyArray = [];//定义一个数组
	$.each(data.factorBusinessInfoDTO.counterpartyDTOList, function(i,v){      
		var counterpartyObject =　{};//定义一个对象
		counterpartyObject.value = v.counterpartyId;
		counterpartyObject.text = v.counterparty;
		counterpartyArray.push(counterpartyObject);
	});
	reInitDropDownList($("#counterpartySelect"),counterpartyArray);
	//根据交易对手下拉框值更新相关数据（比如弹框的应收账款列表）
	updateByCounterparty();
	
	$("#financeCustomerName").val(data.factorBusinessInfoDTO.financeCustomerName);
	$("#projectName").val(data.factorBusinessInfoDTO.projectName);
	$("#businessProduct").val(BusinessProduct[data.factorBusinessInfoDTO.businessProduct]);
	//还款账号下拉框
	var repaymentAccountArray = [];
    repaymentAccountArray.push({value:data.factorBusinessInfoDTO.factoringAccount　,　text:data.factorBusinessInfoDTO.factoringAccount});
    if(data.factorBusinessInfoDTO.factoringAccount != data.factorBusinessInfoDTO.settlementAccount)
    	repaymentAccountArray.push({value:data.factorBusinessInfoDTO.settlementAccount　,　text:data.factorBusinessInfoDTO.settlementAccount});
    
	reInitDropDownList($("#repaymentAccount"),repaymentAccountArray);
	//更新额度列表
	updateCreditData(data.creditDTO);
	//清空费用列表数据
	$("#cost-tb tbody").empty();
	//清空还款计划列表数据
	$("#repayment-tb tbody").empty();
}

//根据业务合同号获取数据失败
function getBusinessInfoError(message){
	$("#loanInfoId").val("");//清空放款编号
	emptySelect($("#counterpartySelect"));//清空交易对手下拉框
	upateNotUsedInvoiceList([]);//清空应收账款两个表以及全局变量
	$('#noInvoiceTip').css('display', 'none');//隐藏“没有可用应收账款”
	counterpartyTextGlobal = "";//应收账款弹框的交易对手名称设为空
	$("#financeCustomerName").val("");//清空融资客户名称
	$("#projectName").val("");//清空项目名称
	$("#businessProduct").val("");//清空业务产品
	emptySelect($("#repaymentAccount"));//清空还款账号下拉框
	$("#limit-tb tbody").empty();//清空额度列表数据
	$("#cost-tb tbody").empty();//清空费用列表数据
	$("#repayment-tb tbody").empty();//清空还款计划列表数据
	showErrorBox(message);
}

//清空下拉框
function emptySelect(obj){
//	var dropDownList = $(obj).data('kendoDropDownList');
//	var dataSource = dropDownList.dataSource;
//	var data = dataSource.data();
//	$.each(data, function(i, n) {
//		dataSource.remove(data[i]);
//	});
	reInitDropDownList($(obj),[]);
	$(obj).siblings().find("span").first().text("");
}

//计算最高放款金额（最高放款金额 = MIN（应收金额1*融资比例1+应收金额2*融资比例2... ，可用额度））
function calculateMaxLoanAmount(){
	var invoiceAmtSum = 0;
	$("#receivables-tb").find("tr").each(function(){
	    var invoiceTdArr = $(this).children();
	    var invoiceAmt = parseFloat(invoiceTdArr.eq(5).text().substr(1).parseMoney());//应收金额
	    var financingRatio = parseFloat(invoiceTdArr.eq(6).text().replace(/%/gm,'')).divide(100).toFixed(5);//融资比例
	    invoiceAmtSum = invoiceAmtSum.add(invoiceAmt.multiply(financingRatio));
	});
	
	var availableLimitSum = 0;
	$("#limit-tb").find("tr").each(function(){
		var limitTdArr = $(this).children();
		var availableLimit = 0;
		if(!isEditLoan)//新增页面，取可用额度
			availableLimit = parseFloat(limitTdArr.eq(4).text().substr(1).parseMoney());
		else{//修改页面
			if(limitTdArr.eq(6).text() == "SINGLE")//单笔授信，取授信额度
				availableLimit = parseFloat(limitTdArr.eq(2).text().substr(1).parseMoney());
			else if(limitTdArr.eq(6).text() == "SYNTHETICAL")//综合授信，取放款融资金额+可用额度
				availableLimit = parseFloat(limitTdArr.eq(4).text().substr(1).parseMoney()).add(loanFinanceAmount);
		}
		availableLimitSum = availableLimitSum.add(availableLimit);
	});
	var maxLoanAmount = invoiceAmtSum<availableLimitSum?invoiceAmtSum : availableLimitSum;
	if($("#receivables-tb").find("tr").size()==0)
		maxLoanAmount = availableLimitSum;
	$("#maxLoanAmount").text(maxLoanAmount.toString().formatMoney());
	if($("#loanAmount").val()!=""){
		$("#loanAmount").focus();
		$("#loanAmount").blur();
	}
}

function showDeleteConfirm(ckb, message) {
	deleteCkb = ckb;
	if ($("input[name=" + ckb + "]:checked").size() == 0) {
		showTipBox("notice-icon01", "请选择要删除的行！");
	} else {
		showConfirmBox(message);
	}
}

//小工具start--------------------------------------------------------

//将“YEAR”转换成"y"
//将“MONTH”转换成"m"
//将“DAY”转换成"d"
function convertDateUnit(dateUnit){
	if(dateUnit=="YEAR")
		return "y";
	else if(dateUnit=="MONTH")
		return "m";
	else if(dateUnit=="DAY")
		return "d";
	else
		return "";
}

//重新初始化下拉框
function reInitDropDownList(obj,data){
	$(obj).kendoDropDownList({
      dataTextField: "text",
      dataValueField: "value",
      dataSource: data,
      index: 0 // 当前默认选中项，索引从0开始。
  });
}

// 表格操作（增加）
function addClientRow(tab, trHtml){
	var $tr = $("#" + tab + "");
	trHtml.appendTo($tr);
}

// 表格操作（判断修改的行，返回修改的行号）
function amendClientRowRead(ckb) {
	var ckbs = $("input[name=" + ckb + "]:checked");
	if (ckbs.size() == 0) {
		showTipBox("notice-icon01", "请选择需要修改的行！");
	} else if (ckbs.size() != 1) {
		showTipBox("notice-icon01", "一次只能修改一行！");
	} else {
		var chTr_index = ckbs.parent().parent().index(); // 用于标记修改的行,仅用于修改操作
		return chTr_index;
	}
}

// 表格操作（删除）
function removeClientRow(ckb) {
	// 获取选中的复选框，然后循环遍历删除
	var ckbs = $("input[name=" + ckb + "]:checked");
	ckbs.each(function() {
		if(ckb=="receivables-tb")//应收账款列表
			checkedInvoiceIdGlobal.splice($.inArray($(this).parent().parent().attr('invoiceid'),checkedInvoiceIdGlobal),1);
		$(this).parent().parent().remove();
	});
	// 删除后更新序号
	if(ckb != "repayment-tb") {
		var len = $("#" + ckb + " tr").length;
		for (var i = 0; i < len; i++) {
			$("#" + ckb + " tr:eq(" + i + ") th:last").html(i + 1);
		}
	}
}

function submitSuccess(data) {
	showTipBox("notice-icon02", "操作成功，3秒后自动跳转到详情页");
	gou(3, basepath + "/asset/loan/goto/loanDetail?id=" + data)
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
//小工具end--------------------------------------------------------
//////////////////////////////////////////////////////////////
//初始化弹框和表格
function initButtonEventAndTable(){
	initLoanValidator();
	
	$("body").on("click","#submitAddLoan",function() {
		if (!loanValidator.validate()) {
			return;
		}
		if(canSubmit) {
			canSubmit = false;
			var postData = {};
			postData.contractNo = $.trim($('#businessContractNo').val());
			postData.loanInfoId = $('#loanInfoId').val();
			postData.counterpartyId = $('#counterpartySelect').val();
			postData.financeAmount = $.trim($("#loanAmount").val().parseMoney());
			postData.currency = $("#currencyType").val();
			postData.repaymentType = $("#repaymentMethod").val();
			postData.loanPeriodUnit = $("#dateUnit").val();
			postData.loanPeriod =　$.trim($("#loanPeriod").val());
			postData.dayCountConvention = $("#interestBaseDays").val();
			postData.interestRateUnit = $("#interestRateUnit").val();
			postData.interestRate =$.trim($("#interest").val());
			postData.loanDate = $.trim($("#loanDate").val());
			postData.dueDate =　$.trim($("#overdueDate").val());
			postData.penaltyRate = $.trim($("#overdueInterest").val());
			postData.repaymentAccountNo = $("#repaymentAccount").val().parseBankAccountNo();
			var invoiceInfoIds = [];
			$('#receivables-tb tr').each(function() {
				invoiceInfoIds.push($(this).attr('invoiceid'));
			});
			postData.invoiceInfoIds  =  invoiceInfoIds;
			var expenses = [];
			$('#cost-tb tr').each(function() {
				expenses.push({subject: $.trim($(this).children('td:eq(0)').text()), amount: $.trim($(this).children('td:eq(1)').text().substr(1)).parseMoney()});
			});
			postData.expenses = expenses;
			var plans = [];
			$('#repayment-tb tr').each(function(index,value) {
				if(index!=0)
					plans.push({financeBalance: $.trim($(this).children('td:eq(0)').text().substr(1)).parseMoney(), valueDate: $(this).children('td:eq(1)').text(), settleInterestDate: $(this).children('td:eq(2)').text(), repaymentDate: $(this).children('td:eq(3)').text(), accountPrincipal: $.trim($(this).children('td:eq(4)').text().substr(1)).parseMoney(), accountInterest:$.trim($(this).children('td:eq(5)').text().substr(1)).parseMoney(), accountOverdue: $.trim($(this).children('td:eq(6)').text().substr(1)).parseMoney(),accountAmount: $.trim($(this).children('td:eq(7)').text().substr(1)).parseMoney(), settleStatus: $(this).children('td:eq(9)').text()});
			});
			postData.plans = plans;
			if(isEditLoan) {
				cana.postJson(basepath + "/asset/loan/updateAssetLoan", postData, submitSuccess, showErrorBox, function(data) {canSubmit = true;$("#submitAddLoan").text('提交');});
			} else {
				cana.postJson(basepath + "/asset/loan/createAssetLoan", postData, submitSuccess, showErrorBox, function(data) {canSubmit = true;$("#submitAddLoan").text('提交');});
			}
			$(this).text('提交中...');
		}
	});
	
	initDatepicker();
	
	initInvoice();
	
	initCharge();
	
	initPlan();
}

//表单校验初始化
function initLoanValidator(){
	setValidator($("#loanPeriod"), "pattern", periodRule.pattern, periodRule.message);
	setValidator($("#loanDate"), "pattern", dateRule.pattern, dateRule.message);
	setValidator($("#overdueDate"), "pattern", dateRule.pattern, dateRule.message);
	loanValidator = $("#addLoanByHandForm").kendoValidator({
		rules: {
//			loanAmountRulePattern: loanAmountRule.rulePattern,
			loanAmountMinRule: loanAmountRule.ruleMin,
			loanAmountMaxRule: loanAmountRule.ruleMax,
			rateRulePattern: rateRule.rulePattern,
			rateMinRule: rateRule.ruleMin,
		},
		messages: {
//			loanAmountRulePattern: loanAmountRule.ruleMessagePattern,
			loanAmountMinRule: loanAmountRule.ruleMinMessage,
			loanAmountMaxRule: loanAmountRule.ruleMaxMessage,
			rateRulePattern: rateRule.ruleMessagePattern,
			rateMinRule: rateRule.ruleMinMessage,
		},
		needRuleAttrbute : false
	}).data("kendoValidator");
}

//初始化日期
function initDatepicker(){
	$(".time-onex").datepicker({
		format : "yyyy-mm-dd",
		language : "zh-CN",
		autoclose : true,
		todayHighlight : true,
		weekStart : 0,
		firstDay : 0,
		onClose : function(selectedDate) {
			if(selectedDate!="")
				$("input.time-twox").datepicker("option","minDate", DateAdd("d", "1", new Date(selectedDate)).format("yyyy-MM-dd"));
			calculateOverdueDate();
		}
	}).on("show", function() {
		$("div.datepicker table thead .prev").html("");
		$("div.datepicker table thead .next").html("");
	});
	$(".time-twox").datepicker({
		format : "yyyy-mm-dd",
		language : "zh-CN",
		autoclose : true,
		todayHighlight : true,
		weekStart : 0,
		firstDay : 0,
		minDate : +1,
		onClose : function(selectedDate) {
			if(selectedDate!="")
				$("input.time-onex").datepicker("option","maxDate", DateAdd("d", "-1", new Date(selectedDate)).format("yyyy-MM-dd"));
		}
	}).on("show", function() {
		$("div.datepicker table thead .prev").html("");
		$("div.datepicker table thead .next").html("");
	});
	
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
					$("input.time-three").datepicker("option","minDate", DateAdd("d", "1", new Date(selectedDate)).format("yyyy-MM-dd"));
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
					$("input.time-three").val(selectedDate);
					$("input.time-three").trigger("blur");
				}
			}
		}).on("show", function() {
			$("div.datepicker table thead .prev").html("");
			$("div.datepicker table thead .next").html("");
		});
		$(".time-three").datepicker({
			format : "yyyy-mm-dd",
			language : "zh-CN",
			autoclose : true,
			todayHighlight : true,
			weekStart : 0,
			firstDay : 0,
			onClose : function(selectedDate) {
				if(selectedDate!=""){
					$("input.time-one").datepicker("option","maxDate", DateAdd("d", "-1", new Date(selectedDate)).format("yyyy-MM-dd"));
					$("input.time-two").val(selectedDate);
					$("input.time-two").trigger("blur");
				}
			}
		}).on("show", function() {
			$("div.datepicker table thead .prev").html("");
			$("div.datepicker table thead .next").html("");
		});
}

//应收账款start--------------------------------------------------------
//表格操作（判断新增的行，返回修改的行号）...新增应收账款
function receivablesPopRowRead(ckb) {
	var ckbs = $("input[name=" + ckb + "]:checked:enabled");
	if (ckbs.size() == 0) {
		showTipBox("notice-icon01", "请选择需要新增的行！");
	} else {
		var trId = [];
		var trInvoiceId = [];
		for (var i = 0; i < ckbs.size(); i++) {
			trId[i] = ckbs.eq(i).parent().parent().index();
			trInvoiceId[i] = ckbs.eq(i).parent().parent().attr('invoiceid');
		}
		checkedInvoiceIdGlobal = checkedInvoiceIdGlobal.concat(trInvoiceId);//追加新增加的应收账款id
		return trId;
	}
}

//更新弹框的应收账款列表数据
function updateInvoicePopData(data){
	//清空弹框里的数据
	$("#receivablesPop-tb tbody").empty();
	//添加进去
	$.each(data, function(i,v){
		var $html = $('<tr class="client-add-tr" invoiceId="' + v.id + '">'
			+ '<th><input type="checkbox" class="input-new" name="receivablesPop-tb"></th>'
			+ '<th>' + (i+1) + '</th>'
			+ '<td>' + v.counterparty + '</td>'
			+ '<td>' + v.invoiceNo + '</td>'
			+ '<td>￥' + v.nominvoiceAmt.formatMoney() + '</td>'
			+ '<td>￥' + v.invoiceAmt.formatMoney() + '</td>'
			+ '<td>' + v.financingRatio + '%' + '</td>'
			+ '<td>' + v.invoiceDate + '</td>'
			+ '<td>' + v.dueDate + '</td>'
			+ '</tr>');
		addClientRow("receivablesPop-tb",$html);
	});
}

//新增应收账款
function initInvoice(){
	// “新增”按钮
	$("body").on("click", ".add-receivables-btn", function() {
		$(".open-receivablesPop").click();
		//弹框的交易对手名称
		$("#counterparty").val(counterpartyTextGlobal);
		
		if(invoicePopDataGlobal!=undefined){
			//初始化弹框中的数据
			updateInvoicePopData(invoicePopDataGlobal);
		}
		
		//给已经增加过了的复选框置灰
		$.each(checkedInvoiceIdGlobal,function(i,v){
			$("#receivablesPop-tb").find("tr").each(function(){
				if($(this).attr('invoiceid')==v){
					$(this).find("input[name='receivablesPop-tb']").prop("checked","checked").prop("disabled","disabled");
				}
			});
		});
	});
	
	//新增应收账款弹框——“确定”按钮
	$("body").on("click","#receivablesPop-btn",function() {
		var $index = receivablesPopRowRead("receivablesPop-tb");
		var invoiceInfoIds = [];
		$('#receivables-tb tr').each(function() {
			invoiceInfoIds.push($(this).attr('invoiceid'));
		});
		if ($index != undefined) {
			$.each($index,function() {
				var $td = $("#receivablesPop-tb tr").eq(this).find("td"), $idx = $("#receivables-tb tr").length;
				var $html = $('<tr class="client-add-tr" invoiceId="' + $("#receivablesPop-tb tr").eq(this).attr('invoiceId') + '">'
						+ '<th><input type="checkbox" class="input-new" name="receivables-tb"></th>'
						+ '<th>'
						+ ($idx + 1)
						+ '</th>'
						+ '<td>'
						+ $td.eq(0).text()
						+ '</td>'
						+ '<td>'
						+ $td.eq(1).text()
						+ '</td>'
						+ '<td>'
						+ $td.eq(2).text()
						+ '</td>'
						+ '<td>'
						+ $td.eq(3).text()
						+ '</td>'
						+ '<td>'
						+ $td.eq(4).text()
						+ '</td>'
						+ '<td>'
						+ $td.eq(5).text()
						+ '</td>'
						+ '<td>'
						+ $td.eq(6).text()
						+ '</td>' + '</tr>');
				addClientRow("receivables-tb",$html);
			});
			$(".dlg-wrapper .close-receivablesPop").click();
		}
		//计算最高放款金额
		calculateMaxLoanAmount();
	});
	
	//删除账款
	$("body").on("click", ".del-receivables-btn", function() {
		showDeleteConfirm("receivables-tb", "确定删除选中的应收账款吗？");
	});
}

function getUnInvoiceListSuccess(data) {
	if(data == null || data.length == 0) {
		$('#noInvoiceTip').css('display', '');
	} else {
		$('#noInvoiceTip').css('display', 'none');
	}
	upateNotUsedInvoiceList(data);
	if(isEditLoan && $("#counterpartySelect").val() == counterpartyId){
		$('#noInvoiceTip').css('display', 'none');
		addInvoiceInfoWhenEditLoanPage(invoiceInfoList);
	}
}

//获取弹框未放过款的应收账款列表成功
function upateNotUsedInvoiceList(data){
	//清空已增加的应收账款列表
	$("#receivables-tb tbody").empty();
	if(data != null)
		invoicePopDataGlobal = data;
	else
		invoicePopDataGlobal = [];
	checkedInvoiceIdGlobal.splice(0,checkedInvoiceIdGlobal.length);//清空变量（已增加的应收账款在弹框中的index数组）
	//计算最高放款金额
	calculateMaxLoanAmount();
}
//应收账款end--------------------------------------------------------

//额度start--------------------------------------------------------
//更新额度列表数据
function updateCreditData(data){
	//清空额度列表数据
	$("#limit-tb tbody").empty();
	//添加进去
	var $html = $('<tr class="client-add-tr">'
		+ '<td>' + data.creditModeDesc + '</td>'
		+ '<td>' + data.currencyDesc + '</td>'
		+ '<td>￥' + data.totalLimitStr.formatMoney() + '</td>'
		+ '<td>￥' + data.usedLimitStr.formatMoney() + '</td>'
		+ '<td>￥' + data.availableLimitStr.formatMoney() + '</td>'
		+ '<td>' + data.dueDate + '</td>'
		+ '<td style="display:none">' + data.creditMode + '</td>'
		+ '</tr>');
	addClientRow("limit-tb",$html);
	//计算最高放款金额
	calculateMaxLoanAmount();
}
//额度end--------------------------------------------------------

//费用start--------------------------------------------------------
//新增/修改费用信息核心操作
function opCost() {
	var $index = $(".cost-ch-id").val(), $ind = $("#cost-tb tr").length, $class = $(
			".co-class").val(), $money = $(".co-money").val().formatMoney(), html = $('<tr class="client-add-tr">'
			+ '<th><input type="checkbox" class="input-new" name="cost-tb"></th>'
			+ '<th>'
			+ ($ind + 1)
			+ '</th>'
			+ '<td>'
			+ $class
			+ '</td>'
			+ '<td>￥'
			+ $money + '</td>' + '<td></td>' + '</tr>');
	$(".dlg-wrapper .close-costPop").click();
	if ($index == '') {
		addClientRow("cost-tb", html);
	} else {
		var $td = $("#cost-tb tr").eq($index).find("td");
		$td.eq(0).html($class);
		$td.eq(1).html("￥" + $money);
	}
}

//初始化费用相关的
function initCharge(){
	// 新增费用信息
	$("body").on("click",".add-cost-btn",function() {
		$(".open-costPop").click();
		PopMoneyNumFormat();
		// 表单验证
		var validator = $("#cost-form").kendoValidator().data("kendoValidator");
		$("#cost-btn").click(function() {
			if (!validator.validate()) {
				return;
			}
			opCost();
		});
		
	});
	// 修改费用信息
	$("body").on("click",".ch-cost-btn",function() {
		var $index = amendClientRowRead("cost-tb");
		if ($index != undefined) {
			$(".open-costPop").click();
			var $td = $("#cost-tb tr").eq($index).find("td");

			$(".co-class").val($td.eq(0).text());
			$(".co-money").val($td.eq(1).text().substr(1));
			$(".cost-ch-id").val($index);

			// 表单验证
			var validator = $("#cost-form").kendoValidator().data(
					"kendoValidator");
			$("#cost-btn").click(function() {
				if (!validator.validate()) {
					return;
				}
				opCost();
			});
		}
	});
	// 删除费用信息
	$("body").on("click", ".del-cost-btn", function() {
		showDeleteConfirm("cost-tb", "确定删除选中的费用信息吗？");
	});
}
//费用end--------------------------------------------------------

//还款计划start--------------------------------------------------------
//生成还款计划第一行（只是展示用）
function generateLoanPlanFirstRow(){
	var $html = $('<tr class="client-add-tr">'
			+ '<th><input type="checkbox" disabled="disabled" class="input-new" name="repayment-tb"></th>'
			+ '<td>￥'
			+ $("#loanAmount").val()
			+ '</td>'
			+ '<td>'
			+ $("#loanDate").val()
			+ '</td>'
			+ '<td>'
			+ '-'
			+ '</td>'
			+ '<td>'
			+ $("#loanDate").val()
			+ '</td>'
			+ '<td>'
			+ '-'
			+ '</td>'
			+ '<td>'
			+ '-'
			+ '</td>'
			+ '<td>'
			+ '-'
			+ '</td>' 
			+ '<td>'
			+ '-'
			+ '</td>'
			+ '<td>'
			+ '-'
			+ '</td>' 
			+ '</tr>');
	addClientRow("repayment-tb",$html);
}

//自动生成还款计划成功
function generateLoanPlanSuccess(data){
	//清空还款计划表
	$("#repayment-tb tbody").empty();
	//生成第一行（只是展示用）
	generateLoanPlanFirstRow();
	$.each(data,function(i,v) {
		if(v.paidOverdue == undefined)
			v.paidOverdue = "0";
		var $html = $('<tr class="client-add-tr">'
				+ '<th><input type="checkbox" class="input-new" ' + ((v.settleStatus == 'UNSETTLE' ? '' : 'disabled="disabled"')) + 'name="repayment-tb"></th>'
				+ '<td>￥'
				+ v.financeBalance.formatMoney()
				+ '</td>'
				+ '<td>'
				+ v.valueDate
				+ '</td>'
				+ '<td>'
				+ v.settleInterestDate
				+ '</td>'
				+ '<td>'
				+ v.repaymentDate
				+ '</td>'
				+ '<td>￥'
				+ v.accountPrincipal.formatMoney()
				+ '</td>'
				+ '<td>￥'
				+ v.accountInterest.formatMoney()
				+ '</td>'
				+ '<td>￥'
				+ v.accountOverdue.formatMoney()
				+ '</td>'
				+ '<td>￥'
				+ v.accountAmount.formatMoney()
				+ '</td>' 
				+ '<td>'
				+ SettleStatus[v.settleStatus]
				+ '</td>'
				+ '<td style="display:none">'
				+ v.settleStatus
				+ '</td>'
				+ '<td style="display:none">'
				+ v.paidOverdue.formatMoney()
				+ '</td>'
				+ '<td style="display:none">'
				+ v.existPaidInfo
				+ '</td>'
				+ '</tr>');
		addClientRow("repayment-tb",$html);
	})
}

//新增/修改还款信息核心操作 repayment-balance
function opAppoint() {
	var $index = $(".repayment-id").val(), $balance = $(".repayment-balance")
			.val(), $valueDate = $(".repayment-valueDate").val(), $expiryDate = $(
			".repayment-expiryDate").val(), $date = $(".repayment-date").val(), $should = $(
			"#accountPrincipal").val(), $interest = $("#accountInterest")
			.val(), $overdueInterest = $("#accountOverdue").val(), $all = $("#accountAmount").val(),
			$settleStatus = $("#settleStatus").val(),$settleStatusDesc = $("#settleStatus option:selected").text();
			html = $('<tr class="client-add-tr">'
			+ '<th><input type="checkbox" class="input-new" name="repayment-tb"></th>'
			+ '<td>￥'
			+ $balance
			+ '</td>'
			+ '<td>'
			+ $valueDate
			+ '</td>'
			+ '<td>'
			+ $expiryDate
			+ '</td>'
			+ '<td>'
			+ $date
			+ '</td>'
			+ '<td>￥'
			+ $should
			+ '</td>'
			+ '<td>￥'
			+ $interest
			+ '</td>'
			+ '<td>￥'
			+ $overdueInterest
			+ '</td>'
			+ '<td>￥' 
			+ $all 
			+ '</td>' 
			+ '<td>' 
			+ $settleStatusDesc
			+ '</td>' 
			+ '<td style="display:none">' 
			+ $settleStatus
			+ '</td>' 
			+ '<td style="display:none">'
			+ '0.00'
			+ '</td>'
			+ '<td style="display:none">'
			+ 'false'
			+ '</td>'
			+ '</tr>');
	$(".dlg-wrapper .close-repaymentPop").click();
	if ($index == '') {
		if($("#repayment-tb").find("tr").size()==0)
			generateLoanPlanFirstRow();
		addClientRow("repayment-tb", html);
	} else {
		var $td = $("#repayment-tb tr").eq($index).find("td");
		$td.eq(0).html("￥" + $balance);
		$td.eq(1).html($valueDate);
		$td.eq(2).html($expiryDate);
		$td.eq(3).html($date);
		$td.eq(4).html("￥" + $should);
		$td.eq(5).html("￥" + $interest);
		$td.eq(6).html("￥" + $overdueInterest);
		$td.eq(7).html("￥" + $all);
		$td.eq(8).html($settleStatusDesc);
		$td.eq(9).html($settleStatus);
	}
}

//初始化还款计划相关的
function initPlan(){
	//“自动生成”按钮
	$("body").on("click","#auto-generate-repayment-btn",function(){
		var financeAmount = $("#loanAmount").val().parseMoney();
		if(financeAmount == ""){
			showTipBox("notice-icon01", "融资金额不能为空");
			return;
		}
		var repaymentType = $("#repaymentMethod").val();
		var dayCountConvention = $("#interestBaseDays").val(); 
		if(dayCountConvention == ""){
			showTipBox("notice-icon01", "计息基准天数不能为空");
			return;
		}
		var interestRateUnit = $("#interestRateUnit").val(); 
		var interestRate = $("#interest").val(); 
		if(interestRate == ""){
			showTipBox("notice-icon01", "利率不能为空");
			return;
		}
		var loanDate = $("#loanDate").val(); 
		if(loanDate == ""){
			showTipBox("notice-icon01", "放款日不能为空");
			return;
		}
		var dueDate = $("#overdueDate").val(); 
		if(dueDate == ""){
			showTipBox("notice-icon01", "到期日不能为空");
			return;
		}
		cana.post(basepath + "/asset/loan/generateLoanPlan",{financeAmount:financeAmount,repaymentType:repaymentType,dayCountConvention:dayCountConvention,interestRateUnit:interestRateUnit,interestRate:interestRate,loanDate:loanDate,dueDate:dueDate},generateLoanPlanSuccess,showErrorBox,null);
	});
	
	// 新增还款信息
	$("body").on("click",".add-repayment-btn",function() {
		$(".open-repaymentPop").click();
		initInput();
	});
	
	// 修改还款信息
	$("body").on("click",".ch-repayment-btn",function() {
		var $index = amendClientRowRead("repayment-tb");
		if ($index != undefined) {
			var $td = $("#repayment-tb tr").eq($index).find("td");
//			if(!$td.eq(12).text()){
//				showTipBox("notice-icon01",  "该还款计划已结清，不能修改");
//				return;
//			}
			$(".open-repaymentPop").click();
			initInput();
			//都是未结清的
			//“新增放款"页面　or "修改放款"页面，未还过款　＝> 都可以修改
			//"修改放款"页面 有过还款 ＝>　只可以修改”逾期费“和”结清状态“
			var existPaidInfo = $td.eq(11).text();
			if(isEditLoan && existPaidInfo == "true"){
				$(".repayment-balance").prop("disabled","disabled");
				$(".repayment-valueDate").prop("disabled","disabled");
				$(".repayment-expiryDate").prop("disabled","disabled");
				$(".repayment-date").prop("disabled","disabled");
				$("#accountPrincipal").prop("disabled","disabled");
				$("#accountInterest").prop("disabled","disabled");
				$("#accountAmount").prop("disabled","disabled");
			}　
			$(".repayment-id").val($index);
			$(".repayment-balance").val($td.eq(0).text().substr(1));
			$(".repayment-valueDate").val($td.eq(1).text());
			$(".repayment-expiryDate").val($td.eq(2).text());
			$(".repayment-date").val($td.eq(3).text());
			$("#accountPrincipal").val($td.eq(4).text().substr(1));
			$("#accountInterest").val($td.eq(5).text().substr(1));
			$("#accountOverdue").val($td.eq(6).text().substr(1));
			$("#accountAmount").val($td.eq(7).text().substr(1));
			$("#minOverdueInterest").text($td.eq(10).text())
			var settleStatus = $("#settleStatus").data("kendoDropDownList");
			settleStatus.select($("#settleStatus option[value="+$td.eq(9).text()+"]").index());
		}
	});
	// 删除还款信息
	$("body").on("click", ".del-repayment-btn", function() {
		showDeleteConfirm("repayment-tb", "确定删除选中的还款计划？");
	});
	
	// 还款计划弹框的“应收本金”，“应收利息”，“逾期费”失去焦点后计算“应还总金额”
	$("body").on("blur","#accountPrincipal,#accountInterest,#accountOverdue",function(){
		var should = $("#accountPrincipal").val();
		var interest = $("#accountInterest").val();
		var overdueInterest = $("#accountOverdue").val();
		if(should == "" || parseFloat(should.parseMoney()) < 0)
			should = "0.00";
		if(interest == "" || parseFloat(interest.parseMoney()) < 0)
			interest = "0.00";
		if(overdueInterest == "" || parseFloat(overdueInterest.parseMoney()) < 0)
			overdueInterest = "0.00";
		var all = parseFloat(should.parseMoney()).add(parseFloat(interest.parseMoney())).add(parseFloat(overdueInterest.parseMoney()))+"";
		$("#accountAmount").val(all.formatMoney());
	})
	
	//初始化还款计划弹框
	function initInput() {
		PopMoneyNumFormat();
		initDatepicker();
		// 表单验证
		setValidator($("#repayment-valueDate"), "pattern", dateRule.pattern, dateRule.message);
		setValidator($("#repayment-expiryDate"), "pattern", dateRule.pattern, dateRule.message);
		setValidator($("#repayment-date"), "pattern", dateRule.pattern, dateRule.message);
		var validator = $("#repayment-form").kendoValidator({
			rules: {
				amountMinRule: amountRule.ruleMin,
				amountMaxRule: amountRule.ruleMax,
			},
			messages: {
				amountMinRule: amountRule.ruleMinMessage,
				amountMaxRule: amountRule.ruleMaxMessage,
			},
			needRuleAttrbute : false
		}).data("kendoValidator");
		$("#repayment-btn").click(function() {
			if (!validator.validate()) {
				return;
			}
			opAppoint();
		});
	}
	
}
//还款计划end--------------------------------------------------------