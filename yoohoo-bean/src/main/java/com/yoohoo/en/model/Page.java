package com.yoohoo.en.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page<T>
{
    
    public Page(long totalRec, List<T> data, int curPage, int pageSize)
    {
        this.data = data;
        this.totalCount = totalRec;
        this.totalPage = (int)(((totalRec - 1) / pageSize) + 1);
        this.currentPage = curPage;
        this.pageSize = pageSize;
        
        if (curPage + 1 <= this.totalPage)
        {
            this.hasNext = true;
        }
        
        if (curPage - 1 > 0)
        {
            this.hasLast = true;
        }
        
        otherParam = new HashMap<String, Object>();
    }
    
    public void putParam(String key, Object value){
        otherParam.put(key, value);
    }
    
    public Map<String, Object> getOtherParam()
    {
        return otherParam;
    }
    
    private int pageSize;
    
    private List<T> data;
    
    private boolean hasLast;
    
    private boolean hasNext;
    
    private int currentPage;
    
    private int totalPage;
    
    private long totalCount;
    
    public long getTotalCount()
    {
        return totalCount;
    }
    
    public void setTotalCount(long totalCount)
    {
        this.totalCount = totalCount;
    }
    
    private Map<String, Object> otherParam;
    
    public int getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public List<T> getData()
    {
        return data;
    }
    
    public void setData(List<T> data)
    {
        this.data = data;
    }
    
    public boolean isHasLast()
    {
        return hasLast;
    }
    
    public void setHasLast(boolean hasLast)
    {
        this.hasLast = hasLast;
    }
    
    public boolean isHasNext()
    {
        return hasNext;
    }
    
    public void setHasNext(boolean hasNext)
    {
        this.hasNext = hasNext;
    }
    
    public int getCurrentPage()
    {
        return currentPage;
    }
    
    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }
    
    public int getTotalPage()
    {
        return totalPage;
    }
    
    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }
}
