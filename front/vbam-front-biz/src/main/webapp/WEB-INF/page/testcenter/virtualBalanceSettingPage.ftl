<#import "/page/testcenter/common.ftl" as common/>
<@common.htmlBase>
     <form method="post" action="saveBalance">
             <table>
                     <tr>
                             <td>账号：</td>
                             <td><input type="text" name="accountNo" value="${accountNo!}"></td>
                     </tr>
                     <tr>
                             <td>余额(元)：</td>
                             <td><input type="text" name="balance" value="${balance!}"></td>
                     </tr>
                     <tr>
                             <td>有效期(小时，最多和默认都是72)：</td>
                             <td><input type="text" name="expireTime" value="${expireTime!}"></td>
                     </tr>
                     <tr>
                             <td><input type="submit" value="确认"></td>
                     </tr>
             </table>
     </form>
     <script>
     	$('input[name="accountNo"]').on('change',function(e) { $('input[name="accountNo"]').val($('input[name="accountNo"]').val().replace(/ /g,'') ); })
     </script>
</@common.htmlBase>