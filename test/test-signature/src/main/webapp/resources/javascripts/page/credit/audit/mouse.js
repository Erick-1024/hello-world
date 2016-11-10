$(function(){
	
	//企业信息鼠标悬停提示
    $(".monitorChk-tab").kendoTooltip({
    	filter: "a.inc-info",
        content: kendo.template($("#template-incInfo").html()),
        width: 400,
        position: "bottom"
    });
    //联系人信息鼠标悬停提示
    $(".monitorChk-tab").kendoTooltip({
        filter: "a.contacter-info",
        content: kendo.template($("#template-contacter").html()),
        width: 400,
        position: "bottom"
    });
    //真旅网评价鼠标悬停提示
    $(".monitorChk-tab").kendoTooltip({
        filter: "a.traveler-jadge",
        content: kendo.template($("#template-traveler").html()),
        width: 400,
        position: "bottom"
    });
});
