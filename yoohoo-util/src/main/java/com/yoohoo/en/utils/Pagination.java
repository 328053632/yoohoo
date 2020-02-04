/*
 * 文 件 名:  Pagination.java
 * 版    权:  Scint Med Co., Ltd. Copyright 2010-2011,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  Caohaijun
 * 修改时间:  2011-2-28
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */

package com.yoohoo.en.utils;

/**
 * 
 * <分页对象>
 * <功能详细描述>
 * 
 * @author  Caohaijun
 * @version  [MedHospital0001.1.0, 2011-3-3]
 * @see  [相关类/方法]
 * @since  [MedHospital0001/1.0]
 */
public class Pagination
{
    /**
     * 用来标示该分页对象，分页对象缓存时也是通过这个id去找到对应的分页信息的

     */
    private String id;
    
    /**
     * 总页数

     */
    private Integer totalPages = new Integer(0);;
    
    /**
     * 总行数

     */
    private Integer totalRows = new Integer(0);;
    
    /**
     * 一页中显示行数,默认10条每页

     */
    private Integer pageSize = new Integer(50);
    
    /**
     * 当前页

     */
    private Integer curPageIndex = new Integer(0);
    
    /**
     * 开始行
     */
    
    private Integer startRowIndex = new Integer(0);
    
    public Pagination()
    {
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public Integer getCurPageIndex()
    {
        return curPageIndex;
    }
    
    public void setCurPageIndex(Integer curPageIndex)
    {
        this.curPageIndex = curPageIndex;
    }
    
    public Integer getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public Integer getTotalPages()
    {
        if (totalRows == null || totalRows.equals(new Integer(0)))
        {
            return new Integer(0);
        }
        if (pageSize != null && pageSize.intValue() != 0)
        {
            totalPages = new Integer((totalRows.intValue()
                    + pageSize.intValue() - 1)
                    / pageSize.intValue());
        }
        return totalPages;
    }
    
    public Integer getTotalRows()
    {
        return totalRows;
    }
    
    public void setTotalRows(Integer totalRows)
    {
        this.totalRows = totalRows;
        acountStartRowIndex();
    }
    
    public Integer getStartRowIndex()
    {
        return this.startRowIndex;
    }
    
    public void setStartRowIndex(Integer startRowIndex)
    {
        this.startRowIndex = startRowIndex;
    }
    
    public Integer getEndRowIndex()
    {
        return new Integer(this.getStartRowIndex().intValue() + this.pageSize);
    }
    
    private void acountStartRowIndex()
    {
        if (this.getCurPageIndex() == null || this.getTotalPages() == null
                || this.getPageSize() == null)
        {
            this.startRowIndex = new Integer(0);
            return;
        }
        if (this.getCurPageIndex().intValue() > this.getTotalPages().intValue()-1)
        {
            this.setCurPageIndex(new Integer(this.getTotalPages().intValue()-1));
        }
        if (this.getCurPageIndex().intValue() <0)
        {
            this.setCurPageIndex(0);
        }
        int curRowIndex = this.getPageSize().intValue()
                * this.getCurPageIndex().intValue();
        
        this.startRowIndex = curRowIndex;
    }
    
    public void setTotalPages(Integer totalPages)
    {
        this.totalPages = totalPages;
    }
    
    /**
     * 得到属性字符串
     * @return String 属性字符串
     */
    public String toString()
    {
        String line = System.getProperty("line.separator");
        StringBuffer sb = new StringBuffer("{");
        sb.append(line);
        sb.append("id=").append(this.id).append(line);
        sb.append("curPageIndex=").append(this.curPageIndex).append(line);
        sb.append("pageSize=").append(this.pageSize).append(line);
        sb.append("totalRows=").append(this.totalRows).append(line);
        sb.append("startRowIndex=").append(this.startRowIndex).append(line);
        sb.append("totalPages=").append(this.totalPages).append(line);
        sb.append("}");
        return sb.toString();
    }
    
}