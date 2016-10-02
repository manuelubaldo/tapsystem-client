package com.tap.tapsystem;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends Activity
{
    Button sign_in,sign_up;
    public static EditText username, password;
    private ApiInterface api;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        api = ApiClient.client().create(ApiInterface.class);

        username = (EditText)findViewById(R.id.textUsername);
        password = (EditText)findViewById(R.id.textPassword);

        sign_in=(Button)findViewById(R.id.buttonSignIn);
        sign_up=(Button)findViewById(R.id.buttonSignUp);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_in.setEnabled(false);
                User user = new User();
                user.settPassword(password.getText().toString());
                user.settUserName(username.getText().toString());
                Call<User> call = api.login(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d("MainActivity",response.toString());
                        User user = response.body();
                        if(user.getiUserID()==0){
                            Toast.makeText(HomeActivity.this,"Invalid Username or Password.",Toast.LENGTH_LONG).show();

                        }else{
                            Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        sign_in.setEnabled(true);
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(HomeActivity.this,"Unable to Connect." + t.getMessage(),Toast.LENGTH_LONG).show();
                        sign_in.setEnabled(true);
                    }
                });
            }
        });

    }



}