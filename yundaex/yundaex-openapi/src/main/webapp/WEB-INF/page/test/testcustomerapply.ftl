<#import "/page/test/common.ftl" as common/>

<@common.htmlBase>

<table width="1200px">
<tr>
	<td width="1200px">
		<div id='applyDiv'>
			韵达客户申请测试：<br/>
			站点编号：<input type="text" name="stationNo" value="" /><br/>
			站点名称：<input type="text" name="stationName" value="" /><br/>
			站点负责人/公司名称：<input type="text" name="stationMgr" value="" /><br/>
			借款人姓名：<input type="text" name="custName" value="" /><br/>
			借款人身份证号：<input type="text" name="custIdno" value="" /><span>保证格式正确</span> <br/>
			借款人手机号：<input type="text" name="telephone" value="" /><span>保证格式正确</span><br/>
			站点经营-省份：<input type="text" name="province" value="310000" /><span>西藏/新疆系统审核不通过</span><br/>
			站点经营-城市：<input type="text" name="city" value="310100" /><span>上海市</span><br/>
			站点经营详细地址：<input type="text" name="address" value="" /><br/>
			加盟年限：<input type="text" name="busiLimit" value="" />两年以上(>=2) Long类型<br/>
			区域代码：<input type="text" name="regioncode" value="" /><br/>
			意向额度：<input type="text" name="applyCreditLimit" value="" />单位是分<br/>
			意向期限：<input type="text" name="loanLimit" value="" /><br/>
			增信方式：<input type="text" name="addCredit" value="" /><br/>
			还款方式：<input type="text" name="repaymentType" value="" /><br/>
			其他说明：<input type="text" name="explain" value="" /><br/>
			签名：<input type="text" name="sign" value=""/> <button id="sign" value="">生成签名</button>
			
		</div>
			<button id="applyButton">提交</button><br/><br/>
			请求报文：<textarea name="requestBody"></textarea> <br/>
			响应报文：<textarea name="responseBody"></textarea> <br/>
	</td>
</tr>
</table>
<script>
$(function() {
    $("#sign").click(function(){
    var body = {};
		$("#applyDiv input").each(function(i, e){
			body[$(e).attr('name')] = $(e).val();
		});
      $.ajax({
         type: "POST",
          url: "${ydGetSignUrl}",
         data: JSON.stringify(body),
         headers: {
			    "content-type": "application/json",
		 },
		 success: function(data){
		         alert(data.data);
				$("input[name=sign]").val(data.data);
			},
			error: function(data){
			    alert("error");
			}	
      });
    });


	$("#applyButton").click(function(){
		var body = {};
		$("#applyDiv input").each(function(i, e){
			body[$(e).attr('name')] = $(e).val();
		});
		$("textarea[name=requestBody]").text(JSON.stringify(body));
		$.ajax({
			type: "POST",
			url: "${ydCustomerApplyUrl}",
			data: JSON.stringify(body),
			headers: {
			    "content-type": "application/json",
			},
			success: function(data){
				$("textarea[name=responseBody]").text(JSON.stringify(data));
			},
			error: function(data){
			    alert("error");
			}
		});
	});
	
});
</script>


</@common.htmlBase>