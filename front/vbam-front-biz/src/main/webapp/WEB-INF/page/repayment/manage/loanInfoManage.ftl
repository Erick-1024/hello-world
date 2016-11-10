<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="融资信息管理" jsFiles=["page/repayment/manage/loanInfoManage.js"] cssFiles=["css/finance.css"] localCssFiles=[] 
	curTopMenu = "融资管理" curSubMenu = "融资信息管理" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
    	<#if userType=="FINACE">
    	<form>
            <ul class="searchList clearfix">
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="80">
                            <col width="300">
                        </colgroup>
                        <tbody>
                       	<tr>
                            <th>资金方</th>
                            <td>
                                <input type="text" style="width:264px;" name="factorCompany">
                            </td>
                        </tr>
                       	<tr>
                            <th>平台流水号</th>
                            <td>
                                <input type="text" style="width:264px;" name="loanId">
                            </td>
                        </tr>
                        <tr>
                            <th>还款账号</th>
                            <td>
                                <select id="accountNoStatus" class="selectWrap" data-role="dropdownlist" style="width:264px">
                                <option value="ALL" selected="selected">全部</option>
                                <option value="EXIST">有</option>
                                <option value="NOTEXIST">无</option>
                            	</select>
                            </td>
                        </tr>
                        <tr>
                            <th>放 款 日</th>
                            <td>
                             	<input type="text" class="datepicker startDate hasIcon" name="loanStartDate" readonly>
                                <em> 至 </em>
                                <input type="text" class="datepicker endDate hasIcon" name="loanEndDate" readonly>
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
                            <th>业务合同号</th>
                            <td>
                                <input type="text" style="width:264px;" name="businessContractNo">
                            </td>
                        </tr>
                        <tr>
                            <th>还款状态</th>
                            <td>
                                <select class="selectWrap" data-role="dropdownlist" style="width:264px" id="settleStatus">
                                    <option value="">全部</option>
                                    <option value="UNSETTLE">未结清</option>
                                    <option value="SETTLED">已结清</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>到 期 日</th>
                            <td>
                                <input type="text" class="datepicker startDate hasIcon" name="dueStartDate" readonly>
                                <em> 至 </em>
                                <input type="text" class="datepicker endDate hasIcon" name="dueEndDate" readonly>
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
                            <th>业务产品</th>
                            <td>
                                <input type="text" style="width:264px;" name="businessProduct">
                            </td>
                        </tr>
                        <tr>
                            <th>外部帐户名称</th>
                            <td>
                                <input type="text" style="width:264px;" name="outCustomerName">
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
        <#if userType=="FACTOR">
        <form>
            <ul class="searchList clearfix">
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
                                <input type="text" style="width:264px;" name="financeCompany">
                            </td>
                        </tr>
                        <tr>
                            <th>平台流水号</th>
                            <td>
                                <input type="text" style="width:264px;" name="loanId">
                            </td>
                        </tr>
                        <tr>
                            <th>还款账号</th>
                            <td>
                                <select id="accountNoStatus" class="selectWrap" data-role="dropdownlist" style="width:264px">
                                <option value="ALL" selected="selected">全部</option>
                                <option value="EXIST">有</option>
                                <option value="NOTEXIST">无</option>
                            	</select>
                            </td>
                        </tr>
                        <tr>
                            <th>放 款 日</th>
                            <td>
                             	<input type="text" class="datepicker startDate hasIcon" name="loanStartDate" readonly>
                                <em> 至 </em>
                                <input type="text" class="datepicker endDate hasIcon" name="loanEndDate" readonly>
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
                            <th>业务合同号</th>
                            <td>
                                <input type="text" style="width:264px;" name="businessContractNo">
                            </td>
                        </tr>
                        <tr>
                            <th>还款状态</th>
                            <td>
                                <select class="selectWrap" data-role="dropdownlist" style="width:264px" id="settleStatus">
                                    <option value="">全部</option>
                                    <option value="UNSETTLE">未结清</option>
                                    <option value="SETTLED">已结清</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>到 期 日</th>
                            <td>
                                <input type="text" class="datepicker startDate hasIcon" name="dueStartDate" readonly>
                                <em> 至 </em>
                                <input type="text" class="datepicker endDate hasIcon" name="dueEndDate" readonly>
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
                            <th>外部帐户名称</th>
                            <td>
                                <input type="text" style="width:264px;" name="outCustomerName">
                            </td>
                        </tr>
                        <tr>
                            <th>业务产品</th>
                            <td>
                                <input type="text" style="width:264px;" name="businessProduct">
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
        <form>
            <ul class="searchList clearfix">
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="80">
                            <col width="300">
                        </colgroup>
                        <tbody>
                        <tr>
                        	<th>资金方</th>
                            <td>
                                <input type="text" style="width:264px;" name="factorCompany">
                            </td>
                        </tr>
                        <tr>
                            <th>平台流水号</th>
                            <td>
                                <input type="text" style="width:264px;" name="loanId">
                            </td>
                        </tr>
                        <tr>
                            <th>还款账号</th>
                            <td>
                                <select id="accountNoStatus" class="selectWrap" data-role="dropdownlist" style="width:264px">
                                <option value="ALL" selected="selected">全部</option>
                                <option value="EXIST">有</option>
                                <option value="NOTEXIST">无</option>
                            	</select>
                            </td>
                        </tr>
                        <tr>
                            <th>放 款 日</th>
                            <td>
                             	<input type="text" class="datepicker startDate hasIcon" name="loanStartDate" readonly>
                                <em> 至 </em>
                                <input type="text" class="datepicker endDate hasIcon" name="loanEndDate" readonly>
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
                                <input type="text" style="width:264px;" name="financeCompany">
                            </td>
                        </tr>
                        <tr>
                            <th>业务合同号</th>
                            <td>
                                <input type="text" style="width:264px;" name="businessContractNo">
                            </td>
                        </tr>
                        <tr>
                            <th>还款状态</th>
                            <td>
                                <select class="selectWrap" data-role="dropdownlist" style="width:264px" id="settleStatus">
                                    <option value="">全部</option>
                                    <option value="UNSETTLE">未结清</option>
                                    <option value="SETTLED">已结清</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>到 期 日</th>
                            <td>
                                <input type="text" class="datepicker startDate hasIcon" name="dueStartDate" readonly>
                                <em> 至 </em>
                                <input type="text" class="datepicker endDate hasIcon" name="dueEndDate" readonly>
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
                            <th>外部帐户名称</th>
                            <td>
                                <input type="text" style="width:264px;" name="outCustomerName">
                            </td>
                        </tr>
                        <tr>
                            <th>业务产品</th>
                            <td>
                                <input type="text" style="width:264px;" name="businessProduct">
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
        <form>
            <ul class="searchList clearfix">
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="80">
                            <col width="300">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>资金方</th>
                            <td>
                                <input type="text" style="width:264px;" name="factorCompany">
                            </td>
                        </tr>
                        <tr>
                            <th>平台流水号</th>
                            <td>
                                <input type="text" style="width:264px;" name="loanId">
                            </td>
                        </tr>
                        <tr>
                            <th>还款账号</th>
                            <td>
                                <select id="accountNoStatus" class="selectWrap" data-role="dropdownlist" style="width:264px">
                                <option value="ALL" selected="selected">全部</option>
                                <option value="EXIST">有</option>
                                <option value="NOTEXIST">无</option>
                            	</select>
                            </td>
                        </tr>
                        <tr>
                            <th>放 款 日</th>
                            <td>
                             	<input type="text" class="datepicker startDate hasIcon" name="loanStartDate" readonly>
                                <em> 至 </em>
                                <input type="text" class="datepicker endDate hasIcon" name="loanEndDate" readonly>
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
                                <input type="text" style="width:264px;" name="financeCompany">
                            </td>
                        </tr>
                        <tr>
                            <th>业务合同号</th>
                            <td>
                                <input type="text" style="width:264px;" name="businessContractNo">
                            </td>
                        </tr>
                        <tr>
                            <th>还款状态</th>
                            <td>
                                <select class="selectWrap" data-role="dropdownlist" style="width:264px" id="settleStatus">
                                    <option value="">全部</option>
                                    <option value="UNSETTLE">未结清</option>
                                    <option value="SETTLED">已结清</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>到 期 日</th>
                            <td>
                                <input type="text" class="datepicker startDate hasIcon" name="dueStartDate" readonly>
                                <em> 至 </em>
                                <input type="text" class="datepicker endDate hasIcon" name="dueEndDate" readonly>
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
                            <th>业务产品</th>
                            <td>
                                <input type="text" style="width:264px;" name="businessProduct">
                            </td>
                        </tr>
                        <tr>
                            <th>外部帐户名称</th>
                            <td>
                                <input type="text" style="width:264px;" name="outCustomerName">
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
        <#if authorizeKey("FM_REPAYMENTINFO_MANAGE_PLANSELECT")>
	        <div style="margin-top:15px;">
	            <a class="form-add-link" href="${basepath}/repayment/plan/input/planSelect">录入还款计划</a><span id="userType" data-userType=${userType}></span>
	        </div>
        </#if>
        <div id="repayManage-gridWrap" data-usertype=${userType!}>
	        <div class="finance-grid" id="repayManage-grid" style="margin-top:15px;"></div>
        </div>
    </section>
