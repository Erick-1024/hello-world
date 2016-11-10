<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="打印码" jsFiles=["page/account/printcode/printCodeList.js","common/dateutil.js"] cssFiles=["css/account.css"] localCssFiles=[] 
	curTopMenu = "账户管理" curSubMenu = "打印码" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
	<section class="whiteBg">
		<form id="companyInfoSearch">
            <ul class="searchList clearfix">
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="300">
                            <col width="300">
                        </colgroup>
                        <tbody>
	                        <tr>
	                            <th>银行账户</th>
	                            <td>
	                            	<input type="text" name="accountNo" id="accountNo" style="width: 185px;" validationMessage="不能为空" required>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th>起始记录号</th>
	                            <td>
	                            	<input type="text" name="startIndex" id="startIndex" class="bankCard" style="width: 185px;" validationMessage="不能为空" required>
	                            </td>
	                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <table class="searchTab">
                        <colgroup>
                            <col width="300">
                            <col width="300">
                        </colgroup>
                        <tbody>
	                        <tr>
	                            <th>交易日期</th>
	                            <td>
	                            	<input type="text" name="startDate" id="startDate" class="datepicker startDate hasIcon" readonly>
	                                <em> 至 </em>
	                                <input type="text" name="endDate" id="endDate" class="datepicker startDate hasIcon" readonly>
	                            </td>
	                        </tr>
	                         <tr>
	                            <th></th>
	                            <td>
	                            </td>
	                        </tr>
                        </tbody>
                    </table>
                </li>
                <li>
                    <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                </li>
            </ul>
		</form>
	    <div id="printCodeList">
    	</div>
	</section>
</div>
</@hb.htmlBase>