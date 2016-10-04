package com.tap.tapsystem;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends Activity {

    int userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();
        final Bundle bd = intent.getExtras();

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);

        if(bd!=null){
            userid = (int) bd.get("userid");
            String username = (String) bd.get("username");
            TextView lblUsername = (TextView) findViewById(R.id.labelUsername );
            lblUsername.setText(username);

            Button paymentButton = (Button) findViewById(R.id.payment);

            paymentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MenuActivity.this,SeatsActivity.class);
                    intent.putExtra("userid",userid);
                    startActivity(intent);
                }
            });
        }else{
            Toast.makeText(MenuActivity.this,"Error Occured",Toast.LENGTH_LONG).show();
        }

    }
}
