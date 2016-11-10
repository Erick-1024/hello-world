var userType = "";
$(document).ready(function()
{ 
	userType = $(".main-container").data("usertype");
	bindSerachButton();
	bindSwitchList();
	generateRepaymentPlanAndExpense();
});
/*----------------- 绑定查询按钮点击事件 和 回车查询事件 --------------*/
function bindSerachButton()
{
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('#search-repayment-plan-btn').click();
    	}
    });
	
    $("#search-repayment-plan-btn").click(function(){
    	generateRepaymentPlanAndExpense();
    });
}
/*----------------- 绑定列表切换事件 --------------*/
function bindSwitchList()
{
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
}
/*----------------- 生成还款计划列表 和费用列表 --------------*/
function generateRepaymentPlanAndExpense()
{
	generateRepaymentPlan();
	generateRepaymentExpense();
}
/*----------------- 生成还款计划列表 --------------*/
function generateRepaymentPlan()
{
	$("#manualGrid-plan").remove();
	$("#repayment-plan").append("<div class=\"finance-grid\" id=\"manualGrid-plan\" style=\"margin-top:15px;\"></div>");
	//通过还款信息
    $("#manualGrid-plan").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                    data: { 
                    	financeCompany: $("input[name=financeCompany]").val(),
                    	businessProduct: $("input[name=businessProduct]").val(),
                    	factorCompany: $("input[name=factorCompany]").val(),
                    	coreCompanyName: $("input[name=coreCompanyName]").val(),
                    	beginRepaymentDate: $("input[name=begainRepaymentDate]").val(),
                    	endRepaymentDate: $("input[name=endRepaymentDate]").val(),
                    	loanNo: $("input[name=loanNo]").val(),
                    	loanId: $("input[name=loanId]").val(),
                    	settleStatus: $("#settleStatus").val()
                    },
                    type : "POST",
                    url: basepath + "/repayment/active/queryRepaymentPlansBySearchCondition"
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
						$("#manualGrid-plan .k-grid-header-wrap").css("overflow-x","auto");
					}else {
						$("#manualGrid-plan .k-grid-header-wrap").css("overflow-x","");
					}
				return total;
			}
        }},
        columns: [{
            field: "loanInfoId",
            title: "平台流水号",
            width: 130,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "businessProduct",
            title: "业务产品",
            width: 100,
            attributes: {
                style: "text-align: left"
            }
        },{
            field: "factorCompany",
            title: "资金方",
            width: 120,
            hidden: userType == "FACTOR"
        },{
            field: "financeCompany",
            title: "融资客户",
            width: 150,
            hidden: userType == "FINACE"
        },{
            field: "coreCompanyName",
            title: "核心企业",
            width: 150,
            hidden: userType == "CORECOMPANY"
        },{
            field: "loanNo",
            title: "放款编号",
            width: 200,
            attributes: {
                style: "text-align: center"
            }
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
            }
        },{
            field: "financeBalance",
            title: "融资余额",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "valueDate",
            title: "收益计算日",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "settleInterestDate",
            title: "收益分配日",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "repaymentDate",
            title: "还款日",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "extensionDays",
            title: "展期天数",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "accountPrincipal",
            title: "应还本金",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountInterestUntilNow",
            title: "今日应还收益",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountInterest",
            title: "到期应还收益",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountServiceCharge",
            title: "到期应还服务费",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountExtensionCharge",
            title: "展期费用",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "overdueManageCharge",
            title: "逾期管理费",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidNormalPrincipal",
            title: "已还本金",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidNormalInterest",
            title: "已还收益",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidNormalServiceCharge",
            title: "已还服务费",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidExtensionCharge",
            title: "已还展期费用",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidOverdueManageCharge",
            title: "已还逾期管理费",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "overduePrincipal",
            title: "逾期本金",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "overdueInterest",
            title: "逾期收益",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "overdueServiceCharge",
            title: "逾期服务费",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "overdueExtensionCharge",
            title: "逾期展期费用",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
        	field: "settleStatus",
            title: "结清状态",
            sortable: false,
            width:180,
            attributes: {
                style: "text-align: center"
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            pageSize: 10,
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
        }
    });
}
/*----------------- 生成费用列表 --------------*/
function generateRepaymentExpense()
{
	$("#manualGrid-cost").remove();
	$("#repayment-cost").append("<div class=\"finance-grid\" id=\"manualGrid-cost\" style=\"margin-top:15px;\"></div>");
	 //通过还款信息
    $("#manualGrid-cost").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                	data: { 
                    	financeCompany: $("input[name=financeCompany]").val(),
                    	businessProduct: $("input[name=businessProduct]").val(),
                    	factorCompany: $("input[name=factorCompany]").val(),
                    	coreCompanyName: $("input[name=coreCompanyName]").val(),
                    	beginRepaymentDate: $("input[name=begainRepaymentDate]").val(),
                    	endRepaymentDate: $("input[name=endRepaymentDate]").val(),
                    	loanNo: $("input[name=loanNo]").val(),
                    	loanId: $("input[name=loanId]").val(),
                    	settleStatus: $("#settleStatus").val()
                    },
                    type : "POST",
                    url: basepath + "/repayment/active/queryRepaymentExpensesBySearchCondition"
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
							$("#manualGrid-cost .k-grid-header-wrap").css("overflow-x","auto");
						}else {
							$("#manualGrid-cost .k-grid-header-wrap").css("overflow-x","");
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
            field: "loanInfoId",
            title: "平台流水号",
            width: 120
        },{
            field: "expenseSubject",
            title: "费用名目",
            width: 120
        },{
            field: "repaymentDate",
            title: "还款日",
            width: 120
        },{
            field: "repaymentAmount",
            title: "应还费用",
            width: 120
        },{
            field: "paidAmount",
            title: "已还费用",
            width: 150
        },{
            field: "settleStatus",
            title: "结清状态",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            pageSize: 10,
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
        }
    });

    // 导出还款计划
	$("body").on("click", "#export-repayment-plan-btn", function() {

		var form = $('<form method="POST" action="' + basepath + "/repayment/active/exportRepaymentPlansBySearchCondition" + '">');
        form.append($('<input type="hidden" name="financeCompany" value="' + $("input[name=financeCompany]").val() + '">'));
        form.append($('<input type="hidden" name="businessProduct" value="' + $("input[name=businessProduct]").val() + '">'));
        form.append($('<input type="hidden" name="factorCompany" value="' + ($("input[name=factorCompany]").val() || '') + '">'));
        form.append($('<input type="hidden" name="coreCompanyName" value="' + $("input[name=coreCompanyName]").val() + '">'));
        form.append($('<input type="hidden" name="beginRepaymentDate" value="' + $("input[name=begainRepaymentDate]").val() + '">'));
        form.append($('<input type="hidden" name="endRepaymentDate" value="' + $("input[name=endRepaymentDate]").val() + '">'));
        form.append($('<input type="hidden" name="loanNo" value="' + $("input[name=loanNo]").val() + '">'));
        form.append($('<input type="hidden" name="settleStatus" value="' + $("#settleStatus").val() + '">'));
        form.appendTo('body').submit().remove();
        
//    	window.location.href = basepath + "/repayment/active/exportRepaymentPlansBySearchCondition?financeCompany=" + $("input[name=financeCompany]").val()
//    		+ "&businessProduct=" + $("input[name=businessProduct]").val()
//    		+ "&factorCompany=" + $("input[name=factorCompany]").val()
//    		+ "&coreCompanyName=" + $("input[name=coreCompanyName]").val()
//    		+ "&beginRepaymentDate=" + $("input[name=begainRepaymentDate]").val()
//    		+ "&endRepaymentDate=" + $("input[name=endRepaymentDate]").val()
//    		+ "&loanNo=" + $("input[name=loanNo]").val()
//    		+ "&settleStatus=" + $("#settleStatus").val();
	});
}