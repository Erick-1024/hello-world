$(function(){
	showList();
	initTradeStatusBtn();
})

function showList(){
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
                		businessSeq:$.trim($('#businessSeq').val()),
                		customerName:$.trim($('#customerName').val()),
                		startDate:$.trim($('#startDate').val()),
                		endDate:$.trim($('#endDate').val()),
                		tradeStatus:$('.status-chk.trade-status').attr('value')
                	},
                	type:"post",
                	url:basepath+"/yundaex/loan/loanFlow"
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
            field: "businessSeq",
            title: "业务流水号",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "financeCompany",
            title: "客户名称",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "createTime",
            title: "交易时间",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "transferStartTime",
            title: "支付时间",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
				return new Date(data.transferStartTime).format("yyyy-MM-dd hh:mm");
			}
        },{
            field: "transferStatus",
            title: "支付状态",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	if (data.transferStatus == "SUCCESS") {
            		return "成功";
				} else if(data.transferStatus == "HANDING") {
					return "交易中";
				} else {
					return "失败";
				}
			}
        },{
            field: "loanAmt",
            title: "金额",
            width: 100,
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

/**
 * 初始化状态
 */
function initTradeStatusBtn(){
    $('.status-normal').click(function(){
		$(this).addClass('status-chk').removeClass('status-default');
		$(this).siblings('a').addClass('status-default').removeClass('status-chk');
		showList();
	});
};
