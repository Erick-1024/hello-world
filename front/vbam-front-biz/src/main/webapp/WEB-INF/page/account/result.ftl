<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="${action!'代开户申请'}" jsFiles=[] cssFiles=["css/common.css", "css/account.css"] localCssFiles=[] 
	curTopMenu = "账户管理" curSubMenu = "${action!'代开户申请'}" removeExtHeader = false removeExtFooter = false>

<div class="main-container">
    <section class="whiteBg">
        <div class="applyAutSuccess-wrap">
            <div class="applyAutSuccess-title">${action!"代申请账户"}</div>
            <div class="applyAutSu-content">
                <div class="applyAutSu-head">${message!}</div>
                <#if accounts ?? >
                <div class="commonTips-table">
                	<table>
                    	<colgroup>
                        	<col width="150px">
                        	<col width="150px">
                        	<col width="150px">
                    	</colgroup>
                    	<thead>
                    	<tr style="text-align:center;">
                        	<td>银行账号</td>
                        	<td>银行账户名称</td>
                        	<td>账户性质</td>
                    	</tr>
                    	</thead>
                    	<tbody>
                    	<#list accounts as AccountDTO >
                    	<tr>
                        	<td>${formatBankAccountNo((AccountDTO.accountNo)!)}</td>
                        	<td>${(AccountDTO.accountName)!}</td>
                        	<td>${(AccountDTO.accountType.desc())!}</td>
                    	</tr>
                    	</#list>
                    </tbody>
                	</table>
            	</div>
            	</#if>
                <div>如有疑问请与CANA管理员联系：021-53866655。</div>
                <div class="applyAutSu-foot">
                    <a class="applyAutSu-link" href="${nextUrl!"agent"}">${next!"继续代开户"}</a>
                </div>

            </div>
        </div>
    </section>
</div>
	
</@hb.htmlBase>