$(function(){
	
	$('.form-search-btn').click(function() {
		var grid = $("#monitorList-grid").data("kendoGrid");
		grid.setDataSource(getDataSource());
	});
	
	bindEntry();
	
	initList();
	
});

function bindEntry() {
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('.form-search-btn').click();
    	}
    });
}

function getDataSource(){
	return new kendo.data.DataSource({
		pageSize : 10,
		type : "json", // 后台返回的数据类型
		method : "post",
		transport : {
			read : {
				type : "post",
				url: basepath + "/asset/archivesManagement/querySpecialProgram",
				data: {
					specialProgramName: $.trim($('#specialProgramName').val()),
					underlyingAssetType: $.trim($('#underlyingAssetType').val()),
					status: $.trim($('#status').val())
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


function initList() {
	//初始化表格
	var grid = $("#monitorList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        columns: [{
            field: "id",
            title: "专项计划编号",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "specialProgramName",
            title: "专项计划名称",
            width: 220,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "underlyingAssetTypeDesc",
            title: "基础资产类型",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "managerName",
            title: "管理人名称",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "statusDesc",
            title: "状态",
            width: 60,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "updateTime",
            title: "最近更新日期",
            width: 140,
            attributes: {
                style: "text-align: center"
            },
            template: function(data) {
            	return new Date(data.updateTime).format("yyyy-MM-dd");
            }
        },{
            field: "measure",
            title: "操作",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	var edHtml = '';
            	if(sim_am_manage) {
            		edHtml += "<a class='userCenter-link' name='manage' href='" + basepath + "/asset/archivesManagement/goto/manage?id=" + data.id + "'>管理</a>";
            	}
                return edHtml;
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