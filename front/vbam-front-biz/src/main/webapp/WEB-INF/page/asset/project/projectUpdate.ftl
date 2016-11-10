<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="项目管理" jsFiles=["common/formValidator.js","page/asset/project/projectUpdate.js","page/asset/project/project.js","page/asset/project/formValidatorRules.js","common/dateutil.js","js/common/ajaxfileupload.js"] cssFiles=["css/project.css","css/monitor.css","css/valifrom.css"] localCssFiles=[] 
	curTopMenu = "项目管理" curSubMenu = "所有列表" removeExtHeader = false removeExtFooter = false>
<div class="main-container" style="background:none;">
<div class="pro-bg" >
	<form id="updateProject" name ="updateProject" method ="post" action ="">
	    <div class="pro-title"><span class="pro-title-left">项目要素</span></div>
	    <input id ="projectId" name ="projectId" type="hidden" value="${projectDTO.id!}">
	    
	    <#list projectDTO.projectFactors  as projectFactors>
	     	<#if projectDTO.projectFactors?exists >
	    <input id ="factorId" name ="factorId" type="hidden" value="${projectFactors.id!}">
	    </#if>
	    </#list>
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
	             <span class="inline-box"><input type="text" id ="name" name ="name" value=${projectDTO.name!}  class="pro-table-input pro-input-width all-project-class" validationMessage="项目名称不能为空" required/></span>
	                <span class="inline-box"><div class=""></div></span>
	            </td>
	            <th><span class="redFalg">*</span>业务类型</th>
	            <td>
	                <select class="out-input"  id= "style" name ="type"  value="${projectDTO.type.name()!}" class ="" style="width:200px;"   data-role="dropdownlist">
	                    <option value="platform" <#if projectDTO.type == "platform">selected ="selected"</#if>>平台</option>
	                    
	                   <#-- <option value="factor" <#if projectDTO.type == "factor">selected ="selected"</#if>>保理</option> 
	                    <option value="small_loan" <#if projectDTO.type == "small_loan">selected ="selected"</#if>>小贷</option>
	                    <option value="rental" <#if projectDTO.type == "rental">selected ="selected"</#if>>租赁</option> -->
	                </select><span class="pro-error">错误提示</span>
	            </td>
	            <th><span class="redFalg">*</span>项目状态</th>
	            <td>
	                <select class="out-input pro-status" id = "status" name ="status" class ="" value =${projectDTO.status.name()!} style="width:200px;"  data-role="dropdownlist">
	                   <option value="CREATE" <#if projectDTO.status.name() == "CREATE" >selected ="selected"</#if>>立项</option>
	                   <option value="GOING" <#if  projectDTO.status.name() == "GOING">selected ="selected"</#if>>进行中</option> 
	                   <option value="PAUSE" <#if  projectDTO.status.name() == "PAUSE">selected ="selected"</#if>>暂停</option>
	                   <option value="CLOSE" <#if  projectDTO.status.name() == "CLOSE">selected ="selected"</#if>>结束</option>
	                </select><span class="pro-error">错误提示</span>
	            </td>
	        </tr>
	        </tbody>
	    </table>
	    <div class="pro-title"><span class="pro-title-left">核心企业信息</span><span class="pro-title-right">折叠</span></div>
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
	            	<span class="inline-box"><input type="text" id ="coreCompanyName" name ="coreCompanyName"value="${projectDTO.coreCompanyName!}" class="pro-table-input pro-input-width all-project-class" validationMessage="核心企业不能为空" required/></span>
	           	 		<span class="inline-box"><div class="11Validform_checktip"></div></span>
	            </td>
	            <th><span class="redFalg"></span>银行账号</th>
	            <td>
	                <span class="inline-box"><input type="text" id ="coreAccountNo" name ="coreAccountNo" value="${projectDTO.coreAccountNo!}" class="bankCard pro-table-input pro-input-width all-project-class" ></span>
	                <span class="inline-box"><div class="Validform_checktip"></div></span>
	            </td>
	            <th></th>
	            <td></td>
	        </tr>
	        <tr>
	            <th><span class="redFalg">*</span>组织机构代码</th>
	             <td>
	                <span class="inline-box"><input type="text" id ="coreOrganizationCode" name ="coreOrganizationCode" value="${projectDTO.coreOrganizationCode!}" class="pro-table-input pro-input-width all-project-class"  validationMessage="组织机构代码不能为空" required></span>
	                <span class="inline-box"><div class="Validform_checktip"></div></span>
	            </td>
	            <th><span class="redFalg">*</span>所处行业</th>
	            <td>
	                <select class="out-input"  id ="coreIndustry" name ="coreIndustry" value ="${projectDTO.coreIndustry.name()!}"　 class ="all-project-class" style="width:200px;"  data-role="dropdownlist">
	                            <option value="TRAVEL"    <#if projectDTO.coreIndustry.name() == "TRAVEL">selected ="selected"</#if>>差旅</option>
	                            <option value="LOGISTICS" <#if projectDTO.coreIndustry.name() == "LOGISTICS">selected ="selected"</#if>>物流</option>
	                            <option value="EDUCATION" <#if projectDTO.coreIndustry.name() == "EDUCATION">selected ="selected"</#if>>教育</option>
	                            <option value="BANK"      <#if projectDTO.coreIndustry.name() == "BANK">selected ="selected"</#if>>银行</option>
	                            <option value="MEDIA"     <#if projectDTO.coreIndustry.name() == "MEDIA">selected ="selected"</#if>>传媒</option>
	                            <option value="COMMUNICATION" <#if projectDTO.coreIndustry.name() == "COMMUNICATION">selected ="selected"</#if>>通信</option>
	                            <option value="SYNTHESIS"  <#if projectDTO.coreIndustry.name() == "SYNTHESIS">selected ="selected"</#if>>综合</option>
	                            <option value="DIGGING"   <#if projectDTO.coreIndustry.name() == "DIGGING">selected ="selected"</#if>>采掘</option>
	                            <option value="CHEMICAL"  <#if projectDTO.coreIndustry.name() == "CHEMICAL">selected ="selected"</#if>>化工</option>
	                            <option value="STEEL"    <#if projectDTO.coreIndustry.name() == "STEEL">selected ="selected"</#if>>钢铁</option>
	                            <option value="CAR"   　　　<#if projectDTO.coreIndustry.name() == "CAR">selected ="selected"</#if>>汽车</option>
	                            <option value="ELECTRON"  <#if projectDTO.coreIndustry.name() == "ELECTRON">selected ="selected"</#if>>电子</option>
	                            <option value="IT"　　　　　 　<#if projectDTO.coreIndustry.name() == "IT">selected ="selected"</#if>>计算机</option>
	                            <option value="REALTY" 　　<#if projectDTO.coreIndustry.name() == "REALTY">selected ="selected"</#if>>房地产</option>
	                            <option value="COMMERCE" <#if projectDTO.coreIndustry.name() == "COMMERCE">selected ="selected"</#if>>商业贸易</option>
	                            <option value="LEISURE" 　<#if projectDTO.coreIndustry.name() == "LEISURE">selected ="selected"</#if>>休闲服务</option>
	                            <option value="HUSBANDRY" <#if projectDTO.coreIndustry.name() == "HUSBANDRY">selected ="selected"</#if>>农林牧渔</option>
	                            <option value="NONFERROUS" <#if projectDTO.coreIndustry.name() == "NONFERROUS">selected ="selected"</#if>>有色金属</option>
	                            <option value="BUILDING" <#if projectDTO.coreIndustry.name() == "BUILDING">selected ="selected"</#if>>建筑建材</option>
	                            <option value="DECORATION" <#if projectDTO.coreIndustry.name() == "DECORATION">selected ="selected"</#if>>建筑装饰</option>
	                            <option value="ELECTRIC" <#if projectDTO.coreIndustry.name() == "ELECTRIC">selected ="selected"</#if>>电器设备</option>
	                            <option value="MECHANICAL" <#if projectDTO.coreIndustry.name() == "MECHANICAL">selected ="selected"</#if>>机械设备</option>
	                            <option value="DEFENSE" <#if projectDTO.coreIndustry.name() == "DEFENSE">selected ="selected"</#if>>国防军工</option>
	                            <option value="APPLIANCE" <#if projectDTO.coreIndustry.name() == "APPLIANCE">selected ="selected"</#if>>家用电器</option>
	                            <option value="FOOD" <#if projectDTO.coreIndustry.name() == "FOOD">selected ="selected"</#if>>食品饮料</option>
	                            <option value="TEXTILE" <#if projectDTO.coreIndustry.name() == "TEXTILE">selected ="selected"</#if>>纺织服装</option>
	                            <option value="LIGHT" <#if projectDTO.coreIndustry.name() == "LIGHT">selected ="selected"</#if>>轻工制造</option>
	                            <option value="MEDICAL" <#if projectDTO.coreIndustry.name() == "MEDICAL">selected ="selected"</#if>>医药生物</option>
	                            <option value="UTILITY" <#if projectDTO.coreIndustry.name() == "UTILITY">selected ="selected"</#if>>公用事业</option>
	                            <option value="TRAFFIC" <#if projectDTO.coreIndustry.name() == "TRAFFIC">selected ="selected"</#if>>交通事业</option>
	                            <option value="NONBANKING" <#if projectDTO.coreIndustry.name() == "NONBANKING">selected ="selected"</#if>>非银金融</option>
	                </select>
	                <span class="pro-error">错误提示</span>
	            </td>
	            <th></th>
	            <td></td>
	        </tr>
	        <tr>
	            <th><span class="redFalg">*</span>营业执照代码</th>
	            <td>
	                <span class="inline-box"><input type="text" id ="coreBusinessLicenceCode" name ="coreBusinessLicenceCode" value="${projectDTO.coreBusinessLicenceCode!}" class="pro-table-input pro-input-width all-project-class" validationMessage="营业执照码不能为空" required/></span>
	                <span class="inline-box"><div class="Validform_checktip"></div></span>
	            </td>
	            <th><span class="redFalg">*</span>经济类型</th>
	            <td>
	                <select class="out-input " id ="coreEconomicCategory" name ="coreEconomicCategory"  value =${projectDTO.coreEconomicCategory.name()!} style="width:200px;"  data-role="dropdownlist">
	                    <option value="INDIVIDUAL" <#if projectDTO.coreEconomicCategory.name() == "INDIVIDUAL">selected ="selected"</#if>>个体</option>
	                    <option value="POOLING"    <#if projectDTO.coreEconomicCategory.name() == "POOLING">selected ="selected"</#if>>联营</option>
	                    <option value="STOCK"      <#if projectDTO.coreEconomicCategory.name() == "STOCK">selected ="selected"</#if>>股份制</option>
	                    <option value="FOREIGN"    <#if projectDTO.coreEconomicCategory.name() == "FOREIGN">selected ="selected"</#if>>外商投资</option>
	                    <option value="HMT"　       <#if projectDTO.coreEconomicCategory.name() == "HMT">selected ="selected"</#if>>港澳台投资</option>
	                    <option value="OTHER"　     <#if projectDTO.coreEconomicCategory.name() == "OTHER">selected ="selected"</#if>>其他经济类</option>
	                </select>
	                <span class="pro-error">错误提示</span>
	            </td>
	            <th></th>
	            <td></td>
	        </tr>
	        <tr>
	            <th><span class="redFalg">*</span>税务登记证号码</th>
	            <td>
	                <span class="inline-box"><input type="text" id ="coreTaxRegistrationCertificateCode" name="coreTaxRegistrationCertificateCode" value="${projectDTO.coreTaxRegistrationCertificateCode!}" class="pro-table-input pro-input-width all-project-class" validationMessage="税务登记证号码不能为空" required /></span>
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
	    <div class="pro-title"><span class="pro-title-left">资金方信息</span><span class="pro-title-right">折叠</span></div>
	    <div class="pro-box" >
	        <div class="pro-box-title">
	        
	             <a class="status-normal status-chk fly-left newTr project-hide-class" id ="factor-select" href="javascript:void(0);" onclick="newCr();">新增</a>
                <a class="status-normal status-chk fly-left chTr project-hide-class" id ="factor-update" href="javascript:void(0);" onclick="chTr('ckb');">修改</a>
                <a class="status-normal status-chk fly-left project-hide-class" id ="factor-remove" href="javascript:void(0);" onclick="delTr2();">删除</a>
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
                </tbody>
            </table>
	       <!-- <div class="monitor-grid" id="monitorList-grid"> 
	        </div>-->
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
	                <span class="inline-box"><input type="text" id ="financeApplicant" name ="financeApplicant" value =${projectDTO.financeApplicant!} class="pub-int-one pub-int all-project-class"  validationMessage="融资申请人不能为空" required></span>
	                <span class="inline-box"><div class="Validform_checktip"></div></span>
	            </td>
	                <th></th>
	                <td></td>
	            </tr>
	            <tr>
	                <th><span class="redFalg">*</span>单笔贷款金额</th>
	                <td>
	                    <span class="inline-box"><input type="text" id ="singleLoanLimitLower" name ="singleLoanLimitLower" value ="${projectDTO.singleLoanLimitLowerAdd!}" class="pub-int-two pub-int all-project-class"><em>&ensp;-&ensp;</em></span>
	                   <input type="text" id ="singleLoanLimitUpper" name ="singleLoanLimitUpper" value =${projectDTO.singleLoanLimitUpperAdd!} class="pub-int-two pub-int all-project-class" validationMessage ="单笔贷款金额不能为空"　required/><em>元</em>
	                	<span class="inline-box"><div class=""></div></span>
	                </td>
	                <th></th>
	                <td></td>
	            </tr>
	            <tr>
	                <th><span class="redFalg">*</span>利率区间</th>
	                <td>
	                    <input type="text" id ="interestRateLower" name ="interestRateLower" value ="${projectDTO.interestRateLowerAdd!}" class="pub-int-two pub-int all-project-class">
	                    <em>&ensp;-&ensp;</em>
	                    <input type="text" id ="interestRateUpper"  name ="interestRateUpper" value ="${projectDTO.interestRateUpperAdd!}" class="pub-int-two pub-int all-project-class">
	                    <em>&ensp;%</em>
	                    <span class="pro-error">错误提示</span>
	                </td>
	                <th><span class="redFalg">*</span>利率单位</th>
	                <td>
	                
	                <select class="" id = "interestRateUnit" name ="interestRateUnit" class ="" value =${projectDTO.interestRateUnit.name()!} style="width:200px;"  data-role="dropdownlist">
	                   <option value="YEAR" <#if projectDTO.interestRateUnit.name() == "YEAR" >selected ="selected"</#if>>年</option>
	                   <option value="MONTH" <#if  projectDTO.interestRateUnit.name() == "MONTH">selected ="selected"</#if>>月</option>
	                   <option value="DAY" <#if  projectDTO.interestRateUnit.name() == "DAY">selected ="selected"</#if>>日</option>
	                </select><span class="pro-error">错误提示</span>
	                </td>
	            </tr>
	            <tr>
	                <th><span class="redFalg">*</span>单笔贷款期限</th>
	                <td>
	                    <input type="text" id ="loanPeriodLower" name ="loanPeriodLower" value =${projectDTO.loanPeriodLower!} class="pub-int-two pub-int all-project-class">
	                    <em>&ensp;-&ensp;</em>
	                    <input type="text" id ="loanPeriodUpper" name ="loanPeriodUpper" value = ${projectDTO.loanPeriodUpper!} class="pub-int-two pub-int all-project-class">
	                    <span class="pro-error">错误提示</span>
	                </td>
	                <th><span class="redFalg">*</span>期限单位</th>
	                <td>
	                	 <select class="" id = "loanPeriodUnit" name ="loanPeriodUnit" class ="" value =${projectDTO.loanPeriodUnit.name()!} style="width:200px;"  data-role="dropdownlist">
			                   <option value="YEAR" <#if projectDTO.loanPeriodUnit.name() == "YEAR" >selected ="selected"</#if>>年</option>
			                   <option value="MONTH" <#if  projectDTO.loanPeriodUnit.name() == "MONTH">selected ="selected"</#if>>月</option>
			                   <option value="DAY" <#if  projectDTO.loanPeriodUnit.name() == "DAY">selected ="selected"</#if>>日</option>
	                	</select><span class="pro-error">错误提示</span>
	                </td>
	            </tr>
	            <tr>
	                <th><span class="redFalg">*</span>还款方式</th>
	                <td style="position:relative; text-indent: 15px;">
	                    <div class="fang-box">
	                    <#list projectDTO.repaymentMethodTypeList as dict> 
	                       	<#if projectDTO.repaymentMethodList?exists >
	                       		<#assign flag = true/>
		                    	<#list projectDTO.repaymentMethodList as repaymentMethods >
			                    	<#if repaymentMethods.name() == dict.name()>
					                    <label><input type="checkbox"  name ="repaymentMethods" value =${repaymentMethods!} class="input-new" checked ="checked">${dict.desc()!}
					                    </label>
					                    <#assign flag = false/>
					                    <#break>
					                </#if>
			                    </#list>
			                    <#if flag>
			                    	<label><input type="checkbox"  name ="repaymentMethods" value =${dict.name()!} class="input-new">${dict.desc()!}</label>
			                    	
			                    </#if>
		                     <#else>
		                      	<label><input type="checkbox"  name ="repaymentMethods" value =${dict.name()!} class="input-new">${dict.desc()!}</label>
		                      	
		                    </#if>  
		                </#list>
		                <span class="pro-error">错误提示</span>
	                    </div>
	                </td>
	                <th></th>
	                <td></td>
	            </tr>
	            <tr>
					<th>是否使用节假日</th>
					<td>
						<#if projectDTO.useHolidayPolicy>是<#else>否</#if>
					</td>
					<th></th>
					<td></td>
				</tr>
	            <tr>
	                <th><span class="redFalg">*</span>展期</th>
	                <td>
		                <span class="inline-box"><input type="text" id ="extensionDays" name ="extensionDays" value =${projectDTO.extensionDays!} class="pub-int-two pub-int all-project-class extensionDays"　pattern="^(-|\+)?\d+$" validationMessage="展期不能为空，且输入必须是整数" required/><em>&ensp;天&ensp;</em></span>
	                   <span class="inline-box"><div class=""></div></span>
	                </td>
	                <th><span class="redFalg">*</span>提前还款手续费率</th>
	                
	            <td>
	                <span class="inline-box"><input type="text" id ="earlyRepaymentChargeRatio" name ="earlyRepaymentChargeRatio" value =${projectDTO.earlyRepaymentChargeRatioAdd!} class="pub-int-two pub-int all-project-class"  validationMessage="提前还款手续费率不能为空" required/> <em>&ensp;&ensp;%</em></span>
	                <span class="inline-box"><div class="Validform_checktip"></div></span>
	            </td>
	            </tr>
	            <tr>
	                <th><span class="redFalg">*</span>展期收息方式</th>
	             	 <td>
	                    <label class="left-style"><input type="radio"  class="input-radio" name="extensionRatioMethod"  value = "AMOUNT" <#if projectDTO.extensionRatioMethod.desc() == "定额">checked</#if>/>固定值</label> 
	                    <label><input type="radio" class="input-radio"  name="extensionRatioMethod" value ="RATIO" <#if projectDTO.extensionRatioMethod.desc() == "比例">checked</#if>/>按比例上浮</label>
	                    <span class="pro-error">错误提示</span>
	                </td> 
	                <th><span class="redFalg">*</span>展期利率/展期上浮比例</th>
	            <td>
	                <span class="inline-box"><input type="text" name ="extensionRatio" id ="extensionRatio" value =${projectDTO.extensionRatioAdd!} class="pub-int-two pub-int all-project-class" validationMessage ="展期上浮比率不能为空" required/> <em>&ensp;&ensp;%</em></span>
	                <span class="inline-box"><div class="Validform_checktip"></div></span>
	            </td>
	            </tr>
	            <tr>
	                <th><span class="redFalg">*</span>逾期管理费罚息方式</th>
	                <td>
	                    <label class="left-style"><input type="radio"   name="penaltyRateMethod"  class="input-radio" value ="AMOUNT" <#if projectDTO.penaltyRateMethod.desc() == "定额">checked</#if>/>固定值</label>
	                    <label><input type="radio" class="input-radio"  name="penaltyRateMethod" value ="RATIO" <#if projectDTO.penaltyRateMethod.desc() == "比例">checked</#if>/>按比例上浮</label>
	                    <span class="pro-error">错误提示</span>
	                </td>
	                <th><span class="redFalg">*</span>逾期利率/逾期上浮比例</th>
	            <td>
	                <span class="inline-box"><input type="text" id ="penaltyRate" name ="penaltyRate" value =${projectDTO.penaltyRateAdd!} class="pub-int-two pub-int all-project-class" validationMessage="逾期上浮比例不能为空" required/> <em>&ensp;&ensp;%</em></span>
	                <span class="inline-box"><div class="Validform_checktip"></div></span>
	            </td>
	            </tr>
	            <tr>
	                <th><span class="redFalg">*</span>扣款时点</th>
	            <td>
	                <span class="inline-box"><input type="text" id ="deductionTime" name ="deductionTime" value =${projectDTO.deductionTime!} class="pub-int-two pub-int all-project-class"   /></span>
	                <span class="inline-box"><div class="Validform_checktip"></div></span>
	            </td>
	                <th></th>
	                <td></td>
	            </tr>
	            <tr>
	                <th><span class="redFalg">*</span>扣款规则</th>
	                <td >
	                    <label class="left-style"><input type="radio" class="input-radio" id ="PART" name="deductionRule" value = "PART" <#if projectDTO.deductionRule.desc() == "可部分扣款">checked</#if>/>可部分扣款</label>
	                    <label><input type="radio" class="input-radio" id ="ALL" name="deductionRule" value = "ALL" <#if projectDTO.deductionRule.desc() == "足额后扣款">checked</#if>/>足额后扣款</label>
	                    <span class="pro-error">错误提示</span>
	                </td>
	                <th></th>
	                <td></td>
	            </tr>
	            <tr>
	                <th>产品介绍</th>
	                <td>
	                    <textarea class="ta-box" id ="productIntroduction" class ="all-project-class" name ="productIntroduction">${projectDTO.productIntroduction!}</textarea><br>
	                    <span class="pro-error" style="margin-left: 20px;">错误提示</span>
	                </td>
	                <td></td>
	                <td></td>
	            </tr>
	            <tr>
	                <th>产品类型</th>
	                <td>
	                    <textarea class="ta-box" id ="productTypeDesc"  class ="all-project-class" name ="productTypeDesc"  validationmessage="产品类型不能为空"　required>${projectDTO.productTypeDesc!}</textarea><br>
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
	            <tr class ="project-hide-class">
	                <th>合同版本日期</th>
	                <td>
	                    <input type="text" name ="factorVersionName" class=""   value="2016-1-1" style="width:120px;border:1px solid #fff !important;outline:none;"></input>
	                    <span class="pro-error">错误提示</span>
	                </td>
	                <th>名称</th>
	                <td>
	                    <input type="text"  name ="documentName1" class=""  value="标准合同模板" style="border:1px solid #fff !important;outline:none;"></input>
	                    <span class="pro-error">错误提示</span>
	                </td>
	                 
	                <td>
	                    <a class="form-search-link close-btn" href="javascript:void(0);">上传附件</a>
	                    <a class="form-search-link close-btn" href="javascript:void(0);">查看</a>
	                    <a class="form-search-link close-btn mr-left" href="javascript:void(0);" >删除</a>
	                  <#if authorizeKey("ASSET_LIST_ADD")>
	                    <a class="form-search-link mr-left" href="javascript:void(0);" onclick="newTr('list-table');">新增</a>
	                  </#if>
	                </td>
	            </tr> 
	            
	            <#list projectDTO.projectDocument as projectDocument >
	            <tr>
	                <th>合同版本日期</th>
	               
	            <td>
	                <span class="inline-box"><input type="text" name="version" value="${projectDocument.version!}"  class="all-project-class" style="width:120px;border:1px solid #fff !important;outline:none;" readonly="readonly"></span>
	                <span class="inline-box"><div class="Validform_checktip"></div></span>
	            </td>
	          
	                <th><span class="redFalg">*</span>名称</th>
	             <td>
	                <span class="inline-box"><input type="text" name="documentName" value="${projectDocument.name!}" class ="all-project-class" validationMessage="合同名称不能为空" required/></span>
	                <span class="inline-box"><div class="Validform_checktip"></div></span>
	          	 </td>
	       
	                <td class="file-top ">
	                	<label　class ="project-hide-class">
	               		<input type="file" class="fileBox project-hide-class" value="" name="file"  id="additionInformationMedia"/>
	               		<input  class ="doucumentid" type="hidden" name="mediaId" value ="${projectDocument.mediaId!}"/>
	               		<a class="form-search-link project-hide-class" href="javascript:void(0);">上传附件</a>
	                    </label>
	            		<a class="form-search-link mr-left  project-hide-class" href="${mediaserver}/imageservice?mediaImageId=${projectDocument.mediaId!}&mediaType=download">查看</a>
	            		 <#if authorizeKey("ASSET_LIST_DELETE")>
	            			<a class="form-search-link mr-left project-hide-class removeNew" href="javascript:void(0);">删除</a>
	            		</#if>
	            		<a class="form-search-link mr-left document-download"  style="visibility:hidden" href="${mediaserver}/imageservice?mediaImageId=${projectDocument.mediaId!}&mediaType=download">下载</a>
	                </td>
	            </tr>
	           </#list>
	            </tbody>
	        </table>
	        <div style="text-align: center; margin-top:30px;">
	            <a class="form-search-link" id ="submit"  href="javascript:void(0);" >提交</a>
	        </div>
	</form>
