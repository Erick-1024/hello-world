var cana = {};

cana.post = function(url,data,success,error,complete){
	$.ajax({
		url:url,
		data:data,
		type:'post',
		timeout : 15000,
		dataType:'json',
		traditional:true,
		success : function(response) {
			if (response.status.toLowerCase() == 'success') {
				if (success != undefined)
					success(response.data);
			} else if (response.status.toLowerCase() == 'failed') {
				if (error != undefined)
					error(response.message);
			}
		},
		error : function(response){
			if(error != undefined)
				error('网络异常!');
			else
				cana.alert('网络异常!');
		},
		complete : function(response){
			if(complete != undefined)
				complete(response.data);
		},
		timeout : function(){
			cana.alert("请求超时!");
		}
	});
};

cana.postSync = function(url, data, success, error, complete) {
	var response = $.ajax({
		url : url,
		data : data,
		type : 'post',
		dataType : 'json',
		traditional:true,
		async : false
	});
	if (response.readyState == 4 && response.status / 100 == 2) {
		var p = true;
		var result = $.parseJSON(response.responseText);
		if (result.status.toLowerCase() == 'success') {
			if (success != undefined)
				success(result.data);
		} else if (result.status.toLowerCase() == 'failed') {
			if (error != undefined)
				error(result.message);
			p = false;
		}
	} else {
		if (error != undefined)
			error('网络异常!');
		else
			cana.alert('网络异常!');
		p = false;
	}
	if (complete != undefined)
		complete(result.data);
	return p;
};

cana.get = function(url,data,success,error,complete){
	$.ajax({
		url:url,
		data:data,
		type:'get',
		timeout : 15000,
		dataType:'json',
		traditional:true,
		success : function(response) {
			if (response.status.toLowerCase() == 'success') {
				if (success != undefined)
					success(response.data);
			} else if (response.status.toLowerCase() == 'failed') {
				if (error != undefined)
					error(response.message);
			}
		},
		error : function(response){
			if(error != undefined)
				error('网络异常!');
			else
				cana.alert('网络异常!');
		},
		complete : function(response){
			if(complete != undefined)
				complete(response.data);
		},
		timeout : function(){
			cana.alert("请求超时!");
		}
	});
};

cana.ajaxSubmit = function(form, success, error, complete) {
	var $form;
	if (!($() && $().ajaxSubmit)){
		cana.alert('required JQuery and JQuery.form');
		return;
	}
	if (form.jquery)
		$form = form;
	else
		$form = $('#' + form);
	$form.ajaxSubmit({
		timeout : 15000,
		dataType : 'json', // 'xml', 'script', or 'json'
		traditional:true,
		success : function(response) {
			if (response.status.toLowerCase() == 'success') {
				if (success != undefined)
					success(response.data);
			} else if (response.status.toLowerCase() == 'failed') {
				if (error != undefined)
					error(response.message);
			}
		},
		error : function(response) {
			if (error != undefined)
				error('网络异常!');
			else
				cana.alert('网络异常!');
		},
		complete : function(response) {
			if (complete != undefined)
				complete(response.data);
		},
		timeout : function(){
			cana.alert("请求超时!");
		}
	});
};

/**
 * luhn校验的过程：
 * 1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加.
 * 2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，则将其减去9），再求和.
 * 3、将奇数位总和加上偶数位总和，结果应该可以被10整除
 * 例如，卡号是：5432123456788881
 * 则奇数、偶数位（用红色标出）分布：5432123456788881 
 * 奇数位和=35 
 * 偶数位乘以2（有些要减去9）的结果：1 6 2 6 1 5 7 7，求和=35
 *  最后35+35=70 可以被10整除，认定校验通过。
 */	

cana.bank = {
	luhn : function(accountNo) {
		if (accountNo == null || 
			accountNo == undefined || 
			accountNo.length == 0 || 
			!accountNo.match("\\d+")) {
			return false;
		}
		var chs = accountNo.split("");
		var luhmSum = 0;
		for (var i = chs.length - 1; i >= 0; i--) {
			var k = chs[i] - '0';
			if ((chs.length - i) % 2 == 0) {
				k *= 2;
				k = Math.floor(k / 10) + k % 10;
			}
			luhmSum += k;
		}
		return (luhmSum % 10 == 0) ? true : false;
	},
	withdrawFee : function(amount) {
		if(amount < 1000 * 10)return '5.50';
		if(amount < 1000 * 100)return '10.50';
		if(amount < 1000 * 500)return '15.50';
		if(amount < 1000 * 1000)return '20.50';
		var value = amount*0.00002+0.5;
		return value > 200 ? '200.00' : value.toFixed(2);
	}
};

cana.alert = function(data){
	showAlertWin(data);
};

cana.success = function(data){
	showSuccessWin(data);
};

/**
 * 页面常量
 */
cana.constant = {
	maxImgSize : 5242880,	//5M
};


cana.file = {
	size : function($file) {
		if (!$file.jquery) {
			window.console.log('required jquery obj！');
			return;
		}
		var img = $file[0];//ie无法获取
		// img.fileSize：IE ,fileObj.files[0].fileSize：chrome，fileObj.files[0].size：FF
		var size = img.fileSize || (img.files && img.files[0].fileSize) || (img.files && img.files[0].size);
		return size;
	},
};

cana.money = {
	toChinese : function(n) {
		if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n)){
			window.console.log('数据非法');
			return '';
		}
		if((n+'').split('.')[0].length >12){
			window.console.log('最多支持12位整数');
			return '';
		}
        var unit = '仟佰拾亿仟佰拾万仟佰拾元角分', str = '';
            n += '00';
        var p = n.indexOf('.');
        if (p >= 0)
            n = n.substring(0, p) + n.substr(p+1, 2);
            unit = unit.substring(unit.length - n.length);
        for (var i=0; i < n.length; i++)
            str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
        return str.replace(/零(仟|佰|拾|角)/g, '零').replace(/(零)+/g, '零').replace(/零(万|亿|元)/g, '$1').replace(/(亿)万|壹(拾)/g, '$1$2').replace(/^元零?|零分/g, '').replace(/元$/g, '元整');
	},
};


cana.string = {
	removeBlank : function(s){
		return s.replace(/\s/g, '');
	},
	blank4char : function(s){
		return cana.string.removeBlank(s).replace(/[\s]/g, '').replace(/(\d{4})(?=\d)/g, "$1 ")
	},
};