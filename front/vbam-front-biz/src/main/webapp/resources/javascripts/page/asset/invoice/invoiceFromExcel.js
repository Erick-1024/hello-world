var sequence = 0;
var sequence2 = 0;
$(function(){
	//初始化成功提示弹窗
    tipPopWindow = new PopWindow({
        title: "提示",
        width: 420,
        reload: true,
        template: "#tipBox_template"
    }).init();

	//导入按钮
	$("#importExcel").click(function(){
		if($("rediskey").val()==""){
			$("#importResult").text("导入失败，请刷新页面！");
			return;
		}
		
		getLegalInvoiceInfo();
		getIllegalInvoiceInfo();
		setTimeout("setNum()",200);
	});
	
	//测试使用
	$("#redisExcel").click(function(){
		var key = $("#rediskey").val();
		location.href = encodeURI(basepath + '/asset/invoice/gotoPassRedis?rediskey='+key);
	});
	
	$("#photos").kendoUpload({
		async: {
	        saveUrl: "importInvoiceInfoExcel",
	        autoUpload: true
	    },
		multiple: true,
		showFileList: false,
		upload: onUpload,
		success:onSuccess,
		error:onError
	});
	
	var flag = true;
	//提交按钮点击事件
	$("#buttonConfirm").on("click", function() {
		if(flag){
			flag = false;
			$.ajax({
				type: 'post',
				url: "importExcelInvList",
				data: {redisKey:$("#rediskey").val()},
				success: function(data){
					if("SUCCESS" == data.status){
						
						tipPopWindow.open().center();
						$("#tip-box-window .dlg-notice").addClass("notice-icon02");
						$("#tip-box-window .notice-content").text(data.message);
						setTimeout("gotoInvoiceListPage()",1000);
					}		
					else{
						flag = true;
						tipPopWindow.open().center();
						$("#tip-box-window .dlg-notice").addClass("notice-icon03");
						$("#tip-box-window .notice-content").text(data.message);
					}
				},
				error: function(data){
					flag = true;
					showAlertWin("网络异常："+data.responseText);
				}
			});
		}
	});
	
});

function onSuccess(e){
	 $("#uploadInvoiceExcel").text("上传附件");
	if(e.response.status=="FAILED"){
		$.each(e.files, function () {
			tipPopWindow.open().center();
			$("#tip-box-window .dlg-notice").addClass("notice-icon03");
			$("#tip-box-window .notice-content").text(e.response.message);
			
	    });
	}else{
		$.each(e.files, function () {
		   $(".import-success-box").append('<span>'+this.name+'</span>');
		});
	}
	
}

function onError(e){
	$("#uploadInvoiceExcel").text("上传附件");
	tipPopWindow.open().center();
    $("#tip-box-window .dlg-notice").addClass("notice-icon01");
    $("#tip-box-window .notice-content").text("上传失败。");
}


function onUpload(e) {
    var files = e.files;
    $("#uploadInvoiceExcel").text("上传中...");
    $.each(files, function () {
    	 e.data = { rediskey: $("#rediskey").val() };
        if (this.extension.toLowerCase() != ".xlsx" && this.extension.toLowerCase() != ".xls") {
        	$("#uploadInvoiceExcel").text("上传附件");
        	$(".import-success-box").append('<span style="color:red">'+this.name+'&emsp;不是excel文件</span>');
            e.preventDefault();
        }
    });
}


//设置计数条数
function setNum(){
	var legalNum = $("#legalInvoiceInfoGridDiv .sumData").text();
	var illegalNum = $("#illegalInvoiceInfoGridDiv .sumData").text();
	if("" == legalNum)
		legalNum = "0";
	if("" == illegalNum)
		illegalNum = "0";
	var total = parseInt(legalNum) + parseInt(illegalNum);
	$("#legalNum").text(legalNum);
	$("#illegalNum").text(illegalNum);
	$("#totalNum").text(total);
}

//导入成功，跳转到列表页面
function gotoInvoiceListPage(){
	location.href = encodeURI(basepath + '/asset/invoice/invoiceList');
}

var pageSize = 5;
var pageable = {
	      pageSizes: false,  //设置每页显示行数
	      buttonCount: 5,  //显示页数
	      pageSize: pageSize,//每页显示多少行
	      page: 1,  //当前页，默认设为1
	      messages: {
	          display: "共<span class='sumData'>{2}</span>条数据",
	          empty: "没有数据",
	          page: "页",
	          of: "/ {0}",
	          itemsPerPage: "条/页",
	          first: "第一页",
	          last: "最后一页",
		      previous: "前一页",
		      next: "下一页"
	      }
	  };

/*----------------- 通过的应收账款信息列表 --------------*/
var legalColumns =
	[{
        title: "序号",
        width: 100,
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
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "currencyDesc",
        title: "币别",
        width: 300,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "businessProductDesc",
        title: "业务产品",
        width: 300,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "businessContractNo",
        title: "业务合同号",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "countInvoice",
        title: "单证笔数",
        width: 130,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "sumInvoiceAmt",    	
        title: "账款余额",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    }];

//获取通过的应收账款信息
function getLegalInvoiceInfo(){
	$("#legalInvoiceInfoGridDiv").empty();
	var kendoGrid = $("#legalInvoiceInfoGridDiv").kendoGrid(
	{
        selectable: "row",  //设置可选择数据行
		columns: legalColumns,
		dataSource: {
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						key : $("#rediskey").val()
						},
					url: basepath + "/asset/invoice/query/success"	      
				}
			},
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
	  		schema:{
	  			data: "data",
	  			total: function(data) {
					var total = data.totalNum;
	  				if(total == 0){
	  					$("#legalInvoiceInfoGridDiv .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#legalInvoiceInfoGridDiv .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
	  		}	  		
		},
		pageable: pageable
	});
	kendoGrid.data("kendoGrid").pager.bind('change', function(){
		sequence = 0;
	});
}

/*----------------- 未通过的应收账款信息列表 --------------*/

var illegalColumns =
	[{
        title: "序号",
        width: 100,
        template: function(data){
			sequence2 ++;
			return sequence2;
		},
		attributes: {
            style: "text-align: center"
        }
    },{
        field: "customerName",
        title: "客户名称",
        width: 200,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "currencyDesc",
        title: "币别",
        width: 300,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "businessProductDesc",
        title: "业务产品",
        width: 300,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "businessContractNo",
        title: "业务合同号",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "countInvoice",
        title: "单证笔数",
        width: 130,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "sumInvoiceAmt",    	
        title: "账款余额",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    }];

//获取未通过的应收账款
function getIllegalInvoiceInfo(){
	$("#illegalInvoiceInfoGridDiv").empty();
	var kendoGrid = $("#illegalInvoiceInfoGridDiv").kendoGrid(
	{
        selectable: "row",  //设置可选择数据行
		columns: illegalColumns,
		dataSource: {
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						key : $("#rediskey").val()
						},
					url: basepath + "/asset/invoice/query/fail"	      
				}
			},
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
	  		schema:{
	  			data: "data",
	  			total: function(data) {
					var total = data.totalNum;
	  				if(total == 0){
	  					$("#illegalInvoiceInfoGridDiv .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#illegalInvoiceInfoGridDiv .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
	  		}	  		
		},
		pageable: pageable
	});
	kendoGrid.data("kendoGrid").pager.bind('change', function(){
		sequence2 = 0;
	});
}
