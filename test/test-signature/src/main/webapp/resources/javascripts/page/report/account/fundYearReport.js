$(function() {
	var userType = $.trim($('.userType').val());
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
					url : "./fundYearReport"
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
		columns : [ 
	    {
			title : "序号",
			width : 50,
			template : function(data){
				return ++ option.index;
			},
			attributes : {
				style : "text-align: center"
			}
		}, 
		{
			title : "年份",
			width : 120,
			template : function(data){
				return data.reportDate;
			},
			attributes : {
				style : "text-align: center"
			}
		}, 
		{
			title : "上年账户余额",
			width : 120,
			template : function(data){
				return data.lastBalance;
			},
			attributes : {
				style : "text-align: center"
			}
		}, 
		{
			title : "上年监管户（自有）余额",
			width : 160,
			template : function(data) {
				return data.lastOwnSupervisionBalance == null ? '0' : data.lastOwnSupervisionBalance;
			},
			attributes : {
				style : "text-align: center"
			}
		}, 
		{
			title : "上年监管户(资金方)余额",
			width : 160,
			template : function(data) {
				return data.lastOtherSupervisionBalance == null ? '0' : data.lastOtherSupervisionBalance;
			},
			attributes : {
				style : "text-align: center"
			}
		},
		{
			title : "累计入金金额",
			width : 100,
			template : function(data){
				return data.depositFund;
			},
			attributes : {
				style : "text-align: center"
			}
		}, 
		{
			title : "累计转账金额",
			width : 120,
			template : function(data){
				return data.transferFund;
			},
			attributes : {
				style : "text-align: center"
			}
		}, 
		{
			title : "累计提现金额",
			width : 120,
			template : function(data){
				return data.withdrawFund;
			},
			attributes : {
				style : "text-align: center"
			}
		}, 
		{
			title : "累计提现费用",
			width : 120,
			template : function(data){
				return data.withdrawFee;
			},
			attributes : {
				style : "text-align: center"
			}
		}, 
		{
			title : "本年账户余额",
			width : 120,
			template : function(data){
				return data.currentBalance;
			},
			attributes : {
				style : "text-align: center"
			}
		},
		{
			title : "本年监管户（自有）余额",
			width : 160,
			template : function(data) {
				return data.currentOwnSupervisionBalance == null ? '0' : data.currentOwnSupervisionBalance;
			},
			attributes : {
				style : "text-align: center"
			}
		}, 
		{
			title : "本年监管户(资金方)余额",
			width : 160,
			template : function(data) {
				return data.currentOtherSupervisionBalance == null ? '0' : data.currentOtherSupervisionBalance;
			},
			attributes : {
				style : "text-align: center"
			}
		}, 
		],
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
	option.columns = initGrid(option);
	
	// 通过还款信息
	$("#reprots-finSumGrid").kendoGrid(option);
	
	$('.form-search-link').on('click', function() {
		$("#reprots-finSumGrid").empty();
		$("#reprots-finSumGrid").kendoGrid(option);
	});
	
	var exportLock = false;
	$('.export-btn').on('click',function(){
		if(exportLock)return;
		exportLock = true;
		var action = $('.reprots-form').attr('action');
		$('.reprots-form').attr('target','_blank');
		$('.reprots-form').attr('action','./exportFundYearReport');
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
	
	function initGrid(o) {
		var columnsForFactor = [];
		for ( var index in o.columns) {
			if (userType == 'FINACE') {
				columnsForFactor.push(o.columns[index]);
			} else if (userType == 'FACTOR' && o.columns[index].title.indexOf('资金方') <= 0) {
				columnsForFactor.push(o.columns[index]);
			} else if (userType == 'CANA' && o.columns[index].title.indexOf('自有') <= 0) {
				columnsForFactor.push(o.columns[index]);
			} else if (userType == 'CORECOMPANY') {
				columnsForFactor.push(o.columns[index]);
			}
		}
		return columnsForFactor;
	};
});