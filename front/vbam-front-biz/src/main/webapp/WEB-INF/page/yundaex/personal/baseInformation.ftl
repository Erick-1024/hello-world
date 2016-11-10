<div class="pro-title"><span class="pro-title-left">基础信息</span></div>
<div class="tb-box" style="position:relative;">
    <table class="check-table">
        <colgroup>
            <col width="80">
            <col width="100">
            <col width="80">
            <col width="100">
            <col width="80">
            <col width="200">
        </colgroup>
        <tbody>
        <tr>
            <th>申请日期</th>
            <td>${(cusApplyDTO.applyDate)?string("yyyy-MM-dd HH:mm:ss")}</td>
            <th>站点名称</th>
            <td>${(cusApplyDTO.stationName)!}</td>
            <th>经营地址</th>
            <td>${(cusApplyDTO.detailAddress)!}</td>
        </tr>
        <tr>
            <th>额度</th>
            <td>${creditLimit!} 元</td>
            <th>站点编号</th>
            <td>${(cusApplyDTO.stationNo)!}</td>
            <th>加盟年限</th>
            <td>${(cusApplyDTO.busiLimit)!}</td>
        </tr>
        <tr>
            <th>实际控制人</th>
            <td>${(cusApplyDTO.controller)!}</td>
            <th>实际控制人身份证号码</th>
            <td>${(cusApplyDTO.controllerIdno)!}</td>
            <th>实际控制人联系方式</th>
            <td>${(cusApplyDTO.controllerPhone)!}</td>
        </tr>
        <tr>
            <th>电子邮箱</th>
            <td>${(cusApplyDTO.controllerEmail)!}</td>
            <th></th>
            <td></td>
            <th></th>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>