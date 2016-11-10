var pageSize = 10;
var base_all = 'false',//页面全选状态 默认不选中
    base_in = [],//用于保存页面选中的value的值（未全选）
    base_out = [],//用于保存全选状态下未选中的value值
    special_in='',//用于保存单选的value值
    asset_all = 'false',//弹窗全选状态 默认不选中
    asset_in = [],//用于保存弹窗选中的value值（未全选）
    asset_out = [];//用于保存全选状态下未选中的value值
$(function(){
	$("body").on("keydown", function(e){
		var theEvent = e || window.event;    
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
    	//回车按钮事件
    	if(code == 13){
    		if($("input[name='loanInfoId']").is(":visible")) {
    			$('.assetConvertSearch').click();
    		} else if($("#specialProgramNameSearch").is(":visible")) {
				$('#popSearchBtn').click();
    		} else {
    			$('#underlyingAssetSearch').click();
    		}
    	}
    });
    
    
	//初始化日期选择控件
    starDate();
    //初始化弹窗
    openAssetPop();
    //初始化页面表格
    base_grid();
    //入池点击事件
    $("body").on('click','.inSump',function(){
    	if($("input[name='base-check']:checked").length < 1){
    		showAlertWin("请至少选择一个基础资产");
    		return;
    	}
    	loadSpecialProgramPop();
    });
    // 入池-确定按钮
    $("body").on('click','#interests-btn',function(){
    	var specialProgramId =  $.trim($('input[name="interests-choose"]:checked').val());
    	var underlyingAssetIds = new Array();
    	if(specialProgramId == null || specialProgramId == ""){
    		showAlertWin("请选择一个专项计划");
    		return;
    	}
    	var underlyingAssetId = $(this).data("id");
    	if(underlyingAssetId != null && underlyingAssetId != ""){
    		underlyingAssetIds.push(underlyingAssetId);
    	}else{
    		if($("input[name='base-check']:checked").length < 1){
        		showAlertWin("请至少选择一个基础资产");
        		return;
        	}
    		$("input[name='base-check']:checked").each(function(){
    			underlyingAssetIds.push($(this).val());
    		});
    	}
    	bindSpecialProgram(specialProgramId, underlyingAssetIds);
    });

    $("body").on('click','.bindSpecialProgram',function(){
    	$("[name=asset-check]").removeAttr("checked","checked");
    	loadSpecialProgramPop();
    	$("#interests-btn").attr("data-id", $(this).data("id"));
    });
    
    //资产选择点击事件
    $('.checkAsset').on('click',function(){
        $(".limit-next-btn").trigger('click');
        $(".k-widget.k-window").css("top","25%");
        asset_grid();
        starDate();
    });
    //页面表格全选点击事件
    $("body").on("click",".base-allCheck",function(){
        base_in = [];
        base_out = [];
        var $type = $(this).is(":checked");
        if($type==true){
        	$("[name=base-check]").each(function(){
        	    if($(this).attr("disabled") != "disabled"){
        	    	$(this).prop("checked","checked");
        	    }
        	});
        	base_all = 'true';
        }else if($type==false){
            $("[name=base-check]").removeAttr("checked","checked");
            base_all = 'false';
        }
    });
    //页面选框点击事件
    $("body").on("click","input[name=base-check]",function(){
        var $this = $(this);
        checkedClick($this,base_all,base_in,base_out);
    });
    //资产表格全选点击事件
    $("body").on("click",".asset-allCheck",function(){
        asset_in = [];
        asset_out = [];
        var $type = $(this).is(":checked");
        if($type==true){
        	$("[name=asset-check]").prop("checked","checked");
        	asset_all = 'true';
        }else if($type==false){
            $("[name=asset-check]").removeAttr("checked","checked");
            asset_all = 'false';
        }
    });
    //资产选框点击事件
    $("body").on("click","input[name=asset-check]",function(){
        var $this = $(this);
        checkedClick($this,asset_all,asset_in,asset_out);
    });
    //专项表格单选点击事件
    $("body").on("click","input[name=interests-choose]",function(){
        var data = $(this).val();
        special_in=data;
    });
    // 搜索点击事件
    $("body").on("click","#underlyingAssetSearch",function(){
    	base_grid();
    });
    
    // 出备选池
    $("body").on("click",".unbindSpecialProgram",function(){
    	confirmPopWindow.open().center();
    	$("#confirm-box-window .notice-content").html("是否确认出池");
    	$("#loanNo-unbindSP").val($(this).data("id"));
    });

    // 出备选池
    $("body").on("click","#unbind-confirm",function(){
    	unbindSpecialProgram($("#loanNo-unbindSP").val());
    });

    // 删除基础资产
    $("body").on("click",".deleteUnderlyingAsset",function(){
    	deletePopWindow.open().center();
    	$("#confirm-box-window-delete .notice-content").html("是否确认删除基础资产");
    	$("#loanNo-delete").val($(this).data("id"));
    });
    
    // 删除基础资产
    $("body").on("click","#delete-confirm",function(){
    	deleteUnderlyingAsset($("#loanNo-delete").val());
    });

    //保理放款转为基础资产
    $("body").on("click",".comfirm-loan",function(){
    	var loanInfoIds = new Array();
		if(asset_in.length < 1 && asset_out.length < 1 && asset_all=="false"){
    		showAlertWin("请至少选择一个基础资产");
    		return;
    	}
		if(asset_all=="true"){
			createUnderlyingAssetByFactorLoan(true);
		}else{
			createUnderlyingAssetByFactorLoan(false);
		}
    });

    $("body").on("click",".assetConvertSearch",function(){
    	var grid = $("#asset-grid").data("kendoGrid");
		grid.dataSource.fetch();
    });

    $("body").on("click","#confirm-close-btn",function(){
    	$(".k-window-action").click();
    });
    
});

