$(function() {
	
	$(".issue-date").datepicker({
        format: "yyyy-mm-dd",
        language: "zh-CN",
        autoclose: true,
        todayHighlight: true,
        weekStart: 0,
        firstDay: 0,
        maxDate: "0",
    }).on("show", function () {
        $("div.datepicker table thead .prev").html("");
        $("div.datepicker table thead .next").html("");
    });
	
	$(".close-date").datepicker({
        format: "yyyy-mm-dd",
        language: "zh-CN",
        autoclose: true,
        todayHighlight: true,
        weekStart: 0,
        firstDay: 0,
        minDate: "0",
    }).on("show", function () {
        $("div.datepicker table thead .prev").html("");
        $("div.datepicker table thead .next").html("");
    });
	
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
	//回车按钮事件
	document.onkeydown=keyDownSearch;  
    function keyDownSearch(e) {    
        var theEvent = e || window.event;    
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
        if (code == 13) {  
        	$("#submit").click();
        }    
        return true;    
    }
    var submitFlag = true;
    var validator = $("#issueAdd-id").kendoValidator().data("kendoValidator");
    
    //点击提交按钮
    $("#submit").click(function() {
		if(!validator.validate()) {
			return;
		};
		var postData = {};
		var originator =[];
		var serviceAgency =[];
		var originatorNameLength = $('[name =originator]').length;
		var serviceAgencyNameLength = $('[name =serviceAgency]').length;
		//原始权益人
		for(var i=0; i< originatorNameLength; i++) {
				originator.push({originatorName: $($('[name =originator]')[i]).val()});
			}
		//资产服务机构
		for(var i=0; i< serviceAgencyNameLength; i++){
			serviceAgency.push({serviceAgencyName: $($('[name =serviceAgency]')[i]).val()});
		}
		
		postData.originators =originator;
		postData.serviceAgencys =serviceAgency;
		postData.id =$('#specialProgramId').val();
		postData.specialProgramName =$("#specialProgramName").val();
		postData.underlyingAssetType =$("#underlyingAssetType").val();
		postData.managerName =$('#managerName').val();
		postData.lawFirmName =$('#lawFirmName').val();
		postData.accountingFirmName =$('#accountingFirmName').val();
		postData.supervisionBank =$('#supervisionBank').val();
		postData.custodianBank =$('#custodianBank').val();
		postData.establishmentDate =$('#establishmentDate').val();
		postData.renewalPeriod =$('#renewalPeriod').val();
		postData.estimateDueDate =$('#estimateDueDate').val();
		postData.statutoryDueDate =$('#statutoryDueDate').val();
		postData.preferA =$('#preferA').val().parseMoney();
		postData.preferB =$('#preferB').val().parseMoney();
		postData.gross =$('#gross').val().parseMoney();
		postData.defer =$('#defer').val().parseMoney();
		postData.trustMethod =$('#trustMethod').val();
		postData.assetPoolPrincipalBalance =$('#assetPoolPrincipalBalance').val().parseMoney();
		postData.contractNum =$('#contractNum').val();
		postData.weightedAverageContractPeriod =$('#weightedAverageContractPeriod').val();
		postData.status =$("#status").val();
		postData.financeNum =$('#financeNum').val();
		postData.weightedAverageInterestRate =$('#weightedAverageInterestRate').val();
		postData.cyclePeriod =$('#cyclePeriod').val();
		postData.capitalAccumulationDate =$('#capitalAccumulationDate').val();
		postData.cyclePurchaseDate =$('#cyclePurchaseDate').val();
		postData.incomeRecoveryDate =$('#incomeRecoveryDate').val();
		postData.recyclingPaymentDate =$('#recyclingPaymentDate').val();
		postData.excessFund =$('#excessFund').val().parseMoney();
		postData.cycleWeightedAverageInterestRate =$('#cycleWeightedAverageInterestRate').val();
		postData.cyclePurchaseTerm =$('#cyclePurchaseTerm').val();
		postData.underlyingAssetQualityStandard =$('#underlyingAssetQualityStandard').val();
		if($('#showHide-btn').val()=="否"){
			postData.cyclePurchaseStructure =false;
		}else if($('#showHide-btn').val()=="是"){
			postData.cyclePurchaseStructure =true;
		}
		
		
		$.ajax({
		    type : "POST",
			url : basepath + "/asset/specialprogram/issueAddFormData",
			data :  JSON.stringify(postData),
			dataType : 'json',
			contentType:'application/json;charset=utf-8',
			success : function(data,status) {
				if(data.status =="SUCCESS"){
					tipPopWindow.open().center();
					$(".k-window-actions").remove();
					$("#tip-box-window .dlg-notice").addClass("notice-icon02");
					$("#tip-box-window .notice-content").text("成立专项计划成功");
					
				}else {
					popWindow.open().center();
					$("#tip-box-window .dlg-notice").addClass("notice-icon01");
					$("#tip-box-window .notice-content").text(data.message);
				}
				
			},
			error : function(e){
				showAlertWin("成立专项计划异常");
			}
		});
    });
});


