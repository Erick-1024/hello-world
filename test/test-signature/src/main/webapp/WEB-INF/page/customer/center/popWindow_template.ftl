<!--修改登录密码弹窗模板-->
<script id="template-editPwd" type="text/x-kendo-template">
    <div class="dlg-wrapper">
    <form id="editPwd-form" name="editPwd-form">
        <table class="dlg-table">
            <colgroup>
                <col width="150">
                <col width="300">
            </colgroup>
            <tbody>
            <tr>
                <td>当前密码</td>
                <td>
                    <input name="old_pwd" id="old_pwd" class="login_old_pwd password" type="password">
                </td>
            </tr>
            <tr>
                <td>新密码</td>
                <td>
                    <input name="new_pwd" id="new_pwd" class="login_new_pwd_1 password" type="password">
                </td>
            </tr>
            <tr>
                <td>新密码</td>
                <td>
                    <input name="new_pwd_sec" id="new_pwd_sec" class="login_new_pwd_2 password" type="password">
                </td>
            </tr>
            <tr>
                <td></td>
                <td style="padding-top:20px;" class>
                    <a class="default-link confirm-link" id="confirmEditPwd"  href="javascript:void(0);">确认</a>
                    <a class="default-link back-link" href="javascript:void(0);">取消</a>
                </td>
            </tr>
            </tbody>
        </table>
        </form>
    </div>
</script>

<!--设置支付密码弹窗模板-->
<script id="template-payPwd" type="text/x-kendo-template">
    <div class="dlg-wrapper">
    	<form id="payPwd-form" name="payPwd-form">
        <table class="dlg-table">
            <colgroup>
                <col width="150">
                <col width="300">
            </colgroup>
            <tbody>
            <tr>
                <td>登录密码</td>
                <td>
                    <input name="old_pay" id="old_pay" class="login_old_pwd password" type="password"/>
                </td>
            </tr>
            <tr>
                <td>新支付密码</td>
                <td>
                    <input name="new_pay" id="new_pay" class="login_new_pwd_1 password" type="password">
                </td>
            </tr>
            <tr>
                <td>确认支付密码</td>
                <td>
                    <input name="new_pay_sec" id="new_pay_sec" class="login_new_pwd_2 password" type="password">
                </td>
            </tr>
            <tr>
                <td></td>
                <td style="padding-top:20px;">
                    <a class="default-link confirm-link" id="confirmPayPwd" href="javascript:void(0);">确认</a>
                    <a class="default-link back-link" href="javascript:void(0);">取消</a>
                </td>
            </tr>
            </tbody>
        </table>
        </form>
    </div>
</script>

<!--修改支付密码弹窗模板-->
<script id="template-editPayPwd" type="text/x-kendo-template">
    <div class="dlg-wrapper">
    <form id="editPayPwd-form" name="editPayPwd-form">
        <table class="dlg-table">
            <colgroup>
                <col width="150">
                <col width="300">
            </colgroup>
            <tbody>
            <tr>
                <td>原支付密码</td>
                <td>
                    <input name="old_edit_pay" id="old_edit_pay" class="login_old_pwd password" type="password"/>
                </td>
            </tr>
            <tr>
                <td>新支付密码</td>
                <td>
                    <input name="new_edit_pay" id="new_edit_pay" class="login_new_pwd_1 password" type="password">
                </td>
            </tr>
            <tr>
                <td>确认支付密码</td>
                <td>
                    <input name="new_edit_pay_sec" id="new_edit_pay_sec" class="login_new_pwd_2 password" type="password">
                </td>
            </tr>
            <tr>
                <td></td>
                <td style="padding-top:20px;">
                    <a class="default-link confirm-link" id="confirmEditPayPwd" href="javascript:void(0);">确认</a>
                    <a class="default-link back-link" href="javascript:void(0);">取消</a>
                </td>
            </tr>
            </tbody>
        </table>
        </form>
    </div>
</script>
    <!--组织机构代码弹窗模板-->
<script id="template-updateState" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <table class="dlg-table">
            <colgroup>
                <col width="200px">
                <col width="250px">
                <col width="250px">
            </colgroup>
            <tbody>
            <tr>
                <td>上传组织机构代码证</td>
                <td class="tageBox">
                    <input type="file" class="frontage" name="image" id="organizationCodeCertificateMedia" accept="image/jpeg, image/png">
                    <a class="frontage-link" href="">选择图片</a>
                    <a class="tageNotice registe-link" id="organizationCodeCertificateMediaId" href="javascript:void(0);">未上传</a><br>
	                <input class="image  image_ori" name="organizationCodeCertificateMediaId" value="" type="hidden">
	                <span data-for="organizationCodeCertificateMediaId" class="k-invalid-msg"></span>
                </td>
                
            </tr>
            <tr>
                <td></td>
                <td style="padding-top:30px;">
                    <a class="default-link confirm-link" id="confirm_ori_media" href="javascript:void(0);">确认</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</script>
