var PAGESIZE = 10;
$(function(){
	initTradeRecentTimeButton();
	
	initRefundRecentTimeButton();
	
	searchRefundDetails();
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('.form-search-link').click();
    	}
    });
	
	//点击"查询"
	$(".form-search-link").click(function(){
		searchRefundDetails();
    });
})

//初始化查询条件“交易日期-最近”按钮
function initTradeRecentTimeButton(){
	$('.trade-date').click(function(){
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
		$('#tradeStartDate').val(startDay.format("yyyy-MM-dd"));
		$('#tradeEndDate').val(new Date().format("yyyy-MM-dd"));
	});
};

//初始化查询条件“退款日期-最近”按钮
function initRefundRecentTimeButton(){
	$('.refund-date').click(function(){
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
		$('#refundStartDate').val(startDay.format("yyyy-MM-dd"));
		$('#refundEndDate').val(new Date().format("yyyy-MM-dd"));
	});
};

function searchRefundDetails(){
	$("#monitorLimit-grid").empty();
	$("#monitorLimit-grid").kendoGrid({
		selectable : "row", // 设置可选择数据行
		sortable : true, // 列排序
		dataSource : {
			pageSize : PAGESIZE,
			type : "json", // 后台返回的数据类型
			method : "post",
			transport : {
				read : {
					type: "post",
					data: {
							orderNum: $.trim($("#orderNum").val()),
							summaryId: $.trim($("#summaryId").val()),
							customerName: $.trim($("#companyName").val()),
							tradeStartDate: $.trim($("#tradeStartDate").val()),
							tradeEndDate: $.trim($("#tradeEndDate").val()),
							refundStartDate: $.trim($("#refundStartDate").val()),
							refundEndDate: $.trim($("#refundEndDate").val()),
							tradeType: "REFUND"
					},
					url: basepath + "/credit/trade/refund/details/searchList"
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
				field : "outTradeNo",
				title : "订单编号",
				width : 80,
				attributes : {
					style : "text-align: center"
				}
			},{
				field : "outOriginTradeNo",
				title : "原订单号",
				width : 80,
				attributes : {
					style : "text-align: center"
				}
			},{
				field : "summaryId",
				title : "汇总编号",
				width : 80,
				attributes : {
					style : "text-align: center"
				}
			},{
				field : "finaceCustomerName",
				title : "客户名称",
				width : 120,
				attributes : {
					style : "text-align: center"
				}
			},{
				field : "originTradeEndTime",
				title : "交易日期",
				width : 120,
				attributes : {
					style : "text-align: center"
				},
				template: function(data){
					return new Date(data.originTradeEndTime).format("yyyy-MM-dd hh:mm");
				}
			},{
				field : "tradeStartTime",
				title : "退款日期",
				width : 120,
				attributes : {
					style : "text-align: center"
				},
				template: function(data){
					return new Date(data.tradeStartTime).format("yyyy-MM-dd hh:mm");
				}
			},{
				field : "fee",
				title : "退款金额",
				width : 100,
				attributes : {
					style: "text-align: center"
				},template: function(data){
					return data.fee.formatMoney();
				}
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