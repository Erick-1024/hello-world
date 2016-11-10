<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="合同阅读" jsFiles=["/page/guide/yundaex/loanContract.js"] cssFiles=["css/yunda.css"] 
		localCssFiles=[] removeExtHeader = false removeExtFooter = true showMenu=false>
<style>
input[type=button].inputDisable,input[type=button].inputDisable:hover{
	background-color: #ccc;
}
</style>
<div class="backstage-mainWrap">
	<input id="applyAmt" type="hidden" value="${applyAmt!}"/>
	<input id="proId" type="hidden" value="${proId!}"/>
    <div class="protocol-wrap">
        <div class="protocol-head">单笔融资合同</div>
        <div class="protocol-head" style="font-size:12px;font-weight:normal;">${batchNo!}</div>
        <div class="protocol-contain">
            <div class="protocol-title">甲方：凯拿商业保理（深圳）有限公司</div>
            <br>
            <div class="protocol-title">乙方：<span class="udLine" style="text-indent:0">${companyName}</span> </div>
            <br>
            <p class="protocol-title">鉴于甲乙双方已签署了《国内保理业务合同》（编号：<span class="udLine" style="text-indent:0">${protocolNo!}</span>），乙方根据《国内保理业务合同》约定向甲方申请保理融资款，针对单笔保理融资款的具体要素，双方协商一致约定如下：</p>
            <P>一、	本次保理融资放款金额为人民币<span class="udLine" style="text-indent:0">${applyAmt!}</span>元，以甲方实际放款金额为准。</P>
            <P>二、	保理融资利率为<span class="udLine" style="text-indent:0">${rate!}</span>。</P>
            <P>三、	本次保理融资期限为自实际放款之日起<span class="udLine" style="text-indent:0">${loanPeriod}</span>个月。</P>
            <P>四、	本次融资的还款付息方式为：</P>
            <P>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<#if repaymentMethod == "ORDER">☑<#else>□</#if>1月，随借随还；</P>
            <P>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<#if repaymentMethod == "MONTHLY">☑<#else>□</#if>3月，按月付息，到期还本并结清剩余利息；</P>
            <P>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<#if repaymentMethod == "EQUALALL">☑<#else>□</#if>6月，等额本息；</P>
            <P>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<#if repaymentMethod == "EQUALPRINCIPAL">☑<#else>□</#if>6月，等额本金；</P>
            <P>由甲方自行扣收应收账款回款以清偿融资款本息，且乙方于融资到期日承担差额（若有）补足义务。</P>
            <P class="protocol-title">乙方承诺将按照《国内保理业务合同》以及本合同约定履行义务，《国内保理业务合同》与本合同不一致的内容，以本合同为准。</P>
            <P class="protocol-title">本合同一式二份，甲乙双方各执一份，具有同等法律效力。</P>
            <br><br>
            <div class="protocol-title" style="text-align: center;">（以下无正文）</div>
            <br><br><br><br>
            <div class="protocol-title">甲方（公章）：${factorCompanyName!}<span style="display:inline-block;width:250px;"></span>乙方（公章）：${finaceCompanyName!}</div>
            <br><br>
            <div class="protocol-title">&emsp;&emsp;&emsp;&emsp;年&emsp;&emsp;&emsp;&emsp;    月&emsp;&emsp;&emsp;&emsp;    日   <span style="display:inline-block;width:265px;"></span> &emsp;&emsp;&emsp;&emsp; 年&emsp;&emsp;&emsp;&emsp;    月&emsp;&emsp;&emsp;&emsp;    日</div>
            <br><br>
        </div>
        <div class="protocol-foot">
            <input type="button" id="numBtn" value="我已阅读" class="procl-link inputDisable" href="javascript: void(0);" disabled="true">
            <span>(10s)</span>
        </div>
    </div>
    <footer class="backstage-foot">Copyright © 2015 CANA All Rights Reserved. 沪ICP备15045029号</footer>
</div>
</@hb.htmlBase>