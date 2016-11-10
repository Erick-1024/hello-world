$(function(){
	
	$("td#accountNo").each(function(i,e){
		var formatAccountNo = $(e).text().formatBankAccountNo();
		$(e).text(formatAccountNo);
	})
	
	//初始化操作弹窗
	popWindow = new PopWindow({
		title: "提示",
		width: 420,
		reload: true,
		template: "#tipBox_template"
	}).init();
	
	/**
	 * 查看更多--展开买家企业名单；收起--收起买家名单列表
	 */
	$("#switchBuyers").click(function(){
		var t = $(this).text();
		if(t == "查看更多"){
			$("#clients").addClass("clientList-all");
			$(this).text("收起");
		}
		else{
			$("#clients").removeClass("clientList-all");
			$(this).text("查看更多");
		}
	});
	/**
	 * 更改任意单选框的值就立即更新最终的审核结果
	 */
	$(".radio").click(function() {
		updateAuditResult();
	});
	
});

function updateAuditResult(){
	var flag = true;
	//客户类型
	if($("#checkUserTypeTrue").hasClass("active")){
		$("#checkUserType").val("true");
		flag = flag & true;
	}else if($("#checkUserTypeFalse").hasClass("active")){
		$("#checkUserType").val("false");
		flag = flag & false;
	}
	//授权书
	if($("#checkAuthorizationLetterTrue").hasClass("active")){
		$("#checkAuthorizationLetter").val("true");
		flag = flag & true;
	}else if($("#checkAuthorizationLetterFalse").hasClass("active")){
		$("#checkAuthorizationLetter").val("false");
		flag = flag & false;
	}
	//企业名称
	if($("#checkCompanyNameTrue").hasClass("active")){
		$("#checkCompanyName").val("true");
		flag = flag & true;
	}else if($("#checkCompanyNameFalse").hasClass("active")){
		$("#checkCompanyName").val("false");
		flag = flag & false;
	}
	//组织机构代码
	if($("#checkOrganizationCodeTrue").hasClass("active")){
		$("#checkOrganizationCode").val("true");
		flag = flag & true;
	}else if($("#checkOrganizationCodeFalse").hasClass("active")){
		$("#checkOrganizationCode").val("false");
		flag = flag & false;
	}
	//营业执照号码
	if($("#checkBusinessLicenceCodeTrue").hasClass("active")){
		$("#checkBusinessLicenceCode").val("true");
		flag = flag & true;
	}else if($("#checkBusinessLicenceCodeFalse").hasClass("active")){
		$("#checkBusinessLicenceCode").val("false");
		flag = flag & false;
	}
	//税务登记证号码
	if($("#checkTaxRegistrationCertificateCodeTrue").hasClass("active")){
		$("#checkTaxRegistrationCertificateCode").val("true");
		flag = flag & true;
	}else if($("#checkTaxRegistrationCertificateCodeFalse").hasClass("active")){
		$("#checkTaxRegistrationCertificateCode").val("false");
		flag = flag & false;
	}
	//法人代表身份证
	if($("#checkLegalPersonIdentityCardTrue").hasClass("active")){
		$("#checkLegalPersonIdentityCard").val("true");
		flag = flag & true;
	}else if($("#checkLegalPersonIdentityCardFalse").hasClass("active")){
		$("#checkLegalPersonIdentityCard").val("false");
		flag = flag & false;
	}
	//其他
	if($("#checkOthersTrue").hasClass("active")){
		$("#checkOthers").val("true");
		flag = flag & true;
	}else if($("#checkOthersFalse").hasClass("active")){
		$("#checkOthers").val("false");
		flag = flag & false;
	}
	
	if(flag){
		$("#auditResult").text("通过");
		$("#roleId").removeAttr("disabled");
	}else{
		$("#auditResult").text("不通过");
		$("#roleId").attr("disabled", "disabled");
	}
}

var resubmit = false;
function submitReview(){
	if(resubmit)
		return;
	resubmit = true;
	$(".submitReviewButton").text("提交中...");
		cana.post(
			basepath + "/account/apply/review",
			{
				auditorId: userKey,
				accountApplyId: $("#id").data("applyid"),
				auditMessage: $("#auditMessage").val(),
				checkUserType: $("#checkUserType").val(),
				checkAuthorizationLetter: $("#checkAuthorizationLetter").val(),
				checkOthers: $("#checkOthers").val(),
				checkCompanyName: $("#checkCompanyName").val(),
				checkOrganizationCode: $("#checkOrganizationCode").val(),
				checkBusinessLicenceCode: $("#checkBusinessLicenceCode").val(),
				checkTaxRegistrationCertificateCode: $("#checkTaxRegistrationCertificateCode").val(),
				checkLegalPersonIdentityCard: $("#checkLegalPersonIdentityCard").val(),
				roleId: $("#roleId").val(),
			}, 
			function(data) {
				location.href = encodeURI(basepath + "/account/apply/detail?applyId=" + $("#id").data("applyid") + "&curSubMenu=" + curSubMenu);
			},
			function(data){
				resubmit = false;
				$(".submitReviewButton").text("提交");
				popWindow.open().center();
				$("#tip-box-window .dlg-notice").addClass("notice-icon01");
				$("#tip-box-window .notice-content").text(data);
			});
}