package com.yoohoo.en.web.lesson.service.impl;

import com.yoohoo.en.dao.mapper.TLibraryChapterMapper;
import com.yoohoo.en.dao.mapper.TLibraryMapper;
import com.yoohoo.en.dao.model.TLibrary;
import com.yoohoo.en.dao.model.TLibraryChapter;
import com.yoohoo.en.dao.model.TLibraryChapterExample;
import com.yoohoo.en.fastdfs.FastDFSClient;
import com.yoohoo.en.fastdfs.FastDFSException;
import com.yoohoo.en.web.lesson.service.TLibraryChapterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By LiWenLong On 2018/9/8 14:31
 * E-Mail:it_lwl@163.com
 */

@Service
public class TLibraryChapterServiceImpl  implements TLibraryChapterService {
    
    @Autowired
    TLibraryChapterMapper tLibraryChapterMapper;

    @Autowired
    TLibraryMapper tLibraryMapper;
    @Override
    public void saveBatchFile(List<TLibraryChapter> libraryChapterList) {

      if(libraryChapterList.size()>0){
            //增加文件类型
            for(TLibraryChapter tLibraryChapter:libraryChapterList){
                TLibrary library = tLibraryMapper.selectByPrimaryKey(tLibraryChapter.getFileId());
                tLibraryChapter.setFileType(library.getFileType());
            }
            Integer chapterId = libraryChapterList.get(0).getChapterId();
            List<TLibraryChapter> addList=new ArrayList<>();
            for(TLibraryChapter tLibraryChapter:libraryChapterList){
                TLibraryChapterExample example=new TLibraryChapterExample();
                example.createCriteria().andChapterIdEqualTo(chapterId).andFileIdEqualTo(tLibraryChapter.getFileId());
                List<TLibraryChapter> tLibraryChapterList1 = tLibraryChapterMapper.selectByExample(example);
                if(tLibraryChapterList1.size()>0){
                    TLibraryChapter bean = tLibraryChapterList1.get(0);
                    bean.setStatus(tLibraryChapter.getStatus());
                    tLibraryChapterMapper.updateByExampleSelective(bean,example);
                }else{
                    addList.add(tLibraryChapter);
                }
            }
            if(addList.size()>0){
                tLibraryChapterMapper.insertBatch(addList);
            }
         /*   this.deleteChapterFile(chapterId,fileIdList);*/
        }
    }

    @Override
    public List<TLibraryChapter> selectByChapterId(Integer chapterId) {
        TLibraryChapterExample example=new TLibraryChapterExample();
        example.createCriteria().andChapterIdEqualTo(chapterId).andStatusEqualTo(1);
        List<TLibraryChapter> list = tLibraryChapterMapper.selectByExample(example);

        for(TLibraryChapter tLibraryChapter:list){
            TLibrary library = tLibraryMapper.selectByPrimaryKey(tLibraryChapter.getFileId());
            tLibraryChapter.setFileName(library.getFileName());
        }
        return list;
    }


 /*   private void deleteChapterFile(Integer chapterId, List<Integer> fileIdList) {
        //使用not in 来对不在数组中的文件id 的状态进行修改
        TLibraryChapterExample example=new TLibraryChapterExample();
        TLibraryChapterExample.Criteria criteria = example.createCriteria();
        criteria.andChapterIdEqualTo(chapterId);
        if(CollectionUtils.isNotEmpty(fileIdList)){
            criteria.andFileIdNotIn(fileIdList);
        }
        TLibraryChapter bean =new TLibraryChapter();
        bean.setStatus(0);
        tLibraryChapterMapper.updateByExampleSelective(bean,example);
    }*/


    /**
     * 获取文件路径
     * @param chapterId
     * @return
     */
    @Override
    public List<TLibrary> selectFile(Integer chapterId) {

        TLibraryChapterExample example=new TLibraryChapterExample();
        example.createCriteria().andChapterIdEqualTo(chapterId).andStatusEqualTo(1).andFileTypeEqualTo(2);
        List<TLibraryChapter> libraryChapters = tLibraryChapterMapper.selectByExample(example);
        List<TLibrary> urlList=new ArrayList<>();
        if(libraryChapters.size()>0){
            for (TLibraryChapter t:libraryChapters) {
                //根据fileId 查询文件url
                TLibrary library = tLibraryMapper.selectByPrimaryKey(t.getFileId());
                urlList.add(library);
            }
            return urlList;
        }
        return  null;
    }

    @Override
    public   Map<String,byte[]> downloadStream( List<TLibrary> urlList) {
        FastDFSClient fastDFSClient=new FastDFSClient();
        Map<String,byte[]>  map=new HashMap<>();
        for (TLibrary t:urlList){
            try {
                byte[] download = fastDFSClient.download(t.getFileUrl());
                map.put(t.getFileId()+"-"+t.getFileName(),download);
            } catch (FastDFSException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    @Override
    public void deleteByFileId(Integer fileid) {

        TLibraryChapterExample example=new TLibraryChapterExample();
        example.createCriteria().andFileIdEqualTo(fileid);
        tLibraryChapterMapper.deleteByExample(example);
    }

    @Override
    @Transactional(isolation=Isolation.SERIALIZABLE)
    public Integer deleteById(Integer id) {
        TLibrary tLibrary=new TLibrary();
        TLibraryChapterExample example=new TLibraryChapterExample();
        example.createCriteria().andIdEqualTo(id);
        List<TLibraryChapter> libraryChapters = tLibraryChapterMapper.selectByExample(example);
        if(null!=libraryChapters && libraryChapters.size()>0){

            TLibraryChapter tLibraryChapter = libraryChapters.get(0);
            Integer fileId = tLibraryChapter.getFileId();
           tLibrary = tLibraryMapper.selectByPrimaryKey(fileId);
        }

        //删除关联课件
        tLibraryChapterMapper.deleteByPrimaryKey(id);
        //删除课件

        if(null!=tLibrary){
            String fileUrl = tLibrary.getFileUrl();
            if(fileUrl!=null){
                FastDFSClient fastDFSClient=new FastDFSClient();
                try {
                    int i = fastDFSClient.deleteFile(fileUrl);
                    if(i==0){
                        tLibraryMapper.deleteByPrimaryKey(tLibrary.getFileId());
                        return i;
                    }
                    return null;
                } catch (FastDFSException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
       /* TLibraryChapter bean=new TLibraryChapter();
        bean.setStatus(0);
        tLibraryChapterMapper.updateByExampleSelective(bean,example);*/
    }

}
