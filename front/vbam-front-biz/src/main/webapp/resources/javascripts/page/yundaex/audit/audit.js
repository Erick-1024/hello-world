var resubmit = false;
var canSubmit = false;
$(function(){
	
	if($("#controllerIsLegal").val()==1){
		$(".hidden-one").attr("style","display:none");
	};
	if($("#accountOwner").val()!="OTHER"){
		$(".hidden-two").attr("style","display:none");
	};
	if($("#whetherTbOrder").val()==0){
		$(".hidden-three").attr("style","display:none");
	};
	
	if($("#organizationMediaId").val()==""){
		$(".organizationMediaId").remove();
	};
	if($("#businessLicenceMediaId").val()==""){
		$(".businessLicenceMediaId").remove();
	};
	if($("#taxRegistrationCertificateMediaId").val()==""){
		$(".taxRegistrationCertificateMediaId").remove();
	};
	if($("#legalIdnoFrontMediaId").val()==""){
		$(".legalIdnoFrontMediaId").remove();
	};
	
	if($(".additionInformationMediaId").val()==""){
		$("#additionInformationMediaId").remove();
	};
	
	//formatMoney();
	updateAuditResult();
	
	$(".input-radio").click(function() {
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
	
	//提交判断条件
	if($("#custEmail").val()!="" && $("#payAccount").val()!="" && $("#payAccountAddress").val()!=""){
		canSubmit = true;
	}
	
});

/*function formatMoney() {
	$("#shortLoan").text($("#shortLoan").text().formatMoney());
	$("#bailBalance").text($("#bailBalance").text().formatMoney());
	var annualSales = $("#annualSales").text();
	
	 * if(annualSales.length != 0) {
	 * $("#annualSales").text(annualSales.formatMoney()); }
	 
}*/

function updateAuditResult(){
	var flag = true;
	var organizationNo = $("input[name=checkOrganizationCode]:checked").val();
		if(organizationNo == "true"){
			flag = flag & true;
		}else{
			flag = flag & false;
		}
    var businessLicenceNo = $("input[name=checkBusinessLicenceCode]:checked").val();
	    if(businessLicenceNo == "true"){
	    	flag = flag & true;
	    }else{
	    	flag = flag & false;
	    }
    var taxRegistrationCertificateNo = $("input[name=checkTaxRegistrationCertificateCode]:checked").val();
	    if(taxRegistrationCertificateNo == "true"){
	    	flag = flag & true;
	    }else{
	    	flag = flag & false;
	    }
    var legalIdno = $("input[name=checkLegalIdno]:checked").val();
	    if(legalIdno == "true"){
	    	flag = flag & true;
	    }else{
	    	flag = flag & false;
	    }
    var detailAddress = $("input[name=stationAddress]:checked").val();
	    if(detailAddress == $("#DOWNTOWN").val() || detailAddress == $("#MARKET").val()){
	    	flag = flag & true;
	    }else{
	    	flag = flag & false;
	    }
    var other = $("input[name=checkOther]:checked").val();
	    if(other == "true"){
	    	flag = flag & true;
	    }else{
	    	flag = flag & false;
	    }
    var executeIndividualInfo = $.trim($("#executeIndividualInfo").val());
	    if(checkRule(executeIndividualInfo)){
	    	flag = flag & true;
	    }else{
	    	flag = flag & false;
	    }
    var negativeNetwork = $.trim($("#negativeNetwork").val());
	    if(checkRule(negativeNetwork)){
	    	flag = flag & true;
	    }else{
	    	flag = flag & false;
	    }
	   
	if(flag){
		$("#auditResult").text("通过");
	}else{
		$("#auditResult").text("不通过");
	}
}

function checkRule(val){
	if(val != ""){
		return false;
	}else{
		return true;
	}
	
}

function submitReview(){
	if(resubmit)
		return;
	if(!canSubmit){
		openPop("审核提示","请填写补充资料");
		return;
	}
	resubmit = true;
	var id = $("#id").val();
	$(".confirm-link").text("提交中...");
		cana.post(
			basepath + "/yundaex/audit/audit",
			{
				customerApplyId: id,
				checkOrganizationCode: $("input[name=checkOrganizationCode]:checked").val(),
				checkBusinessLicenceCode: $("input[name=checkBusinessLicenceCode]:checked").val(),
				checkTaxRegistrationCertificateCode: $("input[name=checkTaxRegistrationCertificateCode]:checked").val(),
				checkLegalIdno: $("input[name=checkLegalIdno]:checked").val(),
				stationAddress: $("input[name=stationAddress]:checked").val(),
				executeIndividualInfo: $.trim($("#executeIndividualInfo").val()),
				negativeNetwork: $.trim($("#negativeNetwork").val()),
				checkOther: $("input[name=checkOther]:checked").val(),
				manualAuditRemarks: $.trim($("#manualAuditRemarks").val())
			}, 
			function(data) {
				location.href = encodeURI(basepath + '/yundaex/audit/detail?id=' + id);
			},
			function(data){
				resubmit = false;
				$(".confirm-link").text("提交");
				//popWindow.open().center();
				//$("#tip-box-window .dlg-notice").addClass("notice-icon01");
				//$("#tip-box-window .notice-content").text(data);
			});
    }