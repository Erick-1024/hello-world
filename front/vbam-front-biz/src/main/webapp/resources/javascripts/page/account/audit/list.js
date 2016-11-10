var PAGESIZE = 10;
var sequence = 0;
$(function(){
	//初始化列表
	searchApplys();
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('.form-search-link').click();
    	}
    });
	
	//点击"查询"
	$(".form-search-link").click(function(){
		searchApplys();
    });
});

/**
 * 根据查询条件查询 账户列表
 */
function searchApplys(){
	var applyBySelf = $('#applyBySelf').val() || false;
    sequence = 0;
    $(".accountManageGrid").empty();
	var grid = $(".accountManageGrid").kendoGrid({
		selectable: "row",  //设置可选择数据行
		sortable: true,  //列排序
		dataSource:{
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						accountNo: $.trim($("#accountNo").val().parseBankAccountNo()),
						accountName: $.trim($("#accountName").val()),
						applyCompanyName: $.trim($("#applyCompanyName").val()),
						applyType: $.trim($("#applyType").val()),
						applyStatus: $.trim($("#applyStatus").val()),
						startTime: $.trim($("#startTime").val()),
						endTime: $.trim($("#endTime").val()),
						auditBySelf : !applyBySelf,
						applyBySelf : applyBySelf
					},
					url: basepath + "/account/audit/search"
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
            field: "applyCompanyName",
            title: "申请客户",
            width: 300,
            hidden: applyBySelf
        },{
			field: "applyTypeDesc",
			title: "申请类型",
			width: 70,
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
            template: function(data) {
            	return data.accountNo.formatBankAccountNo();
            },
            attributes:{
                style: "text-align:center"
            }
        },{
			field: "createTime",
			title: "申请时间",
			width: 150,
			template: function(data){
				return new Date(data.createTime).format("yyyy-MM-dd hh:mm:ss");
			},
			attributes:{
                style: "text-align:center"
			}
		},{
            field: "applyStatusDesc",
            title: "审核状态",
            width: 70,
            attributes:{
                style: "text-align:center"
			}
        },{
            title: "操作",
            width: 120,
            template: function(data){
            	var url = "";
            	if (data.applyType == "CREATE_SUPERVISION" || data.applyType == "REMOVE_SUPERVISION") {
            		if (supervisionApplyDetailAuth) {
		        		url += "<a class='comRow-link incRole-link' target='_blank' href='"
		        			+ basepath + "/account/supervision/detail?applyId=" + data.id
		        			+ "'>详情</a>";
            		}
	        		if (data.allowAudit && supervisionApplyAuditAuth) {
		        		url += "<a class='comRow-link incEdit-link' target='_blank' href='"
		        			+ basepath + "/account/supervision/audit?applyId=" + data.id
		        			+ "'>审核</a>";
	        		}
            	} else if (data.applyType == "TRANSFER_FUND" || data.applyType == "WITHDRAW_FUND") {
            		if (tradeApplyDetailAuth) {
		        		url += "<a class='comRow-link incRole-link' target='_blank' href='"
		        			+ basepath + "/account/trade/detail?tradeApplyId=" + data.id
		        			+ "'>详情</a>";
            		}
	        		if (data.allowAudit && tradeApplyAuditAuth) {
		        		url += "<a class='comRow-link incEdit-link' target='_blank' href='"
		        			+ basepath + "/account/trade/audit?tradeApplyId=" + data.id
		        			+ "'>审核</a>";
	        		}
            	}
            	return url;
            }
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
