var PAGESIZE = 10;
var sequence = 0;
$(function(){
	//初始化列表
	searchAccount();
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('.form-search-link').click();
    	}
    });
	
	//点击"查询"
	$(".form-search-link").click(function(){
		searchAccount();
    });
	
	//初始化操作确认弹窗
	confirmPopWindow = new PopWindow({
		title: "提示",
		width: 420,
		reload: true,
		template: "#template-notice"
	}).init();
	
	//初始化操作结果弹窗
	tipPopWindow = new PopWindow({
		title: "提示",
		width: 420,
		reload: true,
		template: "#tipBox_template"
	}).init();
	
	//确认提交弹窗确认按钮
    $("body").on("click", "#confirm-box-window .confirm-link", function(e){
        var win = $(e.target).closest(".k-window");
        var overlay = win.prev(".k-overlay");
        if(win.is(":visible")){
            win.css("display", "none");
            overlay.css("display", "none");
        }
        var data = $("#confirm-box-window #operationObj").val();
        var obj = $("a[data="+data+"]");
        var operation = data.split("-")[0];
		var accountId = data.split("-").pop();
        if(operation == "freeze"){
        	var url = "freeze";
    		var message = "账户冻结";
    		operationReturnTipBox(obj,url,accountId,message);
        }
        if(operation == "unfreeze"){
        	var url = "unfreeze";
    		var message = "账户解冻";
    		operationReturnTipBox(obj,url,accountId,message);
        }
        if(operation == "setDefault"){
        	var url = "setDefault";
    		var message = "设为默认账户";
    		operationReturnTipBox(obj,url,accountId,message);
        }
    });
    
  //确认提交弹窗关闭按钮
    $("body").on("click", "#confirm-box-window .back-link", function(e){
        var win = $(e.target).closest(".k-window");
        var overlay = win.prev(".k-overlay");
        if(win.is(":visible")){
            win.css("display", "none");
            overlay.css("display", "none");
        }
    });
    
	//点击"冻结"
	$("body").on("click", ".freeze-link", function(e){
		confirmPopWindow.open().center();
		$("#confirm-box-window #operationObj").val($(this).attr("data"));
		$("#confirm-box-window .notice-content").html("是否确认冻结此账户？<br/>账号：" + $(this).closest('tr').find('.col-account-no').text());
	});
    
    //点击"解冻"
	$("body").on("click", ".unfreeze-link", function(e){
		confirmPopWindow.open().center();
		$("#confirm-box-window #operationObj").val($(this).attr("data"));
		$("#confirm-box-window .notice-content").html("是否确认解冻此账户？<br/>账号：" + $(this).closest('tr').find('.col-account-no').text());
	});
	
	//点击"设为默认账户"
	$("body").on("click", ".setDefault-link", function(e){
		confirmPopWindow.open().center();
		$("#confirm-box-window #operationObj").val($(this).attr("data"));
		$("#confirm-box-window .notice-content").text("确认要设此账户为默认账户吗？");
	});
	
	//点击"详情"
	$("body").on("click", ".detail-link", function(e){
		var id = $(this).parent().next().text();
		window.open(encodeURI(basepath + "/account/detail?accountId=" + id));
	});
	
	//点击"解除监管关系"
	$("body").on("click", ".removeSupervision-link", function(e){
		var id = $(this).parent().next().next().text();
		if(id == "")
			id = $(this).parent().next().text();
		window.open(encodeURI(basepath + "/account/supervision/remove?accountId=" + id));
	});

	//点击"查看" --查询余额
	$("body").on("click", ".queryBalance-link", function(e){
		var accountNo = $(this).attr("data-accountNo");
		getAccountBalance(accountNo,$(this));
	});
	
	//点击"获取到的余额" --显示"查看"
	$("body").on("click", ".showQuery-link", function(e){
		$(this).text("查看");
		$(this).removeClass("showQuery-link").addClass("queryBalance-link");
		$(this).parent().attr("style","text-align:center")
	});
	
});

/**
 * 解冻，冻结，设默认账户post
 * @returns
 */
function operationReturnTipBox(obj,url,accountId,message){
	$.ajax({
        type: 'POST',  
        url: url,
        data: {
        	accountId: accountId
        },
        success:function(data){
        	tipPopWindow.open().center();
    		if(data == "success")
			{	
    			$("#tip-box-window .dlg-notice").addClass("notice-icon02");
				$("#tip-box-window .notice-content").text(message + "成功");
				if(url == "freeze"){
					$(obj).parent().prev().prev().text("冻结");
					if(unfreezeAuth){
						$(obj).text("解冻");
						$(obj).removeClass("freeze-link");
						$(obj).addClass("unfreeze-link");
						$(obj).attr("data","unfreeze-" + accountId);
					}else{
						$(obj).remove();
					}
				}
				else if(url == "unfreeze"){
					$(obj).parent().prev().prev().text("正常");
					if(freezeAuth){
						$(obj).text("冻结");
						$(obj).removeClass("unfreeze-link");
						$(obj).addClass("freeze-link");
						$(obj).attr("data","freeze-" + accountId);
					}else{
						$(obj).remove();
					}
				}
				else if(url == "setDefault"){
					$(obj).remove();
				}
			}
			else
			{
				$("#tip-box-window .dlg-notice").addClass("notice-icon01");
				$("#tip-box-window .notice-content").text(data);
			}
        }
    });
}

