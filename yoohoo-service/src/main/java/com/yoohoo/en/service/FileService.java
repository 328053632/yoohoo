package com.yoohoo.en.service;


import org.springframework.web.multipart.MultipartFile;


public interface FileService
{
	 public String saveMultipartFile(MultipartFile file);
}
