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
	
	//没有证件信息时，不显示查看
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
	
	var accessManualState = $("#accessManualState").val();
	if(accessManualState == "ACCESS"){
	$("#auditResult").text("通过");
	}else{
	$("#auditResult").text("不通过");};
	if(accessManualState == "WAIT"){
		$("#auditResult").text("未审核");
	};
});