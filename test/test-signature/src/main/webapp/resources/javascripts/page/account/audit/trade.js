var PAGESIZE = 5;
$(function(){
	
	getAccountBalance();
	
	searchLoan();
	
	//点击"刷新" --账户余额
	$("body").on("click", ".accountBalanceButton", function(e){
		getAccountBalance();
	});
});

/**
 * 刷新--获取一个账户余额
 */
var isAccountBalanceRequesting = false;
function getAccountBalance(){
	if(isAccountBalanceRequesting)
		return;
	isAccountBalanceRequesting = true;
	$(".accountBalance").text("获取中");
	cana.post(basepath + "/account/trade/updateFundInfo",
			{
				accountNo: $("#sendAccountNo").val().parseBankAccountNo(),
				finaceBalance: $(".finaceBalance").text().parseMoney()
			},
			function(data){
				var accountBalance = parseFloat(data.accountBalance).toFixed(2);
				var fundCoverage = parseFloat(data.fundCoverRatio).toFixed(2);
				if(!isNaN(accountBalance))
					$(".accountBalance").text((accountBalance+"").formatMoney());
		        if(!isNaN(fundCoverage))
		        	$(".fundCoverage").text(fundCoverage);
			},function(data){
				$(".accountBalance").text("获取失败");
			},function(){
				isAccountBalanceRequesting = false;
			});
}

/**
 * 查询 放款信息
 */
function searchLoan(){
	$("#accountDetailGrid").remove();
	$(".accountDetail-wrap").append("<div id=\"accountDetailGrid\" class=\"accountDetail\"></div>");
	var grid = $("#accountDetailGrid").kendoGrid({
		selectable: "row",  //设置可选择数据行
		sortable: true,  //列排序
		dataSource:{
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						accountSupervisionId: $("#accountSupervisionId").val()
					},
					url: basepath + "/account/trade/loan"
				}
			},
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			schema: {
				data: "data",
				total: function(data){
					var total = data.totalNum;
					if(total == 0){
						$("#accountDetailGrid .k-grid-header-wrap").css("overflow-x","auto");
					}else{
						$("#accountDetailGrid .k-grid-header-wrap").css("overflow-x","");
					}
					return total;
				}
			}
		},
		sortable: false,
		groupable: false,
		columns: [{
			field: "loanNo",
			title: "放款编号",
			width: 180
		},{
            field: "businessContractNo",
            title: "业务合同号",
            width: 180
        },{
			field: "financeCompany",
			title: "融资客户名称",
			width: 300
		},{
            field: "voucherNo",
            title: "凭证号码",
            width: 180
        },{
            field: "currency",
            title: "币别",
            width: 50,
            template:function(data){
            	return data.currency == "RMB"?"人民币":data.currency;
            }
        },{
			field: "businessProduct",
			title: "产品类型",
			width: 180
		},{
            field: "financeAmount",
            title: "融资金额",
            width: 100,
            template : function(data) {
            	return data.financeAmount.formatMoney();
            }
        },{
			field: "financeBalance",
			title: "融资余额",
			width: 100,
            template : function(data) {
            	return data.financeBalance.formatMoney();
            }
		}],
		pageable: {
			pageSizes: false,  //设置每页显示行数
			pageSize: PAGESIZE,
			buttonCount: 5,  //显示页数
			page: 1,
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
