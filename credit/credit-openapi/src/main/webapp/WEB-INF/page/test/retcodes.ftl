<#import "/page/test/common.ftl" as common/>

<@common.htmlBase>

<div>

</div>
<table border="1px">
	<tr>
		<th width="100px">返回码</th>
		<th>描述</th>
	</tr>
	<#list retcodes as retcode>
		<tr>
			<td>${(retcode.retCode)!}</td>
			<td>
				<#if retcode.retCode == "SUCCESS">
					成功
				<#elseif retcode.retCode == "ERROR" >
					未知错误
				<#else>
				${(retcode.retMsg)!}
				</#if>
			</td>
		</tr>
	</#list>
</table>

</@common.htmlBase>