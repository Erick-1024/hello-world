<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="专项计划" jsFiles=["page/asset/specialprogram/specialProgramBusiness.js","page/asset/customer/jquery.nav.js","common/dateutil.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
	curTopMenu = "证券化发行管理" curSubMenu = "专项计划管理" removeExtHeader = false removeExtFooter = true>
	<div class="pro-bg" style="background: #fff;min-width: 1500px;">
    <form class="" id="">
        <div class="left-nav">
            <ul id="client-nav">
                <li class="current"><a href="#nav-one">基本信息&ensp;▶</a></li>
                <li><a href="#nav-two">原始权益人&ensp;▶</a></li>
                <li><a href="#nav-three">资产服务机构&ensp;▶</a></li>
            </ul>
        </div>

        <div class="client-bg">
            <!--弹窗触发按钮 -->
            <a class="status-normal status-chk message-pop" href="javascript:void(0);" style="display:none;"></a>
            <!--基本信息-->
            <a class="open-appoint-btn" href="javascript:void(0);" style="display:none;"></a>
            <div class="pro-title" id="nav-one"><span class="pro-title-left">基本信息</span></div>
            <div class="client-hide-bg">
                <table class="client-tb">
                    <colgroup>
                        <col width="120px">
                        <col width="300px">
                        <col width="120px">
                        <col width="300px">
                        <col width="120px">
                        <col width="300px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>专项计划编号</th>
                        <td>${specialProgramDTO.id!}</td>
                        <th>专项计划名称</th>
                        <td>${specialProgramDTO.specialProgramName!}</td>
                        <th>基础资产类型</th>
                        <td>${specialProgramDTO.underlyingAssetType.desc()!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>预计成立日期</th>
                        <td>${specialProgramDTO.estimateEstablishmentDate!}</td>
                        <th>状态</th>
                        <td>${specialProgramDTO.status.desc()!}</td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>管理人名称</th>
                        <td>${specialProgramDTO.managerName!}</td>
                        <th>律所</th>
                        <td>${specialProgramDTO.lawFirmName!}</td>
                        <th>会计事务所</th>
                        <td>${specialProgramDTO.accountingFirmName!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>监管银行</th>
                        <td>${specialProgramDTO.supervisionBank!}</td>
                        <th>托管银行</th>
                        <td>${specialProgramDTO.custodianBank!}</td>
                        <th>评级机构</th>
                        <td>${specialProgramDTO.ratingAgency!}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>资产评估机构</th>
                        <td>${specialProgramDTO.assetEvaluationAgency!}</td>
                        <th></th>
                        <td></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!--原始权益人-->
            <div class="pro-title" id="nav-two"><span class="pro-title-left">原始权益人</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">

                <div class="min-box-width" style="width:400px;">
                    <table class="m-table fact-table" style="border-bottom:none;">
                        <colgroup>
                            <col width="">
                            <col width="17px">
                        </colgroup>
                        <thead>
                        <tr class="top-list-one">
                            <td>名称</td>
                            <td></td>
                        </tr>
                        </thead>
                    </table>
                    <div class="fact-tb-box">
                        <table class="m-table fact-table-two" style="border:none;margin-top:0;" id="interests-tb">
                            <colgroup>
                                <col width="">

                            </colgroup>
                            <tbody>
								 <#if specialProgramDTO.originators?exists >
				                    <#list specialProgramDTO.originators as originator>
                           				 <tr class="client-add-tr">
					                        <td style ="border-top:1px dashed #d0d0d0;">${originator.originatorName!}</td>
                            				</tr>
				                    </#list>
			                    </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!--资产服务机构-->
            <div class="pro-title" id="nav-three"><span class="pro-title-left">资产服务机构</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">

                <div class="min-box-width" style="width:400px;">
                    <table class="m-table fact-table" style="border-bottom:none;">
                        <colgroup>
                            <col width="">
                            <col width="17px">
                        </colgroup>
                        <thead>
                        <tr class="top-list-one">
                            <td>名称</td>
                            <td></td>
                        </tr>
                        </thead>
                    </table>
                    <div class="fact-tb-box">
                        <table class="m-table fact-table-two" style="border:none;margin-top:0;" id="asset-tb">
                            <colgroup>
                                <col width="">
                            </colgroup>
                            <tbody>
                               <#if specialProgramDTO.serviceAgencys?exists >
				                    <#list specialProgramDTO.serviceAgencys as serviceAgency>
                            			<tr class="client-add-tr">
					                        <td style ="border-top:1px dashed #d0d0d0;">${serviceAgency.serviceAgencyName!}</td>
                            			</tr>
				                    </#list>
			                    </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>



            <div class="client-foot">
                <a class="default-link back-link" href="${basepath}/asset/specialprogram/specialProgram/list">返回</a>
            </div>
        </div>
    </form>
</div>
<!--原始权益人弹窗-->
<script id="template-resetPwd-one" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <form class="out-new-form">
            <div class="dlg-del-row">

                <table class="client-tb">
                    <colgroup>
                        <col width="80px">
                        <col width="220px">
                        <col width="80px">
                        <col width="220px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>企业名称</th>
                        <td><input type="text" class="interests-input" style="width:180px;"></td>
                        <th>客户类型</th>
                        <td>
                            <select class="" style="width:180px;"  data-role="dropdownlist">
                                <option value="">保理商</option>
                                <option value="">券商/信托</option>
                                <option value="">其他参与机构</option>
                            </select>
                        </td>
                        <td><a class="form-search-btn" href="javascript:void(0);" style="float:right;"><i class="searchIcon"></i>搜索</a></td>
                    </tr>
                    </tbody>
                </table>

            </div>
            <div class="dlg-del-row">
                <div class="monitor-grid" id="interests-grid"></div>
            </div>
            <div class="dlg-del-row">
                <a class="default-link confirm-link" id="interests-btn" href="javascript:void(0);">确认</a>
                <a class="default-link back-link" href="javascript:void(0);">返回</a>
            </div>
        </form>
    </div>
</script>
<!--资产服务机构弹窗-->
<script id="template-resetPwd-two" type="text/x-kendo-template">
    <div class="dlg-wrapper">
        <div class="dlg-del-row">
            <form class="out-new-form">
                <table class="client-tb">
                    <colgroup>
                        <col width="80px">
                        <col width="220px">
                        <col width="80px">
                        <col width="220px">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>企业名称</th>
                        <td><input type="text" class="asset-input" style="width:180px;"></td>
                        <th>客户类型</th>
                        <td>
                            <select class="" style="width:180px;"  data-role="dropdownlist">
                                 <option value="">保理商</option>
                                <option value="">券商/信托</option>
                                <option value="">其他参与机构</option>
                            </select>
                        </td>
                        <td><a class="form-search-btn" href="javascript:void(0);" style="float:right;"><i class="searchIcon"></i>搜索</a></td>
                    </tr>
                    </tbody>
                </table>

            </form>
        </div>
        <div class="dlg-del-row">
            <div class="monitor-grid" id="asset-grid"></div>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="asset-btn" href="javascript:void(0);">确认</a>
            <a class="default-link back-link" href="javascript:void(0);">返回</a>
        </div>
    </div>
</script>
<!--提示弹窗-->
<script id="tipBox_template" type="text/x-kendo-template">
    <div id="tip-box-window" class="dlg-wrapper">
        <div class="dlg-del-row">
            <span class="dlg-notice"></span>
            <span id="notice-content" class="notice-content"></span>
        </div>
    </div>
</script>
<script type="text/javascript">


    //声明两个全局变量用于保存两个弹窗中选中的值的value
    var data1 = "";
    var data2 = "";

    $(function(){
        //弹窗初始化
        openPop();
        //新增原始权益人
        $("body").on("click",".add-appoint-btn",function(){
            $(".limit-add-btn").click();
            $(".k-widget.k-window").css("top","25%");
            interestsPopGrid();
        });
        //新增资产服务机构
        $(".add-cost-btn").on("click",function(){
            $(".limit-next-btn").click();
            $(".k-widget.k-window").css("top","25%");
            assetPopGrid();
        });
        //原始权益人新增事件
        $("body").on("click","#interests-btn",function(){
            var $name = "";
            if(data1!=""){
                $name = data1;
                $(".back-link").click();
                addClientRow("interests-tb",$name);
            }else if($(".interests-input").val()!=""){
                $name = $(".interests-input").val();
                $(".back-link").click();
                addClientRow("interests-tb",$name);
            }else{
                $(".message-pop").click();
                $("#tip-box-window .dlg-notice").addClass("notice-icon01");
                $("#tip-box-window .notice-content").text("搜索并选择企业名称！");
            }

        });
        //资产服务机构新增事件
        $("body").on("click","#asset-btn",function(){
            var $name = "";
            if(data2!=""){
                $name = data2;
                $(".back-link").click();
                addClientRow("asset-tb",$name);
            }else if($(".asset-input").val()!=""){
                $name = $(".asset-input").val();
                $(".back-link").click();
                addClientRow("asset-tb",$name);
            }else{
                $(".message-pop").click();
                $("#tip-box-window .dlg-notice").addClass("notice-icon01");
                $("#tip-box-window .notice-content").text("搜索并选择企业名称！");
            }
        });
        //删除原始权益人
        $(".del-appoint-btn").on("click",function(){
            removeClientRow("interests-tb");
        });
        //删除资产服务机构
        $(".del-cost-btn").on("click",function(){
            removeClientRow("asset-tb");
        });



    });
    //弹窗初始化
    function openPop(){
        //初始化原始权益人弹窗
        new PopWindow(".limit-add-btn", {
            title: "新增原始权益人",
            width: 800,
            reload: true,
            template: "#template-resetPwd-one"
        }).init();
        //初始化资产服务机构弹窗
        new PopWindow(".limit-next-btn", {
            title: "新增资产服务机构",
            width: 800,
            reload: true,
            template: "#template-resetPwd-two"
        }).init();
        //初始化消息弹窗
        new PopWindow(".message-pop",{
            title: "提示",
            width: 420,
            reload: true,
            template: "#tipBox_template"
        }).init();
    }
    //原始权益人弹出框表格
    function interestsPopGrid(){
        $("#interests-grid").kendoGrid({
            selectable: "row",  //设置可选择数据行
            sortable: false,  //列排序
            dataSource:{
                pageSize: 5,
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
            dataBound: onDataBoundOne,
            columns: [{
                field: "id",
                title: "选择",
                width: 120,
                attributes: {
                    style: "text-align: center"
                },
                template: function(item){
                    return "<input type='radio' name='interests-choose' value='' class='client-radio'>";
                }
            },{
                field: "sequence",
                title: "企业名称",
                width: 300,
                attributes: {
                    style: "text-align: center"
                }
            },{
                field: "customer",
                title: "客户类型",
                width: 120,
                attributes:{
                    style:"text-align:center"
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
    }
    //资产服务机构弹出框表格
    function assetPopGrid(){
        $("#asset-grid").kendoGrid({
            selectable: "row",  //设置可选择数据行
            sortable: false,  //列排序
            dataSource:{
                pageSize: 5,
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
            dataBound: onDataBoundTwo,
            columns: [{
                field: "id",
                title: "选择",
                width: 120,
                attributes: {
                    style: "text-align: center"
                },
                template: function(item){
                    return "<input type='radio' name='asset-choose' value='' class='client-radio'>";
                }
            },{
                field: "sequence",
                title: "企业名称",
                width: 300,
                attributes: {
                    style: "text-align: center"
                }
            },{
                field: "customer",
                title: "客户类型",
                width: 120,
                attributes:{
                    style:"text-align:center"
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
    }
    //原始权益人弹出框表格分页事件
    function onDataBoundOne(){
        if(data1=="")
            return;
        var $length = $("input[name=interests-choose]");
        for(var i=0;i<$length.length;i++){
            if($length.eq(i).val()==data1){
                $length.eq(i).prop("checked","checked");
            }
        }
    }
    //资产服务机构弹出框表格分页事件
    function onDataBoundTwo(){
        if(data1=="")
            return;
        var $length = $("input[name=asset-choose]");
        for(var i=0;i<$length.length;i++){
            if($length.eq(i).val()==data2){
                $length.eq(i).prop("checked","checked");
            }
        }
    }
    //表格操作（增加）
    function addClientRow(tab,data){
        var $tr=$("#"+tab+""),
                $html=$('<tr class="client-add-tr">'+
                        '<th><input type="checkbox" class="input-new" name="'+tab+'"></th>'+
                        '<td>'+data+'</td>'+
                        '</tr>');
        $html.appendTo($tr);
    }
    //表格操作（删除）
    function removeClientRow(ckb) {
        //获取选中的复选框，然后循环遍历删除
        var ckbs = $("input[name=" + ckb + "]:checked");
        if (ckbs.size() == 0) {
            $(".message-pop").click();
            $("#tip-box-window .dlg-notice").addClass("notice-icon01");
            $("#tip-box-window .notice-content").text("请选择要删除的行！");
            return;
        }
        ckbs.each(function () {
            $(this).parent().parent().remove();
        });
    }
</script>
	
<!--权限配置-->
<script>
	var specialplan_info_detail = ${authorizeKey("BAI_CI_DETAIL")?string("true","false")};
	var specialplan_info_modify = ${authorizeKey("BAI_CI_EDIT")?string("true","false")};
</script>
<footer>Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
<div style="height:70px"></div>	
</@hb.htmlBase>