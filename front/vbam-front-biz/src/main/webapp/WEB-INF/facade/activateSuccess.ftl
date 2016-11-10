<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="" curTopMenu = "" curSubMenu = "" jsFiles=["js/vendor/jquery.ztree.core-3.5.js","js/vendor/jquery.ztree.excheck-3.5.js","/facade/activateSuccess.js"] cssFiles=["css/zTree.css"] localCssFiles=["/facade/success.css"] removeExtHeader = true removeExtFooter = false>
<header class="main-header">
    <div class="stairMenuContent">
        <div class="wrap clearfix">
            <div class="navLeft">
                <a class="logo" href="${basepath}/facade/signin">CANA</a>
            </div>
            <div class="headRightOperate">
                <span class="userContent">Hi<a class="goLogin" href="${basepath}/facade/signin">请登录</a></span>
            </div>
        </div>
    </div>
</header>

<div class="main-staffAcu">
    <div class="staffAcu-wrap">
        <div class="staffAcu-title">员工账户激活</div>
        <div class="staffAcuActive-content">
            <div class="staffAcu-cnt-head" id="roleId" data-roleId ="${userDTO.roleId!}">您的账户已激活成功！</div>
            <div class="staffAcu-row">用户名：<span>${userDTO.username!}</span></div>
            <div class="staffAcu-row">已开通权限：</div>
			<div id="roletree" class="ztree"></div>
            <div class="staffAcu-row">请妥善保管您的用户名、密码。如有疑问请与CANA管理员联系：021-53866655。</div>
            <div class="staffAcu-foot">
                <a class="staffAcu-link" href="${basepath}/facade/signin">登 录</a>
            </div>
        </div>
    </div>
</div>
</@hb.htmlBase>
