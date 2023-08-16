package com.zyf.pojo;

public class UserInformation
{
    private int id;
    private String avatarPath;
    private String nickname;
    private int sex;//1为男 0为女
    private String account;
    private String city;

    public UserInformation()
    {

    }

    public UserInformation(int id, String avatarPath, String nickname, int sex, String account, String city)
    {
        this.id = id;
        this.avatarPath = avatarPath;
        this.nickname = nickname;
        this.sex = sex;
        this.account = account;
        this.city = city;
    }

    public UserInformation(String avatarPath, String nickname, int sex, String account, String city)
    {
        this.avatarPath = avatarPath;
        this.nickname = nickname;
        this.sex = sex;
        this.account = account;
        this.city = city;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getAvatarPath()
    {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath)
    {
        this.avatarPath = avatarPath;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public int getSex()
    {
        return sex;
    }

    public void setSex(int sex)
    {
        this.sex = sex;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return "UserInformation{" +
                "id=" + id +
                ", avatarPath='" + avatarPath + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", account='" + account + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
