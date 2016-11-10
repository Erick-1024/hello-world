<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="额度管理" jsFiles=["common/formValidator.js","page/asset/credit/creditManage.js","page/asset/credit/formValidatorRules.js","common/dateutil.js","page/asset/credit/creditAplay.js","page/asset/credit/creditUpdate.js"] cssFiles=["css/project.css","css/monitor.css","css/valifrom.css"] localCssFiles=[] 
	curTopMenu = "基础资产信息" curSubMenu = "额度管理" removeExtHeader = false removeExtFooter = false>
<div class="pro-bg" style="background: #fff;">
    <div class="pro-box-bg">
        <div class="first-title">客户信息</div>
        <input type="hidden" id ="customerId" name ="customerId" value="${customerId!}" style="width:200px;">
         <input type="hidden" id ="creditId" name ="creditId" value="${creditId!}" style="width:200px;">
        <form>
            <table class="client-tb">
                <colgroup>
                    <col width="100px">
                    <col width="150px">
                    <col width="100px">
                    <col width="150px">
                    <col width="100px">
                    <col width="150px">
                    <col width="100px">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
					<th>客户名称</th>
                    <td>${customerName!}</td>
                    <th>客户类型</th>
                    <td>${customerType!}</td>
                    <th>总授信额度</th>
                    <td>￥${formatMoney(totalLimit!)}</td>
                    <th>总额度</th>
                    <td>￥${formatMoney(availableLimit!)}</td>
                </tr>
                <tr></tr>
                <tr>
                <#if authorizeKey("BAI_CM_CREATE")>
                    <td><a class="form-search-btn limit-add" href="javascript:void(0);">申请额度</a></td>
				</#if>
                </tr>
                </tbody>
            </table>
        </form>
        <a class="form-search-btn update-credit" href="javascript:void(0);" style ="display:none">申请额度</a>
        <div class="monitor-grid" id="monitorList-grid"></div>
    </div>
</div>
<!--申请额度弹窗-->
<script id="template-resetPwd-five" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <form class="out-new-form" id ="credit-aplay-form">
                <table class="client-tb">
                    <colgroup>
                        <col width="100">
                        <col width="200">
                        <col width="100">
                        <col width="200">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>客户名称</th>
                        <td>${customerName!}</td>
                        <th>客户类型</th>
                        <td>${customerType!}</td>
                    </tr>
                    <tr>
                        <th>业务合同号</th>
                        <td><input type="text" id ="businessContractNo" name ="businessContractNo" style="width:200px;"></td>
                        <th></th>
                        <td></td>
                    </tr>
                    <tr>
                        <th>额度种类</th>
                        <td>
                            <select class="s-mode" id ="creditMode" name ="creditMode" style="width:200px;"  data-role="dropdownlist">
                                <#list creditModes as creditMode>		
									 	<option value=${creditMode.name()!}>${creditMode.desc()!}</option>		
								</#list>
                            </select>
                        </td>
                        <th>币种</th>
                        <td>
                            <select class="s-mode" id ="currency" name ="currency" style="width:200px;"  data-role="dropdownlist">
                                <#list currencys as currency>		
									 	<option value=${currency.name()!}>${currency.desc()!}</option>		
								</#list>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>申请金额</th>
	                        <td>
		                        <label class="client-unit-box">
		                        	<input class ="moneyNum" type="text" id ="totalLimit" name ="totalLimit" style="width:200px;" onkeyup="clearNoNum(this)" validationMessage="申请金额不能为空" required>
		                        	<div class="client-unit-two">元</div>
		                        </label>
	                        </td>
                        <th>申请费用</th>
                        <td>
                        <label class="client-unit-box">
                        <input class ="moneyNum" type="text" id ="expense" name ="expense" style="width:200px;" onkeyup="clearNoNum(this)" validationMessage="申请费用不能为空">
                        <div class="client-unit-two">元</div></td>
                    </tr>
                    <tr>
                        <th>生效日期</th>
                        <td><input type="text" id ="effectiveDate" name ="effectiveDate" class="time-one data-style hasIcon" style="width:200px;" validationMessage="生效日期不能为空" required></td>
                        <th>到期日期</th>
                        <td><input type="text" id ="dueDate" name ="dueDate" class="time-two data-style hasIcon" style="width:200px;" validationMessage="到期日期不能为空" required></td>
                    </tr>
                    </tbody>
                </table>
            </form>
                <input type="hidden" class="financing-index-id" value="">
        </div>
        <div class="dlg-del-row">
            <input class="form-search-link" type="button" value="确定" id="submit" style="border:0;">
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
<!--修改额度弹窗-->
<script id="template-resetPwd-ch" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <form class="out-new-form" id ="credit-update-form">
        <input type="hidden" id ="credit-Id" name ="id" value="" style="width:200px;">
                <table class="client-tb">
                    <colgroup>
                        <col width="100">
                        <col width="200">
                        <col width="100">
                        <col width="200">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>客户名称</th>
                        <td>${customerName!}</td>
                        <th>客户类型</th>
                        <td>${customerType!}</td>
                    </tr>
                    <tr>
                        <th>业务合同号</th>
                        <td><input type="text" id ="businessC" name ="businessContractNo1" value ="" style="width:200px;">
                        	 <input type="hidden" id ="creditId-m" name ="credit" value="">
                        </td>
                        
                        <th></th>
                        <td></td>
                    </tr>
                    <tr>
                        <th>额度种类</th>
                        <td>
                            <select class="s-mode credit-M" style="width:200px;" id ="credit-M" name ="creditMode" value ="" data-role="dropdownlist">
                                 <#list creditModes as creditMode>		
									 	<option value=${creditMode.name()!}>${creditMode.desc()!}</option>		
								</#list>
                            </select>
                        </td>
                        <th>币种</th>
                        <td>
                            <select class="s-mode" style="width:200px;" id ="curr" name ="currency" value ="" data-role="dropdownlist">
                                <#list currencys as currency>		
									 	<option value=${currency.name()!}>${currency.desc()!}</option>		
								</#list>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>申请金额</th>
                        <td>
	                       	 <label class="client-unit-box">
		                        <input class ="moneyNum" type="text" style="width:200px;" id ="totalL" name ="totalLimit" value ="" onkeyup="clearNoNum(this)" validationMessage="申请金额不能为空" required>
		                        <div class="client-unit-two">元</div>
	                        </label>
                        </td>
                        <th>申请费用</th>
                        <td>
                        <label class="client-unit-box">
                        <input class ="moneyNum" type="text" style="width:200px;" id ="exp" name ="totalExpense" value ="" onkeyup="clearNoNum(this)" validationMessage="申请费用不能为空">
                        <div class="client-unit-two">元</div>
                        </label>
                        </td>
                    </tr>
                    <tr>
                        <th>生效日期</th>
                        <td><input type="text" class="time-one data-style hasIcon" style="width:200px;" id ="effectiveD" name ="effectiveDate" value ="" validationMessage="生效日期不能为空" required></td>
                        <th>到期日期</th>
                        <td><input type="text" class="time-two data-style hasIcon" style="width:200px;" id ="dueD" name ="dueDate" value ="" validationMessage="到期日期不能为空" required></td>
                    </tr>
                    <tr>
                        <th>状态</th>
                        <td id ="status"></td>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </form>
                <input type="hidden" class="financing-index-id" value="">
        </div>
        <div class="dlg-del-row">
         <input class="form-search-link" type="button" value="确定" id="submit-two" style="border:0;">
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
<!--成功提示弹窗-->
<script id="tipBox_template" type="text/x-kendo-template">
    <div id="tip-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice"></span>
            <span id="notice-content" class="notice-content"></span>
        </div>
    </div>
