<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="入池" jsFiles=["page/asset/assetpool/enter.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] curTopMenu = "证券化发行管理" curSubMenu = "资产池管理" removeExtHeader = false removeExtFooter = false>
<a class="message-pop" href="javascript:void(0);" style="display:none;"></a>
<div class="pro-bg" style="background: #fff;">
    <div class="pro-box-bg">
    	<input type="hidden" id = "specialProgramId" value = ${assetpoolListDTO.id!} />
        <#include "specialprogram.ftl">
        <div class="pro-title" id="nav-three"><span class="pro-title-left">合格筛选标准</span><span class="pro-title-right">折叠</span></div>
        <div class="client-hide-bg">
            <table class="client-tb">
                <colgroup>
                    <col width="150">
                    <col width="300">
                    <col width="150">
                    <col width="300">
                    <col width="150">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>业务合同号</th>
                    <td><input type="text" value="" name="businessContractNo" class="" style="width:242px;"></td>
                    <th>业务产品</th>
                    <td>
                        <select class="" style="width:242px;"  data-role="dropdownlist" id="businessProduct">
                            <option value="">全部</option>
                            <#list businessProductArry as businessProduct>
                            <option value="${businessProduct.name()}">${businessProduct.desc()}</option>
                            </#list>
                        </select>
                    </td>
                    <th>借款人名称</th>
                    <td><input type="text" value="" name="customerName" class="" style="width:242px;"></td>
                </tr>
                <tr>
                    <th>借款人经济类型</th>
                    <td><input type="text" value="" name="customerEconomicCategory" class="" style="width:242px;"></td>
                    <th>交易对手名称</th>
                    <td><input type="text" value="" name="counterparty" class="" style="width:242px;"></td>
                    <th>交易对手经济类型</th>
                    <td><input type="text" value="" name="counterpartyEconomicCategory" class="" style="width:242px;"></td>
                </tr>
                <tr>
                    <th>借款人所在地区</th>
                    <td><input type="text" value="" name="customerCity" class="" style="width:242px;"></td>
                    <th>借款人所属行业</th>
                    <td><input type="text" value="" name="customerIndustry" class="" style="width:242px;"></td>
                    <th>交易对手所在地区</th>
                    <td><input type="text" value="" name="counterpartyCity" class="" style="width:242px;"></td>
                </tr>
                <tr>
                    <th>交易对手所属行业</th>
                    <td><input type="text" value="" name="counterpartyIndustry" class="" style="width:242px;"></td>
                    <th>交易对手评级</th>
                    <td><input type="text" value="" name="counterpartyRating" class="" style="width:242px;"></td>
                    <th>起息日</th>
                    <td>
                        <input type="text" value="" name="loanStartDate" class="datepicker startDate hasIcon" style="width:110px;">
                        <em>至</em>
                        <input type="text" value="" name="loanEndDate" class="datepicker endDate hasIcon" style="width:110px;">
                    </td>
                </tr>
                <tr>
                    <th>结息日</th>
                    <td>
                        <input type="text" value="" name="settleStartDate" class="datepicker startDate hasIcon" style="width:110px;">
                        <em>至</em>
                        <input type="text" value="" name="settleEndDate" class="datepicker endDate hasIcon" style="width:110px;">
                    </td>
                    <th>到期日</th>
                    <td>
                        <input type="text" value="" name="dueStartDate" class="datepicker startDate hasIcon" style="width:110px;">
                        <em>至</em>
                        <input type="text" value="" name="dueEndDate" class="datepicker endDate hasIcon" style="width:110px;">
                    </td>
                    <th>还款付息方式</th>
                    <td><input type="text" value="" name="repaymentMethod" class="" style="width:242px;"></td>
                </tr>
                <tr>
                    <th>保证人</th>
                    <td><input type="text" value="" name="guaranteeInfo" class="" style="width:242px;"></td>
                    <th>保证企业</th>
                    <td><input type="text" value="" name="guaranteeCompanyInfo" class="" style="width:242px;"></td>
                    <th>担保品编号</th>
                    <td><input type="text" value="" name="guaranteeGoodsNo" class="" style="width:242px;"></td>
                </tr>
                <tr>
                    <th>应收账款金额</th>
                    <td>
                        <input type="text" value="" name="invoiceStartAmount" class="moneyNum" style="width:110px;">
                        <em>至</em>
                        <input type="text" value="" name="invoiceEndAmount" class="moneyNum" style="width:110px;">
                    </td>
                    <th>应收账款余额</th>
                    <td>
                        <input type="text" value="" name="invoiceStartBalance" class="moneyNum" style="width:110px;">
                        <em>至</em>
                        <input type="text" value="" name="invoiceEndBalance" class="moneyNum" style="width:110px;">
                    </td>
                    <th>融资金额</th>
                    <td>
                        <input type="text" value="" name="financeStartAmount" class="moneyNum" style="width:110px;">
                        <em>至</em>
                        <input type="text" value="" name="financeEndAmount" class="moneyNum" style="width:110px;">
                    </td>
                </tr>
                <tr>
                    <th>融资余额</th>
                    <td>
                        <input type="text" value="" name="financeStartBalance" class="moneyNum" style="width:110px;">
                        <em>至</em>
                        <input type="text" value="" name="financeEndBalance" class="moneyNum" style="width:110px;">
                    </td>
                    <th>年化利率(%)</th>
                    <td>
                        <input type="text" value="" name="annualInterestRateStart" class="" style="width:110px;">
                        <em>至</em>
                        <input type="text" value="" name="annualInterestRateEnd" class="" style="width:110px;">
                    </td>
                    <th>应收利息</th>
                    <td>
                        <input type="text" value="" name="accountInterestStart" class="moneyNum" style="width:110px;">
                        <em>至</em>
                        <input type="text" value="" name="accountInterestEnd" class="moneyNum" style="width:110px;">
                    </td>
                </tr>
                <tr>
                    <th>逾期管理费率(%)</th>
                    <td>
                        <input type="text" value="" name="penaltyRateStart" class="" style="width:110px;">
                        <em>至</em>
                        <input type="text" value="" name="penaltyRateEnd" class="" style="width:110px;">
                    </td>
                    <th>应还总金额</th>
                    <td>
                        <input type="text" value="" name="accountTotalAmountStart" class="moneyNum" style="width:110px;">
                        <em>至</em>
                        <input type="text" value="" name="accountTotalAmountEnd" class="moneyNum" style="width:110px;">
                    </td>
                    <th>借款人授信额度</th>
                    <td>
                        <input type="text" value="" name="creditLimitStart" class="moneyNum" style="width:110px;">
                        <em>至</em>
                        <input type="text" value="" name="creditLimitEnd" class="moneyNum" style="width:110px;">
                    </td>
                </tr>
                <tr>
                    <th>借款人授信余额</th>
                    <td>
                        <input type="text" value="" name="creditBalanceStart" class="moneyNum" style="width:110px;">
                        <em>至</em>
                        <input type="text" value="" name="creditBalanceEnd" class="moneyNum" style="width:110px;">
                    </td>
                    <th>交易对手非承保额度</th>
                    <td>
                        <input type="text" value="" name="counterpartyLimitStart" class="moneyNum" style="width:110px;">
                        <em>至</em>
                        <input type="text" value="" name="counterpartyLimitEnd" class="moneyNum" style="width:110px;">
                    </td>
                    <th>交易对手非承保余额</th>
                    <td>
                        <input type="text" value="" name="counterpartyBalanceStart" class="moneyNum" style="width:110px;">
                        <em>至</em>
                        <input type="text" value="" name="counterpartyBalanceEnd" class="moneyNum" style="width:110px;">
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><a class="form-search-btn" href="javascript:void(0);" style="margin-left:140px;" id="searchBtn"><i class="searchIcon"></i>搜索</a></td>
                </tr>
                </tbody>
            </table>

        </div>

        <div class="monitor-grid" id="monitorList-grid"></div>
        <div class="" style="height:20px;"></div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" href="javascript:void(0);" id="enterPool">提交</a>
            <a class="default-link back-link" href="javascript:history.back(-1);">返回</a>
        </div>
    </div>

</div>
<script id="tipBox_template" type="text/x-kendo-template">
    <div id="tip-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <span id="notice-icon" class="dlg-notice"></span>
            <span id="notice-content" class="notice-content"></span>
        </div>
    </div>
</script>
</@hb.htmlBase>