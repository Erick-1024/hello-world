<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="专项计划管理新增" jsFiles=["common/formValidator.js","page/asset/specialprogram/formValidatorRules.js","page/asset/specialprogram/specialProgramBusiness.js","page/asset/specialprogram/specialProgramManage.js","page/asset/specialprogram/addSpecialProgram.js","common/dateutil.js","page/asset/customer/jquery.nav.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
	curTopMenu = "证券化发行管理" curSubMenu = "专项计划管理" removeExtHeader = false removeExtFooter = true>
	<div class="pro-bg" style="background: #fff;min-width: 1500px;">
    <form class="" id="specialProgram">
        <div class="left-nav">
            <ul id="client-nav">
                <li class="current"><a href="#nav-one" style="text-indent:5px;">基本信息&ensp;▶</a></li>
                <li><a href="#nav-two" style="text-indent:5px;">原始权益人&ensp;▶</a></li>
                <li><a href="#nav-three" style="text-indent:5px;">资产服务机构&ensp;▶</a></li>
            </ul>
        </div>
	
        <div class="client-bg">
            <!--弹窗触发按钮 -->
            <a class="status-normal status-chk limit-add-btn" href="javascript:void(0);" style="display:none;"></a>
            <a class="status-normal status-chk limit-next-btn" href="javascript:void(0);" style="display:none;"></a>
            <a class="status-normal status-chk message-pop" href="javascript:void(0);" style="display:none;"></a>
            <a class="status-normal status-chk message-popX" href="javascript:void(0);" style="display:none;"></a>
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
                        <th><span class="redFalg">*</span>专项计划编号</th>
                        <td><input type="text" style="width:200px;" class="" name="id" id ="specialProgramId" value="${specialProgramId!}"></td>
                        <th><span class="redFalg">*</span>专项计划名称</th>
                        <td><input type="text" style="width:200px;" class="" name="specialProgramName" id ="specialProgramName" value=""></td>
                        <th><span class="redFalg">*</span>基础资产类型</th>
                        <td>
                            <select class="" style="width:200px;" id ="underlyingAssetType" name="underlyingAssetType" data-role="dropdownlist">
                            	<#list basicAssetTypes as basicAssetType>		
									<option value=${basicAssetType.name()!}>${basicAssetType.desc()!}</option>		
								</#list>
                            </select>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <th><span class="redFalg">*</span>预计成立日期</th>
                        <td><input type="text" style="width:200px;" class="datepicker startDate hasIcon" id ="estimateEstablishmentDate" name="estimateEstablishmentDate" value="" ></td>
                        <th><span class="redFalg">*</span>状态</th>
                        <td>
                            <select class="" style="width:200px;" id ="status" name ="status" data-role="dropdownlist">
									<option value="CREATE">立项</option>			
                            </select>
                        </td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th><span class="redFalg">*</span>管理人名称</th>
	                        <td><input type="text" style="width:200px;" class="" id ="managerName" name="managerName" value="${companyName!}">
	                        	<span class="new-manual-text" style="display:none" id ="">该券商尚未注册，确定是否指定为该专项计划的管理人？</span>
	                        </td>
                        <th>律所</th>
                        <td><input type="text" style="width:200px;" class="" id ="lawFirmName" name="lawFirmName" value=""></td>
                        <th>会计事务所</th>
                        <td><input type="text" style="width:200px;" class="" id ="accountingFirmName" name="accountingFirmName" value=""></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>监管银行</th>
                        <td><input type="text" style="width:200px;" class="" id ="supervisionBank" name="supervisionBank" value=""></td>
                        <th>托管银行</th>
                        <td><input type="text" style="width:200px;" class="" id ="custodianBank" name="custodianBank" value=""></td>
                        <th>评级机构</th>
                        <td><input type="text" style="width:200px;" class="" id ="ratingAgency" name="ratingAgency" value=""></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>资产评估机构</th>
                        <td><input type="text" style="width:200px;" class="" id ="assetEvaluationAgency" name="assetEvaluationAgency" value=""></td>
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
                <div style="margin-top:15px;">
                    <a class="status-normal status-chk add-appoint-btn" href="javascript:void(0);">新增</a>
                    <a class="status-normal status-chk del-appoint-btn" href="javascript:void(0);">删除</a>


                </div>
                <div class="min-box-width" style="width:400px;">
                    <table class="m-table fact-table" style="border-bottom:none;">
                        <colgroup>
                            <col width="15%">
                            <col width="">
                            <col width="17px">
                        </colgroup>
                        <thead>
                        <tr class="top-list-one">
                            <th>选择</th>
                            <td>名称</td>
                            <td></td>
                        </tr>
                        </thead>
                    </table>
                    <div class="fact-tb-box">
                        <table class="m-table fact-table-two" style="border:none;margin-top:0;" id="interests-tb">
                            <colgroup>
                                <col width="15%">
                                <col width="">

                            </colgroup>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!--资产服务机构-->
            <div class="pro-title" id="nav-three"><span class="pro-title-left">资产服务机构</span><span class="pro-title-right">折叠</span></div>
            <div class="client-hide-bg">
                <div class="">
                    <a class="status-normal status-chk add-cost-btn" href="javascript:void(0);">新增</a>
                    <a class="status-normal status-chk del-cost-btn" href="javascript:void(0);">删除</a>

                </div>
                <div class="min-box-width" style="width:400px;">
                    <table class="m-table fact-table" style="border-bottom:none;">
                        <colgroup>
                            <col width="15%">
                            <col width="">
                            <col width="17px">
                        </colgroup>
                        <thead>
                        <tr class="top-list-one">
                            <th>选择</th>
                            <td>名称</td>
                            <td></td>
                        </tr>
                        </thead>
                    </table>
                    <div class="fact-tb-box">
                        <table class="m-table fact-table-two" style="border:none;margin-top:0;" id="asset-tb">
                            <colgroup>
                                <col width="15%">
                                <col width="">
                            </colgroup>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>



            <div class="client-foot">
             <input class="form-search-link" type="button" value="提交" id ="submit"  style="border:0;">
                <a class="default-link back-link" href="${basepath!}/asset/specialprogram/specialProgram/list">返回</a>
            </div>
        </div>
    </form>
</div>
<!--原始权益人弹窗-->
<script id="template-resetPwd-one" type="text/x-kendo-template">
    <div class="dlg-wrapper">
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
                        <td><input type="text" class="interests-input" id="companyNameId" style="width:180px;"></td>
                        <th>客户类型</th>
                        <td>
                            <select class="" style="width:180px;" id ="userTypeId" data-role="dropdownlist">
                                <option value="FACTOR">保理商</option>
                               <#-- <option value="BROKERTRUSTORG">券商/信托</option>
                                <option value="OTHERORG">其他参与机构</option> -->
                            </select>
                        </td>
                        <td><a class="form-search-btn" id ="form-search-btnOne" href="javascript:void(0);" style="float:right;"><i class="searchIcon"></i>搜索</a></td>
                    </tr>
                    </tbody>
                </table>
        </div>
        <form class="out-new-form">
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
                        <td><input type="text" class="asset-input" id="companyName" style="width:180px;"></td>
                        <th>客户类型</th>
                        <td>
                            <select class="" style="width:180px;" id="userType"  data-role="dropdownlist">
                                <option value="FACTOR">保理商</option>
                             <#--   <option value="BROKERTRUSTORG">券商/信托</option>
                                <option value="OTHERORG">其他参与机构</option>  -->
                            </select>
                        </td>
                        <td><a class="form-search-btn" id ="form-search-btnTwo" href="javascript:void(0);" style="float:right;"><i class="searchIcon"></i>搜索</a></td>
                    </tr>
                    </tbody>
                </table>

            
        </div>
         <form class="out-new-form">
        <div class="dlg-del-row">
            <div class="monitor-grid" id="asset-grid"></div>
        </div>
        <div class="dlg-del-row">
            <a class="default-link confirm-link" id="asset-btn" href="javascript:void(0);">确认</a>
            <a class="default-link back-link" href="javascript:void(0);">返回</a>
        </div>
        </form>
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
<!--弹窗1-->
<script id="tipBox_template1" type="text/x-kendo-template">
	<div id="tip-box-window" class="dlg-wrapper">
		<div class="dlg-del-row">
			<span class="dlg-notice"></span>
			<span id="notice-content" class="notice-content"></span>
		</div>
		<div class="dlg-del-row">
			<a class="default-link confirm-link" href="${basepath}/asset/specialprogram/specialProgram/list">确认</a>
			<#-- <a class="default-link back-link" href="javascript:void(0);">返回</a> -->
		</div>
	</div>
</script>

<script id="tipBox_template2" type="text/x-kendo-template">
	<div id="tip-box-window" class="dlg-wrapper">
		<div class="dlg-del-row">
			<span class="dlg-notice"></span>
			<span id="notice-content" class="notice-content"></span>
		</div>
		<div class="dlg-del-row" id ="addSpecial">
			<a class="default-link confirm-link" id="specialDel" href="javascript:void(0);">确认</a>
			 <a class="default-link back-link" href="javascript:void(0);" style ="display:none">返回</a> 
		</div>
	</div>
</script>

<script id="tipBox_template3" type="text/x-kendo-template">
	<div id="tip-box-window" class="dlg-wrapper">
		<div class="dlg-del-row">
			<span class="dlg-notice"></span>
			<span id="notice-content" class="notice-content"></span>
		</div>
		<div class="dlg-del-row">
			<a class="default-link confirm-link" id="assetIdDel" href="javascript:void(0);">确认</a>
			 <a class="default-link back-link" href="javascript:void(0);">返回</a>
		</div>
	</div>
</script>
<!--权限配置-->
<script>
	var specialplan_info_detail = ${authorizeKey("BAI_CI_DETAIL")?string("true","false")};
	var specialplan_info_modify = ${authorizeKey("BAI_CI_EDIT")?string("true","false")};
</script>
<footer>Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
<div style="height:70px"></div>	
</@hb.htmlBase>