package com.yoohoo.en.utils.vcode;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * ttf字体文件
 *
 * @author dsna
 */
public class ImgFontByte
{
    /**
     * ttf字体文件的十六进制字符串
     */
    private static byte[] ttf1 = null;


    private static void loadByte()
    {
        try
        {
            ClassPathResource font = new ClassPathResource("font.ttf");
            InputStream inputStream = font.getInputStream();
            ttf1 = StreamUtils.copyToByteArray(inputStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public Font getFont(int fontHeight)
    {
        if (ttf1 == null)
        {
            loadByte();
        }
        try
        {
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, new ByteArrayInputStream(ttf1));
            return baseFont.deriveFont(Font.PLAIN, fontHeight);
        }
        catch (Exception e)
        {
            return new Font("Arial", Font.PLAIN, fontHeight);
        }
    }

}