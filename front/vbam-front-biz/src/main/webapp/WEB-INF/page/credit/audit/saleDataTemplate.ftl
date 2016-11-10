<#--销售数据弹窗-->
<div class="open-se-pop" style="display:none;">
<div class="out-ch"></div>
<div class="out-min-ch" style="position:fixed;top:15%;">
    <div class="out-header">销售数据</div>
    <div class="list-box">
        <table class="new-war-table-xx">
            <colgroup>
                <col width="30%">
                <col width="30%">
                <col width="">
            </colgroup>
            <tbody>
            <tr class="new-war-title">
                <td>年份</td>
                <td>月份</td>
                <td>销售额（元）</td>
            </tr>
            <#if customerApplyDetailDTO.saleDatas??>
            	<#list customerApplyDetailDTO.saleDatas as saleData>
            		<tr>
		                <td>${saleData.year}</td>
		                <td>${saleData.month}</td>
		                <td>${saleData.saleMoneyStr}</td>
		            </tr>
            	</#list>
            </#if>
            </tbody>
        </table>
        <div class="dlg-del-row" style="margin-top:15px;">
            <a class="default-link back-link close-se-pop" href="javascript:void(0);">返回</a>
        </div>

    </div>
</div>
</div>