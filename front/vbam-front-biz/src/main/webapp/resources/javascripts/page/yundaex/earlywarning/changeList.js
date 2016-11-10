$(function(){
	
	generatekendoGrid();
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('.form-search-link').click();
    	}
    });
	
	//点击"查询"
	$(".form-search-link").click(function(){
		generatekendoGrid();
    });
	
});

function generatekendoGrid() {
	var inTimeEndVal = $.trim($("[name='inTimeEndv']").val());
	if(inTimeEndVal!="")
		inTimeEndVal = new Date(DateAdd("d", "1", new Date(inTimeEndVal))).format("yyyy-MM-dd");
	
	var outTimeEndVal = $.trim($("[name='outTimeEnd']").val());
	if(outTimeEndVal!="")
		outTimeEndVal = new Date(DateAdd("d", "1", new Date(outTimeEndVal))).format("yyyy-MM-dd");
	
	$("#monitorSrl-grid").empty();
	//初始化数据表格
    $("#monitorSrl-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource : {
			pageSize : 10,
			type : "json", // 后台返回的数据类型
			method : "post",
			transport : {
				read : {
					type : "post",
					data: {
						companyName: $.trim($("[name='companyName']").val()),
						inTimeStart: $.trim($("[name='inTimeStart']").val()),
						inTimeEnd: inTimeEndVal,
						outTimeStart: $.trim($("[name='outTimeStart']").val()),
						outTimeEnd: outTimeEndVal,
						earlywarningLevel: $.trim($("[name='earlywarningLevel']").val())
				},
				url: basepath + "/yundaex/earlywarning/query/changeList"
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
            field: "financeId",
            title: "客户编号",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "financeCompany",
            title: "客户名称",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "inTime",
            title: "转入时间",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data) {
            	return new Date(data.inTime).format("yyyy-MM-dd hh:mm");
            }
        },{
            field: "levelDesc",
            title: "预警状态",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
                var val = kendo.htmlEncode(data.level);
                var res = "";
                if(val == "yellow"){
                    res = "<span class='monitor-yellow'>" + data.levelDesc + "</span>";
                }else if(val == "orange"){
                    res = "<span class='monitor-orange'>" + data.levelDesc + "</span>";
                }else if(val == "red"){
                    res = "<span class='monitor-red'>" + data.levelDesc + "</span>";
                }else{
                    res = "-";
                }
                return res;
            }
        },{
            field: "outTime",
            title: "转出时间",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data) {
            	if(data.outTime == null)
            		return '-';
            	else
            		return new Date(data.outTime).format("yyyy-MM-dd hh:mm");
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