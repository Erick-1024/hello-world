<!--应收账款信息弹框-->
<script id="template-receivables" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <form class="" id="receivables-form">
            <div class="dlg-del-row">
                <label style="height:45px;line-height: 45px;">
                    交易对手&emsp;<input id="counterparty" type="text" value="" name="" disabled="disabled" style="width:200px;">
                </label>
                <div class="min-box-width" style="width:970px;">
                    <!--应收账款信息table title-->
                    <table class="m-table fact-table" style="border-bottom:none;">
                        <colgroup>
                            <col width="60px">
                            <col width="60px">
                            <col width="220px">
                            <col width="100px">
                            <col width="100px">
                            <col width="100px">
                            <col width="100px">
                            <col width="100px">
                            <col width="">
                            <col width="17px">
                        </colgroup>
                        <thead>
                        <tr class="top-list-one">
                            <th>选择</th>
                            <td>序号</td>
                            <td>交易对手</td>
                            <td>单证号码</td>
                            <td>单证面额</td>
                            <td>应收金额</td>
                            <td>融资比例</td>
                            <td>开票日</td>
                            <td>到期日</td>
                            <td></td>
                        </tr>
                        </thead>
                    </table>
                    <!--应收账款信息table tbody-->
                    <div class="fact-tb-box">
                        <table class="m-table fact-table-two" style="border:none;margin-top:0;" id="receivablesPop-tb">
                            <colgroup>
                                <col width="60px">
                                <col width="60px">
                                <col width="220px">
                                <col width="100px">
                                <col width="100px">
                                <col width="100px">
                                <col width="100px">
                                <col width="100px">
                                <col width="">
                            </colgroup>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="dlg-del-row">
                <a class="default-link confirm-link" id="receivablesPop-btn" href="javascript:void(0);">确定</a>
                <a class="default-link back-link close-receivablesPop" href="javascript:void(0);">取消</a>
            </div>
        </form>
    </div>
</script>