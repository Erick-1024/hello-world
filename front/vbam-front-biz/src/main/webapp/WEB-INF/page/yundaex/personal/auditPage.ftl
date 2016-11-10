<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="身份审核" jsFiles=["js/common/yunda.js","page/yundaex/personal/audit.js"] cssFiles=["css/monitor.css","css/yunda.css"] localCssFiles=[] 
	curTopMenu = "韵达项目" curSubMenu = "身份审核" removeExtHeader = false removeExtFooter = false showMenu=true>

<div class="main-container">
    <div class="bg-box">
        <form>
            <#--<div class="pro-title"><span class="pro-title-left">基础信息</span></div>
            <div class="tb-box" style="position:relative;">
                <table class="check-table">
                    <colgroup>
                        <col width="80">
                        <col width="100">
                        <col width="80">
                        <col width="100">
                        <col width="80">
                        <col width="200">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>申请日期</th>
                        <td>${(cusApplyDTO.applyDate)?string("yyyy-MM-dd HH:mm:ss")}</td>
                        <th>站点名称</th>
                        <td>${(cusApplyDTO.stationName)!}</td>
                        <th>站点编号</th>
                        <td>${(cusApplyDTO.stationNo)!}</td>
                    </tr>
                    <tr>
                        <th>经营地址</th>
                        <td>${(cusApplyDTO.detailAddress)!}</td>
                        <th>加盟年限</th>
                        <td>${(cusApplyDTO.busiLimit)!}</td>
                        <th></th>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>-->
            <#include "baseInformation.ftl" />
            <div class="pro-title"><span class="pro-title-left">审核信息</span></div>
            <div class="tb-box" style="">
                <table class="check-table">
                    <colgroup>
                        <col width="400px">
                        <col width="150px">
                        <col width="600px">
                    </colgroup>
                    <tbody>
                    <!--实际控制人审核信息-->
                    <tr>
                        <th><#if personalInfoDTO.type == "CONTROLLER">实际控制人姓名<#elseif personalInfoDTO.type == "ACCOUNT_HOLDER">开户人姓名<#else>姓名</#if></th>
                        <td>${(personalInfoDTO.realName)!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>身份证号码</th>
                        <td>${(personalInfoDTO.residentIdentityCardNo)!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>手持正反身份证照片</th>
                        <td>
                            <a href="${mediaserver}/imageservice?mediaImageId=${(personalInfoDTO.residentIdentityCardFrontMediaId)!}&mediaType=video" class="look-more" target='_blank'>查看正面</a>
                            <a href="${mediaserver}/imageservice?mediaImageId=${(personalInfoDTO.residentIdentityCardBackMediaId)!}&mediaType=video" class="look-more" target='_blank'>查看反面</a>
                        </td>
                        <td>
                            <label class="accordance"><input type="radio" value="0" name="accordance" class="input-radio">一致</label>
                            <em>&emsp;&emsp;</em>
                            <label class="accordance"><input type="radio" value="1" name="accordance" class="input-radio">不一致</label>
                        </td>
                    </tr>
                    <!--分割线-->
                    <tr></tr>
                    <tr>
                        <th class="bor-de" style="color:#000;text-align: left;">审核结果</th>
                        <td class="bor-de"></td>
                        <td class="bor-de"></td>
                    </tr>
                    <!--分割线-->
                    <tr>
                        <th>审核结果</th>
                        <td><span class="result"></span></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="bottom-btn">
                <div class="b-btn-box">
                    <a class="form-search-link" href="javascript:void(0);" data-id="${personalInfoDTO.id}" id="submitAudit" >提交</a>
                    <a class="form-search-link" href="javascript:history.back();">返回</a>
                </div>
            </div>
        </form>
    </div>
</div>

</@hb.htmlBase>