var sequence = 0;
$(function(){
	//初始化成功提示弹窗
    tipPopWindow = new PopWindow({
        title: "提示",
        width: 420,
        reload: true,
        template: "#tipBox_template"
    }).init();
	//文件input
	$("#uploadLoanPlanExcel").click(function() {
		if($("rediskey").val()==""){
			$("#importResult").text("载入失败，请刷新页面！");
			return;
		}
		$("#photos").click();
	});
	
	//导入按钮
	$("#importExcel").click(function(){
		if($("rediskey").val()==""){
			$("#importResult").text("导入失败，请刷新页面！");
			return;
		}
		sequence = 0;
		getLegalLoanPlan();
		getIllegalLoanPlan();
	});
	
	//测试使用
	$("#redisExcel").click(function(){
		var key = $("#rediskey").val();
		location.href = encodeURI(basepath + '/asset/invoice/gotoPassRedis?rediskey='+key);
	});
	
	$("#photos").kendoUpload({
		async: {
	        saveUrl: "importLoanPlanExcel",
	        autoUpload: true
	    },
		multiple: true,
		showFileList: false,
		upload: onUpload,
		success:onSuccess,
		error:onError
	});
	
	//提交按钮点击事件
	$("#buttonConfirm").on("click", function() {
		$.ajax({
			type: 'post',
			url: "importExcelLoanPlanList",
			data: {rediskey:$("#rediskey").val()},
			success: function(data){
				if("SUCCESS" == data.status){
					
					tipPopWindow.open().center();
					$("#tip-box-window .dlg-notice").addClass("notice-icon02");
					$("#tip-box-window .notice-content").text(data.message);
					setTimeout("gotoLoanListPage()",1000);
				}		
				else{
					tipPopWindow.open().center();
					$("#tip-box-window .dlg-notice").addClass("notice-icon03");
					$("#tip-box-window .notice-content").text(data.message);
				}
			},
			error: function(data){
				showAlertWin("网络异常："+data.responseText);
			}
		});
	});
	
});

