var amountPattern = "^[0-9]+(.[0-9]{1,2})?$";
var validator;
$(function() {
	// 检验是否是周末和9-17点
	validator();
	
	// 检验控件
	validatorControl();
	
	// 数据检验
	newValidator();
	
	// 显示页面按钮
	showControl();
	
	// 初始化 选择产品 生成 到期日
	checkProType();
	
	// 根据产品期限 生成 到期日
	getDueDate();
});

/**
 * 显示页面按钮
 */
function showControl() {
   	if ($("#signatureSoftwateInstall").parent().is(":hidden")) {
		$("#confirmBtn").parent().show();
	} else {
		$("#confirmBtn").parent().hide();
	}
}

/**
 * 检查申请时间
 */
function validator() {
	// 检验时间
	if (!ValidatorWeekDate()) {
		closePage();
	}
}

/**
 * 检验控件
 */
function validatorControl() {
	// 判断是否在工作时间
	if (ValidatorWeekDate()) {
		// 加载控件
		Signature.onLoad();
		
		// 初始化事件
		initEvent();
		
		// 浏览器检验
		validBrowser();
	}
}

/**
 * 浏览器检验
 */
function validBrowser() {
	if (navigator.appName.indexOf("Internet") >= 0 || navigator.appVersion.indexOf("Trident") >= 0) {
    	setInterval("ieShowInstallCotrol()", 6000);
	} else {
    	Signature.judeSoftwareInstall($("#signatureSoftwateInstall").parent());
 
    	//判断是否为google浏览器,如果是且版本是45以上,软件不支持
    	var browser = getChromeBrowserInfo();
    	if(browser){ 
    		var verinfo = (browser+"").replace(/[^0-9.]/ig,"");
    		var versionNo = verinfo.substring(0, verinfo.indexOf("."));
    		if(versionNo > 45){
    			$("#chromeMgs").parent().show();
    			$("#signatureSoftwateInstall").next().hide();
    			$("#signatureSoftwateInstall").unbind();
		    	$("#signatureSoftwateInstall").css("background-color", "#ccc");
    		}
    	}
    }
}

/**
 * 如果6秒钟，客户没有点击IE下载，则显示按键下载
 */
function ieShowInstallCotrol() {
	Signature.judeSoftwareInstall($('#signatureSoftwateInstall').parent());
	showControl();
}

/**
 * 初始化页面事件
 */
function initEvent() {
	$("#signatureSoftwateInstall").click(function() {
		$(Signature.softwareUrls).multiDownload();
	});

	$("#confirmBtn").click(function() {
		submitData();
	});
}

/**
 * 跳转签合同页面
 */
function confirmLoanInfo(){
	var applyAmt = $.trim($("#applyAmt").val());
	var proId = $('input[name="proType"]:checked').val();
	
	window.location.href = encodeURI(basepath + "/yundaex/loan/confirmInfomation?applyAmt=" + applyAmt + "&proId=" + proId);
}

function error(){
	$("#confirmBtn").text("确认");
	$("#confirmBtn").css("background-color", "#0f8aba");
	$("#confirmBtn").click(function() {
		submitData();
	});
}

/**
 * 检验以及提交数据
 */
function submitData() {
	var applyAmt = $.trim($("#applyAmt").val());
	var proId = $('input[name="proType"]:checked').val();
	
	if (!validator.validate()) {
		return;
	}
	
	$.ajax({
		type : 'POST',
		url : basepath + '/yundaex/loan/checkLoanApply',
		data : {
			applyAmt : applyAmt,
			proId : proId
		},
		success : function(data) {
			if(data.status=='SUCCESS'){
				$("#confirmBtn").unbind();
		    	$("#confirmBtn").text("证书校验中...");
		    	$("#confirmBtn").css("background-color", "#ccc");
		    	Signature.setDNFilter(companyName);
		    	Signature.installCert({callback:confirmLoanInfo, error: error});
			} else {
				showAlertWin(data.message);
			}
		},
		error: function(data){
			showAlertWin("网络异常"+data.responseText);
		}
	});
}

