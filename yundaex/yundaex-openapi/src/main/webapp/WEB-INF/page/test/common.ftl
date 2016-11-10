<#global basepath >${req.contextPath}</#global>

<#global ydCustomerApplyUrl= basepath + "/cana/ydCustomerApply"/>
<#global ydGetSignUrl= basepath + "/test/ydGetSign"/>
<#global limitBalance= basepath + "/cana/customerLimitQuery"/>

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
	<div>韵达项目授信系统测试页面
		<a href="/yundaex-openapi/test/customerApply">客户申请</a>
		<a href="/yundaex-openapi/test/limit/balance">授信余额查询</a>
		<!--
		<a href="/yundaex-openapi/test/trade">支付退款</a>
		<a href="/yundaex-openapi/test/trade/status">交易状态查询</a>
		<a href="/yundaex-openapi/test/retcodes">返回码列表</a>
		<a href="/yundaex-openapi/test/limit/balance">授信余额查询</a>
		<a href="/yundaex-openapi/test/trade/loan/detail">账单明细详情</a>
		<a href="/yundaex-openapi/test/trade/loan/list">账单列表</a>
		<a href="/yundaex-openapi/test/getCustomerFinanceInfo">融资查询</a>
		<a href="/yundaex-openapi/test/marketing/currentActivity">当前活动</a>
		<a href="/yundaex-openapi/test/marketing/prepayProduct">促销活动信息</a>
		-->
	</div>
	<br/>
	<#nested/>
</body>
</html>
</#macro>
