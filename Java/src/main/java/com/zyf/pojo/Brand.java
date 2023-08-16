package com.zyf.pojo;

import java.io.Serializable;

public class Brand implements Serializable//Brand类可被实例化
{
    private int id;
    private String brandName;
    private String companyName;
    private String ordered;
    private String status;
    private String imageUrl;

    private int flag;
    public Brand()
    {

    }
    public String getStatusStr()
    {
        if(status == null)
        {
            return "未知";
        }
        return Integer.parseInt(status) == 0 ? "禁售":"启用";
    }

    public int getFlag()
    {
        return flag;
    }

    public void setFlag(int flag)
    {
        this.flag = flag;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }
    public Brand(int id, String brandName, String companyName, String ordered, String status)
    {
        this.id = id;
        this.brandName = brandName;
        this.companyName = companyName;
        this.ordered = ordered;
        this.status = status;
    }

    public Brand(int id, String brandName, String companyName, String ordered, String status, String imageUrl)
    {
        this.id = id;
        this.brandName = brandName;
        this.companyName = companyName;
        this.ordered = ordered;
        this.status = status;
        this.imageUrl = imageUrl;
    }

    public Brand(String brandName, String companyName, String ordered, String status)
    {
        this.brandName = brandName;
        this.companyName = companyName;
        this.ordered = ordered;
        this.status = status;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getBrandName()
    {
        return brandName;
    }

    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getOrdered()
    {
        return ordered;
    }

    public void setOrdered(String ordered)
    {
        this.ordered = ordered;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "Brand{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", ordered='" + ordered + '\'' +
                ", status='" + status + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", flag=" + flag +
                '}';
    }
}
