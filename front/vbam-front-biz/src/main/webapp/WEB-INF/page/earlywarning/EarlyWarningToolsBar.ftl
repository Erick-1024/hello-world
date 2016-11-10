<#macro toolsBar threeLevelMenu="" >
	<div class="thirdNavGroup">
		<ul>
			<#if authorizeKey("TZ_EARLYWARNING_CUSTOMER")>
        	<li <#if threeLevelMenu=="customer">class="thirdNavCut"</#if> >
                <a class="thirdNavItem" href="${basepath}/earlywarning/customer/list">贷后预警</a>
            </li>
            </#if>
            <#if authorizeKey("TZ_EARLYWARNING_INFORMATION")>
            <li <#if threeLevelMenu=="information">class="thirdNavCut"</#if> >
                <a class="thirdNavItem" href="${basepath}/earlywarning/customer/information/list">预警信息</a>
            </li>
            </#if>
            <#if authorizeKey("TZ_EARLYWARNING_REVIEW")>
            <li <#if threeLevelMenu=="review">class="thirdNavCut"</#if> >
                <a class="thirdNavItem" href="${basepath}/earlywarning/review/list">审核列表</a>
            </li>
            </#if>
            <#if authorizeKey("TZ_EARLYWARNING_CHANGE_HISTORY")>
            <li <#if threeLevelMenu=="change_history">class="thirdNavCut"</#if> >
                <a class="thirdNavItem" href="${basepath}/earlywarning/levelchange/list">预警流水</a>
            </li>
            </#if>
            <#if authorizeKey("TZ_EARLYWARNING_EVENT")>
            <li <#if threeLevelMenu=="event">class="thirdNavCut"</#if> >
                <a class="thirdNavItem" href="${basepath}/earlywarning/event/addList">新增预警事件</a>
            </li>
            </#if>
		</ul>
	</div>
</#macro>
