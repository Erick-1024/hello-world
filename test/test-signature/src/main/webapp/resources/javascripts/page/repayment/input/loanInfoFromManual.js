var PAGESIZE = 10;
var loanNo;
var validator;
var accountNoList;

function isValueExist(value, url) {
	var exists = true;
	$.ajax({
		url: basepath + "/loanInfo/input/" + url,
		async: false,
		type: 'post',
		data:{
			value: value
		},
		success: function(data) {
			if("SUCCESS" == data.status){
				exists = !data.data;
			}else{
				showAlertWin(data.message);
				exists = true;
			}
		}
	});
	return exists;
};

function removeVal() {
	$("#failMessage").css("display", "none");
	$("#loanNo").val("");
	$("#businessContractNo").val("");
	$("#financeCompany").val("");
	$("#coreCompanyName").val("");
	$("#voucherNo").val("");
	$("#businessProduct").val("");
	$("#receivablesAmount").val("");
	$("#receivablesBalance").val("");
	$("#financeAmount").val("");
	$("#financeBalance").val("");
	$("#interestRate").val("");
	$("#loanPeriod").val("");
	$("#loanDate").val("");
	$("#dueDate").text("");
	$("#accountNoTd .k-input").text("");
	$("#currency").data("kendoDropDownList").value("");
	$("#loanPeriodUnit").data("kendoDropDownList").value("");
	$("#interestRateUnit").data("kendoDropDownList").value("");
	$("#repaymentMethod").data("kendoDropDownList").value("");
	$(".k-invalid-msg").attr("style", "display:none");
	$(".k-invalid-msg").prev().removeClass("k-invalid");
	$(".k-invalid-msg").prev().attr("aria-invalid", "");
}

function newValidator() {
	setValidator($("#businessContractNo"), "required", true, businessContractNoRule.required);
	setValidator($("#financeCompany"), "required", true, financeCompanyRule.required);
	setValidator($("#businessProduct"), "required", true, businessProductRule.required);
	setValidator($("#receivablesAmount"), "pattern", receivablesAmountRule.pattern, receivablesAmountRule.message);
	setValidator($("#receivablesBalance"), "pattern", receivablesBalanceRule.pattern, receivablesBalanceRule.message);
	setValidator($("#financeAmount"), "required", true, financeAmountRule.required);
	setValidator($("#financeAmount"), "pattern", financeAmountRule.pattern, financeAmountRule.message);
	setValidator($("#financeBalance"), "required", true, financeBalanceRule.required);
	setValidator($("#financeBalance"), "pattern", financeBalanceRule.pattern, financeBalanceRule.message);
	setValidator($("#interestRate"), "required", true, interestRateRule.required);
	setValidator($("#interestRate"), "pattern", interestRateRule.pattern, interestRateRule.message);
	setValidator($("#loanPeriod"), "required", true, loanPeriodRule.required);
	setValidator($("#loanPeriod"), "pattern", loanPeriodRule.pattern, loanPeriodRule.message);
	setValidator($("#loanDate"), "required", true, loanDateRule.required);
	setValidator($("#loanNo"), "required", true, loanNoRule.required);
	
	validator = $("#loanInfoAdd-form").kendoValidator({
		rules: {
			currencyRule: currencyRule.rule,
			receivablesAmountRule: receivablesAmountRule.rule,
			receivablesAmountFormateRule: receivablesAmountRule.formateRule,
			receivablesBalanceRule: receivablesBalanceRule.rule,
			receivablesBalanceFormateRule: receivablesBalanceRule.formateRule,
			financeAmountRule: financeAmountRule.rule,
			financeAmountFormateRule: financeAmountRule.formateRule,
			financeBalanceRule: financeBalanceRule.rule,
			financeBalanceFormateRule: financeBalanceRule.formateRule,
			loanNoRule:loanNoRule.rule,
			interestRateFormateRule: interestRateRule.formateRule
		},
		messages: {
			currencyRule: currencyRule.ruleMessage,
			receivablesAmountRule: receivablesAmountRule.ruleMessage,
			receivablesAmountFormateRule: receivablesAmountRule.formateRuleMessage,
			receivablesBalanceRule: receivablesBalanceRule.ruleMessage,
			receivablesBalanceFormateRule: receivablesBalanceRule.formateRuleMessage,
			financeAmountRule: financeAmountRule.ruleMessage,
			financeAmountFormateRule: financeAmountRule.formateRuleMessage,
			financeBalanceRule: financeBalanceRule.ruleMessage,
			financeBalanceFormateRule: financeBalanceRule.formateRuleMessage,
			loanNoRule:loanNoRule.ruleMessage,
			interestRateFormateRule: interestRateRule.formateRuleMessage
		},
		needRuleAttrbute : false
	}).data("kendoValidator");
}

