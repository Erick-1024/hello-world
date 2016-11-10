<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="市场数据详情" jsFiles=["common/dateutil.js","page/asset/marketresearch/detail.js","js/common/jquery.nav.js","js/common/business.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] curTopMenu = "ABS市场研究" curSubMenu = "市场数据总览" removeExtHeader = false removeExtFooter = true>

  <div class="pro-bg" style="background: #fff;">
    <form class="" id="">
        <div class="left-nav">
            <ul id="client-nav">
                <li class="current"><a href="#nav-one">产品要素&ensp;▶</a></li>
                <li><a href="#nav-two">证券信息&ensp;▶</a></li>
            </ul>
        </div>

        <div class="client-bg">
            <!--产品要素-->
            <a class="open-appoint-btn" href="javascript:void(0);" style="display:none;"></a>
            <div class="pro-title" id="nav-one"><span class="pro-title-left">产品要素</span></div>
            <div class="client-hide-bg">
                <table class="client-tb">
                    <colgroup>
                        <col width="120px">
                        <col width="200px">
                        <col width="120px">
                        <col width="200px">
                        <col width="120px">
                        <col width="200px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>产品名称</th>
                        <td style="position:relative;">
                            <div style="position: absolute;top:0;left:0;width:500px;line-height: 45px;">${marketDataListDTO.projectName!}</div>
                        </td>
                        <th></th>
                        <td></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>总金额（亿）</th>
                        <td>${marketDataListDTO.issueTotalAmount!}</td>
                        <th>资产类型</th>
                        <td>${marketDataListDTO.underlyingAssetType!}</td>
                        <th>监管机构</th>
                        <td>${marketDataListDTO.supervisionAgencyDesc!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>发行日期</th>
                        <td>${marketDataListDTO.issueDate!}</td>
                        <th>发行机构</th>
                        <td>${marketDataListDTO.originator!}</td>
                        <th>发行人</th>
                        <td>${marketDataListDTO.issuer!}</td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!--证券信息-->
            <div class="pro-title" id="nav-two"><span class="pro-title-left">证券信息</span></div>
            <div class="client-hide-bg">
                <table class="m-table fact-table gridOutLine-my">
		            <colgroup>
		                <col width="300">
		                <col width="300">
		                <col width="300">
		                <col width="300">
		                <col width="300">
		                <col width="300">
		            </colgroup>
		            <thead>
		            <tr class="top-list-one">
		                <td class="gridInnerLine-my">产品名称</td>
		                <td class="gridInnerLine-my">发行规模（亿）</td>
		                <td class="gridInnerLine-my">债项评级</td>
		                <td class="gridInnerLine-my">利率</td>
		                <td class="gridInnerLine-my">AAA平均利率</td>
		                <td class="gridInnerLine-my">优先级利率</td>
		            </tr>
		            </thead>
		            <tbody>
		            <#list marketDataListDTO.productDTOs as productDTO>
		            <tr class="client-add-tr">
		                <td class="gridInnerLine-my">${productDTO.productName!}</td>
		                <td class="gridInnerLine-my">${productDTO.issueAmount!}</td>
		                <td class="gridInnerLine-my">${productDTO.debtRating!}</td>
		                <td class="gridInnerLine-my">${productDTO.interestRate!}</td>
		                <td class="gridInnerLine-my">${productDTO.aaaAverageInterestRate!}</td>
		                <td class="gridInnerLine-my">${productDTO.priorityAverageInterestRate!}</td>
		            </tr>
		            </#list>
		            </tbody>
                </table>

            </div>

            <div class="client-foot">
                <a class="default-link back-link" href="${basepath}/asset/marketResearch/marketDataSearchList">返回</a>
            </div>
        </div>
    </form>
</div>
<footer>Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
<div style="height:70px"></div>
</@hb.htmlBase>