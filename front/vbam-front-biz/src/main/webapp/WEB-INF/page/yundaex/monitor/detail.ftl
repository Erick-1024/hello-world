<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="监控详情" jsFiles=["js/common/kendo.culture.zh-CN.min.js","page/yundaex/monitor/detail.js","common/dateutil.js"] cssFiles=["/css/monitor.css","css/project.css","css/kendo.default.min.css"] localCssFiles=[] 
curTopMenu = "韵达项目" curSubMenu = "监控" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <div class="monitor-wrap">
            <div class="monitor-wrap-til">基本信息</div>
            <table class="monitor-wrap-element">
                <colgroup>
                    <col width="120">
                    <col width="400">
                    <col width="120">
                    <col width="200">
                    <col width="120">
                    <col width="">
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
                    
                        <span>${formatMoney(usedLimit!'')}元</span>
                    </td>
                </tr>
                <tr>
                <td class="monitor-trtil">保证金余额</td>
                    <td>
                        <span>${formatMoney(bailBalance!'')}元</span>
                    </td>
                    <td class="monitor-trtil">短期借款</td>
                    <td>
                        <span>${formatMoney(shortLoan!'')}元</span>
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
                     <input type="text" class="startDate" id="startDate" value="" style="width:110px;">
                        <em> 至 </em>
                        <input type="text" class="endDate" id="endDate" value="" style="width:110px;">
                    </td>
                    <td>
                        <a href="javascript:void(0);" class="form-search-link" id="searchBtn"><i class="searchIcon"></i>查询</a>
                    </td>
                    <td>
                        <a href="javascript:void(0);" class="form-search-link sixMonth">近6个月</a>
                        <a href="javascript:void(0);" class="form-search-link oneYear">近12个月</a>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">注：最多查询24个月的数据</td>
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