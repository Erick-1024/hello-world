<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="客户详情" jsFiles=["page/asset/customer/customerDetail.js","page/asset/customer/jquery.nav.js","page/asset/customer/customer.js","common/dateutil.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
curTopMenu = "基础资产信息" curSubMenu = "客户信息" removeExtHeader = false removeExtFooter = true>
<form class="">
    <div class="left-nav">
        <ul id="client-nav">
            <li class="current"><a href="#nav-one">基本信息&ensp;▶</a></li>
            <li><a href="#nav-two">工商信息&ensp;▶</a></li>
            <li><a href="#nav-three">高级信息&ensp;▶</a></li>
        </ul>
    </div>
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
                    <th>客户名称</th>
                    <td>${customerDetailDTO.customerName!}</td>
                    <th>客户类型</th>
                    <td>${customerDetailDTO.customerType.desc()!}</td>
                    <td></td>
                </tr>
                <tr>
                    <th>联系人</th>
                    <td>${customerDetailDTO.contactName!}</td>
                    <th>手机号码</th>
                    <td>${customerDetailDTO.mobileNo!}</td>
                    <td></td>
                </tr>
                <tr>
                    <th>电子邮箱</th>
                    <td>${customerDetailDTO.mail!}</td>
                    <th>公司地址</th>
                    <td>${customerDetailDTO.companyAddress!}</td>
                    <td></td>
                </tr>
                <tr>
                    <th>经济类型</th>
                    <td>${customerDetailDTO.economicCategory.desc()!}</td>
                    <th>所属行业</th>
                    <td>${customerDetailDTO.industry.desc()!}</td>
                    <td></td>
                </tr>
                <tr>
                    <th>所属区域</th>
                    <td>${customerDetailDTO.city.desc()!}</td>
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
                    <col width="400px">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>法定代表人</th>
                    <td>${customerDetailDTO.legalRepresentative!}</td>
                    <th>注册资本</th>
                    <td>${formatMoney(customerDetailDTO.registeredCapital!'')}元</td>
                    <td></td>
                </tr>
                <tr>
                    <th>营业执照号码</th>
                    <td>${customerDetailDTO.businessLicenceCode!}</td>
                    <th>营业执照有效日期</th>
                  <td>${customerDetailDTO.businessLicenceCodeExpiryDate?string("yyyy-MM-dd")!}</td>
                    <td></td>
                </tr>
                <tr>
                    <th>税务登记证号码</th>
                    <td>${customerDetailDTO.taxRegistrationCertificateCode!}</td>
                    <th>税务登记证有效日期</th>
                    <td>${customerDetailDTO.taxRegistrationCertificateCodeExpiryDate?string("yyyy-MM-dd")!}</td> 
                    <td></td>
                </tr>
                <tr>
                    <th>组织机构代码</th>
                    <td>${customerDetailDTO.organizationCode}</td>
                    <th>组织机构代码证有效日期</th>
                    <td>${customerDetailDTO.organizationCodeExpiryDate?string("yyyy-MM-dd")!}</td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
        <!--高级信息 shareholder executive purchase sell financing-->
        <div class="pro-title" id="nav-three"><span class="pro-title-left">高级信息</span><span class="pro-title-right">折叠</span></div>
        <div class="client-hide-bg">
            <div class="client-two-title">股东情况</div>
            <table class="m-table client-pro-table" style="margin-top:10px;" id="financing">
                     <colgroup>
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
		                        <td>${customerStk_index+1}</td>
		                        <td>${customerStk.stkType.desc()!}</td>
		                        <td>${customerStk.stkName!}</td>
		                        <td>${formatMoney(customerStk.stkPayamt!'')}</td>
		                        <td>${customerStk.stkPcnt!}</td>
		                        <td>${customerStk.stkPayamtType.desc()!}</td>
		                        <td>${customerStk.stkStatus!}</td>
		                    </tr>
	                    </#list>
                    </#if>
                    </tbody>
                </table>
            <#--<div class="client-two-table">
                <div class="monitor-grid" id="shareholder-monitorList-grid"></div>
            </div> -->

            <div class="client-two-title">高管人员情况</div>
            <table class="m-table client-pro-table" style="margin-top:10px;" id="executive">
                    <colgroup>
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
	                        <td>${customerMag_index+1}</td>
	                        <td>${customerMag.magName!}</td>
	                        <td>${customerMag.magSex!}</td>
	                        <td>${customerMag.magIdentityCardNo!}</td>
	                        <td>${customerMag.magDutyType!}</td>
	                        <td>${customerMag.magEducation.desc()!}</td>
	                        <td>${customerMag.magProfession!}</td>
	                        <td>${customerMag.experience!}</td>
	                    </tr>
	                    </#list>
	                 </#if>
                    </tbody>
                </table>

            <div class="client-two-title">采购情况</div>
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
		                        <td>${customerPurchase_index+1}</td>
		                        <td>${customerPurchase.supplierName!}</td>
		                        <td>${formatMoney(customerPurchase.purchaseLastYear!'')}</td>
		                        <td>${customerPurchase.percent!}</td>
		                        <td>${customerPurchase.settleType.desc()!}</td>
		                        <td>${formatMoney(customerPurchase.accountPayableBalance!'')}</td>
		                    </tr>
	                    </#list>
	                </#if>
                    </tbody>
                </table>

            <div class="client-two-title">销售情况</div>
            	 <table class="m-table client-pro-table" style="margin-top:10px;" id="sell">
                    <colgroup>
                        <col width="8%">
                        <col width="19%">
                        <col width="16%">
                        <col width="16%">
                        <col width="16%">
                        <col width="">
                    </colgroup>
                    <tbody>
                    <tr class="top-list-one">
                        <td>序号</td>
                        <td>客户名称</td>
                        <td>上年销售额（万元）</td>
                        <td>占总销售额（%）</td>
                        <td>合作年限</td>
                        <td>应收账款余额（万元）</td>
                    </tr>
                    <#if customerDetailDTO.customerSales?exists >
                    	<#list customerDetailDTO.customerSales as customerSale>
		                    <tr class="client-add-tr">
		                        <td>${customerSale_index+1}</td>
		                        <td>${customerSale.saleCustomerName!}</td>
		                        <td>${formatMoney(customerSale.saleLastYear!'')}</td>
		                        <td>${customerSale.percent!}</td>
		                        <td>${customerSale.cooperationPeriod!}</td>
		                        <td>${formatMoney(customerSale.accountReceivableBalance!'')}</td>
		                    </tr>
		                 </#list>
		           </#if>
                    </tbody>
                </table>

            <div class="client-two-title">融资情况</div>
            	<table class="m-table client-pro-table" style="margin-top:10px;" id="financing">
                    <colgroup>
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
		                        <td>${customerMas_index+1}</td>
		                        <td>${customerMas.financialInstitutionName!}</td>
		                        <td>${formatMoney(customerMas.amount!'')}</td>
		                        <td>${customerMas.startDate!}</td>
		                        <td>${customerMas.endDate!}</td>
		                        <td>${customerMas.guaranteeType!}</td>
		                        <td>${customerMas.remark!}</td>
		                    </tr>
		              </#list>
		           </#if>   
                    </tbody>
                </table>
        </div>

        <div class="client-foot">
            <a class="form-search-link" href="${basepath!}/asset/enterpriseInfo/customerListPage">返回</a>
        </div>
    </div>
</form>
<script>
$(function(){
	//页面展示隐藏
    $(".pro-title-right").click(function(){
        $(this).parent().next().toggle();
        var ty = $(this).parent().next().is(":hidden");
        if(ty==false){
            $(this).html("折叠");
        }else if(ty==true){
            $(this).html("展开");
        }
    });
    //左侧导航栏鼠标滑动效果插件启动
    $('#client-nav').onePageNav();
})
 </script>
 <footer>Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
<div style="height:70px"></div>	
</@hb.htmlBase>