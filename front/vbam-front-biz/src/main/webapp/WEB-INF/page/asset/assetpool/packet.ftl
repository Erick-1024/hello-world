<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="封包" jsFiles=["common/dateutil.js","page/asset/assetpool/packet.js","js/common/jquery.nav.js","js/common/business.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] curTopMenu = "证券化发行管理" curSubMenu = "资产池管理" removeExtHeader = false removeExtFooter = true>

<div class="pro-bg" style="background: #fff;">
    <form class="" id="">
        <div class="left-nav">
            <ul id="client-nav">
                <li class="current"><a style="text-indent: 5px;" href="#nav-one">基本信息&ensp;▶</a></li>
                <li><a style="text-indent: 5px;" href="#nav-two">原始权益人&ensp;▶</a></li>
                <li><a style="text-indent: 5px;" href="#nav-three">资产服务机构&ensp;▶</a></li>
            </ul>
        </div>

        <div class="client-bg">
            <!--弹窗触发按钮 -->
            <a class="status-normal status-chk message-pop" href="javascript:void(0);" style="display:none;"></a>
            <!--基本信息-->
            <a class="open-appoint-btn" href="javascript:void(0);" style="display:none;"></a>
            <div class="pro-title" id="nav-one"><span class="pro-title-left">基本信息</span></div>
            <div class="client-hide-bg">
                <table class="client-tb">
                    <colgroup>
                        <col width="120px">
                        <col width="300px">
                        <col width="120px">
                        <col width="300px">
                        <col width="120px">
                        <col width="300px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>专项计划编号</th>
                        <td>${assetpoolListDTO.id!}
                        <input type="hidden" id="id" value="${assetpoolListDTO.id!}">
                        </td>
                        <th>专项计划名称</th>
                        <td>${assetpoolListDTO.specialProgramName!}</td>
                        <th>基础资产类型</th>
                        <td>${assetpoolListDTO.underlyingAssetType.desc()!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>预计成立日期</th>
                        <td>${assetpoolListDTO.estimateEstablishmentDate!}</td>
                        <th>状态</th>
                        <td>${assetpoolListDTO.status.desc()!}
                        <input type="hidden" id="status" value="${assetpoolListDTO.status!}">
                        </td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>管理人名称</th>
                        <td>${assetpoolListDTO.managerName!}</td>
                        <th>律所</th>
                        <td>${assetpoolListDTO.lawFirmName!}</td>
                        <th>会计事务所</th>
                        <td>${assetpoolListDTO.accountingFirmName!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>监管银行</th>
                        <td>${assetpoolListDTO.supervisionBank!}</td>
                        <th>托管银行</th>
                        <td>${assetpoolListDTO.custodianBank!}</td>
                        <th>评级机构</th>
                        <td>${assetpoolListDTO.ratingAgency!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>资产评估机构</th>
                        <td>${assetpoolListDTO.assetEvaluationAgency!}</td>
                        <th>初始资产池规模</th>
                        <td>${formatMoney(assetpoolListDTO.originAssetpoolScale!'')}
                        <input type="hidden" id="originAssetpoolScale" value="${assetpoolListDTO.originAssetpoolScale!}">
                        </td>
                        <th>封包日期</th>
                        <td><input type="text" name="encapsulationDate" class="datepicker packetDate hasIcon" style="width:200px;" readonly="readonly"></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!--原始权益人-->
            <div class="pro-title" id="nav-two"><span class="pro-title-left">原始权益人</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">

                <div class="min-box-width" style="width:400px;">
                    <table class="m-table fact-table" style="border-bottom:none;">
                        <colgroup>
                            <col width="">
                            <col width="17px">
                        </colgroup>
                        <thead>
                        <tr class="top-list-one">
                            <td>名称</td>
                            <td></td>
                        </tr>
                        </thead>
                    </table>
                    <div class="fact-tb-box">
                        <table class="m-table fact-table-two" style="border:none;margin-top:0;" id="interests-tb">
                            <colgroup>
                                <col width="">

                            </colgroup>
                            <tbody>
                                <#list assetpoolListDTO.originators as originator>
		                            <tr class="client-add-tr">
		                                <td style="border-top:1px dashed #dadada;">${originator.originatorName!}</td>
		                            </tr>
                                </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!--资产服务机构-->
            <div class="pro-title" id="nav-three"><span class="pro-title-left">资产服务机构</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">

                <div class="min-box-width" style="width:400px;">
                    <table class="m-table fact-table" style="border-bottom:none;">
                        <colgroup>
                            <col width="">
                            <col width="17px">
                        </colgroup>
                        <thead>
                        <tr class="top-list-one">
                            <td>名称</td>
                            <td></td>
                        </tr>
                        </thead>
                    </table>
                    <div class="fact-tb-box">
                        <table class="m-table fact-table-two" style="border:none;margin-top:0;" id="asset-tb">
                            <colgroup>
                                <col width="">
                            </colgroup>
                            <tbody>
                                <#list assetpoolListDTO.serviceAgencys as serviceAgency>
		                            <tr class="client-add-tr">
		                                <td style="border-top:1px dashed #dadada;">${serviceAgency.serviceAgencyName!}</td>
		                            </tr>
                                </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="client-foot">
                <a class="default-link confirm-link repayment-entry" href="javascript:void(0);">提交</a>
                <a class="default-link back-link" href="javascript:void(0);">关闭</a>
            </div>
        </div>
    </form>
</div>
<footer>Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
<div style="height:70px"></div>
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