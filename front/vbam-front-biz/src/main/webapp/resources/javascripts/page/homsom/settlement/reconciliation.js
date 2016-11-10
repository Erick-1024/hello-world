var pageSize = 10;
var loanTotalAmount = 0;
//定义全局变量
var base_all = 'true',//页面全选状态 默认不选中
        base_in = [],//用于保存页面选中的value的值（未全选）
        base_out = [];//用于保存全选状态下未选中的value值
var validator = null;
$(function(){
    //弹窗初始化
    openPop();
    //初始化表格
    pageFormData();
    //核销点击事件
    $("body").on("click",".repo-btn",function(){
        $(".openOnePop").trigger('click');
        moneyFormat();
        $("input[name='settlementCounterpartyId']").val($(this).parent().next().text());
        $("input[name='counterpartyId']").val($(this).parent().next().next().text());
        $("input[name='settlementCounterpartyName']").val($(this).parent().prev().prev().prev().prev().prev().prev().prev().text());
        $("input[name='settlementAmount']").val($(this).parent().prev().prev().prev().prev().prev().text());
        $("input[name='settlementDate']").val($("td[name='settlementDate']").text());
        $("td[name='settlementCounterpartyNameTD1']").text($(this).parent().prev().prev().prev().prev().prev().prev().prev().text());
        validator = $("#actualMoneyInputForm").kendoValidator().data("kendoValidator");
    });
    //确认核销
    $("body").on("click",".go-next",function(){
	    if(!validator.validate()) {
	        return;
	    }
	    var actualAmount = $("input[name='actualSettlementAmount']").val();
	    var settlementCounterpartyName = $("input[name='settlementCounterpartyName']").val();
	    var settlementAmount = $("input[name='settlementAmount']").val();
	    var settlementDate = $("input[name='settlementDate']").val();
	    $(".back-link").trigger('click');
	    $(".openTwoPop").trigger('click');
        popFormDataOne();
        $("td[name='actualSettlementAmount']").text(actualAmount);
        $("td[name='settlementCounterpartyName']").text(settlementCounterpartyName);
        $("td[name='settlementAmount']").text(settlementAmount);
        $("td[name='settlementDateTD']").text(settlementDate);
        $("input[name='settlementCounterpartyIdSubmit']").val($.trim($("input[name='settlementCounterpartyId']").val()));
        $("input[name='counterpartyIdSubmit']").val($.trim($("input[name='counterpartyId']").val()));
    });
    //核销明细点击事件
    $("body").on("click",".repoBack-btn",function(){
    	$(".openThreePop").trigger('click');
    	popFormDataTwo();
    	$("td[name='settlementCounterpartyNameTD']").text($(this).parent().prev().prev().prev().prev().prev().prev().prev().text());
    	$("td[name='settlementDateTD2']").text($("td[name='settlementDate']").text());
    	$("td[name='loanTotalAmountTD']").text($(this).parent().prev().prev().prev().prev().prev().prev().text());
    	$("td[name='settlementAmountTD']").text($(this).parent().prev().prev().prev().prev().prev().text());
    	$("td[name='actualSettlementAmountTD']").text($(this).parent().prev().prev().prev().prev().text());
    	$("td[name='refundAmountTD']").text($(this).parent().prev().prev().prev().text());
    	$("td[name='buybackAmountTD']").text($(this).parent().prev().prev().text());
    });
    //页面表格全选点击事件
    $("body").on("click",".base-allCheck",function(){
        base_in = [];
        base_out = [];
        var $type = $(this).is(":checked");
        if($type==true){
            $("[name=base-check]").prop("checked","checked");
            base_all = 'true';
            $("td[name='loanTotalAmount']").text(loanTotalAmount.formatMoney());
        }else if($type==false){
            $("[name=base-check]").removeAttr("checked","checked");
            base_all = 'false';
            $("td[name='loanTotalAmount']").text("0.00");
        }
        if(Number($("td[name='loanTotalAmount']").text().parseMoney()) > Number($("td[name='actualSettlementAmount']").text().parseMoney())){
        	$("td[name='buybackAmount']").text((Number($("td[name='loanTotalAmount']").text().parseMoney()) - Number($("td[name='actualSettlementAmount']").text().parseMoney())).toFixed(2).formatMoney());
        	$("td[name='refundAmount']").text("0.00");
        }else{
        	$("td[name='buybackAmount']").text("0.00")
        	$("td[name='refundAmount']").text((Number($("td[name='actualSettlementAmount']").text().parseMoney()) - Number($("td[name='loanTotalAmount']").text().parseMoney())).toFixed(2).formatMoney());
        }
    });
    //页面选框点击事件
    $("body").on("click","input[name=base-check]",function(){
        var $this = $(this);
        var loanAmount = Number($(this).parent().next().next().text().parseMoney());
        var interestAmount = Number($(this).parent().next().next().next().next().text().parseMoney());
        if($this.is(":checked")){
        	$("td[name='loanTotalAmount']").text((Number($("td[name='loanTotalAmount']").text().parseMoney()) + loanAmount + interestAmount).toFixed(2).formatMoney());
        }else{
        	$("td[name='loanTotalAmount']").text((Number($("td[name='loanTotalAmount']").text().parseMoney()) - loanAmount - interestAmount).toFixed(2).formatMoney());
        }
        if(Number($("td[name='loanTotalAmount']").text().parseMoney()) > Number($("td[name='actualSettlementAmount']").text().parseMoney())){
        	$("td[name='buybackAmount']").text((Number($("td[name='loanTotalAmount']").text().parseMoney()) - Number($("td[name='actualSettlementAmount']").text().parseMoney())).toFixed(2).formatMoney());
        	$("td[name='refundAmount']").text("0.00");
        }else{
        	$("td[name='buybackAmount']").text("0.00")
        	$("td[name='refundAmount']").text((Number($("td[name='actualSettlementAmount']").text().parseMoney()) - Number($("td[name='loanTotalAmount']").text().parseMoney())).toFixed(2).formatMoney());
        }
        checkedClick($this,base_all,base_in,base_out);
    });

    $("body").on("click",".confirm-settlement",function(){
    	settlementConfirm();
    });
});

