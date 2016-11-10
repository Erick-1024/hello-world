<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="上传信息" jsFiles=["js/common/yunda.js", "js/common/ajaxfileupload.js","common/formValidator.js","page/yundaex/personal/putInformation.js"] cssFiles=["css/monitor.css","css/yunda.css"] localCssFiles=[] 
	curTopMenu = "" curSubMenu = "" removeExtHeader = false removeExtFooter = false showMenu=false>

<div class="main-container" style="background: #e5f6fe;">
    <div class="bg-box">
        <div class="out-one" id="max-box" style="padding-bottom:20px;">
            <div class="out-one-header"><#if type.name() == "CONTROLLER">上传实际控制人信息<#elseif type.name() == "ACCOUNT_HOLDER">上传开户人信息<#else>上传身份信息</#if></div>
            <div class="out-list-box">
            	<form method="post" action="${basepath}/yundaex/personal/facade/put" id="submtForm">
	            	<input type="hidden" name="id" value="${id!}">
	                <input type="hidden" name="code" value="${code!}">
	                <div class="out-content">
	                    <table class="contract-table">
	                        <colgroup>
	                            <col width="340px">
	                            <col width="220px">
	                            <col width="280px">
	                        </colgroup>
	                        <tbody>
	                        <tr>
	                            <th>姓名</th>
	                            <td><span>${realName!}</span></td>
	                            <td></td>
	                        </tr>
	                        <tr>
	                            <th>身份证号</th>
	                            <td colspan=2><input type="text" value="" name="residentIdentityCardNo" class="width200"/></td>
	                        </tr>
	                        <tr>
	                            <th>上传手持正反身份证照片</th>
	                            <td>
	                                <div class="in-up-box left-f" style="width:100px;">
	                                	<input type="hidden" name="residentIdentityCardFrontMediaId" value="">
	                                    <input type="file" class="up-input" value="" name="image" accept="image/jpg, image/png" id="residentIdentityCardFrontMedia"/>
	                                    <a class="in-up-txt" href="javascript:void(0);">上传正面</a>
	                                    <a class="in-up-txt2" href="javascript:void(0);" style="display:none" target='_blank'>查看</a>
	                                </div>
	                                <div class="in-up-box left-f" style="width:100px;">
	                                	<input type="hidden" name="residentIdentityCardBackMediaId" value="">
	                                    <input type="file" class="up-input" value="" name="image" accept="image/jpg, image/png" id="residentIdentityCardBackMedia"/>
	                                    <a class="in-up-txt" href="javascript:void(0);">上传反面</a>
	                                    <a class="in-up-txt2" href="javascript:void(0);" style="display:none" target='_blank'>查看</a>
	                                </div>
	                            </td>
	                            <td><span class="waring-msg">上传格式：png、jpg。本人分别手持身份证正反面照片，并能够清晰看到身份证信息。</span></td>
	                        </tr>
	                        <tr>
	                            <th></th>
	                            <td>
	                                <div class="in-up-box left-f" style="width:100px;">
	                                    <a class="in-up-txt front-one" href="javascript:void(0);">示例</a>
	                                </div>
	                                <div class="in-up-box left-f" style="width:100px;">
	                                    <a class="in-up-txt verso-one" href="javascript:void(0);">示例</a>
	                                </div>
	                            </td>
	                            <td></td>
	                        </tr>
	                        </tbody>
	                    </table>
	                </div>
                </form>
            </div>
            <div class="out-foot">
                <a class="popup-btn-two" href="javascript:void(0);" id="confirmSubmit">确认</a>
            </div>
        </div>
    </div>
</div>
<!--身份证正面-->
<script id="template-moniteMild" type="text/x-kendo-template">
    <div class="moniteDetail-wrap">
        <img src="${host}/css/images/verso.jpg" class="card-img">
    </div>
</script>
<!--身份证反面-->
<script id="template-moniteMiddle" type="text/x-kendo-template">
    <div class="moniteDetail-wrap">
        <img src="${host}/css/images/front.jpg" class="card-img">
    </div>
</script>
</@hb.htmlBase>