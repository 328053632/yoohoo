package com.yoohoo.en.mcore.utils;

import com.yoohoo.en.mcore.xss.SQLFilter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 *
 * @author YuanzongInfo
 * @email admin@yuanzonginfo.com
 * @date 2017-03-14 23:15
 */
public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	//当前页码
    private int page = 1;
    //每页条数
    private int limit = 10;
    // 偏移量
    private int offset = 0;

    public Query(Map<String, Object> params){
        this.putAll(params);
        String sidx = null;
        String order = null;
        //分页参数
        try
        {
            this.page = Integer.parseInt(params.get("page").toString());
            this.limit = Integer.parseInt(params.get("limit").toString());
            //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
            sidx = (String)params.get("sidx");
            order = (String)params.get("order");
        }
        catch (Exception e)
        {

        }


        this.offset = (page - 1) * limit;
        this.put("offset", offset);
        this.put("page", page);
        this.put("limit", limit);

        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        this.put("sidx", SQLFilter.sqlInject(sidx));
        this.put("order", SQLFilter.sqlInject(order));
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset()
    {
        return offset;
    }

    public void setOffset(int offset)
    {
        this.offset = offset;
    }
}
