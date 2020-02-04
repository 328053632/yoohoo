package com.yoohoo.en.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yoohoo.en.fastdfs.FastDFSClient;
import com.yoohoo.en.fastdfs.FastDFSException;
import com.yoohoo.en.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String saveMultipartFile(MultipartFile file) {

        	//1.上传到fastDFS服务器中
            FastDFSClient fastDFSClient=new FastDFSClient();
            try {
                return fastDFSClient.uploadFileWithMultipart(file);
            } catch (FastDFSException e) {
                e.printStackTrace();
                return null;
            }
	}
}
