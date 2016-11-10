var PAGESIZE = 10;
$(function() {
	//初始化页面
	$("body").on("keydown", function() {
		var evt = window.event || arguments.callee.caller.arguments[0];
		// 键盘回车事件
		if (evt.keyCode == 13) {
			$('.form-search-link').click();
		}
	})
	
	getHistoryInfo();
	//点击"搜索"
	$("#seachBtn").click(function(){
		getHistoryInfo();
	});
	
});
function getHistoryInfo(){
	var sequence = 0;
	$("#monitorList-grid").empty();
    //初始化表格
	 var grid = $("#monitorList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                	type:"post",
                	data: {
                		creditId: $.trim($("#creditId").val()),
                		customerId: $.trim($("#customerId").val()),
                		creditMode: $.trim($(".creditMode select").val()) == "" ? null:$.trim($(".creditMode select").val()),
                		creditOperateType: $.trim($(".creditOperateType select").val()) == "" ? null:$.trim($(".creditOperateType select").val())
					},
					url: basepath + "/asset/credit/search/creditAudit"
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
  					$("#manual-repayGrid .k-grid-header-wrap").css("overflow-x","auto");
  				}else {
  					$("#manual-repayGrid .k-grid-header-wrap").css("overflow-x","");
				}
  				return total;
			}
		}
    },
        columns: [{
            field: "createTime",
            title: "操作日期",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
	        template: function(data) {
	        	if(data.createTime != null && data.createTime != ""){
					return new Date(data.createTime).format("yyyy-MM-dd hh:mm:ss");
	        	}else{
	        		return "";
	        	}
		}
        },{
            field: "bussinessContractNo",
            title: "业务合同号",
            width: 100,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "creditModeDesc",
            title: "额度种类",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "currency",
            title: "币种",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "totalLimitStr",
            title: "申请金额",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	return data.totalLimitStr.formatMoney();
            }
        },{
            field: "usedLimitStr",
            title: "已用额度",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template :function(data){
            	return data.usedLimitStr.formatMoney();
            }
        },{
            field: "availableLimitStr",
            title: "可用额度",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	return data.availableLimitStr.formatMoney();
            }
        },{
            field: "typeDesc",
            title: "状态",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "dueDate",
            title: "到期日期",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            pageSize: 10,
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
};





