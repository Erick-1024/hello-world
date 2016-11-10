function initializePage() {
	var userStatus = $("#userStatus").data("userstatus");
	if(userStatus != "PENDINGAUDIT"){
		$(".review-link").remove();
		if($("#checkCompanyName").data("checkcompanyname")){
			$("#checkCompanyName").text("一致");
		}else {
			$("#checkCompanyName").text("不一致");
		}
		if($("#checkOrganizationCode").data("checkorganizationcode")){
			$("#checkOrganizationCode").text("一致");
		}else {
			$("#checkOrganizationCode").text("不一致");
		}
		if($("#checkBusinessLicenceCode").data("checkbusinesslicencecode")){
			$("#checkBusinessLicenceCode").text("一致");
		}else {
			$("#checkBusinessLicenceCode").text("不一致");
		}
		if($("#checkTaxRegistrationCertificateCode").data("checktaxregistrationcertificatecode")){
			$("#checkTaxRegistrationCertificateCode").text("一致");
		}else {
			$("#checkTaxRegistrationCertificateCode").text("不一致");
		}
		if($("#checkLegalPersonIdentityCard").data("checklegalpersonidentitycard")){
			$("#checkLegalPersonIdentityCard").text("一致");
		}else {
			$("#checkLegalPersonIdentityCard").text("不一致");
		}
		if($("#checkOthers").data("checkothers")){
			$("#checkOthers").text("一致");
		}else {
			$("#checkOthers").text("不一致");
		}
		if(userStatus == "PENDINGACTIVATE" || userStatus == "ACTIVATED"){
			$("#auditResult").text("通过");
		}else {
			$("#roleName").remove();
			$("#auditResult").text("未通过");
		}
	}else {
		$("#auditResult").text("待审核");
	}
	
	$("body").on("click", ".review-link", function() {
		location.href = encodeURI(basepath + "/customer/gotoReview?customerId=" + $("#id").data("userid") + "&curSubMenu=" + curSubMenu);
	});
}

$(function() {
	initializePage();
	
	if(!reviewAuth){
		$(".review-link").remove();
	}
});