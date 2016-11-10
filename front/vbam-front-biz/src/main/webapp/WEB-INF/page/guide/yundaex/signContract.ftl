<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="融资客户确认" jsFiles=["common/signature.js","page/guide/yundaex/signContract.js"] cssFiles=[] localCssFiles=[] 
	curTopMenu = "" curSubMenu = "" removeExtHeader = false removeExtFooter = true showMenu=false>
	
<div class="backstage-mainWrap">
	<div class="commonTips-wrap">
	    <div class="commonTips-title">签署合同</div>
	    <div class="commonTips-content">
	        <div class="finaConfirm-content" style="text-align:center">
	            <div class="finaLeadCheck">
	            	<input id="supervisionAccountNo" type="hidden" value="${supervisionAccountNo!}"/>
	            	<input id="contractId" type="hidden" value="${contractId!}"/>
	                <label class="checkboxInfo">
	                    <span class="checkbox"></span>阅读并签署《<a id="readContract" download="Cana金融合同" class="repayment-link">国内保理业务合同</a>》
	                </label>
	            </div>
	            <#--<div class="finaLead-other">
	                下载《<a id="downloadContract" download="Cana金融合同" class="repayment-link">应收款债权转让协议</a>》签字并邮寄，邮寄地址：上海市黄淮区淮海中路222号。CANA收到合同后，会完成您的额度激活，并发送邮件给您。
	            </div>-->
	        </div>
	        <div class="commonTips-foot">
	        	<input id="isRead" type="hidden" value="${isRead!}"/>
	            <a id="completeContract" class="commonBtn-link confirm-btn disable-btn">同 意</a>
	        </div>
	    </div>
	</div>
	<footer class="backstage-foot">Copyright © 2015 CANA All Rights Reserved. 沪ICP备15045029号</footer>
</div>
<#include "../tipBoxTemplate.ftl" />
<style>
	#readContract,#downloadContract,#completeContract{cursor:pointer}
</style>
</@hb.htmlBase>