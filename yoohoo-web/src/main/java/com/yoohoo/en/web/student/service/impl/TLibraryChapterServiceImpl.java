package com.yoohoo.en.web.student.service.impl;

import com.yoohoo.en.dao.mapper.TLibraryChapterMapper;
import com.yoohoo.en.dao.mapper.TLibraryMapper;
import com.yoohoo.en.dao.model.TLibrary;
import com.yoohoo.en.dao.model.TLibraryChapter;
import com.yoohoo.en.dao.model.TLibraryChapterExample;
import com.yoohoo.en.dao.model.TLibraryExample;
import com.yoohoo.en.web.student.service.TLibraryChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By LiWenLong On 2018/10/23 14:20
 * E-Mail:it_lwl@163.com
 */
@Service("libraryChapterService")
public class TLibraryChapterServiceImpl implements TLibraryChapterService {

    @Autowired
    TLibraryChapterMapper tLibraryChapterMapper;


    @Autowired
    TLibraryMapper tLibraryMapper;

    @Override
    public List<TLibrary> getFiles(Integer chapterId,Integer type) {
        TLibraryChapterExample example=new TLibraryChapterExample();
        example.createCriteria().andChapterIdEqualTo(chapterId).andFileTypeEqualTo(type).andStatusEqualTo(1);
        List<TLibraryChapter> libraryChapters = tLibraryChapterMapper.selectByExample(example);
        if(libraryChapters.size()==0){
            return null;
        }else{
            List<TLibrary> tLibraryList=new ArrayList<>();
            for(TLibraryChapter t:libraryChapters){
                TLibraryExample e=new TLibraryExample();
                e.createCriteria().andFileIdEqualTo(t.getFileId());
                List<TLibrary> LibraryList = tLibraryMapper.selectByExample(e);
                if(LibraryList.size()>0){
                    tLibraryList.add(LibraryList.get(0));
                }else{
                    return null;
                }
            }
            return  tLibraryList;
        }
    }
}
