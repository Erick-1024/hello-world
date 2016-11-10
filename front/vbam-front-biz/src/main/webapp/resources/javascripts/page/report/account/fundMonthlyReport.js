var pageSize = 10;
var sequence = 0;
var monthPattern = "^\\d{4}[-]\\d{2}$";
$(function(){
	
//	setValidator($("input[name='reportDate']"), "required", true, "日期不能为空");
	setValidator($("input[name='reportDate']"), "pattern", monthPattern, "日期格式不正确");
	
	$("input[name='reportDate']").kendoValidator();
	
    kendo.culture('zh-CN');
    $("#monthpicker").kendoDatePicker({
        start: "year",
        depth: "year",
        /*  min:"2016-09",*/
        format: "yyyy-MM"
    });
    $(".k-state-default").css("border-color","#dadada");
    $(".k-animation-container").css("color","#000");
    $(".k-link.k-nav-fast").css("color","#2e2e2e");
    $(".k-link").css("color","#2e2e2e");
   // $(".k-calendar .k-link").css("cssText","color:#2e2e2e;");
    $(".k-i-arrow-w").css("cssText","background-position:-16px -48px!important;");
    $(".k-i-arrow-e").css("cssText","background-position:-16px -16px!important;");
    
    //加载列表数据
    accountFundMonthlyReport();
    getMonthlyBalanceSum();
//    getMainAccountBalance();
    
    // 点击搜索按钮
    $("body").on('click','.form-search-link',function(){
    	accountFundMonthlyReport();
    	getMonthlyBalanceSum();
//    	getMainAccountBalance();
    });
    
    document.onkeydown=keyDownSearch;  
    function keyDownSearch(e) {    
        var theEvent = e || window.event;    
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
        if (code == 13) {    
        	accountFundMonthlyReport();
            return false;    
        }    
        return true;    
    }
    
    exportEvent();
});

function exportFundMonthlyReport() {
	var validator = $("input[name='reportDate']").data("kendoValidator");
	if(!validator.validate()) {
		return;
	};
	var accountNo = $.trim($("input[name='accountNo']").val());
	var financeName = $("input[name=financeName]").val();
	var accountName = $("input[name=accountName]").val();
	var factorName = $("input[name=factorName]").val();
	var accountType = $("select[name=accountType]").val() == "ALL" ? "" : $("select[name=accountType]").val();
	var supervisionStatus = $("select[name=supervisionStatus]").val() == "ALL" ? "" : $("select[name=supervisionStatus]").val();
	var transferInAccount = $("select[name=transferInAccount]").val() == "ALL" ? "" : $("select[name=transferInAccount]").val();
	var accountStatus = $("select[name=accountStatus]").val() == "ALL" ? "" : $("select[name=accountStatus]").val();
	var fundBalanceGetState = $("select[name=fundBalanceGetState]").val() == "ALL" ? "" : $("select[name=fundBalanceGetState]").val();
	var reportDate = $("input[name=reportDate]").val();
	window.location.href = basepath + "/report/account/exportFundMonthlyReport?accountNo=" + accountNo + "&financeName=" + financeName + "&accountName=" 
	+ accountName + "&factorName=" + factorName + "&accountType=" + accountType + "&supervisionStatus=" + supervisionStatus + "&transferInAccount=" 
	+ transferInAccount + "&accountStatus=" + accountStatus + "&fundBalanceGetState=" + fundBalanceGetState + "&reportDate=" + reportDate;
		
}

function exportEvent() {
	$("body").on("click", ".export-btn", exportFundMonthlyReport);
}

