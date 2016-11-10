 <div class="userCenter-content hidden">
                <table>
                    <colgroup>
                        <col width="250">
                        <col width="200">
                        <col width="300">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td>全部账户总余额</td>
                        <td>
                            <span class="allBalances"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>一般账户（未监管）总余额</td>
                        <td>
                            <span class="generalNoSupervisionBalances"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>一般账户（未监管）数量</td>
                        <td>
                            <span class="generalNoSupervisionNumber"></span>
                        </td>
                    </tr>
                    <#if userType!='CANA'>
                    <tr>
                        <td>一般账户（监管）总余额</td>
                        <td>
                            <span class="generalHaveSupervisionBalances"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>一般账户（监管）数量</td>
                        <td>
                            <span class="generalHaveSupervisionNumber"></span>
                        </td>
                    </tr>
                    <#-- 资金方不显示-->
                    <#if finance??>
                     <tr>
                        <td>专用账户（未监管）总金额</td>
                        <td>
                            <span class="specialNoSupervisionBalances"></span>
                        </td>
                    </tr>
                     <tr>
                        <td>专用账户（未监管）数量</td>
                        <td>
                            <span class="specialNoSupervisionNumber"></span>
                        </td>
                    </tr>
                    </#if>
                    <tr>
                        <td>专用账户（监管）总余额</td>
                        <td>
                            <span class="specialHaveSupervisionBalances"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>专用账户（监管）数量</td>
                        <td>
                            <span class="specialHaveSupervisionNumber"></span>
                        </td>
                    </tr>
                    </#if>
                    <#if authorizeKey('PC_ACCOUNT_NUMBER_INFO_UPDATE_PAYPWD')>
                    <tr class="tr_editPassword"  <#if setPayPwd??><#else> style="display:none;" </#if>>
                        <td>支付密码</td>
                        <td></td>
                        <td>
                            <a class="userCenter-link editPayPwd" href="javascript:void(0);">修改支付密码</a>
                            <a class="userCenter-link forgetPayPwd"  href="javascript:void(0);">忘记密码</a>
                        </td>
                    </tr>
                    </#if>
                    <#if authorizeKey('PC_ACCOUNT_NUMBER_INFO_SET_PAYPWD')>
                     <tr class="tr_setPassword" <#if setPayPwd??>style="display:none;" </#if>>
                        <td>支付密码</td>
                        <td></td>
                        <td>
                            <a class="userCenter-link payPwd" href="javascript:void(0);">设置支付密码</a>
                        </td>
                    </tr>
                    </#if>
                    </tbody>
                </table>
            </div>