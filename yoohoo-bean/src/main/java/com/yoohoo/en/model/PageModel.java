package com.yoohoo.en.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装分页类
 * 实现分页查询
 * @param <T>
 */
public class PageModel<T> {
    //结果集
    private List<T> data;
    //当前页
    private int currentPage;
    //每页多少条记录
    private int pageSize;
    //总页数
    private int totalPage;

    private boolean hasNext;

    private boolean hasPrev;
    //分页项集合
    private List<PageItem> items;

    public PageModel(int currentPage, int pageSize, List<T> data, int total) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.data = data;
        this.totalPage = (total - 1) / pageSize + 1;
        this.hasNext = this.currentPage < this.totalPage;
        this.hasPrev = this.currentPage > 1;
        this.items = pageItems();
    }

    private List<PageItem> pageItems() {
        List<PageItem> list = new ArrayList<>();

        list.add(new PageItem(true, Integer.toString(currentPage), currentPage));

        if (currentPage - 1 >= 1) {
            list.add(0, new PageItem(true, Integer.toString(currentPage - 1), currentPage - 1));
        }
        if (currentPage - 2 >= 1) {
            list.add(0, new PageItem(true, Integer.toString(currentPage - 2), currentPage - 2));
        }
        if (currentPage + 1 <= totalPage) {
            list.add(new PageItem(true, Integer.toString(currentPage + 1), currentPage + 1));
        }
        if (currentPage + 2 <= totalPage) {
            list.add(new PageItem(true, Integer.toString(currentPage + 2), currentPage + 2));
        }

        if (list.size() == 5) {
            if (list.get(0).getPage() > 1) {
                list.add(0, new PageItem(false, "...", 0));
            }
            if (list.get(4).getPage() < totalPage) {
                list.add(new PageItem(false, "...", 0));
            }
        } else {
            int size = list.size();

            if (list.get(0).getPage() == 1) {
                int len = 5 - size;
                int page = list.get(size - 1).getPage();
                for (int i = 0; i < len; i++) {
                    ++page;
                    if (page <= totalPage) {
                        list.add(new PageItem(true, Integer.toString(page), page));
                    } else {
                        break;
                    }
                }
                if (list.get(list.size() - 1).getPage() < totalPage) {
                    list.add(new PageItem(false, "...", 0));
                }
            } else if (list.get(size - 1).getPage() == totalPage) {
                int len = 5 - size;
                int page = list.get(0).getPage();
                for (int i = 0; i < len; i++) {
                    page--;
                    if (page >= 1) {
                        list.add(0, new PageItem(true, Integer.toString(page), page));
                    } else {
                        break;
                    }
                }
                if (list.get(0).getPage() > 1) {
                    list.add(0, new PageItem(false, "...", 0));
                }
            }
        }
        return list;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrev() {
        return hasPrev;
    }

    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    public List<PageItem> getItems() {
        return items;
    }

    public void setItems(List<PageItem> items) {
        this.items = items;
    }
}
