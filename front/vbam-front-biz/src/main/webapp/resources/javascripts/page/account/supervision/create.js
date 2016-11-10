var tipBoxWindow;
var superiveConfirmWindow;
$(function() {
	init();
	initValidator();

	tipBoxWindow = new PopWindow({
		title: "提示",
		width: 420,
		reload: true,
		template: "#tipBox_template"
	}).init();


    $(".superive-confirm").click(function(){
    	var validator = $(".main-container").data("kendoValidator");
        if (!validator.validate()) {
        	return;
        }

        if (isRelateSpecialAccounts()) {
            var specials = $("input[name='specialAccountId']");
            if (specials.size() == 0) {
            	tipBoxWindow.open().center();
            	$("#tip-box-window .dlg-notice").addClass("notice-icon01");
    			$("#tip-box-window .notice-content").text("请至少关联一个专用账号");
            	return;
            }
        };

    	if (!superiveConfirmWindow) {
	    	//初始化提交弹窗
	    	superiveConfirmWindow = new PopWindow({
	            title: "确认提交",
	            width: 680,
	            reload: true,
	            template: "#template-confirmTips"
	        }).init();
    	}
    	$('#confirm-box-window .notice-content').html('是否确认与' + getSupervisorName()
    			+ '建立监管关系？<br/>监管账号：' + $('input[name="accountNo"]').val()
    			+ '<br/>默认还款账号：' + $.trim($("#setDefaultRepayment .active .labelFonts").html()))
    	superiveConfirmWindow.open().center();
    });

    //专用账户表格删除按钮
    $("#addSupervise").on("click", ".del-account", function(e){
        $(e.target).closest("tr").remove();
        var index = 1;
        $('.special-account-index').each(function(){
        	$(this).html(index++);
        });
    });

    //专用账户弹窗确认按钮
    $("body").on("click", ".superive-wrapper .confirm-link", function(e){
        var win = $(e.target).closest(".k-window");
        var overlay = win.prev(".k-overlay");
        if(win.is(":visible")){
            win.css("display", "none");
            overlay.css("display", "none");
        }

        $('#addSupervise tr').remove();
        var selectSpecials = $('#superive-chooseBody .active');
        for (var i = 0; i < selectSpecials.size(); ++i) {
        	var accountId = $(selectSpecials[i]).attr('account-id')
        	var accountNo = $(selectSpecials[i]).attr('account-no')
        	var accountName = $(selectSpecials[i]).attr('account-name')
        	var buyerName = $(selectSpecials[i]).attr('buyer-name')
        	addSpecialAccountRow(accountId, accountNo, accountName, buyerName);
        }
    });

    //给弹窗中的专用账户加上选择样式
    $("body").on("click", ".supervise-choose", function(e){
    	var specialAccountIds = [];
    	var selectedSpecials = $("input[name='specialAccountId']");
    	for (var i = 0; i < selectedSpecials.size(); ++i) {
        	specialAccountIds.push(selectedSpecials[i].value);
        }
    	var specials = $('#superive-chooseBody .checkboxInfo');
    	for (var i = 0; i < specials.size(); ++i) {
    		for (var j = 0; j < specialAccountIds.length; ++j) {
	    		if ($(specials[i]).attr('account-id') == specialAccountIds[j]) {
	    			$(specials[i]).addClass('active')
	    		}
    		}
    	}
    })

    //提交弹窗确认按钮
    $("body").on("click", "#confirm-box-window .confirm-link", function(e){
        var win = $(e.target).closest(".k-window");
        var overlay = win.prev(".k-overlay");
        if(win.is(":visible")){
            win.css("display", "none");
            overlay.css("display", "none");
        }
        submit();
    });

});

function initValidator() {
    var container = $(".main-container");
    kendo.init(container);
    container.kendoValidator({
        rules: {
            supervisorRule: function (input) {
            	return checkSupervisorName();
            },
            accountNoRule: function(input) {
            	return checkSuperviseAccountNo();
            },
            specialAccountNoRule: function(input) {
            	return checkSpecialAccountNo(input);
            }
        }
    });
}

