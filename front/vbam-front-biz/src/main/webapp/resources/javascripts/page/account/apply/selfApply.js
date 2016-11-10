$(function(){
	var submit_lock = false; //限制重复提交
	function resultForawrd(data){
		$('body').append('<form id="result-forward" action="result/self" method="post"></form>');
		for(var index in data){
			$('#result-forward').append('<input type="hidden" name="accountNos" value="'+data[index]+'">');
		}
		$('#result-forward').submit();
	};
	function validateBuyerName(){
		var v = true;
		var $buyers = getSpecialInput();
		if($buyers == null || $buyers.length < 1){
			cana.alert('请手动输入或通过Excel导入买方名称');
			return false;
		}
		$buyers.each(function(){
            if(!validate($(this)))v = false;
        });
		return v;
	};
    $('.applyAut-wrap01').on('click','.createAccount',function(){
    	if(submit_lock)return;
    	submit_lock = true;
    	var $this = $(this);
    	$this.text('开户中...');
    	cana.ajaxSubmit($('#selfApply'),function(data){
    		resultForawrd(data);
        },function(data){
        	$this.text('立即开户');
        	submit_lock = false;
        	cana.alert(data);
        });
    });
	$(".applyAut-next").click(function(){
        $(".applyAut-wrap01").hide();
        $(".applyAut-wrap02").removeClass("hidden");
	});
	$(".applyAut-wrap02").on('mousedown','.createAccount',function(){
		var accountType = $('.applyAut-wrap02').find('.radio').filter('.active').attr('data-val');
		if(accountType=='SPECIAL' && !validateBuyerName()){
			return;
		}
		$('tr').filter('.hidden').find('input').attr('disabled','disabled');
        $('tr').filter('.hidden').find('select').attr('disabled','disabled');
        $('.frontage') && $('.frontage').attr('disabled','disabled');
        if(submit_lock)return;
    	submit_lock = true;
    	var $this = $(this);
    	$this.text('开户中...');
        cana.ajaxSubmit($('#selfApply'),function(data){
        	resultForawrd(data);
        },function(data){
        	$this.text('立即开户');
        	submit_lock = false;
        	cana.alert(data);
        });
	});
    $('#acount-table').on('blur','input',function(){
        $('#acount-table input').each(function(){
            validate($(this));
        });
    });
    $('#acount-table01').on('blur','input',function(){
        $('#acount-table01 input').each(function(){
            validate($(this));
        });
    });
    
    $(".radioBox .radio").on('click',function(){
    	var $this = $(this);
    	var tag = $this.children().eq(1).text() ;
    	var $input = $this.parent().find('input');
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
        	break;
        case "专用账户":
        	var special = $('.special-view').eq(1).find('.radio').filter('.active').find('span:last').text();
        	$('.special-view').removeClass('hidden');
        	$('.account-cnt').addClass("hidden");
        	if(special == "手动录入") $('.account-expt01').removeClass('hidden');
        	else if(special == "Excel导入") $('.account-expt02').removeClass('hidden');
        	break;
        case "手动录入":
        	$(".account-expt01").removeClass("hidden");
        	$(".account-expt02").addClass("hidden");
        	break;
        case "Excel导入":
        	$(".account-expt01").addClass("hidden");
        	$(".account-expt02").removeClass("hidden");
        	break;
    	}
    });
    
    function validate($this){
        $this.next().text('');
        var buyerName = $this.val();
	    if(buyerName == '' || buyerName == null || buyerName == undefined){
		$this.next().text('不能为空');
		$this.next().css({'display':''});
		return false;
		}
		else if(buyerName=='其他'||buyerName=='其它'){
		$this.next().text('不能为'+buyerName);
		$this.next().css({'display':''});
		return false;
		}
	    $this.next().css({'display':'none'});
		return true;
    }
});