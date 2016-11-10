/*----------------- 初始化JS --------------*/
$(document).ready(function()
{ 
	generateRepaymentPlan();//生成还款计划列表
	generateExpenseList();//生成费用列表
	
	activePay();
	
	//还款计划列表详情按钮点击事件
	$("body").on("click",".repayDetail-link", function()
	{
		 var repaymentPlanId = $(this).data("id");
		$.ajax({
			type: 'post',
			url: basepath + "/loanInfo/manage/getRepaymentDetails",
			data: {repaymentPlanId:repaymentPlanId},
			success: function(data)
			{
				if("" != data.data)
				{
					if(true == data.data.isOverdue)
					{
						$(".overdue").removeClass("hidden");
					}
//					alert(data.data.id);repaymentPeriod
//					$("#detail-").text(data.data.)
					$("#detail-loanNo").text(data.data.loanNo);
					$("#detail-repaymentPeriod").text(data.data.repaymentPeriod);
					$("#detail-financeBalance").text(data.data.financeBalance.formatMoney());
					$("#detail-valueDate").text(data.data.valueDate);
					$("#detail-settleInterestDate").text(data.data.settleInterestDate);
					$("#detail-repaymentDate").text(data.data.repaymentDate);
					$("#detail-accountPrincipal").text(data.data.accountPrincipal.formatMoney());
					$("#detail-accountInterest").text(data.data.accountInterest.formatMoney());
					$("#detail-accountServiceCharge").text(data.data.accountServiceCharge.formatMoney());
					$("#detail-accountExtensionCharge").text(data.data.accountExtensionCharge.formatMoney());
					$("#detail-paidNormalPrincipal").text(data.data.paidNormalPrincipal.formatMoney());
					$("#detail-paidNormalInterest").text(data.data.paidNormalInterest.formatMoney());
					$("#detail-paidNormalServiceCharge").text(data.data.paidNormalServiceCharge.formatMoney());
					$("#detail-paidExtensionCharge").text(data.data.paidExtensionCharge.formatMoney());
					$("#detail-paidEarlyRepaymentCharge").text(data.data.paidEarlyRepaymentCharge.formatMoney());
					$("#detail-overduePrincipal").text(data.data.overduePrincipal.formatMoney());
					$("#detail-overdueInterest").text(data.data.overdueInterest.formatMoney());
					$("#detail-overdueServiceCharge").text(data.data.overdueServiceCharge.formatMoney());
					$("#detail-overdueManageCharge").text(data.data.overdueManageCharge.formatMoney());
					$("#detail-accountExtensionCharge2").text(data.data.accountExtensionCharge.formatMoney());
					$("#detail-paidOverduePrincipal").text(data.data.paidOverduePrincipal.formatMoney());
					$("#detail-paidOverdueInterest").text(data.data.paidOverdueInterest.formatMoney());
					$("#detail-paidOverdueServiceCharge").text(data.data.paidOverdueServiceCharge.formatMoney());
					$("#detail-paidOverdueManageCharge").text(data.data.paidOverdueManageCharge.formatMoney());
					$("#detail-paidExtensionCharge2").text(data.data.paidExtensionCharge.formatMoney());
				}
			},
			error: function(data)
			{
				showAlertWin(data.responseText);
			}
		});
	});
	/*----------------- 默认JS --------------*/
    $(".repayment-navlink").click(function(event){
        var obj = $(event.target).parent("li");
        if(!obj.hasClass("repayment-nav-active")){
            obj.addClass("repayment-nav-active").siblings().removeClass("repayment-nav-active");
        }

        if(obj.is(".repayment-nav ul li:first")){
            $("#repayment-plan").removeClass("hidden");
            $("#repayment-cost").addClass("hidden");
        }else if(obj.is(".repayment-nav ul li:last")){
            $("#repayment-plan").addClass("hidden");
            $("#repayment-cost").removeClass("hidden");
        }
    });

    //查看还款详情弹窗
    new PopWindow(".repayDetail-link", {
        title: "还款详情",
        width: 860,
        reload: true,
        template: "#template-Detail"
    }).init();

    $("body").on("click", ".dlg-wrapper-foot .close-window", function(e){
        var win = $(e.target).closest(".k-window");
        var overlay = win.prev(".k-overlay");
        if(win.is(":visible")){
            win.css("display", "none");
            overlay.css("display", "none");
        }
    });
	//历史还款明细点击事件
	$("body").on("click",".repayOp-link", function()
	{
//		var loanId = $(this).data("loanid");
		var loanId = $("#loanId").text()
		$.ajax({
			type: 'post',
			url: basepath + "/loanInfo/manage/getRepaymentDetailsHistory",
			data: {loanId:loanId},
			success: function(data)
			{
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
				showAlertWin(data.responseText);
			}
		});
	});
    //历史明细弹窗
    new PopWindow(".repayOp-link", {
        title: "历史还款明细",
        width: 800,
        reload: true,
        template: "#template-repayDetail"
    }).init();
});

/*----------------- 公共变量 --------------*/
//是否可以主动还款
var isRepay = true;

