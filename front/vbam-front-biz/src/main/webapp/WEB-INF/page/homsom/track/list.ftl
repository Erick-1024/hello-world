<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="" jsFiles=["common/dateutil.js","page/homsom/track/list.js"] cssFiles=["css/monitor.css", "css/project.css"] localCssFiles=[] 
	curTopMenu = "恒顺国旅项目" curSubMenu = "核销履历">
<div class="main-container" style="padding:15px;">
    <div class="">
        <div class="first-title">查询条件</div>
        <table class="client-tb" style="padding:10px 0 15px 0;border-bottom:3px solid #F1F1F1;">
            <colgroup>
                <col width="100">
                <col width="320">
                <col width="100">
                <col width="320">
                <col width="150">
                <col width="">
            </colgroup>
            <tbody>
            <tr>
                <th>交易对手</th>
                <td><input type="text" style="width:254px;" id ="counterpartyName"></td>
                <th>出票日期</th>
                <td>
                    <input type="text" id="startIssueDate" style="width:120px;" class="datepicker startDate hasIcon">
                    <em> - </em>
                    <input type="text" id="endIssueDate" style="width:120px;" class="datepicker endDate hasIcon">
                </td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <th>票号</th>
                <td><input type="text" id="ticketNo" style="width:254px;"></td>
                <th>核销日期</th>
                <td>
                    <input type="text" id="startSettleDate" style="width:120px;" class="datepicker startDate hasIcon">
                    <em> - </em>
                    <input type="text" id="endSettleDate" style="width:120px;" class="datepicker endDate hasIcon">
                </td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <th>回购日期</th>
                <td>
                    <input type="text" id="startBuybackDate" style="width:120px;" class="datepicker startDate hasIcon">
                    <em> - </em>
                    <input type="text" id="endBuybackDate" style="width:120px;" class="datepicker endDate hasIcon">
                </td>
                <td></td>
                <td></td>
                <td><a href="javaScript:void(0);" id ="seachBtn" class="form-search-btn"><i class="searchIcon"></i>搜索</a></td>
                <td></td>
            </tr>
            </tbody>
        </table>
        <div class="monitor-grid" id="monitorList-grid"></div>
    </div>
</div>
</@hb.htmlBase>