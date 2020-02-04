package com.yoohoo.en.model;

import java.io.Serializable;

/**
 * 分页项容器
 */
public class PageItem implements Serializable
{
    private static final long serialVersionUID = 5255825884443081137L;

    private boolean click;

    private String text;

    private int page;

    public PageItem(boolean click, String text, int page)
    {
        this.click = click;
        this.text = text;
        this.page = page;
    }

    public boolean isClick()
    {
        return click;
    }

    public void setClick(boolean click)
    {
        this.click = click;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }
}
