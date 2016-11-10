var type=0;
var first_contract=0;
$(function(){
	init();
	initPopWindow();
	initContactInfo();
	setOrModifyPwd();
	
	$('body').delegate('.tab_contract','click',function(){
		if(first_contract==0){
			showContractTable();
			first_contract=1;
		}   
	});
	$('body').delegate('.tab_account','click',function(){
		$.ajax({
			type:"POST",
			dataType : "json", 
			data:{userId:$('.company_userId').val()},
		    url:basepath+"/user/accountInfo",
		    success:function(response){
		    	if(response.status=='SUCCESS'){
		    		var data=response.data;
		    		$('.allBalances').text(isNaN(data.allBalances)?data.allBalances:data.allBalances.formatMoney());
		    		$('.generalNoSupervisionBalances').text(isNaN(data.generalNoSupervisionBalances)?data.generalNoSupervisionBalances:data.generalNoSupervisionBalances.formatMoney());
		    		$('.generalNoSupervisionNumber').text(data.generalNoSupervisionNumber);
		    		$('.generalHaveSupervisionBalances').text(isNaN(data.generalHaveSupervisionBalances)?data.generalHaveSupervisionBalances:data.generalHaveSupervisionBalances.formatMoney());
		    		$('.generalHaveSupervisionNumber').text(data.generalHaveSupervisionNumber);
		    		$('.specialNoSupervisionBalances').text(isNaN(data.specialNoSupervisionBalances)?data.specialNoSupervisionBalances:data.specialNoSupervisionBalances.formatMoney());
		    		$('.specialNoSupervisionNumber').text(data.specialNoSupervisionNumber);
		    		$('.specialHaveSupervisionBalances').text(isNaN(data.specialHaveSupervisionBalances)?data.specialHaveSupervisionBalances:data.specialHaveSupervisionBalances.formatMoney());
		    		$('.specialHaveSupervisionNumber').text(data.specialHaveSupervisionNumber);
		    	}else{
		    		popWrite('notice-icon01',response.message);
		    	}
		    },
		    error:function(){
		    	popWrite('notice-icon01','请求失败');
		    }
		});
	});
});
function init(){
	$("body").delegate(".frontage", "change", function() {
		upload_type = true;
		$(this).parent().find(".image").blur();
	});
    $('body').on('blur','.image_ori',function(){
    	initImageVal('.image_ori');
    });
    $('body').on('blur','.image_bus',function(){
    	initImageVal('.image_bus');
    });
    $('body').on('blur','.image_tax',function(){
    	initImageVal('.image_tax');
    });
    
    $('body').on('click','#confirm_ori_media',function(){
    	var oriObj=$(this).parent().parent().parent().find('.image');
    	oriObj.blur();
    	if(oriObj.val()=='')
    		return ;
    	ajaxMedia({userId:$('.company_userId').val(),orgMediaId:oriObj.val(),
    		busMediaId:'',taxMediaId:''},'.ori_look',oriObj.val());
	});
    $('body').on('click','#confirm_bus_media',function(){
    	var busObj=$(this).parent().parent().parent().find('.image');
    	busObj.blur();
    	if(busObj.val()=='')
    		return ;
    	ajaxMedia({userId:$('.company_userId').val(),orgMediaId:'',
    		busMediaId:busObj.val(),taxMediaId:''},'.bus_look',busObj.val());
    	
	});
    $('body').on('click','#confirm_tax_media',function(){
    	var taxObj=$(this).parent().parent().parent().find('.image');
    	taxObj.blur();
    	if(taxObj.val()=='')
    		return ;
    	ajaxMedia({userId:$('.company_userId').val(),orgMediaId:'',
    		   busMediaId:'',taxMediaId:taxObj.val()},'.tax_look',taxObj.val());
	});
}
function ajaxMedia(mdata,c,d){
	$.ajax({
		type:"POST",
		dataType : "json", 
		data:mdata,
	    url:basepath+"/user/modifyMedia",
	    success:function(response){
	    	if(response.status=='SUCCESS'){
	    		$(".k-window-action.k-link").click();
	    		$(c).attr('href',mediaserver+'imageservice?mediaImageId='+d)
	    	}else{
	    		popWrite('notice-icon01',response.message);
	    	}
	    },
	    error:function(){
	    	popWrite('notice-icon01','请求失败');
	    }
	});
}
function initImageVal(class_){
		var validator = $(class_).kendoValidator({
			rules: {
				imageNotNullRule: UpdateImageRule.ruleNotNull,
				imageNotRightRule: UpdateImageRule.ruleNotRight
			},
			messages: {
				imageNotNullRule: UpdateImageRule.ruleNotNullMessage,
				imageNotRightRule: UpdateImageRule.ruleNotRightMessage
			},
			needRuleAttrbute : false
		}).data("kendoValidator");
		
		if(!validator.validate()) {
			return;
		};
}
function ajaxFileUpload(name) {
	$.ajaxFileUpload({
	    url: basepath+'/facade/save',
	    type: 'post',
	    secureuri: false, //一般设置为false
	    fileElementId: name, // 上传文件的id、name属性名
	    dataType: 'text', //返回值类型，一般设置为json、application/json
	    success: function(data){
	    	if(data == "FAILED"){
	    		$("#" + name + "Id").text("上传失败");
	    	}else if($("input[name="+name+"Id]").length > 0){
	    		$("input[name="+name+"Id]").val(data);
	    		$("#" + name + "Id").text("查看");
	    		$("#" + name + "Id").attr("href", mediaserver + "imageservice?mediaImageId=" + data);
	    		$("#" + name + "Id").attr("target", "_blank");
	    	}
	    	upload_type = false;
	    },
	    error: function(data, status, e){
	    	$("#" + name + "Id").text("上传失败");
	    	upload_type = false;
	    }
	});
};

