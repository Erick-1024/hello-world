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
	<div>凯拿系统测试中心
		<a href="datetime">系统虚拟时间设置</a>
		<a href="balance?accountNo=">账户余额设置</a>
		<a href="/credit-openapi/test/trade">真旅项目测试页面</a>
		<a href="/vj-openapi/test/pay">VJ项目测试页面</a>
		<a href="withdrawState">提现状态设置页面</a>
		<a href="virtualBalanceForAllAccount">给所有账户设置随机虚拟余额</a>
	</div>
	<br/>
	<#nested/>
</body>
</html>
</#macro>
