$(function(){
	if($("#permSpecialProgram").val()=="true")
		loadSpecialProgramListGrid();
	
	if($("#permAsset").val()=="true")
		loadAssetListGrid();

	//计划日志表查询按钮
	$('body').on("click","#specialProgramSearchBtn",function() {
		var grid = $("#plan-grid").data("kendoGrid");
		grid.setDataSource(getSpecialProgramDataSource());
	});
	
	//资产日志表查询按钮
	$('body').on("click","#assetSearchBtn",function() {
		var grid = $("#asset-grid").data("kendoGrid");
		grid.setDataSource(getAssetDataSource());
	});
	
	//tab选项卡切换
	$("body").on("click",".thirdNavGroup ul li",function(e){
		var index = $(this).index();
		$(this).addClass("thirdNavCut").siblings().removeClass("thirdNavCut");
		$(".plan-box>div").eq(index).show().siblings().hide();
	});
	
    bindEntry();
    
});

//绑定回车事件为查询当前
function bindEntry() {
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		if($("#planTab").hasClass("thirdNavCut"))
    			$('#specialProgramSearchBtn').click();
    		if($("#assetTab").hasClass("thirdNavCut"))
    			$('#assetSearchBtn').click();
    	}
    });
}

//查询计划日志
function getSpecialProgramDataSource(){
	var specialProgramStatus = $("#specialProgramStatusSelect").val();
	if(specialProgramStatus=="")
		specialProgramStatus = null;
	return new kendo.data.DataSource({
		pageSize : 10,
		type : "json", // 后台返回的数据类型
		method : "post",
		transport : {
			read : {
				type : "post",
				url: basepath + "/asset/log/searchSpecialProgramLog",
				data: {
					specialProgramName: $.trim($('#sp-specialProgramName').val()),
					operateStartDate: $.trim($('#sp-operateStartDate').val()),
					operateEndDate: $.trim($('#sp-operateEndDate').val()),
					operateCompanyName: $.trim($('#sp-operateCompanyName').val()),
					operateType: specialProgramStatus
				}
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
    });
}

//初始化计划日志表格
function loadSpecialProgramListGrid() {
	var grid = $("#plan-grid").kendoGrid({
	    selectable: "row",  //设置可选择数据行
	    sortable: true,  //列排序
	    columns: [{
	        field: "specialProgramId",
	        title: "专项计划编号",
	        width: 150,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "specialProgramName",
	        title: "专项计划名称",
	        width: 200,
	        attributes:{
	            style:"text-align:center"
	        }
	    },{
	        field: "underlyingAssetTypeDesc",
	        title: "基础资产类型",
	        width: 120,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "assetPoolAmount",
	        title: "资产池金额",
	        width: 120,
	        attributes: {
	            style: "text-align: center"
	        },
            template: function(data){
            	if(data.assetPoolAmount==null)
	        		return "-";
                return data.assetPoolAmount.formatMoney();
            }
	    },{
	        field: "operateCompanyName",
	        title: "操作企业名称",
	        width: 200,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "operateUsername",
	        title: "操作人",
	        width: 120,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "createTime",
	        title: "操作时间",
	        width: 150,
	        attributes: {
	            style: "text-align: center"
	        },
	        template: function(data){
	        	return new Date(data.createTime).format('yyyy-MM-dd hh:mm');
	        }
	    },{
	        field: "operateTypeDesc",
	        title: "操作",
	        width: 80,
	        attributes: {
	            style: "text-align: center"
	        }
	    }],
	    pageable: {
	        pageSizes: false,  //设置每页显示行数
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
	}).data("kendoGrid");
	grid.setDataSource(getSpecialProgramDataSource());
}

//查询资产日志
function getAssetDataSource(){
//	var specialProgramName = $.trim($('#as-specialProgramName').val())==""?null:$.trim($('#as-specialProgramName').val());
//	var loanInfoId = $.trim($('#as-loanInfoId').val())==""?null:$.trim($('#as-loanInfoId').val());
//	var businessContractNo = $.trim($('#as-businessContractNo').val())==""?null:$.trim($('#as-businessContractNo').val());
//	var operateCompanyName = $.trim($('#as-operateCompanyName').val())==""?null:$.trim($('#as-operateCompanyName').val());
	var underlyingAssetOperateType = $("#underlyingAssetOperateTypeSelect").val()==""?null:$("#underlyingAssetOperateTypeSelect").val();
	return new kendo.data.DataSource({
		pageSize : 10,
		type : "json", // 后台返回的数据类型
		method : "post",
		transport : {
			read : {
				type : "post",
				url: basepath + "/asset/log/searchUnderlyingAssetLog",
				data: {
					specialProgramName: $.trim($('#as-specialProgramName').val()),
					loanInfoId: $.trim($('#as-loanInfoId').val()),
					businessContractNo: $.trim($('#as-businessContractNo').val()),
					operateStartDate: $.trim($('#as-operateStartDate').val()),
					operateEndDate: $.trim($('#as-operateEndDate').val()),
					operateCompanyName: $.trim($('#as-operateCompanyName').val()),
					operateType: underlyingAssetOperateType
				}
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
    });
}

//初始化资产日志表格
function loadAssetListGrid() {
	var grid = $("#asset-grid").kendoGrid({
	    selectable: "row",  //设置可选择数据行
	    sortable: true,  //列排序
	    columns: [{
	        field: "loanInfoId",
	        title: "放款编号",
	        width: 150,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "businessContractNo",
	        title: "业务合同号",
	        width: 120,
	        attributes:{
	            style:"text-align:center"
	        }
	    },{
	        field: "customerName",
	        title: "借款人名称",
	        width: 150,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "customerEconomicCategory",
	        title: "借款人经济类型",
	        width: 150,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "counterparty",
	        title: "交易对手名称",
	        width: 150,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "counterpartyEconomicCategory",
	        title: "交易对手经济类型",
	        width: 150,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "customerCity",
	        title: "借款人所在地区",
	        width: 150,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "customerIndustry",
	        title: "借款人所属行业",
	        width: 150,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "counterpartyCity",
	        title: "交易对手所在地区",
	        width: 150,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "counterpartyIndustry",
	        title: "交易对手所属行业",
	        width: 150,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "creditLimit",
	        title: "授信额度",
	        width: 120,
	        attributes: {
	            style: "text-align: center"
	        },
	        template:function(data){
	        	if(data.creditLimit==null)
	        		return "-";
	        	return data.creditLimit.formatMoney();
	        }
	    },{
	        field: "creditBalance",
	        title: "可用额度",
	        width: 120,
	        attributes: {
	            style: "text-align: center"
	        },
	        template:function(data){
	        	if(data.creditBalance==null)
	        		return "-";
	        	return data.creditBalance.formatMoney();
	        }
	    },{
	        field: "counterpartyLimit",
	        title: "交易对手非承保额度",
	        width: 130,
	        attributes: {
	            style: "text-align: center"
	        },
	        template:function(data){
	        	if(data.counterpartyLimit==null)
	        		return "-";
	        	return data.counterpartyLimit.formatMoney();
	        }
	    },{
	        field: "counterpartyBalance",
	        title: "交易对手非承保余额",
	        width: 130,
	        attributes: {
	            style: "text-align: center"
	        },
	        template:function(data){
	        	if(data.counterpartyBalance==null)
	        		return "-";
	        	return data.counterpartyBalance.formatMoney();
	        }
	    },{
	        field: "invoiceAmount",
	        title: "应收账款金额",
	        width: 120,
	        attributes: {
	            style: "text-align: center"
	        },
	        template:function(data){
	        	if(data.invoiceAmount==null)
	        		return "-";
	        	return data.invoiceAmount.formatMoney();
	        }
	    },{
	        field: "invoiceBalance",
	        title: "应收账款余额",
	        width: 120,
	        attributes: {
	            style: "text-align: center"
	        },
	        template:function(data){
	        	if(data.invoiceBalance==null)
	        		return "-";
	        	return data.invoiceBalance.formatMoney();
	        }
	    },{
	        field: "financeAmount",
	        title: "融资金额",
	        width: 120,
	        attributes: {
	            style: "text-align: center"
	        },
	        template:function(data){
	        	if(data.financeAmount==null)
	        		return "-";
	        	return data.financeAmount.formatMoney();
	        }
	    },{
	        field: "financeBalance",
	        title: "融资余额",
	        width: 120,
	        attributes: {
	            style: "text-align: center"
	        },
	        template:function(data){
	        	if(data.financeBalance==null)
	        		return "-";
	        	return data.financeBalance.formatMoney();
	        }
	    },{
	        field: "loanDate",
	        title: "起息日",
	        width: 120,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "dueDate",
	        title: "到期日",
	        width: 120,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "repaymentMethod",
	        title: "还款方式",
	        width: 160,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "interestRate",
	        title: "利率",
	        width: 120,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "loanPeriod",
	        title: "期限",
	        width: 120,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "specialProgramName",
	        title: "所属专项计划",
	        width: 120,
	        attributes: {
	            style: "text-align: center"
	        },
	        template: function(data){
	        	if(data.specialProgramName==null)
	        		return "-";
	        	return data.specialProgramName;
	        }
	    },{
	        field: "operateCompanyName",
	        title: "操作企业名称",
	        width: 200,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "operateUsername",
	        title: "操作人",
	        width: 120,
	        attributes: {
	            style: "text-align: center"
	        }
	    },{
	        field: "createTime",
	        title: "操作时间",
	        width: 150,
	        attributes: {
	            style: "text-align: center"
	        },
	        template: function(data){
	        	return new Date(data.createTime).format('yyyy-MM-dd hh:mm');
	        }
	    },{
	        field: "operateTypeDesc",
	        title: "操作",
	        width: 80,
	        attributes: {
	            style: "text-align: center"
	        }
	    }],
	    pageable: {
	        pageSizes: false,  //设置每页显示行数
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
	}).data("kendoGrid");
	grid.setDataSource(getAssetDataSource());
}