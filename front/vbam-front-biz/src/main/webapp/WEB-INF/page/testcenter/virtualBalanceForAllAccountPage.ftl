<#import "/page/testcenter/common.ftl" as common/>
<@common.htmlBase>
     <form method="post" action="saveVirtualBalanceForAllAccount">
         <table>
             <tr>
                 <td>设置全局随机虚拟余额标志</td>
             </tr>
             <tr>
                 <td>过期时间：<input type="text" name="expireTime"></td>
                 <td><input type="submit" value="确认"></td>
             </tr>
         </table>
     </form>
     <form method="post" action="cancelVirtualBalanceForAllAccount">
         <table>
             <tr>
                 <td>取消设置全局随机虚拟余额标志</td>
             </tr>
             <tr>
                 <td><input type="submit" value="确认"></td>
             </tr>
         </table>
     </form>
     <script>
     	
     </script>
</@common.htmlBase>