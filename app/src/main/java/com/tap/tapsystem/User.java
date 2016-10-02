package com.tap.tapsystem;

/**
 * Created by Lenovo ThinkPad X220 on 10/2/2016.
 */

public class User {

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
}
