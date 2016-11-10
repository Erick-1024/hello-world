var payPassword={
		required:"支付密码不能为空",
};


function initTradeStatusBtn(){
	$('.status-normal').click(function(){
		$(this).addClass('status-chk').removeClass('status-default');
		$(this).siblings('a').addClass('status-default').removeClass('status-chk');
		if($(this).hasClass('last-data')){
			initRecentTimeButton(this);
		}
		showList();
	});
};

function initSearchBtn(){
	var _parent='';
	$('.form-search-link').click(function(){
		showList();
	});
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		showList();
    	}
    });
	 //人工操作按钮
    $("body").on("click", "#monitorTrade-grid .limitAct-link", function(e){
    	$('.error-text').text('');
    	_parent = $(e.target).closest("tr");
    	initPopWindow($(this).attr('transferid'));
    	refreshBalance($(this).attr('account'));
        $(".window-overlay").removeClass("hidden");
        $("#template-fundDetail").removeClass("hidden");
    });
    
    //弹窗刷新按钮
    $('body').on('click','.refresh_balance',function(e){
    	refreshBalance($(this).attr('value'));
    });
    //确定操作按钮
    $('body').on('click','.confirm-link',function(){
		setValidator($('#payPassword'),"required",true,payPassword.required);
//		setValidator($('#payPassword'),"pattern",payPassword.rule,payPassword.ruleMessage);
		var validator=$('#payPassword').kendoValidator().data("kendoValidator");
		if(!validator.validate()){
			return;
		}
		var transferId=$('.fromAccountNo').attr('value');
		$.ajax({
			type:"POST",async:false,dataType:"json",data:{id:transferId},url:basepath+"/credit/transfer/manual",
			success:function(response){
				if(response.status=='SUCCESS'){
					 $(_parent).find("td:eq(5)").text(response.data);
					 if(response.data!='转账失败')
						 $(_parent).find("td:eq(7)").find('a').remove();
					 closePopWindow();
				}else{
					$('.error-text').text(response.message);
				}
			}
		});
		
	});	
    
    //关闭弹窗
    $(".closeHref, .back-link, .close-window").click(function(){
    	closePopWindow();
    });
    
    //获得输入框焦点
    
    $('#payPassword').focus(function(){
    	$('.error-text').text('');
    })
};
function closePopWindow(){
	$(".window-overlay").addClass("hidden");
    $(".template-limitAct").addClass("hidden");
    $('#payPassword').val('');
	$('#payPassword').parent().find('span').remove();
}
function initPopWindow(transferId){
	$('.fromAccountNo').removeAttr('value');
	$('.fromAccountNo').text('');
	$('.fromAccountName').text('');
	$('.toAccountNo').text('');
	$('.toAccountName').text('');
	$('.toFee').text('');
	$.ajax({
		type:"POST",
		dataType:"json",
		data:{id:transferId},
		url:basepath+"/credit/transfer/operate",
		success:function(response){
			if(response.status=='SUCCESS'){
				var data=response.data;
				$('.fromAccountNo').attr('value',transferId);
				$('.fromAccountNo').text(data.fromAccountNo);
				$('.fromAccountName').text(data.fromAccountName);
				$('.toAccountNo').text(data.toAccountNo);
				$('.toAccountName').text(data.toAccountName);
				$('.toFee').text(data.toFee);
			}
		}
	});
};

function refreshBalance(accountNo){
	$('.fromAccountBalance').text("获取中..");
	$('.refresh_balance').attr('value',accountNo);
	$.ajax({
		type:"POST",
		dataType:"json",
		data:{accountNoString:accountNo},
		url:basepath + "/account/trade/queryAccountBalance",
		success:function(response){
			if(response.status=='SUCCESS'){
				var data=response.data;
				$('.fromAccountBalance').text(data.accountBalances[0].formatMoney());
			}
		}
	});
}
//初始化查询条件“交易日期-最近”按钮
function initRecentTimeButton(obj){
		var value = $(obj).attr("value");
		var startDay = new Date();
		if(value == "oneWeek")
			subtractDays(startDay, 6);
		if(value == "oneMonth"){
			startDay = subtractMonths(startDay, 1);
			addDays(startDay, 1);
		}
		if(value == "threeMonth"){
			startDay = subtractMonths(startDay, 3);
			addDays(startDay, 1);
		}
		$(".startDate").val(startDay.format("yyyy-MM-dd"));	
		$(".endDate").val(new Date().format("yyyy-MM-dd"));
};