package com.yoohoo.en.utils;

/**
* @Description 数字处理工具类
* @author suqiaoqiao
* @version 2016年7月12日 下午3:21:02
 */
public class NumberTools
{
	/**
	 * 字符串转换为整型
	 * @param numStr
	 * @return
	 */
	public static int strToInt(String numStr, int defaultNum)
	{
		int result = defaultNum;
		try
		{
			result = Integer.parseInt(numStr);
		}
		catch (Exception e)
		{
			result = defaultNum;
		}
		return result;
	}
	/**
	 * 字符串转换为整型
	 * @param numStr
	 * @return
	 */
	public static Integer strToInteger(String numStr, Integer defaultNum)
    {
        if (numStr == null)
        {
            return defaultNum;
        }
        Integer result = defaultNum;
        try
        {
            result = Integer.parseInt(numStr);
        }
        catch (Exception e)
        {
            result = defaultNum;
        }
        return result;
    }

    public static boolean isEq(Integer bigI, int i)
    {
        if(bigI == null){
            return false;
        }
        return bigI.compareTo(i) == 0;
    }

    public static boolean isNotEq(Integer bigI, int i)
    {
        return !isEq(bigI,  i);
    }
	
}
