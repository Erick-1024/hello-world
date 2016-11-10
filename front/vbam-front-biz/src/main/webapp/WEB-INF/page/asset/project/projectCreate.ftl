<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="项目管理" jsFiles=["common/formValidator.js","page/asset/project/projectCreate.js","page/asset/project/formValidatorRules.js","page/asset/project/project.js","common/dateutil.js","js/common/ajaxfileupload.js"] cssFiles=["css/project.css","css/monitor.css","css/valifrom.css"] localCssFiles=[] 
	curTopMenu = "项目管理" curSubMenu = "所有列表" removeExtHeader = false removeExtFooter = false>
<div class="pro-bg" >
<form id="createProject" name ="createProject" method ="post" action ="">
    <div class="pro-title"><span class="pro-title-left">项目要素</span></div>
    <table class="pro-table td-table">
        <colgroup>
            <col width="80">
            <col width="100">
            <col width="80">
            <col width="100">
            <col width="80">
            <col width="100">
        </colgroup>
        <tbody>
        <tr>
            <th><span class="redFalg">*</span>项目名称</th>
            <td>
             <span class="inline-box"><input type="text" id ="name" name ="name" class="pro-table-input pro-input-width"  required/></span>
                <span class="inline-box"><div class=""></div></span>
            </td>
            <th><span class="redFalg">*</span>业务类型</th>
            <td>
                <select class="out-input"  id= "style" name ="type" style="width:200px;"  data-role="dropdownlist">
                    <option value="platform">平台</option>
                </select><span class="pro-error">错误提示</span>
            </td>
            <th><span class="redFalg">*</span>项目状态</th>
            <td>
                <select class="out-input" id = "status" name ="status" style="width:200px;"  data-role="dropdownlist">
                   <option value="CREATE">立项</option>
                   <option value="GOING">进行中</option> 
                </select><span class="pro-error">错误提示</span>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="pro-title">核心企业信息</span><span class="pro-title-right">折叠</span></div>
    <div class="hide-box" >
    <table class="pro-table td-table">
        <colgroup>
            <col width="80">
            <col width="100">
            <col width="50">
            <col width="100">
            <col width="80">
            <col width="100">
        </colgroup>
        <tbody>
        <tr>
            <th><span class="redFalg">*</span>核心企业名称</th>
            <td>
            	<span class="inline-box"><input type="text" id ="coreCompanyName" name ="coreCompanyName" class="pro-table-input pro-input-width" validationMessage="核心企业不能为空" required/></span>
           	 		<span class="inline-box"><div class="11Validform_checktip"></div></span>
            </td>
            <th><span class="redFalg"></span>银行账号</th>
            <td>
                <span class="inline-box"><input type="text" id ="coreAccountNo" name ="coreAccountNo" class="bankCard pro-table-input pro-input-width"></span>
                <span class="inline-box"><div class="32Validform_checktip"></div></span>
            </td>
            <th></th>
            <td></td>
        </tr>
        <tr>
            <th><span class="redFalg">*</span>组织机构代码</th>
             <td>
                <span class="inline-box"><input type="text" id ="coreOrganizationCode" name ="coreOrganizationCode" class="pro-table-input pro-input-width"  validationMessage="组织机构代码不能为空" required></span>
                <span class="inline-box"><div class="Validform_checktip"></div></span>
            </td>
            <th><span class="redFalg">*</span>所处行业</th>
            <td>
                <select class="out-input"  id ="coreIndustry" name ="coreIndustry" style="width:200px;"  data-role="dropdownlist">
                            <option value="TRAVEL">差旅</option>
                            <option value="LOGISTICS">物流</option>
                            <option value="EDUCATION">教育</option>
                            <option value="BANK">银行</option>
                             <option value="MEDIA">传媒</option>
                            <option value="COMMUNICATION">通信</option>
                            <option value="SYNTHESIS">综合</option>
                            <option value="DIGGING">采掘</option>
                            <option value="CHEMICAL">化工</option>
                            <option value="STEEL">钢铁</option>
                            <option value="CAR">汽车</option>
                            <option value="ELECTRON">电子</option>
                            <option value="IT">计算机</option>
                            <option value="REALTY">房地产</option>
                            <option value="COMMERCE">商业贸易</option>
                            <option value="LEISURE">休闲服务</option>
                            <option value="HUSBANDRY">农林牧渔</option>
                            <option value="NONFERROUS">有色金属</option>
                            <option value="BUILDING">建筑建材</option>
                            <option value="DECORATION">建筑装饰</option>
                            <option value="ELECTRIC">电器设备</option>
                            <option value="MECHANICAL">机械设备</option>
                            <option value="DEFENSE">国防军工</option>
                            <option value="APPLIANCE">家用电器</option>
                            <option value="FOOD">食品饮料</option>
                            <option value="TEXTILE">纺织服装</option>
                            <option value="LIGHT">轻工制造</option>
                            <option value="MEDICAL">医药生物</option>
                            <option value="UTILITY">公用事业</option>
                            <option value="TRAFFIC">交通事业</option>
                            <option value="NONBANKING">非银金融</option>
                </select>
                <span class="pro-error">错误提示</span>
            </td>
            <th></th>
            <td></td>
        </tr>
        <tr>
            <th><span class="redFalg">*</span>营业执照代码</th>
            <td>
                <span class="inline-box"><input type="text" id ="coreBusinessLicenceCode" name ="coreBusinessLicenceCode" class="pro-table-input pro-input-width" validationMessage="营业执照码不能为空" required/></span>
                <span class="inline-box"><div class="Validform_checktip"></div></span>
            </td>
            <th><span class="redFalg">*</span>经济类型</th>
            <td>
                <select class="out-input" id ="coreEconomicCategory" name ="coreEconomicCategory" style="width:200px;"  data-role="dropdownlist">
                    <option value="STATE">国有</option>
                    <option value="COLLECTIVE">集体</option>
                    <option value="PRIVATE">私营</option>
                    <option value="INDIVIDUAL">个体</option>
                    <option value="POOLING">联营</option>
                    <option value="STOCK">股份制</option>
                    <option value="FOREIGN">外商投资</option>
                    <option value="HMT">港澳台投资</option>
                    <option value="OTHER">其他经济类</option>
                </select>
                <span class="pro-error">错误提示</span>
            </td>
            <th></th>
            <td></td>
        </tr>
        <tr>
            <th><span class="redFalg">*</span>税务登记证号码</th>
            <td>
                <span class="inline-box"><input type="text" id ="coreTaxRegistrationCertificateCode" name="coreTaxRegistrationCertificateCode" class="pro-table-input pro-input-width" validationMessage="税务登记证号码不能为空" required /></span>
                <span class="inline-box"><div class="Validform_checktip"></div></span>
            </td>
            <th></th>
            <td></td>
            <th></th>
            <td></td>
        </tr>
        </tbody>
    </table>
    </div>
    <div class="pro-title">资金方信息</span><span class="pro-title-right">折叠</span></div>
    <div class="pro-box" >
        <div class="pro-box-title">
        
            <a class="status-normal status-chk fly-left newTr" href="javascript:void(0);" onclick="newCr();">新增</a>
            <a class="status-normal status-chk fly-left chTr" href="javascript:void(0);" onclick="chTr('ckb');">修改</a>
            <a class="status-normal status-chk fly-left" href="javascript:void(0);" onclick="delTr2();">删除</a>
        </div>
        <div class="pro-box-list" id="tab">
            <table class="m-table new-table-one td-table">
                <colgroup>
                    <col width="30">
                    <col width="30">
                    <col width="80">
                    <col width="80">
                    <col width="80">
                    <col width="80">
                    <col width="80">
                    <col width="80">
                </colgroup>
                <tbody>
                <tr class="top-list-one">
                    <th></th>
                    <td>序号</td>
                    <td>企业名称</td>
                    <td>组织机构代码</td>
                    <td>营业执照号码</td>
                    <td>税务登记证号码</td>
                    <td>银行账号</td>
                    <td>合作状态</td>
                </tr>
                <tr class="top-list-two">
                    <th><input type="checkbox" class="input-new" name='ckb'></th>
	                    <td>1</td>
	                    <td>
		                    <span name='companyName'>${userVo.customer.customerName!}</span>
		                    <input type='hidden' name='projectFactors[0].companyName' value="${userVo.customer.customerName!}">
	                    </td>
	                    
	                    <td>
		                    <span name='organizationCode'>${userVo.customer.organizationCode!}</span>
		                   <input type="hidden" name="projectFactors[0].organizationCode" value="${userVo.customer.organizationCode!}">
	                    </td>
	                    
	                    <td>
		                    <span name='businessLicenceCode'>${userVo.customer.businessLicenceCode!}</span>
		                    <input type='hidden' name='projectFactors[0].businessLicenceCode' value="${userVo.businessLicenceCode!}">
	                    </td>
	                    
	                    <td>
		                    <span name='taxRegistrationCertificateCode'>${userVo.customer.taxRegistrationCertificateCode!}</span>
		                    <input type='hidden' name='projectFactors[0].taxRegistrationCertificateCode' value="${userVo.taxRegistrationCertificateCode!}">
	                    </td>
	                    
	                    <td>
	                    	<span name="accountNo"></span>
	                    	<input type="hidden" name="projectFactors[0].accountNo" value="">
	                    </td>
                    
                    <td>
                    <span name="factorStatus">正常</span>
                    <input type="hidden" id="project-factor-status" name="projectFactors[0].status" value="NORMAL">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="pro-title"><span class="pro-title-left">基础交易信息</span><span class="pro-title-right">折叠</span></div>
    <div class="hide-box" >
        <table class="query-table td-table" style="padding-bottom:30px;">
            <colgroup>
                <col width="60">
                <col width="150">
                <col width="60">
                <col width="230">
            </colgroup>
            <tbody>
            <tr>
                <th><span class="redFalg">*</span>融资申请人</th>
                 <td>
                <span class="inline-box"><input type="text" id ="financeApplicant" name ="financeApplicant"  class="pub-int-one pub-int "  validationMessage="融资申请人不能为空" required></span>
                <span class="inline-box"><div class="Validform_checktip"></div></span>
            </td>
                <th></th>
                <td></td>
            </tr>
            <tr>
                <th><span class="redFalg">*</span>单笔贷款金额</th>
                <td>
                    <span class="inline-box"><input type="text" id ="singleLoanLimitLower" name ="singleLoanLimitLower" class="pub-int-two pub-int"><em>&ensp;-&ensp;</em></span>
                   <input type="text" id ="singleLoanLimitUpper" name ="singleLoanLimitUpper" class="pub-int-two pub-int" validationMessage ="单笔贷款金额不能为空"　required/><em>&ensp;元</em>
                	<span class="inline-box"><div class=""></div></span>
                </td>
                <th></th>
                <td></td>
            </tr>
            <tr>
                <th><span class="redFalg">*</span>利率区间</th>
                <td>
                    <input type="text" id ="interestRateLower" name ="interestRateLower" class="pub-int-two pub-int">
                    <em>&ensp;-&ensp;</em>
                    <input type="text" id ="interestRateUpper"  name ="interestRateUpper" class="pub-int-two pub-int">
                    <em>&ensp;%</em>
                    <span class="pro-error">错误提示</span>
                </td>
                <th><span class="redFalg">*</span>利率单位</th>
                <td>
                    <select class="" id ="interestRateUnit" name ="interestRateUnit" style="width:200px;"  data-role="dropdownlist">
                        <option value="YEAR">年</option>
                        <option value="MONTH">月</option> 
                        <option value="DAY">日</option>
                    </select>
                    <span class="pro-error">错误提示</span>
                </td>
            </tr>
            <tr>
                <th><span class="redFalg">*</span>单笔贷款期限</th>
                <td>
                    <input type="text" id ="loanPeriodLower" name ="loanPeriodLower" class="pub-int-two pub-int">
                    <em>&ensp;-&ensp;</em>
                    <input type="text" id ="loanPeriodUpper" name ="loanPeriodUpper" class="pub-int-two pub-int">
                    <span class="pro-error">错误提示</span>
                </td>
                <th><span class="redFalg">*</span>期限单位</th>
                <td>
                    <select class="" id ="loanPeriodUnit" name ="loanPeriodUnit" style="width:200px;"  data-role="dropdownlist">
                        <option value="YEAR">年</option>
                        <option value="MONTH">月</option> 
                        <option value="DAY">日</option>
                    </select>
                    <span class="pro-error">错误提示</span>
                </td>
            </tr>
            <tr>
                <th><span class="redFalg">*</span>还款方式</th>
                <td style="position:relative; text-indent: 15px;">
                    <div class="fang-box">
                        <label><input type="checkbox"   name ="repaymentMethods" value ="ORDER" class="input-new">订单款回款还款</label>
                        <label><input type="checkbox"   name ="repaymentMethods" value ="MATURITY" class="input-new">到期一次性还本及支付收益</label>
                        <label><input type="checkbox"   name ="repaymentMethods" value ="MONTHLY" class="input-new">按月支付收益到期还本</label>
                        <label><input type="checkbox"   name ="repaymentMethods" value ="EQUALALL" class="input-new">等额本息</label>
                        <label><input type="checkbox"   name ="repaymentMethods" value ="EQUALPRINCIPAL" class="input-new">等额本金</label>
                        <span class="pro-error">错误提示</span>
                    </div>
                </td>
                <th></th>
                <td></td>
            </tr>
            <tr>
                <th><span class="redFalg">*</span>是否使用节假日</th>
                <td>
                    <select class="" id ="useHolidayPolicy" name ="useHolidayPolicy" style="width:200px;"  data-role="dropdownlist">
                        <option value="false">否</option>
                        <option value="true">是</option> 
                    </select>
                    <span class="pro-error">错误提示</span>
                </td>
                <th></th>
                <td></td>
            </tr>
            <tr>
                <th><span class="redFalg">*</span>展期</th>
                <td>
	                <span class="inline-box"><input type="text" id ="extensionDays" name ="extensionDays" class="pub-int-two pub-int" pattern ="^(-|\+)?\d+$" validationMessage="展期不能为空，且输入必须是整数" required/><em>&ensp;天&ensp;</em></span>
                   <span class="inline-box"><div class="Validform_checktip"></div></span>
                </td>
                <th><span class="redFalg">*</span>提前还款手续费率</th>
                
            <td>
                <span class="inline-box"><input type="text" id ="earlyRepaymentChargeRatio" name ="earlyRepaymentChargeRatio" class="pub-int-two pub-int"/> <em>&ensp;%&ensp;</em></span>
                <span class="inline-box"><div class="Validform_checktip"></div></span>
            </td>
            </tr>
            <tr>
                <th><span class="redFalg">*</span>展期收息方式</th>
                <td>
                    <label class="left-style"><input type="radio"  class="input-radio" name="extensionRatioMethod" value ="AMOUNT">固定值</label>
                    <label><input type="radio" class="input-radio"  name="extensionRatioMethod" value ="RATIO">按比例上浮</label>
                    <span class="pro-error">错误提示</span>
                </td>
                <th><span class="redFalg">*</span>展期利率/展期上浮比例</th>
            <td>
                <span class="inline-box"><input type="text" name ="extensionRatio" id ="extensionRatio" class="pub-int-two pub-int" validationMessage ="展期上浮比率不能为空" required/> <em>&ensp;%&ensp;</em></span>
                <span class="inline-box"><div class="Validform_checktip"></div></span>
            </td>
            </tr>
            <tr>
                <th><span class="redFalg">*</span>逾期管理费罚息方式</th>
                <td>
                    <label class="left-style"><input type="radio"   name="penaltyRateMethod" value ="AMOUNT" class="input-radio" >固定值</label>
                    <label><input type="radio" class="input-radio"  name="penaltyRateMethod" value ="RATIO" >按比例上浮</label>
                    <span class="pro-error">错误提示</span>
                </td>
                <th><span class="redFalg">*</span>逾期利率/逾期上浮比例</th>
            <td>
                <span class="inline-box"><input type="text" id ="penaltyRate" name ="penaltyRate" class="pub-int-two pub-int" validationMessage="逾期上浮比例不能为空" required/> <em>&ensp;%&ensp;</em></span>
                <span class="inline-box"><div class="Validform_checktip"></div></span>
            </td>
            </tr>
            <tr>
                <th><span class="redFalg">*</span>扣款时点</th>
            <td>
                <span class="inline-box"><input type="text" id ="deductionTime" name ="deductionTime" class="pub-int-two pub-int" /></span>
                <span class="inline-box"><div class="Validform_checktip"></div></span>
            </td>
                <th></th>
                <td></td>
            </tr>
            <tr>
                <th><span class="redFalg">*</span>扣款规则</th>
                <td >
                    <label class="left-style"><input type="radio" class="input-radio" id ="PART" name="deductionRule" value ="PART">可部分扣款</label>
                    <label><input type="radio" class="input-radio" id ="ALL" name="deductionRule" value ="ALL">足额后扣款</label>
                    <span class="pro-error">错误提示</span>
                </td>
                <th></th>
                <td></td>
            </tr>
            <tr>
                <th>产品介绍</th>
                <td>
                    <textarea class="ta-box" id ="productIntroduction" name ="productIntroduction"></textarea><br>
                    <span class="pro-error" style="margin-left: 20px;">错误提示</span>
                </td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <th>产品类型</th>
                <td>
                    <textarea class="ta-box" id ="productTypeDesc" name ="productTypeDesc" validationmessage="产品类型不能为空"　required></textarea><br>
                    <span class="pro-error" style="margin-left: 20px;">错误提示</span>
                </td>
                <td></td>
                <td></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="pro-title"><span class="pro-title-left">合同模板</span></div>
    <div class="last-box">
        <table class="pro-table-style td-table" id="list-table">
            <colgroup>
                <col width="60">
                <col width="60">
                <col width="30">
                <col width="80">
                <col width="350">
            </colgroup>
            <tbody>
            <tr>
                <th>合同版本日期</th>
                <td>
                <span class="inline-box"><input type="text" value ="" name="version" class="version-class" style="width:120px;border:1px solid #fff !important;outline:none;" readonly="readonly"></span>
                <span class="inline-box"><div class="Validform_checktip"></div></span>
            </td>
                <th><span class="redFalg">*</span>名称</th>
                <td>
                <span class="inline-box"><input type="text" name="documentName" validationMessage="合同名称不能为空" required/></span>
                <span class="inline-box"><div class="Validform_checktip"></div></span>
            </td>
                <td class="file-top">
                	<label>
                   		 <input type="file" class="fileBox" value="" name="file"  id="additionInformationMedia"/>
                   		 <input  class ="doucumentid" type="hidden" name=mediaId>
                   		 <a class="form-search-link" href="javascript:void(0);">上传附件</a>
                    </label>
            		<a class="form-search-link mr-left close-btn" href="javascript:void(0);">查看</a>
            		<a class="form-search-link mr-left close-btn del-once" href="javascript:void(0);">删除</a>
                    <a class="form-search-link mr-left" href="javascript:void(0);" onclick="newTr('list-table');">新增</a>
                </td>
            </tr>
            </tbody>
        </table>
        <div style="text-align: center; margin-top:30px;">
            <a class="form-search-link" id ="submit"  href="javascript:void(0);" >提交</a>
        </div>
    </div>
