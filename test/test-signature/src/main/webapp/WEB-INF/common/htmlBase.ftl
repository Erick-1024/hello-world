<#import "/template/menuUtils.ftl" as mu/>

<#macro htmlBase title = "Cana" jsFiles=[] cssFiles=[] localCssFiles=[]
	curTopMenu = "" curSubMenu = ""
	showMenu = true
	onlyShowMainMenu=false
	removeExtHeader = false
	removeExtFooter = false>

<!DOCTYPE html>
<html>
<head lang="en">
	<link rel="shortcut icon" href="${host}/images/favicon.ico" type="image/x-icon">
	<link rel="icon" href="${host}/images/favicon.ico" type="image/x-icon">
	<title>${title}</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${host}/css/kendo.common.min.css">
    <link rel="stylesheet" href="${host}/css/kendo.reset.css">
    <link rel="stylesheet" href="${host}/css/jquery-ui-1.10.3.custom.min.css">
    <link rel="stylesheet" href="${host}/css/common.css">
    <script src="${basepath}/resources/javascripts/common/init.js"></script>
    <script src="${basepath}/resources/javascripts/common/popWindow.js"></script>
    <script src="${host}/js/common/jquery-1.11.2.min.js"></script>
    <script src="${host}/js/common/kendo.web.min.js"></script>
    <script src="${host}/js/common/jquery.cookie.js"></script>
    <script src="${host}/js/vendor/jquery-ui-1.10.3.custom.min.js"></script>
    <script src="${host}/js/vendor/underscore-min.js"></script>
    <script src="${host}/js/common/global.js"></script>
    <script src="${host}/js/common/common.js"></script>
    <!--[if lte IE 9]>
    <script src="${host}/js/common/html5.js"></script>
    <![endif]-->
	
	<#-- css -->
	<#list cssFiles as css>
		<link rel="stylesheet" href="${host}/${css}?v=${getStaticResourceVersion()}" />
	</#list>
	<#list localCssFiles as localCss>
		<link rel="stylesheet" href="${basepath}/resources/css/${localCss}?v=${getStaticResourceVersion()}" />
	</#list>
	<script type="text/javascript">
		var basepath = '${basepath}';
		var host = '${host}';
		var mediaserver = '${mediaserver}';
		var curMenu = '';
		var sessionId = '${(session.id)!''}';
		var SID = '${(SID)!''}';
		<#if (currentUser())??>
			var username = '${currentUser().userData.username}';
			var userKey = '${currentUser().userData.id}';
			var isLogin = true;
			$(function(){
				$.ajax({
			        type: 'POST',  
			        url: "${basepath}/message/notification/searchList",
			        data: {
			        	isRead:"false"
			        },
			        success:function(data){
			        	var total = data.total;
			        	var maxTotalShow = "99";
			        	if(parseInt(total) <= parseInt(maxTotalShow))
							$(".emailFonts").text(total);
						else if(parseInt(total) > parseInt(maxTotalShow))
						 	$(".emailFonts").text(maxTotalShow+"+");
			        }
			    });
			})
		<#else>
			var isLogin = false;
		</#if>
	</script>
	
	<#--Log 日志-->
	<#--
	<script type="text/javascript" src="${basepath}/resources/javascripts/common/log.js"></script>
	-->
	
	<#list jsFiles as js>
		<#if js?starts_with('js')>
			<script type="text/javascript" src="${host!""}/${js}?v=${getStaticResourceVersion()}"></script>
		<#else>
			<script type="text/javascript" src="${basepath!""}/resources/javascripts/${js}?v=${getStaticResourceVersion()}"></script>
		</#if>
	</#list>
	
	<script type="text/x-kendo-template" id="message_template">
		<div class="#=statusClass#">
			<p>#=message#</p>
		</div>
	</script>
	<!-- @CLB@ -->
</head>
<body>
	<#if !removeExtHeader>
		<header class="main-header">
	        <div class="stairMenuContent">
	            <div class="wrap clearfix">
	                <div class="navLeft">
	                    <a class="logo" href="${basepath}">CANA</a>
	                    <#if showMenu>
	                    <ul class="stairMenu">
	                    	<@mu.printUMMenu curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
							<@mu.printAMMenu curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
							<@mu.printRMMenu curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
	                    	<@mu.printPMMenu curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
	                    	<@mu.printPCMenu curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=true/>
	                    	<@mu.printMAMenu curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=true/>
	                    	<@mu.printTZMenu curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
	                    	<@mu.printRPMenu curTopMenu=curTopMenu curSubMenu=curSubMenu onlyShowMainMenu=onlyShowMainMenu/>
	                    </ul>
	                    </#if>
	                </div>
	                <div class="headRightOperate">
	                	<#if (currentUser().userData)??>
							<a class="emailContent" href="${basepath}/message/notification/list">
								<i class="emailIcon"></i>
								<span class="emailFonts"></span>
							</a>
						</#if>
	                    <span class="userContent"><a style="color:white;text-decoration:none" href="${basepath}/user/individual">Hi，${(currentUser().userData.username)!""}</a></span>
	                    <a href="${basepath}/signOut" class="exitFonts">退出</a>
	                </div>
	            </div>
	        </div>
	        <#if showMenu>
	        	<div class="headSecondLevel"></div>
	        </#if>
	    </header>
	</#if>
	<#nested/>
	<#if !removeExtFooter>
		<footer id="footTop">Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
	</#if>
</body>
</html>
</#macro>
