<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="还款计划录入结果" jsFiles=[""] cssFiles=["css/finance.css"] localCssFiles=[] curTopMenu = "融资管理" curSubMenu = "还款计划录入" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <div class="repaymentBack-wrap">
            <div class="finance-title">还款计划录入结果</div>
            <div class="finance-content">
                <div class="financeCnt-head">成功导入还款计划${correctRepaymentPlanNum!'0'}笔，未成功${incorrectRepaymentPlanNum!'0'}笔！</div>
                <div class="financeCnt-head">成功导入还款费用${correctRepaymentExpenseNum!'0'}笔，未成功${incorrectRepaymentExpenseNum!'0'}笔！</div>
                <div>导入详情请查看<a class="repayment-link" href="${basepath}/loanInfo/manage/gotoLoanInfoManage">融资信息</a>。</div>
                <div class="financeCnt-foot">
                    <a class="default-link confirm-link" href="${basepath}/loanInfo/manage/gotoLoanInfoManage">查看融资信息</a>
                    <a class="default-link back-link" href="${basepath}/repayment/plan/input/planSelect">取消</a>
                </div>
            </div>
        </div>
    </section>
</div>
</@hb.htmlBase>