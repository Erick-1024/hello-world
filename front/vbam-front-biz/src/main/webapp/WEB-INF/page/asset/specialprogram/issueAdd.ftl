<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="专项计划成立管理新增" jsFiles=["common/formValidator.js","page/asset/specialprogram/formValidatorRules.js","page/asset/specialprogram/specialProgramBusiness.js","page/asset/specialprogram/issueAdd.js","common/dateutil.js","page/asset/customer/jquery.nav.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
	curTopMenu = "证券化发行管理" curSubMenu = "发行后管理" >
	<div class="main-container">
    <section class="whiteBg">
        <div class="thirdNavGroup">
            <ul>
                <li class="thirdNavCut" id="show-nav">
                    <a class="thirdNavItem" href="javascript:void(0);">基本信息</a>
                </li>
                <li style="display:none;" id="hide-nav">
                    <a class="thirdNavItem" href="javascript:void(0);">循环购买信息</a>
                </li>
            </ul>
        </div>
        <form id ="issueAdd-id">
        <div class="plan-box">
            <div>
                <div class="pro-title" style="height:40px;line-height:40px;"><span class="pro-title-left">基本信息</span></div>
                <table class="client-tb" style="margin-top:10px;">
                    <colgroup>
                        <col width="150">
                        <col width="300">
                        <col width="150">
                        <col width="300">
                        <col width="150">
                        <col width="300">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>专项计划编号</th>
                        <td><input type="text" class="" value="${specialProgramDTO.id!}" id ="specialProgramId" name="specialProgramId" style="width:200px;" disabled="disabled"></td>
                        <th>专项计划名称</th>
                        <td><input type="text" class="" value="${specialProgramDTO.specialProgramName!}" id ="specialProgramName" name="specialProgramName" style="width:200px;" disabled="disabled"></td>
                        <th>基础资产类型</th>
                        <td><input type="text" class="" value="${specialProgramDTO.underlyingAssetType.desc()!}" id="underlyingAssetType1" name="underlyingAssetType" style="width:200px;" disabled="disabled"></td>
                        	<input type="hidden" class="" value="FACTOR" style="width:200px;" id="underlyingAssetType" name="underlyingAssetType">
                        <td></td>
                    </tr>
                    <tr>
                        <th>管理人名称</th>
                        <td><input type="text" class="" value="${specialProgramDTO.managerName!}" id ="managerName" name="managerName" style="width:200px;" disabled="disabled"></td>
                        <th>律所</th>
                        <td><input type="text" class="" value="${specialProgramDTO.lawFirmName!}" id ="lawFirmName" name="lawFirmName" style="width:200px;" disabled="disabled"></td>
                        <th>会计事务所</th>
                        <td><input type="text" class="" value="${specialProgramDTO.accountingFirmName!}" id ="accountingFirmName" name="accountingFirmName" style="width:200px;" disabled="disabled"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>监管银行</th>
                        <td><input type="text" class="" value="${specialProgramDTO.supervisionBank!}" id ="supervisionBank" name="supervisionBank" style="width:200px;" disabled="disabled"></td>
                        <th>托管银行</th>
                        <td><input type="text" class="" value="${specialProgramDTO.custodianBank!}" id = "custodianBank" name="custodianBank" style="width:200px;" disabled="disabled"></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>原始权益人</th>
                        <td style="position:relative;">
                            <div style="position:absolute;top:0;left:0;width:800px;padding-top:8px;">
                            	<#if specialProgramDTO.originators?exists >
				                    <#list specialProgramDTO.originators as originator>
		                                <input type="text" class="" value="${originator.originatorName!}" name="originator" style="width:200px;float:left;margin-right:50px;" disabled="disabled">
				                    </#list>
			                    </#if>
                            </div>
                        </td>
                        <th></th>
                        <td></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>资产服务机构名称</th>
                        <td style="position:relative;">
                            <div style="position:absolute;top:0;left:0;width:1000px;padding-top:8px;">
                        		<#if specialProgramDTO.serviceAgencys?exists >
				                    <#list specialProgramDTO.serviceAgencys as serviceAgency>
                                		<input type="text" class="" value="${serviceAgency.serviceAgencyName!}" name="serviceAgency" style="width:200px;float:left;margin-right:50px;" disabled="disabled">
				                    </#list>
		                   		 </#if>
                            </div>
                        </td>
                        <th></th>
                        <td></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th><span class="redFalg">*</span>状态</th>
                        <td>
                            <select class="" style="width:200px;" id ="status" name ="status" data-role="dropdownlist">
                                <option value="ESTABLISH">成立</option>
                            </select>
                        </td>
                        <th><span class="redFalg">*</span>成立日期</th>
                        <td><input type="text" class="datepicker issue-date hasIcon" value="" id ="establishmentDate" name="establishmentDate" style="width:200px;" validationMessage="成立日期不能为空" required></td>
                        <th><span class="redFalg">*</span>续存期</th>
                        <td>
                        	<label class="client-unit-box" style="position:relative;">
                        	<input type="text" class="" value="" id="renewalPeriod" name="renewalPeriod" onkeyup="clearNoNum(this)" style="width:200px;" validationMessage="续存期不能为空" required>
                        	<span class="client-unit-two">天</span>
                            </label>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <th><span class="redFalg">*</span>预计到期日期</th>
                        <td><input type="text" class="datepicker startDate hasIcon" value="" id="estimateDueDate" name="estimateDueDate" style="width:200px;" validationMessage="预计到期日期不能为空" required></td>
                        <th><span class="redFalg">*</span>法定到期日期</th>
                        <td><input type="text" class="datepicker close-date hasIcon" value="" id ="statutoryDueDate" name="statutoryDueDate" style="width:200px;" validationMessage="法定到期日期不能为空" required></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>总规模</th>
                        <td><input type="text" class="moneyNum" value="${formatMoney(specialProgramDTO.gross!'')}" id ="gross" name="gross" style="width:200px;" disabled="disabled"></td>
                        <th></th>
                        <td></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>优先A</th>
                        <td><input type="text" class="moneyNum" value="" id ="preferA" name="preferA" style="width:200px;"></td>
                        <th>优先B</th>
                        <td><input type="text" class="moneyNum" value="" id ="preferB" name="preferB" style="width:200px;"></td>
                        <th>次级</th>
                        <td><input type="text" class="moneyNum" value="" id ="defer" name="defer" style="width:200px;"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>授信方式</th>
                        <td style="position:relative;">
                            <div style="position:absolute;top:0;left:0;width:1000px;padding-top:8px;">
                                <input type="text" class="" value="" id="trustMethod" name="trustMethod" style="width:800px;" maxlength="50">
                            </div>
                        </td>
                        <th></th>
                        <td></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>资产池本金余额</th>
                        <td><input type="text" class="moneyNum" value="${formatMoney(specialProgramDTO.assetPoolPrincipalBalance!'')}" id ="assetPoolPrincipalBalance" name="assetPoolPrincipalBalance" style="width:200px;" disabled="disabled"></td>
                        <th>合同笔数</th>
                        <td><input type="text" class="" value="" id ="contractNum" name="contractNum" onkeyup="clearNoNum(this)" style="width:200px;"></td>
                        <th>加权平均合同期限</th>
                        <td>
                        	<label class="client-unit-box" style="position:relative;">
	                        	<input type="text" class="" value="" id ="weightedAverageContractPeriod" name="weightedAverageContractPeriod" onkeyup="clearNoNum(this)" style="width:200px;">
	                        	<span class="client-unit-two">天</span>
                            </label>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>融资人数量</th>
                        <td><input type="text" class="" value="" id ="financeNum" name="financeNum" onkeyup="clearNoNum(this)" style="width:200px;"></td>
                        <th>加权平均利率</th>
                        <td>
                      		<label class="client-unit-box" style="position:relative;">
	                        	<input type="text" class="aaa" value="" id ="weightedAverageInterestRate" name="weightedAverageInterestRate" style="width:200px;">
	                        	<span class="client-unit-two">%</span>
                            </label>
                        </td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>循环购买结构</th>
                        <td>
                            <select class="" style="width:200px;" id="showHide-btn" name ="cyclePurchaseStructure"  data-role="dropdownlist">
                               <#-- <option value="none">请选择</option> -->
                                <option value="否">否</option>
                                <option value="是">是</option>
                            </select>
                        </td>
                        <th></th>
                        <td></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>

                    </tbody>
                </table>
            </div>
            <div style="display:none;">
                <div class="pro-title" style="height:40px;line-height:40px;"><span class="pro-title-left">循环购买信息</span></div>
                <table class="client-tb" style="margin-top:10px;">
                    <colgroup>
                        <col width="150">
                        <col width="300">
                        <col width="150">
                        <col width="300">
                        <col width="150">
                        <col width="300">
                        <col width="">
                    </colgroup>
                    <tbody>
                   <tr>
                        <th>专项计划编号</th>
                        <td><input type="text" class="" value="${specialProgramDTO.id!}" id ="" name="specialProgramId1" style="width:200px;" disabled="disabled"></td>
                        <th>专项计划名称</th>
                        <td><input type="text" class="" value="${specialProgramDTO.specialProgramName!}" name="specialProgramName1" style="width:200px;" disabled="disabled"></td>
                        <th>基础资产类型</th>
                        <td><input type="text" class="" value="${specialProgramDTO.underlyingAssetType.desc()!}" name="underlyingAssetType1" style="width:200px;" disabled="disabled"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>管理人名称</th>
                        <td><input type="text" class="" value="${specialProgramDTO.managerName!}" name="managerName1" style="width:200px;" disabled="disabled"></td>
                        <th>律所</th>
                        <td><input type="text" class="" value="${specialProgramDTO.lawFirmName!}" name="lawFirmName1" style="width:200px;" disabled="disabled"></td>
                        <th>会计事务所</th>
                        <td><input type="text" class="" value="${specialProgramDTO.accountingFirmName!}" name="accountingFirmName1" style="width:200px;" disabled="disabled"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>监管银行</th>
                        <td><input type="text" class="" value="${specialProgramDTO.supervisionBank!}" name="supervisionBank1" style="width:200px;" disabled="disabled"></td>
                        <th>托管银行</th>
                        <td><input type="text" class="" value="${specialProgramDTO.custodianBank!}" name="custodianBank1" style="width:200px;" disabled="disabled"></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>原始权益人</th>
                        <td style="position:relative;">
                            <div style="position:absolute;top:0;left:0;width:800px;padding-top:8px;">
                            	<#if specialProgramDTO.originators?exists >
				                    <#list specialProgramDTO.originators as originator>
		                                <input type="text" class="" value="${originator.originatorName!}" name="originator1" style="width:200px;float:left;margin-right:50px;" disabled="disabled">
				                    </#list>
			                    </#if>
                            </div>
                        </td>
                        <th></th>
                        <td></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>资产服务机构名称</th>
                        <td style="position:relative;">
                            <div style="position:absolute;top:0;left:0;width:1000px;padding-top:8px;">
                            	<#if specialProgramDTO.serviceAgencys?exists >
				                    <#list specialProgramDTO.serviceAgencys as serviceAgency>
                                		<input type="text" class="" value="${serviceAgency.serviceAgencyName!}" name="serviceAgency1" style="width:200px;float:left;margin-right:50px;" disabled="disabled">
				                    </#list>
		                   		 </#if>
                            </div>
                        </td>
                        <th></th>
                        <td></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th><span class="redFalg">*</span>循环期</th>
                        <td id ="add_id">
                        	<input type="text" class="" value="" id ="cyclePeriod" name="cyclePeriod" onkeyup="clearNoNum(this)" style="width:200px;">
                        </td>
                        <th>资金归集日</th>
                        <td><input type="text" class="" value="" id ="capitalAccumulationDate" name="capitalAccumulationDate" style="width:200px;"></td>
                        <th>循环购买日</th>
                        <td><input type="text" class="" value="" id ="cyclePurchaseDate" name="cyclePurchaseDate" style="width:200px;"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>收入回收日</th>
                        <td><input type="text" class="" value="" id ="incomeRecoveryDate" name="incomeRecoveryDate" style="width:200px;"></td>
                        <th>回收款转付日</th>
                        <td><input type="text" class="" value="" id ="recyclingPaymentDate" name="recyclingPaymentDate" style="width:200px;"></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>超额资金</th>
                        <td><input type="text" class="moneyNum" id ="excessFund" value="" name="excessFund" style="width:200px;"></td>
                        <th></th>
                        <td></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>加权平均利率</th>
                        <td><input type="text" class="" value="" id ="cycleWeightedAverageInterestRate" name="cycleWeightedAverageInterestRate" style="width:200px;"></td>
                        <th></th>
                        <td></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>循环购买条款</th>
                        <td style="position:relative;">
                            <div style="position:absolute;top:0;left:0;width:1000px;padding-top:8px;">
                                <input type="text" class="" value="" id ="cyclePurchaseTerm" name="cyclePurchaseTerm" style="width:800px;">
                            </div>
                        </td>
                        <th></th>
                        <td></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>基础资产合格标准</th>
                        <td style="position:relative;">
                            <div style="position:absolute;top:0;left:0;width:1000px;padding-top:8px;">
                                <input type="text" class="" value="" id ="underlyingAssetQualityStandard" name="underlyingAssetQualityStandard" style="width:800px;">
                            </div>
                        </td>
                        <th></th>
                        <td></td>
                        <th></th>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
         </form>
        <div style="text-align: center;padding:15px 0;">
            <a class="default-link confirm-link" href="javascript:void(0);" id ="submit">提交</a>
            <a class="default-link back-link" href="${basepath!}/asset/specialprogram/specialProgramIssue/list">关闭</a>
        </div>
    </section>
</div>



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
			<a class="default-link confirm-link" href="${basepath}/asset/specialprogram/specialProgramIssue/list">确认</a>
			<#-- <a class="default-link back-link" href="javascript:void(0);">返回</a> -->
		</div>
	</div>
</script>
<script type="text/javascript">
    $(function(){
        //循环购买结构触发后事件
        $("#showHide-btn").kendoDropDownList({
            close:onclose
        });
        //tab选项卡切换
        $("body").on("click",".thirdNavGroup ul li",function(e){
            var index = $(this).index();
            $(this).addClass("thirdNavCut").siblings().removeClass("thirdNavCut");
            $(".plan-box>div").eq(index).show().siblings().hide();
        });
    });
    //触发后事件  
    function onclose(){
        var data = $("#showHide-btn").val();
        if(data=='是'){
            $("#hide-nav").show();
            $("#hide-nav").addClass("thirdNavCut").siblings().removeClass("thirdNavCut");
            $(".plan-box>div").eq(1).show().siblings().hide();
            $("#add_id").html('<input type="text" class="" value="" id ="cyclePeriod" name="cyclePeriod" onkeyup="clearNoNum(this)" style="width:200px;" validationMessage="循环期不能为空" required>');
        }else{
            $("#hide-nav").hide();
            $("#add_id").html('<input type="text" class="" value="" id ="cyclePeriod" name="cyclePeriod" onkeyup="clearNoNum(this)" style="width:200px;"> ');
        }
    }
</script>
<#--百分比 -->
<script>         
	$("body").on("change",".aaa",function(){
		var val = $(this).val();
        var reg = /^(100|100\.[0]{1,3}|[0-9]{1,2}|[0-9]{1,2}\.[0-9]{1,3})$/;
        if(reg.test(val) == false){
        	$(this).val('');
        }
	})
</script>
<script>
	function clearNoNum(obj){   
    	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
    	obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是. 
    	obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.   
    	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	}
</script>
</@hb.htmlBase>