//声明全局变量用于保存弹窗中选中的值的value
var idSelect = "";
var nameSelect = "";
$(function() {
	// 弹窗初始化
	openPop();
	
	
	// 专项计划编号单选事件
	$("body").on("click", ".interests-choose", function() {
		idSelect = $(this).val();
		nameSelect = $(this).attr("data-name");
	});
	
	// 整行选择
	$("body").on("click", "#interests-grid tbody tr", function() {
		$(this).find("input[name=interests-choose]").prop("checked", true);
		idSelect = $(this).find("input[name=interests-choose]").val();
	});
	
	//弹框查询点击事件
	$("body").on("click","#popSearchBtn",function(){
		var grid = $("#interests-grid").data("kendoGrid");
		grid.setDataSource(getDataSource());
	})
});

//基础资产修改页面——弹窗确定点击事件
function confirmSpecialProgram(){
	if (idSelect == "") {
		$(".message-pop").click();
		$("#tip-box-window .dlg-notice").addClass("notice-icon01");
		$("#tip-box-window .notice-content").text("请选择专项计划！");
	} else {
		$("#special-plan").val(idSelect);
		$("#specialProgramName").val(nameSelect);
		$(".k-window-action").click()
	}
}

//点击按钮——弹出专项计划弹框
function loadSpecialProgramPop() {
	$(".limit-add-btn").click();
	$(".k-widget.k-window").css({
		"top" : "25%"
	});
	interestsPopGrid();
}

// 弹窗初始化
function openPop() {
	// 选择专项计划弹窗
	new PopWindow(".limit-add-btn", {
		title : "专项计划",
		width : 800,
		reload : true,
		template : "#template-resetPwd-one"
	}).init();
}

//专项计划编号弹出框表格分页事件
function onDataBoundOne() {
	if (idSelect == "")
		return;
	var $length = $("input[name=interests-choose]");
	for (var i = 0; i < $length.length; i++) {
		if ($length.eq(i).val() == idSelect) {
			$length.eq(i).prop("checked", "checked");
		}
	}
}

//生成专项计划列表
function interestsPopGrid() {
	var grid = $("#interests-grid").kendoGrid({
	    selectable: "row",  //设置可选择数据行
	    sortable: true,  //列排序
	    dataBound : onDataBoundOne,
	    columns: [{
	        field: "id",
	        title: "选择",
	        width: 120,
	        attributes: {
	            style: "text-align: center"
	        },
            template: function(item){
                return "<input type='radio' name='interests-choose' value='"+item.id+"' data-name='"+item.specialProgramName+"' class='client-radio interests-choose'>";
            }
	    },{
	        field: "id",
	        title: "专项计划编号",
	        width: 80,
	        attributes:{
	            style:"text-align:center"
	        }
	    },{
	        field: "specialProgramName",
	        title: "专项计划名称",
	        width: 150,
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
	}).data("kendoGrid");
	grid.setDataSource(getDataSource());
}

//获取专项计划数据
function getDataSource(){
	return new kendo.data.DataSource({
		pageSize : 5,
		type : "json", // 后台返回的数据类型
		method : "post",
		transport : {
			read : {
				type : "post",
				url: basepath + "/asset/underlyingAsset/getProgramList",
				data: {
					specialProgramName: $.trim($('#specialProgramNameSearch').val())
				}
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
    });
}

