<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="合同阅读" jsFiles=["/page/yundaex/personal/contract.js"] cssFiles=["css/yunda.css"] 
		localCssFiles=[] removeExtHeader = false removeExtFooter = true showMenu=false>
<style>
input[type=button].inputDisable,input[type=button].inputDisable:hover{
	background-color: #ccc;
}
</style>
<div class="backstage-mainWrap">
	<input id="contractId" type="hidden" value="${contractNo!}"/>
	<input name="isRead" type="hidden" value="${isRead}"/>
	<input name="id" type="hidden" value="${id}"/>
	<input name="code" type="hidden" value="${code}"/>
    <div class="protocol-wrap">
        <div class="protocol-head">授权委托书</div>
        <div class="protocol-contain">
            <div class="protocol-title">委托人：${realName}</div>
            <br>
            <div class="protocol-title">受托人：${factorCompanyName}</div>
            <p>法定代表人：严正飞</p>
            <p>住所：深圳市前海深港合作区前湾一路1号A栋201室</p>
            <br>
            <p>鉴于委托人将其账户提供予<span style="text-decoration: underline;">&emsp;${finaceCompanyName}&emsp;</span>作为其在与受托人签订的编号为<span style="text-decoration: underline;">&emsp;${contractNo}&emsp;</span>的《国内保理业务合同》项下的收款账户，同时作为
                <span style="text-decoration: underline;">&emsp;${finaceCompanyName}&emsp;</span>在上海韵达货运有限公司（以下简称“韵达”）的监管账户；在此，委托人承诺并确认如下：</p>
            <p>若<span style="text-decoration: underline;">&emsp;${finaceCompanyName}&emsp;</span>到期未足额向受托人清偿保理融资款本息或者支付差额补足款或者未按承诺使用相关款项，委托人则无条件并不可撤销
            地授权受托人可通知韵达冻结委托人在韵达的监管账户中的资金，同时将其账户内的款项划付至受托人的指定账户，用于归还《国内
            保理业务合同》项下的本息、逾期管理费及其他相关款项，委托人不得对此提出异议。</p>
            <br><br>
            <div class="protocol-title" style="text-align: right;">委托人：<span style="display:inline-block;width:140px;"></span> </div>
            <br>
            <div class="protocol-title" style="text-align: right;">&emsp;&emsp;年&emsp;&emsp;月&emsp;&emsp;日<span style="display:inline-block;width:100px;"></span></div>

        </div>
        <div class="protocol-foot">
            <input type="button" id="numBtn" value="我已阅读" class="procl-link inputDisable" href="javascript: void(0);" disabled="true"/>
            <span>(10s)</span>
        </div>
    </div>
    <footer class="backstage-foot">Copyright © 2015 CANA All Rights Reserved. 沪ICP备15045029号</footer>
</div>
</@hb.htmlBase>