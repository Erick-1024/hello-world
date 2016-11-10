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
                    	specialProgramName: $.trim($("#specialProgramName").val()),
                    	underlyingAssetType: $.trim($("#underlyingAssetType").val()),
                    	managerName: $.trim($("#managerName").val()),
                    	startDate : $.trim($(".startDate").val()),
                    	endDate : $.trim($(".endDate").val()),
                    	status: $.trim($("#status").val())
                    },
                    type : "POST",
                    url:  basepath + "/asset/pool/assetpoolList"
                
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
            field: "id",
            title: "专项计划编号",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "specialProgramName",
            title: "专项计划名称",
            width: 200,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "underlyingAssetTypeDesc",
            title: "基础资产类型",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "managerName",
            title: "管理人名称",
            width: 200,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "estimateEstablishmentDate",
            title: "预计成立日期",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "originAssetpoolScale",
            title: "初始资产池规模",
            width: 150,
            attributes: {
                style: "text-align: center"
            },template: function(data){
                return data.originAssetpoolScale.formatMoney();
            }
        },{
            field: "statusDesc",
            title: "状态",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
        },{
            field: "accountIncome",
            title: "当日应还收入",
            width: 120,
            attributes: {
                style: "text-align: center"
            },template: function(data){
            	return data.accountIncome.formatMoney();
            }
        },{
            field: "accountPrincipal",
            title: "当日应还本金",
            width: 120,
            attributes: {
                style: "text-align: center"
            },template: function(data){
            	return data.accountPrincipal.formatMoney();
            }
        },{
            field: "accountAmount",
            title: "当日应还总额",
            width: 120,
            attributes: {
                style: "text-align: center"
            },template: function(data){
            	return data.accountAmount.formatMoney();
            }
        },{
            field: "paidIncome",
            title: "当日实际收入",
            width: 120,
            attributes: {
                style: "text-align: center"
            },template: function(data){
            	return data.paidIncome.formatMoney();
            }
        },{
            field: "paidPrincipal",
            title: "当日实际本金",
            width: 120,
            attributes: {
                style: "text-align: center"
            },template: function(data){
            	return data.paidPrincipal.formatMoney();
            }
        },{
            field: "paidAmount",
            title: "当日实际总额",
            width: 120,
            attributes: {
                style: "text-align: center"
            },template: function(data){
            	return data.paidAmount.formatMoney();
            }
        },{
            field: "unpaidAmount",
            title: "当日未偿总额",
            width: 120,
            attributes: {
                style: "text-align: center"
            },template: function(data){
            	return data.unpaidAmount.formatMoney();
            }
        },{
            title: "操作",
            width: 150,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	var url="";
            	
        		if(manageAuth){
        			url += '<a class="comRow-link" href="' + basepath + '/asset/pool/assetpoolManage?id=' + data.id +'&status='+ data.status +'" >管理</a>';
        		}
        		if(packetAuth && data.status=="CREATE" && parseInt(data.originAssetpoolScale) > 0){
        			url += '<a class="comRow-link" href="'+basepath+ '/asset/pool/assetpoolPacket?id='+ data.id +'" >封包</a>' ;
        		}
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