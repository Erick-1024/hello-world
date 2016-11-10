<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="市场数据总览" jsFiles=["js/common/kendo.culture.zh-CN.min.js","js/common/echarts.min.js","common/dateutil.js","page/asset/marketresearch/summary.js"] cssFiles=["css/monitor.css","css/project.css","css/kendo.default.min.css"] localCssFiles=[] curTopMenu = "ABS市场研究" curSubMenu = "市场数据总览">
	<div class="pro-bg" style="background: #fff;">
    <div class="pro-box-bg">
        <div class="pro-title" style="width:900px;margin:0 auto;"><span class="pro-title-left">企业资产证券化产品</span></div>
        <div class="import-box" style="width:900px;margin:0 auto;">
            <table class="client-tb">
                <colgroup>
                    <col width="250">
                    <col width="200">
                    <col width="250">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>发行产品总数</th>
                    <td>${marketDataSummaryDTO.issueTotalNum!}</td>
                    <th>发行产品总额（亿）</th>
                    <td>${marketDataSummaryDTO.issueTotalAmount!}</td>
                </tr>
                <tr>
                    <th>最近30天发行产品总数</th>
                    <td>${marketDataSummaryDTO.recentThirtyNum!}</td>
                    <th>最近30天发行产品总额（亿）</th>
                    <td>${marketDataSummaryDTO.recentThirtyAmount!}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="pandect-box">
            <ul>
                <li class="pandect-table-title">企业资产证券化产品发行统计</li>
                <li class="pandect-table-nav">
                    <div style="float:left;">
                        <span>缩放</span>
                        <a class="dataZoom" href="javascript:void(0);" data-time="0">1月</a>
                        <a class="dataZoom" href="javascript:void(0);" data-time="2">3月</a>
                        <a class="dataZoom" href="javascript:void(0);" data-time="5">6月</a>
                        <a class="dataZoom" href="javascript:void(0);" data-time="11">1年</a>
                        <a class="dataZoom" href="javascript:void(0);" data-time="all">所有</a>
                    </div>
                    <div style="float:right;">
                            <em>从</em>
                            <input type="text" class="startDate" id="startDate" value="${marketDataSummaryDTO.minDate!}" style="width:110px;">
                            <em>到</em>
                            <input type="text" class="endDate" id="endDate" value="${marketDataSummaryDTO.maxDate!}" style="width:110px;">
                    </div>
                </li>
                <li class="pandect-table-body">
                    <div id="main" style="width: 900px;height:600px;"></div>
                </li>
            </ul>
        </div>
    <div style="width:900px;height:auto;margin:0 auto;">
        <div class="pro-title">
            <span class="pro-title-left">最新发行企业产品</span>
            <span style="float:right;"><a class="pandect-more" href="javascript:void(0);">更多</a></span>
        </div>
        <!-- <div class="monitor-grid" id="monitorList-grid"></div> -->
        <table class="m-table fact-table gridOutLine-my">
            <colgroup>
                <col width="500">
                <col width="200">
                <col width="200">
            </colgroup>
            <thead>
            <tr class="top-list-one">
                <td class="gridInnerLine-my">产品名称</td>
                <td class="gridInnerLine-my">总金额（亿）</td>
                <td class="gridInnerLine-my">资产类型</td>
            </tr>
            </thead>
            <tbody>
            <#list marketDataSummaryDTO.recentIssueProducts as products>
            <tr class="client-add-tr">
                <td class="gridInnerLine-my">${products.projectName!}</td>
                <td class="gridInnerLine-my">${products.issueTotalAmount!}</td>
                <td class="gridInnerLine-my">${products.underlyingAssetType!}</td>
            </tr>
            </#list>
            </tbody>
        </table>
        
    </div>

    </div>
</div>
<!--提示弹窗-->
<script id="tipBox_template" type="text/x-kendo-template">
    <div id="tip-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice"></span>
            <span id="notice-content" class="notice-content"></span>
        </div>
    </div>
</script>
</@hb.htmlBase>