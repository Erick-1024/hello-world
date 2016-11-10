function isValueExist(value, url,id) {
	var result = $.ajax({
		url: basepath + "/asset/customer/" + url,
		async: false,
		type: 'post',
		data: {
			value: value
		}
	}).responseText;
	if(result == "true")
		flag = false;
	else
		flag = true;
	return flag;
};

var submitFlag = true;

function bindEvent() {
	setValidator($("#customerName"), "required", true, customerNameRule.required);
}

$(function() {
	
	//回车按钮事件
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	
    	if(evt.keyCode==13){
    		if($(".k-overlay").is(':hidden')==true){
    			$("#submit").click();
    		}else{
    			$("#customer-stk").click();
    			$("#magid-btn").click();
    			$("#purchase-btn").click();
	    		$("#sell-btn").click();
				$("#five-btn").click();
    		}
    	}
    });

	
	//表单校验
	bindEvent()
	var validator = $("#createCustomer").kendoValidator({
		rules: {
				customerNameIsExistRule: customerNameRule.rule,
		},
		messages: {
			customerNameIsExistRule: customerNameRule.ruleMessage,
		},
		needRuleAttrbute : false
	}).data("kendoValidator");
	
	//点击提交
	$("#submit").click(function() {
		if(submitFlag){
    		submitFlag = false;
		if(!validator.validate()) {
			return;
		};
		
		var postData = {};
		var customerSkt =[];
		var customerMag =[];
		var customerPurchase =[];
		var customerSale =[];
		var customerMas =[];
		var stkTypeLength = $('[name=stkType]').length;
		var magNameLength = $('[name=magName]').length;
		var supplierNameLength = $('[name=supplierName]').length;
		var saleCustomerNameLength = $('[name=saleCustomerName]').length;
		var financialInstitutionNameLength = $('[name=financialInstitutionName]').length;
		//股东类型
		for(var i=0; i< stkTypeLength; i++) {
			customerSkt.push({stkType: $($('[name=stkType]')[i]).next().val(), stkName: $($('[name=stkName]')[i]).text(),
				stkPayamt:$($('[name =stkPayamt]')[i]).text().parseMoney(),
				stkPcnt:$($('[name =stkPcnt]')[i]).text(),
				stkPayamtType:$($('[name =stkPayamtType]')[i]).next().val(),
				stkStatus:$($('[name =stkStatus]')[i]).next().val()});
			}
		//高管
		for(var i=0; i< magNameLength; i++) {
			customerMag.push({magName: $($('[name=magName]')[i]).text(), magSex: $($('[name=magSex]')[i]).text(),
				magIdentityCardNo:$($('[name =magIdentityCardNo]')[i]).text(),
				magDutyType:$($('[name =magDutyType]')[i]).text(),
				magEducation:$($('[name =magEducation]')[i]).next().val(),
				magProfession:$($('[name =magProfession]')[i]).next().val(),
				experience:$($('[name =experience]')[i]).text()});
			}
		//采购
		for(var i=0; i< supplierNameLength; i++) {
			customerPurchase.push({supplierName: $($('[name=supplierName]')[i]).text(), purchaseLastYear: $($('[name=purchaseLastYear]')[i]).text().parseMoney(),
				percent:$($('[name =percent]')[i]).text(),
				settleType:$($('[name =settleType]')[i]).next().val(),
				accountPayableBalance:$($('[name =accountPayableBalance]')[i]).text().parseMoney()});
			}
		//销售
		for(var i=0; i< saleCustomerNameLength; i++) {
			customerSale.push({saleCustomerName: $($('[name=saleCustomerName]')[i]).text(), saleLastYear: $($('[name=saleLastYear]')[i]).text().parseMoney(),
				percent:$($('[name =percent]')[i]).text(),
				cooperationPeriod:$($('[name =cooperationPeriod]')[i]).text(),
				accountReceivableBalance:$($('[name =accountReceivableBalance]')[i]).text().parseMoney()});
			}
		//融资
		for (var i=0; i< financialInstitutionNameLength;i++){
			customerMas.push({financialInstitutionName: $($('[name=financialInstitutionName]')[i]).text(), amount: $($('[name=amount]')[i]).text().parseMoney(),
				startDate:$($('[name =startDate]')[i]).text(),
				endDate:$($('[name =endDate]')[i]).text(),
				guaranteeType:$($('[name =guaranteeType]')[i]).text(),
				remark:$($('[name =remark]')[i]).text()});
		}
		
		postData.customerStks = customerSkt;
		postData.customerMags = customerMag;
		postData.customerPurchases = customerPurchase;
		postData.customerSales = customerSale;
		postData.customerMass = customerMas;
		
		postData.customerName =$('#customerName').val();
		postData.customerType =$("#customerType").val();
		postData.contactName =$("#contactName").val();
		postData.mobileNo =$('#mobileNo').val();
		postData.mail =$('#mail').val();
		postData.companyAddress =$('#companyAddress').val();
		postData.economicCategory =$('#economicCategory').val();
		postData.industry =$('#industry').val();
		postData.city =$('#city').val();
		postData.legalRepresentative =$('#legalRepresentative').val();
		postData.registeredCapital =$('#registeredCapital').val().parseMoney();
		postData.businessLicenceCode =$('#businessLicenceCode').val();
		postData.businessLicenceCodeExpiryDate =$('#businessLicenceCodeExpiryDate').val();
		postData.taxRegistrationCertificateCode =$('#taxRegistrationCertificateCode').val();
		postData.taxRegistrationCertificateCodeExpiryDate =$('#taxRegistrationCertificateCodeExpiryDate').val();
		postData.organizationCode =$('#organizationCode').val();
		postData.organizationCodeExpiryDate =$('#organizationCodeExpiryDate').val();
		
		
		$.ajax({
		    type : "POST",
			url : basepath + "/asset/customer/customerCreateFormDate",
			data :  JSON.stringify(postData),
			dataType : 'json',
			contentType:'application/json;charset=utf-8',
			success : function(data,status) {
				if(data.status =="SUCCESS"){
					$(".suc-message-btn").click();
					$(".client-x-txt").html(data.message);
				}else {
					submitFlag = true;
					$(".client-message").click();
					$(".client-x-txt").html(data.message);
				}
			},
			error : function(e){
				submitFlag = true;
				$(".client-x-txt").html("未知异常联系管理员");
			}
		});
		}
	});
});






