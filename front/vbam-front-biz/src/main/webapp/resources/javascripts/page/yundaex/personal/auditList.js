var pageSize = 10;
var sequence = 0;
function searchLoanInfo() {
	$("#auditList-grid").children().remove();
	var grid = $("#auditList-grid").kendoGrid({
		selectable: "row",  //设置可选择数据行
		dataSource:{
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						customerName: $("input[name=stationName]").val(),
						auditStartTime: $("input[name=auditStartTime]").val(),
						auditEndTime: $("input[name=auditEndTime]").val(),
						auditStatus: getStatus($("input[name=auditStatus]").val())
					},
					url: basepath + "/yundaex/personal/queryList"
				}
			},
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			schema: {
				data: "data",
				total: function(data) {
					var total = data.total;
	  				if(total == 0){
	  					$("#manual-repayGrid .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#manual-repayGrid .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
			}
			},
			columns: [{
	            field: "sequence",
	            title: "序号",
	            width: 80,
	            attributes:{
	                style:"text-align:center"
	            },
	            template : function(data){
					sequence ++;
					return sequence;
				},
	        },{
	            field: "stationName",
	            title: "网点客户名称",
	            width: 200,
	            attributes: {
	                style: "text-align: center"
	            }
	        },{
	            field: "controller",
	            title: "实际控制人",
	            width: 100,
	            attributes: {
	                style: "text-align: center"
	            },
	            template: function(data) {
	            	if(data.type != "ACCOUNT_HOLDER"){
						return data.realName;
	            	}
	            	return "-";
				}
	        },{
	            field: "accountHolder",
	            title: "开户人",
	            width: 100,
	            attributes: {
	                style: "text-align: center"
	            },
	            template: function(data) {
	            	if(data.type != "CONTROLLER"){
						return data.realName;
	            	}
	            	return "-";
				}
	        },{
	            field: "isCommitInfo",
	            title: "是否提交身份证明",
	            width: 100,
	            attributes: {
	                style: "text-align: center"
	            },
	            template: function(data) {
	            	if(data.residentIdentityCardNo != null && data.residentIdentityCardNo != ""){
						return "是";
	            	}else{
	            		return "否";
	            	}
				}
	        },{
	            field: "auditApplyTime",
	            title: "提交审核时间",
	            width: 120,
	            attributes: {
	                style: "text-align: center"
	            },
	            template: function(data) {
	            	if(data.auditApplyTime != null && data.auditApplyTime != ""){
						return new Date(data.auditApplyTime).format("yyyy-MM-dd hh:mm:ss");
	            	}else{
	            		return "";
	            	}
				}
	        },{
	            field: "auditorName",
	            title: "审核人",
	            width: 100,
	            attributes: {
	                style: "text-align: center"
	            }
	        },{
	            field: "auditStateDesc",
	            title: "审核状态",
	            width: 100,
	            attributes: {
	                style: "text-align: center"
	            }
	        },{
	            field: "audit",
	            title: "操作",
	            width: 200,
	            attributes: {
	                style: "text-align: center"
	            },
	            template: function(data){
	                var res = "";
	                if(detailAuth)
	                	res +="<a class='comRow-link contract-more' href='javascript:detail("+data.id+");'>详情</a>";
	                
	                if(data.auditState == null || data.auditState =="" || data.auditState == "PENDINGAUDIT" || data.auditState == "NOTPASSED"){
	                	if(data.auditState == "PENDINGAUDIT" && auditAuth)
	                		res +="<a class='comRow-link contract-more' href='javascript:audit("+data.id+");'>审核</a>";
                		if(resendMailAuth)
                			res +="<a class='comRow-link contract-more' href='javascript:resendMail("+data.id+");'>重发邮件</a>";
	                }
	                return res;
	            }
	        }],
	        pageable: {
	            pageSizes: false, //设置每页显示行数
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
	grid.data("kendoGrid").pager.bind('change', function(){
		sequence = 0;
	});
}

function getStatus(number){
	var status = "";
	switch(number){
	case "1":
		status = "PENDINGAUDIT";
		break;
	case "2":
		status = "PASSED";
		break;
	case "3":
		status = "NOTPASSED";
		break;
	default:
		break;
	  
	}
	return status;
}

function detail(id){
	location.href=basepath+"/yundaex/personal/gotoDetailPage?id="+id;
}
function audit(id){
	location.href=basepath+"/yundaex/personal/gotoAuditPage?id="+id;
}
function resendMail(id){
	$.ajax({
		url:basepath + "/yundaex/personal/resend",
		data:{id:id},
		type:'post',
		timeout : 15000,
		dataType:'json',
		traditional:true,
		success : function(response) {
			if (response.status.toLowerCase() == 'success') {
				showSuccessWin(response.message);
			} else if (response.status.toLowerCase() == 'failed') {
				showAlertWin(response.message);
			}
		},
		error : function(response){
			showAlertWin('网络异常!');
		},
		timeout : function(){
			showAlertWin("请求超时!");
		}
	});
}
$(function(){
	searchLoanInfo();
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('.form-search-link').click();
    	}
    });
	
    $(".form-search-link").click(function(){
    	searchLoanInfo();
    });
});
