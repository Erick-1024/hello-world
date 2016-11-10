/**
 * Created by DELLO on 2016/8/4.
 */
$(function(){
    //左侧导航栏鼠标滑动效果插件启动
    $('#client-nav').onePageNav();
    //页面展示隐藏
    $(".pro-title-right").click(function(){
        $(this).parent().next().toggle();
        var ty = $(this).parent().next().is(":hidden");
        if(ty==false){
            $(this).html("折叠");
        }else if(ty==true){
            $(this).html("展开");
        }
    });
});