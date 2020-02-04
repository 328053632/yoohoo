package com.yoohoo.en.web.lesson.service;

import com.yoohoo.en.dao.model.TLibrary;
import com.yoohoo.en.dao.model.TLibraryChapter;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created By LiWenLong On 2018/9/8 14:30
 * E-Mail:it_lwl@163.com
 */
public interface TLibraryChapterService {
    void
    saveBatchFile(List<TLibraryChapter> libraryChapterList);

    List<TLibraryChapter> selectByChapterId(Integer chapterId);

    List<TLibrary> selectFile(Integer chapterId);

    Map<String,byte[]>  downloadStream(List<TLibrary> urlList);

    void deleteByFileId(Integer fileid);

    Integer deleteById(Integer id);
}
