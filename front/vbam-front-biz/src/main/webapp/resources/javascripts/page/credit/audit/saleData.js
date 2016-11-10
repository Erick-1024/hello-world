$(function(){
	//打开销售数据弹窗
	$("#saleDataDetail").click(function(){
		$(".open-se-pop").show();
	});

	//关闭销售数据弹窗
	$(".close-se-pop").click(function(){
		$(".open-se-pop").hide();
	});
	
	//销售数据“导出”
    $("body").on("click","#saleDataExport",function(){
    	exportSaleData();
    });
})

//导出销售数据
function exportSaleData(){
	var tzCustomerId= $("#tzCustomerId").val();
	var applyDate = $("#applyDate").text();
	window.open(basepath + "/credit/audit/exportSaleData?tzCustomerId=" + tzCustomerId +"&applyDate=" + applyDate);
}