//关闭弹窗
function closeThePop(){
    $(".k-overlay").hide();
    $(".k-window").hide();
}
// 入备选池
function bindSpecialProgram(specialProgramId, underlyingAssetIds){
	$.ajax({
		async : false,  
		cache:false,  
		type: 'POST',  
		dataType : "json",
		contentType : "application/json",
		data:JSON.stringify({specialProgramId: specialProgramId, underlyingAssetIds : underlyingAssetIds}),
		url: "bindSpecialProgram",
		success:function(data){ //请求成功后处理函数。 
			if(data.status == "SUCCESS"){
				$(".k-window-action").click();
				successPopWin("入备选池成功");
				var grid = $("#monitorList-grid").data("kendoGrid");
				grid.dataSource.fetch();
			}else{
				showAlertWin(data.message);
			} 
		},
		error: function (data) {
        	showAlertWin("网络异常!");
        }
	});
}

// 出选池
function unbindSpecialProgram(underlyingAssetId){
	$.ajax({
		async : false,  
		cache:false,  
		type: 'POST',  
		dataType : "json",
		data:{underlyingAssetId : underlyingAssetId},
		url: "unbindSpecialProgram",
		success:function(data){ //请求成功后处理函数。 
			if(data.status == "SUCCESS"){
				closeThePop();
				successPopWin("出备选池成功");
				var grid = $("#monitorList-grid").data("kendoGrid");
				grid.dataSource.fetch();
			}else{
				showAlertWin(data.message);
			} 
		},
		error: function (data) {
			showAlertWin("网络异常!");
		}
	});
}

// 删除基础资产
function deleteUnderlyingAsset(underlyingAssetId){
	$.ajax({
		async : false,  
		cache:false,  
		type: 'POST',  
		dataType : "json",
		data:{underlyingAssetId : underlyingAssetId},
		url: "deleteUnderlyingAsset",
		success:function(data){ //请求成功后处理函数。 
			if(data.status == "SUCCESS"){
				closeThePop();
				successPopWin("基础资产删除成功");
				var grid = $("#monitorList-grid").data("kendoGrid");
				grid.dataSource.fetch();
			}else{
				showAlertWin(data.message);
			} 
		},
		error: function (data) {
			showAlertWin("网络异常!");
		}
	});
}

