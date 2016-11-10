<#assign AccountType=getEnumInfo('AccountType') />
<#assign TranState=getEnumInfo('TranState') />
<#assign TranType=getEnumInfo('TranType') />
<#assign BusinessProduct=getEnumInfo('BusinessProduct') />
<#assign LoanState=getEnumInfo('LoanState') />
<#assign Currency=getEnumInfo('Currency') />
<#assign RepaymentType=getEnumInfo('RepaymentType') />
<#assign SettleStatus=getEnumInfo('SettleStatus') />
<#assign CreditTradeStatus=getEnumInfo('CreditTradeStatus') />
<#assign CreditLimitStatus=getEnumInfo('CreditLimitStatus') />
<#assign ApplyApplicantType=getEnumInfo('ApplyApplicantType') />
<#assign CreditMode=getEnumInfo('CreditMode') />
<#assign EarlywarningEventCategory=getEnumInfo('EarlywarningEventCategory') />
<#assign YundaexEarlywarningEventCategory=getEnumInfo('YundaexEarlywarningEventCategory') />
<#assign YundaexEarlywarningEventSubCategory=getEnumInfo('YundaexEarlywarningEventSubCategory') />


<script type="text/javascript">
	<#if AccountType?has_content>
		var AccountType = {};
		<#list AccountType?keys as key>
			AccountType["${key}"] = "${AccountType[key]}";
		</#list>
	</#if>
</script>
<script type="text/javascript">
	<#if TranState?has_content>
		var TranState = {};
		<#list TranState?keys as key>
			TranState["${key}"] = "${TranState[key]}";
		</#list>
	</#if>
</script>
<script type="text/javascript">
	<#if TranType?has_content>
		var TranType = {};
		<#list TranType?keys as key>
			TranType["${key}"] = "${TranType[key]}";
		</#list>
	</#if>
</script>
<script type="text/javascript">
	<#if BusinessProduct?has_content>
		var BusinessProduct = {};
		<#list BusinessProduct?keys as key>
			BusinessProduct["${key}"] = "${BusinessProduct[key]}";
		</#list>
	</#if>
</script>
<script type="text/javascript">
	<#if LoanState?has_content>
		var LoanState = {};
		<#list LoanState?keys as key>
			LoanState["${key}"] = "${LoanState[key]}";
		</#list>
	</#if>
</script>
<script type="text/javascript">
	<#if Currency?has_content>
		var Currency = {};
		<#list Currency?keys as key>
			Currency["${key}"] = "${Currency[key]}";
		</#list>
	</#if>
</script>
<script type="text/javascript">
	<#if RepaymentType?has_content>
		var RepaymentType = {};
		<#list RepaymentType?keys as key>
			RepaymentType["${key}"] = "${RepaymentType[key]}";
		</#list>
	</#if>
</script>
<script type="text/javascript">
	<#if SettleStatus?has_content>
		var SettleStatus = {};
		<#list SettleStatus?keys as key>
			SettleStatus["${key}"] = "${SettleStatus[key]}";
		</#list>
	</#if>
</script>
<script type="text/javascript">
	<#if CreditTradeStatus?has_content>
		var CreditTradeStatus = {};
		<#list CreditTradeStatus?keys as key>
			CreditTradeStatus["${key}"] = "${CreditTradeStatus[key]}";
		</#list>
	</#if>
</script>
<script type="text/javascript">
	<#if CreditLimitStatus?has_content>
		var CreditLimitStatus = {};
		<#list CreditLimitStatus?keys as key>
			CreditLimitStatus["${key}"] = "${CreditLimitStatus[key]}";
		</#list>
	</#if>
</script>
<script type="text/javascript">
	<#if ApplyApplicantType?has_content>
		var ApplyApplicantType = {};
		<#list ApplyApplicantType?keys as key>
			ApplyApplicantType["${key}"] = "${ApplyApplicantType[key]}";
		</#list>
	</#if>
</script>
<script type="text/javascript">
	<#if CreditMode?has_content>
		var CreditMode = {};
		<#list CreditMode?keys as key>
			CreditMode["${key}"] = "${CreditMode[key]}";
		</#list>
	</#if>
</script>
<script type="text/javascript">
	<#if EarlywarningEventCategory?has_content>
		var EarlywarningEventCategory = {};
		<#list EarlywarningEventCategory?keys as key>
			EarlywarningEventCategory["${key}"] = "${EarlywarningEventCategory[key]}";
		</#list>
	</#if>
</script>
<script type="text/javascript">
	<#if YundaexEarlywarningEventCategory?has_content>
		var YundaexEarlywarningEventCategory = {};
		<#list YundaexEarlywarningEventCategory?keys as key>
			YundaexEarlywarningEventCategory["${key}"] = "${YundaexEarlywarningEventCategory[key]}";
		</#list>
	</#if>
</script>
<script type="text/javascript">
	<#if YundaexEarlywarningEventSubCategory?has_content>
		var YundaexEarlywarningEventSubCategory = {};
		<#list YundaexEarlywarningEventSubCategory?keys as key>
			YundaexEarlywarningEventSubCategory["${key}"] = "${YundaexEarlywarningEventSubCategory[key]}";
		</#list>
	</#if>
</script>

