package com.tap.tapsystem;

import android.support.annotation.Nullable;

import java.util.Date;

/**
 * Created by Lenovo ThinkPad X220 on 9/22/2016.
 */
public class SubRoutes {
    private int iSubRouteID;
    private String iRouteID;
    private String tRouteName;
    private int iCityID;
    private double nFareAmount;
    private String tCreatedBy;

    @Nullable
    private Date dDateCreated;

    @Nullable
    private String tModifiedBy;

    @Nullable
    private Date dDateModified;

    @Nullable
    private boolean bActive;

    public int getiSubRouteID() {
        return iSubRouteID;
    }

    public void setiSubRouteID(int iSubRouteID) {
        this.iSubRouteID = iSubRouteID;
    }

    public String getiRouteID() {
        return iRouteID;
    }

    public void setiRouteID(String iRouteID) {
        this.iRouteID = iRouteID;
    }

    public void settRouteName(String tRouteName) {
        this.tRouteName = tRouteName;
    }

    public int getiCityID() {
        return iCityID;
    }

    public void setiCityID(int iCityID) {
        this.iCityID = iCityID;
    }

    public double getnFareAmount() {
        return nFareAmount;
    }

    public void setnFareAmount(double nFareAmount) {
        this.nFareAmount = nFareAmount;
    }

    public String gettCreatedBy() {
        return tCreatedBy;
    }

    public void settCreatedBy(String tCreatedBy) {
        this.tCreatedBy = tCreatedBy;
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

    public String gettRouteName() {
        return tRouteName;
    }
}
