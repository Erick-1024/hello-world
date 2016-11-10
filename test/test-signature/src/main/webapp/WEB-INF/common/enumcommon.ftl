<#assign SingleProductStatus=getEnumInfo('AccountType') />

<script type="text/javascript">
	<#if AccountType?has_content>
		var AccountType = {};
		<#list AccountType?keys as key>
			AccountType["${key}"] = "${AccountType[key]}";
		</#list>
	</#if>
</script>
