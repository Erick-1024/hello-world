var count = 10;
var myCountDown;
$(function () {
    myCountDown = setInterval(countDown, 1000);
});
function countDown() {
	count--;
    $("#numBtn").attr("disabled", true);
    $("#numBtn").next().html("("+count+"s)");
    if (count == 0) {
        $("#numBtn").val("我已阅读").removeAttr("disabled");
        $("#numBtn").removeClass("inputDisable");
        $("#numBtn").next().html("");
        clearInterval(myCountDown);
        count = 10;
        $("#numBtn").click(function(){
        	var id = $("input[name=id]").val();
        	var code = $("input[name=code]").val();
        	var contractId = $("#contractId").val();
        	var isRead = $("input[name=isRead]").val();
        	window.location.href = encodeURI(basepath + "/yundaex/personal/facade/gotoSignContract?id="+id+"&code="+code+"&isRead="+isRead+"&contractId=" + contractId);
        })
    }
}

window.onload = function(){    
	var autoHeight = (document.body.clientHeight)*0.6;
    $(".protocol-contain").css("height", autoHeight+"px");
    //alert(screenHeight);
    window.onresize = function(){
        var autoHeight = (document.body.clientHeight)*0.6;
        $(".protocol-contain").css("height", autoHeight+"px");
    }
}