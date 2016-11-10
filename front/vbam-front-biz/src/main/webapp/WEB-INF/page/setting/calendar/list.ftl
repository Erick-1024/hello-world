<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="节假日设置" jsFiles=["common/cana.util.js", "page/setting/calendar/list.js"] cssFiles=["css/monitor.css","css/model.css"] localCssFiles=[] 
curTopMenu = "设置" curSubMenu = "节假日" removeExtHeader = false removeExtFooter = false>

<div class="workbench">
    <div class="center-workbench">
        <a href="javascript:void(0);" class="open-pop-btn" style="display:none;"></a>
        <div class="public-title"><span>节假日设置</span></div>
        <table class="public-table">
            <colgroup>
            <col width="100">
            <col width="500">
            <col width="">
            </colgroup>
            <tbody>
            <tr>
                <th>时间</th>
                <td>
                    <input id="startDate" type="text" style="width:150px;" class="datepicker startDate hasIcon">
                    <em>至</em>
                    <input id="endDate" type="text" style="width:150px;" class="datepicker endDate hasIcon">
                </td>
                <td>
                    <a class="form-search-link" href="javascript:void(0);" style="float:right;"><i class="searchIcon"></i>查询</a>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="festival-into">
        	<#if authorizeKey("SET_CALENDAR_IMPORT")>
	            <div class="public-input-file">
	                <input type="file" id="photos" class="input-file" name="excel" accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" style="width:100px;height:30px;">
	                <a class="public-button-link" id="upload-a-btn" href="javascript:void(0);" style="position:absolute;left:0;top:0;">Excel导入</a>
	            </div>
        	</#if>
        	<#if authorizeKey("SET_CALENDAR_EXPORT")>
	            <a class="public-button-link" id="export" href="javascript:void(0);" style="margin-top:5px;margin-left:20px;">导出Excel</a>
        	</#if>
        </div>
        <div class="monitor-grid" id="monitorList-grid" style="margin:0 15px 15px 15px;"></div>
    </div>
</div>
<!--提示弹窗-->
<script id="modify_calendar_template" type="text/x-kendo-template">
    <div id="modify-calendar-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <table class="public-table" style="margin-top:0;">
                <colgroup>
                    <col width="220">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>年份</th>
                    <td id="year"></td>
                </tr>
                <tr>
                    <th>日期</th>
                    <td id="monthDay"></td>
                </tr>
                <tr>
                    <th>是否假期</th>
                    <td>
                        <div class="public-radio-group">
                            <label class="public-radio-item">
                                <i class="public-radio-icon public-commonIcon-radio" id="true"></i>是
                            </label>
                            <label class="public-radio-item">
                                <i class="public-radio-icon public-commonIcon-radio" id="false"></i>否
                            </label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>假期名称</th>
                    <td><input type="text" style="width:160px;" id="description"></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="modifyOk" href="javascript:void(0);" date="">确认</a>
            <a class="default-link back-link" id="modifyCancel" href="javascript:void(0);">返回</a>
        </div>
    </div>
</script>

<#include '../../tipBoxTemplate.ftl'/>

<!--权限配置-->
<script>
	var set_calendar_modify = ${authorizeKey("SET_CALENDAR_MODIFY")?string("true","false")};
</script>

</@hb.htmlBase>