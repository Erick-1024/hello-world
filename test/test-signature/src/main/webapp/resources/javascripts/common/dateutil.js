Date.prototype.format = function(format){
	var o = {
		"M+" : this.getMonth()+1, //month
		"d+" : this.getDate(),    //day
		"h+" : this.getHours(),   //hour
		"m+" : this.getMinutes(), //minute
		"s+" : this.getSeconds(), //second
		"q+" : Math.floor((this.getMonth()+3)/3),  //quarter
		"S" : this.getMilliseconds() //millisecond
	}
	if(/(y+)/.test(format))
		format = format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
	for(var k in o)
		if(new RegExp("("+ k +")").test(format))
			format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] :("00"+ o[k]).substr((""+ o[k]).length));
	return format;
};


function computeDueDate(loanDate, loanPeriodUnit, loanPeriod) {
	var dueDate = new Date(loanDate);
	if("日" == loanPeriodUnit){
		dueDate.setDate(dueDate.getDate() + loanPeriod);
		return dueDate;
	}
	if("月" == loanPeriodUnit){
		dueDate.setMonth(dueDate.getMonth() + loanPeriod);
	    if (dueDate.getDate() != loanDate.getDate()){
	    	dueDate.setDate(0);
	    }
	    return dueDate;
	}
	if("年" == loanPeriodUnit){
		dueDate.setFullYear(dueDate.getFullYear() + loanPeriod);
		if (dueDate.getDate() != loanDate.getDate()){
			dueDate.setDate(0);
		}
		return dueDate;
	}
};

//增加天 
function addDays(date, value) {
    date.setDate(date.getDate() + value);
    return date;
}

//减去天 
function subtractDays(date, value) {
    date.setDate(date.getDate() - value);
    return date;
}

//减去月 
function subtractMonths(date, value) {
	var daysInMonth = new Array([0],[31],[28],[31],[30],[31],[30],[31],[31],[30],[31],[30],[31]);
	var strYear = date.getFullYear();  
	var strDay = date.getDate();  
	var strMonth = date.getMonth()+1;
	if(strYear%4 == 0 && strYear%100 != 0)
		daysInMonth[2] = 29;
	
	if(strMonth - value == 0){
		strYear -= 1;
		strMonth = 12;
	}else{
		strMonth -= value;
	}
	strDay = daysInMonth[strMonth] >= strDay ? strDay : daysInMonth[strMonth];
	if(strMonth<10)
		strMonth="0"+strMonth;
	if(strDay<10)
		strDay="0"+strDay;
	
	var datastr = strYear+"-"+strMonth+"-"+strDay;
	date = new Date(datastr);
	return date;
}