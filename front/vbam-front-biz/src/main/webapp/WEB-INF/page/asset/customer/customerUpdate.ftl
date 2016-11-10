<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="客户信息修改" jsFiles=["page/asset/customer/formValidatorRules.js","common/formValidator.js","page/asset/customer/customerUpdate.js","page/asset/customer/jquery.nav.js","page/asset/customer/customerCommon.js","page/asset/customer/customer.js","common/dateutil.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
curTopMenu = "基础资产信息" curSubMenu = "客户信息" removeExtHeader = false removeExtFooter = true>
		<form class="" id="updateCustomer" name ="updateCustomer" method ="post" action ="">
		    <div class="left-nav">
		        <ul id="client-nav">
		            <li class="current"><a href="#nav-one">基本信息&ensp;▶</a></li>
		            <li><a href="#nav-two">工商信息&ensp;▶</a></li>
		            <li><a href="#nav-three">高级信息&ensp;▶</a></li>
		        </ul>
		    </div>
		    <input type="hidden" id ="id" name ="id" value="${customerDetailDTO.id!}" style="width:200px;">
		    <div style="width:100%;height:96px;" class="section" id="nav-one"></div>
		    <div class="client-bg">
		        <!--基本信息-->
		        <div class="pro-title" id=""><span class="pro-title-left">基本信息</span></div>
		        <div class="client-hide-bg">
		            <table class="client-tb">
		                <colgroup>
		                    <col width="200px">
		                    <col width="400px">
		                    <col width="200px">
		                    <col width="400px">
		                    <col width="">
		                </colgroup>
		                <tbody>
		                <tr>
		                    <th><span class="redFalg">*</span>客户名称</th>
		                    <td><input type="text" id ="customerName" name ="customerName" value="${customerDetailDTO.customerName!}" style="width:200px;"></td>
		                    <th><span class="redFalg">*</span>客户类型</th>
		                    <td>
		                        <select class="" id ="customerType" name ="customerType" value="${customerDetailDTO.customerType.desc()!}" style="width:200px;"  data-role="dropdownlist">
			                        <#list customerTypeEnums as customerTypeEnum>	
			                        
			                        	
									 	<option value=${customerTypeEnum.name()!} <#if customerTypeEnum.name() == (customerDetailDTO.customerType.name())!>selected ="selected"</#if>>${customerTypeEnum.desc()!}</option>		
									</#list>
		                        </select>
		                    </td>
		                    <td></td>
		                </tr>
		                <tr>
		                    <th><span class="redFalg">*</span>联系人</th>
		                    <td><input type="text" id ="contactName" name ="contactName" value="${customerDetailDTO.contactName!}" style="width:200px;" validationMessage="联系人不能为空" required></td>
		                    <th><span class="redFalg">*</span>手机号码</th>
		                    <td><input type="text" id ="mobileNo" name ="mobileNo" value="${customerDetailDTO.mobileNo!}" style="width:200px;"pattern="^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$" style="width:200px;" validationMessage="手机号码格式错误" required></td>
		                    <td></td>
		                </tr>
		                <tr>
		                    <th><span class="redFalg">*</span>电子邮箱</th>
		                    <td><input type="text" id ="mail" name ="mail" value="${customerDetailDTO.mail!}" style="width:200px;" pattern ="^[0-9|a-z|A-Z|-|_]+([-+.][0-9|a-z|A-Z|-|_]+)*@[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*\.[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*$" validationMessage="电子邮箱格式错误" required></td>
		                    <th><span class="redFalg">*</span>公司地址</th>
		                    <td><input type="text" id ="companyAddress" name ="companyAddress" value="${customerDetailDTO.companyAddress!}" style="width:200px;" validationMessage="公司地址不能为空" required></td>
		                    <td></td>
		                </tr>
		                <tr>
		                    <th><span class="redFalg">*</span>经济类型</th>
		                    <td>
		                        <select class="" id ="economicCategory" name ="economicCategory" style="width:200px;"  data-role="dropdownlist">
		                            <#list economicCategoryEnums as economicCategoryEnum>
		                            	<option value=${economicCategoryEnum.name()!} <#if economicCategoryEnum.name()! ==(customerDetailDTO.economicCategory.name())!>selected ="selected"</#if>>${economicCategoryEnum.desc()!}</option>
		                            </#list>
		                        </select>
		                    </td>
		                    <th><span class="redFalg">*</span>所属行业</th>
		                    <td>
		                        <select class="" id ="industry" name ="industry" style="width:200px;"  data-role="dropdownlist">
		                            <#list industryTypeEnums as industryTypeEnum>		
									 	<option value=${industryTypeEnum.name()!} <#if industryTypeEnum.name()! == (customerDetailDTO.industry.name())!>selected ="selected"</#if>>${industryTypeEnum.desc()!}</option>		
									</#list>
		                        </select>
		                    </td>
		                    <td></td>
		                </tr>
		                <tr>
		                    <th><span class="redFalg">*</span>所属区域</th>
		                    <td>
		                        <select class="" id ="city" name ="city" style="width:200px;"  data-role="dropdownlist">
		                        			<#list customerCityTypeEnums as customerCityTypeEnum>
		                        				<option value=${customerCityTypeEnum.name()!} <#if customerCityTypeEnum.name()! == (customerDetailDTO.city.name())!>selected ="selected"</#if>>${customerCityTypeEnum.desc()!}</option>
		                        			</#list>
		                        </select>
		                    </td>
		                    <th></th>
		                    <td></td>
		                    <td></td>
		                </tr>
		                </tbody>
		            </table>
		        </div>
		        <!--工商信息-->
		        <div class="pro-title" id="nav-two"><span class="pro-title-left">工商信息</span><span class="pro-title-right">折叠</span></div>
		        <div class="client-hide-bg">
		            <table class="client-tb">
		                <colgroup>
		                    <col width="200px">
		                    <col width="400px">
		                    <col width="200px">
		                    <col width="420px">
		                    <col width="">
		                </colgroup>
		                <tbody>
		                <tr>
		                    <th><span class="redFalg">*</span>法定代表人</th>
		                    <td><input type="text" id ="legalRepresentative" name ="legalRepresentative" value="${customerDetailDTO.legalRepresentative!}" style="width:200px;" validationMessage="法定代表人不能为空" required></td>
		                    <th><span class="redFalg">*</span>注册资本</th>
		                    <td><input class ="moneyNum" type="text" id ="registeredCapital" name ="registeredCapital" value="${formatMoney(customerDetailDTO.registeredCapital!'')}" style="width:200px;" onkeyup="clearNoNum(this)" validationMessage="注册资本不能为空" required><span>元</span></td>
		                    <td></td>
		                </tr>
		                <tr>
		                    <th><span class="redFalg">*</span>营业执照号码</th>
		                    <td><input type="text" id ="businessLicenceCode" name ="businessLicenceCode" value="${customerDetailDTO.businessLicenceCode!}" style="width:200px;" validationMessage="营业执照号码不能为空" required></td>
		                    <th><span class="redFalg">*</span>营业执照有效日期</th>
		                    <td><input type="text" id ="businessLicenceCodeExpiryDate" name ="businessLicenceCodeExpiryDate" value="${customerDetailDTO.businessLicenceCodeExpiryDate?string("yyyy-MM-dd")!}" class="time-one data-style hasIcon" style="width:200px;" validationMessage="营业执照有效时间不能为空" required></td>
		                    <td></td>
		                </tr>
		                <tr>
		                    <th><span class="redFalg">*</span>税务登记证号码</th>
		                    <td><input type="text" id ="taxRegistrationCertificateCode" name ="taxRegistrationCertificateCode" value="${customerDetailDTO.taxRegistrationCertificateCode!}" style="width:200px;" validationMessage="税务登记证号码不能为空" required></td>
		                    <th><span class="redFalg">*</span>税务登记证有效日期</th>
		                    <td><input type="text" id ="taxRegistrationCertificateCodeExpiryDate" name ="taxRegistrationCertificateCodeExpiryDate" value="${customerDetailDTO.taxRegistrationCertificateCodeExpiryDate?string("yyyy-MM-dd")!}" class="time-one data-style hasIcon" style="width:200px;" validationMessage="税务登记证有效时间不能为空" required></td>
		                    <td></td>
		                </tr>
		                <tr>
		                    <th><span class="redFalg">*</span>组织机构代码</th>
		                    <td><input type="text" id ="organizationCode" name ="organizationCode" value="${customerDetailDTO.organizationCode}" style="width:200px;" validationMessage="组织机构代码不能为空" required></td>
		                    <th><span class="redFalg">*</span>组织机构代码证有效日期</th>
		                    <td><input type="text" id ="organizationCodeExpiryDate" name ="organizationCodeExpiryDate" value="${customerDetailDTO.organizationCodeExpiryDate?string("yyyy-MM-dd")!}" class="time-one data-style hasIcon" style="width:200px;" validationMessage="组织机构代码有效时间不能为空" required></td>
		                    <td></td>
		                </tr>
		                </tbody>
		            </table>
		            <a href="javascript:void(0);" class="client-shareholder" style="display:none;">click</a>
		            <a href="javascript:void(0);" class="client-executive" style="display:none;">click</a>
		            <a href="javascript:void(0);" class="client-purchase" style="display:none;">click</a>
		            <a href="javascript:void(0);" class="client-sell" style="display:none;">click</a>
		            <a href="javascript:void(0);" class="client-financing" style="display:none;">click</a>
		            <a href="javascript:void(0);" class="client-message" style="display:none;">click</a>
		            <a href="javascript:void(0);" class="suc-message-btn" style="display:none;">click</a>
		        </div>
		        <!--高级信息-->
		        <div class="pro-title" id="nav-three"><span class="pro-title-left">高级信息</span><span class="pro-title-right">折叠</span></div>
		        <div class="client-hide-bg">
		            <div class="client-two-title">股东情况</div>
		            <div class="client-advanced">
		                <div class="pro-box-title">
		                    <a class="status-normal status-chk client-add-btn shareholder-add" href="javascript:void(0);">新增</a>
		                    <a class="status-normal status-chk shareholder-ch-btn" href="javascript:void(0);">修改</a>
		                    <a class="status-normal status-chk client-del-btn" href="javascript:void(0);">删除</a>
		                </div>
		                <table class="m-table client-pro-table" style="margin-top:10px;" id="shareholder">
		                    <colgroup>
		                        <col width="8%">
		                        <col width="8%">
		                        <col width="14%">
		                        <col width="14%">
		                        <col width="14%">
		                        <col width="14%">
		                        <col width="14%">
		                        <col width="">
		                    </colgroup>
		                    <tbody>
		                    <tr class="top-list-one">
		                        <th>选择</th>
		                        <td>序号</td>
		                        <td>股东类型</td>
		                        <td>股东名称</td>
		                        <td>出资额（万元）</td>
		                        <td>出资比例（%）</td>
		                        <td>出资方式</td>
		                        <td>是否到位</td>
		                    </tr>
		                    <#if customerDetailDTO.customerStks?exists >
			                    <#list customerDetailDTO.customerStks as customerStk>
				                    <tr class="client-add-tr">
			                        <th><input type="checkbox" class="input-new" name="shareholder"></th>
			                        <th><span name ="stkId">${customerStk_index+1}</span><input type="hidden" name="stk-mm" value="${customerStk.id!}"></th>
			                        <td><span name ="stkType">${customerStk.stkType.desc()!}</span><input type="hidden" name="customerStks[${customerStk_index+1}].stkType" value="${customerStk.stkType.name()!}"></td>
			                        <td><span name ="stkName">${customerStk.stkName!}</span><input type="hidden" name="customerStks[${customerStk_index+1}].stkName" value="${customerStk.stkName!}"></td>
			                        <td><span name ="stkPayamt">${formatMoney(customerStk.stkPayamt!'')}</span><input type="hidden" name="customerStks[${customerStk_index+1}].stkPayamt" value="${customerStk.stkPayamt!}"></td>
			                        <td><span name ="stkPcnt">${customerStk.stkPcnt!}</span><input type="hidden" name="customerStks[${customerStk_index+1}].stkPcnt" value="${customerStk.stkPcnt!}"></td>
			                        <td><span name ="stkPayamtType">${customerStk.stkPayamtType.desc()!}</span><input type="hidden" name="customerStks[${customerStk_index+1}].stkPayamtType" value="${customerStk.stkPayamtType.name()!}"></td>
			                        <td><span name ="stkStatus">${customerStk.stkStatus!}</span><input type="hidden" name="customerStks[${customerStk_index+1}].stkStatus" value="${customerStk.stkStatus!}"></td>
			                        </tr>
			                    </#list>
                 		   </#if>
		                    </tbody>
		                </table>
		            </div>
		            <div class="client-two-title">高管人员情况</div>
		            <div class="client-advanced">
		                <div class="pro-box-title">
		                    <a class="status-normal status-chk client-add-btn executive-add" href="javascript:void(0);">新增</a>
		                    <a class="status-normal status-chk executive-ch-btn" href="javascript:void(0);">修改</a>
		                    <a class="status-normal status-chk client-del-btn" href="javascript:void(0);">删除</a>
		                </div>
		                <table class="m-table client-pro-table" style="margin-top:10px;" id="executive">
		                    <colgroup>
		                        <col width="8%">
		                        <col width="8%">
		                        <col width="8%">
		                        <col width="8%">
		                        <col width="12%">
		                        <col width="10%">
		                        <col width="10%">
		                        <col width="12%">
		                        <col width="">
		                    </colgroup>
		                    <tbody>
		                    <tr class="top-list-one">
		                        <th>选择</th>
		                        <td>序号</td>
		                        <td>姓名</td>
		                        <td>性别</td>
		                        <td>身份证号</td>
		                        <td>职务</td>
		                        <td>学历</td>
		                        <td>专业</td>
		                        <td>主要经历</td>
		                    </tr>
		                    <#if customerDetailDTO.customerMags?exists >
			                    <#list customerDetailDTO.customerMags as customerMag>
				                    <tr class="client-add-tr">
						                <th><input type="checkbox" class="input-new" name="executive"></th>
						                <th><span name ="magId">${customerMag_index+1}</span><input type="hidden" name="mag-id-02" value="${customerMag.id!}"></th>
						                <td><span name ="magName">${customerMag.magName!}</span><input type="hidden" name="customerMags[${customerMag_index+1}].magName" value="${customerMag.magName!}"></td>
						                <td><span name ="magSex">${customerMag.magSex!}</span><input type="hidden" name="customerMags[${customerMag_index+1}].magSex" value="${customerMag.magSex!}"></td>
						                <td><span name ="magIdentityCardNo">${customerMag.magIdentityCardNo!}</span><input type="hidden" name="customerMags[${customerMag_index+1}].magIdentityCardNo" value="${customerMag.magIdentityCardNo!}"></td>
						                <td><span name ="magDutyType">${customerMag.magDutyType!}</span><input type="hidden" name="customerMags[${customerMag_index+1}].magDutyType" value="${customerMag.magDutyType!}"></td>
						                <td><span name ="magEducation">${customerMag.magEducation.desc()!}</span><input type="hidden" name="customerMags[${customerMag_index+1}].magEducation" value="${customerMag.magEducation!}"></td>
						                <td><span name ="magProfession">${customerMag.magProfession!}</span><input type="hidden" name="customerMags[${customerMag_index+1}].magProfession" value="${customerMag.magProfession!}"></td>
						                <td><div name ="experience" class="over-text">${customerMag.experience!}</div><input type="hidden" name="customerMags[${customerMag_index+1}].experience" value="${customerMag.experience!}"></td>
				                        
				                    </tr>
			                    </#list>
			                 </#if>
		                    </tbody>
		                </table>
		            </div>
		            <div class="client-two-title">采购情况</div>
		            <div class="client-advanced">
		                <div class="pro-box-title">
		                    <a class="status-normal status-chk client-add-btn purchase-add" href="javascript:void(0);">新增</a>
		                    <a class="status-normal status-chk purchase-ch-btn" href="javascript:void(0);">修改</a>
		                    <a class="status-normal status-chk client-del-btn" href="javascript:void(0);">删除</a>
		                </div>
		                <table class="m-table client-pro-table" style="margin-top:10px;" id="purchase">
		                    <colgroup>
		                        <col width="8%">
		                        <col width="8%">
		                        <col width="19%">
		                        <col width="16%">
		                        <col width="16%">
		                        <col width="16%">
		                        <col width="">
		                    </colgroup>
		                    <tbody>
		                    <tr class="top-list-one">
		                        <th>选择</th>
		                        <td>序号</td>
		                        <td>供应商名称</td>
		                        <td>上年采购量（万元）</td>
		                        <td>占总采购量（%）</td>
		                        <td>结算方式</td>
		                        <td>应付账款余额（万元）</td>
		                    </tr>
		                     <#if customerDetailDTO.customerPurchases?exists >
			                    <#list customerDetailDTO.customerPurchases as customerPurchase>
				                    <tr class="client-add-tr">
				                        <th><input type="checkbox" class="input-new" name="purchase"></th>
						                <th><span name ="purchaseId">${customerPurchase_index+1}</span><input type="hidden" name="purchase-id-02" value="${customerPurchase.id!}"></th>
						                <td><span name ="supplierName">${customerPurchase.supplierName!}</span><input type="hidden" name="customerPurchases[${customerPurchase_index+1}].supplierName" value="${customerPurchase.supplierName!}"></td>
						                <td><span name ="purchaseLastYear">${formatMoney(customerPurchase.purchaseLastYear!'')}</span><input type="hidden" name="customerPurchases[${customerPurchase_index+1}].purchaseLastYear" value="${customerPurchase.purchaseLastYear!}"></td>
						                <td><span name ="percent">${customerPurchase.percent!}</span><input type="hidden" name="customerPurchases[${customerPurchase_index+1}].percent" value="${customerPurchase.percent!}"></td>
						                <td><span name ="settleType">${customerPurchase.settleType.desc()!}</span><input type="hidden" name="customerPurchases[${customerPurchase_index+1}].settleType" value="${customerPurchase.settleType!}"></td>
						                <td><span name ="accountPayableBalance">${formatMoney(customerPurchase.accountPayableBalance!'')}</span><input type="hidden" name="customerPurchases[${customerPurchase_index+1}].accountPayableBalance" value="${customerPurchase.accountPayableBalance!}"></td>
				                        
				                        
				                    </tr>
			                    </#list>
	               			 </#if>
		                    </tbody>
		                </table>
		            </div>
		            <div class="client-two-title">销售情况</div>
		            <div class="client-advanced">
		                <div class="pro-box-title">
		                    <a class="status-normal status-chk client-add-btn sell-add" href="javascript:void(0);">新增</a>
		                    <a class="status-normal status-chk sell-ch-btn" href="javascript:void(0);">修改</a>
		                    <a class="status-normal status-chk client-del-btn" href="javascript:void(0);">删除</a>
		                </div>
		                <table class="m-table client-pro-table" style="margin-top:10px;" id="sell">
		                    <colgroup>
		                        <col width="8%">
		                        <col width="8%">
		                        <col width="19%">
		                        <col width="16%">
		                        <col width="16%">
		                        <col width="16%">
		                        <col width="">
		                    </colgroup>
		                    <tbody>
		                    <tr class="top-list-one">
		                        <th>选择</th>
		                        <td>序号</td>
		                        <td>客户名称</td>
		                        <td>上年销售额（万元）</td>
		                        <td>占总销售额（%）</td>
		                        <td>合作年限</td>
		                        <td>应收账款余额（万元）</td>
		                         <#if customerDetailDTO.customerSales?exists >
				                    <#list customerDetailDTO.customerSales as customerSale>
						                    <tr class="client-add-tr">
					                         	<th><input type="checkbox" class="input-new" name="sell"></th>
								                <th><span name ="saleId">${customerSale_index+1}</span><input type ="hidden" name ="sale-id-05" value ="${customerSale.id!}"></th>
								                <td><span name ="saleCustomerName">${customerSale.saleCustomerName!}</span><input type="hidden" name="customerSales[${customerSale_index+1}].saleCustomerName" value="${customerSale.saleCustomerName!}"></td>
								                <td><span name ="saleLastYear">${formatMoney(customerSale.saleLastYear!'')}</span><input type="hidden" name="customerSales[${customerSale_index+1}].saleLastYear" value="${customerSale.saleLastYear!}"></td>
								                <td><span name ="percent">${customerSale.percent!}</span><input type="hidden" name="customerSales[${customerSale_index+1}].percent" value="${customerSale.percent!}"></td>
								                <td><span name ="cooperationPeriod">${customerSale.cooperationPeriod!}</span><input type="hidden" name="customerSales[${customerSale_index+1}].cooperationPeriod" value="${customerSale.cooperationPeriod!}"></td>
								                <td><span name ="accountReceivableBalance">${formatMoney(customerSale.accountReceivableBalance!'')}</span><input type="hidden" name="customerSales[${customerSale_index+1}].accountReceivableBalance" value="${customerSale.accountReceivableBalance!}"></td>
						                        
						                    </tr>
						                </#list>
			          				 </#if>
		                    </tr>
		                    </tbody>
		                </table>
		            </div>
		            <div class="client-two-title">融资情况</div>
		            <div class="client-advanced">
		                <div class="pro-box-title">
		                    <a class="status-normal status-chk client-add-btn financing-add-btn" href="javascript:void(0);">新增</a>
		                    <a class="status-normal status-chk financing-ch-btn" href="javascript:void(0);">修改</a>
		                    <a class="status-normal status-chk client-del-btn" href="javascript:void(0);">删除</a>
		                </div>
		                <table class="m-table client-pro-table" style="margin-top:10px;" id="financing">
		                    <colgroup>
		                        <col width="8%">
		                        <col width="8%">
		                        <col width="12%">
		                        <col width="12%">
		                        <col width="12%">
		                        <col width="12%">
		                        <col width="12%">
		                        <col width="">
		
		                    </colgroup>
		                    <tbody>
		                    <tr class="top-list-one">
		                        <th>选择</th>
		                        <td>序号</td>
		                        <td>融资机构名称</td>
		                        <td>余额（万元）</td>
		                        <td>起始日</td>
		                        <td>到期日</td>
		                        <td>担保方式</td>
		                        <td>备注</td>
		                    </tr>
		                    <#if customerDetailDTO.customerMass?exists >
		                    	<#list customerDetailDTO.customerMass as customerMas>   
				                    <tr class="client-add-tr">
				                        <th><input type="checkbox" class="input-new" name="financing"></th>
						                <th><span name ="masId">${customerMas_index+1}</span><input type ="hidden" name ="mas-id02" value ="${customerMas.id!}"></th>
						                <td><span name ="financialInstitutionName">${customerMas.financialInstitutionName!}</span><input type="hidden" name="customerMass[${customerMas_index+1}].financialInstitutionName" value="${customerMas.financialInstitutionName!}"></td>
						                <td><span name ="amount">${formatMoney(customerMas.amount!'')}</span><input type="hidden" name="customerMass[${customerMas_index+1}].amount" value="${customerMas.amount!}"></td>
						                <td><span name ="startDate">${customerMas.startDate!}</span><input type="hidden" name="customerMass[${customerMas_index+1}].startDate" value="${customerMas.startDate!}"></td>
						                <td><span name ="endDate">${customerMas.endDate!}</span><input type="hidden" name="customerMass[${customerMas_index+1}].endDate" value="${customerMas.endDate!}"></td>
						                <td><span name ="guaranteeType">${customerMas.guaranteeType!}</span><input type="hidden" name="customerMass[${customerMas_index+1}].guaranteeType" value="${customerMas.guaranteeType!}"></td>
						                <td><div name ="remark" class="over-text">${customerMas.remark!}</div><input type="hidden" name="customerMass[${customerMas_index+1}].remark" value="${customerMas.remark!}"></td>
				                        
				                    </tr>
				              </#list>
		        		   </#if>  
		                    </tbody>
		                </table>
		            </div>
		        </div>
		
		        <div class="client-foot">
		            <input class="form-search-link" type="button" value="提交" id ="submit"  style="border:0;">
		            <a class="form-search-link" href="${basepath!}/asset/enterpriseInfo/customerListPage;">返回</a>
		        </div>
		    </div>
		</form>
		<!--股东弹窗-->
		<script id="template-resetPwd-one" type="text/x-kendo-template">
		    <div class="dlg-wrapper">
		    <form class="out-new-form" id ="stk-pp">
		        <div class="dlg-del-row">
		            
		                <table class="client-tb">
		                    <colgroup>
		                        <col width="38%">
		                        <col width="">
		                    </colgroup>
		                    <tbody>
		                    <tr>
		                        <th>股东类型</th>
		                        <td>
		                            <select class="s-type" id ="stkType" style="width:200px;" name="stkType01" data-role="dropdownlist">
		                            	<#list customerStkTypeEnums as customerStkTypeEnum>		
									 		<option value=${customerStkTypeEnum.name()!}>${customerStkTypeEnum.desc()!}</option>		
										</#list>
		                            </select>
		                        </td>
		                    </tr>
		                    <tr>
		                        <th>股东名称</th>
		                        <td><input class="s-name" type="text"  value="" style="width:200px;" name="stkName" validationMessage="股东名称不能为空" required></td>
		                    </tr>
		                    <tr>
		                        <th>出资额</th>
		                        <td style="position:relative;">
		                            <input class="s-num moneyNum" type="text" value="" style="width:200px; padding-right: 40px;" name="stkPayamt" validationMessage="出资额不能为空" required>
		                            <div class="client-unit">万元</div>
		                        </td>
		                    </tr>
		                    <tr>
		                        <th>出资比例</th>
		                        <td style="position:relative;">
		                            <input class="s-scale precent-p aaa" id ="precent-id" name="stkPcnt" type="text" value="" style="width:200px; padding-right: 40px;" validationMessage="出资比例不能为空" required>
		                            <div class="client-unit">%</div>
		                        </td>
		                    </tr>
		                    <tr>
		                        <th>出资方式</th>
		                        <td>
		                            <select class="s-mode" style="width:200px;" name="stkPayamtType" data-role="dropdownlist" id="stkPayamtType">
		                            	<#list customerStkPayamtTypeEnums as customerStkPayamtTypeEnum>		
									 		<option value=${customerStkPayamtTypeEnum.name()!}>${customerStkPayamtTypeEnum.desc()!}</option>		
										</#list>
		                            </select>
		                        </td>
		                    </tr>
		                    <tr>
		                    
		                        <th>是否到位</th>
		                        <td>
		                            <label class="client-ch-w s-if"><input type='radio' name='client-choose' value='yes' checked="checked" class='client-radio'>是</label>
		                            <label class="client-ch-w s-if"><input type='radio' name='client-choose' value='no' class='client-radio'>否</label>
		                        </td>
		                    </tr>
		                    
		                    </tbody>
		                </table>
		                
		      
		        </div>
		        <div class="dlg-del-row">
		         	<input type="button" class="default-link confirm-link suc-shareholder" id="customer-stk" value="确定" style="border:0;">
		            <input type="button" class="default-link back-link" value="取消" style="border:0;">
		        </div>
		        </form>
		        <input type="hidden" class="shareholder-index-id" value="">
		    </div>
		</script>
		<!--高管人员弹窗-->
		<script id="template-resetPwd-two" type="text/x-kendo-template">
		    <div class="dlg-wrapper">
		        <div class="dlg-del-row">
		            <form class="out-new-form" id ="magid">
		                <table class="client-tb">
		                    <colgroup>
		                        <col width="38%">
		                        <col width="">
		                    </colgroup>
		                    <tbody>
		                    <tr>
		                        <th>姓名</th>
		                        <td><input class="e-name" name ="magName01" type="text" value="" style="width:200px;" validationMessage="姓名不能为空" required></td>
		                    </tr>
		                    <tr>
		                        <th>性别</th>
		                        <td>
		                            <label class="client-ch-w e-sex"><input type='radio' name='client-choose' value='men' checked="checked" class='client-radio'>男</label>
		                            <label class="client-ch-w e-sex"><input type='radio' name='client-choose' value='women' class='client-radio'>女</label>
		                        </td>
		                    </tr>
		                    <tr>
		                        <th>身份证号</th>
		                        <td><input class="e-card sf-card" id ="magIdentityCardNo" name ="magIdentityCardNo" type="text" value="" style="width:200px; pattern="^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$" validationMessage="身份证号格式不合法" required></td>
		                    </tr>
		                    <tr>
		                        <th>职务</th>
		                        <td><input class="e-work" name ="magDutyType" type="text" value="" style="width:200px;" validationMessage="职务不能为空" required></td>
		                    </tr>
		                    <tr>
		                        <th>学历</th>
		                        <td>
		                            <select class="e-edu" name ="magEducation" style="width:200px;" id ="magEducation"  data-role="dropdownlist">
		                                <#list customerManEducationEnums as customerManEducationEnum>		
									 		<option value=${customerManEducationEnum.name()!}>${customerManEducationEnum.desc()!}</option>		
										</#list>
		                            </select>
		                        </td>
		                    </tr>
		                    <tr>
		                        <th>专业</th>
		                        <td><input class="e-pro" name ="magProfession" type="text" value="" style="width:200px;" validationMessage="专业不能为空" required></td>
		                    </tr>
		                    <tr>
		                        <th>主要经历</th>
		                        <td><textarea class="e-exp" name ="experience" placeholder="300字以内" style="width:200px;height:100px;"></textarea></td>
		                    </tr>
		                    </tbody>
		                </table>
		                
		            
		        </div>
		        <div class="dlg-del-row">
		            <input type="button" class="default-link confirm-link" id="magid-btn" value="确定" style="border:0;">
		            <input type="button" class="default-link back-link" value="取消" style="border:0;">
		        </div>
		        </form>
		        <input type="hidden" class="executive-index-id" value="">
		    </div>
		</script>
		<!--采购弹窗-->
		<script id="template-resetPwd-three" type="text/x-kendo-template">
		    <div class="dlg-wrapper">
		        <div class="dlg-del-row">
		            <form class="out-new-form" id="purchase-form">
		                <table class="client-tb">
		                    <colgroup>
		                        <col width="38%">
		                        <col width="">
		                    </colgroup>
		                    <tbody>
		                    <tr>
		                        <th>供应商名称</th>
		                        <td><input class="p-name" type="text" name ="supplierName01" value="" style="width:200px;" validationMessage="供应商名称不能为空" required></td>
		                    </tr>
		                    <tr>
		                        <th>上半年采购量</th>
		                        <td style="position:relative;">
		                            <input class="p-num moneyNum" type="text" name ="purchaseLastYear" value="" style="width:200px; padding-right: 40px;" validationMessage="上半年采购量不能为空" required>
		                            <div class="client-unit">万元</div>
		                        </td>
		                    </tr>
		                    <tr>
		                        <th>占总采购量</th>
		                        <td style="position:relative;">
		                            <input class="p-scale precent-p aaa" name ="percent" type="text" value="" style="width:200px; padding-right: 40px;" validationMessage="占总采购量不能为空" required>
		                            <div class="client-unit">%</div>
		                        </td>
		                    </tr>
		                    <tr>
		                        <th>结算方式</th>
		                        <td>
		                            <select class="p-mode" style="width:200px;" name ="settleType" id ="settleType" data-role="dropdownlist">
		                               <#list customerSettleTypeEnums as customerSettleTypeEnum>
		                               		<option value=${customerSettleTypeEnum.name()!}>${customerSettleTypeEnum.desc()!}</option>
		                               	</#list>
		                            </select>
		                        </td>
		                    </tr>
		                    <tr>
		                        <th>应付账款余额</th>
		                        <td style="position:relative;">
		                            <input class="p-bal moneyNum" type="text" name ="accountPayableBalance" value="" style="width:200px; padding-right: 40px;" validationMessage="应付账款余额不能为空" required>
		                            <div class="client-unit">万元</div>
		                        </td>
		                    </tr>
		                    </tbody>
		                </table>
		               
		            
		        </div>
		        <div class="dlg-del-row">
		            <input type="button" class="default-link confirm-link" id="purchase-btn" value="确定" style="border:0;">
		            <input type="button" class="default-link back-link" value="取消" style="border:0;">
		        </div>
		        </form>
		         <input type="hidden" class="purchase-index-id" value="">
		    </div>
		</script>
		<!--销售弹窗-->
		<script id="template-resetPwd-four" type="text/x-kendo-template">
		    <div class="dlg-wrapper">
		        <div class="dlg-del-row">
		            <form class="out-new-form" id="sell-form">
		                <table class="client-tb">
		                    <colgroup>
		                        <col width="38%">
		                        <col width="">
		                    </colgroup>
		                    <tbody>
		                    <tr>
		                        <th>客户名称</th>
		                        <td><input class="l-name" type="text" name ="saleCustomerName01" value="" style="width:200px;" validationMessage="客户名称不能为空" required></td>
		                    </tr>
		                    <tr>
		                        <th>上半年销售额</th>
		                        <td style="position:relative;">
		                            <input class="l-num moneyNum" type="text" value="" name ="saleLastYear" style="width:200px; padding-right: 40px;" validationMessage="上半年销售额不能为空" required>
		                            <div class="client-unit">万元</div>
		                        </td>
		                    </tr>
		                    <tr>
		                        <th>占总销售额</th>
		                        <td style="position:relative;">
		                            <input class="l-scale precent-p aaa" type="text" name ="percent" value="" style="width:200px; padding-right: 40px;" validationMessage="占总销售额不能为空" required>
		                            <div class="client-unit">%</div>
		                        </td>
		                    </tr>
		                    <tr>
		                        <th>合作年限</th>
		                        <td><input class="l-year" type="text" name ="cooperationPeriod" value="" style="width:200px;" onkeyup="clearNoNum(this)" validationMessage="合作年限不能为空" required></td>
		                    </tr>
		                    <tr>
		                        <th>应收账款余额</th>
		                        <td style="position:relative;">
		                            <input class="l-bal moneyNum" type="text" name ="accountReceivableBalance" value="" style="width:200px; padding-right: 40px;" validationMessage="应收账款余额不能为空" required>
		                            <div class="client-unit">万元</div>
		                        </td>
		                    </tr>
		                    </tbody>
		                </table>
		                
		            
		        </div>
		        <div class="dlg-del-row">
		            <input type="button" class="default-link confirm-link" id="sell-btn" value="确定" style="border:0;">
		            <input type="button" class="default-link back-link" value="取消" style="border:0;">
		        </div>
		        </form>
		        <input type="hidden" class="sell-index-id" value="">
		    </div>
		</script>
		<!--融资弹窗-->
		
		<script id="template-resetPwd-five" type="text/x-kendo-template">
		    <div class="dlg-wrapper">
		        <div class="dlg-del-row">
		            <form class="out-new-form" id="out-new-form">
		                <table class="client-tb">
		                    <colgroup>
		                        <col width="38%">
		                        <col width="">
		                    </colgroup>
		                    <tbody>
		                    <tr>
		                        <th>金融机构名称</th>
		                        <td><input class="f-name" type="text" name="financialInstitutionName01" value="" style="width:200px;" validationMessage="金融机构名称不能为空" required></td>
		                    </tr>
		                    <tr>
		                        <th>金额</th>
		                        <td style="position:relative;">
		                            <input class="f-bal moneyNum" type="text" name="amount" value="" style="width:200px; padding-right: 40px;" validationMessage="金额不能为空" required>
		                            <div class="client-unit">万元</div>
		                        </td>
		                    </tr>
		                    <tr>
		                        <th>起始日</th>
		                        <td><input type="text" name="startDate" class="datepicker startDate hasIcon f-star" style="width:200px;" validationMessage="起始日不能为空" required></td>
		                    </tr>
		                    <tr>
		                        <th>到期日</th>
		                        <td><input type="text" name="endDate" class="datepicker endDate hasIcon f-end" style="width:200px;" validationMessage="到期日不能为空" required></td>
		                    </tr>
		                    <tr>
		                        <th>担保方式</th>
		                        <td><input class="f-mode" name="guaranteeType" type="text" value="" style="width:200px;" validationMessage="担保方式不能为空" required></td>
		                    </tr>
		                    <tr>
		                        <th>备注</th>
		                        <td><input class="f-other" name="remark" type="text" value="" style="width:200px;" validationMessage="备注不能为空" required></td>
		                    </tr>
		                    </tbody>
		                </table>
		            
		        </div>
		        <div class="dlg-del-row">
		            <input type="button" class="default-link confirm-link" id="five-btn" value="确定" style="border:0;">
		            <input type="button" class="default-link back-link" value="取消" style="border:0;">
		        </div>
		        </form>
		                <input type="hidden" class="financing-index-id" value="">
		    </div>
		</script>
		
		
		
		
		<script id="template-resetPwd-new" type="text/x-kendo-template">
		    <div class="dlg-wrapper">
		        <form class="out-new-form">
		        <div class="dlg-del-row">
		        
		
		
		        </div>
		        <div class="dlg-del-row">
		            <input type="button" class="default-link confirm-link" id="ok-btn" value="确定" style="border:0;">
		            <input type="button" class="default-link back-link" value="取消" style="border:0;">
		        </div>
		        </form>
		        <input type="hidden" class="financing-index-id" value="">
		    </div>
		</script>
		
		
		
		
		
		<!--消息弹窗-->
		<script id="template-resetPwd" type="text/x-kendo-template">
		    <div class="dlg-wrapper">
		        <div class="dlg-del-row">
		            <span class="dlg-notice notice-icon01"></span><span class="notice-content client-x-txt"></span>
		        </div>
		        <div class="dlg-del-row">
		            <a class="default-link back-link" href="javascript:void(0);">返回</a>
		        </div>
		    </div>
		</script>
		<!--消息弹窗-->
		<script id="template-resetPwd-suc" type="text/x-kendo-template">
		    <div class="dlg-wrapper">
		        <div class="dlg-del-row">
		            <span class="dlg-notice notice-icon02"></span><span class="notice-content client-x-txt"></span>
		        </div>
		        <div class="dlg-del-row">
		            <a class="default-link confirm-link" href="${basepath}/asset/enterpriseInfo/customerListPage">确定</a>
		        </div>
		    </div>
		</script>
		
<script>
$(function(){
	$(".time-one").datepicker({
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
	//$("body").on("change",".precent-p",function(){
		//var val = $(this).val();
       // var reg = /^(0|[1-9][0-9]?|100)$/;
        //if(reg.test(val) == false){
        	//$(this).next().show();
        	//$(this).val("");
           // return  false;
       	// }else{
        //	$(this).next().hide();
       // }
	//})
	
	
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
<script>
	$("body").on("change",".sf-card",function(){
		var val = $(this).val();
        var reg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
        if(reg.test(val) == false){
        	$(this).next().show();
        	$(this).val("");
            return  false;
        }else{
        	$(this).next().hide();
        }
	})
</script>
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
	<footer>Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
<div style="height:70px"></div>		
</@hb.htmlBase>