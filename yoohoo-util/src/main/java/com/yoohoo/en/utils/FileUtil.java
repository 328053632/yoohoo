package com.yoohoo.en.utils;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	    /**
	     * 判断文件大小
	     *
	     * @param len
	     *            文件长度
	     * @param size
	     *            限制大小
	     * @param unit
	     *            限制单位（B,K,M,G）
	     * @return
	     */
	    public static boolean checkFileSize(List<MultipartFile> fileList, int size, String unit) {
	    	Long len = 0L;
	    	for(MultipartFile e:fileList){
	    		len += e.getSize();
	    	}
	        double fileSize = 0;
	        if ("B".equals(unit.toUpperCase())) {
	            fileSize = (double) len;
	        } else if ("K".equals(unit.toUpperCase())) {
	            fileSize = (double) len.intValue() / 1024;
	        } else if ("M".equals(unit.toUpperCase())) {
	            fileSize = (double) len.intValue() / 1048576;
	        } else if ("G".equals(unit.toUpperCase())) {
	            fileSize = (double) len.intValue() / 1073741824;
	        }
	        if (fileSize > size) {
	            return false;
	        }
	        return true;
	    }
}

