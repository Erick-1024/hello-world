<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="选择放款信息" jsFiles=["page/repayment/input/planLoanInfoList.js"] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "融资管理" curSubMenu = "还款计划录入" removeExtHeader = false removeExtFooter = false>
	<div class="main-container">
	    <div class="whiteBg">
	        <form id="repayInfoSearch">
		        <ul class="searchList clearfix">
	                <li>
	                    <table class="searchTab">
	                        <colgroup>
	                            <col width="70">
	                            <col width="300">
	                        </colgroup>
	                        <tbody>
	                        <tr>
	                            <th>融资客户</th>
	                            <td>
	                                <input type="text" style="width:264px;" name="financeCompanyName">
	                            </td>
	                        </tr>
	                        <tr>
	                            <th>业务产品</th>
	                            <td>
	                                <input type="text" style="width:264px;"  name="businessProduct">
	                            </td>
	                        </tr>
	                        
	                        </tbody>
	                    </table>
	                </li>
	                <li>
	                    <table class="searchTab">
	                        <colgroup>
	                            <col width="80">
	                            <col width="300">
	                        </colgroup>
	                        <tbody>
	                        <tr>
	                            <th>核心企业</th>
	                            <td>
	                                <input type="text" style="width:264px;" name="coreCompanyName">
	                            </td>
	                        </tr>
	                        <tr>
	                            <th>放 款 日</th>
	                            <td>
	                                <input type="text" class="datepicker startDate hasIcon" readonly name="loanStartDate">
	                                <em> 至 </em>
	                                <input type="text" class="datepicker endDate hasIcon" readonly name="loanEndDate">
	                            </td>
	                        </tr>
	                        </tbody>
	                    </table>
	                </li>
	                <li>
	                    <table class="searchTab">
	                        <colgroup>
	                            <col width="80">
	                            <col width="220">
	                        </colgroup>
	                        <tbody>
	                        <tr>
	                            <th>业务合同号</th>
	                            <td>
	                                <input type="text" style="width:264px;" name="businessContractNo">
	                            </td>
	                        </tr>
	                        <tr>
	                            <th>到 期 日</th>
	                            <td>
	                                <input type="text" class="datepicker startDate hasIcon" readonly name="dueStartDate">
	                                <em> 至 </em>
	                                <input type="text" class="datepicker endDate hasIcon" readonly name="dueEndDate">
	                            </td>
	                        </tr>
	                        </tbody>
	                    </table>
	                </li>
	                <li>
	                    <a class="form-search-link repay-search"  href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
	                </li>
	            </ul>
	        </form>
	        <div id="manual-repayGridWrap">
				<div class="finance-grid" id="manual-repayGrid" style="margin-top:15px;"></div>
	        </div>
	        <form id="planManualInputSelect" method="post" class="hidden">
				<input name="businessMode" value=${businessMode}>
				<input name="inputMethod" value=${inputMethod}>
			<form>
	    </div>
	</div>
</@hb.htmlBase>