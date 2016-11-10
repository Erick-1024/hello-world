var pageSize = 5;
var pageable = {
	      pageSizes: false,  //设置每页显示行数
	      buttonCount: 5,  //显示页数
	      pageSize: pageSize,//每页显示多少行
	      page: 1,  //当前页，默认设为1
	      messages: {
	          display: "共<span class='sumData'>{2}</span>条数据",
	          empty: "没有数据",
	          page: "页",
	          of: "/ {0}",
	          itemsPerPage: "条/页",
	          first: "第一页",
	          last: "最后一页",
		      previous: "前一页",
		      next: "下一页"
	      }
	  };

var legalColumns =
	[{
        field: "ticketNo",
        title: "票号",
        width: 150,
		attributes: {
            style: "text-align: center"
        }
    },{
        field: "counterpartyName",
        title: "交易对手",
        width: 250,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "orderId",
        title: "订单编号",
        width: 300,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "issueDate",
        title: "出票时间",
        width: 200,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "loanAmount",
        title: "应收金额",
        width: 200,
        attributes: {
            style: "text-align: center"
        },
        template: function(data){
            return fmoney(data.loanAmount/100);
        }
    }];

var illegalColumns;

$(function(){
	if(urlParam == 'settlement') {
		legalColumns.push({
			field: "settleAmount&interestAmount",
	        title: "实还金额",
	        width: 200,
	        attributes: {
	            style: "text-align: center"
	        },
	        template: function(data){
	            return fmoney((data.loanAmount + data.interestAmount)/100);
	        }
		});
	}
	
//	illegalColumns = JSON.parse(JSON.stringify(legalColumns));	//　此方法的拷贝，函数function会失效
	illegalColumns = deepClone(legalColumns);
	illegalColumns.push(
		{
	        field: "reason",    	
	        title: "原因",
	        width: 250,
	        attributes: {
	            style: "text-align: center"
	        }
	    }
	);
	
	$('body').on('click', 'a[name=tipBoxButton]', function() {
		tipBoxTemplate.close();
	});
	
	//文件input
	$("#uploadExcel").click(function() {
		if($("#rediskey").val()==""){
			$("#importResult").text("载入失败，请刷新页面！");
			return;
		}
		$("#photos").click();
	});
	
	//导入按钮
	$("#importExcel").click(function(){
		if($("#rediskey").val()==""){
			$("#importResult").text("导入失败，请刷新页面！");
			return;
		}
		getPassList();
		getUnPassList();
	});

	$("#photos").kendoUpload({
		async: {
	        saveUrl: basepath + "/homsom/settlement/" + urlParam + "/upload/" + channel,
	        autoUpload: true
	    },
		multiple: true,
		showFileList: false,
		upload: onUpload,
		success: onSuccess,
		error: onError
	});
	
	//提交按钮点击事件
	$("#buttonConfirm").on("click", function() {
		cana.post(basepath + "/homsom/settlement/" + urlParam + "/import/" + channel + "?rediskey=" + $("#rediskey").val(), null, submitSuccess, submitError, null);
	});
	
});

function onSuccess(e){
	$("#uploadExcel").text("上传附件");
	if(e.response.status != "SUCCESS"){
    	$.each(e.files, function () {
    		showTipBox("notice-icon03", e.response.message);
        });
    } else {
    	$.each(e.files, function () {
    		$(".import-success-box").append('<span>'+this.name+'</span>');
        });
    }
}

function onError(e){
	$("#uploadExcel").text("上传附件");
	showTipBox("notice-icon03", '上传失败');
}

function onUpload(e) {
    var files = e.files;
    $("#uploadExcel").text("上传中...");
    $.each(files, function () {
    	 e.data = { rediskey: $("#rediskey").val() };
        if (this.extension.toLowerCase() != ".xlsx" && this.extension.toLowerCase() != ".xls") {
        	$("#uploadExcel").text("上传附件");
        	$(".import-success-box").append('<span style="color:red">'+this.name+'&emsp;不是excel文件</span>');
            e.preventDefault();
        }
    });
}

function submitSuccess(data) {
	showTipBox("notice-icon02", "提交成功");
	setTimeout("gotoListPage()",1000);
}

function submitError(data) {
	showTipBox("notice-icon03", data);
}

function gotoListPage(){
	location.href = encodeURI(basepath + '/homsom/settlement/goto/' + redict);
}

function setDataNum(num, passed){
	var numStr;
	if(passed){
		$("#legalNum").text(num);
		numStr = $("#illegalGridDiv .sumData").text();
	}else{
		$("#illegalNum").text(num);
		numStr = $("#legalGridDiv .sumData").text();
	}
	if("" == numStr)
		numStr = "0";
	var total = parseInt(numStr) + num;
	$("#totalNum").text(total);
}

function getPassList() {
	$("#legalGridDiv").empty();
	var kendoGrid = $("#legalGridDiv").kendoGrid(
	{
        selectable: "row",  //设置可选择数据行
		columns: legalColumns,
		dataSource: {
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						rediskey : $("#rediskey").val(),
						passed: true,
						},
					url: basepath + "/homsom/settlement/" + urlParam + "/query"
				}
			},
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
	  		schema:{
	  			data: "data",
	  			total: function(data) {
					var total = data.totalNum;
	  				if(total == 0){
	  					$("#legalGridDiv .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#legalGridDiv .k-grid-header-wrap").css("overflow-x","");
					}
	  				setDataNum(total, true);
	  				return total;
				}
	  		}	  		
		},
		pageable: pageable
	});
}

function getUnPassList(){
	$("#illegalGridDiv").empty();
	var kendoGrid = $("#illegalGridDiv").kendoGrid(
	{
        selectable: "row",  //设置可选择数据行
		columns: illegalColumns,
		dataSource: {
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						rediskey : $("#rediskey").val(),
						passed: false,
						},
					url: basepath + "/homsom/settlement/" + urlParam + "/query"	      
				}
			},
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
	  		schema:{
	  			data: "data",
	  			total: function(data) {
					var total = data.totalNum;
	  				if(total == 0){
	  					$("#illegalGridDiv .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#illegalGridDiv .k-grid-header-wrap").css("overflow-x","");
					}
	  				setDataNum(total, false);
	  				return total;
				}
	  		}	  		
		},
		pageable: pageable
	});
}

// 深度复制，其中的函数function是直接赋值的，还是共享内存的，这是因为函数更多的是完成某些功能，有个输入值和返回值，而且对于上层业务而言更多的是完成业务功能，并不需要真正将函数深拷贝。
function deepClone(data) {
	  var t = type(data), o, i, ni;
	  
	  if(t === 'array') {
	      o = [];
	  }else if( t === 'object') {
	      o = {};
	  }else {
	      return data;
	  }
	  
	  if(t === 'array') {
	      for (i = 0, ni = data.length; i < ni; i++) {
	          o.push(deepClone(data[i]));
	      }
	      return o;
	  }else if( t === 'object') {
	      for( i in data) {
	          o[i] = deepClone(data[i]);
	      }
	      return o;
	  }
}

function type(obj) {
	  var toString = Object.prototype.toString;
	  var map = {
	      '[object Boolean]'  : 'boolean', 
	      '[object Number]'   : 'number', 
	      '[object String]'   : 'string', 
	      '[object Function]' : 'function', 
	      '[object Array]'    : 'array', 
	      '[object Date]'     : 'date', 
	      '[object RegExp]'   : 'regExp', 
	      '[object Undefined]': 'undefined',
	      '[object Null]'     : 'null', 
	      '[object Object]'   : 'object'
	  };
	  return map[toString.call(obj)];
}
