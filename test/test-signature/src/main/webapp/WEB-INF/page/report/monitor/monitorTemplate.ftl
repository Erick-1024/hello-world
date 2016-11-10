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
	        <td class="monitor-tabWrap-td">质押反担保覆盖率</td>
	        # for(var i=0;i<totalNum;i++){ #
	        	<td>#=data[i].counterGuaranteeRate==null?"-":data[i].counterGuaranteeRate#</td>
	        # } #
	    </tr>
	    <tr>
	        <td class="monitor-tabWrap-td">销售变化率</td>
	        # for(var i=0;i<totalNum;i++){ #
	        	<td>#=data[i].salesChangeRate==null?"-":data[i].salesChangeRate#</td>
	        # } #
	    </tr>
	    <tr>
	        <td class="monitor-tabWrap-td">销售回款率</td>
	        # for(var i=0;i<totalNum;i++){ #
	        	<td>#=data[i].salesRepaymentRate==null?"-":data[i].salesRepaymentRate#</td>
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
	        <td class="monitor-tabWrap-td">合格AR余额</td>
	         # for(var i=0;i<totalNum;i++){ #
	        	<td>#=data[i].qualifiedAR==null?"-":data[i].qualifiedAR.formatMoney()#</td>
	        # } #
	    </tr>
	    <tr>
	        <td class="monitor-tabWrap-td">机票销售总金额</td>
	         # for(var i=0;i<totalNum;i++){ #
	        	<td>#=data[i].ticketAllSales==null?"-":data[i].ticketAllSale.formatMoney()#</td>
	        # } #
	    </tr>
	    <tr>
	        <td class="monitor-tabWrap-td">机票回款总金额</td>
	         # for(var i=0;i<totalNum;i++){ #
	        	<td>#=data[i].ticketRepayment==null?"-":data[i].ticketRepayment.formatMoney()#</td>
	        # } #
	    </tr>
	    <tr>
	        <td class="monitor-tabWrap-td">机票已起飞金额</td>
	         # for(var i=0;i<totalNum;i++){ #
	        	<td>#=data[i].ticketTakeOffSale==null?"-":data[i].ticketTakeOffSale.formatMoney()#</td>
	        # } #
	    </tr>
	    </tbody>
	</table>
</script>