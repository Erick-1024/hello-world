<#import "/common/htmlBase.ftl" as hb> 
<@hb.htmlBase title="用款申请" jsFiles=["page/yundaex/loan/loanDetail.js","common/dateutil.js","js/common/yunda.js","common/cana.util.js","common/formValidator.js","common/jquery.multiDownload.js","common/signature.js"] cssFiles=["css/monitor.css","css/yunda.css"] localCssFiles=[] 
curTopMenu = "韵达项目" curSubMenu = "用款申请" removeExtHeader = false removeExtFooter = false>
<div class="main-container">
    <section class="whiteBg">
        <form id="loanInfoForm">
        <div class="bg-box">
        <div class="pro-title"><span class="pro-title-left">基础信息</span><span class="pro-title-right">折叠</span></div>
        <div class="tb-box">
            <table class="check-table">
                <colgroup>
                    <col width="80">
                    <col width="100">
                    <col width="80">
                    <col width="100">
                    <col width="80">
                    <col width="200">
                </colgroup>
                <tbody>
                <tr>
                    <th>用款申请日期</th>
                    <td>${yundaexLoanApplyDTO.applyDate!}</td>
                    <th>站点编号</th>
                    <td>${yundaexLoanApplyDTO.stationNo!}</td>
                    <th>站点名称</th>
                    <td>${yundaexLoanApplyDTO.stationName!}</td>
                </tr>
                <tr>
                    <th>经营地址</th>
                    <td>${yundaexLoanApplyDTO.detailAddress!}</td>
                    <th>法定代表人</th>
                    <td>${yundaexLoanApplyDTO.legalPerson!}</td>
                    <th></th>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="pro-title"><span class="pro-title-left">用款信息</span></div>
        <div class="tb-box" style="">
            <table class="check-table">
                <colgroup>
                    <col width="80">
                    <col width="100">
                    <col width="80">
                    <col width="100">
                    <col width="80">
                    <col width="200">
                </colgroup>
                <tbody>
                <tr>
                    <th>总额度</th>
                    <td>${yundaexLoanApplyDTO.totalLimit!}</td>
                    <th></th>
                    <td></td>
                    <th></th>
                    <td></td>
                </tr>
                <tr>
                    <th>可用额度</th>
                    <td id = "notUsedLimit">${yundaexLoanApplyDTO.notUsedLimit!}</td>
                    <th></th>
                    <td></td>
                    <th></th>
                    <td></td>
                </tr>
                <tr>
                    <th>申请用款金额</th>
                    <td colspan = "2">
	                    <input type="text" value="" id="applyAmt" name ="applyAmt" placeholder="￥" class="cashTxt moneyNum"/>
                    	<span data-for="applyAmt" class="k-invalid-msg"></span>
                    </td>
                    <td></td>
                    <th></th>
                    <td></td>
                </tr>
                <#assign n = 0 />
                <#list yundaexLoanApplyDTO.yundaexProductDTOs as yundaexProductDTO> 
               	<tr>
               		<#if n == 0>
                    	<th>产品类型</th>
                     </#if>
                    <#if n != 0>
                    	<th></th>
                     </#if>
                    <td><label><input type="radio" name="proType" class="input-radio" value=${yundaexProductDTO.id!} <#if n == 0> checked </#if>/><span name='loanPeriod'>${yundaexProductDTO.loanPeriod!}</span>${yundaexProductDTO.loanPeriodUnit!}，${yundaexProductDTO.repaymentType!}</label></td>
                    <th><span class="red-txt">${yundaexProductDTO.interestRateUnit!}利率:${yundaexProductDTO.interestRate!}</span></th>
                    <td></td>
                    <th></th>
                    <td></td>
                </tr>
                 <#assign n = n+1 /> 
				</#list> 
                <tr>
                    <th>放款日</th>
                    <td id = 'loanDate'>${yundaexLoanApplyDTO.loanDate!} </td>
                    <th></th>
                    <td></td>
                    <th></th>
                    <td></td>
                </tr>
                <tr>
                    <th>到期日</th>
                    <td id = 'dueDate'></td>
                    <th></th>
                    <td></td>
                    <th></th>
                    <td></td>
                </tr>
                <tr></tr>
                <tr>
                	<th></th>
                    <td colspan = "3" align="center">
                    	<div style="display:none">
                			<i style="color:#ff0000;" id ="chromeMgs">您当前的谷歌浏览器版本过高，不能使用安全控件，请更换浏览器，建议使用ie浏览器</i>
                		</div>
                        <div style="display:none">
                			<a class="btn-bto-a" href="javascript:void(0);" id="signatureSoftwateInstall">需要安装安全控件</a>
                			<i style="color:#ff0000;">安装完成后需要刷新页面</i>
                		</div>
                		<div style="display:none">
                			<a class="commonBtn-link confirm-btn" href="javascript:void(0);" id="confirmBtn">提交</a>
                    		<font color="red">请在工作日9:00-17:00进行用款申请</font>
                    	</div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    </form>
    </section>
</div>
<!--消息提示框-->
<div class="popup-box" style="display: none;">
    <div class="popup-out-ch"></div>
    <div class="popup-in-ch">
        <!--提示标题-->
        <div class="popup-header">提交结果</div>
        <div class="popup-list-box">
            <div class="popup-content" style="text-align: center;">
                <i class="popup-img notice-icon02"></i><span class="pop-con">您申请的放款申请已成功提交，请查询你的账户余额。</span>
            </div>
            <div class="popup-foot">
                <a class="popup-btn" href="javascript:void(0);" onclick="closeNews();">确定</a>
            </div>
        </div>
    </div>
</div>
<div class="x-box" style="display:none;">
    <div class="popup-out-ch"></div>
    <div class="popup-in-ch">
        <div class="popup-header">提示</div>
        <div class="popup-list-box">
            <div class="popup-content" style="text-align: center;">
                <i class="popup-img notice-icon01"></i><span class="pop-con" style="display:inline-block;">请在工作日9:00-17:00进行用款申请。</span>
            </div>
            <div class="popup-foot">
                <a class="popup-btn" href="javascript:void(0);" onclick="closeBox();">知道了</a>
            </div>
        </div>
    </div>
</div>
</@hb.htmlBase>