var PAGESIZE = 10;
var sequence = 0;
$(function(){
	//初始化列表
	searchTradeRecord();
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('.form-search-link').click();
    	}
    });
	
	//点击"查询"
	$(".form-search-link").click(function(){
		searchTradeRecord();
    });
});

/**
 * 根据查询条件查询 流水明细
 */
function searchTradeRecord(){
	$(".accountManageGrid").remove();
	$("#tradeRecordGridWrap").append("<div class=\"accountManageGrid\"></div>");
    sequence = 0;
	var grid = $(".accountManageGrid").kendoGrid({
		selectable: "row",  //设置可选择数据行
		sortable: true,  //列排序
		dataSource:{
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
							accountName: $.trim($("#accountName").val()),
							accountNo: $.trim($("#accountNo").val()).parseBankAccountNo(),
							tradeType: $.trim($("#accountTradeType").val()),
							oppositeAccountName: $.trim($("#oppositeAccountName").val()),
							oppositeAccountNo: $.trim($("#oppositeAccountNo").val()),
							accountType: $.trim($("#accountType").val()),
							startTime: $.trim($("#beginTime").val()),
							endTime: $.trim($("#endTime").val())
					},
					url: basepath + "/account/trade/searchTradeRecordList"
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
						$(".accountManageGrid .k-grid-header-wrap").css("overflow-x","auto");
					}else{
						$(".accountManageGrid .k-grid-header-wrap").css("overflow-x","");
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
			width: 50,
			template: function(data){
				sequence ++;
				return sequence;
			},
			attributes:{
                style: "text-align:center"
			}
		},{
            field: "businessSeq",
            title: "业务流水号",
            width: 250
        },{
            field: "accountName",
            title: "账户名称",
            width: 300
        },{
            field: "userTypeDesc",
            title: "用户类型",
            width: 70,
            attributes: {
                style: "text-align: center"
            }
        },{
			field: "accountNo",
			title: "银行账号",
			width: 170,
			template: function(data){
				return data.accountNo.formatBankAccountNo();
			},
            attributes:{
                style: "text-align:center"
            }
		},{
            field: "tradeTypeDesc",
            title: "交易类型",
            width: 70,
			attributes: {
                style: "text-align: center"
            }
        },{
            field: "operateType",
            title: "操作方式",
            width: 70,
			attributes: {
                style: "text-align: center"
            }
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
            width: 100,
			template: function(data){
				return data.amount.formatMoney();
			},
			attributes: {
                style: "text-align: center"
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
			field: "factorName",
			title: "资金方",
			width: 300,
			hidden: userType=="FACTOR"
		},{
            field: "finaceName",
            title: "融资客户",
            width: 300,
            hidden: userType=="FINACE"
        },{
            field: "accountTypeDesc",
            title: "账户类型",
            width: 70,
			attributes: {
                style: "text-align: center"
            }
        },{
			field: "accountSupervisionStatusDesc",
			title: "监管状态",
			width: 70,
			attributes: {
                style: "text-align: center"
            }
		},{
            field: "statusDesc",
            title: "交易状态",
            width: 70,
			attributes: {
                style: "text-align: center"
            }
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
	grid.data("kendoGrid").pager.bind('change', function(){
		sequence = 0;
	});
}