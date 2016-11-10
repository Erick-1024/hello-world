<!--费用信息弹框-->
<script id="template-resetPwd-cost" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <form class="" id="cost-form">
            <div class="dlg-del-row">

                <table class="client-tb">
                    <colgroup>
                        <col width="40%">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>费用科目</th>
                        <td><input type="text" style="width:200px;" class="co-class" name="exa-class"  validationMessage="不能为空" required></td>
                    </tr>
                    <tr>
                        <th>金额</th>
                        <td style="position:relative;">
                        	<input type="text" style="width:200px;" id="changeMoney" class="co-money moneyNum" name="exa-money"  validationMessage="不能为空" required>
                        	<div class="client-unit">元</div>
                        </td>
                    </tr>
                    </tbody>
                </table>

            </div>
            <div class="dlg-del-row">
                <a class="default-link confirm-link" id="cost-btn" href="javascript:void(0);">确定</a>
                <a class="default-link back-link close-costPop" href="javascript:void(0);">取消</a>
            </div>
        </form>
        <input type="hidden" value="" class="cost-ch-id">
    </div>
</script>