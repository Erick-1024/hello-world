$(function(){
	$("input[name=accordance]").change(function(){
		var result = getAuditResult($("input[name=accordance]:checked").val());
		$("span.result").text(result[1]);
	});
	
	$("#submitAudit").click(comfirmSubmit);
});

function getAuditResult(number){
	switch(number){
	case "0":
		return ["PASSED", "通过"];
	case "1":
		return ["NOTPASSED", "不通过"];
	}
}

function comfirmSubmit(){
	if($("input[name=accordance]:checked").length == 0){
		showAlertWin('请先审核!');
		return;
	}
	var id = $(this).attr("data-id");
	$(this).unbind();
	var $object = $(this);
	$.ajax({
		url:basepath + "/yundaex/personal/audit",
		data:{id:id,auditStatus:getAuditResult($("input[name=accordance]:checked").val())[0]},
		type:'post',
		timeout : 15000,
		dataType:'json',
		traditional:true,
		success : function(response) {
			if (response.status.toLowerCase() == 'success') {
				showSuccessWin(response.message);
				setTimeout(location.href = basepath + "/yundaex/personal/gotoListPage", 4000);
			} else if (response.status.toLowerCase() == 'failed') {
				showAlertWin(response.message);
				$object.bind("click", comfirmSubmit);
			}
		},
		error : function(response){
			showAlertWin('网络异常!');
			$object.bind("click", comfirmSubmit);
		},
		timeout : function(){
			showAlertWin("请求超时!");
			$object.bind("click", comfirmSubmit);
		}
	});
}