function onSuccess(e){
	$("#uploadLoanPlanExcel").text("上传附件");
	if(e.response=="FAILED"){
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

function onError(e){
	$("#uploadLoanPlanExcel").text("上传附件");
	tipPopWindow.open().center();
    $("#tip-box-window .dlg-notice").addClass("notice-icon01");
    $("#tip-box-window .notice-content").text("上传失败。");
}

/*function getRediskey(){
	$.ajax({
		type: "POST",
		url: basepath + "/asset/invoice/getRediskey",
		dataType: "json",
		success: function(data){
			if(data !=""){
				$(".import-success-box").append('<input type="hidden" id="rediskey" name="rediskey" value="'+data+'" />');
			}
		}
	});
}*/


function onUpload(e) {
    var files = e.files;
    $("#uploadLoanPlanExcel").text("上传中...");
    $.each(files, function () {
    	 e.data = { rediskey: $("#rediskey").val() };
        if (this.extension.toLowerCase() != ".xlsx" && this.extension.toLowerCase() != ".xls") {
        	$("#uploadLoanPlanExcel").text("上传附件");
        	//$(".import-success-box").append('<span>'+this.name+'&nbsp;&nbsp不是excel文件</span>');
        	$(".import-success-box").append('<span style="color:red">'+this.name+'&emsp;不是excel文件</span>');
            e.preventDefault();
        }
    });
}

function uploadSubmit(url,uploadFileId){
	　　var file = $('#uploadLoanPlanExcel').val();
	  var key = $("#rediskey").val();
	  if(key == ""){
		  $("#importResult").text("导入失败，请刷新页面！");
		  return;
	  }
	　　//检查是否已选择上传文件
	　　if (file != '') {
	　　　　var filename = file.replace(/.*(\/|\\)/, '');
	　　　　var fileext = (/[.]/.exec(filename)) ? /[^.]+$/.exec(filename.toLowerCase()) : '';
	　　　　//检查文件格式
	　　　　if (fileext == 'xlsx' || fileext == 'xls'){
		　　　　//上传excel文件
			ajaxFileUpload(url, uploadFileId, key);
	　　　　}
	　　　　else {
			$("#importResult").text("文件格式必须是*.xls或*.xlsx");
	　　　　}
	　　}
	　　else {
		$("#importResult").text("请选择excel文件！");
	　　}
}

function setDataNum(num, passed){
	var numStr;
	if(passed){
		$("#legalNum").text(num);
		numStr = $("#illegalLoanPlanGridDiv .sumData").text();
	}else{
		$("#illegalNum").text(num);
		numStr = $("#legalLoanPlanGridDiv .sumData").text();
	}
	if("" == numStr)
		numStr = "0";
	var total = parseInt(numStr) + num;
	$("#totalNum").text(total);
}
//导入成功，跳转到列表页面
function gotoLoanListPage(){
	location.href = encodeURI(basepath + '/asset/loan/goto/loanList');
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
        field: "loanInfoId",
        title: "放款编号",
        width: 100,
		attributes: {
            style: "text-align: center"
        }
    },{
        field: "contractNo",
        title: "业务合同号",
        width: 200,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "financeName",
        title: "融资客户",
        width: 300,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "businessProduct",
        title: "业务产品",
        width: 300,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "financeAmount",
        title: "融资金额",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "financeBalance",
        title: "融资余额",
        width: 130,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "accountPrincipal",    	
        title: "应还本金",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "accountInterest",    	
        title: "应还利息",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "valueDate",    	
        title: "起息日",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "settleInterestDate",    	
        title: "结息日",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "repaymentDate",    	
        title: "还款日",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },
    {
        field: "settleStatusDesc",    	
        title: "结清状态",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    }];

//获取通过的应收账款信息
function getLegalLoanPlan()
{
	$("#legalLoanPlanGridDiv").empty();
	var kendoGrid = $("#legalLoanPlanGridDiv").kendoGrid(
	{
        selectable: "row",  //设置可选择数据行
		columns: legalColumns,
		dataSource: {
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						rediskey : $("#rediskey").val(),
						passed: true,
						},
					url: basepath + "/asset/loan/getLoanPlanFromExcel"	      
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
	  				setDataNum(total, true);
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
        field: "loanInfoId",
        title: "放款编号",
        width: 100,
		attributes: {
            style: "text-align: center"
        }
    },{
        field: "contractNo",
        title: "业务合同号",
        width: 200,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "financeName",
        title: "融资客户",
        width: 300,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "businessProduct",
        title: "业务产品",
        width: 300,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "financeAmount",
        title: "融资金额",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "financeBalance",
        title: "融资余额",
        width: 130,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "accountPrincipal",    	
        title: "应还本金",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "accountInterest",    	
        title: "应还利息",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "valueDate",    	
        title: "起息日",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "settleInterestDate",    	
        title: "结息日",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "repaymentDate",    	
        title: "还款日",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },
    {
        field: "settleStatusDesc",    	
        title: "结清状态",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    },{
        field: "checkFailedMessage",    	
        title: "未通过原因",
        width: 150,
        attributes: {
            style: "text-align: center"
        }
    }];

//获取未通过的应收账款
function getIllegalLoanPlan(){
	$("#illegalLoanPlanGridDiv").empty();
	var kendoGrid = $("#illegalLoanPlanGridDiv").kendoGrid(
	{
        selectable: "row",  //设置可选择数据行
		columns: illegalColumns,
		dataSource: {
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						rediskey : $("#rediskey").val(),
						passed: false,
						},
					url: basepath + "/asset/loan/getLoanPlanFromExcel"	      
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
	  				setDataNum(total, false);
	  				return total;
				}
	  		}	  		
		},
		pageable: pageable
	});
}
