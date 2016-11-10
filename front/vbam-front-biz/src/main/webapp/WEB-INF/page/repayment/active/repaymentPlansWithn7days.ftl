<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="7日应还" jsFiles=["page/repayment/active/repaymentPlansWithn7days.js"] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "融资管理" curSubMenu = "7日应还" removeExtHeader = false removeExtFooter = false>
<div class="main-container" data-userType=${userType}>
     <div class="whiteBg">
        <div class="repayment-nav">
            <ul>
                <li class="repayment-nav-active">
                    <a class="repayment-navlink" href="javascript:void(0);">还款计划</a>
                </li>
                <li>
                    <a class="repayment-navlink" href="javascript:void(0);">费用列表</a>
                </li>
            </ul>
        </div>
        <div class="repayment-grid" id="repayment-plan">
            <div class="finance-grid" id="manualGrid-plan" style="margin-top:15px;"></div>
        </div>
        <div class="repayment-grid hidden" id="repayment-cost">
            <div class="finance-grid" id="manualGrid-cost" style="margin-top:15px;"></div>
        </div>
    </div>
</div>
<!--权限配置-->
<script>
	var activeRepaymentAuth = ${authorizeKey("FM_SEVEN_ACTIVE_REPAYMENT")?string("true","false")};
</script>
</@hb.htmlBase>