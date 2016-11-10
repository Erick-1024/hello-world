<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="流水明细" jsFiles=["page/account/trade/listTradeRecord.js","common/dateutil.js"] cssFiles=["css/account.css"] localCssFiles=[] 
	curTopMenu = "账户管理" curSubMenu = "流水明细" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
	<section class="whiteBg">
		<form id="companyInfoSearch">
            <ul class="searchList clearfix">
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="300">
                        </colgroup>
                        <tbody>
	                        <tr>
	                            <th>账户名称</th>
	                            <td>
	                            	<input type="text" name="accountName" id="accountName" style="width: 264px;">
	                            </td>
	                        </tr>
	                        <tr>
	                            <th>交易户名</th>
	                            <td>
	                            	<input type="text" name="oppositeAccountName" id="oppositeAccountName" style="width: 264px;">
	                            </td>
	                        </tr>
	                        <tr>
                            <th>交易时间</th>
	                            <td>
	                            	<input type="text" name="beginTime" id="beginTime" class="datepicker startDate hasIcon" readonly>
	                                <em> 至 </em>
	                                <input type="text" name="endTime" id="endTime" class="datepicker startDate hasIcon" readonly>
	                            </td>
                        	</tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="240">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>银行账号</th>
                            <td>
                            	<input type="text" name="accountNo" id="accountNo" class="bankCard" style="width: 185px;">
                            </td>
                        </tr>
                        <tr>
                            <th>交易账号</th>
                            <td>
                            	<input type="text" name="oppositeAccountNo" id="oppositeAccountNo" style="width: 185px;">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="70">
                            <col width="240">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>账户类型</th>
                            <td>
                                <select class="selectWrap" name="accountType" id="accountType" data-role="dropdownlist">
                                    <option value="">全部</option>
                                    <#if accountTypeList??>
	                                    <#list accountTypeList as accountType>
	                                    	<option value=${accountType.name()}>${accountType.desc()}</option>
										</#list>
									</#if>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>交易类型</th>
                            <td>
                                <select class="selectWrap" name="accountTradeType" id="accountTradeType" data-role="dropdownlist">
                                    <option value="">全部</option>
									<#if accountTradeTypeList??>
	                                    <#list accountTradeTypeList as accountTradeType>
	                                    	<option value=${accountTradeType.name()}>${accountTradeType.desc()}</option>
										</#list>
									</#if>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                </li>
            </ul>
		</form>
	    <div id="tradeRecordGridWrap">
	       	<div class="accountManageGrid"></div>
    	</div>
	</section>
</div>
<script>
	var userType = "${userType}";
</script>
</@hb.htmlBase>