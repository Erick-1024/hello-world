var pageSize = 10;
var digitalPattern = "^\\d{1,}$"
$(function(){
	//初始化弹窗
	new PopWindow(".deployPop", {
        title: "保理业务配置",
        width: 600,
        reload: true,
        template: "#tipBox_template"
    }).init();
	
    $("body").on("click",".deployPop",function(){
    	var counterpartyId = $(this).parent().next().text();
    	var inConfigFlag = $(this).parent().next().next().text();
    	if('false' == inConfigFlag){
    		var counterparty = $(this).parent().prev().prev().prev().prev().text();
    		$("td[name='counterparty']").text(counterparty);
    		$("input[name='counterpartyId']").val(counterpartyId);
    	}else{
    		getExistConfig(counterpartyId);
    	}
//    	setValidator($("span[name='loanNoForAdd']"), "required", true, loanNoRule.required);
    	setValidator($("input[name='days']"), "pattern", digitalPattern, "贷款期限只能为数字类型");
    	setValidator($("input[name='daysTwo']"), "pattern", digitalPattern, "回购期限只能为数字类型");
        var validator = $("#deployForm").kendoValidator().data("kendoValidator");
        $("#deployForm-btn").click(function() {
            if(!validator.validate()) {
                return;
            }
            //此处是验证通过的执行的方法
            saveCounterpartyConfig();
        });
    });
    
    loadData();
})

function getExistConfig(counterpartyId){
	$.ajax({
		type: "POST",
		url: basepath + "/homsom/config/get/counterpartyConfig/HOMSOM",
		data: {counterpartyId : counterpartyId},
		dataType: "json",
		success: function(data){
			if(data.status == "SUCCESS"){
				$("td[name='counterparty']").text(data.data.counterparty);
	    		$("input[name='counterpartyId']").val(data.data.counterpartyId);
	    		$("input[name='days']").val(data.data.loanPeriod);
	    		$("input[name='daysTwo']").val(data.data.buybackPeriod);
	    		var type = $("#deployForm select.repayment").data("kendoDropDownList");
	            type.select($("#deployForm select.repayment option[value="+data.data.repaymentMethod+"]").index());
			}else{
				showAlertWin("系统繁忙，请稍后重试");
			}
		}
	});
}

function saveCounterpartyConfig(){
	$.ajax({
		type: "POST",
		url: basepath + "/homsom/config/save/counterpartyConfig/HOMSOM",
		data: { counterpartyId : $.trim($("input[name='counterpartyId']").val()),
				counterparty : $.trim($("td[name='counterparty']").text()),
				loanPeriod : $.trim($("input[name='days']").val()),
				buybackPeriod : $.trim($("input[name='daysTwo']").val()),
				repaymentMethod : $.trim($("select.repayment").val())},
		dataType: "json",
		success: function(data){
			if(data.status == "SUCCESS"){
				$(".k-i-close").click();
				showSuccessWin("保存成功");
				loadData();
			}else{
				showAlertWin("系统繁忙，请稍后重试");
			}
		}
	});
}

function loadData(){
	 //初始化表格
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
                	$.ajax({
                    	data: paraData,
                    	type : "POST",
                    	dataType:"json",
                    	url: basepath+"/homsom/config/get/list/HOMSOM",
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
            field: "counterparty",
            title: "交易对手名称",
            width: 220,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "loanPeriod",
            title: "贷款期限（天）",
            width: 80,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "buybackPeriod",
            title: "回购期限（天）",
            width: 80,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "repaymentMethod",
            title: "还款方式",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	if(data.repaymentMethod == "ORDER"){
            		return "订单款回款还款";
            	}else if(data.repaymentMethod == "MATURITY"){
            		return "到期一次性还本及支付收益";
            	}else if(data.repaymentMethod == "MONTHLY"){
            		return "按月支付收益到期还本";
            	}else{
            		return "";
            	}
            }
        },{
            field: "measure",
            title: "操作",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(item){
                var edHtml = "<a class='userCenter-link deployPop' href='javascript:void(0);'>修改</a>";
                return edHtml;
            }
        },{
        	field: "counterpartyId",
            title: "counterpartyId",
            hidden: true
        },{
        	field: "inConfigFlag",
            title: "inConfigFlag",
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