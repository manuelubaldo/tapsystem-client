package com.tap.tapsystem;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Lenovo ThinkPad X220 on 9/18/2016.
 */
public interface ApiInterface {

    @POST("charging")
    Call<FairCharging> charge(@Body FairCharging fair);

}
