<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="测试" jsFiles=["page/wechat/imgTest.js","page/wechat/jweixin-1.0.0.js"] cssFiles=["css/login.css"] localCssFiles=[] removeExtHeader = true>
<header class="main-header">
    <div class="stairMenuContent">
        <div class="wrap clearfix">
        </div>
    </div>
</header>
<div class="main-registion">
    <div class="registion-wrap">
        <form method="post" action="register" id="register-form">
	        <div class="registion-content">
	            <table class="registion-tab">
	                <colgroup>
	                    <col width="150px">
	                    <col width="250px">
	                    <col width="260px">
	                    <col width="150px">
	                </colgroup>
	                <tbody>
	                <tr>
	                    <td class="registionRow-til"></td>
	                    <td>
	                    </td>
	                    <td class="tageBox">
	                        <a class="frontage-link" href="javascript:void(0);">上传图片</a>
	                    </td>
	                </tr>
	                </tbody>
	            </table>
	        </div>
        </form>
    </div>
</div>
</@hb.htmlBase>
