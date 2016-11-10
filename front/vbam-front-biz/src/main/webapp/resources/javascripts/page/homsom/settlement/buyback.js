var pageSize = 10;
$(function(){
    //初始化弹窗1
    new PopWindow(".openRopePop", {
        title: "回购",
        width: 600,
        reload: true,
        template: "#tipBox_repo"
    }).init();
    //初始化弹窗2
    new PopWindow(".openBackPop", {
        title: "回购明细",
        width: 600,
        reload: true,
        template: "#tipBox_back"
    }).init();
    //初始化弹窗3
    new PopWindow(".openExceptionPop", {
    	title: "回购明细",
    	width: 600,
    	reload: true,
    	template: "#tipBox_exception"
    }).init();
    //初始化表格
    pageFormData();
    //回购点击事件
    $("body").on("click",".repo-btn",function(){
        $(".openRopePop").trigger('click');
        popFormDataOne($(this).parent().next().text());
        $("td[name='buybackCounterpartyName']").text($(this).parent().prev().prev().prev().prev().text());
        $("td[name='buybackDateTD']").text($("td[name='buybackDate']").text());
        $("td[name='buybackAmount']").text($(this).parent().prev().prev().prev().text());
        $("input[name='buybackCounterpartyId']").val($.trim($(this).parent().next().text()));
        $("input[name='counterpartyId']").val($.trim($(this).parent().next().next().text()));
    });
    //回购明细点击事件
    $("body").on("click",".repoBack-btn",function(){
        $(".openBackPop").trigger('click');
        popFormDataTwo($(this).parent().next().text());
        $("td[name='buybackCounterpartyNameTD']").text($(this).parent().prev().prev().prev().prev().text());
        $("td[name='buybackDateTD2']").text($("td[name='buybackDate']").text());
        $("td[name='buybackAmountTD']").text($(this).parent().prev().prev().prev().text());
    });
    
    $("body").on("click",".confirm-buyback",function(){
    	buybackConfirmCheck($("input[name='buybackCounterpartyId']").val(), $("input[name='counterpartyId']").val());
    });
});

function buybackConfirmCheck(buybackCounterpartyId, counterpartyId){
	$.ajax({
		type: "POST",
		url: basepath + "/homsom/settlement/get/buyback/ticket/list/check/HOMSOM",
		data: {state : "UNSETTLE", buybackCounterpartyId: buybackCounterpartyId, counterpartyId: counterpartyId },
		dataType: "json",
		success: function(data){
			if(data.status == "SUCCESS"){
				$(".openExceptionPop").trigger('click');
				for(var ticketNo in data.data) {
					$("td[name='ticketNoList']").html($("td[name='ticketNoList']").html() + "<p>" + data.data[ticketNo] + "</p>");
				}
				$("body").on("click",".confirm-after-check",function(){
					buybackConfirm(counterpartyId, buybackCounterpartyId, data.data);
				});
			}else{
				buybackConfirm(counterpartyId, buybackCounterpartyId , null);
			}
		}
	});
}

function buybackConfirm(counterpartyId, buybackCounterpartyId, unselectedTicketNoList){
	$.ajax({
		async : false,  
		cache : false,  
		type: 'POST',  
		dataType : "json",
		contentType : "application/json",
		data:JSON.stringify({buybackCounterpartyId: buybackCounterpartyId, paymentType: "BUYBACK", unselectedTicketNoList: unselectedTicketNoList, counterpartyId: counterpartyId}),
		url: basepath + "/homsom/settlement/reconciliation/comfirm/HOMSOM",
		success:function(data){ //请求成功后处理函数。 
			if(data.status == "SUCCESS"){
				$(".k-window-action").click();
				showSuccessWin("核销成功");
				$("#monitorList-grid").find("td").each(function(){
					if($(this).text() == buybackCounterpartyId){
						$(this).prev().html("<a class='userCenter-link repoBack-btn' href='javascript:void(0);'>回购明细</a>");
						$(this).prev().prev().text("已回购");
						$(this).prev().prev().prev().text(data.message);
					}
				});
			}else{
				showAlertWin(data.message);
			} 
		},
		error: function (data) {
        	showAlertWin("网络异常!");
        }
	});
}