/**
 * 检验chrome
 * @returns
 */
function getChromeBrowserInfo(){
	var agent = navigator.userAgent.toLowerCase();
	var regStr_chrome = /chrome\/[\d.]+/gi;

	// Chrome
	if (agent.indexOf("chrome") > 0) {
		return agent.match(regStr_chrome);
	}
	return false;
}

/**
 * 增加谷歌提示
 */
function hintMessage(){
	return "<div style='text-align:center;margin-bottom:20px'><span><font color=red>您当前的谷歌浏览器版本过高，不能使用安全控件，请更换浏览器，建议使用ie浏览器</font></span><div>";
}

/**
 * 检查申请时间
 */
function ValidatorWeekDate() {
	var flag = true;
	var myDate = new Date(); // 获取当前完整时间
	var week = myDate.getDay(); // 获取当前是周几(0是周日，6是周六)
	if (week == 0 && week == 6) {
		flag = $(".x-box").show();
	} else {
		flag = timeRange("9:00", "17:00");
	}
	
	return flag;
}

/**
 * 检查申请时间
 */
function timeRange(beginTime, endTime) {
	var strb = beginTime.split(":");
	if (strb.length != 2) {
		return false;
	}

	var stre = endTime.split(":");
	if (stre.length != 2) {
		return false;
	}

	var b = new Date();
	var e = new Date();
	var n = new Date();

	b.setHours(strb[0]);
	b.setMinutes(strb[1]);
	e.setHours(stre[0]);
	e.setMinutes(stre[1]);

	if (n.getTime() - b.getTime() > 0 && n.getTime() - e.getTime() < 0) {
		return true;
	} else {
		$(".x-box").show();
		return false;
	}
}

/**
 * 关闭提示框
 */
function closeBox() {
	$(".x-box").hide();
}

/**
 * 关闭页面所有按钮
 */
function closePage() {
	$("input").attr("disabled", "disabled");
	$("#confirmBtn").addClass("disable-btn");
	$("#confirmBtn").removeAttr("onclick");
}

/**
 * 初始化 选择产品 生成 到期日
 * 
 * @returns
 */
function checkProType() {
	$('[name="proType"]').change(function() {
		getDueDate();
	});
}

/**
 * 根据产品期限 生成 到期日
 * 
 * @returns
 */
function getDueDate() {
	var loanDate = $('#loanDate').text();
	var loanPeriod = $('input[name="proType"]:checked').next().text();
	loanPeriod = loanPeriod.split("月")[0];
	var date = loanDate.replace(/-/g, "/");
	var dueDate = DateAdd('m', loanPeriod, new Date(date)).format("yyyy-MM-dd");
	$('#dueDate').text(dueDate);
}

/**
 * js检验逻辑
 */
var applyAmtMsg = {
	formateRule : function(input) {
		if (input.is("#applyAmt")) {
			var value = $("#applyAmt").val().parseMoney();
			var result = value.match(amountPattern);
			if (null == result) {
				return false;
			} else {
				return true;
			}
		}
		return true;
	},
	formateRuleMessage : "申请用款金额格式不正确",
	rule : function(input) {
		if (input.is("#applyAmt")) {
			if (parseFloat($("#applyAmt").val().parseMoney()) <= parseFloat($(
					"#notUsedLimit").text().parseMoney())) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	},
	ruleMessage : "申请用款金额大于可用额度"
};

/**
 * 初始化检验
 * @returns
 */
function newValidator() {
	setValidator($("#interestRateRule"), "pattern", applyAmtMsg.pattern,
			applyAmtMsg.message);

	validator = $("#loanInfoForm").kendoValidator({
		rules : {
			applyAmtMsg : applyAmtMsg.formateRule,
			applyAmtRuleMsg : applyAmtMsg.rule
		},
		messages : {
			applyAmtMsg : applyAmtMsg.formateRuleMessage,
			applyAmtRuleMsg : applyAmtMsg.ruleMessage
		},
		needRuleAttrbute : false
	}).data("kendoValidator");
}