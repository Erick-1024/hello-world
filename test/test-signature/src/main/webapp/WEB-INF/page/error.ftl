<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="错误页面" jsFiles=[] cssFiles=["css/login.css"] localCssFiles=[] curTopMenu = "" curSubMenu = "" removeExtHeader = false removeExtFooter = false>
<div class="main-registion">
    <div class="error-wrap">
        <div class="activate-title">提示</div>
        <div class="error-box">
            <div class="error-box-row">
                <label class="error-box-img"></label><span class="error-box-info">:( ${errorMsg!}</span>
				<span style="display:none">
                    错误详情，请将此处信息复制给开发人员：
                    errorTime:${serverTime!};
                    rpid:${rpid!}
                </span>
                <div class="error-box-foot">如有疑问也可联系客服热线：021-53866655</div>
            </div>
        </div>
    </div>
</div>
</@hb.htmlBase>

