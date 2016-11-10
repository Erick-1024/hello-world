<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="合同阅读" jsFiles=["/page/guide/contract.js"] cssFiles=["css/login.css"] 
		localCssFiles=[] removeExtHeader = false removeExtFooter = true showMenu=false>
<style>
input[type=button].inputDisable,input[type=button].inputDisable:hover{
	background-color: #ccc;
}
</style>
<div class="backstage-mainWrap">
    <div class="protocol-wrap">
		<input id="supervisionAccountNo" type="hidden" value="${supervisionAccountNo!}"/>
        <div class="protocol-head">${contractName!}</div>
        <div class="protocol-contain">

<p> 合同编号：<u>${contractId!}</u></p>
<br/>
<p class="strongTxt">甲方（出借人）：<u>${individualLoanPersonName!}</u></p>           
<p class="strongTxt">身份证号码：<u>${individualLoanPersonIdentity!}</u></p> 
<br/>
<p class="strongTxt">乙方（借款人）：<u>${finaceCompanyName!}</u></p> 
<p class="strongTxt">身份证号码：<u>${finaceBusinessLicenceCode!}</u></p> 
<br/>
<p>乙方向甲方申请借款，甲方同意在上海凯拿资产管理有限公司提供的平台（以下简称“凯拿平台”）上向乙方出借款项，甲乙双方本着平等自愿、诚实信用的原则，经协商一致，达成本合同，并保证共同遵守执行。</p>

<br/>
<p class="strongTxt">一、借款本金</p>
<p>1.1乙方向甲方借款人民币（大写）<u>${creditLimitChinese!}</u> 元,（小写）<u>${creditLimit!}</u>元（以乙方实际收到的款项为准）。</p>
<p>1.2上述借款可采用分次出借的方式，若借款期间乙方有部分还款的，乙方对甲方最终的欠款以甲方已出借而尚未获得偿还的全部借款总额计算。</p>

<br/>
<p class="strongTxt">二、借款用途</p>
<p>2.1借款用于支付乙方在真旅网天地行平台购买机票等差旅服务产品产生的费用。</p>
<p>2.2乙方须保证借款目的和借款用途合法，不存在任何合同诈骗或集资诈骗的可能性，未经甲方事先书面同意，乙方不得将本合同项下的借款用于其他目的。</p>

<br/>
<p class="strongTxt">三、借款利息</p>
<p>3.1利率为<u>${interestRateRange!}</u>，具体以甲方支付借款前的确认通知为准（甲方的确认通知可以采用邮件通知、短信通知、凯拿平台发布通知等任一方式）；日利率=年利率/360=月利率/30。</p>
<p>3.2借款利息=借款本金*日利率*实际借款期限</p>

<br/>
<p class="strongTxt">四、借款方式</p>
<p>4.1乙方委托上海长寿国际旅行社有限公司代为收取上述借款，直接用于支付乙方在真旅网天地行平台购买机票等差旅服务产品产生的费用，指定的代收账户如下：</p>
<p>户名：<u>上海长寿国际旅行社有限公司</u></p>
<p>账号：<u>3110 2100 1005 1241 810</u></p>
<p>开户行：<u>中信银行上海静安支行</u></p>
<p>本账户不作为接收借款人还款的账户，借款人应按本合同6.1条还款。</p>
<p>4.2乙方在真旅网天地行平台购买机票等差旅服务产品时，操作选择使用甲方出借的款项用以支付订单款，则甲方依据乙方操作订单生成的支付接口文档，委托凯拿平台将借款汇入上述乙方指定账户后即视为乙方已实际收到甲方出借的款项，乙方即负有按照本合同约定还本付息的义务。</p>
 
<br/>
<p class="strongTxt">五、还款期限</p>
<p>乙方应于每次收到借款之日起<u>一个月</u>内还清该笔借款本息。</p>
 
<br/>
<p class="strongTxt">六、还款方式</p>
<p>6.1乙方应按照甲方要求，在凯拿平台上开设还款专用账户，并保证在借款到期日前，该还款专用账户上有足够的款项用以清偿借款本息。</p>
<p>6.2甲方允许乙方提前还款，提前还款的，利息计至提前还款日。</p>
<p>6.3乙方在此不可撤销地授权凯拿平台在借款到期后或者乙方提前还款时将上述还款专用账户中的款项扣划，用以向甲方清偿借款本息。</p>

