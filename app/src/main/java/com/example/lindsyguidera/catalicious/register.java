package com.example.lindsyguidera.catalicious;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final android.widget.EditText etAge = (android.widget.EditText) findViewById(R.id.etAge);
        final android.widget.EditText etName = (android.widget.EditText) findViewById(R.id.etName);
        final android.widget.EditText etUsername = (android.widget.EditText) findViewById(R.id.etUsername);
        final android.widget.EditText etPassword = (android.widget.EditText) findViewById(R.id.etPassword);
        final android.widget.Button bRegister = (android.widget.Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(android.view.View v){
                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final int age = Integer.parseInt(etAge.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                        JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                Intent intent = new Intent(register.this, Login.class);
                                        register.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
                                builder.setMessage("RegisterFailed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                };


                RegisterRequest registerRequest = new RegisterRequest(name, username, age, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(register.this);
                queue.add(registerRequest);
            }
        });

    }
}
