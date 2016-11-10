function isCompanyNameExist(name, url) {
	var result = $.ajax({
		url: basepath + "/asset/" + url,
		async: false,
		type: 'post',
		data: {
			name: name,
		}
	}).responseText;
	if(result == "true")
		flag = false;
	else
		flag = true;
	return flag;
};
function bindEvent() {
	setValidator($("#name"), "required", true, nameRule.required);
	setValidator($("#coreCompanyName"),"required", true, coreCompanyNameRule.required);
	setValidator($("#extensionDays"), "pattern", extensionDaysRule.pattern, extensionDaysRule.message);
	setValidator($("#coreOrganizationCode"), "required", true, coreOrganizationCodeRule.required);
	setValidator($("#coreBusinessLicenceCode"), "required", true, coreBusinessLicenceCodeRule.required);
	setValidator($("#deductionTime"), "required", true, deductionTimeRule.required);
	setValidator($("#deductionTime"), "pattern", deductionTimeRule.pattern, deductionTimeRule.message);
	setValidator($("#earlyRepaymentChargeRatio"), "required", true, earlyRepaymentChargeRatioRule.required);
	setValidator($("#earlyRepaymentChargeRatio"), "pattern", earlyRepaymentChargeRatioRule.pattern, earlyRepaymentChargeRatioRule.message);
}


$(function() {
	$("body").on("change", "#factorCompanyName", function(){
		if($("#factorCompanyName").val() != ""){
			$("#factorCompanyName").blur();
		}
	});
	
	//回车按钮事件
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	if(evt.keyCode==13){
    		$('.form-search-link').click();
    	}
    });
	
	//form表单校验
	bindEvent();
	var validator = $("#createProject").kendoValidator().data("kendoValidator");
	//点击提交
	$("#submit").click(function() {
		var validator = $("#createProject").data("kendoValidator");
			if(!validator.validate()) {
				return;
			};
		
		var postData = {};
		var projectRequest= [];
		var projectDocument= [];
		var repaymentMethods ="";
		var length = $('[name=companyName]').length;
		var length1 =$('[name=name]').length;
		
		for(var i=0; i< length; i++) {
			projectRequest.push({companyName: $($('[name=companyName]')[i]).text(), organizationCode: $($('[name=organizationCode]')[i]).text(),
				                businessLicenceCode:$($('[name =businessLicenceCode]')[i]).text(),
				                taxRegistrationCertificateCode:$($('[name =taxRegistrationCertificateCode]')[i]).text(),
				                accountNo:$($('[name =accountNo]')[i]).text().replace(/ /g,''),
				                status:$($('[name =factorStatus]')[i]).next().val()
				                });
			}
		var length1 =$('[name=version]').length;
		for (var j= 0; j < length1; j++) {
			projectDocument.push({version: $($('[name=version]')[j]).val(),name: $($('[name=documentName]')[j]).val(),mediaId: $($('[name=mediaId]')[j]).val()});
		}
		if(projectRequest ==null||projectRequest.length==0){
			return openNews("消息提示：","请添加资金方信息！","2");
		}
		postData.projectFactors = projectRequest;
		postData.projectDocuments = projectDocument;
		postData.name = $('#name').val();
		postData["type"] =$('#style').val(); 
		postData.status =$('#status').val(); 
		postData.coreCompanyName =$('#coreCompanyName').val(); 
		postData.coreAccountNo =$('#coreAccountNo').val().replace(/ /g,''); 
		postData.coreOrganizationCode =$('#coreOrganizationCode').val(); 
		postData.coreIndustry =$('#coreIndustry').val(); 
		postData.coreBusinessLicenceCode =$('#coreBusinessLicenceCode').val(); 
		postData.coreEconomicCategory =$('#coreEconomicCategory').val(); 
		postData.coreTaxRegistrationCertificateCode =$('#coreTaxRegistrationCertificateCode').val(); 
		postData.financeApplicant =$('#financeApplicant').val(); 
		postData.singleLoanLimitLower =$('#singleLoanLimitLower').val(); 
		postData.singleLoanLimitUpper =$('#singleLoanLimitUpper').val(); 
		postData.interestRateLower=$('#interestRateLower').val(); 
		postData.interestRateUpper =$('#interestRateUpper').val(); 
		postData.interestRateUnit =$('#interestRateUnit').val(); 
		postData.loanPeriodLower =$('#loanPeriodLower').val(); 
		postData.loanPeriodUpper =$('#loanPeriodUpper').val(); 
		postData.loanPeriodUnit =$('#loanPeriodUnit').val(); 
		$('input[name=repaymentMethods]:checked').each(function() {
			repaymentMethods += $(this).val();
			repaymentMethods+=",";
		});
		postData.repaymentMethods=repaymentMethods.substring(0,repaymentMethods.length-1);
		postData.useHolidayPolicy = $('#useHolidayPolicy').val() == 'true';
		postData.extensionDays =$('#extensionDays').val(); 
		postData.earlyRepaymentChargeRatio =$('#earlyRepaymentChargeRatio').val(); 
		postData.extensionRatioMethod =$("input[name='extensionRatioMethod']:checked").val(); 
		postData.extensionRatio =$('#extensionRatio').val(); 
		postData.penaltyRateMethod =$("input[name='penaltyRateMethod']:checked").val(); 
		postData.penaltyRate =$('#penaltyRate').val(); 
		postData.deductionTime =$('#deductionTime').val(); 
		postData.deductionRule =$("input[name='deductionRule']:checked").val();
		postData.productIntroduction =$('#productIntroduction').val(); 
		postData.productTypeDesc =$('#productTypeDesc').val(); 
		
		$.ajax({
		    type : "POST",
			url : basepath + "/asset/project/projectCreateFormDate",
			data :  JSON.stringify(postData),
			dataType : 'json',
			contentType:'application/json;charset=utf-8',
			success : function(data) {
				if(data.status==true){
					openNewsTwo("消息提示：","项目新增成功！","1");
				}else{
					for(var key in data.errorInfos){
						openNewsUp("消息提示：",data.errorInfos[key],"2");
						};
				}
			}
		});

	});
	
