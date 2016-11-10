 var pageSize = 10;
 $(function(){
	 //初始化列表
	 getSpecialProgramInfo()
	// 回车按钮事件
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
	// 点击列表搜索
	$("#seachBtn").click(function() {
		getSpecialProgramInfo();
	});
	  //删除权限
    //初始化确认弹窗
    confirmPopWindow = new PopWindow({
        title: "提示",
        width: 420,
        reload: true,
        template: "#template-notice"
    }).init();
    tipPopWindow = new PopWindow({
		title: "提示",
		width: 420,
		reload: true,
		template: "#tipBox_template1"
	}).init();
    $("body").on("click", ".specialprogram-delete", function(e){
        confirmPopWindow.open().center();
        $("#confirm-box-window .notice-content").html("是否确定删除此专项计划<input type='hidden' id ='id' value="+$(this).attr("data-id")+">");
    });
    $("body").on("click", ".confirm-link-two", function(e) {
     	$.ajax({
     		type : "POST",
     		data : {
     			specialProgramId : $("#id").val(),
     		},
     		url : basepath + "/asset/specialprogram/delete",
     		dataType : 'json',
     		success : function(data, status) {
     			if (data.status == "SUCCESS") {
     				closeThePop();
     				tipPopWindow.open().center();
     				$(".k-window-actions").remove();
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
 
 
 function getSpecialProgramInfo(){
        //初始化表格
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
						url: basepath+"/asset/specialprogram/querySpecialProgram"
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
                field: "estimateEstablishmentDate",
                title: "预计成立日期",
                width: 140,
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
                field: "measure",
                title: "操作",
                width: 120,
                attributes: {
                    style: "text-align: center"
                },
                template: function(item){
                	var url = "";
                	if(specialProgram_detail){
                		url += "<a class='comRow-link' href='"+basepath+"/asset/specialprogram/specialProgramDetail?specialProgramId="+item.id+"'>详情</a>";
                	}
                	if(specialProgram_modify){
                		if(item.allowUpdate == true){
                			url += "<a class='comRow-link' href='"+basepath+"/asset/specialprogram/updateSpecialProgram?specialProgramId="+item.id+"'>修改</a>";
                		}
                	}
                	if(specialProgram_detele){
                		if(item.allowDelete == true){
                			url += "<a class='comRow-link specialprogram-delete' href='javascript:void(0);' data-id='"+item.id+"'>删除</a>";
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
//关闭弹窗
 function closeThePop() {
 	$(".k-overlay").hide();
 	$(".k-window").hide();
 }


