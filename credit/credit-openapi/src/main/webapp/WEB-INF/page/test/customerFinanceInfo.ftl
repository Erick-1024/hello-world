<#import "/page/test/common.ftl" as common/>
<@common.htmlBase>
<table width="1000px">
	<tr>
		<td width="1000px">
			授信余额查询测试：<br>
			机构：<input class="institution" value="travelzen"/><p	>
			真旅采购商ID：<input class="customerId"/><p>
			<button class="genSign" />生成签名</button> ： <textarea type="text" class="sign"></textarea><p>
			<input class="btn_submit" type="button" value="测试"/><p>
			请求报文:<textarea type="textarea" class="reqContext"/></textarea><p>
			响应报文:<textarea type="textarea" class="resContext"/></textarea><p>
		</td>
	</tr>
</table>

<script>
	$(function(){
		$('.genSign').click(function(){
			var institution=$('.institution').val();
			var plain=institution+$('.customerId').val();
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
		body.customerId=$('.customerId').val();
		body.sign=$('.sign').val();
		$('.reqContext').val(JSON.stringify(body));
		$.ajax({
			type: "POST",
			url: "${customerFinanceInfoUrl!}",
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
</@common.htmlBase>