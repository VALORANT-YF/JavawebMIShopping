package com.zyf.pojo;

public class Engineer
{
    private int id;
    private String workNumber;
    private String password;

    public Engineer()
    {

    }

    public Engineer(int id, String workNumber, String password)
    {
        this.id = id;
        this.workNumber = workNumber;
        this.password = password;
    }

    public Engineer(String workNumber, String password)
    {
        this.workNumber = workNumber;
        this.password = password;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getWorkNumber()
    {
        return workNumber;
    }

    public void setWorkNumber(String workNumber)
    {
        this.workNumber = workNumber;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "Engineer{" +
                "id=" + id +
                ", workNumber='" + workNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