<br/>
<p class="strongTxt">七、还款顺序</p>
<p>7.1甲乙双方之间存在多笔已到期借款的，甲方有权决定乙方每笔还款所对应的清偿顺序。</p>
<p>7.2乙方的还款不足以清偿全部欠款的，甲方有权根据需要选择不同顺序对以下款项进行清偿：（1）为追索而支出的费用、赔偿金；（2）逾期利息；（3）利息；（4）本金。</p>

<br/>
<p class="strongTxt">八、承诺和保证</p>
<p>8.1乙方应按甲方要求提供其在中国人民银行征信中心的“个人信用信息服务平台”的用户名、密码、短信验证码等，授权并协助甲方或甲方委托的第三方查询、获取其个人信用报告；<span class="strongTxt">同时授权甲方或凯拿平台可向其他依法设立的信用信息数据库或其他社会征信机构查询其个人征信信息。</span></p>
<p>8.2甲乙双方协商一致委托凯拿平台代甲方在必要时对乙方进行借款的违约提醒、催收工作，包括但不限于：电话通知、发送催收函、对乙方提起诉讼等。乙方对前述委托的提醒、催收事项已明确知晓并须积极配合。</p>
<p>8.3乙方同意，在乙方发生违约行为时，甲方或者凯拿平台有权通知上海长寿国际旅行社有限公司将乙方已购买的机票等差旅服务产品进行退票，并将退票款汇至甲方账户，用以清偿乙方对甲方的借款本息、逾期利息等款项。乙方在此不可撤销地授权上海长寿国际旅行社有限公司进行上述退票退款以及将退票款代为还款的操作，无需再另行征得乙方同意，乙方不得对退票并汇付退票款的行为提出任何异议。</p>
<p class="strongTxt">8.4乙方同意，甲方或凯拿平台有权将乙方的违约情况通报中国人民银行征信系统、依法设立的信用信息数据库或其他社会征信机构。</p>

<br/>
<p class="strongTxt">九、违约责任：</p>
<p>9.1乙方如未按合同约定归还借款本息，则甲方有权按照如下方式向乙方收取逾期利息：逾期利息利率为在原约定的利率基础上上浮百分之五十，计算方法为：逾期利息=逾期未支付的款项×日利率×（1+50%）×逾期天数。</p>
<p>9.2若乙方在本合同项下某一笔借款出现逾期的，则甲方有权要求乙方立即提前归还其他所有尚未到期的借款，并有权解除本合同，拒绝乙方其他借款请求。</p>
<p>9.3若乙方违约，导致甲方还有其他损失的，乙方应赔偿甲方的其他经济损失以及因诉讼发生的律师费、诉讼费、差旅费等费用。</p>

<br/>
<p class="strongTxt">十、其他</p> 
<p>10.1本合同适用中华人民共和国法律并依其解释。为本合同之目的，本条所述“中华人民共和国法律”不包括香港特别行政区、澳门特别行政区和台湾地区法律。 </p>
<p>10.2本合同在履行过程中发生的争议，由当事人双方友好协商解决，也可由第三人调解。协商或调解不成的，可依法向本合同签订地上海市黄浦区有管辖权的人民法院提起诉讼。</p>
<p>10.3本合同采用电子文本形式，协议各方采用电子签名方式签署后即成立，自甲方依据本合同约定将款项支付给乙方之日起生效。</p>
<p>10.4如果本合同的任何一条或多条违反适用的法律、法规，则该条将被视为无效，但该无效条款并不影响本合同其他条款的效力。</p>

<br/>
<p>（以下无正文）</p>

<div class="stamp-box">
    <div class="stamp-row">甲方（签字）：</div>
    <div class="stamp-row"> ${year!} 年 ${month!} 月 ${day!} 日 </div>
</div>
<div class="stamp-box">
    <div class="stamp-row">乙方（签字）：</div>
    <div class="stamp-row"> ${year!} 年 ${month!} 月 ${day!} 日 </div>
</div>


        </div>
        <div class="protocol-foot">
            <input type="button" id="numBtn" value="我已阅读" class="procl-link inputDisable" href="javascript: void(0);" disabled="true">
            <span>(10s)</span>
        </div>
    </div>
    <footer class="backstage-foot">Copyright © 2015 CANA All Rights Reserved. 沪ICP备15045029号</footer>
</div>
</@hb.htmlBase>