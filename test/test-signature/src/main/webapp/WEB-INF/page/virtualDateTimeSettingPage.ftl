
     <form method="get" action="saveDatetime">
             <table>
                     <tr>
                             <td>日期（yyyy-MM-dd）：</td>
                             <td><input type="text", name="virtualDate", value="${virtualDate!}"></td>
                     </tr>
                     <tr>
                             <td>时偏移：</td>
                             <td><input type="text", name="hourOffset", value="${hourOffset!}"></td>
                     </tr>
                     <tr>
                             <td><input type="submit" value="确认"></td>
                     </tr>
             </table>
     </form>
