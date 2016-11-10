var detailWindow;

var earlywarningTypeText;

var queryHistoryUrl = basepath + "/yundaex/earlywarning/query/earlyWarningEventHistory";

$(function(){
    
	earlywarningTypeText = $('[earlywarningType]');
	
	detailWindow = new PopWindow({
		title: "预警事件详情",
		width: 867,
		reload: true,
		template: "#earlywarningEventDetail_template"
	}).init();
	
	generatekendoGrid();
	
	bindEntry();
	
	$('body').on('click', 'a[name=tipBoxButton]', function() {
		tipBoxTemplate.close();
	});
	
	//点击"查询"
	$(".form-search-link").click(function(){
		generatekendoGrid();
    });
	
	$('body').on('click', 'a[buttontype=detail]', function() {
		generateWindow(false);
	});
	
	$('body').on('click', 'a[buttontype=cancel]', function() {
		generateWindow(true);
	});
	
	$('body').on('click', '.back-link,.return-link', function() {
		closeWin();
	});
	
	$('body').on('click', '.post-link', function() {
		cana.post(basepath + "/earlywarning/event/cancel", {earlywarningEventId: $('[aria-selected=true] > *:last > a').attr('eventId'), cancelExtra: $('[name=reviewRepresent] textarea').val()}, cancelSuccess, cancelFail);
	});
	
	/*通过判断“预警种类”以确定显示的内容*/
	$('body').on('click', '.relieve-status', function() {
		$("[name=detailContent]").toggle(
			    function(){
			        $(this).addClass("hidden");
			    },
			    function(){
			        $(this).removeClass("hidden");
	        });
	});
	
});

function bindEntry() {
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('.form-search-link').click();
    	}
    });
}

function generateWindow(isPredict) {
	$('[name=companyName]').text($('#companyName').text());
	$('[name=entryReviewTime]').text($('[aria-selected=true] [name=reviewTime]').text());
	$('[name=earlywarningEventCategory]').text($('[earlywarningtype]').text());
	
	$('[name=detailContent]').remove();
	
	$('#predicLevel').remove();
	$('[name=reviewRepresent]').remove();
	$('[name=buttonTd]').empty();
	if(isPredict) {
		$('[name=buttonTd]').append('<a class="default-link confirm-link post-link" href="javascript:void(0);">解除预警</a><a class="default-link back-link" href="javascript:" onclick="javascript:void(0);">返回</a>');
		cana.postSync(basepath + "/earlywarning/getPredictEarlyWarningLevel/cancel", {id: $('[aria-selected=true] > *:last > a').attr('eventId')}, predictLevelSuccess, function(){});
	} else {
		$('[name=buttonTd]').append('<a class="default-link confirm-link return-link" href="javascript:void(0);">返回</a>');
	}
	var eventType = $('[earlywarningtype]').attr('earlywarningtype');
	var extra = $('[aria-selected=true] > *:last > a').attr('extra');
	if(eventType!='SYSTEM')
		extra = JSON.parse(extra);
	$('.relieve-table tr:eq(2)').after(generateEventDetailContent(eventType, $('[aria-selected=true] > *:last > a').attr('subType'), $('[aria-selected=true] [name=occurTime]').text(), $('[aria-selected=true] span[name=amount]').text(), $('[earlywarningtype]').attr('earlywarningtype') == 'SYSTEM' ? JSON.parse($('[aria-selected=true] span[represent]').attr('represent')) : $('[aria-selected=true] span[represent]').text(), extra));
	
	openWin();
}

function predictLevelSuccess(data) {
	if(data == null) {
		setLevel(null, "正常");
	} else {
		for(var key in data) {
			setLevel(key, data[key]);
		}
	}
}

