package com.yoohoo.en.dao.model;

import java.io.Serializable;

public class TCategoryInfo implements Serializable {
    private Integer id;

    private String categoryName;

    private Integer level;

    private Integer parentId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    private TCategoryInfo(Builder b) {
        id = b.id;
        categoryName = b.categoryName;
        level = b.level;
        parentId = b.parentId;
    }

    public TCategoryInfo() {
        super();
    }

    public static class Builder {
        private Integer id;

        private String categoryName;

        private Integer level;

        private Integer parentId;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder categoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public Builder level(Integer level) {
            this.level = level;
            return this;
        }

        public Builder parentId(Integer parentId) {
            this.parentId = parentId;
            return this;
        }

        public TCategoryInfo build() {
            return new TCategoryInfo(this);
        }
    }
}