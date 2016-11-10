// 必填字段数组
var required = ["BUSINESS_LICENCE","ORGANIZATION_CREDENTIAL","TAX_REGISTRATION_CERTIFICATE_CREDENTIAL",
                "ORGANIZATION_CREDIT_CREDENTIAL","CBK_LICENCE","COMPANY_POLICY","LEGAL_PERSON_IDENTITY_CARD_FRONT_AND_BACK",
                "COMPANY_INTRODUCE","BASIC_TRANSACTION_CONTRACT","FACTORING_BUSINESS_MAIN_CONTRACT",
                "ASSIGNMENT_OF_ACCOUNTS_RECEIVABLE_APPLICATION_AND_RECEIPT","PEOPLE_BANK_ACCOUNT_RECEIVABLE_TRANSFER_REGISTRATION"];

var desc = ["营业执照","组织机构代码证","税务登记证","机构信用代码证","开户许可证",
            "公司章程","法定代表身份证正反面", "公司简介","基础交易合同","保理业务主合同",
            "应收账款转让通知及回执","人民银行应收账款转让登记"];

$(function(){
	$("body").delegate(".input-file", "change", function(){
		var element = $(this);
		uploadSubmit(element, basepath + "/asset/enterpriseInfo/saveFile", $(this).attr("id"), 
		function(data){
			var result = data.split(":")
			if(result[0] == "SUCCESS"){
				element.next().next().find("img").css('display','block'); 
				element.parent().parent().find(".blue").css('display','block')
				element.parent().parent().find(".look-acc").prop("href", mediaserver + "imageservice?mediaImageId=" + result[2] + "&mediaType=video")
				element.parent().find("#fileName").val(result[1]); 
				element.parent().find("#mediaId").val(result[2]); 
			}else{
				element.next().next().find("img").css('display','none'); 
				showAlertWin(result[1]);
			}
		}, 
		function(data){
			showAlertWin(data.responseText);
		});
    });

	$("body").on("blur",".annex-txt",function(){
		$(this).parent().parent().prev().find("input[name='remark']").val($(this).val());
	});

	$("body").on("click",".saveTemp",function(){
		saveTempInfo();
	});

	$("body").on("click",".submitInfo",function(){
		submitInfo();
	});

});

function ajaxFileUpload(url, uploadFileId, success, error) {
	$.ajaxFileUpload({
	    url: url,
	    type: 'post',
	    secureuri: false, //一般设置为false
	    fileElementId: uploadFileId, // 上传文件的id、name属性名
	    dataType: 'text', //返回值类型，一般设置为json、application/json
	    success:success,
	    error:error
	});
};

function uploadSubmit(element, url,uploadFileId,success,error){
	//检查是否已选择上传文件
	var file = element.val();
	var filename = file.replace(/.*(\/|\\)/, '');
	var fileext = (/[.]/.exec(filename)) ? /[^.]+$/.exec(filename.toLowerCase()) : '';
	//检查文件格式
	if (file != '') {
		if (fileext == 'doc' || fileext == 'docx' || fileext == 'pdf' || fileext == 'png' || fileext == 'jpg' || fileext == 'txt'
			|| fileext == 'ppt' || fileext == 'pptx' || fileext == 'xls' || fileext == 'xlsx'){
			//上传文件
			ajaxFileUpload(url, uploadFileId, success, error);
		}else{
//			showAlertWin("文件格式必须是*.doc、*.docx、*.png、*.jpg、*.pdf、*.txt、*.ppt、*.pptx、*.xls、*.xlsx");
			showAlertWin("文件格式不支持");
		}
	}
}

function saveTempInfo(){
	// 置空form表单中数据
	$("form[name=submitData]").html("");
	// 将cutomerId放入form表单中
	var customerId = $("input[name=customerId]").val();
	var tempHtml = '<input type="hidden" name="customerId" value="'+customerId+'">';
	var $cust = $(tempHtml);
	$cust.appendTo("form[name=submitData]");
	// customerId校验
	if(customerId == null || customerId == ''){
		showAlertWin("客户Id不能为空");
		return;
	}
	// 获取数据并将数据放入form表单中
	var index = 0;
	$(".info").each( function(i, e){
		var inputs = $(this).html();
		var $inputs = $(inputs);
		if($inputs.filter("input[name='fileName']").val() != null && $inputs.filter("input[name='mediaId']").val() != ""){
			$inputs.filter("input").each( function(i, e){
				$(this).attr("name", "infoList["+index+"]."+$(this).attr("name"))
			});
			$inputs.appendTo("form[name=submitData]");
			index++;
		}
	});
	if(index < 1){
		showAlertWin("请至少上传一个文件");
		return;
	}
	// 修改提交表单地址并提交表单
	$("form[name=submitData]").attr("action",basepath + "/asset/enterpriseInfo/saveTempInfo");
	$("form[name=submitData]").submit();
}

function submitInfo(){
	// 置空form表单中数据
	$("form[name=submitData]").html("");
	// 将cutomerId放入form表单中
	var customerId = $("input[name=customerId]").val();
	var tempHtml = '<input type="hidden" name="customerId" value="'+customerId+'">';
	var $cust = $(tempHtml);
	$cust.appendTo("form[name=submitData]");
	// customerId校验
	if(customerId == null || customerId == ''){
		showAlertWin("客户Id不能为空");
		return;
	}
	if(!dataValidate()){
		return false;
	}
	// 获取数据并将数据放入form表单中
	var index = 0;
	$(".info").each( function(i, e){
		var inputs = $(this).html();
		var $inputs = $(inputs);
		if($inputs.filter("input[name='fileName']").val() != null && $inputs.filter("input[name='mediaId']").val() != ""){
			$inputs.filter("input").each( function(i, e){
				$(this).attr("name", "infoList["+index+"]."+$(this).attr("name"))
			});
			$inputs.appendTo("form[name=submitData]");
			index++;
		}
	});
	// 修改提交表单地址并提交表单
	$("form[name=submitData]").attr("action",basepath + "/asset/enterpriseInfo/submitInfo");
	$("form[name=submitData]").submit();
}

function dataValidate(){
	// 必填字段校验
	for(var index = 0; index < required.length; index++){
		var count = 0 ;
		$(".info").each( function(i, e){
			if($(this).find("input[name='fileName']").val() != null && $(this).find("input[name='mediaId']").val() != ""){
				if($(this).find("input[name='itemType']").val() == required[index]){
					count++;
				}
			}
		});
		if( count<1 ){
			showAlertWin(desc[index]+"不能为空");
			return false;
		}
	}
	return true;
}


	