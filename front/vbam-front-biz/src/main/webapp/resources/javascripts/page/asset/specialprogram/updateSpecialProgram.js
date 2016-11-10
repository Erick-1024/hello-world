//校验专项计划编号
function isValueExist(specialProgramId,oldId,url) {
	var result = $.ajax({
		url: basepath + "/asset/specialprogram/" + url,
		async: false,
		type: 'post',
		data: {
			specialProgramId: specialProgramId,
			oldId: oldId
		}
	}).responseText;
	if(result == "true")
		flag = false;
	else
		flag = true;
	return flag;
};

//校验专项计划管理人名称是否存在
function isCompanyNameExist() {
	var result = $.ajax({
		url: basepath + "/asset/specialprogram/checkManageName",
		async: false,
		type: 'post',
		data: {
			companyName: $("#managerName").val()
		}
	}).responseText;
	if(result == "true")
		flag = false;
	else
		flag = true;
	return flag;
};

function bindEvent() {
	setValidator($("#specialProgramId"), "required", true, specialProgramIdRule.required);
	setValidator($("#specialProgramName"), "required", true, specialProgramNameRule.required);
	setValidator($("#estimateEstablishmentDate"), "required", true, estimateEstablishmentDateRule.required);
}

$(function() {
	
	$("input[name=managerName]").change(function(){
		$("#mangageId").hide();
		flag_companyName = isCompanyNameExist();
		if(flag_companyName== false){
			tipPopWindow1.open().center();
			$("#tip-box-window .dlg-notice").addClass("notice-icon01");
			$("#tip-box-window .notice-content").text("该券商尚未注册，确定是否指定为该专项计划的管理人？");
		}else{
			$("#mangageId").hide();
		}
	});
	
	
	
	bindEvent();
	var validator = $("#specialProgram-update").kendoValidator({
		rules: {
			specialProgramIdIsExistRule: specialProgramIdRule.rule,
		},
		messages: {
			specialProgramIdIsExistRule: specialProgramIdRule.ruleMessage,
		},
		needRuleAttrbute : false
	}).data("kendoValidator");
	
	
	//回车按钮事件
	document.onkeydown=keyDownSearch;  
    function keyDownSearch(e) {    
        var theEvent = e || window.event;    
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
        if (code == 13) {  
        	if($(".k-overlay").length == 0){
    			$("#submit").click();
    		}else{
    			if($(".k-overlay").is(':hidden')==true){
    				$("#submit").click();
    			} else {
    				$("#form-search-btnOne").click();
    				$("#form-search-btnTwo").click();
    			}
    		}
        }    
        return true;    
    } 
	
	 //初始化消息弹窗（失败）
    popWindow = new PopWindow(".message-pop",{
        title: "提示",
        width: 420,
        reload: true,
        template: "#tipBox_template"
    }).init();
	//初始化弹窗（成功提示）
	tipPopWindow = new PopWindow({
		title: "提示",
		width: 420,
		reload: true,
		template: "#tipBox_template1"
	}).init();
	//初始化弹窗（成功提示）
	tipPopWindow1 = new PopWindow({
		title: "提示",
		width: 420,
		reload: true,
		template: "#tipBox_template2"
	}).init();
	//判断状态如果是除立项以外的状态，数据只读，除状态可选外
	var status=$("#status").val();
	if(status =="PACKAGE" ||status =="ISSUE" || status =="ESTABLISH" || status =="CLOSE"){
		$('input').attr("disabled","disabled");
		$('#submit').removeAttr("disabled");
		$(".all-hide").hide();
	}
	
	//点击提交
	$("#submit").click(function() {
		if(!validator.validate()) {
			return;
		};
		
		var postData = {};
		var originator =[];
		var serviceAgency =[];
		
		var originatorNameLength = $("#interests-tb tr").length;
		var serviceAgencyNameLength = $("#asset-tb tr").length;
		//原始权益人
		for(var i=0; i< originatorNameLength; i++) {
				originator.push({originatorName: $($("#interests-tb tr")[i]).find("td").text(), id: $($('[name=interests-tb]')[i]).data('id')});
			}
		for(var i=0; i< serviceAgencyNameLength; i++){
			serviceAgency.push({serviceAgencyName: $($("#asset-tb tr")[i]).find("td").text(),id:$($('[name=asset-tb]')[i]).data('id')});
		}
		
		//判断原始权益人和资产服务机构是否为空
		if((originator == null||originatorNameLength == 0)&&(serviceAgency == null || serviceAgencyNameLength == 0)){
			showAlertWin("原始权益人和资产服务机构不能为空");
			return ;
		}
		if(originator == null||originatorNameLength == 0){
			showAlertWin("原始权益人不能为空");
			return ;
		}
		if(serviceAgency == null || serviceAgencyNameLength == 0){
			showAlertWin("资产服务机构不能为空");
			return ;
		}
		
		postData.originators =originator;
		postData.serviceAgencys =serviceAgency;
		postData.id =$('#specialProgramId').val();
		postData.specialProgramName =$("#specialProgramName").val();
		postData.underlyingAssetType =$("#underlyingAssetType").val();
		postData.estimateEstablishmentDate =$('#estimateEstablishmentDate').val();
		postData.status =$("#status").val();
		postData.managerName =$('#managerName').val();
		postData.lawFirmName =$('#lawFirmName').val();
		postData.accountingFirmName =$('#accountingFirmName').val();
		postData.supervisionBank =$('#supervisionBank').val();
		postData.custodianBank =$('#custodianBank').val();
		postData.ratingAgency =$('#ratingAgency').val();
		postData.assetEvaluationAgency =$('#assetEvaluationAgency').val();
		postData.businessLicenceCode =$('#businessLicenceCode').val();
		postData.businessLicenceCodeExpiryDate =$('#businessLicenceCodeExpiryDate').val();
		postData.taxRegistrationCertificateCode =$('#taxRegistrationCertificateCode').val();
		postData.taxRegistrationCertificateCodeExpiryDate =$('#taxRegistrationCertificateCodeExpiryDate').val();
		postData.organizationCode =$('#organizationCode').val();
		postData.organizationCodeExpiryDate =$('#organizationCodeExpiryDate').val();
		
		$.ajax({
		    type : "POST",
			url : basepath + "/asset/specialprogram/updataSpecialProgramFormData",
			data :  JSON.stringify(postData),
			dataType : 'json',
			contentType:'application/json;charset=utf-8',
			success : function(data,status) {
				if(data.status =="SUCCESS"){
					tipPopWindow.open().center();
					$(".k-window-actions").remove();
					$("#tip-box-window .dlg-notice").addClass("notice-icon02");
					$("#tip-box-window .notice-content").text("修改成功");
					
				}else {
					popWindow.open().center();
					$("#tip-box-window .dlg-notice").addClass("notice-icon01");
					$("#tip-box-window .notice-content").text(data.message);
				}
			},
			error : function(e){
				showAlertWin("修改专项计划异常");
			}
		});
	});
});



