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
        $("#numBtn").next().html("");
        clearInterval(myCountDown);
        count = 10;
        $("#numBtn").click(function(){
        	var supervisionAccountNo = $("#supervisionAccountNo").val();
        	var contractId = $("#contractId").val();
        	window.location.href = encodeURI(basepath + "/guide/gotoSignContract?isRead=true&supervisionAccountNo=" + supervisionAccountNo  + "&contractId=" + contractId);
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