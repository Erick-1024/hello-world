var resubmit = false;

$(function(){

	//初始化操作弹窗
	popWindow = new PopWindow({
		title: "提示",
		width: 420,
		reload: true,
		template: "#tipBox_template"
	}).init();
	
	formatMoney();
	updateAuditResult();
	
	$(".radio").click(function() {
		updateAuditResult();
	});
	
	$(".checkText").blur(function() {
		updateAuditResult();
	});
	
	// 限制只能输入数字
	$(".onlyNum").keypress(function(event) { 
        var keyCode = event.which; 
        if ((keyCode <= 57 && keyCode >= 48) || (keyCode == 8) || (keyCode == 0)) 
            return true; 
        else 
            return false; 
    }).focus(function() { 
        this.style.imeMode='disabled'; 
    });  
	
});

function formatMoney() {
	$("#applyCreditLimit").text($("#applyCreditLimit").text().formatMoney());
	$("#proposalCreditLimit").text($("#proposalCreditLimit").text().formatMoney());
	var annualSales = $("#annualSales").text();
	if(annualSales.length != 0) {
		$("#annualSales").text(annualSales.formatMoney());
	}
}

function updateAuditResult(){
	var flag = true;
	
	//企业被执行金额
	flag = flag & numCompareLimit($("#enterpriseExecutionMoney"));
	
	//个人被执行金额
	flag = flag & numCompareLimit($("#individualExecutionMoney"));

	//企业被执行次数
	flag = flag & numCompareLimit($("#enterpriseExecutionTimes"));

	//个人被执行金额
	flag = flag & numCompareLimit($("#individualExecutionTimes"));

	//网络负面
	if($.trim($("#negativeNetwork").val()).length < 1) {
		flag = flag & true;
	} else {
		flag = flag & false;
	}
	
	//工商信息
	if($("#businessInfoTrue").hasClass("active")){
		$("#businessInfo").val("true");
		flag = flag & true;
	}else if($("#businessInfoFalse").hasClass("active")){
		$("#businessInfo").val("false");
		flag = flag & false;
	}
	//组织机构代码
	if($("#organizationMediaIdTrue").hasClass("active")){
		$("#organizationMediaId").val("true");
		flag = flag & true;
	}else if($("#organizationMediaIdFalse").hasClass("active")){
		$("#organizationMediaId").val("false");
		flag = flag & false;
	}
	//营业执照号码
	if($("#businessLicenceMediaIdTrue").hasClass("active")){
		$("#businessLicenceMediaId").val("true");
		flag = flag & true;
	}else if($("#businessLicenceMediaIdFalse").hasClass("active")){
		$("#businessLicenceMediaId").val("false");
		flag = flag & false;
	}
	//税务登记证号码
	if($("#taxRegistrationCertificateMediaIdTrue").hasClass("active")){
		$("#taxRegistrationCertificateMediaId").val("true");
		flag = flag & true;
	}else if($("#taxRegistrationCertificateMediaIdFalse").hasClass("active")){
		$("#taxRegistrationCertificateMediaId").val("false");
		flag = flag & false;
	}
	//其他
	if($("#manualAuditRemarksTrue").hasClass("active")){
		$("#manualAuditRemarks").val("true");
		flag = flag & true;
	}else if($("#manualAuditRemarksFalse").hasClass("active")){
		$("#manualAuditRemarks").val("false");
		flag = flag & false;
	}
	
	if(flag){
		$("#auditResult").text("通过");
	}else{
		$("#auditResult").text("不通过");
	}
}

function numCompareLimit(inputObject) {
	if((isNaN(parseFloat(inputObject.val()))?parseFloat(0):parseFloat(inputObject.val())) <= parseFloat(inputObject.attr('limit'))) {
		return true;
	} else {
		return false;
	}
}

function submitReview(){
	if(resubmit)
		return;
	resubmit = true;
	var id = $(".confirm-link").attr("name");
	$(".confirm-link").text("提交中...");
		cana.post(
			basepath + "/credit/audit/audit",
			{
				customerApplyId: id,
				enterpriseExecutionMoney: $.trim($("#enterpriseExecutionMoney").val()),
				enterpriseExecutionTimes: $.trim($("#enterpriseExecutionTimes").val()),
				individualExecutionMoney: $.trim($("#individualExecutionMoney").val()),
				individualExecutionTimes: $.trim($("#individualExecutionTimes").val()),
				negativeNetwork: $.trim($("#negativeNetwork").val()),
				checkCompanyInfo: $("#businessInfo").val(),
				checkOrganizationCode: $("#organizationMediaId").val(),
				checkBusinessLicenceCode: $("#businessLicenceMediaId").val(),
				checkTaxRegistrationCertificateCode: $("#taxRegistrationCertificateMediaId").val(),
				checkOthers: $("#manualAuditRemarks").val(),
				manualAuditRemarks: $("#manualAuditRemarksText").val()
			}, 
			function(data) {
				location.href = encodeURI(basepath + '/credit/audit/detail?id=' + id + "&canaId=" + $(".confirm-link").attr("canaId"));
			},
			function(data){
				resubmit = false;
				$(".confirm-link").text("提交");
				popWindow.open().center();
				$("#tip-box-window .dlg-notice").addClass("notice-icon01");
				$("#tip-box-window .notice-content").text(data);
			});
}