/**
 * 刷新--获取一个账户余额
 */
var isAccountBalanceRequesting = false;
function getAccountBalance(accountNo,obj){
	if(isAccountBalanceRequesting)
		return;
	isAccountBalanceRequesting = true;
	var objParent = $(obj).parent();
	$(obj).removeClass("queryBalance-link").addClass("showQuery-link");
	$(obj).text("获取中");
	cana.post(basepath + "/account/trade/queryAccountBalance",
			{accountNoString: accountNo},
			function(data){
				var accountBalance = parseFloat(data.accountBalances[0]).toFixed(2);
				if(!isNaN(accountBalance)){
					$(objParent).attr("style","text-align:right");
					$(obj).text(accountBalance.formatMoney());
				}
			},function(data){
				$(obj).text("获取失败");
			},function(){
				isAccountBalanceRequesting = false;
			});
}

/**
 * 根据查询条件查询 账户列表
 */
function searchAccount(){
	$(".accountManageGrid").remove();
	$("#accountGridWrap").append("<div class=\"accountManageGrid\"></div>");
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
							factorName: $.trim($("#factorName").val()),
							finaceName: $.trim($("#finaceName").val()),
							coreCompanyName: $.trim($("#coreCompanyName").val()),
							accountStatus: $.trim($("#accountStatus").val()),
							accountType: $.trim($("#accountType").val()),
							accumulationStatus: $.trim($("#accumulationStatus").val()),
							supervisoryStatus: $.trim($("#supervisionStatus").val()),
							isTransferInAccount: $('#isTransferInAccount').val()
					},
					url: basepath + "/account/searchList"
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
            field: "accountName",
            title: "账户名称",
            width: 300
        },{
			field: "accountNo",
			title: "银行账号",
			width: 170,
			template: function(data){
				return '<span class="col-account-no">' + data.accountNo.formatBankAccountNo() + '</span>';
			},
            attributes:{
                style: "text-align:center"
            }
		},{
            field: "accountTypeDesc",
            title: "账户类型",
            width: 70,
            attributes:{
                style: "text-align:center"
            }
        },{
            field: "supervisionStatusDesc",
            title: "监管状态",
            width: 70,
            attributes:{
                style: "text-align:center"
            },
            hidden: userType!="FACTOR" && userType!="FINACE" && userType!="CANA"
        },{
            title: "回款账户",
            width: 70,
            attributes:{
                style: "text-align:center"
            },
            hidden: userType!="FACTOR" && userType!="CANA",
            template: function(data) {
            	return data.transferInAccount ? "是" : "否";
            }
        },{
			field: "factorName",
			title: "资金方",
			width: 300,
			hidden: userType!="FINACE" && userType!="CANA"
		},{
            field: "finaceName",
            title: "融资客户",
            width: 300,
            hidden: userType!="FACTOR" && userType!="CANA"
        },{
            field: "coreCompanyName",
            title: "核心企业",
            width: 300,
            hidden: userType!="CANA"
        },{
			field: "buyerName",
			title: "买方企业",
			width: 300,
			hidden: userType!="FACTOR" && userType!="FINACE" && userType!="CANA"
		},{
            field: "accumulationStatusDesc",
            title: "归集状态",
            width: 70,
            attributes:{
                style: "text-align:center"
            },
            hidden: userType!="FACTOR" && userType!="FINACE" && userType!="CANA"
        },{
            field: "accumulationAccountNo",
            title: "归集账号",
            width: 170,
			template: function(data){
				if(!data.accumulationAccountNo)
					return "";
				return data.accumulationAccountNo.formatBankAccountNo();
			},
            attributes:{
                style: "text-align:center"
            },
            hidden: userType!="FACTOR" && userType!="FINACE" && userType!="CANA"
        },{
            field: "accountStatusDesc",
            title: "账户状态",
            width: 70,
            attributes:{
                style: "text-align:center"
            }
        },{
            title: "余额",
            width: 100,
            template: function(data){
            	var url = "";
            	if(detailAuth)
            		url += "<a class='queryBalance-link' href='javascript:void(0);' data-accountNo=" + data.accountNo + ">查看</a>";
            	return url;
            },
            attributes:{
                style: "text-align:center"
            }
        },{
            title: "操作",
            width: 300,
            template: function(data){
            	var url = "";
            	if(detailAuth)
            		url += "<a class='comRow-link detail-link' href='javascript:void(0);'>详情</a>";
            	if(freezeAuth && data.allowFreeze)
            		url += "<a class='comRow-link freeze-link' href='javascript:void(0);' data=freeze-"+data.accountId+">冻结</a>";
            	if(unfreezeAuth && data.allowUnfreeze)
            		url += "<a class='comRow-link unfreeze-link' href='javascript:void(0);' data=unfreeze-"+data.accountId+">解冻</a>";
            	if(setDefaultAuth && data.allowSetDefault)
            		url += "<a class='comRow-link setDefault-link' href='javascript:void(0);' data=setDefault-"+data.accountId+">设为默认账户</a>";
            	if(removeSupersivionAuth && data.allowRemoveSupervision)
            		url += "<a class='comRow-link removeSupervision-link' href='javascript:void(0);'>解除监管关系</a>";
            	return url;
            }
        },{
        	field: "accountId",
            title: "accountId",
            hidden: true
        },{
        	field: "accumulationAccountId",
            title: "accumulationAccountId",
            hidden: true
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
