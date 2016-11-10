var validator;
var pwdPath;
function setOrModifyPwd(){
	$('body').on('click','.payPwd',function(){
		type=2;
		initVal('#old_pay','#new_pay','#new_pay_sec','#payPwd-form');
	});
	$('body').on('click','.editPayPwd',function(){
		type=3;
		initVal('#old_edit_pay','#new_edit_pay','#new_edit_pay_sec','#editPayPwd-form');
	});
	$('body').on('click','#confirmEditPayPwd',function(){
		ajaxPwd('#old_edit_pay','#new_edit_pay');
	});
	$('body').on('click','#confirmPayPwd',function(){
		ajaxPwd('#old_pay','#new_pay');
	});
	
	
	//忘记密码
    new PopWindow(".forgetPayPwd", {
        title: "忘记支付密码",
        width: 460,
        reload: true,
        resizable: false,
        content: {
            template: '<div class="dlg-notice-row style="text-align:center">' +
            '<span class="notice-content">请与CANA客服人员联系，联系电话021-53866655-8008</span>' +
            '</div>'
        }
    }).init();
}

function initVal(loginPwd,newPwd,newPwdSec,edit_form){
	setValidator($(loginPwd), "required", true, loginPwdRule.required);
	//setValidator($(loginPwd), "pattern", loginPwdRule.pattern, loginPwdRule.message);
	setValidator($(newPwd), "required", true, loginPwdRule.required);
	setValidator($(newPwd), "pattern", loginPwdRule.pattern, loginPwdRule.message);
	setValidator($(newPwdSec), "required", true, loginPwdRule.required);
	validator = $(edit_form).kendoValidator({
		rules: {
			secondPasswordRule: secondPasswordRule.rule,
			loginPwdRule:loginPwdRule.rule
	          },
	   messages: {
		     secondPasswordRule: secondPasswordRule.ruleMessage,
		     loginPwdRule:loginPwdRule.ruleMessage
	  },
	  needRuleAttrbute : false 
}).data("kendoValidator");
}
function ajaxPwd(loginPwd,newPwd){
	if(!validator.validate()) {
		return;
	}
	if(type==2){
		pwdPath=basepath+"/user/setPayPwd";
	}else{
		pwdPath=basepath+"/user/modifyPayPwd"
	}
	$.ajax({
		type:"POST",
		dataType : "json", 
		data:{userId:$.trim($('.company_userId').val()),
			  oldPwd:$(loginPwd).val(),
			  newPwd:$(newPwd).val(),
			  flag:type},
	    url:pwdPath,
	    success:function(response){
	    	if(response.status=='SUCCESS'){
	    		$(".k-window-action.k-link").click();
	    		popWrite('notice-icon02',type==2?'密码设置成功':'密码修改成功');
	    	}else{
	    		popWrite('notice-icon01',response.message);
	    	}
	    },
	    error:function(){
	    	popWrite('notice-icon01','请求失败');
	    }
	});
}
function isLoginSuccess(input){
	var oldName;
	switch(type){
	case 2:
		oldName='old_pay';
			break;
	case 3:
		oldName='old_edit_pay';
	}
	if(input.is("[name="+oldName+"]")){
		var flag=true;
		$.ajax({
			type:"POST",
			async:false,
			dataType : "json", 
			data:{userId:$.trim($('.company_userId').val()),
				  loginPwd:$('#'+oldName).val(),
				  flag:type
				  },
		    url:basepath+"/user/isloginPwd",
		    success:function(response){
		    	if(response.status=='SUCCESS'){
		    		flag= true;
		    	}else{
		    		flag= false;
		    	}
		    }
		    
		});
		return flag;
	}
	return true;
}
function checkPasswordMatch(input){
	var nPwd;
	switch(type){
	case 2:
		nPwd='new_pay';
			break;
	case 3:
		nPwd='new_edit_pay';
	}
	if(input.is("[name="+nPwd+"_sec]")){
		var rePassword = $.trim($("#"+nPwd+"_sec").val())
		if($.trim($("#"+nPwd).val()) == rePassword){
			return true;
		}
		return false;
	}
	return true;	
}

