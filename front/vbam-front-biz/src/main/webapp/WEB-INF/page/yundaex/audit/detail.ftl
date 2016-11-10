<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="审核列表详情" jsFiles=["js/common/yunda.js","page/yundaex/audit/detail.js"] cssFiles=["css/yunda.css","css/monitor.css"] localCssFiles=[] 
curTopMenu = "韵达项目" curSubMenu = "审核列表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <div class="bg-box">
        <div class="pro-title"><span class="pro-title-left">基本信息</span></div>
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
                    <td>${customerApplyDetailDTO.applyDate!?string('yyyy-MM-dd HH:mm:ss')}</td>
                    <th>网点名称</th>
                    <td>${customerApplyDetailDTO.stationName!}</td>
                    <th>经营地址</th>
                    <td>${customerApplyDetailDTO.detailAddress!}</td>
                </tr>
                <tr>
                    <th>网点编号</th>
                    <td>${customerApplyDetailDTO.stationNo!}</td>
                    <th>加盟年限</th>
                    <td>${customerApplyDetailDTO.busiLimit!}年</td>
                    <th>其他说明</th>
                    <td>${customerApplyDetailDTO.yundaexExplain!}</td>
                </tr>
                <tr>
                    <th>联系人名称</th>
                    <td>${customerApplyDetailDTO.custName!}</td>
                    <th>联系人身份证号</th>
                    <td>${customerApplyDetailDTO.custIdno!}</td>
                    <th>联系人手机号</th>
                    <td>${customerApplyDetailDTO.custPhone!}</td>
                </tr>
                <tr>
                    <th>联系人邮箱</th>
                    <td>${customerApplyDetailDTO.custEmail!}</td>
                </tr>
                </tbody>
            </table>
            <#if (customerApplyDetailDTO.accessManualState) =="WAIT" >
            <#if authorizeKey("YD_AUDIT_LIST_GOTOADDDATA")>
            <a class="form-search-link add-cont" href="${basepath}/yundaex/audit/gotoAddData?id=${customerApplyDetailDTO.id!}">补充资料</a>
            </#if>
            </#if>
        </div>
        
        <div class="pro-title"><span class="pro-title-left">账户信息</span><span class="pro-title-right">展开</span></div>
        <div class="tb-box" style="display:none;">
             <table class="check-table">
                <colgroup>
                    <col width="100">
                    <col width="100">
                    <col width="80">
                    <col width="100">
                    <col width="80">
                    <col width="200">
                </colgroup>
                <tbody>
                <tr>
                    <th>打款账户账号</th>
                    <td>${customerApplyDetailDTO.payAccount!}</td>
                    <th>账户人名称</th>
                    <td>${customerApplyDetailDTO.payAccountName!}</td>
                    <th>开户行</th>
                    <td>${customerApplyDetailDTO.payAccountBankName!}</td>
                </tr>
                <tr>
                    <th>银行地址</th>
                    <td>${customerApplyDetailDTO.bankProvince!}</td>
                   <th>保证金账户余额</th>
                    <td>
                    <#if (customerApplyDetailDTO.bailBalance)??>
                    ${formatMoney(customerApplyDetailDTO.bailBalance!'')}元
                    </#if>
                    </td>
                    <th>开户行地址</th>
                    <td>${customerApplyDetailDTO.payAccountAddress!}</td>
                </tr>
                </tbody>
            </table>
                
        </div>
        
        <div class="pro-title"><span class="pro-title-left">经营信息</span><span class="pro-title-right">展开</span></div>
        <div class="tb-box" style="display:none;">
            <table class="check-table">
                <colgroup>
                    <col width="100">
                    <col width="100">
                    <col width="80">
                    <col width="100">
                    <col width="80">
                    <col width="200">
                </colgroup>
                <tbody>
                <tr>
                    <th>实际控制人</th>
                    <td>${customerApplyDetailDTO.controller!}</td>
                    <th>实际控制人身份证号</th>
                    <td>${customerApplyDetailDTO.controllerIdno!}</td>
                    <th>实际控制人籍贯</th>
                    <td>${customerApplyDetailDTO.controllerOrigin!}</td>
                </tr>
                <tr>
                    <th>实际控制人邮箱</th>
                    <td>${customerApplyDetailDTO.controllerEmail!}</td>
                    <th>实际控制人手机号码</th>
                    <td>${customerApplyDetailDTO.controllerPhone!}</td>
                    <th>韵达评价</th>
                    <td>${customerApplyDetailDTO.yundaexJudgeDesc!}</td>
                </tr>
                <tr>
                    <th>实际控制人与法人代表是否是同一人</th>
                    <td>
                    <#if (customerApplyDetailDTO.controllerIsLegal!)=="1">
                    是
                    </#if>
                    <#if (customerApplyDetailDTO.controllerIsLegal!)=="0">
                    否
                    </#if>
                    <input type="hidden" id="controllerIsLegal" name="controllerIsLegal" value="${customerApplyDetailDTO.controllerIsLegal!}" >
                    </td>
                    <th>开户人</th>
                    <td>${customerApplyDetailDTO.accountOwnerDesc!}
                    <input type="hidden" id="accountOwner" name="accountOwner" value="${customerApplyDetailDTO.accountOwner!}" >
                    </td>
                    <th>网点数量</th>
                    <td>
                    ${customerApplyDetailDTO.stationAmount!}
                    </td>
                </tr>
                <!--隐藏1（法人）-->
                    <tr class="hidden-one">
                    <th>法人名称</th>
                    <td>${customerApplyDetailDTO.legalPerson!}</td>
                    <th>法人代表邮箱</th>
                    <td>${customerApplyDetailDTO.legalEmail!}</td>
                    <th>法人代表手机号码</th>
                    <td>${customerApplyDetailDTO.legalPhone!}</td>
                </tr>
                <!--隐藏2（开户人）-->
                    <tr class="hidden-two">
                    <th>打款账户户名</th>
                    <td>${customerApplyDetailDTO.accountOwnerName!}</td>
                    <th>开户人邮箱</th>
                    <td>${customerApplyDetailDTO.accountOwnerEmail!}</td>
                    <th>开户人手机号码</th>
                    <td>${customerApplyDetailDTO.accountOwnerPhone!}</td>
                </tr>
                <tr>
                    <th>代理资质</th>
                    <td>${customerApplyDetailDTO.agentQualification!}</td>
                    <th>是否有淘宝、天猫订单</th>
                    <td>
                    <#if (customerApplyDetailDTO.whetherTbOrder!)=="1">
                    是
                    </#if>
                    <#if (customerApplyDetailDTO.whetherTbOrder!)=="0">
                    否
                    </#if>
                    <input type="hidden" id="whetherTbOrder" name="whetherTbOrder" value="${customerApplyDetailDTO.whetherTbOrder!}" >
                    </td>
                    <!--隐藏3（淘宝）-->
                    <th><span class="hidden-three">淘宝、天猫订单占比</span></th>
                    <td>
                    <#if (customerApplyDetailDTO.tbOrderRatio)??>
                    <span class="hidden-three">${customerApplyDetailDTO.tbOrderRatio!}%</span>
                    <#else>
                    <span class="hidden-three">${customerApplyDetailDTO.tbOrderRatio!}</span>
                    </#if>
                    </td>
                </tr>
                
                <tr>
                    <th>短期借款</th>
                    <td>
                    <#if (customerApplyDetailDTO.shortLoan)??>
                    ${formatMoney(customerApplyDetailDTO.shortLoan!'')}元
                    </#if>
                    </td>
                    <th>借款来源</th>
                    <td>${customerApplyDetailDTO.loanFrom!}</td>
                    <th>借款分类</th>
                    <td>${customerApplyDetailDTO.loanTypeDesc!}</td>
                </tr>
                <tr>
                    <th>其他（补）</th>
                    <td>
                    ${customerApplyDetailDTO.otherExplain!}
                    </td>
                </tr>
                
                </tbody>
            </table>
        </div>
        <div class="pro-title"><span class="pro-title-left">证件信息</span></div>
        <div class="tb-box" style="">
            <table class="check-table">
                <colgroup>
                    <col width="300">
                    <col width="500">
                </colgroup>
                <tbody>
                <tr>
                    <th>组织机构代码证</th>
                    <td>
                        <span class="sp-wid1 sp1">${customerApplyDetailDTO.organizationNo!}</span>
                        <input type="hidden" id="organizationMediaId" value="${customerApplyDetailDTO.organizationMediaId!}" />
                        <a class="comRow-link td-mar organizationMediaId" href="${mediaserver}imageservice?mediaImageId=${customerApplyDetailDTO.organizationMediaId!}&mediaType=video" target="_blank">查看</a>
                    </td>
                </tr>
                <tr>
                    <th>营业执照证</th>
                    <td>
                        <span class="sp-wid1 sp2">${customerApplyDetailDTO.businessLicenceNo!}</span>
                        <input type="hidden" id="businessLicenceMediaId" value="${customerApplyDetailDTO.businessLicenceMediaId!}" />
                        <a class="comRow-link td-mar businessLicenceMediaId" href="${mediaserver}imageservice?mediaImageId=${customerApplyDetailDTO.businessLicenceMediaId!}&mediaType=video" target="_blank">查看</a>
                    </td>
                </tr>
                <tr>
                    <th>税务登记证</th>
                    <td>
                        <span class="sp-wid1 sp3">${customerApplyDetailDTO.taxRegistrationCertificateNo!}</span>
                        <input type="hidden" id="taxRegistrationCertificateMediaId" value="${customerApplyDetailDTO.taxRegistrationCertificateMediaId!}" />
                        <a class="comRow-link td-mar taxRegistrationCertificateMediaId" href="${mediaserver}imageservice?mediaImageId=${customerApplyDetailDTO.taxRegistrationCertificateMediaId!}&mediaType=video" target="_blank">查看</a>
                    </td>
                </tr>
                <tr>
                    <th>法人身份证</th>
                    <td>
                        <span class="sp-wid1 sp4">${customerApplyDetailDTO.legalIdno!}</span>
                        <input type="hidden" id="legalIdnoFrontMediaId" value="${customerApplyDetailDTO.legalIdnoFrontMediaId!}" />
                        <a class="comRow-link td-mar legalIdnoFrontMediaId" href="${mediaserver}imageservice?mediaImageId=${customerApplyDetailDTO.legalIdnoFrontMediaId!}&mediaType=video" target="_blank">查看</a>
                    </td>
                </tr>
                <tr>
                    <th>经营地址</th>
                    <td>${customerApplyDetailDTO.detailAddress!}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${customerApplyDetailDTO.stationAddressDesc!}
                    </td>
                </tr>
                <tr>
                    <th>保证金账户余额</th>
                    <td><#if (customerApplyDetailDTO.bailBalance)??>
                    ${formatMoney(customerApplyDetailDTO.bailBalance!'')}元
                    </#if></td>
                </tr>
                <tr>
                    <th>年检记录</th>
                    <td>${customerApplyDetailDTO.qualifiedInspectionRecordDesc!}</td>
                </tr>
                <tr>
                    <th>申请客户与韵达签署特许经营（加盟）合同到期日</th>
                    <td>
                    <#if (customerApplyDetailDTO.ranchiseContractDeadline)??>
                    ${customerApplyDetailDTO.ranchiseContractDeadline!?string('yyyy-MM-dd')}
                    </#if>
                    </td>
                </tr>
                <tr>
                    <th>法院被执行人信息查询</th>
                    <td>${customerApplyDetailDTO.executeIndividualInfo!}</td>
                </tr>
                <tr>
                    <th>负面信息搜索</th>
                    <td>${customerApplyDetailDTO.negativeNetwork!}</td>
                </tr>
                <tr>
                    <th>其它</th>
                    <td>${customerApplyDetailDTO.manualAuditRemarks!}</td>
                </tr>
                <!--分割线-->
                <tr></tr>
                <tr>
                    <th class="bor-de" style="text-align: left; color:#000;">审核结果</th>
                    <td class="bor-de">
                    </td>
                </tr>
                <tr id="auditResultP">
                    <th>审核结果</th>
                    <td id="auditResult">
                    <input type="hidden" id="accessManualState" value="${customerApplyDetailDTO.accessManualState!}" />
                    </td>
                </tr>
                <tr></tr>
                <tr>
                    <th></th>
                    <td>
                        <a class="form-search-link" href="${basepath}/yundaex/audit/list">返回</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</@hb.htmlBase>
