<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="逾期列表" jsFiles=["page/repayment/active/overdueRepaymentPlan.js"] cssFiles=["css/finance.css"] localCssFiles=[] curTopMenu = "融资管理" curSubMenu = "逾期列表">
<div class="main-container" data-userType=${userType}>
    <div class="whiteBg">
    	<#if userType=="FACTOR">
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
	                                <input type="text" style="width:264px" name="financeCompany">
	                            </td>
	                        </tr>
	                        <tr>
	                            <th>业务产品</th>
	                            <td>
	                                <input type="text" style="width:264px" name="businessProduct">
	                            </td>
	                        </tr>
	                        <tr>
	                            <th>还 款 日</th>
	                            <td>
	                                <input type="text" name="" class="datepicker startDate hasIcon" readonly>
	                                <em> 至 </em>
	                                <input type="text" name="" class="datepicker endDate hasIcon" readonly>
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
                              	  <input type="text" name="coreCompanyName">
                            	</td>
                       		 </tr>
	                         <tr>
	                            <th>平台流水号</th>
	                            <td>
	                                <input type="text" name="loanId">
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
		                            <th>放款编号</th>
		                            <td>
		                                <input type="text" name="loanNo">
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
	                            <col width="300">
	                        </colgroup>
	                        <tbody>
	                        <tr>
	                        	<th>资金方</th>
	                            <td>
	                                <input type="text" style="width:264px" name="factorCompany">
	                            </td>
	                        </tr>
	                        <tr>
	                            <th>业务产品</th>
	                            <td>
	                                <input type="text" style="width:264px" name="businessProduct">
	                            </td>
	                        </tr>
	                        <tr>
	                            <th>还 款 日</th>
	                            <td>
	                                <input type="text" name="" class="datepicker startDate hasIcon" readonly>
	                                <em> 至 </em>
	                                <input type="text" name="" class="datepicker endDate hasIcon" readonly>
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
                            	<th>融资客户</th>
	                            <td>
	                                <input type="text"  name="financeCompany">
	                            </td>
                       		 </tr>
	                         <tr>
	                            <th>平台流水号</th>
	                            <td>
	                                <input type="text" name="loanId">
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
		                            <th>放款编号</th>
		                            <td>
		                                <input type="text" name="loanNo">
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
	                            <col width="300">
	                        </colgroup>
	                        <tbody>
	                        <tr>
	                            <th>资金方</th>
	                            <td>
	                                <input type="text" style="width:264px" name="factorCompany">
	                            </td>
	                        </tr>
	                        <tr>
	                            <th>业务产品</th>
	                            <td>
	                                <input type="text" style="width:264px" name="businessProduct">
	                            </td>
	                        </tr>
	                        <tr>
	                            <th>还 款 日</th>
	                            <td>
	                                <input type="text" name="" class="datepicker startDate hasIcon" readonly>
	                                <em> 至 </em>
	                                <input type="text" name="" class="datepicker endDate hasIcon" readonly>
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
                            	<th>核心企业</th>
                            	<td>
                              	  <input type="text" name="coreCompanyName">
                            	</td>
                       		 </tr>
	                         <tr>
	                            <th>平台流水号</th>
	                            <td>
	                                <input type="text" name="loanId">
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
	                            <th>放款编号</th>
	                            <td>
	                                <input type="text" name="loanNo">
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
	                            <col width="300">
	                        </colgroup>
	                        <tbody>
	                        <tr>
	                            <th>融资客户</th>
	                            <td>
	                                <input type="text" style="width:264px;" name="financeCompany">
	                            </td>
	                        </tr>
	                        <tr>
	                            <th>放款编号</th>
	                            <td>
	                                <input type="text" name="loanNo" style="width:264px;">
	                            </td>
	                        </tr>
	                        <tr>
	                            <th>还 款 日</th>
	                            <td>
	                                <input type="text" name="" class="datepicker startDate hasIcon" readonly>
	                                <em> 至 </em>
	                                <input type="text" name="" class="datepicker endDate hasIcon" readonly>
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
	                            <th>平台流水号</th>
	                            <td>
	                                <input type="text" name="loanId">
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
	var activeRepaymentAuth = ${authorizeKey("FM_OVERDUE_ACTIVE_REPAYMENT")?string("true","false")};
</script>
</@hb.htmlBase>
