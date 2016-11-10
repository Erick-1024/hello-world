var iamgeRegex = "(.jpg|.png|.jpeg|.pdf)$";
var MAX_SIZE = 5 * 1024 * 1024;
function verifyImageType(input) {
	var fileName = input.parent().find(".imageUpload").val();
	return fileName.toLowerCase().match(iamgeRegex);
};

function verifyImageSize(input) {
		var fileInput = input.parent().find(".frontage");
		var size;
//		if($.browser.msie){//ie旧版浏览器
//	        var fileMgr = new ActiveXObject("Scripting.FileSystemObject");
//	        var filePath = input[0].value;
//	        var fileObj = fileMgr.getFile(filePath);
//	        size = fileObj.size; //byte
//		}else//其它浏览器
			size = fileInput[0].files[0].size;//byte
		if(MAX_SIZE >= size)
			return true;
		return false;
};


function ajaxFileUpload(name) {
	$.ajaxFileUpload({
	    url: 'save',
	    type: 'post',
	    secureuri: false, //一般设置为false
	    fileElementId: name, // 上传文件的id、name属性名
	    dataType: 'text', //返回值类型，一般设置为json、application/json
	    success: function(data){
	    	if(data == "FAILED"){
	    		var notice = "<span id="+ name + "Id" + " style='color:#ff6600;'>上传失败</span>";
	    		$("#" + name).parent().find(".tageNotice").append(notice);
	    	}else if(-1 != data.indexOf("LARGE")){
	    		var notice = "<span id="+ name + "Id" + " style='color:#ff6600;'>" + data.split(":")[1] + "</span>";
	    		$("#" + name).parent().find(".tageNotice").append(notice);
			}else{
				$("input[name="+name+"Id]").val(data);
				var notice = "<a id="+ name + "Id" + " style='color:#0f8aba;' href=" + mediaserver + "imageservice?mediaImageId=" + data + "&mediaType=video" + " target='_blank'>查看</a>";
				$("#" + name).parent().find(".tageNotice").append(notice);
	    	}
	    	isImageTypeOk = false;
	    	isImageSizeOk = false;
	    },
	    error: function(data, status, e){
	    	var notice = "<span id="+ name + "Id" + " style='color:#ff6600;'>上传失败</span>";
    		$("#" + name).parent().find(".tageNotice").append(notice);
	    	isImageTypeOk = false;
	    	isImageSizeOk = false;
	    }
	});
};

