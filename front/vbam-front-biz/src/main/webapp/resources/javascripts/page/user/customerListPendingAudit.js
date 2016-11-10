var PAGESIZE = 10;
var sequence = 0;
var id;
$(function(){
    //初始化数据表格
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
    
    $("body").on("click", ".review-link", function(e){
    	id = $(this).parent().next().text();
    	window.open(encodeURI(basepath + "/customer/gotoReview?customerId=" + id + "&curSubMenu=" + curSubMenu));
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
						accountAuditStatus: "PENDINGAUDIT"
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
			template: function(data){
				return new Date(data.createTime).format("yyyy-MM-dd hh:mm:ss");
			},
			attributes: {
                style: "text-align: center"
            }
        },{
            field: "accountAuditStatus",
            title: "审核状态",
            width: 70,
			template: function(data){
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
            title: "操作",
            width: 250,
            template: function(data){
            	var url = "";
            	if(detailAuth){
            		url += "<a class='comRow-link detail-link' href='javascript:void(0);'>详情</a>";
            	}
        		if(reviewAuth){
        			url += "<a class='comRow-link review-link' href='javascript:void(0);'>审核</a>";
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