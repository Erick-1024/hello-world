<#global basepath = "/credit-openapi"/>
<#global genSignUrl= basepath + "/test/trade/generateSign"/>
<#global creditPayUrl= basepath + "/credit/trade/pay"/>
<#global creditRefundUrl= basepath + "/credit/trade/refund"/>
<#global creditAgentRepaymentUrl= basepath + "/credit/trade/tzAccountRepayment"/>
<#global tzCustomerApplyUrl= basepath + "/tzCustomerApply"/>
<#global testAsyncNotifyTradeStatusUrl = basepath +  "/test/asyncnotify/tradeStatus"/>
<#global tradeStatus= basepath + "/credit/trade/queryStatus"/>
<#global limitBalance= basepath + "/credit/limit/balance"/>
<#global creditLoanDetail=basepath+"/credit/loan/detail"/>
<#global creditLoanList=basepath+"/credit/loan/list"/>
<#global customerFinanceInfoUrl = basepath+"/credit/getCustomerFinanceInfo"/>
<#global currentActivityUrl = basepath + "/credit/marketing/currentActivity" />
<#global prepayProductUrl = basepath + "/credit/marketing/prepayProduct" />

<#macro htmlBase>
<!DOCTYPE html>
<html>
<head>
<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>

<style type="text/css">
	textarea {width: 400px; margin: 0px; height: 120px;}
	input {width: 200px;}
	button {padding: 5px}
	a {margin-left: 20px}
</style>
</head>
<body>
	<div>凯拿授信系统测试页面
		<a href="/credit-openapi/test/customerApply">客户申请</a>
		<a href="/credit-openapi/test/trade">支付退款/账户还款</a>
		<a href="/credit-openapi/test/trade/status">交易状态查询</a>
		<a href="/credit-openapi/test/retcodes">返回码列表</a>
		<a href="/credit-openapi/test/limit/balance">授信余额查询</a>
		<a href="/credit-openapi/test/trade/loan/detail">账单明细详情</a>
		<a href="/credit-openapi/test/trade/loan/list">账单列表</a>
		<a href="/credit-openapi/test/getCustomerFinanceInfo">融资查询</a>
		<a href="/credit-openapi/test/marketing/currentActivity">当前活动</a>
		<a href="/credit-openapi/test/marketing/prepayProduct">促销活动信息</a>
	</div>
	<br/>
	<#nested/>
</body>
</html>
</#macro>
