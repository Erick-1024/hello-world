<#import "/common/htmlBase.ftl" as hb> 
<#import "/page/yundaex/earlywarning/toolsBar.ftl" as ctb/>
<@hb.htmlBase title="新增预警事件" jsFiles=["facade/formValidatorRules.js","common/formValidator.js", "page/yundaex/earlywarning/addList.js", "common/judgeIE.js", "common/dateutil.js", "common/cana.util.js", "js/common/ajaxfileupload.js", "js/common/warPublic.js"] cssFiles=["css/monitor.css","css/warning.css"] localCssFiles=[] 
curTopMenu = "韵达项目" curSubMenu = "预警" removeExtHeader = false removeExtFooter = false>

<#include '/common/enumcommon.ftl'/>

<div class="main-container">
    <section class="whiteBg">
        <@ctb.toolsBar "add"/>
        <form class="monitor-form" onsubmit="return false;">
            <table class="monitor-table">
                <colgroup>
                    <col width="80">
                    <col width="280">
                    <col width="100">
                    <col width="280">
                    <col width="100">
                    <col width="145">
                </colgroup>
                <tbody>
                <tr>
                    <th style="text-align: right;">客户名称</th>
                    <td>
                        <input type="text" style="width:240px;" name="companyName" productId memberId>
                    </td>
                    <th style="text-align: right;">当前预警状态</th>
                    <td id="earlywaringLevel">
                    </td>
                    <td>
                        <a class="form-search-link" href="javascript:void(0);"><i class="searchIcon"></i>查询</a>
                    </td>
                    <td id="addEvent">
                    	<a class="form-search-out " href="javascript:void(0);" onclick="openWin();">增加预警事件</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="new-war-table">
            <table>
                <colgroup>
                    <col width="300">
                    <col width="300">
                    <col width="300">
                    <col width="300">
                </colgroup>
                <tbody>
                <tr class="new-war-title">
                    <td>预警种类</td>
                    <td>预警数量(条)</td>
                    <td>最后修改时间</td>
                    <td>操作</td>
                </tr>
                </tbody>
            </table>
        </div>
    </section>
</div>

<!--增加预警事件弹窗-->
<div class="new-style-box" style="display: none;">
    <div class="out-ch"></div>
    <div class="out-min-ch" style="top:15%">
        <div class="out-header">增加预警事件</div>
        <div class="list-box">
            <form class="out-new-form">
                <table class="relieve-table">
                    <colgroup>
                        <col width="30%">
                        <col width="70%">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>客户名称</th>
                        <td>
                        	<input style="width:240px;" type="text" id="companyName">
                        </td>
                    </tr>
                    <tr>
                        <th>预警种类</th>
                        <td>
                            <select id="parentSelectWrap" data-role="dropdownlist" style="width:240px;">
                            	<#list YundaexEarlywarningEventCategory?keys as key>
                            		<#if key != "SYSTEM">
                            			<option value="${key}">${YundaexEarlywarningEventCategory[key]}</option>
                            		</#if>
								</#list>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>事件种类</th>
                        <td>
                            <select id="subSelectWrap" data-role="dropdownlist" style="width:240px;">
                            </select>
                        </td>
                    </tr>
                    <tr class="opt-before">
                        <th>判决时间</th>
                        <td>
                            <input type="text" class="datepicker time-one" name="courtTime" style="width: 160px;" readonly>
                        </td>
                    </tr>
                    <tr class="opt-before">
                        <th>执行金额</th>
                        <td>
                            <input type="text" placeholder="￥" class="moneyNum" style="width:160px;" name="amount"> 元
                        </td>
                    </tr>
                    <tr class="opt-after hidden">
                        <th>事件来源</th>
                        <td>
                            <input type="text" name="eventOrigin" style="width:160px;">
                        </td>
                    </tr>
                    <tr class="opt-after hidden">
                        <th>事件发生时间</th>
                        <td>
                            <input type="text" class="datepicker time-one" name="occurTime" style="width: 160px;" readonly>
                        </td>
                    </tr>
                    <tr>
                        <th>事件附件</th>
                        <td class="tageBox" style="width:100px;">
                        	<input type="file" class="up-frontage" id="file" name="file"><a class="up-but" href="javascript:void(0);">上传附件</a><span id="file-size-tip" style="color:red; margin-left:15px;">文件大小不超过20M</span>
                        </td>
                    </tr>
                    <tr>
                        <th style="vertical-align: text-top">事件描述</th>
                        <td>
                            <textarea name="represent"></textarea>
                        </td>
                    </tr>
                    <tr class="opt-after-two hidden">
                        <th>预警事件状态</th>
                        <td>
                            <#list earlywarningLevels as earlywarningLevel>
	                        	<a class="war-out" href="javascript:void(0);" enum=${earlywarningLevel}>${earlywarningLevel.desc()}</a>
                    		</#list>
                            <input type="hidden" value class="war-check-one" name="earlywarningLevel">
                        </td>
                    </tr>
                    <tr>
                        <th>预计预警状态</th>
                        <td id="predictLevel">
                        </td>
                    </tr>
                    <tr>
                        <th style="vertical-align: text-top">说明</th>
                        <td>
                            <textarea name="reviewExtra"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td style="padding-top:20px;">
                            <a class="default-link confirm-link" name="submit-link" href="javascript:void(0);">提交审核</a>
                            <a class="default-link back-link" href="javascript:" onclick="closeWin();">返回</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>

<#include '../../tipBoxTemplate.ftl'/>

</@hb.htmlBase>
