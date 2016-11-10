$(function() {

	getSettlementList();
	// 点击列表搜索
	$("#seachBtn").click(function() {
		getSettlementList();
	});
	
	//回车事件
	document.onkeydown=keyDownSearch;  
    function keyDownSearch(e) {    
        var theEvent = e || window.event;    
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
        if (code == 13) {    
        	$("#seachBtn").click();
            return false;    
        }    
        return true;    
    } 
});
    
 function getSettlementList(){
 //初始化表格
 $("#monitorList-grid").empty();
	var sequence = 0;
	 var grid = $("#monitorList-grid")
			.kendoGrid(
					{
						selectable : "row", //设置可选择数据行
						sortable : false, //列排序
						dataSource : {
							pageSize : 10,
							type : "json", //后台返回的数据类型
							transport : {
								read : {
									type : "post",
									data : {
										counterpartyName : $.trim($(
												"#counterpartyName").val()),
										ticketNo : $.trim($("#ticketNo").val()),
										startIssueDate : $.trim($(
												"#startIssueDate").val()),
										endIssueDate : $
												.trim($("#endIssueDate").val()),
										startSettleDate : $.trim($(
												"#startSettleDate").val()),
										endSettleDate : $.trim($(
												"#endSettleDate").val()),
										startBuybackDate : $.trim($(
												"#startBuybackDate").val()),
										endBuybackDate : $.trim($(
												"#endBuybackDate").val()),
									},
									url : basepath
											+ "/homsom/settlement/track/list/HOMSOM"
								}
							},
							//解析远程响应的数据
							serverPaging : true,
							serverSorting : true,
							serverFiltering : true,
							schema : {
								data : "data",
								total : function(data) {
									var total = data.totalNum;
									if (total == 0) {
										$(
												"#manual-repayGrid .k-grid-header-wrap")
												.css("overflow-x", "auto");
									} else {
										$(
												"#manual-repayGrid .k-grid-header-wrap")
												.css("overflow-x", "");
									}
									return total;
								}
							}
						},
						columns : [ {
							field : "loanNo",
							title : "放款编号",
							width : 120,
							attributes : {
								style : "text-align: center"
							}
						}, {
							field : "counterpartyName",
							title : "交易对手",
							width : 220,
							attributes : {
								style : "text-align:center"
							}
						}, {
							field : "ticketNo",
							title : "票号",
							width : 100,
							attributes : {
								style : "text-align: center"
							}
						}, {
							field : "loanAmount",
							title : "放款金额",
							width : 100,
							attributes : {
								style : "text-align: center"
							}
						}, {
							field : "loanDays",
							title : "放款期限（天）",
							width : 100,
							attributes : {
								style : "text-align: center"
							}
						}, {
							field : "interestAmount",
							title : "利息",
							width : 100,
							attributes : {
								style : "text-align: center"
							}
						}, {
							field : "settleDate",
							title : "核销日期",
							width : 100,
							attributes : {
								style : "text-align: center"
							}
						}, {
							field : "buybackDate",
							title : "回购日期",
							width : 100,
							attributes : {
								style : "text-align: center"
							}
						}, {
							field : "state",
							title : "状态",
							width : 100,
							attributes : {
								style : "text-align: center"
							}
						} ],
						pageable : {
							pageSizes : false, //设置每页显示行数
							pageSize : 10,
							page : 1,
							buttonCount : 5, //显示页数
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
    	 grid.data("kendoGrid").pager.bind('change', function(){
 			sequence = 0;
 		});
	}