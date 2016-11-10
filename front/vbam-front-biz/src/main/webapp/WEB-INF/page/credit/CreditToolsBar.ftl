<#macro toolsBar threeLevelMenu="" >
	<div class="thirdNavGroup">
	    <ul>
	    	<#if authorizeKey("TZ_LOAN_DETAILS")>
		        <li <#if threeLevelMenu=="loanDetails">class="thirdNavCut"</#if> >
		            <a href="${basepath}/credit/trade/loan/details" class="thirdNavItem">放款明细</a>
		        </li>
		    </#if>
		    <#if authorizeKey("TZ_REFUND_DETAILS")>
		        <li <#if threeLevelMenu=="refundDetails">class="thirdNavCut"</#if> >
		            <a href="${basepath}/credit/trade/refund/details" class="thirdNavItem">退款明细</a>
		        </li>
	        </#if>
	        <#if authorizeKey("TZ_LOAN_SERIALS")>
		        <li <#if threeLevelMenu=="transferPayment">class="thirdNavCut"</#if> >
		            <a href="${basepath}/credit/transfer/payment" class="thirdNavItem">放款流水</a>
		        </li>
	        </#if>
	        <#if authorizeKey("TZ_REFUND_SERIALS")>
		        <li <#if threeLevelMenu=="transferRefund">class="thirdNavCut"</#if> >
		            <a href="${basepath}/credit/transfer/refund" class="thirdNavItem">退款流水</a>
		        </li>
	        </#if>
	        <#if authorizeKey("TZ_STATEMENT")>
		        <li <#if threeLevelMenu=="statement">class="thirdNavCut"</#if> >
		            <a href="${basepath}/credit/statement" class="thirdNavItem">对账单下载</a>
		        </li>
	        </#if>
	    </ul>
	</div>
	
</#macro>
