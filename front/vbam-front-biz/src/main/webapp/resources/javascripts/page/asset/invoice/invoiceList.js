$(function(){
	// 初始化消息弹窗
	new PopWindow(".open-message-btn", {
		title : "提示",
		width : 400,
		reload : true,
		template : "#tipBox_template"
	}).init();
    
	initSubmitButton();
	tableSearch();
	
	 $('body').on('click', 'a[name=delete]', function() {
	    	$('a[name=confirmBoxOK]').attr('invoiceId', $(this).attr('invoiceId'));
	    	showConfirmBox("确定删除" + $($(this).parent().parent().children()[4]).text() + "业务合同吗？");
	    });
	 
	$('body').on('click', 'a[name=confirmBoxOK]', function() {
	    	cana.get(basepath + "/asset/invoice/invoiceDelete/" + $(this).attr('invoiceId'), null, deleteSuccess, deleteFail, null);
	    	confirmPopWindow.close();
	    });
	
})

function deleteSuccess(data) {
	$(".open-message-btn").click();
	$("#tip-box-window .dlg-notice").addClass("notice-icon02");
	$("#tip-box-window .notice-content").text("删除成功！");
	tableSearch();
}

function deleteFail(data) {
	$(".open-message-btn").click();
	$("#tip-box-window .dlg-notice").addClass("notice-icon03");
	$("#tip-box-window .notice-content").text("删除失败！");
}

/**
 * 初始化按钮事件
 */
function initSubmitButton() {
	// 搜索
	$("#form-search").click(function() {
		tableSearch();
	});
	
	// 新增
	$("#form-add").click(function() {
		location.href = encodeURI(basepath + '/asset/invoice/invoiceCreate');
	});
	//文件导入
	$("#form-excel").click(function() {
		location.href = encodeURI(basepath + '/asset/invoice/invImportList');
	});
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		tableSearch();
    	}
    });
}

function tableSearch() {
	var sequence = 0;
	$("#monitorList-grid").empty();
	//初始化表格
	var grid = $("#monitorList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                    data: { 
                    	customerName: $.trim($("#customerName").val()),
                    	businessContractNo: $.trim($("#businessContractNo").val()),
                    	currencyType: $.trim($("#currencyType").val()),
                    	businessProduct: $.trim($("#businessProduct").val())
                    },
                    type : "POST",
                    url:  basepath + "/asset/invoice/invoiceList"
                
            }
        },
        //解析远程响应的数据
        serverPaging: true,
		serverSorting: true,
		serverFiltering: true,
		schema: {
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
	sortable: false,
	groupable: false,
        columns: [{
            field: "sequence",
            title: "序号",
            width: 80,
            template: function(data){
				sequence ++;
				return sequence;
			},
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "customerName",
            title: "客户名称",
            width: 200,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "currency",
            title: "币种",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "businessProductDesc",
            title: "业务产品",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "businessContractNo",
            title: "业务合同号",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "countInvoice",
            title: "单证笔数",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "sumInvoiceAmt",
            title: "账款余额",
            width: 120,
            attributes: {
                style: "text-align: center"
            },template: function(data){
                return data.sumInvoiceAmt.formatMoney();
            }
        },{
            title: "操作",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	var url="";
            	var loanState = kendo.htmlEncode(data.loanState);
            	
        		if(detailAuth){
        			url += '<a class="comRow-link" href="' + basepath + '/asset/invoice/invoiceDetail?id=' + data.id +'" >详情</a>';
        		}
        		if(updateAuth){
        			url += '<a class="comRow-link" href="'+basepath+ '/asset/invoice/invoiceUpdate?id='+ data.id +'" >修改</a>' ;
        		}
        		if(deleteAuth && loanState == "UNLOAN"){
        			url += '<a class="comRow-link" name="delete" href="javascript:void(0);" invoiceId=' + data.id + '>删除</a>';
        		}
                return url;
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            buttonCount: 10,  //显示页数
            pageSize: 10,
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
	grid.data("kendoGrid").pager.bind('change', function(){
		sequence = 0;
	});
}
    
    