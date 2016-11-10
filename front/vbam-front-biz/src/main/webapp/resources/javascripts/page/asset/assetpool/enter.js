var base_all = 'false',//页面全选状态 默认不选中
    base_in = [],//用于保存页面选中的value的值（未全选）
    base_out = [];//用于保存全选状态下未选中的value值
$(function(){
	new PopWindow(".message-pop",{
        title: "提示",
        width: 420,
        reload: true,
        template: "#tipBox_template"
    }).init();
	
	document.onkeydown=keyDownSearch;  
    function keyDownSearch(e) {    
        var theEvent = e || window.event;    
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
        if (code == 13) {    
        	grid();
            return false;    
        }    
        return true;    
    }
    
    //页面展示隐藏
    $(".pro-title-right").click(function(){
        $(this).parent().next().toggle();
        var ty = $(this).parent().next().is(":hidden");
        if(ty==false){
            $(this).html("折叠");
        }else if(ty==true){
            $(this).html("展开");
        }
    });
    //初始化表格
    grid();
    //页面表格全选点击事件
    $("body").on("click",".base-allCheck",function(){
        base_in = [];
        base_out = [];
        var $type = $(this).is(":checked");
        if($type==true){
            $("[name=base-check]").prop("checked","checked");
            base_all = 'true';
        }else if($type==false){
            $("[name=base-check]").removeAttr("checked","checked");
            base_all = 'false';
        }
    });
    //页面表格多选框点击事件
    $("body").on("click","input[name=base-check]",function(){
        var $this = $(this);
        checkedClick($this,base_all,base_in,base_out);
    });
    
    $("#searchBtn").click(function(){
    	grid();
    });
    
    $("#enterPool").click(function(){
    	if($("input[name=base-check]:checked").length < 1){
    		showAlertWin("请至少选择一个基础资产");
    		return;
    	}
    	var underlyingAssetIds = [];
    	$("input[name=base-check]:checked").each(function(){
    		underlyingAssetIds.push($(this).val());
    	});
    	$.ajax({
    	      async : false, 
    	      cache:false, 
    	      type: 'POST', 
    	      dataType : "json",
    	      contentType : "application/json",
    	      data:JSON.stringify({underlyingAssetIds : underlyingAssetIds}),
    	      url: "assetpoolEnter",
    	      success:function(data){ //请求成功后处理函数。
    	        if(data.status == "SUCCESS"){
    	        	successPopWin("入池成功");
    	        }else{
    	           showAlertWin(data.message);
    	        }
    	      },
    	      error: function (data) {
    	        showAlertWin("网络异常!");
    	        }
    	   });
    });
});

function successPopWin(notice){
	$(".message-pop").click();
	$("#notice-content").text(notice);
	$(".k-window-action").hide();
	$("#notice-icon").addClass("dlg-notice notice-icon02");
	var $comfirmedButton = '<div  class="dlg-del-row">'
		+ '<a class="default-link confirm-link" href="javascript:void(0);" id="continueEnter">继续入池</a>'
		+ '<a class="default-link confirm-link" href="javascript:void(0);" id="backManage">返回</a>'
        + '</div>';
    $("#tip-box-window").append($comfirmedButton);
    $("#backManage").click(function(){
    	location.href = basepath + "/asset/pool/assetpoolManage?id="+$.trim($("#specialProgramId").val())+"&status=CREATE";
    });
    $("#continueEnter").click(function(){
    	$(".k-window-action").click();
    	grid();
    });
}

