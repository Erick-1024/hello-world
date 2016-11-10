<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="资产池列表" jsFiles=["common/dateutil.js","page/asset/assetpool/assetPoolList.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] curTopMenu = "证券化发行管理" curSubMenu = "资产池管理">
	<div class="pro-bg" style="background: #fff;min-width: 1500px;">
    <div class="pro-box-bg">
        <form>
            <table class="client-tb" style="padding-top:20px;">
                <colgroup>
                    <col width="120px">
                    <col width="260px">
                    <col width="120px">
                    <col width="180px">
                    <col width="120px">
                    <col width="250px">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>专项计划名称</th>
                    <td><input type="text" id="specialProgramName" name="specialProgramName" style="width:242px;"></td>
                    <th>基础资产类型</th>
                    <td>
                        <select id="underlyingAssetType" style="width:150px;" name="underlyingAssetType"  data-role="dropdownlist">
                            <option value="">全部</option>
                            <#list UnderlyingAssetType as assetType>
                            <option value="${assetType}">${assetType.desc()}</option>
                            </#list>
                        </select>
                    </td>
                    <th>管理人名称</th>
                    <td><input type="text" id="managerName" name="managerName" style="width:200px;"></td>
                    <td></td>
                </tr>
                <tr>
                    <th>预计成立日期</th>
                    <td>
                        <input type="text" class="datepicker startDate hasIcon" readonly="readonly" style="width:110px;">
                        <em>至</em>
                        <input type="text" class="datepicker endDate hasIcon" readonly="readonly" style="width:110px;">
                    </td>
                    <th>状态</th>
                    <td>
                        <select id="status" name="status" style="width:150px;"  data-role="dropdownlist">
                            <option value="">全部</option>
                            <#list SpecialProgramStatus as status>
                            <option value="${status}">${status.desc()}</option>
                            </#list>
                        </select>
                    </td>

                    <td></td>
                    <td></td>
                    <td>
                        <a id="form-search" class="form-search-btn" href="javascript:void(0);" style="float:left;"><i class="searchIcon"></i>搜索</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorList-grid"></div>
        <a class="limit-add-btn" href="javascript:void(0);" style="display:none;"></a>
    </div>
</div>

<!--权限配置-->
<script>
	var manageAuth = ${authorizeKey("SIM_PM_MANAGE")?string("true","false")};
	var packetAuth = ${authorizeKey("SIM_PM_PACKET")?string("true","false")};
</script>
</@hb.htmlBase>