//判断上传是否发生改变
	$("body").delegate(".fileBox", "change", function(){
		fileUpload(this);
    });
	
});
//合同上传
function fileUpload(param) {
	$.ajaxFileUpload({
	    url: 'saveFile',
	    type: 'post',
	    secureuri: false, //一般设置为false
	    fileElementId: $(param).attr('id'), // 上传文件的id属性名
	    dataType: 'text', //返回值类型，一般设置为json、application/json
	    success: function(data){
	    	if(data == "FAILED"){
	    		openNews("消息提示：","上传失败！","2");	
	    	}else{
	    		openNews("消息提示：","上传成功！","1");
	    		var d = new Date(); 
	    		$(param).parent().parent().siblings().find(".version-class").val(d.toJSON().substr(0,10));
	    		$(param).parent().next().attr("href",mediaserver + 'imageservice?mediaImageId=' + data + '&mediaType=download');
	    		$(param).next().val(data);
	    		$(param).parent().siblings().removeClass("close-btn");

	    	}
	    },
	    error: function(data, status, e){
	    	openNews("消息提示：","上传出错！","3");
	    }
	});

};



function openNews(title, content,state) {
	$(".txt-title").html(title);
	$(".txt-cot").html(content);
	if(state==2){　//失败
        $(".popup-img-one").addClass("notice-icon03").show(); 
    }else if(state==1){  //成功
        $(".popup-img-one").addClass("notice-icon02").show();        
    }else if(state==3){  //感叹号
        $(".popup-img-one").addClass("notice-icon01").show(); 
    }
	$(".xp-out").show();
}
function openNewsUp(title, content,state) {
	$(".txt-title-up").html(title);
	$(".txt-cot-up").html(content);
	if(state==2){　//失败
        $(".popup-img-up").addClass("notice-icon03").show(); 
    }else if(state==1){  //成功
        $(".popup-img-up").addClass("notice-icon02").show();        
    }else if(state==3){  //感叹号
        $(".popup-img-up").addClass("notice-icon01").show(); 
    }
	$(".xp-out-up").show();
}
function openNewsTwo(title, content,state) {
	$(".txt-title-two").html(title);
	$(".txt-cot-two").html(content);
	if(state==2){　//失败
        $(".popup-img-two").addClass("notice-icon03").show(); 
    }else if(state==1){  //成功
        $(".popup-img-two").addClass("notice-icon02").show();        
    }else if(state==3){  //感叹号
        $(".popup-img-two").addClass("notice-icon01").show(); 
    }
	$(".xp-out-success").show();
}
function closeNews() {
	$(".xp-out").hide();
	$(".txt-title").html("");
	$(".popup-img-one").removeClass("notice-icon01").removeClass("notice-icon02").removeClass("notice-icon03").hide();
	$(".txt-cot").html("");
}
function closeNewsTwo() {
	$(".xp-out-success").hide();
	$(".txt-title-two").html("");
	$(".popup-img-two").removeClass("notice-icon01").removeClass("notice-icon02").removeClass("notice-icon03").hide();
	$(".txt-cot-two").html("");
	window.location.href=basepath +"/asset/project/projectList"; 
}
function closeNewsUp() {
	$(".xp-out-up").hide();
	$(".txt-title-up").html("");
	$(".popup-img-up").removeClass("notice-icon01").removeClass("notice-icon02").removeClass("notice-icon03").hide();
	$(".txt-cot-up").html("");
}




