var pageSize = 10;
$(function(){
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	if(evt.keyCode==13){
    		$("#submit-two").click();
    	}
    });
	//点击提交
	$("body").on("click","#submit-two",function(){
		var validator = $("#credit-update-form").data("kendoValidator");
		if(!validator.validate()) {
			return;
		};
		var postData = {};
		postData.id =$('#credit-Id').val();
		postData.businessContractNo =$('#businessC').val();
		postData.creditMode =$('#credit-M').val();
		postData.currency =$('#curr').val();
		postData.totalLimit =$('#totalL').val().parseMoney();
		postData.expense =$('#exp').val().parseMoney();
		postData.effectiveDate =$('#effectiveD').val();
		postData.dueDate =$('#dueD').val();
		$.ajax({
		    type : "POST",
			url : basepath + "/asset/credit/update",
			data :  postData,
			dataType : 'json',
			success : function(data,status) {
				if(data.status =="SUCCESS"){
					closeThePop();
					successPopWindow.open().center();
		            $("#confirm-box-window #operationObj").val($(this).attr("data"));
		            $("#confirm-box-window .notice-content").html("修改额度成功！");
				}else {
					failPopWindow.open().center();
		            $("#confirm-box-window #operationObj").val($(this).attr("data"));
		            $("#confirm-box-window .notice-content").html(data.message);
				}
			},
		});
	});
});

