</script>
<!--确认弹窗-->
<script id="template-notice" type="text/x-kendo-template">
    <div id="confirm-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <input id="operationObj" type="hidden" value=""/>
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
            <a class="default-link confirm-link confirm-link-two" href="javascript:void(0);">确认</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
<!--状态弹窗-->
<script id="template-status" type="text/x-kendo-template">
    <div id="confirm-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <input id="operationObj" type="hidden" value=""/>
            <table class="dlg-rowTable">
                <tr>
                    <td class="dlg-row-left-limit">
                        <span class="dlg-notice notice-icon01"></span>
                    </td>
                    <td class="dlg-row-right-limit">
                        <span class="notice-content">额度状态除了申请和生效之外不能修改！</span>
                    </td>
                </tr>
            </table>
        </div>
        <div class="dlg-del-row">
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
<!--申请额度成功弹窗-->
<script id="template-notice-two" type="text/x-kendo-template">
    <div id="confirm-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <input id="operationObj" type="hidden" value=""/>
            <table class="dlg-rowTable">
                <tr>
                    <td class="dlg-row-left-limit">
                        <span class="dlg-notice notice-icon02"></span>
                    </td>
                    <td class="dlg-row-right-limit">
                        <span class="notice-content">申请额度成功！</span>
                    </td>
                </tr>
            </table>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id ="bnt-id" href="javascript:void(0);">确认</a>
        </div>
    </div>
</script>
<!--申请额度失败弹窗-->
<script id="template-notice-three" type="text/x-kendo-template">
    <div id="confirm-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <input id="operationObj" type="hidden" value=""/>
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
            <a class="default-link back-link" href="javascript:void(0);">返回</a>
        </div>
    </div>
</script>
<script>
	
	function clearNoNum(obj){   
    	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
    	obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是. 
    	obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.   
    	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	}
</script>
<!--权限配置-->
<script>
	var credit_info_modify = ${authorizeKey("BAI_CM_EDIT")?string("true","false")};
	var credit_info_history = ${authorizeKey("BAI_CM_HISTORY")?string("true","false")};
</script>
</@hb.htmlBase>
