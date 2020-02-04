package com.yoohoo.en.web.resp;

import com.yoohoo.en.bean.response.BaseResp;
import com.yoohoo.en.dao.model.TClassDefine;

import java.util.List;

public class ClassTypeListResp extends BaseResp
{
    private static final long serialVersionUID = 7986178250708649474L;

    private List<TClassDefine> classDefines;

    private List<String> lessonTimes;

    public List<TClassDefine> getClassDefines()
    {
        return classDefines;
    }

    public void setClassDefines(List<TClassDefine> classDefines)
    {
        this.classDefines = classDefines;
    }

    public List<String> getLessonTimes()
    {
        return lessonTimes;
    }

    public void setLessonTimes(List<String> lessonTimes)
    {
        this.lessonTimes = lessonTimes;
    }
}
