var PAGESIZE = 5;

$(function(){

	if ($($("#tradeRecordAndLoan li a")[0]).text() == "放款信息") {
		searchLoan();
	} else {
		searchTradeRecord();
	}
	
	// <li> 切换(收支明细与放款信息)
	$("#tradeRecordAndLoan li").click(function(){
		$(this).siblings("li").removeClass("bankAcuNav-active");
		$(this).addClass("bankAcuNav-active");
	});

});

/**
 * 查询 账户收支明细
 */
function searchTradeRecord(){
	$("#accountDetailGrid").removeClass("hidden");
	$("#loanMatterGrid").addClass("hidden");
	
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
						  	accountId: $("#accountId").val()
					},
					url: basepath + "/account/trade/tradeRecord"
				}
			},
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			schema: {
				data: "data",
				total: function(data){
					var total = data.total;
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
            field: "businessSeq",
            title: "业务流水号",
            width: 170
        },{
			field: "tradeTypeDesc",
			title: "交易类型",
			width: 70
		},{
            field: "operateType",
            title: "操作方式",
            width: 70
        },{
            field: "oppositeAccountName",
            title: "交易户名",
            width: 300
        },{
            field: "oppositeAccountNo",
            title: "交易账号",
            width: 170,
			template: function(data){
				if (data.oppositeAccountNo)
					return data.oppositeAccountNo.formatBankAccountNo();
				else
					return '';
			},
            attributes:{
                style: "text-align:center"
            }
        },{
			field: "amount",
			title: "交易金额",
			width: 180,
			attributes:{
                style: "text-align:right"
			},
			template: function(data){
				return data.amount.formatMoney();
			}
		},{
            field: "tradeEndTime",
            title: "交易时间",
            width: 150,
            template: function(data){
            	if (data.tradeEndTime != null) {
            		return new Date(data.tradeEndTime).format("yyyy-MM-dd hh:mm:ss");
            	} else {
            		return '';
            	}
			},
            attributes:{
                style: "text-align:center"
            }
        },{
			field: "statusDesc",
			title: "交易状态",
			width: 70
		},{
            field: "remark",
            title: "备注",
            width: 100
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

/**
 * 查询 放款信息
 */
function searchLoan(){
	$("#loanMatterGrid").removeClass("hidden");
	$("#accountDetailGrid").addClass("hidden");
	
	$("#loanMatterGrid").remove();
	$(".accountDetail-wrap").append("<div id=\"loanMatterGrid\" class=\"accountDetail\"></div>");
	var grid = $("#loanMatterGrid").kendoGrid({
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
						$("#loanMatterGrid .k-grid-header-wrap").css("overflow-x","auto");
					}else{
						$("#loanMatterGrid .k-grid-header-wrap").css("overflow-x","");
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
			attributes:{
                style: "text-align:right"
			},
			template: function(data){
				return data.financeAmount.formatMoney();
			}
        },{
			field: "financeBalance",
			title: "融资余额",
			width: 100,
			attributes:{
                style: "text-align:right"
			},
			template: function(data){
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