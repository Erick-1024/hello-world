<#macro toolsBar threeLevelMenu="" >
	<div class="thirdNavGroup">
		<ul>
			<#if authorizeKey("YD_EARLYWARNING_POSTLOAN")>
        	<li <#if threeLevelMenu=="postLoan">class="thirdNavCut"</#if> >
                <a class="thirdNavItem" href="${basepath}/yundaex/earlywarning/postLoanlist">贷后预警</a>
            </li>
            </#if>
            <#if authorizeKey("YD_EARLYWARNING_INFORMATION")>
            <li <#if threeLevelMenu=="information">class="thirdNavCut"</#if> >
                <a class="thirdNavItem" href="${basepath}/yundaex/earlywarning/informationList">预警信息</a>
            </li>
            </#if>
            <#if authorizeKey("YD_EARLYWARNING_REVIEW")>
            <li <#if threeLevelMenu=="review">class="thirdNavCut"</#if> >
                <a class="thirdNavItem" href="${basepath}/yundaex/earlywarning/reviewList">审核列表</a>
            </li>
            </#if>
            <#if authorizeKey("YD_EARLYWARNING_CHANGE_HISTORY")>
            <li <#if threeLevelMenu=="change">class="thirdNavCut"</#if> >
                <a class="thirdNavItem" href="${basepath}/yundaex/earlywarning/changeList">预警流水</a>
            </li>
            </#if>
            <#if authorizeKey("YD_EARLYWARNING_ADD")>
            <li <#if threeLevelMenu=="add">class="thirdNavCut"</#if> >
                <a class="thirdNavItem" href="${basepath}/yundaex/earlywarning/addList">新增预警事件</a>
            </li>
            </#if>
		</ul>
	</div>
</#macro>
