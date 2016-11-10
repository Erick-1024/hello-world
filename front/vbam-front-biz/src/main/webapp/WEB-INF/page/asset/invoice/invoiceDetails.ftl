<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="应收账款详情" jsFiles=["js/common/jquery.nav.js","js/common/business.js","page/asset/invoice/invoiceDetails.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] curTopMenu = "基础资产信息" curSubMenu = "应收账款管理" removeExtHeader = false removeExtFooter = true>
<div class="pro-bg" style="background: #fff;">
    <form class="">
        <div class="left-nav">
            <ul id="client-nav">
                <li class="current"><a href="#nav-one">基本信息&ensp;▶</a></li>
                <li><a href="#nav-two">账款信息&ensp;▶</a></li>
                <li><a href="#nav-three">收费信息&ensp;▶</a></li>
            </ul>
        </div>

        <div class="client-bg">
            <!--基本信息-->
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
                        <th>客户名称</th>
                        <td>${invListDTO.customerName!}</td>
                        <th></th>
                        <td></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>业务合同号</th>
                        <td>${invListDTO.businessContractNo!}</td>
                        <th>币种</th>
                        <td>${invListDTO.currency!}</td>
                        <th>业务产品</th>
                        <td>${invListDTO.businessProductDesc!}</td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!--账款信息-->
            <div class="pro-title" id="nav-two"><span class="pro-title-left">账款信息</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <div class="min-box-width">
                    <table class="m-table fact-table">
                        <colgroup>
                            <col width="8%">
                            <col width="13%">
                            <col width="13%">
                            <col width="13%">
                            <col width="13%">
                            <col width="13%">
                            <col width="13%">
                            <col width="">
                        </colgroup>
                        <thead>
                        <tr class="top-list-one">
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
                        <table class="m-table fact-table" style="border:none;margin-top:0;" id="appoint-tb">
                            <colgroup>
                                <col width="8%">
                                <col width="13%">
                                <col width="13%">
                                <col width="13%">
                                <col width="13%">
                                <col width="13%">
                                <col width="13%">
                                <col width="">
                            </colgroup>
                            <tbody>
                            <#if invListDTO.invoiceInfoDTOs??>
                               <#list invListDTO.invoiceInfoDTOs as invoiceInfoDTO>
                                    <tr class="client-add-tr">
                                    <th>${invoiceInfoDTO_index+1}</th>
	                                <td>${invoiceInfoDTO.counterparty!}</td>
	                                <td>${invoiceInfoDTO.invoiceNo!}</td>
	                                <td>￥${formatMoney(invoiceInfoDTO.nominvoiceAmt!'')}</td>
	                                <td>￥${formatMoney(invoiceInfoDTO.invoiceAmt!'')}</td>
	                                <td>${invoiceInfoDTO.financingRatio!}</td>
	                                <td>${invoiceInfoDTO.invoiceDate!}</td>
	                                <td>${invoiceInfoDTO.dueDate!}</td>
                               </#list>
                            </#if>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!--费用信息-->
            <div class="pro-title" id="nav-three"><span class="pro-title-left">费用信息</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <div class="min-box-width">
                    <table class="m-table fact-table">
                        <colgroup>
                            <col width="10%">
                            <col width="26%">
                            <col width="26%">
                            <col width="">
                        </colgroup>
                        <thead>
                        <tr class="top-list-one">
                            <td>序号</td>
                            <td>费用科目</td>
                            <td>金额</td>
                            <td></td>
                        </tr>
                        </thead>
                    </table>
                    <div class="fact-tb-box">
                        <table class="m-table fact-table" style="border:none;margin-top:0;" id="cost-tb">
                            <colgroup>
                                <col width="10%">
                                <col width="26%">
                                <col width="26%">
                                <col width="">
                            </colgroup>
                            <tbody>
                            <#if invListDTO.expenseDTOs??>
                               <#list invListDTO.expenseDTOs as expenseDTO>
                                    <tr class="client-add-tr">
                                    <th>${expenseDTO_index+1}</th>
	                                <td>${expenseDTO.expenseSubject!}</td>
	                                <td>￥${formatMoney(expenseDTO.amountStr!'')}</td>
	                                <td></td>
		                            </tr>
                               </#list>
                            </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>



            <div class="client-foot">
                <a class="form-search-link" href="${basepath}/asset/invoice/invoiceList">返回</a>
            </div>
        </div>
    </form>
</div>
<footer>Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
<div style="height:70px"></div>
</@hb.htmlBase>