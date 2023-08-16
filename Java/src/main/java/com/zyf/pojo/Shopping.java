package com.zyf.pojo;

import java.io.OutputStream;
import java.io.Serializable;

public class Shopping implements Serializable //Shopping类可被实例化
{
    private int id ;
    private String addressee ; //收件人
    private String brandName ; //商品名称
    private String brandImg ; //商品图片
    private String receiptAddress ; //收件地址
    private String shoppingAccount ; //购买商品的账号
    private int price ; //商品价格
    private int brandNumber;//商品数量
    public Shopping()
    {

    }

    public Shopping(int id, String addressee, String brandName, String brandImg, String receiptAddress, String shoppingAccount , int price  , int brandNumber)
    {
        this.id = id;
        this.addressee = addressee;
        this.brandName = brandName;
        this.brandImg = brandImg;
        this.receiptAddress = receiptAddress;
        this.shoppingAccount = shoppingAccount;
        this.price = price;
        this.brandNumber = brandNumber;
    }

    public Shopping(String addressee, String brandName, String brandImg, String receiptAddress, String shoppingAccount , int price , int brandNumber)
    {
        this.addressee = addressee;
        this.brandName = brandName;
        this.brandImg = brandImg;
        this.receiptAddress = receiptAddress;
        this.shoppingAccount = shoppingAccount;
        this.price = price;
        this.brandNumber = brandNumber;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getAddressee()
    {
        return addressee;
    }

    public void setAddressee(String addressee)
    {
        this.addressee = addressee;
    }

    public String getBrandName()
    {
        return brandName;
    }

    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }

    public String getBrandImg()
    {
        return brandImg;
    }

    public void setBrandImg(String brandImg)
    {
        this.brandImg = brandImg;
    }

    public String getReceiptAddress()
    {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress)
    {
        this.receiptAddress = receiptAddress;
    }

    public String getShoppingAccount()
    {
        return shoppingAccount;
    }

    public void setShoppingAccount(String shoppingAccount)
    {
        this.shoppingAccount = shoppingAccount;
    }

    public int getBrandNumber()
    {
        return brandNumber;
    }

    public void setBrandNumber(int brandNumber)
    {
        this.brandNumber = brandNumber;
    }

    @Override
    public String toString()
    {
        return "Shopping{" +
                "id=" + id +
                ", addressee='" + addressee + '\'' +
                ", brandName='" + brandName + '\'' +
                ", brandImg='" + brandImg + '\'' +
                ", receiptAddress='" + receiptAddress + '\'' +
                ", shoppingAccount='" + shoppingAccount + '\'' +
                ", price=" + price +
                ", brandNumber=" + brandNumber +
                '}';
    }
}
