$(function(){
	
		//点击按钮自动刷新
		closeRefresh();
        //初始化申请弹窗
        new PopWindow(".limit-add", {
            title: "申请额度",
            width: 800,
            reload: true,
            template: "#template-resetPwd-five"
        }).init();
        //初始化修改额度弹窗  update-credit
        new PopWindow(".update-credit", {
            title: "修改额度",
            width: 800,
            reload: true,
            template: "#template-resetPwd-ch"
        }).init();
        $("body").on("click", ".limit-add", function() {
        	moneyFormat();
        	bindEvent();
        	var validator = $("#credit-aplay-form").kendoValidator({
        		rules: {
        			businessContractNoIsExistRule: businessContractNoRule1.rule,
        		},
        		messages: {
        			businessContractNoIsExistRule: businessContractNoRule1.ruleMessage,
        		},
        		needRuleAttrbute : false
        	}
        	).data("kendoValidator");
        });
        $("body").on("click", ".update-credit", function() {
        	moneyFormat();
        });
        
        $("body").on("click", ".limit-change", function() {
        	//发请求判断是否被修改
        	var $this =$(this);
        	$("#creditId-m").val($this.attr("data-id"));
        	$.ajax({
    		    type : "POST",
    			data :{
    				creditId : $this.attr("data-id"),
    			},
    			url : basepath + "/asset/credit/checkForModify",
    			dataType : 'json',
    			success : function(data,status) {
    				if(data.status =="SUCCESS"){
    					if(data.data.allowModify ==true){
    						$(".update-credit").click();
		            		$("#creditId-m").val($this.attr("data-id"));
		                	datePicker();
		            		$.ajax({
		            		    type : "POST",
		            			data :{
		            				creditId :$this.attr("data-id"),
		            			},
		            			url : basepath + "/asset/credit/get/creditById",
		            			dataType : 'json',
		            			success : function(data,status) {
		            				if(data.status =="SUCCESS"){
		            					//校验初始化
		            				 	bindEvent();
		            		        	var validator = $("#credit-update-form").kendoValidator({
		            		        		rules: {
		            		        			businessContractNoIsExistRule: businessContractNoRule.rule,
		            		        		},
		            		        		messages: {
		            		        			businessContractNoIsExistRule: businessContractNoRule.ruleMessage,
		            		        		},
		            		        		needRuleAttrbute : false
		            		        	}).data("kendoValidator");
		            		        	//修改额度数据初始化
		            					var creditMode = $("#credit-M").data("kendoDropDownList");
		            					creditMode.select($("#credit-M option[value="+data.data.creditMode+"]").index());
		            					$("#businessC").val(data.data.businessContractNo);
		            		        	$("#curr").val(data.data.currency);
		            		        	$("#totalL").val((data.data.totalLimitStr).formatMoney());
		            		        	$("#exp").val((data.data.totalExpenseStr).formatMoney());
		            		        	$("#effectiveD").val(data.data.effectiveDate);
		            		        	$("#dueD").val(data.data.dueDate);
		            		        	$("#status").text(data.data.statusDesc);
		            		        	$("#credit-Id").val(data.data.id);
		            				}else {
		            					showAlertWin("修改失败");
		            				}
		            			},
		            		});
		            		//判断是单笔授信放款之后的额度是否有权限修改
		            		if(data.data.allowBusinessContractNo==false){
		            			$("#businessC").prop("disabled","disabled");
		            		}
		            		if(data.data.allowMode==false){
		            			$("#credit-M").data("kendoDropDownList").enable(false);
		            		}
		            		if(data.data.allowCurreny==false){
		            			$("#curr").data("kendoDropDownList").enable(false);
		            		}
		            		if(data.data.allowTotalLimit==false){
		            			$("#totalL").prop("disabled","disabled");
		            		}
		            		if(data.data.allowExpense==false){
		            			$("#exp").prop("disabled","disabled");
		            		}
		            		if(data.data.allowEffectiveDate==false){
		            			$("#effectiveD").prop("disabled","disabled");
		            		}
		            		if(data.data.allowDueDate==false){
		            			$("#dueD").prop("disabled","disabled");
		            		}
    					}else{
    						showAlertWin("没有权限修改该额度");
    					}
    				}else{
    					showAlertWin("检验可修改查询失败");
    				}
    			},
    		});
        });
        //初始化确认弹窗
        confirmPopWindow = new PopWindow({
            title: "提示",
            width: 420,
            reload: true,
            template: "#template-notice"
        }).init();
        statusPopWindow = new PopWindow({
            title: "提示",
            width: 420,
            reload: true,
            template: "#template-status"
        }).init();
      //初始化确认弹窗
        successPopWindow = new PopWindow({
            title: "提示",
            width: 420,
            reload: true,
            template: "#template-notice-two"
        }).init();
        failPopWindow = new PopWindow({
            title: "提示",
            width: 420,
            reload: true,
            template: "#template-notice-three"
        }).init();
        //初始化成功提示弹窗
        tipPopWindow = new PopWindow({
            title: "提示",
            width: 420,
            reload: true,
            template: "#tipBox_template"
        }).init();
        //冻结提示
        $("body").on("click", ".freeze", function(e) {
    		$.ajax({
    		    type : "POST",
    			data :{
    				creditId : $("#id-credit").val(),
    			},
    			url : basepath + "/asset/credit/freeze",
    			dataType : 'json',
    			success : function(data,status) {
    				if(data.status =="SUCCESS"){
    					closeThePop();
    					tipPopWindow.open().center();
    					$("#tip-box-window .dlg-notice").removeClass("notice-icon01");
    		            $("#tip-box-window .dlg-notice").addClass("notice-icon02");
    		            $("#tip-box-window .notice-content").text("操作成功");
    		            window.location.reload(); 
    				}else {
    					closeThePop();
    					tipPopWindow.open().center();
    					$("#tip-box-window .dlg-notice").removeClass("notice-icon02");
    		            $("#tip-box-window .dlg-notice").addClass("notice-icon01");
    		            $("#tip-box-window .notice-content").text(data.message);
    				}
    			},
    		});
        });
        //解冻
     $("body").on("click", ".unfreeze", function(e) {
 		$.ajax({
		    type : "POST",
			data :{
				creditId : $("#id-credit").val(),
			},
			url : basepath + "/asset/credit/unfreeze",
			dataType : 'json',
			success : function(data,status) {
				if(data.status =="SUCCESS"){
					closeThePop();
					tipPopWindow.open().center();
					$("#tip-box-window .dlg-notice").removeClass("notice-icon01");
		            $("#tip-box-window .dlg-notice").addClass("notice-icon02");
		            $("#tip-box-window .notice-content").text("操作成功");
		            window.location.reload(); 
				}else {
					closeThePop();
					tipPopWindow.open().center();
					$("#tip-box-window .dlg-notice").removeClass("notice-icon02");
		            $("#tip-box-window .dlg-notice").addClass("notice-icon01");
		            $("#tip-box-window .notice-content").text(data.message);
				}
			},
		});
           
        });
     
     //撤销
     $("body").on("click", ".revoke", function(e) {
  		$.ajax({
 		    type : "POST",
 			data :{
 				creditId : $("#id-credit").val(),
 			},
 			url : basepath + "/asset/credit/revoke",
 			dataType : 'json',
 			success : function(data,status) {
 				if(data.status =="SUCCESS"){
 					closeThePop();
					tipPopWindow.open().center();
					$("#tip-box-window .dlg-notice").removeClass("notice-icon01");
		            $("#tip-box-window .dlg-notice").addClass("notice-icon02");
		            $("#tip-box-window .notice-content").text("操作成功");
		            window.location.reload(); 
 				}else {
 					closeThePop();
					tipPopWindow.open().center();
					$("#tip-box-window .dlg-notice").removeClass("notice-icon02");
		            $("#tip-box-window .dlg-notice").addClass("notice-icon01");
		            $("#tip-box-window .notice-content").text(data.message);
 				}
 			},
 		});
            
         });
     //作废
     $("body").on("click", ".cancel", function(e) {
  		$.ajax({
 		    type : "POST",
 			data :{
 				creditId : $("#id-credit").val(),
 			},
 			url : basepath + "/asset/credit/cancel",
 			dataType : 'json',
 			success : function(data,status) {
 				if(data.status =="SUCCESS"){
 					closeThePop();
					tipPopWindow.open().center();
					$("#tip-box-window .dlg-notice").removeClass("notice-icon01");
		            $("#tip-box-window .dlg-notice").addClass("notice-icon02");
		            $("#tip-box-window .notice-content").text("操作成功");
		            window.location.reload(); 
 				}else {
 					closeThePop();
					tipPopWindow.open().center();
					$("#tip-box-window .dlg-notice").removeClass("notice-icon02");
		            $("#tip-box-window .dlg-notice").addClass("notice-icon01");
		            $("#tip-box-window .notice-content").text(data.message);
 				}
 			},
 		});
            
         });
        //点击"冻结"
        $("body").on("click", ".freeze-link", function(e){
            confirmPopWindow.open().center();
            $("#confirm-box-window #operationObj").val($(this).attr("data"));
            $("#confirm-box-window .notice-content").html("是否确认冻结此金额？<input type='hidden' id ='id-credit' value="+$(this).attr("data-id")+"><br/>申请金额：" + $(this).closest('tr').find('td').eq(3).text());
            $(".confirm-link").addClass("freeze");
            $(".confirm-link").removeClass("unfreeze revoke cancel");
        });

        //点击"解冻"
        $("body").on("click", ".unfreeze-link", function(e){
            confirmPopWindow.open().center();
            $("#confirm-box-window #operationObj").val($(this).attr("data"));
            $("#confirm-box-window .notice-content").html("是否确认解冻此金额？<input type='hidden' id ='id-credit' value="+$(this).attr("data-id")+"><br/>申请金额：" + $(this).closest('tr').find('td').eq(3).text());
            $(".confirm-link").addClass("unfreeze");
            $(".confirm-link").removeClass("revoke cancel freeze");
        });

        //点击"撤销"
        $("body").on("click", ".repeal-link", function(e){
            confirmPopWindow.open().center();
            $("#confirm-box-window #operationObj").val($(this).attr("data"));
            $("#confirm-box-window .notice-content").html("确认要撤销此金额? <input type='hidden' id ='id-credit' value="+$(this).attr("data-id")+"><br/>申请金额：" + $(this).closest('tr').find('td').eq(3).text());
            $(".confirm-link").addClass("revoke");
            $(".confirm-link").removeClass("unfreeze cancel freeze");
        });

        //点击"作废"
        $("body").on("click", ".nullify-link", function(e){
            confirmPopWindow.open().center();
            $("#confirm-box-window #operationObj").val($(this).attr("data"));
            $("#confirm-box-window .notice-content").html("确认要作废此金额？<input type='hidden' id ='id-credit' value="+$(this).attr("data-id")+"><br/>申请金额：" + $(this).closest('tr').find('td').eq(3).text());
            $(".confirm-link").addClass("cancel");
            $(".confirm-link").removeClass("unfreeze revoke freeze");
        });

        //时间空间 （针对弹窗中时间空间失效的情况）
        $("body").delegate(".time-one", "focusin", function(){
            //选择日期范围是今天之前(<=今天)
            $(this).datepicker({
                format: "yyyy-mm-dd",
                language: "zh-CN",
                autoclose: true,
                todayHighlight: true,
                weekStart: 0,
                firstDay: 0,
                onClose: function (selectedDate) {
                	 var date1 = getNowFormatDate();
                     var date2 = selectedDate;
                     var $status = dateCompare(date1,date2);
                     if($status==1){
                         selectedDate = date1
                     }
                    $("input.time-two").datepicker("option", "minDate", selectedDate);
                }
            }).on("show", function () {
                $("div.datepicker table thead .prev").html("");
                $("div.datepicker table thead .next").html("");
            });
            //选择日期范围是大于等于生效日
            $(".time-two").datepicker({
                format: "yyyy-mm-dd",
                language: "zh-CN",
                autoclose: true,
                todayHighlight: true,
                weekStart: 0,
                firstDay: 0
            }).on("show", function () {
                $("div.datepicker table thead .prev").html("");
                $("div.datepicker table thead .next").html("");
            });
        });
        
        getCreditManageInfo();

});   

