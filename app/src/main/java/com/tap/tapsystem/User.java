package com.tap.tapsystem;

/**
 * Created by Lenovo ThinkPad X220 on 10/2/2016.
 */

public class User {

    private int iUserID;
    private String tAccountType;
    private String tUserName;
    private String tPassword;

    public String gettPassword() {
        return tPassword;
    }

    public void settPassword(String tPassword) {
        this.tPassword = tPassword;
    }

    public String gettUserName() {
        return tUserName;
    }

    public void settUserName(String tUserName) {
        this.tUserName = tUserName;
    }

    public String gettAccountType() {
        return tAccountType;
    }

    public void settAccountType(String tAccountType) {
        this.tAccountType = tAccountType;
    }

    public int getiUserID() {
        return iUserID;
    }

    public void setiUserID(int iUserID) {
        this.iUserID = iUserID;
    }
}
