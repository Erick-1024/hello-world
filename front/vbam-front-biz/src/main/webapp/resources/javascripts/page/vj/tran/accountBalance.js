$(function(){
	initSearchButton();
	initExportButton();
	initRecentTimeButton();
	loadList();
});

function initSearchButton(){
	$('#searchBtn').click(function(){
		loadList();
	});
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		loadList();
    	}
    });
};
function initExportButton(){
	//点击“导出”
	$("body").on("click", "#exportBtn", exportAccountBalance);
};

function exportAccountBalance() {
	var tranType = $.trim($("#tranType").val());
	var tranState = $.trim($('input[name=tranState]').val());
	var tranStartDate = $.trim($('input[name=tranStartDate]').val());
	var tranEndDate = $.trim($('input[name=tranEndDate]').val());
	window.open(basepath + "/vj/tran/account/balance/export?tranType=" + tranType + "&tranState=" + tranState + "&tranStartDate=" + tranStartDate  + "&tranEndDate=" + tranEndDate  + "&page=0");
}

//初始化查询条件“交易日期-最近”按钮
function initRecentTimeButton(){
	$('.war-out.tran-date').click(function(){
		$(this).addClass('war-on');
		var value = $(this).attr("value");
		var startDay = new Date();
		if(value == "oneWeek")
			subtractDays(startDay, 6);
		if(value == "oneMonth"){
			startDay = subtractMonths(startDay, 1);
			addDays(startDay, 1);
		}
		if(value == "threeMonths"){
			startDay = subtractMonths(startDay, 3);
			addDays(startDay, 1);
		}
		$(".startDate").val(startDay.format("yyyy-MM-dd"));
		$(".endDate").val(new Date().format("yyyy-MM-dd"));
	});
};

function loadList() {
	$("#monitorSrl-grid").empty();
	//初始化数据表格
    $("#monitorSrl-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
            pageSize: 10,
            type: "json", //后台返回的数据类型
            method: "post",
            transport : {
				read : {
					type : "post",
					data: {
						tranType: $.trim($("#tranType").val()),
						tranState: $.trim($('input[name=tranState]').val()),
						tranStartDate: $.trim($('input[name=tranStartDate]').val()),
						tranEndDate: $.trim($('input[name=tranEndDate]').val())
				},
				url: basepath + "/vj/tran/account/balance/list"
			}
        },
        serverPaging: true,
		serverSorting: true,
		serverFiltering: true,
		schema : {
			data: "data",
			total: function(data){
				var total = data.totalNum;
				if(total == 0){
					$("#monitorSrl-grid .k-grid-header-wrap").css("overflow-x","auto");
				}else{
					$("#monitorSrl-grid .k-grid-header-wrap").css("overflow-x","");
				}
				return total;
				}
			}
		},
        columns: [{
            field: "canaTranSeq",
            title: "CANA交易流水号",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "customerName",
            title: "客户名称",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "identityCardNo",
            title: "身份证号",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "tranTypeDesc",
            title: "交易类型",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "stateDesc",
            title: "交易状态",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "amountStr",
            title: "金额",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "bankCheckDate",
            title: "交易时间",
            width: 100,
            attributes: {
                style: "text-align: center"
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