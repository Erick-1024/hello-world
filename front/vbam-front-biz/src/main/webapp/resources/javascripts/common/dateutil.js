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
	
	date.setUTCFullYear(strYear, strMonth-1, strDay);
	date.setUTCHours(0, 0, 0, 0);
	return date;
}

/**
 * 日期加上月份
 * @param dtstr
 * @param n
 * @returns {String}
 */
function DateAdd(interval, number, date) {
    switch (interval) {
    case "y": {
        date.setFullYear(date.getFullYear() + Number(number) );
        return date;
        break;
    }
    case "q": {
        date.setMonth(date.getMonth() + Number(number)  * 3);
        return date;
        break;
    }
    case "m": {
        date.setMonth(date.getMonth() + Number(number) );
        return date;
        break;
    }
    case "w": {
        date.setDate(date.getDate() + Number(number)  * 7);
        return date;
        break;
    }
    case "d": {
        date.setDate(date.getDate() + Number(number) );
        return date;
        break;
    }
    case "h": {
        date.setHours(date.getHours() + Number(number) );
        return date;
        break;
    }
    case "m ": {
        date.setMinutes(date.getMinutes() + Number(number) );
        return date;
        break;
    }
    case "s": {
        date.setSeconds(date.getSeconds() + Number(number) );
        return date;
        break;
    }
    default: {
        date.setDate(date.getDate() + Number(number) );
        return date;
        break;
    }
    }
}

//获得某个日期对应月份的最后一天  
//2016-07-21 返回的是 31
function getLastDay(date) {         
	var new_year = date.getFullYear();//取当前的年份          
	var new_month = date.getMonth()+1;//取下一个月     
	if(date.getMonth()>12) { //如果是7月，date.getMonth() 返回的是6
  	new_month -=12;//月份减          
  	new_year++;//年份增          
	}         
	var new_date = new Date(new_year,new_month,1);//取当年下个月中的第一天          
	return (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月最后一天日期          
}