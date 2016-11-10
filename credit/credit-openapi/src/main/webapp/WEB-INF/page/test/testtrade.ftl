<#import "/page/test/common.ftl" as common/>

<@common.htmlBase>

<table width="1800px">
<tr>
	<td width="600px">
		<div id='payDiv'>
			支付测试：<br/>
			机构：<input type="text" name="institution" value="travelzen" /><br/>
			外部客户ID：<input type="text" name="customerId" value="" /><br/>
			外部交易ID：<input type="text" name="tradeNo" value="${tradeNo!}" /><br/>
			支付金额：<input type="text" name="paymentFee" value="" /><br/>
			促销活动ID：<input type="text" name="activityId" value="" /><br/>
			<button class="genSign" typeDiv="payDiv">生成签名</button>：<textarea name="sign" ></textarea><br/>
			回调地址：<input type="text" name="notifyURL" value="${serverUrlPrefix}${testAsyncNotifyTradeStatusUrl}" /><br/>
			交易时间：<input type="text" name="tradeTime" value="${tradeTime!}" /><br/>
			客户名称：<input type="text" name="customerName" value="真旅采购商客户" /><br/>
			订单信息：<input type="text" name="orderInfo" value="机票" /><br/>
			<button id="payButton">支付</button><br/><br/>
			请求报文：<textarea name="requestBody"></textarea> <br/>
			响应报文：<textarea name="responseBody"></textarea> <br/>
		</div>
	</td>
	<td width="600px">
		<div id='refundDiv'>
			退款测试：<br/>
			机构：<input type="text" name="institution" value="travelzen" /><br/>
			外部客户ID：<input type="text" name="customerId" value="" /><br/>
			外部交易ID：<input type="text" name="tradeNo" value="${refundTradeNo!}" /><br/>
			外部原交易ID：<input type="text" name="originTradeNo" value="${tradeNo!}" /><br/>
			退款金额：<input type="text" name="refundFee" value="" /><br/>
			<button class="genSign" typeDiv="refundDiv">生成签名</button>：<textarea name="sign" ></textarea><br/>
			回调地址：<input type="text" name="notifyURL" value="${serverUrlPrefix}${testAsyncNotifyTradeStatusUrl}" /><br/>
			交易时间：<input type="text" name="tradeTime" value="${tradeTime!}" /><br/>
			<button id="refundButton">退款</button><br/><br/>
			请求报文：<textarea name="requestBody" ></textarea> <br/>
			响应报文：<textarea name="responseBody" ></textarea> <br/>
		</div>
	</td>
	<td width="600px">
		<div id='agentRepaymentDiv'>
			账户还款测试：<br/>
			机构：<input type="text" name="institution" value="travelzen" /><br/>
			外部客户ID：<input type="text" name="customerId" value="" /><br/>
			外部交易ID：<input type="text" name="tradeNo" value="${agentRepaymentTradeNo!}" /><br/>
			放款批次号（loanInfoId）：<input type="text" name="loanInfoId" value="" /><br/>
			账户还款金额：<input type="text" name="fee" value="" /><br/>
			<button class="genSign" typeDiv="agentRepaymentDiv">生成签名</button>：<textarea name="sign" ></textarea><br/>
			回调地址：<input type="text" name="notifyURL" value="${serverUrlPrefix}${testAsyncNotifyTradeStatusUrl}" /><br/>
			交易时间：<input type="text" name="tradeTime" value="${tradeTime!}" /><br/>
			<button id="agentRepaymentButton">账户还款</button><br/><br/>
			请求报文：<textarea name="requestBody" ></textarea> <br/>
			响应报文：<textarea name="responseBody" ></textarea> <br/>
		</div>
	</td>
