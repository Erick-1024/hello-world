<!--提交确认弹窗模板-->
<script id="template-notice" type="text/x-kendo-template">
    <div id="confirm-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
        	<input id="operationObj" type="hidden" value=""/>
            <table class="dlg-rowTable">
                <tr>
                    <td class="dlg-row-left">
                        <span class="dlg-notice notice-icon01"></span>
                    </td>
                    <td class="dlg-row-right">
                        <span class="notice-content">确认要执行操作吗？</span>
                    </td>
                </tr>
            </table>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" href="javascript:void(0);">确认</a><a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>