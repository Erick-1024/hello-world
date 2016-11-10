var pageSize = 10;
var userType = "";
var dataSource = {
		type: "json", //后台返回的数据类型
		transport: {
			read: {
				type: "post",
				data: {
					userType: $("#userType").data("usertype"),
					factorCompany: $("input[name=factorCompany]").val(),
					financeCompany: $("input[name=financeCompany]").val(),
					coreCompanyName: $("input[name=coreCompanyName]").val(),
					businessContractNo: $("input[name=businessContractNo]").val(),
					businessProduct: $("input[name=businessProduct]").val(),
					loanStartDate: $("input[name=loanStartDate]").val(),
					loanEndDate: $("input[name=loanEndDate]").val(),
					dueStartDate: $("input[name=dueStartDate]").val(),
					dueEndDate: $("input[name=dueEndDate]").val(),
					accountNoStatus: $("#accountNoStatus").val(),
					settleStatus: $("#settleStatus").val(),
					loanId: $("input[name=loanId]").val()
				},
				url: basepath + "/loanInfo/manage/getLoanInfoList"
			}
		},
		serverPaging: true,
		serverSorting: true,
		serverFiltering: true,
		schema: {
			data: "data",
			total: function(data) {
				var total = data.totalNum;
  				if(total == 0){
  					$("#repayManage-grid .k-grid-header-wrap").css("overflow-x","auto");
  				}else {
  					$("#repayManage-grid .k-grid-header-wrap").css("overflow-x","");
				}
  				return total;
			}
		}
};
var pageable = {
	    pageSizes: false, //设置每页显示行数
	    pageSize: pageSize,
	    page: 1,
	    buttonCount: 5,  //显示页数
	    messages: {
	        display: "共<span class='sumData'>{2}</span>条数据",
	        empty: "没有数据",
	        page: "页",
	        of: "/ {0}",
	        itemsPerPage: "条/页",
	        first: "第一页",
	        previous: "前一页",
	        next: "下一页",
	        last: "最后一页"
	    }
};

function generateAll()
{
	$("#repayManage-grid").kendoGrid({
		selectable: "row",  //设置可选择数据行
		dataSource:{
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						userType: $("#userType").data("usertype"),
						factorCompany: $("input[name=factorCompany]").val(),
						financeCompany: $("input[name=financeCompany]").val(),
						outCustomerName: $("input[name=outCustomerName]").val(),
						coreCompanyName: $("input[name=coreCompanyName]").val(),
						businessContractNo: $("input[name=businessContractNo]").val(),
						businessProduct: $("input[name=businessProduct]").val(),
						loanStartDate: $("input[name=loanStartDate]").val(),
						loanEndDate: $("input[name=loanEndDate]").val(),
						dueStartDate: $("input[name=dueStartDate]").val(),
						dueEndDate: $("input[name=dueEndDate]").val(),
						accountNoStatus: $("#accountNoStatus").val(),
						settleStatus: $("#settleStatus").val(),
						loanId: $("input[name=loanId]").val()
					},
					url: basepath + "/loanInfo/manage/getLoanInfoList"
				}
			},
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			schema: {
				data: "data",
				total: function(data) {
					var total = data.totalNum;
	  				if(total == 0){
	  					$("#repayManage-grid .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#repayManage-grid .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
			}
	},
	        columns: [{
	            field: "loanNo",
	            title: "放款编号",
	            width: 150
	        },
	        {
                field: "id",
                title: "平台流水号",
                width: 150,
	            attributes: {
	                style: "text-align: center"
	            }
            },{
	            field: "businessContractNo",
	            title: "业务合同号",
	            width: 150
	        },
	        {
                field: "factorCompany",
                title: "资金方",
                width: 200,
                hidden: userType == "FACTOR"
            },{
	            field: "financeCompany",
	            title: "融资客户",
	            width: 200,
                hidden: userType == "FINACE"
	        },{
	            field: "outCustomerName",
	            title: "外部帐户名称",
	            width: 150
	        },{
	            field: "coreCompanyName",
	            title: "核心企业",
	            width: 200
	        },{
	            field: "businessProduct",
	            title: "业务产品",
	            width: 150
	        },{
	            field: "financeAmount",
	            title: "融资金额",
	            width: 100,
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
	            width: 100,
	            attributes: {
	                style: "text-align: right"
	            },
	            template: function(data)
	            {
	            	var money = data.financeBalance + "";
	            	return money.formatMoney();	
	            }
	        },{
	            field: "loanDate",
	            title: "放款日",
	            width: 120,
	            attributes: {
	                style: "text-align: center"
	            }
	        },{
	            field: "dueDate",
	            title: "到期日",
	            width: 120,
	            attributes: {
	                style: "text-align: center"
	            }
	        },{
	            field: "interestRateUnit",
	            title: "费率单位",
	            width: 70,
	            attributes: {
	                style: "text-align: center"
	            }
	        },{
	            field: "interestRate",
	            title: "费率",
	            width: 70
	        },{
	            field: "repaymentMethod",
	            title: "还款方式",
	            width: 180
	        },
	        {
                field: "accountNo",
                title: "还款账号",
                width: 180,
	            attributes: {
	                style: "text-align: center"
	            },
	            template: function(data)
	            {
	            	var bankAccountNo = data.accountNo + "";
	            	return bankAccountNo.formatBankAccountNo();	
	            }
            },{
	            field: "settleStatus",
	            title: "结清状态",
	            width: 70,
	            attributes: {
	                style: "text-align: center"
	            }
	        },{
                title: "查询",
                sortable: false,
                width: 200,
                template: function(data)
                {
                	var link = "";
                	if(planDetailAuth)
                		link += "<a target='_blank' class='comRow-link incRole-link' href='gotoRepaymentPlanDetails?loanId=" + data.id + "'>还款计划</a>";
                	if(repaymetDetailsHistoryAuth)
                		link += "<a class='comRow-link repayDetail-link' href='javascript:void(0);' data-loanid="+ data.id +">历史还款明细</a>";
                	return link;
                }
            },{
                title: "操作",
                width: 300,
                template: function(data){
                	var link = "";
                	if(data.settleStatus=="未结清" && activeRepaymentAuth && !data.accessToAdjustAndOfflineRepayment && (null != data.accountNo && "" != data.accountNo))
                		link += "<a target='_blank' class='comRow-link incRole-link' href='"+basepath+"/repayment/active/gotoActiveRepayment?loanInfoId=" + data.id + "'>主动还款</a>";
                	if(offlineRepaymentAuth && data.accessToAdjustAndOfflineRepayment)
            			link += "<a target='_blank' class='comRow-link incRole-link' href='"+basepath+"/repayment/adjustment/toAdjustment?loanInfoId=" + data.id + "'>线下还款</a>";
                	if(adjustMentAuth && data.accessToAdjustAndOfflineRepayment)
            			link += "<a target='_blank' class='comRow-link incRole-link' href='"+basepath+"/repayment/adjustment/gotoAdjustment?loanInfoId=" + data.id + "'>调账</a>";
                	if(setAccountNoAuth　&& (null == data.accountNo || "" == data.accountNo))
	            		link+= "<a id='sethello' class='comRow-link setAccount' href='javascript:void(0);' data-factorcompany=" + data.factorCompany + " data-financecompany=" +data.financeCompany + " data-loanno=" +data.loanNo + " data-loanid=" +data.id +">设置还款账号</a>";

                	return link;
                }
            }],
	        pageable: pageable
	    });
}
function searchLoanInfo() {
	$("#repayManage-grid").remove();
	$("#repayManage-gridWrap").append("<div class=\"finance-grid\" id=\"repayManage-grid\" style=\"margin-top:15px;\"></div>");
	generateAll();
	
}

