package com.yoohoo.en.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public enum PathUtil
{
    INSTANCE;

    /**
     * 封面图片保存到服务的的相对路径跟目录
     */
    public static final String CAVER_SAVE_HOME = "statics/cover/";
    /**
     *
     * 将教师图片存储到服务器的相对路径的根目录
     */
    public static  final String TEACHER_SAVE_HOME="statics/teacher/";

    /**
     * 分面图片保存到服务的的相对路径跟目录(临时存放, 保存后会移动到对应的目录)
     */
    public static final String CAVER_TEMP_SAVE_HOME = "cover/temp/";

    public static String siteRootPath;

    public String siteRootPath(HttpServletRequest request)
    {
        if (StringUtils.isEmpty(siteRootPath))
        {
            String realPath = request.getSession().getServletContext().getRealPath(File.separator);
            siteRootPath = realPath.endsWith(File.separator) ? realPath : realPath + File.separator;
        }
        return siteRootPath;
    }

    public String randomFileName(String extension)
    {
        return UUID.randomUUID().toString().replaceAll("-", "") + "." + extension;
    }

    public void checkDir(String path)
    {
        File file = new File(path);
        if (!file.exists())
        {
            file.mkdirs();
        }
    }

    public String moveFile(String originalFileName, String newFileName, HttpServletRequest request)
    {
        String siteRootPath = siteRootPath(request);

        File originalFile = new File(siteRootPath + originalFileName);
        File newFile = new File(siteRootPath + newFileName);
        checkDir(newFile.getParent());
        try
        {
            FileUtils.copyFile(originalFile, newFile);
            originalFile.delete();
            return newFileName;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return originalFile.getName();
        }
    }
}
