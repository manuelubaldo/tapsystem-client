package com.tap.tapsystem;

/**
 * Created by Lenovo ThinkPad X220 on 9/18/2016.
 */
public class FairCharging {
    private String Route;
    private String Destination;
    private String CardNo;
    private String Remarks;
    private Double Balance;
    private String PhoneNo;
    private String SeatNo;
    private String BusPlateNo;

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getCardNo() {
        return CardNo;
    }

    public void setCardNo(String cardNo) {
        CardNo = cardNo;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double balance) {
        Balance = balance;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getSeatNo() {
        return SeatNo;
    }

    public void setSeatNo(String seatNo) {
        SeatNo = seatNo;
    }

    public String getRoute() {
        return Route;
    }

    public void setRoute(String route) {
        Route = route;
    }

    public String getBusPlateNo() {
        return BusPlateNo;
    }

    public void setBusPlateNo(String busPlateNo) {
        BusPlateNo = busPlateNo;
    }
}
