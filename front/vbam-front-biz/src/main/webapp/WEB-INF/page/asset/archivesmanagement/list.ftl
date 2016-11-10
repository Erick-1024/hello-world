<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="档案列表" jsFiles=["page/asset/archivesmanagement/list.js", "common/dateutil.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
curTopMenu = "证券化发行管理" curSubMenu = "档案管理" removeExtHeader = false removeExtFooter = false>

<div class="pro-bg" style="background: #fff;min-width: 1500px;">
    <div class="pro-box-bg">
        <form onsubmit="return false;">
            <table class="client-tb" style="padding-top:20px;">
                <colgroup>
                    <col width="120px">
                    <col width="220px">
                    <col width="120px">
                    <col width="180px">
                    <col width="100px">
                    <col width="250px">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>专项计划名称</th>
                    <td><input id="specialProgramName" type="text" class="" style="width:200px;"></td>
                    <th>基础资产类型</th>
                    <td>
                        <select class="" style="width:150px;" id="underlyingAssetType" data-role="dropdownlist">
                            <option value="">全部</option>
                            <#list basicAssetTypes as basicAssetType>		
								<option value="${basicAssetType.name()}">${basicAssetType.desc()}</option>		
						  	</#list>
                        </select>
                    </td>
                    <th>状态</th>
                    <td>
                        <select class="" style="width:150px;" id="status" data-role="dropdownlist">
                            <option value="">全部</option>
                            <#list specialProgramStatuss as specialProgramStatus>		
								<option value="${specialProgramStatus.name()}">${specialProgramStatus.desc()}</option>		
							</#list>
                        </select>
                    </td>
                    <td><a class="form-search-btn" href="javascript:void(0);" style="float:left;"><i class="searchIcon"></i>搜索</a></td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorList-grid"></div>
    </div>
</div>

<!--权限配置-->
<script>
	var sim_am_manage = ${authorizeKey("SIM_AM_MANAGE")?string("true","false")};
</script>

</@hb.htmlBase>