//页面表格数据
function pageFormData(){
    $("#monitorList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
        	type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: function(option){
                	var paraData = {};
                	paraData.pageSize = option.data.pageSize;
                	paraData.page = option.data.page;
                	paraData.paymentType = "BUYBACK";
                	$.ajax({
                    	data: paraData,
                    	type : "POST",
                    	dataType:"json",
                    	url: basepath+"/homsom/settlement/get/buyback/list/HOMSOM",
                    	success:function(data){
                    		option.success(data);
                    	}
                	});
                 }
            },
            serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			//解析远程响应的数据
			schema:{
				data: "data",
				total: function(data){
					var total = data.totalNum;
					if(total == 0){
						$("#monitorList-grid .k-grid-header-wrap").css("overflow-x","auto");
					}else{
						$("#monitorList-grid .k-grid-header-wrap").css("overflow-x","");
					}
					return total;
				}
			}
        },
        columns: [{
            field: "counterpartyName",
            title: "交易对手",
            width: 220,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "buybackAmount",
            title: "应回购总金额",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "actualBuybackAmount",
            title: "回购总金额",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "risk",
            title: "状态",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	if(data.state == "UNSETTLE"){
            		return "未核销";
            	}else if(data.state == "SETTLED"){
            		return "已核销";
            	}else{
            		return "";
            	}
            }
        },{
            field: "risk",
            title: "操作",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
                var edHtml = "";
                if(data.state=="UNSETTLE"){
                    edHtml = "<a class='userCenter-link repo-btn' href='javascript:void(0);'>回购</a>";
                }else{
                    edHtml = "<a class='userCenter-link repoBack-btn' href='javascript:void(0);'>回购明细</a>";
                }
                return edHtml;
            }
        },{
            field: "id",
            title: "id",
            hidden: true
        },{
            field: "counterpartyId",
            title: "counterpartyId",
            hidden: true
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
}
//回购弹窗表格数据
function popFormDataOne(buybackCounterpartyId){
    $("#grid-repo").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
        	type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: function(option){
                	var paraData = {};
                	paraData.pageSize = option.data.pageSize;
                	paraData.page = option.data.page;
                	paraData.buybackCounterpartyId = $.trim(buybackCounterpartyId);
                	paraData.paymentType = "BUYBACK";
                	paraData.state = "UNSETTLE";
                	$.ajax({
                    	data: paraData,
                    	type : "POST",
                    	dataType:"json",
                    	url: basepath+"/homsom/settlement/get/buyback/ticket/list/HOMSOM",
                    	success:function(data){
                    		option.success(data);
                    	}
                	});
                 }
            },
            serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			//解析远程响应的数据
			schema:{
				data: "data",
				total: function(data){
					var total = data.totalNum;
					if(total == 0){
						$("#monitorList-grid .k-grid-header-wrap").css("overflow-x","auto");
					}else{
						$("#monitorList-grid .k-grid-header-wrap").css("overflow-x","");
					}
					return total;
				}
			}
        },
        columns: [{
            field: "ticketNo",
            title: "回购票号",
            width: 120,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "loanAmount",
            title: "本金",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "loanPeriod",
            title: "期限（天）",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "interestAmount",
            title: "利息",
            width: 100,
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
}
//回购明细弹窗表格数据
function popFormDataTwo(buybackCounterpartyId){
    $("#grid-back").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
        	type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: function(option){
                	var paraData = {};
                	paraData.pageSize = option.data.pageSize;
                	paraData.page = option.data.page;
                	paraData.buybackCounterpartyId = buybackCounterpartyId;
                	paraData.paymentType = "BUYBACK";
                	paraData.state = "SETTLED";
                	$.ajax({
                    	data: paraData,
                    	type : "POST",
                    	dataType:"json",
                    	url: basepath+"/homsom/settlement/get/buyback/ticket/list/HOMSOM",
                    	success:function(data){
                    		option.success(data);
                    	}
                	});
                 }
            },
            serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			//解析远程响应的数据
			schema:{
				data: "data",
				total: function(data){
					var total = data.totalNum;
					if(total == 0){
						$("#monitorList-grid .k-grid-header-wrap").css("overflow-x","auto");
					}else{
						$("#monitorList-grid .k-grid-header-wrap").css("overflow-x","");
					}
					return total;
				}
			}
        },
        columns: [{
            field: "ticketNo",
            title: "回购票号",
            width: 120,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "loanAmount",
            title: "本金",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "loanPeriod",
            title: "期限（天）",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "interestAmount",
            title: "利息",
            width: 100,
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
}