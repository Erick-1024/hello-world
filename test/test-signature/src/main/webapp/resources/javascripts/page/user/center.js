var type=0;// 1:修改登陆密码 2：设置支付密码 3：修改支付密码
var warn;
var validator;
$(function(){
	initPopWindow();
	initBtn();
});

function initBtn(){
	var validator;
	$('body').on('click','.editPwd',function(){
		type=1;
		initVali('#old_pwd','#new_pwd','#new_pwd_sec','#confirmEditPwd','#editPwd-form');
	});
	$('body').on('click','#confirmEditPwd',function(){
		ajaxLoginPwd('#old_pwd','#new_pwd');
	});
	$('body').on('click','#comfirm_name',function(){
		setValidator($('#cName'),"required",true,contactNameRule.required);
		var validator=$('#cName').kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
 		modifyContacts({userId:$.trim($('.modify_user_id').val()),contactName:$.trim($('#cName').val()),
	        mobileNum:'',jobTitle:'',mail:''},'.cName',$.trim($('#cName').val()));
	});
	$('body').on('click','#comfirm_job',function(){
		setValidator($('#jobTitle'),"required",true,contactNameRule.required);
		var validator=$('#jobTitle').kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
 		modifyContacts({userId:$.trim($('.modify_user_id').val()),contactName:'',
	        mobileNum:'',jobTitle:$.trim($('#jobTitle').val()),mail:''},'.jobTitle',$.trim($('#jobTitle').val()));
 		
	});
	$('body').on('click','#comfirm_tel',function(){
		setValidator($('#tel'),"required",true,mobileNoRule.required);
		setValidator($('#tel'),"pattern",mobileNoRule.pattern,mobileNoRule.message);
		var validator=$('#tel').kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
 		modifyContacts({userId:$.trim($('.modify_user_id').val()),contactName:'',
 				        mobileNum:$.trim($('#tel').val()),jobTitle:'',mail:''},'.tel',$.trim($('#tel').val()));
	});
	$('body').on('click','#comfirm_mail',function(){
		setValidator($('#email'),"required",true,emailNoRule.required);
		setValidator($('#email'),"pattern",emailNoRule.pattern,emailNoRule.message);
		var validator=$('#email').kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
 		modifyContacts({userId:$.trim($('.modify_user_id').val()),contactName:'',
		        mobileNum:'',jobTitle:'',mail:$.trim($('#email').val())},'.email',$.trim($('#email').val()));
	});
	
	//初始化保存操作弹窗
    warn=new PopWindow("X", {
        title: "提示",
        width: 420,
        reload: true,
        template: "#template-saveFinished"
    }).init();
    
    $('body').on('click','.editName-link',function(){
    	$('#cName').val($('.cName').text());
    });
    $('body').on('click','.editJob-link',function(){
    	$('#jobTitle').val($('.jobTitle').text());
    });
    $('body').on('click','.editCellphone-link',function(){
    	$('#tel').val($('.tel').text());
    });
    $('body').on('click','.editEmail-link',function(){
    	$('#email').val($('.email').text());
    });
    
    
  //忘记密码
    new PopWindow(".forgetPayPwd", {
        title: "忘记支付密码",
        width: 460,
        reload: true,
        resizable: false,
        content: {
            template: '<div class="dlg-notice-row style="text-align:center">' +
            '<span class="notice-content">请与CANA客服人员联系，联系电话021-83866655-8008</span>' +
            '</div>'
        }
    }).init();
}
function initVali(loginPwd,newPwd,newPwdSec,edit_btn,edit_form){
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
function ajaxLoginPwd(loginPwd,newPwd){
	if(!validator.validate()) {
		return;
	}
	$.ajax({
		type:"POST",
		dataType : "json", 
		data:{userId:$.trim($('.modify_user_id').val()),
			  oldPwd:$(loginPwd).val(),
			  newPwd:$(newPwd).val(),
			  flag:type},
	    url:basepath+"/user/modifyLoginPwd",
	    success:function(response){
	    	if(response.status=='SUCCESS'){
	    		$(".k-window-action.k-link").click();
	    		popWrite('notice-icon02','密码修改成功');
	    		
	    	}else{
	    		popWrite('notice-icon01',response.message);
	    	}
	    	
	    },
	    error:function(){
	    	popWrite('notice-icon01','请求失败');
	    }
	});
}
//修改联系人信息
function modifyContacts(mdata,a,c){
	$.ajax({
		type:"POST",
		dataType : "json", 
		data:mdata,
	    url:basepath+"/user/modifyPersonalInfo",
	    success:function(response){
	    	if(response.status=='SUCCESS'){
	    		$(".k-window-action.k-link").click();
	    		$(a).text(c);
	    		popWrite('notice-icon02',response.message);
	    	}else{
	    		popWrite('notice-icon01',response.message);
	    	}
	    },
	    error:function(){
	    	popWrite('notice-icon01','请求失败');
	    }
	});
}
function popWrite(icon,word){
	$(".dlg-notice").addClass(icon);
	$(".notice-content").text(word);
	warn.center().open();
	setTimeout("closePop()",1000);	
}
function closePop(){
	warn.close();
}

function isLoginSuccess(input){
	var oldName;
	switch(type){
	case 1:
		oldName='old_pwd';
		break;
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
			data:{userId:$.trim($('.modify_user_id').val()),
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
	case 1:
		nPwd='new_pwd';
		break;
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
