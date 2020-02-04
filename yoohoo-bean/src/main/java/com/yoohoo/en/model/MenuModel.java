package com.yoohoo.en.model;

import java.io.Serializable;
import java.util.List;

public class MenuModel implements Serializable
{
    private static final long serialVersionUID = -8743375391035729426L;

    private boolean show;

    private String name;

    private int id;

    private List<MenuModel> items;

    public boolean isShow()
    {
        return show;
    }

    public void setShow(boolean show)
    {
        this.show = show;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public List<MenuModel> getItems()
    {
        return items;
    }

    public void setItems(List<MenuModel> items)
    {
        this.items = items;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("MenuModel{");
        sb.append("show=").append(show);
        sb.append(", name='").append(name).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
