 $(function(){
	
	 var yundays = yundaDate();
	 kendo.culture('zh-CN');
	    $("#startDate").kendoDatePicker({
	    	value: yundays,
	        start: "year",
	        depth: "year",
	        format: "yyyy-MM"
	    });
	 $("#endDate").kendoDatePicker({
		 	value: new Date(),
	    	start: "year",
	    	depth: "year",
	    	format: "yyyy-MM"
	    });
	 $(".k-i-arrow-w").css("cssText","background-position:-16px -48px!important;");
	 $(".k-i-arrow-e").css("cssText","background-position:-16px -16px!important;");
	 searchMetricList();
	
	 //
	 var datepicker = $("#startDate").data("kendoDatePicker");
	 
	$(".sixMonth").click(function(){
		
		 var startDays = '',
		    date = new Date(),
		    year = parseInt(date.getFullYear()),
		    month = parseInt(date.getMonth() -4);
		if(month>0){
		    startDays = year+'-'+month;
		}else{
		    startDays = (year-1)+'-'+(month+12);
		}
		 datepicker.value(startDays);
		searchMetricList();
	});
	
	$(".oneYear").click(function(){
		 var startDays = '',
		    date = new Date(),
		    year = parseInt(date.getFullYear()),
		    month = parseInt(date.getMonth() -10);
		if(month>0){
		    startDays = year+'-'+month;
		}else{
		    startDays = (year-1)+'-'+(month+12);
		}
		 datepicker.value(startDays);
		
		searchMetricList();
	});
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		searchList();
    	}
    });
	//点击
	$("#searchBtn").click(function(){
		searchMetricList();
    });
}) 

//初始化指标表格
function searchMetricList(){
	$.post(
			basepath + "/yundaex/monitor/detail/metric",
			{
				memberId: $.trim($("#memberId").val()),
				startDate: $.trim($("#startDate").val()),
				endDate: $.trim($("#endDate").val())
			},
			function(data) {
				if(data.status == "SUCCESS"){
					var template = kendo.template($('#template-table-metric').html());
					$('#monitor-tabMetric').empty().append(template(data));
					var template1 = kendo.template($('#template-table-data').html());
					$('#monitor-tabData').empty().append(template1(data));
				}
			}
		);
}

function yundaDate() {
	 var startDays = '',
	    date = new Date(),
	    year = parseInt(date.getFullYear()),
	    month = parseInt(date.getMonth() -7);
	if(month>0){
	    startDays = year+'-'+month;
	}else{
	    startDays = (year-1)+'-'+(month+12);
	}
	return startDays;
}
