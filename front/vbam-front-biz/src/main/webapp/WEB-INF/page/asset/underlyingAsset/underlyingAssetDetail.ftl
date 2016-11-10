<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="基础资产详情" jsFiles=["js/common/jquery.nav.js","js/common/business.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] curTopMenu = "证券化发行管理" curSubMenu = "基础资产管理" removeExtHeader = false removeExtFooter = true>
<div class="pro-bg" style="background: #fff;min-width: 1600px;">
    <form class="" id="">
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
            <a class="status-normal status-chk limit-next-btn" href="javascript:void(0);" style="display:none;"></a>
            <a class="status-normal status-chk message-pop" href="javascript:void(0);" style="display:none;"></a>
            <!--基本信息-->
            <a class="open-appoint-btn" href="javascript:void(0);" style="display:none;"></a>
            <div class="pro-title" id="nav-one"><span class="pro-title-left">基本信息</span></div>
            <div class="client-hide-bg">
                <table class="client-tb">
                    <colgroup>
                        <col width="150px">
                        <col width="300px">
                        <col width="150px">
                        <col width="300px">
                        <col width="120px">
                        <col width="300px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>放款编号</th>
                        <td>${(underlyingAssetDTO.loanNo)!""}</td>
                        <th>业务合同号</th>
                        <td>${(underlyingAssetDTO.businessContractNo)!""}</td>
                        <th>借款人名称</th>
                        <td>${(underlyingAssetDTO.customerName)!""}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>借款人类型</th>
                        <td>${(underlyingAssetDTO.customerEconomicCategory)!""}</td>
                        <th>交易对手名称</th>
                        <td>${(underlyingAssetDTO.counterparty)!""}</td>
                        <th>交易对手类型</th>
                        <td>${(underlyingAssetDTO.counterpartyEconomicCategory)!""}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>借款人所在地区</th>
                        <td>${(underlyingAssetDTO.customerCity)!""}</td>
                        <th>借款人所属行业</th>
                        <td>${(underlyingAssetDTO.customerIndustry)!""}</td>
                        <th>借款人评级</th>
                        <td>${(underlyingAssetDTO.customerRating)!""}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>交易对手所在地区</th>
                        <td>${(underlyingAssetDTO.counterpartyCity)!""}</td>
                        <th>交易对手所属行业</th>
                        <td>${(underlyingAssetDTO.counterpartyIndustry)!""}</td>
                        <th>交易对手评级</th>
                        <td>${(underlyingAssetDTO.counterpartyRating)!""}</td>
                        <td></td>
                    </tr><tr>
                        <th>担保人信息</th>
                        <td>${(underlyingAssetDTO.guaranteeInfo)!""}</td>
                        <th>担保人类型</th>
                        <td>${(underlyingAssetDTO.guaranteeType)!""}</td>
                        <th>担保企业信息</th>
                        <td>${(underlyingAssetDTO.guaranteeCompanyInfo)!""}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>担保企业类型</th>
                        <td>${(underlyingAssetDTO.guaranteeCompanyType)!""}</td>
                        <th>担保品编号</th>
                        <td>${(underlyingAssetDTO.guaranteeGoodsNo)!""}</td>
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
                        <col width="300px">
                        <col width="150px">
                        <col width="300px">
                        <col width="120px">
                        <col width="300px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>授信额度</th>
                        <td>${(underlyingAssetDTO.creditLimit)!""}</td>
                        <th>授信可用额度</th>
                        <td>${(underlyingAssetDTO.creditBalance)!""}</td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>交易对手非承保额度</th>
                        <td>${(underlyingAssetDTO.counterpartyLimit)!""}</td>
                        <th>交易对手非承保余额</th>
                        <td>${(underlyingAssetDTO.counterpartyBalance)!""}</td>
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
                        <col width="300px">
                        <col width="150px">
                        <col width="300px">
                        <col width="120px">
                        <col width="300px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>应收账款金额</th>
                        <td>${(underlyingAssetDTO.invoiceAmount)!""}</td>
                        <th>应收账款余额</th>
                        <td>${(underlyingAssetDTO.invoiceBalance)!""}</td>
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
                        <col width="300px">
                        <col width="150px">
                        <col width="300px">
                        <col width="120px">
                        <col width="300px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>融资金额</th>
                        <td>${(underlyingAssetDTO.financeAmount)!""}</td>
                        <th>融资余额</th>
                        <td>${(underlyingAssetDTO.financeBalance)!""}</td>
                        <th>起息日</th>
                        <td>${(underlyingAssetDTO.loanDate)!""}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>到期日</th>
                        <td>${(underlyingAssetDTO.dueDate)!""}</td>
                        <th>还款方式</th>
                        <td>${(underlyingAssetDTO.repaymentMethod)!""}</td>
                        <th>利率类型</th>
                        <td>${(underlyingAssetDTO.interestRateUnit.desc())!""}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>利率</th>
                        <td>${(underlyingAssetDTO.interestRate)!""}</td>
                        <th>期限</th>
                        <td>${(underlyingAssetDTO.loanPeriod)!""} 
                        	<#if underlyingAssetDTO.assetSource == "FACTOR">
		                        ${(underlyingAssetDTO.loanPeriodUnit)!""} 
                        	</#if>
                        </td>
                        <th>五级分类</th>
                        <td>${(underlyingAssetDTO.fiveLevelCategory)!""}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>结清栏</th>
                        <td>${(underlyingAssetDTO.settleStatus)!""}</td>
                        <th>提前还款栏</th>
                        <td>
                        	<#if (underlyingAssetDTO.forwardDays) ??>
	                        	<#if underlyingAssetDTO.forwardDays != "0" >
	    	                    		是
	    	                    	<#else>
	    	                    		否
    	                    	</#if>
    	                    	<#else>
    	                    		否
                        	</#if>
                        </td>
                        <th>提前还款天数</th>
                        <td>${(underlyingAssetDTO.forwardDays)!"0 天"} 
                        	<#if underlyingAssetDTO.assetSource == "FACTOR" && (underlyingAssetDTO.forwardDays)??>
		                        天
                        	</#if>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>展期栏</th>
                        <td>
                        	<#if (underlyingAssetDTO.extensionDays) ??>
	                        	<#if (underlyingAssetDTO.extensionDays) != "0" >
	    	                    		是
	    	                    	<#else>
	    	                    		否
    	                    	</#if>
    	                    	<#else>
    	                    		否
                        	</#if>
                        </td>
                        <th>展期天数</th>
                        <td>${(underlyingAssetDTO.extensionDays)!"0 天"} 
                        	<#if underlyingAssetDTO.assetSource == "FACTOR" && (underlyingAssetDTO.extensionDays)??>
		                        天
                        	</#if>
                        </td>
                        <th>违约资产栏</th>
                        <td>
                        	<#if (underlyingAssetDTO.overdueDays) ??>
	                        	<#if (underlyingAssetDTO.overdueDays) != "0" >
	    	                    		是
	    	                    	<#else>
	    	                    		否
    	                    	</#if>
    	                    	<#else>
    	                    		否
                        	</#if>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>违约天数</th>
                        <td>${(underlyingAssetDTO.overdueDays)!"0 天"} 
							<#if underlyingAssetDTO.assetSource == "FACTOR" && (underlyingAssetDTO.overdueDays)??>
		                        天
                        	</#if>
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
                        <col width="300px">
                        <col width="150px">
                        <col width="300px">
                        <col width="120px">
                        <col width="300px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>专项计划编号</th>
                        <td>${(underlyingAssetDTO.specialProgramId)!""}</td>
                        <th>专项计划名称</th>
                        <td>${(underlyingAssetDTO.specialProgramName)!""}</td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="client-foot">
                <a class="default-link back-link" href="javascript:history.back(-1);">返回</a>
            </div>
        </div>
    </form>
</div>
<footer>Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
<div style="height:70px"></div>
</@hb.htmlBase>
