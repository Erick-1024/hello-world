<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="应收账款管理" jsFiles=["common/formValidator.js","page/asset/invoice/formValidatorRules.js","js/common/jquery.nav.js","js/common/business.js","page/asset/invoice/invoiceUpdate.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] curTopMenu = "基础资产信息" curSubMenu = "应收账款管理" removeExtHeader = false removeExtFooter = true>
<div class="pro-bg" style="background: #fff;">
    <form class="submit">
        <div class="left-nav">
            <ul id="client-nav">
                <li class="current"><a href="#nav-one">基本信息&ensp;▶</a></li>
                <li><a href="#nav-two">账款信息&ensp;▶</a></li>
                <li><a href="#nav-three">费用信息&ensp;▶</a></li>
            </ul>
        </div>
        <div class="client-bg">
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
                        <th>业务合同号</th>
                        <td>${invListDTO.businessContractNo!}
                        <input type="hidden" id="invBasicId" value="${invListDTO.id!}"/>
                        <input type="hidden" id="businessContractNo" value="${invListDTO.businessContractNo!}"/>
                        <input type="hidden" id="loanState" value="${invListDTO.loanState!}"/>
                        </td>
                        <th></th>
                        <td></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>客户名称</th>
                        <input type="hidden" id="customerId" name="customerId" value="${invListDTO.customerId!}" />
                        <td id="customerName" name="customerName">${invListDTO.customerName!}</td>
                        <th>币种</th>
                        <td id="currency" name="currency">${invListDTO.currency!}</td>
                        <th>业务产品</th>
                        <input type="hidden" id = "businessProduct" name="businessProduct" value="${invListDTO.businessProduct!}" />
                        <td id="businessProductDesc">${invListDTO.businessProductDesc!}</td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            
            <!--账款信息-->
            <div class="pro-title" id="nav-two"><span class="pro-title-left">账款信息</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <div style="margin-top:15px;">
                    <a class="status-normal status-chk add-appoint-btn" href="javascript:void(0);">新增</a>
                    <a class="status-normal status-chk ch-appoint-btn" href="javascript:void(0);">修改</a>
                    <a class="status-normal status-chk del-appoint-btn" href="javascript:void(0);">删除</a>

                    <a class="open-sec-btn" href="javascript:void(0);" style="display:none;"></a>
                </div>
                <div class="min-box-width">
                    <table class="m-table fact-table" style="border-bottom:none;">
                        <colgroup>
                            <col width="8%">
                            <col width="8%">
                            <col width="12%">
                            <col width="12%">
                            <col width="12%">
                            <col width="12%">
                            <col width="12%">
                            <col width="12%">
                            <col width="">
                        </colgroup>
                        <thead>
                        <tr class="top-list-one">
                            <th>选择</th>
                            <td>序号</td>
                            <td>交易对手</td>
                            <td>单证号码</td>
                            <td>单证面额</td>
                            <td>应收金额</td>
                            <td>融资比例(%)</td>
                            <td>开票日</td>
                            <td>到期日</td>
                        </tr>
                        </thead>
                    </table>
                    <div class="fact-tb-box">
                        <table class="m-table fact-table-two" style="border:none;margin-top:0;" id="appoint-tb">
                            <colgroup>
                                <col width="8%">
                                <col width="8%">
                                <col width="12%">
                                <col width="12%">
                                <col width="12%">
                                <col width="12%">
                                <col width="12%">
                                <col width="12%">
                                <col width="">
                            </colgroup>
                            <tbody>
                            <#if invListDTO.invoiceInfoDTOs??>
                               <#list invListDTO.invoiceInfoDTOs as invoiceInfoDTO>
                                    <tr class="client-add-tr">
                                    <th><input type="checkbox" class="input-new" name="appoint-tb" <#if invoiceInfoDTO.loanInfoId??>disabled</#if>></th>
                                    <th>${invListDTO.invoiceInfoDTOs?size-invoiceInfoDTO_index}</th>
	                                <td name="counterparty">${invoiceInfoDTO.counterparty!}</td>
	                                <td name="invoiceNo">${invoiceInfoDTO.invoiceNo!}</td>
	                                <td name="nominvoiceAmt">￥${formatMoney(invoiceInfoDTO.nominvoiceAmt!'')}</td>
	                                <td name="invoiceAmt">￥${formatMoney(invoiceInfoDTO.invoiceAmt!'')}</td>
	                                <td name="financingRatio">${invoiceInfoDTO.financingRatio!}</td>
	                                <td name="invoiceDate">${invoiceInfoDTO.invoiceDate!}</td>
	                                <td name="dueDate">${invoiceInfoDTO.dueDate!}</td>
	                                <td style="display:none" name="id">${invoiceInfoDTO.id!}</td>
	                                <td style="display:none" name="counterpartyId">${invoiceInfoDTO.counterpartyId!}</td>
                                    <td style="display:none" name="loanInfoId">${invoiceInfoDTO.loanInfoId!}</td>
                               </#list>
                            </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            
            <!--费用信息-->
            <div class="pro-title" id="nav-three"><span class="pro-title-left">费用信息</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <div class="">
                    <a class="status-normal status-chk add-cost-btn" href="javascript:void(0);">新增</a>
                    <a class="status-normal status-chk ch-cost-btn" href="javascript:void(0);">修改</a>
                    <a class="status-normal status-chk del-cost-btn" href="javascript:void(0);">删除</a>
                    <a class="open-cost-btn" href="javascript:void(0);" style="display:none;"></a>
                    <a class="open-message-btn" href="javascript:void(0);" style="display:none;"></a>
                </div>
                <div class="min-box-width">
                <table class="m-table fact-table" style="border-bottom:none;" id ="feetable">
                    <colgroup>
                        <col width="10%">
                        <col width="10%">
                        <col width="26%">
                        <col width="26%">
                        <col width="">
                    </colgroup>
                    <thead>
                    <tr class="top-list-one">
                        <th>选择</th>
                        <th>序号</th>
                        <th>费用科目</th>
                        <th>金额</th>
                    </tr>
                    </thead>
                </table>
                <div class="fact-tb-box">
                    <table class="m-table fact-table-two" style="border:none;margin-top:0;" id="cost-tb">
                        <colgroup>
                            <col width="10%">
                            <col width="10%">
                            <col width="26%">
                            <col width="26%">
                        </colgroup>
                    <tbody>
                    <#if invListDTO.expenseDTOs??>
                       <#list invListDTO.expenseDTOs as expenseDTO>
                            <tr class="client-add-tr">
                            <th><input type="checkbox" class="input-new" name="cost-tb" <#if invListDTO.loanState == "LOANED">disabled</#if>></th>
                            <th>${expenseDTO_index+1}</th>
                            <td name="expenseSubject">${expenseDTO.expenseSubject!}</td>
                            <td name="amountStr">￥${formatMoney(expenseDTO.amountStr!'')}</td>
                            <td style="display:none" name="id">${expenseDTO.id!}</td>
                            </tr>
                       </#list>
                     </#if>
                    </tbody>
                </table>
                </div>
                </div>
            </div>
            <div class="client-foot">
                <a class="form-search-link sc-btn" href="javascript:void(0);" id="submitBtn">提交</a>
                <a class="form-search-link" href="${basepath}/asset/invoice/invoiceList">返回</a>
            </div>
        </div>
    </form>
</div>
<footer>Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
<div style="height:70px"></div>
<!--新增应收账款弹窗-->
<script id="template-resetPwd-new" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <form class="out-new-form">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:100px;text-align: right;">客户名称</td>
                        <td style="width:200px;"><input type="text" id="custName" style="width:160px;"></td>
                        <td><a class="form-search-btn" href="javascript:void(0);" id="cusSearch"><i class="searchIcon"></i>搜索</a></td>
                    </tr>
                    </tbody>
                </table>

            </form>
        </div>
        <div class="dlg-del-row">
            <div class="monitor-grid" id="client-out-grid"></div>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link go-appoint-pop" href="javascript:void(0);" id="cusBut">确定</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>

<!--新增/修改应收账款信息弹窗-->
<script id="template-resetPwd-arr" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <form class="" id="appoint-form">
        <input type="hidden" id = "invCustId" name="invCustId" value="" />
        <input type="hidden" id = "invNo" name="invNo" value="" />
            <div class="dlg-del-row">
                <table class="client-tb">
                    <colgroup>
                        <col width="40%">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>交易对手</th>
                        <td class="ap-name" id="invCustName"></td>
                    </tr>
                    <tr>
                        <th>单证号码</th>
                        <td><input type="text" class="ap-num" style="width:200px;" name="exa-num"></td>
                    </tr>
                    <tr>
                        <th>单证面额</th>
                        <td style="position:relative;"><input type="text" class="ap-ratio moneyNum" name="exa-ratio" style="width:200px;" onkeyup="clearNoNum(this)" validationMessage="不能为空" required>
                        <div class="client-unit">元</div>
                        </td>
                    </tr>
                    <tr>
                        <th>应收金额</th>
                        <td style="position:relative;"><input type="text" class="ap-money moneyNum" style="width:200px;" name="exa-ap-type" onkeyup="clearNoNum(this)" validationMessage="不能为空" required>
                        <div class="client-unit">元</div>
                        </td>
                    </tr>
                    <tr>
                        <th>融资比例</th>
                        <td style="position:relative;">
                        <input type="text" class="ap-scale" style="width:200px; padding-right: 40px;" name="exa-yes" pattern="^[0-9]{1,2}([.][0-9]{1,3})?$|^100([.][0]{1,3})?$" data-pattern-msg="数据不合法" data-required-msg="不能为空" required>
                        <div class="client-unit">%</div>
                        </td>
                    </tr>
                    <tr>
                        <th>开票日期</th>
                        <td><input type="text" readOnly class="time-one data-style hasIcon" name="exa-start" style="width:200px;" data-required-msg="不能为空" required></td>
                    </tr>
                    <tr>
                        <th>到期日期</th>
                        <td><input type="text" readOnly class="time-two data-style hasIcon" name="exa-end" style="width:200px;" data-required-msg="不能为空" required></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="dlg-del-row">
                <a class="default-link confirm-link" id="appoint-btn" href="javascript:void(0);">确定</a>
                <a class="default-link back-link" href="javascript:void(0);">取消</a>
            </div>
        </form>
        <input type="hidden" value="" class="appoint-ch-id">
    </div>
</script>

<!--新增/修改费用信息弹窗-->
<script id="template-resetPwd-cost" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <form class="" id="cost-form">
        <div class="dlg-del-row">

            <table class="client-tb">
                <colgroup>
                    <col width="40%">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>费用科目</th>
                    <td><input type="text" style="width:200px;" class="co-class" name="exa-class"  validationMessage="不能为空" required></td>
                </tr>
                <tr>
                    <th>金额</th>
                    <td style="position:relative;"><input type="text" style="width:200px;" onkeyup="clearNoNum(this)" class="co-money moneyNum cashTxt" name="exa-money"  validationMessage="不能为空" required>
                    <div class="client-unit">元</div>
                    </td>
                </tr>
                </tbody>
            </table>

        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="cost-btn" href="javascript:void(0);">确定</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
        </form>
        <input type="hidden" value="" class="cost-ch-id">
    </div>
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
            <a class="default-link confirm-link" id="delete-confirm" href="javascript:void(0);">确认</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
<script>
	function clearNoNum(obj){   
    	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
    	obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是. 
    	obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.   
    	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
    	//obj.value = obj.value.formatMoney();
	}
	//输入数字校验
	function checkNum(obj) {
		obj.value = obj.value.replace(/[^\d]/g,"");
	}
</script>
</@hb.htmlBase>
