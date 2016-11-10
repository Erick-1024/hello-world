<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="新建监管关系" jsFiles=["page/account/supervision/create.js"] cssFiles=["css/account.css"] localCssFiles=[] 
	curTopMenu = "账户管理" curSubMenu = "新建监管关系">

<div class="main-container">
    <section class="whiteBg">
        <div class="accountList-wrap">
            <div class="accountList-title">新建监管关系</div>
            <div class="accountList-content">
                <table class="supervise-table">
                    <colgroup>
                        <col width="200px">
                        <col width="400px">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td class="acu-title">${(userType.desc())!}企业名称</td>
                        <td>
                            <span>${(customer.companyName)!}</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="acu-title"><span class="redFalg">*</span>${(supervisorUserType.desc())!}企业名称</td>
                        <td>
                            <input name="supervisorName" type="text" style="width:230px;"
                            	supervisorRule required data-required-msg="企业名称不能为空" />
                        </td>
                    </tr>
                    <tr>
                        <td class="acu-title"><span class="redFalg">*</span>银行监管账号</td>
                        <td>
                            <input id="combobox" name="accountNo" type="text"
                            	style="width:230px;" placeholder="输入账号" class="bankCard"
                            	accountNoRule required data-required-msg="银行监管账号不能为空" />
                            <input name="accountId" type="hidden" />
                        </td>
                    </tr>
                    <tr>
                        <td class="acu-title">银行账户名称</td>
                        <td>
                            <span id="comboboxVal"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="acu-title"><span class="redFalg">*</span>设为默认还款账户</td>
                        <td>
                            <div class="radioContent" id="setDefaultRepayment">
                                <label class="radio">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">是</span>
                                </label>
                                <label class="radio active">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">否</span>
                                </label>
                                <a style="margin-left:20px" class="common-tooltip" title="未设置还款账号的放款信息均通过默认还款账号执行扣款。请谨慎选择。" href="javascript: void(0);">提示</a>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="acu-title"><span class="redFalg">*</span>关联专用账号</td>
                        <td>
                            <div class="radioContent" id="relateSpecialAccounts">
                                <label class="radio">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">是</span>
                                </label>
                                <label class="radio active">
                                    <span class="radioIcon"></span>
                                    <span class="labelFonts">否</span>
                                </label>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="SuperviseAct" style="display: none;">
                	<#if userType="FACTOR">
                    <a class="supervise-add" href="javascript:void(0);">新增</a>
                    </#if>
					<#if userType="FINACE">
                    <a class="supervise-choose" href="javascript:void(0);">选择</a>
                    </#if>
                </div>
                <div class="addSupervise-table" style="display: none;">
                    <table class="addSupervise-head">
                        <colgroup>
                            <col width="50px">
                            <col width="250px">
                            <col width="200px">
                            <col width="200px">
                            <col width="80px">
                            <col width="17px">
                        </colgroup>
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>银行账号</th>
                            <th>账户名称</th>
                            <th>买方名称</th>
                            <th>操作</th>
                            <th></th>
                        </tr>
                        </thead>
                    </table>
                    <div class="addSupervise-body">
                        <table id="addSupervise">
                            <colgroup>
                                <col width="50px">
                                <col width="250px">
                                <col width="200px">
                                <col width="200px">
                                <col width="80px">
                            </colgroup>
                            <tbody></tbody>
                        </table>
                    </div>
                </div>
                <div class="superive-foot">
                    <a class="default-link confirm-link superive-confirm" href="javascript:void(0);">提交</a>
                    <a class="default-link back-link" href="${basepath!}/account/list">返回</a>
                </div>

            </div>
        </div>
    </section>
</div>

<#include "chooseSpecialAccountTemplate.ftl"/>
<#include "../tipBoxTemplate.ftl"/>
<!--审批弹窗模板-->
<script id="template-confirmTips" type="text/x-kendo-template">
    <div class="dlg-wrapper" id="confirm-box-window">
        <div class="dlg-del-row">
            <table class="dlg-rowTable">
                <tr>
                    <td class="dlg-row-left">
                        <span class="dlg-notice notice-icon01"></span>
                    </td>
                    <td class="dlg-row-right">
                        <span class="notice-content"></span>
                    </td>
                </tr>
            </table>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>

</@hb.htmlBase>