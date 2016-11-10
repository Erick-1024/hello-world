$(function(){
	searchStatement();
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('#searchBtn').click();
    	}
    });
	
	//点击"查询"
	$("#searchBtn").click(function(){
		searchStatement();
    });
	
	//点击“导出”
	$("body").on("click", "#exportBtn", exportStatement);
})

function exportStatement() {
	var tradeStartDate = $.trim($('.startDate').val());
	var tradeEndDate = $.trim($('.endDate').val());
	var statementSpecificField = true;
	window.open(basepath + "/credit/exportStatement?tradeStartDate=" + tradeStartDate + "&tradeEndDate=" + tradeEndDate + "&statementSpecificField=" + statementSpecificField);
}

function searchStatement(){
	 //初始化数据表格
	$("#monitorTrade-grid").empty();
    $("#monitorTrade-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
            pageSize: 10,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                	data:{
                		tradeStartDate:$.trim($('.startDate').val()),
                		tradeEndDate:$.trim($('.endDate').val()),
                		statementSpecificField : true
                	},
                	type:"post",
                	url:basepath+"/credit/searchStatement"
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
						$("#monitorTrade-grid .k-grid-header-wrap").css("overflow-x","auto");
					}else{
						$("#monitorTrade-grid .k-grid-header-wrap").css("overflow-x","");
					}
					return total;
				}
			}
        },
        columns: [{
            field: "outTradeNo",
            title: "真旅订单编号",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "outCustomerName",
            title: "真旅客户名称",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "businessSeq",
            title: "账户业务流水号",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "tradeType",
            title: "交易类型",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "tradeStatus",
            title: "交易状态",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "transferStatus",
            title: "转账状态",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "fee",
            title: "金额",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	if(data.tradeType=="退款" || data.tradeType=="账户还款")
            		return "-"+data.fee;
            	return data.fee;
			}
        },{
            field: "tradeStartTime",
            title: "交易时间",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
				return new Date(data.tradeStartTime).format("yyyy-MM-dd hh:mm");
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