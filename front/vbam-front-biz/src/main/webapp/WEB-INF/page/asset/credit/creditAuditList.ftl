<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="额度历史" jsFiles=["page/asset/credit/creditDetail.js","page/asset/project/project.js","common/dateutil.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
curTopMenu = "基础资产信息" curSubMenu = "额度管理" removeExtHeader = false removeExtFooter = false>
<div class="pro-bg" style="background: #fff;">
    <div class="pro-box-bg">
        <div class="first-title">客户信息</div>
        <input type="hidden" id ="customerId" name ="customerId" value="${customerId!}" style="width:200px;">
        <input type="hidden" id ="creditId" name ="creditId" value="${creditId!}" style="width:200px;">
        <form>
            <table class="client-tb">
                <colgroup>
                    <col width="100px">
                    <col width="150px">
                    <col width="100px">
                    <col width="150px">
                    <col width="100px">
                    <col width="150px">
                    <col width="100px">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>客户名称</th>
                    <td>${customerName!}</td>
                    <th>客户类型</th>
                    <td>${customerType!}</td>
                    <th>总授信额度</th>
                    <td>￥${formatMoney(totalLimit!)}</td>
                    <th>总额度</th>
                    <td>￥${formatMoney(availableLimit!)}</td>
                </tr>
                <tr></tr>
                </tbody>
            </table>
            <table class="client-tb">
                <colgroup>
                    <col width="100px">
                    <col width="220px">
                    <col width="100px">
                    <col width="220px">
                    <col width="100px">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>额度种类</th>
                    <td>
                        <select class="creditMode" id ="creditMode" style="width:200px;"  data-role="dropdownlist">
                         <option value="">全部</option>
                            <#list creditModes as creditMode>		
									 <option value=${creditMode.name()!}>${creditMode.desc()!}</option>		
							</#list>
                        </select>
                    </td>
                    <th>状态</th>
                    <td>
                        <select class="creditOperateType" id ="creditOperateType" style="width:200px;"  data-role="dropdownlist">
                            <option value="">全部</option>
                            <#list creditOperateTypes as creditOperateType>		
									 <option value=${creditOperateType.name()!}>${creditOperateType.desc()!}</option>		
							</#list>
                        </select>
                    </td>
                    <td></td>
                    <td>
                        <a class="form-search-btn" href="javascript:void(0);"id ="seachBtn"><i class="searchIcon"></i>搜索</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorList-grid"></div>
    </div>
</div>
</@hb.htmlBase>
