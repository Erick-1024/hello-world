<!--确认弹窗-->
<script id="template-notice" type="text/x-kendo-template">
    <div id="confirm-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice notice-icon01"></span>
            <span id="notice-content" class="notice-content"></span>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" name="confirmBoxOK" href="javascript:void(0);">确认</a>
            <a class="default-link back-link" name="confirmBoxCancel" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>

<script>
	var confirmPopWindow;
	$(function() {
		//初始化消息弹窗
	    confirmPopWindow = new PopWindow({
	        title: "提示",
	        width: 420,
	        reload: true,
	        template: "#template-notice"
	    }).init();
	});
	//弹出消息弹窗方法
	function showConfirmBox(text) {
		confirmPopWindow.open().center();
	    $("#confirm-box-window .notice-content").text(text);
	}
</script>