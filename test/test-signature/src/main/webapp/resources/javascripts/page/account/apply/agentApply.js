//----------------------------------------验证规则--------------------------------

function bindEvent() {
	function ajaxFileUpload($this) {
    	var $link = $this.parent().find('.registe-link');
    	var $input = $this.siblings('.image-id');
    	var size = cana.file.size($this);
    	window.console.log(size);
		var regex = /(.jpg|.png|.jpeg|.pdf)$/;
		var result = $this.val().toLowerCase().match(regex);
		$input.val('fail');
		if (result == null) {
			$input.attr('data-imageRule-msg','上传文件格式不正确');
			$input.change();
			return;
		} else if(size > cana.constant.maxImgSize){
			$input.change();
			$input.attr('data-imageRule-msg','上传文件最大只支持5M');
			return;
		}
    	$.ajaxFileUpload({
    	    url: '../../facade/save',
    	    type: 'post',
    	    secureuri: false, //一般设置为false
    	    fileElementId: $this.attr('id'), // 上传文件的id、name属性名
    	    dataType: 'text', //返回值类型，一般设置为json、application/json
    	    success : function(data){
    	    	if(data == '' || data.toLowerCase() == 'failed'){
    	    		$link.text("上传失败");
    	    	}
    	    	else if(-1 != data.indexOf("LARGE")){
    	    		$link.text("上传图片最大支持5M");
    	    	}
    	    	else{
		    		$input.val(data);
		    		$link.text("查看");
		    		$link.attr("href", mediaserver + "imageservice?mediaImageId=" + data +'&mediaType=video');
		    		$link.attr("target", "_blank");
		    		$input.change();
		    	}
		    },
		    error : function(data,status,e){
		    	$link.text("上传失败");
		    }
    	});
    };
    
	function checkBuyerNames($inputs){
		if($inputs.length < 1)return true;
		var $all = getSpecialInput();
		var names = [];
		var result = true;
		$all.each(function(){ // 获取所有已输入的非空买方名称
			var bn = $(this).val();
			if(bn != '')names.push($.trim(bn));
		});
		$inputs.each(function(){
			var $this = $(this);
			var bn = $this.val();
			var $span = $this.next();
			if(bn == ''){
				$span.text('不能为空');
				$span.css({'display':''});
				result = false;
			} else if(bn=='其他' || bn=='其它'){
				$span.text('不能为'+bn);
				$span.css({'display':''});
				result = false;
			} else if($.inArray(bn,names,$.inArray(bn,names)+1) > -1){
				$span.text('重复输入');
				$span.css({'display':''});
				result = false;
			}
		});
		$all.each(function(){// 去除重复提醒
			var $this = $(this);
			var bn = $this.val();
			var $span = $this.next();
			if ($.trim($span.text()) == '重复输入') {
				if ($.inArray(bn, names, $.inArray(bn, names) + 1) < 0) {
					$span.text('');
					$span.css({ 'display' : 'none'
					});
				}
			}	
		});
		return result;
	};
	
	
	addValidator('#companyName',companyNameRule);
	addValidator('#organizationCode',organizationCodeRule);
	addValidator('#businessLicenceCode',businessLicenceCodeRule);
	addValidator('#taxRegistrationCertificateCode',taxRegistrationCertificateCodeRule);
	addValidator('#contactName',contactNameRule);
	addValidator('#contactTel',mobileNoRule);
	addValidator('#contactMail',emailNoRule);
	
	$('body').on('click','.file', function() {
		$(this).prev().click();
	});
	
	$("body").on("change",'.image', function() {
		ajaxFileUpload($(this));
	});
	
	var validator = $("#agent-apply-form").kendoValidator({
		rules: {
			imageRule : function(input) {
				if (input.is(".image-id")) {
					if(input.val() == '') {
						input.attr('data-imageRule-msg','图片必须上传');
						return false;
					}
					if(input.val() == 'fail'){
						return false;
					}
				}
				return true;
			},
			companyRule : function(input){
//				if(input.attr('name') != 'companyName')return true;
//				if($('#companyName').parent().parent().is('.hidden')) return true;//隐藏时不做校验
//				var result = cana.postSync('checkFinanceName',{'financeName':$('#companyName').val()},function(data){
//				},function(data){
//					input.attr("data-companyRule-msg", data);
//				});
//				return result;
				return true;
			},
			accountExistRule : function(input){
				if($(".applyAut-wrap02").is('.hidden'))return true;//隐藏时不做校验
				if(input.attr('name') != 'supervisorAccountNo')return true;
				if(input.parent().parent().is('.hidden')) return true;//隐藏时不做校验
				var accountNo = $.trim(input.val()).parseBankAccountNo();
				if(accountNo == ''){
					input.attr("data-accountExistRule-msg", '账号不能为空');
					return false;
				}
				if(!/[0-9]{19}/.test($.trim(accountNo))){
					input.attr("data-accountExistRule-msg", '账号必须是19位数字');
					return false;
				}
				var financeName = $('input[name=supervisorType]').val()=='FACTOR'?'':$('#companyName').val();
				return cana.postSync('checkAccountExist',{'supervisorAccountNo':accountNo,'financeName':financeName},function(data){
					$('#accountName').text(data.accountName);
				},function(data){
					$('#accountName').text('');
					input.attr("data-accountExistRule-msg", data);
				});
			},
		},
		messages: {
		},
		needRuleAttrbute : false,
	}).data("kendoValidator");
	
	$("body").on("click", ".applyAut-next", function() {
		if(!validator.validate()) {
			return;
		};
		$(".applyAut-wrap01").hide();
        $(".applyAut-wrap02").removeClass("hidden");
        document.documentElement.scrollTop = document.body.scrollTop =0;
	});
	
	$('body').on('blur','.buyer-name',function(){
		if($(this).is(':visible')){
			checkBuyerNames($(this));
		}
	});
	
    // 提交表单
	var submit_lock = false; //限制重复提交
	$(".applyAut-wrap02").on('click','.createAccount',function(){
		if(submit_lock) return;
		var $inputs= getSpecialInput();
		var tag = $('.special-view').eq(0).find('label').filter('.active').find('span:last').text();
		if(tag == "专用账户"&&($inputs==undefined||$inputs==null||$inputs.length<1)){
			cana.alert('请手动输入或通过Excel导入买方名称');
			return;
		}
		$('.applyAut-table tr').find('input').removeAttr("disabled");
		$('.applyAut-table tr').find('select').removeAttr("disabled");
        $('.applyAut-table tr').filter('.hidden').find('input').attr('disabled','disabled');
        $('.applyAut-table tr').filter('.hidden').find('select').attr('disabled','disabled');
        $('.frontage').attr('disabled','disabled');
        var notPass = !validator.validate();
        notPass = !checkBuyerNames(getSpecialInput()) || notPass; // 用于把所有的验证提醒都打开
        if(notPass){
        	$('.applyAut-table tr').find('input').removeAttr("disabled");
            $('.applyAut-table tr').find('select').removeAttr("disabled");
        	return;
        }
        var form = $('#agent-apply-form').serializeArray();
		var params = {};
		for(var i in form){
			if(form[i].name == 'supervisorAccountNo') {
				params.supervisorAccountNo = form[i].value.parseBankAccountNo();
			}else if(form[i].name == 'buyerNames'){
				if(params.buyerNames == undefined){
					params.buyerNames = [form[i].value];
				}
				else{
					params.buyerNames.push(form[i].value);
				}
			}else{
				params[form[i].name] = form[i].value;
			}
		}
		submit_lock = true;
        cana.post($('#agent-apply-form').attr('action'),params,function(data){
        	window.location.href = 'result/agent'
        },function(data){
        	$('.applyAut-table tr').find('input').removeAttr("disabled");
            $('.applyAut-table tr').find('select').removeAttr("disabled");
        	submit_lock = false;
        	cana.alert('提交申请失败：'+data);
        });
    });
	
	 $(".radioBox .radio").on('click',function(){
	    	var $this = $(this);
	    	var $tr = $this.parent().parent().parent();
	    	var $input = $this.parent().find('input');
	    	var tag = $this.children().eq(1).text() ;
	    	$this.addClass("active");
	    	$this.siblings().removeClass("active");
	    	if($input!=undefined && $input!=null)$input.val($this.attr('data-val'));
	        switch(tag){
	        case "一般账户":
	        	$('.account-cnt').removeClass("hidden");
	        	$('.special-view').addClass("hidden");
	        	$('.special-view').eq(0).removeClass("hidden");
	        	$('.account-expt01').addClass('hidden');
	        	$('.account-expt02').addClass('hidden');
	        	var $acc = $('.special-account');
	        	if($acc!=undefined && $acc!=null)$acc.addClass("hidden");
	        	break;
	        case "专用账户":
	        	$('.account-cnt').addClass("hidden");
	        	$('.special-view').removeClass("hidden");
	        	var special = $('.special-view:last label').filter('.active').children().eq(1).text() ;
	        	if(special == "手动录入") $('.account-expt01').removeClass('hidden');
	        	else if(special == "Excel导入") $('.account-expt02').removeClass('hidden');
	        	$view = $('.special-view').eq(2);
	        	var val = "";
	        	var $acc = $('.special-account');
	        	if($view!=undefined && $view!=null)val=$view.find('label').filter('.active').children().eq(1).text() ;
	        	if($acc!=undefined && $acc!=null && val=="录入")$acc.removeClass("hidden");
	        	break;
	        case "新建":
	        	$tr.next().addClass("hidden");
	        	$tr.next().next().addClass("hidden");
	        	break;
	        case "录入":
	        	$tr.next().removeClass("hidden");
	        	$tr.next().next().removeClass("hidden");
	        	break;
	        case "手动录入":
	        	$(".account-expt01").removeClass("hidden");
	        	$(".account-expt02").addClass("hidden");
	        	break;
	        case "Excel导入":
	        	$(".account-expt01").addClass("hidden");
	        	$(".account-expt02").removeClass("hidden");
	        	break;
	        case '融资客户':
	        	validator.validate();
	        	break;
	        case '资金方':
	        	validator.validate();
	        	break;
	        }
	    });
}

//----------------------------------------------------检查用户名（不能为空/重名）-----------------------

$(function() {
	bindEvent();
})