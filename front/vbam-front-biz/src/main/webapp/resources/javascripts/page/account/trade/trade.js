var PAGESIZE = 5;
var withdrawFundStartTime = "09:00:00";
var withdrawFundEndTime = "17:00:00";
$(function(){
	var isWithdrawFundByTime = checkIsWithdrawFundByTime(withdrawFundStartTime,withdrawFundEndTime);
	
	cana.post(basepath + "/account/trade/checkSetPayPassword", {}, function(data) {
		if (data.isSetPayPassword == "false") {
			popWindow.open().center();
			$("#tip-box-window .dlg-notice").addClass("notice-icon01");
			$("#tip-box-window .notice-content").text("您尚未设置支付密码，请在“企业信息->账户信息”中设置！");
		}
	});
	
	var tradeType = $('#accountTradeType').val();
	// 初始化确认操作弹窗
	var pop = new PopWindow(".xxxxxxxxxxxx", {
		title : "提示",
		width : 420,
		reload : true,
		template : "#template-withdrawNotice"
	}).init();
	
	//初始化操作弹窗
	var popWindow = new PopWindow({
		title: "提示",
		width: 420,
		reload: true,
		template: "#tipBox_template"
	}).init();
	
	addValidator('#payPassword',payPasswordRule);
	addValidator('#receiveAccountName',accountNameRule);
	addValidator('#verifyCode',verifyCodeRule);
	var validator = $('#trade-form').kendoValidator({
		rules:{
//			receiveBankName:function(input){
//				if(input[0]==$('#receiveBankName')[0]){
//					return cana.postSync('checkReceiveAccountName',{'receiveAccountName':input.val()},function(data){
//					},function(data){
//						input.attr('data-receiveBankName-msg',data);
//					});
//				}
//				return true;
//			},
			transferFundRule: function (input) {
				if(input[0]==$('#amount')[0]){
					return checkTransferFund(input);
				}
				return true;
            },
            receiveAccountNoRule : function(input) {
            	if(input[0]==$('#receiveAccountNo')[0]){
            		var accountNo = input.val().parseBankAccountNo();
            		if(accountNo == ''){
            			input.attr('data-receiveAccountNoRule-msg','账号不能为空');
            			return false;
            		}
            		if(tradeType == 'WITHDRAW_FUND'){
//            			if(accountNo.length > 19){
//                			input.attr('data-receiveAccountNoRule-msg','账号必须为小于19位');
//                			return false;
//                		}
//            			var luhn = cana.bank.luhn(accountNo);
//    					if(luhn == false){
//    						input.attr('data-receiveAccountNoRule-msg','收款账号不存在');
//    						return luhn;
//    					}
            		}else if (tradeType == 'TRANSFER_FUND'){
//            			if(accountNo.length != 19){
//                			input.attr('data-receiveAccountNoRule-msg','账号必须为19位');
//                			return false;
//                		}
            			if(accountNo == $('#accountNo').val().parseBankAccountNo()){
						input.attr('data-receiveAccountNoRule-msg','转出账号和收款账号不能相同');
						return false;
            			}
            		}
				}
				return true;
            },
            accountNoRule : function(input){
            	if(input[0]==$('#accountNo')[0] && $(".trade-wrap").is(":hidden")){
            		var accountNo = input.val().parseBankAccountNo();
            		if(accountNo == ''){
            			input.attr('data-accountNoRule-msg','账号不能为空');
            			return false;
            		}
            		if(accountNo.length != 19){
            			input.attr('data-accountNoRule-msg','账号必须为19位');
            			return false;
            		}
        			$('.accountName').text('正在拉取账号数据');
        			return cana.postSync('checkAccountTradeAble',{'accountNo':accountNo,'tradeType':tradeType},function(data){
        				$('.accountName').text(data.accountName);
        				var template = kendo.template($('#template-account-info').html());
        				$('.account-info').empty().append(template(data));
        				$('#sendAccountNo').text($('#sendAccountNo').text().formatBankAccountNo());
        			},function(data){
        				$('.accountName').text('拉取账号数据失败');
        				input.attr('data-accountNoRule-msg',data);
        			});
            	}
            	return true;
            },
//            receiveAccountNameRule : function(input){
//            	if(input[0]==$('#receiveAccountName').val() && tradeType == 'TRANSFER_FUND'){
//            		return cana.postSync('checkReceiveAccountAndName',{'receiveAccountNo':$('#receiveAccountNo').val().parseBankAccountNo(),'receiveAccountName':input.val()},
//            		  function(data){
//        			},function(data){
//        				input.attr('data-receiveAccountNameRule-msg',data);
//        			});
//            	}
//            	return true;
//            }
		},
		messages:{
		},
		needRuleAttrbute : false,
	}).data("kendoValidator");
	
	$(".next-link").on('click',function() {
		if($(this).hasClass("withdraw-button") && isWithdrawFundByTime==false)
			return;
		if(validator.validateInput('#accountNo')){
			clickNextLink();
		}
	});

	//点击"刷新" --账户余额
	$("body").on("click", ".accountBalanceButton", function(e){
		getAccountBalance();
	});
	
	// 关闭弹窗
	$("body").on("click", ".dlg-wrapper .back-link", function(e) {
		var win = $(e.target).closest(".k-window");
		var overlay = win.prev(".k-overlay");
		if (win.is(":visible")) {
			win.css("display", "none");
			overlay.css("display", "none");
		}
	});
	
	$("body").on('change', '#province', function(){
		cityLoad();
	});

	$("body").on('change', '#receiveBankName', function(){
		branchLoad();
		var amount = $.trim($("#amount").val());
		var bank = $.trim($("#receiveBankName").val());
		if(null != amount && "" != amount){
			calculateCommissionCharge(amount, bank);
			checkTransferFund($("#amount"));
		}
	});

	// 加载城市列表
	initCityDropDownList();
	
	// 加载支行名称
	initBranchDropDownList();
	
	// 返回
	$('body').on('click','.back',function(){
		if (!$(".trade-wrap").is(":hidden")) {
			$(".accountList-wrap").show();
			$(".trade-wrap").hide();
		}
	});
	// 提交
	var submit_lock = false; //限制重复提交
	$('body').on('click','.submit',function(){
		if(validator.validate() && !submit_lock ){
			pop.center().open();
		}
	});
	// 取消
	$('body').on('click','.cancel',function(){
		pop.close();
	});
	// 确认
	$('body').on('click','.confirm',function(){
		pop.close();
		var form = $('#trade-form').serializeArray();
		var params = {};
		for(var i in form){
			if(form[i].name == 'accountNo') {
				params.accountNo = form[i].value.parseBankAccountNo();
			}else if(form[i].name == 'receiveAccountNo') {
				params.receiveAccountNo = form[i].value.parseBankAccountNo();
			}else if(form[i].name == 'amount') {
				params.amount = form[i].value.parseMoney();
			}else if(form[i].name == 'receiveAccountName'){
				params.lianHangNo = form[i].value;
			}else{
				params[form[i].name] = form[i].value;
			}
		}
		params.receiveAccountName = $("#receiveAccountName option[value="+params.lianHangNo+"]").text()
		submit_lock = true;
		cana.post($('#trade-form').attr('action'),params,function(data){
			var result = data.result.toLowerCase();
			if(result == 'trade_success'){
				window.location.href = 'result/'+tradeType.toLowerCase()+'/'+result;
			}else if(result == 'trade_handling'){
				window.location.href = 'result/'+tradeType.toLowerCase()+'/'+result;
			}
		},function(data){
			//refreshCode();
			submit_lock = false;
			cana.alert(data);
		});
	});
	
	$('body').on('click','.refresh-code',function(){
		refreshCode();
	});

	function clickNextLink(){
		if ($(".trade-wrap").is(":hidden")) {
			$(".accountList-wrap").hide();
			$(".trade-wrap").show();
			var finaceBalance = $(".finaceBalance").text().formatMoney();
			$(".finaceBalance").text(finaceBalance);
			getAccountBalance();
			if($("#accountType").val() == "GENERAL" && $("#accountSupervisionStatus").val() == "HAVE_SUPERVISION"){
				$("#loanInfo").attr("style","display:block;");
				searchLoan();
			}
		}
		cityLoad();
		branchLoad();
	};
	
	function refreshCode(){
		$('.validateImg').filter('img').attr('src','/vbam-front-biz/captcha/gen?'+Math.random());
	};
	
	function checkTransferFund(input) {
		var amount = $("#amount").val().parseMoney();
		$('#transfer-ch').text(cana.money.toChinese(amount));
		var balance = parseFloat($(".accountBalance").text().parseMoney());
		if(isNaN(balance)){
			input.attr("data-transferFundRule-msg", "账户余额"+$(".accountBalance").text()+"，不可交易");
			return false;
		}
		if(parseFloat(amount) <= 0){
			input.attr("data-transferFundRule-msg", "交易金额必须大于0");
			return false;
		}
		var transferMoneyRe = /^([1-9][\d]{0,8}|0)(\.[\d]{1,2})?$/;
		if(!transferMoneyRe.test(amount)){
			if(amount*100 % 1 > 0){
				input.attr("data-transferFundRule-msg", "最多支持两位小数");
			}else{
				input.attr("data-transferFundRule-msg", "单笔交易最多支持9位整数");
			}
			return false;
		}
		
		var totalAmount = parseFloat(amount);
		var commissionCharge;
		if(tradeType == "WITHDRAW_FUND"){
			var bank = $.trim($("#receiveBankName").val());
			commissionCharge = calculateCommissionCharge(amount,bank);
			$(".commissionCharge").text(commissionCharge);
			$('#commission-ch').text(cana.money.toChinese(commissionCharge));
			totalAmount +=  parseFloat(commissionCharge);
		}
		if(totalAmount> balance){
			input.attr("data-transferFundRule-msg", "您的帐户余额不足，您最大提现金额为"+maxWithdraw(balance,bank)+"，手续费为"+calculateCommissionCharge(amount,bank));
			return false;
		}
		return true;

	}

	/**
	 * 刷新--获取一个账户余额
	 */
	var isAccountBalanceRequesting = false;
	function getAccountBalance(){
		if(isAccountBalanceRequesting)
			return;
		isAccountBalanceRequesting = true;
		$(".accountBalance").text("获取中");
		cana.post(basepath + "/account/trade/updateFundInfo",
				{
					accountNo: $("#accountNo").val().parseBankAccountNo(),
					finaceBalance: $(".finaceBalance").text().parseMoney()
				},
				function(data){
					var accountBalance = parseFloat(data.accountBalance).toFixed(2);
					var fundCoverage = parseFloat(data.fundCoverRatio*100).toFixed(2);
					if(!isNaN(accountBalance)){
						$(".accountBalance").text(accountBalance.formatMoney());
						$('#balance-ch').text(cana.money.toChinese(accountBalance));
					}
			        if(!isNaN(fundCoverage))
			        	$(".fundCoverage").text(fundCoverage);
				},function(data){
					$(".accountBalance").text("获取失败");
				},function(){
					isAccountBalanceRequesting = false;
				});
	}

	
});

