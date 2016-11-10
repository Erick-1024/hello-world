<script id="earlywarningEventDetail_template" type="text/x-kendo-template">
    <div class="relieve-content">
        <table class="relieve-table">
            <colgroup>
                <col width="40%">
                <col width="60%">
            </colgroup>
            <tbody>
            <tr>
                <th>客户名称</th>
                <td name="companyName"></td>
            </tr>
            <tr>
                <th>审核通过时间</th>
                <td name="entryReviewTime"></td>
            </tr>
            <tr>
                <th>预警种类</th>
                <td><span class="war-pan" name="earlywarningEventCategory"></span><a class="relieve-status" href="javascript:void(0);">详情</a></td>
            </tr>
            <tr name="buttonTr">
                <th></th>
                <td style="padding-top:20px;" name="buttonTd">
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</script>