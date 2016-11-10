$(function() {
	$('.form-search-link').on('click', function() {
		$("#advancedList-grid").kendoGrid(option);
	});
	// 初始化数据表格
	var option = {
		selectable : "row", // 设置可选择数据行
		sortable : true, // 列排序
		dataSource : {
			pageSize : 10,
			type : "json", // 后台返回的数据类型
			method : "post",
			transport : {
				read : {
					data : function() {
						var array = $('.advanced-form').serializeArray();
						var params = {}
						for ( var index in array) {
							params[array[index].name] = array[index].value;
						}
						return params;
					},
					type : "POST",
					url : "./list"
				}
			},
		// 解析远程响应的数据
		serverPaging: true,
		serverSorting: true,
		serverFiltering: true,
		schema : {
			data : function(response) {
				return response.data;
				},
			total : function(response) {
				return response.totalNum;
				}
			}
		},
		columns : [
				{
					field : "batchNo",
					title : "批次编号",
					width : 80
				},
				{
					field : "whiteCustomerNumber",
					title : "客户数量",
					width : 150
				},
				{
					field : "enable",
					title : "批次状态",
					width : 200,
					template : function(data) {
						if(data.enable)return '启用';
						return '关闭';
					}
				},
				{
					field : "createTime",
					title : "创建时间",
					width : 150
				},
				{
					title : "操作",
					sortable : false,
					width : 100,
					template: function(data) {
						return '<a class="comRow-link advanced-detail" href="./detail?batchNo='+data.batchNo+'">详情</a>' +
							   '<a class="comRow-link advanced-role" href="javascript:void(0);" data="'+data.batchNo+','+data.cooperationPeriod+','+data.purchaseOrderGrowthRate+','+data.overdueRate+','+data.overdueTimes+'">规则</a>';
		            },
					editable : "popup"
				} ],
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
	};
	$("#advancedList-grid").kendoGrid(option);

	var window = new PopWindow(".advanced-role", {
		title : "系统规则",
		width : 460,
		reload : true,
		template : "#template-advancedRole"
	}).init();
	
	$('body').on('click','.advanced-role',function(){
		var data = $(this).attr('data').split(',');
		window.data({
			batchNo:data[0],
			cooperationPeriod:data[1] == 'null' ? 0 : data[1],
			purchaseOrderGrowthRate:((data[2] == 'null' ? 0 : data[2]) * 100).toFixed(3) + '%',
			overdueRate : ((data[3] == 'null' ? 0 : data[3]) * 100).toFixed(3) + '%',
			overdueTimes : data[4] == 'null' ? 0 : data[4]
		});
	});
});