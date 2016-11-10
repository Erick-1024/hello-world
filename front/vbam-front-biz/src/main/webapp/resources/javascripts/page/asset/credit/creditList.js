var pageSize = 10;

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
	
	//点击新建确认
	$("body").on("click","#createBnt",function(){
		var id = $('input[name=client-choose]:checked').val();
		if(id == 'undefined' || id == null){
			showAlertWin("未选中任何客户");
			return;
		};
		location.href = basepath+"/asset/credit/listpage?customerId="+id;
	});
		
	
	getCreditInfo();
	//点击列表搜索
	$("#seachBtn").click(function(){
		getCreditInfo();
	});
	//新建额度搜索
	$("body").on("click","#seachBtn-two",function(){
		getcreateInfo();
	});
	
	//初始化新建弹窗
    new PopWindow(".limit-add-btn", {
        title: "申请额度",
        width: 800,
        reload: true,
        template: "#template-resetPwd-new"
    }).init();
    //新建
    getcreateInfo();
    
	$(".limit-add").click(function(){
        $(".limit-add-btn").click();
        $(".k-widget.k-window").css("top","25%");
        
        getcreateInfo();
    });
    
});
function getCreditInfo(){
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
						customerName :$.trim($("#customerName").val()),
						isApplyCredit :true
					},
					url: basepath+"/asset/credit/search/customer"
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
            title: "客户编号",
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
            width: 220,
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
            field: "availableLimitDesc",
            title: "可用余额",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template :function(data){
            	return data.availableLimitDesc.formatMoney();
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
            	if(credit_info_detail)
            		url += "<a class='comRow-link' href='"+basepath+"/asset/credit/listpage?customerId="+item.id+"'>详情</a>";
            	if(credit_info_modify){
            		url += "<a class='comRow-link' href='"+basepath+"/asset/credit/listpage?customerId="+item.id+"'>修改</a>";
            	}
            	if(credit_info_history){
            		url += "<a class='comRow-link' href='"+basepath+"/asset/credit/audit/listpage?customerId="+item.id+"'>查看历史</a>";
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


function getcreateInfo(){
	 $("#client-out-grid").empty();
	var sequence = 0;
        //新建弹窗表格初始化
        var grid = $("#client-out-grid").kendoGrid({
            selectable: "row",  //设置可选择数据行
            sortable: true,  //列排序
            dataSource:{
                pageSize: 5,
                type: "json", //后台返回的数据类型
                method: "post",
                transport: {
                    read: {
                    	data: {
    						customerName: $.trim($("#customer-name").val()),
    						isApplyCredit:false,
    					},
    					type:"post",
    					url: basepath + "/asset/credit/search/customer"
                    }
            },
            serverPaging: true,
    		serverSorting: true,
    		serverFiltering: true,
            //解析远程响应的数据
    		schema:{
    			data: "data",
    			total: function(data){
    				var total = data.totalNum;
    				if(total == 0){
    					$(".accountManageGrid .k-grid-header-wrap").css("overflow-x","auto");
    				}else{
    					$(".accountManageGrid .k-grid-header-wrap").css("overflow-x","");
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
                    return "<input type='radio' name='client-choose' value='"+ item.id + "' class='client-radio'>";
                }
            },{
                field: "sequence",
                title: "客户编号",
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















