<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="企业基础资料编辑" jsFiles=["js/common/jquery.nav.js","page/asset/enterpriseInfo/client.js","page/asset/enterpriseInfo/enterpriseInfo.js","js/common/ajaxfileupload.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] curTopMenu = "基础资产信息" curSubMenu = "企业信息" removeExtHeader = false removeExtFooter = true>
<form class="">
    <div class="left-nav">
        <ul id="client-nav">
            <li class="current"><a href="#nav-one">基本信息&ensp;▶</a></li>
            <li><a href="#nav-two">工商资料&ensp;▶</a></li>
            <li><a href="#nav-three">财务资料&ensp;▶</a></li>
            <li><a href="#nav-four">法务查询&ensp;▶</a></li>
            <li><a href="#nav-five">征信查询&ensp;▶</a></li>
            <li><a href="#nav-six">基础交易&ensp;▶</a></li>	
            <li><a href="#nav-seven">保理流程&ensp;▶</a></li>
            <li><a href="#nav-eight">台账查询&ensp;▶</a></li>
        </ul>
    </div>
    <div style="width:100%;height:96px;" class="section" id="nav-one"></div>
    <div class="client-bg">
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
                    <td>${customerName!}</td>
                    <th>客户类型</th>
                    <td>${customerType!}</td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
        <!--工商资料-->
        <div class="pro-title" id="nav-two"><span class="pro-title-left">工商资料</span><span class="pro-title-right">折叠</span></div>
        <div class="client-hide-bg">
        	<div class="low-text">
                <div style="display:inline-block;width:120px;">
                    <p>支持的文件格式</p>
                </div>
                <div style="display:inline-block;">
                    <span style="color:red">*.doc、*.docx、*.png、*.jpg、*.pdf、*.txt、*.ppt、*.pptx、*.xls、*.xlsx</span>
                </div>

            </div>
            <table class="client-tb">
                <colgroup>
                    <col width="100px">
                    <col width="300px">
                    <col width="650px">
                    <col width="100px">
                    <col width="">
                </colgroup>
                <tbody>
	                <tr class="client-up-title">
	                    <td style="text-align: center;">文件编号</td>
	                    <td style="text-align: center;">文件清单</td>
	                    <td style="text-align: center;">附件上传</td>
	                    <td style="text-align: center;">操作</td>
	                    <td></td>
	                </tr>
                	<#list businessMaterial as bm>
		                <tr class="client-center">
			                    <td valign="top">1-${bm_index+1}</td>
			                    <td valign="top"><#if bm.isMust()><span class="redFalg">*</span></#if>${bm.desc()!}</td>
			                    <td class="up-td">
			                    <#if materialMap ??>
			                		<#list materialMap?keys as key>
			                			<#if key == "BUSINESS_MATERIAL">
											<#if materialMap[key]??>
												<#list materialMap[key]?keys as item>
													<#if item == bm.name()>
														<#if materialMap[key][item]??>
															<#list materialMap[key][item] as file>
										                        <ul class="client-ul-su">
								                    				<li>
								                    					<div class="float-left client-gray">文件名：</div>
								                    					<div class="float-left success-txt" title="${file.fileName!}">${file.fileName!}</div>
								                    				</li>
								                    				<li>
								                    					<div class="float-left client-gray">附件说明：</div>
								                    					<div class="float-left success-txt" title="${file.remark!}">${file.remark!}</div>
								                    					<div class="info" style="display:none;">
								                    						<input id="categlory" name="categlory" value="${file.categlory!}">
								                    						<input type="hidden" id="itemType" name="itemType" value="${file.itemType!}">
								                    						<input type="hidden" id="fileName" name="fileName" value="${file.fileName!}">
								                    						<input type="hidden" id="mediaId" name="mediaId" value="${file.mediaId!}">
								                    						<input type="hidden" id="remark" name="remark" value="${file.remark!}">
								                    						<input type="hidden" id="sequence" name="sequence" value="${file.sequence!}">
								                    					</div>
								                    				</li>
								                    				<li><a class="blue" href="${mediaserver}/imageservice?mediaImageId=${file.mediaId!}&mediaType=video" target="_blank">查看</a></li>
								                    				<li><a class="blue remove-acc" href="javascript:void(0);">删除</a></li>
							                    				</ul>
															</#list>
														</#if>
													</#if>
												</#list>
											</#if>
				                		</#if>
			                		</#list>
			                	</#if>
		                        <ul class="client-ul-st">
		                            <li class="up-div">
		                                <input id="${enterpriseInfoType[0].name()}-${bm.name()!}" name="image" class="input-file" type="file">
		                                <a class="input-file-a file" href="javascript:void(0);">上传附件</a>
		                                <span class="ok-icon"><img src="${host}images/success.png" style="display:none;"></span>
		                                <div class="info" style="display:none;">
			                                <input type="hidden" id="categlory" name="categlory" value="${enterpriseInfoType[0].name()}">
			                                <input type="hidden" id="itemType" name="itemType" value="${bm.name()!}">
			                                <input type="hidden" id="fileName" name="fileName" value="">
			                                <input type="hidden" id="mediaId" name="mediaId" value="">
			                                <input type="hidden" id="remark" name="remark" value="">
			                                <input type="hidden" id="sequence" name="sequence" value="1">
		                                </div>
		                            </li>
		                            <li>
		                                <label style="margin-left:20px;">
		                                    附件说明&emsp;<input class="annex-txt" type="text" placeholder="请输入附件说明" style="width:255px;" maxlength="20">
		                                </label>
		                            </li>
		                            <li><a class="blue look-acc" href="javascript:void(0);" style="display:none;" target="_blank">查看</a></li>
		                            <li><a class="blue delete-acc" href="javascript:void(0);" style="display:none;">删除</a></li>
		                        </ul>
		                    </td>
		                    <td valign="top">
		                        <a class="blue add-acc" href="javascript:void(0);">继续添加附件</a>
		                    </td>
		                    <td></td>
		                </tr>
		        	</#list>
                </tbody>
            </table>
        </div>
        <!--财务资料-->
        <div class="pro-title" id="nav-three"><span class="pro-title-left">财务资料</span><span class="pro-title-right">折叠</span></div>
        <div class="client-hide-bg">
            <table class="client-tb">
                <colgroup>
                    <col width="100px">
                    <col width="300px">
                    <col width="650px">
                    <col width="100px">
                    <col width="">
                </colgroup>
                <tbody>
	                <tr class="client-up-title">
	                    <td style="text-align: center;">文件编号</td>
	                    <td style="text-align: center;">文件清单</td>
	                    <td style="text-align: center;">附件上传</td>
	                    <td style="text-align: center;">操作</td>
	                    <td></td>
	                </tr>
	                <#if financeMaterial??>
	                	<#list financeMaterial as fm>
			                <tr class="client-center">
			                    <td valign="top">2-${fm_index+1}</td>
			                    <td valign="top"><#if fm.isMust()><span class="redFalg">*</span></#if>${fm.desc()!}</td>
			                    <td class="up-td">
				                    <#if materialMap ??>
				                		<#list materialMap?keys as key>
				                			<#if key == "FINANCE_MATERIAL">
												<#if materialMap[key]??>
													<#list materialMap[key]?keys as item>
														<#if item == fm.name()>
															<#if materialMap[key][item]??>
																<#list materialMap[key][item] as file>
											                        <ul class="client-ul-su">
									                    				<li>
									                    					<div class="float-left client-gray">文件名：</div>
									                    					<div class="float-left success-txt" title="${file.fileName!}">${file.fileName!}</div>
									                    				</li>
									                    				<li>
									                    					<div class="float-left client-gray">附件说明：</div>
									                    					<div class="float-left success-txt" title="${file.remark!}">${file.remark!}</div>
									                    					<div class="info" style="display:none;">
									                    						<input id="categlory" name="categlory" value="${file.categlory!}">
									                    						<input type="hidden" id="itemType" name="itemType" value="${file.itemType!}">
									                    						<input type="hidden" id="fileName" name="fileName" value="${file.fileName!}">
									                    						<input type="hidden" id="mediaId" name="mediaId" value="${file.mediaId!}">
									                    						<input type="hidden" id="remark" name="remark" value="${file.remark!}">
									                    						<input type="hidden" id="sequence" name="sequence" value="${file.sequence!}">
									                    					</div>
									                    				</li>
									                    				<li><a class="blue" href="${mediaserver}/imageservice?mediaImageId=${file.mediaId!}&mediaType=video" target="_blank">查看</a></li>
									                    				<li><a class="blue remove-acc" href="javascript:void(0);">删除</a></li>
								                    				</ul>
																</#list>
															</#if>
														</#if>
													</#list>
												</#if>
					                		</#if>
				                		</#list>
				                	</#if>
			                        <ul class="client-ul-st">
			                            <li class="up-div">
			                                <input id="${enterpriseInfoType[1].name()}-${fm.name()!}" name="image" class="input-file" type="file">
			                                <a class="input-file-a file" href="javascript:void(0);">上传附件</a>
			                                <span class="ok-icon"><img src="${host}images/success.png" style="display:none;"></span>
			                                <div class="info" style="display:none;"> 
			                                    <input type="hidden" id="categlory" name="categlory" value="${enterpriseInfoType[1].name()}">
				                                <input type="hidden" id="itemType" name="itemType" value="${fm.name()!}">
				                                <input type="hidden" id="fileName" name="fileName">
				                                <input type="hidden" id="mediaId" name="mediaId">
				                                <input type="hidden" id="remark" name="remark">
				                                <input type="hidden" id="sequence" name="sequence" value="1">
			                                </div>
			                            </li>
			                            <li>
			                                <label style="margin-left:20px;">
			                                    附件说明&emsp;<input class="annex-txt" type="text" placeholder="请输入附件说明" style="width:255px;" maxlength="20">
			                                </label>
			                            </li>
			                            <li><a class="blue look-acc" href="javascript:void(0);" style="display:none;" target="_blank">查看</a></li>
		                            	<li><a class="blue delete-acc" href="javascript:void(0);" style="display:none;">删除</a></li>
			                        </ul>
			                    </td>
			                    <td valign="top">
			                        <a class="blue add-acc" href="javascript:void(0);">继续添加附件</a>
			                    </td>
			                    <td></td>
			                </tr>
			        	</#list>
	                </#if>
                </tbody>
            </table>
        </div>
        <!--法务查询-->
        <div class="pro-title" id="nav-four"><span class="pro-title-left">法务查询</span><span class="pro-title-right">折叠</span></div>
        <div class="client-hide-bg">
            <div class="low-text">
                <div style="display:inline-block;width:200px;">
                    <p>中国裁判文书网</p>
                    <p>全国法院被执行人信息查询</p>
                    <p>中国法院网</p>
                    <p>各地税务局连接（税务总局）</p>
                </div>
                <div style="display:inline-block;">
                    <a href="http://wenshu.court.gov.cn/" target="_blank">http://wenshu.court.gov.cn/</a>
                    <a href="http://zhixing.court.gov.cn/search/" target="_blank">http://zhixing.court.gov.cn/search/</a>
                    <a href="http://www.chinacourt.org/index.shtml" target="_blank">http://www.chinacourt.org/index.shtml</a>
                    <a href="http://www.chinatax.gov.cn/" target="_blank">http://www.chinatax.gov.cn/</a>
                </div>

            </div>
            <table class="client-tb">
                <colgroup>
                    <col width="100px">
                    <col width="300px">
                    <col width="650px">
                    <col width="100px">
                    <col width="">
                </colgroup>
                <tbody>
	                <tr class="client-up-title">
	                    <td style="text-align: center;">文件编号</td>
	                    <td style="text-align: center;">文件清单</td>
	                    <td style="text-align: center;">附件上传</td>
	                    <td style="text-align: center;">操作</td>
	                    <td></td>
	                </tr>
	                <#if legalInquire??>
	                	<#list legalInquire as li>
			                <tr class="client-center">
			                    <td valign="top">3-${li_index+1}</td>
			                    <td valign="top"><#if li.isMust()><span class="redFalg">*</span></#if>${li.desc()!}</td>
			                    <td class="up-td">
			                    	<#if materialMap ??>
				                		<#list materialMap?keys as key>
				                			<#if key == "LEGAL_INQUIRE">
												<#if materialMap[key]??>
													<#list materialMap[key]?keys as item>
														<#if item == li.name()>
															<#if materialMap[key][item]??>
																<#list materialMap[key][item] as file>
											                        <ul class="client-ul-su">
									                    				<li>
									                    					<div class="float-left client-gray">文件名：</div>
									                    					<div class="float-left success-txt" title="${file.fileName!}">${file.fileName!}</div>
									                    				</li>
									                    				<li>
									                    					<div class="float-left client-gray">附件说明：</div>
									                    					<div class="float-left success-txt" title="${file.remark!}">${file.remark!}</div>
									                    					<div class="info" style="display:none;">
									                    						<input id="categlory" name="categlory" value="${file.categlory!}">
									                    						<input type="hidden" id="itemType" name="itemType" value="${file.itemType!}">
									                    						<input type="hidden" id="fileName" name="fileName" value="${file.fileName!}">
									                    						<input type="hidden" id="mediaId" name="mediaId" value="${file.mediaId!}">
									                    						<input type="hidden" id="remark" name="remark" value="${file.remark!}">
									                    						<input type="hidden" id="sequence" name="sequence" value="${file.sequence!}">
									                    					</div>
									                    				</li>
									                    				<li><a class="blue" href="${mediaserver}/imageservice?mediaImageId=${file.mediaId!}&mediaType=video" target="_blank">查看</a></li>
									                    				<li><a class="blue remove-acc" href="javascript:void(0);">删除</a></li>
								                    				</ul>
																</#list>
															</#if>
														</#if>
													</#list>
												</#if>
					                		</#if>
				                		</#list>
				                	</#if>
			                        <ul class="client-ul-st">
			                            <li class="up-div">
			                                <input id="${enterpriseInfoType[2].name()}-${li.name()!}" name="image" class="input-file" type="file">
			                                <a class="input-file-a file" href="javascript:void(0);">上传附件</a>
			                                <span class="ok-icon"><img src="${host}images/success.png" style="display:none;"></span>
			                                <div class="info" style="display:none;">
				                                <input type="hidden" id="categlory" name="categlory" value="${enterpriseInfoType[2].name()}">
				                                <input type="hidden" id="itemType" name="itemType" value="${li.name()!}">
				                                <input type="hidden" id="fileName" name="fileName">
				                                <input type="hidden" id="mediaId" name="mediaId">
				                                <input type="hidden" id="remark" name="remark">
				                                <input type="hidden" id="sequence" name="sequence" value="1">
			                                </div>
			                            </li>
			                            <li>
			                                <label style="margin-left:20px;">
			                                    附件说明&emsp;<input class="annex-txt" type="text" placeholder="请输入附件说明" style="width:255px;" maxlength="20">
			                                </label>
			                            </li>
			                            <li><a class="blue look-acc" href="javascript:void(0);" style="display:none;" target="_blank">查看</a></li>
		                            	<li><a class="blue delete-acc" href="javascript:void(0);" style="display:none;">删除</a></li>
			                        </ul>
			                    </td>
			                    <td valign="top">
			                        <a class="blue add-acc" href="javascript:void(0);">继续添加附件</a>
			                    </td>
			                    <td></td>
			                </tr>
			        	</#list>
	                </#if>
                </tbody>
            </table>
        </div>
        <!--征信查询-->
        <div class="pro-title" id="nav-five"><span class="pro-title-left">征信查询</span><span class="pro-title-right">折叠</span></div>
        <div class="client-hide-bg">
            <table class="client-tb">
                <colgroup>
                    <col width="100px">
                    <col width="300px">
                    <col width="650px">
                    <col width="100px">
                    <col width="">
                </colgroup>
                <tbody>
	                <tr class="client-up-title">
	                    <td style="text-align: center;">文件编号</td>
	                    <td style="text-align: center;">文件清单</td>
	                    <td style="text-align: center;">附件上传</td>
	                    <td style="text-align: center;">操作</td>
	                    <td></td>
	                </tr>
	                <#if creditInquire??>
	                	<#list creditInquire as li>
			                <tr class="client-center">
			                    <td valign="top">4-${li_index+1}</td>
			                    <td valign="top"><#if li.isMust()><span class="redFalg">*</span></#if>${li.desc()!}</td>
			                    <td class="up-td">
			                    	<#if materialMap ??>
				                		<#list materialMap?keys as key>
				                			<#if key == "CREDIT_INQUIRE">
												<#if materialMap[key]??>
													<#list materialMap[key]?keys as item>
														<#if item == li.name()>
															<#if materialMap[key][item]??>
																<#list materialMap[key][item] as file>
											                        <ul class="client-ul-su">
									                    				<li>
									                    					<div class="float-left client-gray">文件名：</div>
									                    					<div class="float-left success-txt" title="${file.fileName!}">${file.fileName!}</div>
									                    				</li>
									                    				<li>
									                    					<div class="float-left client-gray">附件说明：</div>
									                    					<div class="float-left success-txt" title="${file.remark!}">${file.remark!}</div>
									                    					<div class="info" style="display:none;">
									                    						<input id="categlory" name="categlory" value="${file.categlory!}">
									                    						<input type="hidden" id="itemType" name="itemType" value="${file.itemType!}">
									                    						<input type="hidden" id="fileName" name="fileName" value="${file.fileName!}">
									                    						<input type="hidden" id="mediaId" name="mediaId" value="${file.mediaId!}">
									                    						<input type="hidden" id="remark" name="remark" value="${file.remark!}">
									                    						<input type="hidden" id="sequence" name="sequence" value="${file.sequence!}">
									                    					</div>
									                    				</li>
									                    				<li><a class="blue" href="${mediaserver}/imageservice?mediaImageId=${file.mediaId!}&mediaType=video" target="_blank" style="display:none;">查看</a></li>
									                    				<li><a class="blue remove-acc" href="javascript:void(0);" style="display:none;">删除</a></li>
								                    				</ul>
																</#list>
															</#if>
														</#if>
													</#list>
												</#if>
					                		</#if>
				                		</#list>
				                	</#if>
			                        <ul class="client-ul-st">
			                            <li class="up-div">
			                                <input id="${enterpriseInfoType[3].name()}-${li.name()!}" name="image" class="input-file" type="file">
			                                <a class="input-file-a file" href="javascript:void(0);">上传附件</a>
			                                <span class="ok-icon"><img src="${host}images/success.png" style="display:none;"></span>
			                                <div class="info" style="display:none;">
				                                <input type="hidden" id="categlory" name="categlory" value="${enterpriseInfoType[3].name()}">
				                                <input type="hidden" id="itemType" name="itemType" value="${li.name()!}">
				                                <input type="hidden" id="fileName" name="fileName">
				                                <input type="hidden" id="mediaId" name="mediaId">
				                                <input type="hidden" id="remark" name="remark">
				                                <input type="hidden" id="sequence" name="sequence" value="1">
			                                </div>
			                            </li>
			                            <li>
			                                <label style="margin-left:20px;">
			                                    附件说明&emsp;<input class="annex-txt" type="text" placeholder="请输入附件说明" style="width:255px;" maxlength="20">
			                                </label>
			                            </li>
			                            <li><a class="blue look-acc" href="javascript:void(0);" style="display:none;" target="_blank">查看</a></li>
		                            	<li><a class="blue delete-acc" href="javascript:void(0);" style="display:none;">删除</a></li>
			                        </ul>
			                    </td>
			                    <td valign="top">
			                        <a class="blue add-acc" href="javascript:void(0);">继续添加附件</a>
			                    </td>
			                    <td></td>
			                </tr>
			        	</#list>
	                </#if>
                </tbody>
            </table>
        </div>
        <!--基础交易-->
        <div class="pro-title" id="nav-six"><span class="pro-title-left">基础交易</span><span class="pro-title-right">折叠</span></div>
        <div class="client-hide-bg">
            <table class="client-tb">
                <colgroup>
                    <col width="100px">
                    <col width="300px">
                    <col width="650px">
                    <col width="100px">
                    <col width="">
                </colgroup>
                <tbody>
	                <tr class="client-up-title">
	                    <td style="text-align: center;">文件编号</td>
	                    <td style="text-align: center;">文件清单</td>
	                    <td style="text-align: center;">附件上传</td>
	                    <td style="text-align: center;">操作</td>
	                    <td></td>
	                </tr>
	                <#if basicTransaction??>
	                	<#list basicTransaction as bt>
			                <tr class="client-center">
			                    <td valign="top">5-${bt_index+1}</td>
			                    <td valign="top"><#if bt.isMust()><span class="redFalg">*</span></#if>${bt.desc()!}</td>
			                    <td class="up-td">
			                    	<#if materialMap ??>
				                		<#list materialMap?keys as key>
				                			<#if key == "BASIC_TRANSACTION">
												<#if materialMap[key]??>
													<#list materialMap[key]?keys as item>
														<#if item == bt.name()>
															<#if materialMap[key][item]??>
																<#list materialMap[key][item] as file>
											                        <ul class="client-ul-su">
									                    				<li>
									                    					<div class="float-left client-gray">文件名：</div>
									                    					<div class="float-left success-txt" title="${file.fileName!}">${file.fileName!}</div>
									                    				</li>
									                    				<li>
									                    					<div class="float-left client-gray">附件说明：</div>
									                    					<div class="float-left success-txt" title="${file.remark!}">${file.remark!}</div>
									                    					<div class="info" style="display:none;">
									                    						<input id="categlory" name="categlory" value="${file.categlory!}">
									                    						<input type="hidden" id="itemType" name="itemType" value="${file.itemType!}">
									                    						<input type="hidden" id="fileName" name="fileName" value="${file.fileName!}">
									                    						<input type="hidden" id="mediaId" name="mediaId" value="${file.mediaId!}">
									                    						<input type="hidden" id="remark" name="remark" value="${file.remark!}">
									                    						<input type="hidden" id="sequence" name="sequence" value="${file.sequence!}">
									                    					</div>
									                    				</li>
									                    				<li><a class="blue" href="${mediaserver}/imageservice?mediaImageId=${file.mediaId!}&mediaType=video" target="_blank">查看</a></li>
									                    				<li><a class="blue remove-acc" href="javascript:void(0);">删除</a></li>
								                    				</ul>
																</#list>
															</#if>
														</#if>
													</#list>
												</#if>
					                		</#if>
				                		</#list>
				                	</#if>
			                        <ul class="client-ul-st">
			                            <li class="up-div">
			                                <input id="${enterpriseInfoType[4].name()}-${bt.name()!}" name="image" class="input-file" type="file">
			                                <a class="input-file-a file" href="javascript:void(0);">上传附件</a>
			                                <span class="ok-icon"><img src="${host}images/success.png" style="display:none;"></span>
			                                <div class="info" style="display:none;">
				                                <input type="hidden" id="categlory" name="categlory" value="${enterpriseInfoType[4].name()}">
				                                <input type="hidden" id="itemType" name="itemType" value="${bt.name()!}">
				                                <input type="hidden" id="fileName" name="fileName">
				                                <input type="hidden" id="mediaId" name="mediaId">
				                                <input type="hidden" id="remark" name="remark">
				                                <input type="hidden" id="sequence" name="sequence" value="1">
			                                </div>
			                            </li>
			                            <li>
			                                <label style="margin-left:20px;">
			                                    附件说明&emsp;<input class="annex-txt" type="text" placeholder="请输入附件说明" style="width:255px;" maxlength="20">
			                                </label>
			                            </li>
			                            <li><a class="blue look-acc" href="javascript:void(0);" style="display:none;" target="_blank">查看</a></li>
		                            	<li><a class="blue delete-acc" href="javascript:void(0);" style="display:none;">删除</a></li>
			                        </ul>
			                    </td>
			                    <td valign="top">
			                        <a class="blue add-acc" href="javascript:void(0);">继续添加附件</a>
			                    </td>
			                    <td>
			                    </td>
			                </tr>
			        	</#list>
	                </#if>
                </tbody>
            </table>
        </div>
        <!--保理流程-->
        <div class="pro-title" id="nav-seven"><span class="pro-title-left">保理流程</span><span class="pro-title-right">折叠</span></div>
        <div class="client-hide-bg">
            <table class="client-tb">
                <colgroup>
                    <col width="100px">
                    <col width="300px">
                    <col width="650px">
                    <col width="100px">
                    <col width="">
                </colgroup>
                <tbody>
	                <tr class="client-up-title">
	                    <td style="text-align: center;">文件编号</td>
	                    <td style="text-align: center;">文件清单</td>
	                    <td style="text-align: center;">附件上传</td>
	                    <td style="text-align: center;">操作</td>
	                    <td></td>
	                </tr>
	                <#if factoringFlow??>
		                <#list factoringFlow as ff>
		                	<#if ff_index == 0>
				                <tr><td style="font-weight: bold;text-align: center;">评审材料</td><td></td><td></td><td></td><td></td></tr>
				            <#elseif ff_index == 9>   
				                <tr><td style="font-weight: bold;text-align: center;">放款前资料</td><td></td><td></td><td></td><td></td></tr>
				            <#elseif ff_index == 19>   
				                <tr><td style="font-weight: bold;text-align: center;">放款后资料</td><td></td><td></td><td></td><td></td></tr>
		                	</#if>
			                <tr class="client-center">
			                    <td valign="top">6-${ff_index+1}</td>
			                    <td valign="top"><#if ff.isMust()><span class="redFalg">*</span></#if>${ff.desc()!}</td>
			                    <td class="up-td">
			                    	<#if materialMap ??>
				                		<#list materialMap?keys as key>
				                			<#if key == "FACTORING_FLOW">
												<#if materialMap[key]??>
													<#list materialMap[key]?keys as item>
														<#if item == ff.name()>
															<#if materialMap[key][item]??>
																<#list materialMap[key][item] as file>
											                        <ul class="client-ul-su">
									                    				<li>
									                    					<div class="float-left client-gray">文件名：</div>
									                    					<div class="float-left success-txt" title="${file.fileName!}">${file.fileName!}</div>
									                    				</li>
									                    				<li>
									                    					<div class="float-left client-gray">附件说明：</div>
									                    					<div class="float-left success-txt" title="${file.remark!}">${file.remark!}</div>
									                    					<div class="info" style="display:none;">
									                    						<input id="categlory" name="categlory" value="${file.categlory!}">
									                    						<input type="hidden" id="itemType" name="itemType" value="${file.itemType!}">
									                    						<input type="hidden" id="fileName" name="fileName" value="${file.fileName!}">
									                    						<input type="hidden" id="mediaId" name="mediaId" value="${file.mediaId!}">
									                    						<input type="hidden" id="remark" name="remark" value="${file.remark!}">
									                    						<input type="hidden" id="sequence" name="sequence" value="${file.sequence!}">
									                    					</div>
									                    				</li>
									                    				<li><a class="blue" href="${mediaserver}/imageservice?mediaImageId=${file.mediaId!}&mediaType=video" target="_blank">查看</a></li>
									                    				<li><a class="blue remove-acc" href="javascript:void(0);">删除</a></li>
								                    				</ul>
																</#list>
															</#if>
														</#if>
													</#list>
												</#if>
					                		</#if>
				                		</#list>
				                	</#if>
			                        <ul class="client-ul-st">
			                            <li class="up-div">
			                                <input id="${enterpriseInfoType[5].name()}-${ff.name()!}" name="image" class="input-file" type="file">
			                                <a class="input-file-a file" href="javascript:void(0);">上传附件</a>
			                                <span class="ok-icon"><img src="${host}images/success.png" style="display:none;"></span>
			                                <div class="info" style="display:none;">
				                                <input type="hidden" id="categlory" name="categlory" value="${enterpriseInfoType[5].name()}">
				                                <input type="hidden" id="itemType" name="itemType" value="${ff.name()!}">
				                                <input type="hidden" id="fileName" name="fileName">
				                                <input type="hidden" id="mediaId" name="mediaId">
				                                <input type="hidden" id="remark" name="remark">
				                                <input type="hidden" id="sequence" name="sequence" value="1">
			                                </div>
			                            </li>
			                            <li>
			                                <label style="margin-left:20px;">
			                                    附件说明&emsp;<input class="annex-txt" type="text" placeholder="请输入附件说明" style="width:255px;" maxlength="20">
			                                </label>
			                            </li>
			                            <li><a class="blue look-acc" href="javascript:void(0);" style="display:none;" target="_blank">查看</a></li>
		                            	<li><a class="blue delete-acc" href="javascript:void(0);" style="display:none;">删除</a></li>
			                        </ul>
			                    </td>
			                    <td valign="top">
			                        <a class="blue add-acc" href="javascript:void(0);">继续添加附件</a>
			                    </td>
			                    <td></td>
			                </tr>
			        	</#list>
	                </#if>
                </tbody>
            </table>
        </div>
        <!--台账查询-->
        <div class="pro-title" id="nav-eight"><span class="pro-title-left">台账查询</span><span class="pro-title-right">折叠</span></div>
        <div class="client-hide-bg">
            <table class="client-tb">
                <colgroup>
                    <col width="100px">
                    <col width="300px">
                    <col width="650px">
                    <col width="100px">
                    <col width="">
                </colgroup>
                <tbody>
	                <tr class="client-up-title">
	                    <td style="text-align: center;">文件编号</td>
	                    <td style="text-align: center;">文件清单</td>
	                    <td style="text-align: center;">附件上传</td>
	                    <td style="text-align: center;">操作</td>
	                    <td></td>
	                </tr>
	                <#if ledgerInquire??>
	                	<#list ledgerInquire as le>
			                <tr class="client-center">
			                    <td valign="top">7-${le_index+1}</td>
			                    <td valign="top"><#if le.isMust()><span class="redFalg">*</span></#if>${le.desc()!}</td>
			                    <td class="up-td">
			                    	<#if materialMap ??>
				                		<#list materialMap?keys as key>
				                			<#if key == "LEDGER_INQUIRE">
												<#if materialMap[key]??>
													<#list materialMap[key]?keys as item>
														<#if item == le.name()>
															<#if materialMap[key][item]??>
																<#list materialMap[key][item] as file>
											                        <ul class="client-ul-su">
									                    				<li>
									                    					<div class="float-left client-gray">文件名：</div>
									                    					<div class="float-left success-txt" title="${file.fileName!}">${file.fileName!}</div>
									                    				</li>
									                    				<li>
									                    					<div class="float-left client-gray">附件说明：</div>
									                    					<div class="float-left success-txt" title="${file.remark!}">${file.remark!}</div>
									                    					<div class="info" style="display:none;">
									                    						<input id="categlory" name="categlory" value="${file.categlory!}">
									                    						<input type="hidden" id="itemType" name="itemType" value="${file.itemType!}">
									                    						<input type="hidden" id="fileName" name="fileName" value="${file.fileName!}">
									                    						<input type="hidden" id="mediaId" name="mediaId" value="${file.mediaId!}">
									                    						<input type="hidden" id="remark" name="remark" value="${file.remark!}">
									                    						<input type="hidden" id="sequence" name="sequence" value="${file.sequence!}">
									                    					</div>
									                    				</li>
									                    				<li><a class="blue" href="${mediaserver}/imageservice?mediaImageId=${file.mediaId!}&mediaType=video" target="_blank">查看</a></li>
									                    				<li><a class="blue remove-acc" href="javascript:void(0);">删除</a></li>
								                    				</ul>
																</#list>
															</#if>
														</#if>
													</#list>
												</#if>
					                		</#if>
				                		</#list>
				                	</#if>
			                        <ul class="client-ul-st">
			                            <li class="up-div">
			                                <input id="${enterpriseInfoType[6].name()}-${le.name()!}" name="image" class="input-file" type="file">
			                                <a class="input-file-a file" href="javascript:void(0);">上传附件</a>
			                                <span class="ok-icon"><img src="${host}images/success.png" style="display:none;"></span>
			                                <div class="info" style="display:none;">
				                                <input type="hidden" id="categlory" name="categlory" value="${enterpriseInfoType[6].name()}">
				                                <input type="hidden" id="itemType" name="itemType" value="${le.name()!}">
				                                <input type="hidden" id="fileName" name="fileName" value="">
				                                <input type="hidden" id="mediaId" name="mediaId" value="">
				                                <input type="hidden" id="remark" name="remark" value="">
				                                <input type="hidden" id="sequence" name="sequence" value="1">
			                                </div>
			                            </li>
			                            <li>
			                                <label style="margin-left:20px;">
			                                    附件说明&emsp;<input class="annex-txt" type="text" placeholder="请输入附件说明" style="width:255px;" maxlength="20">
			                                </label>
			                            </li>
			                            <li><a class="blue look-acc" href="javascript:void(0);" style="display:none;" target="_blank">查看</a></li>
		                            	<li><a class="blue delete-acc" href="javascript:void(0);" style="display:none;">删除</a></li>
			                        </ul>
			                    </td>
			                    <td valign="top">
			                        <a class="blue add-acc" href="javascript:void(0);">继续添加附件</a>
			                    </td>
			                    <td>
			                    </td>
			                </tr>
			        	</#list>
	                </#if>
                </tbody>
            </table>
        </div>
        <div class="client-foot">
            <a class="form-search-link submitInfo" href="javascript:void(0);">提交</a>
            <a class="form-search-link saveTemp" href="javascript:void(0);">保存</a>
            <a class="form-search-link" href="javascript: history.back(-1);">返回</a>
        </div>
    </div>
</form>
<input type="hidden" name="customerId" value="${customerId!}">
<form name="submitData" action="${basepath}/asset/enterpriseInfo/saveTempInfo" enctype="application/json" method="post">
</form>
<footer>Copyright © 2015-2016 CANA All Rights Reserved. 沪ICP备15045029号</footer>
<div style="height:70px"></div>
</@hb.htmlBase>