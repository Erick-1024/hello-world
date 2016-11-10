var pageSize = 10;
var sequence = 0;
$(function(){
	
	
	//回车按钮事件
	document.onkeydown=keyDownSearch;  
    function keyDownSearch(e) {    
        var theEvent = e || window.event;    
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
        if (code == 13) {    
        	$("#seachBtn").click();
            return false;    
        }    
        return true;    
    } 
    
	initGrid();
	$("#seachBtn").click(function(){
		getCustomerInfo();
	});

});

function initGrid(){
	$("#monitorList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
//        sortable: true,  //列排序
        dataSource:{
//            pageSize: 10,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: function(option){
                	var paraData = {};
                	paraData.customerName = $.trim($(".customerName").val());
                	paraData.customerType = $.trim($(".customerType select").val()) == "" ? null:$.trim($(".customerType select").val());
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
            field: "industryDesc",
            title: "所属行业",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "cityDesc",
            title: "所属地区",
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
            	var link = "";
            	if(customer_info_detail)
            		link += "<a class='comRow-link' href='"+basepath+"/asset/customer/customerDetail?customerId="+item.id+"'>详情</a>";
            	if(customer_info_modify){
            		link += "<a class='comRow-link' href='"+basepath+"/asset/customer/customerUpdate?customerId="+item.id+"'>修改</a>";
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
	$("#monitorList-grid").data("kendoGrid").pager.bind('change', function(){
		sequence = 0;
	});
}

function getCustomerInfo(){
	var grid = $("#monitorList-grid").data("kendoGrid");
	grid.pager.page(1);
}