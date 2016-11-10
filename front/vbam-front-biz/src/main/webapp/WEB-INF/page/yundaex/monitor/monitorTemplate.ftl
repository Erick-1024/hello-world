<script id="template-table-metric" type="text/x-kendo-template">
	<table>
	    <colgroup>
	        <col width="150">
	        # for(var i=0;i<totalNum;i++){ #
	        	<col>
	        # } #
	    </colgroup>
	    <tbody>
	    <tr class="monitor-tabWrap-tr">
	        <th></th>
	        # for(var i=0;i<totalNum;i++){ #
	        	<th>#=data[i].date#</th>
	        # } #
	    </tr>
	    <tr>
	        <td class="monitor-tabWrap-td">揽派件增长率</td>
	        # for(var i=0;i<totalNum;i++){ #
	        	<td>#=data[i].recandsendGrowthrate==null?"-":data[i].recandsendGrowthrate#</td>
	        # } #
	    </tr>
	    <tr>
	        <td class="monitor-tabWrap-td">保证金账户余额/</br>日资金需求</td>
	        # for(var i=0;i<totalNum;i++){ #
	        	<td>#=data[i].bailBalanceDivideDayRequirements==null?"-":data[i].bailBalanceDivideDayRequirements#</td>
	        # } #
	    </tr>
	    <tr>
	        <td class="monitor-tabWrap-td">韵达评级</td>
	        # for(var i=0;i<totalNum;i++){ #
	        	<td>#=data[i].yundaexGrade==null?"-":data[i].yundaexGrade#</td>
	        # } #
	    </tr>
	    </tbody>
	</table>
</script>


<script id="template-table-data" type="text/x-kendo-template">
	<table>
	    <colgroup>
	        <col width="150">
	        # for(var i=0;i<totalNum;i++){ #
	        	<col>
	        # } #
	    </colgroup>
	    <tbody>
	    <tr class="monitor-tabWrap-tr">
	        <th></th>
	         # for(var i=0;i<totalNum;i++){ #
	        	<th>#=data[i].date#</th>
	        # } #
	    </tr>
	    <tr>
	        <td class="monitor-tabWrap-td">保证金余额</td>
	         # for(var i=0;i<totalNum;i++){ #
	        	<td>#=data[i].bailBalance==null?"-":data[i].bailBalance.formatMoney()#</td>
	        # } #
	    </tr>
	    <tr>
	        <td class="monitor-tabWrap-td">净现金增长量</td>
	         # for(var i=0;i<totalNum;i++){ #
	        	<td>#=data[i].netCashflowGrowth==null?"-":data[i].netCashflowGrowth#</td>
	        # } #
	    </tr>
	    <tr>
	        <td class="monitor-tabWrap-td">最大授信金额</td>
	         # for(var i=0;i<totalNum;i++){ #
	        	<td>#=data[i].creditLimit==null?"-":data[i].creditLimit.formatMoney()#</td>
	        # } #
	    </tr>
	    <tr>
	        <td class="monitor-tabWrap-td">逾期次数(CANA)</td>
	         # for(var i=0;i<totalNum;i++){ #
	        	<td>#=data[i].overdues==null?"-":data[i].overdues#</td>
	        # } #
	    </tr>
	    </tbody>
	</table>
</script>