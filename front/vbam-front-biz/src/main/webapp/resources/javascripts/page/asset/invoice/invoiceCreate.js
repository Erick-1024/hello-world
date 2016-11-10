$(function() {
	// 初始化控件
	initModel();
	// 初始化事件
	initEvent();

});
  
/**
 * 初始化控件
 */
function initModel() {
	 // 初始化新增账款信息弹窗
	new PopWindow(".open-sec-btn", {
		title : "新增账款信息",
		width : 800,
		reload : true,
		template : "#template-resetPwd-new"
	}).init();
	
	// 初始化新增/修改账款信息弹窗
	new PopWindow(".open-appoint-btn", {
		title : "新增/修改账款信息",
		width : 800,
		reload : true,
		template : "#template-resetPwd-arr"
	}).init();
	
	// 初始化费用信息弹窗
	new PopWindow(".open-cost-btn", {
		title : "新增/修改费用信息",
		width : 800,
		reload : true,
		template : "#template-resetPwd-cost"
	}).init();
	
	// 初始化消息弹窗
	new PopWindow(".open-message-btn", {
		title : "提示",
		width : 400,
		reload : true,
		template : "#tipBox_template"
	}).init();
	
	 //初始化确认弹窗
    confirmPopWindow = new PopWindow({
        title: "提示",
        width: 420,
        reload: true,
        template: "#template-notice"
    }).init();
    
    
}
  
/**
 * 初始化事件
 */
