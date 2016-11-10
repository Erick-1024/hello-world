var count = 10;
var myCountDown;
$(function () {
	autoAjustHeight();
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
       			$("#read").submit();
        	
        })
    }
}

function autoAjustHeight(){
	var autoHeight = (document.body.clientHeight)*0.6;
	$(".protocol-contain").css("height", autoHeight+"px");
	//alert(screenHeight);
	window.onresize = function(){
	    var autoHeight = (document.body.clientHeight)*0.6;
	    $(".protocol-contain").css("height", autoHeight+"px");
	}
}