function initAccountNoDropDownList() {
	var accountNoDataSource = new kendo.data.DataSource({
    	transport: {
            read: {
                dataType: "json",
                type: "post",
                url: basepath + "/loanInfo/input/queryRepaymentAccounts"
            }
        },
        schema:{
        	data: "data"
        }
	});
	
	$("#accountNo").kendoDropDownList({
		autoBind: false,
		dataTextField: "accountNo",
        dataValueField: "accountSupervisionId",
        dataSource: accountNoDataSource
	});
};

function accountNoDropDownListChange() {
	$("body").on("blur", "#financeCompany", function() {
		if("" != $("#financeCompany").val()){
			accountNoList = $("#accountNo").data("kendoDropDownList")
			accountNoList.enable(false);
			accountNoList.dataSource.read({
				financeCompany: $("#financeCompany").val()
			});
			accountNoList.enable(true);
		}else{
			return;
		}
	});
}

function addLoanInfo() {
    //新增行信息
	$("body").on("click", ".form-add-link", function() {
    	$(".add-button").removeClass("hidden");
    	$(".edit-button").addClass("hidden");
        $(".window-overlay").removeClass("hidden");
        $(".template-manualAdd").removeClass("hidden");
	});
	
	
    $("body").on("click", "#confirmAdd", function() {
		if(!validator.validate()) {
			return;
		};
		$.post(
			basepath + "/loanInfo/input/singleSave?key=" + location.search.substr(location.search.indexOf("=")+1),
			{
				loanNo: $("#loanNo").val(),
				businessContractNo: $("#businessContractNo").val(),
				currency: $("#currency").val(),
				financeCompany: $("#financeCompany").val(),
				coreCompanyName: $("#coreCompanyName").val(),
				voucherNo: $("#voucherNo").val(),
				businessProduct: $("#businessProduct").val(),
				receivablesAmount: ($("#receivablesAmount").val()==""?"0.00":$("#receivablesAmount").val()).parseMoney(),
				receivablesBalance: ($("#receivablesBalance").val()==""?"0.00":$("#receivablesAmount").val()).parseMoney(),
				financeAmount: $("#financeAmount").val().parseMoney(),
				financeBalance: $("#financeBalance").val().parseMoney(),
				interestRateUnit: $("#interestRateUnit").val(),
				interestRate: $("#interestRate").val() + "%",
				loanDate: $("#loanDate").val(),
				loanPeriodUnit: $("#loanPeriodUnit").val(),
				loanPeriod: $("#loanPeriod").val(),
				dueDate: $("#dueDate").text(),
				repaymentMethod: $("#repaymentMethod").val(),
				accountNo: $("#accountNo option:selected").text(),
				accountSupervisionId: $("#accountNo").val()
			},
			function(data){
				if(data.status == "SUCCESS"){
					searchLoanInfo();
					$(".window-overlay").addClass("hidden");
			        $(".template-manualAdd").addClass("hidden");
			        removeVal();
				}else{
					$("#failMessage").text(data.message);
					$("#failMessage").css("display", "");
				}
			}
		);
	});
}