function initContactInfo(){
	
	$('body').on('click','#comfirm_name',function(){
		setValidator($('#cName'),"required",true,contactNameRule.required);
		var validator=$('#cName').kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
 		modifyContacts({userId:$.trim($('.company_userId').val()),contactName:$.trim($('#cName').val()),
	        mobileNum:'',jobTitle:'',mail:''},'.cName',$.trim($('#cName').val()));
	});
	
	$('body').on('click','#comfirm_job',function(){
		setValidator($('#jobTitle'),"required",true,contactNameRule.required);
		var validator=$('#jobTitle').kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
 		modifyContacts({userId:$.trim($('.company_userId').val()),contactName:'',
	        mobileNum:'',jobTitle:$.trim($('#jobTitle').val()),mail:''},'.jobTitle',$.trim($('#jobTitle').val()));
 		
	});
	$('body').on('click','#comfirm_tel',function(){
		setValidator($('#tel'),"required",true,mobileNoRule.required);
		setValidator($('#tel'),"pattern",mobileNoRule.pattern,mobileNoRule.message);
		var validator=$('#tel').kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
 		modifyContacts({userId:$.trim($('.company_userId').val()),contactName:'',
 				        mobileNum:$.trim($('#tel').val()),jobTitle:'',mail:''},'.tel',$.trim($('#tel').val()));
	});
	$('body').on('click','#comfirm_mail',function(){
		setValidator($('#email'),"required",true,emailNoRule.required);
		setValidator($('#email'),"pattern",emailNoRule.pattern,emailNoRule.message);
		var validator=$('#email').kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
 		modifyContacts({userId:$.trim($('.company_userId').val()),contactName:'',
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
}

//修改联系人信息
function modifyContacts(mdata,a,c){
	$.ajax({
		type:"POST",
		dataType : "json", 
		data:mdata,
	    url:basepath+"/user/modifyContactInfo",
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
	if(type==2){
		$('.tr_editPassword').show();
		$('.tr_setPassword').hide();
	}
}