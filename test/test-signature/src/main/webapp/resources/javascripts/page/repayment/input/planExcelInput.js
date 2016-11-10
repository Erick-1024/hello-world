var pageSize = 10;

$(function() {
	checkPassRepaymentPlan();
	checkNegativeRepaymentPlan();
	getPageCountDTO();
	// 点击导入按钮
	$("body").delegate(".confirm-link", "click", function() {
		uploadSubmit("importRepaymentPlanExcel", "uploadFileId", function(data){
			if(data == "success"){
				$("#importFlag").text("Excel导入成功");
				getPageCountDTO();
				checkPassRepaymentPlan();
				checkNegativeRepaymentPlan();
			} else {
				$("#importFlag").text("Excel导入失败，请重新导入");
			}
		}, 
		function(data){$("#importFlag").text(data.responseText);});
	});
	
	$(".fileBox").on("change",function(){
		$("#importFlag").text($('#uploadFileId').val());
	});

	$(".repayment-navlink").click(
		function(event) {
			var obj = $(event.target).parent("li");
			if (!obj.hasClass("repayment-nav-active")) {
				obj.addClass("repayment-nav-active").siblings()
						.removeClass("repayment-nav-active");
			}
			if (obj.is(".repayment-nav ul li:first")) {
				$("#repayment-plan").removeClass("hidden");
				$("#repayment-cost").addClass("hidden");
				getPageCountDTO();
				checkPassRepaymentPlan();
				checkNegativeRepaymentPlan();
			} else if (obj.is(".repayment-nav ul li:last")) {
				$("#repayment-plan").addClass("hidden");
				$("#repayment-cost").removeClass("hidden");
				getPageCountDTO();
				checkPassRepaymentFee();
				checkNegativeRepaymentFee();
			}
			//检查提交按钮位置
            chkfix();
	});
	
	// 点击确定保存数据
	$("body").delegate(".repayment-entry", "click", function() {
		$.ajax({
			type: "POST",
			url: basepath + "/repayment/plan/input/getNumByRedisKey",
			data: {redisKey : $.trim($("input[name='redisKey']").val())},
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
					if( Number(data.data.totalCorrectNumForPlan) == 0 && Number(data.data.totalCorrectNumForExpense) == 0){
						showAlertWin("未添加任何数据，保存失败");
					}else{
						$("#saveInfo").submit();
					}
				}
			}
		});
	});
	
    $(".main-header a,.redirect").click(function(e){
    	e.preventDefault();
    	var url = $(this).attr("href");
    	$.ajax({
			type: "POST",
			url: basepath + "/repayment/plan/input/getNumByRedisKey",
			data: {redisKey : $.trim($("input[name='redisKey']").val())},
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
					if( Number(data.data.totalCorrectNumForPlan) != 0 || Number(data.data.totalCorrectNumForExpense) != 0){
						$(".template-leave").click();
						$("body").on("click", "#comfirmLeave",function(){
							window.location.href = url;
						});
					}else{
						window.location.href = url;
					}
				}
			}
		});
    });
	
    //确认离开当前页面提示弹窗
    new PopWindow(".template-leave", {
        title: "确认离开",
        width: 460,
        reload: true,
        template: "#template-leave"
    }).init();
});

//获取数据总数量
function getPageCountDTO(){
	$.ajax({
		type: "POST",
		url: basepath + "/repayment/plan/input/getNumByRedisKey",
		data: {redisKey : $.trim($("#uploadFileId").data("key")),dataType : 0, page:1, pageSize:10},
		dataType: "json",
		success: function(data){
			if(data.status == "SUCCESS"){
					$("span[name='totalNumForPlan']").text(data.data.totalNumForPlan == null ? "":data.data.totalNumForPlan);
					$("span[name='totalCorrectNumForPlan']").text(data.data.totalCorrectNumForPlan == null ? "":data.data.totalCorrectNumForPlan);
					$("span[name='totalIncorrectForPlan']").text(data.data.totalIncorrectNumForPlan == null ? "":data.data.totalIncorrectNumForPlan);
					$("span[name='totalNumForExpense']").text(data.data.totalNumForExpense == null ? "":data.data.totalNumForExpense);
					$("span[name='totalCorrectNumForExpense']").text(data.data.totalCorrectNumForExpense == null ? "":data.data.totalCorrectNumForExpense);
					$("span[name='totalIncorrectForExpense']").text(data.data.totalIncorrectNumForExpense == null ? "":data.data.totalIncorrectNumForExpense);
			}
		}
	});	
}

