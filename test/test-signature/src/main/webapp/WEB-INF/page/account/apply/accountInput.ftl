<div class="applyAut-wrap02 hidden">
	<div class="accountList-content">
		<table class="applyAut-table" style="margin: 0 150px;">
			<colgroup>
				<col width="30%">
				<col width="30%">
				<col width="40%">
			</colgroup>
			<tbody>
			    <#-- labels定义在每个includ该页面的父页面 -->
			    <#list labels as label >
			        <tr class="special-view hidden">
					<td class="applyAut-table-til"><span class="redStar">*</span>${label[0][0]}</td>
					<td>
						<div class="radioBox radioBox0${label_index+1}">
							<label class="radio active" data-val="${label[1][1]}"> 
                                <span class="radioIcon"></span>
								<span class="labelFonts">${label[1][0]}</span>
							</label> 
                            <label class="radio" data-val="${label[2][1]}">
                                <span class="radioIcon"></span> 
                                <span class="labelFonts">${label[2][0]}</span>
							</label>
							<#if label[0][1]!="">
							<span class="hidden">
							<input type="text" name="${label[0][1]}" value="${label[1][1]}"/>
							</span>
							</#if>
						</div>
					</td>
					<#if label[0][1] == "supervisorType">
					<td rowspan="4"><span class="radioLabel" style="position: relative; color:red;font-size:10px;margin:0px auto 80px -10px;">提示：开立“专用账户”时，您需选择某未被监管的一般账户对专用账户进行监管，每日资金将归集至该账户中</span></td>
					<#else>
					<td></td>
					</#if>
				</tr>
				<#if label[0][0] == "选择监管账户" >
				<tr class="special-account hidden">
				    <td class="applyAut-table-til"><span class="redStar">*</span>银行账号</td>
				    <td>
				        <input type="text" class="bankCard" id="supervisorAccountNo" name="supervisorAccountNo" placeholder="银行账号"
				        	style="width:180px;" />
				        <span data-for="supervisorAccountNo" class="k-invalid-msg"></span>
				    </td>
				    <td></td>
				</tr>
				<tr class="special-account hidden">
				    <td class="applyAut-table-til"><span class="redStar">*</span>账户名称</td>
				    <td><span id="accountName"></span></td>
				    <td></td>
				</tr>
				</#if>
			    </#list> 
				<tr class="account-cnt">
					<td class="applyAut-table-til"><span class="redStar">*</span>申请账户数</td>
					<td>
					<select id="accountNumber" name="accountNumber" class="selectWrap" data-role="dropdownlist">
					<#list 1..accountMaxNumber as i >
						<option value="${i}">${i}</option>
					</#list>
						</select>
					</td>
					<td></td>
				</tr>
				<tr class="account-expt01 buyer-names hidden">
					<td class="applyAut-table-til"></td>
					<td  colspan="2">
						<p class="applyAut-notice" style="color:red;font-size:10px;">提示：为确保准确扣款，“买方企业全称”请填写完整，请勿简写，系统将根据买方企业全称创建对应的银行账户</p>
						<div style="margin-top: 20px; margin-bottom: 10px;">
							<a class="accountList-link add-account"
								href="javascript:void(0);">新增</a>
						</div>
						<table id="audit-thead">
							<colgroup>
								<col width="80">
								<col width="200">
								<col width="100">
								<col width="17">
							</colgroup>
							<thead>
								<tr>
									<th>序号</th>
									<th>买方企业名称</th>
									<th>操作</th>
									<th></th>
								</tr>
							</thead>
						</table>
						<div class="applyAut-auditTab">
							<table id="acount-table">
								<colgroup>
									<col width="80px">
									<col width="200px">
									<col width="100px">
								</colgroup>
								<tbody>
									
								</tbody>
							</table>
						</div>
					</td>
				</tr>
				<tr class="account-expt02 buyer-names hidden">
					<td class="applyAut-table-til"></td>
					<td colspan="2"><!-- 注：为确保扣款准确，“买方企业全称“请使用全称，请勿简写系统会根据买方企业创建一一对应的银行账户 -->
						<p class="applyAut-notice" style="color:red;font-size:10px;">提示：为确保准确扣款，“买方企业全称”请填写完整，请勿简写，系统将根据买方企业全称创建对应的银行账户</p>
						<div style="margin-top: 20px; margin-bottom: 10px;">
							<label class="excelBox">
								<input type="file" class="frontage excel" name="excel" id="file9" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
                            	<a class="frontage-link accountList-link add-account import-excel" href="javascript:void(0);">Excel导入</a>
                            </label>
							<a class="accountList-link export-account" href="${mediaserver}imageservice?mediaImageId=${excelTemplateId}&mediaName=template.xls&mediaType=download">Excel模板</a>
						</div>
						<table id="audit-thead01">
							<colgroup>
								<col width="80">
								<col width="200">
								<col width="100">
								<col width="17">
							</colgroup>
							<thead>
								<tr>
									<th>序号</th>
									<th>买方企业名称</th>
									<th>操作</th>
									<th></th>
								</tr>
							</thead>
						</table>
						<div class="applyAut-auditTab">
							<table id="acount-table01">
								<colgroup>
									<col width="80px">
									<col width="200px">
									<col width="100px">
								</colgroup>
								<tbody>
									
								</tbody>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td class="applyAut-table-til"></td>
					<td style="padding-top: 30px;" colspan="2"><a id="submit"
						class="default-link confirm-link createAccount" href="javascript:void(0);">立即开户</a>
						<a class="default-link confirm-link applyAut-back"
						href="javascript:void(0);">返回</a></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
