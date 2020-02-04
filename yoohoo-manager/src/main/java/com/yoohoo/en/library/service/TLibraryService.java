package com.yoohoo.en.library.service;

import com.yoohoo.en.dao.model.TLibrary;
import com.yoohoo.en.dao.model.TLibraryExample;
import com.yoohoo.en.fastdfs.FastDFSException;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

/**
 * Created By LiWenLong On 2018/9/4 13:39
 * E-Mail:it_lwl@163.com
 */
public interface TLibraryService {
    List<TLibrary> queryList(TLibraryExample example);

    int count(TLibraryExample example);

    void save(TLibrary tLibrary,String realPath) throws IOException;

    TLibrary queryById(Integer fileid);

    Integer deleteFile(String fileUrl) throws FastDFSException;

    void deleteByFileId(Integer fileid);

    List<TLibrary> queryAllList();

    List<TLibrary> queryAllListByFileType(Integer fileType);

    TLibrary findById(Integer fileId);

    void updataeByFileId(TLibrary tLibrary);

    List<Integer> saveMultipartFile(List<MultipartFile> file, String fileType);
}
