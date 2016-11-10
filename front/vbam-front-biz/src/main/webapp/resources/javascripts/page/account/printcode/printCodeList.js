var sequence = 0;
$(function(){
	$("#endDate").val(new Date().format("yyyy-MM-dd"));
	$("#startDate").val(subtractMonths(new Date(), 1).format("yyyy-MM-dd"));
	$("#startIndex").val(1);
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('.form-search-link').click();
    	}
    });
	
	//点击"查询"
	$(".form-search-link").click(function(){
		var validator = $("#companyInfoSearch").kendoValidator().data("kendoValidator");
		if (!validator.validate()) {
			return;
		}	
		searchTradeRecord();
    });
});

/**
 * 查询银行下的交易码
 */
function searchTradeRecord(){
	$("#printCodeList").empty();
    sequence = 0;
	var grid = $("#printCodeList").kendoGrid({
		selectable: "row",  //设置可选择数据行
		sortable: true,  //列排序
		dataSource:{
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
							accountNo: $.trim($("#accountNo").val()).parseBankAccountNo(),
							tradeType: $.trim($("#startRecord").val()),
							startDate: $.trim($("#startDate").val()),
							endDate: $.trim($("#endDate").val()),
							startIndex: $.trim($("#startIndex").val())
					},
					url: basepath + "/account/printcode/queryAccountPrintCode"
				}
			}
		},
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
            field: "oppositeAccountNo",
            title: "对方账号",
            width: 170,
            template: function(data){
				return data.oppositeAccountNo.formatBankAccountNo();
			},
			attributes: {
                style: "text-align: center"
            }
        },{
            field: "oppositeAccountName",
            title: "对方账户名称",
            width: 170,
			attributes: {
                style: "text-align: center"
            }
        },{
            field: "oppositeBankName",
            title: "对方开户行名称",
            width: 300,
			attributes: {
                style: "text-align: center"
            }
        },{
            field: "debitCreditTagDesc",
            title: "借贷标识",
            width: 70,
            attributes:{
                style: "text-align:center"
            }
        },{
            field: "tranDate",
            title: "交易时间",
            width: 150,
            attributes:{
                style: "text-align:center"
            }
        },{
			field: "bankTradeTypeDesc",
			title: "交易类型",
			width: 100
		},{
            field: "amount",
            title: "交易金额",
            width: 100,
            template: function(data){
				return parseFloat(data.amount).divide(100).toFixed(2).formatMoney();
			},
			attributes: {
                style: "text-align: center"
            }
        },{
            field: "accountBalance",
            title: "账户余额",
            width: 100,
            template: function(data){
				return parseFloat(data.accountBalance).divide(100).toFixed(2).formatMoney();
			},
			attributes: {
                style: "text-align: center"
            }
        },{
            field: "fee",
            title: "手续费金额",
            width: 100,
            template: function(data){
				return parseFloat(data.fee).divide(100).toFixed(2).formatMoney();
			},
			attributes: {
                style: "text-align: center"
            }
        },{
			field: "memo",
			title: "摘要",
			width: 120,
			attributes: {
                style: "text-align: center"
            }
		},{
            field: "verifyCode",
            title: "打印校验码",
            width: 170,
			attributes: {
                style: "text-align: center"
            }
        }]
	});
}
