<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="签署合同" jsFiles=["common/signature.js","page/yundaex/personal/signContract.js"] cssFiles=[] localCssFiles=[] 
	curTopMenu = "" curSubMenu = "" removeExtHeader = false removeExtFooter = true showMenu=false>

<div class="backstage-mainWrap">
	<div class="commonTips-wrap">
	    <div class="commonTips-title">签署合同</div>
	    <div class="commonTips-content">
	        <div class="finaConfirm-content" style="width:268px;">
	        	<input id="contractId" type="hidden" value="${contractId!}"/>
	        	<#if personDTO.type != "CONTROLLER">
		            <div class="finaLeadCheck">
		                <label class="checkboxInfo">
		                    <span class="checkbox"></span>阅读并签署《<a download="Cana金融合同" class="repayment-link readContract" onclick="readContract('ACCOUNT_HOLDER')">授权委托书</a>》
		                </label>
		            </div>
	            </#if>
	            <#if personDTO.type != "ACCOUNT_HOLDER">
		            <div class="finaLeadCheck">
		                <label class="checkboxInfo">
		                    <span class="checkbox"></span>阅读并签署《<a download="Cana金融合同" class="repayment-link readContract" onclick="readContract('CONTROLLER')">个人连带担保责任保证书</a>》
		                </label>
		            </div>
	            </#if>
	            <#--<div class="finaLead-other">
	                下载《<a id="downloadContract" download="Cana金融合同" class="repayment-link">应收款债权转让协议</a>》签字并邮寄，邮寄地址：上海市黄淮区淮海中路222号。CANA收到合同后，会完成您的额度激活，并发送邮件给您。
	            </div>-->
	        </div>
	        <div class="commonTips-foot">
	        	<input id="isRead" type="hidden" value="${isRead!0}"/>
	        	<input name="id" type="hidden" value="${id}"/>
	        	<input name="code" type="hidden" value="${code}"/>
	        	<input type="hidden" value="${(personDTO.residentIdentityCardNo)!}" name="residentIdentityCardNo"/>
                <a id="completeContract" class="commonBtn-link confirm-btn disable-btn">同 意</a>
	        </div>
	    </div>
	</div>
	<footer class="backstage-foot">Copyright © 2015 CANA All Rights Reserved. 沪ICP备15045029号</footer>
</div>
<#include "../../tipBoxTemplate.ftl" />
<style>
	.readContract,#downloadContract,#completeContract{cursor:pointer}
</style>
</@hb.htmlBase>