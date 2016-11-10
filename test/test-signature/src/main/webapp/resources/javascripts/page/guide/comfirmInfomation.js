$(function(){
	$(".finaRadio .radio").click(function(){
		$(this).addClass("active");
		$(this).parent(".finaRow").siblings().find(".radio").removeClass("active");
	}); 
		
    $(".confirm-link").click(function(){
    	var isCreateNewAccount = $.trim($("#supervisionAccount .active").attr("name"));
		var supervisionAccountNo = $.trim($("#accountNo").val());
		window.location.href = encodeURI(basepath + "/guide/confirmInfomation?isCreateNewAccount=" + isCreateNewAccount + "&supervisionAccountNo=" + supervisionAccountNo);
    });
    
});