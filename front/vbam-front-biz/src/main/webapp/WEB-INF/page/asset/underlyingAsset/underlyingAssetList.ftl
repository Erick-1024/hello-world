<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="基础资产列表" jsFiles=["page/asset/underlyingAsset/underlyingAssetList.js","common/formValidator.js", "page/asset/underlyingAsset/specialProgramPop.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] curTopMenu = "证券化发行管理" curSubMenu = "基础资产管理" removeExtHeader = false removeExtFooter = true>
<a class="limit-add-btn" href="javascript:void(0);" style="display:none;"></a>
<a class="limit-next-btn" href="javascript:void(0);" style="display:none;"></a>
<a class="message-pop" href="javascript:void(0);" style="display:none;"></a>
<div class="pro-bg" style="background: #fff;min-width: 1500px;">
    <div class="pro-box-bg">
        <form>
            <table class="client-tb" style="padding-top:20px;">
                <colgroup>
                    <col width="120px">
                    <col width="260px">
                    <col width="120px">
                    <col width="260px">
                    <col width="200px">
                    <col width="">
                </colgroup>
                <tbody>

                <tr>
                    <th>放款编号</th>
                    <td><input type="text" name="loanNo" class="" style="width:242px;"></td>
                    <th>业务合同号</th>
                    <td><input type="text" name="businessContractNo" class="" style="width:242px;"></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <th>借款人名称</th>
                    <td><input type="text" name="customerName" class="" style="width:242px;"></td>
                    <th>起息日</th>
                    <td>
                        <input type="text" name="loanDateStart" class="datepicker valueStarDate hasIcon" readonly="readonly" style="width:110px;">
                        <em>至</em>
                        <input type="text" name="loanDateEnd" class="datepicker valueEndDate hasIcon" readonly="readonly" style="width:110px;">
                    </td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <th>到期日</th>
                    <td>
                        <input type="text" name="dueDateStart" class="datepicker maturityStartDate hasIcon" readonly="readonly" style="width:110px;">
                        <em>至</em>
                        <input type="text" name="dueDateEnd" class="datepicker maturityEndDate hasIcon" readonly="readonly" style="width:110px;">
                    </td>
                    <td></td>
                    <td></td>
                    <td><a id="underlyingAssetSearch" class="form-search-btn" href="javascript:void(0);" style="float:right;"><i class="searchIcon"></i>搜索</a></td>
                    <td>
                    	<#if authorizeKey("SIM_UAM_IMPORT") >
	                        <a class="form-search-btn" href="${basepath!}/asset/underlyingAsset/import" style="float:right;">资产导入</a>
                    	</#if>
                    	<#if authorizeKey("SIM_UAM_CHOOSE") >
	                        <a class="form-search-btn checkAsset" href="javascript:void(0);" style="float:right;">资产选择</a>
                    	</#if>
                    	<#if authorizeKey("SIM_UAM_BINDING") >
    	                    <a class="form-search-btn inSump" href="javascript:void(0);" style="float:right;">入池</a>
                    	</#if>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorList-grid"></div>

    </div>
</div>

<#include 'assetCommonTemplate.ftl'/>
<!--资产选择弹窗-->
<script id="template-resetPwd-two" type="text/x-kendo-template">
    <div class="dlg-wrapper template-resetPwd-two">
        <div class="dlg-del-row">
            <table class="client-tb">
            	<colgroup>
                        <col width="100px">
                        <col width="300px">
                        <col width="100px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>放款编号</th>
                        <td><input type="text" class="interests-input" style="width:180px;" name="loanInfoId"></td>
                        <th>融资余额</th>
                        <td>
                            <input type="text" style="width:120px;" name="financeBalanceLower">
                            <em> - </em>
                            <input type="text" style="width:120px;" name="financeBalanceUpper">
                        </td>
                    </tr>
                    <tr>
                        <th>到期日</th>
                        <td>
                            <input type="text" style="width:120px;" class="datepicker maturityStartDate hasIcon" name="dueDateBegin">
                            <em> - </em>
                            <input type="text" style="width:120px;" class="datepicker maturityEndDate hasIcon" name="dueDateEnd">
                        </td>
                        <th></th>
                        <td><a class="form-search-btn assetConvertSearch" href="javascript:void(0);"><i class="searchIcon"></i>搜索</a></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="dlg-del-row">
            <div class="monitor-grid" id="asset-grid"></div>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link comfirm-loan" href="javascript:void(0);">确认</a>
            <a class="default-link back-link" href="javascript:void(0);">返回</a>
        </div>
    </div>
</script>
<script id="template-notice" type="text/x-kendo-template">
    <div id="confirm-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
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
        	<input type="hidden" id="loanNo-unbindSP">
            <a class="default-link confirm-link" id="unbind-confirm" href="javascript:void(0);">确认</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
<script id="template-delete" type="text/x-kendo-template">
    <div id="confirm-box-window-delete" class="dlg-wrapper">
        <div class="dlg-del-row">
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
        	<input type="hidden" id="loanNo-delete">
            <a class="default-link confirm-link" id="delete-confirm" href="javascript:void(0);">确认</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
<script id="tipBox_template" type="text/x-kendo-template">
    <div id="tip-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <span id="notice-icon" class="dlg-notice"></span>
            <span id="notice-content" class="notice-content"></span>
        </div>
    </div>
</script>
<script>
	var detailAuth = ${authorizeKey("SIM_UAM_DETAIL")?string("true", "false")};
	var deleteAuth = ${authorizeKey("SIM_UAM_DELETE")?string("true", "false")};
	var editAuth = ${authorizeKey("SIM_UAM_EDIT")?string("true", "false")};
	var chooseAuth = ${authorizeKey("SIM_UAM_CHOOSE")?string("true", "false")};
	var importAuth = ${authorizeKey("SIM_UAM_IMPORT")?string("true", "false")};
	var bindingAuth = ${authorizeKey("SIM_UAM_BINDING")?string("true", "false")};
	var unbindAuth = ${authorizeKey("SIM_UAM_UNBIND")?string("true", "false")};
</script>

</@hb.htmlBase>
