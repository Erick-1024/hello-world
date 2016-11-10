<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="主动还款结果" jsFiles=[] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "融资管理" curSubMenu = "融资信息管理"  removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <div class="repaymentBack-wrap">
            <div class="finance-title">主动还款结果</div>
            <div class="finance-content">
                <div class="financeCnt-head">还款成功<span class="inputFont">等待资金方修改还款计划，请及时与资金方联系，以免扣款异常</span></div>
               
                <div class="financeCnt-foot">
                    <a class="default-link confirm-link" href="${basepath}/loanInfo/manage/gotoRepaymentPlanDetails?loanId=${loanInfoId}">还款计划详情</a>
                </div>

            </div>
        </div>
    </section>
</div>
</@hb.htmlBase>