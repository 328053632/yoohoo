package com.yoohoo.en.library.service.impl;

import com.yoohoo.en.dao.mapper.TLibraryMapper;
import com.yoohoo.en.dao.model.TLibrary;
import com.yoohoo.en.dao.model.TLibraryExample;
import com.yoohoo.en.dao.model.TLibraryExample.Criteria;
import com.yoohoo.en.fastdfs.FastDFSClient;
import com.yoohoo.en.fastdfs.FastDFSException;
import com.yoohoo.en.fastdfs.TrackerServerPool;
import com.yoohoo.en.library.service.TLibraryService;
import com.yoohoo.en.mcore.utils.ShiroUtils;
import com.yoohoo.en.msys.entity.SysUserEntity;
import com.yoohoo.en.msys.service.SysUserRoleService;

import org.apache.shiro.SecurityUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created By LiWenLong On 2018/9/4 13:42
 * E-Mail:it_lwl@163.com
 */

@Service
public class TLibraryServiceImpl implements TLibraryService {

    FastDFSClient fastDFSClient=new FastDFSClient();
    @Autowired

    TLibraryMapper tLibraryMapper;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;

    @Override
    public List<TLibrary> queryList(TLibraryExample example) {
        List<TLibrary> tLibraries = tLibraryMapper.selectByExample(example);
        return tLibraries;
    }

    @Override
    public int count(TLibraryExample example) {
        int i = tLibraryMapper.countByExample(example);
        return i;
    }


    @Override
    public void save(TLibrary tLibrary,String realPath){
        try {
            //1.完善信息
            tLibrary.setCreateTime(new Date());
            //2.获取临时文件路径
            String fileUrl = tLibrary.getFileUrl();
            String FileExtName = fileUrl.substring(fileUrl.lastIndexOf(".") + 1);
            String fileName = tLibrary.getFileName();
            tLibrary.setFileName(fileName+"."+FileExtName);
            //3.通过根据指定的路径上传文件
            String upload = fastDFSClient.upload(realPath+fileUrl, null);
            //3.存储成功后获取路径，更新fielUrl，将相关信息存储到数据库中
            tLibrary.setFileUrl(upload);
            //4.获取文件信息
            FileInfo fileInfo = null;
            TrackerServer trackerServer = TrackerServerPool.borrowObject();
            StorageClient1 storageClient = new StorageClient1(trackerServer, null);
            fileInfo = storageClient.get_file_info1(upload);
            // 返还对象
            TrackerServerPool.returnObject(trackerServer);
            double fileSize = Double.parseDouble( Long.toString(fileInfo.getFileSize()))/1024.0/1024.0;
            tLibrary.setFileSize(String.valueOf(fileSize).substring(0,4)+"MB");
            tLibraryMapper.insert(tLibrary);
        } catch (FastDFSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

    }

    @Override
    public TLibrary queryById(Integer fileid) {

        return tLibraryMapper.selectByPrimaryKey(fileid);
    }

    @Override
    public Integer deleteFile(String fileUrl) throws FastDFSException {
        //删除文件
        int i = 0;

            i = fastDFSClient.deleteFile(fileUrl);

        return i;
    }

    @Override
    public void deleteByFileId(Integer fileid) {
        tLibraryMapper.deleteByPrimaryKey(fileid);
    }


    @Override
    public List<TLibrary> queryAllList() {
        return tLibraryMapper.queryAllFileList();
    }


    @Override
    public List<TLibrary> queryAllListByFileType(Integer fileType) {
        TLibraryExample example=new TLibraryExample();
        example.setOrderByClause("file_name");
        Criteria criteria = example.createCriteria();
        criteria.andFileTypeEqualTo(fileType);
        if(!sysUserRoleService.isAdmin(ShiroUtils.getUserId()))
        	criteria.andUserIdEquals(ShiroUtils.getUserId()); 
        return tLibraryMapper.selectByExample(example);
    }


    @Override
    public TLibrary findById(Integer fileId) {
        TLibrary library = tLibraryMapper.selectByPrimaryKey(fileId);
        return library;
    }

    @Override
    public void updataeByFileId(TLibrary tLibrary) {
        tLibraryMapper.updateByPrimaryKey(tLibrary);
    }


    /**
     *
     *  文件上传到服务器然后存储到数据库中
     * @param file
     * @param fileType
     */
    @Override
    public  List<Integer> saveMultipartFile(List<MultipartFile> file, String fileType) {


        List<Integer> fileIdList=new ArrayList<>();
        SysUserEntity principal = (SysUserEntity ) SecurityUtils.getSubject().getPrincipal();
        //1.上传到fastDFS服务器中
        for (MultipartFile mf:file){
            FastDFSClient fastDFSClient=new FastDFSClient();
            TLibrary tLibrary=new TLibrary();
            try {
                String fileUrl = fastDFSClient.uploadFileWithMultipart(mf);
                tLibrary.setFileUrl(fileUrl);
                tLibrary.setFileSize(String.valueOf(mf.getSize()/1024.0/1024.0));
                tLibrary.setFileType(Integer.parseInt(fileType));
                tLibrary.setFileName(mf.getOriginalFilename());
                tLibrary.setCreateTime(new Date());
                tLibrary.setUplaodUser(principal.getUsername());
                tLibraryMapper.insert(tLibrary);
                fileIdList.add(tLibrary.getFileId());
            } catch (FastDFSException e) {
                /*if(fileIdList.size()>0){
                    for(Integer id:fileIdList){
                        TLibraryExample example=new TLibraryExample();
                        example.createCriteria().andFileIdEqualTo(id);
                        tLibraryMapper.deleteByExample(example);
                    }
                }*/
                e.printStackTrace();
                return null;
            }
        }
        return fileIdList;
    }

}