// 保理放款转为基础资产
function createUnderlyingAssetByFactorLoan(convertAllMode){
	$.ajax({
		async : false,  
		cache:false,  
		type: 'POST',  
		dataType : "json",
		contentType : "application/json",
		data:JSON.stringify({convertAllMode : convertAllMode,
							loanInfoIds : asset_in, excludeLoanInfoIds : asset_out, 
							loanInfoId : $.trim($("input[name='loanInfoId']").val()),
							financeBalanceLower : $.trim($("input[name='financeBalanceLower']").val()),
							financeBalanceUpper : $.trim($("input[name='financeBalanceUpper']").val()),
							dueDateBegin : $.trim($("input[name='dueDateBegin']").val()),
							dueDateEnd : $.trim($("input[name='dueDateEnd']").val())}),
		url: "createUnderlyingAssetByFactorLoan",
		success:function(data){ //请求成功后处理函数。 
			if(data.status == "SUCCESS"){
				$(".k-window-action").click();
				successPopWin("保理放款转化基础资产成功");
				var grid = $("#monitorList-grid").data("kendoGrid");
				grid.dataSource.fetch();
			}else{
				showAlertWin(data.message);
			} 
		},
		error: function (data) {
        	showAlertWin("网络异常!");
        }
	});
}
function successPopWin(notice){
	$(".message-pop").click();
	$("#notice-content").text(notice);
	$("#notice-icon").addClass("dlg-notice notice-icon02");
	var $comfirmedButton = '<div id="confirm-close-btn" class="dlg-del-row">'
        + '<a class="default-link confirm-link" href="javascript:void(0);">确定</a>'
        + '</div>';
    $("#tip-box-window").append($comfirmedButton);
}
//弹窗
function openAssetPop(){
    //资产选择弹窗
    new PopWindow(".limit-next-btn", {
        title: "资产选择",
        width: 800,
        reload: true,
        template: "#template-resetPwd-two"
    }).init();
	 //初始化确认弹窗
    confirmPopWindow = new PopWindow({
        title: "提示",
        width: 420,
        reload: true,
        template: "#template-notice"
    }).init();
    deletePopWindow = new PopWindow({
    	title: "提示",
    	width: 420,
    	reload: true,
    	template: "#template-delete"
    }).init();
  //初始化消息弹窗
	new PopWindow(".message-pop",{
        title: "提示",
        width: 420,
        reload: true,
        template: "#tipBox_template"
    }).init();
}
//页面表格
function base_grid(){
	$("#monitorList-grid").empty();
    $("#monitorList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataBound: baseDataBound,
        dataSource:{
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: function(option){
                	var paraData = {};
                	paraData.loanNo = $.trim($("input[name='loanNo']").val());
                	paraData.businessContractNo = $.trim($("input[name='businessContractNo']").val());
                	paraData.customerName = $.trim($("input[name='customerName']").val());
                	paraData.loanDateStart = $.trim($("input[name='loanDateStart']").val());
                	paraData.loanDateEnd = $.trim($("input[name='loanDateEnd']").val());
                	paraData.dueDateStart = $.trim($("input[name='dueDateStart']").val());
                	paraData.dueDateEnd = $.trim($("input[name='dueDateEnd']").val());
                	paraData.pageSize = option.data.pageSize;
                	paraData.page = option.data.page;
                	$.ajax({
                		contentType:"application/json",
                    	data: JSON.stringify(paraData),
                    	type : "POST",
                    	dataType:"json",
                    	url: basepath+"/asset/underlyingAsset/queryUnderlyingAssetList",
                    	success:function(data){
                    		option.success(data);
                    	}
                	});
                 }
            },
            serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			//解析远程响应的数据
			schema:{
				data: "data",
				total: function(data) {
					var total = data.totalNum;
					if(total == 0){
						$("#monitorList-grid .k-grid-header-wrap").css("overflow-x","auto");
					}else {
						$("#monitorList-grid .k-grid-header-wrap").css("overflow-x","");
					}
					return total;
				}
			}
        },
        columns: [{
            field: "sequence",
            title: "<input type='checkbox' class='input-new base-allCheck' name='base-allCheck'>&ensp;全选",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(item){
            	if(item.assetPoolStatus != "NOT_ENTER")
            		return "<input type='checkbox' class='input-new' disabled='disabled' name='base-check' value='"+item.loanNo+"'>";
            	else
            		return "<input type='checkbox' class='input-new' name='base-check' value='"+item.loanNo+"'>";
            }
        },{
            field: "loanNo",
            title: "放款编号",
            width: 200,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "businessContractNo",
            title: "业务合同号",
            width: 200,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "customerName",
            title: "借款人名称",
            width: 200,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "customerEconomicCategory",
            title: "借款人类型",
            width: 160,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "counterparty",
            title: "交易对手名称",
            width: 200,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "counterpartyEconomicCategory",
            title: "交易对手类型",
            width: 160,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "customerCity",
            title: "借款人所在地区",
            width: 160,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "customerIndustry",
            title: "借款人所属行业",
            width: 200,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "counterpartyCity",
            title: "交易对手所在地区",
            width: 160,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "counterpartyIndustry",
            title: "交易对手所属行业",
            width: 200,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "creditLimit",
            title: "授信额度",
            width: 160,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "creditBalance",
            title: "可用额度",
            width: 160,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "counterpartyLimit",
            title: "交易对手非承保额度",
            width: 200,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "counterpartyBalance",
            title: "交易对手非承保额度余额",
            width: 200,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "invoiceAmount",
            title: "应收账款金额",
            width: 160,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "invoiceBalance",
            title: "应收账款余额",
            width: 160,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "financeAmount",
            title: "融资金额",
            width: 160,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "financeBalance",
            title: "融资余额",
            width: 160,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "loanDate",
            title: "起息日",
            width: 160,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "dueDate",
            title: "到期日",
            width: 160,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "repaymentMethod",
            title: "还款方式",
            width: 160,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "interestRate",
            title: "利率",
            width: 100,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "loanPeriod",
            title: "期限",
            width: 100,
            attributes:{
                style:"text-align:center"
            },
            template: function(data){
            	if(data.assetSource == "FACTOR")
            		return data.loanPeriod + " " + data.loanPeriodUnit;
            	else
            		return data.loanPeriod;
            }
        },{
            field: "specialProgramName",
            title: "所属专项计划",
            width: 200,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "measure",
            title: "操作",
            width: 300,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	var edHtml = "";
            	if(detailAuth)
            		edHtml += "<a class='comRow-link' href='" + basepath + "/asset/underlyingAsset/underlyingAssetDetail?underlyingAssetId=" + data.loanNo + "'>详情</a>";
            	if(data.assetSource == "MANUAL" && editAuth)
            		edHtml += "<a class='comRow-link' href='" + basepath + "/asset/underlyingAsset/editAsset?assetId=" + data.loanNo + "'>修改</a>";
            	if(data.assetPoolStatus == "NOT_ENTER" && (data.specialProgramId == null || data.specialProgramId == "") && deleteAuth)
            		edHtml += "<a class='comRow-link deleteUnderlyingAsset' href='javascript:void(0);' data-id='" + data.loanNo + "'>删除</a>"
        		if(data.assetPoolStatus == "NOT_ENTER" && (data.specialProgramId == null || data.specialProgramId == "") && bindingAuth)
        			edHtml += "<a class='comRow-link bindSpecialProgram' href='javascript:void(0);' data-id='" + data.loanNo + "'>入池</a>"
            	if(data.assetPoolStatus == "ENTERING" && unbindAuth)
            		edHtml += "<a class='comRow-link unbindSpecialProgram' href='javascript:void(0);' data-id='" + data.loanNo + "'>出池</a>";
                return edHtml;
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            pageSize: pageSize,
            page: 1,
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

//资产选择弹窗表格
function asset_grid(){
    $("#asset-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataBound: assetDataBound,
        dataSource:{
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: function(option){
                	var paraData = {};
                	paraData.loanInfoId = $.trim($("input[name='loanInfoId']").val());
                	paraData.financeBalanceLower = $.trim($("input[name='financeBalanceLower']").val());
                	paraData.financeBalanceUpper = $.trim($("input[name='financeBalanceUpper']").val());
                	paraData.dueDateBegin = $.trim($("input[name='dueDateBegin']").val());
                	paraData.dueDateEnd = $.trim($("input[name='dueDateEnd']").val());
                	paraData.pageSize = option.data.pageSize;
                	paraData.page = option.data.page;
                	$.ajax({
                    	data: paraData,
                    	type : "POST",
                    	dataType:"json",
                    	url: basepath+"/asset/underlyingAsset/queryFactorLoanForUnderlyingAsset",
                    	success:function(data){
                    		option.success(data);
                    	}
                	});
                 }
            },
            serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			//解析远程响应的数据
			schema:{
				data: "data",
				total: function(data) {
					var total = data.totalNum;
					if(total == 0){
						$("#monitor-grid .k-grid-header-wrap").css("overflow-x","auto");
					}else {
						$("#monitor-grid .k-grid-header-wrap").css("overflow-x","");
					}
					return total;
				}
			}
        },
        columns: [{
            field: "id",
            title: "<input type='checkbox' class='input-new asset-allCheck' name='asset-allCheck'>&ensp;全选",
            width: 80,
            attributes: {
                style: "text-align: center"
            },
            template: function(item){
                return "<input type='checkbox' class='input-new' name='asset-check' value='"+item.loanInfoId+"'>";
            }
        },{
            field: "loanInfoId",
            title: "放款编号",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "customerName",
            title: "融资客户",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "financeAmount",
            title: "融资金额",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
                return data.financeAmount.formatMoney();
            }
        },{
            field: "financeBalance",
            title: "融资余额",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
                return data.financeBalance.formatMoney();
            }
        },{
            field: "dueDate",
            title: "到期日",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "settleStatusDesc",
            title: "状态",
            width: 80,
            attributes:{
                style:"text-align:center"
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            pageSize: 5,
            page: 1,
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
//页面表格分页事件
function baseDataBound(){
//    var $data = $("[name=base-check]");
//    pageClick($data,base_all,base_in,base_out);
	$("[name=base-allCheck]").prop("checked", false);
}
//专项弹窗表格分页事件
function specialDataBound(){
    var $data = $("[name=interests-choose]");
    if(special_in!=''){
        for(var i=0;i<$data.length;i++){
            if($data.eq(i).val()==special_in){
                $data.eq(i).prop("checked","checked");
            }
        }
    }
}
//资产弹窗表格分页事件
function assetDataBound(){
    var $data = $("[name=asset-check]");
    pageClick($data,asset_all,asset_in,asset_out);
//    $("[name=asset-allCheck]").prop("checked", false);
}

//全选点击事件
function allCheckedClick($all,$in,$out,$this,$next){
    $in = [];
    $out = [];
    var $type = $this.is(":checked");
    if($type==true){
        $next.prop("checked","checked");
        $all = 'true';
    }else if($type==false){
        $next.removeAttr("checked","checked");
        $all = 'false';
    }
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
//    	$("input[name='asset-allCheck']").prop("checked","checked")
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
//起息日、到期日
function starDate(){
    $("input.datepicker.valueStarDate").datepicker({
        format: "yyyy-mm-dd",
        language: "zh-CN",
        autoclose: true,
        todayHighlight: true,
        weekStart: 0,
        firstDay: 0,
        onClose: function (selectedDate) {
            $("input.datepicker.valueEndDate").datepicker("option", "minDate", selectedDate);
        }
    });
    $("input.datepicker.valueEndDate").datepicker({
        format: "yyyy-mm-dd",
        language: "zh-CN",
        autoclose: true,
        todayHighlight: true,
        weekStart: 0,
        firstDay: 0,
        onClose: function (selectedDate) {
            $("input.datepicker.valueStarDate").datepicker("option", "maxDate", selectedDate);
        }
    });
    $("input.datepicker.maturityStartDate").datepicker({
        format: "yyyy-mm-dd",
        language: "zh-CN",
        autoclose: true,
        todayHighlight: true,
        weekStart: 0,
        firstDay: 0,
        onClose: function (selectedDate) {
            $("input.datepicker.maturityEndDate").datepicker("option", "minDate", selectedDate);
        }
    });
    $("input.datepicker.maturityEndDate").datepicker({
        format: "yyyy-mm-dd",
        language: "zh-CN",
        autoclose: true,
        todayHighlight: true,
        weekStart: 0,
        firstDay: 0,
        onClose: function (selectedDate) {
            $("input.datepicker.maturityStartDate").datepicker("option", "maxDate", selectedDate);
        }
    });
}