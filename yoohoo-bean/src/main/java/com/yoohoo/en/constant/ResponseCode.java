package com.yoohoo.en.constant;

public enum ResponseCode
{

    SUCCESS("0", "SUCCESS"),
    ERROR("500", "系统异常"),
    PARAM_ERROR("600","参数错误"),
    USER_NOT_LOGIN("10000","用户未登录"),
    USER_NOT_EXIST("20000", "用户不存在"),
    PASSWORD_NOT_MATCHING("20001", "密码不匹配"),
    TEACHER_TYPE_NOT_EXIT("20007", "用户类型不匹配"),
    REGISTER_FAILED("20002", "注册失败"),
    VERIFY_CODE_LESS_EFFICACY("20003", "验证码失效"),
    SEND_VERIFICATION_CODE_ERROR("20004","发送验证码失败"),
    SEND_VERIFICATION_CODE_ERROR_MSISDN_IS_REGISTER("20005","该号码已注册，请直接登陆"),
    SEND_VERIFICATION_CODE_ERROR_MSISDN_IS_NOT_REGISTER("20006","该号码未注册，请先注册"),
    CREATE_ROOM_ERROR("30000","创建教室失败"),
    CREATE_ROOM_ERROR_LESSON_NOT_EXISTS("30001","对应的课程不存在"),
    CREATE_ROOM_ERROR_TALK_CREATE_ERROR("30002","调用拓课云创建教室失败"),
    DATE_ERROR("30101","日期有误"),
    CONSUME_RECORD_HAS_SURED("ME10001","消费记录已确认过，不能重复确认！"),
    GET_RECORD_VIDE_ERROR("4007","当前房间号不存在"),
    NO_VIEO("-1","当前课程没有课堂录像");
    private String code;

    private String message;

    ResponseCode(String code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public boolean eq(ResponseCode resp)
    {
    	return this.code.equals(resp.getCode());
    }
    public boolean eq(String code)
    {
    	return this.code.equals(code);
    }
}
