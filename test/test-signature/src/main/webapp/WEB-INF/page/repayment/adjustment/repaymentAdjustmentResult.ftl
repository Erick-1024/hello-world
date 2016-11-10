<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="调账结果" jsFiles=[] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "融资管理" curSubMenu = "融资信息管理" removeExtHeader = false removeExtFooter = false>

<div class="main-container">
    <section class="whiteBg">
        <div class="repaymentBack-wrap">
            <div class="finance-title">调账结果</div>
            <div class="finance-content">
                <div class="financeCnt-head">调账已成功修改！</div>
                <div class="financeCnt-foot">
                    <a class="default-link confirm-link" href="${basepath}/repayment/adjustment/gotoAdjustment?loanInfoId=${loanInfoId}">查看还款计划</a>
                </div>

            </div>
        </div>
    </section>
</div>
</@hb.htmlBase>
