<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="项目详情" jsFiles=["page/asset/project/projectDetail.js","page/asset/project/project.js","common/dateutil.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
curTopMenu = "项目管理" curSubMenu = "所有列表" removeExtHeader = false removeExtFooter = false>
<div class="main-container" style="background:none;">
<div class="pro-bg">
    <div class="pro-title">
    	<span class="pro-title-left">项目要素</span>
    	<input id ="projectId" type="hidden" value="${projectDTO.id!}">
    </div>
    <table class="query-table">
        <colgroup>
            <col width="60">
            <col width="100">
            <col width="60">
            <col width="100">
            <col width="60">
            <col width="150">
        </colgroup>
        <tbody>
        <tr>
            <th>项目名称</th>
            <td>${projectDTO.name!}</td>
            <th>业务类型</th>
            <td>${(projectDTO.type.desc())!}</td>
            <th>项目状态</th>
            <td>${projectDTO.status.desc()!}</td>
        </tr>
        </tbody>
    </table>
    <div class="pro-title"><span class="pro-title-left">核心企业信息</span><span class="pro-title-right">折叠</span></div>
    <div class="hide-box" >
    <table class="query-table">
        <colgroup>
            <col width="60">
            <col width="100">
            <col width="60">
            <col width="310">
        </colgroup>
        <tbody>
        <tr>
            <th>核心企业名称</th>
            <td>${projectDTO.coreCompanyName!}</td>
            <th>银行账号</th>
            <td>${projectDTO.coreAccountNo!}</td>
        </tr>
        <tr>
            <th>组织机构代码</th>
            <td>${projectDTO.coreOrganizationCode!}</td>
            <th>所处行业</th>
            <td>${projectDTO.coreIndustry.desc()!}</td>
        </tr>
        <tr>
            <th>营业执照代码</th>
            <td>${projectDTO.coreBusinessLicenceCode!}</td>
            <th>经济类型</th>
            <td>${projectDTO.coreEconomicCategory.desc()!}</td>
        </tr>
        <tr>
            <th>税务登记证号码</th>
            <td>${projectDTO.coreTaxRegistrationCertificateCode!}</td>
            <th></th>
            <td></td>
        </tr>
        </tbody>
    </table>
    </div>
    <div class="pro-title"><span class="pro-title-left">资金方信息</span><span class="pro-title-right">折叠</span></div>
    <div class="pro-box" >
        <div class="pro-box-list">
            <div class="monitor-grid" id="monitorList-grid">
            <#--	<table class="query-table">
            		 <colgroup>
			            <col width="60">
			            <col width="130">
			            <col width="60">
			            <col width="280">
			            <col width="280">
			            <col width="280">
       				 </colgroup>
       				 <tbody>
       				 <tr>
       				 	<th>序号</th>
       				 	<th>资金方名称</th>
       				 	<th>组织机构代码</th>
       				 	<th>营业执照号码</th>
       				 	<th>税务登记证号码</th>
       				 	<th>账户信息</th>
       				 </tr>
       				 <#list projectDTO.projectFactors as projectFactor >
       				 	<tr>
       				 		<td>${projectFactor_index+1}</td>
       				 		<td>${projectFactor.companyName!}</td>
       				 		<td>${projectFactor.organizationCode!}</td>
       				 		<td>${projectFactor.businessLicenceCode!}</td>
       				 		<td>${projectFactor.taxRegistrationCertificateCode!}</td>
       				 		<td>${projectFactor.accountNo!}</td>
       				 	</tr>
       				 </#list>
       				 </tbody>
            	</table> -->
            </div>
        </div>
    </div>
    <div class="pro-title"><span class="pro-title-left">基础交易信息</span><span class="pro-title-right">折叠</span></div>
    <div class="hide-box" >
    <table class="query-table" style="padding-bottom:0;">
        <colgroup>
            <col width="60">
            <col width="130">
            <col width="60">
            <col width="280">
        </colgroup>
        <tbody>
        <tr>
            <th>融资申请人</th>
            <td>${projectDTO.financeApplicant!}</td>
            <th></th>
            <td></td>
        </tr>
        <tr>
            <th>单笔贷款金额</th>
            <td>${projectDTO.singleLoanLimitLowerAdd!}-${projectDTO.singleLoanLimitUpperAdd!}元</td>
            <th></th>
            <td></td>
        </tr>
        <tr>
            <th>利率区间</th>
            <td>${projectDTO.interestRateLowerAdd!}-${projectDTO.interestRateUpperAdd}%</td>
            <th>利率单位</th>
            <td>${projectDTO.interestRateUnit.desc()!}</td>
        </tr>
        <tr>
            <th>单笔贷款期限</th>
            <td>${projectDTO.loanPeriodLower!}-${projectDTO.loanPeriodUpper!}</td>
            <th>期限单位</th>
            <td>${projectDTO.loanPeriodUnit.desc()!}</td>
        </tr>
		<tr>
			<th>还款方式</th>
			<td>
			<#if projectDTO.repaymentMethods?exists>
				<#list projectDTO.repaymentMethodList as repaymentMethodList > 
				<sapn style="padding-right:10px;">${repaymentMethodList.desc()!}</sapn>
				</#list>
			</#if>
			</td>
			<th></th>
			<td></td>
		</tr>
		<tr>
			<th>是否使用节假日</th>
			<td>
				<#if projectDTO.useHolidayPolicy>是<#else>否</#if>
			</td>
			<th></th>
			<td></td>
		</tr>
		<tr>
            <th>展期</th>
            <td>${projectDTO.extensionDays!}天</td>
            <th>提前还款手续费率</th>
            <td>${projectDTO.earlyRepaymentChargeRatioAdd!}%</td>
        </tr>
        <tr>
            <th>展期收息方式</th>
            <td>${projectDTO.extensionRatioMethod.desc()!}</td>
            <th>展期利率/展期上浮比例</th>
            <td>${projectDTO.extensionRatioAdd!}%</td>
        </tr>
        <tr>
            <th>逾期管理费罚息方式</th>
            <td>${projectDTO.penaltyRateMethod.desc()!}</td>
            <th>逾期利率/逾期上浮比例</th>
            <td>${projectDTO.penaltyRateAdd!}%</td>
        </tr>
        <tr>
            <th>扣款时点</th>
            <td>${projectDTO.deductionTime!}</td>
            <th></th>
            <td></td>
        </tr>
        <tr>
            <th>扣款规则</th>
            <td>${projectDTO.deductionRule.desc()!}</td>
            <th></th>
            <td></td>
        </tr>

        </tbody>
    </table>
    <table class="query-table" style="padding-top:0;">
        <colgroup>
            <col width="60">
            <col width="390">
            <col width="40">
            <col width="40">
        </colgroup>
        <tbody>
        <tr>
            <th>产品介绍</th>
            <td>
                <div class="pro-m-txt">${projectDTO.productIntroduction!}</div>
            </td>
            <th></th>
            <td></td>
        </tr>
        <tr>
            <th>产品类型</th>
            <td>
                <div class="pro-m-txt">${projectDTO.productTypeDesc!}</div>
            </td>
            <th></th>
            <td></td>
        </tr>
        </tbody>
    </table>
    </div>
    <div class="pro-title"><span class="pro-title-left">合同模板</span></div>
    <table class="pro-table">
        <colgroup>
            <col width="100">
            <col width="30">
            <col width="60">
            <col width="60">
            <col width="100">
            <col width="100">
            <col width="400">
        </colgroup>
        <tbody>
         <#list projectDTO.projectDocument as projectDocument >
        <tr>
            <th>合同版本日期</th>
            <td></td>
            <td>${projectDocument.version!}</td>
            <td>名称</td>
            <td>${projectDocument.name!}</td>
            <td>
                <a class="form-search-link" href="${mediaserver}/imageservice?mediaImageId=${projectDocument.mediaId!}&mediaType=download">下载</a>
            </td>
            <td></td>
        </tr>
        </#list>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td style="padding:30px 0;"><a class="form-search-link" href="${basepath!}/asset/project/projectList">返回</a></td>
            <td></td>
        </tr>
        </tbody>
    </table>

</div>
		<div id="projectListGridWrap">
		<div class="assetProjectListManageGrid"></div>
       </div>
</div>

<script>
	var detailAuth = ${authorizeKey("TZ_CREDIT_AUDIT_DETAIL")?string("true", "false")};
	var auditAuth = ${authorizeKey("TZ_CREDIT_AUDIT_AUDIT")?string("true", "false")};
</script>
</@hb.htmlBase>
