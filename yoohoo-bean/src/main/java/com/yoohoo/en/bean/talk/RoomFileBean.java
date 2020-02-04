package com.yoohoo.en.bean.talk;

/**
 * Created By LiWenLong On 2018/11/5 10:25
 * E-Mail:it_lwl@163.com
 */
import java.util.List;

public class RoomFileBean {

    private int result;
    private List<Roomfile> roomfile;
    public void setResult(int result) {
        this.result = result;
    }
    public int getResult() {
        return result;
    }

    public void setRoomfile(List<Roomfile> roomfile) {
        this.roomfile = roomfile;
    }
    public List<Roomfile> getRoomfile() {
        return roomfile;
    }

}