function setLevel(earlywaringLevel, eralywaringLevelDesc) {
	var predictContent = '<tr id="predicLevel">' +
	    '<th>预计预警状态</th>' +
	    '<td>';
	if(earlywaringLevel == null) {
		predictContent += '<a class="monitor-Bg monitor-skyBg" href="javascript:void(0);">' + eralywaringLevelDesc + '</a>';
	} else if(earlywaringLevel == 'yellow') {
		predictContent += '<a class="monitor-Bg monitor-yellowBg" href="javascript:void(0);">' + eralywaringLevelDesc + '</a>';
	} else if(earlywaringLevel == 'orange') {
		predictContent += '<a class="monitor-Bg monitor-orangeBg" href="javascript:void(0);">' + eralywaringLevelDesc + '</a>';
	} else {
		predictContent += '<a class="monitor-Bg monitor-redBg" href="javascript:void(0);">' + eralywaringLevelDesc + '</a>';
	}
	predictContent += '</td></tr><tr name="reviewRepresent"><th style="vertical-align: text-top">解除说明</th><td><textarea></textarea></td></tr>';
	$('[name=buttonTr]').before(predictContent);
}

function cancelSuccess(data) {
	showPopWindow("notice-icon02", "解除预警事件成功，通过审核后即可生效！");
}

function cancelFail(data) {
	showPopWindow("notice-icon03", data);
}

function showPopWindow(icon, text) {
	showTipBox(icon, text);
	closeWin();
}

/*打开弹窗*/
function openWin(){
	$("body").unbind("keydown");
	detailWindow.open().center();
}
/*关闭弹窗*/
function closeWin(){
	bindEntry();
	detailWindow.close();
}

