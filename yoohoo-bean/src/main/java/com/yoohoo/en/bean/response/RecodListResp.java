package com.yoohoo.en.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created By LiWenLong On 2018/8/6 16:13
 * E-Mail:it_lwl@163.com
 *
 * 请求录像回放地址
 */
public class RecodListResp   implements Serializable {

    //返回结果
    private String result;
    //返回视频列表
    private List<Record> recordlist;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Record> getRecordlist() {
        return recordlist;
    }

    public void setRecordlist(List<Record> recordlist) {
        this.recordlist = recordlist;
    }

    @Override
    public String toString() {
        return "RecodListResp{" +
                "result='" + result + '\'' +
                ", recordlist=" + recordlist +
                '}';
    }
}