var pageSize = 10;
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
/*----------------- 生成还款计划列表 --------------*/
function generateRepaymentPlan()
{
	$("#manualGrid-plan").remove();
	$("#repayment-plan").append("<div class=\"finance-grid\" id=\"manualGrid-plan\" style=\"margin-top:15px;\"></div>");
	//通过还款信息
    $("#manualGrid-plan").kendoGrid({
        selectable: "row",  //设置可选择数据行
        dataSource:{
            type: "json", //后台返回的数据类型
            pageSize: pageSize,//必须设置，否则无法向后台传回page 和 pageSize
            transport: {
                read: {
	                    data: { loanId: $("#loanId").text() },
	                    type : "POST",
	                    url: basepath + "/loanInfo/manage/getRepaymentPlanFromDB"	
                	  }
            		},
            serverPaging: true,
    		serverSorting: true,
    		serverFiltering: true,
            //解析远程响应的数据
            schema:{
            	data: "data",
      			total: function(data) {
					var total = data.totalNum;
	  				if(total == 0){
	  					$("#repayment-plan .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#repayment-plan .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
            }
        },
     
        columns: [{
            field: "loanNo",
            title: "放款编号",
            width: 150
        },{
            field: "repaymentPeriod",
            title: "期数",
            width: 50,
            attributes: {
                style: "text-align: center"
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
            field: "valueDate",
            title: "收益计算日",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "settleInterestDate",
            title: "收益分配日",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "repaymentDate",
            title: "还款日",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "accountPrincipal",
            title: "应还本金",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.accountPrincipal + "";
            	return money.formatMoney();	
            }
        },{
            field: "accountInterest",
            title: "应还收益",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.accountInterest + "";
            	return money.formatMoney();	
            }
        },{
            field: "accountServiceCharge",
            title: "应还服务费",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.accountServiceCharge + "";
            	return money.formatMoney();	
            }
        },{
            field: "overduePrincipal",
            title: "逾期本金",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.overduePrincipal + "";
            	return money.formatMoney();	
            }
        },{
            field: "overdueInterest",
            title: "逾期收益",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.overdueInterest + "";
            	return money.formatMoney();	
            }
        },{
            field: "overdueServiceCharge",
            title: "逾期服务费",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.overdueServiceCharge + "";
            	return money.formatMoney();	
            }
        },{
            field: "accountExtensionCharge",
            title: "展期费用",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.accountExtensionCharge + "";
            	return money.formatMoney();	
            }
        },{
            field: "overdueManageCharge",
            title: "逾期管理费",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.overdueManageCharge + "";
            	return money.formatMoney();	
            }
        },{
            field: "accountTotalAmount",
            title: "应还总金额",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.accountTotalAmount + "";
            	return money.formatMoney();	
            }
        },{
            field: "settleStatus",
            title: "结清状态",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            title: "操作",
            sortable: false,
            width:100,
            template: function(data)
            {
            	var link = "";
            	if(repaymentDetailAuth)
            		link += "<a class='comRow-link repayDetail-link' href='javascript:void(0);' data-id=" + data.id + ">详情</a>";
            	if(activeRepaymentAuth && data.settleStatus=="未结清" && isRepay){
           		isRepay = false;
            		link += "<a class='comRow-link activeRepayPlan-link' href='javascript:void(0);'>还款</a>";
            						}
            	return link;
            }
        }],
        pageable: pageable
    });
}
/*----------------- 生成费用列表 --------------*/
function generateExpenseList()
{
	$("#manualGrid-cost").remove();
	$("#repayment-cost").append("<div class=\"finance-grid\" id=\"manualGrid-cost\" style=\"margin-top:15px;\"></div>");
	//通过还款信息
    $("#manualGrid-cost").kendoGrid({
        selectable: "row",  //设置可选择数据行
        dataSource:{
            type: "json", //后台返回的数据类型
            pageSize: pageSize,//必须设置，否则无法向后台传回page 和 pageSize
            transport: {
                read: {
	                    data: { loanId: $("#loanId").text() },
	                    type : "POST",
	                    url: basepath + "/loanInfo/manage/getExpenseListFromDB"	
                	  }
            		},
            serverPaging: true,
    		serverSorting: true,
    		serverFiltering: true,
            //解析远程响应的数据
            schema:{
            	data: "data",
      			total: function(data) {
					var total = data.totalNum;
	  				if(total == 0){
	  					$("#repayment-cost .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#repayment-cost .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
            }
        },
        columns: [{
            field: "loanNo",
            title: "放款编号",
            width: 100
        },{
            field: "expenseSubject",
            title: "费用明目",
            width: 120
        },{
            field: "repaymentDate",
            title: "还款日",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "paidNormalAmount",
            title: "已还金额",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.paidNormalAmount + "";
            	return money.formatMoney();	
            }
        },{
            field: "repaymentAmount",
            title: "应还金额",
            width: 120,
            attributes: {
                style: "text-align: right"
            },
            template: function(data)
            {
            	var money = data.repaymentAmount + "";
            	return money.formatMoney();	
            }
        },{
            field: "settleStatus",
            title: "结清状态",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            title: "操作",
            sortable: false,
            width:100,
            template: function(data){
            	var link = "";
            	if(activeRepaymentAuth)
            		link += "<a class='comRow-link activeRepayExpense-link' href='javascript:void(0);'>还款</a>";
            	return link;
            }
        }],
        pageable: pageable
    });
}

function activePay() {
	var url = basepath + "/repayment/active/showActiveRepaymentPage?loanInfoId=" + $("#loanId").text(),
				data = {};
	$("body").on("click", ".activeRepayPlan-link", function() {
		data = {
				activeRepaymentType:"single_plan"
		};
		StandardPost(url, data);
	});
	
	$("body").on("click", ".activeRepayExpense-link", function() {
		data = {
				activeRepaymentType:"single_expense"
		};
		StandardPost(url, data);
	});
	
	$("body").on("click", "#activeRepayAllPlan", function() {
		data = {
				activeRepaymentType:"mutiple_plan"
		};
		StandardPost(url, data);
	});
	
	$("body").on("click", "#activeRepayAllExpense", function() {
		data = {
				activeRepaymentType:"mutiple_expense"
		};
		StandardPost(url, data);
	});
		
}