</div>
<!--权限配置-->
<script>
	var planDetailAuth = ${authorizeKey("FM_REPAYMENTINFO_MANAGE_PLANDETAIL")?string("true","false")};
	var setAccountNoAuth = ${authorizeKey("FM_REPAYMENTINFO_MANAGE_SETACCOUNTNO")?string("true","false")};
	var repaymetDetailsHistoryAuth = ${authorizeKey("FM_REPAYMENTINFO_MANAGE_REPAYMENTDETAILSHISTORY")?string("true","false")};
	var offlineRepaymentAuth = ${authorizeKey("FM_REPAYMENTINFO_MANAGE_OFFLINEREPAYMENT")?string("true","false")};
	var adjustMentAuth = ${authorizeKey("FM_REPAYMENTINFO_MANAGE_ADJUSTMENT")?string("true","false")};
	var activeRepaymentAuth = ${authorizeKey("FM_REPAYMENTINFO_MANAGE_ACTIVEREPAYMENT")?string("true","false")};
</script>
<!--设置弹窗模板-->
<script id="template-setAccount" type="text/x-kendo-template">
	<div style="width:600px;height:100px;text-align:center;line-height:40px;" id="accountWaitHtml">
		<span style="">加载中...</span>
	</div>
    <div id="setAccount" class="dlg-wrapper hidden">
        <table class="dlg-table">
            <colgroup>
                <col width="200px">
                <col width="400px">
            </colgroup>
            <tbody>
            <tr>
                <td>资金方<span id="loanno" hidden></span></td>
                <td id="factorCompany"></td>
            </tr>
            <tr>
                <td>融资客户</td>
                <td id="financeCompany"></td>
            </tr>
            <tr>
                <td>监管账号</td>
                <td>
                    <select class="selectWrap" data-role="dropdownlist" name="accountNo" id="accountNo" style="width:180px">
	               	</select>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="dlg-wrapper-foot">
            <a id="saveAccount" class="default-link confirm-link" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
    <!--没有可用的监管账号-->
    <div id="noAccount" class="dlg-wrapper hidden">
        <div class="dlg-wrapper-content" style="text-align: center;">
            <div>您还没有还款账号。<a class="repayment-link" href="${basepath}/account/supervision/create">建立还款账号</a></div>
        </div>
        <div class="dlg-wrapper-foot">
            <a class="default-link back-link" href="javascript:void(0);">关闭</a>
        </div>
    </div>
