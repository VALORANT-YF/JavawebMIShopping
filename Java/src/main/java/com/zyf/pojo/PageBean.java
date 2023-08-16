package com.zyf.pojo;

import java.util.List;

//分页查询的JavaBean
public class PageBean<T>
{
    private long totalCount; //总记录数

    private List<T> rows; //当前页数据

    public long getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(long totalCount)
    {
        this.totalCount = totalCount;
    }

    public List<T> getRows()
    {
        return rows;
    }

    public void setRows(List<T> rows)
    {
        this.rows = rows;
    }
}