function initEvent() {
	$("#businessContractNo").blur(function() {
		$("#errorMsg").remove();
		 $.ajax({
			type : "POST",
			url : basepath + '/asset/invoice/getBusinessInfo',
			data : {
				businessContractNo : $("#businessContractNo").val()
			},
			dataType : "json",
			success : function(data) {
				if("SUCCESS" == data.status){
					cleanData();
					$("#businessContractNo").css("border","1px solid #dadada");
					$('#customerName').text(data.data.customerName);
					$('#customerId').val(data.data.customerId);
					$('#currency').text(data.data.currency);
					$('#businessProductDesc').text(data.data.businessProductDesc);
					$('#businessProduct').val(data.data.businessProduct);
				}else{
					cleanData();
					//<span id="errorMsg" class="k-widget k-tooltip k-tooltip-validation k-invalid-msg" data-for="exa-num" role="alert" style="position: relative; top: 0px; left: 0px;"><span class="k-icon k-warning"></span></span>
					$("#businessContractNo").attr("style","border:1px solid #ff6600 !important;width:150px");
					$('#businessContractNo').after('<span id="errorMsg" class="k-widget k-tooltip k-tooltip-validation k-invalid-msg" data-for="exa-num" role="alert" style="position: relative; top: 0px; left: 0px;"><span class="k-icon k-warning"></span>'+data.message+'</span>');
				}
			},
			error : function() {
				cleanData();
				$("#businessContractNo").attr("style","border:1px solid #ff6600 !important;width:150px");
				$('#businessContractNo').after('<span id="errorMsg" class="k-widget k-tooltip k-tooltip-validation k-invalid-msg" data-for="exa-num" role="alert" style="position: relative; top: 0px; left: 0px;"><span class="k-icon k-warning"></span>'+"网络异常"+'</span>');
			}
		});
	});
	
	var subflag = true;
	// 增加提交事件
	$("#submitBtn").click(function(){
		submitDate(subflag);
	});
	
	// 新增应收账款弹窗（第一步）
	$("body").on("click", ".add-appoint-btn", function() {
		$(".open-sec-btn").click();
		$(".k-widget.k-window").css("top", "30%");
		$("#cusSearch").click(function(){
			initInvModle();
		});
		initInvModle();
	});
	
	// 新增选择页面子额度可输入控制
	$("body").on("change", ".checked-btn", function() {
		var attr = $(this).is(":checked");
		if (attr == true) {
			$(".for-check-one").removeAttr("disabled", "disabled");
		} else {
			$(".for-check-one").attr("disabled", "disabled");
		}
	});

	// 新增账款信息（弹窗2）
	$("body").on("click", ".go-appoint-pop", function() {
		var custName = $("input[name='client-choose']:checked").val();
		var custId = $("input[name='client-choose']:checked").attr("custId");
		if(custId == undefined || custName == undefined){
			$(".open-message-btn").click();
			$("#tip-box-window .dlg-notice").addClass("notice-icon01");
			$("#tip-box-window .notice-content").text("请选择交易对手");
			return;
		}
		closeThePop();
		$(".open-appoint-btn").click();
		$("#invCustName").text(custName);
		$("#invCustId").val(custId);

		moneyNum();
		datePicker();
		
		// 表单验证
		var validator = $("#appoint-form").kendoValidator({
			rules : {
				invNoFormateMsg : invNoNullMsg.formateRule,
				invNoMsg : invNoUpateMsg.rule,
				dateMsg : dateMsg.formateRule,
				financingRatio:financingRatio.rule
			},
			messages : {
				invNoFormateMsg : invNoNullMsg.formateRuleMessage,
				invNoMsg : invNoUpateMsg.ruleMessage,
				dateMsg : dateMsg.formateRuleMessage,
				financingRatio:financingRatio.ruleMessage
			},
			needRuleAttrbute : false
		}).data("kendoValidator");
		
		$("#appoint-btn").click(function() {
			if (!validator.validate()) {
				return;
			}

			opAppoint();
		});
	});
	
	// 新增费用信息
	$("body").on("click", ".add-cost-btn", function() {
		$(".open-cost-btn").click();
		moneyNum();
		// 表单验证
		var validator = $("#cost-form").kendoValidator().data("kendoValidator");
		$("#cost-btn").click(function() {
			if (!validator.validate()) {
				return;
			}
			opCost();
		});
	});

	// 修改账款信息
	$("body").on("click", ".ch-appoint-btn", function() {
		var $index = amendClientRowRead("appoint-tb");
		if ($index != undefined) {
			$(".open-appoint-btn").click();
			var $td = $("#appoint-tb tr").eq($index).find("td");

			$(".appoint-ch-id").val($index);
			$(".ap-name").text($td.eq(0).text());
			$(".ap-num").val($td.eq(1).text());
			$(".ap-ratio").val($td.eq(2).text().substring(1).parseMoney());
			$(".ap-money").val($td.eq(3).text().substring(1).parseMoney());
			$(".ap-scale").val($td.eq(4).text());
			$(".time-one").val($td.eq(5).text());
			$(".time-two").val($td.eq(6).text());
			$("#invCustId").val($td.eq(7).text());
			$("#invNo").val($td.eq(1).text());
			moneyNum();
			datePicker();
			// 表单验证
			var validator = $("#appoint-form").kendoValidator({
				rules : {
					invNoNullMsg : invNoNullMsg.formateRule,
					invNoMsg : invNoUpateMsg.rule,
					dateMsg : dateMsg.formateRule,
					financingRatioUpdate:financingRatioUpdate.rule
				},
				messages : {
					invNoNullMsg : invNoNullMsg.formateRuleMessage,
					invNoMsg : invNoUpateMsg.ruleMessage,
					dateMsg : dateMsg.formateRuleMessage,
					financingRatioUpdate:financingRatioUpdate.ruleMessage
				},
				needRuleAttrbute : false
			}).data("kendoValidator");
			
			$("#appoint-btn").click(function() {
				if (!validator.validate()) {
					return;
				}
				opAppoint();
			});
		}
	});

	// 修改费用信息
	$("body").on("click", ".ch-cost-btn", function() {
		var $index = amendClientRowRead("cost-tb");
		if ($index != undefined) {
			$(".open-cost-btn").click();
			moneyNum();
			var $td = $("#cost-tb tr").eq($index).find("td");

			$(".co-class").val($td.eq(0).text());
			$(".co-money").val($td.eq(1).text().substring(1).parseMoney());
			$(".cost-ch-id").val($index);

			// 表单验证
			var validator = $("#cost-form").kendoValidator().data(
					"kendoValidator");
			$("#cost-btn").click(function() {
				if (!validator.validate()) {
					return;
				}
				opCost();
			});
		}
	});

	// 删除账款信息
	$("body").on("click", ".del-appoint-btn", function() {
		removeClientRow("appoint-tb");
	});
	
	// 删除费用信息
	$("body").on("click", ".del-cost-btn", function() {
		removeClientRow("cost-tb");
	});
}
 
