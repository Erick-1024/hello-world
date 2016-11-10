function searchRepaymentRule() {
	$("#repayRule-grid").remove();
	$("#repayRule-gridWrap").append("<div class='finance-grid repayRule-grid' id='repayRule-grid'></div>");
    $("#repayRule-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        dataSource:{
            pageSize: 10,
            type: "json", //后台返回的数据类型
            transport: {
            	read: {
            		type : "POST",
            		data: {
            			id: $.trim($("#ruleId").val()),
            			scopeOfApplication: $.trim($("#scopeOfApplication").val()) == "ALL" ? "" : $.trim($("#scopeOfApplication").val())
            		},
            		url: basepath + "/repayment/rule/query"
                }
            },
            serverPaging: true,
    		serverSorting: true,
    		serverFiltering: true,
    		schema:{
            	data: "data",
            	total: function(data) {
					var total = data.totalNum;
	  				if(total == 0){
	  					$("#repayRule-gridWrap .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#repayRule-gridWrap .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
            }
        },
        columns: [{
            field: "id",
            title: "规则编号",
            width: 80
        },{
            title: "规则范围",
            width: 80,
            template: function(data) {
				if(null == data.fianceCustomerCompanys)
					return "全部融资客户";
				return "部分融资客户"
			}
        },{
            field: "fianceCustomerCompanys",
            title: "融资客户",
            width: 250
        },{
            field: "factorTransferInAccountNo",
            title: "回款账户",
            width: 120,
            template: function(data){
            	var account = data.factorTransferInAccountNo + "";
            	return account.formatBankAccountNo();	
            },
            attributes: {
                style: "text-align: center"
            }
        },{
            title: "操作",
            sortable: false,
            width:100,
            template: function(data){
            	var url = "";
            	url += "<a class='comRow-link incRole-link' id='detail-button' data-id=" + data.id + " href='javascript:void(0);'>详情</a>" +
            			"<a class='comRow-link editRule-link' id='edit-button' data-id=" + data.id + " href='javascript:void(0);'>编辑</a>";
            	return url;
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
};

function showAddRepaymentRulePage() {
	$("body").on("click", ".addRule-link", function() {
		window.open(basepath + "/repayment/rule/showAddRule");
	});
};

$(function() {
	searchRepaymentRule();
	
	showAddRepaymentRulePage();
	
	$("body").on("click", "#search-button", function() {
		searchRepaymentRule();
	});
	
    $("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$("#search-button").click();
    	}
    });
    
    $("body").on("click", "#detail-button", function() {
		window.open(basepath + "/repayment/rule/showDetail?ruleId=" + $(this).data("id"));
	});
    
    $("body").on("click", "#edit-button", function() {
		window.open(basepath + "/repayment/rule/showModify?ruleId=" + $(this).data("id"));
	});
});