package com.tap.tapsystem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Lenovo ThinkPad X220 on 9/18/2016.
 */
public interface ApiInterface {

    @POST("charging")
    Call<FairCharging> charge(@Body FairCharging fair);

    @GET("subroutes")
    Call<List<SubRoutes>>  getSubRoutes();

    @GET("routes")
    Call<List<Routes>>  getRoutes();

    @GET("bus")
    Call<List<Bus>> getBuses();

    @POST("login")
    Call<User> login(@Body User user);

}
