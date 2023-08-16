package com.zyf.pojo;

public class EngineerInformation
{
    private int id;
    private String engineerName;
    private String city;
    private String telephone;
    private String newPassword;
    private String onboarding;
    private String department;

    public String getOnboarding()
    {
        return onboarding;
    }

    public void setOnboarding(String onboarding)
    {
        this.onboarding = onboarding;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword(String newPassword)
    {
        this.newPassword = newPassword;
    }

    public EngineerInformation()
    {

    }

    public EngineerInformation(String engineerName, String city, String telephone, String newPassword, String onboarding, String department)
    {
        this.engineerName = engineerName;
        this.city = city;
        this.telephone = telephone;
        this.newPassword = newPassword;
        this.onboarding = onboarding;
        this.department = department;
    }

    public EngineerInformation(String engineerName, String city, String telephone)
    {
        this.engineerName = engineerName;
        this.city = city;
        this.telephone = telephone;
    }

    public EngineerInformation(int id, String engineerName, String city, String telephone)
    {
        this.id = id;
        this.engineerName = engineerName;
        this.city = city;
        this.telephone = telephone;
    }

    public EngineerInformation(int id, String engineerName, String city, String telephone, String newPassword)
    {
        this.id = id;
        this.engineerName = engineerName;
        this.city = city;
        this.telephone = telephone;
        this.newPassword = newPassword;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getEngineerName()
    {
        return engineerName;
    }

    public void setEngineerName(String engineerName)
    {
        this.engineerName = engineerName;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    @Override
    public String toString()
    {
        return "EngineerInformation{" +
                "id=" + id +
                ", engineerName='" + engineerName + '\'' +
                ", city='" + city + '\'' +
                ", telephone='" + telephone + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", onboarding='" + onboarding + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
