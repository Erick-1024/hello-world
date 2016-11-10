$(function(){
	initAuditStateButton();
	initSubmitButton();
	tableSearch();
})

function initAuditStateButton() {
	$(".war-out").click(function() {
		$(".war-on").removeClass("war-on");
		$(this).addClass("war-on");
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
							auditState: $.trim($(".war-on").attr("value"))
					},
					url: basepath + "/yundaex/limit/creditList"
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
			field: "bailRatio",
			title: "保证金账户余额/日资金需求",
			width: 50,
			template:function(data){
            	var val = kendo.htmlEncode(data.bailRatio);
            	val = val == "null"? "0": val;
            	var res = "";
            	if(val<10)
            		res="<span class='monitor-red'>"+val+"%</span>";
            	else
            		res="<span>"+val+"%</span>"
            	return res;
            },
			attributes: {
                style: "text-align: center"
            }
		},{
            field: "grade",
            title: "评级",
            width: 40,
            template:function(data){
            	var points = kendo.htmlEncode(data.points);
            	var grade = kendo.htmlEncode(data.grade);
            	var res = "";
            	if(points<=70)
            		res="<span class='monitor-red'>"+grade+"</span>";
            	else
            		res="<span>"+grade+"</span>"
            	return res;
            },
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "totalLimit",
            title: "额度",
            width: 40,
            template:function(data){
            	var val = kendo.htmlEncode(data.totalLimit);
            	var res = "";
            	if(val.substring(0,1)=="-")
            		res="<span class='monitor-red'>"+val+"</span>";
            	else
            		res="<span>"+val+"</span>"
            	return res;
            },
            attributes: {
                style: "text-align: center"
            }
        },{
			field: "auditTime",
			title: "审核日期",
			width: 80,
            attributes:{
                style: "text-align:center"
            }
		},{
			field: "creditLimitGenerateStateDesc",
			title: "授信状态",
			width: 50,
			template:function(data){
            	var creditLimitGenerateStateDesc = kendo.htmlEncode(data.creditLimitGenerateStateDesc);
            	var creditLimitGenerateState = kendo.htmlEncode(data.creditLimitGenerateState);
            	var res = "";
            	if(creditLimitGenerateState == "UNFINISH")
            		res="<span class='monitor-red'>"+creditLimitGenerateStateDesc+"</span>";
            	else
            		res="<span>"+creditLimitGenerateStateDesc+"</span>"
            	return res;
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
