<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="还款计划" jsFiles=["page/repayment/active/activeRepaymentPlan.js"] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "融资管理" curSubMenu = "还款计划" removeExtHeader = false removeExtFooter = false>
<div class="main-container" data-userType=${userType}>
   <div class="whiteBg">
    	<#if userType=="FACTOR">
    	<form id="repayInfoSearch">
            <ul class="searchList clearfix">
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="220">
                        </colgroup>
                        <tbody>
						<tr>
                            <th>融资客户</th>
                            <td>
                                <input type="text" name="financeCompany">
                            </td>
                        </tr>
                        <tr>
                            <th>结清状态</th>
                            <td>
                                <select class="selectWrap" data-role="dropdownlist" style="width:180px" id="settleStatus">
                                	<option value="ALLSTATUS" selected="selected">全部</option>
                                    <option value="SETTLED">已结清</option>
                                    <option value="UNSETTLE">未结清</option>
                                    <option value="OVERDUE">逾期</option>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="220">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>核心企业</th>
                            <td>
                                <input type="text" name="coreCompanyName">
                            </td>
                        </tr>
                        <tr>
                            <th>业务产品</th>
                            <td>
                                <input type="text" name="businessProduct">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="300">
                        </colgroup>
                        <tbody>
						<tr>
                            <th>放款编号</th>
                            <td>
                                <input type="text" style="width:262px;" name="loanNo">
                            </td>
                        </tr>
                        <tr>
                            <th>还 款 日</th>
                            <td>
                                <input type="text" class="datepicker startDate hasIcon" name="begainRepaymentDate" readonly>
                                <em> 至 </em>
                                <input type="text" class="datepicker endDate hasIcon" name="endRepaymentDate" readonly>
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
    	</#if>
    	<#if userType=="CORECOMPANY">
    	<form id="repayInfoSearch">
            <ul class="searchList clearfix">
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="220">
                        </colgroup>
                        <tbody>
						<tr>
                            <th>资金方</th>
                            <td>
                                <input type="text" name="factorCompany">
                            </td>
                        </tr>
                        <tr>
                            <th>结清状态</th>
                            <td>
                                <select class="selectWrap" data-role="dropdownlist" style="width:180px" id="settleStatus">
                                	<option value="ALLSTATUS" selected="selected">全部</option>
                                    <option value="SETTLED">已结清</option>
                                    <option value="UNSETTLE">未结清</option>
                                    <option value="OVERDUE">逾期</option>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="220">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>融资客户</th>
                            <td>
                                <input type="text" name="financeCompany">
                            </td>
                        </tr>
                        <tr>
                            <th>业务产品</th>
                            <td>
                                <input type="text" name="businessProduct">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="300">
                        </colgroup>
                        <tbody>
						<tr>
                            <th>放款编号</th>
                            <td>
                                <input type="text" style="width:262px;" name="loanNo">
                            </td>
                        </tr>
                        <tr>
                            <th>还 款 日</th>
                            <td>
                                <input type="text" class="datepicker startDate hasIcon" name="begainRepaymentDate" readonly>
                                <em> 至 </em>
                                <input type="text" class="datepicker endDate hasIcon" name="endRepaymentDate" readonly>
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
    	</#if>
        <#if userType=="FINACE">
        <form id="repayInfoSearch">
            <ul class="searchList clearfix">
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="220">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>资金方</th>
                            <td>
                                <input type="text" name="factorCompany">
                            </td>
                        </tr>
                        <tr>
                            <th>结清状态</th>
                            <td>
                                <select class="selectWrap" data-role="dropdownlist" style="width:180px" id="settleStatus">
                                	<option value="ALLSTATUS" selected="selected">全部</option>
                                    <option value="SETTLED">已结清</option>
                                    <option value="UNSETTLE">未结清</option>
                                    <option value="OVERDUE">逾期</option>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="220">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>核心企业</th>
                            <td>
                                <input type="text" name="coreCompanyName">
                            </td>
                        </tr>
                        <tr>
                            <th>业务产品</th>
                            <td>
                                <input type="text" name="businessProduct">
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
                            <th>放款编号</th>
                            <td>
                                <input type="text" style="width:262px;" name="loanNo">
                            </td>
                        </tr>
                        <tr>
                            <th>还 款 日</th>
                            <td>
                                <input type="text" class="datepicker startDate hasIcon" name="begainRepaymentDate" readonly>
                                <em> 至 </em>
                                <input type="text" class="datepicker endDate hasIcon" name="endRepaymentDate" readonly>
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
        </#if>
        <#if userType=="CANA">
        <form id="repayInfoSearch">
            <ul class="searchList clearfix">
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="220">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>融资客户</th>
                            <td>
                                <input type="text" name="financeCompany">
                            </td>
                        </tr>
                        <tr>
                            <th>放款编号</th>
                            <td>
                                <input type="text" style="width:180px;" name="loanNo">
                            </td>
                        </tr>
                        <tr>
                            <th>结清状态</th>
                            <td>
                                <select class="selectWrap" data-role="dropdownlist" style="width:180px" id="settleStatus">
                                	<option value="ALLSTATUS" selected="selected">全部</option>
                                    <option value="SETTLED">已结清</option>
                                    <option value="UNSETTLE">未结清</option>
                                    <option value="OVERDUE">逾期</option>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="220">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>资金方</th>
                            <td>
                                <input type="text" name="factorCompany">
                            </td>
                        </tr>
                        <tr>
                            <th>业务产品</th>
                            <td>
                                <input type="text" name="businessProduct">
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
                                <input type="text" style="width:264px" name="coreCompanyName">
                            </td>
                        </tr>
						<tr>
                            <th>还 款 日</th>
                            <td>
                                <input type="text" class="datepicker startDate hasIcon" name="begainRepaymentDate" readonly>
                                <em> 至 </em>
                                <input type="text" class="datepicker endDate hasIcon" name="endRepaymentDate" readonly>
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
        </#if>
        <div class="repayment-nav">
            <ul>
                <li class="repayment-nav-active">
                    <a class="repayment-navlink" href="javascript:void(0);">还款计划</a>
                </li>
                <li>
                    <a class="repayment-navlink" href="javascript:void(0);">费用列表</a>
                </li>
            </ul>
        </div>
        <div class="repayment-grid" id="repayment-plan">
            <div class="finance-grid" id="manualGrid-plan" style="margin-top:15px;"></div>
        </div>
        <div class="repayment-grid hidden" id="repayment-cost">
            <div class="finance-grid" id="manualGrid-cost" style="margin-top:15px;"></div>
        </div>
    </div>
</div>
<!--权限配置-->
<script>
	var activeRepaymentAuth = ${authorizeKey("FM_ACTIVE_REPAYMENT_RULE")?string("true","false")};
</script>
</@hb.htmlBase>