function generatekendoGrid() {
	$("#monitorSrl-grid").empty();
	
	var entryReviewTimeEndVal = $.trim($("[name='entryReviewTimeEnd']").val());
	if(entryReviewTimeEndVal!="")
		entryReviewTimeEndVal = new Date(DateAdd("d", "1", new Date(entryReviewTimeEndVal))).format("yyyy-MM-dd");
	
	var occurTimeEndVal = $.trim($("[name='occurTimeEnd']").val());
	if(occurTimeEndVal!="")
		occurTimeEndVal = new Date(DateAdd("d", "1", new Date(occurTimeEndVal))).format("yyyy-MM-dd");
	
	if(earlywarningTypeText.attr('earlywarningType') != 'SYSTEM' && earlywarningTypeText.attr('type') =='history') {
		//初始化数据表格
	    $("#monitorSrl-grid").kendoGrid({
	        selectable: "row",  //设置可选择数据行
	        sortable: true,  //列排序
	        dataSource:{
	            pageSize: 10,
	            type: "json", //后台返回的数据类型
	            method: "post",
	            transport: {
	            	read : {
						type : "post",
						data: {
							memberId: $('[memberId]').attr('memberId'),
							entryReviewTimeStart: $('[name=entryReviewTimeStart]').val(),
							entryReviewTimeEnd: entryReviewTimeEndVal,
							occurTimeStart: $('[name=occurTimeStart]').val(),
							occurTimeEnd: occurTimeEndVal,
							earlywarningType: $('[earlywarningType]').attr('earlywarningType'),
							isCancel: $('[name=isCancel]').val() 
						},
					url: queryHistoryUrl
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
	            field: "entryReviewTime",
	            title: "审核通过时间",
	            width: 80,
	            attributes:{
	                style:"text-align:center"
	            },
	            template: function(data) {
	            	return '<span name="reviewTime">' + new Date(data.entryReviewTime).format("yyyy-MM-dd hh:mm") + '</span>';
	            }
	        },{
	            field: "occurTime",
	            title: "事件发生时间",
	            width: 80,
	            attributes: {
	                style: "text-align: center"
	            },
	            template: function(data) {
	            	return '<span name="occurTime">' + new Date(data.occurTime).format("yyyy-MM-dd") + '</span>';
	            }
	        },{
	            field: "represent",
	            title: "事件描述",
	            width: 200,
	            attributes:{
	                style:"text-align:center"
	            },
	            template:function(data){
	                var txt = data.represent;
	                if(txt == null) {
	                	return '-';
	                } else {
	                	var l =txt.length;
		                var outTxt = "";
		                if(l>=20){
		                    outTxt = txt.substring(0,20)+"...";
		                }else{
		                    outTxt = txt;
		                }
		                return '<span represent>' + outTxt + '</span>';
	                }
	            }
	        },{
	            field: "amount",
	            title: "执行金额",
	            width: 80,
	            attributes: {
	                style: "text-align: center"
	            },
	            template:function(data) {
	            	return '<span name="amount">' + (data.amount == null ? "-" : (data.amount/100).toString().formatMoney()) + '</span>';
	            }
	        },{
	            field: "stateDesc",
	            title: "事件状态",
	            width: 80,
	            attributes: {
	                style: "text-align: center"
	            }
	        },{
	            field: "opera",
	            title: "操作",
	            width: 80,
	            attributes: {
	                style: "text-align: center"
	            },
	            template: function(data){
	                return "<a class='comRow-link' buttonType='detail' href='javascript:void(0);' extra=" + data.extraData + " eventId=" + data.id + ">详情</a>";
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
	} else if (earlywarningTypeText.attr('earlywarningType') == 'SYSTEM' && earlywarningTypeText.attr('type') =='history') {
		
		//初始化数据表格
	    $("#monitorSrl-grid").kendoGrid({
	        selectable: "row",  //设置可选择数据行
	        sortable: true,  //列排序
	        dataSource:{
	            pageSize: 10,
	            type: "json", //后台返回的数据类型
	            method: "post",
	            transport: {
	            	read : {
						type : "post",
						data: {
							memberId: $('[memberId]').attr('memberId'),
							entryReviewTimeStart: $('[name=entryReviewTimeStart]').val(),
							entryReviewTimeEnd: entryReviewTimeEndVal,
							earlywarningType: $('[earlywarningType]').attr('earlywarningType'),
							isCancel: $('[name=isCancel]').val() 
						},
					url: queryHistoryUrl
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
                field: "entryReviewTime",
                title: "预警事件时间",
                width: 120,
                attributes:{
                    style:"text-align:center"
                },
	            template: function(data) {
	            	return '<span name="reviewTime">' + new Date(data.entryReviewTime).format("yyyy-MM-dd hh:mm") + '</span>';
	            }
            },{
                field: "represent",
                title: "事件描述",
                width: 200,
	            attributes:{
	                style:"text-align:center"
	            },
                template:function(data){
                	var txt = data.represent.standard.replace("<", "&lt;");
                	var l =txt.length;
                	var outTxt = "";
                	if(l>=50){
                		outTxt = txt.substring(0,50)+"...";
                	}else{
                		outTxt = txt;
                	}
                	return "<span represent='" + JSON.stringify(data.represent) + "'>" + outTxt + "</span>";
                }
            },{
                field: "stateDesc",
                title: "事件状态",
                width: 80,
                attributes:{
                    style:"text-align:center"
                }
            },{
                field: "opera",
                title: "操作",
                width: 80,
                attributes: {
                    style: "text-align: center"
                },
                template: function(data){
                    return "<a class='comRow-link' buttonType='detail' href='javascript:void(0);' extra=" + data.extraData + " eventId=" + data.id + " subType=" + data.subType + ">详情</a>";
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
	} else if (earlywarningTypeText.attr('earlywarningType') != 'SYSTEM' && earlywarningTypeText.attr('type') =='cancel') {
		//初始化数据表格
	    $("#monitorSrl-grid").kendoGrid({
	        selectable: "row",  //设置可选择数据行
	        sortable: true,  //列排序
	        dataSource:{
	            pageSize: 10,
	            type: "json", //后台返回的数据类型
	            method: "post",
	            transport: {
	            	read : {
						type : "post",
						data: {
							memberId: $('[memberId]').attr('memberId'),
							entryReviewTimeStart: $('[name=entryReviewTimeStart]').val(),
							entryReviewTimeEnd: entryReviewTimeEndVal,
							occurTimeStart: $('[name=occurTimeStart]').val(),
							occurTimeEnd: occurTimeEndVal,
							earlywarningType: $('[earlywarningType]').attr('earlywarningType'),
							isCancel: false
						},
					url: queryHistoryUrl
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
                field: "entryReviewTime",
                title: "审核通过时间",
                width: 120,
                attributes:{
                    style:"text-align:center"
                },
	            template: function(data) {
	            	return '<span name="reviewTime">' + new Date(data.entryReviewTime).format("yyyy-MM-dd hh:mm") + '</span>';
	            }
            },{
                field: "occurTime",
                title: "事件发生时间",
                width: 120,
                attributes: {
                    style: "text-align: center"
                },
	            template: function(data) {
	            	return '<span name="occurTime">' + new Date(data.occurTime).format("yyyy-MM-dd") + '</span>';
	            }
            },{
                field: "represent",
                title: "事件描述",
                width: 200,
                attributes: {
                    style: "text-align: center"
                },
                template:function(data){
                    var txt = data.represent;
                    if(txt == null) {
                    	return '-';
                    } else {
                    	var l =txt.length;
                    	var outTxt = "";
                    	if(l>=20){
                    		outTxt = txt.substring(0,20)+"...";
                    	}else{
                    		outTxt = txt;
                    	}
                    	return '<span represent>' + outTxt + '</span>';
                    }
                }
            },{
                field: "amount",
                title: "执行金额",
                width: 80,
                attributes: {
                    style: "text-align: center"
                },
	            template:function(data) {
	            	return '<span name="amount">' + (data.amount == null ? "-" : (data.amount/100).toString().formatMoney()) + '</span>';
	            }
            },{
                field: "opera",
                title: "操作",
                width: 80,
                attributes: {
                    style: "text-align: center"
                },
                template: function(data){
                    return "<a class='comRow-link' buttonType='cancel' href='javascript:void(0);' extra=" + data.extraData + " eventId=" + data.id + ">解除</a>";
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
	} else {
		//初始化数据表格
	    $("#monitorSrl-grid").kendoGrid({
	        selectable: "row",  //设置可选择数据行
	        sortable: true,  //列排序
	        dataSource:{
	            pageSize: 10,
	            type: "json", //后台返回的数据类型
	            method: "post",
	            transport: {
	            	read : {
						type : "post",
						data: {
							memberId: $('[memberId]').attr('memberId'),
							entryReviewTimeStart: $('[name=entryReviewTimeStart]').val(),
							entryReviewTimeEnd: entryReviewTimeEndVal,
							earlywarningType: $('[earlywarningType]').attr('earlywarningType'),
							isCancel: false
						},
					url: queryHistoryUrl
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
                field: "entryReviewTime",
                title: "预警事件时间",
                width: 120,
                attributes:{
                    style:"text-align:center"
                },
	            template: function(data) {
	            	return '<span name="reviewTime">' + new Date(data.entryReviewTime).format("yyyy-MM-dd hh:mm") + '</span>';
	            }
            },{
                field: "represent",
                title: "事件描述",
                width: 200,
                attributes: {
                    style: "text-align: center"
                },
                template:function(data){
                    var txt = data.represent.standard.replace("<", "&lt;");
                    var l =txt.length;
                    var outTxt = "";
                    if(l>=20){
                        outTxt = txt.substring(0,20)+"...";
                    }else{
                        outTxt = txt;
                    }
                    return "<span represent='" + JSON.stringify(data.represent) + "'>" + outTxt + "</span>";
                }
            },{
                field: "opera",
                title: "操作",
                width: 80,
                attributes: {
                    style: "text-align: center"
                },
                template: function(data){
                    return "<a class='comRow-link' buttonType='cancel' href='javascript:void(0);' extra=" + data.extraData + " eventId=" + data.id + " subType=" + data.subType + ">解除</a>";
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
	}
}