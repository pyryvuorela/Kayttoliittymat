package com.example.pyry.kayttoliittymat;

/**
 * Created by Pyry on 10.3.2016.
 */
public class User {
    private String userName;
    private String userPassword;
    private String userHouse;

    public User(String name, String password, String house){
        this.userName = name;
        this.userPassword = password;
        this.userHouse = house;
    }
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHouse() {
        return userHouse;
    }

    public void setUserHouse(String userHouse) {
        this.userHouse = userHouse;
    }
}
