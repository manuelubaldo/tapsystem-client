package com.tap.tapsystem;

import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Spinner RoutesSpinner;
    private Spinner DestinationsSpinner;
    private NfcAdapter nfc;

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

        SendSMS("5554","Hello World");
        ApiInterface api = ApiClient.client().create(ApiInterface.class);
        FairCharging fair = new FairCharging();
        fair.setCardNo("20160000000001");
        fair.setDestination("Brgy. Sampaloc II");
        fair.setRemarks("");

        Call<FairCharging> call = api.charge(fair);
        call.enqueue(new Callback<FairCharging>() {
            @Override
            public void onResponse(Call<FairCharging> call, Response<FairCharging> response) {
                FairCharging result = response.body();
                Log.d(TAG,"Charging : " + result.toString());
            }

            @Override
            public void onFailure(Call<FairCharging> call, Throwable t) {

            }
        });

    }

    private void SendSMS(String phoneNo , String message){
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNo,null,message,null,null);
    }

}
