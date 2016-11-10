var pageSize = 10;

$(function(){
	$("body").on("click",".add-client-popup",function(){
		getCustomerForWindow();
        $(".client-popup").show();
    });
    //关闭新建企业资料弹窗
    $("body").on("click",".client-back",function(){
        $(".client-popup").hide();
    });
	getCustomerInfo();
	$("#seachBtn").click(function(){
		getCustomerInfo();
	});
//	getCustomerForWindow();
	$("#searchBtnWindow").click(function(){
		getCustomerForWindow();
	});
	
	$("#client-out-grid").on("click", "tbody tr", function(){
		$(this).find("input[name=client-choose]").prop("checked",true);
	});
	//新建
	$("#createBtn").click(function(){
		var id = $('input[name=client-choose]:checked').val();
		if(id == 'undefined' || id == null){
			showAlertWin("未选中任何客户");
			return;
		};
		location.href = basepath+"/asset/enterpriseInfo/add?customerId="+id;
	})
	
	$("body").on("keydown", function(e){
		var theEvent = e || window.event;    
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
    	//回车按钮事件
    	if(code == 13){
    		if($("input[name='customerChooseWindow']").is(":visible")) {
    			$("#searchBtnWindow").click();
    		} else {
    			$("#seachBtn").click();
    		}
    	}
    });
});
    
function getCustomerInfo(){
	var sequence = 0;
	$("#monitorList-grid").empty();
	//初始化数据表格
    var grid = $("#monitorList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
//        sortable: true,  //列排序
        dataSource:{
//            pageSize: 10,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: function(option){
                	var paraData = {};
                	var submitState = $.trim($(".submitState select").val())
                	var materialState = [submitState];
                	if(submitState == null || submitState == ''){
                		materialState = ["PARTSUBMIT","SUBMITTED"];
                	}
                	paraData.customerName = $.trim($(".customerName").val());
                	paraData.customerType = $.trim($(".customerType select").val()) == "" ? null:$.trim($(".customerType select").val());
                	paraData.page = option.data.page;
                	paraData.enterpriseMaterialState = materialState;
                	paraData.pageSize = option.data.pageSize;
                	$.ajax({
                		contentType:"application/json",
                    	data: JSON.stringify(paraData),
                    	type : "POST",
                    	dataType:"json",
                    	url: basepath+"/asset/enterpriseInfo/list",
                    	success:function(data){
                    		option.success(data);
                    	}
                	});
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
            field: "sequence",
            title: "序号",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template : function(data){
				sequence ++;
				return sequence;
			},
        },{
            field: "customerName",
            title: "客户名称",
            width: 300,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "customerTypeDesc",
            title: "客户类型",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "enterpriseMaterialState",
            title: "资料提交状态",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "updateTime",
            title: "最后更新时间",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(data) {
            	if(data.updateTime != null && data.updateTime != ""){
					return new Date(data.updateTime).format("yyyy-MM-dd hh:mm:ss");
            	}else{
            		return "";
            	}
			}
        },{
            field: "measure",
            title: "操作",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(item){
            	var link = "";
            	if(enterprise_info_detail)
            		link += "<a class='comRow-link' href='"+basepath+"/asset/enterpriseInfo/queryEnterpisrInfo?customerId="+item.id+"' target='_blank'>详情</a>";
            	if(enterprise_info_modify){
            		link += "<a class='comRow-link' href='"+basepath+"/asset/enterpriseInfo/modify?customerId="+item.id+"'>修改</a>";
            	}
            	return link;
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            pageSize: pageSize,
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

function getCustomerForWindow(){
	var sequence = 0;
	$("#client-out-grid").empty();
	//初始化数据表格
    var grid = $("#client-out-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
//        sortable: true,  //列排序
        dataSource:{
//            pageSize: 5,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
            	read: function(option){
                	var paraData = {"enterpriseMaterialState":["UNSUBMIT"]};
                	paraData.customerName = $.trim($(".customerNameWindow").val());
//                	paraData.customerType = $.trim($(".customerType select").val()) == "" ? null:$.trim($(".customerType select").val());
                	paraData.page = option.data.page;
                	paraData.pageSize = option.data.pageSize;
                	$.ajax({
                		contentType:"application/json",
                    	data: JSON.stringify(paraData),
                    	type : "POST",
                    	dataType:"json",
                    	url: basepath+"/asset/enterpriseInfo/list",
                    	success:function(data){
                    		option.success(data);
                    	}
                	});
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
            field: "sequence",
            title: "选择用户",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(item){
                return "<input type='radio' name='client-choose' value='"+item.id+"' class='client-radio'>";
            }
        },{
            field: "sequence",
            title: "序号",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template : function(data){
				sequence ++;
				return sequence;
			},
        },{
            field: "customerName",
            title: "客户名称",
            width: 300,
            attributes:{
                style:"text-align:center"
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            pageSize: pageSize,
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