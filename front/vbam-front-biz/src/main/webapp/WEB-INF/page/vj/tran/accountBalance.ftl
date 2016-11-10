<#import "/common/htmlBase.ftl" as hb>
<#import "/page/vj/VJToolsBar.ftl" as ctb/>
<@hb.htmlBase title="对账单" jsFiles=["page/vj/tran/accountBalance.js","common/dateutil.js", "js/common/warPublic.js"] cssFiles=["css/monitor.css","css/vjproject.css"] localCssFiles=[] 
	curTopMenu = "VJ项目" curSubMenu = "交易明细" removeExtHeader = false removeExtFooter = false>
	
<#include '/common/enumcommon.ftl'/>
	
<div class="main-container">
    <section class="whiteBg">
    	<@ctb.toolsBar "accountBalance"/>
		<form class="monitor-form">
            <table class="monitor-table">
                <colgroup>
                    <col width="80">
                    <col width="320">
                    <col width="250">
                    <col width="140">
                    <col width="140">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                	<th style="text-align: right;">交易类型</th>
                    <td>
                        <select class="selectWrap" id="tranType" data-role="dropdownlist" style="width:180px;">
                        	<option value="">全部</option>
                        	<#list TranType?keys as key>
                        		<#if (key == "PAY" || key == "DEDUCT")>
                        			<option value="${key}">${TranType[key]}</option>
                        		</#if>
							</#list>
                        </select>
                    </td>
                    <td>
                    </td>
                    <td>
                        <a class="form-search-link" id="searchBtn" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                    <td>
                        <a class="form-search-link" id="exportBtn" href="javascript:void(0);">导出</a>
                    </td>
                </tr>
                
                <tr>
                    <th style="text-align: right;">交易日期</th>
                    <td>
                        <input type="text" class="datepicker startDate hasIcon" readonly name="tranStartDate">
                        <em>&ensp;-&ensp;</em>
                        <input type="text" class="datepicker endDate hasIcon" readonly name="tranEndDate">
                    </td>
                    <td style="position: relative;">
                        <div style="position:absolute;left:-25px;top:9px;">
                        	<a class="war-out tran-date" href="javascript:void(0);" value="oneWeek">近一周</a>
                        	<a class="war-out tran-date" href="javascript:void(0);" value="oneMonth">近一个月</a>
                        	<a class="war-out tran-date" href="javascript:void(0);" value="threeMonths">近三个月</a>
                            <input type="hidden" class="war-check-one">
                        </div>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">交易状态</th>
                    <td>
                        <a class="war-out war-on" href="javascript:void(0);" enum="">全部</a>
                        <#list TranState?keys as key>
                    		 <a class="war-out" href="javascript:void(0);" enum="${key}">${TranState[key]}</a>
						</#list>
                        <input type="hidden" value class="war-check-one" name="tranState">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorSrl-grid"></div>
	 </section>
 </div>
 </@hb.htmlBase>