function editLoanInfo() {
	   //编辑行信息
    $("body").on("click", ".manualGrid .editManual", function(e){
		$.post(
				basepath + "/loanInfo/input/queryOne?key=" + location.search.substr(location.search.indexOf("=")+1),
				{
					loanNo: $(this).data("loanno")
				},
				function(data) {
        			if(data.status == "SUCCESS"){
    					$("#loanNo").val(data.data.loanNo);
    					$("#businessContractNo").val(data.data.businessContractNo);
    					$("#financeCompany").val(data.data.financeCompany);
    					$("#financeCompany").blur();
    					$("#coreCompanyName").val(data.data.coreCompanyName);
    					$("#voucherNo").val(data.data.voucherNo);
    					$("#businessProduct").val(data.data.businessProduct);
    					$("#receivablesAmount").val(data.data.receivablesAmount.formatMoney());
    					$("#receivablesBalance").val(data.data.receivablesBalance.formatMoney());
    					$("#financeAmount").val(data.data.financeAmount.formatMoney());
    					$("#financeBalance").val(data.data.financeBalance.formatMoney());
    					$("#interestRate").val(data.data.interestRate.formatMoney());
    					$("#loanPeriod").val(data.data.loanPeriod);
    					$("#loanDate").val(data.data.loanDate);
    					$("#dueDate").text(data.data.dueDate);
    					$("#currency").data("kendoDropDownList").value(data.data.currency);
    					$("#loanPeriodUnit").data("kendoDropDownList").value(data.data.loanPeriodUnit);
    					$("#interestRateUnit").data("kendoDropDownList").value(data.data.interestRateUnit);
    					$("#repaymentMethod").data("kendoDropDownList").value(data.data.repaymentMethod);
    					$("#accountNo").data("kendoDropDownList").value(data.data.accountSupervisionId);
    			    	$(".add-button").addClass("hidden");
    			    	$(".edit-button").removeClass("hidden");
    			        $(".window-overlay").removeClass("hidden");
    			        $(".template-manualAdd").removeClass("hidden");
        			}else{
        				showAlertWin(data.message);
        			}
				}
			);
    });
    
    $("body").on("click", "#confirmEdit", function() {
		if(!validator.validate()) {
			return;
		};
		$.post(
			basepath + "/loanInfo/input/modify?key=" + location.search.substr(location.search.indexOf("=")+1),
			{
				loanNo: $("#loanNo").val(),
				businessContractNo: $("#businessContractNo").val(),
				currency: $("#currency").val(),
				financeCompany: $("#financeCompany").val(),
				coreCompanyName: $("#coreCompanyName").val(),
				voucherNo: $("#voucherNo").val(),
				businessProduct: $("#businessProduct").val(),
				receivablesAmount: $("#receivablesAmount").val().parseMoney(),
				receivablesBalance: $("#receivablesBalance").val().parseMoney(),
				financeAmount: $("#financeAmount").val().parseMoney(),
				financeBalance: $("#financeBalance").val().parseMoney(),
				interestRate: $("#interestRate").val() + "%",
				interestRateUnit: $("#interestRateUnit").val(),
				loanDate: $("#loanDate").val(),
				loanPeriodUnit: $("#loanPeriodUnit").val(),
				loanPeriod: $("#loanPeriod").val(),
				dueDate: $("#dueDate").text(),
				repaymentMethod: $("#repaymentMethod").val(),
				accountNo: $("#accountNo option:selected").text(),
				accountSupervisionId: $("#accountNo").val()
			},
			function(data) {
				if(data.status == "SUCCESS"){
					searchLoanInfo();
					$(".window-overlay").addClass("hidden");
			        $(".template-manualAdd").addClass("hidden");
			        removeVal();
				}else{
					$("#failMessage").text(data.message);
					$("#failMessage").css("display", "");
				}
			}
		);
	});
}

