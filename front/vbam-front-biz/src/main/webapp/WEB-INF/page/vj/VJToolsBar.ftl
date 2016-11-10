<#macro toolsBar threeLevelMenu="" >
	<div class="thirdNavGroup">
	    <ul>
	    	<#if authorizeKey("VJ_TRAN_LOAN_DETAILLIST")>
		        <li <#if threeLevelMenu=="loanDetailList">class="thirdNavCut"</#if> >
		            <a href="${basepath}/vj/tran/loan/detailList" class="thirdNavItem">放款明细</a>
		        </li>
		    </#if>
		    <#if authorizeKey("VJ_TRAN_REPAYMENT_DETAILLIST")>
		        <li <#if threeLevelMenu=="repaymentDetailList">class="thirdNavCut"</#if> >
		            <a href="${basepath}/vj/tran/repayment/detailList" class="thirdNavItem">主动还款明细</a>
		        </li>
	        </#if>
	        <#if authorizeKey("VJ_TRAN_ACCOUNT_BALANCE")>
		        <li <#if threeLevelMenu=="accountBalance">class="thirdNavCut"</#if> >
		            <a href="${basepath}/vj/tran/account/balance" class="thirdNavItem">对账单</a>
		        </li>
	        </#if>
	    </ul>
	</div>
	
</#macro>
