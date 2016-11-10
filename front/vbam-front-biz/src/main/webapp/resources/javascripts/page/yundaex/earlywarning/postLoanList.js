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
	
    //点击“明细”
    $("#monitorList-grid").kendoTooltip({
        filter: "a.item-link",
        content: {
        	url: basepath + "/yundaex/earlywarning/tip" 
        },
        width: 650,
        position: "bottom",
        requestStart: function(e) {
            e.options.url = kendo.format(basepath + "/yundaex/earlywarning/tip?memberId="+$(e.target.parent().siblings()[0]).text()+"&outCustomerId="+e.target.attr('outCustomerId'));
        }
    });
    
    $('.form-search-out').click(function() {
    	window.open(basepath + "/yundaex/earlywarning/export?companyName=" + $.trim($("[name='companyName']").val()) + "&earlyWarningLevel=" + $.trim($("[name='earlyWarningLevel']").val()));
    });
    
});

function generatekendoGrid() {
	$("#monitorList-grid").empty();
	$("#monitorList-grid").kendoGrid({
		selectable : "row", // 设置可选择数据行
		sortable : true, // 列排序
		dataSource : {
			pageSize : 10,
			type : "json", // 后台返回的数据类型
			method : "post",
			transport : {
				read : {
					type : "post",
					data: {
						companyName: $.trim($("[name='companyName']").val()),
                	 	earlyWarningLevel: $.trim($("[name='earlyWarningLevel']").val())
				},
				url: basepath + "/yundaex/earlywarning/query/postLoanlist"
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
					$("#monitorList-grid .k-grid-header-wrap").css("overflow-x","auto");
				}else{
					$("#monitorList-grid .k-grid-header-wrap").css("overflow-x","");
				}
				return total;
				}
			}
		},
		columns : [{
            field: "memberId",
            title: "客户编号",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "companyName",
            title: "客户名称",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "outCustomerName",
            title: "网点客户名称",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "limit",
            title: "额度",
            width: 80,
            attributes: {
                style: "text-align: center"
            },
            template: function(data) {
            	return (data.limit/100).toString().formatMoney();
            }
        },{
            field: "residualLimit",
            title: "剩余额度",
            width: 80,
            attributes: {
                style: "text-align: center"
            },
            template: function(data) {
            	return (data.residualLimit/100).toString().formatMoney();
            }
        
        },{
            field: "earlywarningLevelDesc",
            title: "风险等级",
            width: 80,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
                var val = kendo.htmlEncode(data.earlywarningLevel);
                var res = "";
                if(val == "yellow"){
                    res = "<span class='monitor-yellow'>"+data.earlywarningLevelDesc+"</span>";
                }else if(val == "orange"){
                    res = "<span class='monitor-orange'>"+data.earlywarningLevelDesc+"</span>";
                }else if(val == "red"){
                    res = "<span class='monitor-red'>"+data.earlywarningLevelDesc+"</span>";
                }else{
                    res = "-";
                }
                return res;
            }
        },{
            field: "earlywarningLevel",
            title: "预警指标",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
                var val = kendo.htmlEncode(data.earlywarningLevel);
                var res = "";
                if(val == "yellow"){
                    res = "轻度 | <a class='item-link' href='javascript:void(0);' level='" + val + "' outCustomerId='" + data.outCustomerId + "'>明细</a>";
                }else if(val == "orange"){
                    res = "中度 | <a class='item-link' href='javascript:void(0);' level='" + val + "' outCustomerId='" + data.outCustomerId + "'>明细</a>";
                }else if(val == "red"){
                    res = "高度 | <a class='item-link' href='javascript:void(0);' level='" + val + "' outCustomerId='" + data.outCustomerId + "'>明细</a>";
                }else{
                    res = "- | -";
                }
                return res;
            }
        },{
            field: "action",
            title: "预警措施",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        }],
		pageable : {
			pageSizes : false, // 设置每页显示行数
			buttonCount : 5, // 显示页数
			messages : {
				display : "共<span class='sumData'>{2}</span>条数据",
				empty : "没有数据",
				page : "页",
				of : "/ {0}",
				itemsPerPage : "条/页",
				first : "第一页",
				previous : "前一页",
				next : "下一页",
				last : "最后一页"
			}
		}
	});

}