//确认提交
function submit() {

	var url = basepath + "/account/supervision/create";
    var specialAccountIds = [];
    if (isRelateSpecialAccounts()) {
        var specials = $("input[name='specialAccountId']");
        for (var i = 0; i < specials.size(); ++i) {
        	specialAccountIds.push(specials[i].value);
        }
    };
    
    var data = {
		supervisionCompanyName: getSupervisorName(),
		supervisionAccountId: $("input[name='accountId']").val(),
		isDefaultRepayment: isSetDefaultRepayment(),
		specialAccountIds: specialAccountIds
    }
    //提交
    $.ajax({
    	url: url,
    	method: "POST",
    	data : data,
    	traditional:true,
    	success : function(data) {
    		if (data.result) {
    			location.href = basepath + "/account/supervision/create_success";
    		} else {
    			tipBoxWindow.open().center();
            	$("#tip-box-window .dlg-notice").addClass("notice-icon01");
    			$("#tip-box-window .notice-content").text("提交申请失败：" + data.reason);
    		}
    	}
    });
}

//校验专用账户
function checkSpecialAccountNo(input) {
	if (!isRelateSpecialAccounts()) {
		return true;
	}
	var accountNo = $(input).val().parseBankAccountNo();

	var index = $(input).closest('tr').index();
	var prevSpecialAccs = $('input[name^=specialAccountNo-]:lt(' + index + ')');
	for (var i = 0; i < prevSpecialAccs.length; ++i) {
		if (prevSpecialAccs[i].value.parseBankAccountNo() == accountNo) {
			input.attr("data-specialAccountNoRule-msg", "账号不能重复");
			return false;
		}
	}

	var result = $.ajax({
		url: basepath + "/account/supervision/checkSpecialAccountNo",
		type: "post",
		async: false,
		data: {
			accountNo: accountNo,
			supervisorName : getSupervisorName()
		},
	});
	if (result.status/100 != 2) {
		input.attr("data-specialAccountNoRule-msg", "请求服务器出错");
		return false;
	} else {
		var data = $.parseJSON(result.responseText);
		if (data.result == false) {
			input.attr("data-specialAccountNoRule-msg", data.reason);
			return false;
		}else {
			input.closest("tr").find("input[name='specialAccountId']").val(data.accountId);
			input.closest("tr").find(".specialAccountName").html(data.accountName);
			input.closest("tr").find(".specialBuyerName").html(data.buyerName);
			return true;
		}
	};
}

//校验监管账户
function checkSuperviseAccountNo(){
	var accountNo = $('input[name="accountNo"]').val().parseBankAccountNo();
	var result = $.ajax({
		url: basepath + "/account/supervision/checkGeneralAccountNo",
		type: "post",
		async: false,
		data: {
			accountNo: accountNo,
			supervisorName : getSupervisorName()
		},
	});
	if (result.status/100 != 2) {
		$('input[name="accountNo"]').attr("data-accountNoRule-msg", "请求服务器出错");
		return false;
	} else {
		var data = $.parseJSON(result.responseText);
		if (data.result == false) {
			$('input[name="accountNo"]').attr("data-accountNoRule-msg", data.reason);
			return false;
		}else {
			$("#comboboxVal").html(data.accountName);
			$("input[name='accountId']").val(data.accountId);
			return true;
		}
	}
}

//校验监管客户名称
function checkSupervisorName(){
	var result = $.ajax({
		url: basepath + "/account/supervision/checkSupervisorName",
		async: false,
		timeout: 1000,
		type: 'post',
		data: {
			supervisorName: getSupervisorName()
		}
	});
	if (result.status/100 != 2) {
		$("input[name=supervisorName]").attr("data-supervisorRule-msg", "请求服务器出错");
		return false;
	} else {
		if (result.responseText == "") {
			return true;
		} else {
			$("input[name=supervisorName]").attr("data-supervisorRule-msg", result.responseText);
			return false;
		}
	}
}

