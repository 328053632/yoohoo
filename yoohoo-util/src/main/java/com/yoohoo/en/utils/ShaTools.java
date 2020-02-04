/*
 * 文件名：ShaTools.java
 * 版权：Copyright 2005-2006 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：该类用于保存本系统用到的常用工具方法
 */

package com.yoohoo.en.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.security.DigestException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.log4j.Logger;

/**
 * <p>
 * Title: 该类用于保存本系统用到的常用工具方法
 * </p>
 *
 * <p>
 * Description: 当系统中有常用、功能明确的方法均放置在本类中作为静态方法
 * </p>
 * 
 * @author jKF13181
 * @version [版本号, 2009-4-1]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ShaTools {
	private static final Logger logger = Logger.getLogger(ShaTools.class);

	/** 给getTimeString()使用，避免重复创建对象。 */
	@SuppressWarnings("unused")
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("EN"));

	/** 十六进制码 */
	private static final String[] HEX_DIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/** 十六进制 */
	private static final int HEX_NUM = 16;

	/**
	 * 该类仅用来保存静态方法，不应实例化。
	 */
	private ShaTools() {

	}

	/**
	 * 将调用该函数的线程休眠一段时间。
	 *
	 * @param millisecond
	 *            休眠的毫秒数。
	 */
	public static void sleep(long millisecond) {
		try {
			Thread.sleep(millisecond);
		} catch (InterruptedException ex) {
			// 异常处理
		}
	}

	/**
	 * 返回异常的堆栈信息。
	 *
	 * @param t
	 *            待处理的异常对象。
	 * @return 异常对象printStackTrace()方法输出的内容。
	 */
	public static String getStackTrace(Throwable t) {
		if (null == t) {
			return null;
		}
		StringWriter sw = new StringWriter(256);
		logger.error("ShaTools getStackTrace(Throwable t) error.." + sw.toString());
		// t.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	/**
	 * 比特到十六进制字符串的转换
	 * 
	 * @param b
	 *            byte 传入的比特值
	 * @return String 返回的十六进制字符串
	 */
	public static String byteToHexString(byte b) {
		int n = b;
		if (0 > n) {
			n = 256 + n;
		}
		int d1 = n / HEX_NUM;
		int d2 = n % HEX_NUM;
		return HEX_DIGITS[d1] + HEX_DIGITS[d2];
	}

	/**
	 * 比特数组到十六进制字符串的转换
	 * 
	 * @param b
	 *            byte[] 比特数组
	 * @return String 十六进制字符串
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer result = new StringBuffer(128);
		for (int i = 0; i < b.length; i++) {
			result.append(byteToHexString(b[i]));
		}
		return result.toString();
	}

	/**
	 * 计算MD5消息摘要。 (MD5摘要长度为16字节，保存摘要的数组长度要够，否则抛异常。)
	 *
	 * @param data
	 *            需要计算摘要的数据。
	 * @param offset
	 *            计算摘要的数据的起始偏移地址。
	 * @param length
	 *            数据的长度。
	 * @param digest
	 *            存放摘要的字节数组。
	 * @param dOffset
	 *            摘要存放起始位置。
	 */
	public static void md5(byte[] data, int offset, int length, byte[] digest, int dOffset) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(data, offset, length);
			md5.digest(digest, dOffset, HEX_NUM);
		} catch (NoSuchAlgorithmException ex) {
			logger.error("md5 NoSuchAlgorithmException exception", ex);
		} catch (DigestException ex) {
			logger.error("md5 DigestException exception", ex);
		}
	}

	/**
	 * 计算消息摘要。
	 *
	 * @param data
	 *            计算摘要的数据。
	 * @param offset
	 *            数据偏移地址。
	 * @param length
	 *            数据长度。
	 * @return 摘要结果。(16字节)
	 */
	public static byte[] md5(byte[] data, int offset, int length) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(data, offset, length);
			return md5.digest();
		} catch (NoSuchAlgorithmException ex) {
			logger.error("md5 NoSuchAlgorithmException exception", ex);
			return new byte[0];
		}
	}

	/**
	 * 计算消息摘要。
	 *
	 * @param data
	 *            计算摘要的数据。
	 * @param offset
	 *            数据偏移地址。
	 * @param length
	 *            数据长度。
	 * @return 摘要结果。(16字节)
	 */
	public static String md5Str(byte[] data, int offset, int length) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(data, offset, length);
			return byteArrayToHexString(md5.digest());
		} catch (NoSuchAlgorithmException ex) {
			logger.error("md5Str NoSuchAlgorithmException exception", ex);
			return null;
		}
	}

	/**
	 * 把String类型的IP地址转换为整数
	 *
	 * @param ip
	 *            IP地址
	 * @return ip对应的整数
	 */
	public static int getIntIP(String ip) {
		StringTokenizer token = new StringTokenizer(ip, ".");
		int[] intToken = new int[4];

		for (int i = 3; i >= 0; i--) {
			if (token.hasMoreTokens()) {
				intToken[i] = Integer.parseInt(token.nextToken());
			}
		}

		int ipaddr = (intToken[0]) | (intToken[1] << 8) | (intToken[2] << 16) | (intToken[3] << 24);
		return ipaddr;
	}

	/**
	 * 获取url
	 *
	 * @param paras
	 *            {host,cmsID,contentID,fileID} //两个cms怕contentid和fileid 重名
	 * @return 生成的url
	 */
	public static String getUrl(Object[] paras) {
		String url = null;
		if (4 == paras.length) {
			StringBuffer sb = new StringBuffer(128);
			sb.append(paras[0]);
			sb.append("?cmsID=");
			sb.append(paras[1]);
			sb.append("&ContentID=");
			sb.append(paras[2]);
			sb.append("&FileID=");
			sb.append(paras[3]);
			url = sb.toString();
		} else {
			// PortalLog.getLog().error(Thread.currentThread().getName() +
			// "count of paras error!");
		}
		return url;
	}

	/**
	 * 判断下载的内容是否为Kjava内容。 判断下载内容的mimeType里是否包含KJava内容的MimeType，
	 * 如果包含，则认为应该适配为MIDP协议
	 * 
	 * @param mimeType
	 *            String[] 下载内容的mimeType类型，其中可能包含DRM的mimeType类型， 应该是一个长度不为0的数组。
	 * @param jarMimeType
	 *            String[] 所有的KJava内容的MimeType类型，需要从配置文件中获取， 应该是一个长度不为0的数组。
	 * @return boolean true 是Kjava内容；false 不是Kjava内容
	 */
	public static boolean isKjavaContent(String[] mimeType, String[] jarMimeType) {
		boolean result = false;
		int mimeTypeLength = mimeType.length;
		int jarMimeTypeLength = jarMimeType.length;
		for (int i = 0; i < mimeTypeLength; i++) {
			for (int j = 0; j < jarMimeTypeLength; j++) {
				if (mimeType[i].equals(jarMimeType[j])) {
					result = true;
					return result;
				}
			}
		}
		return result;
	}

	// end add by luoshengli 2005-10-05 问题单A01D48539

	/**
	 * 计算消息SHA-1摘要。
	 *
	 * @param data
	 *            byte[] 计算摘要的数据。
	 * @param offset
	 *            int 数据偏移地址。
	 * @param length
	 *            int 数据长度。
	 * @return String 摘要结果。(20字节)
	 */
	public static String shaStr(byte[] data, int offset, int length) {
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			sha.update(data, offset, length);
			return byteArrayToHexString(sha.digest());
		} catch (NoSuchAlgorithmException ex) {
			logger.error("shaStr NoSuchAlgorithmException exception", ex);
			return null;
		}
	}
	
	public static String getMd5ByFile(String inputFile) throws IOException{
	    FileInputStream fileInputStream = null;
	    String md5Val = "";
	    try
        {
	        fileInputStream = new FileInputStream(inputFile);
	        md5Val = DigestUtils.md5Hex(IOUtils.toByteArray(fileInputStream)); 
        }
        finally{
            if(null != fileInputStream){
                fileInputStream.close();
            }
        }
	    return md5Val;
	}
	
	public static String fileMD5(String inputFile) throws IOException {
		// 缓冲区大小（这个可以抽出一个参数）
		int bufferSize = 256 * 1024;
		FileInputStream fileInputStream = null;
		DigestInputStream digestInputStream = null;
		try {
			// 拿到一个MD5转换器（同样，这里可以换成SHA1）
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// 使用DigestInputStream
			fileInputStream = new FileInputStream(inputFile);
			digestInputStream = new DigestInputStream(fileInputStream, messageDigest);
			// read的过程中进行MD5处理，直到读完文件
			byte[] buffer = new byte[bufferSize];
			while (digestInputStream.read(buffer) > 0)
				;
			// 获取最终的MessageDigest
			messageDigest = digestInputStream.getMessageDigest();
			// 拿到结果，也是字节数组，包含16个元素
			byte[] resultByteArray = messageDigest.digest();
			// 同样，把字节数组转换成字符串
			return ShaTools.byteArrayToHexString(resultByteArray);
		} catch (NoSuchAlgorithmException e) {
			return null;
		} finally {
			try {
				digestInputStream.close();
			} catch (Exception e) {
				logger.error("fileMD5 Error:", e);
			}
			try {
				fileInputStream.close();
			} catch (Exception e) {
				logger.error("fileMD5 Error:", e);
			}
		}
	}
}
