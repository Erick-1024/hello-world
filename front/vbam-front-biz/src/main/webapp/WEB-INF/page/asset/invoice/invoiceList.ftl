<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="应收账款列表" jsFiles=["page/asset/invoice/invoiceList.js", "common/cana.util.js","common/dateutil.js"] cssFiles=["css/project.css","css/monitor.css"] localCssFiles=[] 
curTopMenu = "基础资产信息" curSubMenu = "应收账款管理" removeExtHeader = false removeExtFooter = false>
<div class="pro-bg" style="background: #fff;">
    <div class="pro-box-bg">
        <form>
            <table class="client-tb" style="padding-top:20px;">
                <colgroup>
                    <col width="120px">
                    <col width="250px">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>客户名称</th>
                    <td><input type="text" class="" id="customerName" name="customerName" style="width:200px;"></td>
                    <td></td>
                </tr>
                <tr>
                    <th>业务合同号</th>
                    <td><input type="text" class="" id="businessContractNo" name="businessContractNo" style="width:200px;"></td>
                    <td></td>
                </tr>
                <tr>
                    <th>币种</th>
                    <td>
                        <select class="" style="width:200px;" id="currencyType" name="currencyType"  data-role="dropdownlist">
                            <option value="">全部</option>
                            <#list CreditCurrencyType as currency>
                            <option value="${currency}">${currency}</option>
                            </#list>
                        </select>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <th>业务产品</th>
                    <td>
                        <select class="" style="width:200px;" id="businessProduct" name="businessProduct" data-role="dropdownlist">
                            <option value="">全部</option>
                            <#list BusinessProduct as product>
                            <option value="${product}">${product.desc()}</option>
                            </#list>
                        </select>
                    </td>
                    <td style="padding-left:50px;">
                        <a id="form-search" class="form-search-btn" href="javascript:void(0);"><i class="searchIcon"></i>搜索</a>
                       <!-- <a id="form-sys" class="form-search-btn limit-add" href="javascript:void(0);" style="float:right;">系统导入</a>-->
                        <#if authorizeKey("BAI_IV_IMPEXCEL")>
                        <a id="form-excel" class="form-search-btn limit-add" href="javascript:void(0);" style="float:right;">文件导入</a>
                        </#if>
                        <#if authorizeKey("BAI_IV_CREATE")>
                        <a id="form-add" class="form-search-btn limit-add" href="javascript:void(0);" style="float:right;">新建</a>
                        </#if>
                        
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorList-grid"></div>
        <a class="limit-add-btn" href="javascript:void(0);" style="display:none;"></a>
        <a class="open-message-btn" href="javascript:void(0);" style="display:none;"></a>
    </div>
</div>
<script>
	var detailAuth = ${authorizeKey("BAI_IV_DETAIL")?string("true", "false")};
	var deleteAuth = ${authorizeKey("BAI_IV_DELETE")?string("true", "false")};
	var updateAuth = ${authorizeKey("BAI_IV_EDIT")?string("true", "false")};
</script>
<!--提示弹窗-->
<script id="tipBox_template" type="text/x-kendo-template">
    <div id="tip-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice"></span>
            <span id="notice-content" class="notice-content"></span>
        </div>
    </div>
</script>
<#include '../../confirmBoxTemplate.ftl'/>
</@hb.htmlBase>