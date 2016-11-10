var detailWindow;
var eventData;

$(function(){
	generatekendoGrid();
	
	$('body').on('click', 'a[name=tipBoxButton]', function() {
		tipBoxTemplate.close();
		generatekendoGrid();
	});
	
	detailWindow = new PopWindow({
		title: "预警事件详情/审核",
		width: 867,
		reload: true,
		template: "#earlywarningEventDetail_template"
	}).init();
	
	$('body').on('keydown', function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('.form-search-link').click();
    	}
    });
	
	//点击"查询"
	$('.form-search-link').click(function() {
		generatekendoGrid();
    });
	
	// 详情按钮
	$('body').on('click', '.detail', function() {
		generateWindow(this, false);
	});
	
	// 审核按钮
	$('body').on('click', '.review', function(){
		generateWindow($(this).prev(), true);
    });
	
	$('body').on('click', '[name=agree]', function() {
		cana.post(basepath + "/yundaex/earlywarning/audit", {earlywarningEventReviewId: $('[aria-selected=true] > td:last > a:first').attr('reviewid'), result: true}, auditPass);
	});
	
	$('body').on('click', '[name=disagree]', function() {
		cana.post(basepath + "/yundaex/earlywarning/audit", {earlywarningEventReviewId: $('[aria-selected=true] > td:last > a:first').attr('reviewid'), result: false}, auditNotPass);
	});
	
	$('body').on('click', '.back-link,.return-link', function() {
		detailWindow.close();
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

/*
 * 获取事件信息
 */
function getEventDataSuccess(data) {
	eventData = data;
}

/*
 * 获取审核说明信息
 */
function getReviewDataSuccess(data) {
	if(data.extraData != null) {
		$('[name=review-extra]').text(data.extraData);
	}
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
	predictContent += '</td></tr>';
	$('tr[currentLevel]').after(predictContent);
}

function generateWindow(detailButton, isPredict) {
	$('[name=companyNameText]').text($('[aria-selected=true] > *:eq(1)').text());
	$('[name=action]').text($('[aria-selected=true] > *:eq(5)').text());
	$('[name=applyDate]').text($('[aria-selected=true] > *:eq(6)').text());
	$('[name=earlywarningEventCategory]').text($('[aria-selected=true] > *:eq(3) > span').text());
	$('[name=earlywarningEventSubCategory]').text($('[aria-selected=true] > *:eq(4) > span').text());
	$('[name=popWindowEarlyWarningLevel]').empty();
	var level = $('[aria-selected=true] > *:eq(2) > span').attr('level');
	if(level == 'yellow')
		$('[name=popWindowEarlyWarningLevel]').append('<a class="monitor-Bg monitor-yellowBg" href="javascript:void(0);">' + $('[aria-selected=true] > *:eq(2) > span').text() + '</a>');
	else if(level == "orange")
		$('[name=popWindowEarlyWarningLevel]').append('<a class="monitor-Bg monitor-orangeBg" href="javascript:void(0);">' + $('[aria-selected=true] > *:eq(2) > span').text() + '</a>');
	else if(level == 'red')
		$('[name=popWindowEarlyWarningLevel]').append('<a class="monitor-Bg monitor-redBg" href="javascript:void(0);">' + $('[aria-selected=true] > *:eq(2) > span').text() + '</a>');
	else
		$('[name=popWindowEarlyWarningLevel]').append('<a class="monitor-Bg monitor-skyBg" href="javascript:void(0);">正常</a>');
	$('[name=detailContent]').remove();
	
	detailWindow.open().center();
	
	cana.postSync(basepath + "/yundaex/earlywarning/query/reviewDetail?earlywarningEventId=" + $(detailButton).attr('eventid'), null, getEventDataSuccess);
	
	$('#predicLevel').remove();
	$('[name=buttonTd]').empty();
	if(isPredict) {
		$('[name=buttonTd]').append('<a class="default-link confirm-link" name="agree" href="javascript:void(0);">同意</a><a class="default-link confirm-link" name="disagree" href="javascript:void(0);">拒绝</a><a class="default-link back-link" href="javascript:void(0);">返回</a>');
//		if($('[aria-selected=true] > *:eq(5) > span').attr('applyType') == 'cancel') {
//			cana.postSync(basepath + "/yundaex/earlywarning/getPredictEarlyWarningLevel", {id: eventData.id,action:'cancel'}, predictLevelSuccess, function(){});
//		} else {
//			cana.post(basepath + "/yundaex/earlywarning/getPredictEarlyWarningLevel", {action:'add',financeId: $('[aria-selected=true] > *:eq(0)').text(), outCustomer:$(detailButton).attr('outCustomerId'), type: $('[aria-selected=true] > *:eq(3) > span').attr('eventType'),subType: $('[aria-selected=true] > *:eq(4) > span').attr('eventSubType'), level: eventData.level}, predictLevelSuccess);
//		}
	} else {
		$('[name=buttonTd]').append('<a class="default-link confirm-link return-link" href="javascript:void(0);">返回</a>');
	}
	$('.relieve-table tr:eq(4)').after(generateEventDetailContent($('[aria-selected=true] > *:eq(3) > span').attr('eventType'), eventData.subType, eventData.occurTime, (eventData.amount/100).toString().formatMoney(), eventData.represent, eventData.extraData));
	
	cana.get(basepath + "/yundaex/earlywarning/audit?earlywarningEventReviewId="+$(detailButton).attr('reviewid'), null, getReviewDataSuccess);
}

function auditPass(data) {
	showTipBox("notice-icon02", "操作成功，该预警事件通过了您的审核");
	detailWindow.close();
}

function auditNotPass(data) {
	showTipBox("notice-icon02", "操作成功，该预警事件未通过您的审核");
	detailWindow.close();
}

function generatekendoGrid() {
	var auditTimeEndVal = $.trim($("[name='auditTimeEnd']").val());
	if(auditTimeEndVal!="")
		auditTimeEndVal = new Date(DateAdd("d", "1", new Date(auditTimeEndVal))).format("yyyy-MM-dd");
	$("#monitorSrl-grid").empty();
	//初始化数据表格
    $("#monitorSrl-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
            pageSize: 10,
            type: "json", //后台返回的数据类型
            method: "post",
            transport : {
				read : {
					type : "post",
					data: {
						companyName: $.trim($("[name='companyName']").val()),
						auditTimeStart: $.trim($("[name='auditTimeStart']").val()),
						auditTimeEnd: auditTimeEndVal,
                	 	earlywarningLevel: $.trim($("[name='earlyWarningLevel']").val()),
                	 	earlywarningEventAction: $.trim($("[name='earlywarningEventAction']").val()),
                	 	earlywarningReviewState: $.trim($("[name='earlywarningReviewState']").val())
				},
				url: basepath + "/yundaex/earlywarning/query/reviewList"
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
            field: "financeId",
            title: "客户编号",
            width: 80,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "financeCompany",
            title: "客户名称",
            width: 200,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "earlyWarningLevelDesc",
            title: "预警状态",
            width: 80,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
                var val = kendo.htmlEncode(data.earlyWarningLevel);
                var res = "";
                if(val == "yellow"){
                    res = "<span class='monitor-yellow' level=" + val + ">" + data.earlyWarningLevelDesc + "</span>";
                }else if(val == "orange"){
                    res = "<span class='monitor-orange' level=" + val + ">" + data.earlyWarningLevelDesc + "</span>";
                }else if(val == "red"){
                    res = "<span class='monitor-red' level=" + val + ">" + data.earlyWarningLevelDesc + "</span>";
                }else{
                    res = "-";
                }
                return res;
            }
        },{
            field: "eventType",
            title: "预警种类",
            width: 140,
            attributes: {
                style: "text-align: center"
            },
            template: function(data) {
            	return '<span eventType=' + data.eventType + '>' + YundaexEarlywarningEventCategory[data.eventType] + '</span>'
            }
        },{
            field: "eventSubType",
            title: "事件种类",
            width: 140,
            attributes: {
                style: "text-align: center"
            },
            template: function(data) {
            	return '<span eventSubType=' + data.eventSubType + '>' + YundaexEarlywarningEventSubCategory[data.eventSubType] + '</span>'
            }
        },{
            field: "applyTypeDesc",
            title: "新增/解除事件审核",
            width: 80,
            attributes: {
                style: "text-align: center"
            },
            template: function(data) {
            	return '<span applyType=' + data.applyType + '>' + data.applyTypeDesc + '</span>'
            }
        },{
            field: "createTime",
            title: "申请时间",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data) {
            	return new Date(data.createTime).format("yyyy-MM-dd hh:mm");
            }
        },{
            field: "stateDesc",
            title: "审核状态",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "reviewerRealName",
            title: "审核人",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data) {
            	if(data.reviewerRealName==null)
            		return '-';
            	return data.reviewerRealName;
            }
        },{
            field: "audit",
            title: "操作",
            width: 110,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
                var val = kendo.htmlEncode(data.state);
                var res = '';
               	res += "<a class='comRow-link detail' name='detail' eventId='" + data.eventId + "' reviewId='" + data.id + "' outCustomerId='" + data.outCustomerId + "' href='javascript:void(0);'>详情</a>";
                if(val=="wait_for_review" && auditAuth){
                    res += "<a class='comRow-link review' name='review' href='javascript:void(0);'>审核</a>";
                }
                return res;
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