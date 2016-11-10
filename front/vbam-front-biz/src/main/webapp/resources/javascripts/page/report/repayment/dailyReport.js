var sequence = 0;
var pageSize = 10;

$(function(){
  
	queryDailyReport();
	
	//点击查询按钮
	$("body").on("click", "#queryDailyReport", function(e){
		queryDailyReport();
    });
	
	bindEvent();
});
 
 function queryDailyReport(){
	$("#reprots-sumGrid").remove();
	$("#dailyReportGridWrap").append("<div id=\"reprots-sumGrid\" class=\"reprots-grid\"></div>");
	sequence = 0;
	//查询日报表
	$("#reprots-sumGrid").kendoGrid({
		selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
            pageSize: pageSize,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                    data: { 
                    		businessProduct:$.trim($("#businessProduct").val()),
                    		startTime:$.trim($("input[name='startTime']").val()),
                    		endTime:$.trim($("input[name='endTime']").val())},
                    type : "POST",
                    url: basepath +"/report/repayment/queryRepaymentDailyReport"
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
	  					$("#dailyReportGridWrap .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#dailyReportGridWrap .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
			}
        },
        columns: [{
            field: "sequence",
            title: "序号",
            width: 50 ,
            template: function(){
            	sequence++;
            	return sequence;
            },
			attributes: {
                style: "text-align: center"
            }
        },{
            field: "reportDate",
            title: "日期",
            width: 120,
			attributes: {
                style: "text-align: center"
            }
        },{
            field: "businessProduct",
            title: "项目",
            width: 120,
			attributes: {
                style: "text-align: center"
            }
        },{
            field: "financeBalance",
            title: "融资余额",
            width: 120,
            template: function(data){
            	return data.financeBalance.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "financeAmount",
            title: "当日放款金额",
            width: 100,
            template: function(data){
            	return data.financeAmount.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountPrincipal",
            title: "应还本金",
            width: 120,
            template: function(data){
            	return data.accountPrincipal.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountInterest",
            title: "应还利息",
            width: 120,
            template: function(data){
            	return data.accountInterest.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountServiceCharge",
            title: "应还服务费",
            width: 120,
            template: function(data){
            	return data.accountServiceCharge.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountExtensionCharge",
            title: "应还展期费",
            width: 120,
            template: function(data){
            	return data.accountExtensionCharge.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountExpense",
            title: "应还固定费用",
            width: 120,
            template: function(data){
            	return data.accountExpense.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountCharge",
            title: "应还总费用",
            width: 120,
            template: function(data){
            	return data.accountCharge.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountOverduePrincipal",
            title: "应还逾期本金",
            width: 120,
            template: function(data){
            	return data.accountOverduePrincipal.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountOverdueInterest",
            title: "应还逾期利息",
            width: 120,
            template: function(data){
            	return data.accountOverdueInterest.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountOverdueServiceCharge",
            title: "应还逾期服务费",
            width: 120,
            template: function(data){
            	return data.accountOverdueServiceCharge.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountOverdueExtensionCharge",
            title: "应还逾期展期费",
            width: 120,
            template: function(data){
            	return data.accountOverdueExtensionCharge.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountOverduePrincipalPenalty",
            title: "应还逾期本金罚息",
            width: 120,
            template: function(data){
            	return data.accountOverduePrincipalPenalty.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountOverdueInterestPenalty",
            title: "应还逾期利息罚息",
            width: 120,
            template: function(data){
            	return data.accountOverdueInterestPenalty.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountOverdueServiceChargePenalty",
            title: "应还逾期服务费罚息",
            width: 140,
            template: function(data){
            	return data.accountOverdueServiceChargePenalty.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountOtherPenalty",
            title: "应还其他罚息",
            width: 120,
            template: function(data){
            	return data.accountOtherPenalty.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountOverdue",
            title: "应还逾期总金额",
            width: 120,
            template: function(data){
            	return data.accountOverdue.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidPrincipal",
            title: "已还本金",
            width: 120,
            template: function(data){
            	return data.paidPrincipal.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidInterest",
            title: "已还利息",
            width: 120,
            template: function(data){
            	return data.paidInterest.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidServiceCharge",
            title: "已还服务费",
            width: 120,
            template: function(data){
            	return data.paidServiceCharge.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidExtensionCharge",
            title: "已还展期费",
            width: 120,
            template: function(data){
            	return data.paidExtensionCharge.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidExpense",
            title: "已还固定费用",
            width: 120,
            template: function(data){
            	return data.paidExpense.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidEarlyRepaymentCharge",
            title: "已还提前还款手续费",
            width: 140,
            template: function(data){
            	return data.paidEarlyRepaymentCharge.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidCharge",
            title: "已还总费用",
            width: 120,
            template: function(data){
            	return data.paidCharge.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidOverduePrincipal",
            title: "已还逾期本金",
            width: 120,
            template: function(data){
            	return data.paidOverduePrincipal.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidOverdueInterest",
            title: "已还逾期利息",
            width: 120,
            template: function(data){
            	return data.paidOverdueInterest.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidOverdueServiceCharge",
            title: "已还逾期服务费",
            width: 120,
            template: function(data){
            	return data.paidOverdueServiceCharge.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidOverdueExtensionCharge",
            title: "已还逾期展期费",
            width: 120,
            template: function(data){
            	return data.paidOverdueExtensionCharge.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidOverduePrincipalPenalty",
            title: "已还逾期本金罚息",
            width: 120,
            template: function(data){
            	return data.paidOverduePrincipalPenalty.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidOverdueInterestPenalty",
            title: "已还逾期利息罚息",
            width: 120,
            template: function(data){
            	return data.paidOverdueInterestPenalty.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidOverdueServiceChargePenalty",
            title: "已还逾期服务费罚息",
            width: 140,
            template: function(data){
            	return data.paidOverdueServiceChargePenalty.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidOtherPenalty",
            title: "已还其他罚息",
            width: 120,
            template: function(data){
            	return data.paidOtherPenalty.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "paidOverdue",
            title: "已还逾期总金额",
            width: 150,
            template: function(data){
            	return data.paidOverdue.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "adjustPrincipal",
            title: "当日调账本金",
            width: 150,
            template: function(data){
            	return data.adjustPrincipal.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "adjustInterest",
            title: "当日调账利息",
            width: 150,
            template: function(data){
            	return data.adjustInterest.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "adjustServiceCharge",
            title: "当日调账服务费",
            width: 150,
            template: function(data){
            	return data.adjustServiceCharge.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "adjustExtension",
            title: "当日调账展期",
            width: 150,
            template: function(data){
            	return data.adjustExtension.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "adjustOverdueInterest",
            title: "当日调账逾期利息",
            width: 150,
            template: function(data){
            	return data.adjustOverdueInterest.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "adjustOverdueServiceCharge",
            title: "当日调账逾期服务费",
            width: 150,
            template: function(data){
            	return data.adjustOverdueServiceCharge.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "adjustPenalty",
            title: "当日调账逾期管理费",
            width: 150,
            template: function(data){
            	return data.adjustPenalty.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "adjustExpense",
            title: "当日调账固定费用",
            width: 150,
            template: function(data){
            	return data.adjustExpense.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "adjustAmount",
            title: "当日调账总费用",
            width: 150,
            template: function(data){
            	return data.adjustAmount.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
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

function exportDailyReport() {
	var startTime = $.trim($("input[name='startTime']").val());
	var endTime = $.trim($("input[name='endTime']").val());
	var businessProduct = $.trim($("#businessProduct").val());
	window.location.href = basepath + "/report/repayment/exportRepaymentDailyReport?startTime=" + startTime + "&endTime=" + endTime + "&businessProduct=" + businessProduct;
	
}
 
function bindEvent() {
	$("body").on("click", ".export-btn", exportDailyReport);
}