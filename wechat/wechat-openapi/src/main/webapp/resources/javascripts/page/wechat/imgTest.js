function bindEvent() {
	$("body").delegate(".frontage-link", "click", function() {
//		 wx.previewImage({
//	            current: 'http://www.css88.com/doc/zeptojs_api/logo.png', // 当前显示图片的http链接
//	            urls: ['http://www.css88.com/doc/zeptojs_api/logo.png'] // 需要预览的图片http链接列表
//	        });
		 
		 wx.chooseImage({
           count: 1, // 默认9
           sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
           sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
           success: function (res) {
               var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
               wx.uploadImage({
                   localId: 'localIds', // 需要上传的图片的本地ID，由chooseImage接口获得
                   isShowProgressTips: 1, // 默认为1，显示进度提示
                   success: function (res) {
                       var serverId = res.serverId; // 返回图片的服务器端ID
                   }
               });
           }
       });
	});
	
	
	
//	$(".img-btn").click(function(){
//        wx.previewImage({
//            current: 'http://www.css88.com/doc/zeptojs_api/logo.png', // 当前显示图片的http链接
//            urls: [] // 需要预览的图片http链接列表
//        });
//    });
//    $(".up-btn").click(function(){
//        wx.chooseImage({
//            count: 1, // 默认9
//            sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
//            sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
//            success: function (res) {
//                var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
//                wx.uploadImage({
//                    localId: 'localIds', // 需要上传的图片的本地ID，由chooseImage接口获得
//                    isShowProgressTips: 1, // 默认为1，显示进度提示
//                    success: function (res) {
//                        var serverId = res.serverId; // 返回图片的服务器端ID
//                    }
//                });
//            }
//        });
//    });
}

$(function(){
	let urlTxt = encodeURIComponent(location.href.split('#')[0]); 
	
	$.ajax({
		type : 'POST',
		url : basepath + '/test/getConfig',
		data : {
			url : urlTxt
		},
		success : function(data) {
			  wx.config({
			        debug: true, 
			        appId: data.appId, 
			        timestamp: data.timestamp, 
			        nonceStr: data.nonceStr, 
			        signature: data.signature,
			        jsApiList: data.jsApiList
			    });
			    wx.ready(function(){
			    	alert("js-sdk调用成功");
			    });
			
		},
		error: function(data){
			showAlertWin("网络异常"+data.responseText);
		}
	});
	
	bindEvent();
});