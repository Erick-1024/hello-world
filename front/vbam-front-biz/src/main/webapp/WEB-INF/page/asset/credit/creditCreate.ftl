<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="额度管理" jsFiles=["common/formValidator.js","page/asset/project/projectCreate.js","page/asset/project/formValidatorRules.js","page/asset/project/project.js","common/dateutil.js","js/common/ajaxfileupload.js"] cssFiles=["css/project.css","css/monitor.css","css/valifrom.css"] localCssFiles=[] 
	curTopMenu = "额度管理" curSubMenu = "所有列表" removeExtHeader = false removeExtFooter = false>
	<div class="pro-bg" style="background: #fff;">
    <div class="pro-box-bg">
        <div class="first-title">客户信息</div>
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
                    <td>韵达公司</td>
                    <th>客户类型</th>
                    <td>融资申请人</td>
                    <th>总授信额度</th>
                    <td>￥1,000,000,000</td>
                    <th>可用额度</th>
                    <td>￥30,000,000</td>
                </tr>
                <tr></tr>
                <tr>
                    <td><a class="form-search-btn limit-add" href="javascript:void(0);">申请额度</a></td>

                </tr>
                </tbody>
            </table>
        </form>
        <div class="monitor-grid" id="monitorList-grid"></div>
    </div>
</div>
<!--申请额度弹窗-->
<script id="template-resetPwd-five" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <form class="out-new-form">
                <table class="client-tb">
                    <colgroup>
                        <col width="100">
                        <col width="200">
                        <col width="100">
                        <col width="200">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>客户名称</th>
                        <td>韵达公司</td>
                        <th>客户类型</th>
                        <td>融资申请人</td>
                    </tr>
                    <tr>
                        <th>编号</th>
                        <td><input type="text" disabled="disabled" style="width:200px;"></td>
                        <th></th>
                        <td></td>
                    </tr>
                    <tr>
                        <th>额度种类</th>
                        <td>
                            <select class="s-mode" style="width:200px;"  data-role="dropdownlist">
                                <option value="">综合授信</option>
                                <option value="">单笔授信</option>
                            </select>
                        </td>
                        <th>币种</th>
                        <td>
                            <select class="s-mode" style="width:200px;"  data-role="dropdownlist">
                                <option value="">RMB</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>申请金额</th>
                        <td><input type="text" style="width:200px;"></td>
                        <th>申请费用</th>
                        <td><input type="text" style="width:200px;"></td>
                    </tr>
                    <tr>
                        <th>生效日期</th>
                        <td><input type="text" class="time-one data-style hasIcon" style="width:200px;"></td>
                        <th>到期日期</th>
                        <td><input type="text" class="time-two data-style hasIcon" style="width:200px;"></td>
                    </tr>
                    </tbody>
                </table>
                <input type="hidden" class="financing-index-id" value="">
            </form>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" href="javascript:void(0);">确定</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
<!--修改额度弹窗-->
<script id="template-resetPwd-ch" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <form class="out-new-form">
                <table class="client-tb">
                    <colgroup>
                        <col width="100">
                        <col width="200">
                        <col width="100">
                        <col width="200">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>客户名称</th>
                        <td>韵达公司</td>
                        <th>客户类型</th>
                        <td>融资申请人</td>
                    </tr>
                    <tr>
                        <th>编号</th>
                        <td><input type="text" disabled="disabled" style="width:200px;"></td>
                        <th></th>
                        <td></td>
                    </tr>
                    <tr>
                        <th>额度种类</th>
                        <td>
                            <select class="s-mode" style="width:200px;"  data-role="dropdownlist">
                                <option value="">综合授信</option>
                                <option value="">单笔授信</option>
                            </select>
                        </td>
                        <th>币种</th>
                        <td>
                            <select class="s-mode" style="width:200px;"  data-role="dropdownlist">
                                <option value="">RMB</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>申请金额</th>
                        <td><input type="text" style="width:200px;"></td>
                        <th>申请费用</th>
                        <td><input type="text" style="width:200px;"></td>
                    </tr>
                    <tr>
                        <th>生效日期</th>
                        <td><input type="text" class="time-one data-style hasIcon" style="width:200px;"></td>
                        <th>到期日期</th>
                        <td><input type="text" class="time-two data-style hasIcon" style="width:200px;"></td>
                    </tr>
                    <tr>
                        <th>状态</th>
                        <td>生效</td>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
                <input type="hidden" class="financing-index-id" value="">
            </form>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" href="javascript:void(0);">确定</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
