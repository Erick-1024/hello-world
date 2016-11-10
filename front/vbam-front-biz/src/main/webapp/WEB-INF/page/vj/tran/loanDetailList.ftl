<#import "/common/htmlBase.ftl" as hb>
<#import "/page/vj/VJToolsBar.ftl" as ctb/>
<@hb.htmlBase title="放款明细" jsFiles=["page/vj/tran/loanDetailList.js","common/dateutil.js", "js/common/warPublic.js"] cssFiles=["css/monitor.css","css/vjproject.css"] localCssFiles=[] 
	curTopMenu = "VJ项目" curSubMenu = "交易明细" removeExtHeader = false removeExtFooter = false>
	
<#include '/common/enumcommon.ftl'/>
	
<div class="main-container">
    <section class="whiteBg">
    	<@ctb.toolsBar "loanDetailList"/>
		<form class="monitor-form">
            <table class="monitor-table">
                <colgroup>
                    <col width="130">
                    <col width="312">
                    <col width="100">
                    <col width="370">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th style="text-align: right;">CANA交易流水号</th>
                    <td><input type="text" name="canaTranSeq" style="width:267px;"></td>
                    <th style="text-align: right;">VJ交易流水号</th>
                    <td><input type="text" name="vjTranSeq" style="width:267px;"></td>
                    <td>
                        <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">客户名称</th>
                    <td><input type="text" name="customerName" style="width:267px;"></td>
                    <th style="text-align: right;">身份证号</th>
                    <td><input type="text" name="identityCardNo" style="width:267px;"></td>
                </tr>
                <tr>
                	<th style="text-align: right;">CANA平台流水号</th>
                    <td><input type="text" name="loanId" style="width:267px;"></td>
                	<th style="text-align: right;">交易状态</th>
                    <td style="padding-top:5px;">
                        <a class="war-out war-on" href="javascript:void(0);" enum="">全部</a>
                        <#list TranState?keys as key>
                    		 <a class="war-out" href="javascript:void(0);" enum="${key}">${TranState[key]}</a>
						</#list>
                        <input type="hidden" value class="war-check-one" name="tranState">
                    </td>
                </tr>
                <tr>
                	<th style="text-align: right;">放款日期</th>
                    <td>
                        <input type="text" class="datepicker startDate hasIcon" readonly name="tranStartDate">
                        <em>&ensp;-&ensp;</em>
                        <input type="text" class="datepicker endDate hasIcon" readonly name="tranEndDate">
                    </td>
                    <td></td>
                    <td style="position: relative;">
                        <div style="position:absolute;left:-95px;top:9px;">
                        <a class="war-out tran-date" href="javascript:void(0);" value="oneWeek">近一周</a>
                        <a class="war-out tran-date" href="javascript:void(0);" value="oneMonth">近一个月</a>
                        <a class="war-out tran-date" href="javascript:void(0);" value="threeMonths">近三个月</a>
                        <input type="hidden" class="war-check-one">
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorSrl-grid"></div>
	 </section>
 </div>
 
 <!--权限配置-->
<script>
	var download_contract_auth = ${authorizeKey("VJ_DOWNLOAD_CONTRACT")?string("true","false")};
</script>
 
 </@hb.htmlBase>