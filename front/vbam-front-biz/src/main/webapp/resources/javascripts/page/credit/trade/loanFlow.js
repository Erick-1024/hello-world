$(function(){
	initTradeStatusBtn();
	initSearchBtn();
	showList();
	
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
                		summaryId:$.trim($('.summaryId').val()),
                		customerName:$.trim($('.customerName').val()),
                		tradeStartDate:$.trim($('.startDate').val()),
                		tradeEndDate:$.trim($('.endDate').val()),
                		tradeStatus:$('.status-chk.trade-status').attr('value')
                	},
                	type:"post",
                	url:basepath+"/credit/transfer/payment"
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
            field: "summaryId",
            title: "CANA平台流水号",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "businessSeq",
            title: "业务流水号",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "financeCustomerName",
            title: "客户名称",
            width: 120,
            attributes: {
                style: "text-align: center"
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
            }
        },{
            field: "fee",
            title: "金额",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            title: "操作",
            sortable: false,
            width: 80,
            attributes:{
                style: "text-align: center"
            },
            template:function(item){
            	var status=item.transferStatus;
            	var res='';
            	if(status=='失败' && userRoot){
            		res= "<a class='manual-chk limitAct-link' href='javascript:void(0);' transferid='"+item.id+"' account='"+item.fromAccountNo+"'>人工操作</a>";
            	}
            	return res;
            },
//            command: [{
//                name: "detail",
//                text: "人工操作",
//                template: function(item){
//                    return "<a class='manual-chk limitAct-link' href='javascript:void(0);'>"+ item.text +"</a>";
//                }
//            }],
            editable: "popup"
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
