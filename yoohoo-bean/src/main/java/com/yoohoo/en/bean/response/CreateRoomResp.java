package com.yoohoo.en.bean.response;

public class CreateRoomResp extends BaseResp {

	private static final long serialVersionUID = 7081940271889311282L;

	private String roomId;
	private Long scheduleId;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

}
