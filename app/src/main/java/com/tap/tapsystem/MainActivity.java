package com.tap.tapsystem;

import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Spinner RoutesSpinner;
    private Spinner DestinationsSpinner;
    private NfcAdapter nfc;
    private ApiInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RoutesSpinner = (Spinner) findViewById(R.id.routes);
        DestinationsSpinner = (Spinner) findViewById(R.id.destinations);

        ArrayAdapter<CharSequence> routes = ArrayAdapter.createFromResource(this,R.array.routes,android.R.layout.simple_spinner_dropdown_item);
        routes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        RoutesSpinner.setAdapter(routes);

        ArrayAdapter<CharSequence> destinations = ArrayAdapter.createFromResource(this,R.array.destinations,android.R.layout.simple_spinner_dropdown_item);
        destinations.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DestinationsSpinner.setAdapter(destinations);


        nfc = NfcAdapter.getDefaultAdapter(this);
        if (nfc==null){
            Toast.makeText(MainActivity.this, "NFC Not Supported", Toast.LENGTH_LONG).show();
        }

        //loadSubRoutes();

        //SendSMS("5554","Hello World");
    }

    public void OnClick(View view){
        EditText samplenfcno = (EditText) findViewById(R.id.samplenfcno);
        Spinner dest = (Spinner) findViewById(R.id.destinations);


        api = ApiClient.client().create(ApiInterface.class);
        FairCharging fair = new FairCharging();
        fair.setCardNo(samplenfcno.getText().toString());
        fair.setDestination(dest.getSelectedItem().toString());
        fair.setRemarks("");
        Call<FairCharging> call = api.charge(fair);

        call.enqueue(new Callback<FairCharging>() {
            @Override
            public void onResponse(Call<FairCharging> call, Response<FairCharging> response) {
                FairCharging result = response.body();
                final TextView remarks = (TextView) findViewById(R.id.remarks);
                remarks.setText(response.body().getRemarks());
                //SendSMS(response.body().getPhoneNo(),"Thank you for patronizing our Services.");
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
                TextView remarks = (TextView) findViewById(R.id.remarks);
                remarks.setText(t.getMessage());
            }
        });
    }

    private void SendSMS(String phoneNo , String message){
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNo,null,message,null,null);
    }

    private void loadSubRoutes()
    {
        Call<List<SubRoutes>> call = api.getSubRoutes();
        call.enqueue(new Callback<List<SubRoutes>>() {
            @Override
            public void onResponse(Call<List<SubRoutes>> call, Response<List<SubRoutes>> response) {
                List<SubRoutes> subRoutes = response.body();
                List<String> subRoute = new ArrayList<String>();

                for(SubRoutes s : subRoutes){
                    subRoute.add(s.gettRouteName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this ,android.R.layout.simple_spinner_dropdown_item,subRoute);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                DestinationsSpinner.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<SubRoutes>> call, Throwable t) {

            }
        });
    }

}