//输入金额格式化
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
			 var date1 = getNowFormatDate();
             var date2 = selectedDate;
             var $status = dateCompare(date1,date2);
             if($status==1){
                 selectedDate = date1
             }
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
//时间选择控件
function dateCompare(date1,date2){
    date1 = date1.replace(/\-/gi,"/");
    date2 = date2.replace(/\-/gi,"/");
    var time1 = new Date(date1).getTime();
    var time2 = new Date(date2).getTime();
    if(time1 > time2){
        return 1;
    }else if(time1 == time2){
        return 2;
    }else{
        return 3;
    }
}
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

function getCreditManageInfo(){
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
                    	  type: "post",
                    	  data: {
                    		  customerId : $.trim($("#customerId").val()),
      					},
      					url: basepath + "/asset/credit/search/credit"
                  }
              },
            serverPaging: true,
  			serverSorting: true,
  			serverFiltering: true,
  			schema: {
  				data: "data",
  				total: function(data) {
  					var total = data.totalNum;
  	  				if(total == 0){
  	  					$("#manual-repayGrid .k-grid-header-wrap").css("overflow-x","auto");
  	  				}else {
  	  					$("#manual-repayGrid .k-grid-header-wrap").css("overflow-x","");
  					}
  	  				return total;
  				}
  			}
          },
              columns: [{
                  field: "businessContractNo",
                  title: "业务合同号",
                  width: 100,
                  attributes: {
                      style: "text-align: center"
                  }
              },{
                  field: "creditModeDesc",
                  title: "额度种类",
                  width: 100,
                  attributes:{
                      style:"text-align:center"
                  }
              },{
                  field: "currency",
                  title: "币种",
                  width: 80,
                  attributes: {
                      style: "text-align: center"
                  }
              },{
                  field: "totalLimitStr",
                  title: "申请金额",
                  width: 100,
                  attributes: {
                      style: "text-align: center"
                  },
                  template: function (data){
                	  return data.totalLimitStr.formatMoney();
                  }
              },{
                  field: "availableLimitStr",
                  title: "可用余额",
                  width: 100,
                  attributes: {
                      style: "text-align: center"
                  },
                  template: function (data){
                	  return data.availableLimitStr.formatMoney();
                  }
              },{
                  field: "effectiveDate",
                  title: "生效日期",
                  width: 100,
                  attributes: {
                      style: "text-align: center"
                  }
              },{
                  field: "dueDate",
                  title: "到期日期",
                  width: 100,
                  attributes: {
                      style: "text-align: center"
                  }
              },{
                  field: "totalExpenseStr",
                  title: "申请费用",
                  width: 100,
                  attributes: {
                      style: "text-align: center"
                  },
                  template: function (data){
                	  return data.totalExpenseStr.formatMoney();
                  }
              },{
                  field: "statusDesc",
                  title: "状态",
                  width: 80,
                  attributes: {
                      style: "text-align: center"
                  }
              },{
                  field: "measure",
                  title: "操作",
                  width: 260,
                  attributes: {
                      style: "text-align: center"
                  },
                  template: function(item){
                	  var edHtml="";
                	  if(credit_info_modify){
	                	  if(item.allowModify==true){
	                		  edHtml +="<a class='comRow-link limit-change' href='javascript:void(0);' data-id='"+item.id+"' data-status="+ item.statusDesc+">修改</a>"
	                	  }
                	  	}
                	  if(credit_info_modify){
	                	  if(item.allowFreeze==true){
	                		  edHtml +="<a class='comRow-link freeze-link' href='javascript:void(0);' data-id='"+item.id+"'>冻结</a>"
	                	  }
                	  }
                	  if(credit_info_modify){
                		  if(item.allowUnFreeze==true){
                    		  edHtml +="<a class='comRow-link unfreeze-link' href='javascript:void(0);' data-id='"+item.id+"'>解冻</a>"
                    	  }
                	  }
                	  if(credit_info_modify){
                		  if(item.allowRevoke==true){
                    		  edHtml +="<a class='comRow-link repeal-link' href='javascript:void(0);' data-id='"+item.id+"'>撤销</a>"
                    	  } 
                	  }
                	  if(credit_info_modify){
                		  if(item.allowCancel==true){
                    		  edHtml +="<a class='comRow-link nullify-link' href='javascript:void(0);' data-id='"+item.id+"'>作废</a>"
                    	  } 
                	  }
                	  if(credit_info_history){
                		  edHtml +="<a class='comRow-link' href='"+ basepath +"/asset/credit/audit/listpage?customerId=" + item.customerId +"&creditId="+item.id+"'>查看历史</a>";
                	  }
                      return edHtml;
                  }
              }],
              pageable: {
                  pageSizes: false,  //设置每页显示行数
                  pageSize: 10,
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
		    grid.data("kendoGrid").pager.bind('change', function(){
				sequence = 0;
			});

      }  
       
//关闭弹窗
function closeThePop(){
    $(".k-overlay").hide();
    $(".k-window").hide();
}
//关闭刷新页面
function closeRefresh(){
	  $("body").on("click","#bnt-id",function(){
		  	closeThePop();
		  	window.location.reload(); 
		 })
}


function isValueExist(businessContractNo,creditId, url) {
	var result = $.ajax({
		url: basepath + "/asset/credit/" + url,
		async: false,
		type: 'post',
		data: {
			businessContractNo: businessContractNo,
			creditId:creditId
		}
	}).responseText;
	if(result == "true")
		flag = false;
	else
		flag = true;
	return flag;
};

function bindEvent() {
	setValidator($("#businessContractNo"), "required", true, businessContractNoRule.required);
	setValidator($("#businessContractNo"), "pattern", businessContractNoRule.pattern, businessContractNoRule.message);
	setValidator($("#businessC"), "required", true, businessContractNoRule.required);
	setValidator($("#businessC"), "pattern", businessContractNoRule.pattern, businessContractNoRule.message);
}