<!--营业执照弹窗模板-->
<script id="template-updateLicense" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <table class="dlg-table">
            <colgroup>
                <col width="200px">
                <col width="250px">
                <col width="250px">
            </colgroup>
            <tbody>
            <tr>
                <td>上传营业执照</td>
                <td class="tageBox">
                    <input type="file" class="frontage" name="image" id="businessLicenceMedia" accept="image/jpeg, image/png">
                    <a class="frontage-link" href="">选择图片</a>
                    <a class="tageNotice registe-link" id="businessLicenceMediaId" href="javascript:void(0);">未上传</a><br>
	                <input class="image image_bus" name="businessLicenceMediaId" value="" type="hidden">
	                <span data-for="businessLicenceMediaId" class="k-invalid-msg"></span>
                </td>
            </tr>
            <tr>
                <td></td>
                <td style="padding-top:30px;">
                    <a class="default-link confirm-link" id="confirm_bus_media" href="javascript:void(0);">确认</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</script>
<!--税务登记证弹窗模板-->
<script id="template-updateTax" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <table class="dlg-table">
            <colgroup>
                <col width="200px">
                <col width="250px">
                <col width="250px">
            </colgroup>
            <tbody>
            <tr>
                <td>上传税务登记证</td>
                <td class="tageBox">
                    <input type="file" class="frontage" name="image" id="taxRegistrationCertificateMedia" accept="image/jpeg, image/png">
                    <a class="frontage-link" href="">选择图片</a>
                    <a class="tageNotice registe-link" id="taxRegistrationCertificateMediaId" href="javascript:void(0);">未上传</a><br>
	                <input class="image image_tax" name="taxRegistrationCertificateMediaId" value="" type="hidden">
	                <span data-for="taxRegistrationCertificateMediaId" class="k-invalid-msg"></span>
                </td>
            </tr>
            <tr>
                <td></td>
                <td style="padding-top:30px;">
                    <a class="default-link confirm-link" id="confirm_tax_media" href="javascript:void(0);">确认</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</script>
<!-- 修改姓名-->
<script id="template-editName" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <table class="dlg-ruleTable">
            <colgroup>
                <col width="40%">
                <col width="60%">
            </colgroup>
            <tbody>
            <tr>
                <td class="repayDetail-til">姓名</td>
                <td>
                    <input name="cName" id="cName" type="text" style="width:180px;" value="">
                </td>
            </tr>
            </tbody>
        </table>
        <div class="dlg-wrapper-foot">
            <a class="default-link confirm-link " id="comfirm_name" href="javascript:void(0);">确定</a>
            <a class="default-link back-link" href="javascript:void(0);">返回</a>
        </div>
    </div>
</script>
<!-- 修改职称-->
<script id="template-editJob" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <table class="dlg-ruleTable">
            <colgroup>
                <col width="40%">
                <col width="60%">
            </colgroup>
            <tbody>
            <tr>
                <td class="repayDetail-til">职位</td>
                <td>
                    <input name="jobTitle" id="jobTitle" type="text" style="width:180px;" value="">
                </td>
            </tr>
            </tbody>
        </table>
        <div class="dlg-wrapper-foot">
            <a class="default-link confirm-link " id="comfirm_job" href="javascript:void(0);">确定</a>
            <a class="default-link back-link" href="javascript:void(0);">返回</a>
        </div>
    </div>
</script>
<!--修改手机号弹窗模板-->
<script id="template-editCellhpone" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <table class="dlg-ruleTable">
            <colgroup>
                <col width="40%">
                <col width="60%">
            </colgroup>
            <tbody>
            <tr>
                <td class="repayDetail-til">手机号码</td>
                <td>
                    <input id="tel" name="tel" type="text" style="width:180px;" value="">
                </td>
            </tr>
            </tbody>
        </table>
        <div class="dlg-wrapper-foot">
            <a class="default-link confirm-link " id="comfirm_tel" href="javascript:void(0);">确定</a>
            <a class="default-link back-link" href="javascript:void(0);">返回</a>
        </div>
    </div>
</script>
<!--修改电子邮箱弹窗模板-->
<script id="template-editEmail" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <table class="dlg-ruleTable">
            <colgroup>
                <col width="40%">
                <col width="60%">
            </colgroup>
            <tbody>
            <tr>
                <td class="repayDetail-til">电子邮箱</td>
                <td>
                    <input name="email" id="email" type="text" style="width:180px;" value="">
                </td>
            </tr>
            </tbody>
        </table>
        <div class="dlg-wrapper-foot">
            <a class="default-link confirm-link " id="comfirm_mail" href="javascript:void(0);">确定</a>
            <a class="default-link back-link" href="javascript:void(0);">返回</a>
        </div>
    </div>
</script>
<!--提示弹窗模板-->
<script id="template-saveFinished" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice"></span>
            <span id="notice-content" class="notice-content"></span>
        </div>
    </div>
</script>
