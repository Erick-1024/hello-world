<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="档案管理" jsFiles=["page/asset/archivesmanagement/manage.js", "common/cana.util.js", "common/init.js", "common/dateutil.js"] cssFiles=["css/monitor.css","css/project.css"] localCssFiles=[] 
curTopMenu = "证券化发行管理" curSubMenu = "档案管理" removeExtHeader = false removeExtFooter = false>

<div class="pro-bg" style="background: #fff;min-width: 1200px;">
    <div class="pro-box-bg">
        <form>
            <div class="d-file-title">专项计划信息</div>
            <table class="client-tb" style="padding:5px 0 15px 0;border-bottom: 3px solid #F1F1F1;">
                <colgroup>
                    <col width="120px">
                    <col width="200px">
                    <col width="120px">
                    <col width="200px">
                    <col width="120px">
                    <col width="100px">
                    <col width="100px">
                    <col width="">
                </colgroup>
                <tbody>
                <tr>
                    <th>专项计划编号</th>
                    <td id='id'>${id}</td>
                    <th>专项计划名称</th>
                    <td>${specialProgramName}</td>
                    <th>基础资产类型</th>
                    <td>${underlyingAssetTypeDesc}</td>
                    <th>状态</th>
                    <td status="${status.name()}">${status.desc()}</td>
                </tr>
                </tbody>
            </table>
            <div class="" style="padding:10px 0;">
            	<#if status.name() == 'CREATE'>
	            	<#if authorizeKey("SIM_AM_UPLOAD")>
		            	<div class="import-up">
		                    <input type="file" id="photos" class="input-file" name="file" style="top:0;width:100px;z-index:10;">
		                    <a class="default-link confirm-link" id="upload-a-btn" href="javascript:void(0);" style="position:absolute;left:0;top:0;background:#14ade9;">上传文件</a>
		                </div>
		            </#if>
		            <#if authorizeKey("SIM_AM_DIRECTORY")>
		            	<a class="form-search-link new-folder" href="javascript:void(0);">新建文件夹</a>
		            </#if>
		        </#if>
                <#if authorizeKey("SIM_AM_DOWNLOAD")>
                	<a class="form-search-link download-all" href="javascript:void(0);">下载全部</a>
                </#if>
            </div>
            <div class="d-file-nav">
                <span class="first-allPage">全部文件</span>
                <ul class="navPageBox" style="display:none;">
                    <li class="navPageOne">
                        <a class="upPage d-link-text" href="javascript:void(0);">返回上一级</a>
                        <span class="midden-line">|</span>
                        <a class="allPage d-link-text" href="javascript:void(0);" data-id="">全部文件</a>
                    </li>
                </ul>
            </div>
            <div class="">
                <table class="file-table" style="font-size:13px;">
                    <colgroup>
                        <col width="400">
                        <col width="200">
                        <col width="200">
                        <col width="200">
                    </colgroup>
                    <thead>
                    <tr class="file-thead">
                        <td>文件名</td>
                        <td>大小</td>
                        <td>修改日期</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody class="file-inBox">
                    </tbody>
                </table>
            </div>
        </form>
    </div>
</div>

<#include '../../tipBoxTemplate.ftl'/>
<#include '../../confirmBoxTemplate.ftl'/>

<!--权限配置-->
<script>
	var sim_am_delete = ${authorizeKey("SIM_AM_DELETE")?string("true","false")};
	var sim_am_download = ${authorizeKey("SIM_AM_DOWNLOAD")?string("true","false")};
</script>

</@hb.htmlBase>