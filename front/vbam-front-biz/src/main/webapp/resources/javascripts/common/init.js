String.prototype.formatBankAccountNo = function(){
	return this.replace(/\D/g, '').replace(/....(?!$)/g, '$& ');
}

String.prototype.parseBankAccountNo = function(){
	return this.replace(/\ +/g,"");
}

String.prototype.formatMoney = function(){
	return fmoney(this);
}

String.prototype.parseMoney = function(){
	return this.replace(/[^\d\.-]/g, "");
}

function fmoney(s) {
   
    var temp;
    //n = n > 0 && n <= 20 ? n : 2;
    if(/.+(\..*\.|\-).*/.test(s)){
        return;
    }
    s = parseFloat((s + "").replace(/[^\d\.\-]/g, "")).toFixed(2) + "";
    var l = s.split(".")[0].split("").reverse(),
        r = s.split(".")[1];
    t = "";
    for(i = 0; i < l.length; i ++ ) {
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length && (l[i+1]!='-')? "," : "");
    }
    temp = t.split("").reverse().join("") + "." + r;
    return temp;
}


function StandardPost(url,args){
    var body = $(document.body),
        form = $("<form method='post'></form>"),
        input;
    form.attr({"action":url});
    $.each(args,function(key,value){
        input = $("<input type='hidden'>");
        input.attr({"name":key});
        input.val(value);
        form.append(input);
    });

    form.appendTo(document.body);
    form.submit();
    document.body.removeChild(form[0]);
};

/**
 * 重写订单的计算金额方法
 * 修复BUG: 2.3e-19的toFixed(1)会显示2.3
 */
function toFixed (exponent) {
	var roundNum = 0.5;
	if (this < 0) {
		roundNum = -0.5;
	}
    var returnValue =  parseInt(this * Math.pow(10, exponent) + roundNum) / Math.pow(10, exponent) + "";
    if (exponent > 0) {
        var dotIndex = returnValue.indexOf(".");
        if (dotIndex == -1) {
            returnValue = returnValue + ".";
            dotIndex = returnValue.length - 1;
        }
        var diff = returnValue.length - 1 - dotIndex;
        if (diff < exponent) {
            for (; diff < exponent; diff++) {
                returnValue = returnValue + "0";
            }
        }
    }
    return returnValue;
}

/**
 * 小数精度
 * @param value
 * @param precision
 * @returns
 */
function oldToFixed(precision){
	var value = this;
	if(isNaN(value)){
		return value;
	}
	precision = precision || 0;
	var array = String(value).split(".");
	var integral = array[0];
	var fraction = array[1] || "";
	if(fraction.length > precision){
		if(fraction.charAt(precision) >= '5'){
			fraction = (fraction.substr(0, precision) * 1 + 1) + "";
		}else{
			fraction = fraction.substr(0, precision);
		}
	}else{
		fraction = paddingRight(fraction, precision, '0')
	}
	if(fraction * 1 >= Math.pow(10, precision)){
		integral = (integral * 1 + (integral < 0 ? -1 : 1)) + "";
		fraction = fraction.substr(fraction.length - precision, precision);
	}
	return integral + (precision == 0 ? "" : ("." + paddingLeft(fraction, precision, '0')));
}

function paddingRight(val, length, char){
	val = String(val || "");
	if(val.length >= length){
		return val.substr(0, length);
	}
	return val + new Array(length - val.length + 1).join(char);
}

function paddingLeft(val, length, char){
	val = String(val || "");
	if(val.length >= length){
		return val.substr(0, length);
	}
	return new Array(length - val.length + 1).join(char) + val;
}

/**
 * 加法
 */
function add(num){
	var array1 = this.toString().split(".");
	var array2 = num.toString().split(".");
	var f1 = (array1[1] || "").length;
	var f2 = (array2[1] || "").length;
	var base = Math.max(f1, f2);
	var num1 = (array1[0] + paddingRight(array1[1], base, '0')) * 1;
	var num2 = (array2[0] + paddingRight(array2[1], base, '0')) * 1;
	return (num1 + num2) / Math.pow(10, base);
}

/**
 * 减法
 */
function subtract(num){
	return this.add(-num);
}

/**
 * 乘法
 */
function multiply(num){
	var array1 = this.toString().split(".");
	var array2 = num.toString().split(".");
	var f1 = (array1[1] || "").length;
	var f2 = (array2[1] || "").length;
	var base = f1 + f2;
	num1 = (array1[0] + (array1[1]||"")) * 1;
	num2 = (array2[0] + (array2[1]||"")) * 1;
	return (num1 * num2) / Math.pow(10, base);
}

/**
 * 除法
 */
function divide(num){
	var array1 = this.toString().split(".");
	var array2 = num.toString().split(".");
	var f1 = (array1[1] || "").length;
	var f2 = (array2[1] || "").length;
	var base = Math.max(f1 + f2);
	num1 = (array1[0] + paddingRight(array1[1], base, '0')) * 1;
	num2 = (array2[0] + paddingRight(array2[1], base, '0')) * 1;
	return num1 / num2;
}

/**
 * 求余
 */
function mod(num){
	var array1 = this.toString().split(".");
	var array2 = num.toString().split(".");
	var f1 = (array1[1] || "").length;
	var f2 = (array2[1] || "").length;
	var base = Math.max(f1 + f2);
	num1 = (array1[0] + paddingRight(array1[1], base, '0')) * 1;
	num2 = (array2[0] + paddingRight(array2[1], base, '0')) * 1;
	var quotient = num1 / num2;
	if(quotient == parseInt(quotient)){
		return 0;
	}
	return (num1 - num2 * quotient.toString().split(".")[0]) / Math.pow(10, base);
}

/**
 * string TOFIXED方法，用于兼容原先的数字TOFIXED
 */
function strToFixed(precision){
	var value=this;
	if(isNaN(value)){
		return;
	}
	return new Number(value).toFixed(precision);
}
$(function(){

	String.prototype.toFixed=strToFixed;
	Number.prototype.toFixed = toFixed;
	Number.prototype.add = add;
	Number.prototype.subtract = subtract;
	Number.prototype.multiply = multiply;
	Number.prototype.divide = divide;
	Number.prototype.mod = mod;
});