function ajaxFileUpload(url, uploadFileId, key, success, error) {
	$.ajaxFileUpload({
	    url: url,
	    type: 'post',
	    data: {redisKey : key},
	    secureuri: false, //一般设置为false
	    fileElementId: uploadFileId, // 上传文件的id、name属性名
	    dataType: 'text', //返回值类型，一般设置为json、application/json
	    success:success,
	    error:error
	});
};

function uploadSubmit(url,uploadFileId,success,error)
{
	　　var file = $('#uploadFileId').val();
	  var key = $("#choiceResult").data("key");
	  if(key == "")
	  {
		  $("#importResult").text("导入失败，请刷新页面！");
		  return;
	  }
	　　//检查是否已选择上传文件
	　　if (file != '') 
	  {
	　　　　var filename = file.replace(/.*(\/|\\)/, '');
	　　　　var fileext = (/[.]/.exec(filename)) ? /[^.]+$/.exec(filename.toLowerCase()) : '';
	　　　　//检查文件格式
	　　　　if (fileext == 'xlsx' || fileext == 'xls') 
		{
		　　　　//上传excel文件
			ajaxFileUpload(url, uploadFileId, key, success, error);
	　　　　}
	　　　　else 
		{
			$("#importResult").text("文件格式必须是*.xls或*.xlsx");
	　　　　}
	　　}
	　　else 
	  {
		$("#importResult").text("请选择excel文件！");
	　　}
}
//设置计数条数
function setNum()
{
	var legalNum = $("#legalLoanInfoGridDiv .sumData").text();
	var illegalNum = $("#illegalLoanInfoGridDiv .sumData").text();
	if("" == legalNum)
		legalNum = "0";
	if("" == illegalNum)
		illegalNum = "0";
	var total = parseInt(legalNum) + parseInt(illegalNum);
	$("#legalNum").text(legalNum);
	$("#illegalNum").text(illegalNum);
	$("#totalNum").text(total);
}
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
/*----------------- 通过放款信息列表 --------------*/
var legalColumns =
	[{
        field: "loanNo",
        title: "放款编号",
        width: 100
    },{
        field: "businessContractNo",
        title: "业务合同号",
        width: 200
    },{
        field: "financeCompany",
        title: "融资客户",
        width: 300
    },{
        field: "coreCompanyName",
        title: "核心企业",
        width: 300
    },{
        field: "voucherNo",
        title: "凭证号码",
        width: 150
    },{
        field: "currency",
        title: "币别",
        width: 80,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "businessProduct",
        title: "业务产品",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "receivablesAmount",
        title: "应收账款金额",
        width: 120,
        attributes: {
            style: "text-align: right"
        },
        template: function(data)
        {
        	var money = data.receivablesAmount + "";
        	return money.formatMoney();	
        }
    },{
        field: "receivablesBalance",
        title: "应收账款余额",
        width: 120,
        attributes: {
            style: "text-align: right"
        },
        template: function(data)
        {
        	var money = data.receivablesBalance + "";
        	return money.formatMoney();	
        }
    },{
        field: "financeAmount",
        title: "融资金额",
        width: 120,
        attributes: {
            style: "text-align: right"
        },
        template: function(data)
        {
        	var money = data.financeAmount + "";
        	return money.formatMoney();	
        }
    },{
        field: "financeBalance",
        title: "融资余额",
        width: 120,
        attributes: {
            style: "text-align: right"
        },
        template: function(data)
        {
        	var money = data.financeBalance + "";
        	return money.formatMoney();	
        }
    },{
        field: "interestRateUnit",
        title: "费率单位",
        width: 120,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "interestRate",
        title: "费率",
        width: 120,
        attributes: {
            style: "text-align: right"
        }
    },{
        field: "accountNo",
        title: "还款账号",
        width: 200,
        attributes: {
            style: "text-align: center"
        },
        template: function(data)
        {
        	if(null != data.accountNo)
        	{
        		var bankAccountNo = data.accountNo + "";	
            	return "<span>"+bankAccountNo.formatBankAccountNo()+"</span>"
//        		return "<span>"+data.accountNo+"</span>"
        	}
        	else
        		return "";
        		
        }
    },{
        field: "loanDate",
        title: "放款日",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "dueDate",
        title: "到期日",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "loanPeriodUnit",
        title: "期限单位",
        width: 120,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "loanPeriod",
        title: "贷款期限",
        width: 100
    },{
        field: "repaymentMethod",
        title: "还款方式",
        width: 130,
        attributes: {
            style: "text-align: center"
        }
    },{
        title: "设置还款账号",
        width: 150,
        template: function(data)
        {
        	if(data.accountNo == null)
        	{
        		return "<a id='sethello' class='comRow-link setAccount' href='javascript:void(0);' data-factorcompany=" + data.factorCompany + " data-financecompany=" +data.financeCompany + " data-loanno=" +data.loanNo +">设置</a>"
        	}
        	else
        		return "";
        }
    }];