</form>
</div>

<!--资金方信息修改弹窗-->
<div class="style-box" style="display: none;">
    <div class="out-ch"></div>
    <div class="out-min-ch">
        <div class="out-header">修改资金方信息</div>
        <div class="list-box">
            <form class="ch-form">
                <table class="out-table">
                    <colgroup>
                        <col width="140">
                        <col width="200">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>资金方名称</th>
                        <td>
                            <input type="text" class="out-input outInWid trCh1" name="ch-tr1">
                        </td>
                    </tr>
                    <tr>
                        <th>组织机构代码</th>
                        <td>
                            <input type="text" class="out-input outInWid trCh2" name="ch-tr2">
                        </td>
                    </tr>
                    <tr>
                        <th>营业执照号码</th>
                        <td>
                            <input type="text" class="out-input outInWid trCh3" name="ch-tr3">
                        </td>
                    </tr>
                    <tr>
                        <th>税务登记证号码</th>
                        <td>
                            <input type="text" class="out-input outInWid trCh4" name="ch-tr4">
                        </td>
                    </tr>
                    <tr>
                        <th>银行账号</th>
                        <td>
                            <input type="text" class="bankCard out-input outInWid trCh5" name="ch-tr5">
                        </td>
                    </tr>
                    <tr>
                        <th>合作状态</th>
                        <td>
                            <select class="out-input select-in trCh6" style="width:200px;" name="ch-tr6">
	                             <#list projectFactorStatusEnums as ps>
									<option value=${ps.name()}>${ps.desc()}</option>
								 </#list>
                            </select>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
            <div class="out-foot-btn">
                <a class="default-link confirm-link" href="javascript:void(0);" onclick="trChange();" style="float:left;margin-right: 0;">确认</a>
                <a class="default-link back-link" href="javascript:void(0);" onclick="closeCh();" style="float:right;margin-right: 0;">关闭</a>
            </div>

        </div>

    </div>
