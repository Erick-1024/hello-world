<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="放款信息录入结果" jsFiles=[] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "融资管理" curSubMenu = "放款信息录入" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <div class="repaymentBack-wrap">
            <div class="finance-title">放款信息录入结果</div>
            <div class="finance-content">
                <div class="financeCnt-head">成功导入放款信息${totalSuccess}笔，未成功${totalFail}笔！导入详情请查看<span class="inputFont"><a href="${basepath}/loanInfo/manage/gotoLoanInfoManage">融资信息</a></span>。</div>
                <div>如需自动还款，请继续增加还款计划。</div>
                
               
                <div class="financeCnt-foot">
	                <#if fromWhere=="excel">
	                	<a class="default-link confirm-link" href="${basepath}/loanInfo/input/gotoLoanInfoFromExcel?key=${key}">录入放款信息</a>
	                </#if>
                	<#if fromWhere=="manul">
                		<a class="default-link confirm-link" href="${basepath}/loanInfo/input/manual?key=${key}">录入放款信息</a>
                	</#if>
                    <a class="default-link confirm-link" href="${basepath}/repayment/plan/input/planSelect">增加还款计划</a>
                </div>

            </div>
        </div>
    </section>
</div>
</@hb.htmlBase>