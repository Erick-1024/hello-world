<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="合同阅读" jsFiles=["/page/guide/travelzen/resign/contract.js"] cssFiles=["css/login.css"] 
		localCssFiles=[] removeExtHeader = false removeExtFooter = true showMenu=false>
<style>
input[type=button].inputDisable,input[type=button].inputDisable:hover{
	background-color: #ccc;
}
.protocol-list2 {
	padding-left: 90px;
	white-space: pre-line;
}
</style>
<div class="backstage-mainWrap">
    <div class="protocol-wrap">
		<input id="supervisionAccountNo" type="hidden" value="${supervisionAccountNo!}"/>
        <div class="protocol-head">${contractName!}【有追索权保理】</div>
        <div class="protocol-contain">
			<#include "../companyContractV2ContainText.ftl"/>
        </div>
        <div class="protocol-foot">
            <input type="button" id="numBtn" value="我已阅读" class="procl-link inputDisable" href="javascript: void(0);" disabled="true">
            <span>(10s)</span>
        </div>
    </div>
    <footer class="backstage-foot">Copyright © 2015 CANA All Rights Reserved. 沪ICP备15045029号</footer>
</div>
</@hb.htmlBase>