function settlementConfirm(){
	var settlementCounterpartyId = $.trim($("input[name='settlementCounterpartyIdSubmit']").val());
	var counterpartyId = $.trim($("input[name='counterpartyIdSubmit']").val());
	var loanTotalAmount = $("td[name='loanTotalAmount']").text().parseMoney();
	var actualSettlementAmount = $("td[name='actualSettlementAmount']").text().parseMoney();
	$.ajax({
		async : false,  
		cache : false,  
		type: 'POST',  
		dataType : "json",
		contentType : "application/json",
		data:JSON.stringify({chooseAll: $(".base-allCheck").is(":checked"), unselectedTicketNoList : base_out, selectedTicketNoList: base_in,
							 settlementCounterpartyId: settlementCounterpartyId, counterpartyId: counterpartyId,
							 paymentType: "SETTLEMENT", actualSettlementAmount: actualSettlementAmount.parseMoney(),
							 loanAmount: loanTotalAmount.parseMoney()}),
		url: basepath + "/homsom/settlement/reconciliation/comfirm/HOMSOM",
		success:function(data){ //请求成功后处理函数。 
			if(data.status == "SUCCESS"){
				$(".k-window-action").click();
				showSuccessWin("核销成功");
				$("#monitorList-grid").find("td").each(function(){
					if($(this).text() == settlementCounterpartyId){
						$(this).prev().html("<a class='userCenter-link repoBack-btn' href='javascript:void(0);'>核销明细</a>");
						$(this).prev().prev().text("已核销");
						$(this).prev().prev().prev().prev().prev().text($("td[name='actualSettlementAmount']").text());
						$(this).prev().prev().prev().prev().prev().prev().text(data.message);
						if(Number(loanTotalAmount) > Number(actualSettlementAmount)){
							$(this).prev().prev().prev().text((Number(loanTotalAmount) - Number(actualSettlementAmount)).toFixed(2).formatMoney());
						}else{
							$(this).prev().prev().prev().prev().text((Number(actualSettlementAmount) - Number(loanTotalAmount)).toFixed(2).formatMoney());
						}
					}
				});
			}else{
				showAlertWin(data.message);
			} 
		},
		error: function (data) {
        	showAlertWin("网络异常!");
        }
	});
}