</div>
	
	<!--<div class="monitor-grid k-grid k-widget" id="manualChk-grid"
			data-role="grid">
		</div> -->
	
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
                        <th>账户信息</th>
                        <td>
                            <input type="text" class="bankCard out-input outInWid trCh5" name="ch-tr5">
                        </td>
                    </tr>
                    <tr>
                        <th>合作状态</th>
                        <td>
                            <select class="out-input select-in trCh6" style="width:200px;" name="ch-tr6">
                               <option value="NORMAL">正常</option>
                                <option value="PAUSE">暂停</option>
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
                            <input type="text" class="out-input outInWid" name="newName">
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
                        <th>账户信息</th>
                        <td>
                            <input type="text" class="bankCard out-input outInWid" name="numberFour">
                        </td>
                    </tr>
                    <tr>
                        <th>合作状态</th>
                        <td>
                            <select class="out-input select-in" style="width:200px;" name="new-status">
                                <option value="NORMAL">正常</option>
                                <option value="PAUSE">暂停</option>
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
        <div class="out-header txt-title"></div>
        <div class="list-box">
            <i class="popup-img popup-img-one" style="display: none;"></i><p class="news txt-cot" style="display:inline-block;"></p>
            <div class="out-foot-btn-two">
                <a class="default-link confirm-link" href="javascript:void(0);" onclick="closeNews();">确定</a>
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
</@hb.htmlBase>
