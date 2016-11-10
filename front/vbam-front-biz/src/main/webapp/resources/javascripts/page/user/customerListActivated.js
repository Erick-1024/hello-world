var PAGESIZE = 10;
var sequence = 0;
var id;
$(function(){
	
    //初始化重置密码弹窗
    new PopWindow(".reset-link", {
        title: "重发邮件",
        width: 420,
        reload: true,
        template: "#template-reset"
    }).init();
    
    //初始化重置支付密码弹窗
    new PopWindow(".resetPayPassword-link", {
        title: "重置支付密码",
        width: 420,
        reload: true,
        template: "#template-resetPayPassword"
    }).init();
    
    $("body").on("click", ".resetPayPassword-link", function(e){
    	id = $(this).parent().next().text();
    	$("span[name='customerName']").text("客户名称："+$(this).parent().prev().prev().prev().prev().prev().prev().prev().text())
    	$("#confirmResetPayPassword").attr("data-id",$.trim(id));
    });

    $("body").on("click", ".reset-link", function(e){
    	id = $(this).parent().next().text();
    	$("span[name='customerName']").text("客户名称："+$(this).parent().prev().prev().prev().prev().prev().prev().prev().text())
    });
	
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
    
    $("body").on("click", "#confirmResetPayPassword", function(e){
    	var userId = $.trim($(this).attr("data-id"));
 		$.ajax({
 			type: "POST",
 			url: basepath+"/user/resetPayPassword",
 			data: { userId : userId},
 				dataType: "json",
 				success: function(data){
 					if(data.status == "SUCCESS"){
 						var win = $(e.target).closest(".k-window");
 						var overlay = win.prev(".k-overlay");
 						if(win.is(":visible")){
 							win.css("display", "none");
 							overlay.css("display", "none");
 						}
 					}else{
 						var win = $(e.target).closest(".k-window");
 						var overlay = win.prev(".k-overlay");
 						if(win.is(":visible")){
 							win.css("display", "none");
 							overlay.css("display", "none");
 						}
 						showAlertWin(data.message);
 					}
 				}
 		});
    });
    
    $("body").on("click", "#confirmReset", function(e) {
    	$.get(
        		basepath + "/customer/restPassword?userId=" + id,
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
						accountActivateStatus: "ACTIVATED"
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
            field: "organizationCode",
            title: "组织机构代码",
            width: 120
        },{
            field: "businessLicenceCode",
            title: "营业执照号码",
            width: 120
        },{
            field: "taxRegistrationCertificateCode",
            title: "税务登记证",
            width: 120
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
            title: "操作",
            width: 300,
            template: function(data){
            	var url = "";
            	if(detailAuth){
            		url += "<a class='comRow-link detail-link' href='javascript:void(0);'>详情</a>";
            	}
            	if(resetAuth){
            		url += "<a class='comRow-link reset-link' href='javascript:void(0);'>重置登录密码</a>";
            	}
            	if(resetPayPasswordAuth){
            		url += "<a class='comRow-link resetPayPassword-link' href='javascript:void(0);'>重置支付密码</a>";
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
            buttonCount: 5,	//显示页数
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