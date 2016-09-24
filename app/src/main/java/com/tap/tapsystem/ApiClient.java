package com.tap.tapsystem;
/**
 * Created by Lenovo ThinkPad X220 on 9/18/2016.
 */
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    public static final String BASE_URL = "http://192.168.254.108:50598/api/";
    private static Retrofit retrofit = null;

    public static Retrofit client(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }


}
