package com.cana.account.server.test.enumtest;

public enum EnumExample {

    Name1,
    Name2;
    
    private String reason;
    public String getReason() {
        return Name1.reason;
    }
    public void setReason(String reason) {
        Name1.reason = reason;
    }
}
