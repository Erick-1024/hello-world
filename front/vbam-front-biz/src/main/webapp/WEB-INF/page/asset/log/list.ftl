<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="管理日志" jsFiles=["page/asset/log/list.js","common/cana.util.js","common/dateutil.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] curTopMenu = "证券化发行管理" curSubMenu = "管理日志" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <div class="thirdNavGroup">
            <ul>
            	<#if permSpecialProgram>
	                <li id="planTab" class="thirdNavCut">
	                    <a class="thirdNavItem" href="javascript:void(0);">计划日志</a>
	                </li>
                </#if>
                <#if permAsset>
	                <li id="assetTab" <#if !permSpecialProgram>class="thirdNavCut"</#if>>
	                    <a class="thirdNavItem" href="javascript:void(0);">资产日志</a>
	                </li>
                </#if>
            </ul>
        </div>
        <div class="plan-box">
	    	<input type="hidden" id="permSpecialProgram" value="${permSpecialProgram?string('true','false')}">
	    	<input type="hidden" id="permAsset" value="${permAsset?string('true','false')}">
        <#if permSpecialProgram>
            <div>
                <div class="pro-title" style="height:40px;line-height:40px;"><span class="pro-title-left">查询条件</span></div>
                <div>
                    <ul>
                        <li style="float:left;width:400px;height:100px;">
                            <table class="client-tb" style="margin-top:10px;">
                                <colgroup>
                                    <col width="150">
                                    <col width="300">
                                </colgroup>
                                <tbody>
                                <tr>
                                    <th>专项计划名称</th>
                                    <td><input type="text" id="sp-specialProgramName" name="" value="" style="width:242px;"></td>
                                </tr>
                                <tr>
                                    <th>操作日期</th>
                                    <td>
                                        <input type="text" id="sp-operateStartDate" class="datepicker startDate hasIcon" name="" value="" style="width:110px;" readonly="readonly">
                                        <em>至</em>
                                        <input type="text" id="sp-operateEndDate" class="datepicker endDate hasIcon" name="" value="" style="width:110px;" readonly="readonly">
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </li>
                        <li style="float:left;width:350px;height:100px;">
                            <table class="client-tb" style="margin-top:10px;">
                                <colgroup>
                                    <col width="150">
                                    <col width="250">
                                </colgroup>
                                <tbody>
                                <tr>
                                    <th>操作企业名称</th>
                                    <td><input type="text" id="sp-operateCompanyName" name="" value="" style="width:200px;"></td>
                                </tr>
                                <tr>
                                    <th></th>
                                    <td></td>
                                </tr>
                                </tbody>
                            </table>
                        </li>
                        <li style="float:left;width:350px;height:100px;">
                            <table class="client-tb" style="margin-top:10px;">
                                <colgroup>
                                    <col width="150">
                                    <col width="300">
                                </colgroup>
                                <tbody>
                                <tr>
                                    <th>操作</th>
                                    <td>
                                        <select style="width:200px;" name="specialProgramStatusSelect" id="specialProgramStatusSelect" data-role="dropdownlist">
                                        	<option value="">全部</option>
				                        	<#if specialProgramStatusList??>
				                        		<#list specialProgramStatusList as specialProgramStatus>
				                                	<option value=${specialProgramStatus.name()}>${specialProgramStatus.desc()}</option>
												</#list>
				                        	</#if>
			                            </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th></th>
                                    <td></td>
                                </tr>
                                </tbody>
                            </table>
                        </li>
                        <li style="float:left;width:150px;height:100px;padding-top:16px;">
                            <a id="specialProgramSearchBtn" class="form-search-btn" href="javascript:void(0);" style="float:left;"><i class="searchIcon"></i>搜索</a>
                        </li>
                    </ul>
                </div>
                <div style="clear: both;"></div>
                <div class="monitor-grid" id="plan-grid"></div>
            </div>
		</#if>
		<#if permAsset>
            <div <#if permSpecialProgram>style="display:none;"</#if>>
                <div class="pro-title" style="height:40px;line-height:40px;"><span class="pro-title-left">查询条件</span></div>
                <div>
                    <ul>
                        <li style="float:left;width:400px;height:100px;">
                            <table class="client-tb" style="margin-top:10px;">
                                <colgroup>
                                    <col width="150">
                                    <col width="300">
                                </colgroup>
                                <tbody>
                                <tr>
                                    <th>专项计划名称</th>
                                    <td><input type="text" id="as-specialProgramName" name="" value="" style="width:242px;"></td>
                                </tr>
                                <tr>
                                    <th>操作日期</th>
                                    <td>
                                        <input type="text" class="datepicker startDate hasIcon" id="as-operateStartDate" name="" value="" style="width:110px;" readonly="readonly">
                                        <em>至</em>
                                        <input type="text" class="datepicker endDate hasIcon" id="as-operateEndDate" name="" value="" style="width:110px;" readonly="readonly">
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </li>
                        <li style="float:left;width:350px;height:100px;">
                            <table class="client-tb" style="margin-top:10px;">
                                <colgroup>
                                    <col width="150">
                                    <col width="250">
                                </colgroup>
                                <tbody>
                                <tr>
                                    <th>放款编号</th>
                                    <td><input type="text" id="as-loanInfoId" name="" value="" style="width:200px;"></td>
                                </tr>
                                <tr>
                                    <th>操作企业名称</th>
                                    <td><input type="text" id="as-operateCompanyName" name="" value="" style="width:200px;"></td>
                                </tr>
                                </tbody>
                            </table>
                        </li>
                        <li style="float:left;width:350px;height:100px;">
                            <table class="client-tb" style="margin-top:10px;">
                                <colgroup>
                                    <col width="150">
                                    <col width="300">
                                </colgroup>
                                <tbody>
                                <tr>
                                    <th>业务合同号</th>
                                    <td><input type="text" id="as-businessContractNo" name="" value="" style="width:200px;"></td>
                                </tr>
                                <tr>
                                    <th>操作</th>
                                    <td>
                                    	<select style="width:200px;" name="underlyingAssetOperateTypeSelect" id="underlyingAssetOperateTypeSelect" data-role="dropdownlist">
                                    		<option value="">全部</option>
				                        	<#if underlyingAssetOperateTypeList??>
				                        		<#list underlyingAssetOperateTypeList as underlyingAssetOperateType>
				                                	<option value=${underlyingAssetOperateType.name()}>${underlyingAssetOperateType.desc()}</option>
												</#list>
				                        	</#if>
				                        </select>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </li>
                        <li style="float:left;width:150px;height:100px;padding-top:16px;">
                            <a id="assetSearchBtn" class="form-search-btn" href="javascript:void(0);" style="float:left;"><i class="searchIcon"></i>搜索</a>
                        </li>
                    </ul>
                </div>
                <div style="clear: both;"></div>
                <div class="monitor-grid" id="asset-grid"></div>
            </div>
        </#if>
        </div>
    </section>
</div>

</@hb.htmlBase>
