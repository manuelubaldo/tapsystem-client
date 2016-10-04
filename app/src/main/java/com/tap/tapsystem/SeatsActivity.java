package com.tap.tapsystem;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeatsActivity extends Activity implements View.OnClickListener {

    private int userID;
    private int iBusID;
    private String tBusPlateNo;
    private int iRouteID;
    private String tRouteName;
    private int iSeatNo;
    private ApiInterface api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_seats);
        api = ApiClient.client().create(ApiInterface.class);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd!=null){
            for(int i = 1; i<61 ; i++){
                int resourceID = getResources().getIdentifier("toggleButton" + i,"id",SeatsActivity.this.getPackageName());

                if(resourceID!=0){
                    ToggleButton tg = (ToggleButton) findViewById(resourceID);
                    tg.setOnClickListener(SeatsActivity.this);
                }
            }
            userID = bd.getInt("userid");
            loadBusAssignment();
        }



    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void onClick(View view) {
        int resourceID = view.getId();

        ToggleButton tg = (ToggleButton) findViewById(resourceID);
        if(tg.getText()!=""){

        }

        for(int i = 1; i<61 ; i++){
            int resID = getResources().getIdentifier("toggleButton" + i,"id",SeatsActivity.this.getPackageName());
            if (resourceID == resID){
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("iBusID",iBusID);
                intent.putExtra("iRouteID",iRouteID);
                intent.putExtra("tBusPlateNo",tBusPlateNo);
                intent.putExtra("tRouteName",tRouteName);
                intent.putExtra("iSeatNo",i);
                startActivity(intent);
                break;
            }
        }
    }

    public void loadBusAssignment() {
        Call<BusAssignment> busAssignment = api.busAssignment(userID);
        busAssignment.enqueue(new Callback<BusAssignment>() {
            @Override
            public void onResponse(Call<BusAssignment> call, Response<BusAssignment> response) {
                BusAssignment ba = response.body();
                iBusID = ba.iBusID;
                iRouteID = ba.iRouteID;
                tBusPlateNo = ba.tBusPlateNo;
                tRouteName = ba.tRouteName;
            }

            @Override
            public void onFailure(Call<BusAssignment> call, Throwable t) {
                Toast.makeText(SeatsActivity.this,"An error has occured",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
