
function showCustomPopWindow(template){
    new PopWindow({
        title: "提示消息",
        width: 460,
        reload: true,
        resizable: false,
        visible: true,
        content: {
            template: template
        }
    }).init().center();
};

function showSuccessWin(text){
	var template = "<div class='dlg-common-row'><span class='dlg-notice notice-icon02'></span><span class='notice-content'>" + text + "</span></div>";
	showCustomPopWindow(template);
};

function showAlertWin(text){
	var template = "<div class='dlg-common-row'><span class='dlg-notice notice-icon01'></span><span class='notice-content'>" + text + "</span></div>";
	showCustomPopWindow(template);
};