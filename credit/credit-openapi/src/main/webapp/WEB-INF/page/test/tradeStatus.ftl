<#import "/page/test/common.ftl" as common/>
<@common.htmlBase>
<table width="1000px">
	<tr>
		<td width="1000px">
			交易状态查询测试：<br>
			机构：<input class="institution" value="travelzen"/><p	>
			唯一交易编号：<input class="tradeNo" /><p>
			交易类型：<input class="tradeType"/><p>
			<button class="genSign" />生成签名</button>
			： <textarea type="text" class="sign"></textarea><p>
			<input class="btn_submit" type="button" value="测试"/><p>
			请求报文:<textarea type="textarea" class="reqContext"/></textarea><p>
			响应报文:<textarea type="textarea" class="resContext"/></textarea><p>
		</td>
	</tr>
</table>
</@common.htmlBase>
<script>
	$(function(){
		$('.genSign').click(function(){
			var institution=$('.institution').val();
			var plain=institution+$('.tradeNo').val()+$('.tradeType').val();
			$.ajax({
			type: "POST",
			url: "${genSignUrl!}",
			data: {
				"institution" : institution,
				"plain": plain
			},
			success: function(data){
				$('.sign').text(data);
			}
		});
	});
	
	$('.btn_submit').click(function(){
		var body={};
		body.institution=$('.institution').val();
		body.tradeNo=$('.tradeNo').val();
		body.tradeType=$('.tradeType').val();
		body.sign=$('.sign').val();
		$('.reqContext').val(JSON.stringify(body));
		$.ajax({
			type: "POST",
			url: "${tradeStatus!}",
			data: JSON.stringify(body),
			headers: {
			    "content-type": "application/json",
			},
			success: function(data){
				$(".resContext").val(JSON.stringify(data));
			}
		});
	});
	})
</script>