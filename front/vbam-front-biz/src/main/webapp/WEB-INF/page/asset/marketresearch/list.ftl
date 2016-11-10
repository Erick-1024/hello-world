<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="市场数据列表" jsFiles=["common/dateutil.js","page/asset/marketresearch/list.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] curTopMenu = "ABS市场研究" curSubMenu = "市场数据总览">
	<div class="pro-bg" style="background: #fff;">
    <div class="pro-box-bg">
        <div class="pro-title"><span class="pro-title-left">企业资产证券化产品</span></div>
        <div class="import-box">
            <table class="client-tb">
                <colgroup>
                    <col width="120">
                    <col width="100">
                    <col width="200">
                    <col width="100">
                    <col width="200">
                    <col width="100">
                    <col width="200">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                   <th>发行产品总数</th>
                    <td>${marketDataSummaryDTO.issueTotalNum!}</td>
                    <th>发行产品总额（亿）</th>
                    <td>${marketDataSummaryDTO.issueTotalAmount!}</td>
                    <th>最近30天发行产品总数</th>
                    <td>${marketDataSummaryDTO.recentThirtyNum!}</td>
                    <th>最近30天发行产品总额（亿）</th>
                    <td>${marketDataSummaryDTO.recentThirtyAmount!}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="pro-title"><span class="pro-title-left">查询条件</span></div>
        <div class="import-box">
            <table class="client-tb">
                <colgroup>
                    <col width="100">
                    <col width="250">
                    <col width="100">
                    <col width="250">
                    <col width="100">
                    <col width="250">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>监管机构</th>
                    <td>
                        <select id="supervisionAgency" style="width:200px;"  data-role="dropdownlist">
                            <option value="">全部</option>
                            <#list SupervisionAgencyEnum as status>
                            <option value="${status}">${status.desc()}</option>
                            </#list>
                        </select>
                    </td>
                    <th>基础资产类型</th>
                    <td>
                        <select id="underlyingAssetType" style="width:200px;"  data-role="dropdownlist">
                            <option value="">全部</option>
                            <#list UnderlyingAssetType as status>
                            <option value="${status}">${status}</option>
                            </#list>
                        </select>
                    </td>
                    <th>发行时间</th>
                    <td>
                        <select id="valueDate" style="width:200px;"  data-role="dropdownlist">
                            <option value="">全部年份</option>
                            <option value="2024">2024</option>
                            <option value="2023">2023</option>
                            <option value="2022">2022</option>
                            <option value="2021">2021</option>
                            <option value="2020">2020</option>
                            <option value="2019">2019</option>
                            <option value="2018">2018</option>
                            <option value="2017">2017</option>
                            <option value="2016">2016</option>
                            <option value="2015">2015</option>
                            <option value="2014">2014</option>
                            <option value="2013">2013</option>
                            <option value="2012">2012</option>
                            <option value="2011">2011</option>
                            <option value="2010">2010</option>
                            <option value="2009">2009</option>
                            <option value="2008">2008</option>
                            <option value="2007">2007</option>
                            <option value="2006">2006</option>
                            <option value="2005">2005</option>
                        </select>
                    </td>
                    <td><a id="form-search" class="form-search-btn" href="javascript:void(0);" style="float:left;"><i class="searchIcon"></i>搜索</a></td>
                </tr>
                <tr>
                    <th>发起机构</th>
                    <td>
                        <input type="text" id="originator" style="width: 200px;">
                    </td>
                    <th>发行人</th>
                    <td>
                    	<input type="text" id="issuer" style="width: 200px;">
                    </td>
                    <th></th>
                    <td></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="monitor-grid" id="monitorList-grid"></div>
    </div>
</div>

</@hb.htmlBase>