//获取通过的放款信息
function getLegalLoanInfo()
{
	$("#finance-pass").remove();
	$("#legalLoanInfoGridDiv").append("<div class=\"finance-grid\" id=\"finance-pass\"></div>");
	var kendoGrid = $("#finance-pass").kendoGrid(
	{
        selectable: "row",  //设置可选择数据行
		columns: legalColumns,
		dataSource: {
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						key : $("#choiceResult").data("key")
						},
					url: basepath + "/loanInfo/input/query/success"	      
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
	  					$("#legalLoanInfoGridDiv .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#legalLoanInfoGridDiv .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
	  		}	  		
		},
		pageable: pageable
	});
}

/*----------------- 未通过放款信息列表 --------------*/
var illegalColumns =
	[{
        field: "loanNo",
        title: "放款编号",
        width: 100
    },{
        field: "businessContractNo",
        title: "业务合同号",
        width: 200
    },{
        field: "financeCompany",
        title: "融资客户",
        width: 300
    },{
        field: "coreCompanyName",
        title: "核心企业",
        width: 300
    },{
        field: "voucherNo",
        title: "凭证号码",
        width: 150
    },{
        field: "currency",
        title: "币别",
        width: 80,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "businessProduct",
        title: "业务产品",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "receivablesAmount",
        title: "应收账款金额",
        width: 120,
        attributes: {
            style: "text-align: right"
        }
    },{
        field: "receivablesBalance",
        title: "应收账款余额",
        width: 120,
        attributes: {
            style: "text-align: right"
        }
    },{
        field: "financeAmount",
        title: "融资金额",
        width: 120,
        attributes: {
            style: "text-align: right"
        }
    },{
        field: "financeBalance",
        title: "融资余额",
        width: 120,
        attributes: {
            style: "text-align: right"
        }
    },{
        field: "interestRateUnit",
        title: "费率单位",
        width: 80,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "interestRate",
        title: "费率",
        width: 80,
        attributes: {
            style: "text-align: right"
        }
    },{
        field: "accountNo",
        title: "还款账号",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "loanDate",
        title: "放款日",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "dueDate",
        title: "到期日",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "loanPeriodUnit",
        title: "期限单位",
        width: 120,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "loanPeriod",
        title: "贷款期限",
        width: 100
    },{
        field: "repaymentMethod",
        title: "还款方式",
        width: 130,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "verifyFailReason",
        title: "检验未通过原因",
        width: 200
    }];
//获取未通过的放款信息
function getIllegalLoanInfo()
{
	$("#finance-unpass").remove();
	$("#illegalLoanInfoGridDiv").append("<div class=\"finance-grid\" id=\"finance-unpass\"></div>");
	var kendoGrid = $("#finance-unpass").kendoGrid(
	{
        selectable: "row",  //设置可选择数据行
		columns: illegalColumns,
		dataSource: {
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						key : $("#choiceResult").data("key")
						},
					url: basepath + "/loanInfo/input/query/fail"	      
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
	  					$("#illegalLoanInfoGridDiv .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#illegalLoanInfoGridDiv .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
	  		}	  		
		},
		pageable: pageable
	});
}
/*----------------- 还款账号设置 --------------*/
var factor = "";
var finance = "";
var loanno = "";
function setMsg()
{
	$("#factorCompany").text(factor);
	$("#financeCompany").text(finance);
	$("#loanno").text(loanno);
}
function setButtonClick()
{
	setTimeout("setMsg()",10);
	//获取还款账户list
	$.ajax({
		type: 'post',
		url: "queryRepaymentAccounts",
		data: {financeCompany:finance},
		success: function(data)
		{
			if("" != data.data)
			{
				$("#setAccount").removeClass("hidden");
				$("#accountNo").kendoDropDownList({
					dataTextField: "accountNo",
		            dataValueField: "accountSupervisionId",
		            dataSource: data.data
				});
			}
			else
			{
				$("#noAccount").removeClass("hidden");
			}
		},
		error: function(data)
		{
			showAlertWin("网络异常："+data.responseText);
		}
	});
}

function gotoSaveSuccessPage()
{
	location.replace(basepath + "/loanInfo/input/gotoSaveSuccessPage?key=" + $("#choiceResult").data("key"));
}

