$(function(){
	initSubmitButton();
	tableSearch();
});

/**
 * 初始化按钮事件
 */
function initSubmitButton() {
	// 搜索
	$("#form-search").click(function() {
		tableSearch();
	});
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		tableSearch();
    	}
    });
}


function tableSearch() {
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
                    data: { 
                    	supervisionAgency: $.trim($("#supervisionAgency").val()),
                    	underlyingAssetType: $.trim($("#underlyingAssetType").val()),
                    	valueDate: $.trim($("#valueDate").val()),
                    	originator : $.trim($("#originator").val()),
                    	issuer : $.trim($("#issuer").val())
                    },
                    type : "POST",
                    url:  basepath + "/asset/marketResearch/marketDataSearchList"
                
            }
        },
        //解析远程响应的数据
        serverPaging: true,
		serverSorting: true,
		serverFiltering: true,
		schema: {
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
	sortable: false,
	groupable: false,
        columns: [{
            field: "projectName",
            title: "产品名称",
            width: 400,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "supervisionAgencyDesc",
            title: "监管机构",
            width: 150,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "underlyingAssetType",
            title: "基础资产类型",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "issueTotalAmount",
            title: "总金额（亿）",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "issueDate",
            title: "发行日期",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "aaaaverageInterestRate",
            title: "AAA利率",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "priorityAverageInterestRate",
            title: "优先级平均利率",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
        },{
            field: "originator",
            title: "发起机构",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "issuer",
            title: "发行人",
            width: 200,
            attributes: {
                style: "text-align: center"
            }
        },{
            title: "操作",
            width: 150,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	var url="";
        			url += '<a class="comRow-link" href="' + basepath + '/asset/marketResearch/detail?id=' + data.id +'" >详情</a>';
                return url;
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            buttonCount: 10,  //显示页数
            pageSize: 10,
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