package com.yoohoo.en.constant;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author jaxer
 * @version [版本号, 2016年7月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ApplicationConstant
{
    public static final String SESSION_VALIDATECODE = "validate-code";
    
   // public static final long LESSON_OPEN_BEFORE_TEACHER = 20*60 * 1000L;

	//课时确认时间
	public static final long LESSON_CONFIRM_AFTER_CONSUME=60*1000L;

	//上课老师进入教室时间
    public static final long LESSON_OPEN_BEFORE_TEACHER = 10*60 * 1000L;

    //教室过期时间
    public static final long LESSON_OPEN_AFTER_TEACHER = 60 * 1000L;

    // public static final long LESSON_OPEN_BEFORE_STUDENT = 20*60 * 1000L ;

	//学生进入教室时间
	public static final long LESSON_OPEN_BEFORE_STUDENT =  10*60 * 1000L ;
    /**
     * 登录用户信息session键
     */
    public static final String LOGIN_STUDENT_TAG = "LOGIN_STUDENT";

    public static final String LOGIN_TEACHER_TAG = "LOGIN_TEACHER";

    public static final int USER_STATUS_DEL = 0;

    public static final int USER_STATUS_NORMAL = 1;

    /**
     * 验证码类型: 注册
     */
    public static final int VERIFY_CODE_TYPE_REGISTER = 1;

    /**
     * 验证码类型: 修改密码
     */
    public static final int VERIFY_CODE_TYPE_MODIFY = 2;

    /**
     * 消息状态: 未读
     */
    public static final int MESSAGE_STATUS_NOT_READ = 0;

    /**
     * 消息状态: 已读
     */
    public static final int MESSAGE_STATUS_READ = 1;

    /**
     * 消息状态: 已删除
     */
    public static final int MESSAGE_STATUS_DELETE = 2;

    /**
     * 消息类型: 试听
     */
    public static final int MESSAGE_TYPE_AUDITION = 4;

    /**
     * 消息类型: 评测
     */
    public static final int MESSAGE_TYPE_EVALUATING = 5;

    /**
     * 消息类型: 请假 24 小时内
     */
    public static final int MESSAGE_TYPE_LEAVE = 2;

	/**
	 * 消息类型: 请假 超过24小时
	 */
    public static final int MESSAGE_TYPE_LEAVE_2 = 7;

    /**
     * 消息类型: 补课
     */
    public static final int MESSAGE_TYPE_REMEDIATION = 6;


	/**
	 * 申请更换时间
	 */
	public static final int CHANGE_TIME = 7;

    /**
     * 消息类型: 旁听
     */
    public static final int MESSAGE_TYPE_AUDIT = 3;

    /**
     * 消息类型: 插班
     */
    public static final int MESSAGE_TYPE_INSERTION = 8;
    

    /**
     * 发送短信配置key值
     */
    public static final String SMS_CONFIG_KEY = "sms-param";

    /**
     * 试听课时间段key值
     */
    public static final String LESSON_TIME_KEY = "lesson-time";

    /**
     * 用户类型: 老师
     */
    public static final int USER_TYPE_TEACHER = 1;

    /**
     * 用户类型: 学生
     */
    public static final int USER_TYPE_STUDENT = 2;

    /**
     * 用户类型: 系统
     */
    public static final int USER_TYPE_SYSTEM = 3;
    public static final String TALK_API_RESULT_SUCCESS="0";
    public static final String TALK_CONF_KEY_API_URL = "talk-api-url";
    public static final String TALK_CONF_KEY_AUTHKEY ="talk-authkey";
    public static final String TALK_CONF_KEY_DOMAIN ="talk-domain";
    public static final String TALK_CONF_KEY_ASSISTANT_PAWD ="talk-assistant_passwd";
    public static final String TALK_LESSON_FINLISHED_BACKURL="lesson-finlished-backurl";


	/**
	 * 试听课程
	 */
	public static final int LESSON_TYPE_AUDITION = 1;

	/**
	 * 付费课程
	 */
	public static final int LESSON_TYPE_FU_FEI = 0;

    public enum UserStatus{
    	
    	NORMAL(1),DELETED(0);
    	
    	private int code;
    	
    	private UserStatus(int code)
    	{
    		this.code=code;
    	}
    	public int getValue()
    	{
    		return this.code;
    	}
    	public boolean eq(int code)
    	{
    		return Integer.compare(this.code, code)==0;
    	}
    }
    
    public enum BlanceType{
    	
    	RECHARGE(1),CONSUME(2);
    	
    	private int code;
    	
    	private BlanceType(int code)
    	{
    		this.code=code;
    	}
    	public int getValue()
    	{
    		return this.code;
    	}
    	public boolean eq(int code)
    	{
    		return Integer.compare(this.code, code)==0;
    	}
    }
    
    public enum BusinessType{
    	
    	LESSON(1),LEAVE(2),ABSENTEEISM(3);
    	
    	private int code;
    	
    	private BusinessType(int code)
    	{
    		this.code=code;
    	}
    	public int getValue()
    	{
    		return this.code;
    	}
    	public boolean eq(int code)
    	{
    		return Integer.compare(this.code, code)==0;
    	}
    }

    
    public enum BlacneLogStatus{
    	
    	WAIT(0),SURE(1);
    	
    	private int code;
    	
    	private BlacneLogStatus(int code)
    	{
    		this.code=code;
    	}
    	public int getValue()
    	{
    		return this.code;
    	}
    	public boolean eq(int code)
    	{
    		return Integer.compare(this.code, code)==0;
    	}
    }
    
    public enum CheckStatus{
    	
    	NOTCHECK(1),CHECKED(2);
    	
    	private int code;
    	
    	private CheckStatus(int code)
    	{
    		this.code=code;
    	}
    	public int getValue()
    	{
    		return this.code;
    	}
    	public boolean eq(int code)
    	{
    		return Integer.compare(this.code, code)==0;
    	}
    }
}