function deleteLoanInfo() {
    //删除行
    $("body").on("click", ".manualGrid .delManual", function(){
    	loanNo = $(this).data("loanno");
    });
    
    //确认删除提示弹窗
    new PopWindow(".manualGrid .delManual", {
        title: "确认删除",
        width: 420,
        reload: true,
        template: "#template-del"
    }).init();
    
    $("body").on("click", "#comfirmDel", function(e){
        $.post(
            	basepath + "/loanInfo/input/delete?key=" + location.search.substr(location.search.indexOf("=")+1),
            	{
            		loanNo: loanNo
            	},
            	function(data) {
            		var win = $(e.target).closest(".k-window");
            		var overlay = win.prev(".k-overlay");
            		if(win.is(":visible")){
            			win.css("display", "none");
            			overlay.css("display", "none");
            		}
    				if(data.status == "SUCCESS"){
    					searchLoanInfo();
    				}else{
    					showAlertWin(data.message);
    				}
    			
    			}
            );
    });
}

function closeDlg() {
    //关闭弹窗
	$("body").on("click", ".closeHref, .back-link", function() {
        $(".window-overlay").addClass("hidden");
        $(".template-manualAdd").addClass("hidden");
        removeVal();
	});
    
    //关闭弹窗
    $("body").on("click", ".dlg-wrapper .back-link", function(e){
        var win = $(e.target).closest(".k-window");
        var overlay = win.prev(".k-overlay");
        if(win.is(":visible")){
            win.css("display", "none");
            overlay.css("display", "none");
        }
    });
}

function saveLoanInfo() {
    //保存数据
    $("body").on("click", "#confirmSave", function(){
    	$.get(
    			basepath + "/loanInfo/input/save?key=" + location.search.substr(location.search.indexOf("=")+1),
    			function(data) {
    				if(data.status == "SUCCESS"){
    					location.href = basepath + "/loanInfo/input/showSuccess?key=" + location.search.substr(location.search.indexOf("=")+1)
    				}else if (data.status == "WARNING") {
    					showAlertWin(data.message);
					}else showAlertWin(data.message);
				});
    });
}

function bindEvent() {
	$("body").on("change", "#loanDate", function() {
		var dueDate = computeDueDate(new Date($("#loanDate").val()), $("#loanPeriodUnit").val(), $("#loanPeriod").val() == "" ? 0 : parseInt($("#loanPeriod").val()));
		$("#dueDate").text(dueDate.format("yyyy-MM-dd"));
	});
	
	$("body").on("change", "#loanPeriodUnit", function() {
		if($("#loanPeriod").val() != ""){
			var dueDate = computeDueDate(new Date($("#loanDate").val()), $("#loanPeriodUnit").val(), $("#loanPeriod").val() == "" ? 0 : parseInt($("#loanPeriod").val()));
			$("#dueDate").text(dueDate.format("yyyy-MM-dd") == "NaN-aN-aN" ? "" : dueDate.format("yyyy-MM-dd"));
		}else {
			$("#dueDate").text("");
		}
	});
	
	$("body").on("change", "#loanPeriod", function() {
		if($("#loanPeriod").val() != ""){
			var dueDate = computeDueDate(new Date($("#loanDate").val()), $("#loanPeriodUnit").val(), $("#loanPeriod").val() == "" ? 0 : parseInt($("#loanPeriod").val()));
			$("#dueDate").text(dueDate.format("yyyy-MM-dd") == "NaN-aN-aN" ? "" : dueDate.format("yyyy-MM-dd"));
		}else {
			$("#dueDate").text("");
		}
	});
}

