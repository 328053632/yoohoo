package com.yoohoo.en.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 字符串工具类
 */
public class StringUtil
{
    public final static String PORTAL_CLIENT = "client";
    
    public final static String PORTAL_WEB = "web";
    
    public final static String TRUE = "1";
    
    public final static String FALSE = "0";
    
    /**
     * Logger for this class
     */
    private static final Logger log = Logger.getLogger(StringUtil.class);
    
    private StringUtil()
    {
        
    }
    
    /**
     * 若null对象或是空白字符，返回默认值，否则其trim结果
     * 
     * @param str
     * @return [参数说明]
     */
    public static String getTrim(String str, String def)
    {
        if (str == null)
        {
            return def;
        }
        
        String t = str.trim();
        return (t.length() == 0) ? def : t;
    }
    
    /**
     * 若null对象或是空白字符，返回默认值，否则其trim结果
     * 
     * @param str
     * @return [参数说明]
     */
    public static String defIfEmpty(String str, String def)
    {
        String s = str;
        if ((str == null) || ((s = str.trim()).length() == 0))
        {
            return def;
        }
        return s;
    }
    
    /**
     * 允许null对象的trim方法
     * 
     * @param str
     * @return [参数说明]
     */
    public static String trim(String str)
    {
        return str == null ? null : str.trim();
    }
    
    /**
     * 判断字符串是否为null对象或是空白字符
     */
    public static boolean isEmpty(String str)
    {
        return ((str == null) || (str.trim().length() == 0));
    }
    
    /**
     * 判断字符串是否bu为null对象或是空白字符
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }
    
    /**
     * 判断两个字符串是否相等
     */
    public static boolean isEq(String str, String other)
    {
        if (str == null)
        {
            return other == null;
        }
        return str.equals(other);
    }
    
    /**
     * 判断两个字符串是否不相等
     */
    public static boolean isNotEq(String str, String other)
    {
        return !isEq(str, other);
    }
    
    // modified by coder at 2011-12-16 for IRD-21463 begin
    
    /**
     * 判断字符串和整数是否在字符串上相等
     */
    public static boolean isEq(String str, int other)
    {
        return String.valueOf(other).equals(str);
    }
    
    /**
     * 判断字符串和整数是否在字符串上相等
     */
    public static boolean isEq(String str, long other)
    {
        return String.valueOf(other).equals(str);
    }
    
    /**
     * 判断字符串和整数是否在字符串上不相等
     */
    public static boolean isNotEq(String str, int other)
    {
        return !isEq(str, other);
    }
    
    /**
     * 判断字符串和整数是否在字符串上不相等
     */
    public static boolean isNotEq(String str, long other)
    {
        return !isEq(str, other);
    }
    
    // modified by coder at 2011-12-16 for IRD-21463 end
    
    /**
     * 判断字符串和整数是否在字符串上相等
     */
    public static boolean isEq(int i, String str)
    {
        return String.valueOf(i).equals(str);
    }
    
    /**
     * 判断字符串和整数是否在字符串上不相等
     */
    public static boolean isNotEq(int i, String str)
    {
        return !isEq(i, str);
    }
    
