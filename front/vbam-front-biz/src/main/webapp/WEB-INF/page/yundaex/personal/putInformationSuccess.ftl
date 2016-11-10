<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="上传信息成功" jsFiles=[] cssFiles=["css/login.css"] localCssFiles=[] removeExtHeader = true>
<header class="main-header">
    <div class="stairMenuContent">
        <div class="wrap clearfix">
            <div class="navLeft">
                <a class="logo" href="${basepath}">CANA</a>
            </div>
            <div class="headRightOperate">
                <span class="userContent">Hi<a class="goLogin" href="${basepath}/facade/signin">请登录</a></span>
            </div>
        </div>
    </div>
</header>
<div class="main-registion">
    <div class="activate-wrap">
        <div class="activate-title">上传身份信息</div>
        <div class="activate-content">
            <div class="activate-cnt-head">身份信息提交成功！</div>
            <div class="activate-row">&nbsp;</span></div>
            <div class="activate-backrow">您的身份信息已提交，</br>审核结果将发送至您的邮箱，请注意查收！</div>
        </div>
    </div>
</div>
</@hb.htmlBase>