var resubmit = false;

$(function(){

	//初始化操作弹窗
	popWindow = new PopWindow({
		title: "提示",
		width: 420,
		reload: true,
		template: "#tipBox_template"
	}).init();
	
	updateApproveResult();
	
	$(".radio").click(function() {
		updateApproveResult();
	});
	
});

function updateApproveResult(){

	//额度
	if($("#auditLimitOpinionTrue").hasClass("active")){
		$("#auditLimitOpinion").val("true");
	}else if($("#auditLimitOpinionFalse").hasClass("active")){
		$("#auditLimitOpinion").val("false");
	}
	
}

function popErrorWindow(msg) {
	popWindow.open().center();
	$("#tip-box-window .dlg-notice").addClass("notice-icon01");
	$("#tip-box-window .notice-content").text(msg);
}

function submitReview(){
	if(resubmit)
		return;
	var auditLimit =　$.trim($("#auditLimit").text()).parseMoney();
	if($("#auditLimitOpinion").val() == "false")
		auditLimit = $("#auditLimitInput").val();
//	if(auditLimit == ""){
//		popErrorWindow("额度不能为空！");
//		return;
//	}

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
	if($.trim(legalPerson) == '') {
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
//	resultDTO.auditLimit = auditLimit;
	if(!applicantType)
		if($("#legalPerson").size()>0)
			resultDTO.applicantType = "company";
		else
			resultDTO.applicantType = "individual";
	else
		resultDTO.applicantType = applicantType;
	resultDTO.legalPerson = legalPerson;
	cana.postJson(
		basepath + "/credit/audit/audit",
		resultDTO, 
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