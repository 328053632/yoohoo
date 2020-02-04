package com.yoohoo.en.service;

import com.yoohoo.en.bean.response.CreateRoomResp;
import com.yoohoo.en.bean.response.RecodListResp;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ITalkCloudService {
	
	void startLessonCallBack(String roomId);
	
	void finishLessonCallBack(String roomId);
	
	void loginLogoutCallBack(String roomId,String userId,Integer roleType,Integer status);
	
	CreateRoomResp createOrUpdateRoom(Integer scheduleId,Integer lessonId,Integer chapterId,Integer classId,Integer teacherId);
	
	String getTeacherJoinRoomUrl(String roomId,Integer teacherId);
	
	String getStudentJoinRoomUrl(String roomId,Integer studentId);
	
	public String teacherEntryRoom(String roomId, String userPwd, String name, int userId);

	String getAssistTeacherUrl(String roomId);

    String getParentsJoinRoomUrl(String roomId, Integer userId);

	RecodListResp getRecordList(String roomId);

	Integer uploadFileByRoomId( String roomId, Map<String,InputStream> map, Integer shceduleId );

	String parentEntryRoom(String roomId, String userPwd, String name, int userId);

	List<String> getRoomFile(String roomId);

	 Integer deleteFileById(List<String> fileidarr);
}
