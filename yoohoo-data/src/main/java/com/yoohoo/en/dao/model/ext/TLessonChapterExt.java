package com.yoohoo.en.dao.model.ext;


import com.yoohoo.en.dao.model.TLessonChapter;
import org.apache.commons.beanutils.BeanUtils;

public class TLessonChapterExt extends TLessonChapter {
    private boolean isLight=false;

    public boolean isLight() {
        return isLight;
    }

    public void setLight(boolean light) {
        isLight = light;
    }

    public TLessonChapterExt copyFrom(TLessonChapter c)
    {
        if(null != c)
        {
            try {
                BeanUtils.copyProperties(this,c);
            } catch (Exception e) {
            }
        }
        return this;
    }
}
