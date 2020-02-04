package com.yoohoo.en.utils;

import java.net.URLEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class TalkMD5Util {
	/***
	 * md5加密
	 * @param md5
	 * @return
	 */
	public static String MD5(String md5) 
	{
		try
		{
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte anArray : array) {
				sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} 
		catch (java.security.NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
			return null;
		}

	}
/**
 * 密码编码
 * @param content
 * @param key
 * @return
 */
	public static String encrypts(String content, String key)
	{
		if(key == null || key.length() != 16)
		{
			System.err.println("AES key 的长度必须是16位！");
			return null;
		}
		try
		{
			Cipher cipher = Cipher.getInstance("AES/ECB/NOPadding");
			int blockSize = cipher.getBlockSize();
			byte[] dataBytes = content.getBytes();
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0)
			{
				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
			}
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, keyspec);
			byte[] encrypted = cipher.doFinal(plaintext);
			if (encrypted == null){
				return null;
			}
			char[] hexArray = "0123456789abcdef".toCharArray();
			char[] hexChars = new char[encrypted.length * 2];
			for (int j = 0; j < encrypted.length; j++) {
				int v = encrypted[j] & 0xFF;
				hexChars[j * 2] = hexArray[v >>> 4];
				hexChars[j * 2 + 1] = hexArray[v & 0x0F];
			}
			return new String(hexChars);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 输入中文和特殊字符加密
	 * @param str
	 * @return
	 */
	public static String encode(String str) { 
		String strUTF8 = null;
		try {  
			strUTF8 = URLEncoder.encode(str, "UTF-8"); 
			System.out.println(strUTF8); 
		} catch (Exception e) {  
			e.printStackTrace();  
		} 
		return strUTF8;
	}  
}
