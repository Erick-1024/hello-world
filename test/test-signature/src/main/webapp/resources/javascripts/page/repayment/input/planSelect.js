$(function() {
	$(".radioBox01 .radio").click(function(){
        $(this).addClass("active");
        $(this).siblings().removeClass("active");
    });

    $(".radioBox02 .radio").click(function(){
        $(this).addClass("active");
        $(this).siblings().removeClass("active");
    });

    $(".repayment-next").click(function(){
//        var box01 = $(".radioBox01 > .radio:first-child").hasClass("active");
//        var boxSnd01 = $(".radioBox02 > .radio:first-child").hasClass("active");
//        var boxSnd02 = $(".radioBox02 > .radio:nth-child(2)").hasClass("active");
//        var boxSnd03 = $(".radioBox02 > .radio:last-child").hasClass("active");
//        if(box01 && boxSnd01){
//            location.href = "Excel导入还款计划.html";
//        }else if(box01 && boxSnd02){
//            location.href = "选择还款信息(手动录入).html";
//        }else if(box01 && boxSnd03){
//            location.href = "";
//        }
        $("input[name='businessMode']").val($("#businessMode .active").attr("name"));
        $("input[name='inputMethod']").val($("#inputMethod .active").attr("name"));
        $("#planSelect-form").submit();
    });
});