<#import "/common/htmlBase.ftl" as hb>
<#-- jsFiles 填写需要的js文件，以js开头的是静态服务器的js，其他是本项目的js，cssFiles 是静态服务器css，localCssFiles 是本项目的css文件（填写除公共路径以外的路径） -->
<@hb.htmlBase title="Cana" jsFiles=[] cssFiles=[] localCssFiles=[] removeExtFooter = true>
	<div class="backstage-mainWrap">
	    <div class="commonTips-wrap">
	        <div class="commonTips-title">还款方式</div>
	        <div class="commonTips-content">
	            <div class="commonTips-head" style="text-align:left;margin-left:200px">
	                您好！如需还款请直接将资金转款至：<br/>
	        		<span style="margin-left:30px">账户名称：<span style="color:red">${accountName!}</span></span><br/>
					<span style="margin-left:30px">还款账号：<span style="color:red">${(formatBankAccountNo(accountNo))!}</span></span><br/>
					<span style="margin-left:30px">收款行地址：中信银行上海静安支行</span><br/>
					<span style="color:red">提醒：为了提高转账成功率，请选择网银平台上的大额或同城通道进行转账</span><br/>
					当日24:00系统自动按到期日进行扣款还款。后期将逐步开放更多还款途径。
	            </div>
	            <div class="commonTips-foot">
	                <a class="default-link confirm-link" href="${basepath!}/loanInfo/manage/gotoLoanInfoManage">查看融资信息</a>
	            </div>
	        </div>
	    </div>
	    <footer class="backstage-foot">Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
	</div>
</@hb.htmlBase>