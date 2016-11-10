<table>
	<tr>
		<td>企业名称</td>
		<td>用户名</td>
		<td>营业执照号</td>
		<td>组织机构代码</td>
		<td>联系人</td>
		<td>联系电话</td>
		<td>邮箱</td>
		<td>客户类型</td>
		<td>提交时间</td>
		<td>账号状态</td>
		<td>审批状态</td>
		<td>操作</td>
	</tr>
	<#list customerSearchResultDTOs as customer>
		<tr>
			<td>${customer.companyName!}</td>
			<td>${customer.username!}</td>
			<td>${customer.businessLicenceCode!}</td>
			<td>${customer.organizationCode!}</td>
			<td>${customer.contactName!}</td>
			<td>${customer.contactTel!}</td>
			<td>${customer.contactMail!}</td>
			<td>${(customer.userType.desc())!}</td>
			<td>${(customer.createTime?datetime)!}</td>
			<td>${(customer.accountActivateStatus.desc())!}</td>
			<td>${(customer.accountProcessStatus.desc())!}</td>
			<td><a href="customerDetail?customerId=${customer.id}">详情</a></td>
			<#if customer.accountProcessStatus.desc() = "未处理">
				<td><a href="gotoReview?customerId=${customer.id}">审核</a></td>
			<#elseif customer.accountProcessStatus.desc() = "审批通过">
				<#if customer.accountActivateStatus.desc() = "未激活">
					<td><a href="../resend?uesrId${customer.id}">重发邮件</a><td>
				</#if>
			</#if>
			
		</tr>
	</#list>
</table>