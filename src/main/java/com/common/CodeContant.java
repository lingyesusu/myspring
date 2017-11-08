package com.common;

/**
 * 带回前端的访问回执
 */
public enum CodeContant {
     
     AJAX_SUCCESS(MessageContant.SUCCESS, "200"), 
     AJAX_FAIL(MessageContant.FAIL, "400"),
     AJAX_LOGINSUCCESS(MessageContant.LOGINSUCCESS, "200"), 
     AJAX_LOGINFAIL(MessageContant.LOGINFAIL, "400"),
     AJAX_NONEERROR(MessageContant.NONEERROR, "400"), 
     AJAX_MESSAGEERROR(MessageContant.MESSAGEERROR, "400"),
     AJAX_SAMPLENOERROR(MessageContant.SAMPLENOERROR, "400"),
     AJAX_STYLECODEERROR(MessageContant.STYLECODEERROR, "400"),
     AJAX_REGERROR(MessageContant.REGERROR, "400");
     
    private String msg ;
    private String code ;
     
    private CodeContant( String msg , String code ){
        this.msg = msg ;
        this.code = code ;
    }
     
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
 