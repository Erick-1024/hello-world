var PAGESIZE_LIST = 10;
var PAGESIZE_DETAIL = 5;
$(function(){
	initRecentTimeButton();
	
	searchLoanDetails();
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('.form-search-link').click();
    	}
    });
	
	//点击"查询"
	$(".form-search-link").click(function(){
		searchLoanDetails();
    });
	// 详情弹窗
	$("body").on("click", "#monitorLimit-grid .detail-link", function(e) {
		$(".window-overlay").removeClass("hidden");
		$(".template-manualAdd").removeClass("hidden");
		var summaryId = $(this).attr("data-summaryId");
		searchLoanDetailsDetail(summaryId);
	});
	
	// 关闭弹窗
	$(".closeHref").click(function() {
		$(".window-overlay").addClass("hidden");
		$(".template-manualAdd").addClass("hidden");
	});
});

//初始化查询条件“交易日期-最近”按钮
function initRecentTimeButton(){
	$('.status-normal').click(function(){
		$(this).addClass('status-chk').removeClass('status-default');
		$(this).siblings("a").addClass('status-default').removeClass('status-chk');
		var value = $(this).attr("value");
		var startDay = new Date();
		if(value == "oneWeek")
			subtractDays(startDay, 6);
		if(value == "oneMonth"){
			startDay = subtractMonths(startDay, 1);
			addDays(startDay, 1);
		}
		if(value == "threeMonths"){
			startDay = subtractMonths(startDay, 3);
			addDays(startDay, 1);
		}
		$("#tradeStartDate").val(startDay.format("yyyy-MM-dd"));
		$("#tradeEndDate").val(new Date().format("yyyy-MM-dd"));
	});
};


//查询放款明细列表
function searchLoanDetails(){
	$("#monitorLimit-grid").empty();
	$("#monitorLimit-grid").kendoGrid({
		selectable : "row", // 设置可选择数据行
		sortable : true, // 列排序
		dataSource : {
			pageSize : PAGESIZE_LIST,
			type : "json", // 后台返回的数据类型
			method : "post",
			transport : {
				read : {
					type: "post",
					data: {
							summaryId: $.trim($("#summaryId").val()),
							customerName: $.trim($("#companyName").val()),
							tradeStartDate: $.trim($("#tradeStartDate").val()),
							tradeEndDate: $.trim($("#tradeEndDate").val())
					},
					url: basepath + "/credit/trade/loan/details/searchList"
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
						$("#monitorLimit-grid .k-grid-header-wrap").css("overflow-x","auto");
					}else{
						$("#monitorLimit-grid .k-grid-header-wrap").css("overflow-x","");
					}
					return total;
				}
			}
		},
		columns : [{
				field : "loanInfoId",
				title : "CANA平台流水号",
				width : 80,
				attributes : {
					style : "text-align: center"
				}
			},{
				field : "financeCompany",
				title : "客户名称",
				width : 120,
				attributes : {
					style : "text-align: center"
				}
			},{
				field : "loanDate",
				title : "交易日期",
				width : 120,
				attributes : {
					style : "text-align: center"
				}
			},{
				field : "dueDate",
				title : "到期日",
				width : 120,
				attributes : {
					style : "text-align: center"
				}
			},{
				field : "extensionDays",
				title : "展期天数",
				width : 80,
				attributes : {
					style : "text-align: center"
				},
				template: function(data){
					if(data.extensionDays==null)
						return "-";
					return data.extensionDays;
				}
			},{
				field : "interestRate",
				title : "费率（日）",
				width : 120,
				attributes : {
					style : "text-align: center"
				},
				template: function(data){
					return (parseFloat(data.interestRate)*100).toFixed(3)+"%";
				}
			},{
				field : "financeAmount",
				title : "金额",
				width : 100,
				attributes : {
					style: "text-align: center"
				},
				template: function(data){
					return String(data.financeAmount/100).formatMoney();
				}
			},{
				title : "操作",
				sortable : false,
				width : 80,
				template: function(data){
	            	var url = "";
	            	if(detailAuth)
	            		url += "<a class='comRow-link detail-link' href='javascript:void(0);' data-summaryId="+data.loanInfoId+">详情</a>";
	            	return url;
	            },
				editable : "popup"
			}],
			pageable: {
						pageSizes : false, // 设置每页显示行数
						buttonCount : 5, // 显示页数
						messages : {
							display : "共<span class='sumData'>{2}</span>条数据",
							empty : "没有数据",
							page : "页",
							of : "/ {0}",
							itemsPerPage : "条/页",
							first : "第一页",
							previous : "前一页",
							next : "下一页",
							last : "最后一页"
						}
			}
	});
}

//查询放款明细详情
function searchLoanDetailsDetail(summaryId){
	$("#fundDetail-grid").empty();
	$("#fundDetail-grid").kendoGrid({
		selectable : "row", // 设置可选择数据行
		sortable : true, // 列排序
		dataSource : {
			pageSize : PAGESIZE_DETAIL,
			type : "json", // 后台返回的数据类型
			method : "post",
			transport : {
				read : {
					type: "post",
					data: {
						summaryId: summaryId,
						tradeType: "PAYMENT"
				},
				url: basepath + "/credit/trade/loan/details/searchDetail"
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
						$("#fundDetail-grid .k-grid-header-wrap").css("overflow-x","auto");
					}else{
						$("#fundDetail-grid .k-grid-header-wrap").css("overflow-x","");
					}
					return total;
				}
			}
		},
		columns : [{
			field : "outTradeNo",
			title : "真旅订单编号",
			width : 80,
			attributes : {
				style : "text-align: center"
			}
		},{
			field : "orderInfo",
			title : "订单信息",
			width : 120,
			attributes : {
				style : "text-align: center"
			}
		},{
			field : "tradeStartTime",
			title : "交易时间",
			width : 120,
			attributes : {
				style : "text-align: center"
			},
			template: function(data){
				return new Date(data.tradeStartTime).format("hh:mm");
			}
		},{
			field : "fee",
			title : "订单金额",
			width : 120,
			attributes : {
				style : "text-align: center"
			},template: function(data){
				return data.fee.formatMoney();
			}
		}],
		pageable : {
			pageSizes : false, // 设置每页显示行数
			buttonCount : 5, // 显示页数
			messages : {
				display : "共<span class='sumData'>{2}</span>条数据",
				empty : "没有数据",
				page : "页",
				of : "/ {0}",
				itemsPerPage : "条/页",
				first : "第一页",
				previous : "前一页",
				next : "下一页",
				last : "最后一页"
			}
		}
	});
	
}