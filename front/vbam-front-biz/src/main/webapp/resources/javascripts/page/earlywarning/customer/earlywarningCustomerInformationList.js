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
	$("#monitorInfo-grid").empty();
	//初始化数据表格
    $("#monitorInfo-grid").kendoGrid({
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
						companyName: $.trim($("[name='companyName']").val()),
                	 	earlyWarningLevel: $.trim($("[name='earlyWarningLevel']").val())
				},
				url: basepath + "/earlywarning/customer/query/earlyWarningCustomerInformation"
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
					$("#monitorInfo-grid .k-grid-header-wrap").css("overflow-x","auto");
				}else{
					$("#monitorInfo-grid .k-grid-header-wrap").css("overflow-x","");
				}
				return total;
				}
			}
		},
        columns: [{
            field: "financeId",
            title: "客户编号",
            width: 80,
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
            field: "outCustomerName",
            title: "外部客户名称",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "updateTime",
            title: "预警时间",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(data) {
            	return new Date(data.updateTime).format("yyyy-MM-dd hh:mm");
            }
        },{
            field: "earlywaringLevelDesc",
            title: "当前预警状态",
            width: 80,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
                var val = data.level;
                var res = "";
                if(val == "yellow"){
                    res = "<span class='monitor-yellow'>"+data.earlywaringLevelDesc+"</span>";
                }else if(val == "orange"){
                    res = "<span class='monitor-orange'>"+data.earlywaringLevelDesc+"</span>";
                }else if(val == "red"){
                    res = "<span class='monitor-red'>"+data.earlywaringLevelDesc+"</span>";
                }else{
                    res = "-";
                }
                return res;
            }
        },{
            title: "操作",
            sortable: false,
            width:80,
            attributes: {
                style: "text-align: center"
            },
            template: function(data) {
            	if(adjuestAuth) {
            		return '<a class="comRow-link" href="' + basepath + '/earlywarning/customer/Typelist?memberId=' + data.financeId + '&outCustomerId=' + data.outCustomerId + '">调整预警</a>';
            	} else {
            		return '';
            	}
            },
            editable: "popup"
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