function searchLoanInfo() {
	$(".manualGrid").remove();
	$(".manualGridWrap").append("<div class=\"manualGrid\"></div>");
	$(".manualGrid").kendoGrid({
		selectable: "row",  //设置可选择数据行
		dataSource:{
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						key : location.search.substr(location.search.indexOf("=")+1)
					},
					url: basepath + "/loanInfo/input/query/success"
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
	  					$(".manualGridWrap .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$(".manualGridWrap .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
			}
		},
        columns: [{
            field: "loanNo",
            title: "放款编号",
            width: 150
        },{
            field: "businessContractNo",
            title: "业务合同号",
            width: 200
        },{
            field: "financeCompany",
            title: "融资客户",
            width: 300
        },{
            field: "coreCompanyName",
            title: "核心企业",
            width: 300
        },{
            field: "voucherNo",
            title: "凭证号码",
            width: 150
        },{
            field: "currency",
            title: "币别",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "businessProduct",
            title: "业务产品",
            width: 150
        },{
            title: "应收账款金额",
            width: 120,
            template: function(data) {
//				var value = data.receivablesAmount;
//				if(-1 == value.indexOf(".")){
//					value += ".00";
//				}else if(2 == value.length - value.indexOf(".")){
//					value += "0";
//				}
				return data.receivablesAmount.formatMoney();
			},
            attributes: {
                style: "text-align: right"
            }
        },{
            title: "应收账款余额",
            width: 120,
            template: function(data) {
//				var value = data.receivablesBalance;
//				if(-1 == value.indexOf(".")){
//					value += ".00";
//				}else if(2 == value.length - value.indexOf(".")){
//					value += "0";
//				}
				return data.receivablesBalance.formatMoney();
			},
            attributes: {
                style: "text-align: right"
            }
        },{
            title: "融资金额",
            width: 120,
            template: function(data) {
//				var value = data.financeAmount;
//				if(-1 == value.indexOf(".")){
//					value += ".00";
//				}else if(2 == value.length - value.indexOf(".")){
//					value += "0";
//				}
				return data.financeAmount.formatMoney();
			},
            attributes: {
                style: "text-align: right"
            }
        },{
            title: "融资余额",
            width: 120,
            template: function(data) {
//				var value = data.financeBalance;
//				if(-1 == value.indexOf(".")){
//					value += ".00";
//				}else if(2 == value.length - value.indexOf(".")){
//					value += "0";
//				}
				return data.financeBalance.formatMoney();
			},
            attributes: {
                style: "text-align: right"
            }
        },{
        	field: "interestRateUnit",
            title: "费率单位",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            title: "费率",
            width: 120,
            template: function(data) {
				var value = data.interestRate;
				if(-1 == value.indexOf("%")){
					return value + "%";
				}else return value;
			},
            attributes: {
                style: "text-align: right"
            }
        },{
            title: "还款账号",
            width: 180,
            template : function(data) {
            	if(null== data.accountNo)
            		return "";
            	return data.accountNo.formatBankAccountNo();
            },
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "loanDate",
            title: "放款日",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "dueDate",
            title: "到期日",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "loanPeriodUnit",
            title: "期限单位",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "loanPeriod",
            title: "贷款期限",
            width: 100
        },{
            field: "repaymentMethod",
            title: "还款方式",
            width: 130
        },{
            title: "操作",
            width: 150,
            template: function (data) {
                return "<a class='comRow-link editManual' href='javascript:void(0);' data-loanNo=" + data.loanNo + ">编辑</a>" +
                		"<a class='comRow-link delManual' href='javascript:void(0);' data-loanNo=" + data.loanNo + ">删除</a>";
            }
        }],
        pageable: {
			pageSizes: false,  //设置每页显示行数
			pageSize: PAGESIZE,
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
};

$(function(){
    newValidator();
    
    initAccountNoDropDownList();
    
    accountNoDropDownListChange();
    
    addLoanInfo();
    
    editLoanInfo();
    
    deleteLoanInfo();
    
    closeDlg();
    
    saveLoanInfo();
    
	bindEvent();

	searchLoanInfo();
	
	$(".main-header a,.redirect").click(function(e){
		e.preventDefault();
    	var url = $(this).attr("href");
    	$.ajax({
			type: "POST",
			url: basepath + "/loanInfo/input/countCorrectData?key=" + location.search.substr(location.search.indexOf("=")+1),
			dataType: "json",
			success: function(data){
				if (data.status == "WARNING") {
					window.location.href = url;
				}else{
					$(".template-leave").click();
			    	$("body").on("click", "#comfirmLeave",function(){
			    		window.location.href = url;
			    	});
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
});

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

function chkfix(){
    var clientHeight = $(window).height();
    var offsetTop = $("#foot-fixBar").offset().top;

    if(offsetTop >= clientHeight){
        $("#foot-fixBar").addClass("foot-fixBar");
    }else{
        $("#foot-fixBar").removeClass("foot-fixBar");
    }
};