//表格
function grid(){
	$("#monitorList-grid").empty();
	base_all = 'false';
    base_in = [];
    base_out = [];
    $("#monitorList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataBound: baseDataBound,
        dataSource:{
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                    data: {
                    	specialProgramId: $.trim($("#specialProgramId").val()),
                    	businessContractNo: $.trim($("input[name=businessContractNo]").val()),
                    	businessProduct: $.trim($("#businessProduct").val()),
                    	customerName: $.trim($("input[name=customerName]").val()),
                    	customerEconomicCategory: $.trim($("input[name=customerEconomicCategory]").val()),
                    	counterparty: $.trim($("input[name=counterparty]").val()),
                    	counterpartyEconomicCategory: $.trim($("input[name=counterpartyEconomicCategory]").val()),
                    	customerCity: $.trim($("input[name=customerCity]").val()),
                    	customerIndustry: $.trim($("input[name=customerIndustry]").val()),
                    	counterpartyCity: $.trim($("input[name=counterpartyCity]").val()),
                    	counterpartyIndustry: $.trim($("input[name=counterpartyIndustry]").val()),
                    	counterpartyRating: $.trim($("input[name=counterpartyRating]").val()),
                    	loanStartDate: $.trim($("input[name=loanStartDate]").val()),
                    	loanEndDate: $.trim($("input[name=loanEndDate]").val()),
                    	settleStartDate: $.trim($("input[name=settleStartDate]").val()),
                    	settleEndDate: $.trim($("input[name=settleEndDate]").val()),
                    	dueStartDate: $.trim($("input[name=dueStartDate]").val()),
                    	dueEndDate: $.trim($("input[name=dueEndDate]").val()),
                    	repaymentMethod: $.trim($("input[name=repaymentMethod]").val()),
                    	guaranteeInfo: $.trim($("input[name=guaranteeInfo]").val()),
                    	guaranteeCompanyInfo: $.trim($("input[name=guaranteeCompanyInfo]").val()),
                    	guaranteeGoodsNo: $.trim($("input[name=guaranteeGoodsNo]").val()),
                    	invoiceStartAmount: $.trim($("input[name=invoiceStartAmount]").val()),
                    	invoiceEndAmount: $.trim($("input[name=invoiceEndAmount]").val()),
                    	invoiceStartBalance: $.trim($("input[name=invoiceStartBalance]").val()),
                    	invoiceEndBalance: $.trim($("input[name=invoiceEndBalance]").val()),
                    	financeStartAmount: $.trim($("input[name=financeStartAmount]").val()),
                    	financeEndAmount: $.trim($("input[name=financeEndAmount]").val()),
                    	financeStartBalance: $.trim($("input[name=financeStartBalance]").val()),
                    	financeEndBalance: $.trim($("input[name=financeEndBalance]").val()),
                    	annualInterestRateStart: $.trim($("input[name=annualInterestRateStart]").val()),
                    	annualInterestRateEnd: $.trim($("input[name=annualInterestRateEnd]").val()),
                    	accountInterestStart: $.trim($("input[name=accountInterestStart]").val()),
                    	accountInterestEnd: $.trim($("input[name=accountInterestEnd]").val()),
                    	penaltyRateStart: $.trim($("input[name=penaltyRateStart]").val()),
                    	penaltyRateEnd: $.trim($("input[name=penaltyRateEnd]").val()),
                    	accountTotalAmountStart: $.trim($("input[name=accountTotalAmountStart]").val()),
                    	accountTotalAmountEnd: $.trim($("input[name=accountTotalAmountEnd]").val()),
                    	creditLimitStart: $.trim($("input[name=creditLimitStart]").val()),
                    	creditLimitEnd: $.trim($("input[name=creditLimitEnd]").val()),
                    	creditBalanceStart: $.trim($("input[name=creditBalanceStart]").val()),
                    	creditBalanceEnd: $.trim($("input[name=creditBalanceEnd]").val()),
                    	counterpartyLimitStart: $.trim($("input[name=counterpartyLimitStart]").val()),
                    	counterpartyLimitEnd: $.trim($("input[name=counterpartyLimitEnd]").val()),
                    	counterpartyBalanceStart: $.trim($("input[name=counterpartyBalanceStart]").val()),
                    	counterpartyBalanceEnd: $.trim($("input[name=counterpartyBalanceEnd]").val()),
                    	},
                    type : "POST",
                    url: basepath + "/asset/underlyingAsset/entering/search"
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
                        $("#monitorList-grid .k-grid-header-wrap").css("overflow-x","auto");
                    }else{
                        $("#monitorList-grid .k-grid-header-wrap").css("overflow-x","");
                    }
                    return total;
                }
            }
        },
        columns: [{
            field: "sequence",
            title: "<input type='checkbox' class='input-new base-allCheck' name='base-allCheck'>&ensp;全选",
            width: 150,
            attributes: {
                style: "text-align: center"
            },
            template: function(item){
                return "<input type='checkbox' class='input-new' name='base-check' value='"+item.loanNo+"'>";
            }
        },{
            field: "loanNo",
            title: "放款编号",
            width: 150,
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
            field: "customerName",
            title: "借款人名称",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "customerEconomicCategory",
            title: "借款人类型",
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
            title: "交易对手类型",
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
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "creditBalance",
            title: "可用额度",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "counterpartyLimit",
            title: "交易对手非承保额度",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "counterpartyBalance",
            title: "交易对手非承保额度余额",
            width: 180,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "invoiceAmount",
            title: "应收账款金额",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "invoiceBalance",
            title: "应收账款余额",
            width: 150,
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
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "loanDate",
            title: "起息日",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "dueDate",
            title: "到期日",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "repaymentMethod",
            title: "还款方式",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "interestRate",
            title: "利率",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "loanPeriod",
            title: "期限",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "specialProgramName",
            title: "所属专项计划",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
//        },{
//            field: "measure",
//            title: "操作",
//            width: 150,
//            attributes: {
//                style: "text-align: center"
//            },
//            template: function(item){
//                var edHtml = "<a class='userCenter-link' href='javascript:void(0);' style='margin-right:10px;'>评估结果</a>";
//                return edHtml;
//            }
        }],
        pageable: {
			pageSizes: false,  //设置每页显示行数
			buttonCount: 5,  //显示页数
			pageSize: 10,
			page: 1,
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
//表格分页按钮点击事件
function baseDataBound(){
//    var $data = $("[name=base-check]");
//    pageClick($data,base_all,base_in,base_out);
	$("input[name=base-allCheck]").prop("checked",false);
}
//选框点击事件
function checkedClick($this,$all,$in,$out){
    var $type = $this.is(":checked");
    var $id = $this.val();
    if($all=="true"){
        if($type==true){
            $.each($out,function(index,item){
                if(item==$id){
                    $out.splice(index,1);
                }
            })
        }else if($type==false){
            $out.push($id);
        }
    }else if($all=="false"){
        if($type==true){
            $in.push($id);
        }else if($type==false){
            $.each($in,function(index,item){
                if(item==$id){
                    $in.splice(index,1);
                }
            })
        }
    }
}
//分页多选分页点击核心事件
function pageClick($this,$all,$in,$out){
    if($all=="true"){
        $this.prop("checked","checked");
        for(var i=0;i<$this.length;i++){
            var $o = $this.eq(i).val();
            $.each($out,function(index,item){
                if(item==$o){
                    $this.eq(i).removeAttr("checked","checked");
                }
            })
        }
    }else if($all=="false"){
        for(var i=0;i<$this.length;i++){
            var $d = $this.eq(i).val();
            $.each($in,function(index,item){
                if(item==$d){
                    $this.eq(i).prop("checked","checked");
                }
            })
        }
    }
}