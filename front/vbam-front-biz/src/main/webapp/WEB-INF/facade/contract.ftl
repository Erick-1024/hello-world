<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="账号激活" jsFiles=["/facade/contract.js"] cssFiles=["css/login.css"] localCssFiles=[] removeExtHeader = true removeExtFooter = true>
<style>
	span.font-bold{
		color:#3F3D3D;
		font-weight:bold;
	}
</style>
<header class="main-header">
    <div class="stairMenuContent">
        <div class="wrap clearfix">
            <div class="navLeft">
                <a class="logo" href="${basepath}/welcome">CANA</a>
            </div>
            <div class="headRightOperate">
                <span class="userContent">Hi<a class="goLogin" href="${basepath}/facade/signin">请登录</a></span>
            </div>
        </div>
    </div>
</header>
<div class="backstage-mainWrap">
    <div class="protocol-wrap">
        <div class="protocol-head">凯拿平台会员服务协议</div>
        <div class="protocol-contain">
            <div class="protocol-title">重要提示：</div>
            <p>
                凯拿资产管理平台（网址：${vbamWebServer}，以下简称“平台”）所提供的各项服务的所有权和运作权均归【上海凯拿资产管理有限公司】(下简称“凯拿资产管理”）所有。凯拿资产管理平台会员服务协议（以下简称“本协议”）系由会员与凯拿资产管理就平台的各项服务所订立的相关权利义务规范。本协议在会员和凯拿资产管理间具有合同上的法律效力。<span class="font-bold">凯拿资产管理在此特别提醒您认真阅读、充分理解本协议各条款，特别是其中所涉及的免除及限制凯拿资产管理责任的条款、对会员权利限制条款等。请您审慎阅读并选择接受或不接受本协议。除非您接受本协议所有条款，否则您无权使用凯拿资产管理于本协议下所提供的服务。您一经注册或使用平台即视为对本协议全部条款已充分理解并完全接受。如有违反而导致任何法律后果的发生，您将以自己的名义独立承担相应的法律责任。</span>
            </p>
			<p>
				<span class="font-bold">会员在作出投资决定之前，应仔细阅线下纸质版相关合同，全面认识产品的风险收益特征和产品特性，并充分考虑自身的风险承受能力，理性判断市场，谨慎做出投资决策。</span>
			</p>
			<p>
				各类投资品种存在市场风险、信用风险、流动性风险及操作风险等，从而可能对投资产产品财产和收益产生影响，会员应知晓投资产品存在上述风险。
			</p>
			<p>
				本网站所载商标、微号和服务标志及其他任何数据的所有版权、专利权、知识产权及其它产权均属凯拿资管所有。本网站所载资料受版权保护。未经凯拿资管事前以书面同意，不可将此等材料的任何部分修改、翻版、储存于检索系统、传送、复制、分发或以任何其他方式作商业或公共用途。
			</P>
			<p>
				<span class="font-bold">凯拿资管尽力严谨处理本网站所载内容，但并不就其完整性和准确性作出保证，凯拿资管的任何员工也不就该等数据之间任何错误或遗漏承担责任（包括任何第三方责任）。
凡通过本网站与其他网站的链接，而获得其他网站所提供的网上资料、内容及服务，会员应该自己进行辨别及判断，并应当由该等网站对其提供内容承担相应责任，凯拿资管对此不承担任何责任。</span>
			</p>
			<p>
				会员于此确认，凯拿资管对于会员所使用本网站的各项服务或无法使用各项服务所导致的任何直接、间接或特别损害，不应当承担任何法律责任。凯拿平台应当具有法律法规规定的其它有关免责规定项下所有的一切权利，如果凯拿资管没有主张或延迟主张该等权利，并不应当视为凯拿资管对于该等权利的弃权。本协议与本网站内的其他协议、规则、通知、公告等一起构成凯拿资管与会员就本网站的服务或产品所达成的全部合意。
			</p>
            <div class="protocol-title">第一章 凯拿资产管理服务</div>
            <p><span class="font-bold">第一条 凯拿资产管理服务是由上海凯拿资产管理服务有限公司通过凯拿资产管理平台（网址：${vbamWebServer}
                ）向其会员提供的服务，具体服务内容主要包括：交易信息发布、提供交易管理服务、提供客户服务等，具体服务内容详情以凯拿资产管理届时提供的服务为准。</span></p>

            <p>第二条
                在本协议履行过程中，凯拿资产管理可根据情况对本服务协议进行修改。有关本协议的任何内容发生变动，凯拿资产管理将通过平台公布最新的服务协议，会员认可并同意，<span class="font-bold">凯拿资产管理将最新的协议发布在平台即履行了向会员的告知义务，本协议一经发布立即对所有会员产生效力，会员承诺及时查阅相关信息，并认可凯拿资产管理不再向会员作个别通知。如果会员不同意凯拿资产管理对本服务协议所做的修改，会员有权停止使用平台服务。</span>如果会员继续使用平台服务，则视为会员接受凯拿资产管理对本服务协议所做的修改，并应遵照修改后的协议执行。</p>

            <p>第三条
                凯拿资产管理对于会员的通知及任何其他的协议、告示或其他关于会员使用会员账户及服务的通知，会员同意平台可通过平台公告、电子邮件、手机短信、无线通讯装置等电子方式等方式进行，会员同意该等通知于发送之日视为已送达收件人。<span class="font-bold">前述通知凯拿资产管理以会员向平台提交的通讯信息进行发送，会员变更通讯信息的，应当及时向平台提交相关变更信息，否则因信息变更导致会员无法收到相关通知的，由会员自行承担责任。因信息传输等原因导致会员未在前述通知发出当日收到该等通知的，凯拿资产管理不承担责任。</span></p>

            <p>第四条
                凯拿资产管理可以依其合理判断暂时停止提供、限制或改变平台对会员的服务，只要会员仍然使用平台服务，即表示会员仍然同意本服务协议。凯拿资产管理承诺尽最大努力恢复对会员的服务，若会员因自身违法、违规操作平台或造成平台及其他会员损失等情况导致平台停止对其提供服务的，会员同意在前述情况或行为未予纠正之前，凯拿资产管理有权不恢复对会员的服务。</p>

            <div class="protocol-title">第二章 会员</div>
            <p>第五条 凯拿资产管理的会员是指符合中华人民共和国法律法规规定的具有完全民事权利和民事行为能力，能够独立承担民事责任的自然人或法人。在使用凯拿资产管理服务前，会员必须先在平台上进行实名注册。</p>

            <p><span class="font-bold">第六条 会员承诺以下事项：</span></p>

            <p><span class="font-bold">1、会员必须依凯拿资产管理要求提示提供会员真实、最新、有效及完整的资料。</span></p>

            <p>
                <span class="font-bold">2、会员有义务维持并及时更新会员的资料，确保其为真实、最新、有效及完整。若会员提供任何错误、虚假、过时或不完整的资料，或者凯拿资产管理依其独立判断怀疑资料为错误、虚假、过时或不完整，凯拿资产管理有权暂停或终止会员的会员账户，并拒绝会员使用平台的部份或全部功能。在此情况下，凯拿资产管理不承担任何责任，并且会员同意承担因此所产生的直接或间接的任何支出或损失。</span></p>

            <P><span class="font-bold">3、如因会员未及时更新基本资料，导致平台无法提供或提供时发生任何错误，会员不得将此作为取消交易或拒绝付款的理由，凯拿资产管理亦不承担任何责任，所有后果应由会员承担。</span></P>

            <p>
                <span class="font-bold">4、会员承诺通过会员注册的平台账户所作出的所有行为和指令均是会员的真实意思表示，会员不得将平台账户出借或授权他人使用，若会员擅自将平台账户出借或授权他人使用、或因保管不善导致账户丢失、被盗用等情况所造成的损失由会员自行承担，且会员应当承担由会员账户所执行的行为和指令所产生的法律责任。</span></p>

            <p><span class="font-bold">5、会员同意凯拿资产管理可通过合法途径搜集或核实会员的信息，并按照本协议的约定进行使用或披露。</span></p>

            <div class="protocol-title">第三章 凯拿资产管理服务的内容</div>
            <p>第七条
                凯拿资产管理服务内容主要包括根据会员需求发布交易信息、提供交易管理服务、提供客户服务等，具体详情以平台届时提供的服务内容为准。凯拿资产管理服务的部分内容需要会员根据凯拿资产管理要求完成身份认证及银行卡认证后提供，未进行身份认证及/或银行卡认证的会员将无法使用该部分平台服务。因未能完成认证而无法享受平台服务造成的损失，凯拿资产管理不承担任何责任。

            <p>第八条
                <span class="font-bold">凯拿资产管理将为会员提供信息发布服务。会员确认凯拿资产管理向会员提供的各种信息及资料仅为会员进行交易的参考，会员应依其独立判断做出交易决策。会员依据凯拿资产管理提供的信息进行交易的，产生的风险由会员自行承担，会员确认凯拿资产管理不对会员进行交易的本金、收益或其他权益进行任何承诺或担保。会员无权因其采纳了凯拿资产管理提供的信息或咨询而向凯拿资产管理提出任何权利主张。在交易过程中，会员之间发生的纠纷，由纠纷各方自行解决，凯拿资产管理不承担任何责任。</span></p>

            <p><span class="font-bold">第九条 凯拿资产管理将为会员提供以下交易管理服务：</span></p>

            <P><span class="font-bold">1、会员在凯拿资产管理平台进行注册时将生成会员账户，会员账户将记载会员在平台的活动，上述会员账户是会员登陆平台的唯一账户。</span></P>

            <p><span class="font-bold">2、会员保证并承诺通过平台进行交易的资金来源合法。</span></p>

            <p>
                <span class="font-bold">3、会员确认，会员在平台上按凯拿资产管理服务流程所确认的交易状态，将成为凯拿资产管理为会员进行相关交易或操作（包括但不限于支付或收取款项、冻结资金、订立合同等）的不可撤销的指令。会员同意相关指令的执行时间以凯拿资产管理在凯拿资产管理系统中进行实际操作的时间为准。会员同意凯拿资产管理有权依据本协议及/或凯拿资产管理相关纠纷处理规则等约定对相关事项进行处理。</span></p>

            <p><span class="font-bold">会员未能及时对交易状态进行修改、确认或未能提交相关申请所引起的任何纠纷或损失由会员自行负责，凯拿资产管理不承担任何责任。</span></p>

            <p><span class="font-bold">4、会员了解凯拿资产管理并不是银行或金融机构，按照中国法律规定，凯拿资产管理不提供“即时”金额转账服务，会员同意凯拿资产管理对资金到账延迟不承担任何责任。</span></p>

            <p><span class="font-bold">5、会员理解并同意，凯拿资产管理向符合条件的会员提供服务。凯拿资产管理对在平台上进行交易行为不承担任何责任，会员因交易行为导致的损失（包括但不限于收益、手续费等损失）由会员自行承担，凯拿资产管理不承担责任。</span></p>

            <p>
                <span class="font-bold">6、会员通过平台进行各项交易或接受交易款项时，若会员未遵从本服务协议条款或凯拿资产管理公布的交易规则中的操作指示，凯拿资产管理不承担任何责任。若发生上述状况而款项已先行拨入会员账户下，会员同意凯拿资产管理有权直接从相关会员
                账户中扣回款项及禁止会员要求支付此笔款项之权利。此款项若已汇入会员的银行账户，会员同意凯拿资产管理有向会员事后索回的权利，由此产生的追索费用由会员承担。</span></p>

            <p><span class="font-bold">7、凯拿资产管理有权基于交易安全等方面的考虑不时设定涉及交易的相关事项，包括但不限于交易限额、交易次数等，会员了解凯拿资产管理的前述设定可能会对交易造成一定不便，对此没有异议。</span></p>

            <p>
                <span class="font-bold">8、如果凯拿资产管理发现了因系统故障或其他任何原因导致的处理错误，无论有利于凯拿资产管理还是有利于会员，凯拿资产管理都有权纠正该错误。如果该错误导致会员实际收到的款项多于应获得的金额，则无论错误的性质和原因为何，凯拿资产管理保留纠正不当执行的交易的权利，会员应根据凯拿资产管理向会员发出的有关纠正错误的通知的具体要求返还多收的款项或进行其他操作。会员理解并同意，会员因前述处理错误而多付或少付的款项均不计收益，凯拿资产管理不承担因前述处理错误而导致的任何损失或责任（包括会员可能因前述错误导致的收益、汇率等损失）。</span></p>

            <p><span class="font-bold">第十条 凯拿资产管理将为会员提供以下客户服务：</span></p>

            <p>
                <span class="font-bold">1、银行卡认证：为使用凯拿资产管理或凯拿资产管理委托的第三方机构提供的充值、取现、代扣等服务，会员应按照服务提供方规定的流程提交以会员本人名义登记的有效银行借记卡等信息，经由服务提供方审核通过后，会将会员的账户与前述银行账户进行绑定。如会员未按照服务提供方规定提交相关信息或提交的信息错误、虚假、过时或不完整，或者服务提供方有合理的理由怀疑会员提交的信息为错误、虚假、过时或不完整，服务提供方有权拒绝为会员提供银行卡认证服务，会员因此未能使用充值、取现、代扣等服务而产生的损失自行承担。</span></p>

            <p><span class="font-bold">2、充值：会员可使用凯拿资产管理指定的方式向会员账户充入资金，用于通过平台进行交易。</span></p>

            <p>
                <span class="font-bold">3、代收/代付：凯拿资产管理按照其平台当时向会员开放的功能提供代收/代付服务，自行或委托第三方机构代为收取其他会员或有第三方向会员的账户支付的本息、代偿金等各类款项，或者，将会员账户里的款项支付给会员指定的其他方。凯拿资产管理不保证提供的前述服务符合会员的期望。</span></p>

           <!-- <p><span class="font-bold">转账：一期仅限转款至cana平台内有关联的银行账户中</span></p>-->

            <p>
                <span class="font-bold">4、提现：会员可以通过平台当时开放的取现功能将会员账户中的资金转入经过认证的银行卡账户中。凯拿资产管理将于收到会员的前述指示后，尽快通过第三方机构将相应的款项汇入会员经过认证的银行卡账户（根据会员提供的银行不同，会产生汇入时间上的差异）。</span></p>

            <!--<p>必须提现至本人名下的银行账户中，如提现至他人账户，cana不承担任何责任</p>-->

            <p>
            <span class="font-bold">
                5、查询：凯拿资产管理将对会员在平台的所有操作进行记录，不论该操作之目的最终是否实现。会员可以通过会员账户实时查询会员账户名下的交易记录。会员理解并同意会员最终收到款项的服务是由会员经过认证的银行卡对应的银行提供的，需向该银行请求查证。会员理解并同意通过平台查询的任何信息仅作为参考，不作为相关操作或交易的证据或依据；如该等信息与凯拿资产管理记录存在任何不一致，应以凯拿资产管理提供的书面记录为准。
                会员了解，上述充值、代收/代付及提现服务涉及凯拿资产管理与银行、担保公司、第三方支付机构等第三方的合作。会员同意：(a)
                受银行、担保公司、第三方支付机构等第三方仅在工作日进行资金代扣及划转的现状等各种原因所限，凯拿资产管理不对前述服务的资金到账时间做任何承诺，也不承担与此相关的责任，包括但不限于由此产生的收益、货币贬值等损失；(b)
                一经会员使用前述服务，即表示会员不可撤销地授权凯拿资产管理进行相关操作，且该等操作是不可逆转的，会员不能以任何理由拒绝付款或要求取消交易。
                就前述服务，凯拿资产管理暂不会向会员收取费用，但会员应按照第三方的规定向第三方支付费用，具体请见第三方网站的相关信息。会员与第三方之间就费用支付事项产生的争议或纠纷，与凯拿资产管理无关。</span></p>

            <p><span class="font-bold">6、会员每次使用凯拿资产管理服务应直接登录平台或使用凯拿资产管理提供的链接登陆凯拿资产管理平台 （网址：${vbamWebServer},
                如凯拿资产管理以公告等形式发布新的网址，请届时登陆新的网址），而不要通过邮件或其他网站提供的链接登录。会员每次拨打凯拿资产管理客户电话应拨打凯拿资产管理官方网站提供的客服电话
                （如凯拿资产管理以公告等形式发布新的客服电话，请届时拨打新的客服电话），而不要拨打其他任何电话。</span></p>

            <p>
                <span class="font-bold">7、会员同意，凯拿资产管理有权在提供凯拿资产管理服务过程中以各种方式投放各种商业性广告或其他任何类型的商业信息（包括但不限于在平台的任何页面上投放广告），并且，会员同意接受凯拿资产管理通过电子邮件或其他方式向会员发送商品促销或其他相关商业信息。</span></p>

            <p>第十一条 凯拿平台将为会员提供以下合同管理服务：</p>

            <p>
                <span class="font-bold">1、在凯拿资产管理平台交易需订立的合同采用电子合同方式。会员使用会员账户登录平台后，根据凯拿资产管理的相关规则，以会员账户ID在平台通过点击确认或类似方式签署的电子合同即视为会员本人真实意愿并以会员本人名义签署的合同，具有法律效力。</span>个人会员应妥善保管自己的账户密码等账户信息，会员通过前述方式订立的电子合同对合同各方具有法律约束力，会员不得以其账户密码等账户信息被盗用或其他理由否认已订立的合同的效力或不按照该等合同履行相关义务。</p>

            <p>2、会员根据本协议以及凯拿资产管理的相关规则签署电子合同后，不得擅自修改该合同。</p>

            <p>3、会员不得私自仿制、伪造在凯拿资产管理平台上签订的电子合同或印章，不得用伪造的合同进行招摇撞骗或进行其他非法使用，否则由会员自行承担责任。</p>

            <div class="protocol-title">第四章 风险提示</div>
            <p><span class="font-bold">第十二条 会员了解并认可，任何通过平台进行的交易并不能避免以下风险的产生，会员签署本协议即意味着会员知晓并愿意承以下风险，会员应根据实际情况衡量自身的风险承受能力。凯拿资产管理不能也没有义务为如下风险负责：</span></p>

            <p>1、宏观经济风险：因宏观经济形势变化，可能引起交易价格等方面的异常波动，会员有可能遭受损失。</p>

            <p>2、政策风险：有关法律、法规及相关政策、规则发生变化，可能引起交易价格等方面异常波动，会员有可能遭受损失。</p>

            <p>3、违约风险：因其他交易方无力或无意愿按时足额履约，会员有可能遭受损失。</p>

            <p>4、费率风险：市场费率变化可能对购买或持有产品的实际收益产生影响。</p>

            <p>5、不可抗力因素导致的风险。</p>

            <p>6、因会员的过错导致的任何损失，该过错包括但不限于：决策失误、操作不当、遗忘或泄露密码、密码被他人破解、会员使用的计算机系统被第三方侵入、会员委托他人代理交易时他人恶意或不当操作而造成的损失。</p>

            <p>7、其他因非凯拿资产管理原因所导致的损失和风险。</p>

            <p>第十三条
                凯拿资产管理不对任何会员及/或任何交易提供任何明示、暗示或约定的担保或条件。凯拿资产管理不能也不试图对会员发布的信息进行控制或审核，对该等信息，凯拿资产管理不承担任何形式的证明、鉴定服务。平台仅作为会员之间进行相关交易的居间信息交流平台，为会员提供本协议约定的服务。会员依赖于其独立判断进行交易，应对其作出的行为承担全部责任。</p>

            <p>第十四条 会员确认并知晓本协议并不能揭示会员通过平台进行交易的全部风险及市场的全部情形。会员在做出交易决策前，应全面了解相关交易，谨慎决策并自行承担全部风险。</p>

            <div class="protocol-title">第五章 服务费用</div>
            <p>第十五条
                当会员使用凯拿资产管理服务时，平台会向会员收取相关服务费用。各项服务费用详见会员使用凯拿资产管理服务时平台上所列之收费方式说明。会员同意凯拿资产管理有单方面制定及调整服务费用的权利，会员签署本协议并使用凯拿资产管理提供的服务，即表示会员认可凯拿资产管理有关服务费用的规定，否则会员应当立即停止使用平台账户并按照平台要求经凯拿资产管理审核同意后注销会员账户。会员同意其在账户注销或停止使用平台服务之前，已经在平台所进行进行的交易或其他行为所产生的责任仍由会员承担。</p>

            <p>第十六条
                会员同意并认可凯拿资产管理可依据交易需求与第三方进行合作，会员知晓其在使用平台服务过程中（如充值或取现等）可能需要向第三方（如银行或第三方支付公司等）支付一定的费用，会员愿意承担因此产生的费用，并愿意按照第三方合作主体的相关要求或其与第三方主体签订的相关协议缴纳前述费用，会员愿意接受凯拿资产管理为协助第三方合作主体对会员缴纳相关费用进行提示。</p>

            <div class="protocol-title">第六章 账户安全及管理</div>
            <P>第十七条 会员了解并同意，确保会员账户及密码的机密安全是会员的责任。会员将对利用该会员账户及密码所进行的一切行动及言论，负完全的责任。</P>

            <p><span class="font-bold">第十八条
                会员如发现有第三人冒用或盗用会员账户及密码，或其他任何未经合法授权的情形，应立即以有效的书面方式通知凯拿资产管理，要求凯拿资产管理暂停相关服务，否则由此产生的一切责任由会员本人承担。同时，会员理解凯拿资产管理对会员的请求采取行动需要合理期限，在此之前，会员仍然要对第三人使用其账户的行为后果承担责任。</span></p>

            <p>第十九条
                会员决定不再使用会员账户时，应首先清偿所有应付款项，再将会员账户中的可用款项（如有）全部取现或者向凯拿资产管理发出其它合法的支付指令，并向凯拿资产管理申请注销该会员账户，经其审核同意后可正式注销会员账户。会员不因注销账户而免除因使用账户产生的相关法律责任。</p>

            <p>第二十条
                凯拿资产管理有权基于单方独立判断，在其认为可能发生危害交易安全等情形时，不经通知而先行暂停、中断或终止向会员提供本协议项下的全部或部分会员服务（包括但不限于发布信息、交易管理及收费服务等），并有权利用会员提供的注册资料向会员进行追索，会员同意，在会员的行为造成其他会员损害时，其他会员向凯拿资产管理申请提供会员的注册资料等信息的，凯拿资产管理有权向受损害的其他会员提供前述信息，并协助其进行追索，会员同意，在会员对凯拿资产管理或其他会员造成损失时，凯拿资产管理可以为追索之需要向其他方公开会员的信息并进行失信披露。会员因本条约定的信息披露产生的损失由会员自行承担。</p>
 
            <p>第二十一条 会员同意，会员账户的暂停、中断或终止不代表会员责任的终止，会员仍应对使用平台服务期间的行为承担可能的违约或损害赔偿责任，同时凯拿资产管理仍可保有会员的相关信息。</P>

            <div class="protocol-title">第七章 会员的守法义务及承诺</div>
            <p>第二十二条 会员同意并保证不得利用凯拿资产管理平台进行信用卡套现、洗钱或其他违法、违纪行为，否则应依法承担由此产生的法律责任和后果。因前述行为给凯拿资产管理或其他会员造成损失的，会员应当进行相应赔偿。</p>

            <p>第二十三条 会员同意凯拿资产管理有权依其单独判断删除平台内各类不符合法律政策或不真实或不适当的信息内容而无须通知会员，并无需承担任何责任。
                若会员未遵守以上规定的，凯拿资产管理有权作出独立判断并采取暂停或关闭会员账户等措施，而无需承担任何责任。</p>

            <p>第二十四条
                会员同意，由于会员违反本协议，或违反通过援引并入本协议并成为本协议一部分的文件，或由于会员使用凯拿资产管理服务违反了任何法律或第三方的权利而造成任何第三方进行或发起的任何补偿申请或要求，应当由会员自行解决相关纠纷并承担责任。因前述事项使凯拿资产管理或相关主体产生的费用和损失（包括但不限于律师费、诉讼费、公证费等），会员承诺对平台及其关联方、合作伙伴、董事以及雇员给予全额补偿并使之不受损害。</p>

            <p>第二十五条
                会员承诺，其通过平台上传或发布的信息均真实、合法、有效的，其向凯拿资产管理提交的任何资料均真实、有效、完整、详细、准确。如因违背上述承诺，造成凯拿资产管理或凯拿资产管理其他使用方损失的，会员将承担相应责任。</p>

            <div class="protocol-title">第八章 服务中断或故障</div>
            <p>第二十六条
                会员同意，基于互联网的特殊性，凯拿资产管理不担保服务不会中断，也不担保服务的及时性和/或安全性。平台系统因相关状况无法正常运作，使会员无法使用任何平台服务或使用任何平台服务时受到任何影响时，凯拿资产管理对会员或第三方不负任何责任。</p>

            <div class="protocol-title">第九章 责任范围及限制</div>
            <p>第二十七条
                <span class="font-bold">凯拿资产管理服务的合作单位所提供的服务品质及内容由该合作单位自行负责。</span>平台的内容可能涉及由第三方所有、控制或者运营的其他网站（以下简称“第三方网站”）。凯拿资产管理不能保证也没有义务保证第三方网站上任何信息的真实性和有效性。会员同意接受凯拿资产管理选择的第三方网站，并愿意按照第三方网站的服务协议使用第三方网站。会员确认凯拿资产管理并不对第三方网站的服务或行为做任何承诺与保证，第三方网站的内容、产品、广告和其他任何信息均由会员自行判断并承担风险，会员与第三方网站之间的纠纷由其自行解决，若因会员与第三方网站的纠纷造成凯拿资产管理损失的，会员应当承担相应责任。会员经由凯拿资产管理的使用下载或取得任何资料，应由会员自行考量且自负风险，因资料的下载而导致的任何损失由会员自行承担。</p>

            <p>第二十八条 会员自凯拿资产管理工作人员或经由平台取得的建议或资讯，无论该建议或资讯的表现形式如何，均不构成凯拿资产管理对平台服务的任何保证，前述表现形式包括但不限于文字、语音、视频等。</p>

            <p>第二十九条 会员同意除本协议另有规定外，在任何情况下，凯拿资产管理对本协议所承担的违约赔偿责任总额不超过其向会员收取的平台服务费用总额。</p>

            <div class="protocol-title">第十章 隐私权保护及授权条款</div>
            <p>第三十条
                凯拿资产管理对于会员提供的、自行收集的、经认证的个人信息将按照本协议予以保护、使用或者披露。凯拿资产管理无需会员同意即可向关联实体转让与平台有关的全部或部分权利和义务。未经事先书面同意，会员不得转让其在本协议项下的任何权利和义务。</p>

            <p>第三十一条
                凯拿资产管理按照会员在其平台上的行为自动追踪关于会员的交易或其他资料。会员同意，在不透露会员的隐私资料及不损害会员利益的前提下，凯拿资产管理有权对整个会员数据库进行分析并根据自身需求对会员数据库进行合理使用。</p>

            <p>第三十二条 会员同意凯拿资产管理可使用会员的相关资料以解决与本协议或与履行本协议相关的争议、或对纠纷进行调停。</p>

            <p>第三十三条 凯拿资产管理采用行业标准惯例以保护会员的资料。会员因履行本协议提供给凯拿资产管理的信息，凯拿资产管理不会恶意出售或免费共享给任何第三方。</p>

            <p>第三十四条
                会员授权凯拿资产管理，除法律另有规定之外，将会员提供给凯拿资产管理的信息、享受平台服务产生的信息（包括本协议签署之前提供和产生的信息）以及凯拿资产管理根据本条约定查询、收集的信息，用于凯拿资产管理及其因服务必要委托的合作伙伴为本人提供服务、推荐产品、开展市场调查与信息数据分析。
                会员授权凯拿资产管理，除法律另有规定之外，基于为会员提供更优质服务和产品的目的，向凯拿资产管理因服务必要开展合作的伙伴提供、查询、收集会员的信息。为确保会员信息的安全，凯拿资产管理及其合作伙伴对上述信息负有保密义务，并采取各种措施保证信息安全。
                本条款自本协议签署时生效，具有独立法律效力，不受合同成立与否及效力状态变化的影响。</p>

            <div class="protocol-title">第十一章 知识产权的保护</div>
            <p>第三十五条
                凯拿资产管理平台上所有内容，包括但不限于著作、图片、档案、资讯、资料、平台架构、平台画面的安排、网页设计，均由凯拿资产管理或能够证明权利归属的其他权利人依法拥有其知识产权，包括但不限于商标权、专利权、著作权、商业秘密等。</p>

            <p>第三十六条 尊重知识产权是会员应尽的义务，如有违反，会员应对凯拿资产管理或其他相关权利人承担损害赔偿等法律责任。</p>

            <div class="protocol-title">第十二章 条款的解释、法律适用及争端解决</div>
            <p>第三十七条
                本协议是由会员与凯拿资产管理共同签订的，适用于会员在平台的全部活动。本协议内容包括但不限于协议正文条款及已经发布的或将来可能发布的各类规则，所有条款和规则为协议不可分割的一部分，与协议正文具有同等法律效力。</p>

            <p>第三十八条 凯拿资产管理对本服务协议拥有最终的解释权。</p>

            <p>第三十九条
                本协议签订地为中国上海市黄埔区。因本协议所引起的会员与凯拿资产管理/平台间的任何纠纷或争议，首先应友好协商解决，协商不成的，会员在此完全同意将纠纷或争议提交凯拿资产管理所在地有管辖权的人民法院诉讼解决。</p>

            <div class="protocol-title">第十三章 其他事项</div>
            <p>第四十条
                会员同意并确认本协议以电子合同的方式生成及保存，并同意由凯拿资产管理对本协议的原始数据进行保存，任何其他主体以任何形式出具的与本协议内容及效力有关的文件非经凯拿资产管理书面认可，会员均不得依据其向凯拿资产管理或平台进行权利主张。</p>

            <p>第四十一条 本协议经会员在平台进行点击确认后即生成，并在生成时对协议双方产生法律效力。</p>
        </div>
        <div class="protocol-foot">
            <input type="button" id="numBtn" value="我已阅读" class="procl-link" href="javascript: void(0);" disabled="true">
            <span>(10s)</span>
        </div>
    </div>
    <footer class="backstage-foot">Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
</div>

<form id="read" action="${basepath}/facade/userActivation/goToActive?readed=true" method="post">
	<input name="userId" id="userIdForContact" type="hidden" value="${userId}">
	<input name="securityCode" id="securityCodeForContact" type="hidden" value="${securityCode}">
	<input name="usernameForRead" id="usernameForRead" type="hidden" value="${usernameForRead!}">
	<input name="passwordForContact" id="passwordForContact" type="hidden" value="${passwordForContact!}">
	<input name="passwordForContactSecond" id="passwordForContactSecond" type="hidden" value="${passwordForContactSecond!}">
</form>
</@hb.htmlBase>

