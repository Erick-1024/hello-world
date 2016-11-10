$(function() {

	$("body").on("click", ".repayment-next", function() {
		$("input[name='businessMode']").val($("#businessMode .active").attr("name"));
		$("input[name='inputMethod']").val($("#inputMethod .active").attr("name"));
		$("#loanInfoSelect-form").submit();
	});
});