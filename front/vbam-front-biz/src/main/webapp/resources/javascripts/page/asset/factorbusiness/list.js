$(function(){

	//初始化申请保理弹窗
    new PopWindow(".limit-add-btn", {
        title: "选择客户",
        width: 500,
        reload: true,
        template: "#template-resetPwd-new"
    }).init();
    
    $('body').on('click', 'a[name=tipBoxButton]', function() {
		tipBoxTemplate.close();
	});
    
    $('#listSearch').click(function() {
    	var grid = $("#monitorList-grid").data("kendoGrid");
    	grid.setDataSource(getDataSource());
    });
    
    $("body").on("click", "#customerSearch", function(){
    	loadCustomerList();
    });
    
    $('body').on('click', "a[name=delete]", function() {
    	$('a[name=confirmBoxOK]').attr('factorBusinessId', $(this).attr('factorBusinessId'));
    	showConfirmBox("确定删除" + $($(this).parent().parent().children()[0]).text() + "业务合同吗？");
    });
    
    $('body').on('click', "a[name=edit]", function() {
    	window.location.href = basepath + "/asset/factorBusiness/goto/edit/" + $(this).attr('factorBusinessId');
    });
    
    $('body').on('click', "a[name=detail]", function() {
    	window.location.href = basepath + "/asset/factorBusiness/goto/detail/" + $(this).attr('factorBusinessId');
    });
    
    $('body').on('click', 'a[name=confirmBoxOK]', function() {
    	cana.get(basepath + "/asset/factorBusiness/delete/" + $(this).attr('factorBusinessId'), null, deleteSuccess, deleteFail, null);
    	confirmPopWindow.close();
    });
    
    //申请保理点击事件触发弹窗
    $(".limit-add").click(function(){
        $(".limit-add-btn").click();
        $(".k-widget.k-window").css("top","25%");
        $('#customerSearch').click();
    });
    
    $('body').on('click', '#customerOk', function() {
    	var customerId = $.trim($('input[name=client-choose]:checked').val());
    	if(customerId == '') {
    		showTipBox("notice-icon01", "请选择客户");
    		return;
    	}
    	$('.back-link').click();
    	window.location.href = basepath + "/asset/factorBusiness/goto/new/" + customerId;
    });
    
    loadFactorBusinessList();
    
    bindEntry();
    
});

function getDataSource(){
	return new kendo.data.DataSource({
		pageSize: 10,
		type: "json", //后台返回的数据类型
		method: "post",
		transport: {
			read : {
				type : "post",
				data: {
					customerName: $.trim($('#customerName').val()),
					businessProduct: $.trim($('#businessProduct').val()),
					loanState: $.trim($('#loanState').val()),
					businessContractNo: $.trim($('#businessContractNo').val())
				},
				url: basepath + "/asset/factorBusiness/get/list"
			}
		},
		serverPaging: true,
		serverSorting: true,
		serverFiltering: true,
		//解析远程响应的数据
		schema:{
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

function deleteSuccess(data) {
	showTipBox("notice-icon02", "删除成功！");
	var grid = $("#monitorList-grid").data("kendoGrid");
	grid.dataSource.fetch();
}

function deleteFail(data) {
	showTipBox("notice-icon03", "删除失败！");
}
    
function bindEntry() {
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		if($('#innerCustomerName').is(":visible")) {
    			if($("#innerCustomerName").is(":focus")) {
    				$('#customerSearch').click();
    			} else if($('[name=tipBoxButton]').is(":visible")) {
    				tipBoxTemplate.close();
    			} else {
    				$('#customerOk').click();
    			}
    		} else {
    			$('#listSearch').click();
    		}
    	}
    });
}

function loadCustomerList() {
	
	$("#client-out-grid").empty();
	//弹窗表格初始化
    $("#client-out-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
			pageSize : 10,
			type : "json", // 后台返回的数据类型
			method : "post",
			transport : {
				read : {
					type : "post",
					url: basepath + "/asset/factorBusiness/get/customers",
					data: {
						customerName: $.trim($('#innerCustomerName').val())
					},
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
        columns: [{
            field: "sequence",
            title: "选择用户",
            width: 60,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	if(data.creditStatus == 'NORMAL' && data.availableLimit > 0)
            		return "<input type='radio' name='client-choose' value='" + data.id + "' class='client-radio'>";
            	else
            		return "<input type='radio' name='client-choose' class='client-radio' disabled='disabled'>";
            }
        },{
            field: "customerName",
            title: "客户名称",
            width: 300,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "creditLimitState",
            title: "额度状态",
            width: 100,
            attributes:{
                style:"text-align:center"
            },
            template: function(data) {
            	if(data.creditStatus == 'NORMAL' && data.availableLimit > 0)
            		return '可用';
            	else
            		return '不可用';
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
    });
    
}

function loadFactorBusinessList() {

    //初始化表格
    var grid = $("#monitorList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        columns: [{
            field: "businessContractNo",
            title: "业务合同号",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "customerName",
            title: "客户名称",
            width: 220,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "businessProduct",
            title: "业务产品",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
                return BusinessProduct[data.businessProduct];
            }
        },{
            field: "loanState",
            title: "状态",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
                return LoanState[data.loanState];
            }
        },{
            field: "contractEndDate",
            title: "合同到期日",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "measure",
            title: "操作",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	var edHtml = "";
            	if(detailAuth) {
            		edHtml = edHtml + "<a class='comRow-link' name='detail' href='javascript:void(0);' factorBusinessId='" + data.id + "'>详情</a>";
            	}
            	if(data.loanState == 'UNLOAN') {
            		if(editAuth) {
                		edHtml = edHtml + "<a class='comRow-link' name='edit' href='javascript:void(0);' factorBusinessId='" + data.id + "'>修改</a>";
                	}
                	if(deleteAuth) {
                		edHtml = edHtml + "<a class='comRow-link' name='delete' href='javascript:void(0);' factorBusinessId='" + data.id + "'>删除</a>";
                	}
            	}
                return edHtml;
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
    grid.setDataSource(getDataSource());
}