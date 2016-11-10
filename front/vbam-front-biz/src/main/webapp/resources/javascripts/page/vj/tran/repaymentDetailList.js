$(function() {
	initSearchButton();
	initRecentTimeButton();
	searchRepaymentDetailList();
	initRepayDetail();
});

function initSearchButton(){
	$('.form-search-link').click(function(){
		searchRepaymentDetailList();
	});
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		searchRepaymentDetailList();
    	}
    });
};

//初始化查询条件“交易日期-最近”按钮
function initRecentTimeButton(){
	$('.war-out.tran-date').click(function(){
		$(this).addClass('war-on');
		var value = $(this).attr("value");
		var startDay = new Date();
		if(value == "oneWeek")
			subtractDays(startDay, 6);
		if(value == "oneMonth"){
			startDay = subtractMonths(startDay, 1);
			addDays(startDay, 1);
		}
		if(value == "threeMonths"){
			startDay = subtractMonths(startDay, 3);
			addDays(startDay, 1);
		}
		$(".startDate").val(startDay.format("yyyy-MM-dd"));
		$(".endDate").val(new Date().format("yyyy-MM-dd"));
	});
};

// 初始化数据表格
function searchRepaymentDetailList(){
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
						vjTranSeq:$.trim($('#vjTranSeq').val()),
						canaTranSeq:$.trim($('#canaTranSeq').val()),
						loanId:$.trim($('#loanId').val()),
						customerName:$.trim($('#customerName').val()),
						identityCardNo:$.trim($('#identityCardNo').val()),
						tranStartDate:$.trim($('.startDate').val()),
						tranEndDate:$.trim($('.endDate').val()),
						tranState:$('.war-on.tran-state').attr('value')
                		},
					type : "POST",
					url : basepath+"/vj/tran/repayment/details/searchList"
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
			field : "customerName",
			title : "客户名称",
			width : 60,
			attributes : {
				style : "text-align:center"
			}
		}, {
			field : "identityCardNo",
			title : "身份证号",
			width : 100,
			attributes : {
				style : "text-align: center"
			}

		}, {
			field : "loanId",
			title : "CANA平台流水号",
			width : 80,
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "canaTranSeq",
			title : "CANA交易流水号",
			width : 90,
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "vjTranSeq",
			title : "VJ交易流水号",
			width : 90,
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "tranTypeDesc",
			title : "交易类型",
			width : 60,
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "amount",
			title : "交易金额",
			width : 80,
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "stateDesc",
			title : "交易状态",
			width : 60,
			attributes : {
				style : "text-align: center"
			}
		},{
            field: "id",
            hidden: true
        }, {
			field : "createTime",
			title : "交易日期",
			width : 100,
			attributes : {
				style : "text-align: center"
			}
		},{
            title: "操作",
            width: 80,
			attributes : {
				style : "text-align: center"
			},
            template: function(data){
            	var url = "";
            	if(detailAuth)
            		url += "<a class='comRow-link detail-link' href='javascript:void(0);' data-repaymentSingleCollectId="+ data.repaymentSingleCollectId + " data-canaTranSeq="+ data.canaTranSeq + ">详情</a>";
            	return url;
            }
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

function initRepayDetail(){
	//还款明细点击事件
	$("body").on("click",".detail-link", function(){
		var repaymentSingleCollectId = $(this).attr("data-repaymentSingleCollectId");
		var canaTranSeq = $(this).attr("data-canaTranSeq");
		$.ajax({
			type: 'post',
			url: basepath + "/vj/tran/repayment/detail",
			data: {
				repaymentSingleCollectId:repaymentSingleCollectId
			},
			success: function(data){
				$("#repayWaitHtml").addClass("hidden");
				var json = data.data;
				if(null == json || json.length==0)
					$("#noRepaymentDetails").removeClass("hidden");
				else{
					$("#haveRepaymentDetails").removeClass("hidden");
					$(json).each(function(){
						var text = "<tr>";
						text += "<td>" + canaTranSeq + "</td>";
						text += "<td>" + this.operatingTime + "</td>";
						text += "<td>" + this.amountDetails + "</td>";
						text += "</tr>";
						$(text).appendTo("#repaymentDetails");
					});
				}
			},
			error: function(data){
				showAlertWin("网络异常："+data.responseText);
			}
		});
	});
	
    //明细弹窗
    new PopWindow(".detail-link", {
        title: "还款明细",
        width: 800,
        reload: true,
        template: "#template-repayDetail"
    }).init();
}