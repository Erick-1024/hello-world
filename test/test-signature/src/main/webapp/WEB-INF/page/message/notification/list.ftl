<#import "/common/htmlBase.ftl" as hb>

<@hb.htmlBase title="消息中心" jsFiles=["page/message/notification/list.js","common/dateutil.js"] cssFiles=["css/newsCenter.css"] localCssFiles=[] 
	curTopMenu = "消息中心" curSubMenu = "消息中心" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <form class="newsCenter-form">
            <table class="newsCenter-table">
                <colgroup>
                	<col width="70">
                    <col width="220">
                    <col width="70">
                    <col width="350">
                    <col width="70">
                    <col width="220">
                    <col width="150">
                </colgroup>
                <tbody>
                <tr>
                    <th>消息类型</th>
                    <td>
                        <select class="selectWrap" name="notificationType" id="notificationType" data-role="dropdownlist">
                            <option value="">全部</option>
                            <#if notificationTypes??>
                                <#list notificationTypes as notificationType>
                                	<option value=${notificationType.name()}>${notificationType.desc()}</option>
								</#list>
							</#if>
                        </select>
                    </td>
                    <th>消息时间</th>
                    <td>
                    	<input type="text" name="beginTime" id="beginTime" class="datepicker startDate hasIcon" readonly>
                        <em> 至 </em>
                        <input type="text" name="endTime" id="endTime" class="datepicker endDate hasIcon" readonly>
                    </td>
                    <th>查看状态</th>
                    <td>
                        <div class="radioContent" style="padding:5px 0;" id="isRead">
                            <label class="radio active" name="">
                                <span class="radioIcon"></span>
                                <span class="labelFonts">全部</span>
                            </label>
                            <label class="radio" name="false">
                                <span class="radioIcon"></span>
                                <span class="labelFonts">未读</span>
                            </label>
                            <label class="radio" name="true">
                                <span class="radioIcon"></span>
                                <span class="labelFonts">已读</span>
                            </label>
                        </div>
                    </td>
                    <td>
                        <a href="javascript:void(0);" class="form-search-link"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div id="notificationGridWrap">
	       	<div id="newsList-grid" class="newsCenter-grid"></div>
    	</div>
    </section>
</div>
</@hb.htmlBase>