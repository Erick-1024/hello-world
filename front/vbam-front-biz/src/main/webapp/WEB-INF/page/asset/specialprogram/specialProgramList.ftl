<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="专项计划管理列表" jsFiles=["common/dateutil.js","page/asset/specialprogram/SpecialProgramList.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
	curTopMenu = "证券化发行管理" curSubMenu = "专项计划管理">
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
                    <td><input type="text" class="" id ="specialProgramName" style="width:242px;"></td>
                    <th>基础资产类型</th>
                    <td>
                        <select class="" style="width:150px;" id ="underlyingAssetType" data-role="dropdownlist">
                          <#list basicAssetTypes as basicAssetType>		
							<option value=${basicAssetType.name()!}>${basicAssetType.desc()!}</option>		
						  </#list>
                        </select>
                    </td>
                    <th>管理人名称</th>
                    <td><input type="text" class="" id ="managerName" style="width:200px;"></td>
                    <td></td>
                </tr>
                <tr>
                    <th>预计成立日期</th>
                    <td>
                        <input type="text" class="datepicker startDate hasIcon" readonly="readonly" id ="startEstimateEstablishmentDate" style="width:110px;">
                        <em>至</em>
                        <input type="text" class="datepicker endDate hasIcon" readonly="readonly" id ="endEstimateEstablishmentDate" style="width:110px;">
                    </td>
                    <th>状态</th>
                    <td>
                        <select class="" style="width:150px;" id="statusDesc" data-role="dropdownlist">
                            	<option value="">全部</option>
                           <#list specialProgramStatuss as specialProgramStatus>		
								<option value=${specialProgramStatus.name()!}>${specialProgramStatus.desc()!}</option>		
							</#list>
                        </select>
                    </td>

                    <td></td>
                    <td></td>
                    <td>
                        <a class="form-search-btn" href="javascript:void(0);" style="float:left;" id="seachBtn"><i class="searchIcon"></i>搜索</a>
                        <#if authorizeKey("SIM_SP_ADD") >
                       		 <a class="form-search-btn limit-add" href="${basepath}/asset/specialprogram/addSpeciaProgram" style="float:right;">新建</a>
                        </#if>
                    </td>
                </tr>

                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorList-grid"></div>
        <a class="limit-add-btn" href="javascript:void(0);" style="display:none;"></a>
    </div>
</div>
<script id="tipBox_template" type="text/x-kendo-template">
    <div id="tip-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice"></span>
            <span id="notice-content" class="notice-content"></span>
        </div>
    </div>
</script>
<!--确认弹窗-->
<script id="template-notice" type="text/x-kendo-template">
    <div id="confirm-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <input id="operationObj" type="hidden" value=""/>
            <table class="dlg-rowTable">
                <tr>
                    <td class="dlg-row-left-limit">
                        <span class="dlg-notice notice-icon01"></span>
                    </td>
                    <td class="dlg-row-right-limit">
                        <span class="notice-content">确认要执行操作吗？</span>
                    </td>
                </tr>
            </table>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link confirm-link-two" href="javascript:void(0);">确认</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
<script id="tipBox_template1" type="text/x-kendo-template">
    <div id="tip-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice"></span>
            <span id="notice-content" class="notice-content"></span>
        </div>
    </div>
</script>
<!--权限配置-->
<script>
	var specialProgram_detail = ${authorizeKey("SIM_SP_DETAIL")?string("true","false")};
	var specialProgram_modify = ${authorizeKey("SIM_SP_UPDATE")?string("true","false")};
	var specialProgram_detele = ${authorizeKey("SIM_SP_DELETE")?string("true","false")};
</script>
</@hb.htmlBase>