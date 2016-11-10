<#import "/page/testcenter/common.ftl" as common/>
<@common.htmlBase>
     <form method="post" action="saveWithdrawState">
         <table>
             <tr>
                 <td>业务流水号：</td>
                 <td><input type="text" name="businessSeq"></td>
             </tr>
             <tr>
                 <td>提现状态：</td>
                 <td>
                 	<select name="state">
                 		<option value="fail">失败</option>
                 		<option value="success">成功</option>
                 	</select>
                 </td>
             </tr>
             <tr>
                 <td><input type="submit" value="确认"></td>
             </tr>
         </table>
     </form>
     <script>
     	
     </script>
</@common.htmlBase>