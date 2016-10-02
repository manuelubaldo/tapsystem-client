package com.tap.tapsystem;

import android.Manifest;
import android.app.Activity;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Spinner RoutesSpinner;
    private Spinner DestinationsSpinner;
    private NfcAdapter nfc;
    private ApiInterface api;
    private TextView remarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);

        api = ApiClient.client().create(ApiInterface.class);

        RoutesSpinner = (Spinner) findViewById(R.id.routes);
        DestinationsSpinner = (Spinner) findViewById(R.id.destinations);

        nfc = NfcAdapter.getDefaultAdapter(this);
        if (nfc==null){
            //Toast.makeText(MainActivity.this, "NFC Not Supported", Toast.LENGTH_LONG).show();
        }

        remarks = (TextView) findViewById(R.id.remarks);
        loadSubRoutes();
        loadBuses();


    }


    public void OnClick(View view){
        EditText samplenfcno = (EditText) findViewById(R.id.samplenfcno);
        Spinner dest = (Spinner) findViewById(R.id.destinations);
        Spinner route = (Spinner) findViewById(R.id.routes);
        Spinner seats = (Spinner) findViewById(R.id.seats);
        Spinner plateno = (Spinner) findViewById(R.id.plateno);

        remarks.setText("Please Wait...");
        api = ApiClient.client().create(ApiInterface.class);
        FairCharging fair = new FairCharging();
        fair.setCardNo(samplenfcno.getText().toString());
        fair.setDestination(dest.getSelectedItem().toString());
        fair.setRoute(route.getSelectedItem().toString());
        fair.setBusPlateNo(plateno.getSelectedItem().toString());
        fair.setRemarks("");
        fair.setSeatNo(seats.getSelectedItem().toString());
        Call<FairCharging> call = api.charge(fair);

        call.enqueue(new Callback<FairCharging>() {
            @Override
            public void onResponse(Call<FairCharging> call, Response<FairCharging> response) {
                FairCharging result = response.body();

                remarks.setText(response.body().getRemarks());
                SendSMS(response.body().getPhoneNo(),"Thank you for patronizing our Services.");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        remarks.setText("Please Tap your Card");
                    }
                },1500);
            }

            @Override
            public void onFailure(Call<FairCharging> call, Throwable t) {
                remarks.setText(t.getMessage());
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        remarks.setText("Please Tap your Card");
                    }
                },1500);
            }
        });
    }

    private void SendSMS(String phoneNo , String message){
        try{
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNo,null,message,null,null);
            Toast.makeText(MainActivity.this,"SMS was sent",Toast.LENGTH_LONG).show();
        }
        catch (Exception ex){
            Toast.makeText(MainActivity.this,"Unable to send SMS",Toast.LENGTH_LONG).show();
            Log.d(TAG,ex.getMessage());
        }

    }

    private void loadSubRoutes()
    {
        Call<List<SubRoutes>> call = api.getSubRoutes();
        call.enqueue(new Callback<List<SubRoutes>>() {
            @Override
            public void onResponse(Call<List<SubRoutes>> call, Response<List<SubRoutes>> response) {
                List<SubRoutes> subRoutes = response.body();
                Log.d(TAG,"Length : " + subRoutes.size());
                List<String> subRoute = new ArrayList<>();

                for(SubRoutes s : subRoutes){
                    subRoute.add(s.gettRouteName());
                }
                Log.d(TAG,"Length : " + subRoute.size());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this ,android.R.layout.simple_spinner_dropdown_item,subRoute);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                DestinationsSpinner.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<SubRoutes>> call, Throwable t) {
                Log.d(TAG + " - loadSubRoutes",t.toString());
            }
        });

        Call<List<Routes>> call2 = api.getRoutes();
        call2.enqueue(new Callback<List<Routes>>() {
            @Override
            public void onResponse(Call<List<Routes>> call, Response<List<Routes>> response) {
                List<Routes> routes = response.body();
                Log.d(TAG,"Length : " + routes.size());
                List<String> route = new ArrayList<>();

                for(Routes s : routes){
                    route.add(s.getRouteName());
                }
                Log.d(TAG,"Length : " + route.size());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this ,android.R.layout.simple_spinner_dropdown_item,route);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                RoutesSpinner.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Routes>> call, Throwable t) {
                Log.d(TAG + " - loadSubRoutes",t.toString());
            }
        });

        List<String> seats = new ArrayList<>();
        for(int i = 1 ;i<=60;i++){
            seats.add("" + i);
        }

        ArrayAdapter<String> seatsAdapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,seats);
        seatsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner seatsSpinner = (Spinner) findViewById(R.id.seats);
        seatsSpinner.setAdapter(seatsAdapter);
    }

    public void loadBuses(){
        Call<List<Bus>> call = api.getBuses();
        call.enqueue(new Callback<List<Bus>>() {
            @Override
            public void onResponse(Call<List<Bus>> call, Response<List<Bus>> response) {
                List<Bus> buses = response.body();
                List<String> bus = new ArrayList<>();
                for(Bus b:buses){
                    bus.add(b.gettPlateNo());
                }
                ArrayAdapter<String> busAdapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,bus);
                busAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Spinner BusSpinner = (Spinner) findViewById(R.id.plateno);
                BusSpinner.setAdapter(busAdapter);
            }

            @Override
            public void onFailure(Call<List<Bus>> call, Throwable t) {

            }
        });

    }

}
