var resubmit = false;
var resultFlag = true;

$(function() {

    //审核结果消息弹窗
	tipPopWindow = new PopWindow({
		title : "提示",
		width : 420,
		reload : true,
		template : "#tipBox_template"
	}).init();
	
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
	
	//二次确认框 确定按钮
	$("body").on("click", "#confirm-box-window .confirm-link", function(e){
		submitReview();
	});
    
});

function updateAuditResult(){

	var flag = true;
	
	// 企业被执行金额
	flag = flag & numCompareLimit($("#enterpriseExecutionMoney"));

	// 个人被执行金额
	flag = flag & numCompareLimit($("#individualExecutionMoney"));

	// 企业被执行次数
	flag = flag & numCompareLimit($("#enterpriseExecutionTimes"));

	// 个人被执行金额
	flag = flag & numCompareLimit($("#individualExecutionTimes"));

	// 网络负面
	if ($.trim($("#negativeNetwork").val()).length < 1) {
		flag = flag & true;
	} else {
		flag = flag & false;
	}

	// 工商信息
	if ($("#businessInfoTrue").hasClass("active")) {
		$("#businessInfo").val("true");
		flag = flag & true;
	} else if ($("#businessInfoFalse").hasClass("active")) {
		$("#businessInfo").val("false");
		flag = flag & false;
	}
	// 组织机构代码
	if ($("#organizationMediaIdTrue").hasClass("active")) {
		$("#organizationMediaId").val("true");
		flag = flag & true;
	} else if ($("#organizationMediaIdFalse").hasClass("active")) {
		$("#organizationMediaId").val("false");
		flag = flag & false;
	}
	// 营业执照号码
	if ($("#businessLicenceMediaIdTrue").hasClass("active")) {
		$("#businessLicenceMediaId").val("true");
		flag = flag & true;
	} else if ($("#businessLicenceMediaIdFalse").hasClass("active")) {
		$("#businessLicenceMediaId").val("false");
		flag = flag & false;
	}
	// 税务登记证号码
	if ($("#taxRegistrationCertificateMediaIdTrue").hasClass("active")) {
		$("#taxRegistrationCertificateMediaId").val("true");
		flag = flag & true;
	} else if ($("#taxRegistrationCertificateMediaIdFalse").hasClass("active")) {
		$("#taxRegistrationCertificateMediaId").val("false");
		flag = flag & false;
	}
	// 其他
	if ($("#manualAuditRemarksTrue").hasClass("active")) {
		$("#manualAuditRemarks").val("true");
		flag = flag & true;
	} else if ($("#manualAuditRemarksFalse").hasClass("active")) {
		$("#manualAuditRemarks").val("false");
		flag = flag & false;
	}

	if(flag){
		$("#auditResult").text("通过");
		resultFlag = true;
	} else {
		$("#auditResult").text("不通过");
		resultFlag = false;
	}
}

function numCompareLimit(inputObject) {
	if (inputObject.val()==undefined || (isNaN(parseFloat(inputObject.val().parseMoney())) ? parseFloat(0)
			: parseFloat(inputObject.val().parseMoney())) <= parseFloat(inputObject
			.attr('limit').parseMoney())) {
		return true;
	} else {
		return false;
	}
}

function popErrorWindow(msg) {
	tipPopWindow.open().center();
	$("#tip-box-window .dlg-notice").addClass("notice-icon01");
	$("#tip-box-window .notice-content").text(msg);
}

//二次确认弹框
function confirmSubmitReview(){
	showConfirmBox("是否确认审核结果为：" + $("#auditResult").text());
}

