function generateEventDetailContent(eventType, subEventType, occur_time, amount, represent, extra_data) {
	var detailContent;
	if(eventType == 'SYSTEM') {
		detailContent='<tr class="status-tr-five hidden" name="detailContent">' +
            '<th></th>' +
            '<td>' +
                '<div class="status-tab">' +
                    '<table>' +
                        '<colgroup>' +
                            '<col width="200">' +
                            '<col width="250">' +
                        '</colgroup>' +
                        '<tr>' +
                            '<td>' + represent.standard.replace("<", "&lt;") + '</td>' +
                            '<td>' +
                            generateSystemData(extra_data, subEventType) +
                            '</td>' +
                        '</tr>' +
                    '</table>' +
                '</div>' +
            '</td>' +
        '</tr>';
	} else {
		detailContent='<tr class="status-tr-one hidden" name="detailContent">' +
            '<th></th>' +
            '<td>' +
                '<div class="status-tab">' +
                    '<table>' +
                        '<colgroup>' +
                            '<col width="100">' +
                            '<col width="250">' +
                        '</colgroup>' +
                        '<tr>' +
                            '<td>' + generateDataString(eventType) + '时间</td>' +
                            '<td>' + new Date(occur_time).format("yyyy-MM-dd") + '</td>' +
                        '</tr>' +
                        generateManualData(eventType, amount, extra_data) +
                        '<tr>' +
                            '<td>事件描述</td>' +
                            '<td><textarea name="represent" disabled="disabled">' + (represent == null ? '' : represent) + '</textarea></td>' +
                        '</tr>' +
                    '</table>' +
                '</div>' +
            '</td>' +
        '</tr>';
	}
	return detailContent;
}

function generateSystemData(extra_data, subEventType) {
	return '<span class="monitor-red">' + extra_data + '</span>';
//	var returnValue = '';
//	if(subEventType == 'CONTINUE_OVERDUE_NUM' || subEventType == 'TOTAL_OVERDUE_NUM') {
//		return '<span class="monitor-red">' + extra_data.number + '次</span>';
//	} else {
//		for(var i in extra_data) {
//			returnValue +='<span class="monitor-red">' + (extra_data[i].metirc * 100).toFixed(2) + '%' + '（' + extra_data[i].date + '）</span><br>';
//		}
//	}
//	return returnValue;
}

function generateManualData(eventType, amount, extra_data) {
	var returnValue = '<tr><td>';
	if(eventType == 'LITIGATION_DISPUTE') {//诉讼纠纷
		returnValue += '执行金额</td>' +
		'<td>' + amount + " 元";
	} else {
		 returnValue += '事件来源</td>' +
	     '<td>' + (extra_data.eventOrigin==undefined?"":extra_data.eventOrigin);
	}
	returnValue = returnValue +
	'</td>' +
    '</tr>' +
    '<tr>' +
    '<td>事件附件</td>' +
    '<td>';
	var medias = extra_data.medias;
	for(var i in medias) {
		returnValue += '<div class="look-img-two"><span class="look-img-title" style="border:none">' + medias[i].fileName + '</span><a class="status-normal status-chk fly-left" href="' + mediaserver + 'imageservice?mediaImageId=' + medias[i].mediaId + '&mediaType=download">下载</a></div>';
	}
	returnValue = returnValue +
	'</td>' +
    '</tr>';
	return returnValue;
}

function generateDataString(eventType) {
	if(eventType == 'LITIGATION_DISPUTE') {//诉讼纠纷
		return '判决';
	} else {
		return '发生';
	}
}