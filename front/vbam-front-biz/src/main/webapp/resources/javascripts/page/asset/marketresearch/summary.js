var zoom;
var myChart;
$(function(){
	myChart = echarts.init(document.getElementById('main'));
	
	kendo.culture('zh-CN');
    $("#startDate").kendoDatePicker({
        start: "year",
        depth: "year",
        close: onClose,
        format: "yyyy-MM"
    });
    $("#endDate").kendoDatePicker({
    	start: "year",
    	depth: "year",
    	close: onClose,
    	format: "yyyy-MM"
    });
    //初始化消息弹窗
    tipPopWindow = new PopWindow({
        title: "提示",
        width: 420,
        reload: true,
        template: "#tipBox_template"
    }).init();
    //设置kendo日期控件部分样式
    $(".k-i-arrow-w").css("cssText","background-position:-16px -48px!important;");
    $(".k-i-arrow-e").css("cssText","background-position:-16px -16px!important;");
	
    //加载 echarts
	echartDataSummary(zoom);
	//缩放
	$(".dataZoom").click(function(){
		if($(this).data("time") == 'all'){
			echartDataSummary(10000);
		}else{
			zoom = parseInt($(this).data("time"));
			echartDataSummary(zoom);
		}
	});
	
	//更多
	$(".pandect-more").click(function(){
		location.href = encodeURI(basepath + '/asset/marketResearch/marketDataSearchList');
	});
});
//日期变更
function onClose(){
	echartDataSummary(zoom);
}

function echartDataSummary(zoom){
	$.ajax({
		type : "POST",
		url : basepath + '/asset/marketResearch/summaryZoom',
		data : {
			startDate : $.trim($("#startDate").val()),
			endDate : $.trim($("#endDate").val())
		},
		dataType : "json",
		success : function(data) {
			if(data.status=="SUCCESS"){
				echartShow(data.data,zoom);
			}else{
				tipPopWindow.open().center();
				$("#tip-box-window .dlg-notice").addClass("notice-icon03");
				$("#tip-box-window .notice-content").text(data.message);
			}
		},
		error : function(data) {
			tipPopWindow.open().center();
			$("#tip-box-window .dlg-notice").addClass("notice-icon03");
			$("#tip-box-window .notice-content").text("网络异常");
		}
	});
}


function echartShow (data,zoom){
	
    // 指定图表的配置项和数据
	var enums = data.enumArray;
	var yAxisData = new Array();
	var yDataList=data.list;
	for(var i=0;i<yDataList.length;i++){
		if(i == yDataList.length-1){
			var param = { name:enums[i],type:'line', yAxisIndex: 1,smooth : true,smoothMonotone:'x',data:data.list[i] };
			yAxisData.push(param);
		}else{
			var param = { name:enums[i],type:'bar',barWidth : 18,stack: '市场研究',data:data.list[i] };
			yAxisData.push(param);
		}
	}

	var option = {
//			color: colors,
			toolbox: {
				bottom:'5',
	            right:'10',
		        feature: {
//		            dataView: {show: true, readOnly: false},
		            saveAsImage: {show: true,
		            	title :'下载',
		            	name :'企业资产证券化产品发行统计'
		            }
		        }
		    },
		    tooltip : {
		        trigger: 'axis',		   // 坐标轴触发
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		        	type : 'shadow'        // 数据的指示器，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {                      //图例组件展现了不同系列的标记(symbol)，颜色和名字。可以通过点击图例控制是否显示。
		    	bottom:'20',
                height:'60',
		    	orient: 'horizontal',     	    //图例的排列方式 vertical：垂直向下，  horizontal：水平
//		    	selectedMode: 'multiple',		//默认 true
//		    	inactiveColor:'#ccc',
		        data:enums						// ''或者'\n' 图例换行
		    },
		    grid: {
		    	show : true,
		        left: 80,
		        right: 80,
		        bottom: 140,
		        top:10,
		        containLabel: true			//grid 区域是否包含坐标轴的刻度标签
		    },
		    xAxis :{
	            type : 'category',
		        data : data.array
	        },
		    yAxis : [
		        {
		        	show : true,
		            type : 'value',
		            nameLocation : 'middle',
		            nameGap : 60,					//轴名称与轴线之间的距离
		            name : '发行量（亿）',
		            splitLine: {show: false},
//		            splitNumber : 6				//类目轴无效 没有强制效果
		        },
		        {
		            type: 'value',
		            name: '发行产品数量（个）',
		            nameLocation : 'middle',
		            splitLine: {show: false},
		            nameRotate : 270,
		            nameGap : 50,
//		            splitNumber : 6,
//		            position: 'right',         //第一个默认left，第二个默认第一个的另一侧
		            axisLabel: {
		                formatter: '{value}'
		            }
		        }
		        
		    ],
		    dataZoom :[
		               {
					    	type :'slider',
					    	show : true,
					    	xAxisIndex: [0],
					    	startValue: 0,
				            endValue: zoom,
				            filterMode:'filter',
				            bottom : 100
		               },
		               {
		                   type: 'inside',     //滚轮滑动
		                   xAxisIndex: [0],
		                   startValue: 0,
		                   endValue: zoom,
		                   bottom : 100
		               }
		               ],
		    
		    series : yAxisData
	};

	myChart.clear;
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
};
