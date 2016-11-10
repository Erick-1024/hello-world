$(function(){
	
	$("#applyCreditLimit").text($("#applyCreditLimit").text().formatMoney());
	$("#proposalCreditLimit").text($("#proposalCreditLimit").text().formatMoney());
	var annualSales = $("#annualSales").text();
	var enterpriseExecutionMoney = $("#enterpriseExecutionMoney").text();
	var individualExecutionMoney = $("#individualExecutionMoney").text();
	if(annualSales.length != 0) {
		$("#annualSales").text(annualSales.formatMoney());
	}
	if(enterpriseExecutionMoney.length != 0) {
		$("#enterpriseExecutionMoney").text(enterpriseExecutionMoney.formatMoney());
	}
	if(individualExecutionMoney.length != 0) {
		$("#individualExecutionMoney").text(individualExecutionMoney.formatMoney());
	}
	
});
