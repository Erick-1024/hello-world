package com.cana.bankgate.server.mapper;

import java.util.List;

import com.cana.bankgate.server.po.BankgateTrans;
import com.cana.bankgate.server.po.BankgateTransExample;

public interface BankgateExtraMapper {
  
  /**
   * 通过Id锁一条记录
   * @param id
   * @return
   */
  BankgateTrans lockById(String id);
  
  /**
   * 通过Example锁多条记录。<ba>
   * 注：因为Mysql所记录中的条件必须要带有索引，所以Example中必须带有具有索引的列。<br>
   * 现在id,originId,businessType,businessSeq,bankSeq是有索引的.
   * @param ex
   * @return
   */
  List<BankgateTrans> lockByExample(BankgateTransExample ex);
}