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
        <div class="protocol-head">连带责任保证合同</div>
        <div class="protocol-contain">
            <p>本人<span style="text-decoration: underline;">&emsp;${realName}&emsp;</span>（以下简称“保证人”）自愿承担<span style="text-decoration: underline;">&emsp;${finaceCompanyName}&emsp;</span>（以下简称“债务人”）与<span style="text-decoration: underline;">${factorCompanyName}</span>（以下简称“债权人
                ”）签订的编号为<span style="text-decoration: underline;">&emsp;${contractNo}&emsp;</span>的《国内保理业务合同》（以下简称“主合同”）项下债务人到期不履约造成的全部责任。债权人有权
                将相关违约信息通报中国人民银行征信系统和其他依法设立的信用信息数据库。</p>
            <br>
            <p>1. 本合同经签字后同主合同同时生效，保证期间为主合同履行期限届满之日起二年，保证人需对保证期间所有单笔用款承担连带保
            证责任。</p>
            <p>2. 保证担保的范围包括：债权人在主合同项下的到期未获偿付且应由债务人回购或提前回购的融资本金及利息、其他相关费用。但
            如果保证人未能按照本协议的规定及时履行回购或者提前回购的无限连带保证责任，则自其应付未付之日起，按照实际逾期金额和实
            际逾期天数，按主合同规定的逾期违约金标准向债权人加付逾期违约金，保证人的担保范围还包括债权人为实现本协议项下的权利而
            支出的任何法律费用或其他费用，包括但不限于财产保全费、诉讼仲裁费、差旅费、公证认证费、翻译费、评估拍卖费、律师费等。</p>
            <p>3. 保证人自愿接受债权人对其资金和财产状况的调查了解，并按照债权人的要求，提供真实、合法、完整、有效的资金和财产等资
            料、信息；</p>
            <p>4. 本合同所称保证为不可撤销的无限连带责任保证。保证人保证债务人将根据主合同的规定按期足额履行相关协议项下的回购或者
            提前回购义务，如果债务人未能按照主合同的规定履行义务，则债权人有权要求保证人履行。保证人承诺在收到债权人通知后，无条
            件作为第一顺位债务人清偿所担保的债务，通知形式不限书面、邮件、电话、后台在线系统等形式。</p>
            <p>5. 保证人有足够的能力承担保证责任，不因任何指令、财力状况的改变、与任何单位签订的任何协议而减轻或免除所承担的保证责
            任；</p>
            <p>6. 本合同项下的保证是独立的、不可撤销的、持续的担保。本保证合同不受主合同效力的影响，不因主合同无效、可撤销而无效、
            可撤销。保证人的保证责任不因债权人同意展期、重组，或债权人与债务人协议变更主合同而有所改变，不因债务人或保证人任何变
            更自身经营或管理体制的行为而有所改变，也不因债权人将主债权转让予第三人而有所改变，保证人对主债权受让人继续承担保证责
            任。债权人与债务人协议变更主合同或者债务人变更自身经营或管理体制或者债权人将主债权转让予第三人，未取得保证人的书面同
            意，保证人也应承担保证责任。</p>
            <p>7. 债权人依据主合同约定或法律、法规规定解除主合同，或依据主合同约定提前收回主债权时，有权要求保证人提前承担保证责任
            ，保证人应当在债权人发出通知之日起<span style="text-decoration: underline;">&emsp;5个工作日&emsp;</span>内履行连带保证责任，通知形式包括但不限于短信、邮件、电话、后台在线系统等
            。</p>
            <p>8. 当保证人遇到此类情况时，应当于事项发生之日起10日内书面通知债权人。①财务状况恶化、或涉及重大经济纠纷；②住所、联
            系电话等联系发生变更；③发生其他不利于债权人债权实现的情形时；</p>
            <p>9. 本协议之效力、解释、变更、执行与争议解决均适用中华人民共和国法律，因本协议产生之争议，由上海市黄浦区有管辖权人民
            法院管辖。</p>
            <br><br>
            <p>保证人（签名）：            <span style="width:300px; display:inline-block;"></span>债权人(盖章):${factorCompanyName}</p>
            <br>
            <p>身份证号：                          <span style="width:336px; display:inline-block;"></span>法定代表人（签章）：</p>
            <br>
            <p>联系电话：</p>
            <br>
            <p>签署日期：  &emsp;&emsp; 年 &emsp;&emsp; 月 &emsp;&emsp;  日 <span style="width:204px; display:inline-block;"></span>  签署日期：  &emsp;&emsp; 年   &emsp;&emsp; 月   &emsp;&emsp; 日</p>
            <p>（保证人身份证正反面复印件随本合同一同寄回）</p>

        </div>
        <div class="protocol-foot">
            <input type="button" id="numBtn" value="我已阅读" class="procl-link inputDisable" href="javascript: void(0);" disabled="true"/>
            <span>(10s)</span>
        </div>
    </div>
    <footer class="backstage-foot">Copyright © 2015 CANA All Rights Reserved. 沪ICP备15045029号</footer>
</div>
</@hb.htmlBase>