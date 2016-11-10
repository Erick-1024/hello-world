$(function(){
	
	$('#search').click(function() {
		var grid = $("#monitorList-grid").data("kendoGrid");
		grid.setDataSource(getDataSource());
	});
	
	$('#exportButton').click(function() {
		document.getElementById("exportForm").submit();
	});
	
	bindEntry();
	
	initList();
})

function bindEntry() {
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('#search').click();
    	}
    });
}

function getDataSource(){
	return new kendo.data.DataSource({
		pageSize : 10,
		type : "json", // 后台返回的数据类型
		method : "post",
		transport : {
			read : {
				type : "post",
				url: basepath + "/homsom/ticket/get/list/" + channel,
				data: {
					agentName: $.trim($('#agentName').val()),
					issueDateStart: $.trim($('#issueDateStart').val()),
					issueDateEnd: $.trim($('#issueDateEnd').val())
				}
			}
		},
		serverPaging: true,
		serverSorting: true,
		serverFiltering: true,
		schema : {
			data: "data",
			total: function(data){
				var total = data.totalNum;
				if(total == 0){
					$("#monitorSrl-grid .k-grid-header-wrap").css("overflow-x","auto");
				}else{
					$("#monitorSrl-grid .k-grid-header-wrap").css("overflow-x","");
				}
				return total;
			}
		}
    });
}

function initList() {
	//初始化表格
	var grid = $("#monitorList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        columns: [{
            field: "ticketNo",
            title: "客票编号",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "agentName",
            title: "代理商名称",
            width: 220,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "orderId",
            title: "订单编号",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "amount",
            title: "购票金额",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	return fmoney(data.amount/100);
            }
        },{
            field: "issueDate",
            title: "出票日期",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "airline",
            title: "航班号",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "itinerary",
            title: "航程",
            width: 220,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "departureDateTime",
            title: "起飞时间",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "passengerName",
            title: "乘客姓名",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "stateDesc",
            title: "状态",
            width: 70,
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
    }).data("kendoGrid");
	grid.setDataSource(getDataSource());
}