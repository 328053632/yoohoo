package com.yoohoo.en.web.servlet;

import com.yoohoo.en.constant.ApplicationConstant;
import com.yoohoo.en.utils.vcode.ValidateCode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ValidateCodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest reqeust,
            HttpServletResponse response) throws ServletException, IOException {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        
        HttpSession session = reqeust.getSession();
        
        ValidateCode vCode = new ValidateCode(90,34,4,50);
        session.setAttribute(ApplicationConstant.SESSION_VALIDATECODE, vCode.getCode());
        vCode.write(response.getOutputStream());
    }

}