package com.example.lindsyguidera.catalicious;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final android.widget.EditText etUsername = (android.widget.EditText) findViewById(R.id.etUsername);
        final android.widget.EditText etPassword = (android.widget.EditText) findViewById(R.id.etPassword);
        final android.widget.Button bLogin = (android.widget.Button) findViewById(R.id.bLogin);
        final android.widget.TextView registerLink = (android.widget.TextView) findViewById(R.id.tvRegisterHere);

        registerLink.setOnClickListener(new android.view.View.OnClickListener()
        {
            @Override
            public void onClick(android.view.View v)
            {
                android.content.Intent  registerIntent = new android.content.Intent(Login.this, register.class);
                Login.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                                    if (success){

                                        String name = jsonResponse.getString("name");
                                        String age = jsonResponse.getString("age");

                                        Intent intent =new Intent(Login.this, user_area.class);
                                        intent.putExtra("name", name);
                                        intent.putExtra("username", username);
                                        intent.putExtra("age", age);

                                        Login.this.startActivity(intent);

                                    }
                            else{
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                        builder.setMessage("Login Failed")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }


                    }
                };
                LoginRequest LoginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(LoginRequest);
            }
        });

    }
}
