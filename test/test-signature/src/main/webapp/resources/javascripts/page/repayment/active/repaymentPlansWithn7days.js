var userType = "";
$(document).ready(function(){
	userType = $(".main-container").data("usertype");
	bindSwitchList();
	generateRepaymentPlan();
	generateRepaymentExpense();
	
	data = {};
    $("body").on("click", ".activeRepayPlan-link", function() {
    	var url = basepath + "/repayment/active/showActiveRepaymentPage?loanInfoId=" + $(this).parent().next().text(),
    	data = {
    			activeRepaymentType:"single_plan"
    	};
    	StandardPost(url, data);
    });
    
    $("body").on("click", ".activeRepayExpense-link", function() {
    	var url = basepath + "/repayment/active/showActiveRepaymentPage?loanInfoId=" + $(this).parent().next().text(),
		data = {
				activeRepaymentType:"single_expense"
		};
		StandardPost(url, data);
	});
});
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
            pageSize: 10,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                    data: {},
                    type : "POST",
                    url: basepath + "/repayment/active/getRepaymentPlansWithn7Days"
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
            }
        },
        columns: [{
            field: "loanNo",
            title: "放款编号",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "factorCompany",
            title: "资金方",
            width: 120,
            attributes: {
                style: "text-align: left"
            },
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
            field: "repaymentPeriod",
            title: "期数",
            width: 50,
            attributes: {
                style: "text-align: center"
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
            field: "accountPrincipal",
            title: "应还本金",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountInterest",
            title: "应还收益",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountServiceCharge",
            title: "应还服务费",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "accountTotalAmount",
            title: "应还总金额",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            title: "主动还款",
            sortable: false,
            width:100,
            template: function(data)
            {
            	var link = "";
            	if(activeRepaymentAuth){
            		if(data.repaied)
            			link += "<a class='comRow-link incRole-link activeRepayPlan-link' href='javascript:void(0);'>还款</a>";
            	}
            	return link;
            }
        },{
            field: "loanInfoId",
            title: "主键",
            width: 120,
            hidden: true
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
    //通过还款信息
    $("#manualGrid-cost").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
            pageSize: 10,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                    data: {},
                    type : "POST",
                    url: basepath + "/repayment/active/getRepaymentExpenseWithn7Days"
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
        }},
        columns: [{
            field: "loanNo",
            title: "放款编号",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "loanInfoId",
            title: "平台流水号",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "expenseSubject",
            title: "费用名目",
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
            field: "repaymentAmount",
            title: "应还费用",
            width: 120,
            attributes: {
                style: "text-align: right"
            }
        },{
            title: "主动还款",
            sortable: false,
            width:100,
            template: function(data)
            {
            	var link = "";
            	if(activeRepaymentAuth){
            		if(data.repaied){
            			link += "<a class='comRow-link incRole-link activeRepayExpense-link' href='javascript:void(0);'>还款</a>";
            		}
            	}
            	return link;
            }
        },{
            field: "loanInfoId",
            title: "主键",
            width: 120,
            hidden: true
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
