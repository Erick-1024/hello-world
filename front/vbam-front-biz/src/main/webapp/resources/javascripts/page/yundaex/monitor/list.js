$(function() {
	searchMonitorList();
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('.form-search-link').click();
    	}
    });
	
	$('.status-normal').click(function(){
		$(this).addClass('status-chk').removeClass('status-default');
		$(this).siblings().addClass('status-default').removeClass('status-chk');
		searchMonitorList();
	});
	
	//点击"查询"
	$(".form-search-link").click(function(){
		searchMonitorList();
    });
	
	//文件导入
	$("#form-excel").click(function() {
		location.href = encodeURI(basepath + '/yundaex/monitor/monitorImport');
	});
	
	//点击"详情"
	$("body").on("click", ".detail-link", function(e){
		var memberId = $(this).attr('data-memberId');
		var usedLimit = $(this).attr('data-usedLimit');
		var customerName = $(this).attr('data-customerName');
		var shortLoan = $(this).attr('data-shortLoan');
		var bailBalance = $(this).attr('data-bailBalance');
		window.location.href = encodeURI(basepath + "/yundaex/monitor/detail?memberId=" + memberId + "&customerName=" + customerName  + "&usedLimit=" + usedLimit + "&shortLoan=" + shortLoan + "&bailBalance=" + bailBalance);
	});
	
});

//初始化监控数据表格
function searchMonitorList(){
	$("#monitorList-grid").empty();
	$("#monitorList-grid").kendoGrid({
		selectable : "row", // 设置可选择数据行
		sortable : true, // 列排序
		dataSource : {
			pageSize : 10,
			type : "json", // 后台返回的数据类型
			method : "post",
			transport : {
				read : {
					type : "post",
					data: {
						customerName: $.trim($("#customerName").val()),
						creditLimitUsedStatus: $('.status-chk').attr('value')
				},
				url: basepath + "/yundaex/monitor/search/list"
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
					$("#monitorList-grid .k-grid-header-wrap").css("overflow-x","auto");
				}else{
					$("#monitorList-grid .k-grid-header-wrap").css("overflow-x","");
				}
				return total;
				}
			}
		},
		columns : [{
				 	field: "memberId",
		            title: "客户编号",
		            width: 80,
		            attributes: {
	                style: "text-align: center"
		            }
				},{
					field : "customerName",
					title : "客户名称",
					width : 160,
					attributes : {
						style : "text-align: center"
					}
				},{
					field : "usedLimit",
					title : "使用额度",
					width : 100,
					attributes : {
						style : "text-align: center"
					},template: function(data){
						return data.usedLimit.formatMoney();
					}
				},{
					field : "yundaexJudge",
					title : "韵达评价",
					width : 120,
					attributes : {
						style : "text-align: center"
					},
					template: function(data){
						return data.yundaexJudge==null ? '-' : data.yundaexJudge;
					}
				},{
					field : "grade",
					title : "韵达评级",
					width : 120,
					attributes : {
						style : "text-align: center"
					},
					template: function(data){
						return data.grade==null ? '-' : data.grade;
					}
				},{
					field : "recandsendGrowthRate",
					title : "揽派件增长率",
					width : 120,
					attributes : {
						style : "text-align: center"
					},
					template: function(data){
						return data.recandsendGrowthRate==null ? '-' : data.recandsendGrowthRate;
					}
				},{
					field : "bailBalanceDivideDayRequirements",
					title : "保证金账户余额/日资金需求",
					width : 110,
					attributes : {
						style : "text-align: center"
					},
					template: function(data){
						return data.bailBalanceDivideDayRequirements==null ? '-' : data.bailBalanceDivideDayRequirements;
					}
				},{
					title: "操作",
		            width: 100,
		            attributes : {
						style : "text-align: center"
					},
		            template: function(data){
		            	var url = "";
		            	if(detailAuth)
		            		url += "<a class='comRow-link detail-link' href='javascript:void(0);' data-memberId=" + data.memberId + " data-customerName=" + data.customerName+ " data-shortLoan=" + data.shortLoan + " data-usedLimit=" + data.usedLimit +　" data-bailBalance=" + data.bailBalance + ">详情</a>"
		            	return url;
		            },
					editable : "popup"
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