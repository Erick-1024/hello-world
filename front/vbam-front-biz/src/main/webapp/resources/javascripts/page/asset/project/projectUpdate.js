var PAGESIZE = 10;
$(function() {
	var id=$("#projectId").val();
	$.ajax({
	    type : "POST",
	    url : basepath + "/asset/project/projectDetails",
	    data :  {projectId:id },
	    dataType : "json",
		success : function(data) {
			$.each(data.data,function(idx,obj){
			    var $tr=$("#tab tr").eq(-1);
			    var id=idx+1;
			    var html = $('<tr class="top-list-two">' +
			                '<th><input type="checkbox" class="input-new" name="ckb"></th>' +
			                '<td>'+id+'</td>' +
			                '<td><span name="companyName">'+obj.companyName+'</span><input type="hidden" name="projectFactors['+id+'].companyName" value="'+obj.companyName+'"></td>' +
			                '<td><span name="organizationCode">'+obj.organizationCode+'</span><input type="hidden" name="projectFactors['+id+'].organizationCode" value="'+obj.organizationCode+'"></td>' +
			                '<td><span name="businessLicenceCode">'+obj.businessLicenceCode+'</span><input type="hidden" name="projectFactors['+id+'].businessLicenceCode" value="'+obj.businessLicenceCode+'"></td>' +
			                '<td><span name="taxRegistrationCertificateCode">'+obj.taxRegistrationCertificateCode+'</span><input type="hidden" name="projectFactors['+id+'].taxRegistrationCertificateCode" value="'+obj.taxRegistrationCertificateCode+'"></td>' +
			                '<td><span name="accountNo">'+obj.accountNo+'</span><input type="hidden" name="projectFactors['+id+'].accountNo" value="'+obj.accountNo+'"></td>' +
			                '<td><span name="factorStatus">'+obj.status+'</span><input class="status-x" type="hidden" name="projectFactors['+id+'].status" value=""></td>' +
			                '</tr>');
			    if(obj.status=="正常"){
			    	html.find(".status-x").val("NORMAL");
			    }else{
			    	html.find(".status-x").val("PAUSE");
			    }
			    
			    $tr.after(html);
			})
			
		}
	});
	
});

//项目修改js
$(function() {
	//回车按钮事件
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	if(evt.keyCode==13){
    		$('.form-search-link').click();
    	}
    });
	bindEvent();
	//项目进行中或者着暂停form表单校验
	var validator = $("#updateProject").kendoValidator().data("kendoValidator");
	//根据projectDTO.status给相应的标签加上disabled ="disabled" ,当projectDTO.status=="going or pause 给资金方删除按钮加上隐藏属性"
	var status=$("#status").val();
	if(status =="GOING" ||status =="PAUSE"){
			$("#name").attr("readonly","readonly");
			$("#name").css("cssText","border:1px solid #fff !important;outline:none");
			$("#style").attr("disabled","disabled");
			$("#coreCompanyName").attr("readonly","readonly");
			$("#coreAccountNo").attr("readonly","readonly");
			$("#coreOrganizationCode").attr("readonly","readonly");
			$("#coreBusinessLicenceCode").attr("readonly","readonly");
			$("#coreTaxRegistrationCertificateCode").attr("readonly","readonly");
			//项目进行中和暂停资金方删除按钮隐藏
			$("#factor-remove").hide();
		}
		else if(status =="CLOSE"){
			//项目结束所有input元素加数据不能修改
			$(".all-project-class").attr("readonly","readonly").css("border",0).css("background","#EBEBE4");
			$(".input-radio").attr("disabled", true);
			$(".input-new").attr("disabled", true);
			
			$("[data-role='dropdownlist']").kendoDropDownList({
	            enable: false
	        });
			$(".k-state-disabled").css("border",0).css("background","#EBEBE4");
	        $(".k-select").css("display","none");
			//项目结束时候将资金方新增、修改、删除按钮和合同模板隐藏
			$(".project-hide-class").hide();
			//去掉合同下载隐藏属性
			$(".document-download").removeAttr("style");
		}
		else{
			//项目立项的时候
			$("document-download").hide();
		}
	//如果项目是进行中
	if(status =="GOING"){
		//删除项目状态立项
		$("#status-list .k-item").eq(0).hide();
	}
	//点击提交按钮
	$("#submit").click(function() {
		var validator = $("#updateProject").data("kendoValidator");
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
				                status:$($('[name =factorStatus]')[i]).next().val(),
				                id:$($('[name =factorId]')[i]).val()
				                });
			}
		var length1 =$('[name=version]').length;
		for (var j= 0; j < length1; j++) {
			projectDocument.push({version: $($('[name=version]')[j]).val(),name: $($('[name=documentName]')[j]).val(),mediaId: $($('[name=mediaId]')[j]).val()});
		}
		
		$("input[name='repaymentMethods']:checked").each(function() {
			repaymentMethods += $(this).val();
			repaymentMethods+=",";
		});	
		postData.repaymentMethods=repaymentMethods.substring(0,repaymentMethods.length-1);
		postData.projectFactors = projectRequest;
		postData.projectDocuments = projectDocument;
		postData.id=$("#projectId").val();
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
			url : basepath + "/asset/project/projectUpdateForm",
			data :  JSON.stringify(postData),
			dataType : 'json',
			contentType:'application/json;charset=utf-8',
			success : function(data) {
				if(data.status==true){
					openNewsTwo("消息提示：","项目修改成功！","1");
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
	    	openNews("消息提示：","上传出错！","2");
	    }
	});

};

function bindEvent() {
	setValidator($("#extensionDays"),"required", true, extensionDaysRule.required)
	setValidator($("#extensionDays"),"pattern", extensionDaysRule.pattern, extensionDaysRule.message)
	setValidator($("#coreCompanyName"),"required", true, coreCompanyNameRule.required);
	setValidator($("#coreOrganizationCode"), "required", true, coreOrganizationCodeRule.required);
	setValidator($("#deductionTime"), "required", true, deductionTimeRule.required);
	setValidator($("#deductionTime"), "pattern", deductionTimeRule.pattern, deductionTimeRule.message);
}
//提示消息框设置
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
	$(".txt-cot").html("");
}
function closeNewsTwo() {
	$(".xp-out-success").hide();
	$(".txt-title-two").html("");
	$(".txt-cot-two").html("");
	window.location.href=basepath +"/asset/project/projectList"; 
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
function closeNewsUp() {
	$(".xp-out-up").hide();
	$(".txt-title-up").html("");
	$(".popup-img-up").removeClass("notice-icon01").removeClass("notice-icon02").removeClass("notice-icon03").hide();
	$(".txt-cot-up").html("");
}


