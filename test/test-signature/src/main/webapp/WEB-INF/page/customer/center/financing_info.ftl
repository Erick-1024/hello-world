<div class="userCenter-content hidden">
			<#if finance??>
                <table>
                    <colgroup>
                        <col width="250">
                        <col width="250">
                        <col width="200">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td>融资余额</td>
                        <td>
                            <span>${finance.financeBalance!''}</span>
                        </td>
                    </tr>
                    <tr>
                        <td>融资笔数</td>
                        <td>
                            <span>${finance.loanInfoNum!''}</span>
                        </td>
                    </tr>
                    <tr>
                        <td>逾期金额</td>
                        <td>${finance.totalOverdueAmount!''}</td>
                        <td>
                            <a class="userCenter-link" href="javascript:void(0);">详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>逾期笔数</td>
                        <td>
                            <span>${finance.overdueNum!''}</span>
                        </td>
                    </tr>
                    <tr>
                        <td>近7日待还款金额</td>
                        <td>${finance.toPayAmount!''}</td>
                        <td>
                            <a class="userCenter-link" href="javascript:void(0);">详情</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
                </#if>
			<#if coreCompany??>
                <table>
                    <colgroup>
                        <col width="250">
                        <col width="250">
                        <col width="200">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td>融资余额</td>
                        <td>
                            <span>${coreCompany.financeBalance!''}</span>
                        </td>
                    </tr>
                    <tr>
                        <td>融资笔数</td>
                        <td>
                            <span>${coreCompany.loanInfoNum!''}</span>
                        </td>
                    </tr>
                    <tr>
                        <td>逾期金额</td>
                        <td>${coreCompany.totalOverdueAmount!''}</td>
                        <td>
                            <a class="userCenter-link" href="javascript:void(0);">详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>逾期笔数</td>
                        <td>
                            <span>${coreCompany.overdueNum!''}</span>
                        </td>
                    </tr>
                    <tr>
                        <td>近7日待还款金额</td>
                        <td>${coreCompany.toPayAmount!''}</td>
                        <td>
                            <a class="userCenter-link" href="javascript:void(0);">详情</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
                </#if>
                <#if factor??>
                <table>
                    <colgroup>
                        <col width="250">
                        <col width="250">
                        <col width="200">
                    </colgroup>
                    <tbody>
                    <tr>
                        <td>放款金额</td>
                        <td>
                            <span>${factor.financeBalance!''}</span>
                        </td>
                    </tr>
                    <tr>
                        <td>放款笔数</td>
                        <td>
                            <span>${factor.loanInfoNum!''}</span>
                        </td>
                    </tr>
                    <tr>
                        <td>逾期金额</td>
                        <td>${factor.totalOverdueAmount!''}</td>
                        <td>
                            <a class="userCenter-link" href="javascript:void(0);">详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>逾期笔数</td>
                        <td>
                            <span>${factor.overdueNum!''}</span>
                        </td>
                        <td>
                            <a class="userCenter-link" href="javascript:void(0);">详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>逾期客户名单</td>
                        <td></td>
                        <td>
                            <a class="userCenter-link" href="javascript:void(0);">详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>近1个月待回款列表</td>
                        <td></td>
                        <td>
                            <a class="userCenter-link" href="javascript:void(0);">详情</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
                </#if>
            </div>