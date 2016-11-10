<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="" curTopMenu = "" curSubMenu = "" jsFiles=[] cssFiles=["css/login.css"] localCssFiles=[] removeExtHeader = true removeExtFooter = false>
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

<div class="main-registion">
    <div class="activate-wrap">
        <div class="activate-title"> 忘记密码提示</div>
        <div class="forgetPwd-content">
            <div>
                <strong>1.</strong> 企业账号忘记密码？
                <p>请于CANA管理员联系重置密码，联系电话：021-53866655</p>
            </div>
            <div>
                <strong>2.</strong> 员工账号忘记密码？
                <p>请于企业管理员联系重置密码。</p>
            </div>
            <div>
                <strong>3.</strong> 忘记支付密码？
                <p>请于CANA管理员联系重置密码，联系电话：021-53866655</p>
            </div>
        </div>
    </div>
</div>

</@hb.htmlBase>