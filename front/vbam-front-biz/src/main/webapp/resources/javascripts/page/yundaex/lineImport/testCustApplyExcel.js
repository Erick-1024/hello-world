var sequence = 0;
$(function(){
	// 初始化成功提示弹窗
    tipPopWindow = new PopWindow({
        title: "提示",
        width: 420,
        reload: true,
        template: "#tipBox_template"
    }).init();
    
	// 文件input
	$("#uploadInvoiceExcel").click(function() {
		if($("rediskey").val()==""){
			$("#importResult").text("载入失败，请刷新页面！");
			return;
		}
		$("#photos").click();
	});
	
	// 导入按钮
	$("#importExcel").click(function(){
		if($("rediskey").val()==""){
			$("#importResult").text("导入失败，请刷新页面！");
			return;
		}
		sequence = 0;
		getLegalInvoiceInfo();
		getIllegalInvoiceInfo();
		setTimeout("setNum()",200);
	});
	
	$("#photos").kendoUpload({
		async: {
	        saveUrl: "excel",
	        autoUpload: true
	    },
		multiple: true,
		showFileList: false,
		upload: onUpload,
		success:onSuccess,
		error:onError
	});
	
	var flag = true;
	// 提交按钮点击事件
	$("#buttonConfirm").on("click", function() {
		if(flag){
			flag = false;
			$.ajax({
				type: 'post',
				url: "importExcelList",
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
	if(e.response.status=="FAILED"){
		$.each(e.files, function () {
			tipPopWindow.open().center();
			$("#tip-box-window .dlg-notice").addClass("notice-icon03");
			$("#tip-box-window .notice-content").text(this.name+"数据为空，请重新上传！");
			
	    });
	}else{
		$.each(e.files, function () {
		   $(".import-success-box").append('<span>'+this.name+'</span>');
		});
	}
	
}

function onError(){
	tipPopWindow.open().center();
    $("#tip-box-window .dlg-notice").addClass("notice-icon01");
    $("#tip-box-window .notice-content").text("上传失败。");
}

function onUpload(e) {
    var files = e.files;
    $.each(files, function () {
    	 e.data = { rediskey: $("#rediskey").val() };
        if (this.extension.toLowerCase() != ".xlsx" && this.extension.toLowerCase() != ".xls") {
        	// $(".import-success-box").append('<span>'+this.name+'&nbsp;&nbsp不是excel文件</span>');
        	$(".import-success-box").append('<span style="color:red">'+this.name+'&emsp;不是excel文件</span>');
            e.preventDefault();
        }
    });
}

// 设置计数条数
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

// 导入成功，跳转到列表页面
function gotoInvoiceListPage(){
	location.href = encodeURI(basepath + '/test/custApplyExcel');
}

var pageSize = 5;
var pageable = {
	      pageSizes: false,  // 设置每页显示行数
	      buttonCount: 5,  // 显示页数
	      pageSize: pageSize,// 每页显示多少行
	      page: 1,  // 当前页，默认设为1
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
        field: "stationNo",
        title: "网点编码",
        width: 200,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "stationName",
        title: "站点名称",
        width: 300,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "stationMgr",
        title: "公司名称",
        width: 300,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "province",
        title: "站点经营地址-省",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "city",
        title: "站点经营地址-市",
        width: 130,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "address",    	
        title: "站点经营地址-详细地址",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "busiLimit",    	
        title: "加盟年限",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "bailBalance",    	
        title: "保证金余额",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "yundaexJudge",    	
        title: "韵达评价",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "payAccountName",    	
        title: "账户名称",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "payAccount",    	
        title: "账户",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "payAccountAddress",    	
        title: "开户行地址",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "custIdno",    	
        title: "借款人身份证",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "custName",    	
        title: "借款人名称",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "custPhone",    	
        title: "借款人联系方式",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    }];

// 获取通过的应收账款信息
function getLegalInvoiceInfo()
{
	$("#legalInvoiceInfoGridDiv").empty();
	var kendoGrid = $("#legalInvoiceInfoGridDiv").kendoGrid(
	{
        selectable: "row",  // 设置可选择数据行
		columns: legalColumns,
		dataSource: {
			type: "json", // 后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						rediskey : $("#rediskey").val()
						},
					url: basepath + "/test/queryLineRedis/success"      
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
}

/*----------------- 未通过的应收账款信息列表 --------------*/

var illegalColumns =
	[{
        field: "stationNo",
        title: "网点编码",
        width: 200,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "stationName",
        title: "站点名称",
        width: 300,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "stationMgr",
        title: "公司名称",
        width: 300,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "province",
        title: "站点经营地址-省",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "city",
        title: "站点经营地址-市",
        width: 130,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "address",    	
        title: "站点经营地址-详细地址",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "busiLimit",    	
        title: "加盟年限",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "bailBalance",    	
        title: "保证金余额",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "yundaexJudge",    	
        title: "韵达评价",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "payAccountName",    	
        title: "账户名称",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "payAccount",    	
        title: "账户",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "payAccountAddress",    	
        title: "开户行地址",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "custIdno",    	
        title: "借款人身份证",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "custName",    	
        title: "借款人名称",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "custPhone",    	
        title: "借款人联系方式",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    }];

// 获取未通过的应收账款
function getIllegalInvoiceInfo(){
	$("#illegalInvoiceInfoGridDiv").empty();
	var kendoGrid = $("#illegalInvoiceInfoGridDiv").kendoGrid(
	{
        selectable: "row",  // 设置可选择数据行
		columns: illegalColumns,
		dataSource: {
			type: "json", // 后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						rediskey : $("#rediskey").val()
						},
						url: basepath + "/test/queryLineRedis/fail"	      
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
}
