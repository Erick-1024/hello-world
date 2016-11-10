<!--专项计划弹窗-->
<script id="template-resetPwd-one" type="text/x-kendo-template">
    <div class="dlg-wrapper template-resetPwd-one">
        <div class="dlg-del-row">
            <table class="client-tb">
                <colgroup>
                    <col width="120px">
                    <col width="220px">
                    <col width="80px">
                    <col width="220px">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>专项计划名称</th>
                    <td><input type="text" id="specialProgramNameSearch" class="interests-input" style="width:180px;"></td>
                    <th></th>
                    <td><a id="popSearchBtn" class="form-search-btn" href="javascript:void(0);"><i class="searchIcon"></i>搜索</a></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="dlg-del-row">
            <div class="monitor-grid" id="interests-grid"></div>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link bind-SP" id="interests-btn" href="javascript:void(0);">确认</a>
        </div>
    </div>
</script>