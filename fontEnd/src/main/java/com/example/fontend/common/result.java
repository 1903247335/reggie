package com.example.fontend.common;

public class result {
    private int code;
    private String message;
    private Object data;

    public result() {
    }

    public result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 获取
     * @return code
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }
    public static result success(Object data){
        return new result(1,"success",data);
    }
    public static result success(){
        return new result(1,"success",null);
    }
    public static result error(String message){
        return new result(0,message,null);
    }
    public static result error(){
        return new result(0,"error",null);
    }
    /**
     * 获取
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取
     * @return data
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置
     * @param data
     */
    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        return "result{code = " + code + ", message = " + message + ", data = " + data + "}";
    }
}
