<#import "/common/htmlBase.ftl" as hb>
<#import "/page/vj/VJToolsBar.ftl" as ctb/>
<@hb.htmlBase title="主动还款明细" jsFiles=["page/vj/tran/repaymentDetailList.js","common/dateutil.js"] cssFiles=["css/monitor.css","css/vjproject.css","css/finance.css"] localCssFiles=[] 
	curTopMenu = "VJ项目" curSubMenu = "交易明细" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
    	<@ctb.toolsBar "repaymentDetailList"/>
		<form class="monitor-form">
            <table class="monitor-table">
                <colgroup>
                    <col width="130">
                    <col width="280">
                    <col width="100">
                    <col width="300">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th style="text-align: right;">CANA交易流水号</th>
                    <td><input type="text" id="canaTranSeq" style="width:267px;"></td>
                    <th style="text-align: right;">VJ交易流水号</th>
                    <td><input type="text" id="vjTranSeq" style="width:225px;"></td>
                    <td>
                        <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                </tr>
                <tr>
                	<th style="text-align: right;">CANA平台流水号</th>
                    <td><input type="text" id="loanId" style="width:267px;"></td>
                    <th style="text-align: right;">客户名称</th>
                    <td><input type="text" id="customerName" style="width:225px;"></td>
                </tr>
                <tr>
                    <th style="text-align: right;">身份证号</th>
                    <td><input type="text" id="identityCardNo" style="width:267px;"></td>
                </tr>
                <tr>
                    <th style="text-align: right;">交易日期</th>
                    <td>
                        <input type="text" class="datepicker startDate hasIcon" readonly="">
                        <em> - </em>
                        <input type="text" class="datepicker endDate hasIcon" readonly="">
                    </td>
                    <td style="position: relative;">
                        <div style="position:absolute;left:0;top:9px;width:340px;">
                            <a class="war-out tran-date" href="javascript:void(0);" value="oneWeek">近一周</a>
                            <a class="war-out tran-date" href="javascript:void(0);" value="oneMonth">近一个月</a>
                            <a class="war-out tran-date" href="javascript:void(0);" value="threeMonths">近三个月</a>
                            <input type="hidden" value="0" class="war-check-one" name="">
                        </div>
                    </td>
                </tr>
                <tr>
                	<th style="text-align: right;">交易状态</th>
                    <td colspan="3">
                    	<a class="war-out war-on tran-state" href="javascript:void(0);" value="">全部</a>
                    	<#if tranStateList??>
                    		<#list tranStateList as tranState>
                    			<a class="war-out tran-state" href="javascript:void(0);" value="${tranState.name()}">${tranState.desc()}</a>
                    		</#list>
                    	</#if>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorSrl-grid"></div>
	 </section>
 </div>
 
 <script>
	var detailAuth = ${authorizeKey("VJ_TRAN_REPAYMENT_DETAIL")?string("true","false")};
</script>

 <!--详情模板-->
 <script id="template-repayDetail" type="text/x-kendo-template">

	<div style="width:600px;height:100px;text-align:center;line-height:40px;" id="repayWaitHtml">
		<span style="">加载中...</span>
	</div>
   <!--没有还款明细-->
    <div id="noRepaymentDetails" class="dlg-wrapper hidden">
        <div class="dlg-wrapper-content" style="text-align: center;">
            <div>没有还款明细</div>
        </div>
        <div class="dlg-wrapper-foot">
            <a class="default-link back-link" href="javascript:void(0);">关闭</a>
        </div>
    </div>
    <div id="haveRepaymentDetails" class="dlg-wrapper hidden">
        <div class="tableTpl-wrap">
            <div class="tableTpl-head">
                <div class="tableTpl-head-wrap">
                    <table class="tableTpl-grid">
                        <colgroup>
                            <col style="width:100px">
                            <col style="width:100px">
                            <col style="width:200px">
                        </colgroup>
                        <thead>
                        <tr>
                        	<th>
                                <span class="tabTpl-link">CANA交易流水号</span>
                            </th>
                            <th>
                                <span class="tabTpl-link">操作时间</span>
                            </th>
                            <th>
                                <span class="tabTpl-link">金额明细</span>
                            </th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <div class="tableTpl-content">
                <table class="tableTpl-grid">
                    <colgroup>
                        <col style="width:100px">
                        <col style="width:100px">
                        <col style="width:200px">
                    </colgroup>
                    <tbody id="repaymentDetails">
                    </tbody>
                </table>
            </div>
        </div>
        <div class="dlg-wrapper-foot">
            <a class="default-link back-link close-window" href="javascript:void(0);">返回</a>
        </div>
    </div>
</script>

 </@hb.htmlBase>