</tr>
</table>
<script>
$(function() {
	$("#payButton").click(function(){
		var payBody = {};
		payBody.institution = $("#payDiv input[name=institution]").val();
		payBody.customerId = $("#payDiv input[name=customerId]").val();
		payBody.tradeNo = $("#payDiv input[name=tradeNo]").val();
		payBody.paymentFee = $("#payDiv input[name=paymentFee]").val();
		payBody.activityId = $("#payDiv input[name=activityId]").val();
		payBody.notifyURL = $("#payDiv input[name=notifyURL]").val();
		payBody.sign = $("#payDiv textarea[name=sign]").val();
		payBody.tradeTime = $("#payDiv input[name=tradeTime]").val();
		payBody.customerName = $("#payDiv input[name=customerName]").val();
		payBody.orderInfo = $("#payDiv input[name=orderInfo]").val();
		$("#payDiv textarea[name=requestBody]").text(JSON.stringify(payBody));
		$.ajax({
			type: "POST",
			url: "${creditPayUrl!}",
			data: JSON.stringify(payBody),
			headers: {
			    "content-type": "application/json",
			},
			success: function(data){
				$("#payDiv textarea[name=responseBody]").text(JSON.stringify(data));
			}
		});
	});
	
	$("#refundButton").click(function(){
		var body = {};
		body.institution = $("#refundDiv input[name=institution]").val();
		body.customerId = $("#refundDiv input[name=customerId]").val();
		body.tradeNo = $("#refundDiv input[name=tradeNo]").val();
		body.originTradeNo = $("#refundDiv input[name=originTradeNo]").val();
		body.refundFee = $("#refundDiv input[name=refundFee]").val();
		body.notifyURL = $("#refundDiv input[name=notifyURL]").val();
		body.sign = $("#refundDiv textarea[name=sign]").val();
		body.tradeTime = $("#refundDiv input[name=tradeTime]").val();
		body.customerName = $("#refundDiv input[name=customerName]").val();
		body.orderInfo = $("#refundDiv input[name=orderInfo]").val();
		$("#refundDiv textarea[name=requestBody]").text(JSON.stringify(body));
		$.ajax({
			type: "POST",
			url: "${creditRefundUrl!}",
			data: JSON.stringify(body),
			headers: {
			    "content-type": "application/json",
			},
			success: function(data){
				$("#refundDiv textarea[name=responseBody]").text(JSON.stringify(data));
			}
		});
	});
	
	$("#agentRepaymentButton").click(function(){
		var body = {};
		body.institution = $("#agentRepaymentDiv input[name=institution]").val();
		body.customerId = $("#agentRepaymentDiv input[name=customerId]").val();
		body.tradeNo = $("#agentRepaymentDiv input[name=tradeNo]").val();
		body.loanInfoId = $("#agentRepaymentDiv input[name=loanInfoId]").val();
		body.fee = $("#agentRepaymentDiv input[name=fee]").val();
		body.notifyURL = $("#agentRepaymentDiv input[name=notifyURL]").val();
		body.sign = $("#agentRepaymentDiv textarea[name=sign]").val();
		body.tradeTime = $("#agentRepaymentDiv input[name=tradeTime]").val();
		$("#agentRepaymentDiv textarea[name=requestBody]").text(JSON.stringify(body));
		$.ajax({
			type: "POST",
			url: "${creditAgentRepaymentUrl!}",
			data: JSON.stringify(body),
			headers: {
			    "content-type": "application/json",
			},
			success: function(data){
				$("#agentRepaymentDiv textarea[name=responseBody]").text(JSON.stringify(data));
			}
		});
	});
	
	$(".genSign").click(function(){
		var typeDiv = '#' + $(this).attr('typeDiv');
		var plain = $(typeDiv + ' input[name=institution]').val();
		plain += $(typeDiv + ' input[name=customerId]').val();
		plain += $(typeDiv + ' input[name=tradeNo]').val();
		if (typeDiv == '#refundDiv')
			plain += $(typeDiv + ' input[name=originTradeNo]').val();
		if (typeDiv == '#payDiv')
			plain += $(typeDiv + ' input[name=paymentFee]').val();
		if (typeDiv == '#payDiv')
			plain += $(typeDiv + ' input[name=activityId]').val();
		if (typeDiv == '#refundDiv')
			plain += $(typeDiv + ' input[name=refundFee]').val();
		if(typeDiv == '#agentRepaymentDiv')
			plain += $(typeDiv + ' input[name=loanInfoId]').val() + $(typeDiv + ' input[name=fee]').val();
		
		$.ajax({
			type: "POST",
			url: "${genSignUrl!}",
			data: {
				"institution" : $(typeDiv + ' input[name=institution]').val(),
				"plain": plain
			},
			success: function(data){
				$(typeDiv + " textarea[name=sign]").text(data);
			}
		});
	});
	
});
</script>

</@common.htmlBase>