<#macro tradeRecordAndLoan showTradeRecord=true showLoan=false>
<div class="accountList-content">
	<div class="bankAcuDetail-nav">
    	<ul id="tradeRecordAndLoan">
            <#if showLoan>
	            <li class="bankAcuNav-active">
	                <a href="javascript:searchLoan();" class="bankAcuNav-navlink">放款信息</a>
	            </li>
            </#if>
			<#if showLoan>
            	<li>
			<#else>
				<li class="bankAcuNav-active">
			</#if>
                <a href="javascript:searchTradeRecord();" class="bankAcuNav-navlink">账户收支明细</a>
            </li>
        </ul>
    </div>
    <div class="accountDetail-wrap">
        <div id="accountDetailGrid" class="accountDetail k-grid k-widget" data-role="grid" style=""></div>
        <div id="loanMatterGrid" class="accountDetail k-grid k-widget" data-role="grid" style=""></div>
    </div>
</div>
<script src="${basepath}/resources/javascripts/page/account/trade/accountTradeRecordAndLoad.js"></script>

</#macro>