</div>

<!--新增资金方信息弹窗-->
<div class="new-style-box" style="display: none;">
    <div class="out-ch"></div>
    <div class="out-min-ch">
        <div class="out-header">新增资金方信息</div>
        <div class="list-box">
            <form class="out-new-form">
                <table class="out-table">
                    <colgroup>
                        <col width="140">
                        <col width="200">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>资金方名称</th>
                        <td>
                            <input type="text"   class="out-input outInWid" id ="factorCompanyName" name="factorCompanyName">
                        </td>
                    </tr>
                    <tr>
                        <th>组织机构代码</th>
                        <td>
                            <input type="text" class="out-input outInWid" name="numberOne">
                        </td>
                    </tr>
                    <tr>
                        <th>营业执照号码</th>
                        <td>
                            <input type="text" class="out-input outInWid" name="numberTwo">
                        </td>
                    </tr>
                    <tr>
                        <th>税务登记证号码</th>
                        <td>
                            <input type="text" class="out-input outInWid" name="numberThree">
                        </td>
                    </tr>
                    <tr>
                        <th>银行账号</th>
                        <td>
                            <input type="text" class="bankCard out-input outInWid" name="numberFour">
                        </td>
                    </tr>
                    <tr>
                        <th>合作状态</th>
                        <td>
                            <select class="out-input select-in" style="width:200px;" name="new-Status">
								<#list projectFactorStatusEnums as ps>		
								 	<option value=${ps.name()}>${ps.desc()}</option>		
								</#list>
                            </select>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
            <div class="out-foot-btn">
                <a class="default-link confirm-link" href="javascript:void(0);" onclick="addTr3();" style="float:left;margin-right: 0;">确认</a>
                <a class="default-link back-link" href="javascript:void(0);" onclick="closeCr();" style="float:right;margin-right: 0;">关闭</a>
            </div>
        </div>
    </div>
