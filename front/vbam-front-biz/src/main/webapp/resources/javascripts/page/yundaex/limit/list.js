$(function(){
	initLimitStatusButton();
	initSearchButton();
	showTable();
})

function initLimitStatusButton(){
	$(".war-out").click(function() {
		$(".war-on").removeClass("war-on");
		$(this).addClass("war-on");
		showTable();
	});
};

function initSearchButton(){
	$('.form-search-link').click(function(){
		showTable();
	});
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		showTable();
    	}
    });
};

function showTable(){
	 //初始化数据表格
	$('#monitorSrl-grid').empty();
    $("#monitorSrl-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
            pageSize: 10,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                    data: { companyName:$.trim($('#companyName').val()),
                    		limitStart:$.trim($('#limitStart').val()),
                    		limitEnd:$.trim($('#limitEnd').val()),
                    		effectiveDateStart:$.trim($('.startDate').val()),
                    		effectiveDateEnd:$.trim($('.endDate').val()),
                    		limitStatus:$('.war-on').attr('value')
                    	  },
                    type : "POST",
                    url: basepath+"/yundaex/limit/customerList"
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
						$("#monitorLimit-grid .k-grid-header-wrap").css("overflow-x","auto");
					}else{
						$("#monitorLimit-grid .k-grid-header-wrap").css("overflow-x","");
					}
					return total;
				}
			}
        },
        columns: [{
            field: "memberId",
            title: "客户编号",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "companyName",
            title: "客户名称",
            width: 200,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "creditMode",
            title: "授信方式",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "totalLimit",
            title: "额度",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "usedLimit",
            title: "已使用额度",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "effectiveDate",
            title: "额度生效日",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "limitStatus",
            title: "额度状态",
            width: 120,
            attributes: {
                style: "text-align: center"
            },template: function(item){
                var val = kendo.htmlEncode(item.limitStatus);
                var res = "";
                if( val == "未激活"){
                    res = "<span class='monitor-red'>"+val+"</span>";
                }else{
                	res = "<span >"+val+"</span>";
                }
                return res;
            }
        },{
            field: "audit",
            title: "操作",
            width: 200,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	var val = kendo.htmlEncode(data.limitStatus);
            	var applyType = kendo.htmlEncode(data.applyType);
                var res = "";
                if( val == "未激活" && applyType == "OFFLINE_APPLY"){
            		res +="<a class='comRow-link contract-more' href='javascript:resendMail("+data.memberId+");'>发送激活邮件</a>";
                }
                return res;
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
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
}


function resendMail(id) {
	$.ajax({
		url : basepath + "/yundaex/limit/resend",
		data : { userId : id },
		type : 'post',
		timeout : 15000,
		dataType : 'json',
		traditional : true,
		success : function(response) {
			if (response.status.toLowerCase() == 'success') {
				showSuccessWin(response.message);
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