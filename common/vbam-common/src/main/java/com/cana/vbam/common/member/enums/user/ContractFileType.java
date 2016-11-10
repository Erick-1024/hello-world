package com.cana.vbam.common.member.enums.user;

public enum ContractFileType {

   doc("文本文档"); 
   private String desc;
    
    private ContractFileType(String desc){
        this.desc = desc;
    }
    
    public String desc() {
        return desc;
    }
}
