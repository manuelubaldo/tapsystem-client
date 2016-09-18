package com.tap.tapsystem;

import java.util.Date;

/**
 * Created by Lenovo ThinkPad X220 on 9/18/2016.
 */
public class City {
    private int iCity;
    private String tCityName;
    private String tCreatedby;
    private Date dDateCreated;
    private String tModifiedBy;
    private Date dDateModified;
    private boolean bActive;

    public City() {
    }


    public int getiCity() {
        return iCity;
    }

    public void setiCity(int iCity) {
        this.iCity = iCity;
    }

    public String gettCityName() {
        return tCityName;
    }

    public void settCityName(String tCityName) {
        this.tCityName = tCityName;
    }

    public String gettCreatedby() {
        return tCreatedby;
    }

    public void settCreatedby(String tCreatedby) {
        this.tCreatedby = tCreatedby;
    }

    public Date getdDateCreated() {
        return dDateCreated;
    }

    public void setdDateCreated(Date dDateCreated) {
        this.dDateCreated = dDateCreated;
    }

    public String gettModifiedBy() {
        return tModifiedBy;
    }

    public void settModifiedBy(String tModifiedBy) {
        this.tModifiedBy = tModifiedBy;
    }

    public Date getdDateModified() {
        return dDateModified;
    }

    public void setdDateModified(Date dDateModified) {
        this.dDateModified = dDateModified;
    }

    public boolean isbActive() {
        return bActive;
    }

    public void setbActive(boolean bActive) {
        this.bActive = bActive;
    }
}
