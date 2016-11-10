var sequence = 0;
var pageSize = 10;
$(function(){
	
	queryCountReport();
	
	//点击查询按钮
    $("body").on("click", "#queryCountReport", function(e){
    	queryCountReport();
    });
    
    bindEvent();
});
function queryCountReport(){
	$("#reprots-countGrid").remove();
	$("#countReportGridWrap").append("<div id=\"reprots-countGrid\" class=\"reprots-grid\"></div>");
	sequence = 0;
	//查询计数报表
	$("#reprots-countGrid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
            pageSize: pageSize,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
            	 read: {
                     data: { startTime:$.trim($("#startTime").val()),
                     		endTime:$.trim($("#endTime").val())},
                     type : "POST",
                     url: basepath +"/report/repayment/queryRepaymentCountReport"
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
            width: 50,
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
            field: "loanItems",
            title: "放款笔数",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "overdueItems",
            title: "逾期笔数",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "extensionItems",
            title: "展期笔数",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "repaymentItems",
            title: "还款笔数",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "adjustItems",
            title: "调账笔数",
            width: 120,
            attributes: {
                style: "text-align: center"
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

function exportCountReport() {
	var startTime = $.trim($("#startTime").val());
	var endTime = $.trim($("#endTime").val());
	window.open(basepath + "/report/repayment/exportRepaymentCountReport?startTime=" + startTime + "&endTime=" + endTime);
		
}
 
function bindEvent() {
	$("body").on("click", ".export-btn", exportCountReport);
}