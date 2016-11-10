var currentLoanDate;

$(function(){
    //初始化弹窗
    new PopWindow(".detailPop", {
        title: "放款明细",
        width: 800,
        reload: true,
        template: "#loan_detail_tipBox_template"
    }).init();
    
    $('body').on('click', 'a[name=tipBoxButton]', function() {
		tipBoxTemplate.close();
	});
    
    $('body').on('click', 'a[name=confirmBoxOK]', function() {
    	cana.get(basepath + "/homsom/loan/confirm/loan/" + channel + "?date=" + currentLoanDate, null, loanSuccess, loanFail, null);
    	confirmPopWindow.close();
    });
    
    $("body").on("click",".detailPop",function(){
    	$('#loanDate').text($(this).parent().siblings(':first').text());
    	$("#detail-grid").kendoGrid({
            selectable: "row",  //设置可选择数据行
            sortable: false,  //列排序
            dataSource:{
        		pageSize : 10,
        		type : "json", // 后台返回的数据类型
        		method : "post",
        		transport : {
        			read : {
        				type : "post",
        				url: basepath + "/homsom/loan/get/detail/" + channel,
        				data: {
        					loanDate: $('#loanDate').text()
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
            },
            columns: [{
                field: "counterpartyName",
                title: "交易对手",
                width: 220,
                attributes: {
                    style: "text-align: center"
                }
            },{
                field: "applyAmount",
                title: "放款申请金额",
                width: 120,
                attributes:{
                    style:"text-align:center"
                },
                template: function(data){
                	return fmoney(data.applyAmount/100);
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
    });
    
    //状态事件
    $(".hs-all-in").click(function(){
        if($(this).hasClass('active')){
            $(this).removeClass('active');
            $(".hs-list").removeClass('active');
        }else{
            $(this).addClass('active');
            $(".hs-list").addClass('active');
        }
    });
    
    $(".hs-list").click(function(){
        if($(this).hasClass('active')){
            $(this).removeClass('active');
            $(".hs-all-in").removeClass('active');
        }else{
            $(this).addClass('active');
        }
        if($(".hs-list.active").length===4){
            $(".hs-all-in").addClass('active');
        }else{
            $(".hs-all-in").removeClass('active');
        }
    });
    
    $('#search').click(function() {
		var grid = $("#monitorList-grid").data("kendoGrid");
		grid.setDataSource(getDataSource());
	});
    
    $('#exportExcel').click(function(){
    	var url = basepath + "/homsom/loan/export/" + channel + "?startDate=" + $.trim($("#startDate").val()) + "&endDate=" + $.trim($("#endDate").val() + "&loanStates=");
    	$('.hs-list').each(function() {
    		if($(this).hasClass('active')) {
    			url += $(this).attr('enum') + "&loanStates=";
    		}
    	});
    	window.open(url.substring(0, url.lastIndexOf('&loanStates=')));
    });
    
    $('body').on('click', '.deployPop', function() {
    	currentLoanDate = $(this).parent().siblings(':first').text();
    	showConfirmBox("确定对" + currentLoanDate + "的放款申请进行放款吗？");
    });
    
    bindEntry();
    
    initList();
    
})

function bindEntry() {
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('#search').click();
    	}
    });
}

function loanSuccess(data) {
	showTipBox("notice-icon02", "放款操作成功");
	var grid = $("#monitorList-grid").data("kendoGrid");
	grid.dataSource.fetch();
}

function loanFail(data) {
	showTipBox("notice-icon03", data);
}

function getLoanStates() {
	var sz = [];
	$('.hs-list').each(function() {
		if($(this).hasClass('active')) {
			sz.push($(this).attr('enum'));
		}
	});
	return sz;
}

function getDataSource(){
	return new kendo.data.DataSource({
		page : 1,
		pageSize : 10,
		type : "json", // 后台返回的数据类型
		method : "post",
		transport : {
			read: function(option){
				var paraData = {};
				paraData.startDate = $.trim($("#startDate").val());
				paraData.endDate = $.trim($("#endDate").val());
				paraData.loanStates = getLoanStates();
				paraData.channel = channel;
				paraData.page = option.data.page;
				paraData.pageSize = option.data.pageSize;
				$.ajax({
					contentType:"application/json",
			    	data: JSON.stringify(paraData),
			    	type : "POST",
			    	dataType:"json",
			    	url: basepath + "/homsom/loan/get/list",
			    	success:function(data){
			    		option.success(data);
			    	}
				});
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
        sortable: false,  //列排序
        columns: [{
            field: "date",
            title: "放款日期",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "applyAmount",
            title: "放款申请金额",
            width: 120,
            attributes:{
                style:"text-align:center"
            },
            template: function(data){
            	return fmoney(data.applyAmount/100);
            }
        },{
            field: "loanAmount",
            title: "实际放款金额",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	return fmoney(data.loanAmount/100);
            }
        },{
            field: "loanStateDesc",
            title: "状态",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "risk",
            title: "操作",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	var html = '';
            	if((data.loanState == 'WAITING_FOR_LOAN' || data.loanState == 'LOANED') && hs_loan_audit_detail) {
            		html += "<a class='userCenter-link detailPop' href='javascript:void(0);' style='margin-right:10px;'>明细</a>";
            	}
            	if(data.loanState == 'WAITING_FOR_LOAN' && hs_loan_audit_loan) {
            		html += "<a class='userCenter-link deployPop' href='javascript:void(0);'>放款</a>";
            	}
            	return html;
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