/*----------------- 还款账号设置 --------------*/
var factor = "";
var finance = "";
var loanno = "";
var loanid = "";
function setMsg()
{
	$("#factorCompany").text(factor);
	$("#financeCompany").text(finance);
	$("#loanno").text(loanno);
}
function setButtonClick()
{
	//获取还款账户list
	$.ajax({
		type: 'post',
		url: basepath + "/loanInfo/input/queryRepaymentAccounts",
		data: {financeCompany:finance},
		success: function(data)
		{
			$("#accountWaitHtml").addClass("hidden");
			if("" != data.data)
			{
				$("#setAccount").removeClass("hidden");
				$("#accountNo").kendoDropDownList({
					dataTextField: "accountNo",
		            dataValueField: "accountSupervisionId",
		            dataSource: data.data
				});
				setMsg();
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
$(function(){
	userType = $("#repayManage-gridWrap").data("usertype");
	searchLoanInfo();
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('.form-search-link').click();
    	}
    });
	
    $(".form-search-link").click(function(){
    	$("#repayManage-grid").remove();
    	$(".whiteBg").append("<div class=\"finance-grid\" id=\"repayManage-grid\" style=\"margin-top:15px;\"></div>");
    	searchLoanInfo();
    });
    
    $("body").on("click", ".editManual", function(e){
    	var id = $(this).attr("data-for");
    	var rediskey = $("input[name=redisKey]").val();
    	window.open(basepath + "/repayment/plan/input/gotoPlanManualInput?id=" + id+"&redisKey="+rediskey);
    });
    
	//设置按钮点击事件
	$("body").on("click",".setAccount", function()
	{
		 factor = $(this).data("factorcompany");
		 finance = $(this).data("financecompany");
		 loanno = $(this).data("loanno");
		 loanid = $(this).data("loanid");
		 setButtonClick();
	});
	
	//保存还款账户事件
	$("body").on("click","#saveAccount", function()
	{
		var id = loanid;
		var loanNo = loanno;
		var accountSupervisionId = $("#accountNo").val();
		var accountNo =	$("#accountNo option:selected").text();
		$.ajax({
			type: 'POST',
			url: basepath + "/loanInfo/manage/saveAccountNoToDB",
			data: {id:id,loanNo:loanNo,accountSupervisionId:accountSupervisionId,accountNo:accountNo},
			success: function(data)
			{
				if("设置成功" == data)
				{
					$(".back-link").click();
					searchLoanInfo();
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
    
	//历史还款明细点击事件
	$("body").on("click",".repayDetail-link", function()
	{
		var loanId = $(this).data("loanid");
		$.ajax({
			type: 'post',
			url: basepath + "/loanInfo/manage/getRepaymentDetailsHistory",
			data: {loanId:loanId},
			success: function(data)
			{
				$("#repayWaitHtml").addClass("hidden");
				if(null == data.data)
				{
					$("#noRepaymentDetails").removeClass("hidden");
				}
				else
				{
					$("#haveRepaymentDetails").removeClass("hidden");
					var json = data.data;
					$(json).each(function()
					{
						var text = "<tr>";
						text += "<td>"+this.operatingTime+"</td>";
						text += "<td>"+this.repaymentMethod+"</td>";
						text += "<td>"+this.amountDetails+"</td>";
						text += "<td>"+this.offlineTime+"</td>";
						text += "</tr>";
						$(text).appendTo("#repaymentDetailsHistory");
					});
				}
			},
			error: function(data)
			{
				showAlertWin("网络异常："+data.responseText);
			}
		});
	});
    //历史明细弹窗
    new PopWindow(".repayDetail-link", {
        title: "历史还款明细",
        width: 800,
        reload: true,
        template: "#template-repayDetail"
    }).init();
});