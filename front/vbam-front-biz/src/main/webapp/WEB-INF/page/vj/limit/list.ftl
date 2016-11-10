<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="额度列表" jsFiles=["page/vj/limit/list.js","common/dateutil.js"] cssFiles=["css/monitor.css","css/vjproject.css"] localCssFiles=[] 
	curTopMenu = "VJ项目" curSubMenu = "额度列表" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg" style="padding-top:10px;">
        <form class="monitor-form">
            <table class="monitor-table">
                <colgroup>
                    <col width="100">
                    <col width="320">
                    <col width="100">
                    <col width="330">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th style="text-align: right;">客户名称</th>
                    <td><input type="text" id="customerName" style="width:300px;"></td>
                    <th style="text-align: right;">额度</th>
                    <td>
                        <input type="text" id="limitMin" style="width:120px;">
                        <em> - </em>
                        <input type="text" id="limitMax" style="width:120px;">
                    </td>
                    <td>
                        <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">身份证号</th>
                    <td><input type="text" id="identityCardNo" style="width:300px;"></td>
                    <th style="text-align: right;">额度生效日</th>
                    <td>
                        <input type="text" class="datepicker startDate hasIcon" readonly="">
                        <em> - </em>
                        <input type="text" class="datepicker endDate hasIcon" readonly="">
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">额度状态</th>
                    <td style="padding-top:5px;" colspan="3">
                        <a class="war-out war-on" href="javascript:void(0);" value="">全部</a>
                    	<#if limitStatusList??>
                    		<#list limitStatusList as status>
                    			<#if status.name()!="TEMPORARY_FREEZE">
		                        	<a class="war-out" href="javascript:void(0);" value="${status.name()}">${status.desc()}</a>
		                        </#if>
                        	</#list>
                        </#if>
                    </td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorSrl-grid"></div>
    </section>
 </div>
 </@hb.htmlBase>