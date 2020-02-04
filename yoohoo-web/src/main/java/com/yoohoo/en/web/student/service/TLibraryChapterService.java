package com.yoohoo.en.web.student.service;

import java.util.List;

import com.yoohoo.en.dao.model.TLibrary;

/**
 * Created By LiWenLong On 2018/10/23 14:20
 * E-Mail:it_lwl@163.com
 */

public interface TLibraryChapterService {
    List<TLibrary> getFiles(Integer chapterId,Integer type);
}
