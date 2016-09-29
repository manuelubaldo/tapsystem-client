package com.tap.tapsystem;

/**
 * Created by Lenovo ThinkPad X220 on 9/25/2016.
 */
public class Routes {
    private String tFrom;
    private String tTo;

    public String getRouteName(){
        return this.tFrom + " to " + this.tTo;
    }

}