function getMonthlyBalanceSum(){
	var validator = $("input[name='reportDate']").data("kendoValidator");
	if(!validator.validate()) {
		return;
	};
	$.ajax({
		type: "POST",
		url: basepath + "/report/account/getMonthlyBalanceSum",
		data: {accountNo: $.trim($("input[name='accountNo']").val()),
			financeName: $("input[name=financeName]").val(),
			accountName: $("input[name=accountName]").val(),
			factorName: $("input[name=factorName]").val(),
			accountType: $("select[name=accountType]").val() == "ALL" ? "" : $("select[name=accountType]").val(),
			supervisionStatus: $("select[name=supervisionStatus]").val() == "ALL" ? "" : $("select[name=supervisionStatus]").val(),
			transferInAccount: $("select[name=transferInAccount]").val() == "ALL" ? "" : $("select[name=transferInAccount]").val(),
			accountStatus: $("select[name=accountStatus]").val() == "ALL" ? "" : $("select[name=accountStatus]").val(),
			fundBalanceGetState: $("select[name=fundBalanceGetState]").val() == "ALL" ? "" : $("select[name=fundBalanceGetState]").val(),
			reportDate: $("input[name=reportDate]").val()
			},
		dataType: "json",
		success: function(data){
			if(data.status == "SUCCESS"){
				$("#accountBalanceSum").text(data.data);
			}else{
				$("#accountBalanceSum").text(data.message);
			}
		}
	});
}

//function getMainAccountBalance(){
//	var validator = $("input[name='reportDate']").data("kendoValidator");
//	if(!validator.validate()) {
//		return;
//	};
//	$.ajax({
//		type: "POST",
//		url: basepath + "/report/account/getMainAccountBalance",
//		data: {reportDate: $("input[name=reportDate]").val()},
//		dataType: "json",
//		success: function(data){
//			if(data.status == "SUCCESS"){
//				$("#mainAccountBalance").text(data.data);
//			}else{
//				$("#mainAccountBalance").text(data.message);
//			}
//		}
//	});
//}
 
function accountFundMonthlyReport(){
	var validator = $("input[name='reportDate']").data("kendoValidator");
	if(!validator.validate()) {
		return;
	};
	// 余额信息列表
    $("#reprots-dayGrid").empty();
    var grid = $("#reprots-dayGrid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						accountNo: $.trim($("input[name='accountNo']").val()),
						financeName: $("input[name=financeName]").val(),
						accountName: $("input[name=accountName]").val(),
						factorName: $("input[name=factorName]").val(),
						accountType: $("select[name=accountType]").val() == "ALL" ? "" : $("select[name=accountType]").val(),
						supervisionStatus: $("select[name=supervisionStatus]").val() == "ALL" ? "" : $("select[name=supervisionStatus]").val(),
						transferInAccount: $("select[name=transferInAccount]").val() == "ALL" ? "" : $("select[name=transferInAccount]").val(),
						accountStatus: $("select[name=accountStatus]").val() == "ALL" ? "" : $("select[name=accountStatus]").val(),
						fundBalanceGetState: $("select[name=fundBalanceGetState]").val() == "ALL" ? "" : $("select[name=fundBalanceGetState]").val(),
						reportDate: $("input[name=reportDate]").val()
					},
					url: basepath + "/report/account/queryAccountFundMonthlyReport"
				}
			},
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			schema: {
				data: "data",
				total: function(data) {
					var total = data.totalNum;
	  				if(total == 0){
	  					$("#manual-repayGrid .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#manual-repayGrid .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
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
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "month",
            title: "日期",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "factorName",
            title: "保理商",
            width: 200,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "financeName",
            title: "融资客户",
            width: 200,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "accountName",
            title: "账户名称",
            width: 200,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "accountNo",
            title: "银行账号",
            width: 200,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "accountType",
            title: "账户类型",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "supervisionStatus",
            title: "监管类型",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "isTransferInAccount",
            title: "回款账户",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "accountStatus",
            title: "账户状态",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "balance",
            title: "当月账户余额",
            width: 140,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "result",
            title: "获取结果",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "updateTime",
            title: "时间",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            pageSize: pageSize,
            page: 1,
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
    grid.data("kendoGrid").pager.bind('change', function(){
		sequence = 0;
	});
}