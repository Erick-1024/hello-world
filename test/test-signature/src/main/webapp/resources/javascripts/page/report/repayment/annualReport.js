var sequence = 0;
var pageSize = 10;
$(function(){
	
	queryAnnualReport();
	
	//点击查询按钮
    $("body").on("click", "#queryAnnualReport", function(e){
    	queryAnnualReport();
    });
    
    bindEvent();
});
 
 function queryAnnualReport(){
	$("#reprots-sumGrid").remove();
	$("#annualReportGridWrap").append("<div id=\"reprots-sumGrid\" class=\"reprots-grid\"></div>");
	sequence = 0;
  	//查询年报表
    $("#reprots-sumGrid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
            pageSize: pageSize,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
            	 read: {
                     data: { startTime:$.trim($("#startTime").val()),
                     		endTime:$.trim($("#startTime").val())},
                     type : "POST",
                     url: basepath +"/report/repayment/queryRepaymentAnnualReport"
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
	  					$("#annualReportGridWrap .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#annualReportGridWrap .k-grid-header-wrap").css("overflow-x","");
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
            title: "年份",
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
            title: "当年放款金额",
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
            field: "accountCharge",
            title: "应还费用",
            width: 120,
            template: function(data){
            	return data.accountCharge.formatMoney();	
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
            field: "accountOverdue",
            title: "应还逾期金额",
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
            field: "paidCharge",
            title: "已还费用",
            width: 120,
            template: function(data){
            	return data.paidCharge.formatMoney();	
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
            field: "paidOverdue",
            title: "已还逾期金额",
            width: 150,
            template: function(data){
            	return data.paidOverdue.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
        	field: "totalOverdue",
            title: "累计逾期本金",
            width: 150,
            template: function(data){
            	return data.totalOverdue.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
        	field: "totalExtension",
            title: "累计展期本金",
            width: 150,
            template: function(data){
            	return data.totalExtension.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "adjustPrincipal",
            title: "当年调账本金",
            width: 150,
            template: function(data){
            	return data.adjustPrincipal.formatMoney();	
            },
            attributes: {
                style: "text-align: right"
            }
        },{
            field: "adjustAmount",
            title: "当年调账费用",
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
 
function exportAnnualReport() {
	var startTime = $.trim($("#startTime").val());
	var endTime = $.trim($("#startTime").val());
	window.open(basepath + "/report/repayment/exportRepaymentAnnualReport?startTime=" + startTime + "&endTime=" + endTime);
		
}
 
function bindEvent() {
	$("body").on("click", ".export-btn", exportAnnualReport);
}