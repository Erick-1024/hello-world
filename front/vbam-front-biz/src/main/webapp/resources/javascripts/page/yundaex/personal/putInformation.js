$(function(){
	//显示正面示例
	$("#max-box").kendoTooltip({
	    filter: "a.front-one",
	    content: kendo.template($("#template-moniteMild").html()),
	    width: 450,
	    position: "bottom"
	});

	//显示反面示例
	$("#max-box").kendoTooltip({
	    filter: "a.verso-one",
	    content: kendo.template($("#template-moniteMiddle").html()),
	    width: 450,
	    position: "bottom"
	});
	
	$(".up-input").change(function(){
		var name = $(this).attr("id");
		uploadImage(name);
	});
	
	validInput();
	
	$("#confirmSubmit").click(submit);
});
var residentIdentityCardNoRule = {//联系电话
		required : "身份证号不能为空",
		pattern : "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$",
		message : "身份证号格式不正确"
}

function validInput(){
	setValidator("input[name=residentIdentityCardNo]", "required", true, residentIdentityCardNoRule.required);
	setValidator("input[name=residentIdentityCardNo]", "pattern", residentIdentityCardNoRule.pattern, residentIdentityCardNoRule.message);
	var validator = $("#submtForm").kendoValidator().data("kendoValidator");
}

function uploadImage(name){
	$.ajaxFileUpload({
	    url: basepath + '/facade/save',
	    type: 'post',
	    secureuri: false, //一般设置为false
	    fileElementId: name, // 上传文件的id、name属性名
	    dataType: 'text', //返回值类型，一般设置为json、application/json
	    success: function(data){
	    	if(data == "FAILED"){
	    		showAlertWin("上传失败");
	    	}else if(-1 != data.indexOf("LARGE")){
	    		showAlertWin(data.split(":")[1]);
			}else{
				$("input[name="+name+"Id]").val(data);
				$("#" + name).next().next().attr("href", mediaserver + "imageservice?mediaImageId=" + data + "&mediaType=video");
				$("#" + name).next().next().show();
			}
	    },
	    error: function(data, status, e){
	    	showAlertWin("上传失败");
	    }
	});
}

function submit(e){
//	var param = $("input[name=residentIdentityCardNo]").val();
//	if(param == ""  || !/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/.test(param)){
//		showAlertWin("身份证号输入有误");
//		return;
//	}
	var validator = $("#submtForm").kendoValidator().data("kendoValidator");
	if(!validator.validate()){
		return;
	}
	if($("input[name=residentIdentityCardFrontMediaId]").val()=="" || $("input[name=residentIdentityCardBackMediaId]").val()==""){
		showAlertWin("请完成证件上传");
		return;
	}
	$("#submtForm").submit();
}