function moneyNum(){
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

function datePicker(){
	//初始化时间插件
	$(".time-one").datepicker({
		format : "yyyy-mm-dd",
		language : "zh-CN",
		autoclose : true,
		todayHighlight : true,
		weekStart : 0,
		firstDay : 0,
		onClose : function(selectedDate) {
			$("input.time-two").datepicker("option", "minDate", selectedDate);
		}
	}).on("show", function() {
		$("div.datepicker table thead .prev").html("");
		$("div.datepicker table thead .next").html("");
	});
	
	$(".time-two").datepicker({
		format : "yyyy-mm-dd",
		language : "zh-CN",
		autoclose : true,
		todayHighlight : true,
		weekStart : 0,
		firstDay : 0
	}).on("show", function() {
		$("div.datepicker table thead .prev").html("");
		$("div.datepicker table thead .next").html("");
	});
}

/**
 * 清除数据
 */
function cleanData() {
	$('#errorMsg').text('');
	$('#customerName').text('');
	$('#customerId').val('');
	$('#currency').text('');
	$('#businessProductDesc').text('');
	$('#businessProduct').val('');
}

  
/**
 * 表格操作（增加）
 */
function addClientRow(tab, trHtml) {
	var $tr = $("#" + tab + "");
	trHtml.prependTo($tr);
}

/**
 * 表格操作（判断修改的行，返回修改的行号）
 */ 
function amendClientRowRead(ckb) {
	var ckbs = $("input[name=" + ckb + "]:checked");
	if (ckbs.size() == 0) {
		$(".open-message-btn").click();
		$("#tip-box-window .dlg-notice").addClass("notice-icon01");
		$("#tip-box-window .notice-content").text("请选择需要修改的行！");
	} else if (ckbs.size() != 1) {
		$(".open-message-btn").click();
		$("#tip-box-window .dlg-notice").addClass("notice-icon01");
		$("#tip-box-window .notice-content").text("一次只能修改一行！");
	} else {
		var chTr_index = ckbs.parent().parent().index(); //用于标记修改的行,仅用于修改操作
		return chTr_index;
	}
}

/**
 * 表格操作（删除）
 */
function removeClientRow(ckb) {
	//获取选中的复选框，然后循环遍历删除
	var ckbs = $("input[name=" + ckb + "]:checked");
	if (ckbs.size() == 0) {
		$(".open-message-btn").click();
		$("#tip-box-window .dlg-notice").addClass("notice-icon01");
		$("#tip-box-window .notice-content").text("请选择要删除的行！");
		return;
	}
	
	confirmPopWindow.open().center();
    $("#confirm-box-window #operationObj").val();
    $("#confirm-box-window .notice-content").html("是否确认删除");
	
    $("#delete-confirm").click(function(){
    	ckbs.each(function() {
    		$(this).parent().parent().remove();
    	});
    	//删除后更新序号
    	var len = $("#" + ckb + " tr").length;
    	for (var i = 0; i < len; i++) {
    		$("#" + ckb + " tr:eq(" + i + ") th:last").html(i + 1);
    	}
		closeThePop();
	});
}

/**
 * 关闭弹窗
 */
function closeThePop() {
	$(".k-overlay").hide();
	$(".k-window").hide();
}

/**
 * 新增/修改账款信息核心操作
 */
function opAppoint() {
	var $index = $(".appoint-ch-id").val(),
	$ind = $("#appoint-tb tr").length + 1, 
	$name = $(".ap-name").text(),
	$num = $(".ap-num").val(), 
	$ratio = $(".ap-ratio").val().formatMoney(),
	$money = $(".ap-money").val().formatMoney(),
	$scale = $(".ap-scale").val(),
	$timeOne = $(".time-one").val(),
	$timeTwo = $(".time-two").val()
    
	html = $('<tr class="client-add-tr">' + '<th><input type="checkbox" class="input-new" name="appoint-tb"></th>'
			+ '<th>'+ $ind + '</th>' + '<td name="counterparty">' + $name+ '</td>' + '<td name="invoiceNo">' + $num
			+ '</td>' + '<td name="nominvoiceAmt">￥' + $ratio + '</td>' + '<td name="invoiceAmt">￥' + $money + '</td>'
			+ '<td name="financingRatio">' + $scale + '</td>' + '<td name="invoiceDate">' + $timeOne + '</td>' + '<td name="dueDate">'
			+ $timeTwo + '</td> '+'<td style="display:none" name="counterpartyId">' + $("#invCustId").val() +'</td>' + '</tr>');
	
	closeThePop();
	if ($index == '') {
		addClientRow("appoint-tb", html);
	} else {
		var $td = $("#appoint-tb tr").eq($index).find("td");
		$td.eq(0).html($name);
		$td.eq(1).html($num);
		$td.eq(2).html($ratio);
		$td.eq(3).html($money);
		$td.eq(4).html($scale);
		$td.eq(5).html($timeOne);
		$td.eq(6).html($timeTwo);
	}
}

/**
 * 新增/修改费用信息核心操作
 */
function opCost() {
	var $index = $(".cost-ch-id").val(), $ind = $("#cost-tb tr").length + 1, 
	$class = $(".co-class").val(), $money = $(".co-money").val().formatMoney(),
	html = $('<tr class="client-add-tr">' + '<th><input type="checkbox" class="input-new" name="cost-tb"></th>'
			+ '<th>' + $ind + '</th>' + '<td name="expenseSubject">' + $class + '</td>' + '<td name="amountStr">￥'
			+ $money + '</td>' + '</tr>');
	closeThePop();
	if ($index == '') {
		addClientRow("cost-tb", html);
	} else {
		var $td = $("#cost-tb tr").eq($index).find("td");
		$td.eq(0).html($class);
		$td.eq(1).html($money);
	}
}

function initInvModle() {
	var sequence = 0;
    //新建弹窗表格初始化
    var grid = $("#client-out-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
            pageSize: 5,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
            		data: { customerName: $("#custName").val(), businessContractNo:$("#businessContractNo").val()},
            		type : "POST",
            		url: basepath + "/asset/invoice/queryBusinessCounterpartyDTO"
                }
            },
        serverPaging: true,
		serverSorting: true,
		serverFiltering: true,
        //解析远程响应的数据
		schema:{
			data: "data",
			total: function(data){
				var total = data.total;
				if(total == 0){
					$(".accountManageGrid .k-grid-header-wrap").css("overflow-x","auto");
				}else{
					$(".accountManageGrid .k-grid-header-wrap").css("overflow-x","");
				}
				return total;
			}
		  }
		},
        columns: [{
            field: "sequence",
            title: "选择用户",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(item){
                return  "<input type='radio' name='client-choose' value='" + item.counterparty + "' custId='" + item.counterpartyId + "' class='client-radio'>";
               
            }
        },{
            field: "counterpartyId",
            title: "客户编号",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template : function(data){
				sequence ++;
				return sequence;
			},	
        },{
            field: "counterparty",
            title: "客户名称",
            width: 300,
            attributes:{
                style:"text-align:center"
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
    grid.data("kendoGrid").pager.bind('change', function(){
		sequence = 0;
	});
}

/**
 * 提交数据
 */
function submitDate(subflag) {
	// 应收账款信息
	var invList = getListData("appoint-tb");
	// 费用列表
	var feeList = getListData("cost-tb");
	// 表单数据
	var fromData = {
		businessContractNo : $("#businessContractNo").val(),
		customerId : $("#customerId").val(),
		customerName : $("#customerName").text(),
		currency : $("#currency").text(),
		businessProduct : $("#businessProduct").val(),
		actionMode : "ADD" // 新增
	};
	$.extend(fromData,{"invoiceInfoDTOs":invList.data});
	$.extend(fromData,{"expenseDTOs":feeList.data});
	
	if($("#businessContractNo").val()==''){
		$("#errorMsg").remove();
		$("#businessContractNo").attr("style","border:1px solid #ff6600 !important;width:150px");
		$('#businessContractNo').after('<span id="errorMsg" class="k-widget k-tooltip k-tooltip-validation k-invalid-msg" data-for="exa-num" role="alert" style="position: relative; top: 0px; left: 0px;"><span class="k-icon k-warning"></span>'+"业务合同号不能为空"+'</span>');
		return;
	}
	if($("#customerId").val()==''||$("#customerName").text()==''||$("#currency").text()=='')
		return;
	if(subflag){
		subflag = false;
		$.ajax({
			type : "POST",
			url : basepath + '/asset/invoice/updateInvManage',
			data : JSON.stringify(fromData),
			dataType : "json",
			contentType:'application/json;charset=utf-8',
			success : function(data) {
				if(data.status=="SUCCESS"){
					showSuccessWin(data.message);
					setTimeout("gotoInvoiceListPage()",1000);
				}else{
					subflag = true;
					$(".open-message-btn").click();
					$("#tip-box-window .dlg-notice").addClass("notice-icon01");
					$("#tip-box-window .notice-content").text(data.message);
				}
			},
			error : function(data) {
				subflag = true;
				showAlertWin("网络异常");
			}
		});
	}
}

function gotoInvoiceListPage(){
	location.href = encodeURI(basepath + '/asset/invoice/invoiceList');}

/**
 * 动态表单数据
 * @param title
 * @returns
 */
function getListData(title) {
	var count = $("#"+ title).find("tr").length;
	if(count <= 0) {
		return {};
	}
	var data = "{\"data\" : ["; 
	$("#"+ title).find("tr").each(function(j){
		data += "{"
	 	var aa = this;
		var len = $(aa).find("td").length;
	   	$(aa).find("td").each(function(i){
			if($(this).attr("name")){		               
				data += "\"" + $(this).attr("name") + "\":\"" + $(this).text() +"\"";
				if(i != len-1) {
					data += ","
				}
			}
		});
		data += "}"
		if(count !=  j-1) {
			data += ","
		}
		
	});
	data += "]";     
	if(data.length != 1){                                  
		data = data.substring(0, data.length-2) +  "]";      
	}
	data += "}";
	data = eval("(" + data + ")");	
	return data;
}