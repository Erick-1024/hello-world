$(function (){
	
	//权限设置列表页搜索
	$("#form-search-list").click(function(){
		getCustomerListInfo();
	});
	//初始化列表
	getCustomerListInfo();
	
	//初始化新增权限弹窗1
	new PopWindow(".limit-add-btn", {
	    title: "新增权限",
	    width: 800,
	    reload: true,
	    template: "#template-resetPwd-new"
	}).init();
	//新增权限第一个弹框搜索
	$("body").on("click","#out-new-form",function (){
		getCreatePrivilegeCustomerList();
	});
	//新增权限点击事件触发弹窗1
	$(".limit-add").click(function(){
	    $(".limit-add-btn").click();
	    $(".k-widget.k-window").css("top","25%");
	    getCreatePrivilegeCustomerList();
	});
	
	//回车按钮事件
	document.onkeydown=keyDownSearch;  
    function keyDownSearch(e) {    
        var theEvent = e || window.event;    
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
		if(code == 13){
			//主页面回车事件
			if($(".k-overlay").length > 0){
				if($(".k-overlay").is(':hidden')==true){
					$("#form-search-list").click();
				}else{
					//弹框回车事件
					$("#out-new-form").click();
					$("#searchList-two").click();
				}
			}else if($(".k-overlay").length == 0){
				//主页面回车事件
				$("#form-search-list").click();
			}
				
		}
        return true;    
    } 

//	  closeRefresh();
      //初始化确认弹窗
      confirmPopWindow = new PopWindow({
          title: "提示",
          width: 420,
          reload: true,
          template: "#template-notice"
      }).init();
      //删除权限
      $("body").on("click", ".privilge-delete", function(e){
          confirmPopWindow.open().center();
          $("#confirm-box-window .notice-content").html("是否确定删除此权限<input type='hidden' id ='id' value="+$(this).attr("data-id")+">");
      });
      $("body").on("click", ".confirm-link-two", function(e) {
       	$.ajax({
       		type : "POST",
       		data : {
       			id : $("#id").val(),
       		},
       		url : basepath + "/asset/privilege/delete",
       		dataType : 'json',
       		success : function(data, status) {
       			if (data.status == "SUCCESS") {
       				closeThePop();
       				tipPopWindow.open().center();
       				$("#tip-box-window .dlg-notice").addClass("notice-icon02");
       				$("#tip-box-window .notice-content").text("操作成功");
       				setTimeout(function(){  window.location.reload();},1000);
       			} else {
       				closeThePop();
       				tipPopWindow.open().center();
       				$("#tip-box-window .dlg-notice").addClass("notice-icon01");
       				$("#tip-box-window .notice-content").text(data.message);
       			}
       		},
       	});
       });
});

//初始化页面表格
function getCustomerListInfo(){
	$("#monitorList-grid").empty();
	var sequence = 0;
 var grid=$("#monitorList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        dataSource:{
            type: "json", //后台返回的数据类型
            transport: {
                read: {
                	type : "post",
                    data: {
                    	userType: $.trim($("#userType").val()),
                    	companyName: $.trim($("#companyName").val())
                    },
                    url: basepath+ "/asset/privilege/queryList"
                }
        },
        serverPaging: true,
		serverSorting: true,
		serverFiltering: true,
        schema: {
			data: "data",
			total: function (data) {
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
            width: 60,
            attributes: {
                style: "text-align: center"
            },
            template :function (data){
            	sequence ++;
            	return sequence;
            }
        },{
            field: "userTypeDesc",
            title: "客户类型",
            width: 80,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "companyName",
            title: "企业名称",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "customerName",
            title: "客户名称",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "measure",
            title: "操作",
            width: 80,
            attributes: {
                style: "text-align: center"
            },
            template: function(item){
            	var url = "";
            	if(deleteAuth){
            		url += "<a class='comRow-link privilge-delete' href='javascript:void(0);' data-id='"+item.id+"'>删除</a>";
            	}
            	return url;
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            buttonCount: 5,  //显示页数
            pageSize:10,
            page:1,
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

//弹窗1表格初始化
function getCreatePrivilegeCustomerList(){
	$("#client-out-grid").empty();
   var sequence =0;
   var grid =$("#client-out-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        dataSource:{
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                	type : "post",
                    data: {
                    	userType: $.trim($("#userType-id").val()),
                    	companyName: $.trim($("#companyNameId").val())
                    },
                    url: basepath + "/asset/privilege/queryCompany4Add"
                }
        },
        serverPaging: true,
		serverSorting: true,
		serverFiltering: true,
        schema: {
			data: "data",
			total: function (data) {
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
            width: 80,
            attributes: {
                style: "text-align: center"
            },
            template: function(item){
                return "<input type='radio' name='client-choose' value='"+ item.masterId +"' class='client-radio'>";
            }
        },{
            field: "companyName",
            title: "企业名称",
            width: 200,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "userTypeDesc",
            title: "客户类型",
            width: 150,
            attributes:{
                style:"text-align:center"
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            buttonCount: 5,  //显示页数
            pageSize:5,
            page:1,
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

//关闭弹窗
function closeThePop() {
	$(".k-overlay").hide();
	$(".k-window").hide();
}
//关闭刷新页面
function closeRefresh() {
	$("body").on("click", "#bnt-id", function() {
		closeThePop();
		window.location.reload();
	})
}

 
 