function chkfix(){
    var clientHeight = $(window).height();
    var offsetTop = $("#foot-fixBar").offset().top;

    if(offsetTop >= clientHeight){
        $("#foot-fixBar").addClass("foot-fixBar");
    }else{
        $("#foot-fixBar").removeClass("foot-fixBar");
    }
}

//检查通过还款信息
function checkPassRepaymentPlan(){
	$("#finance-passRepay").remove();
	$("#finance-passRepayWrap").append("<div class='finance-grid' id='finance-passRepay'></div>");
	$("#finance-passRepay").kendoGrid({
		selectable : "row", // 设置可选择数据行
		sortable : true, // 列排序
		dataSource : {
			type : "json", // 后台返回的数据类型
			method : "post",
			transport : {
				read : {
					data : {
						redisKey : $.trim($("#uploadFileId").data("key")),
						dataType : 1
					},
					type : "POST",
					url : basepath + "/repayment/plan/input/getPlanByRedisKey"
				}
			},
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			// 解析远程响应的数据
			schema : {
				data: "data",
				total: function(data) {
					var total = data.totalNum;
	  				if(total == 0){
	  					$("#finance-passRepayWrap .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#finance-passRepayWrap .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
			}
		},
		columns : [ {
			field : "loanNo",
			title : "放款编号",
			width : 100
		}, {
			field : "financeCompany",
			title : "融资客户",
			width : 300
		}, {
			field : "coreCompanyName",
			title : "核心企业",
			width : 300
		}, {
			field : "financeAmount",
			title : "融资金额",
			width : 120,
			attributes : {
				style : "text-align: right"
			},
            template: function(data){
            	var money = data.financeAmount + "";
            	return money.formatMoney();	
            }
		}, {
			field : "financeBalance",
			title : "融资余额",
			width : 120,
			attributes : {
				style : "text-align: right"
			},
            template: function(data){
            	var money = data.financeBalance + "";
            	return money.formatMoney();	
            }
		}, {
			field : "loanDate",
			title : "放款日",
			width : 150,
            attributes: {
                style: "text-align: center"
            }
		}, {
			field : "dueDate",
			title : "到期日",
			width : 150,
            attributes: {
                style: "text-align: center"
            }
		}, {
			field : "repaymentPeriod",
			title : "期数",
			width : 50,
            attributes: {
                style: "text-align: center"
            }
		}, {
			field : "valueDate",
			title : "收益计算日",
			width : 150,
            attributes: {
                style: "text-align: center"
            }
		}, {
			field : "settleInterestDate",
			title : "收益分配日",
			width : 150,
            attributes: {
                style: "text-align: center"
            }
		}, {
			field : "repaymentDate",
			title : "还款日",
			width : 150,
            attributes: {
                style: "text-align: center"
            }
		}, {
			field : "accountRepaymentPrincipal",
			title : "应还本金",
			width : 120,
			attributes : {
				style : "text-align: right"
			},
            template: function(data){
            	var money = data.accountRepaymentPrincipal + "";
            	return money.formatMoney();	
            }
		}, {
			field : "accountRepaymentInterest",
			title : "应还收益",
			width : 120,
			attributes : {
				style : "text-align: right"
			},
            template: function(data){
            	var money = data.accountRepaymentInterest + "";
            	return money.formatMoney();	
            }
		}, {
			field : "accountRepaymentServiceCharge",
			title : "应还服务费",
			width : 120,
			attributes : {
				style : "text-align: right"
			},
            template: function(data){
            	var money = data.accountRepaymentServiceCharge + "";
            	return money.formatMoney();	
            }
		}, {
			field : "accountRepaymentTotal",
			title : "应还总金额",
			width : 150,
			attributes : {
				style : "text-align: right"
			},
            template: function(data){
            	var money = data.accountRepaymentTotal + "";
            	return money.formatMoney();	
            }
		}, {
			field : "settleStatus",
			title : "结清状态",
			width : 120,
            attributes: {
                style: "text-align: center"
            }
		} ],
		pageable : {
			pageSizes : false, // 设置每页显示行数
			pageSize : pageSize, 
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

//通过未还款信息
function checkNegativeRepaymentPlan(){
	$("#finance-unpassRepay").remove();
	$("#finance-unpassRepayWrap").append("<div class='finance-grid' id='finance-unpassRepay'></div>");
	$("#finance-unpassRepay").kendoGrid({
		selectable : "row", // 设置可选择数据行
		sortable : false, // 列排序
		dataSource : {
			type : "json", // 后台返回的数据类型
			method : "post",
			transport : {
				read : {
					data : {
						redisKey : $.trim($("#uploadFileId").data("key")),
						dataType : 2
					},
					type : "POST",
					url : basepath + "/repayment/plan/input/getPlanByRedisKey"
				}
			},
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			// 解析远程响应的数据
			schema : {
				data: "data",
				total: function(data) {
					var total = data.totalNum;
	  				if(total == 0){
	  					$("#finance-unpassRepayWrap .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#finance-unpassRepayWrap .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
			}
		},
		columns : [{
	                field: "loanNo",
	                title: "放款编号",
	                width: 100
	            },{
	                field: "financeCompany",
	                title: "融资客户",
	                width: 300
	            }, {
	    			field : "coreCompanyName",
	    			title : "核心企业",
	    			width : 300
	    		},{
	                field: "financeAmount",
	                title: "融资金额",
	                width: 120,
	                attributes: {
	                    style: "text-align: right"
	                }
	            },{
	                field: "financeBalance",
	                title: "融资余额",
	                width: 120,
	                attributes: {
	                    style: "text-align: right"
	                }
	            },{
	                field: "loanDate",
	                title: "放款日",
	                width: 150
	            },{
	                field: "dueDate",
	                title: "到期日",
	                width: 150
	            },{
	                field: "repaymentPeriod",
	                title: "期数",
	                width: 50
	            },{
	                field: "valueDate",
	                title: "收益计算日",
	                width: 150
	            },{
	                field: "settleInterestDate",
	                title: "收益分配日",
	                width: 150
	            },{
	                field: "repaymentDate",
	                title: "还款日",
	                width: 150
	            },{
	                field: "accountRepaymentPrincipal",
	                title: "应还本金",
	                width: 120,
	                attributes: {
	                    style: "text-align: right"
	                }
	            },{
	                field: "accountRepaymentInterest",
	                title: "应还收益",
	                width: 120,
	                attributes: {
	                    style: "text-align: right"
	                }
	            },{
	                field: "accountRepaymentServiceCharge",
	                title: "应还服务费",
	                width: 120,
	                attributes: {
	                    style: "text-align: right"
	                }
	            },{
	                field: "accountRepaymentTotal",
	                title: "应还总金额",
	                width: 150,
	                attributes: {
	                    style: "text-align: right"
	                }
	            },{
	                field: "settleStatus",
	                title: "结清状态",
	                width: 120
	            },{
	                field: "verifyFailReason",
	                title: "检验未通过原因",
	                width: 200
	            }],
		pageable : {
			pageSizes : false, // 设置每页显示行数
			pageSize : pageSize, 
			page: 1,
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

//通过还款费用
function checkPassRepaymentFee(){
	$("#finance-passCost").remove();
	$("#finance-passCostWrap").append("<div class='finance-grid' id='finance-passCost'></div>");
	$("#finance-passCost").kendoGrid({
		selectable : "row", // 设置可选择数据行
		sortable : false, // 列排序
		dataSource : {
			pageSize : 10,
			type : "json", // 后台返回的数据类型
			method : "post",
			transport : {
				read : {
					data : {
						redisKey : $.trim($("#uploadFileId").data("key")),
						dataType : 1
					},
					type : "POST",
					url : basepath + "/repayment/plan/input/getExpenseByRedisKey"
				}
			},
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			// 解析远程响应的数据
			schema : {
				data: "data",
				total: function(data) {
					var total = data.totalNum;
	  				if(total == 0){
	  					$("#finance-passCostWrap .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#finance-passCostWrap .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
			}
		},
		columns : [ {
			field : "loanNo",
			title : "放款编号",
			width : 100
		}, {
			field : "financeCompany",
			title : "融资客户",
			width : 300
		}, {
			field : "coreCompanyName",
			title : "核心企业",
			width : 300
		}, {
			field : "expenseSubject",
			title : "费用明目",
			width : 120
		}, {
			field : "repaymentDate",
			title : "还款日",
			width : 150,
            attributes: {
                style: "text-align: center"
            }
		}, {
			field : "repaymentAmount",
			title : "应还金额",
			width : 120,
			attributes : {
				style : "text-align: right"
			},
            template: function(data){
            	var money = data.repaymentAmount + "";
            	return money.formatMoney();	
            }
		}, {
			field : "settleStatus",
			title : "结清状态",
			width : 120,
            attributes: {
                style: "text-align: center"
            }
		} ],
		pageable : {
			pageSizes : false, // 设置每页显示行数
			pageSize : pageSize, 
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

//未通过还款费用
function checkNegativeRepaymentFee(){
	$("#finance-unpassCost").remove();
	$("#finance-unpassCostWrap").append("<div class='finance-grid' id='finance-unpassCost'></div>");
	$("#finance-unpassCost").kendoGrid({
		selectable : "row", // 设置可选择数据行
		sortable : false, // 列排序
		dataSource : {
			pageSize : 10,
			type : "json", // 后台返回的数据类型
			method : "post",
			transport : {
				read : {
					data : {
						redisKey : $.trim($("#uploadFileId").data("key")),
						dataType : 2
					},
					type : "POST",
					url : basepath + "/repayment/plan/input/getExpenseByRedisKey"
				}
			},
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			// 解析远程响应的数据
			schema : {
				data: "data",
				total: function(data) {
					var total = data.totalNum;
	  				if(total == 0){
	  					$("#finance-unpassCostWrap .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#finance-unpassCostWrap .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
			}
		},
		columns : [ {
			field : "loanNo",
			title : "放款编号",
			width : 100
		}, {
			field : "financeCompany",
			title : "融资客户",
			width : 300
		}, {
			field : "coreCompanyName",
			title : "核心企业",
			width : 300
		}, {
			field : "expenseSubject",
			title : "费用明目",
			width : 120
		}, {
			field : "repaymentDate",
			title : "还款日",
			width : 150
		}, {
			field : "repaymentAmount",
			title : "应还金额",
			width : 120,
			attributes : {
				style : "text-align: right"
			}
		}, {
			field : "settleStatus",
			title : "结清状态",
			width : 120
		}, {
			field : "verifyFailReason",
			title : "检验未通过原因",
			width : 200
		} ],
		pageable : {
			pageSizes : false, // 设置每页显示行数
			pageSize : pageSize, 
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

function ajaxFileUpload(url, uploadFileId, key, success, error) {
	$.ajaxFileUpload({
	    url: url,
	    type: 'post',
	    data: {redisKey : key},
	    secureuri: false, // 一般设置为false
	    fileElementId: uploadFileId, // 上传文件的id、name属性名
	    dataType: 'text', // 返回值类型，一般设置为json、application/json
	    success:success,
	    error:error
	});
};

function uploadSubmit(url,uploadFileId,success,error){
	var file = $('#uploadFileId').val();
	var key = $("#uploadFileId").data("key");
	if(key == ""){
		$("#importResult").text("导入失败，请刷新页面！");
		return;
	}
	// 检查是否已选择上传文件
	if (file != ''){
		var filename = file.replace(/.*(\/|\\)/, '');
		var fileext = (/[.]/.exec(filename)) ? /[^.]+$/.exec(filename.toLowerCase()) : '';
		// 检查文件格式
		if (fileext == 'xlsx' || fileext == 'xls'){
			// 上传excel文件
			ajaxFileUpload(url, uploadFileId, key, success, error);
		} else {
			$("#importResult").text("文件格式必须是*.xls或*.xlsx");
		}
	} else {
		$("#importResult").text("请选择excel文件！");
	}
}

window.onload = function(){
    var clientHeight = 0;
    var realHeight = 0;
    //判断浏览器是否为IE
    var userAgent = navigator.userAgent;
    if(userAgent.indexOf("Firefox") > -1){
        clientHeight = $(window).height();
        realHeight = $(document).height();
    }else{
        clientHeight = document.body.clientHeight;
        realHeight = window.screen.availHeight;
    }
    if((realHeight-clientHeight) >= 64){
        $("#foot-fixBar").addClass("foot-fixBar");
    }else{
        $("#foot-fixBar").removeClass("foot-fixBar");
    }
    chkfix();
    $(window).scroll(function(){
        var scrollTop = $(window).scrollTop();
        var distanceTop = $('#footTop').offset().top - $(window).height();
        if(scrollTop >= distanceTop){
            $("#foot-fixBar").removeClass("foot-fixBar");
        }else{
            $("#foot-fixBar").addClass("foot-fixBar");
        }
    });
};