    /**
     * 判断该字符串是否与后面某个整型参数在字符串上相等
     */
    public static boolean matchs(String base, int... matched)
    {
        int b;
        try
        {
            b = Integer.parseInt(base);
        }
        catch (Exception e)
        {
            return false;
        }
        
        for (int i = 0; i < matched.length; i++)
        {
            if (b == matched[i])
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 判断 该整数是否与后面的某个整数是否相等
     * 
     * @param base
     * @param matched
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean matchs(int base, int... matched)
    {
        for (int i = 0; i < matched.length; i++)
        {
            if (base == matched[i])
            {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 判断字符串是否与其后面某个参数相等
     */
    public static boolean matchs(String base, String... matched)
    {
        for (int i = 0; i < matched.length; i++)
        {
            if (matched[i].equals(base))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 将boolean型转换为字符串，true：1，false：0
     */
    public static String valueOf(boolean b)
    {
        return b ? TRUE : FALSE;
    }
    
    /**
     * 对象转换为String，如果对象为null，则会返回null对象，而不是返回字符串"null"。
     */
    public static String valueOf(Object o)
    {
        return (o == null) ? null : o.toString();
    }
    
    /**
     * 将int型转换为字符串，
     */
    public static String valueOf(int i)
    {
        return String.valueOf(i);
    }
    
    /**
     * 将long型转换为字符串
     */
    public static String valueOf(long l)
    {
        return String.valueOf(l);
    }
    
    /**
     * 将double型转换为字符串
     */
    public static String valueOf(double d)
    {
        return String.valueOf(d);
    }
    
    /**
     * 字符串转化整形
     * 
     * @param val
     * @param def 默认值
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static int toIntValue(String val, int def)
    {
        val = StringUtil.trim(val);
        if (StringUtil.isEmpty(val))
        {
            return def;
        }
        
        int value;
        try
        {
            value = Integer.parseInt(val);
        }
        catch (Exception e)
        {
            value = def;
            log.error("parseInt error");
        }
        
        return value;
    }
    
    /**
     * 获取正整数
     * 
     * @author coder
     * @param val 待转化为整数的字符串
     * @param def 缺省值
     * @return 返回转换后的整数值，为正整数，如果为0也返回缺省值
     */
    public static int toPositiveInt(String val, int def)
    {
        int iRst = Math.abs(def);
        String tmpVal = StringUtil.trim(val);
        if (StringUtil.isEmpty(tmpVal))
        {
            return iRst;
        }
        
        int value;
        try
        {
            value = Integer.parseInt(val);
        }
        catch (Exception e)
        {
            log.warn("Number format error");
            return iRst;
        }
        
        if (value == 0)
        {
            return iRst;
        }
        else
        {
            return Math.abs(value);
        }
    }
    
    /**
     * 字符串转化整形
     * 
     * @param val
     * @param def 默认值
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static long toLongValue(String val, long def)
    {
        if (StringUtil.isEmpty(val))
        {
            return def;
        }
        
        String tmp = StringUtil.trim(val);
        long value;
        try
        {
            value = Long.parseLong(tmp);
        }
        catch (Exception e)
        {
            value = def;
            log.error("parseInt error");
        }
        
        return value;
    }
    
    /**
     * 转换为float值
     * 
     * @param val 待转换的String
     * @param def 转换失败时返回的默认值
     * @return 返回转换后的值
     */
    public static float toFloatValue(String val, float def)
    {
        float value = def;
        try
        {
            value = Float.parseFloat(val);
        }
        catch (Exception e)
        {
            log.error("parseFloat error");
        }
        return value;
    }
    
    /**
     * 去除字符串外边的双引号，包括转义的双引号&quot;
     * 
     * @param str
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String trimQuot(String str)
    {
        if (StringUtil.isEmpty(str))
        {
            return str;
        }
        
        String quot = "\"";
        if (str.startsWith(quot) && str.endsWith(quot))
        {
            str = str.replace(quot, "");
            return str.trim();
        }
        
        String escapeQuot = "&quot;";
        if (str.startsWith(escapeQuot) && str.endsWith(escapeQuot))
        {
            str = str.replace(escapeQuot, "");
            return str.trim();
        }
        
        return str;
    }
    
    /**
     * 把搜索书籍类型字符串转义成SQL语句的字符串 <功能详细描述>
     * 
     * @param str
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getBookItemType(String str)
    {
        String[] temp = str.split("\\;");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < temp.length; i++)
        {
            if (i < temp.length - 1)
            {
                sb.append("'" + temp[i] + "',");
            }
            else
            {
                sb.append("'" + temp[i] + "'");
            }
        }
        
        return sb.toString();
    }
    
    /**
     * 
     * 将按';'分割开的字符串拆分，并放到数组中
     * 
     * @author zKF42393(zhangpy)
     * @param str
     * @return
     */
    public static Set<String> parseStrToSet(String str)
    {
        Set<String> strSet = new HashSet<String>();
        String[] temp = str.split("\\;");
        for (int i = 0; i < temp.length; i++)
        {
            strSet.add(temp[i]);
        }
        return strSet;
    }
    
    /**
     * 
     * 按指定格式将字符串拆分，并放到列表中
     * 
     * @author 万明亮
     * @param source 待分割的字符串
     * @param seperator 分隔符
     * @return 分割好的字符串列表
     */
    public static List<String> parseStrToList(String source, String seperator)
    {
        // 参数检查
        if (source == null || seperator == null)
        {
            return null;
        }
        
        // 执行分拆
        String[] temp = source.split("\\" + seperator);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < temp.length; i++)
        {
            list.add(temp[i]);
        }
        
        return list;
    }
    
    /**
     * 
     * 将列表用指定的符号组装成String
     * 
     * @author kf56385
     * @param source 待组装的列表
     * @param seperator 分隔符
     * @return 组装后的字符串
     */
    public static String parseListToStrBySeperator(List<String> source, String seperator)
    {
        String result = "";
        // 参数检查
        if (isEmpty(source) || source.size() == 0)
        {
            return result;
        }
        
        // 执行组装
        for (int i = 0; i < source.size(); i++)
        {
            String str = source.get(i);
            
            if (i == source.size() - 1)
            {
                result += str;
            }
            else
            {
                result += str + seperator;
            }
            
        }
        
        return result;
    }
    
    /**
     * 用指定的分隔符号拆分字符串
     * 
     * @author r00138849
     * @param srcStr 待拆分的字符串
     * @param sep 分隔符号
     * @return 返回拆分后的字符串列表
     */
    public static List<String> splitString(String srcStr, String sep)
    {
        List<String> list = new ArrayList<String>();
        if (!isEmpty(srcStr))
        {
            String[] temp = srcStr.split(sep);
            for (int i = 0; i < temp.length; i++)
            {
                list.add(temp[i]);
            }
        }
        return list;
    }
    
    /**
     * 返回初步解析的数组 <功能详细描述>
     * 
     * @param str
     * @return [参数说明]
     * 
     * @return Map<String,String> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String[] getBookClassArray(String str)
    {
        // Modify by zkf46428 at 2012-1-10 for IRD-22162 begin
        if (StringUtil.isEmpty(str))
        {
            return null;
        }
        
        // Map<String, String> bookClassMap = new HashMap<String, String>();
        String[] bookClassArray = str.split("\\;");
        // for (int i = 0; i < bookClassArray.length; i++)
        // {
        // String[] key_value = bookClassArray[i].split("\\:");
        // bookClassMap.put(key_value[0], key_value[1]);
        // }
        
        return bookClassArray;
        // Modify by zkf46428 at 2012-1-10 for IRD-22162 end
    }
    
    /**
     * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位
     * 
     * @author patriotlml
     * @param String origin, 原始字符串
     * @param int len, 截取长度(一个汉字长度按2算的)
     * @return String, 返回的字符串
     */
    public static String subChinseseStr(String origin, int len)
    {
        if (origin == null || origin.equals("") || len < 1)
            return "";
        byte[] strByte = new byte[len];
        if (len > length(origin))
        {
            return origin;
        }
        System.arraycopy(origin.getBytes(), 0, strByte, 0, len);
        int count = 0;
        for (int i = 0; i < len; i++)
        {
            int value = (int)strByte[i];
            if (value < 0)
            {
                count++;
            }
        }
        if (count % 2 != 0)
        {
            len = (len == 1) ? ++len : --len;
        }
        return new String(strByte, 0, len);
    }
    
    /*
     * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
     */
    public static boolean isLetter(char c)
    {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }
    
    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     * 
     * @param String s ,需要得到长度的字符串
     * @return int, 得到的字符串长度
     */
    public static int length(String s)
    {
        if (s == null)
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++)
        {
            len++;
            if (!isLetter(c[i]))
            {
                len++;
            }
        }
        return len;
    }
    
    /**
     * 计算字符串的字符长度（中文、英文均按一个字符计算）<br>
     * 如："中文abc" 长度为 5
     */
    public static int lengthc(String s)
    {
        if (s == null)
        {
            return 0;
        }
        return s.length();
    }
    
    /**
     * 密码加密
     * 
     * @param srcStr
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    
    public static String judgeIsSha(String srcStr)
    {
        if (StringUtils.isEmpty(srcStr))
        {
            return "";
        }
        else if (srcStr.length() < 35)
        {
            return DigestUtils.shaHex(srcStr);
        }
        else
        {
            return srcStr;
        }
    }
    
    /**
     * 判断是否含有中文
     * 
     * @param str
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isContainsChinese(String str)
    {
        boolean flg = false;
        if (null == str || "".equals(str))
        {
            return flg;
        }
        String regEx = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(regEx);
        Matcher matcher = pat.matcher(str);
        
        if (matcher.find())
        {
            flg = true;
        }
        return flg;
    }
    
    /**
     * 判断指定的对象是否为空
     * 
     * @param obj
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isEmpty(Object obj)
    {
        if (null == obj)
        {
            return true;
        }
        return false;
    }
    
    /**
     * 
     * 判断指定的对象是不为空
     * 
     * @author zKF47558
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj)
    {
        return !isEmpty(obj);
    }
    
    /**
     * 
     * 判断字符串是否为数字
     * 
     * @author c59623
     * @param str
     * @param exceptionCode
     * @return boolean
     */
    public static boolean assertNum(String str)
    {
        boolean flag = true;
        Pattern pattern = Pattern.compile("([-]{0,1})[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches())
        {
            flag = false;
        }
        return flag;
    }
    
    /**
     * 
     * 获取一个string的字节长度
     * 
     * @author kf56385
     * @param s
     * @return
     */
    public static int getLength(String s)
    {
        if (s == null)
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++)
        {
            len++;
            if (!isLetter(c[i]))
            {
                len++;
            }
        }
        return len;
    }
    
    /**
     * 判断是否在数组中 <功能详细描述>
     * 
     * @param s
     * @param array
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isInArray(String s, String[] array)
    {
        boolean b = false;
        s = (null == s) ? "" : s;
        if (array != null && array.length > 0)
        {
            for (int i = 0; i < array.length; i++)
            {
                if (s.equals(array[i]))
                {
                    b = true;
                    break;
                }
            }
        }
        return b;
        
    }
    
    /**
     * 
     * 获取字符串的长度（一个字母一个字节，一个汉字算两个字节）
     * 
     * @author hKF75327
     * @param str
     * @return 字符串的长度
     */
    public static int getStrLenth(String str)
    {
        if (null == str || "".equals(str.trim()))
        {
            return 0;
        }
        int strLength = 0;
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) > 255)
            {
                strLength += 2;
            }
            else
            {
                strLength += 1;
            }
        }
        return strLength;
    }
    
    /**
     * 判断指定的字符串数组是否为空或长度为0 <功能详细描述>
     * 
     * @param strArr 字符串数组
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isEmpty(String[] strArr)
    {
        if ((null == strArr) || (strArr.length < 1))
        {
            return true;
        }
        return false;
    }
    
    /**
     * 判断指定的字符串数组是否不为空 <功能详细描述>
     * 
     * @param strArr 字符串数组
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNotEmpty(String[] strArr)
    {
        return !isEmpty(strArr);
    }
    
    public static String nvl(String str)
    {
        if (str == null)
        {
            return "";
        }
        return str.trim();
    }
    
    public static String cutStrByLength(String str, Integer length)
    {
        if (StringUtils.isEmpty(str))
        {
            return "";
        }
        
        if (null == length || 0 == length)
        {
            return str;
        }
        
        if (str.length() > length)
        {
            return str.substring(0, length);
        }
        
        return str;
    }
    
    public static String getWebSocketSessionMemcachedKey(String sessionID, String portalType)
    {
        return sessionID + "-" + IpUtil.getLocalIP() + "-" + portalType;
    }
    
    public static String removeHtmlTag(String content)
    {
        content = content.replaceAll("<p.*?>", "");
        content = content.replaceAll("<br.*?>", "");
        content = content.replaceAll("</p.*?>", "");
        content = content.replaceAll("</br.*?>", "");
        return content;
    }
    
    public static Integer toInteger(String val)
    {
        if (isEmpty(val))
        {
            return null;
        }
        try
        {
            return Integer.parseInt(val);
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    public static String getIRealIPAddr(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