function submitReview() {
	if (resubmit)
		return;
	
	var applicantType;
	if ($("#applicantTypeCompany").hasClass("active"))
		applicantType = 'company';
	if ($("#applicantTypeIndividual").hasClass("active"))
		applicantType = 'individual';
	//存在单选框，但是没有选
	if (!applicantType && $("#applicantTypeCompany").size()>0) {
		popErrorWindow("请选择申请人类型");
		return;
	}

	var legalPerson = $('#legalPerson').val();
	if(resultFlag &&　$.trim(legalPerson) == '') {
		//单选框选了“企业” or 没有单选框（说明要么是个人要么是企业），有法人输入框
		if((applicantType && applicantType == 'company') || ($("#applicantTypeCompany").size()==0 && $("#legalPerson").size()>0)){
			popErrorWindow("请输入法人！");
			return;
		}
	}
	
	resubmit = true;
	var id = $(".confirm-link").attr("name");
	$(".confirm-link").text("提交中...");
	var resultDTO={};
	resultDTO.customerApplyId = id;
	resultDTO.enterpriseExecutionMoney = $.trim($("#enterpriseExecutionMoney").val());
	resultDTO.enterpriseExecutionTimes = $.trim($("#enterpriseExecutionTimes").val());
	resultDTO.individualExecutionMoney = $.trim($("#individualExecutionMoney").val());
	resultDTO.individualExecutionTimes = $.trim($("#individualExecutionTimes").val());
	resultDTO.negativeNetwork = $.trim($("#negativeNetwork").val());
	resultDTO.checkCompanyInfo = $("#businessInfo").val();
	resultDTO.checkOrganizationCode = $("#organizationMediaId").val();
	resultDTO.checkBusinessLicenceCode = $("#businessLicenceMediaId").val();
	resultDTO.checkTaxRegistrationCertificateCode = $("#taxRegistrationCertificateMediaId").val();
	resultDTO.checkOthers = $("#manualAuditRemarks").val();
	resultDTO.manualAuditRemarks = $.trim($("#manualAuditRemarksText").val());
	if(!applicantType)
		if($("#legalPerson").size()>0)
			resultDTO.applicantType = "company";
		else
			resultDTO.applicantType = "individual";
	else
		resultDTO.applicantType = applicantType;
		
	resultDTO.legalPerson = $.trim($('#legalPerson').val());
	cana.postJson(
		basepath + "/credit/audit/audit",
		resultDTO, 
		function(data) {
			location.href = encodeURI(basepath + '/credit/audit/detail?id=' + id + "&canaId=" + $(".confirm-link").attr("canaId"));
		},
		function(data){
			resubmit = false;
			$(".confirm-link").text("提交");
			tipPopWindow.open().center();
			$("#tip-box-window .dlg-notice").addClass("notice-icon01");
			$("#tip-box-window .notice-content").text(data);
		});
}

function ajaxFileUpload(element) {
	$.ajaxFileUpload({
	    url: basepath + '/facade/saveFile',
	    type: 'post',
	    secureuri: false, //一般设置为false
	    fileElementId: $(element).attr("id"), // 上传文件的id属性名
	    dataType: 'text', //返回值类型，一般设置为json、application/json
	    success: function(data){
	    	if(data == "FAILED"){
	    		$(element).next().next().remove();
	    		$(element).next().after('<a class="in-up-error" href="javascript:void(0);">失败</a>');
	    		$(element).val("");
	    	}else{
	    		var filename = $(element).val().substring($(element).val().lastIndexOf('\\')+1);
	    		$(element).next().next().next().remove();
	    		$(element).next().next().remove();
	    		$(element).next().after('<a class="in-up-txt2" href="' + mediaserver + 'imageservice?mediaImageId=' + data + '&mediaType=download">下载</a><a class="in-up-txt3 del-png" href="javascript:void(0);"></a>');
	    		$(element).parent().parent().prev().text(filename);
	    		$(element).parent().parent().prev().attr("media-id",data);
	    		$(element).val("");
	    	}
	    },
	    error: function(data, status, e){
	    	$(element).next().next().remove();
    		$(element).next().after('<a class="in-up-error" href="javascript:void(0);">失败</a>');
    		$(element).val("");
	    }
	});
}
