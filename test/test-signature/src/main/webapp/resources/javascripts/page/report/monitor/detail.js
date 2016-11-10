$(function(){
	
	$(".weekBtn").click(function(){
		var startDay = new Date();
		subtractDays(startDay, 7);
		var endDay = new Date();
		subtractDays(endDay, 1);
		$("#startDate").val(startDay.format("yyyy-MM-dd"));
		$("#endDate").val(endDay.format("yyyy-MM-dd"));
	});
	
	$(".monthBtn").click(function(){
		var startDay = new Date();
		startDay = subtractMonths(startDay, 1);
		var endDay = new Date();
		subtractDays(endDay, 1);
		$("#startDate").val(startDay.format("yyyy-MM-dd"));
		$("#endDate").val(endDay.format("yyyy-MM-dd"));
	});
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		searchList();
    	}
    });
	
	initPage();
	
	//选择日期范围是今天之前的前30天
    $("input.datepicker.fromDate").datepicker({
        format: "yyyy-mm-dd",
        language: "zh-CN",
        autoclose: true,
        todayHighlight: true,
        weekStart: 0,
        firstDay: 0,
        minDate: "-1M",
        maxDate: "-1D",
        onClose: function (selectedDate) {
            $("input.datepicker.toDate").datepicker("option", "minDate", selectedDate);
        }
    });
    $("input.datepicker.toDate").datepicker({
        format: "yyyy-mm-dd",
        language: "zh-CN",
        autoclose: true,
        todayHighlight: true,
        weekStart: 0,
        firstDay: 0,
        minDate: "-1M",
        maxDate: "-1D",
        onClose: function (selectedDate) {
            $("input.datepicker.fromDate").datepicker("option", "maxDate", selectedDate);
        }
    });
}) 

//初始化页面
function initPage(){
	//点击"查询"
	$("#searchBtn").click(function(){
		searchList();
    });
	
	$(".monthBtn").click();
	searchList();
}

//查询所有list
function searchList(){
	searchMetricList();
	searchDataList();
}

//初始化指标表格
function searchMetricList(){
	$.post(
			basepath + "/report/monitor/detail/metric",
			{
				memberId: $.trim($("#memberId").val()),
				startDate: $.trim($("#startDate").val()),
				endDate: $.trim($("#endDate").val())
			},
			function(data) {
				if(data.status == "SUCCESS"){
					var template = kendo.template($('#template-table-metric').html());
					$('#monitor-tabMetric').empty().append(template(data));
				}
			}
		);
}

//初始化数据表格
function searchDataList(){
	$.post(
			basepath + "/report/monitor/detail/data",
			{
				memberId: $.trim($("#memberId").val()),
				startDate: $.trim($("#startDate").val()),
				endDate: $.trim($("#endDate").val())
			},
			function(data) {
				if(data.status == "SUCCESS"){
					var template = kendo.template($('#template-table-data').html());
					$('#monitor-tabData').empty().append(template(data));
				}
			}
		);
}