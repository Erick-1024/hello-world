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