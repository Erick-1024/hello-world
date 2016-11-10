$(function(){
    //单选，选择预警种类
    $(".war-out").click(function(){
        $(this).addClass("war-on").siblings().removeClass("war-on");
        var c_index=$(this).index();
        var c_input=$(this).parent().find('input');
        c_input.val(c_index);
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
	
// 限定输入的只能是钱(可以为负值)
	$(".onlyMoney").keypress(function(event) {
		var code = event.which;
		if ((code <= 57 && code >= 48) || (code == 8) || (code == 0) ||(code == 46) ||(code == 45)) {
			return true;
		} else {
			return false;
		}
	}).focus(function(){
		this.style.imeMode='disabled';
	});
	
// 输入框校验
	bindEvent();
	
	
	//文件input
	$("#additionInformationMedia").change(function() {
		fileUpload($(this).attr('id'), $(this).val());
		$(".upadditionInfo").parent().find(".echo").val("1");
		$(".upadditionInfo").parent().find(".k-invalid-msg").remove();
	});
	
	
	//三级联动
	
	$("body").on('change', '#bankProvince', function(){
		cityLoad();
	});
	$("body").on('change', '#payAccountBankName', function(){
		branchLoad();
	});
	
	// 加载城市列表
	initCityDropDownList();
	// 加载支行名称
	initBranchDropDownList();
	
	 $(".k-combobox .k-select").click(function(){
			$.ajax({
				url:basepath + "/yundaex/audit/queryBranches",
				dataType:"json",
				type:"post",
				data:{
					bankName: $.trim($(".payAccountBankName").val()),
					province: $.trim($(".bankProvince").val()),
					city: $.trim($(".bankCity").val())
				},
				success:function(data){
					//$("#lianHangNo").options.success(data);
					branchLoad();
				}
			});
		});
	 /*	 $(".k-dropdown-wrap .k-select").click(function(){
	 $.ajax({
		 url:basepath + "/yundaex/audit/queryBranches",
		 dataType:"json",
		 type:"post",
		 data:{
			 bankName: $.trim($(".payAccountBankName").val()),
			 province: $.trim($(".bankProvince").val()),
			 city: $.trim($(".bankCity").val())
		 },
		 success:function(data){
			 //$("#lianHangNo").options.success(data);
			 branchLoad();
		 }
	 });
 });
*/	
	
	$("#payAccountBankName").change(function(){
		$(".payAccountBankName").val($(".bankName").find(".k-input").html());
	});
	
	$("#bankProvince").change(function(){
		$(".bankProvince").val($(".bankProvince").find(".k-input").html());
	});
	
	$("#bankCity").change(function(){
		$(".bankCity").val($(".bankCity").find(".k-input").html());
	});
	
	$(".lianHangNo").change(function(){
		$("#payAccountAddress").val($(".k-combobox .k-input").val());
	});
	/*$(".lianHangNo").change(function(){
	$("#payAccountAddress").val($(".lianHangNo").find(".k-input").html());
    });*/
	
	$(".choose-one").on("click",function(){
	       var controllerIsLegal =  $(this).find("input[name=controllerIsLegal]").val();
	        if(controllerIsLegal==$("#controllerIsLegal0").val()){
	            $(".hideLegal").show();
	            setValidator($("#legalPerson"), "required", true, legalPersonRule.required);
				setValidator($("#legalEmail"), "required", true, legalEmailRule.required);
				setValidator($("#legalEmail"), "pattern", legalEmailRule.pattern, legalEmailRule.message);
				setValidator($("#legalPhone"), "required", true, legalPhoneRule.required);
				setValidator($("#legalPhone"), "pattern", legalPhoneRule.pattern, legalPhoneRule.message);
	            
	        }else{
	            $(".hideLegal").hide();
	            setValidator($("#legalPerson"), "required", false, legalPersonRule.required);
				setValidator($("#legalEmail"), "required", false, legalEmailRule.required);
				$("#legalEmail").removeAttr("pattern");
				setValidator($("#legalPhone"), "required", false, legalPhoneRule.required);
				$("#legalPhone").removeAttr("pattern");
	        }
	    });
	$(".choose-two").on("click",function(){
        var accountOwner =  $(this).find("input[name=accountOwner]").val();
        if(accountOwner==$("#OTHER").val()){
            $(".hideAccountOwner").show();
            setValidator($("#accountOwnerName"), "required", true, accountOwnerNameRule.required);
			setValidator($("#accountOwnerEmail"), "required", true, accountOwnerEmailRule.required);
			setValidator($("#accountOwnerEmail"), "pattern", accountOwnerEmailRule.pattern, accountOwnerEmailRule.message);
			setValidator($("#accountOwnerPhone"), "required", true, accountOwnerPhoneRule.required);
			setValidator($("#accountOwnerPhone"), "pattern", accountOwnerPhoneRule.pattern, accountOwnerPhoneRule.message);
        }else{
            $(".hideAccountOwner").hide();
            setValidator($("#accountOwnerName"), "required", false, accountOwnerNameRule.required);
			setValidator($("#accountOwnerEmail"), "required", false, accountOwnerEmailRule.required);
			$("#accountOwnerEmail").removeAttr("pattern");
            setValidator($("#accountOwnerPhone"), "required", false, accountOwnerPhoneRule.required);
            $("#accountOwnerPhone").removeAttr("pattern");
        }
    });
    $(".choose-three").on("click",function(){
        var whetherTbOrder =  $(this).find("input[name=whetherTbOrder]").val();
        if(whetherTbOrder==$("#whetherTbOrder1").val()){
            $(".hideTbOrderRatio").show();
            setValidator($("#tbOrderRatio"), "required", true, tbOrderRatioRule.required);
        }else{
            $(".hideTbOrderRatio").hide();
            setValidator($("#tbOrderRatio"), "required", false, tbOrderRatioRule.required);
        }
    });
    
  //按钮  上传资料等信息的回显
	dataEcho();
});

	
	
function dataEcho(){

	if($(".payAccountBankName").val() !=""){
		$(".bankName").find(".k-input").html($(".payAccountBankName").val());
	}
	if($(".bankProvince").val() !=""){
		$(".bankProvince").find(".k-input").html($(".bankProvince").val());
	}
	if($(".bankCity").val() !=""){
		$(".bankCity").find(".k-input").html($(".bankCity").val());
	}	
	if($("#payAccountAddress").val() != ""){
		$(".k-combobox .k-input").val(($("#payAccountAddress").val()));
	}
	/*if($("#payAccountAddress").val() != ""){
		$("#payAccountAddress").parent().find(".k-input").html($("#payAccountAddress").val());
	}*/
	
	if($(".controllerOrigin").val() !=""){
		$("#controllerOrigin option[value='"+$(".controllerOrigin").val()+"']").attr("selected",true);
	}

	if($(".controllerIsLegal").val() ==$("#controllerIsLegal1").val()){
		$("#controllerIsLegal1").attr("checked",true);
	}
	if($(".controllerIsLegal").val() ==$("#controllerIsLegal0").val()){
		$("#controllerIsLegal0").attr("checked",true);
		$(".hideLegal").show();
	}
	$("#"+$(".accountOwner").val()).attr("checked",true);
	if($(".accountOwner").val() == "OTHER"){
		$(".hideAccountOwner").show();
	}

	if($(".whetherTbOrder").val() == $("#whetherTbOrder1").val()){
		$("#whetherTbOrder1").attr("checked",true);
		$(".hideTbOrderRatio").show();
	}
	if($(".whetherTbOrder").val() == $("#whetherTbOrder0").val()){
		$("#whetherTbOrder0").attr("checked",true);
	}
	if($("#organizationMediaId1").val()!= ""){
		var notice = "<a class='in-up-txt2' id='organizationMediaId' style='color:#0f8aba;' href=" + mediaserver + "imageservice?mediaImageId=" + $("#organizationMediaId1").val() + "&mediaType=video" + " target='_blank'>查看</a>";
		$("#organizationMedia").parent().find(".tageNotice").append(notice);
		$("#organizationMedia").parent().find(".echo").val("2");
	}
	if($("#businessLicenceMediaId1").val()!= ""){
		var notice = "<a class='in-up-txt2' id='businessLicenceMediaId' style='color:#0f8aba;' href=" + mediaserver + "imageservice?mediaImageId=" + $("#businessLicenceMediaId1").val() + "&mediaType=video" + " target='_blank'>查看</a>";
		$("#businessLicenceMedia").parent().find(".tageNotice").append(notice);
		$("#businessLicenceMedia").parent().find(".echo").val("2");
	}
	if($("#taxRegistrationCertificateMediaId1").val()!= ""){
		var notice = "<a class='in-up-txt2' id='taxRegistrationCertificateMediaId' style='color:#0f8aba;' href=" + mediaserver + "imageservice?mediaImageId=" + $("#taxRegistrationCertificateMediaId1").val() + "&mediaType=video" + " target='_blank'>查看</a>";
		$("#taxRegistrationCertificateMedia").parent().find(".tageNotice").append(notice);
		$("#taxRegistrationCertificateMedia").parent().find(".echo").val("2");
	}
	if($("#legalIdnoFrontMediaId1").val()!= ""){
		var notice = "<a class='in-up-txt2' id='legalIdnoFrontMediaId' style='color:#0f8aba;' href=" + mediaserver + "imageservice?mediaImageId=" + $("#legalIdnoFrontMediaId1").val() + "&mediaType=video" + " target='_blank'>查看</a>";
		$("#legalIdnoFrontMedia").parent().find(".tageNotice").append(notice);
		$("#legalIdnoFrontMedia").parent().find(".echo").val("2");
	}
	if($("#legalIdnoBackMediaId1").val()!= ""){
		var notice = "<a class='in-up-txt2' id='legalIdnoBackMediaId' style='color:#0f8aba;' href=" + mediaserver + "imageservice?mediaImageId=" + $("#legalIdnoBackMediaId1").val() + "&mediaType=video" + " target='_blank'>查看</a>";
		$("#legalIdnoBackMedia").parent().find(".tageNotice").append(notice);
		$("#legalIdnoBackMedia").parent().find(".echo").val("2");
	}
	if($("#additionInformationMediaId").val()!=""){
		$(".upadditionInfo").parent().after('<div class="left-f removeUse"><span class="in-up-txt3">上传成功</span></div>');
		$(".upadditionInfo").parent().find(".echo").val("2");
	}
}

//加载城市信息
function cityLoad(){
	cityList = $("#bankCity").data("kendoDropDownList")
	cityList.enable(false);
	cityList.dataSource.read();
	cityList.enable(true);
}
//加载支行名称信息
function branchLoad(){
	var bankName = $.trim($(".payAccountBankName").val());
	var province = $.trim($(".bankProvince").val());
	var city = $.trim($(".bankCity").val());
	if(null != bankName && "" != bankName && null != province && "" != province && null != city && "" != city ){
		branchList = $("#lianHangNo").data("kendoComboBox")
		branchList.enable(false);
		branchList.dataSource.read({
			bankName: $.trim($(".payAccountBankName").val()),
			province: $.trim($(".bankProvince").val()),
			city: $.trim($(".bankCity").val())
		});
		branchList.enable(true);
	}
}

function initCityDropDownList() {
	var cityDataSource = new kendo.data.DataSource({
    	transport: {
    		read:function(options){
    			$.ajax({
    				url:basepath + "/yundaex/audit/queryCities",
    				dataType:"json",
    				type:"post",
    				data:{province: $.trim($(".bankProvince").val())},
    				success:function(data){
    					options.success(data);
    					branchLoad();
    				}
    			});
    		}
	
        },
        schema:{
        	data: "data"
        }
	});
	
	var dropDown = $("#bankCity").kendoDropDownList({
		autoBind: false,
		optionLabel: "--请选择城市名称--",
		dataTextField: 0,
        dataValueField: 0,
        change: branchLoad,
        dataSource: cityDataSource
	});
	dropDown.select();
}
function initBranchDropDownList(){
	var branchDataSource = new kendo.data.DataSource({
		transport: {
			read: {
				dataType: "json",
				type: "post",
				asyn: false,
				url: basepath + "/yundaex/audit/queryBranches"
			}
		},
		schema:{
			data: "data"
		}
	});
	
	$("#lianHangNo").kendoComboBox({
		dataTextField: "branchName",
		dataValueField: "lianHangNo",
		dataSource: branchDataSource,
		filter: "contains",
		suggest : true

	});
	
	/*$("#lianHangNo").kendoDropDownList({
		autoBind: false,
		optionLabel: "--请选择支行名称--",
		dataTextField: "branchName",
		dataValueField: "lianHangNo",
		dataSource: branchDataSource,
		filter: "contains"
	});*/
}


//上传补充资料
function fileUpload(name,fileName) {
	$(".removeUse").remove();
	$.ajaxFileUpload({
	    url: 'saveFile',
	    type: 'post',
	    secureuri: false, //一般设置为false
	    fileElementId: name, // 上传文件的id、name属性名
	    dataType: 'text', //返回值类型，一般设置为json、application/json
	    success: function(data){
	    	if(data == "FAILED"){
	    		$("#additionInformationMediaId").val("");
	    		openPop("上传资料信息", "图片上传失败");
	    	}else{
	    		$(".upload-warning").remove();
	    		/*$(".upadditionInfo").after('<div class="look-img-two"><span class="look-img-title" mediaId="' + data + '">' + fileName + '</span><a class="status-normal status-chk fly-left" href="' + mediaserver + 'imageservice?mediaImageId=' + data + '&mediaType=download">查看</a><a style="width:30px" class="status-normal status-chk fly-left delete" href="javascript:void(0);"><i class="delIcon"></i></a></div>');*/
	    		$(".upadditionInfo").parent().after('<div class="in-up-name left-f removeUse">' + fileName + '</div><div class="left-f removeUse"><span class="in-up-txt3">上传成功</span></div>');
	    		$("#additionInformationMediaId").val(data);
	    	}
	    },
	    error: function(data, status, e){
	    	openPop("上传资料信息", data);
	    }
	});
}
    
function bindEvent() {
	setValidator($("#payAccount"), "required", true, payAccountRule.required);
	setValidator($("#payAccountName"), "required", true, payAccountNameRule.required);
	setValidator($("#payAccountAddress"), "required", true, payAccountAddressRule.required);
	setValidator($("#custEmail"), "required", true, custEmailRule.required);
	setValidator($("#custEmail"), "pattern", custEmailRule.pattern, custEmailRule.message);
	setValidator($("#controller"), "required", true, controllerRule.required);
//	setValidator($("#controllerOrigin"), "required", true, controllerOriginRule.required);
	setValidator($("#controllerIdno"), "required", true, controllerIdnoRule.required);
	setValidator($("#controllerIdno"), "pattern", controllerIdnoRule.pattern, controllerIdnoRule.message);
	setValidator($("#controllerEmail"), "required", true, controllerEmailRule.required);
	setValidator($("#controllerEmail"), "pattern", controllerEmailRule.pattern, controllerEmailRule.message);
	setValidator($("#controllerPhone"), "required", true, controllerPhoneRule.required);
	setValidator($("#controllerPhone"), "pattern", controllerPhoneRule.pattern, controllerPhoneRule.message);
	setValidator($("#stationAmount"), "required", true, stationAmountRule.required);
	setValidator($("#agentQualification"), "required", true, agentQualificationRule.required);
	setValidator($("#organizationNo"), "required", true, organizationNoRule.required);
	setValidator($("#businessLicenceNo"), "required", true, businessLicenceNoRule.required);
	setValidator($("#taxRegistrationCertificateNo"), "required", true, taxRegistrationCertificateNoRule.required);
	setValidator($("#legalIdno"), "required", true, legalIdnoRule.required);
	
	setValidator($("#legalIdno"), "pattern", legalIdnoRule.pattern, legalIdnoRule.message);
	$("body").delegate(".imageUpload", "change", function() {
		var name = $(this).attr("id");
		$("#" + name + "Id").remove();
		isImageTypeOk = true;
		isImageSizeOk = true;
		$(this).parent().find(".image").blur();
		if(isImageTypeOk && isImageSizeOk){
			if($("."+name).html()!=""){
				$("."+name).parent().find(".echo").val("1");
			}
			ajaxFileUpload(name);
		}
	});
	
	var validator = $("#addInfo-form").kendoValidator({
		rules: {
			imageNotNullRule: imageRule.ruleNotNull,
			imageNotRightRule: imageRule.ruleNotRight,
			fileNotNullRule: additionInformationMediaIdRule.ruleNotNull,
			fileNotRightRule: additionInformationMediaIdRule.ruleNotRight
		},
		messages: {
			imageNotNullRule: imageRule.ruleNotNullMessage,
			imageNotRightRule: imageRule.ruleNotRightMessage,
			fileNotNullRule: additionInformationMediaIdRule.ruleNotNullMessage,
			fileNotRightRule: additionInformationMediaIdRule.ruleNotRightMessage
		},
		needRuleAttrbute : false
	}).data("kendoValidator");
	
	$("body").on("click", ".addInfo-submit", function() {
		if(!validator.validate()) {
			return;
		};
		$("#addInfo-form").submit();
	});
	
}
