$(function() {
	var option = {
		index : 0,
		selectable : "row", // 设置可选择数据行
		dataSource : {
			pageSize : 10,
			type : "json", // 后台返回的数据类型
			method : "post",
			transport : {
				read : {
					data : function() {
						return getParam();
					},
					type : "POST",
					url : "./fundDailyCountReport"
				}
			},
			// 解析远程响应的数据
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			schema : {
				data : function(response) {
					option.index = 0;
					return response.data;
					},
				total : function(response) {
					return response.totalNum;
					}
			}
		},
		columns : [ {
			title : "序号",
			width : 50,
			template : function(data){
				return ++ option.index;
			},
			attributes : {
				style : "text-align: center"
			}
		}, {
			field : "reportDate",
			title : "日期",
			width : 120,
			attributes : {
				style : "text-align: center"
			}
		},{
			title : "入金笔数",
			width : 120,
			template : function(data){
				var f = (data.depositCount + '').formatMoney();
				return f.substring(0,f.length -3);
			},
			attributes : {
				style : "text-align: center"
			}
		},
		{
			title : "转账转入笔数",
			width : 120,
			template : function(data){
				var f = (data.transferInCount + '').formatMoney();
				return f.substring(0,f.length -3);
			},
			attributes : {
				style : "text-align: center"
			}
		}, {
			title : "转账转出笔数",
			width : 100,
			template : function(data){
				var f = (data.transferOutCount + '').formatMoney();
				return f.substring(0,f.length -3);
			},
			attributes : {
				style : "text-align: center"
			}
		}, {
			title : "提现笔数",
			width : 120,
			template : function(data){
				var f = (data.withdrawCount + '').formatMoney();
				return f.substring(0,f.length -3);
			},
			attributes : {
				style : "text-align: center"
			}
		}, {
			title : "退款笔数",
			width : 120,
			template : function(data){
				var f = (data.refundCount + '').formatMoney();
				return f.substring(0,f.length -3);
			},
			attributes : {
				style : "text-align: center"
			}
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
	// 通过还款信息
	$("#reprots-finCountGrid").kendoGrid(option);
	
	$('.form-search-link').on('click', function() {
		$("#reprots-finCountGrid").empty();
		$("#reprots-finCountGrid").kendoGrid(option);
	});
	
	var exportLock = false;
	$('.export-btn').on('click',function(){
		if(exportLock)return;
		exportLock = true;
		var action = $('.reprots-form').attr('action');
		$('.reprots-form').attr('target','_blank');
		$('.reprots-form').attr('action','./exportFundDailyCountReport');
		$('.reprots-form').submit();
		$('.reprots-form').attr('action',action);
		$('.reprots-form').attr('target','');
		setTimeout(function(){
			exportLock = false;
		},5000);
	});
	
	function getParam(){
		var array = $('.reprots-form').serializeArray();
		var params = {}
		for ( var index in array) {
			params[array[index].name] = array[index].value;
		}
		return params;
	};
});