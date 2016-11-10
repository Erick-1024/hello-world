<#import "/page/test/common.ftl" as common/>
<@common.htmlBase>
<table width="1000px">
	<tr>
		<td width="1000px">
			授信余额查询测试：<br>
			
			站点编号：<input class="stationNo"/><p>
			
			<input class="btn_submit" type="button" value="测试"/><p>
			请求报文:<textarea type="textarea" class="reqContext"/></textarea><p>
			响应报文:<textarea type="textarea" class="resContext"/></textarea><p>
		</td>
	</tr>
</table>
</@common.htmlBase>
<script>
	$(function(){
	});
	
			$('.btn_submit').click(function(){
		var body={};
		body.stationNo=$('.stationNo').val();
		body.sign=$('.sign').val();
		$('.reqContext').val(JSON.stringify(body));
		$.ajax({
			type: "POST",
			url: "${limitBalance!}",
			data: JSON.stringify(body),
			headers: {
			    "content-type": "application/json",
			},
			success: function(data){
				$(".resContext").val(JSON.stringify(data));
			}
		});
	});
</script>