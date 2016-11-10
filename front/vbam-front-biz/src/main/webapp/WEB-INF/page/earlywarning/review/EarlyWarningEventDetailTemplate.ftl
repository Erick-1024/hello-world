<script id="earlywarningEventDetail_template" type="text/x-kendo-template">
    <div class="relieve-content">
        <table class="relieve-table">
            <colgroup>
                <col width="40%">
                <col width="60%">
            </colgroup>
            <tbody>
            <tr>
                <th>新增/解除审核</th>
                <td name="action"></td>
            </tr>
            <tr>
                <th>客户名称</th>
                <td name="companyNameText"></td>
            </tr>
            <tr>
                <th>预警执行时间</th>
                <td name="applyDate"></td>
            </tr>
            <tr>
                <th>预警种类</th>
                <td><span class="war-pan" name="earlywarningEventCategory"></span><a class="relieve-status" href="javascript:void(0);">详情</a></td>
            </tr>
            <tr currentLevel>
                <th>预警状态</th>
                <td name='popWindowEarlyWarningLevel'>
                </td>
            </tr>
            <tr>
                <th>说明</th>
                <td><textarea name="review-extra" disabled="disabled"></textarea></td>
            </tr>
            <tr>
                <th></th>
                <td style="padding-top:20px;" name="buttonTd">
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</script>