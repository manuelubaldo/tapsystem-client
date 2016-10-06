package com.tap.tapsystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;



import java.util.ArrayList;
import java.util.List;

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
    private List<Integer> SeatsTaken = new ArrayList<Integer>();
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

        Button aisleButton = (Button) findViewById(R.id.aisle);
        aisleButton.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void onClick(View view) {
        final int resourceID = view.getId();

        if(resourceID == R.id.aisle){
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("iBusID",iBusID);
            intent.putExtra("iRouteID",iRouteID);
            intent.putExtra("tBusPlateNo",tBusPlateNo);
            intent.putExtra("tRouteName",tRouteName);
            intent.putExtra("iSeatNo",0);
            startActivity(intent);
        }else{


            for(int i = 1; i<61 ; i++){
                int resID = getResources().getIdentifier("toggleButton" + i,"id",SeatsActivity.this.getPackageName());
                if (resourceID == resID){
                    final ToggleButton tg = (ToggleButton) findViewById(resID);
                    if(SeatsTaken.contains(i)){
                        Toast.makeText(SeatsActivity.this,tg.getText() + " " + i,Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(SeatsActivity.this);

                        final int finalI = i;
                        builder
                                .setMessage("Seat was already taken. Do you want to proceed?")
                                .setPositiveButton("Proceed",  new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        tg.setText("Ok");
                                        Intent intent = new Intent(SeatsActivity.this,MainActivity.class);
                                        intent.putExtra("iBusID",iBusID);
                                        intent.putExtra("iRouteID",iRouteID);
                                        intent.putExtra("tBusPlateNo",tBusPlateNo);
                                        intent.putExtra("tRouteName",tRouteName);
                                        intent.putExtra("iSeatNo", finalI);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,int id) {
                                        tg.setText("Ok");
                                        dialog.cancel();
                                    }
                                })
                                .show();
                    }else{
                        SeatsTaken.add(i);
                        Toast.makeText(SeatsActivity.this,tg.getText(),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SeatsActivity.this,MainActivity.class);
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
