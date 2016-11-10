package com.travelzen.framework.core.dict;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ProductType {
    PATCH("散拼团"),CHARTERFLIGHT("包机"),CUTSIT("包位"), CHARTER_CUTSIT("包机包位"), GP("政府采购"), PAPERFARE("私有运价"), FREETRAVEL("自由行"), NORMAL("普通");
    private String desc;
    private ProductType(String desc){
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public static List<ProductType> getDomesticProductType(){
        return Arrays.asList(CHARTER_CUTSIT, GP, FREETRAVEL, NORMAL);
    }
    public static List<ProductType> getInternationalProductType(){
        List<ProductType>  allInternationalProductType= new ArrayList<ProductType>(4);
        allInternationalProductType.add(PATCH);
        allInternationalProductType.add(CHARTERFLIGHT);
        allInternationalProductType.add(CUTSIT);
        allInternationalProductType.add(PAPERFARE);
        allInternationalProductType.add(FREETRAVEL);
        allInternationalProductType.add(NORMAL);
        return allInternationalProductType;

    }

}
