var groupId;
var deleteId;
var canOperate;
$(function() {
	
	groupId = $('#id').text();
	
	canOperate = $('[status]').attr('status') == 'CREATE';
	
	$('body').on('click', 'a[name=tipBoxButton]', function() {
		tipBoxTemplate.close();
	});
	
	$('body').on('click', 'a[name=confirmBoxOK]', function() {
		cana.get(basepath + "/asset/archivesManagement/delete?id=" + deleteId, null, deleteSuccess, requestError, null);
    	confirmPopWindow.close();
    });
	
	//加载文件列表
	updateMediaList();
	
	//点击列表中的文件夹
	$("body").on("click",".next-Page",function(){
		addBreadcrumbsNavigation($(this).data('id'), $(this).text());
    });
	
	//返回上一级点击事件
    $("body").on("click",".upPage",function(){
    	deleteBreadcrumbsNavigation(1);
    });
	
    //全部文件点击事件
    $("body").on("click",".allPage",function(){
    	deleteBreadcrumbsNavigation(0);
    });
    
    //点击相应目录事件
    $("body").on("click",".toPage",function(){
    	deleteBreadcrumbsNavigation($(this).nextAll('.toPage').size() + 1);
    });
    
    //新建文件夹事件
    $(".new-folder").on("click",function(){
    	var num = $(".file-inBox").find(".rowForNew");
        var html = $('<tr class="ones-file">'+
                '<td class="rowForNew">'+
                '<div class="new-files">'+
                '<input type="text" value="新建文件夹" class="new-files-txt">'+
                '<a href="javascript:void(0);" class="d-up-file"></a>'+
                '<a href="javascript:void(0);" class="d-del-file"></a>'+
                '</div>'+
                '</td>'+
                '<td class="textAC"></td>'+
                '<td class="textAC">' + new Date().format("yyyy-MM-dd hh:mm") + '</td>'+
                '<td class="textAC">'+
                '</td>'+
                '</tr>');
        if(num.length == 0){
            html.prependTo($(".file-inBox"));
            html.find(".new-files-txt").select();
        }else{
            $(".new-files-txt").select();
        }
        
    });
    //新建文件夹叉号删除事件
    $("body").on("click",".d-del-file",function(){
        $(this).parent().parent().parent().remove();
    });
    
    //新建文件夹勾号事件
    $("body").on("click",".d-up-file",function(){
    	var directoryName = $(".new-files-txt").val();
    	if(!new RegExp('^[^\\/:*?"<>|]+$').test(directoryName)) {
    		showTipBox("notice-icon01", '名称不能含有/:*?"<>|这些字符');
    	} else if(directoryName.length > 255) {
    		showTipBox("notice-icon01", '名称不能超过255个字符');
    	} else {
    		cana.postSync(basepath + "/asset/archivesManagement/saveDirectory", {path: getPath(), type: 'DIRECTORY', groupId: groupId, fileName: directoryName}, createDircetorySuccess, null, null);
    	}
    });
    
    //文件上传
    $("#photos").kendoUpload({
        async: {
            saveUrl: basepath + "/asset/archivesManagement/saveFile",
            autoUpload: true
        },
        multiple: true,
        showFileList: false,
        upload: onUpload,
        success:onSuccess,
        error:onError
    });

    //列表删除事件
    $("body").on("click",".delThis",function(){
    	var fileElement = $(this).parent().siblings(':first').children();
    	deleteId = fileElement.data('id');
    	var confirmText = '确定删除' + fileElement.text();
    	if(fileElement.is('a')) {
    		confirmText += '以及该文件夹内的所有文件';
    	}
    	confirmText += '？';
    	showConfirmBox(confirmText);
    });
    
    //列表下载事件
    $("body").on("click",".downloadThis",function(){
    	window.open(basepath + "/asset/archivesManagement/download?groupId=" + groupId + "&ids=" + $(this).parent().siblings(':first').children().data('id'));
    });
    
    //下载全部点击事件
    $(".download-all").on("click",function(){
    	if($(".first-allPage").is(":hidden") || (!$(".first-allPage").is(":hidden") && $('.ones-file').length > 0)) {
    		window.open(basepath + "/asset/archivesManagement/download?groupId=" + groupId);
    	} else {
    		showTipBox('notice-icon01', '没有可下载的文件');
    	}
    });
    
});

function bytesToSize(bytes) {
    if (bytes === 0) return '0 B';
    var k = 1024,
        sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
        i = Math.floor(Math.log(bytes) / Math.log(k));
   return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
}

