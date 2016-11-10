<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="监控详情" jsFiles=["page/report/monitor/detail.js","common/dateutil.js"] cssFiles=["/css/monitor.css"] localCssFiles=[] 
curTopMenu = "真旅项目" curSubMenu = "监控" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <div class="monitor-wrap">
            <div class="monitor-wrap-til">基本信息</div>
            <table class="monitor-wrap-element">
                <colgroup>
                    <col width="120">
                    <col width="200">
                    <col width="120">
                    <col width="200">
                    <col width="120">
                    <col width="200">
                </colgroup>
                <tbody>
                <tr>
                    <td class="monitor-trtil">客户名称</td>
                    <td>
                    	<input id="memberId" type="hidden" value="${memberId!''}"/>
                        <span>${customerName!''}</span>
                    </td>
                    <td class="monitor-trtil">使用额度</td>
                    <td>
                        <span>${formatMoney(usedLimit!'')}</span>
                    </td>
                    <td class="monitor-trtil">保证金余额</td>
                    <td>
                        <span>-</span>
                    </td>
                </tr>
                <tr>
                	<td class="monitor-trtil">外部客户名称</td>
                    <td>
                    	<input id="outCustomerId" type="hidden" value="${outCustomerId!''}"/>
                        <span>${outCustomerName!''}</span>
                    </td>
                    <td class="monitor-trtil">日均销售(360天)</td>
                    <td>
                        <span>${formatMoney(dailySales!'')}</span>
                    </td>
                    <td class="monitor-trtil">质押率</td>
                    <td>
                        <span>${pledgeRage!''}</span>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>      
        <form class="monitor-form">
            <table class="monitor-table">
                <colgroup>
                    <col width="100">
                    <col width="280">
                    <col width="150">
                    <col width="280">
                </colgroup>
                <tbody>
                <tr>
                    <th>查询时间</th>
                    <td>
                        <input type="text" readonly="" id="startDate" class="datepicker fromDate hasIcon">
                        <em> 至 </em>
                        <input type="text" readonly="" id="endDate" class="datepicker toDate hasIcon">
                    </td>
                    <td>
                        <a href="javascript:void(0);" class="form-search-link" id="searchBtn"><i class="searchIcon"></i>查询</a>
                    </td>
                    <td>
                        <a href="javascript:void(0);" class="form-search-link weekBtn">近1周</a>
                        <a href="javascript:void(0);" class="form-search-link monthBtn">近2周</a>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">注：最多查询一个月的数据</td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-listWrap">
            <div class="monitor-listWrap-head">
                <span class="monitor-listWrap-til">指标</span>
            </div>
            <div class="monitor-tabWrap" id="monitor-tabMetric">
                
            </div>
        </div>
        <div class="monitor-listWrap">
            <div class="monitor-listWrap-head">
                <span class="monitor-listWrap-til">数据</span>
            </div>
            <div class="monitor-tabWrap" id="monitor-tabData">
                
            </div>
        </div>
    </section>
</div>
<#include 'monitorTemplate.ftl'/>
</@hb.htmlBase>