<!--成功提示弹窗-->
<script id="tipBox_template" type="text/x-kendo-template">
    <div id="tip-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice"></span>
            <span id="notice-content" class="notice-content"></span>
        </div>
    </div>
</script>
<!--确认弹窗-->
<script id="template-notice" type="text/x-kendo-template">
    <div id="confirm-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <input id="operationObj" type="hidden" value=""/>
            <table class="dlg-rowTable">
                <tr>
                    <td class="dlg-row-left-limit">
                        <span class="dlg-notice notice-icon01"></span>
                    </td>
                    <td class="dlg-row-right-limit">
                        <span class="notice-content">申请额度成功！</span>
                    </td>
                </tr>
            </table>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" href="javascript:void(0);">确认</a>
            <a class="default-link back-link" href="javascript:void(0);">取消</a>
        </div>
    </div>
</script>
<script type="text/javascript">
    $(function(){

        //初始化申请弹窗
        new PopWindow(".limit-add", {
            title: "申请额度",
            width: 800,
            reload: true,
            template: "#template-resetPwd-five"
        }).init();
        //初始化修改融资弹窗
        new PopWindow(".limit-change", {
            title: "修改额度",
            width: 800,
            reload: true,
            template: "#template-resetPwd-ch"
        }).init();
        //初始化确认弹窗
        confirmPopWindow = new PopWindow({
            title: "提示",
            width: 420,
            reload: true,
            template: "#template-notice"
        }).init();

        //初始化成功提示弹窗
        tipPopWindow = new PopWindow({
            title: "提示",
            width: 420,
            reload: true,
            template: "#tipBox_template"
        }).init();
        $("body").on("click", "#confirm-box-window .confirm-link", function(e) {
            var win = $(e.target).closest(".k-window");
            var overlay = win.prev(".k-overlay");
            if (win.is(":visible")) {
                win.css("display", "none");
                overlay.css("display", "none");
            }
            tipPopWindow.open().center();
            $("#tip-box-window .dlg-notice").addClass("notice-icon02");
            $("#tip-box-window .notice-content").text("操作成功");
        });
        //点击"冻结"
        $("body").on("click", ".freeze-link", function(e){
            confirmPopWindow.open().center();
            $("#confirm-box-window #operationObj").val($(this).attr("data"));
            $("#confirm-box-window .notice-content").html("是否确认冻结此余额？<br/>业务合同号：121212");
        });

        //点击"解冻"
        $("body").on("click", ".unfreeze-link", function(e){
            confirmPopWindow.open().center();
            $("#confirm-box-window #operationObj").val($(this).attr("data"));
            $("#confirm-box-window .notice-content").html("是否确认解冻此余额？<br/>业务合同号：1212122");
        });

        //点击"撤销"
        $("body").on("click", ".repeal-link", function(e){
            confirmPopWindow.open().center();
            $("#confirm-box-window #operationObj").val($(this).attr("data"));
            $("#confirm-box-window .notice-content").text("确认要撤销此余额吗？");
        });

        //点击"作废"
        $("body").on("click", ".nullify-link", function(e){
            confirmPopWindow.open().center();
            $("#confirm-box-window #operationObj").val($(this).attr("data"));
            $("#confirm-box-window .notice-content").text("确认要作废此余额吗？");
        });

        //时间空间 （针对弹窗中时间空间失效的情况）
        $("body").delegate(".time-one", "focusin", function(){
            //选择日期范围是今天之前(<=今天)
            $(this).datepicker({
                format: "yyyy-mm-dd",
                language: "zh-CN",
                autoclose: true,
                todayHighlight: true,
                weekStart: 0,
                firstDay: 0,
                minDate: "0",
                onClose: function (selectedDate) {
                    $("input.time-two").datepicker("option", "minDate", selectedDate);
                }
            }).on("show", function () {
                $("div.datepicker table thead .prev").html("");
                $("div.datepicker table thead .next").html("");
            });
            //选择日期范围是大于等于生效日
            $(".time-two").datepicker({
                format: "yyyy-mm-dd",
                language: "zh-CN",
                autoclose: true,
                todayHighlight: true,
                weekStart: 0,
                firstDay: 0
            }).on("show", function () {
                $("div.datepicker table thead .prev").html("");
                $("div.datepicker table thead .next").html("");
            });
        });
        //初始化表格
        $("#monitorList-grid").kendoGrid({
            selectable: "row",  //设置可选择数据行
            sortable: true,  //列排序
            dataSource:{
                pageSize: 10,
                type: "json", //后台返回的数据类型
                method: "post",
                transport: {
                    read: {
                        data: { q: "html5" },
                        type : "POST",
                        url: ""
                    }
                }
            },
            //解析远程响应的数据
            schema:{
                model: {
                    id: "id",
                    fields: {
                        grade: {type: "int"}
                    }
                }
            },
            columns: [{
                field: "sequence",
                title: "业务合同号",
                width: 100,
                attributes: {
                    style: "text-align: center"
                }
            },{
                field: "customer",
                title: "额度种类",
                width: 100,
                attributes:{
                    style:"text-align:center"
                }
            },{
                field: "grade",
                title: "币种",
                width: 80,
                attributes: {
                    style: "text-align: center"
                }
            },{
                field: "risk",
                title: "申请金额",
                width: 100,
                attributes: {
                    style: "text-align: center"
                }
            },{
                field: "risk",
                title: "可用余额",
                width: 100,
                attributes: {
                    style: "text-align: center"
                }
            },{
                field: "risk",
                title: "生效日期",
                width: 100,
                attributes: {
                    style: "text-align: center"
                }
            },{
                field: "risk",
                title: "到期日期",
                width: 100,
                attributes: {
                    style: "text-align: center"
                }
            },{
                field: "risk",
                title: "申请费用",
                width: 100,
                attributes: {
                    style: "text-align: center"
                }
            },{
                field: "risk",
                title: "状态",
                width: 80,
                attributes: {
                    style: "text-align: center"
                }
            },{
                field: "measure",
                title: "操作",
                width: 260,
                attributes: {
                    style: "text-align: center"
                },
                template: function(item){
                    var edHtml = "<a class='comRow-link limit-change' href='javascript:void(0);'>修改</a>" +
                            "<a class='comRow-link freeze-link' href='javascript:void(0);'>冻结</a>" +
                            "<a class='comRow-link unfreeze-link' href='javascript:void(0);'>解冻</a>" +
                            "<a class='comRow-link repeal-link' href='javascript:void(0);'>撤销</a>" +
                            "<a class='comRow-link nullify-link' href='javascript:void(0);'>作废</a>" +
                            "<a class='comRow-link' href='javascript:void(0);'>查看历史</a>";
                    return edHtml;
                }
            }],
            pageable: {
                pageSizes: false,  //设置每页显示行数
                buttonCount: 5,  //显示页数
                messages: {
                    display: "共<span class='sumData'>{2}</span>条数据",
                    empty: "没有数据",
                    page: "页",
                    of: "/ {0}",
                    itemsPerPage: "条/页",
                    first: "第一页",
                    previous: "前一页",
                    next: "下一页",
                    last: "最后一页"
                }
            }
        });
    });
</script>
</@hb.htmlBase>