</div>
<!--消息提示框-->
<div class="news-box xp-out" style="display: none;">
    <div class="out-ch"></div>
    <div class="out-min-ch">
        <div class="out-header txt-title">提示</div>
        <div class="list-box">
            <i class="popup-img popup-img-one" style="display: none;"></i><p class="news txt-cot" style="display:inline-block;"></p>
            <div class="out-foot-btn-two">
                <a class="default-link confirm-link" href="javascript:void(0)" onclick="closeNews();">确定</a>
            </div>
        </div>
    </div>
</div>
<!--消息提示框-->
<div class="news-box xp-out-up" style="display: none;">
    <div class="out-ch"></div>
    <div class="out-min-ch">
        <div class="out-header txt-title-up"></div>
        <div class="list-box">
            <i class="popup-img popup-img-up" style="display: none;"></i><p class="news txt-cot-up" style="display:inline-block;"></p>
            <div class="out-foot-btn-two">
                <a class="default-link confirm-link" href="javascript:void(0)" onclick="closeNewsUp();">确定</a>
            </div>
        </div>
    </div>
</div>
<!--新增成功之后跳转消息框  -->
<div class="news-box xp-out-success" style="display: none;">
    <div class="out-ch"></div>
    <div class="out-min-ch">
        <div class="out-header txt-title-two"></div>
        <div class="list-box">
        <i class="popup-img popup-img-two" style="display: none;"></i><p class="news txt-cot-two" style="display:inline-block;"></p>
            <div class="out-foot-btn-two">
                <a class="default-link confirm-link" href="javascript:void(0)" onclick="closeNewsTwo();">确定</a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
</script>
</@hb.htmlBase>