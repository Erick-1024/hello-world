$(function(){
	initLimitStatusButton();
	initSearchButton();
	initActivePopWindow();
	showTable();
})

function initLimitStatusButton(){
	$("body").on("click",".status-normal.limit-status,.status-normal.applicant-type",function() {
		$(this).addClass("status-chk").removeClass("status-default");
		$(this).siblings().addClass("status-default").removeClass("status-chk");
		showTable();
	});
};

function initSearchButton(){
	$('.form-search-link').click(function(){
		showTable();
	});
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		showTable();
    	}
    });
};

function showTable(){
	var endDate;
	if($('.endDate').val()!="")
		endDate = DateAdd("d", "1", new Date($.trim($('.endDate').val()).replace(/-/g, "/"))).format("yyyy-MM-dd");
	
	 //初始化数据表格
	$('#monitorLimit-grid').empty();
    $("#monitorLimit-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
            pageSize: 10,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                    data: { companyName:$.trim($('#companyName').val()),
                    		customerType: $.trim($(".applicant-type.status-chk").attr("value")),
                    		limitStart:$.trim($('#limitStart').val()),
                    		limitEnd:$.trim($('#limitEnd').val()),
                    		effectiveDateStart:$.trim($('.startDate').val()),
                    		effectiveDateEnd:endDate,
                    		limitStatus:$('.limit-status.status-chk').attr('value')
                    	  },
                    type : "POST",
                    url: basepath+"/credit/limit/customerList"
                }
            },
            serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
            schema: {
				data: "data",
				total: function(data){
					var total = data.totalNum;
					if(total == 0){
						$("#monitorLimit-grid .k-grid-header-wrap").css("overflow-x","auto");
					}else{
						$("#monitorLimit-grid .k-grid-header-wrap").css("overflow-x","");
					}
					return total;
				}
			}
        },
        columns: [{
            field: "memberId",
            title: "客户编号",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "companyName",
            title: "客户名称",
            width: 120
        },{
            field: "customerType",
            title: "客户类型",
            width: 60,
            attributes: {
                style: "text-align: center"
            },
            template:function(data){
            	if(data.customerType==null)
            		return "-";
            	return ApplyApplicantType[data.customerType];
            }
        },{
            field: "outCustomerName",
            title: "真旅客户名称",
            width: 120
        },{
            field: "creditMode",
            title: "授信方式",
            width: 80,
            attributes: {
                style: "text-align: center"
            },
            template:function(data){
            	return CreditMode[data.creditMode];
            }
        },{
            field: "totalLimitLongValue",
            title: "总额度",
            width: 80,
            attributes: {
                style: "text-align: center"
            },
            template:function(data){
            	return fmoney(data.totalLimitLongValue/100);
            }
        },{
            field: "usedLimitLongValue",
            title: "已使用额度",
            width: 80,
            attributes: {
                style: "text-align: center"
            },
            template:function(data){
            	return fmoney(data.usedLimitLongValue/100);
            }
        },{
            field: "effectiveDate",
            title: "额度生效日",
            width: 80,
            attributes: {
                style: "text-align: center"
            },
            template:function(data){
            	if(data.effectiveDate==null)
            		return "-";
            	return new Date(data.effectiveDate).format("yyyy-MM-dd");
            }
        },{
            field: "limitStatus",
            title: "额度状态",
            width: 80,
            attributes: {
                style: "text-align: center"
            },template: function(item){
                var val = kendo.htmlEncode(CreditLimitStatus[item.limitStatus]);
                var res = "";
                if( val == "未激活"){
                    res = "<span class='limitStatus monitor-red'>"+val+"</span>";
                }else{
                	res = "<span class='limitStatus'>"+val+"</span>";
                }
                return res;
            }
        },{
        	field: "limitStatus",
            title: "操作",
            sortable: false,
            width: 80,
            attributes:{
                style: "text-align: center"
            },
            template:function(item){
            	var val = kendo.htmlEncode(CreditLimitStatus[item.limitStatus]);
                var res = "";
                if( val == "未激活"&&activeLimit){
                    res = "<a class='manual-chk limitAct-link' href='javascript:void(0);'>激活</a>";
                }
                return res;
            },
            editable: "popup"
        },{
            field: "limitId",
            hidden: true,
            template: function(item) {
            	return '<span class="limitId">' + item.limitId + '</span>';
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
}
function initActivePopWindow(){
	var _parent = '';
    //激活弹窗
    $("body").on("click", "#monitorLimit-grid .limitAct-link", function(e){
        $(".window-overlay").removeClass("hidden");
        $("#template-confirm").removeClass("hidden");
        _parent = $(e.target).closest("tr");
    });
    //关闭弹窗
    $(".closeHref, .back-link , .close-window").click(function(){
    	$(".window-overlay").addClass("hidden");
        $(".template-limitAct").addClass("hidden");
//        $("#template-fail").addClass("hidden");
    });
    //确认
    $(".template-link").click(function(){
    	$("#template-confirm").addClass("hidden");
    	
    	$.ajax({
    		type:"POST",
    		dataType:"json",
    		data:{limitId:$(_parent).find(".limitId").text()},
    		url:basepath+"/credit/limit/active",
    		success:function(response){
    			if(response.status=='SUCCESS'){
    				$(".window-overlay").addClass("hidden");
    				 $(_parent).find(".limitAct-link").addClass("hidden");
    				 $(_parent).find(".limitStatus").text("正常");
    				 $(_parent).find(".limitStatus").removeClass("monitor-red")
    			}else{
    				$("#template-fail .notice-content").text(response.message);
    				$("#template-fail").removeClass("hidden");
    			}
    		},
    		error:function(response){
    			$("#template-fail").removeClass("hidden");
    		}
    	});
        
    });

};
