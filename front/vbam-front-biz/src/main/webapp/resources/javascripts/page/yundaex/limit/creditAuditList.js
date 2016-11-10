$(function(){
	var flag = 0;
	$('body').on('click', 'a[name=reject]', function() {
		flag = 1;
    	$('a[name=confirmBoxOK]').attr('id', $(this).attr('id'));
    	showConfirmBox("确定驳回" + $($(this).parent().parent().children()[1]).text() + "的授信审核吗？");
    });
	$('body').on('click', 'a[name=pass]', function() {
		flag = 2;
		$('a[name=confirmBoxOK]').attr('id', $(this).attr('id'));
		showConfirmBox("确定通过" + $($(this).parent().parent().children()[1]).text() + "的授信审核吗？");
	});
	
	$('body').on('click', 'a[name=confirmBoxOK]', function() {
		if(flag == 1){
			creditAuditReject($(this).attr('id'))
		}
		if(flag == 2){
			creditAuditPass($(this).attr('id'));
		}
    	confirmPopWindow.close();
    });
	
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
	
	$(".creditAuditSearch").click(function() {
		tableSearch();
	});
	
	$(".rejectAll").click(function(){
		$('a[name=confirmBoxOK]').attr('id', 'ALL');
    	showConfirmBox("确定驳回所以的授信审核吗？");
	});
	$(".passAll").click(function(){
		creditAuditPass("ALL");
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
							customerName: $.trim($("#stationName").val()),
							auditState: $.trim($(".war-on").attr("value"))
					},
					url: basepath + "/yundaex/limit/creditAuditList"
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
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "companyName",
            title: "客户名称",
            width: 260,
            attributes: {
                style: "text-align: center"
            }
        },{
			field: "bailRatio",
			title: "保证金账户余额/日资金需求",
			width: 200,
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
            title: "本次评级",
            width: 120,
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
            field: "lastGrade",
            title: "上次评级",
            width: 120,
            template:function(data){
            	var points = kendo.htmlEncode(data.lastPoints);
            	var grade = kendo.htmlEncode(data.lastGrade);
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
            title: "本月额度",
            width: 120,
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
            field: "lastTotalLimit",
            title: "上月额度",
            width: 120,
            template:function(data){
            	var val = kendo.htmlEncode(data.lastTotalLimit);
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
			field: "auditDate",
			title: "审核日期",
			width: 120,
            attributes:{
                style: "text-align:center"
            }
		},{
			field: "creditLimitAuditStateDesc",
			title: "授信状态",
			width: 120,
			template:function(data){
            	var creditLimitAuditStateDesc = kendo.htmlEncode(data.creditLimitAuditStateDesc);
            	var creditLimitAuditState = kendo.htmlEncode(data.creditLimitAuditState);
            	var res = "";
            	if(creditLimitAuditState == "NOTACCESS")
            		res="<span class='monitor-red'>"+creditLimitAuditStateDesc+"</span>";
            	else
            		res="<span>"+creditLimitAuditStateDesc+"</span>"
            	return res;
            },
			attributes: {
                style: "text-align: center"
            }
		},{
            title: "操作",
            width: 180,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	var creditLimitAuditState = kendo.htmlEncode(data.creditLimitAuditState);
                var res = "";
                if( creditLimitAuditState == "WAIT"){
//                	res+='<a class="comRow-link" href="javascript:creditAuditPass(' + data.id +');">通过</a>';
                	res+='<a class="comRow-link" name="pass" href="javascript:void(0);" id =' + data.id +'>通过</a>';
                	res+='<a class="comRow-link" name="reject" href="javascript:void(0);" id =' + data.id +'>驳回</a>';
                }
                return res;
            }
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

function creditAuditPass(id){
	$.ajax({
		url : basepath + "/yundaex/limit/pass",
		data : { id : id },
		type : 'post',
		timeout : 15000,
		dataType : 'json',
		traditional : true,
		success : function(response) {
			if (response.status.toLowerCase() == 'success') {
				showSuccessWin(response.message);
				tableSearch();
			} else if (response.status.toLowerCase() == 'failed') {
				showAlertWin(response.message);
			}
		},
		error : function(response) {
			showAlertWin('网络异常!');
		},
		timeout : function() {
			showAlertWin("请求超时!");
		}
	});
}

function creditAuditReject(id) {
	$.ajax({
		url : basepath + "/yundaex/limit/reject",
		data : { id : id },
		type : 'post',
		timeout : 15000,
		dataType : 'json',
		traditional : true,
		success : function(response) {
			if (response.status.toLowerCase() == 'success') {
				showSuccessWin(response.message);
				tableSearch();
			} else if (response.status.toLowerCase() == 'failed') {
				showAlertWin(response.message);
			}
		},
		error : function(response) {
			showAlertWin('网络异常!');
		},
		timeout : function() {
			showAlertWin("请求超时!");
		}
	});
}
