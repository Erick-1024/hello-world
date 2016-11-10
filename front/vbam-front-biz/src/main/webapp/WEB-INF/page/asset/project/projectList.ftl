<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="项目管理" jsFiles=["page/asset/project/projectList.js","common/dateutil.js"] cssFiles=["css/project.css","css/monitor.css"] localCssFiles=[] 
	curTopMenu = "项目管理" curSubMenu = "所有列表" removeExtHeader = false removeExtFooter = false>
	<div class="main-container">
		 <div class="pro-box-bg">
		<form style="position:relative;">
            <table class="pro-table-width amend-th">
                <colgroup>
                    <col width="160px">
                    <col width="250px">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>项目名称</th>
                    <td><input type="text" id= "name"  class="pro-table-input" style="margin-left: 0;width:178px;"></td>
                        <#--<select class="pro-table-input" id= "name" style="width:160px;">
                            <option value="">全部</option>
                            <option value="真旅项目">真旅项目</option>
                            <option value="韵达项目">韵达项目</option>
                        </select> -->
                </tr>
                <tr>
                    <th>核心企业</th>
                    <td><input type="text" id= "coreCompanyName"  class="pro-table-input" style="margin-left: 0;width:178px;"></td>
                </tr>
                <tr>
                    <th>业务品种</th>
                    <td>
                        <select class="" id="type" style="width:178px;" data-role="dropdownlist">
                            <option value="">全部</option>
                           <#-- <option value="factor">保理</option> -->
                            <option value="platform">平台</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>所处行业</th>
                    <td>
                        <select class="" id="coreIndustry" style="width:178px;" data-role="dropdownlist">
                            <option value="">全部</option>
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
                    </td>
                    <td>
                       <a class="form-search-btn" href="javascript:void(0);"><i class="searchIcon"></i>搜索</a>
                    </td>
                </tr>
                </tbody>
            </table>
            <#if authorizeKey("ASSET_LIST_CREATE")>
            	<a class="form-search-btn form-search-abs" id ="project-create" href="${basepath!}/asset/project/projectCreate">新建</a>
            </#if>
        </form>
			
			<div class="monitor-grid" id="monitorList-grid"></div>
			<div id="projectListGridWrap">
		       		<div class="assetProjectListManageGrid"></div>
    		</div>
			
	</div>
		
	<script>
		<#if userVo.customer.userType.name()=="CANA">
			$("#project-create").hide();
		</#if>
	</script>
	<script>
		var curSubMenu = "所有列表";
		var detailAuth = ${authorizeKey("ASSET_LIST_DETAIL")?string("true", "false")};
		var updateAuth = ${authorizeKey("ASSET_LIST_UPDATE")?string("true", "false")};
	</script>
</@hb.htmlBase>