/*----------------- 初始化JS --------------*/
$(document).ready(function()
{ 
	$(".main-header a,.redirect").click(function(e){
		e.preventDefault();
    	var url = $(this).attr("href");
    	$.ajax({
			type: "POST",
			url: basepath + "/loanInfo/input/countCorrectData?key=" + location.search.substr(location.search.indexOf("=")+1),
			dataType: "json",
			success: function(data){
				if (data.status == "WARNING") {
					window.location.href = url;
				}else{
					$(".template-leave").click();
			    	$("body").on("click", "#comfirmLeave",function(){
			    		window.location.href = url;
			    	});
				}
			}
		});
    });
	
    //确认离开当前页面提示弹窗
    new PopWindow(".template-leave", {
        title: "确认离开",
        width: 460,
        reload: true,
        template: "#template-leave"
    }).init();
	
	getLegalLoanInfo();
	getIllegalLoanInfo();
	//显示上传的文件名
	$(".fileBox").on("change",function()
	{
		$("#choiceResult").text($('#uploadFileId').val().replace(/.*(\/|\\)/, ''));
		uploadSubmit("importLoanInfoExcel", "uploadFileId", 
				function(data)
				{
					if(data == "success")
					{
						$("#importResult").text("Excel上传成功");
						getLegalLoanInfo();
						getIllegalLoanInfo();
						setTimeout("setNum()",200);
					}
					else
					{
						$("#importResult").text("");
						showAlertWin("上传失败，请确保Excel数据不为空，并重新上传！")
					}
				}, 
				function(data){
					showAlertWin("网络异常："+data.responseText);
					});
		$("#uploadFileId").remove();
		$(".fileBox").append('<input id="uploadFileId" class="frontage" type="file" name="excel" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel">');
	});
	
	//确认按钮点击事件
	$("#buttonConfirm").on("click", function()
	{
		$.ajax({
			type: 'post',
			url: "saveLoanInfoFromExcel",
			data: {redisKey:$("#choiceResult").data("key")},
			success: function(data)
			{
				if("SUCCESS" == data.status)
				{
					$(".dlg-notice").addClass("notice-icon02");
					$(".notice-content").text(data.message);
					setTimeout("gotoSaveSuccessPage()",1000);
				}		
				else
				{
					$(".dlg-notice").addClass("notice-icon01");
					$(".notice-content").text(data.message);
				}
			},
			error: function(data)
			{
				showAlertWin("网络异常："+data.responseText);
			}
		});
	});
	
	//选择文件按钮点击事件
	$("body").on("click","#uploadFileId", function()
	{
		$("#importResult").text("");
		$("#choiceResult").text("");
	});
	//设置按钮点击事件
	$("body").on("click",".setAccount", function()
	{
		 factor = $(this).data("factorcompany");
		 finance = $(this).data("financecompany");
		 loanno = $(this).data("loanno");
		 setButtonClick();
	});
	//保存还款账户事件
	$("body").on("click","#saveAccount", function()
	{
		var redisKey = $("#choiceResult").data("key");
		var loanNo = loanno;
		var accountSupervisionId = $("#accountNo").val();
		var accountNo =	$("#accountNo option:selected").text();
		$.ajax({
			type: 'POSt',
			url: "saveAccountNoToLoanInfo",
			data: {redisKey:redisKey,loanNo:loanNo,accountSupervisionId:accountSupervisionId,accountNo:accountNo},
			success: function(data)
			{
				if("设置成功" == data)
				{
					$(".k-i-close").click();
					getLegalLoanInfo();
				}
				else
				{
					showAlertWin(data);
				}
			},
			error: function(data)
			{
				showAlertWin("网络异常："+data.responseText);
			}
		});
	});
    //初始化详情操作弹窗
    new PopWindow(".setAccount", {
     title: "还款账号选择",
     width: 680,
     reload: true,
     template: "#template-setAccount"
     }).init();
    
    //确认保存数据库
    new PopWindow("#buttonConfirm", {
        title: "确认结果",
        width: 420,
        reload: true,
        template: "#template-entryExcel"
    }).init();
    
    //关闭弹窗
    $("body").on("click", ".dlg-wrapper .back-link", function(e){
        var win = $(e.target).closest(".k-window");
        var overlay = win.prev(".k-overlay");
        if(win.is(":visible")){
            win.css("display", "none");
            overlay.css("display", "none");
        }
    });
});

window.onload = function(){
    var clientHeight = 0;
    var realHeight = 0;
    //判断浏览器是否为IE
    var userAgent = navigator.userAgent;
    if(userAgent.indexOf("Firefox") > -1){
        clientHeight = $(window).height();
        realHeight = $(document).height();
    }else{
        clientHeight = document.body.clientHeight;
        realHeight = window.screen.availHeight;
    }
    if((realHeight-clientHeight) >= 64){
        $("#foot-fixBar").addClass("foot-fixBar");
    }else{
        $("#foot-fixBar").removeClass("foot-fixBar");
    }
    chkfix();
    $(window).scroll(function(){
        var scrollTop = $(window).scrollTop();
        var distanceTop = $('#footTop').offset().top - $(window).height();
        if(scrollTop >= distanceTop){
            $("#foot-fixBar").removeClass("foot-fixBar");
        }else{
            $("#foot-fixBar").addClass("foot-fixBar");
        }
    });
};

function chkfix(){
    var clientHeight = $(window).height();
    var offsetTop = $("#foot-fixBar").offset().top;

    if(offsetTop >= clientHeight){
        $("#foot-fixBar").addClass("foot-fixBar");
    }else{
        $("#foot-fixBar").removeClass("foot-fixBar");
    }
};