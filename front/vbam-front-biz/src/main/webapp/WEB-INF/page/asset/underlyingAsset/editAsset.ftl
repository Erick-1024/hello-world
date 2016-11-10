<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="基础资产修改" jsFiles=["page/asset/underlyingAsset/editAsset.js","page/asset/underlyingAsset/assetFormValidatorRules.js","page/asset/underlyingAsset/specialProgramPop.js","common/formValidator.js","common/cana.util.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] curTopMenu = "证券化发行管理" curSubMenu = "基础资产管理" removeExtHeader = false removeExtFooter = false>
<a class="limit-add-btn" href="javascript:void(0);" style="display:none;"></a>
<a class="limit-next-btn" href="javascript:void(0);" style="display:none;"></a>
<a class="message-pop" href="javascript:void(0);" style="display:none;"></a>
<div class="pro-bg" style="background: #fff;">
    <form class="" id="editAssetForm">
        <div class="left-nav">
            <ul id="client-nav">
                <li class="current"><a href="#nav-one">基本信息&ensp;▶</a></li>
                <li><a href="#nav-two">额度信息&ensp;▶</a></li>
                <li><a href="#nav-three">应收账款&ensp;▶</a></li>
                <li><a href="#nav-four">融资信息&ensp;▶</a></li>
                <li><a href="#nav-five">专项计划&ensp;▶</a></li>
            </ul>
        </div>

        <div class="client-bg">
            <!--弹窗触发按钮 -->
            <a class="status-normal status-chk limit-add-btn" href="javascript:void(0);" style="display:none;"></a>
            <a class="status-normal status-chk message-pop" href="javascript:void(0);" style="display:none;"></a>
            <!--基本信息-->
            <a class="open-appoint-btn" href="javascript:void(0);" style="display:none;"></a>
            <div class="pro-title" id="nav-one"><span class="pro-title-left">基本信息</span></div>
            <div class="client-hide-bg">
                <table class="client-tb">
                    <colgroup>
                        <col width="150px">
                        <col width="320px">
                        <col width="150px">
                        <col width="320px">
                        <col width="120px">
                        <col width="320px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>放款编号</th>
                        <td><input type="text" style="width:200px;" id="loanNo" name="loanNo" value="${(dto.loanNo)!}" disabled="disabled"></td>
                        <th>业务合同号</th>
                        <td><input type="text" style="width:200px;" id="businessContractNo" name="businessContractNo" value="${(dto.businessContractNo)!}" disabled="disabled"></td>
                        <th>借款人名称</th>
                        <td><input type="text" style="width:200px;" id="customerName" name="customerName" value="${(dto.customerName)!}"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>借款人类型</th>
                        <td><input type="text" style="width:200px;" id="customerEconomicCategory" name="customerEconomicCategory" value="${(dto.customerEconomicCategory)!}"></td>
                        <th>交易对手名称</th>
                        <td><input type="text" style="width:200px;" id="counterparty" name="counterparty" value="${(dto.counterparty)!}"></td>
                        <th>交易对手类型</th>
                        <td><input type="text" style="width:200px;" id="counterpartyEconomicCategory" name="counterpartyEconomicCategory" value="${(dto.counterpartyEconomicCategory)!}"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>借款人所在地区</th>
                        <td><input type="text" style="width:200px;" id="customerCity" name="customerCity" value="${(dto.customerCity)!}"></td>
                        <th>借款人所属行业</th>
                        <td><input type="text" style="width:200px;" id="customerIndustry" name="customerIndustry" value="${(dto.customerIndustry)!}"></td>
                        <th>借款人评级</th>
                        <td><input type="text" style="width:200px;" id="customerRating" name="customerRating" value="${(dto.customerRating)!}"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>交易对手所在地区</th>
                     	<td><input type="text" style="width:200px;" id="counterpartyCity" name="counterpartyCity" value="${(dto.counterpartyCity)!}"></td>
                        <th>交易对手所属行业</th>
                        <td><input type="text" style="width:200px;" id="counterpartyIndustry" name="counterpartyIndustry" value="${(dto.counterpartyIndustry)!}"></td>
                        <th>交易对手评级</th>
                        <td><input type="text" style="width:200px;" id="counterpartyRating" name="counterpartyRating" value="${(dto.counterpartyRating)!}"></td>
                        <td></td>
                    </tr><tr>
                        <th>担保人信息</th>
                        <td><input type="text" style="width:200px;" id="guaranteeInfo" name="guaranteeInfo" value="${(dto.guaranteeInfo)!}"></td>
                        <th>担保人类型</th>
                        <td><input type="text" style="width:200px;" id="guaranteeType" name="guaranteeType" value="${(dto.guaranteeType)!}"></td>
                        <th>担保企业信息</th>
                        <td><input type="text" style="width:200px;" id="guaranteeCompanyInfo" name="guaranteeCompanyInfo" value="${(dto.guaranteeCompanyInfo)!}"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>担保企业类型</th>
                        <td><input type="text" style="width:200px;" id="guaranteeCompanyType" name="guaranteeCompanyType" value="${(dto.guaranteeCompanyType)!}"></td>
                        <th>担保品编号</th>
                        <td><input type="text" style="width:200px;" id="guaranteeGoodsNo" name="guaranteeGoodsNo" value="${(dto.guaranteeGoodsNo)!}"></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!--额度信息-->
            <div class="pro-title" id="nav-two"><span class="pro-title-left">额度信息</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <table class="client-tb">
                    <colgroup>
                        <col width="150px">
                        <col width="320px">
                        <col width="150px">
                        <col width="320px">
                        <col width="120px">
                        <col width="320px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>授信额度</th>
                        <td>
                        	<label class="client-unit-box" style="width: 320px;">
                        		<input type="text" style="width:200px;" id="creditLimit" class="moneyNum" name="creditLimit" value="${(dto.creditLimit)!}">
                        		<div class="client-unit-two">元</div>
                        	</label>
                        </td>
                        <th>授信可用额度</th>
                        <td>
                        	<label class="client-unit-box" style="width: 320px;">
                        		<input type="text" style="width:200px;" id="creditBalance" class="moneyNum" name="creditBalance" value="${(dto.creditBalance)!}">
                        		<div class="client-unit-two">元</div>
                        	</label>
                        </td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>交易对手非承保额度</th>
                        <td>
                        	<label class="client-unit-box" style="width: 320px;">
	                        	<input type="text" style="width:200px;" id="counterpartyLimit" class="moneyNum" name="counterpartyLimit" value="${(dto.counterpartyLimit)!}">
	                        	<div class="client-unit-two">元</div>
                        	</label>
                        </td>
                        <th>交易对手非承保余额</th>
                        <td>
                        	<label class="client-unit-box" style="width: 320px;">
                        		<input type="text" style="width:200px;" id="counterpartyBalance" class="moneyNum" name="counterpartyBalance" value="${(dto.counterpartyBalance)!}">
                        		<div class="client-unit-two">元</div>
                        	</label>
                        </td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!--应收账款-->
            <div class="pro-title" id="nav-three"><span class="pro-title-left">应收账款</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <table class="client-tb">
                    <colgroup>
                        <col width="150px">
                        <col width="320px">
                        <col width="150px">
                        <col width="320px">
                        <col width="120px">
                        <col width="320px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>应收账款金额</th>
                        <td>
                        	<label class="client-unit-box" style="width: 320px;">
	                        	<input type="text" style="width:200px;" id="invoiceAmount" class="moneyNum" name="invoiceAmount" value="${(dto.invoiceAmount)!}">
	                        	<div class="client-unit-two">元</div>
                        	</label>	
                        </td>
                        <th>应收账款余额</th>
                        <td>
                        	<label class="client-unit-box" style="width: 320px;">
                        		<input type="text" style="width:200px;" id="invoiceBalance" class="moneyNum" name="invoiceBalance" value="${(dto.invoiceBalance)!}">
                        		<div class="client-unit-two">元</div>
                        	</label>
                        </td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!--融资信息-->
            <div class="pro-title" id="nav-four"><span class="pro-title-left">融资信息</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <table class="client-tb">
                    <colgroup>
                        <col width="150px">
                        <col width="320px">
                        <col width="150px">
                        <col width="320px">
                        <col width="120px">
                        <col width="320px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>融资金额</th>
                        <td>
                        	<label class="client-unit-box" style="width: 320px;">
                        		<input type="text" style="width:200px;" id="financeAmount" class="moneyNum" name="financeAmount" value="${(dto.financeAmount)!}">
                        		<div class="client-unit-two">元</div>
                        	</label>	
                        </td>
                        <th>融资余额</th>
                        <td>	
                        	<label class="client-unit-box" style="width: 320px;">
                        		<input type="text" style="width:200px;" id="financeBalance" class="moneyNum" name="financeBalance" value="${(dto.financeBalance)!}">
                        		<div class="client-unit-two">元</div>
                        	</label>
                        </td>
                        <th>起息日</th>
                        <td><input name="loanDate" id="loanDate" type="text" style="width:200px;" class="datepicker time-one hasIcon" data-required-msg="不能为空" required value="${dto.loanDate!}"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>到期日</th>
						<td><input name="dueDate" id="dueDate" type="text" style="width:200px;" class="datepicker time-two hasIcon" data-required-msg="不能为空" required value="${dto.dueDate!}"></td>
                        <th>还款方式</th>
                        <td><input type="text" style="width:200px;" id="repaymentMethod" name="repaymentMethod" value="${(dto.repaymentMethod)!}"></td>
                        <th>利率类型</th>
                        <td>
                        	<select style="width:200px;" name="interestRateUnitSelect" id="interestRateUnitSelect" data-role="dropdownlist">
	                        	<#if interestRateUnitList?? && (dto.interestRateUnit)??>
	                        		<#list interestRateUnitList as interestRateUnit>
                                    	<#if interestRateUnit.name() == dto.interestRateUnit.name()>
                                    		<option value=${interestRateUnit.name()} selected = "selected">${interestRateUnit.desc()}</option>
                                    	<#else>
	                                    	<option value=${interestRateUnit.name()}>${interestRateUnit.desc()}</option>
                                    	</#if>
									</#list>
	                        	</#if>
                            </select>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>利率</th>
                        <td>
                        	<label class="client-unit-box" style="width: 320px;">
                        		<input type="text" style="width:200px;" id="interestRate" name="interestRate" value="${(dto.interestRateNum*100)?string("0.000")}">
                        		<div class="client-unit-two">%</div>
                        	</label>
                        </td>
                        <th>期限</th>
                        <td><input type="text" style="width:200px;" id="loanPeriod" name="loanPeriod" value="${(dto.loanPeriod)!}"></td>
                        <th>五级分类</th>
                        <td><input type="text" style="width:200px;" id="fiveLevelCategory" name="fiveLevelCategory" value="${(dto.fiveLevelCategory)!}"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>结清栏</th>
                        <td>
                        	<select style="width:200px;" name="settleStatusSelect" id="settleStatusSelect" data-role="dropdownlist">
	                        	<#if settleStatusList??>
	                        		<#list settleStatusList as settleStatus>
                                    	<#if settleStatus.name() == dto.settleStatus>
                                    		<option value=${settleStatus.name()} selected = "selected">${settleStatus.desc()}</option>
                                    	<#else>
	                                    	<option value=${settleStatus.name()}>${settleStatus.desc()}</option>
                                    	</#if>
									</#list>
	                        	</#if>
                            </select>
                        </td>
                        <th>提前还款栏</th>
                        <td>
                            <select name="forwardSelect" id="forwardSelect" style="width:200px;"  data-role="dropdownlist">
                                <option value="true" <#if dto.forwardDays!="0">selected="selected"</#if>>是</option>
                                <option value="false" <#if dto.forwardDays=="0">selected="selected"</#if>>否</option>
                            </select>
                        </td>
                        <th>提前还款天数</th>
                        <td>
                            <label class="client-unit-box" style="width: 320px;">
                                <input type="text" style="width:200px;" id="forwardDays" name="forwardDays" value="${(dto.forwardDays)!}">
                                <span class="client-unit-two">天</span>
                            </label>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>展期栏</th>
                        <td>
                            <select name="extensionSelect" id="extensionSelect" style="width:200px;"  data-role="dropdownlist">
                                <option value="true" <#if dto.extensionDays!="0">selected="selected"</#if>>是</option>
                                <option value="false" <#if dto.extensionDays=="0">selected="selected"</#if>>否</option>
                            </select>
                        </td>
                        <th>展期天数</th>
                        <td>
                            <label class="client-unit-box" style="width: 320px;">
                                <input type="text" style="width:200px;" id="extensionDays" name="extensionDays" value="${(dto.extensionDays)!}">
                                <span class="client-unit-two">天</span>
                            </label>
                        </td>
                        <th>违约资产栏</th>
                        <td>
                            <select name="overdueSelect" id="overdueSelect" style="width:200px;"  data-role="dropdownlist">
                                <option value="true" <#if dto.overdueDays!="0">selected="selected"</#if>>是</option>
                                <option value="false" <#if dto.overdueDays=="0">selected="selected"</#if>>否</option>
                            </select>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>违约天数</th>
                        <td>
                            <label class="client-unit-box" style="width: 320px;">
                                <input type="text" style="width:200px;" id="overdueDays" name="overdueDays" value="${(dto.overdueDays)!}">
                                <span class="client-unit-two">天</span>
                            </label>
                        </td>
                        <th></th>
                        <td></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!--专项计划-->
            <div class="pro-title" id="nav-five"><span class="pro-title-left">专项计划</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <table class="client-tb">
                    <colgroup>
                        <col width="150px">
                        <col width="320px">
                        <col width="150px">
                        <col width="320px">
                        <col width="120px">
                        <col width="320px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>专项计划编号</th>
                        <td><input type="text" id="special-plan" style="width:200px;" id="specialProgramId" name="specialProgramId" value="${(dto.specialProgramId)!}" readonly="readonly"></td>
                        <th>专项计划名称</th>
                        <td><input type="text" style="width:200px;" id="specialProgramName" name="specialProgramName" value="${(dto.specialProgramName)!}" disabled="disabled"></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="client-foot">
                <a class="default-link confirm-link" id="submitEditAssetBtn" href="javascript:void(0);">提交</a>
                <a class="default-link back-link" href="${basepath}/asset/underlyingAsset/underlyingAssetList">返回</a>
            </div>
        </div>
    </form>
</div>
<#include 'assetCommonTemplate.ftl'/>
<#include '../../tipBoxTemplate.ftl'/>
<footer id="footTop">Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
</@hb.htmlBase>
