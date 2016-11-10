<#import "/common/htmlBase.ftl" as hb>
<#-- jsFiles 填写需要的js文件，以js开头的是静态服务器的js，其他是本项目的js，cssFiles 是静态服务器css，localCssFiles 是本项目的css文件（填写除公共路径以外的路径） -->
<@hb.htmlBase title="Cana" jsFiles=[] cssFiles=[] localCssFiles=[] removeExtFooter = true>
	<div class="backstage-mainWrap">
	    <div class="backstage-slogn">欢迎，<span>${currentUser().userData.username}</span>登录金融信息平台！</div>
	    <footer class="backstage-foot">Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
	</div>
</@hb.htmlBase>