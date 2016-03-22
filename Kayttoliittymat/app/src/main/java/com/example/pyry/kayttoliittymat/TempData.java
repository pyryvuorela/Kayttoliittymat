package com.example.pyry.kayttoliittymat;

/**
 * Created by Pyry on 22.3.2016.
 */
public class TempData {
    public static String currentUser;
    public static String currentHouse;

    public void setCurrentUser(String currentUser){
        this.currentUser = currentUser;
    }
    public void setCurrentHouse(String currentHouse){
        this.currentHouse = currentHouse;
    }
    public String getCurrentUser(){
        return  currentUser;
    }
    public String getCurrentHouse(){
        return currentHouse;
    }


}
