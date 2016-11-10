<!--提示弹窗模板-->
<script id="tipBox_template" type="text/x-kendo-template">
	<a href="javascript:void(0);" class="open-message-btn" style="display:none;"></a>
    <div id="tip-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice"></span>
            <span id="notice-content" class="notice-content"></span>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" name="tipBoxButton" href="javascript:void(0);">确认</a>
        </div>
    </div>
</script>

<script>
	var tipBoxTemplate;
	$(function() {
		//初始化消息弹窗
    	tipBoxTemplate = new PopWindow(".open-message-btn",{
	        title: "提示",
	        width: 400,
	        reload: true,
	        template: "#tipBox_template"
    	}).init();
	});
	//弹出消息弹窗方法
	function showTipBox(icon, text) {
		tipBoxTemplate.open().center();
		var iconSpan = $("#tip-box-window .dlg-notice");
		iconSpan.removeClass();
		iconSpan.addClass("dlg-notice");
		iconSpan.addClass(icon);
		$("#tip-box-window .notice-content").text(text);
	}
</script>