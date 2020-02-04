package com.yoohoo.en.bean.response;

import com.yoohoo.en.bean.ScheduleInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ScheduleListResponse extends BaseResp
{
    private static final long serialVersionUID = -1904031309022342791L;

    /**
     * 课表关系, key: 星期几, value: 课程表
     */
    private Map<String, List<ScheduleInfo>> scheduleMapper;

    /**
     * 日期对应关系
     */
    private Map<String, Date> dateMapper;

    public Map<String, List<ScheduleInfo>> getScheduleMapper()
    {
        return scheduleMapper;
    }

    public void setScheduleMapper(Map<String, List<ScheduleInfo>> scheduleMapper)
    {
        this.scheduleMapper = scheduleMapper;
    }

    public Map<String, Date> getDateMapper()
    {
        return dateMapper;
    }

    public void setDateMapper(Map<String, Date> dateMapper)
    {
        this.dateMapper = dateMapper;
    }
}
