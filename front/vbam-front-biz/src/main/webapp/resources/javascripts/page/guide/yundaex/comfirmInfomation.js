$(function(){
		
	//初始化操作结果弹窗
	tipPopWindow = new PopWindow({
		title: "提示",
		width: 420,
		reload: true,
		template: "#tipBox_template"
	}).init();
	
    $("#confirmBtn").click(function(){
    	$(this).unbind();
    	$(this).text("证书校验中...");
    	$(this).css("background-color", "#ccc");
    	Signature.setDNFilter(companyName);
    	Signature.installCert({callback:confirmAccountInfo, error: error});
    });
    
    $("body").on("click","#signatureSoftwateInstall",function(){
    	  $(Signature.softwareUrls).multiDownload(); 
    });
    
    Signature.onLoad();
    if (navigator.appName.indexOf("Internet") >= 0 || navigator.appVersion.indexOf("Trident") >= 0) {
    	setInterval("Signature.judeSoftwareInstall($('#signatureSoftwateInstall').parent())", 6000);
    }else{
    	Signature.judeSoftwareInstall($("#signatureSoftwateInstall").parent());
    	
    	//判断是否为google浏览器,如果是且版本是45以上,软件不支持
    	var browser = getChromeBrowserInfo();
    	if(browser){ 
    		var verinfo = (browser+"").replace(/[^0-9.]/ig,"");
    		var versionNo = verinfo.substring(0, verinfo.indexOf("."));
    		if(versionNo > 45){
    			$("#signatureSoftwateInstall").parent().parent().before(hintMessage());
    		}
    	}
    }
});

function processing(){
	$("#confirmBtn").text("证书校验中...");
	$("#confirmBtn").css("background-color", "#ccc");
}

function confirmAccountInfo(){
	var isCreateNewAccount = $.trim($("input[name=nameOne]:checked").val());
	var supervisionAccountNo = $.trim($("#accountNo").val());
	window.location.href = encodeURI(basepath + "/guide/yundaex/confirmInfomation?isCreateNewAccount=" + isCreateNewAccount + "&supervisionAccountNo=" + supervisionAccountNo);
}

function error(){
	$("#confirmBtn").text("确认");
	$("#confirmBtn").css("background-color", "#14ade9");
	 $("#confirmBtn").click(function(){
    	$(this).unbind();
    	$(this).text("证书校验中...");
    	$(this).css("background-color", "#ccc");
    	Signature.installCert({callback:confirmAccountInfo, error: error});
    });
}
function getChromeBrowserInfo(){
	var agent = navigator.userAgent.toLowerCase() ;
	var regStr_chrome = /chrome\/[\d.]+/gi ;
	
	//Chrome
	if(agent.indexOf("chrome") > 0)
	{
		return agent.match(regStr_chrome) ;
	}
	return false;
}

function hintMessage(){
	return "<div style='text-align:center;margin-bottom:20px'><span><font color=red>您当前的谷歌浏览器版本过高，不能使用安全控件，请更换浏览器，建议使用ie浏览器</font></span><div>";
}