//弹窗初始化
function openPop(){
    //初始化弹窗1
    new PopWindow(".openOnePop", {
        title: "核销",
        width: 600,
        reload: true,
        template: "#tipBox_Pop"
    }).init();
    //初始化弹窗2
    new PopWindow(".openTwoPop", {
        title: "确认核销",
        width: 600,
        reload: true,
        template: "#tipBox_repo"
    }).init();
    //初始化弹窗3
    new PopWindow(".openThreePop", {
        title: "核销明细",
        width: 600,
        reload: true,
        template: "#tipBox_back"
    }).init();
}
//页面表格数据
function pageFormData(){
    $("#monitorList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
        	type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: function(option){
                	var paraData = {};
                	paraData.pageSize = option.data.pageSize;
                	paraData.page = option.data.page;
                	paraData.paymentType = "SETTLEMENT";
                	$.ajax({
                    	data: paraData,
                    	type : "POST",
                    	dataType:"json",
                    	url: basepath+"/homsom/settlement/get/reconciliation/list/HOMSOM",
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
            field: "counterpartyName",
            title: "交易对手",
            width: 220,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "loanTotalAmount",
            title: "放款总金额",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "settleAmount",
            title: "核销总金额",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "actualSettleAmount",
            title: "实还总金额",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "refundAmount",
            title: "归还总金额",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "buybackAmount",
            title: "回购总金额",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "risk",
            title: "状态",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	if(data.state == "UNSETTLE"){
            		return "未核销";
            	}else if(data.state == "SETTLED"){
            		return "已核销";
            	}else{
            		return "";
            	}
            }
        },{
            field: "risk",
            title: "操作",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
                var edHtml = "";
                if(data.state == "UNSETTLE"){
                    edHtml = "<a class='userCenter-link repo-btn' href='javascript:void(0);'>核销</a>";
                }else{
                    edHtml = "<a class='userCenter-link repoBack-btn' href='javascript:void(0);'>核销明细</a>";
                }
                return edHtml;
            }
        },{
            field: "id",
            title: "id",
            hidden: true
        },{
            field: "counterpartyId",
            title: "counterpartyId",
            hidden: true
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
//确认核销弹窗表格数据
function popFormDataOne(){
    $("#grid-repo").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataBound: baseDataBound,//点击分页触发事件
        dataSource:{
        	type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: function(option){
                	var paraData = {};
                	paraData.pageSize = option.data.pageSize;
                	paraData.page = option.data.page;
                	paraData.settlementCounterpartyId = $.trim($("input[name='settlementCounterpartyId']").val());
                	paraData.paymentType = "SETTLEMENT";
                	paraData.state = "UNSETTLE";
                	$.ajax({
                    	data: paraData,
                    	type : "POST",
                    	dataType:"json",
                    	url: basepath+"/homsom/settlement/get/reconciliation/ticket/list/HOMSOM",
                    	success:function(data){
                    		loanTotalAmount = 0;
                    		option.success(data);
                    		$("td[name='loanTotalAmount']").text(loanTotalAmount);
                    		if(Number(loanTotalAmount) > Number($("td[name='actualSettlementAmount']").text().parseMoney())){
                    			$("td[name='buybackAmount']").text((Number(loanTotalAmount) - Number($("td[name='actualSettlementAmount']").text().parseMoney())).toFixed(2).formatMoney());
                    			$("td[name='refundAmount']").text("0.00");
                    		}else{
                    			$("td[name='buybackAmount']").text("0.00")
                    			$("td[name='refundAmount']").text((Number($("td[name='actualSettlementAmount']").text().parseMoney()) - Number(loanTotalAmount)).toFixed(2).formatMoney());
                    		}
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
            title: "<input type='checkbox' class='input-new base-allCheck' name='base-allCheck' checked='checked'>全选",
            width: 60,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	var loanAmount = Number(data.loanAmount.parseMoney());
            	var interestAmount = Number(data.interestAmount.parseMoney());
            	loanTotalAmount = Number(loanTotalAmount);
            	loanTotalAmount = (loanAmount + interestAmount + loanTotalAmount).toFixed(2);
                return "<input type='checkbox' class='input-new' name='base-check' value='"+data.ticketNo+"'>";
            }
        },{
            field: "ticketNo",
            title: "核销票号",
            width: 120,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "loanAmount",
            title: "本金",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "loanPeriod",
            title: "期限（天）",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "interestAmount",
            title: "利息",
            width: 100,
            attributes: {
                style: "text-align: center"
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
//核销明细弹窗表格数据
function popFormDataTwo(){
    $("#grid-back").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
        	type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: function(option){
                	var paraData = {};
                	paraData.pageSize = option.data.pageSize;
                	paraData.page = option.data.page;
                	paraData.settlementCounterpartyId = $.trim($("input[name='settlementCounterpartyId']").val());
                	paraData.paymentType = "SETTLEMENT";
                	paraData.state = "SETTLED";
                	$.ajax({
                    	data: paraData,
                    	type : "POST",
                    	dataType:"json",
                    	url: basepath+"/homsom/settlement/get/reconciliation/ticket/list/HOMSOM",
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
            field: "ticketNo",
            title: "核销票号",
            width: 120,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "loanAmount",
            title: "本金",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "loanPeriod",
            title: "期限（天）",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "interestAmount",
            title: "利息",
            width: 100,
            attributes: {
                style: "text-align: center"
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
//确认核销分页事件
function baseDataBound(){
    var $data = $("input[name=base-check]");
    pageClick($data,base_all,base_in,base_out);
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

function moneyFormat(){
	$("body input.moneyNum").on({
        focus: function(){
            $(this).attr("data-oval",$(this).val()); //将当前值存入自定义属性
        },
        blur: function(){
            var oldVal=($(this).attr("data-oval")); //获取原值
            var newVal=($(this).val()); //获取当前值
            if (oldVal!=newVal) {
                if(newVal == "" || isNaN(newVal)){
                    this.value = "";
                    return this.value;
                }
                var s = this.value;
                var temp;
                //n = n > 0 && n <= 20 ? n : 2;
                if(/.+(\..*\.|\-).*/.test(s)){
                    return;
                }
                s = parseFloat((s + "").replace(/[^\d\.\-]/g, "")).toFixed(2) + "";
                var l = s.split(".")[0].split("").reverse(),
                    r = s.split(".")[1];
                t = "";
                for(i = 0; i < l.length; i ++ ) {
                    t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length && (l[i+1]!='-')? "," : "");
                }
                temp = t.split("").reverse().join("") + "." + r;
                this.value = temp;
                return this.value;
            }
        }
    });
}