var pageSize = 10;
var sequence = 0;
function searchLoanInfo() {
	$("#contract-grid").children().remove();
	var grid = $("#contract-grid").kendoGrid({
		selectable: "row",  //设置可选择数据行
		dataSource:{
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						stationName: $("input[name=stationName]").val(),
						comleteStartTime: $("input[name=auditStartTime]").val(),
						comleteEndTime: $("input[name=auditEndTime]").val(),
						contractSignState: getStatus($("input[name=completeStatus]").val())
					},
					url: basepath + "/yundaex/contract/queryList"
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
                field: "financeContractSignStateDesc",
                title: "融资合同",
                width: 120,
                attributes: {
                    style: "text-align: center"
                },
                template: function(data) {
	            	if(data.financeContractSignState == "SIGNED"){
	            		return "已签署";
	            	}else if(data.financeContractSignState == "UNSIGNED"){
	            		return "未签署";
	            	}else{
	            		return "";
	            	}
				}
            },{
                field: "creditContractSignStateDesc",
                title: "用卡授权书",
                width: 120,
                attributes: {
                    style: "text-align: center"
                },
                template: function(data) {
	            	if(data.creditContractSignState == "SIGNED"){
	            		return "已签署";
	            	}else if(data.creditContractSignState == "UNSIGNED"){
	            		return "未签署";
	            	}else{
	            		return "";
	            	}
				}
            },{
                field: "dutyContractSignState",
                title: "连带保证合同",
                width: 120,
                attributes: {
                    style: "text-align: center"
                },
                template: function(data) {
	            	if(data.dutyContractSignState == "SIGNED"){
	            		return "已签署";
	            	}else if(data.dutyContractSignState == "UNSIGNED"){
	            		return "未签署";
	            	}else{
	            		return "";
	            	}
				}
            },{
                field: "completeStateDesc",
                title: "签约完成状态",
                width: 120,
                attributes: {
                    style: "text-align: center"
                },
                template: function(data) {
	            	if(data.completeState == "COMPLETE"){
	            		return "已完成";
	            	}else if(data.completeState == "UNCOMPLETE"){
	            		return "未完成";
	            	}else{
	            		return "";
	            	}
				}
            },
            {
                field: "signCompleteTime",
                title: "签约完成时间",
                width: 120,
                attributes: {
                    style: "text-align: center"
                },
                template: function(data) {
	            	if(data.signCompleteTime != null && data.signCompleteTime != ""){
						return new Date(data.signCompleteTime).format("yyyy-MM-dd hh:mm:ss");
	            	}else{
	            		return "";
	            	}
				}
            },{
                field: "audit",
                title: "操作",
                width: 120,
                attributes: {
                    style: "text-align: center"
                },
                template: function(data){
                    var res = "";
                    if(data.signCompleteTime == null || data.signCompleteTime == ""){
                    	if(resendContractAuth){
                    		var showData = ""
                    			if(data.financeContractSignState == "UNSIGNED")
                    				showData += "FINANCE;";
                    		if(data.creditContractSignState == "UNSIGNED")
                    			showData += "CREDIT;";
                    		if(data.dutyContractSignState == "UNSIGNED")
                    			showData += "DUTY;";
                    		res="<a class='comRow-link item-middle' href='javascript:resendContract("+data.id+");' data-show="+showData+">重发合同</a>";
                    	}
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
	
	/*鼠标移动显示弹出*/
    $("#contract-grid").kendoTooltip({
        filter: "a.item-middle",
        content: function(e){
        	var html = $($("#template-moniteMiddle").html());
        	html.find("a").hide();
        	var dataShow = e.target.attr("data-show");
        	var dataShowArry = dataShow.split(";");
        	for(i in dataShowArry){
        		if(dataShowArry[i] != ""){
        			html.find("#"+dataShowArry[i]).css("display","block");
        		}
        	}
        	return html;
        },
        width: 180,
        position: "bottom"
    });
}

function getStatus(number){
	var status = "";
	switch(number){
	case "1":
		status = "COMPLETE";
		break;
	case "2":
		status = "UNCOMPLETE";
		break;
	default:
		break;
	  
	}
	return status;
}

function resendContract(id){
	$.ajax({
		url:basepath + "/yundaex/contract/resendContract",
		data:{id:id},
		type:'post',
		timeout : 15000,
		dataType:'json',
		traditional:true,
		success : function(response) {
			if (response.status.toLowerCase() == 'success') {
				if(response.totalNum > 0){
					var popContent = "";
					for(i in response.data){
						if(i > 0){
							popContent += "、";
						}
						if(response.data[i] == "ACCOUNT_HOLDER"){
							popContent += "开户人";
						}else if(response.data[i] == "CONTROLLER"){
							popContent += "实际控制人";
						}else{
							popContent += "开户和实际控制是同一人，并且";
						}
					}
					popContent += "身份审核未通过，合同未发送！";
					showAlertWin(popContent);
				}else{

					showSuccessWin(response.message);
				}
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
