$(function(){
	// 初始化消息弹窗
	popWindow = new PopWindow({
		title : "提示",
		width : 400,
		reload : true,
		template : "#tipBox_template"
	}).init();
	
    var date = show();
    $(".packetDate").val(date);

    $(".packetDate").datepicker({
        format: "yyyy-mm-dd",
        language: "zh-CN",
        autoclose: true,
        todayHighlight: true,
        weekStart: 0,
        firstDay: 0,
        maxDate:new Date()         //封包日期不能大于当天
    }).on("show", function () {
        $("div.datepicker table thead .prev").html("");
        $("div.datepicker table thead .next").html("");
    });
    
    //封包提交
    $(".confirm-link").click(function(){
    	$.ajax({
    		type : "POST",
    		url : basepath + '/asset/pool/packet',
    		data : {
    			id : $("#id").val(),
    			originAssetpoolScale : $("#originAssetpoolScale").val(),
    			encapsulationDate : $(".packetDate").val()
    		},
    		dataType : "json",
    		success : function(data) {
    			if(data.status=="SUCCESS"){
    				showSuccessWin(data.message);
    				setTimeout("gotoAssetPoolListPage()",1000);
    			}else{
    				popWindow.open().center();
    				$("#tip-box-window .dlg-notice").addClass("notice-icon01");
    				$("#tip-box-window .notice-content").text(data.message);
    			}
    		},
    		error : function(data) {
    			showAlertWin("网络异常");
    		}
    	});
    });
    
    //封包关闭
    $(".back-link").click(function(){
    	var id = $("#id").val();
    	var status = $("#status").val();
    	location.href = encodeURI(basepath + '/asset/pool/assetpoolManage?id='+id+'&status='+status);
    });
    
});
    
function show(){
	var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

function gotoAssetPoolListPage(){
	location.href = encodeURI(basepath + '/asset/pool/assetpoolList');
}

