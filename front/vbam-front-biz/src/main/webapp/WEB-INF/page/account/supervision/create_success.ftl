<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="新建监管关系" jsFiles=[] cssFiles=["css/account.css"] localCssFiles=[] 
	curTopMenu = "账户管理" curSubMenu = "账户列表">

<div class="main-container">
    <section class="whiteBg">
        <div class="applyAutSuccess-wrap">
            <div class="applyAutSuccess-title">新建监管关系</div>
            <div class="applyAutSu-content">
                <div> &nbsp;</div>
                <div class="applyAutSu-head">您的新建账户监管关系申请已提交，请等待对方审核！</div>
                <div class="applyAutSu-foot">
                    <a class="applyAutSu-link" href="${basepath!}/account/list">返 回</a>
                </div>

            </div>
        </div>
    </section>
</div>

</@hb.htmlBase>