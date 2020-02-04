package com.yoohoo.en.model;

import java.util.List;

public class TreeModel
{
    private String title;

    private boolean expand;

    private List<TreeModel> children;

    private int id;

    private int level;

    private int parentId;

    public TreeModel()
    {
    }

    public TreeModel(Builder builder)
    {
        this.title = builder.title;
        this.expand = builder.expand;
        this.children = builder.children;
        this.id = builder.id;
        this.level = builder.level;
        this.parentId = builder.parentId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public boolean isExpand()
    {
        return expand;
    }

    public void setExpand(boolean expand)
    {
        this.expand = expand;
    }

    public List<TreeModel> getChildren()
    {
        return children;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getParentId()
    {
        return parentId;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("TreeModel{");
        sb.append("title='").append(title).append('\'');
        sb.append(", expand=").append(expand);
        sb.append(", children=").append(children);
        sb.append(", id=").append(id);
        sb.append(", level=").append(level);
        sb.append('}');
        return sb.toString();
    }

    public void setChildren(List<TreeModel> children)
    {
        this.children = children;
    }

    public static class Builder
    {
        private String title;

        private boolean expand;

        private List<TreeModel> children;

        private int id;

        private int level;

        private int parentId;

        public Builder title(String title)
        {
            this.title = title;
            return this;
        }

        public Builder expand(boolean expand)
        {
            this.expand = expand;
            return this;
        }

        public Builder children(List<TreeModel> children)
        {
            this.children = children;
            return this;
        }

        public Builder id(int id)
        {
            this.id = id;
            return this;
        }

        public Builder level(int level)
        {
            this.level = level;
            return this;
        }

        public Builder parentId(int parentId){
            this.parentId = parentId;
            return this;
        }

        public TreeModel build()
        {
            return new TreeModel(this);
        }
    }
}