</script>

<!--历史还款明细模板-->
<script id="template-repayDetail" type="text/x-kendo-template">

	<div style="width:600px;height:100px;text-align:center;line-height:40px;" id="repayWaitHtml">
		<span style="">加载中...</span>
	</div>
   <!--没有历史还款明细-->
    <div id="noRepaymentDetails" class="dlg-wrapper hidden">
        <div class="dlg-wrapper-content" style="text-align: center;">
            <div>没有历史还款明细</div>
        </div>
        <div class="dlg-wrapper-foot">
            <a class="default-link back-link" href="javascript:void(0);">关闭</a>
        </div>
    </div>
    <div id="haveRepaymentDetails" class="dlg-wrapper hidden">
        <div class="tableTpl-wrap">
            <div class="tableTpl-head">
                <div class="tableTpl-head-wrap">
                    <table class="tableTpl-grid">
                        <colgroup>
                            <col style="width:150px">
                            <col style="width:120px">
                            <col>
                            <col style="width:150px">
                        </colgroup>
                        <thead>
                        <tr>
                            <th>
                                <span class="tabTpl-link">操作时间</span>
                            </th>
                            <th>
                                <span class="tabTpl-link">还款方式</span>
                            </th>
                            <th>
                                <span class="tabTpl-link">金额明细</span>
                            </th>
                            <th>
                                <span class="tabTpl-link">线下还款日</span>
                            </th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <div class="tableTpl-content">
                <table class="tableTpl-grid">
                    <colgroup>
                        <col style="width:150px">
                        <col style="width:120px">
                        <col>
                        <col style="width:150px">
                    </colgroup>
                    <tbody id="repaymentDetailsHistory">
                    </tbody>
                </table>
            </div>
        </div>
        <div class="dlg-wrapper-foot">
            <a class="default-link back-link close-window" href="javascript:void(0);">返回</a>
        </div>
    </div>
</script>
</@hb.htmlBase>