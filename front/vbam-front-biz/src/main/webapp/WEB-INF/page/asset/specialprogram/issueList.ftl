<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="专项计划成立管理列表" jsFiles=["common/dateutil.js","page/asset/specialprogram/issueList.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
	curTopMenu = "证券化发行管理" curSubMenu = "发行后管理">
	<div class="pro-bg" style="background: #fff;">
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
                    <td><input type="text" class="" style="width:242px;" id ="specialProgramName" name ="specialProgramName"></td>
                    <th>基础资产类型</th>
                    <td>
                        <select class="" style="width:150px;" id ="underlyingAssetType" name ="underlyingAssetType" data-role="dropdownlist">
                            <option value="">全部</option>
                            <option value="">保理</option>
                        </select>
                    </td>
                    <th>管理人名称</th>
                    <td><input type="text" class="" style="width:200px;" id ="managerName" name ="managerName"></td>
                    <td></td>
                </tr>
                <tr>
                    <th>成立日期</th>
                    <td>
                        <input type="text" class="datepicker startDate hasIcon" id ="startEstimateEstablishmentDate" name ="startEstimateEstablishmentDate" readonly="readonly" style="width:110px;">
                        <em>至</em>
                        <input type="text" class="datepicker endDate hasIcon" id ="endEstimateEstablishmentDate" name ="endEstimateEstablishmentDate" readonly="readonly" style="width:110px;">
                    </td>
                    <th>状态</th>
                    <td>
                        <select class="" style="width:150px;" id ="statusDesc" name ="statusDesc" data-role="dropdownlist">
                            <option value="">全部</option>
                            <option value="ESTABLISH">成立</option>
                            <option value="PACKAGE">封包</option>
                            <option value="ISSUE">发行</option>
                            <option value="CLOSE">结束</option>
                        </select>
                    </td>

                    <td></td>
                    <td></td>
                    <td>
                        <a class="form-search-btn" href="javascript:void(0);" id ="search-id" style="float:left;"><i class="searchIcon"></i>搜索</a>
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
	var specialProgram_add = ${authorizeKey("SIM_SPI_ADD")?string("true","false")};
	var specialProgram_manage = ${authorizeKey("SIM_SPI_MANAGE")?string("true","false")};
</script>
</@hb.htmlBase>