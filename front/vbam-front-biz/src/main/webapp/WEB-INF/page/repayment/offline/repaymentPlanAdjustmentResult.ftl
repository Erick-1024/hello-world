<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="线下还款结果" jsFiles=[""] cssFiles=["css/finance.css"] localCssFiles=[] curTopMenu = "融资管理" curSubMenu = "融资信息管理" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <div class="repaymentBack-wrap">
            <div class="finance-title">线下还款结果</div>
            <div class="finance-content">
                <div class="financeCnt-head">线下还款记录已成功修改！还款计划已成功修改！</div>
                <div class="financeCnt-foot">
                    <a class="default-link confirm-link" href="${basepath}/loanInfo/manage/gotoRepaymentPlanDetails?loanId=${loanInfoId}">查看还款计划</a>
                </div>

            </div>
        </div>
    </section>
</div>
</@hb.htmlBase>
