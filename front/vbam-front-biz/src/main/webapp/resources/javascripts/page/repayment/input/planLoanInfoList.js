var pageSize = 10;
function searchLoanInfo() {
	$("#manual-repayGrid").remove();
	$("#manual-repayGridWrap").append("<div class='finance-grid' id='manual-repayGrid' style='margin-top:15px;'></div>");
	$("#manual-repayGrid").kendoGrid({
		selectable: "row",  //设置可选择数据行
		dataSource:{
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						financeCompany: $("input[name=financeCompanyName]").val(),
						coreCompanyName: $("input[name=coreCompanyName]").val(),
						businessContractNo: $("input[name=businessContractNo]").val(),
						businessProduct: $("input[name=businessProduct]").val(),
						loanStartDate: $("input[name=loanStartDate]").val(),
						loanEndDate: $("input[name=loanEndDate]").val(),
						dueStartDate: $("input[name=dueStartDate]").val(),
						dueEndDate: $("input[name=dueEndDate]").val()
					},
					url: basepath + "/repayment/plan/input/queryLoanInfoList"
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
	  					$("#manual-repayGrid .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#manual-repayGrid .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
			}
			},
	        columns: [{
	            title: "操作",
	            width: 80,
	            template: function(data){
	            	var url = "";
	            	if(true){
	            		url += "<a class='comRow-link editManual' href='javascript:void(0);' data-for='"+data.id+"'>录入</a>";
	            	}
	            	return url;
	            				
	            }
	        },{
	            field: "loanNo",
	            title: "放款编号",
	            width: 130
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
	            width: 160
	        },{
	            field: "financeAmount",
	            title: "融资金额",
	            width: 125,
	            attributes: {
	                style: "text-align: right"
	            },
	            template: function(data) {
					return data.financeAmount.formatMoney();
				}
	        },{
	            field: "financeBalance",
	            title: "融资余额",
	            width: 125,
	            attributes: {
	                style: "text-align: right"
	            },
	            template: function(data) {
					return data.financeBalance.formatMoney();
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
	            field: "interestRate",
	            title: "费率",
	            width: 100
	        },{
	            field: "repaymentMethod",
	            title: "还款方式",
	            width: 140
	        }],
	        pageable: {
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
	        }
	    });
}

$(function(){
	searchLoanInfo();
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('.form-search-link').click();
    	}
    });
	
    $(".form-search-link").click(function(){
    	$("#manual-repayGrid").remove();
    	$(".whiteBg").append("<div class=\"finance-grid\" id=\"manual-repayGrid\" style=\"margin-top:15px;\"></div>");
    	searchLoanInfo();
    });
    
    $("body").on("click", ".editManual", function(e){
    	var id = $(this).attr("data-for");
    	$("#planManualInputSelect").attr("action", basepath + "/repayment/plan/input/gotoPlanManualInputSelect?loanInfoId=" + id);
    	$("#planManualInputSelect").submit();
//    	location.href = ;
    });
});
