$(function(){
	initAuditStateButton();
	initSubmitButton();
	tableSearch();
})

function initAuditStateButton() {
	$("body").on("click",".war-out",function() {
		$(this).addClass("war-on");
		$(this).siblings().removeClass("war-on");
//		$(".war-on").removeClass("war-on");
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
	$("#monitorSrl-grid").empty();
	var grid = $("#monitorSrl-grid").kendoGrid({
		selectable: "row",  //设置可选择数据行
		sortable: true,  //列排序
		dataSource:{
			type: "json", //后台返回的数据类型
			method: "post",
			transport: {
				read: {
					type: "post",
					data: {
							startDate: $.trim($(".startDate").val()),
							endDate: $.trim($(".endDate").val()),
							customerName: $.trim($("#stationName").val()),
							auditState: $.trim($(".war-out.war-on").attr("value"))
					},
					url: basepath + "/yundaex/audit/searchList"
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
						$("#monitorSrl-grid .k-grid-header-wrap").css("overflow-x","auto");
					}else{
						$("#monitorSrl-grid .k-grid-header-wrap").css("overflow-x","");
					}
					return total;
				}
			}
		},
		sortable: false,
		groupable: false,
		columns: [{
            field: "id",
            title: "客户编号",
            width: 60,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "companyName",
            title: "网点客户名称",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
			field: "applyDate",
			title: "申请审核时间",
			width: 80,
			template: function(data){
				return new Date(data.applyDate).format("yyyy-MM-dd hh:mm:ss");
			},
            attributes:{
                style: "text-align:center"
            }
		},{
			field: "auditStateDesc",
			title: "审核状态",
			width: 50,
			attributes: {
                style: "text-align: center"
            }
		},{
            field: "auditor",
            title: "审核人",
            width: 40,
            attributes: {
                style: "text-align: center"
            }
        },{
            title: "操作",
            width: 60,
            template: function(data){
            	var url="";
            	var auditState = kendo.htmlEncode(data.auditState);
            	if(auditState == "WAIT"){
            		if(detailAuth)
                		url+='<a class="comRow-link" href="' + basepath + '/yundaex/audit/detail?id=' + data.id +'">详情</a>';
                	if(auditAuth)
                		url+='<a class="comRow-link" href="' + basepath + '/yundaex/audit/audit?id=' + data.id +'">审核</a>';
            	}else{
            		if(detailAuth)
            			url+='<a class="comRow-link" href="' + basepath + '/yundaex/audit/detail?id=' + data.id + '">详情</a>';
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
