<#import "/page/test/common.ftl" as common/>
<@common.htmlBase>
<table width="1000px">
	<tr>
		<td width="1000px">
			账单列表查询测试：<br>
			机构：<input class="institution" value="travelzen"/><p	>
			真旅采购商ID：<input class="customerId" /><p>
			放款日开始日期：<input class="loanDateStart" type="date"/><p>
			放款日结束日期：<input class="loanDateEnd" type="date"/><p>
			到期日开始日期：<input class="dueDateStart" type="date"/><p>
			到期日结束日期：<input class="dueDateEnd" type="date"/><p>
			结清状态:<input class="settleStatus" /> UNSETTLE,SETTLED<p>
			查询页码:<input class="page" value="1"/><p>
			每页个数:<input class="pageSize" value="10"/><p>
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
			var plain=institution+$('.customerId').val()+$('.loanDateStart').val()+$('.loanDateEnd').val()+$('.dueDateStart').val()+$('.dueDateEnd').val()+
						$('.settleStatus').val()+$('.page').val()+$('.pageSize').val();
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
		body.loanDateStart=$('.loanDateStart').val();
		body.loanDateEnd=$('.loanDateEnd').val();
		body.dueDateStart=$('.dueDateStart').val();
		body.dueDateEnd=$('.dueDateEnd').val();
		body.settleStatus=$('.settleStatus').val();
		body.page=$('.page').val();
		body.pageSize=$('.pageSize').val();
		body.sign=$('.sign').val();
		$('.reqContext').val(JSON.stringify(body));
		$.ajax({
			type: "POST",
			url: "${creditLoanList!}",
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
