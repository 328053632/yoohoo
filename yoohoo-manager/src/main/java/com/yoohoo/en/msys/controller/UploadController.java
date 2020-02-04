package com.yoohoo.en.msys.controller;

import com.yoohoo.en.mcore.utils.R;
import com.yoohoo.en.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/upload")
public class UploadController
{
	@Autowired
	private FileService fileService;

    @RequestMapping(value = "/uploadCover")
    public R uploadCover(HttpServletRequest request)
    {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("file");
        String fileUrl = fileService.saveMultipartFile(file);
        String fileName = file.getOriginalFilename();
        return R.ok().put("tempPath", fileUrl).put("fileName",fileName.substring(0,fileName.lastIndexOf(".")));
    }
}
