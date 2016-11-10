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
	
	//点击"详情"
	$("body").on("click", ".detail-link", function(e){
		var memberId = $(this).attr('data-memberId');
		var outCustomerId = $(this).attr('data-outCustomerId');
		var customerName = $(this).attr('data-customerName');
		var outCustomerName = $(this).attr('data-outCustomerName');
		var usedLimit = $(this).attr('data-usedLimit');
		var pledgeRage = $(this).attr('data-pledgeRage');
		window.open(encodeURI(basepath + "/report/monitor/detail?memberId=" + memberId + "&outCustomerId=" + outCustomerId + "&customerName=" + customerName + "&outCustomerName=" + outCustomerName + "&usedLimit=" + usedLimit + "&pledgeRage=" + pledgeRage));
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
				url: basepath + "/report/monitor/search/list"
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
					field : "customerName",
					title : "客户名称",
					width : 200,
					attributes : {
						style : "text-align: center"
					}
				},{
					field : "outCustomerName",
					title : "外部客户名称",
					width : 200,
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
					field : "pledgeRage",
					title : "质押率",
					width : 150,
					attributes : {
						style : "text-align: center"
					},
					template: function(data){
						return data.pledgeRage==null ? '-' : data.pledgeRage;
					}
				},{
					field : "counterGuaranteeRate",
					title : "质押反担保覆盖率",
					width : 150,
					attributes : {
						style : "text-align: center"
					},
					template: function(data){
						return data.counterGuaranteeRate==null ? '-' : data.counterGuaranteeRate;
					}
				},{
					field : "salesChangeRate",
					title : "销售变化率",
					width : 150,
					attributes : {
						style : "text-align: center"
					},
					template: function(data){
						return data.salesChangeRate==null ? '-' : data.salesChangeRate;
					}
				},{
					field : "salesRepaymentRate",
					title : "销售回款率",
					width : 110,
					attributes : {
						style : "text-align: center"
					},
					template: function(data){
						return data.salesRepaymentRate==null ? '-' : data.salesRepaymentRate;
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
		            		url += "<a class='comRow-link detail-link' href='javascript:void(0);' data-memberId=" + data.memberId + " data-outCustomerId=" + data.outCustomerId + " data-customerName=" + data.customerName + " data-outCustomerName=" + data.outCustomerName + " data-usedLimit=" + data.usedLimit + " data-pledgeRage=" + data.pledgeRage + ">详情</a>";
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