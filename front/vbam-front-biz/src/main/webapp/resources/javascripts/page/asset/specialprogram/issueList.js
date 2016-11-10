$(function (){
	getSpecialProgramList();
	
	//点击搜索
	$("#search-id").click(function() {
		getSpecialProgramList();
	});
	
	//回车事件
	document.onkeydown=keyDownSearch;  
    function keyDownSearch(e) {    
        var theEvent = e || window.event;    
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
        if (code == 13) {    
        	$("#search-id").click();
            return false;    
        }    
        return true;    
    } 
});    


function getSpecialProgramList(){
	 $("#monitorList-grid").empty();
		var sequence = 0;
		//初始化数据表格
	    var grid = $("#monitorList-grid").kendoGrid({
	        selectable: "row",  //设置可选择数据行
	        dataSource:{
	            type: "json", //后台返回的数据类型
	            transport: {
	            	read: {
						type: "post",
						data: {
							specialProgramName :$.trim($("#specialProgramName").val()),
							underlyingAssetType :$.trim($("#underlyingAssetType").val()),
							managerName :$.trim($("#managerName").val()),
							startEstimateEstablishmentDate :$.trim($("#startEstimateEstablishmentDate").val()),
							endEstimateEstablishmentDate :$.trim($("#endEstimateEstablishmentDate").val()),
							status :$.trim($("#statusDesc").val())
						},
						url: basepath+"/asset/specialprogram/queryList"
					}
	            },
	            serverPaging: true,
				serverSorting: true,
				serverFiltering: true,
				schema: {
					data: "data",
					total: function(data) {
						var total = data.totalNum;
		  				if(total == 0){
		  					$("#manual-repayGrid .k-grid-header-wrap").css("overflow-x","auto");
		  				}else {
		  					$("#manual-repayGrid .k-grid-header-wrap").css("overflow-x","");
						}
		  				return total;
					}
				}
	        },
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
                width: 150,
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
                width: 150,
                attributes: {
                    style: "text-align: center"
                }
            },{
                field: "establishmentDate",
                title: "成立日期",
                width: 120,
                attributes: {
                    style: "text-align: center"
                }
            },{
                field: "gross",
                title: "资产池总规模",
                width: 140,
                attributes: {
                    style: "text-align: center"
                },
                template :function(data){
                	return data.gross.formatMoney();
                }
            },{
                field: "statutoryDueDate",
                title: "到期日期",
                width: 120,
                attributes: {
                    style: "text-align: center"
                }
            },{
                field: "statusDesc",
                title: "状态",
                width: 100,
                attributes: {
                    style: "text-align: center"
                }
            },{
                field: "measure",
                title: "操作",
                width: 120,
                attributes: {
                    style: "text-align: center"
                },
                template: function(item){
                	var url = "";
                	if(specialProgram_add){
                		if(item.allowIssue == true){
                			url += "<a class='comRow-link' href='"+basepath+"/asset/specialprogram/issueAdd?specialProgramId="+item.id+"'>成立</a>";
                		}
                	}
                	if(specialProgram_manage){
                		if(item.allowManage == true){
                			url += "<a class='comRow-link' href='"+basepath+"/asset/specialprogram/issueUpdate?specialProgramId="+item.id+"'>管理</a>";
                		}
                	}
                	return url;
                }
            }],
            pageable: {
                pageSizes: false,  //设置每页显示行数
                pageSize: 10,
                page: 1,
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
	    grid.data("kendoGrid").pager.bind('change', function(){
			sequence = 0;
		});
}