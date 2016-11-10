var data1 = "";
var data2 = "";	
$(function(){
		
	    //弹窗初始化
	    openPop();
	    //新增原始权益人
	    $("body").on("click",".add-appoint-btn",function(){
	        $(".limit-add-btn").click();
	        $(".k-widget.k-window").css("top","25%");
	        interestsPopGrid();
	    });
	    //新增资产服务机构
	    $(".add-cost-btn").on("click",function(){
	        $(".limit-next-btn").click();
	        $(".k-widget.k-window").css("top","25%");
	        assetPopGrid();
	    });
	    //原始权益人单选事件
	    $("body").on("click",".interests-choose",function(){
	        data1=$(this).val();
	    });
	    //资产服务机构单选事件
	    $("body").on("click",".asset-choose",function(){
	        data2=$(this).val();
	    });
	    //原始权益人新增事件
	    $("body").on("click","#interests-btn",function(){
	        var $name = "";
	        if(data1!=""){
	            $name = data1;
	            $(".back-link").click();
	            addClientRow("interests-tb",$name);
	        }else if($(".interests-input").val()!=""){
	            $name = $(".interests-input").val();
	            $(".back-link").click();
	            addClientRow("interests-tb",$name);
	        }else{
	        	tipPopWindowOne.open().center();
	         //   $(".message-pop").click();
	            $("#tip-box-window .dlg-notice").addClass("notice-icon01");
	            $("#tip-box-window .notice-content").text("搜索并选择企业名称！");
	        }

	    });
	    //资产服务机构新增事件
	    $("body").on("click","#asset-btn",function(){
	        var $name = "";
	        if(data2!=""){
	            $name = data2;
	            $(".back-link").click();
	            addClientRow("asset-tb",$name);
	        }else if($(".asset-input").val()!=""){
	            $name = $(".asset-input").val();
	            $(".back-link").click();
	            addClientRow("asset-tb",$name);
	        }else{
	        	tipPopWindowOne.open().center();
	           // $(".message-pop").click();
	            $("#tip-box-window .dlg-notice").addClass("notice-icon01");
	            $("#tip-box-window .notice-content").text("搜索并选择企业名称！");
	        }
	    });
	    //删除原始权益人
	    $(".del-appoint-btn").on("click",function(){
	    	var ckbs = $("input[name=interests-tb]:checked");
		    if (ckbs.size() == 0) {
		    	tipPopWindowOne.open().center();
		        $("#tip-box-window .dlg-notice").addClass("notice-icon01");
		        $("#tip-box-window .notice-content").text("请选择要删除的行！");
		        return;
		    }
		    tipPopWindowTwo.open().center();
            $("#tip-box-window .dlg-notice").addClass("notice-icon01");
            $("#tip-box-window .notice-content").text("是否确定删除！");
          
	    });
	    
	    $("body").on("click","#specialDel",function(){
	    	var ckbs = $("input[name=interests-tb]:checked"); 
	    	ckbs.each(function () {
		        $(this).parent().parent().remove();
		    });
	    	$(".back-link").click();
	    });
	    
	    
	    
	    //删除资产服务机构
	    $(".del-cost-btn").on("click",function(){
	    	var ckbs = $("input[name=asset-tb]:checked");
		    if (ckbs.size() == 0) {
		    	tipPopWindowOne.open().center();
		        $("#tip-box-window .dlg-notice").addClass("notice-icon01");
		        $("#tip-box-window .notice-content").text("请选择要删除的行！");
		        return;
		    }
		    tipPopWindowThree.open().center();
            $("#tip-box-window .dlg-notice").addClass("notice-icon01");
            $("#tip-box-window .notice-content").text("是否确定删除！");
	    });
	    
	    
	    $("body").on("click","#assetIdDel",function(){
	    	var ckbs = $("input[name=asset-tb]:checked"); 
	    	ckbs.each(function () {
		        $(this).parent().parent().remove();
		    });
	    	$(".back-link").click();
	    });
	    
	    //点击搜索原始权益人
	    $("body").on("click","#form-search-btnOne",function(){	
	    	interestsPopGrid();
	    });
	    
	    //点击搜索资产服务机构
	    $("body").on("click","#form-search-btnTwo",function (){
	    	assetPopGrid();
	     });

	});
	//弹窗初始化
	function openPop(){
	    //初始化原始权益人弹窗
	    new PopWindow(".limit-add-btn", {
	        title: "新增原始权益人",
	        width: 800,
	        reload: true,
	        template: "#template-resetPwd-one"
	    }).init();
	    //初始化资产服务机构弹窗
	    new PopWindow(".limit-next-btn", {
	        title: "新增资产服务机构",
	        width: 800,
	        reload: true,
	        template: "#template-resetPwd-two"
	    }).init();
	    //初始化消息弹窗
	    tipPopWindowOne = new PopWindow({
	        title: "提示",
	        width: 420,
	        reload: true,
	        template: "#tipBox_template"
	    }).init();
	    
	    //初始化消息弹窗
	    tipPopWindowTwo = new PopWindow({
	        title: "提示",
	        width: 420,
	        reload: true,
	        template: "#tipBox_template2"
	    }).init();
	    
	    //初始化消息弹窗
	    tipPopWindowThree = new PopWindow({
	        title: "提示",
	        width: 420,
	        reload: true,
	        template: "#tipBox_template3"
	    }).init();
	}
	//原始权益人弹出框表格
	function interestsPopGrid(){
		$("#interests-grid").empty();
		var sequence = 0;
		var grid =$("#interests-grid").kendoGrid({
	    	selectable: "row",  //设置可选择数据行
	        dataSource:{
	            type: "json", //后台返回的数据类型
	            method: "post",
	            transport: {
	                read: {
	                	type : "post",
	                    data: {
	                    	userType: $.trim($("#userTypeId").val()),
	                    	companyName: $.trim($("#companyNameId").val())
	                    },
	                    url: basepath + "/asset/specialprogram/queryCompanyList"
	                }
	        },
	        serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
	        //解析远程响应的数据
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
	        dataBound: onDataBoundOne,
	        columns: [{
	            field: "id",
	            title: "选择",
	            width: 120,
	            attributes: {
	                style: "text-align: center"
	            },
	            template: function(item){
	                return "<input type='radio' name='interests-choose' value='"+item.companyName+"' class='client-radio interests-choose'>";
	            }
	        },{
	            field: "companyName",
	            title: "企业名称",
	            width: 300,
	            attributes: {
	                style: "text-align: center"
	            }
	        },{
	            field: "userTypeDesc",
	            title: "客户类型",
	            width: 120,
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
	//资产服务机构弹出框表格
function assetPopGrid(){
	$("#asset-grid").empty();
	var sequence = 0;
  grid = $("#asset-grid").kendoGrid({
	        selectable: "row",  //设置可选择数据行
	        sortable: false,  //列排序
	        dataSource:{
	            type: "json", //后台返回的数据类型
	            method: "post",
	            transport: {
	                read: {
	                	type : "post",
	                    data: {
	                    	userType: $.trim($("#userType").val()),
	                    	companyName: $.trim($("#companyName").val())
	                    },
	                    url: basepath + "/asset/specialprogram/queryCompanyList"
	                }
	        },
	        serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
	        //解析远程响应的数据
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
	        dataBound: onDataBoundTwo,
	        columns: [{
	            field: "id",
	            title: "选择",
	            width: 120,
	            attributes: {
	                style: "text-align: center"
	            },
	            template: function(item){
	                return "<input type='radio' name='asset-choose' value='"+item.companyName+"' class='client-radio asset-choose'>";
	            }
	        },{
	            field: "companyName",
	            title: "企业名称",
	            width: 300,
	            attributes: {
	                style: "text-align: center"
	            }
	        },{
	            field: "userTypeDesc",
	            title: "客户类型",
	            width: 120,
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
	//原始权益人弹出框表格分页事件
	function onDataBoundOne(){
	    if(data1=="")
	        return;
	    var $length = $("input[name=interests-choose]");
	    for(var i=0;i<$length.length;i++){
	        if($length.eq(i).val()==data1){
	            $length.eq(i).prop("checked","checked");
	        }
	    }
	}
	//资产服务机构弹出框表格分页事件
	function onDataBoundTwo(){
	    if(data2=="")
	        return;
	    var $length = $("input[name=asset-choose]");
	    for(var i=0;i<$length.length;i++){
	        if($length.eq(i).val()==data2){
	            $length.eq(i).prop("checked","checked");
	        }
	    }
	}
	//表格操作（增加）
	function addClientRow(tab,data){
	    var $tr=$("#"+tab+""),
	        $html=$('<tr class="client-add-tr">'+
	                '<th><input type="checkbox" class="input-new" name="'+tab+'"></th>'+
	                '<td>'+data+'</td>'+
	                '</tr>');
	    $html.appendTo($tr);
	}
	//表格操作（删除）
//	function removeClientRow(ckb) {
//	    //获取选中的复选框，然后循环遍历删除
//	    var ckbs = $("input[name=" + ckb + "]:checked");
//	    if (ckbs.size() == 0) {
//	    	tipPopWindow.open().center();
//	      //  $(".message-pop").trigger("click");
//	        $("#tip-box-window .dlg-notice").addClass("notice-icon01");
//	        $("#tip-box-window .notice-content").text("请选择要删除的行！");
//	        return;
//	    }
//	    
//	    ckbs.each(function () {
//	        $(this).parent().parent().remove();
//	    });
//	}

