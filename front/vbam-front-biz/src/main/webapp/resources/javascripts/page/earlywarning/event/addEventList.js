var companyNameInput;
var outCustomerNameInput;
var earlywarningTypeSelect;
var amountInput;
var courtTimeInput;
var occurTimeInput;
var representInput;
var reviewExtraInput;
var earlywarningLevelInput;
var earlywarningLevelButton;
var predictLevelIcon;
var companyNameText;
var outCustomerNameText;
var currentEarlywaringLevelIcon;
var addEventButton;
var addWindow;
var eventOriginInput;

var FILE_MAX_SIZE = 20 * 1024 * 1024;

var courtExecutionCompany = 'COURT_EXECUTION_COMPANY';
var courtExecutionIndividual = 'COURT_EXECUTION_INDIVIDUAL';
var negativeReport = 'NEGATIVE_REPORT';
var other = 'OTHER';

$(function(){
	
	//jquery对象赋值
	companyNameInput = $('[name=companyName]');
	outCustomerNameInput = $('[name=outCustomerName]');
	earlywarningTypeSelect = $('#selectWrap');
	amountInput = $('[name=amount]');
	courtTimeInput = $('[name=courtTime]');
	occurTimeInput = $('[name=occurTime]');
	representInput = $('[name=represent]');
	reviewExtraInput = $('[name=reviewExtra]');
	earlywarningLevelInput = $('[name=earlywarningLevel]');
	earlywarningLevelButton = $('.war-out');
	predictLevelIcon =  $('#predictLevel');
	companyNameText = $('#companyName');
	outCustomerNameText = $('#outCustomerName');
	currentEarlywaringLevelIcon = $('#earlywaringLevel');
	addEventButton = $('#addEvent');
	addWindow = $(".new-style-box");
	eventOriginInput = $('[name=eventOrigin]');
	
	bindEntry();
	
	$('body').on('click', 'a[name=tipBoxButton]', function() {
		tipBoxTemplate.close();
	});
	
	// 附件删除按钮
	$("body").on("click", ".delete", function(){
    	$(this).parent().remove();
    });
	
	//点击"查询"
	$('.form-search-link').click(function(){
		if($.trim(companyNameInput.val()) == '') {
			showTipBox("notice-icon01", "客户名称不能为空");
		} else if($.trim(outCustomerNameInput.val()) == '') {
			showTipBox("notice-icon01", "外部客户名称不能为空");
		} else {
			query();
		}
    });
	
	// 事件类型变化触发
	earlywarningTypeSelect.change(function(){
        if($(this).val() == courtExecutionCompany || $(this).val() == courtExecutionIndividual){
            $(".opt-before").removeClass("hidden");
            $(".opt-after").addClass("hidden");
            $(".opt-after-two").addClass("hidden");
        }else if($(this).val()== negativeReport || $(this).val()== other){
            $(".opt-before").addClass("hidden");
            $(".opt-after").removeClass("hidden");
            $(".opt-after-two").removeClass("hidden");
        }
        // 预计预警等级提示
    	predictLevelIcon.empty();
        clearEventContent();
    });
	
	//文件input
	$('#file').change(function() {
		ajaxFileUpload(this,$(this).attr('id'), $(this).val());
	});
	
	//公司名称
	companyNameText.change(function() {
		switchPredictType();
	});
	
	outCustomerNameText.change(function() {
		switchPredictType();
	});
	
	//判决时间
	courtTimeInput.change(function() {
		predictCourtExecution();
	});
	
	//预警等级
	earlywarningLevelButton.click(function() {
		predictOther($(this).attr('enum'), false);
	});
	
	//提交
	$('a[name=submit-link]').click(function() {
		var postData;
		var earlywarningType = earlywarningTypeSelect.val();
		var money = amountInput.val().parseMoney().replace('.', '');
		var courtDate = $.trim(courtTimeInput.val());
		var occurDate = $.trim(occurTimeInput.val());
		if($.trim(companyNameText.val()) == '') {
			showTipBox("notice-icon01", '请输入客户名称');
			return;
		}
		if($.trim(outCustomerNameText.val()) == '') {
			showTipBox("notice-icon01", '请输入外部客户名称');
			return;
		}
		if(earlywarningType == courtExecutionCompany || earlywarningType == courtExecutionIndividual) {
			if(courtDate == '') {
				showTipBox("notice-icon01", '请选择判决时间');
				return;
			} else if (money == '') {
				showTipBox("notice-icon01", '请输入执行金额');
				return;
			}
			postData = getCommonData(earlywarningType, courtDate);
			postData.amount = money;
			postData.extra = {medias: getMediaList()};
			postAddEvent(postData);
		} else if(earlywarningType == negativeReport || earlywarningType == other){
			if(occurDate == '') {
				showTipBox("notice-icon01", '请选择事件发生时间');
				return;
			}
			if(earlywarningLevelInput.val() == '') {
				showTipBox("notice-icon01", '请选择预警事件状态');
				return;
			}
			postData = getCommonData(earlywarningType, occurDate);
			postData.earlywarningLevel = earlywarningLevelInput.val();
			postData.extra = {eventOrigin: eventOriginInput.val(), medias: getMediaList()};
			postAddEvent(postData);
		}
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

function getCommonData(earlywarningType, time) {
	var postData = {};
	postData.companyName = $.trim(companyNameText.val());
	postData.outCustomerName = $.trim(outCustomerNameText.val());
	postData.earlywarningEventCategory = earlywarningType;
	postData.date = time;
	postData.represent = $.trim(representInput.val());
	postData.reviewExtra = $.trim(reviewExtraInput.val());
	return postData; 
}

function getMediaList() {
	var mediaMaps = [];
	$('.look-img-title').each(function() {
		mediaMaps.push({fileName: $(this).text(), mediaId: $(this).attr('mediaid')});
	})
	return mediaMaps;
}

function postAddEvent(postData) {
	$.ajax({
	    url: basepath + "/earlywarning/event/add",
	    /**必须是POST方法**/
	    type:'post',
	    /**返回的数据类型**/
	    dataType:'json',
	    /**必须制定请求的类型**/
	    contentType:'application/json',
	    data:JSON.stringify(postData),
	    success:function(data){
	    	if (data.status.toLowerCase() == 'success') {
	    		addEventSuccess();
			} else if (data.status.toLowerCase() == 'failed') {
				showTipBox("notice-icon03", data.message);
			}
	    }
	});
}

function addEventSuccess() {
	showTipBox("notice-icon02", "增加预警事件成功，通过审核后即可生效！");
	closeWin();
}

function switchPredictType() {
	var earlywarningType = earlywarningTypeSelect.val();
	switch(earlywarningType) {
		case courtExecutionCompany:    
	    case courtExecutionIndividual:
	    	predictCourtExecution();
	    	break;
	    case negativeReport:
	    case other:
	    	predictOther(null, true);
	    	break;
	}
}

function predictOther(inputLevel, isJudgeEarlywarningLevelInput) {
	if(isJudgeEarlywarningLevelInput) {
		if(earlywarningLevelInput.val() == '')
			return;
	}
	var companyNameValue = $.trim(companyNameText.val());
	var outCustomerNameValue = $.trim(outCustomerNameText.val());
	if(companyNameValue != '' && outCustomerNameValue != '') {
		cana.post(basepath + "/earlywarning/getPredictEarlyWarningLevel/add", {companyName: companyNameValue, outCustomerName: outCustomerNameValue, type: earlywarningTypeSelect.val(), level: (inputLevel == null ? earlywarningLevelInput.val() : inputLevel)}, predictSuccess, function(){}, null);
	}
}

function predictCourtExecution() {
	if(earlywarningTypeSelect != undefined) {  //IE8会在页面加载完后执行onpropertychange，为了防止IE报错
		var earlywarningType = earlywarningTypeSelect.val();
		var money = fmoney(amountInput.val()).replace('.', '').replace(',', '');
		var date = $.trim(courtTimeInput.val());
		var companyNameValue = $.trim(companyNameText.val());
		var outCustomerNameValue = $.trim(outCustomerNameText.val());
		if((earlywarningType == courtExecutionCompany || earlywarningType == courtExecutionIndividual) && date != '' && money != '' && companyNameValue != '' && outCustomerNameValue != '') {
			cana.post(basepath + "/earlywarning/getPredictEarlyWarningLevel/add", {companyName: companyNameValue, outCustomerName: outCustomerNameValue, type: earlywarningType, amount: money, occurTime: date}, predictSuccess, function(){}, null);
		}
	}
}

function predictSuccess(data) {
	predictLevelIcon.empty();
	if(data == null) {
		setLevel(null, "正常", predictLevelIcon);
	} else {
		for(var key in data) {
			setLevel(key, data[key], predictLevelIcon);
		}
	}
}

function query() {
	// 客户名称输入框
	companyNameInput.removeAttr('productId').removeAttr('memberId');
	outCustomerNameInput.removeAttr('outCustomerId');

	// 当前预警等级提示
	currentEarlywaringLevelIcon.empty();
	// 列表
	$($('.new-war-title').siblings()).remove();
	
	// 客户名称（添加弹框）
	companyNameText.val('');
	outCustomerNameText.val('');
	
	// 预计预警等级提示
	predictLevelIcon.empty();
	
	clearEventContent();
	
	cana.post(basepath + "/earlywarning/event/query/earlyWarningTypeList", {companyName: $.trim(companyNameInput.val()), outCustomerName: $.trim(outCustomerNameInput.val())}, laodList, queryError, null);
}

function laodList(data) {
	
	companyNameInput.attr('productId', data.productId).attr('memberId', data.memberId);
	companyNameText.val($.trim(companyNameInput.val()));
	outCustomerNameText.val($.trim(outCustomerNameInput.val()));
	
	outCustomerNameInput.attr('outCustomerId', data.outCustomerId);
	
	setLevel(data.earlywaringLevel, data.eralywaringLevelDesc, currentEarlywaringLevelIcon);
	
	for(var i in EarlywarningEventCategory) {
		$('.new-war-table tbody').append('<tr type=' + i + '>' + 
	            '<td>' + EarlywarningEventCategory[i] + '</td>' +
	            '<td>0</td>' + 
	            '<td>-</td>' +
	            '<td><a class="new-link" href=' + basepath + '/earlywarning/event/earlyWarningEventHistory/' + i + '/' + companyNameInput.attr('memberId') + '/' + outCustomerNameInput.attr('outCustomerId') + '/history/event>预警事件历史</a></td>' +
	        '</tr>');
	}
	
	var earlyWaringEventTypeDTOs = data.earlyWaringEventTypeDTOs;
	for(var i in earlyWaringEventTypeDTOs) {
		$('[type=' + earlyWaringEventTypeDTOs[i].earlywaringEventType + '] > *:eq(1)').text(earlyWaringEventTypeDTOs[i].number);
		$('[type=' + earlyWaringEventTypeDTOs[i].earlywaringEventType + '] > *:eq(2)').text(new Date(earlyWaringEventTypeDTOs[i].updateTime).format("yyyy-MM-dd hh:mm"));
	}
	
	$('#earlywaringLevel > a').clone().appendTo(predictLevelIcon);
}

function queryError(data) {
	showTipBox("notice-icon01", data);
}

/*打开弹窗*/
function openWin(){
	$("body").unbind("keydown");
	addWindow.show();
}
/*关闭弹窗*/
function closeWin(){
	bindEntry();
	addWindow.hide();
}

function verifyFileSize(input) {
	if(isIE())
		return true;
	var fileInput = $(input).parent().find(".up-frontage");
	var size = fileInput[0].files[0].size;//byte
	if(FILE_MAX_SIZE >= size)
		return true;
	return false;
};

function ajaxFileUpload(obj,name, fileName) {
	if(!verifyFileSize(obj)){
		showTipBox("notice-icon03", "文件过大");
		return;
	}
	
	$.ajaxFileUpload({
	    url: basepath + '/facade/saveFile',
	    type: 'post',
	    secureuri: false, //一般设置为false
	    fileElementId: name, // 上传文件的id、name属性名
	    dataType: 'text', //返回值类型，一般设置为json、application/json
	    success: function(data){
	    	if(data == "FAILED"){
	    		showTipBox("notice-icon03", "文件上传失败");
	    	}else if(data.indexOf("Large")>-1){
	    		showTipBox("notice-icon03", "文件过大");
	    	}else{
	    		$("#file-size-tip").after('<div class="look-img-two"><span class="look-img-title" style="border:none" mediaId="' + data + '">' + fileName.substring(fileName.lastIndexOf('\\')+1) + '</span><a class="status-normal status-chk fly-left" href="' + mediaserver + 'imageservice?mediaImageId=' + data + '&mediaType=download">下载</a><a style="width:30px" class="status-normal status-chk fly-left delete" href="javascript:void(0);"><i class="delIcon"></i></a></div>');
	    	}
	    },
	    error: function(data, status, e){
	    	showTipBox("notice-icon03", data);
	    }
	});
};

function setLevel(earlywaringLevel, eralywaringLevelDesc, parentDom) {
	if(earlywaringLevel == null) {
		$(parentDom).append('<a class="monitor-Bg monitor-skyBg" href="javascript:void(0);">' + eralywaringLevelDesc + '</a>');
	} else if(earlywaringLevel == 'yellow') {
		$(parentDom).append('<a class="monitor-Bg monitor-yellowBg" href="javascript:void(0);">' + eralywaringLevelDesc + '</a>');
	} else if(earlywaringLevel == 'orange') {
		$(parentDom).append('<a class="monitor-Bg monitor-orangeBg" href="javascript:void(0);">' + eralywaringLevelDesc + '</a>');
	} else {
		$(parentDom).append('<a class="monitor-Bg monitor-redBg" href="javascript:void(0);">' + eralywaringLevelDesc + '</a>');
	}
}

function clearEventContent() {
	// 执行金额
	amountInput.val('');
	// 判决时间
	courtTimeInput.val('');
	// 发生时间
	occurTimeInput.val('')
	// 事件描述
	representInput.val('');
	// 说明
	reviewExtraInput.val('');
	// 预警等级Input
	earlywarningLevelInput.val('');
	// 预警等级按钮
	earlywarningLevelButton.removeClass('war-on');
	// 附件
	$('.look-img-two').remove();
	// 事件来源
	eventOriginInput.val('');
}