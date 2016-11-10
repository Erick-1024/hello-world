package com.cana.member.authorization.exception;

import java.util.Map;


public class MemberException extends RuntimeException {
    private static final long serialVersionUID = 7673986530807260178L;

    private MemberExceptionCode errorCode;
    private Map<String, String> extra;

    public MemberException(MemberExceptionCode errorCode) {
    	this(errorCode, "", null);
    }

    public MemberException(MemberExceptionCode errorCode, String message) {
    	this(errorCode, message, null);
    }

    public MemberException(MemberExceptionCode errorCode, String message, Map<String, String> extra) {
        super(message);
        if(errorCode == null){
        	this.errorCode = MemberExceptionCode.NA;
        }else{
        	this.errorCode = errorCode;
        }
        this.extra = extra;
    }

    public MemberExceptionCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(MemberExceptionCode errorCode) {
        this.errorCode = errorCode;
    }

    public Map<String, String> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, String> extra) {
        this.extra = extra;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