//增加一行专用账户
function addSpecialAccountRow(accountId, accountNo, accountName, buyerName) {
	accountId = accountId || '';
	accountNo = accountNo || '';
	accountName = accountName ||'';
	buyerName = buyerName || '';
	var accountNoTDContent = '';
	var specialAccountIndex = $("#addSupervise tbody tr").size() + 1;
	if (accountNo == '') {
		accountNoTDContent += '<input name="specialAccountNo-' + specialAccountIndex + '" type="text" style="width:200px;"\
			class="bankCard" specialAccountNoRule required data-required-msg="专用账号不能为空" />';
	} else {
		accountNoTDContent += accountNo;
	}
	
	var addTR =
		'<tr>' +
	        '<td class="special-account-index">' + specialAccountIndex + '</td>' +
	        '<td>' + accountNoTDContent +
	        	'<input name="specialAccountId" type="hidden" value="' + accountId + '"/>' +
	        '</td>' +
	        '<td class="specialAccountName">' + accountName + '</td>' +
	        '<td class="specialBuyerName">' + buyerName + '</td>' +
	        '<td>' +
	        	'<a class="accountList-link del-account" href="javascript:void(0);">删除</a>' +
	        '</td>' +
	    '</tr>';
	
	$("#addSupervise tbody").append(addTR);
}

//是否设置默认还款账户
function isSetDefaultRepayment() {
	return "是" == $.trim($("#setDefaultRepayment .active .labelFonts").html());
}

//是否关联专用账户
function isRelateSpecialAccounts() {
	return "是" == $.trim($("#relateSpecialAccounts .active .labelFonts").html());
}

//获取监管企业名称
function getSupervisorName() {
	return $("input[name='supervisorName']").val();
}

function init() {
	//初始化选择专用账户弹窗
    new PopWindow(".supervise-choose", {
        title: "选择专用账户",
        width: 800,
        reload: true,
        resizable: false,
        draggable: false,
        template: "#template-superive"
    }).init();
    
  //增加专用账户按钮
    $(".supervise-add").click(function(){
    	addSpecialAccountRow();
    });

    //专用账户弹窗复选框
    $("body").on("click", ".checkboxWrap .checkboxInfo", function(e){
        $(this).toggleClass("active");
    });
	//专用账户弹窗复选框
    $("body").on("click", ".checkboxContent .checkboxInfo", function(e){
        $(this).toggleClass("active");
        if($(this).hasClass("active")){
            $(".checkboxWrap").children(".checkboxInfo").addClass("active");
        }else{
            $(".checkboxWrap").children(".checkboxInfo").removeClass("active");
        }
    });

    //专用账户弹窗关闭按钮
    $("body").on("click", ".superive-wrapper .back-link", function(e){
        var win = $(e.target).closest(".k-window");
        var overlay = win.prev(".k-overlay");
        if(win.is(":visible")){
            win.css("display", "none");
            overlay.css("display", "none");
        }
    });

    //提交弹窗关闭按钮
    $("body").on("click", "#confirm-box-window .back-link", function(e){
        var win = $(e.target).closest(".k-window");
        var overlay = win.prev(".k-overlay");
        if(win.is(":visible")){
            win.css("display", "none");
            overlay.css("display", "none");
        }
    });
    
    //是否关联专用账户
	$("#relateSpecialAccounts .radio").click(function(){
    	if (isRelateSpecialAccounts()) {
    		$(".SuperviseAct").show();
    		$(".addSupervise-table").show();
    	} else {
    		$('.addSupervise-body tbody').empty();
    		$(".SuperviseAct").hide();
    		$(".addSupervise-table").hide();
    	}
	})
}