/**
 * 根据提现金额计算提现手续费
 * @param amount
 * @returns {String}
 */
function calculateCommissionCharge(amount,bank){
	return cana.bank.withdrawFee(amount,bank);
}


function maxWithdraw(balance, bank, amount){
	return cana.bank.maxWithdraw(balance, bank, amount);
}

/**
 * 查询 放款信息
 */
function searchLoan(){
	$("#withdrawGrid").remove();
	$(".accountDetail-wrap").append("<div id=\"withdrawGrid\" class=\"accountManageGrid\"></div>");
	var grid = $("#withdrawGrid").kendoGrid({
		selectable: "row",  //设置可选择数据行
		sortable: true,  //列排序
		dataSource:{
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						accountSupervisionId: $("#accountSupervisionId").val()
					},
					url: basepath + "/account/trade/loan"
				}
			},
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			schema: {
				data: "data",
				total: function(data){
					var total = data.totalNum;
					if(total == 0){
						$("#withdrawGrid .k-grid-header-wrap").css("overflow-x","auto");
					}else{
						$("#withdrawGrid .k-grid-header-wrap").css("overflow-x","");
					}
					return total;
				}
			}
		},
		sortable: false,
		groupable: false,
		columns: [{
			field: "loanNo",
			title: "放款编号",
			width: 180
		},{
            field: "businessContractNo",
            title: "业务合同号",
            width: 180
        },{
			field: "financeCompany",
			title: "融资客户名称",
			width: 300
		},{
            field: "voucherNo",
            title: "凭证号码",
            width: 180
        },{
            field: "currency",
            title: "币别",
            width: 50,
            template:function(data){
            	return data.currency == "RMB"?"人民币":data.currency;
            }
        },{
			field: "businessProduct",
			title: "产品类型",
			width: 180
		},{
            field: "financeAmount",
            title: "融资金额",
            width: 100,
            template:function(data){
            	return data.financeAmount.formatMoney();
            }
        },{
			field: "financeBalance",
			title: "融资余额",
			width: 100,
            template:function(data){
            	return data.financeBalance.formatMoney();
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
}

function initCityDropDownList() {
	var cityDataSource = new kendo.data.DataSource({
    	transport: {
//            read: {
//                dataType: "json",
//                type: "post",
//                asyn: false,
//                url: basepath + "/account/trade/queryCities"
    		read:function(options){
    			$.ajax({
    				url:basepath + "/account/trade/queryCities",
    				dataType:"json",
    				type:"post",
    				data:{province: $.trim($("#province").val())},
    				success:function(data){
    					options.success(data);
    					branchLoad();
    				}
    			});
    		}
	
        },
        schema:{
        	data: "data"
        }
	});
	
	var dropDown = $("#city").kendoDropDownList({
		autoBind: false,
		dataTextField: 0,
        dataValueField: 0,
        change: branchLoad,
        dataSource: cityDataSource
	});
	dropDown.select();
};

function initBranchDropDownList(){
	var branchDataSource = new kendo.data.DataSource({
		transport: {
			read: {
				dataType: "json",
				type: "post",
				asyn: false,
				url: basepath + "/account/trade/queryBranches"
			}
		},
		schema:{
			data: "data"
		}
	});
	
	$("#receiveAccountName").kendoDropDownList({
		autoBind: false,
		dataTextField: "branchName",
		dataValueField: "lianHangNo",
		dataSource: branchDataSource
	});
};

function cityLoad(){
	cityList = $("#city").data("kendoDropDownList")
	cityList.enable(false);
	cityList.dataSource.read();
	cityList.enable(true);
}

function branchLoad(){
	var bankName = $.trim($("#receiveBankName").val());
	var province = $.trim($("#province").val());
	var city = $.trim($("#city").val());
	if(null != bankName && "" != bankName && null != province && "" != province && null != city && "" != city ){
		branchList = $("#receiveAccountName").data("kendoDropDownList")
		branchList.enable(false);
		branchList.dataSource.read({
			bankName: $.trim($("#receiveBankName").val()),
			province: $.trim($("#province").val()),
			city: $.trim($("#city").val())
		});
		branchList.enable(true);
	}
}

function checkIsWithdrawFundByTime(withdrawFundStartTime,withdrawFundEndTime){
	var today = new Date();
	var hour = today.getHours();
	if(hour < 10)
		hour = "0" + hour;
	var minute = today.getMinutes();
	if(minute < 10)
		minute = "0" + minute;
    var second = today.getSeconds();
	if (second < 10)
		second = "0" + second;
	var nowTime = hour + ":" + minute + ":" + second;
	if(nowTime < withdrawFundStartTime || nowTime > withdrawFundEndTime){
		$(".withdraw-input").attr("disabled", true);
		$(".next-link").addClass("disable-btn");
		return false;
	}
	return true;
}
