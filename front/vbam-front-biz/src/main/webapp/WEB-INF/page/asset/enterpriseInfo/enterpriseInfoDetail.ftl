<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="企业基础资料详情" jsFiles=["js/common/jquery.nav.js","page/asset/enterpriseInfo/client.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] curTopMenu = "基础资产信息" curSubMenu = "企业信息">
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
        <#list enterpriseInfoType as type>
	        <#if type_index == 0>
		        <div class="pro-title" id="nav-two"><span class="pro-title-left">${type.desc()}</span><span class="pro-title-right">折叠</span></div>
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
			                    <td style="text-align: center;"></td>
			                    <td></td>
			                </tr>
			                <!--附件上传模板start-->
			                <#list businessMaterial as bm>
								<tr class="client-center">
				                    <td valign="top">${type_index+1}-${bm_index+1}</td>
				                    <td valign="top">${bm.desc()!}</td>
				                    <td class="up-td">
				                        <ul class="client-ul-su">
						                	<#if materialMap ??>
						                		<#list materialMap?keys as key>
						                			<#if key==type.name()>
														<#if materialMap[key]??>
															<#list materialMap[key]?keys as item>
																<#if item==bm.name()>
																	<#if materialMap[key][item]??>
																		<#list materialMap[key][item] as file>
												                            <li>
												                                <div class="float-left client-gray">文件名：</div>
												                                <div class="float-left success-txt" title="${(file.fileName)!}">${(file.fileName)!}</div>
												                            </li>
												                            <li>
												                                <div class="float-left client-gray">附件说明：</div>
												                                <div class="float-left success-txt" title="${(file.remark)!}">${(file.remark)!}</div>
												                            </li>
												                            <li></li>
												                            <li><a class="blue" href="${mediaserver}imageservice?mediaImageId=${(file.mediaId)!}&mediaType=video" target="_blank">查看</a></li>
																		</#list>
																	</#if>
																</#if>
															</#list>
														</#if>
							                		</#if>
						                		</#list>
						                	</#if>
				                        </ul>
				                    </td>
				                    <td valign="top"></td>
				                    <td></td>
				                </tr>
			                </#list>
		                </tbody>
		            </table>
		        </div>
	        </#if>
	        <#if type_index == 1>
		        <div class="pro-title" id="nav-three"><span class="pro-title-left">${type.desc()}</span><span class="pro-title-right">折叠</span></div>
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
			                    <td style="text-align: center;"></td>
			                    <td></td>
			                </tr>
			                <!--附件上传模板start-->
			                <#list financeMaterial as bm>
								<tr class="client-center">
				                    <td valign="top">${type_index+1}-${bm_index+1}</td>
				                    <td valign="top">${bm.desc()!}</td>
				                    <td class="up-td">
				                        <ul class="client-ul-su">
						                	<#if materialMap ??>
						                		<#list materialMap?keys as key>
						                			<#if key==type.name()>
														<#if materialMap[key]??>
															<#list materialMap[key]?keys as item>
																<#if item==bm.name()>
																	<#if materialMap[key][item]??>
																		<#list materialMap[key][item] as file>
												                            <li>
												                                <div class="float-left client-gray">文件名：</div>
												                                <div class="float-left success-txt" title="${(file.fileName)!}">${(file.fileName)!}</div>
												                            </li>
												                            <li>
												                                <div class="float-left client-gray">附件说明：</div>
												                                <div class="float-left success-txt" title="${(file.remark)!}">${(file.remark)!}</div>
												                            </li>
												                            <li></li>
												                            <li><a class="blue" href="${mediaserver}imageservice?mediaImageId=${(file.mediaId)!}&mediaType=video" target="_blank">查看</a></li>
																		</#list>
																	</#if>
																</#if>
															</#list>
														</#if>
							                		</#if>
						                		</#list>
						                	</#if>
				                        </ul>
				                    </td>
				                    <td valign="top"></td>
				                    <td></td>
				                </tr>
			                </#list>
		                </tbody>
		            </table>
		        </div>
	        </#if>
	        <#if type_index == 2>
		        <div class="pro-title" id="nav-four"><span class="pro-title-left">${type.desc()}</span><span class="pro-title-right">折叠</span></div>
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
			                    <td style="text-align: center;"></td>
			                    <td></td>
			                </tr>
			                <!--附件上传模板start-->
			                <#list legalInquire as bm>
								<tr class="client-center">
				                    <td valign="top">${type_index+1}-${bm_index+1}</td>
				                    <td valign="top">${bm.desc()!}</td>
				                    <td class="up-td">
				                        <ul class="client-ul-su">
						                	<#if materialMap ??>
						                		<#list materialMap?keys as key>
						                			<#if key==type.name()>
														<#if materialMap[key]??>
															<#list materialMap[key]?keys as item>
																<#if item==bm.name()>
																	<#if materialMap[key][item]??>
																		<#list materialMap[key][item] as file>
												                            <li>
												                                <div class="float-left client-gray">文件名：</div>
												                                <div class="float-left success-txt" title="${(file.fileName)!}">${(file.fileName)!}</div>
												                            </li>
												                            <li>
												                                <div class="float-left client-gray">附件说明：</div>
												                                <div class="float-left success-txt" title="${(file.remark)!}">${(file.remark)!}</div>
												                            </li>
												                            <li></li>
												                            <li><a class="blue" href="${mediaserver}imageservice?mediaImageId=${(file.mediaId)!}&mediaType=video" target="_blank">查看</a></li>
																		</#list>
																	</#if>
																</#if>
															</#list>
														</#if>
							                		</#if>
						                		</#list>
						                	</#if>
				                        </ul>
				                    </td>
				                    <td valign="top"></td>
				                    <td></td>
				                </tr>
			                </#list>
		                </tbody>
		            </table>
		        </div>
	        </#if>
	        <#if type_index == 3>
		        <div class="pro-title" id="nav-five"><span class="pro-title-left">${type.desc()}</span><span class="pro-title-right">折叠</span></div>
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
			                    <td style="text-align: center;"></td>
			                    <td></td>
			                </tr>
			                <!--附件上传模板start-->
			                <#list creditInquire as bm>
								<tr class="client-center">
				                    <td valign="top">${type_index+1}-${bm_index+1}</td>
				                    <td valign="top">${bm.desc()!}</td>
				                    <td class="up-td">
				                        <ul class="client-ul-su">
						                	<#if materialMap ??>
						                		<#list materialMap?keys as key>
						                			<#if key==type.name()>
														<#if materialMap[key]??>
															<#list materialMap[key]?keys as item>
																<#if item==bm.name()>
																	<#if materialMap[key][item]??>
																		<#list materialMap[key][item] as file>
												                            <li>
												                                <div class="float-left client-gray">文件名：</div>
												                                <div class="float-left success-txt" title="${(file.fileName)!}">${(file.fileName)!}</div>
												                            </li>
												                            <li>
												                                <div class="float-left client-gray">附件说明：</div>
												                                <div class="float-left success-txt" title="${(file.remark)!}">${(file.remark)!}</div>
												                            </li>
												                            <li></li>
												                            <li><a class="blue" href="${mediaserver}imageservice?mediaImageId=${(file.mediaId)!}&mediaType=video" target="_blank">查看</a></li>
																		</#list>
																	</#if>
																</#if>
															</#list>
														</#if>
							                		</#if>
						                		</#list>
						                	</#if>
				                        </ul>
				                    </td>
				                    <td valign="top"></td>
				                    <td></td>
				                </tr>
			                </#list>
		                </tbody>
		            </table>
		        </div>
	        </#if>
	        <#if type_index == 4>
		        <div class="pro-title" id="nav-six"><span class="pro-title-left">${type.desc()}</span><span class="pro-title-right">折叠</span></div>
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
			                    <td style="text-align: center;"></td>
			                    <td></td>
			                </tr>
			                <!--附件上传模板start-->
			                <#list basicTransaction as bm>
								<tr class="client-center">
				                    <td valign="top">${type_index+1}-${bm_index+1}</td>
				                    <td valign="top">${bm.desc()!}</td>
				                    <td class="up-td">
				                        <ul class="client-ul-su">
						                	<#if materialMap ??>
						                		<#list materialMap?keys as key>
						                			<#if key==type.name()>
														<#if materialMap[key]??>
															<#list materialMap[key]?keys as item>
																<#if item==bm.name()>
																	<#if materialMap[key][item]??>
																		<#list materialMap[key][item] as file>
												                            <li>
												                                <div class="float-left client-gray">文件名：</div>
												                                <div class="float-left success-txt" title="${(file.fileName)!}">${(file.fileName)!}</div>
												                            </li>
												                            <li>
												                                <div class="float-left client-gray">附件说明：</div>
												                                <div class="float-left success-txt" title="${(file.remark)!}">${(file.remark)!}</div>
												                            </li>
												                            <li></li>
												                            <li><a class="blue" href="${mediaserver}imageservice?mediaImageId=${(file.mediaId)!}&mediaType=video" target="_blank">查看</a></li>
																		</#list>
																	</#if>
																</#if>
															</#list>
														</#if>
							                		</#if>
						                		</#list>
						                	</#if>
				                        </ul>
				                    </td>
				                    <td valign="top"></td>
				                    <td></td>
				                </tr>
			                </#list>
		                </tbody>
		            </table>
		        </div>
	        </#if>
	        <#if type_index == 5>
		        <div class="pro-title" id="nav-seven"><span class="pro-title-left">${type.desc()}</span><span class="pro-title-right">折叠</span></div>
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
			                    <td style="text-align: center;"></td>
			                    <td></td>
			                </tr>
			                <!--附件上传模板start-->
			                <#list factoringFlow as bm>
			                	<#if bm_index == 0>
					                <tr><td style="font-weight: bold;text-align: center;">评审材料</td><td></td><td></td><td></td><td></td></tr>
					            <#elseif bm_index == 9>   
					                <tr><td style="font-weight: bold;text-align: center;">放款前资料</td><td></td><td></td><td></td><td></td></tr>
					            <#elseif bm_index == 19>   
					                <tr><td style="font-weight: bold;text-align: center;">放款后资料</td><td></td><td></td><td></td><td></td></tr>
			                	</#if>
								<tr class="client-center">
				                    <td valign="top">${type_index+1}-${bm_index+1}</td>
				                    <td valign="top">${bm.desc()!}</td>
				                    <td class="up-td">
				                        <ul class="client-ul-su">
						                	<#if materialMap ??>
						                		<#list materialMap?keys as key>
						                			<#if key==type.name()>
														<#if materialMap[key]??>
															<#list materialMap[key]?keys as item>
																<#if item==bm.name()>
																	<#if materialMap[key][item]??>
																		<#list materialMap[key][item] as file>
												                            <li>
												                                <div class="float-left client-gray">文件名：</div>
												                                <div class="float-left success-txt" title="${(file.fileName)!}">${(file.fileName)!}</div>
												                            </li>
												                            <li>
												                                <div class="float-left client-gray">附件说明：</div>
												                                <div class="float-left success-txt" title="${(file.remark)!}">${(file.remark)!}</div>
												                            </li>
												                            <li></li>
												                            <li><a class="blue" href="${mediaserver}imageservice?mediaImageId=${(file.mediaId)!}&mediaType=video" target="_blank">查看</a></li>
																		</#list>
																	</#if>
																</#if>
															</#list>
														</#if>
							                		</#if>
						                		</#list>
						                	</#if>
				                        </ul>
				                    </td>
				                    <td valign="top"></td>
				                    <td></td>
				                </tr>
			                </#list>
		                </tbody>
		            </table>
		        </div>
	        </#if>
	        <#if type_index == 6>
		        <div class="pro-title" id="nav-eight"><span class="pro-title-left">${type.desc()}</span><span class="pro-title-right">折叠</span></div>
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
			                    <td style="text-align: center;"></td>
			                    <td></td>
			                </tr>
			                <!--附件上传模板start-->
			                <#list ledgerInquire as bm>
								<tr class="client-center">
				                    <td valign="top">${type_index+1}-${bm_index+1}</td>
				                    <td valign="top">${bm.desc()!}</td>
				                    <td class="up-td">
				                        <ul class="client-ul-su">
						                	<#if materialMap ??>
						                		<#list materialMap?keys as key>
						                			<#if key==type.name()>
														<#if materialMap[key]??>
															<#list materialMap[key]?keys as item>
																<#if item==bm.name()>
																	<#if materialMap[key][item]??>
																		<#list materialMap[key][item] as file>
												                            <li>
												                                <div class="float-left client-gray">文件名：</div>
												                                <div class="float-left success-txt" title="${(file.fileName)!}">${(file.fileName)!}</div>
												                            </li>
												                            <li>
												                                <div class="float-left client-gray">附件说明：</div>
												                                <div class="float-left success-txt" title="${(file.remark)!}">${(file.remark)!}</div>
												                            </li>
												                            <li></li>
												                            <li><a class="blue" href="${mediaserver}imageservice?mediaImageId=${(file.mediaId)!}&mediaType=video" target="_blank">查看</a></li>
																		</#list>
																	</#if>
																</#if>
															</#list>
														</#if>
							                		</#if>
						                		</#list>
						                	</#if>
				                        </ul>
				                    </td>
				                    <td valign="top"></td>
				                    <td></td>
				                </tr>
			                </#list>
		                </tbody>
		            </table>
		        </div>
	        </#if>
        </#list>
    </div>
</form>
</@hb.htmlBase>