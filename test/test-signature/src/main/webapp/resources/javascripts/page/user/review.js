$(function() {
	
	$(".radio").click(function() {
		var flag = true;
		if($("#companyNameTrue").hasClass("active")){
			$("#checkCompanyName").val("true");
			flag = flag & true;
		}else if($("#companyNameFalse").hasClass("active")){
			$("#checkCompanyName").val("false");
			flag = flag & false;
		}
		if($("#organizationCodeTrue").hasClass("active")){
			$("#checkOrganizationCode").val("true");
			flag = flag & true;
		}else if($("#organizationCodeFalse").hasClass("active")){
			$("#checkOrganizationCode").val("false");
			flag = flag & false;
		}
		if($("#businessLicenceCodeTrue").hasClass("active")){
			$("#checkBusinessLicenceCode").val("true");
			flag = flag & true;
		}else if($("#businessLicenceCodeFalse").hasClass("active")){
			$("#checkBusinessLicenceCode").val("false");
			flag = flag & false;
		}
		if($("#taxRegistrationCertificateCodeTrue").hasClass("active")){
			$("#checkTaxRegistrationCertificateCode").val("true");
			flag = flag & true;
		}else if($("#taxRegistrationCertificateCodeFalse").hasClass("active")){
			$("#checkTaxRegistrationCertificateCode").val("false");
			flag = flag & false;
		}
		if($("#legalPersonIdentityCardTrue").hasClass("active")){
			$("#checkLegalPersonIdentityCard").val("true");
			flag = flag & true;
		}else if($("#legalPersonIdentityCardFalse").hasClass("active")){
			$("#checkLegalPersonIdentityCard").val("false");
			flag = flag & false;
		}
		if($("#othersTrue").hasClass("active")){
			$("#checkOthers").val("true");
			flag = flag & true;
		}else if($("#othersFalse").hasClass("active")){
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
	});

	$(".submit-link").click(function() {
		$.post(
			basepath + "/customer/review",
			{
				id: $("#id").data("userid"),
				checkCompanyName: $("#checkCompanyName").val(),
				checkOrganizationCode: $("#checkOrganizationCode").val(),
				checkBusinessLicenceCode: $("#checkBusinessLicenceCode").val(),
				checkTaxRegistrationCertificateCode: $("#checkTaxRegistrationCertificateCode").val(),
				checkLegalPersonIdentityCard: $("#checkLegalPersonIdentityCard").val(),
				checkOthers: $("#checkOthers").val(),
				auditMessage: $("#auditMessage").val(),
				roleId: $("#roleId").val(),
				
			}, 
			function() {
				location.href = encodeURI(basepath + "/customer/customerDetail?customerId=" + $("#id").data("userid") + "&curSubMenu=" + curSubMenu);
			}
		)
	});
	
});