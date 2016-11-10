var sequence = 0;
$(function(){
	initSubmitButton();
	tableSearch();
})

/**
 * 初始化按钮事件
 */
function initSubmitButton() {
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		tableSearch();
    	}
    });
}

function tableSearch() {
	$("#monitorList-grid-test").empty();
	//初始化表格
	var grid = $("#monitorList-grid-test").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                    data: { 
                    	rediskey:$("#key").val()
                    },
                    type : "POST",
                    url:  basepath + "/asset/invoice/queryPassRedis"
                
            }
        },
        //解析远程响应的数据
        serverPaging: true,
		serverSorting: true,
		serverFiltering: true,
		schema: {
			data: "data",
			total: function(data){grid.data("kendoGrid").pager.bind('change', function(){
				sequence = 0;
			});
				var total = data.totalNum;
				if(total == 0){
					$("#monitorList-grid-test .k-grid-header-wrap").css("overflow-x","auto");
				}else{
					$("#monitorList-grid-test .k-grid-header-wrap").css("overflow-x","");
				}
				return total;
			}
		}
	},
	sortable: false,
	groupable: false,
        columns: [{
            field: "sequence",
            title: "序号",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "invoiceSequence",
            title: "账款序号",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "businessContractNo",
            title: "业务合同号",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "memberName",
            title: "客户名称",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "businessProductDesc",
            title: "业务产品",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "currencyDesc",
            title: "币种",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "counterparty",
            title: "交易对手名称",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "invoiceNo",
            title: "单证号码",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "nominvoiceAmt",
            title: "单证面额",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "invoiceAmt",
            title: "应收账款金额",
            width: 200,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "financingRatio",
            title: "融资比例",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "invoiceDate",
            title: "开票日",
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
            field: "expenseSubject",
            title: "本次收取费用名称",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "amount",
            title: "本次收取费用金额",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            buttonCount: 10,  //显示页数
            pageSize: 10,
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
    
    