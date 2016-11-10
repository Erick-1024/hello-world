$(function(){
	initAuditStateButton();
	initSubmitButton();
	tableSearch();
})

function initAuditStateButton() {
	$("body").on("click",".status-normal.in-whitelist",function() {
		$(this).addClass("status-chk").removeClass("status-default");
		$(this).siblings().addClass("status-default").removeClass("status-chk");
		if($(this).text()=="白名单"){
			if($("#wait-approve").hasClass("status-chk"))
				$("#all").addClass("status-chk").removeClass("status-default");
			$("a").remove("#wait-approve");
		}
		else if($("#wait-approve").length==0)
			$("#audit").append('<a class="status-normal status-default audit-status" id="wait-approve" href="javascript:void(0);" value="WAIT_APPROVE">待审批</a>');
		tableSearch();
	});
	
	$("body").on("click",".status-normal.audit-status,.status-normal.automatic-status,.status-normal.applicant-type",function() {
		$(this).addClass("status-chk").removeClass("status-default");
		$(this).siblings().addClass("status-default").removeClass("status-chk");
		tableSearch();
	});
}

function initSubmitButton() {
	
	$(".form-search-link").click(function() {
		tableSearch();
	});
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		tableSearch();
    	}
    });
}

function tableSearch() {
	$("#manualChk-grid").empty();
	var grid = $("#manualChk-grid").kendoGrid({
		selectable: "row",  //设置可选择数据行
		sortable: true,  //列排序
		dataSource:{
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
							startDate: $.trim($(".startDate").val()),
							endDate: $.trim($(".endDate").val()),
							customerName: $.trim($("#customerName").val()),
							inWhitelist: $.trim($(".in-whitelist.status-chk").attr("value")),
							applicantType: $.trim($(".applicant-type.status-chk").attr("value")),
							auditState: $.trim($(".audit-status.status-chk").attr("value")),
							automaticState: $.trim($(".automatic-status.status-chk").attr("value"))
					},
					url: basepath + "/credit/audit/searchList"
				}
			},
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			schema: {
				data: "data",
				total: function(data){
					var total = data.totalNum;
					if(total == 0){
						$("#manualChk-grid .k-grid-header-wrap").css("overflow-x","auto");
					}else{
						$("#manualChk-grid .k-grid-header-wrap").css("overflow-x","");
					}
					return total;
				}
			}
		},
		sortable: false,
		groupable: false,
		columns: [{
            field: "canaId",
            title: "客户编号",
            width: 60,
            attributes: {
                style: "text-align: center"
            }
        },{
			field: "applicantTypeDesc",
			title: "客户类型",
			width: 50,
            attributes:{
                style: "text-align:center"
            }
		},{
			field: "inWhitelist",
			title: "客户属性",
			width: 50,
			template: function(data){
				if(data.inWhitelist)
					return "白名单";
				if(!data.inWhitelist)
					return "非白名单";
			},
            attributes:{
                style: "text-align:center"
            }
		},{
            field: "companyName",
            title: "客户名称",
            width: 100
        },{
            field: "outCustomerName",
            title: "真旅客户名称",
            width: 100
        },{
			field: "applyDate",
			title: "申请时间",
			width: 80,
			template: function(data){
				return new Date(data.applyDate).format("yyyy-MM-dd hh:mm:ss");
			},
            attributes:{
                style: "text-align:center"
            }
		},{
			field: "automaticStateDesc",
			title: "准入状态",
			width: 40,
			attributes: {
                style: "text-align: center"
            }
		},{
			field: "auditStateDesc",
			title: "审核状态",
			width: 40,
			attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	if(data.auditStateDesc==null)
            		return "-";
            	else
            		return data.auditStateDesc;
			}
		},{
            field: "auditor",
            title: "审核人",
            width: 50,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	if(data.auditor==null)
            		return "-";
            	else
            		return data.auditor;
			}
        },{
            title: "操作",
            width: 60,
            template: function(data){
            	var url="";
            	if(detailAuth)
            		url+='<a class="comRow-link" href="' + basepath + '/credit/audit/detail?id=' + data.id + '">详情</a>';
            	if(data.auditState == "WAIT") {
            		if(auditAuth)
            			url+='<a class="comRow-link" href="' + basepath + '/credit/audit/audit?id=' + data.id + '">审核</a>';
            	} 
            	if(data.auditState == "WAIT_APPROVE") {
            		if(approveAuth)
            			url+='<a class="comRow-link" href="' + basepath + '/credit/audit/approve?id=' + data.id + '">审批</a>';
            	} 
            	return url;
            },
            attributes: {
                style: "text-align: center"
            }
        },{
        	field: "id",
            title: "id",
            hidden: true
        }],
		pageable: {
			pageSizes: false,  //设置每页显示行数
			pageSize: 10,
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
	
}