function onSuccess(e){
    $("#upload-a-btn").text("上传附件");
    if(e.response.status != "SUCCESS"){
    	$.each(e.files, function () {
    		showTipBox("notice-icon03", e.response.message);
        });
    }
    updateMediaList();
}

function onError(){
	$("#upload-a-btn").text("上传附件");
	showTipBox("notice-icon03", '上传失败');
}

function onUpload(e) {
    var files = e.files;
    $("#upload-a-btn").text("上传中...");
    $.each(files, function () {
        e.data = { path: getPath(), type: 'FILE', groupId: groupId };
    });
}

function requestError(data) {
	showTipBox("notice-icon03", data);
}

function deleteSuccess(data) {
	updateMediaList();
	showTipBox("notice-icon02", "删除成功");
}

function createDircetorySuccess(data) {
	var html= $('<a class="next-Page d-link-text" href="javascript:void(0);" data-id="' + data.id + '">' + data.name + '</a>');
    $(".rowForNew").addClass("toPageBox");
    $(".new-files").remove();
    html.prependTo($(".rowForNew"));
    $(".rowForNew").removeClass("rowForNew");
    var operate = (sim_am_download ? '<a class="d-link-text downloadThis" href="javascript:void(0);">下载</a>' : '') + (sim_am_delete && sim_am_download && canOperate ? '<em>&nbsp;&nbsp;&nbsp;&nbsp;</em>' : '') + (sim_am_delete && canOperate ? '<a class="d-link-text delThis" href="javascript:void(0);">删除</a>' : '');
    $('.ones-file:first').children('.textAC:last').append(operate);
}

function getPath() {
	var path = '/';
	if($(".first-allPage").is(":hidden")) {
		$('.toPage').each(function() {
			path += ($(this).text() + '/');
		});
		path += ($('.currentPage').text() + '/');
	}
	return path;
}

function addBreadcrumbsNavigation(id, name) {
	$(".first-allPage").hide();
    $(".navPageBox").show();
    var currentPage = $('.currentPage');
    if(currentPage.length != 0) {
    	var dataId = currentPage.data('id');
    	var dataName = currentPage.text();
    	currentPage.remove();
    	$('.navPageOne').append('<a class="toPage d-link-text" href="javascript:void(0);" data-id="' + dataId + '">' + dataName + '</a>');
    }
	$('.navPageOne').append('<span class="midden-line">&gt;</span><span class="currentPage" data-id="' + id + '">' + name + '</span>');
	updateMediaList();
}

function deleteBreadcrumbsNavigation(i) {
	var step = $('.toPage').length;
	if(i > step || i < 1) {
		$('.navPageOne > *:gt(2)').remove();
		$(".first-allPage").show();
	    $(".navPageBox").hide();
	} else {
		var toPage = $('.toPage:eq(' + (step - i) + ')');
		var dataId = toPage.data('id');
    	var dataName = toPage.text();
    	toPage.nextAll().remove();
    	toPage.remove();
    	$('.navPageOne').append('<span class="currentPage" data-id="' + dataId + '">' + dataName + '</span>');
	}
	updateMediaList();
}

function updateMediaList() {
	$('.file-inBox').empty();
	cana.post(basepath + "/asset/archivesManagement/getMediaList", {path: getPath(), groupId: groupId}, laodMediaListSuccess, null, null);
}

function laodMediaListSuccess(data) {
	$.each(data,function(index, obj){
		var html = '<tr class="ones-file"><td class="toPageBox">';
    	if(obj.type == 'FILE') {
    		html += '<span data-id="' + obj.id + '">' + obj.name + '</span>';
    	} else {
    		html += '<a class="next-Page d-link-text" href="javascript:void(0);" data-id="' + obj.id + '">' + obj.name + '</a>';
    	}
		html += '</td><td class="textAC">';
		if(obj.type == 'FILE') {
			html += bytesToSize(obj.size);
		}
		html += '</td><td class="textAC">' + new Date(obj.createTime).format("yyyy-MM-dd hh:mm") + '</td><td class="textAC">';
		if(sim_am_download) {
			html += '<a class="d-link-text downloadThis" href="javascript:void(0);">下载</a>';
		}
		if(sim_am_download && obj.canDelete && sim_am_delete && canOperate) {
			html += '<em>&nbsp;&nbsp;&nbsp;&nbsp;</em>';
		}
		if(obj.canDelete && sim_am_delete && canOperate) {
			html += '<a class="d-link-text delThis" href="javascript:void(0);">删除</a>';
		}
		html += '</td></tr>';
		$(".file-inBox").append(html);
     });
}