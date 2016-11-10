$(function() {
	initSearchButton();
	searchLimitList();
});

function initSearchButton(){
	$('.form-search-link').click(function(){
		searchLimitList();
	});
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		searchLimitList();
    	}
    });
};

// 初始化数据表格
function searchLimitList(){
	$('#monitorSrl-grid').empty();
	$("#monitorSrl-grid").kendoGrid({
		selectable : "row", // 设置可选择数据行
		sortable : true, // 列排序
		dataSource : {
			pageSize : 10,
			type : "json", // 后台返回的数据类型
			method : "post",
			transport : {
				read : {
					data : {
								customerName:$.trim($('#customerName').val()),
								identityCardNo:$.trim($('#identityCardNo').val()),
								limitMin:$.trim($('#limitMin').val()),
								limitMax:$.trim($('#limitMax').val()),
								effectiveDateStart:$.trim($('.startDate').val()),
								effectiveDateEnd:$.trim($('.endDate').val()),
								limitStatus:$('.war-on').attr('value')
                		},
					type : "POST",
					url : basepath+"/vj/limit/searchList"
				}
			},
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			schema : {
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
		columns : [ {
			field : "memberId",
			title : "客户编号",
			width : 80,
			attributes : {
				style : "text-align:center"
			}
		}, {
			field : "customerName",
			title : "客户名称",
			width : 80,
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "mobileNo",
			title : "手机号码",
			width : 80,
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "identityCardNo",
			title : "身份证号",
			width : 120,
			attributes : {
				style : "text-align: center"
			}

		}, {
			field : "creditModeDesc",
			title : "授信方式",
			width : 80,
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "totalLimitStr",
			title : "额度",
			width : 80,
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "usedLimitStr",
			title : "已使用额度",
			width : 80,
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "effectiveDateStr",
			title : "额度生效日",
			width : 80,
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "limitStatusDesc",
			title : "额度状态",
			width : 80,
			attributes : {
				style : "text-align: center"
			}
		},{
            field: "limitId",
            hidden: true
        }],
		pageable : {
			pageSizes : false, // 设置每页显示行数
			buttonCount : 5, // 显示页数
			messages : {
				display : "共<span class='sumData'>{2}</span>条数据",
				empty : "没有数据",
				page : "页",
				of : "/ {0}",
				itemsPerPage : "条/页",
				first : "第一页",
				previous : "前一页",
				next : "下一页",
				last : "最后一页"
			}
		}
	});
}