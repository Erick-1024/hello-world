

<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="审核列表审核" jsFiles=["page/yundaex/audit/kendo.all.min.js","common/formValidator.js","page/yundaex/audit/formValidatorRules.js","js/common/ajaxfileupload.js","common/imageUpload.js","page/yundaex/audit/addInfo.js","js/common/yunda.js", "common/cana.util.js"] cssFiles=["css/monitor.css","css/yunda.css"] localCssFiles=[] 
curTopMenu = "韵达项目" curSubMenu = "审核列表" removeExtHeader = false removeExtFooter = false>

<div class="main-container">
    <div class="bg-box">
        <form method="post" action="submit" id="addInfo-form">
        <div class="pro-title"><span class="pro-title-left">基础信息</span></div>
        <div class="tb-box" style="position:relative;">
            <table class="check-table">
                <colgroup>
                    <col width="12%">
                    <col width="21%">
                    <col width="12%">
                    <col width="21%">
                    <col width="12%">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>申请日期</th>
                    <td>
                    <#if (customerApplyDetailDTO.applyDate)??>
                    ${customerApplyDetailDTO.applyDate!?string("yyyy-MM-dd HH:mm:ss")}
                    </#if>
                    </td>
                    <th>网点名称</th>
                    <td>${customerApplyDetailDTO.stationName!}</td>
                    <th>网点编号</th>
                    <td>${customerApplyDetailDTO.stationNo!}
                    <input type="hidden" id="stationNo" name="stationNo" value="${customerApplyDetailDTO.stationNo!}" />
                    <input type="hidden" id="id" name="id" value="${customerApplyDetailDTO.id!}" />
                    </td>
                </tr>
                <tr>
                    <th>经营地址</th>
                    <td>${customerApplyDetailDTO.detailAddress!}</td>
                    <th>加盟年限</th>
                    <td>
                    <#if (customerApplyDetailDTO.busiLimit)??>
                    ${customerApplyDetailDTO.busiLimit!}年
                    </#if>
                    </td>
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
                </tbody>
            </table>
        </div>
        <div class="pro-title"><span class="pro-title-left">补充资料(韵达)</span></div>
        <div class="tb-box" style="">
            <table class="check-table">
                <colgroup>
                    <col width="12%">
                    <col width="21%">
                    <col width="12%">
                    <col width="21%">
                    <col width="12%">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>打款账户账号</th>
                    <td><input onkeyup="this.value=this.value.replace(/\D/g,'').replace(/....(?!$)/g,'$& ')" type="text" class="onlyNum" name="payAccount" id="payAccount" value="${customerApplyDetailDTO.payAccount!}" /></td>
                    <th>账户人名称</th>
                    <td><input type="text" name="payAccountName" id="payAccountName" value="${customerApplyDetailDTO.payAccountName!}" /></td>
                    <th>开户行</th>
                    <td>
                    <input type="hidden" name="payAccountBankName" class="payAccountBankName" value="${customerApplyDetailDTO.payAccountBankName!}"/>
                     <select class="selectWrap bankName"  name="bankName" id="payAccountBankName" data-role="dropdownlist" style="width: 178px;">
								<option value="">--请选择银行名称--</option>
								<option value="中国农业银行">中国农业银行</option>
                            	<option value="中国工商银行">中国工商银行</option>
								<option value="中国建设银行">中国建设银行</option>
								<option value="城市商业银行">城市商业银行</option>
								<option value="中国银行">中国银行</option>
								<option value="中国邮政储蓄银行">中国邮政储蓄银行</option>
								<option value="恒丰银行">农村商业银行</option>
								<option value="交通银行">交通银行</option>
								<option value="招商银行">招商银行</option>
								<option value="中信银行">中信银行</option>
								<option value="兴业银行">兴业银行</option>
								<option value="中国光大银行">中国光大银行</option>
								<option value="上海浦东发展银行">上海浦东发展银行</option>
								<option value="中国民生银行">中国民生银行</option>
								<option value="广东发展银行">广东发展银行</option>
								<option value="华夏银行">华夏银行</option>
								<option value="平安银行">平安银行</option>
								<option value="恒丰银行">恒丰银行</option>
								<option value="渤海银行">渤海银行</option>
                            </select>
                    </td>
                    
                </tr>
                <tr>
                    <th>省份</th>
                    <td>
                    <input type="hidden" name="bankProvince" class="bankProvince" value="${customerApplyDetailDTO.bankProvince!}"/>
                    <select class="selectWrap bankProvince" name="province" id="bankProvince" data-role="dropdownlist" style="width: 178px;">
                                <option value="">--请选择省份名称--</option>
                                <option value="北京市">北京市</option>
                                <option value="上海市">上海市</option>
								<option value="天津市">天津市</option>
                                <option value="广东省">广东省</option>
								<option value="河北省">河北省</option>
								<option value="山西省">山西省</option>
								<option value="内蒙古自治区">内蒙古自治区</option>
								<option value="辽宁省">辽宁省</option>
								<option value="吉林省">吉林省</option>
								<option value="黑龙江省">黑龙江省</option>
								<option value="江苏省">江苏省</option>
								<option value="浙江省">浙江省</option>
								<option value="安徽省">安徽省</option>
								<option value="福建省">福建省</option>
								<option value="江西省">江西省</option>
								<option value="山东省">山东省</option>
								<option value="河南省">河南省</option>
								<option value="湖北省">湖北省</option>
								<option value="湖南省">湖南省</option>
								<option value="广西壮族自治区">广西壮族自治区</option>
								<option value="海南省">海南省</option>
								<option value="四川省">四川省</option>
								<option value="重庆市">重庆市</option>
								<option value="贵州省">贵州省</option>
								<option value="云南省">云南省</option>
								<option value="西藏自治区">西藏自治区</option>
								<option value="陕西省">陕西省</option>
								<option value="甘肃省">甘肃省</option>
								<option value="青海省">青海省</option>
								<option value="宁夏回族自治区">宁夏回族自治区</option>
								<option value="新疆维吾尔族自治区">新疆维吾尔族自治区</option>
								<option value="台湾">台湾</option>
								<option value="香港">香港</option>
								<option value="澳门">澳门</option>
                            </select>
                    </td>
                    <th>城市</th>
                    <td>
                    <input type="hidden" name="bankCity" class="bankCity" value="${customerApplyDetailDTO.bankCity!}"/>
                    <input class="selectWrap bankCity" name="city" id="bankCity" style="width: 178px;">
                    </td>
                    <th>开户行地址</th>
                    <td>
                    <input name="lianHangNo" class="lianHangNo" id="lianHangNo" value="${customerApplyDetailDTO.lianHangNo!}" style="width:260px;"/>
                    <input type="hidden" name="payAccountAddress" id="payAccountAddress" value="${customerApplyDetailDTO.payAccountAddress!}"/>
                    </td>
                    
                </tr>
                <tr>
                    <th>保证金账户余额</th>
                    <td><input type="text" class="onlyMoney" name="bailBalance" id="bailBalance" pattern="^[-]?[0-9]+([.][0-9]+)?$" data-pattern-msg="数据不合法" value="${customerApplyDetailDTO.bailBalance!}"> 元</td>
                   
                    <th>韵达评价</th>
                    <td>
                    <input type="hidden" class="yundaexJudge" value="${customerApplyDetailDTO.yundaexJudge!}" />
                    <select name="yundaexJudge" class="yundaexJudge" id="yundaexJudge" style="width: 178px;" data-role="dropdownlist">
                        <#list yundaexJudge as judge>
                        <#if (customerApplyDetailDTO.yundaexJudge)??>
                        <option value="${judge}" <#if customerApplyDetailDTO.yundaexJudge==judge> selected="selected" </#if>>${judge.desc()}</option>
                        <#else>
                           <option value="${judge}">${judge.desc()}</option>
                         </#if>
                        </#list>
                    </select>    
                    </td> 
                </tr>
                </tbody>
            </table>
        </div>
        <div class="pro-title"><span class="pro-title-left">补充资料(网点)</span></div>
        <div class="tb-box" style="">
            <table class="check-table">
                <colgroup>
                <!--
                    <col width="100">
                    <col width="100">
                    <col width="80">
                    <col width="100">
                    <col width="80">
                    <col width="200">
                    -->
                    <col width="12%">
                    <col width="21%">
                    <col width="12%">
                    <col width="21%">
                    <col width="12%">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>联系人邮箱</th>
                    <td><input type="text" class="email" name="custEmail" id="custEmail" value="${customerApplyDetailDTO.custEmail!}"></td>
                    <th>实际控制人名称</th>
                    <td><input type="text" name="controller" id="controller" value="${customerApplyDetailDTO.controller!}"></td>
                    <th>实际控制人籍贯</th>
                    <td>
                    <input type="hidden" class="controllerOrigin" value="${customerApplyDetailDTO.controllerOrigin!}" />
                    <select class="selectWrap" name="controllerOrigin" id="controllerOrigin" data-role="dropdownlist" style="width: 178px;">
                                <option value="上海市">上海市</option>
                                <option value="北京市">北京市</option>
								<option value="天津市">天津市</option>
                                <option value="广东省">广东省</option>
								<option value="河北省">河北省</option>
								<option value="山西省">山西省</option>
								<option value="内蒙古自治区">内蒙古自治区</option>
								<option value="辽宁省">辽宁省</option>
								<option value="吉林省">吉林省</option>
								<option value="黑龙江省">黑龙江省</option>
								<option value="江苏省">江苏省</option>
								<option value="浙江省">浙江省</option>
								<option value="安徽省">安徽省</option>
								<option value="福建省">福建省</option>
								<option value="江西省">江西省</option>
								<option value="山东省">山东省</option>
								<option value="河南省">河南省</option>
								<option value="湖北省">湖北省</option>
								<option value="湖南省">湖南省</option>
								<option value="广西壮族自治区">广西壮族自治区</option>
								<option value="海南省">海南省</option>
								<option value="四川省">四川省</option>
								<option value="重庆市">重庆市</option>
								<option value="贵州省">贵州省</option>
								<option value="云南省">云南省</option>
								<option value="西藏自治区">西藏自治区</option>
								<option value="陕西省">陕西省</option>
								<option value="甘肃省">甘肃省</option>
								<option value="青海省">青海省</option>
								<option value="宁夏回族自治区">宁夏回族自治区</option>
								<option value="新疆维吾尔族自治区">新疆维吾尔族自治区</option>
								<option value="台湾">台湾</option>
								<option value="香港">香港</option>
								<option value="澳门">澳门</option>
                            </select>
                    </td>
                </tr>
                <tr>
                    <th>实际控制人身份证号</th>
                    <td><input type="text" name="controllerIdno" id="controllerIdno" value="${customerApplyDetailDTO.controllerIdno!}"></td>
                    <th>实际控制人邮箱</th>
                    <td><input type="text" class="email" name="controllerEmail" id="controllerEmail" value="${customerApplyDetailDTO.controllerEmail!}"></td>
                    <th>实际控制人手机号码</th>
                    <td><input type="text" class="phone onlyNum" name="controllerPhone" id="controllerPhone" value="${customerApplyDetailDTO.controllerPhone!}"></td>
                </tr>
                <tr>
                    <th>实际控制人与法人代表是否是同一人</th>
                    <td>
                        <input type="hidden" class="controllerIsLegal" value="${customerApplyDetailDTO.controllerIsLegal!}" >
                        <label class="choose-one"><input type="radio" value="1" id="controllerIsLegal1" name="controllerIsLegal" class="input-radio controllerIsLegal" checked="checked">是</label>
                        <label class="rad-mar choose-one"><input type="radio" value="0" id="controllerIsLegal0" name="controllerIsLegal" class="input-radio controllerIsLegal">否</label>
                    </td>
                    <th>开户人</th>
                    <td>
                        <input type="hidden" class="accountOwner" value="${customerApplyDetailDTO.accountOwner!}">
                        
                        <label class="choose-two"><input type="radio" id="LEGAL" value="LEGAL" name="accountOwner" class="input-radio">法人代表</label>
                        <label class="rad-mar choose-two"><input type="radio" id="CONTROLLER" value="CONTROLLER" name="accountOwner" class="input-radio" checked="checked">实际控制人</label>
                        <label class="choose-two"><input type="radio" id="OTHER" value="OTHER" name="accountOwner" class="input-radio">其它</label>
                        <label class="choose-two"><input type="radio" id="COMPANY" value="COMPANY" name="accountOwner" class="input-radio">公司</label>
                        
                    </td>
                    
                </tr>
                <tr class="hideLegal" style="display:none;">
                    <th>法人代表名称</th>
                    <td><input type="text" name="legalPerson" id="legalPerson" value="${customerApplyDetailDTO.legalPerson!}"></td>
                    <th>法人代表邮箱</th>
                    <td><input type="text" class="email" name="legalEmail" id="legalEmail" value="${customerApplyDetailDTO.legalEmail!}"></td>
                    <th>法人代表手机号码</th>
                    <td><input type="text" class="phone onlyNum" name="legalPhone" id="legalPhone" value="${customerApplyDetailDTO.legalPhone!}"></td>
                </tr>
                <tr class="hideAccountOwner" style="display:none;">
                    <th>开户人名称</th>
                    <td><input type="text" name="accountOwnerName" id="accountOwnerName" value="${customerApplyDetailDTO.accountOwnerName!}"></td>
                    <th>开户人邮箱</th>
                    <td><input type="text" class="email" name="accountOwnerEmail" id="accountOwnerEmail" value="${customerApplyDetailDTO.accountOwnerEmail!}"></td>
                    <th>开户人手机号码</th>
                    <td><input type="text" class="phone onlyNum" name="accountOwnerPhone" id="accountOwnerPhone" value="${customerApplyDetailDTO.accountOwnerPhone!}"></td>
                </tr>
                <tr>
                    <th>年检记录</th>
                    <td>
                    <input type="hidden" class="qualifiedInspectionRecord" value="${customerApplyDetailDTO.qualifiedInspectionRecord!}">
                    <#list yundaexInspectionRecord as inspectionRecord>
                    <#if (customerApplyDetailDTO.qualifiedInspectionRecord)??>
                    <label><input <#if customerApplyDetailDTO.qualifiedInspectionRecord==inspectionRecord>checked</#if> type="radio" value="${inspectionRecord}" id="${inspectionRecord}" name="qualifiedInspectionRecord" class="input-radio">${inspectionRecord.desc()}</label>
                    <#else>
                    <label><input type="radio" value="${inspectionRecord}" id="${inspectionRecord}" name="qualifiedInspectionRecord" class="input-radio">${inspectionRecord.desc()}</label>
                    </#if>
                    </#list>
                    
                    </td>
                    <th>申请客户与韵达签署特许经营（加盟）合同到期日</th>
                    <td>
                    <#if (customerApplyDetailDTO.ranchiseContractDeadline)??>
                    <input type="text" style="width:161px;" value="${(customerApplyDetailDTO.ranchiseContractDeadline!)?string('yyyy-MM-dd')}" class="datepicker startDate hasIcon" readonly name="ranchiseContractDeadline" >
                    <#else>
                    <input type="text" style="width:161px;" value="${customerApplyDetailDTO.ranchiseContractDeadline!}" class="datepicker startDate hasIcon" readonly name="ranchiseContractDeadline" >
                    </#if>
                    </td>
                    <th>网点数量</th>
                    <td><input type="text" name="stationAmount" id="stationAmount" pattern="^[0-9]+$" data-pattern-msg="数据不合法" value="${customerApplyDetailDTO.stationAmount!}"/></td>
                    
                </tr>
                <tr>
                    <th>短期借款</th>
                    <td><input type="text" class="onlyMoney" name="shortLoan" id="shortLoan" pattern="^[0-9]+([.][0-9]+)?$" data-pattern-msg="数据不合法" value="${customerApplyDetailDTO.shortLoan!}"> 元</td>
                    <th>借款来源</th>
                    <td><input type="text" name="loanFrom" id="loanFrom" value="${customerApplyDetailDTO.loanFrom!}"></td>
                    <th>借款类型</th>
                    <td>
                    <input type="hidden" class="loanType" value="${customerApplyDetailDTO.loanType!}"/>
                    <select id="loanType" name="loanType" data-role="dropdownlist" style="width: 178px;">
                                <#list yundaexLoanType as loanType>
                                <#if (customerApplyDetailDTO.loanType)??>
                                <option value="${loanType}" <#if customerApplyDetailDTO.loanType==loanType> selected="selected" </#if>>${loanType.desc()}</option>
                                <#else>
                                <option value="${loanType}">${loanType.desc()}</option>
                                </#if>
                                </#list>
                    </select>
                    </td>
                </tr>
                
                <tr>
                    <th>代理资质</th>
                    <td><input type="text" name="agentQualification" id="agentQualification" value="${customerApplyDetailDTO.agentQualification!}"></td>
                    <th>是否有淘宝、天猫订单</th>
                    <td>
                        <input type="hidden" class="whetherTbOrder" value="${customerApplyDetailDTO.whetherTbOrder!}">
                        <label class="choose-three"><input type="radio" id="whetherTbOrder1" value="1" name="whetherTbOrder" class="input-radio">是</label>
                        <label class="rad-mar choose-three"><input type="radio" id="whetherTbOrder0" value="0" name="whetherTbOrder" class="input-radio" checked="checked">否</label>
                    </td>
                    <th><span class="hideTbOrderRatio" style="display:none;">天猫、淘宝订单占比</span></th>
                    <td><span class="hideTbOrderRatio" style="display:none;"><input type="text" class="onlyMoney" id="tbOrderRatio" name="tbOrderRatio" value="${customerApplyDetailDTO.tbOrderRatio!}"> %</span></td>
                    <th></th>
                    <td></td>
                </tr>
                
                <tr>
                    <th>组织机构代码证</th>
                    <td>
                        <input type="text" name="organizationNo" id="organizationNo" class="left-f" value="${customerApplyDetailDTO.organizationNo!}">
                        <div class="in-up-box left-f">
                            <input type="file" class="up-input frontage imageUpload" value="" name="image" id="organizationMedia" accept="image/jpeg, image/png, application/pdf"/>
                            <a class="in-up-txt" href="javascript:void(0);">上传</a>
                            <input class="image" id="organizationMediaId1" name="organizationMediaId" type="hidden" value="${customerApplyDetailDTO.organizationMediaId!}">
                            <span class="tageNotice"><span  class="k-invalid-msg" data-for="organizationCodeCertificateMediaId"></span>
                            <input type="hidden" class="echo" value="0">
                            </span>
                        </div>
                    </td>
                    <th>营业执照证</th>
                    <td>
                        <input type="text" name="businessLicenceNo" id="businessLicenceNo" class="left-f" value="${customerApplyDetailDTO.businessLicenceNo!}">
                        <div class="in-up-box left-f">
                            <input type="file" class="up-input frontage imageUpload" value="" name="image" id="businessLicenceMedia" accept="image/jpeg, image/png, application/pdf"/>
                            <a class="in-up-txt" href="javascript:void(0);">上传</a>
                            <input class="image" id="businessLicenceMediaId1" name="businessLicenceMediaId" type="hidden" value="${customerApplyDetailDTO.businessLicenceMediaId!}">
                            <span class="tageNotice"><span class="k-invalid-msg" data-for="businessLicenceMediaId"></span>
                            <input type="hidden" class="echo" value="0">
                            </span>
                        </div>
                    </td>
                    <th>税务登记证</th>
                    <td>
                        <input type="text" name="taxRegistrationCertificateNo" id="taxRegistrationCertificateNo" class="left-f" value="${customerApplyDetailDTO.taxRegistrationCertificateNo!}">
                        <div class="in-up-box left-f">
                            <input type="file" class="up-input frontage imageUpload" name="image" id="taxRegistrationCertificateMedia" accept="image/jpeg, image/png, application/pdf"/>
                            <a class="in-up-txt" href="javascript:void(0);">上传</a>
                            <input class="image" id="taxRegistrationCertificateMediaId1" name="taxRegistrationCertificateMediaId" type="hidden" value="${customerApplyDetailDTO.taxRegistrationCertificateMediaId!}">
	                        <span class="tageNotice"><span class="k-invalid-msg" data-for="taxRegistrationCertificateMediaId"></span>
	                        <input type="hidden" class="echo" value="0">
	                        </span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>法人代表身份证</th>
                    <td>
                        <input type="text" name="legalIdno" id="legalIdno" class="left-f" value="${customerApplyDetailDTO.legalIdno!}">
                        <div class="in-up-box left-f">
                            <input type="file" class="up-input frontage imageUpload" value="" name="image" id="legalIdnoFrontMedia" accept="image/jpeg, image/png, application/pdf"/>
                            <a class="in-up-txt" href="javascript:void(0);">上传正面</a>
                            <input class="image" id="legalIdnoFrontMediaId1" name="legalIdnoFrontMediaId" type="hidden" value="${customerApplyDetailDTO.legalIdnoFrontMediaId!}">
	                        <span class="tageNotice"><span class="k-invalid-msg" data-for="legalPersonIdentityCardFrontMediaId"></span>
	                        <input type="hidden" class="echo" value="0">
	                        </span>
                        </div>
                        <div class="in-up-box left-f">
                            <input type="file" class="up-input frontage imageUpload" value="" name="image" id="legalIdnoBackMedia" accept="image/jpeg, image/png, application/pdf"/>
                            <a class="in-up-txt" href="javascript:void(0);">上传反面</a>
                            <input class="image" id="legalIdnoBackMediaId1" name="legalIdnoBackMediaId" type="hidden" value="${customerApplyDetailDTO.legalIdnoBackMediaId!}">
	                        <span class="tageNotice"><span class="k-invalid-msg" data-for="legalPersonIdentityCardBackMediaId"></span>
	                        <input type="hidden" class="echo" value="0">
	                        </span>
                        </div>
                    </td>
                    <th>上传补充资料附件</th>
                    <td>
                        <div class="in-up-box left-f">
                            <input type="file" class="up-input frontage fileUpload" value="" name="file" id="additionInformationMedia" accept=".docx,.doc,.xls,.xlsx"/>
                            <a class="in-up-txt upadditionInfo" href="javascript:void(0);">上传</a>
                            <input class="file" id="additionInformationMediaId" name="additionInformationMediaId" value="${customerApplyDetailDTO.additionInformationMediaId!}" type="hidden">
                            <input type="hidden" class="echo" value="0">
                        </div>
                        <div class="in-up-name left-f upload-warning" style="width:200px;color:#999">支持xls、xlsx、doc、docx格式</div>
                    </td>
                    <th></th>
                    <td></td>
                </tr>
                
                <tr>
                    <th>其它</th>
                    <td>
                        <textarea class="txt-are" name="otherExplain" value="${customerApplyDetailDTO.otherExplain!}"></textarea>
                    </td>
                    <th>
                    </th>
                    <td></td>
                    <th></th>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="bottom-btn">
            <div class="b-btn-box">
                <a class="form-search-link addInfo-submit" href="javascript:void(0);">提交</a>
                <a class="form-search-link" href="${basepath}/yundaex/audit/audit?id=${customerApplyDetailDTO.id!}">返回</a>
            </div>
        </div>
        </form>
    </div>
</div>

<!--提示框-->
<div class="popup-box" style="display: none;">
    <div class="popup-out-ch"></div>
    <div class="popup-in-ch">
        <!--提示标题-->
        <div class="popup-header">提示标题</div>
        <div class="popup-list-box">
            <div class="popup-content">
                <i class="popup-img notice-icon01"></i><span class="pop-con">提示内容</span>
                <!--<i class="popup-img" style="display:none;"></i><span class="pop-con">提示内容</span>-->
            </div>
            <div class="popup-foot">
                <a class="popup-btn" href="javascript:void(0);" onclick="closePop();">返回</a>
            </div>
        </div>
    </div>
</div>
<script>
	function clearNoNum(obj){   
    	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
    	obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是. 
    	obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.   
    	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	}
</script>
</@hb.htmlBase>
