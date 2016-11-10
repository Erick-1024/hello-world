<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="准入规则" jsFiles=[] cssFiles=["css/monitor.css"] localCssFiles=[] 
curTopMenu = "真旅项目" curSubMenu = "准入规则" removeExtHeader = false removeExtFooter = false>

<div class="main-container">
    <section class="whiteBg">
        <div class="relieve-wrap">
            <div class="monitor-wrap-til">准入规则</div>
            <div class="monitorChk-content">
                <table class="monitorChk-tab">
                    <colgroup>
                        <col width="100px">
                        <col width="150px">
                        <col width="200px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th rowspan="9">系统审核</th>
                        <td class="registionRow-til">是否检查是白名单客户</td>
                        <td>${accessRule.isCheckWhiteCustomer!}</td>
                    </tr>
                    <tr>
                        <td class="registionRow-til">与真旅网合作月份</td>
                        <td>大于等于${accessRule.cooperationPeriod!}个月</td>
                    </tr>
                    <tr>
                        <td class="registionRow-til">最近三个月逾期率（真旅网）</td>
                        <td>小于等于${accessRule.overdueRateTz!}</td>
                    </tr>
                    <tr>
                        <td class="registionRow-til">最近一个月逾期次数（真旅网）</td>
                        <td>小于等于${accessRule.overdueTimesTz!}次</td>
                    </tr>
                    <tr>
                        <td class="registionRow-til">最近三个月逾期率（CANA）</td>
                        <td>小于等于${accessRule.overdueRateCana!}</td>
                    </tr>
                    <tr>
                        <td class="registionRow-til">最近一个月逾期次数(CANA)</td>
                        <td>小于等于${accessRule.overdueTimesCana!}次</td>
                    </tr>
                    <#--
                    <tr>
                        <td class="registionRow-til">订单采购增长率</td>
                        <td>大于等于${accessRule.purchaseOrderGrowthRate!}</td>
                    </tr>
                    -->
                    </tbody>
                </table>
            </div>
            <div class="monitorChk-content">
                <table class="monitorChk-tab">
                    <colgroup>
                        <col width="100px">
                        <col width="150px">
                        <col width="200px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th rowspan="6">人工审核</th>
                        <td class="registionRow-til">法院被执行（企业）总金额（元）</td>
                        <td>小于等于${formatMoney(accessRule.courtExecuteCompanyAmount!)}</td>
                    </tr>
                    <tr>
                        <td class="registionRow-til">执行次数（企业）</td>
                        <td>小于等于${accessRule.courtExecuteCompanyTimes!}次</td>
                    </tr>
                    <tr>
                        <td class="registionRow-til">法院被执行（个人）总金额（元）</td>
                        <td>小于等于${formatMoney(accessRule.courtExecuteIndividualAmount!)}</td>
                    </tr>
                    <tr>
                        <td class="registionRow-til">执行次数（个人）</td>
                        <td>小于等于${accessRule.courtExecuteIndividualTimes!}次</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</div>
</@hb.htmlBase>