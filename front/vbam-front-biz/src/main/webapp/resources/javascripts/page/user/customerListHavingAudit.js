var PAGESIZE = 10;
var sequence = 0;
var id ;
$(function(){
	
    //初始化重发邮件弹窗
    new PopWindow(".resend-link", {
        title: "重发邮件",
        width: 420,
        reload: true,
        template: "#template-resend"
    }).init();
	
	searchCustomer();
	
    $("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('.form-search-link').click();
    	}
    });
	
	$("body").on("click", ".form-search-link", function(e){
    	searchCustomer();
    });
	
    $("body").on("click", ".detail-link", function(e){
    	id = $(this).parent().next().text();
    	window.open(encodeURI(basepath + "/customer/customerDetail?customerId=" + id + "&curSubMenu=" + curSubMenu));
    });
    
    $("body").on("click", ".resend-link", function(e){
    	id = $(this).parent().next().text();
    });
    
    $("body").on("click", "#confirmResend", function(e) {
    	$.get(
        		basepath + "/customer/resend?userId=" + id,
        		function(data) {
    				if(data){
    					var win = $(e.target).closest(".k-window");
                		var overlay = win.prev(".k-overlay");
                		if(win.is(":visible")){
                			win.css("display", "none");
                			overlay.css("display", "none");
                		}
    				}
    			}
        	)
	});
    
    $("body").on("click", ".dlg-wrapper .back-link", function(e){
        var win = $(e.target).closest(".k-window");
        var overlay = win.prev(".k-overlay");
        if(win.is(":visible")){
            win.css("display", "none");
            overlay.css("display", "none");
        }
    });
    
});

//初始化数据表格
function searchCustomer() {
	$(".companyAcutGrid").remove();
	$("#companyAcutGridWrap").append("<div class=\"companyAcutGrid\"></div>");
    sequence = 0;
	var grid = $(".companyAcutGrid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
            type: "json", //后台返回的数据类型
            transport: {
                read: {
                	type: "post",
					data: {
							username: $.trim($("#username").val()),
							userType: ($.trim($("#userType").val()) == "ALL")?"":$.trim($("#userType").val()),
							companyName: $.trim($("#companyName").val()),
							beginTime: $.trim($("#beginTime").val()),
							endTime: $.trim($("#endTime").val()),
							accountAuditStatus: "HAVINGAUDIT",
							accountAuditResult: ($.trim($("#accountAuditResult .active").attr("name")) == "ALL")?"":$.trim($("#accountAuditResult .active").attr("name")),
							accountActivateStatus: ($.trim($("#accountActivateStatus .active").attr("name")) == "ALL")?"":$.trim($("#accountActivateStatus .active").attr("name"))
					},
					url: basepath + "/customer/result"
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
	  					$("#companyAcutGridWrap .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#companyAcutGridWrap .k-grid-header-wrap").css("overflow-x","");
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
			template : function(data){
				sequence ++;
				return sequence;
			},
			attributes: {
                style: "text-align: center"
            }
        },{
            field: "username",
            title: "用户名",
            width: 100
        },{
            field: "userType",
            title: "客户类型",
            width: 70,
			template : function(data){
				if(data.userType == "FACTOR")
					return "资金方";
				else if(data.userType == "FINACE")
					return "融资客户";
				else if(data.userType == "CORECOMPANY")
					return "核心企业";
				else if(data.userType == "BROKERTRUSTORG")
					return "券商/信托";
				else if(data.userType == "OTHERORG")
					return "其他机构";
			}
        },{
            field: "companyName",
            title: "企业名称",
            width: 300
        },{
            field: "agentCompany",
            title: "申请企业",
            width: 300
        },{
            field: "createTime",
            title: "申请时间",
            width: 150,
			template : function(data){
				return new Date(data.createTime).format("yyyy-MM-dd hh:mm:ss");
			},
			attributes: {
                style: "text-align: center"
            }
        },{
			field: "accountActivateStatus",
			title: "账号状态",
			width: 70,
			template : function(data){
				if(data.accountActivateStatus == "UNACTIVATE")
					return "未激活";
				else if (data.accountActivateStatus == "ACTIVATED") {
					return "已激活"
				}
				return "";
			},
			attributes: {
                style: "text-align: center"
            }
		},{
            field: "accountAuditResult",
            title: "审核结果",
            width: 70,
            template : function(data){
				if(data.accountAuditResult == "PASSAUDIT")
					return "通过";
				else if (data.accountAuditResult == "REJECTED") {
					return "未通过";
				}
				return "";
			},
			attributes: {
                style: "text-align: center"
            }
        },{
			field: "accountAuditStatus",
			title: "审核状态",
			width: 70,
			template : function(data){
				if(data.accountAuditStatus == "PENDINGAUDIT")
					return "待审核";
				else if (data.accountAuditStatus == "HAVINGAUDIT") {
					return "已审核";
				}
				return "";
			},
			attributes: {
                style: "text-align: center"
            }
		},{
            field: "auditor",
            title: "审核人",
            width: 100
        },{
            title: "操作",
            width: 200,
            template: function(data){
            	var url = "";
            	if(detailAuth){
            		url += "<a class='comRow-link detail-link' href='javascript:void(0);'>详情</a>";
            	}
            	if(resendAuth && data.accountAuditResult == "PASSAUDIT" && data.accountActivateStatus == "UNACTIVATE"){
            		url += "<a class='comRow-link resend-link' href='javascript:void(0);'>重发邮件</a>";
            	}
            	return url;
            }
        },{
        	field: "id",
            title: "id",
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