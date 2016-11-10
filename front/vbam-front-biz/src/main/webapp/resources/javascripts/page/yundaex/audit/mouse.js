$(function(){
	
	//企业信息鼠标悬停提示
    $(".monitorChk-tab").kendoTooltip({
    	filter: "a.inc-info",
        content: kendo.template($("#template-YDincInfo").html()),
        width: 400,
        position: "bottom"
    });
    //联系人信息鼠标悬停提示
    $(".monitorChk-tab").kendoTooltip({
        filter: "a.contacter-info",
        content: kendo.template($("#template-YDcontacter").html()),
        width: 400,
        position: "bottom"
    });
    //真旅网评价鼠标悬停提示
    $(".monitorChk-tab").kendoTooltip({
        filter: "a.traveler-jadge",
        content: kendo.template($("#template-YDtraveler").html()),
        width: 400,
        position: "bottom"
    });
});
