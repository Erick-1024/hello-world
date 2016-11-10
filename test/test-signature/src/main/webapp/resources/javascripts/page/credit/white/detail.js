$(function() {
	$('.form-search-link').on('click', function() {
		$("#advancedRule-grid").kendoGrid(option);
	});
	// 初始化数据表格
	var option = {
		selectable : "row", // 设置可选择数据行
		sortable : false, // 列排序
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
					url : "./detail"
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
				total : function(response){
					return response.totalNum;
				}
			}
		},
		columns : [
				{
					field : "tzShortId",
					title : "客户编号",
					width : 80
				},
				{
					field : "customerName",
					title : "客户名称",
					width : 150,
					template: function(data) {
						var names = data.customerName.split('||');
						var html = '';
						if(names[0] && names[0].length > 0){
							html = '<span>' + names[0] + '</span>';
						}
						if(names[1] && names[1].length > 0){
							html = html + '<br/><span style="color:#b3a8a8;">'+names[1];
							if(names[2] && names[2].length > 1){
								html = html + '...';
							}
							html = html + '</span>';
						}
						return html;
		            },
				},
				{
					title : "操作",
					sortable : false,
					width : 100,
					template: function(data) {
						return '<a class="comRow-link advanced-rule" href="javascript:void(0);" data="'+data.customerName+','+data.cooperationPeriod+','+data.purchaseOrderGrowthRate+','+data.overdueRate+','+data.overdueTimes+'">指标详情</a>';
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
	$("#advancedRule-grid").kendoGrid(option);

	// 修改密码弹窗
	var window = new PopWindow(".advanced-rule", {
		title : "指标详情",
		width : 460,
		reload : true,
		template : "#template-advancedRule"
	}).init();
	
	$('body').on('click','.advanced-rule',function(){
		var data = $(this).attr('data').split(',');
		window.data({
			customerName:data[0].split('||')[0],
			cooperationPeriod:data[1]=='null' ? 0 : data[1],
			purchaseOrderGrowthRate:((data[2] == 'null' ? 0 : data[2])*100).toFixed(3)+'%',
			overdueRate : ((data[3] == 'null' ? 0 :data[3])*100).toFixed(3) + '%',
			overdueTimes : data[4] == 'null' ? 0 :data[4],
		});
	});
});